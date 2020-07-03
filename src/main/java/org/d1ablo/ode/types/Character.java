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
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Holds the data relating to a single character within the game.
 */
public class Character {
	
	private final static Logger logger = LoggerFactory.getLogger(Character.class);

	@JsonProperty("index")
	private int index;

	@JsonProperty("className")
	private String className;

	@JsonProperty("initStrength")
	private long initStrength;

	@JsonProperty("maxStrength")
	private long maxStrength;

	@JsonProperty("initMagic")
	private long initMagic;

	@JsonProperty("maxMagic")
	private long maxMagic;

	@JsonProperty("initDexterity")
	private long initDexterity;

	@JsonProperty("maxDexterity")
	private long maxDexterity;

	@JsonProperty("initVitality")
	private long initVitality;

	@JsonProperty("maxVitality")
	private long maxVitality;

	@JsonProperty("blockingBonus")
	private long blockingBonus;

	@JsonProperty("dungeonIdleFrameset")
	private int dungeonIdleFrameset;

	@JsonProperty("attackingFrameset")
	private int attackingFrameset;

	@JsonProperty("dungeonWalkFrameset")
	private int dungeonWalkFrameset;

	@JsonProperty("blockSpeed")
	private int blockSpeed; //NOTE: # framesets must not be < speed value on any speed

	@JsonProperty("deathFrameset")
	private int deathFrameset;

	@JsonProperty("castingFrameset")
	private int castingFrameset;

	@JsonProperty("hitRecoverySpeed")
	private int hitRecoverySpeed;

	@JsonProperty("townIdleFrameset")
	private int townIdleFrameset;

	@JsonProperty("townWalkFrameset")
	private int townWalkFrameset;

	@JsonProperty("oneHandedAttackSpeed")
	private int oneHandedAttackSpeed;

	@JsonProperty("castingSpeed")
	private int castingSpeed;

	@JsonCreator
	public Character(){

	}

	public Character(int index, String className, long initStrength, long initMagic, long initDexterity,
					 long initVitality, long maxStrength, long maxMagic, long maxDexterity, long maxVitality,
					 long blockingBonus, int dungeonIdleFrameset, int attackingFrameset, int dungeonWalkFrameset,
					 int blockSpeed, int deathFrameset, int castingFrameset, int hitRecoverySpeed,
					 int townIdleFrameset, int townWalkFrameset, int oneHandedAttackSpeed, int castingSpeed) {
		this.index = index;
		this.className = className;
		this.initStrength = initStrength;
		this.initMagic = initMagic;
		this.initDexterity = initDexterity;
		this.initVitality = initVitality;
		this.maxStrength = maxStrength;
		this.maxMagic = maxMagic;
		this.maxDexterity = maxDexterity;
		this.maxVitality = maxVitality;
		this.blockingBonus = blockingBonus;
		this.dungeonIdleFrameset = dungeonIdleFrameset;
		this.attackingFrameset = attackingFrameset;
		this.dungeonWalkFrameset = dungeonWalkFrameset;
		this.blockSpeed = blockSpeed;
		this.deathFrameset = deathFrameset;
		this.castingFrameset = castingFrameset;
		this.hitRecoverySpeed = hitRecoverySpeed;
		this.townIdleFrameset = townIdleFrameset;
		this.townWalkFrameset = townWalkFrameset;
		this.oneHandedAttackSpeed = oneHandedAttackSpeed;
		this.castingSpeed = castingSpeed;
	}

	public void printCharacter() {
		logger.info(
			"Class: " + className + "\n" +
			"STR: " + initStrength + "--" + maxStrength + "\n" +
			"MAG: " + initMagic + "--" + maxMagic + "\n" +
			"DEX: " + initDexterity + "--" + maxDexterity + "\n" +
			"VIT: " + initVitality + "--" + maxVitality + "\n" +
			"Blocking bonus: " + blockingBonus + "\n" +
			"Dungeon idle frameset: " + dungeonIdleFrameset + "\n" +
			"Attacking frameset: " + attackingFrameset + "\n" +
			"Dungeon walk frameset: " + dungeonWalkFrameset + "\n" +
			"Block speed: " + blockSpeed + " (" + (0.05 * blockSpeed) + "secs)" + "\n" +
			"Death frameset: " + deathFrameset + "\n" +
			"Casting frameset: " + castingFrameset + "\n" +
			"Hit recovery speed: " + hitRecoverySpeed + " (" + (0.05 * hitRecoverySpeed) + "secs)" + "\n" +
			"Town idle frameset: " + townIdleFrameset + "\n" +
			"Town walk frameset: " + townWalkFrameset + "\n" +
			"One handed attack speed: " + oneHandedAttackSpeed + "\n" +
			"Casting speed: " + castingSpeed + " (" + (0.05 * castingSpeed) + "secs)" + "\n"
		);
	}

	@JsonProperty("initStrength")
	public long getInitStrength() {
		return initStrength;
	}

	public void setInitStrength(long initStrength) {
		//TODO -- validation
		this.initStrength = initStrength;
	}

	public long getMaxStrength() {
		return maxStrength;
	}

	public void setMaxStrength(long maxStrength) {
		//TODO -- validation
		this.maxStrength = maxStrength;
	}

	public long getInitMagic() {
		return initMagic;
	}

	public void setInitMagic(long initMagic) {
		//TODO -- validation
		this.initMagic = initMagic;
	}

	public long getMaxMagic() {
		return maxMagic;
	}

	public void setMaxMagic(long maxMagic) {
		//TODO -- validation
		this.maxMagic = maxMagic;
	}

	public long getInitDexterity() {
		return initDexterity;
	}

	public void setInitDexterity(long initDexterity) {
		//TODO -- validation
		this.initDexterity = initDexterity;
	}

	public long getMaxDexterity() {
		return maxDexterity;
	}

	public void setMaxDexterity(long maxDexterity) {
		//TODO -- validation
		this.maxDexterity = maxDexterity;
	}

	public long getInitVitality() {
		return initVitality;
	}

	public void setInitVitality(long initVitality) {
		//TODO -- validation
		this.initVitality = initVitality;
	}

	public long getMaxVitality() {
		return maxVitality;
	}

	public void setMaxVitality(long maxVitality) {
		//TODO -- validation
		this.maxVitality = maxVitality;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String name) {
		//TODO -- validation
		this.className = name;
	}

	public long getBlockingBonus() {
		return blockingBonus;
	}

	public void setBlockingBonus(long blockingBonus) {
		//TODO -- validation
		this.blockingBonus = blockingBonus;
	}

	public int getDungeonIdleFrameset() {
		return dungeonIdleFrameset;
	}

	public void setDungeonIdleFrameset(int dungeonIdleFrameset) {
		//TODO -- validation
		this.dungeonIdleFrameset = dungeonIdleFrameset;
	}

	public int getAttackingFrameset() {
		return attackingFrameset;
	}

	public void setAttackingFrameset(int attackingFrameset) {
		//TODO -- validation
		this.attackingFrameset = attackingFrameset;
	}

	public int getDungeonWalkFrameset() {
		return dungeonWalkFrameset;
	}

	public void setDungeonWalkFrameset(int dungeonWalkFrameset) {
		//TODO -- validation
		this.dungeonWalkFrameset = dungeonWalkFrameset;
	}

	public int getBlockSpeed() {
		return blockSpeed;
	}

	public void setBlockSpeed(int blockSpeed) {
		//TODO -- validation
		//NOTE: # framesets must not be < speed value on any speed
		this.blockSpeed = blockSpeed;
	}

	public int getDeathFrameset() {
		return deathFrameset;
	}

	public void setDeathFrameset(int deathFrameset) {
		//TODO -- validation
		this.deathFrameset = deathFrameset;
	}

	public int getCastingFrameset() {
		return castingFrameset;
	}

	public void setCastingFrameset(int castingFrameset) {
		//TODO -- validation
		this.castingFrameset = castingFrameset;
	}

	public int getHitRecoverySpeed() {
		return hitRecoverySpeed;
	}

	public void setHitRecoverySpeed(int hitRecoverySpeed) {
		//TODO -- validation
		this.hitRecoverySpeed = hitRecoverySpeed;
	}

	public int getTownIdleFrameset() {
		return townIdleFrameset;
	}

	public void setTownIdleFrameset(int townIdleFrameset) {
		//TODO -- validation
		this.townIdleFrameset = townIdleFrameset;
	}

	public int getTownWalkFrameset() {
		return townWalkFrameset;
	}

	public void setTownWalkFrameset(int townWalkFrameset) {
		//TODO -- validation
		this.townWalkFrameset = townWalkFrameset;
	}

	public int getOneHandedAttackSpeed() {
		return oneHandedAttackSpeed;
	}

	public void setOneHandedAttackSpeed(int oneHandedAttackSpeed) {
		//TODO -- validation
		this.oneHandedAttackSpeed = oneHandedAttackSpeed;
	}

	public int getCastingSpeed() {
		return castingSpeed;
	}

	public void setCastingSpeed(int castingSpeed) {
		//TODO -- validation
		this.castingSpeed = castingSpeed;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		//TODO -- validation
		this.index = index;
	}

	public void updateWith(Character charToUpdateFrom) {
		setIndex(charToUpdateFrom.index);
		setClassName(charToUpdateFrom.className);
		setInitStrength(charToUpdateFrom.initStrength);
		setMaxStrength(charToUpdateFrom.maxStrength);
		setInitDexterity(charToUpdateFrom.initDexterity);
		setMaxDexterity(charToUpdateFrom.maxDexterity);
		setInitMagic(charToUpdateFrom.initMagic);
		setMaxMagic(charToUpdateFrom.maxMagic);
		setInitVitality(charToUpdateFrom.initVitality);
		setMaxVitality(charToUpdateFrom.maxVitality);
		setBlockingBonus(charToUpdateFrom.blockingBonus);
		setDungeonIdleFrameset(charToUpdateFrom.dungeonIdleFrameset);
		setAttackingFrameset(charToUpdateFrom.attackingFrameset);
		setDungeonWalkFrameset(charToUpdateFrom.dungeonWalkFrameset);
		setBlockSpeed(charToUpdateFrom.blockSpeed);
		setDeathFrameset(charToUpdateFrom.deathFrameset);
		setCastingFrameset(charToUpdateFrom.castingFrameset);
		setHitRecoverySpeed(charToUpdateFrom.hitRecoverySpeed);
		setTownIdleFrameset(charToUpdateFrom.townIdleFrameset);
		setTownWalkFrameset(charToUpdateFrom.townWalkFrameset);
		setOneHandedAttackSpeed(charToUpdateFrom.oneHandedAttackSpeed);
		setCastingSpeed(charToUpdateFrom.castingSpeed);
	}
}
