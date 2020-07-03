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
import org.d1ablo.ode.factories.BaseItemFactory;
import org.d1ablo.ode.knowledge.DiabloFilePositions;
import org.d1ablo.ode.types.BaseItem;

import java.util.List;

/**
 * A store for base item objects belonging 
 * to the BaseItem class. Interfaces with
 * ReaderWriter for the purposes of reading
 * in items to the store and writing out
 * items back to the EXE. 
 */
public class BaseItemStore {

	@JsonIgnore
	private BaseItem baseItemSelected;

	private ReaderWriter rw;

	@JsonProperty(value = "baseItemList")
	private final List<BaseItem> baseItems;

	private final BaseItemFactory baseItemFactory;

	@JsonCreator
	public BaseItemStore(@JsonProperty("baseItemList") List<BaseItem> baseItems,
						 @JacksonInject BaseItemFactory baseItemFactory) {
		this.baseItems = baseItems;
		this.baseItemFactory = baseItemFactory;
	}

	public BaseItemStore(List<BaseItem> baseItems,
						 BaseItemFactory baseItemFactory,
						 BaseItem baseItemSelected) {
		this.baseItems = baseItems;
		this.baseItemFactory = baseItemFactory;
		this.baseItemSelected = baseItemSelected;
	}

	public void readInItems() {
		long pos = DiabloFilePositions.BASE_ITEMS_OFFSET;
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_BASE_ITEMS; i++){
			byte[] itemBytes = rw.readBytes(pos, DiabloFilePositions.BASE_ITEM_LENGTH_IN_BYTES);
			BaseItem bi = baseItemFactory.constructBaseItem(i, itemBytes);
			baseItems.add(bi);
			pos = pos + DiabloFilePositions.BASE_ITEM_LENGTH_IN_BYTES;
		}
	}

	public void printItems() {
		for(BaseItem bi : baseItems){
			bi.printItem();
		}
	}

	public void writeItemsToEXE() {
		long pos = DiabloFilePositions.BASE_ITEMS_OFFSET;
		for(BaseItem bi : baseItems){
			byte[] itemAsBytes = bi.getItemAsBytes();
			rw.writeBytes(itemAsBytes, pos);
			pos = pos + DiabloFilePositions.BASE_ITEM_LENGTH_IN_BYTES;
		}

	}

	public byte[] getItemAsBytes(int i) {
		return baseItems.get(i).getItemAsBytes();
	}

	@JsonIgnore
	public String[] getItemNames() {
		String[] itemNames = new String[baseItems.size()];
		int itemNamesIndex = 0;
		for(BaseItem bi : baseItems){
			itemNames[itemNamesIndex] = bi.getName();
			itemNamesIndex++;
		}
		return itemNames;
	}

	public BaseItem getItemByName(String itemName) {
		BaseItem itemToReturn = null;
		for(BaseItem item : baseItems){
			if(item.getName().equals(itemName)){
				itemToReturn = item;
				break;
			}
		}
		return itemToReturn;
	}

	public void initialiseUsingBinary(ReaderWriter rw, StringExtractor stringExtractor) {
		this.baseItemFactory.setStringExtractor(stringExtractor);
		this.rw = rw;
	}

	public void updateWith(BaseItemStore baseItemStore) {
		for(int i = 0; i < baseItems.size(); i++) {
			BaseItem baseItem = baseItems.get(i);
			BaseItem itemToUpdateFrom = baseItemStore.baseItems.get(i);
			baseItem.updateWith(itemToUpdateFrom);
		}

	}

	@JsonProperty(value = "baseItemSelected")
	public BaseItem getBaseItemSelected() {
		return baseItemSelected;
	}

	@JsonIgnore
	public void setBaseItemSelected(BaseItem baseItemSelected) {
		this.baseItemSelected = baseItemSelected;
	}
}
