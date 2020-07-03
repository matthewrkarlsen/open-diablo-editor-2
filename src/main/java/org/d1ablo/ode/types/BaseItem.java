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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.d1ablo.ode.bintool.BinEditTool;
import org.d1ablo.ode.knowledge.*;
import org.d1ablo.ode.utils.ValueChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Each BaseItem object acts as a repository for the information
 * pertaining to a particular in-game item. This is for
 * base items only (i.e. the item before it is affected by
 * any prefixes or suffixes).
 */
public class BaseItem {
	
	private final static Logger logger = LoggerFactory.getLogger(BaseItem.class);

	@JsonProperty("activationTrigger")
	private long activationTrigger;

	@JsonProperty("itemType")
	private int itemType;

	@JsonProperty("equipLocation")
	private int equipLocation;

	@JsonProperty("byteSix")
	private int byteSix;

	@JsonProperty("byteSeven")
	private int byteSeven;

	@JsonProperty("graphicValue")
	private int graphicValue;

	@JsonProperty("itemCode")
	private int itemCode;

	@JsonProperty("uniqueItemCode")
	private int uniqueItemCode;

	@JsonProperty("byteFourteen")
	private int byteFourteen;

	@JsonProperty("byteFifteen")
	private int byteFifteen;

	@JsonProperty("namePointer")
	private long namePointer;

	@JsonProperty("name")
	private String name;

	@JsonProperty("magicalNamePointer")
	private long magicalNamePointer;

	@JsonProperty("magicalName")
	private String magicalName;

	@JsonProperty("qualityLevel")
	private long qualityLevel;

	@JsonProperty("durability")
	private long durability;

	@JsonProperty("minAttackDamage")
	private long minAttackDamage;

	@JsonProperty("maxAttackDamage")
	private long maxAttackDamage;

	@JsonProperty("minAc")
	private long minAc;

	@JsonProperty("maxAc")
	private long maxAc;

	@JsonProperty("requiredStr")
	private int requiredStr;

	@JsonProperty("requiredMag")
	private int requiredMag;

	@JsonProperty("requiredDex")
	private int requiredDex;

	@JsonProperty("requiredVit")
	private int requiredVit;

	@JsonProperty("specialEffects")
	private int specialEffects;

	@JsonProperty("magicCode")
	private long magicCode;

	@JsonProperty("spellNumber")
	private long spellNumber;

	@JsonProperty("singleUseFlag")
	private long singleUseFlag;

	@JsonProperty("priceOne")
	private long priceOne;

	@JsonProperty("priceTwo")
	private long priceTwo;

	@JsonProperty("slotNumber")
	private int slotNumber;

	@JsonIgnore
	private BinEditTool binEditTool;

	@JsonCreator
	public BaseItem() {

	}

	public BaseItem(int slotNumber, long activationTrigger, int itemType, int equipLocation, int byteSix,
					int byteSeven, int graphicValue, int itemCode, int uniqueItemCode, int byteFourteen,
					int byteFifteen, long namePointer, String name, long magicalNamePointer,
					String magicalName, long qualityLevel, long durability, long minAttackDamage, long maxAttackDamage,
					long minAc, long maxAc, int requiredStr, int requiredMag, int requiredDex, int requiredVit,
					int specialEffects, long magicCode, long spellNumber, long singleUseFlag, long priceOne,
					long priceTwo, BinEditTool binEditTool) {
		this.slotNumber = slotNumber;
		this.activationTrigger = activationTrigger;
		this.itemType = itemType;
		this.equipLocation = equipLocation;
		this.byteSix = byteSix;
		this.byteSeven = byteSeven;
		this.graphicValue = graphicValue;
		this.itemCode = itemCode;
		this.uniqueItemCode = uniqueItemCode;
		this.byteFourteen = byteFourteen;
		this.byteFifteen = byteFifteen;
		this.namePointer = namePointer;
		this.name = name;
		this.magicalNamePointer = magicalNamePointer;
		this.magicalName = magicalName;
		this.qualityLevel = qualityLevel;
		this.durability = durability;
		this.minAttackDamage = minAttackDamage;
		this.maxAttackDamage = maxAttackDamage;
		this.minAc = minAc;
		this.maxAc = maxAc;
		this.requiredStr = requiredStr;
		this.requiredMag = requiredMag;
		this.requiredDex = requiredDex;
		this.requiredVit = requiredVit;
		this.specialEffects = specialEffects;
		this.magicCode = magicCode;
		this.spellNumber = spellNumber;
		this.singleUseFlag = singleUseFlag;
		this.priceOne = priceOne;
		this.priceTwo = priceTwo;
		this.binEditTool = binEditTool;
	}

    @JsonIgnore
	public byte[] getItemAsBytes(){
		byte[] bytes = new byte[DiabloFilePositions.BASE_ITEM_LENGTH_IN_BYTES];
		binEditTool.setLongAsFourBytes(activationTrigger, bytes, 0);
		bytes[4] = (byte) itemType;
		bytes[5] = (byte) equipLocation;
		bytes[6] = (byte) byteSix;
		bytes[7] = (byte) byteSeven;
		binEditTool.setLongAsFourBytes(graphicValue, bytes, 8);
		bytes[12] = (byte) itemCode;
		bytes[13] = (byte) uniqueItemCode;
		bytes[14] = (byte) byteFourteen;
		bytes[15] = (byte) byteFifteen;
		binEditTool.setPointerAsFourBytes(namePointer, bytes, 16);
		if(magicalNamePointer != 0) {
			binEditTool.setPointerAsFourBytes(magicalNamePointer, bytes, 20);
		}
		binEditTool.setLongAsFourBytes(qualityLevel, bytes, 24);
		binEditTool.setLongAsFourBytes(durability, bytes, 28);
		binEditTool.setLongAsFourBytes(minAttackDamage, bytes, 32);
		binEditTool.setLongAsFourBytes(maxAttackDamage, bytes, 36);
		binEditTool.setLongAsFourBytes(minAc, bytes, 40);
		binEditTool.setLongAsFourBytes(maxAc, bytes, 44);
		bytes[48] = (byte) requiredStr;
		bytes[49] = (byte) requiredMag;
		bytes[50] = (byte) requiredDex;
		bytes[51] = (byte) requiredVit;
		binEditTool.setLongAsFourBytes(specialEffects, bytes, 52);
		binEditTool.setLongAsFourBytes(magicCode, bytes, 56);
		binEditTool.setLongAsFourBytes(spellNumber, bytes, 60);
		binEditTool.setLongAsFourBytes(singleUseFlag, bytes, 64);
		binEditTool.setLongAsFourBytes(priceOne, bytes, 68);
		binEditTool.setLongAsFourBytes(priceTwo, bytes, 72);
		return bytes;
	}

	public void printItem() {
		String[] spellNumbers = SpellNames.SPELL_NAMES;
		logger.info("Slot number: " + slotNumber + " (hex: " + Integer.toHexString(slotNumber) + ")" + "\n" +
			"Name: " + name + "\n" +
			"Name pointer: " + namePointer + "\n" +
			"Magical name: " + magicalName + "\n" +
			"Magical name pointer: " + magicalNamePointer + "\n" +
			"Activation trigger: " + BaseItemActivationTriggers.BASE_ITEM_ACTIVATION_TRIGGERS[(int) activationTrigger] + "\n" +
			"Item type: " + ItemTypes.BROAD_ITEM_TYPES[itemType] + "\n" +
			"Equip location: " + EquipLocations.EQUIP_LOCATIONS[equipLocation] + "\n" +
			"Byte six: " + byteSix + "\n" +
			"Byte seven: " + byteSeven + "\n" +
			"Graphic: " + ItemGraphicValues.GRAPHIC_VALUES[graphicValue] + "\n" +
			"Item code: " + ItemCodes.ITEM_CODES[itemCode] + "\n" +
			"Unique item code: " + ItemCodes.ITEM_CODES[uniqueItemCode] + "\n" +
			"Byte fourteen: " + byteFourteen + "\n" +
			"Byte fifteen: " + byteFifteen + "\n" +
			"Quality level: " + qualityLevel + "\n" +
			"Durability: " + durability + "\n" +
			"Min attack damage: " + minAttackDamage + "\n" +
			"Max attack damage: " + maxAttackDamage + "\n" +
			"Min ac: " + minAc + "\n" +
			"Max ac: " + maxAc + "\n" +
			"Required Str: " + requiredStr + "\n" +
			"Required Mag: " + requiredMag + "\n" +
			"Required Dex: " + requiredDex + "\n" +
			"Required Vit: " + requiredVit + "\n" +
			"Special effects: " + Long.toHexString(specialEffects) + "\n" +
			"Magic code: " + MagicCodes.MAGIC_CODES[(int) magicCode] + "\n" +
			"Spell number: " + spellNumbers[(int) spellNumber] + "\n" +
			"Single use flag: " + UseCountStrings.USE_COUNT_STRINGS[(int) singleUseFlag] + "\n" +
			"Price one: " + priceOne + "\n" +
			"Price two: " + priceTwo + "\n"
		);
	}

	public long getNamePointer() {
		return namePointer;
	}

	//1024 to 0x6B1800 (7018496)
	public void setNamePointer(long namePointer) {
		ValueChecker.checkRange(1024, namePointer, 7018496);
		this.namePointer = namePointer;
	}

	public long getMagicalNamePointer() {
		return magicalNamePointer;
	}

	public void setMagicalNamePointer(long magicalNamePointer) {
		if(magicalNamePointer == 0 || magicalNamePointer >= 1024 && magicalNamePointer <= 7018496){
			this.magicalNamePointer = magicalNamePointer;
		} else {
			throw new IllegalStateException("BaseItem's setMagicalNamePointer() was supplied with a value of " +
					magicalNamePointer + " -- outside the supported range (0; 1024 to 7018496)");
		}
	}

	public long getQualityLevel() {
		return qualityLevel;
	}

	public void setQualityLevel(long qualityLevel) {
		ValueChecker.checkRange(0, qualityLevel, 15);
		this.qualityLevel = qualityLevel;
	}

	public long getDurability() {
		return durability;
	}

	public void setDurability(long durability) {
		ValueChecker.checkRange(0, durability, 255);
		this.durability = durability;
	}

	public long getMinAttackDamage() {
		return minAttackDamage;
	}

	public void setMinAttackDamage(long minAttackDamage) {
		ValueChecker.checkRange(0, minAttackDamage, 255);
		this.minAttackDamage = minAttackDamage;
	}

	public long getMaxAttackDamage() {
		return maxAttackDamage;
	}

	public void setMaxAttackDamage(long maxAttackDamage) {
		ValueChecker.checkRange(0, maxAttackDamage, 255);
		this.maxAttackDamage = maxAttackDamage;
	}

	public long getMinAc() {
		return minAc;
	}

	public void setMinAc(long minAc) {
		ValueChecker.checkRange(0, minAc, 255);
		this.minAc = minAc;
	}

	public long getMaxAc() {
		return maxAc;
	}

	public void setMaxAc(long maxAc) {
		ValueChecker.checkRange(0, maxAc, 255);
		this.maxAc = maxAc;
	}

	public int getRequiredStr() {
		return requiredStr;
	}

	public void setRequiredStr(int requiredStr) {
		ValueChecker.checkRange(0, requiredStr, 255);
		this.requiredStr = requiredStr;
	}

	public int getRequiredMag() {
		return requiredMag;
	}

	public void setRequiredMag(int requiredMag) {
		ValueChecker.checkRange(0, requiredMag, 255);
	    this.requiredMag = requiredMag;
	}

	public int getRequiredDex() {
		return requiredDex;
	}

	public void setRequiredDex(int requiredDex) {
		ValueChecker.checkRange(0, requiredDex, 255);
		this.requiredDex = requiredDex;
	}

	public int getRequiredVit() {
		return requiredVit;
	}

	public void setRequiredVit(int requiredVit) {
		ValueChecker.checkRange(0, requiredVit, 255);
		this.requiredVit = requiredVit;
	}

	public long getSpecialEffects() {
		return specialEffects;
	}

	public void setSpecialEffects(int specialEffects) {
		if(SpecialEffectMap.containsKey(specialEffects)){
			this.specialEffects = specialEffects;
		} else {
			throw new IllegalStateException("Special effects key not acceptable value (" + specialEffects + ")");
		}
	}

	public long getPriceOne() {
		return priceOne;
	}

	//0 to 999999
	public void setPriceOne(long priceOne) {
		ValueChecker.checkRange(0, priceOne, 999999);
		this.priceOne = priceOne;
	}

	public long getPriceTwo() {
		return priceTwo;
	}

	public void setPriceTwo(long priceTwo) {
		ValueChecker.checkRange(0, priceTwo, 999999);
		this.priceTwo = priceTwo;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		//TODO -- validation
		this.name = name;
	}

	public void updateWith(BaseItem itemToUpdateFrom) {
		setActivationTrigger(itemToUpdateFrom.activationTrigger);
		setItemType(itemToUpdateFrom.itemType);
		setEquipLocation(itemToUpdateFrom.equipLocation);
		setByteSix(itemToUpdateFrom.byteSix);
		setByteSeven(itemToUpdateFrom.byteSeven);
		setGraphicValue(itemToUpdateFrom.graphicValue);
		setItemCode(itemToUpdateFrom.itemCode);
		setUniqueItemCode(itemToUpdateFrom.uniqueItemCode);
		setByteFourteen(itemToUpdateFrom.byteFourteen);
		setByteFifteen(itemToUpdateFrom.byteFifteen);
		setNamePointer(itemToUpdateFrom.namePointer);
		setName(itemToUpdateFrom.name);
		setMagicalNamePointer(itemToUpdateFrom.magicalNamePointer);
		setMagicalName(itemToUpdateFrom.magicalName);
		setQualityLevel(itemToUpdateFrom.qualityLevel);
		setDurability(itemToUpdateFrom.durability);
		setMinAttackDamage(itemToUpdateFrom.minAttackDamage);
		setMaxAttackDamage(itemToUpdateFrom.maxAttackDamage);
		setMinAc(itemToUpdateFrom.minAc);
		setMaxAc(itemToUpdateFrom.maxAc);
		setRequiredStr(itemToUpdateFrom.requiredStr);
		setRequiredMag(itemToUpdateFrom.requiredMag);
		setRequiredDex(itemToUpdateFrom.requiredDex);
		setRequiredVit(itemToUpdateFrom.requiredVit);
		setSpecialEffects(itemToUpdateFrom.specialEffects);
		setMagicCode(itemToUpdateFrom.magicCode);
		setSpellNumber(itemToUpdateFrom.spellNumber);
		setSingleUseFlag(itemToUpdateFrom.singleUseFlag);
		setPriceOne(itemToUpdateFrom.priceOne);
		setPriceTwo(itemToUpdateFrom.priceTwo);
		setSlotNumber(itemToUpdateFrom.slotNumber);
	}

	private void setSlotNumber(int slotNumber) {
		//TODO -- validation
		this.slotNumber = slotNumber;
	}

	private void setSingleUseFlag(long singleUseFlag) {
		//TODO -- validation
		this.singleUseFlag = singleUseFlag;
	}

	private void setSpellNumber(long spellNumber) {
		//TODO -- validation
		this.spellNumber = spellNumber;
	}

	private void setMagicCode(long magicCode) {
		//TODO -- validation
		this.magicCode = magicCode;
	}

	private void setMagicalName(String magicalName) {
		//TODO -- validation
		this.magicalName = magicalName;
	}

	private void setByteFifteen(int byteFifteen) {
		//TODO -- validation
		this.byteFifteen = byteFifteen;
	}

	private void setByteFourteen(int byteFourteen) {
		//TODO -- validation
		this.byteFourteen = byteFourteen;
	}

	private void setUniqueItemCode(int uniqueItemCode) {
		//TODO -- validation
		this.uniqueItemCode = uniqueItemCode;
	}

	private void setItemCode(int itemCode) {
		//TODO -- validation
		this.itemCode = itemCode;
	}

	private void setGraphicValue(int graphicValue) {
		//TODO -- validation
		this.graphicValue = graphicValue;
	}

	private void setByteSeven(int byteSeven) {
		//TODO -- validation
		this.byteSeven = byteSeven;
	}

	private void setByteSix(int byteSix) {
		//TODO -- validation
		this.byteSix = byteSix;
	}

	private void setEquipLocation(int equipLocation) {
		//TODO -- validation
		this.equipLocation = equipLocation;
	}

	private void setItemType(int itemType) {
		//TODO -- validation
		this.itemType = itemType;
	}

	private void setActivationTrigger(long activationTrigger) {
		//TODO -- validation
		this.activationTrigger = activationTrigger;
	}
}
