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
import org.d1ablo.ode.factories.CharacterFactory;
import org.d1ablo.ode.knowledge.DiabloFilePositions;
import org.d1ablo.ode.knowledge.SkillBytes;
import org.d1ablo.ode.knowledge.SpellNames;
import org.d1ablo.ode.types.Character;

import java.util.List;

public class CharacterStore {

	@JsonIgnore
	private Character characterSelected;

	private Character char0;
	private Character char1;
	private Character char2;

	@JsonProperty(value = "characterList")
	private final List<Character> characters;
	
	private final BinEditTool beh;
	private final CharacterFactory characterFactory;

	private ReaderWriter rw;

	@JsonCreator
	public CharacterStore(@JsonProperty("characterList") List<Character> characters, @JacksonInject BinEditTool beh,
						  @JacksonInject CharacterFactory characterFactory) {
		this.characters = characters;
		this.beh = beh;
		this.characterFactory = characterFactory;
	}

	public CharacterStore(List<Character> characters,
						  BinEditTool beh,
						  CharacterFactory characterFactory,
						  Character characterSelected) {
		this.characters = characters;
		this.beh = beh;
		this.characterFactory = characterFactory;
		this.characterSelected = characterSelected;
	}

	//TODO -- make this code more concise
	public void readInCharacters() {

		long pos = DiabloFilePositions.INIT_STATS_OFFSET;
		byte[] startingStats = new byte[DiabloFilePositions.INIT_STATS_LENGTH_IN_BYTES];
		for(int j = 0; j < DiabloFilePositions.INIT_STATS_LENGTH_IN_BYTES; j++){
			startingStats[j] = rw.readByte(pos); //TODO -- use readBytes()
			pos++;
		}

		pos = DiabloFilePositions.MAX_STATS_OFFSET;
		byte[] maxStats = new byte[DiabloFilePositions.MAX_STATS_LENGTH_IN_BYTES];
		for(int j = 0; j < DiabloFilePositions.MAX_STATS_LENGTH_IN_BYTES; j++){
			maxStats[j] = rw.readByte(pos); //TODO -- use readBytes()
			pos++;
		}

		pos = DiabloFilePositions.BLOCKING_BONUSES_OFFSET;
		byte[] blockingBonuses = new byte[DiabloFilePositions.BLOCKING_BONUSES_LENGTH_IN_BYTES];
		for(int j = 0; j < DiabloFilePositions.BLOCKING_BONUSES_LENGTH_IN_BYTES; j++){
			blockingBonuses[j] = rw.readByte(pos); //TODO -- use readBytes()
			pos++;
		}

		pos = DiabloFilePositions.BONUSES_AND_FRAMESETS_OFFSET;
		byte[] bonusesAndFramesets = new byte[DiabloFilePositions.BONUSES_AND_FRAMESETS_LENGTH_IN_BYTES];
		for(int j = 0; j < DiabloFilePositions.BONUSES_AND_FRAMESETS_LENGTH_IN_BYTES; j++){
			bonusesAndFramesets[j] = rw.readByte(pos); //TODO -- use readBytes()
			pos++;
		}

		char0 = characterFactory.constructCharacter(0, "Warrior", startingStats, maxStats,
				blockingBonuses, bonusesAndFramesets); //TODO -- Don't hard-code name

		char1 = characterFactory.constructCharacter(1, "Rogue", startingStats, maxStats,
				blockingBonuses, bonusesAndFramesets); //TODO -- Don't hard-code name

		char2 = characterFactory.constructCharacter(2, "Sorceror", startingStats, maxStats,
				blockingBonuses, bonusesAndFramesets); //TODO -- Don't hard-code name

		characters.add(char0);
		characters.add(char1);
		characters.add(char2);
	}

	@JsonIgnore
	public byte[] getInitStatBytes(){
		//TODO -- don't use variable names with char names in them
		//TODO -- reduce code duplication
		byte[] returnedStartingStats = new byte[DiabloFilePositions.INIT_STATS_LENGTH_IN_BYTES];
		long warriorInitStrength = char0.getInitStrength();
		beh.setLongAsFourBytes(warriorInitStrength, returnedStartingStats, 0);
		long rogueInitStrength = char1.getInitStrength();
		beh.setLongAsFourBytes(rogueInitStrength, returnedStartingStats, 4);
		long sorcInitStrength = char2.getInitStrength();
		beh.setLongAsFourBytes(sorcInitStrength, returnedStartingStats, 8);
		long warriorInitMagic = char0.getInitMagic();
		beh.setLongAsFourBytes(warriorInitMagic, returnedStartingStats, 12);
		long rogueInitMagic = char1.getInitMagic();
		beh.setLongAsFourBytes(rogueInitMagic, returnedStartingStats, 16);
		long sorcInitMagic = char2.getInitMagic();
		beh.setLongAsFourBytes(sorcInitMagic, returnedStartingStats, 20);
		long warriorInitDex = char0.getInitDexterity();
		beh.setLongAsFourBytes(warriorInitDex, returnedStartingStats, 24);
		long rogueInitDex = char1.getInitDexterity();
		beh.setLongAsFourBytes(rogueInitDex, returnedStartingStats, 28);
		long sorcInitDex = char2.getInitDexterity();
		beh.setLongAsFourBytes(sorcInitDex, returnedStartingStats, 32);
		long warriorInitVit = char0.getInitVitality();
		beh.setLongAsFourBytes(warriorInitVit, returnedStartingStats, 36);
		long rogueInitVit = char1.getInitVitality();
		beh.setLongAsFourBytes(rogueInitVit, returnedStartingStats, 40);
		long sorcInitVit = char2.getInitVitality();
		beh.setLongAsFourBytes(sorcInitVit, returnedStartingStats, 44);
		return returnedStartingStats;
	}

	//TODO -- make this code more concise
	@JsonIgnore
	public byte[] getMaxStatBytes(){
		//TODO -- don't use variable names with char names in them
		//TODO -- reduce code duplication
		byte[] maxStats = new byte[DiabloFilePositions.MAX_STATS_LENGTH_IN_BYTES];
		long warriorMaxStrength = char0.getMaxStrength();
		beh.setLongAsFourBytes(warriorMaxStrength, maxStats, 0);
		long warriorMaxMagic = char0.getMaxMagic();
		beh.setLongAsFourBytes(warriorMaxMagic, maxStats, 4);
		long warriorMaxDex = char0.getMaxDexterity();
		beh.setLongAsFourBytes(warriorMaxDex, maxStats, 8);
		long warriorMaxVit = char0.getMaxVitality();
		beh.setLongAsFourBytes(warriorMaxVit, maxStats, 12);
		long rogueMaxStrength = char1.getMaxStrength();
		beh.setLongAsFourBytes(rogueMaxStrength, maxStats, 16);
		long rogueMaxMagic = char1.getMaxMagic();
		beh.setLongAsFourBytes(rogueMaxMagic, maxStats, 20);
		long rogueMaxDex = char1.getMaxDexterity();
		beh.setLongAsFourBytes(rogueMaxDex, maxStats, 24);
		long rogueMaxVit = char1.getMaxVitality();
		beh.setLongAsFourBytes(rogueMaxVit, maxStats, 28);
		long sorcMaxStrength = char2.getMaxStrength();
		beh.setLongAsFourBytes(sorcMaxStrength, maxStats, 32);
		long sorcMaxMagic = char2.getMaxMagic();
		beh.setLongAsFourBytes(sorcMaxMagic, maxStats, 36);
		long sorcMaxDex = char2.getMaxDexterity();
		beh.setLongAsFourBytes(sorcMaxDex, maxStats, 40);
		long sorcMaxVit = char2.getMaxVitality();
		beh.setLongAsFourBytes(sorcMaxVit, maxStats, 44);
		return maxStats;
	}

	//TODO -- make this code more concise
	@JsonIgnore
	public byte[] getBlockingBonusBytes(){
		//TODO -- don't use variable names with char names in them
		//TODO -- reduce code duplication
		byte[] blockingBonuses = new byte[DiabloFilePositions.BLOCKING_BONUSES_LENGTH_IN_BYTES];
		long warriorBlockingBonus = char0.getBlockingBonus();
		beh.setLongAsFourBytes(warriorBlockingBonus, blockingBonuses, 0);
		long rogueBlockingBonus = char1.getBlockingBonus();
		beh.setLongAsFourBytes(rogueBlockingBonus, blockingBonuses, 4);
		long sorcBlockingBonus = char2.getBlockingBonus();
		beh.setLongAsFourBytes(sorcBlockingBonus, blockingBonuses, 8);
		return blockingBonuses;
	}

	//TODO -- make this code more concise
	@JsonIgnore
	public byte[] getBonusesAndFramesetBytes(){
		//TODO -- reduce code duplication
		byte[] bonusesAndFramesets = new byte[DiabloFilePositions.BONUSES_AND_FRAMESETS_LENGTH_IN_BYTES];
		bonusesAndFramesets[0] = (byte) char0.getDungeonIdleFrameset();
		bonusesAndFramesets[1] = (byte) char0.getAttackingFrameset();
		bonusesAndFramesets[2] = (byte) char0.getDungeonWalkFrameset();
		bonusesAndFramesets[3] = (byte) char0.getBlockSpeed();
		bonusesAndFramesets[4] = (byte) char0.getDeathFrameset();
		bonusesAndFramesets[5] = (byte) char0.getCastingFrameset();
		bonusesAndFramesets[6] = (byte) char0.getHitRecoverySpeed();
		bonusesAndFramesets[7] = (byte) char0.getTownIdleFrameset();
		bonusesAndFramesets[8] = (byte) char0.getTownWalkFrameset();
		bonusesAndFramesets[9] = (byte) char0.getOneHandedAttackSpeed();
		bonusesAndFramesets[10] = (byte) char0.getCastingSpeed();
		bonusesAndFramesets[11] = (byte) char1.getDungeonIdleFrameset();
		bonusesAndFramesets[12] = (byte) char1.getAttackingFrameset();
		bonusesAndFramesets[13] = (byte) char1.getDungeonWalkFrameset();
		bonusesAndFramesets[14] = (byte) char1.getBlockSpeed();
		bonusesAndFramesets[15] = (byte) char1.getDeathFrameset();
		bonusesAndFramesets[16] = (byte) char1.getCastingFrameset();
		bonusesAndFramesets[17] = (byte) char1.getHitRecoverySpeed();
		bonusesAndFramesets[18] = (byte) char1.getTownIdleFrameset();
		bonusesAndFramesets[19] = (byte) char1.getTownWalkFrameset();
		bonusesAndFramesets[20] = (byte) char1.getOneHandedAttackSpeed();
		bonusesAndFramesets[21] = (byte) char1.getCastingSpeed();
		bonusesAndFramesets[22] = (byte) char2.getDungeonIdleFrameset();
		bonusesAndFramesets[23] = (byte) char2.getAttackingFrameset();
		bonusesAndFramesets[24] = (byte) char2.getDungeonWalkFrameset();
		bonusesAndFramesets[25] = (byte) char2.getBlockSpeed();
		bonusesAndFramesets[26] = (byte) char2.getDeathFrameset();
		bonusesAndFramesets[27] = (byte) char2.getCastingFrameset();
		bonusesAndFramesets[28] = (byte) char2.getHitRecoverySpeed();
		bonusesAndFramesets[29] = (byte) char2.getTownIdleFrameset();
		bonusesAndFramesets[30] = (byte) char2.getTownWalkFrameset();
		bonusesAndFramesets[31] = (byte) char2.getOneHandedAttackSpeed();
		bonusesAndFramesets[32] = (byte) char2.getCastingSpeed();
		return bonusesAndFramesets;
	}

	public void printCharacters() {
		for(Character c : characters){
			c.printCharacter();
		}
	}

	public void writeCharactersToEXE() {

		byte[] retrievedInitStatBytes = this.getInitStatBytes();
		rw.writeBytes(retrievedInitStatBytes, DiabloFilePositions.INIT_STATS_OFFSET);

		byte[] retrievedMaxStatBytes = this.getMaxStatBytes();
		rw.writeBytes(retrievedMaxStatBytes, DiabloFilePositions.MAX_STATS_OFFSET);

		byte[] retrievedBlockingBonusBytes = this.getBlockingBonusBytes();
		rw.writeBytes(retrievedBlockingBonusBytes, DiabloFilePositions.BLOCKING_BONUSES_OFFSET);

		byte[] retrievedBonusesAndFrameSetBytes = this.getBonusesAndFramesetBytes();
		rw.writeBytes(retrievedBonusesAndFrameSetBytes, DiabloFilePositions.BONUSES_AND_FRAMESETS_OFFSET);
	}

	public Character getCharacter(int i) {
		return characters.get(i);
	}

	public void setAllMaxStatsTo255() {
		for(Character c : characters){
			c.setMaxDexterity(255);
			c.setMaxMagic(255);
			c.setMaxStrength(255);
			c.setMaxVitality(255);
		}
	}

	//TODO -- The 3 methods below are "tacked on". Integrate them better.
	//FIXME -- broken due to change in the approach of reading/writing files
	public void setCharZeroStartingSkillBySpellID(int i) {
		long l = i;
		byte[] spellID = new byte[4];
		spellID[0] = (byte)(l);
		spellID[1] = (byte)(l >>>  8);
		spellID[2] = (byte)(l >>> 16);
		spellID[3] = (byte)(l >>> 24);
		String[] spellNames = SpellNames.SPELL_NAMES;
		String spellName = spellNames[i];
		byte[][] skillBytes = SkillBytes.getSkillBytesArray();
		byte[] bytesToUse = skillBytes[i];
//		rw.writeBytes(bytesToUse, TomeOfKnowledge.CHARACTER_ZERO_SKILL_LOC_1);
//		rw.writeBytes(bytesToUse, TomeOfKnowledge.CHARACTER_ZERO_SKILL_LOC_2);
//		rw.writeBytes(spellID, TomeOfKnowledge.CHARACTER_ZERO_SPELL_LOC_1);
	}

	//FIXME -- broken due to change in the approach of reading/writing files
	public void setCharOneStartingSkillBySpellID(int i) {
		long l = i;
		byte[] spellID = new byte[4];
		spellID[0] = (byte)(l);
		spellID[1] = (byte)(l >>>  8);
		spellID[2] = (byte)(l >>> 16);
		spellID[3] = (byte)(l >>> 24);
		String[] spellNames = SpellNames.SPELL_NAMES;
		String spellName = spellNames[i];
		byte[][] skillBytes = SkillBytes.getSkillBytesArray();
		byte[] bytesToUse = skillBytes[i];
//		rw.writeBytes(bytesToUse, TomeOfKnowledge.CHARACTER_ONE_SKILL_LOC_1);
//		rw.writeBytes(bytesToUse, TomeOfKnowledge.CHARACTER_ONE_SKILL_LOC_2);
//		rw.writeBytes(spellID, TomeOfKnowledge.CHARACTER_ONE_SPELL_LOC_1);

	}

	//FIXME -- broken due to change in the approach of reading/writing files
	public void setCharTwoStartingSkillBySpellID(int i) {
		long l = i;
		byte[] spellID = new byte[4];
		spellID[0] = (byte)(l);
		spellID[1] = (byte)(l >>>  8);
		spellID[2] = (byte)(l >>> 16);
		spellID[3] = (byte)(l >>> 24);
		String[] spellNames = SpellNames.SPELL_NAMES;
		String spellName = spellNames[i];
		byte[][] skillBytes = SkillBytes.getSkillBytesArray();
		byte[] bytesToUse = skillBytes[i];
//		rw.writeBytes(bytesToUse, TomeOfKnowledge.CHARACTER_TWO_SKILL_LOC_1);
//		rw.writeBytes(bytesToUse, TomeOfKnowledge.CHARACTER_TWO_SKILL_LOC_2);
//		rw.writeBytes(spellID, TomeOfKnowledge.CHARACTER_TWO_SPELL_LOC_1);
	}

	@JsonIgnore
	public String[] getCharacterNames() {
		String[] charNames = new String[3];
		charNames[0] = characters.get(0).getClassName();
		charNames[1] = characters.get(1).getClassName();
		charNames[2] = characters.get(2).getClassName();
		return charNames;
	}

	public Character getCharacterByName(String charName) {
		Character characterSelected = null;
		for(Character c : characters){
			if(c.getClassName().equals(charName)){
				characterSelected = c;
			}
		}
		return characterSelected;
	}

	public void initialiseUsingBinary(ReaderWriter rw, StringExtractor stringExtractor) {
		this.characterFactory.setStringExtractor(stringExtractor);
		this.rw = rw;
	}

	public void updateWith(CharacterStore characterStore) {
		for(int i = 0; i < characters.size(); i++) {
			Character character = characters.get(i);
			Character charToUpdateFrom = characterStore.characters.get(i);
			character.updateWith(charToUpdateFrom);
		}
	}

	@JsonProperty(value = "characterSelected")
	public Character getCharacterSelected() {
		return characterSelected;
	}

	@JsonIgnore
	public void setCharacterSelected(Character characterSelected) {
		this.characterSelected = characterSelected;
	}
}
