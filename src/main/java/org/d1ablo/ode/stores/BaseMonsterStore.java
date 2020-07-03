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
import org.d1ablo.ode.factories.BaseMonsterFactory;
import org.d1ablo.ode.knowledge.DiabloFilePositions;
import org.d1ablo.ode.types.BaseMonster;
import org.d1ablo.ode.types.bytes.MonsterAsBytes;

import java.util.List;

public class BaseMonsterStore {

	@JsonIgnore
	private BaseMonster baseMonsterSelected;

	private ReaderWriter rw;

	@JsonProperty(value = "baseMonsterList")
	private final List<BaseMonster> baseMonsters;

	private final BaseMonsterFactory baseMonsterFactory;

	@JsonCreator
	public BaseMonsterStore(@JsonProperty("baseMonsterList") List<BaseMonster> baseMonsters,
							@JacksonInject BaseMonsterFactory baseMonsterFactory) {
		this.baseMonsters = baseMonsters;
		this.baseMonsterFactory = baseMonsterFactory;
	}

	public BaseMonsterStore(List<BaseMonster> baseMonsters,
							BaseMonsterFactory baseMonsterFactory,
							BaseMonster baseMonsterSelected) {
		this.baseMonsters = baseMonsters;
		this.baseMonsterFactory = baseMonsterFactory;
		this.baseMonsterSelected = baseMonsterSelected;
	}

	public void readInMonsters() {
		long pos = DiabloFilePositions.MONSTER_ACTIVATION_BYTES_OFFSET;
		byte[] activationBytes = rw.readBytes(pos, DiabloFilePositions.NUMBER_OF_BASE_MONSTERS);
		pos = DiabloFilePositions.BASE_MONSTERS_OFFSET;
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_BASE_MONSTERS; i++){
			byte[] monsterBytes = rw.readBytes(pos, DiabloFilePositions.BASE_MONSTER_LENGTH_IN_BYTES);
			BaseMonster bm = baseMonsterFactory.constructBaseMonster(i, monsterBytes, activationBytes[i]);
			baseMonsters.add(bm);
			pos = pos + DiabloFilePositions.BASE_MONSTER_LENGTH_IN_BYTES;
		}
	}

	public MonsterAsBytes getMonsterAsBytes(int index){
		return baseMonsters.get(index).getMonsterAsBytes();
	}

	public void printMonsters() {
		for(BaseMonster bm : baseMonsters){
			bm.printMonster();
		}

	}

	public void writeMonstersToEXE() {
		long pos = DiabloFilePositions.BASE_MONSTERS_OFFSET;
		long posTwo = DiabloFilePositions.MONSTER_ACTIVATION_BYTES_OFFSET;
		for(BaseMonster bm : baseMonsters){
			MonsterAsBytes mab = bm.getMonsterAsBytes();
			byte[] mainBytes = mab.getMainBytes();
			byte monsterActivationByte = mab.getEnabledByte();
			rw.writeBytes(mainBytes, pos);
			rw.writeByte(monsterActivationByte, posTwo);
			pos = pos + DiabloFilePositions.BASE_MONSTER_LENGTH_IN_BYTES;
			posTwo = posTwo + 1; //activation bytes are next to each other
		}
	}

	@JsonIgnore
	public String[] getMonsterNames() {
		String[] monsterNames = new String[baseMonsters.size()];
		int monsterNamesIndex = 0;
		for(BaseMonster bm : baseMonsters){
			monsterNames[monsterNamesIndex] = bm.getName();
			monsterNamesIndex++;
		}
		return monsterNames;
	}

	public BaseMonster getMonsterByName(String monsterName) {
		BaseMonster baseMonsterToReturn = null;
		for(BaseMonster m : baseMonsters){
			if(m.getName().equals(monsterName)){
				baseMonsterToReturn = m;
				break;
			}
		}
		return baseMonsterToReturn;
	}

	public void initialiseUsingBinary(ReaderWriter rw, StringExtractor stringExtractor) {
		this.baseMonsterFactory.setStringExtractor(stringExtractor);
		this.rw = rw;
	}

	public void updateWith(BaseMonsterStore baseMonsterStore) {
		for(int i = 0; i < baseMonsters.size(); i++) {
			BaseMonster baseMonster = baseMonsters.get(i);
			BaseMonster monsterToUpdateFrom = baseMonsterStore.baseMonsters.get(i);
			baseMonster.updateWith(monsterToUpdateFrom);
		}
	}

	@JsonProperty(value = "baseMonsterSelected")
	public BaseMonster getBaseMonsterSelected() {
		return baseMonsterSelected;
	}

	@JsonIgnore
	public void setBaseMonsterSelected(BaseMonster baseMonsterSelected) {
		this.baseMonsterSelected = baseMonsterSelected;
	}
}
