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
import org.d1ablo.ode.bintool.BinEditTool;
import org.d1ablo.ode.knowledge.DiabloFilePositions;
import org.d1ablo.ode.types.bytes.MonsterAsBytes;
import org.d1ablo.ode.utils.ValueChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Each {@link BaseMonster} object acts as a repository for the information
 * pertaining to a particular in-game monster. This is for
 * generic monsters only (i.e. not unique monsters, which use {@link UniqueMonster}).
 */
public class BaseMonster {
	
	private final static Logger logger = LoggerFactory.getLogger(BaseMonster.class);

	@JsonProperty("animationSize")
	private long animationSize;

	@JsonProperty("seedingSize")
	private long seedingSize;

	@JsonProperty("animationFilePointer")
	private long animationFilePointer;

	@JsonProperty("animationFileName")
	private String animationFileName;

	@JsonProperty("secondAttack")
	private long secondAttack;

	@JsonProperty("soundPointer")
	private long soundPointer;

	@JsonProperty("hasSecondAttackSound")
	private long hasSecondAttackSound;

	@JsonProperty("usesTrnToModColor")
	private long usesTrnToModColor;

	@JsonProperty("trnPointer")
	private long trnPointer;

	@JsonProperty("idleFrameSet")
	private long idleFrameSet;

	@JsonProperty("walkFrameSet")
	private long walkFrameSet;

	@JsonProperty("attackFrameSet")
	private long attackFrameSet;

	@JsonProperty("hitRecoveryFrameSet")
	private long hitRecoveryFrameSet;

	@JsonProperty("deathFrameSet")
	private long deathFrameSet;

	@JsonProperty("secondAttackFrameSet")
	private long secondAttackFrameSet;

	@JsonProperty("idlePlaybackSpeed")
	private long idlePlaybackSpeed;

	@JsonProperty("walkPlaybackSpeed")
	private long walkPlaybackSpeed;

	@JsonProperty("attackPlaybackSpeed")
	private long attackPlaybackSpeed;

	@JsonProperty("hitRecoverySpeed")
	private long hitRecoverySpeed;

	@JsonProperty("deathPlaybackSpeed")
	private long deathPlaybackSpeed;

	@JsonProperty("secondAttackSpeed")
	private long secondAttackSpeed;

	@JsonProperty("namePointer")
	private long namePointer;

	@JsonProperty("name")
	private String name;

	@JsonProperty("minDungeonLevel")
	private int minDungeonLevel;

	@JsonProperty("maxDungeonLevel")
	private int maxDungeonLevel;

	@JsonProperty("monsterItemLevel")
	private int monsterItemLevel;

	@JsonProperty("minHitPoints")
	private long minHitPoints;

	@JsonProperty("maxHitPoints")
	private long maxHitPoints;

	@JsonProperty("attackType1")
	private int attackType1;

	@JsonProperty("attackType2")
	private int attackType2;

	@JsonProperty("attackType3")
	private int attackType3;

	@JsonProperty("attackType4")
	private int attackType4;

	@JsonProperty("attackType5")
	private int attackType5;

	@JsonProperty("monsterIntelligence")
	private int monsterIntelligence;

	@JsonProperty("attackType7")
	private int attackType7;

	@JsonProperty("attackType8")
	private int attackType8;

	@JsonProperty("subType")
	private int subType;

	@JsonProperty("monsterPriChanceToHit")
	private int priChanceToHit;

	@JsonProperty("priToHitFrame")
	private int priToHitFrame;

	@JsonProperty("priMinAttackDamage")
	private int priMinAttackDamage;

	@JsonProperty("priMaxAttackDamage")
	private int priMaxAttackDamage;

	@JsonProperty("secToHitChance")
	private int secToHitChance;

	@JsonProperty("secToHitFrame")
	private int secToHitFrame;

	@JsonProperty("secMinAttackDamage")
	private int secMinAttackDamage;

	@JsonProperty("secMaxAttackDamage")
	private int secMaxAttackDamage;

	@JsonProperty("monsterAc")
	private int monsterAc;

	@JsonProperty("monsterType")
	private int monsterType;

	@JsonProperty("resistancesNormAndNightmare")
	private String resistancesNormAndNightmare;

	@JsonProperty("resistancesHell")
	private String resistancesHell;

	@JsonProperty("itemDropSpecials")
	private int itemDropSpecials;

	@JsonProperty("monsterSelectionOutline")
	private int monsterSelectionOutline;

	@JsonProperty("experiencePoints")
	private long experiencePoints;

	@JsonProperty("enabled")
	private int enabled; //TODO -- convert to boolean

	@JsonProperty("slotNumber")
	private int slotNumber;

	@JsonCreator
	public BaseMonster() {

	}

	/**
	 * Construct a new base monster.
	 *
	 * FIXME -- document
	 */
	public BaseMonster(int slotNumber, long animationSize, long seedingSize, long animationFilePointer,
					   String animationFileName, long secondAttack, long soundPointer, long hasSecondAttackSound,
					   long usesTrnToModColor, long trnPointer, long idleFrameSet, long walkFrameSet,
					   long attackFrameSet, long hitRecoveryFrameSet, long deathFrameSet, long secondAttackFrameSet,
					   long idlePlaybackSpeed, long walkPlaybackSpeed, long attackPlaybackSpeed,
					   long hitRecoverySpeed, long deathPlaybackSpeed, long secondAttackSpeed,
					   long namePointer, String name, int minDungeonLevel, int maxDungeonLevel,
					   int monsterItemLevel, long minHitPoints, long maxHitPoints, int attackType1,
					   int attackType2, int attackType3, int attackType4, int attackType5, int monsterIntelligence,
					   int attackType7, int attackType8, int subType, int priChanceToHit, int priToHitFrame,
					   int priMinAttackDamage, int priMaxAttackDamage, int secToHitChance, int secToHitFrame,
					   int secMinAttackDamage, int secMaxAttackDamage, int monsterAc, int monsterType,
					   String resistancesNormAndNightmare, String resistancesHell, int itemDropSpecials,
					   int monsterSelectionOutline, long experiencePoints, int enabled) {
		this.slotNumber = slotNumber;
		this.animationSize = animationSize;
		this.seedingSize = seedingSize;
		this.animationFilePointer = animationFilePointer;
		this.animationFileName = animationFileName;
		this.secondAttack = secondAttack;
		this.soundPointer = soundPointer;
		this.hasSecondAttackSound = hasSecondAttackSound;
		this.usesTrnToModColor = usesTrnToModColor;
		this.trnPointer = trnPointer;
		this.idleFrameSet = idleFrameSet;
		this.walkFrameSet = walkFrameSet;
		this.attackFrameSet = attackFrameSet;
		this.hitRecoveryFrameSet = hitRecoveryFrameSet;
		this.deathFrameSet = deathFrameSet;
		this.secondAttackFrameSet = secondAttackFrameSet;
		this.idlePlaybackSpeed = idlePlaybackSpeed;
		this.walkPlaybackSpeed = walkPlaybackSpeed;
		this.attackPlaybackSpeed = attackPlaybackSpeed;
		this.hitRecoverySpeed = hitRecoverySpeed;
		this.deathPlaybackSpeed = deathPlaybackSpeed;
		this.secondAttackSpeed = secondAttackSpeed;
		this.namePointer = namePointer;
		this.name = name;
		this.minDungeonLevel = minDungeonLevel;
		this.maxDungeonLevel = maxDungeonLevel;
		this.monsterItemLevel = monsterItemLevel;
		this.minHitPoints = minHitPoints;
		this.maxHitPoints = maxHitPoints;
		this.attackType1 = attackType1;
		this.attackType2 = attackType2;
		this.attackType3 = attackType3;
		this.attackType4 = attackType4;
		this.attackType5 = attackType5;
		this.monsterIntelligence = monsterIntelligence;
		this.attackType7 = attackType7;
		this.attackType8 = attackType8;
		this.subType = subType;
		this.priChanceToHit = priChanceToHit;
		this.priToHitFrame = priToHitFrame;
		this.priMinAttackDamage = priMinAttackDamage;
		this.priMaxAttackDamage = priMaxAttackDamage;
		this.secToHitChance = secToHitChance;
		this.secToHitFrame = secToHitFrame;
		this.secMinAttackDamage = secMinAttackDamage;
		this.secMaxAttackDamage = secMaxAttackDamage;
		this.monsterAc = monsterAc;
		this.monsterType = monsterType;
		this.resistancesNormAndNightmare = resistancesNormAndNightmare;
		this.resistancesHell = resistancesHell;
		this.itemDropSpecials = itemDropSpecials;
		this.monsterSelectionOutline = monsterSelectionOutline;
		this.experiencePoints = experiencePoints;
		this.enabled = enabled;
	}

	@JsonIgnore
	public MonsterAsBytes getMonsterAsBytes(){
		byte[] monsterBytes = new byte[DiabloFilePositions.BASE_MONSTER_LENGTH_IN_BYTES];
		BinEditTool beh = new BinEditTool();
		beh.setLongAsFourBytes(animationSize, monsterBytes, 0);
		beh.setLongAsFourBytes(seedingSize, monsterBytes, 4);
		beh.setPointerAsFourBytes(animationFilePointer, monsterBytes, 8);
		beh.setLongAsFourBytes(secondAttack, monsterBytes, 12);
		beh.setPointerAsFourBytes(soundPointer, monsterBytes, 16);
		beh.setLongAsFourBytes(hasSecondAttackSound, monsterBytes, 20);
		beh.setLongAsFourBytes(usesTrnToModColor, monsterBytes, 24);
		if(trnPointer != 0) {
			beh.setPointerAsFourBytes(trnPointer, monsterBytes, 28);
		}
		beh.setLongAsFourBytes(idleFrameSet, monsterBytes, 32);
		beh.setLongAsFourBytes(walkFrameSet, monsterBytes, 36);
		beh.setLongAsFourBytes(attackFrameSet, monsterBytes, 40);
		beh.setLongAsFourBytes(hitRecoveryFrameSet, monsterBytes, 44);
		beh.setLongAsFourBytes(deathFrameSet, monsterBytes, 48);
		beh.setLongAsFourBytes(secondAttackFrameSet, monsterBytes, 52);
		beh.setLongAsFourBytes(idlePlaybackSpeed, monsterBytes, 56);
		beh.setLongAsFourBytes(walkPlaybackSpeed, monsterBytes, 60);
		beh.setLongAsFourBytes(attackPlaybackSpeed, monsterBytes, 64);
		beh.setLongAsFourBytes(hitRecoverySpeed, monsterBytes, 68);
		beh.setLongAsFourBytes(deathPlaybackSpeed, monsterBytes, 72);
		beh.setLongAsFourBytes(secondAttackSpeed, monsterBytes, 76);
		beh.setPointerAsFourBytes(namePointer, monsterBytes, 80);
		monsterBytes[84] = (byte) minDungeonLevel;
		monsterBytes[85] = (byte) maxDungeonLevel;
		beh.setIntAsTwoBytes(monsterItemLevel, monsterBytes, 86);
		beh.setLongAsFourBytes(minHitPoints, monsterBytes, 88);
		beh.setLongAsFourBytes(maxHitPoints, monsterBytes, 92);
		monsterBytes[96] = (byte) attackType1;
		monsterBytes[97] = (byte) attackType2;
		monsterBytes[98] = (byte) attackType3;
		monsterBytes[99] = (byte) attackType4;
		monsterBytes[100] = (byte) attackType5;
  		monsterBytes[101] = (byte) monsterIntelligence;
		monsterBytes[102] = (byte) attackType7;
		monsterBytes[103] = (byte) attackType8;
		monsterBytes[104] = (byte) subType;
		monsterBytes[105] = (byte) getPriChanceToHit();
		monsterBytes[106] = (byte) priToHitFrame;
		monsterBytes[107] = (byte) priMinAttackDamage;
		monsterBytes[108] = (byte) priMaxAttackDamage;
		monsterBytes[109] = (byte) secToHitChance;
		monsterBytes[110] = (byte) secToHitFrame;
		monsterBytes[111] = (byte) secMinAttackDamage;
		monsterBytes[112] = (byte) secMaxAttackDamage;
		monsterBytes[113] = (byte) monsterAc;
		beh.setIntAsTwoBytes(monsterType, monsterBytes, 114);
		String res1 = resistancesNormAndNightmare.substring(8, 16);
		String res2 = resistancesNormAndNightmare.substring(0, 8); //FIXME -- next 8 lines can be improved using a new method
		monsterBytes[116] = (byte) Integer.parseInt(res2, 2);
		monsterBytes[117] = (byte) Integer.parseInt(res1, 2);
		String res3 = resistancesHell.substring(8, 16);
		String res4 = resistancesHell.substring(0, 8);
		monsterBytes[118] = (byte) Integer.parseInt(res4, 2);
		monsterBytes[119] = (byte) Integer.parseInt(res3, 2);
		beh.setIntAsTwoBytes(itemDropSpecials, monsterBytes, 120);
		beh.setIntAsTwoBytes(monsterSelectionOutline, monsterBytes, 122);
		beh.setLongAsFourBytes(experiencePoints, monsterBytes, 124);
		return new MonsterAsBytes(monsterBytes, (byte) enabled);
	}

	public void printMonster() {
		logger.info(
		"+--------------------------------------+" + "\n" +
		"| Slot number: " + slotNumber + " (hex: " + Integer.toHexString(slotNumber) + ")" + "\n" +
		"| Monster name: " + name + "\n" +
		"| Enabled: " + enabled + "\n" +
		"| Animation size: " + animationSize + "\n" +
		"| Seeding size: " + seedingSize + "\n" +
		"| Animation file pointer: " + animationFilePointer + "\n" +
		"| Animation file name: " + animationFileName + "\n" +
		"| Second attack: " + secondAttack + "\n" +
		"| Sound pointer: " + soundPointer + "\n" +
		"| Has second attack sound: " + hasSecondAttackSound + "\n" +
		"| Uses TRN to mod color: " + usesTrnToModColor + "\n" +
		"| TRN pointer: " + trnPointer + "\n" +
		"| Idle frameset: " + idleFrameSet + "\n" +
		"| Walk frameset: " + walkFrameSet + "\n" +
		"| Attack frameset: " + attackFrameSet + "\n" +
		"| Hit recovery frameset: " + hitRecoveryFrameSet + "\n" +
		"| Death frameset: " + deathFrameSet + "\n" +
		"| Second attack frameset: " + secondAttackFrameSet + "\n" +
		"| Idle playback speed: " + idlePlaybackSpeed + "\n" +
		"| Walk playback speed: " + walkPlaybackSpeed + "\n" +
		"| Attack playback speed: " + attackPlaybackSpeed + "\n" +
		"| Hit recovery speed: " + hitRecoverySpeed + "\n" +
		"| Death playback speed: " + deathPlaybackSpeed + "\n" +
		"| Second attack speed: " + secondAttackSpeed + "\n" +
		"| Name pointer: " + namePointer + "\n" +
		"| Min dungeon level: " + minDungeonLevel + "\n" +
		"| Max dungeon level: " + maxDungeonLevel + "\n" +
		"| Monster item level: " + monsterItemLevel + "\n" +
		"| HPs: " + minHitPoints + "--" + maxHitPoints + "\n" +
		"| Attack type (byte 1): " + attackType1 + "\n" +
		"| Attack type (byte 2): " + attackType2 + "\n" +
		"| Attack type (byte 3): " + attackType3 + "\n" +
		"| Attack type (byte 4): " + attackType4 + "\n" +
		"| Attack type (byte 5): " + attackType5 + "\n" +
		"| Monster intelligence: " + monsterIntelligence + "\n" +
		"| Attack type (byte 7): " + attackType7 + "\n" +
		"| Attack type (byte 8): " + attackType8 + "\n" +
		"| Monster sub-type: " + subType + "\n" +
		"| Primary attack % hit: " + getPriChanceToHit() + "\n" +
		"| Primary to-hit frame: " + priToHitFrame + "\n" +
		"| Primary damage: " + priMinAttackDamage + "--" + priMaxAttackDamage + "\n" +
		"| Sec. attack % hit: " + secToHitChance + "\n" +
		"| Sec. to-hit frame: " + secToHitFrame + "\n" +
		"| Secondary damage: " + secMinAttackDamage + "--" + secMaxAttackDamage + "\n" +
		"| Monster AC: " + monsterAc + "\n" +
		"| Monster type: " + monsterType + "\n" +
		"| Resistances (norm. and nightmare): " + resistancesNormAndNightmare + "\n" +
		"| Resistances (hell mode): " + resistancesHell + "\n" +
		"| Item drop specials: " + itemDropSpecials + "\n" +
		"| Monster selection outline: " + monsterSelectionOutline + "\n" +
		"| XP: " + experiencePoints + "\n" +
		"+--------------------------------------+" + "\n" );
	}

	public long getAnimationSize() {
		return animationSize;
	}

	public void setAnimationSize(long animationSize) {
		Integer[] animationSizes = {96, 128, 160};
		List<Integer> animationSizeList = Arrays.asList(animationSizes);
		if(animationSizeList.contains((int) animationSize)){
			this.animationSize = animationSize;

		} else {
			System.err.println("Error: BaseMonster's setAnimationSize() was"
					+ "supplied with an argument outside the supported range of options (96, 128, 160)");
		}
	}

	public long getSeedingSize() {
		return seedingSize;
	}

	public void setSeedingSize(long seedingSize) {
		ValueChecker.checkRange(0, seedingSize, 2500);
		this.seedingSize = seedingSize;
	}

	public long getAnimationFilePointer() {
		return animationFilePointer;
	}


	public void setAnimationFilePointer(long animationFilePointer) {
		ValueChecker.checkRange(1024, animationFilePointer, 7018496);
		this.animationFilePointer = animationFilePointer;
	}

	public String getAnimationFileName() {
		return animationFileName;
	}

	public long getSecondAttackOnOrOff() {
		return secondAttack;
	}

	public void setSecondAttackOnOrOff(long secondAttack) {
		ValueChecker.checkRange(0, secondAttack, 1);
		this.secondAttack = secondAttack;
	}

	public long getSoundPointer() {
		return soundPointer;
	}

	public void setSoundPointer(long soundPointer) {
		ValueChecker.checkRange(1024, soundPointer, 7018496);
		this.soundPointer = soundPointer;
	}

	public long getHasSecondAttackSound() {
		return hasSecondAttackSound;
	}

	public long getUsesTrnToModColor() {
		return usesTrnToModColor;
	}

	public void setUsesTrnToModColor(long usesTrnToModColor) {
		ValueChecker.checkRange(0, usesTrnToModColor, 1);
		this.usesTrnToModColor = usesTrnToModColor;
	}

	public long getTrnPointer() {
		return trnPointer;
	}

	public void setTrnPointer(long trnPointer) {
		if(trnPointer == 0 || trnPointer >= 1024 && trnPointer <= 7018496){
			this.trnPointer = trnPointer;
		} else {
			throw new IllegalStateException("BaseMonster's setUsesTrnPointer() was "
					+ "supplied with an argument outside the supported range (1024 to 7018496). Actual value: "
			        + trnPointer);
		}
	}

	public long getIdleFrameSet() {
		return idleFrameSet;
	}

	public void setIdleFrameSet(long idleFrameSet) {
		ValueChecker.checkRange(0, idleFrameSet, 24);
		this.idleFrameSet = idleFrameSet;
	}

	public long getWalkFrameSet() {
		return walkFrameSet;
	}

	public void setWalkFrameSet(long walkFrameSet) {
		ValueChecker.checkRange(1, walkFrameSet, 24);
		this.walkFrameSet = walkFrameSet;
	}

	public long getAttackFrameSet() {
		return attackFrameSet;
	}

	public void setAttackFrameSet(long attackFrameSet) {
		ValueChecker.checkRange(1, attackFrameSet, 24);
		this.attackFrameSet = attackFrameSet;
	}

	public long getHitRecoveryFrameSet() {
		return hitRecoveryFrameSet;
	}

	public void setHitRecoveryFrameSet(long hitRecoveryFrameSet) {
		ValueChecker.checkRange(0, hitRecoveryFrameSet, 24);
		this.hitRecoveryFrameSet = hitRecoveryFrameSet;
	}

	public long getDeathFrameSet() {
		return deathFrameSet;
	}

	public void setDeathFrameSet(long deathFrameSet) {
		ValueChecker.checkRange(1, deathFrameSet, 28);
		this.deathFrameSet = deathFrameSet;
	}

	public long getSecondAttackFrameSet() {
		return secondAttackFrameSet;
	}

	public void setSecondAttackFrameSet(long secondAttackFrameSet) {
		ValueChecker.checkRange(0, secondAttackFrameSet, 24);
		this.secondAttackFrameSet = secondAttackFrameSet;
	}

	public long getIdlePlaybackSpeed() {
		return idlePlaybackSpeed;
	}

	public void setIdlePlaybackSpeed(long idlePlaybackSpeed) {
		ValueChecker.checkRange(0, idlePlaybackSpeed, 10);
		this.idlePlaybackSpeed = idlePlaybackSpeed;
	}

	public long getWalkPlaybackSpeed() {
		return walkPlaybackSpeed;
	}

	public void setWalkPlaybackSpeed(long walkPlaybackSpeed) {
		ValueChecker.checkRange(0, walkPlaybackSpeed, 10);
		this.walkPlaybackSpeed = walkPlaybackSpeed;
	}

	public long getAttackPlaybackSpeed() {
		return attackPlaybackSpeed;
	}

	public void setAttackPlaybackSpeed(long attackPlaybackSpeed) {
		ValueChecker.checkRange(0, attackPlaybackSpeed, 10);
		this.attackPlaybackSpeed = attackPlaybackSpeed;
	}

	public long getHitRecoverySpeed() {
		return hitRecoverySpeed;
	}

	public void setHitRecoverySpeed(long hitRecoverySpeed) {
		ValueChecker.checkRange(0, hitRecoverySpeed, 10);
		this.hitRecoverySpeed = hitRecoverySpeed;
	}

	public long getDeathPlaybackSpeed() {
		return deathPlaybackSpeed;
	}

	public void setDeathPlaybackSpeed(long deathPlaybackSpeed) {
		ValueChecker.checkRange(0, deathPlaybackSpeed, 10);
		this.deathPlaybackSpeed = deathPlaybackSpeed;
	}

	public long getSecondAttackSpeed() {
		return secondAttackSpeed;
	}

	public void setSecondAttackSpeed(long secondAttackSpeed) {
		ValueChecker.checkRange(0, secondAttackSpeed, 10);
		this.secondAttackSpeed = secondAttackSpeed;
	}

	public long getNamePointer() {
		return namePointer;
	}

	public void setNamePointer(long namePointer) {
		ValueChecker.checkRange(1024, namePointer, 7018496);
		this.namePointer = namePointer;
	}

	//TODO
	public String getName() {
		return name;
	}

	public int getMinDungeonLevel() {
		return minDungeonLevel;
	}

	public void setMinDungeonLevel(int minDungeonLevel) {
		ValueChecker.checkRange(0, minDungeonLevel, 50);
		this.minDungeonLevel = minDungeonLevel;
	}

	public int getMaxDungeonLevel() {
		return maxDungeonLevel;
	}

	public void setMaxDungeonLevel(int maxDungeonLevel) {
		ValueChecker.checkRange(0, maxDungeonLevel, 50);
		this.maxDungeonLevel = maxDungeonLevel;
	}

	public int getMonsterItemLevel() {
		return monsterItemLevel;
	}

	public void setMonsterItemLevel(int monsterItemLevel) {
		ValueChecker.checkRange(1, monsterItemLevel, 30);
		this.monsterItemLevel = monsterItemLevel;
	}

	public long getMinHitPoints() {
		return minHitPoints;
	}

	public void setMinHitPoints(long minHitPoints) {
		ValueChecker.checkRange(1, minHitPoints, 9999);
		this.minHitPoints = minHitPoints;
	}

	public void setMaxHitPoints(long maxHitPoints) {
		ValueChecker.checkRange(0, maxHitPoints, 9999);
		this.maxHitPoints = maxHitPoints;
	}

	public int getAttackType1() {
		return attackType1;
	}

	//0 to 25
	public void setAttackType1(int attackType1) {
		ValueChecker.checkRange(0, attackType1, 31);
		this.attackType1 = attackType1;
	}

	public int getAttackType2() {
		return attackType2;
	}

	public void setAttackType2(int attackType2) {
		ValueChecker.checkRange(0, attackType2, 0);
		this.attackType2 = attackType2;
	}

	public int getAttackType3() {
		return attackType3;
	}

	public void setAttackType3(int attackType3) {
		ValueChecker.checkRange(0, attackType3, 0);
		this.attackType3 = attackType3;
	}

	public int getAttackType4() {
		return attackType4;
	}

	public void setAttackType4(int attackType4) {
		ValueChecker.checkRange(0, attackType4, 0);
		this.attackType4 = attackType4;
	}

	public int getAttackType5() {
		return attackType5;
	}

	public void setAttackType5(int attackType5) {
		ValueChecker.checkRange(0, attackType5, 128);
		this.attackType5 = attackType5;
	}

	public int getMonsterIntelligence() {
		return monsterIntelligence;
	}

	public void setMonsterIntelligence(int monsterIntelligence) {
		ValueChecker.checkRange(0, monsterIntelligence, 3);
		this.monsterIntelligence = monsterIntelligence;
	}

	public int getAttackType7() {
		return attackType7;
	}

	public void setAttackType7(int attackType7) {
		ValueChecker.checkRange(0, attackType7, 0);
		this.attackType7 = attackType7;
	}

	public int getAttackType8() {
		return attackType8;
	}

	public void setAttackType8(int attackType8) {
		ValueChecker.checkRange(0, attackType8, 0);
	}

	public int getSubType() {
		return subType;
	}

	public void setSubType(int subType) {
		ValueChecker.checkRange(0, subType, 3);
		this.subType = subType;
	}

	public void setPriChanceToHit(int priChanceToHit) {
		ValueChecker.checkRange(0, priChanceToHit, 300);
		this.priChanceToHit = priChanceToHit;
	}

	public int getPriToHitFrame() {
		return priToHitFrame;
	}

	public void setPriToHitFrame(int priToHitFrame) {
		ValueChecker.checkRange(0, priToHitFrame, 25);
		this.priToHitFrame = priToHitFrame;
	}

	public int getPriMinAttackDamage() {
		return priMinAttackDamage;
	}

	public void setPriMinAttackDamage(int priMinAttackDamage) {
		ValueChecker.checkRange(0, priMinAttackDamage, 255);
		this.priMinAttackDamage = priMinAttackDamage;
	}

	public int getPriMaxAttackDamage() {
		return priMaxAttackDamage;
	}

	public void setPriMaxAttackDamage(int priMaxAttackDamage) {
		ValueChecker.checkRange(0, priMaxAttackDamage, 255);
		this.priMaxAttackDamage = priMaxAttackDamage;
	}

	public int getSecToHitChance() {
		return secToHitChance;
	}

	public void setSecToHitChance(int secToHitChance) {
		ValueChecker.checkRange(0, secToHitChance, 100);
		this.secToHitChance = secToHitChance;
	}

	public int getSecToHitFrame() {
		return secToHitFrame;
	}

	public void setSecToHitFrame(int secToHitFrame) {
		ValueChecker.checkRange(0, secToHitFrame, 25);
		this.secToHitFrame = secToHitFrame;
	}

	public int getSecMinAttackDamage() {
		return secMinAttackDamage;
	}

	public void setSecMinAttackDamage(int secMinAttackDamage) {
		ValueChecker.checkRange(0, secMinAttackDamage, 255);
		this.secMinAttackDamage = secMinAttackDamage;
	}

	public int getSecMaxAttackDamage() {
		return secMaxAttackDamage;
	}

	public void setSecMaxAttackDamage(int secMaxAttackDamage) {
		ValueChecker.checkRange(0, secMaxAttackDamage, 255);
		this.secMaxAttackDamage = secMaxAttackDamage;
	}

	public int getMonsterAc() {
		return monsterAc;
	}

	public void setMonsterAc(int monsterAc) {
		ValueChecker.checkRange(0, monsterAc, 255);
		this.monsterAc = monsterAc;
	}

	public int getMonsterType() {
		return monsterType;
	}

	public void setMonsterType(int monsterType) {
		ValueChecker.checkRange(0, monsterType, 3);
		this.monsterType = monsterType;
	}

	public String getResistancesNormAndNightmare() {
		return resistancesNormAndNightmare;
	}

	public void setResistancesNormAndNightmare(
			String resistancesNormAndNightmare) {
		if(resistancesNormAndNightmare.matches("([01]){16}")){ //if resistancesNormAndNightmare matches 16 x (0 or 1) [2 bytes displayed in binary]
			this.resistancesNormAndNightmare = resistancesNormAndNightmare;
		} else {
			throw new IllegalStateException("BaseMonster's setResistancesNormAndNightmare() was "
					+ "supplied with an incorrect argument");
		}
	}

	public String getResistancesHell() {
		return resistancesHell;
	}

	public void setResistancesHell(String resistancesHell) {
		if(resistancesHell.matches("([01]){16}")){
			this.resistancesHell = resistancesHell;
		} else {
			throw new IllegalStateException("BaseMonster's setResistancesNormAndNightmare() was "
					+ "supplied with an incorrect argument");
		}
	}

	public int getItemDropSpecials() {
		return itemDropSpecials;
	}

	public void setItemDropSpecials(int itemDropSpecials) {
		ValueChecker.checkRange(0, itemDropSpecials, 65536);
		this.itemDropSpecials = itemDropSpecials;
	}

	public int getMonsterSelectionOutline() {
		return monsterSelectionOutline;
	}

	public void setMonsterSelectionOutline(int monsterSelectionOutline) {
		ValueChecker.checkRange(0, monsterSelectionOutline, 24);
		this.monsterSelectionOutline = monsterSelectionOutline;
	}

	public long getExperiencePoints() {
		return experiencePoints;
	}

	public void setExperiencePoints(long experiencePoints) {
		ValueChecker.checkRange(0, experiencePoints, 99999);
		this.experiencePoints = experiencePoints;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		ValueChecker.checkRange(0, enabled, 2);
		this.enabled = enabled;
	}

	public void updateWith(BaseMonster monsterToUpdateFrom) {
		setAnimationSize(monsterToUpdateFrom.animationSize);
		setSeedingSize(monsterToUpdateFrom.seedingSize);
		setAnimationFilePointer(monsterToUpdateFrom.animationFilePointer);
		setAnimationFileName(monsterToUpdateFrom.animationFileName);
		setSecondAttackOnOrOff(monsterToUpdateFrom.secondAttack);
		setSoundPointer(monsterToUpdateFrom.soundPointer);
		setHasSecondAttackSound(monsterToUpdateFrom.hasSecondAttackSound);
		setUsesTrnToModColor(monsterToUpdateFrom.usesTrnToModColor);
		setTrnPointer(monsterToUpdateFrom.trnPointer);
		setIdleFrameSet(monsterToUpdateFrom.idleFrameSet);
		setWalkFrameSet(monsterToUpdateFrom.walkFrameSet);
		setAttackFrameSet(monsterToUpdateFrom.attackFrameSet);
		setHitRecoveryFrameSet(monsterToUpdateFrom.hitRecoveryFrameSet);
		setDeathFrameSet(monsterToUpdateFrom.deathFrameSet);
		setSecondAttackFrameSet(monsterToUpdateFrom.secondAttackFrameSet);
		setIdlePlaybackSpeed(monsterToUpdateFrom.idlePlaybackSpeed);
		setWalkPlaybackSpeed(monsterToUpdateFrom.walkPlaybackSpeed);
		setAttackPlaybackSpeed(monsterToUpdateFrom.attackPlaybackSpeed);
		setHitRecoverySpeed(monsterToUpdateFrom.hitRecoverySpeed);
		setDeathPlaybackSpeed(monsterToUpdateFrom.deathPlaybackSpeed);
		setSecondAttackSpeed(monsterToUpdateFrom.secondAttackSpeed);
		setNamePointer(monsterToUpdateFrom.namePointer);
		setName(monsterToUpdateFrom.name);
		setMinDungeonLevel(monsterToUpdateFrom.minDungeonLevel);
		setMaxDungeonLevel(monsterToUpdateFrom.maxDungeonLevel);
		setMonsterItemLevel(monsterToUpdateFrom.monsterItemLevel);
		setMinHitPoints(monsterToUpdateFrom.minHitPoints);
		setMaxHitPoints(monsterToUpdateFrom.maxHitPoints);
		setAttackType1(monsterToUpdateFrom.attackType1);
		setAttackType2(monsterToUpdateFrom.attackType2);
		setAttackType3(monsterToUpdateFrom.attackType3);
		setAttackType4(monsterToUpdateFrom.attackType4);
		setAttackType5(monsterToUpdateFrom.attackType5);
		setMonsterIntelligence(monsterToUpdateFrom.monsterIntelligence);
		setAttackType7(monsterToUpdateFrom.attackType7);
		setAttackType8(monsterToUpdateFrom.attackType8);
		setSubType(monsterToUpdateFrom.subType);
		setPriChanceToHit(monsterToUpdateFrom.getPriChanceToHit());
		setPriToHitFrame(monsterToUpdateFrom.priToHitFrame);
		setPriMinAttackDamage(monsterToUpdateFrom.priMinAttackDamage);
		setPriMaxAttackDamage(monsterToUpdateFrom.priMaxAttackDamage);
		setSecToHitChance(monsterToUpdateFrom.secToHitChance);
		setSecToHitFrame(monsterToUpdateFrom.secToHitFrame);
		setSecMinAttackDamage(monsterToUpdateFrom.secMinAttackDamage);
		setSecMaxAttackDamage(monsterToUpdateFrom.secMaxAttackDamage);
		setMonsterAc(monsterToUpdateFrom.monsterAc);
		setMonsterType(monsterToUpdateFrom.monsterType);
		setResistancesNormAndNightmare(monsterToUpdateFrom.resistancesNormAndNightmare);
		setResistancesHell(monsterToUpdateFrom.resistancesHell);
		setItemDropSpecials(monsterToUpdateFrom.itemDropSpecials);
		setMonsterSelectionOutline(monsterToUpdateFrom.monsterSelectionOutline);
		setExperiencePoints(monsterToUpdateFrom.experiencePoints);
		setEnabled(monsterToUpdateFrom.enabled);
		setSlotNumber(monsterToUpdateFrom.slotNumber);
	}

	private void setSlotNumber(int slotNumber) {
		//TODO -- checkRange()
		this.slotNumber = slotNumber;
	}

	private void setName(String name) {
		//TODO -- validation
		this.name = name;
	}

	private void setHasSecondAttackSound(long hasSecondAttackSound) {
		//TODO -- checkRange()
		this.hasSecondAttackSound = hasSecondAttackSound;
	}

	private void setAnimationFileName(String animationFileName) {
		//TODO -- validation
		this.animationFileName = animationFileName;
	}

	public int getPriChanceToHit() {
		return priChanceToHit;
	}
}
