/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.txt for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */
package org.d1ablo.ode;

import org.d1ablo.ode.bintool.BinEditTool;
import org.d1ablo.ode.bintool.FileCopier;
import org.d1ablo.ode.bintool.StringExtractor;
import org.d1ablo.ode.bintool.rw.ReaderWriter;
import org.d1ablo.ode.factories.*;
import org.d1ablo.ode.stores.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.file.FileAlreadyExistsException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * A class that can be modified by people who have cloned the repo, to do their own modes in a code-based environment.
 * Intended for developers or advanced power users only.
 */
public class ProgrammaticODE {

	private CompleteStore completeStore;

	public static void main(String[] args){
		ProgrammaticODE dm = new ProgrammaticODE();
		dm.run();
	}

	/*
	 * Run the key four steps. Don't change this part for basic modding.
	 */
	public void run(){
		copyBinaryAndUseToInitialiseStores();
		printOutAllGameObjectData();
		implementModChanges();
		writeChangesToDisk();
	}

	/*
	 * Read in data and set up the writer. Don't change this part for basic modding.
	 */
	private void copyBinaryAndUseToInitialiseStores() {
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		String newPath = "Diablo_modified_" + date.format(dateFormat) + ".exe";
		File newFile = initiateFileCopy(newPath);

		RandomAccessFile randomAccessFile;
		try {
			randomAccessFile = new RandomAccessFile(newFile.getPath(), "rw");
		} catch (FileNotFoundException e) {
			throw new IllegalStateException(e);
		}
		ReaderWriter rw = new ReaderWriter(randomAccessFile);

		BinEditTool binEditTool = new BinEditTool();
		QuestFactory questFactory = new QuestFactory(binEditTool);
		SpellFactory spellFactory = new SpellFactory(binEditTool);
		ShrineFactory shrineFactory = new ShrineFactory();
		ItemModifierFactory itemModifierFactory = new ItemModifierFactory(binEditTool);
		UniqueItemFactory uniqueItemFactory = new UniqueItemFactory(binEditTool);
		CharacterFactory characterFactory = new CharacterFactory(binEditTool);
		BaseItemFactory baseItemFactory = new BaseItemFactory(binEditTool);
		BaseMonsterFactory baseMonsterFactory = new BaseMonsterFactory(binEditTool);
		UniqueMonsterFactory uniqueMonsterFactory = new UniqueMonsterFactory(binEditTool);

		completeStore = new CompleteStore(
				new QuestStore(new ArrayList<>(), questFactory, questFactory.getDummyQuest()),
				new SpellsStore(new ArrayList<>(), spellFactory, spellFactory.getDummySpell()),
				new ShrinesStore(new ArrayList<>(), binEditTool, shrineFactory, shrineFactory.getDummyShrine()),
				new ItemModifiersStore(new ArrayList<>(), itemModifierFactory, itemModifierFactory.getDummyItemModifier()),
				new UniqueItemStore(new ArrayList<>(), uniqueItemFactory, uniqueItemFactory.getDummyUniqueItem()),
				new CharacterStore(new ArrayList<>(), binEditTool, characterFactory, characterFactory.getDummyCharacter()),
				new BaseItemStore(new ArrayList<>(), baseItemFactory, baseItemFactory.getDummyBaseItem()),
				new BaseMonsterStore(new ArrayList<>(), baseMonsterFactory, baseMonsterFactory.getDummyBaseMonster()),
				new UniqueMonsterStore(new ArrayList<>(), uniqueMonsterFactory, uniqueMonsterFactory.getDummyUniqueMonster())
		);

		StringExtractor stringExtractor = new StringExtractor(rw.getReader());
		completeStore.initialiseUsingBinary(rw, stringExtractor);
	}

	/*
	 * Print out game object data (show you what you can change). Don't change this part for basic modding (usually).
	 */
	private void printOutAllGameObjectData() {
		completeStore.printAll();

		//Getting just a few items to consider is also possible. See commented-out line below.
		//completeStore.getBaseItemStore().getItemByName("Buckler").printItem();
	}

	/*
	 * Do your modding stuff below here. Examples below (commented out).
	 */
	private void implementModChanges() {
		//v1
		//completeStore.getShrineStore().disableBadShrines();

		//v2
		//completeStore.getCharacterStore().setAllMaxStatsTo255();

		//v3
		//completeStore.getCharacterStore().setCharZeroStartingSkillBySpellID(2); //healing
		//completeStore.getCharacterStore().setCharOneStartingSkillBySpellID(9); //infra-vision (inner sight)
		//completeStore.getCharacterStore().setCharTwoStartingSkillBySpellID(5); //identify

		//v4 -- TODO
		//fix or disable yellow zombies
		//change name of infra-vision
		//make healing skill mana drain
	}

	/*
	 * Write to the copy of the Diablo executable. Don't change this part for basic modding (usually).
	 */
	private void writeChangesToDisk() {
		completeStore.writeAllData();
	}

	private File initiateFileCopy(String toFileName) {
		File newFile;
		try {
			newFile = FileCopier.copyFile("Diablo.exe", toFileName);
		} catch (FileAlreadyExistsException e) {
			throw new IllegalStateException(e);
		}
		return newFile;
	}
}
