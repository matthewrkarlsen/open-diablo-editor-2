/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.txt for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */
package org.d1ablo.ode.types;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.d1ablo.ode.bintool.BinEditTool;
import org.d1ablo.ode.knowledge.DungeonTypes;
import org.d1ablo.ode.knowledge.SpecialLevels;
import org.d1ablo.ode.types.subtype.OneByteSetting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Holds all the data relating to a single quest within the game.
 */
public class Quest {
	
	private final static Logger logger = LoggerFactory.getLogger(Quest.class);

	private BinEditTool binEditTool;

	@JsonProperty("dungeonLevelSingle")
	private OneByteSetting dungeonLevelSingle;

	@JsonProperty("dungeonLevelMulti")
	private int dungeonLevelMulti;

	@JsonProperty("dungeonType")
	private int dungeonType;

	@JsonProperty("questNumber")
	private int questNumber;

	@JsonProperty("byteFourValue")
	private int byteFourValue;

	@JsonProperty("specialLevel")
	private int specialLevel; //SP only

	@JsonProperty("zeroOne")
	private int zeroOne;

	@JsonProperty("zeroTwo")
	private int zeroTwo;

	@JsonProperty("mpTriggerFlag")
	private long mpTriggerFlag;

	@JsonProperty("textEntryIDX")
	private long textEntryIDX;

	@JsonProperty("slotNumber")
	private int slotNumber;

	public Quest(int slotNumber, OneByteSetting dungeonLevelSingle, int dungeonLevelMulti, int dungeonType,
				 int questNumber, int byteFourValue, int specialLevel, int zeroOne, int zeroTwo, long mpTriggerFlag,
				 long textEntryIDX, BinEditTool binEditTool) {
		this.binEditTool = binEditTool;
		this.slotNumber = slotNumber;
		this.dungeonLevelSingle = dungeonLevelSingle;
		this.dungeonLevelMulti = dungeonLevelMulti;
		this.dungeonType = dungeonType;
		this.questNumber = questNumber;
		this.byteFourValue = byteFourValue;
		this.specialLevel = specialLevel; //SP only
		this.zeroOne = zeroOne;
		this.zeroTwo = zeroTwo;
		this.mpTriggerFlag = mpTriggerFlag;
		this.textEntryIDX = textEntryIDX;
	}

	@JsonCreator
	public Quest(@JacksonInject BinEditTool binEditTool) {
		this.binEditTool = binEditTool;
	}

	private Quest() {

	}

    public void printQuest(){
		logger.info("Slot number: " + slotNumber + " (hex: " + Integer.toHexString(slotNumber) + ")");
		logger.info("Dungeon level single: " + dungeonLevelSingle.getValue());
		logger.info("Dungeon level multi: " + dungeonLevelMulti);
		if(dungeonType >= 0 && dungeonType <= 4){
			logger.info("Dungeon type: " + DungeonTypes.DUNGEON_TYPES[dungeonType]);
		}
		logger.info("Quest number: " + questNumber);
		logger.info("Unknown: " + byteFourValue);
		logger.info("Special level: " + SpecialLevels.SPECIAL_LEVELS[specialLevel]);
		logger.info("Zero: " + zeroOne);
		logger.info("Zero: " + zeroTwo);
		logger.info("Multi player trigger flag: " + mpTriggerFlag);
		logger.info("");
	}

	@JsonIgnore
	public byte[] getQuestAsBytes() {
		byte[] questAsBytes = new byte[16];
		questAsBytes[0] = dungeonLevelSingle.asByte();
		questAsBytes[1] = binEditTool.convertIntToByte(dungeonLevelMulti);
		questAsBytes[2] = binEditTool.convertIntToByte(dungeonType);
		questAsBytes[3] = binEditTool.convertIntToByte(questNumber);
		questAsBytes[4] = binEditTool.convertIntToByte(byteFourValue);
		questAsBytes[5] = binEditTool.convertIntToByte(specialLevel);
		questAsBytes[6] = binEditTool.convertIntToByte(zeroOne);
		questAsBytes[7] = binEditTool.convertIntToByte(zeroTwo);
		binEditTool.setLongAsFourBytes(mpTriggerFlag, questAsBytes, 8);
		binEditTool.setLongAsFourBytes(textEntryIDX, questAsBytes, 12);
		return questAsBytes;
	}

	public OneByteSetting getDungeonLevelSingle() {
		return dungeonLevelSingle;
	}

	public void setDungeonLevelSingle(OneByteSetting dungeonLevelSingle) {
		//TODO -- validation
		this.dungeonLevelSingle = new OneByteSetting(dungeonLevelSingle);
	}

	public int getDungeonLevelMulti() {
		return dungeonLevelMulti;
	}

	public void setDungeonLevelMulti(int dungeonLevelMulti) {
		//TODO -- validation
		this.dungeonLevelMulti = dungeonLevelMulti;
	}

	public int getDungeonType() {
		return dungeonType;
	}

	public void setDungeonType(int dungeonType) {
		//TODO -- validation
		this.dungeonType = dungeonType;
	}

	public int getQuestNumber() {
		return questNumber;
	}

	public void setQuestNumber(int questNumber) {
		//TODO -- validation
		this.questNumber = questNumber;
	}

	public int getByteFourValue() {
		return byteFourValue;
	}

	public void setByteFourValue(int byteFourValue) {
		//TODO -- validation
		this.byteFourValue = byteFourValue;
	}

	public int getSpecialLevel() {
		return specialLevel;
	}

	public void setSpecialLevel(int specialLevel) {
		//TODO -- validation
		this.specialLevel = specialLevel;
	}

	public int getZeroOne() {
		return zeroOne;
	}

	public void setZeroOne(int zeroOne) {
		//TODO -- validation
		this.zeroOne = zeroOne;
	}

	public int getZeroTwo() {
		return zeroTwo;
	}

	public void setZeroTwo(int zeroTwo) {
		//TODO -- validation
		this.zeroTwo = zeroTwo;
	}

	public long getMpTriggerFlag() {
		return mpTriggerFlag;
	}

	public void setMpTriggerFlag(long mpTriggerFlag) {
		//TODO -- validation
		this.mpTriggerFlag = mpTriggerFlag;
	}

	public long getTextEntryIDX() {
		return textEntryIDX;
	}

	public void setTextEntryIDX(long textEntryIDX) {
		//TODO -- validation
		this.textEntryIDX = textEntryIDX;
	}

	public void updateWith(Quest q2) {
		setDungeonLevelSingle(q2.dungeonLevelSingle);
		setDungeonLevelMulti(q2.dungeonLevelMulti);
		setDungeonType(q2.dungeonType);
		setQuestNumber(q2.questNumber);
		setByteFourValue(q2.byteFourValue);
		setSpecialLevel(q2.specialLevel);
		setZeroOne(q2.zeroOne);
		setZeroTwo(q2.zeroTwo);
		setMpTriggerFlag(q2.mpTriggerFlag);
		setTextEntryIDX(q2.textEntryIDX);
		//setSlotNumber(q2.slotNumber);
	}

	private void setSlotNumber(int slotNumber) {
		//TODO -- validation
		this.slotNumber = slotNumber;
	}

	public void updateDungeonLevelSingle(int value) {
		this.dungeonLevelSingle.setValue(value);
	}
}
