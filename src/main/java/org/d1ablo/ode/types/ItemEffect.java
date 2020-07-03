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
import com.fasterxml.jackson.annotation.JsonProperty;
import org.d1ablo.ode.knowledge.ItemEffectDescriptions;

/**
 * A single {@link ItemEffect} -- part of an overall {@link ItemModifier}, for modification of {@link BaseItem}s.
 */
public class ItemEffect {

	@JsonProperty("effectNumber")
	private long effectNumber;

	@JsonProperty("effect")
	private long effect;

	@JsonProperty("minValue")
	private long minValue;

	@JsonProperty("maxValue")
	private long maxValue;

	@JsonCreator
	public ItemEffect() {

	}

	public ItemEffect(long effectNumber, long effect, long minValue, long maxValue) {
		this.effectNumber = effectNumber;
		this.effect = effect;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	public static ItemEffect getDummyItemEffect() {
		ItemEffect e = new ItemEffect();
		e.effectNumber = 0;
		e.effect = 0;
		e.minValue = 0;
		e.maxValue = 0;
		return e;
	}

    public long getEffect() {
		return effect;
	}

	public void setEffect(long effect) {
		//TODO -- validation
		this.effect = effect;
	}

	public long getMinValue() {
		return minValue;
	}

	public void setMinValue(long minValue) {
		//TODO -- validation
		this.minValue = minValue;
	}

	public long getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(long maxValue) {
		//TODO -- validation
		this.maxValue = maxValue;
	}

	public long getEffectNumber() {
		return effectNumber;
	}
	
	public String getEffectName()
	{
		return ItemEffectDescriptions.ITEM_EFFECTS[(int) effectNumber];
	}

	public void updateFrom(ItemEffect itemEffect) {
		setEffectNumber(itemEffect.effectNumber);
		setEffect(itemEffect.effect);
		setMinValue(itemEffect.minValue);
		setMaxValue(itemEffect.maxValue);
	}

	private void setEffectNumber(long effectNumber) {
		//TODO -- validation
		this.effectNumber = effectNumber;
	}
}
