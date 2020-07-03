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
import org.d1ablo.ode.factories.UniqueMonsterFactory;
import org.d1ablo.ode.knowledge.DiabloFilePositions;
import org.d1ablo.ode.types.UniqueMonster;

import java.util.List;

/**
 * Holds data relating to all the unique (named) monsters within the game.
 */
public class UniqueMonsterStore {

	@JsonIgnore
	private UniqueMonster uniqueMonsterSelected;

	@JsonProperty(value = "uniqueMonsterList")
	private final List<UniqueMonster> uniqueMonsters;
	private final UniqueMonsterFactory uniqueMonsterFactory;

	private ReaderWriter rw;

	@JsonCreator
	public UniqueMonsterStore(@JsonProperty("uniqueMonsterList") List<UniqueMonster> uniqueMonsters,
							  @JacksonInject UniqueMonsterFactory uniqueMonsterFactory) {
		this.uniqueMonsters = uniqueMonsters;
		this.uniqueMonsterFactory = uniqueMonsterFactory;
	}

	public UniqueMonsterStore(List<UniqueMonster> uniqueMonsters,
							  UniqueMonsterFactory uniqueMonsterFactory,
							  UniqueMonster uniqueMonsterSelected) {
		this.uniqueMonsters = uniqueMonsters;
		this.uniqueMonsterFactory = uniqueMonsterFactory;
		this.uniqueMonsterSelected = uniqueMonsterSelected;
	}

	public void readInUniques() {
		long pos = DiabloFilePositions.UNIQUE_MONSTERS_OFFSET;
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_UNIQUE_MONSTERS; i++){
			byte[] monsterBytes = rw.readBytes(pos, DiabloFilePositions.UNIQUE_MONSTER_LENGTH_IN_BYTES);
			UniqueMonster um = uniqueMonsterFactory.constructUniqueMonster(monsterBytes);
			uniqueMonsters.add(um);
			pos = pos + DiabloFilePositions.UNIQUE_MONSTER_LENGTH_IN_BYTES;
		}
	}

	public void printUniques() {
		for(UniqueMonster um : uniqueMonsters){
			um.printUniqueMonster();
		}

	}

	@JsonIgnore
	public byte[] getUniqueAsBytes(int i) {
		return uniqueMonsters.get(i).getUniqueAsBytes();
	}

	public void writeMonstersToEXE() {
		long pos = DiabloFilePositions.UNIQUE_MONSTERS_OFFSET;
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_UNIQUE_MONSTERS; i++){
			byte[] uniqueAsBytes = this.getUniqueAsBytes(i);
			rw.writeBytes(uniqueAsBytes, pos);
			pos = pos + DiabloFilePositions.UNIQUE_MONSTER_LENGTH_IN_BYTES;
		}

	}

	@JsonIgnore
	public String[] getMonsterNames() {
		String[] monsterNames = new String[uniqueMonsters.size()];
		for(int i = 0; i < uniqueMonsters.size(); i++){
			monsterNames[i] = uniqueMonsters.get(i).getName();
		}
		return monsterNames;
	}

	public UniqueMonster getMonsterByName(String monsterName) {
		UniqueMonster monsterToReturn = null;
		for(UniqueMonster m : uniqueMonsters)
		{
			if(m.getName().equals(monsterName))
			{
				monsterToReturn = m;
				break;
			}
		}
		return monsterToReturn;
	}

	public void initialiseUsingBinary(ReaderWriter rw, StringExtractor stringExtractor) {
		this.uniqueMonsterFactory.setStringExtractor(stringExtractor);
		this.rw = rw;
	}

	public void updateWith(UniqueMonsterStore uniqueMonsterStore) {
		for(int i = 0; i < uniqueMonsters.size(); i++) {
			UniqueMonster uniqueMonster = uniqueMonsters.get(i);
			UniqueMonster u2 = uniqueMonsterStore.uniqueMonsters.get(i);
			uniqueMonster.updateFrom(u2);
		}
	}

	@JsonProperty(value = "uniqueMonsterSelected")
	public UniqueMonster getUniqueMonsterSelected() {
		return uniqueMonsterSelected;
	}

	@JsonIgnore
	public void setUniqueMonsterSelected(UniqueMonster uniqueMonsterSelected) {
		this.uniqueMonsterSelected = uniqueMonsterSelected;
	}
}
