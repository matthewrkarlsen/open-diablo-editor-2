/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.txt for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */
package org.d1ablo.ode.stores;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.d1ablo.ode.bintool.StringExtractor;
import org.d1ablo.ode.bintool.rw.ReaderWriter;
import org.d1ablo.ode.factories.ItemModifierFactory;
import org.d1ablo.ode.knowledge.DiabloFilePositions;
import org.d1ablo.ode.types.ItemModifier;

import java.util.List;

/**
 * Holds the item modifiers. These are prefixes or suffixes that can be appended to the names of base items.
 * If appended to an item, a modifier provides the item with special enhancements or defects of some kind.
 * Each item instance can have only one prefix and one suffix.
 */
public class ItemModifiersStore {

	@JsonIgnore
	private ItemModifier itemModifierSelected;

	private ReaderWriter rw = null;

	@JsonProperty(value = "itemModifierList")
	private final List<ItemModifier> itemModifiers;
	private final ItemModifierFactory itemModifierFactory;

	@JsonCreator
	public ItemModifiersStore(@JsonProperty("itemModifierList") List<ItemModifier> itemModifiers,
							  @JacksonInject ItemModifierFactory itemModifierFactory){
		this.itemModifiers = itemModifiers;
		this.itemModifierFactory = itemModifierFactory;
	}

	public ItemModifiersStore(List<ItemModifier> itemModifiers,
							  ItemModifierFactory itemModifierFactory,
							  ItemModifier itemModifierSelected){
		this.itemModifiers = itemModifiers;
		this.itemModifierFactory = itemModifierFactory;
		this.itemModifierSelected = itemModifierSelected;
	}

	public void readInModifiers(){
		long pos = DiabloFilePositions.MODIFIERS_OFFSET;
		long spacing = DiabloFilePositions.MODIFIER_LENGTH_IN_BYTES;
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_MODIFIERS; i++){
			readModifier(pos, itemModifierFactory);
			pos = pos + spacing;
		}
	}

	private void readModifier(long position, ItemModifierFactory itemModifierFactory) {
		long pos = position;
		byte[] readIn = new byte[DiabloFilePositions.MODIFIER_LENGTH_IN_BYTES];
		for(int j = 0; j < DiabloFilePositions.MODIFIER_LENGTH_IN_BYTES; j++){
			readIn[j] = rw.readByte(pos);
			pos++;
		}

		ItemModifier im = itemModifierFactory.constructItemModifier(readIn);
		itemModifiers.add(im);
	}

	public void printModifiers(){
		for(ItemModifier im : itemModifiers){
			im.printModifier();
		}
	}

	public byte[] getModifierAsBytes(int i) {
		return itemModifiers.get(i).getModifierAsBytes();
	}

	public void writeModifiersToEXE() {
		long pos = DiabloFilePositions.MODIFIERS_OFFSET;
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_MODIFIERS; i++){
			byte[] modifierAsBytes = this.getModifierAsBytes(i);
			rw.writeBytes(modifierAsBytes, pos);
			pos = pos + DiabloFilePositions.MODIFIER_LENGTH_IN_BYTES;
		}
	}

	@JsonIgnore
	public String[] getModifierNames() {
		String[] modifierNames = new String[itemModifiers.size()];
		for(int i = 0; i < itemModifiers.size(); i++){
			ItemModifier iMod = itemModifiers.get(i);
			modifierNames[i] = iMod.getName();
		}
		return modifierNames;
	}

	public ItemModifier getModifierByName(String modifierName) {
		ItemModifier modifierToReturn = null;
		for(ItemModifier m : itemModifiers)
		{
			if(m.getName().equals(modifierName))
			{
				modifierToReturn = m;
				break;
			}
		}
		return modifierToReturn;
	}

	public void initialiseUsingBinary(ReaderWriter rw, StringExtractor stringExtractor) {
		this.itemModifierFactory.setStringExtractor(stringExtractor);
		this.rw = rw;
	}

	public void updateWith(ItemModifiersStore modifierStore) {
		for(int i = 0; i < itemModifiers.size(); i++) {
			ItemModifier itemModifier = itemModifiers.get(i);
			ItemModifier modifierToUpdateWith = modifierStore.itemModifiers.get(i);
			itemModifier.updateWith(modifierToUpdateWith);
		}
	}

	@JsonProperty(value = "itemModifierSelected")
	public ItemModifier getItemModifierSelected() {
		return itemModifierSelected;
	}

	@JsonIgnore
	public void setItemModifierSelected(ItemModifier itemModifierSelected) {
		this.itemModifierSelected = itemModifierSelected;
	}
}
