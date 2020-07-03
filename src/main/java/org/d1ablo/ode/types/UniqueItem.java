/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.md for full contributor list.
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
import org.d1ablo.ode.knowledge.ItemEffectDescriptions;
import org.d1ablo.ode.knowledge.ItemTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds all the data relating to a single unique item within the game.
 */
public class UniqueItem {
	
	private final static Logger logger = LoggerFactory.getLogger(UniqueItem.class);

	@JsonProperty("namePointer")
	private long namePointer;

	@JsonProperty("name")
	private String name;

	@JsonProperty("itemType")
	private int itemType;

	@JsonProperty("qualityLevel")
	private int qualityLevel;

	@JsonProperty("numberOfEffects")
	private int numberOfEffects;

	@JsonProperty("goldValue")
	private long goldValue;

	@JsonProperty("itemEffects")
	private List<ItemEffect> itemEffects;

	private BinEditTool beh;

	@JsonCreator
	public UniqueItem(@JacksonInject BinEditTool beh) {
		this.itemEffects = new ArrayList<>();
		this.beh = beh;
	}

	private UniqueItem() {

	}

	public UniqueItem(long namePointer, String name, int itemType, int qualityLevel, int numberOfEffects,
					  long goldValue, List<ItemEffect> itemEffects, BinEditTool binEditTool) {
		this.beh = binEditTool;
		this.itemEffects = itemEffects;
		this.namePointer = namePointer;
		this.name = name;
		this.itemType = itemType;
		this.qualityLevel = qualityLevel;
		this.numberOfEffects = numberOfEffects;
		this.goldValue = goldValue;
	}

    public void printItem() {
		logger.info("Name: " + name);
		logger.info("Name pointer: " + namePointer);
		logger.info("Item type: " + ItemTypes.UNIQUE_ITEM_TYPES_ARRAY[itemType]);
		logger.info("Quality level: " + qualityLevel);
		logger.info("Number of effects: " + numberOfEffects);
		logger.info("Gold value: " + goldValue);
		for(ItemEffect ie : itemEffects){
			logger.info("Effect " + ie.getEffectNumber() + ": " +
					ItemEffectDescriptions.ITEM_EFFECTS[(int) ie.getEffect()] +"; " + ie.getMinValue() + "; "
					+ ie.getMaxValue());
		}
		logger.info("");
	}

	@JsonIgnore
	public byte[] getItemAsBytes() {
		byte[] itemAsBytes = new byte[84];
		beh.setPointerAsFourBytes(namePointer, itemAsBytes, 0);
		itemAsBytes[4] = (byte) itemType;
		itemAsBytes[5] = (byte) qualityLevel;
		itemAsBytes[6] = (byte) (numberOfEffects);
		itemAsBytes[7] = (byte) (numberOfEffects >>> 8);
		beh.setLongAsFourBytes(goldValue, itemAsBytes, 8);
		for(int offset = 12; offset < 84; offset = offset + 12){
			ItemEffect ie = itemEffects.get( (offset / 12 ) - 1);
			beh.setLongAsFourBytes(ie.getEffect(), itemAsBytes, offset);
			beh.setLongAsFourBytes(ie.getMinValue(), itemAsBytes, offset+4);
			beh.setLongAsFourBytes(ie.getMaxValue(), itemAsBytes, offset+8);
		}

		return itemAsBytes;
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

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		//TODO -- validation
		this.itemType = itemType;
	}

	public int getQualityLevel() {
		return qualityLevel;
	}

	public void setQualityLevel(int qualityLevel) {
		//TODO -- validation
		this.qualityLevel = qualityLevel;
	}

	public int getNumberOfEffects() {
		return numberOfEffects;
	}

	public void setNumberOfEffects(int numberOfEffects) {
		//TODO -- validation
		this.numberOfEffects = numberOfEffects;
	}

	public void setEffect(long effect, int effectIndex) {
		//TODO -- validation
		ItemEffect ie = itemEffects.get(effectIndex-1);
		ie.setEffect(effect);
	}

	public long getMinValue(int effectIndex) {
		return itemEffects.get(effectIndex-1).getMinValue();
	}

	public void setMinValue(long minValue, int effectIndex) {
		//TODO -- validation
		ItemEffect ie = itemEffects.get(effectIndex-1);
		ie.setMinValue(minValue);
	}

	public long getMaxValue(int effectIndex) {
		return itemEffects.get(effectIndex-1).getMaxValue();
	}

	public void setMaxValue(long maxValue, int effectIndex) {
		//TODO -- validation
		ItemEffect ie = itemEffects.get(effectIndex-1);
		ie.setMaxValue(maxValue);
	}
	
	public long getEffectNumber(int effectIndex) {
		return itemEffects.get(effectIndex-1).getEffectNumber();
	}
	
	public String getEffectName(int effectIndex) {
		return itemEffects.get(effectIndex-1).getEffectName();
	}

	public void updateFrom(UniqueItem u2) {
		setNamePointer(u2.namePointer);
		setName(u2.name);
		setItemType(u2.itemType);
		setQualityLevel(u2.qualityLevel);
		setNumberOfEffects(u2.numberOfEffects);
		setGoldValue(u2.goldValue);
		for(int i = 0; i < itemEffects.size(); i++) {
			ItemEffect itemEffect = itemEffects.get(i);
			itemEffect.updateFrom(u2.itemEffects.get(i));
		}
	}

	private void setGoldValue(long goldValue) {
		//TODO -- validation
		this.goldValue = goldValue;
	}
}
