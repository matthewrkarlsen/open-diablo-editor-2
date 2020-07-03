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

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.d1ablo.ode.bintool.BinEditTool;
import org.d1ablo.ode.knowledge.SpellEffects;
import org.d1ablo.ode.knowledge.SpellNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Holds all the data relating to a single spell within the game.
 */
public class Spell {
	
	private final static Logger logger = LoggerFactory.getLogger(Spell.class);

	private final String[] castingSounds = {"Fire", "Lighting", "Utility", "Other"};
	private final String[] animationsWhenCasting = {"fire", "lightning", "magic/other"};

	@JsonProperty("unmoddedSpellIndex")
	private int unmoddedSpellIndex;

	@JsonProperty("manaToCast")
	private int manaToCast;

	@JsonProperty("animationWhenCasting")
	private int animationWhenCasting;

	@JsonProperty("pointerToNameAsSpell")
	private long pointerToNameAsSpell;

	@JsonProperty("nameAsSpell")
	private String nameAsSpell;

	@JsonProperty("pointerToNameAsSkill")
	private long pointerToNameAsSkill;

	@JsonProperty("nameAsSkill")
	private String nameAsSkill;

	@JsonProperty("spellBookQuality")
	private long spellBookQuality;

	@JsonProperty("staffQuality")
	private long staffQuality;

	@JsonProperty("byteTwenty")
	private int byteTwenty;

	@JsonProperty("byteTwentyOne")
	private int byteTwentyOne;

	@JsonProperty("byteTwentyTwo")
	private int byteTwentyTwo;

	@JsonProperty("byteTwentyThree")
	private int byteTwentyThree;

	@JsonProperty("spellActiveInTown")
	private long spellActiveInTown;

	@JsonProperty("baseRequiredMagic")
	private long baseRequiredMagic;

	@JsonProperty("castingSound")
	private int castingSound;

	@JsonProperty("spellEffects")
	private byte[] spellEffects;

	@JsonProperty("manaStep")
	private int manaStep;

	@JsonProperty("minCastingCost")
	private int minCastingCost;

	@JsonProperty("byteThirtyEight")
	private int byteThirtyEight;

	@JsonProperty("byteThirtyNine")
	private int byteThirtyNine;

	@JsonProperty("minCharges")
	private long minCharges;

	@JsonProperty("maxCharges")
	private long maxCharges;

	@JsonProperty("bookCost")
	private long bookCost;

	@JsonProperty("staffCostMultiplier")
	private long staffCostMultiplier;

	@JsonProperty("index")
	int indexInGame;

	private BinEditTool beh;

	@JsonCreator
	public Spell(@JacksonInject BinEditTool beh) {
		this.spellEffects = new byte[3];
		this.beh = beh;
	}

	public Spell(int unmoddedSpellIndex, int manaToCast, int indexInGame, int animationWhenCasting,
				 long pointerToNameAsSpell, long pointerToNameAsSkill, String nameAsSpell, String nameAsSkill,
				 long spellBookQuality, long staffQuality, int byteTwenty,int byteTwentyOne,
				 int byteTwentyTwo, int byteTwentyThree, long spellActiveInTown, long baseRequiredMagic,
				 int castingSound, byte[] spellEffects, int manaStep, int minCastingCost, int byteThirtyEight,
				 int byteThirtyNine, long minCharges, long maxCharges, long bookCost, long staffCostMultiplier,
				 BinEditTool beh) {
		this.beh = beh;
		this.indexInGame = indexInGame;
		this.unmoddedSpellIndex = unmoddedSpellIndex;
		this.manaToCast = manaToCast;
		this.animationWhenCasting = animationWhenCasting;
		this.pointerToNameAsSpell = pointerToNameAsSpell;
		this.nameAsSpell = nameAsSpell;
		this.pointerToNameAsSkill = pointerToNameAsSkill;
		this.nameAsSkill = nameAsSkill;
		this.spellBookQuality = spellBookQuality;
		this.staffQuality = staffQuality;
		this.byteTwenty = byteTwenty;
		this.byteTwentyOne = byteTwentyOne;
		this.byteTwentyTwo = byteTwentyTwo;
		this.byteTwentyThree = byteTwentyThree;
		this.spellActiveInTown = spellActiveInTown;
		this.baseRequiredMagic = baseRequiredMagic;
		this.castingSound = castingSound;
		this.spellEffects = spellEffects;
		this.manaStep = manaStep;
		this.minCastingCost = minCastingCost;
		this.byteThirtyEight = byteThirtyEight;
		this.byteThirtyNine = byteThirtyNine;
		this.minCharges = minCharges;
		this.maxCharges = maxCharges;
		this.bookCost = bookCost;
		this.staffCostMultiplier = staffCostMultiplier;
	}

	private Spell() {

	}

    //Important note: Gap bytes are 32, whereas end-of-strings are 0
	public void printSpell() {
		logger.info("Spell #: " + indexInGame);
		String[] unmoddedSpellNames = SpellNames.SPELL_NAMES;
		String[] spellEffects = SpellEffects.POSSIBLE_SPELL_EFFECTS;
		logger.info("Unmodded Name: " + unmoddedSpellNames[unmoddedSpellIndex]);
		logger.info("Mana to cast + mana from reading: " + manaToCast);
		logger.info("Animation when casting: " + animationsWhenCasting[animationWhenCasting]);
		logger.info("Pointer to name as spell: " + pointerToNameAsSpell);
		logger.info("Name as spell: " + nameAsSpell);
		if(pointerToNameAsSkill != -1){
			logger.info("Pointer to name as skill: " + pointerToNameAsSkill);
		} else {
			logger.info("Pointer to name as skill: not set");
		}
		logger.info("Name as skill: " + nameAsSkill);
		logger.info("Quality level of spellbook: " + spellBookQuality);
		logger.info("Quality level of staff: " + staffQuality);
		logger.info("Byte twenty: " + byteTwenty);
		logger.info("Byte twentyone: " + byteTwentyOne);
		logger.info("Byte twentytwo: " + byteTwentyTwo);
		logger.info("Byte twentythree: " + byteTwentyThree);
		logger.info("Spell active in town: " + spellActiveInTown);
		logger.info("Base required magic: " + baseRequiredMagic);
		int castingSoundIndex = -1;
		if(castingSound == 76){
			castingSoundIndex = 0;
		} else if (castingSound == 78) {
			castingSoundIndex = 1;
		} else if (castingSound == 80) {
			castingSoundIndex = 2;
		} else if (castingSound == 82) {
			castingSoundIndex = 3;
		}
		logger.info("Casting sound: " + castingSounds[castingSoundIndex]);
		logger.info("Spell Effects: " + spellEffects[this.spellEffects[0]] + "; " + spellEffects[this.spellEffects[1]] + "; " + spellEffects[this.spellEffects[2]]);
		logger.info("Mana step: " + manaStep);
		logger.info("Min casting cost: " + minCastingCost);
		logger.info("Zero: " + byteThirtyEight);
		logger.info("Zero: " + byteThirtyNine);
		logger.info("Min charges: " + minCharges);
		logger.info("Max charges: " + maxCharges);
		logger.info("Book cost: " + bookCost);
		logger.info("Staff cost multiplier: " + staffCostMultiplier);
		logger.info("");
	}

	@JsonIgnore
	public byte[] toBytes() {
		byte[] spellAsBytes = new byte[56];
		spellAsBytes[0] = (byte) unmoddedSpellIndex;
		spellAsBytes[1] = (byte) manaToCast;
		spellAsBytes[2] = (byte) (animationWhenCasting);
		spellAsBytes[3] = (byte) (animationWhenCasting >>> 8);
		if(pointerToNameAsSpell != 0) {
			beh.setPointerAsFourBytes(pointerToNameAsSpell, spellAsBytes, 4);
		}
		if(pointerToNameAsSkill != 0) {
			beh.setPointerAsFourBytes(pointerToNameAsSkill, spellAsBytes, 8);
		}
		beh.setLongAsFourBytes(spellBookQuality, spellAsBytes, 12);
		beh.setLongAsFourBytes(staffQuality, spellAsBytes, 16);
		spellAsBytes[20] = (byte) byteTwenty;
		spellAsBytes[21] = (byte) byteTwentyOne;
		spellAsBytes[22] = (byte) byteTwentyTwo;
		spellAsBytes[23] = (byte) byteTwentyThree;
		beh.setLongAsFourBytes(spellActiveInTown, spellAsBytes, 24);
		beh.setLongAsFourBytes(baseRequiredMagic, spellAsBytes, 28);
		spellAsBytes[32] = (byte) castingSound;
		spellAsBytes[33] = spellEffects[0];
		spellAsBytes[34] = spellEffects[1];
		spellAsBytes[35] = spellEffects[2];
		spellAsBytes[36] = (byte) manaStep;
		spellAsBytes[37] = (byte) minCastingCost;
		spellAsBytes[38] = (byte) byteThirtyEight;
		spellAsBytes[39] = (byte) byteThirtyNine;
		beh.setLongAsFourBytes(minCharges, spellAsBytes, 40);
		beh.setLongAsFourBytes(maxCharges, spellAsBytes, 44);
		beh.setLongAsFourBytes(bookCost, spellAsBytes, 48);
		beh.setLongAsFourBytes(staffCostMultiplier, spellAsBytes, 52);
		return spellAsBytes;
	}

	public int getUnmoddedSpellIndex() {
		return unmoddedSpellIndex;
	}

	public int getManaToCast() {
		return manaToCast;
	}

	public void setManaToCast(int manaToCast) {
		//TODO -- validation
		this.manaToCast = manaToCast;
	}

	public int getAnimationWhenCasting() {
		return animationWhenCasting;
	}

	public void setAnimationWhenCasting(int animationWhenCasting) {
		//TODO -- validation
		this.animationWhenCasting = animationWhenCasting;
	}

	public long getPointerToNameAsSpell() {
		return pointerToNameAsSpell;
	}

	public void setPointerToNameAsSpell(long pointerToNameAsSpell) {
		//TODO -- validation
		this.pointerToNameAsSpell = pointerToNameAsSpell;
	}

	public String getNameAsSpell() {
		return nameAsSpell;
	}

	public void setNameAsSpell(String nameAsSpell) {
		//TODO -- validation
		this.nameAsSpell = nameAsSpell;
	}

	public long getPointerToNameAsSkill() {
		return pointerToNameAsSkill;
	}

	public void setPointerToNameAsSkill(long pointerToNameAsSkill) {
		//TODO -- validation
		this.pointerToNameAsSkill = pointerToNameAsSkill;
	}

	public String getNameAsSkill() {
		return nameAsSkill;
	}

	public void setNameAsSkill(String nameAsSkill) {
		//TODO -- validation
		this.nameAsSkill = nameAsSkill;
	}

	public long getSpellBookQuality() {
		return spellBookQuality;
	}

	public void setSpellBookQuality(long spellBookQuality) {
		//TODO -- validation
		this.spellBookQuality = spellBookQuality;
	}

	public long getStaffQuality() {
		return staffQuality;
	}

	public void setStaffQuality(long staffQuality) {
		//TODO -- validation
		this.staffQuality = staffQuality;
	}

	public int getByteTwenty() {
		return byteTwenty;
	}

	public void setByteTwenty(int byteTwenty) {
		//TODO -- validation
		this.byteTwenty = byteTwenty;
	}

	public int getByteTwentyOne() {
		return byteTwentyOne;
	}

	public void setByteTwentyOne(int byteTwentyOne) {
		//TODO -- validation
		this.byteTwentyOne = byteTwentyOne;
	}

	public int getByteTwentyTwo() {
		return byteTwentyTwo;
	}

	public void setByteTwentyTwo(int byteTwentyTwo) {
		//TODO -- validation
		this.byteTwentyTwo = byteTwentyTwo;
	}

	public int getByteTwentyThree() {
		return byteTwentyThree;
	}

	public void setByteTwentyThree(int byteTwentyThree) {
		//TODO -- validation
		this.byteTwentyThree = byteTwentyThree;
	}

	public long getSpellActiveInTown() {
		return spellActiveInTown;
	}

	public void setSpellActiveInTown(long spellActiveInTown) {
		//TODO -- validation
		this.spellActiveInTown = spellActiveInTown;
	}

	public long getBaseRequiredMagic() {
		return baseRequiredMagic;
	}

	public void setBaseRequiredMagic(long baseRequiredMagic) {
		//TODO -- validation
		this.baseRequiredMagic = baseRequiredMagic;
	}

	public int getCastingSound() {
		return castingSound;
	}

	public void setCastingSound(int castingSound) {
		//TODO -- validation
		this.castingSound = castingSound;
	}
	
	public byte getSpellEffect1() {
		return spellEffects[0];
	}
	
	public byte getSpellEffect2() {
		return spellEffects[1];
	}
	
	public byte getSpellEffect3() {
		return spellEffects[2];
	}

	public void setSpellEffect1(byte b) {
		//TODO -- validation
		spellEffects[0] = b;
	}

	public void setSpellEffect2(byte b) {
		//TODO -- validation
		spellEffects[1] = b;
	}

	public void setSpellEffect3(byte b) {
		//TODO -- validation
		spellEffects[2] = b;
	}

	public int getManaStep() {
		return manaStep;
	}

	public void setManaStep(int manaStep) {
		//TODO -- validation
		this.manaStep = manaStep;
	}

	public int getMinCastingCost() {
		return minCastingCost;
	}

	public void setMinCastingCost(int minCastingCost) {
		//TODO -- validation
		this.minCastingCost = minCastingCost;
	}

	public int getByteThirtyEight() {
		return byteThirtyEight;
	}

	public void setByteThirtyEight(int byteThirtyEight) {
		//TODO -- validation
		this.byteThirtyEight = byteThirtyEight;
	}

	public int getByteThirtyNine() {
		return byteThirtyNine;
	}

	public void setByteThirtyNine(int byteThirtyNine) {
		//TODO -- validation
		this.byteThirtyNine = byteThirtyNine;
	}

	public long getMinCharges() {
		return minCharges;
	}

	public void setMinCharges(long minCharges) {
		//TODO -- validation
		this.minCharges = minCharges;
	}

	public long getMaxCharges() {
		return maxCharges;
	}

	public void setMaxCharges(long maxCharges) {
		//TODO -- validation
		this.maxCharges = maxCharges;
	}

	public long getBookCost() {
		return bookCost;
	}

	public void setBookCost(long bookCost) {
		//TODO -- validation
		this.bookCost = bookCost;
	}

	public long getStaffCostMultiplier() {
		return staffCostMultiplier;
	}

	public void setStaffCostMultiplier(long staffCostMultiplier) {
		//TODO -- validation
		this.staffCostMultiplier = staffCostMultiplier;
	}

	public void updateFrom(Spell s2) {
		setManaToCast(s2.manaToCast);
		setAnimationWhenCasting(s2.animationWhenCasting);
		setPointerToNameAsSpell(s2.pointerToNameAsSpell);
		setNameAsSpell(s2.nameAsSpell);
		setPointerToNameAsSkill(s2.pointerToNameAsSkill);
		setNameAsSkill(s2.nameAsSkill);
		setSpellBookQuality(s2.spellBookQuality);
		setStaffQuality(s2.staffQuality);
		setByteTwenty(s2.byteTwenty);
		setByteTwentyOne(s2.byteTwentyOne);
		setByteTwentyTwo(s2.byteTwentyTwo);
		setByteTwentyThree(s2.byteTwentyThree);
		setSpellActiveInTown(s2.spellActiveInTown);
		setBaseRequiredMagic(s2.baseRequiredMagic);
		setCastingSound(s2.castingSound);
		for(int i = 0; i < spellEffects.length; i++) {
			setSpellEffect(i, s2.spellEffects[i]);
		}
		setManaStep(manaStep);
		setMinCastingCost(s2.minCastingCost);
		setByteThirtyEight(s2.byteThirtyEight);
		setByteThirtyNine(s2.byteThirtyNine);
		setMinCharges(s2.minCharges);
		setMaxCharges(s2.maxCharges);
		setBookCost(s2.bookCost);
		setStaffCostMultiplier(s2.staffCostMultiplier);
	}

	private void setSpellEffect(int i, byte spellEffect) {
		//TODO -- validation
		spellEffects[i] = spellEffect;
	}
}
