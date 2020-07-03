/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.md for full contributor list.
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
import org.d1ablo.ode.factories.UniqueItemFactory;
import org.d1ablo.ode.knowledge.DiabloFilePositions;
import org.d1ablo.ode.types.UniqueItem;

import java.util.List;

public class UniqueItemStore {

	@JsonIgnore
	private UniqueItem uniqueItemSelected;

	private ReaderWriter rw;

	@JsonProperty(value = "uniqueItemList")
	private final List<UniqueItem> uniqueItems;

	private final UniqueItemFactory uniqueItemFactory;

	@JsonCreator
	public UniqueItemStore(@JsonProperty("uniqueItemList") List<UniqueItem> uniqueItems,
						   @JacksonInject UniqueItemFactory uniqueItemFactory){
		this.uniqueItems = uniqueItems;
		this.uniqueItemFactory = uniqueItemFactory;
	}

	public UniqueItemStore(List<UniqueItem> uniqueItems,
						   UniqueItemFactory uniqueItemFactory,
						   UniqueItem uniqueItemSelected){
		this.uniqueItems = uniqueItems;
		this.uniqueItemFactory = uniqueItemFactory;
		this.uniqueItemSelected = uniqueItemSelected;
	}

	public void readInItems() {
		long pos = DiabloFilePositions.UNIQUE_ITEMS_OFFSET;
		long spacing = DiabloFilePositions.UNIQUE_ITEM_LENGTH_IN_BYTES;
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_UNIQUE_ITEMS; i++){
			readItem(pos, uniqueItemFactory);
			pos = pos + spacing;
		}
	}

	private void readItem(long position, UniqueItemFactory uniqueItemFactory){
		long pos = position;
		byte[] readIn = new byte[DiabloFilePositions.UNIQUE_ITEM_LENGTH_IN_BYTES];
		for(int j = 0; j < DiabloFilePositions.UNIQUE_ITEM_LENGTH_IN_BYTES; j++){
			readIn[j] = rw.readByte(pos);
			pos++;
		}

		UniqueItem ui = uniqueItemFactory.constructUniqueItem(readIn);
		uniqueItems.add(ui);
	}

	public void printItems() {
		for(UniqueItem ui : uniqueItems){
			ui.printItem();
		}
	}

	public byte[] getItemAsBytes(int i) {
		return uniqueItems.get(i).getItemAsBytes();
	}

	public void writeItemsToEXE() {
		long pos = DiabloFilePositions.UNIQUE_ITEMS_OFFSET;
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_UNIQUE_ITEMS; i++){
			byte[] itemAsBytes = this.getItemAsBytes(i);
			rw.writeBytes(itemAsBytes, pos);
			pos = pos + DiabloFilePositions.UNIQUE_ITEM_LENGTH_IN_BYTES;
		}
	}

	@JsonIgnore
	public String[] getItemNames() {
		String[] itemNames = new String[uniqueItems.size()];
		for(int i = 0; i < uniqueItems.size(); i++){
			itemNames[i] = uniqueItems.get(i).getName();
		}
		return itemNames;
	}

	public UniqueItem getUniqueItemByName(String uniqueItemName) {
		UniqueItem itemToReturn = null;
		for(UniqueItem i : uniqueItems)
		{
			if(i.getName().equals(uniqueItemName))
			{
				itemToReturn = i;
				break;
			}
		}
		return itemToReturn;
	}

	public void initialiseUsingBinary(ReaderWriter rw, StringExtractor stringExtractor) {
		this.uniqueItemFactory.setStringExtractor(stringExtractor);
		this.rw = rw;
	}

	public void updateWith(UniqueItemStore uniqueItemStore) {
		for(int i = 0; i < uniqueItems.size(); i++) {
			UniqueItem uniqueItem = uniqueItems.get(i);
			UniqueItem u2 = uniqueItemStore.uniqueItems.get(i);
			uniqueItem.updateFrom(u2);
		}
	}

	@JsonProperty(value = "uniqueItemSelected")
	public UniqueItem getUniqueItemSelected() {
		return uniqueItemSelected;
	}

	@JsonIgnore
	public void setUniqueItemSelected(UniqueItem uniqueItemSelected) {
		this.uniqueItemSelected = uniqueItemSelected;
	}
}
