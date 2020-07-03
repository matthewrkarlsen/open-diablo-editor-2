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
import org.d1ablo.ode.knowledge.MonsterAITypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Holds all the data relating to a single unique monster within the game.
 */
public class UniqueMonster {
	
	private final static Logger logger = LoggerFactory.getLogger(UniqueMonster.class);

	@JsonProperty("monsterType")
	private long monsterType;

	@JsonProperty("namePointer")
	private long namePointer;

	@JsonProperty("name")
	private String name;

	@JsonProperty("trnPointer")
	private long trnPointer;

	@JsonProperty("trnName")
	private String trnName;

	@JsonProperty("dungeonLevel")
	private int dungeonLevel;

	@JsonProperty("hitPoints")
	private int hitPoints;

	@JsonProperty("monsterAI")
	private int monsterAI;

	@JsonProperty("intelligenceFactor")
	private int intelligenceFactor;

	@JsonProperty("minAttackDmg")
	private int minAttackDmg;

	@JsonProperty("maxAttackDmg")
	private int maxAttackDmg;

	@JsonProperty("resistances")
	private String resistances;

	@JsonProperty("packTrigger")
	private int packTrigger;

	@JsonProperty("packSpecials")
	private long packSpecials;

	@JsonProperty("specialSoundWav")
	private long specialSoundWav;

	private BinEditTool beh;

	@JsonCreator
	public UniqueMonster(@JacksonInject BinEditTool beh) {
		this.beh = beh;
	}

	public UniqueMonster(long monsterType, long namePointer, String name, long trnPointer, String trnName,
						 int dungeonLevel, int hitPoints, int monsterAI, int intelligenceFactor, int minAttackDmg,
						 int maxAttackDmg, String resistances, int packTrigger, long packSpecials,
						 long specialSoundWav, BinEditTool beh) {
		this.beh = beh;
		this.monsterType = monsterType;
		this.namePointer = namePointer;
		this.name = name;
		this.trnPointer = trnPointer;
		this.trnName = trnName;
		this.dungeonLevel = dungeonLevel;
		this.hitPoints = hitPoints;
		this.monsterAI = monsterAI;
		this.intelligenceFactor = intelligenceFactor;
		this.minAttackDmg = minAttackDmg;
		this.maxAttackDmg = maxAttackDmg;
		this.resistances = resistances;
		this.packTrigger = packTrigger;
		this.packSpecials = packSpecials;
		this.specialSoundWav = specialSoundWav;
	}

	private UniqueMonster() {

	}

    public void printUniqueMonster() {
		logger.info(
			"Name: " + name + "\n" +
			"Monster type: " + monsterType + "\n" +
			"Name pointer: " + namePointer + "\n" +
			"TRN pointer: " + trnPointer + "\n" +
			"TRN file: " + trnName + "\n" +
			"Dungeon level: " + dungeonLevel + "\n" +
			"HPs: " + hitPoints + "\n" +
			"Monster AI: " + MonsterAITypes.MONSTER_AIS[monsterAI] + "\n" +
			"Intelligence factor: " + intelligenceFactor + "\n" +
			"Min attack dmg: " + minAttackDmg + "\n" +
			"Max attack dmg: " + maxAttackDmg + "\n" +
			"Resistances: " + resistances + "\n" +
			"Pack trigger: " + packTrigger + "\n" +
			"Pack specials: " + packSpecials + "\n" +
			"Special WAV sound: " + specialSoundWav + "\n"
		);
	}

	@JsonIgnore
	public byte[] getUniqueAsBytes() {
		byte[] uniqueAsBytes = new byte[32];
		beh.setLongAsFourBytes(monsterType, uniqueAsBytes, 0);
		beh.setPointerAsFourBytes(namePointer, uniqueAsBytes, 4);
		if(trnPointer != 0) {
			beh.setPointerAsFourBytes(trnPointer, uniqueAsBytes, 8);
		}
		beh.setIntAsTwoBytes(dungeonLevel, uniqueAsBytes, 12);
		beh.setIntAsTwoBytes(hitPoints, uniqueAsBytes, 14);
		uniqueAsBytes[16] = (byte) monsterAI;
		uniqueAsBytes[17] = (byte) intelligenceFactor;
		uniqueAsBytes[18] = (byte) minAttackDmg;
		uniqueAsBytes[19] = (byte) maxAttackDmg;
		String[] resistancesSplit = resistances.split(";");
		uniqueAsBytes[20] = Byte.parseByte(resistancesSplit[0], 2);
		uniqueAsBytes[21] = Byte.parseByte(resistancesSplit[1], 2);
		beh.setIntAsTwoBytes(packTrigger, uniqueAsBytes, 22);
		beh.setLongAsFourBytes(packSpecials, uniqueAsBytes, 24);
		beh.setLongAsFourBytes(specialSoundWav, uniqueAsBytes, 28);
		return uniqueAsBytes;
	}

	public long getMonsterType() {
		return monsterType;
	}

	public void setMonsterType(long monsterType) {
		//TODO -- validation
		this.monsterType = monsterType;
	}

	public long getNamePointer() {
		return namePointer;
	}

	public void setNamePointer(long namePointer) {
		//TODO -- validation
		this.namePointer = namePointer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		//TODO -- validation
		this.name = name;
	}

	public long getTrnPointer() {
		return trnPointer;
	}

	public void setTrnPointer(long trnPointer) {
		//TODO -- validation
		this.trnPointer = trnPointer;
	}

	public int getDungeonLevel() {
		return dungeonLevel;
	}

	public void setDungeonLevel(int dungeonLevel) {
		//TODO -- validation
		this.dungeonLevel = dungeonLevel;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public void setHitPoints(int hitPoints) {
		//TODO -- validation
		this.hitPoints = hitPoints;
	}

	public int getMonsterAI() {
		return monsterAI;
	}

	public void setMonsterAI(int monsterAI) {
		//TODO -- validation
		this.monsterAI = monsterAI;
	}

	public int getIntelligenceFactor() {
		return intelligenceFactor;
	}

	public void setIntelligenceFactor(int intelligenceFactor) {
		//TODO -- validation
		this.intelligenceFactor = intelligenceFactor;
	}

	public int getMinAttackDmg() {
		return minAttackDmg;
	}

	public void setMinAttackDmg(int minAttackDmg) {
		//TODO -- validation
		this.minAttackDmg = minAttackDmg;
	}

	public int getMaxAttackDmg() {
		return maxAttackDmg;
	}

	public void setMaxAttackDmg(int maxAttackDmg) {
		//TODO -- validation
		this.maxAttackDmg = maxAttackDmg;
	}

	public String getResistances() {
		return resistances;
	}

	public void setResistances(String resistances) {
		//TODO -- validation
		this.resistances = resistances;
	}

	public int getPackTrigger() {
		return packTrigger;
	}

	public void setPackTrigger(int packTrigger) {
		//TODO -- validation
		this.packTrigger = packTrigger;
	}

	public long getPackSpecials() {
		return packSpecials;
	}

	public void setPackSpecials(long packSpecials) {
		//TODO -- validation
		this.packSpecials = packSpecials;
	}

	public long getSpecialSoundWav() {
		return specialSoundWav;
	}

	public void setSpecialSoundWav(long specialSoundWav) {
		//TODO -- validation
		this.specialSoundWav = specialSoundWav;
	}

	public void updateFrom(UniqueMonster u2) {
		setMonsterType(u2.monsterType);
		setNamePointer(u2.namePointer);
		setName(u2.name);
		setTrnPointer(u2.trnPointer);
		setTrnName(u2.trnName);
		setDungeonLevel(u2.dungeonLevel);
		setHitPoints(u2.hitPoints);
		setMonsterAI(u2.monsterAI);
		setIntelligenceFactor(u2.intelligenceFactor);
		setMinAttackDmg(u2.minAttackDmg);
		setMaxAttackDmg(u2.maxAttackDmg);
		setResistances(u2.resistances);
		setPackTrigger(u2.packTrigger);
		setPackSpecials(u2.packSpecials);
		setSpecialSoundWav(u2.specialSoundWav);
	}

	private void setTrnName(String trnName) {
		//TODO -- validation
		this.trnName = trnName;
	}
}
