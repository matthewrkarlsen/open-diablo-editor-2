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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.d1ablo.ode.knowledge.DiabloFilePositions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Holds all the data relating to a single shrine within the game.
 */
public class Shrine {
	
	private final static Logger logger = LoggerFactory.getLogger(Shrine.class);

	@JsonProperty("shrineName")
	private String shrineName;

	@JsonProperty("shrinePointer")
	private long shrinePointer;

	@JsonProperty("minShrineLevel")
	private int minShrineLevel;

	@JsonProperty("maxShrineLevel")
	private int maxShrineLevel;

	@JsonProperty("gameTypesInWhichPresent")
	private int gameTypesInWhichPresent;

	@JsonProperty("shrineIndex")
	private int shrineIndex;

	public Shrine(int shrineIndex, String shrineName, long shrinePointer, int minShrineLevel, int maxShrineLevel,
				  int gameTypesInWhichPresent){
		this.shrineIndex = shrineIndex;
		this.shrineName = shrineName;
		this.shrinePointer = shrinePointer;
		this.minShrineLevel = minShrineLevel;
		this.maxShrineLevel = maxShrineLevel;
		this.gameTypesInWhichPresent = gameTypesInWhichPresent;
	}

	@JsonCreator
	public Shrine() {

	}

    public void printShrine() {
		logger.info("Shrine index: " + shrineIndex);
		logger.info("Shrine name: " + shrineName);
		logger.info("Shrine name pointer: " + shrinePointer);
		logger.info("Min shrine level: " + minShrineLevel);
		logger.info("Max shrine level: " + maxShrineLevel);
		logger.info("SP+MP(0)/SP(1)/MP(2): " + gameTypesInWhichPresent);
		logger.info("");
	}

	@JsonIgnore
	public byte[] getShrinePointerBytes() {
		byte[] shrinePointerBytes = new byte[4];
		long shrinePointerRev = shrinePointer + DiabloFilePositions.DIABLO_POINTERS_OFFSET;
		shrinePointerBytes[0] = (byte)(shrinePointerRev);
		shrinePointerBytes[1] = (byte)(shrinePointerRev >>> 8);
		shrinePointerBytes[2] = (byte)(shrinePointerRev >>> 16);
		shrinePointerBytes[3] = (byte)(shrinePointerRev >>> 32);
		return shrinePointerBytes;
	}

	@JsonIgnore
	public byte getMinShrineLevelByte() {
		return (byte) minShrineLevel;
	}

	@JsonIgnore
	public byte getMaxShrineLevelByte() {
		return (byte) maxShrineLevel;
	}

	@JsonIgnore
	public byte getGameTypeByte() {
		return (byte) gameTypesInWhichPresent;
	}

	public void setMinShrineLevel(int minShrineLevel) {
		//TODO -- validation
		this.minShrineLevel = minShrineLevel;
	}

	public void setMaxShrineLevel(int maxShrineLevel) {
		//TODO -- validation
		this.maxShrineLevel = maxShrineLevel;
	}

	public String getShrineName() {
		return shrineName;
	}

	public void setShrineName(String shrineName) {
		//TODO -- validation
		this.shrineName = shrineName;
	}

	public long getShrinePointer() {
		return shrinePointer;
	}

	public void setShrinePointer(long shrinePointer) {
		//TODO -- validation
		this.shrinePointer = shrinePointer;
	}

	public int getMinShrineLevel() {
		return minShrineLevel;
	}

	public int getMaxShrineLevel() {
		return maxShrineLevel;
	}

	public int getGameTypesInWhichPresent() {
		return gameTypesInWhichPresent;
	}

	public void setGameTypesInWhichPresent(int gameTypesInWhichPresent) {
		//TODO -- validation
		this.gameTypesInWhichPresent = gameTypesInWhichPresent;
	}

	public int getShrineIndex() {
		return shrineIndex;
	}

	public void setShrineIndex(int shrineIndex) {
		//TODO -- validation
		this.shrineIndex = shrineIndex;
	}

	public void updateWith(Shrine s2) {
		setShrineName(s2.shrineName);
		setShrinePointer(s2.shrinePointer);
		setMinShrineLevel(s2.minShrineLevel);
		setMaxShrineLevel(s2.maxShrineLevel);
		setGameTypesInWhichPresent(s2.gameTypesInWhichPresent);
		setShrineIndex(s2.shrineIndex);
	}
}
