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
import org.d1ablo.ode.knowledge.ItemEffectsForModifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Magical modifiers that are applied to base items to enhance them.
 */
public class ItemModifier {
	
	private final static Logger logger = LoggerFactory.getLogger(ItemModifier.class);

	@JsonProperty("namePointer")
	private long namePointer;

	@JsonProperty("name")
	private String name;

	@JsonProperty("itemEffects")
	private byte[] itemEffects;

	@JsonProperty("minimumEffectValue")
	private long minimumEffectValue;

	@JsonProperty("maximumEffectValue")
	private long maximumEffectValue;

	@JsonProperty("qualityLevel")
	private long qualityLevel;

	@JsonProperty("occurrencePossibilities")
	private String occurrencePossibilities; //Bow, Jewelry, Weapons, Staves, Armor, Shields -- 001100 etc...

	@JsonProperty("byteTwentyThree")
	private int byteTwentyThree;

	@JsonProperty("excludedComboIndicator")
	private String excludedComboIndicator; //Values "00000001" and "00000010" cannot form a combination. 00000000 is fine.

	@JsonProperty("cursedIndicator")
	private long cursedIndicator; // A value of "00000000" signifies the prefix/suffix as cursed. "01000000" signifies it as not.

	@JsonProperty("minGold")
	private long minGold;

	@JsonProperty("maxGold")
	private long maxGold;

	@JsonProperty("valueMultiplier")
	private long valueMultiplier;

	@JsonCreator
	public ItemModifier() {
		this.itemEffects = new byte[4];
	}

	public ItemModifier(long namePointer, String name, byte[] itemEffects, long minimumEffectValue,
						long maximumEffectValue, long qualityLevel, String occurrencePossibilities,
						int byteTwentyThree, String excludedComboIndicator, long cursedIndicator,
						long minGold, long maxGold, long valueMultiplier) {
		this.namePointer = namePointer;
		this.name = name;
		this.itemEffects = itemEffects;
		this.minimumEffectValue = minimumEffectValue;
		this.maximumEffectValue = maximumEffectValue;
		this.qualityLevel = qualityLevel;
		this.occurrencePossibilities = occurrencePossibilities;
		this.byteTwentyThree = byteTwentyThree;
		this.excludedComboIndicator = excludedComboIndicator;
		this.cursedIndicator = cursedIndicator;
		this.minGold = minGold;
		this.maxGold = maxGold;
		this.valueMultiplier = valueMultiplier;
	}

    @JsonIgnore
	public byte[] getModifierAsBytes(){
		BinEditTool beh = new BinEditTool();
		byte[] modifierAsBytes = new byte[48];
		beh.setPointerAsFourBytes(namePointer, modifierAsBytes, 0);
		modifierAsBytes[4] = itemEffects[0];
		modifierAsBytes[5] = itemEffects[1];
		modifierAsBytes[6] = itemEffects[2];
		modifierAsBytes[7] = itemEffects[3];
		beh.setLongAsFourBytes(minimumEffectValue, modifierAsBytes, 8);
		beh.setLongAsFourBytes(maximumEffectValue, modifierAsBytes, 12);
		beh.setLongAsFourBytes(qualityLevel, modifierAsBytes, 16);
		String occurencePossibilitiesOne = occurrencePossibilities.substring(0, 2);
		String occurencePossibilitiesTwo = occurrencePossibilities.substring(2, 4);
		String occurencePossibilitiesThree = occurrencePossibilities.substring(4, 6);
		modifierAsBytes[20] = Byte.parseByte(occurencePossibilitiesThree, 16);
		modifierAsBytes[21] = Byte.parseByte(occurencePossibilitiesTwo, 16);
		modifierAsBytes[22] = Byte.parseByte(occurencePossibilitiesOne, 16);
		modifierAsBytes[23] = (byte) byteTwentyThree;
		String[] split = excludedComboIndicator.split(";");
		modifierAsBytes[24] = Byte.parseByte(split[7], 16);
		modifierAsBytes[25] = Byte.parseByte(split[6], 16);
		modifierAsBytes[26] = Byte.parseByte(split[5], 16);
		modifierAsBytes[27] = Byte.parseByte(split[4], 16);
		modifierAsBytes[28] = Byte.parseByte(split[3], 16);
		modifierAsBytes[29] = Byte.parseByte(split[2], 16);
		modifierAsBytes[30] = Byte.parseByte(split[1], 16);
		modifierAsBytes[31] = Byte.parseByte(split[0], 16);
		beh.setLongAsFourBytes(cursedIndicator, modifierAsBytes, 32);
		beh.setLongAsFourBytes(minGold, modifierAsBytes, 36);
		beh.setLongAsFourBytes(maxGold, modifierAsBytes, 40);
		beh.setLongAsFourBytes(valueMultiplier, modifierAsBytes, 44);

		return modifierAsBytes;
	}

	public void printModifier(){
		logger.info("Modifier name: " + name);
		logger.info("Modifier name pointer: " + namePointer);
		String[] effects = ItemEffectsForModifiers.ITEM_EFFECTS;
		logger.info("Item Effects: " + effects[itemEffects[0]] + "; " + effects[itemEffects[1]] + "; " + effects[itemEffects[2]] + "; " + effects[itemEffects[3]]);
		logger.info("Minimum effect value: " + minimumEffectValue);
		logger.info("Maximum effect value: " + maximumEffectValue);
		logger.info("Quality level: " + qualityLevel);
		logger.info("Occurrence probability (bit encoded): " + occurrencePossibilities);
		logger.info("Zero: " + byteTwentyThree);
		logger.info("Excluded combinations indicator: " + excludedComboIndicator);
		logger.info("Cursed indicator: " + cursedIndicator);
		logger.info("Min gold: " + minGold);
		logger.info("Max gold: " + maxGold);
		logger.info("Value multiplier: " + valueMultiplier);
		logger.info("");
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
	
	public byte getItemEffectOne() {
		return itemEffects[0];
	}
	
	public byte getItemEffectTwo() {
		return itemEffects[1];
	}
	
	public byte getItemEffectThree() {
		return itemEffects[2];
	}
	
	public byte getItemEffectFour() {
		return itemEffects[3];
	}

	public void setItemEffectOne(byte b) {
		//TODO -- validation
		this.itemEffects[0] = b;
	}

	public void setItemEffectTwo(byte b) {
		//TODO -- validation
		this.itemEffects[1] = b;
	}

	public void setItemEffectThree(byte b) {
		//TODO -- validation
		this.itemEffects[2] = b;
	}

	public void setItemEffectFour(byte b) {
		//TODO -- validation
		this.itemEffects[3] = b;
	}

	public long getMinimumEffectValue() {
		return minimumEffectValue;
	}

	public void setMinimumEffectValue(long minimumEffectValue) {
		//TODO -- validation
		this.minimumEffectValue = minimumEffectValue;
	}

	public long getMaximumEffectValue() {
		return maximumEffectValue;
	}

	public void setMaximumEffectValue(long maximumEffectValue) {
		//TODO -- validation
		this.maximumEffectValue = maximumEffectValue;
	}

	public long getQualityLevel() {
		return qualityLevel;
	}

	public void setQualityLevel(long qualityLevel) {
		//TODO -- validation
		this.qualityLevel = qualityLevel;
	}

	public String getOccurrencePossibilities() {
		return occurrencePossibilities;
	}

	public void setOccurrencePossibilities(String occurrencePossibilities) {
		//TODO -- validation
		this.occurrencePossibilities = occurrencePossibilities;
	}

	public int getByteTwentyThree() {
		return byteTwentyThree;
	}

	public void setByteTwentyThree(int byteTwentyThree) {
		//TODO -- validation
		this.byteTwentyThree = byteTwentyThree;
	}

	public String getExcludedComboIndicator() {
		return excludedComboIndicator;
	}

	public void setExcludedComboIndicator(String excludedComboIndicator) {
		//TODO -- validation
		this.excludedComboIndicator = excludedComboIndicator;
	}

	public long getCursedIndicator() {
		return cursedIndicator;
	}

	public void setCursedIndicator(long cursedIndicator) {
		//TODO -- validation
		this.cursedIndicator = cursedIndicator;
	}

	public long getMinGold() {
		return minGold;
	}

	public void setMinGold(long minGold) {
		//TODO -- validation
		this.minGold = minGold;
	}

	public long getMaxGold() {
		return maxGold;
	}

	public void setMaxGold(long maxGold) {
		//TODO -- validation
		this.maxGold = maxGold;
	}

	public long getValueMultiplier() {
		return valueMultiplier;
	}

	public void setValueMultiplier(long valueMultiplier) {
		//TODO -- validation
		this.valueMultiplier = valueMultiplier;
	}

	public void updateWith(ItemModifier modifierToUpdateWith) {
		setNamePointer(modifierToUpdateWith.namePointer);
		setName(modifierToUpdateWith.name);
		setItemEffectOne(modifierToUpdateWith.itemEffects[0]);
		setItemEffectTwo(modifierToUpdateWith.itemEffects[1]);
		setItemEffectThree(modifierToUpdateWith.itemEffects[2]);
		setItemEffectFour(modifierToUpdateWith.itemEffects[3]);
		setMinimumEffectValue(modifierToUpdateWith.minimumEffectValue);
		setMaximumEffectValue(modifierToUpdateWith.maximumEffectValue);
		setQualityLevel(modifierToUpdateWith.qualityLevel);
		setByteTwentyThree(modifierToUpdateWith.byteTwentyThree);
		setExcludedComboIndicator(modifierToUpdateWith.excludedComboIndicator);
		setCursedIndicator(modifierToUpdateWith.cursedIndicator);
		setMinGold(modifierToUpdateWith.minGold);
		setMaxGold(modifierToUpdateWith.maxGold);
		setValueMultiplier(modifierToUpdateWith.valueMultiplier);
	}
}
