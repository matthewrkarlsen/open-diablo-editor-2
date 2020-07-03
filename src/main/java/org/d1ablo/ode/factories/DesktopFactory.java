/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.md for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */
package org.d1ablo.ode.factories;

import org.d1ablo.ode.bintool.BinEditTool;
import org.d1ablo.ode.bintool.FileCopier;
import org.d1ablo.ode.bintool.StringExtractor;
import org.d1ablo.ode.bintool.rw.ReaderWriter;
import org.d1ablo.ode.gui.DesktopODE;
import org.d1ablo.ode.stores.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;

public class DesktopFactory {

    public static final String MODDED_EXE_FILE_NAME = "DiabloModded.exe";
    public static final String BASE_EXE_FILE_NAME = "Diablo.exe";

    public DesktopODE constructDesktopODE() {
        File moddedFile = new File(MODDED_EXE_FILE_NAME);
        File origFile = new File(BASE_EXE_FILE_NAME);

        ReaderWriter rw;
        if(moddedFile.exists()) {
            rw = obtainReaderWriter();
        } else if (origFile.exists()) {
            initiateFileCopy(BASE_EXE_FILE_NAME, MODDED_EXE_FILE_NAME);
            rw = obtainReaderWriter();
        } else {
            throw new IllegalStateException("Could not find DiabloModded.exe or Diablo.exe");
        }

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

        CompleteStore completeStore = new CompleteStore(
                new QuestStore(new ArrayList<>(), questFactory),
                new SpellsStore(new ArrayList<>(), spellFactory),
                new ShrinesStore(new ArrayList<>(), binEditTool, shrineFactory),
                new ItemModifiersStore(new ArrayList<>(), itemModifierFactory),
                new UniqueItemStore(new ArrayList<>(), uniqueItemFactory),
                new CharacterStore(new ArrayList<>(), binEditTool, characterFactory),
                new BaseItemStore(new ArrayList<>(), baseItemFactory),
                new BaseMonsterStore(new ArrayList<>(), baseMonsterFactory),
                new UniqueMonsterStore(new ArrayList<>(), uniqueMonsterFactory)
        );

        StringExtractor stringExtractor = new StringExtractor(rw.getReader());
        completeStore.initialiseUsingBinary(rw, stringExtractor);
        return new DesktopODE(completeStore);
    }

    private ReaderWriter obtainReaderWriter() {
        ReaderWriter rw;
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(MODDED_EXE_FILE_NAME, "rw");
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
        rw = new ReaderWriter(randomAccessFile);
        return rw;
    }

    private File initiateFileCopy(String fromFileName, String toFileName) {
        File newFile;
        try {
            newFile = FileCopier.copyFile(fromFileName, toFileName);
        } catch (FileAlreadyExistsException e) {
            throw new IllegalStateException(e);
        }
        return newFile;
    }
}
