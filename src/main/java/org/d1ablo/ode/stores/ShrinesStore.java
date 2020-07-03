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
import org.d1ablo.ode.bintool.BinEditTool;
import org.d1ablo.ode.bintool.StringExtractor;
import org.d1ablo.ode.bintool.rw.ReaderWriter;
import org.d1ablo.ode.factories.ShrineFactory;
import org.d1ablo.ode.knowledge.DiabloFilePositions;
import org.d1ablo.ode.types.Shrine;
import org.d1ablo.ode.types.bytes.ShrinesAsBytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Holds data relating to all the shrines within the game.
 */
public class ShrinesStore {

	@JsonIgnore
	private Shrine shrineSelected;

	private final static Logger logger = LoggerFactory.getLogger(ShrinesStore.class);

	private ReaderWriter rw;
	private final BinEditTool beh;

	@JsonProperty(value = "shrineList")
	private final List<Shrine> shrines;

	private final ShrineFactory shrineFactory;

	byte[] origShrinePointerBytes;
	byte[] origMinShrineLevelBytes;
	byte[] origMaxShrineLevelBytes;
	byte[] origGameTypesInWhichPresentBytes;

	@JsonCreator
	public ShrinesStore(@JsonProperty("shrineList") List<Shrine> shrines, @JacksonInject BinEditTool beh,
						@JacksonInject ShrineFactory shrineFactory){
		this.shrines = shrines;
		this.beh = beh;
		this.shrineFactory = shrineFactory;
	}

	public ShrinesStore(List<Shrine> shrines,
						BinEditTool beh,
						ShrineFactory shrineFactory,
						Shrine shrineSelected){
		this.shrines = shrines;
		this.beh = beh;
		this.shrineFactory = shrineFactory;
		this.shrineSelected = shrineSelected;
	}

	//TODO -- simplify if possible
	public void readInShrines(){
		
		long[] shrinePointers = new long[DiabloFilePositions.NUMBER_OF_SHRINES];
		int[] minShrineLevels = new int[DiabloFilePositions.NUMBER_OF_SHRINES];
		int[] maxShrineLevels = new int[DiabloFilePositions.NUMBER_OF_SHRINES];
		int[] gameTypesInWhichPresent = new int[DiabloFilePositions.NUMBER_OF_SHRINES];

		long pos = DiabloFilePositions.SHRINE_POINTERS_OFFSET;
		origShrinePointerBytes = rw.readBytes(pos, DiabloFilePositions.NUMBER_OF_SHRINES*4);
		for(int shrineIdx = 0; shrineIdx < DiabloFilePositions.NUMBER_OF_SHRINES; shrineIdx++){
			byte[] pointerBytes = new byte[4];
			System.arraycopy(origShrinePointerBytes, shrineIdx * 4, pointerBytes, 0, pointerBytes.length);
			shrinePointers[shrineIdx] = beh.convertFourBytesToOffset(pointerBytes, 0);
		}

		pos = DiabloFilePositions.SHRINE_MIN_LEVELS_OFFSET;
		origMinShrineLevelBytes = rw.readBytes(pos, DiabloFilePositions.NUMBER_OF_SHRINES);
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_SHRINES; i++){
			minShrineLevels[i] = rw.read(pos);
			pos++;
		}

		pos = DiabloFilePositions.SHRINE_MAX_LEVELS_OFFSET;
		origMaxShrineLevelBytes = rw.readBytes(pos, DiabloFilePositions.NUMBER_OF_SHRINES);
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_SHRINES; i++){
			maxShrineLevels[i] = rw.read(pos);
			pos++;
		}

		pos = DiabloFilePositions.SHRINE_GAME_TYPE_OFFSET;
		origGameTypesInWhichPresentBytes = rw.readBytes(pos, DiabloFilePositions.NUMBER_OF_SHRINES);
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_SHRINES; i++){
			gameTypesInWhichPresent[i] = rw.read(pos);
			pos++;
		}

		//create Shrine objects and add to list
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_SHRINES; i++){
			Shrine s = shrineFactory.constructShrine(i, shrinePointers, minShrineLevels,
					maxShrineLevels, gameTypesInWhichPresent);
			shrines.add(s);
		}
	}

	public void printShrines() {
		for(Shrine s : shrines){
			s.printShrine();
		}
	}

	@JsonIgnore
	public ShrinesAsBytes getShrinesAsBytes() {
		byte[] shrinePointerBytes = this.getShrinePointerBytes();
		byte[] minShrineLevelBytes = this.getMinShrineLevelBytes();
		byte[] maxShrineLevelBytes = this.getMaxShrineLevelBytes();
		byte[] gameTypesInWhichPresentBytes = this.getGameTypesInWhichPresentBytes();
		return new ShrinesAsBytes(shrinePointerBytes, minShrineLevelBytes, maxShrineLevelBytes, gameTypesInWhichPresentBytes);
	}

	@JsonIgnore
	public byte[] getShrinePointerBytes(){
		byte[] shrinePointerBytes = new byte[DiabloFilePositions.NUMBER_OF_SHRINES*4];
		int location = 0;
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_SHRINES; i++){
			Shrine s = shrines.get(i);
			byte[] singleShrineBytes = s.getShrinePointerBytes();
			System.arraycopy(singleShrineBytes, 0, shrinePointerBytes, location, 4);
			location = location + 4;
		}
		return shrinePointerBytes;
	}

	@JsonIgnore
	public byte[] getMinShrineLevelBytes(){
		byte[] minShrineLevelBytes = new byte[DiabloFilePositions.NUMBER_OF_SHRINES];
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_SHRINES; i++){
			Shrine s = shrines.get(i);
			minShrineLevelBytes[i] = s.getMinShrineLevelByte();
		}
		return minShrineLevelBytes;
	}

	@JsonIgnore
	public byte[] getMaxShrineLevelBytes(){
		byte[] maxShrineLevelBytes = new byte[DiabloFilePositions.NUMBER_OF_SHRINES];
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_SHRINES; i++){
			Shrine s = shrines.get(i);
			maxShrineLevelBytes[i] = s.getMaxShrineLevelByte();
		}
		return maxShrineLevelBytes;
	}

	@JsonIgnore
	public byte[] getGameTypesInWhichPresentBytes(){
		byte[] gameTypesInWhichPresentBytes = new byte[DiabloFilePositions.NUMBER_OF_SHRINES];
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_SHRINES; i++){
			Shrine s = shrines.get(i);
			gameTypesInWhichPresentBytes[i] = s.getGameTypeByte();
		}
		return gameTypesInWhichPresentBytes;
	}

	public Shrine getShrine(int i) {
		return shrines.get(i);
	}

	public void writeShrinesToEXE() {
		ShrinesAsBytes sab = this.getShrinesAsBytes();

		byte[] shrinePointerBytes = sab.getShrinePointerBytes();
		rw.writeBytes(shrinePointerBytes, DiabloFilePositions.SHRINE_POINTERS_OFFSET);

		byte[] minShrineLevelBytes = sab.getMinShrineLevelBytes();
		rw.writeBytes(minShrineLevelBytes, DiabloFilePositions.SHRINE_MIN_LEVELS_OFFSET);

		byte[] maxShrineLevelBytes = sab.getMaxShrineLevelBytes();
		rw.writeBytes(maxShrineLevelBytes, DiabloFilePositions.SHRINE_MAX_LEVELS_OFFSET);

		byte[] gameTypesInWhichPresentBytes = sab.getGameTypeBytes();
		rw.writeBytes(gameTypesInWhichPresentBytes, DiabloFilePositions.SHRINE_GAME_TYPE_OFFSET);
	}

	public void disableBadShrines() {
		Shrine hiddenShrine = this.getShrine(1);
		hiddenShrine.setMinShrineLevel(0);
		hiddenShrine.setMaxShrineLevel(0);

		Shrine fascinatingShrine = this.getShrine(9);
		fascinatingShrine.setMinShrineLevel(0);
		fascinatingShrine.setMaxShrineLevel(0);

		Shrine sacredShrine = this.getShrine(16);
		sacredShrine.setMinShrineLevel(0);
		sacredShrine.setMaxShrineLevel(0);

		Shrine secludedShrine = this.getShrine(22);
		secludedShrine.setMinShrineLevel(0);
		secludedShrine.setMaxShrineLevel(0);

		Shrine ornateShrine = this.getShrine(23);
		ornateShrine.setMinShrineLevel(0);
		ornateShrine.setMaxShrineLevel(0);

		Shrine taintedShrine = this.getShrine(25);
		taintedShrine.setMinShrineLevel(0);
		taintedShrine.setMaxShrineLevel(0);
	}

	@JsonIgnore
	public String[] getShrineNames() {
		String[] shrineNames = new String[shrines.size()];
		for(int i = 0; i < shrines.size(); i++){
			shrineNames[i] = shrines.get(i).getShrineName();
		}
		return shrineNames;
	}

	public Shrine getShrineByName(String shrineName) {
		Shrine shrineToReturn = null;
		for(Shrine s : shrines)
		{
			if(s.getShrineName().equals(shrineName))
			{
				shrineToReturn = s;
				break;
			}
		}
		return shrineToReturn;
	}

	public void initialiseUsingBinary(ReaderWriter rw, StringExtractor stringExtractor) {
		this.shrineFactory.setStringExtractor(stringExtractor);
		this.rw = rw;
	}

	public void updateWith(ShrinesStore shrineStore) {
		for(int i = 0; i < shrines.size(); i++) {
			Shrine shrine = shrines.get(i);
			Shrine s2 = shrineStore.shrines.get(i);
			shrine.updateWith(s2);
		}
	}

	@JsonProperty(value = "shrineSelected")
	public Shrine getShrineSelected() {
		return shrineSelected;
	}

	@JsonIgnore
	public void setShrineSelected(Shrine shrineSelected) {
		this.shrineSelected = shrineSelected;
	}
}
