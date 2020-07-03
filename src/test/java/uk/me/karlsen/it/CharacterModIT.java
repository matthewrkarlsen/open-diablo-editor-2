package uk.me.karlsen.it;

import org.d1ablo.ode.bintool.*;
import org.d1ablo.ode.bintool.rw.ReaderWriter;
import org.d1ablo.ode.factories.*;
import org.d1ablo.ode.knowledge.DiabloFilePositions;
import org.d1ablo.ode.stores.*;
import org.d1ablo.ode.types.Character;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.fail;

//TODO -- check init <= max && check values of 0 && check values of > 255
public class CharacterModIT {

    @Before
    public void beforeTest() {
        File diabloTestFile = new File("DiabloTest.exe");
        if(diabloTestFile.exists()) {
            boolean deleted = diabloTestFile.delete();
            if(!deleted) {
                throw new IllegalStateException("Could not delete DiabloTest.exe during clean up.");
            }
        }
    }

    @After
    public void afterTest() {
        File diabloTestFile = new File("DiabloTest.exe");
        boolean deleted = diabloTestFile.delete();
        if(!deleted) {
            throw new IllegalStateException("Could not delete DiabloTest.exe during clean up.");
        }
    }

    @Test
    public void givenStandardExe_whenSetWarriorInitStr31_thenModdedExeValue31() {
        testModStat(new BinDiff(DiabloFilePositions.INIT_STR_OFFSET, -1, 31), odeCore -> {
            CharacterStore characterStore = odeCore.getCharacterStore();
            Character c0 = characterStore.getCharacter(0);
            c0.setInitStrength(31);
            odeCore.writeAllData();
        });
    }

    @Test
    public void givenStandardExe_whenSetWarriorInitMag44_thenModdedExeValue44() {
        testModStat(new BinDiff(DiabloFilePositions.INIT_MAG_OFFSET, -1, 44), odeCore -> {
            CharacterStore characterStore = odeCore.getCharacterStore();
            Character c0 = characterStore.getCharacter(0);
            c0.setInitMagic(44);
            odeCore.writeAllData();
        });
    }

    @Test
    public void givenStandardExe_whenSetWarriorInitDex24_thenModdedExeValue24() {
        testModStat(new BinDiff(DiabloFilePositions.INIT_DEX_OFFSET, -1, 24), odeCore -> {
            CharacterStore characterStore = odeCore.getCharacterStore();
            Character c0 = characterStore.getCharacter(0);
            c0.setInitDexterity(24);
            odeCore.writeAllData();
        });
    }

    @Test
    public void givenStandardExe_whenSetWarriorInitVit32_thenModdedExeValue32() {
        testModStat(new BinDiff(DiabloFilePositions.INIT_VIT_OFFSET, -1, 32), odeCore -> {
            CharacterStore characterStore = odeCore.getCharacterStore();
            Character c0 = characterStore.getCharacter(0);
            c0.setInitVitality(32);
            odeCore.writeAllData();
        });
    }

    @Test
    public void givenStandardExe_whenSetRogueInitStr31_thenModdedExeValue31() {
        testModStat(new BinDiff(DiabloFilePositions.INIT_STR_OFFSET + 4, -1, 31), odeCore -> {
            CharacterStore characterStore = odeCore.getCharacterStore();
            Character c1 = characterStore.getCharacter(1);
            c1.setInitStrength(31);
            odeCore.writeAllData();
        });
    }

    @Test
    public void givenStandardExe_whenSetRogueInitMag44_thenModdedExeValue44() {
        testModStat(new BinDiff(DiabloFilePositions.INIT_MAG_OFFSET + 4, -1, 44), odeCore -> {
            CharacterStore characterStore = odeCore.getCharacterStore();
            Character c1 = characterStore.getCharacter(1);
            c1.setInitMagic(44);
            odeCore.writeAllData();
        });
    }

    @Test
    public void givenStandardExe_whenSetRogueInitDex24_thenModdedExeValue24() {
        testModStat(new BinDiff(DiabloFilePositions.INIT_DEX_OFFSET + 4, -1, 24), odeCore -> {
            CharacterStore characterStore = odeCore.getCharacterStore();
            Character c1 = characterStore.getCharacter(1);
            c1.setInitDexterity(24);
            odeCore.writeAllData();
        });
    }

    @Test
    public void givenStandardExe_whenSetRogueInitVit32_thenModdedExeValue32() {
        testModStat(new BinDiff(DiabloFilePositions.INIT_VIT_OFFSET + 4, -1, 32), odeCore -> {
            CharacterStore characterStore = odeCore.getCharacterStore();
            Character c1 = characterStore.getCharacter(1);
            c1.setInitVitality(32);
            odeCore.writeAllData();
        });
    }

    @Test
    public void givenStandardExe_whenSetMageInitStr31_thenModdedExeValue31() {
        testModStat(new BinDiff(DiabloFilePositions.INIT_STR_OFFSET + 8, -1, 31), odeCore -> {
            CharacterStore characterStore = odeCore.getCharacterStore();
            Character c2 = characterStore.getCharacter(2);
            c2.setInitStrength(31);
            odeCore.writeAllData();
        });
    }

    @Test
    public void givenStandardExe_whenSetMageInitMag44_thenModdedExeValue44() {
        testModStat(new BinDiff(DiabloFilePositions.INIT_MAG_OFFSET + 8, -1, 44), odeCore -> {
            CharacterStore characterStore = odeCore.getCharacterStore();
            Character c2 = characterStore.getCharacter(2);
            c2.setInitMagic(44);
            odeCore.writeAllData();
        });
    }

    @Test
    public void givenStandardExe_whenSetMageInitDex24_thenModdedExeValue24() {
        testModStat(new BinDiff(DiabloFilePositions.INIT_DEX_OFFSET + 8, -1, 24), odeCore -> {
            CharacterStore characterStore = odeCore.getCharacterStore();
            Character c2 = characterStore.getCharacter(2);
            c2.setInitDexterity(24);
            odeCore.writeAllData();
        });
    }

    @Test
    public void givenStandardExe_whenSetMageInitVit32_thenModdedExeValue32() {
        testModStat(new BinDiff(DiabloFilePositions.INIT_VIT_OFFSET + 8, -1, 32), odeCore -> {
            CharacterStore characterStore = odeCore.getCharacterStore();
            Character c2 = characterStore.getCharacter(2);
            c2.setInitVitality(32);
            odeCore.writeAllData();
        });
    }

    /*
     * MAX VALUES
     */
    @Test
    public void givenStandardExe_whenSetWarriorMaxStr31_thenModdedExeValue31() {
        testModStat(new BinDiff(DiabloFilePositions.WAR_MAX_STAT_OFFSET, -1, 31), odeCore -> {
            CharacterStore characterStore = odeCore.getCharacterStore();
            Character c0 = characterStore.getCharacter(0);
            c0.setMaxStrength(31);
            odeCore.writeAllData();
        });
    }

    @Test
    public void givenStandardExe_whenSetWarriorMaxMag44_thenModdedExeValue44() {
        testModStat(new BinDiff(DiabloFilePositions.WAR_MAX_STAT_OFFSET + 4, -1, 44), odeCore -> {
            CharacterStore characterStore = odeCore.getCharacterStore();
            Character c0 = characterStore.getCharacter(0);
            c0.setMaxMagic(44);
            odeCore.writeAllData();
        });
    }

    @Test
    public void givenStandardExe_whenSetWarriorMaxDex24_thenModdedExeValue24() {
        testModStat(new BinDiff(DiabloFilePositions.WAR_MAX_STAT_OFFSET + 8, -1, 24), odeCore -> {
            CharacterStore characterStore = odeCore.getCharacterStore();
            Character c0 = characterStore.getCharacter(0);
            c0.setMaxDexterity(24);
            odeCore.writeAllData();
        });
    }

    @Test
    public void givenStandardExe_whenSetWarriorMaxVit32_thenModdedExeValue32() {
        testModStat(new BinDiff(DiabloFilePositions.WAR_MAX_STAT_OFFSET + 12, -1, 32), odeCore -> {
            CharacterStore characterStore = odeCore.getCharacterStore();
            Character c0 = characterStore.getCharacter(0);
            c0.setMaxVitality(32);
            odeCore.writeAllData();
        });
    }

    @Test
    public void givenStandardExe_whenSetRogueMaxStr31_thenModdedExeValue31() {
        testModStat(new BinDiff(DiabloFilePositions.ROG_MAX_STAT_OFFSET, -1, 31), odeCore -> {
            CharacterStore characterStore = odeCore.getCharacterStore();
            Character c1 = characterStore.getCharacter(1);
            c1.setMaxStrength(31);
            odeCore.writeAllData();
        });
    }

    @Test
    public void givenStandardExe_whenSetRogueMaxMag44_thenModdedExeValue44() {
        testModStat(new BinDiff(DiabloFilePositions.ROG_MAX_STAT_OFFSET + 4, -1, 44), odeCore -> {
            CharacterStore characterStore = odeCore.getCharacterStore();
            Character c1 = characterStore.getCharacter(1);
            c1.setMaxMagic(44);
            odeCore.writeAllData();
        });
    }

    @Test
    public void givenStandardExe_whenSetRogueMaxDex24_thenModdedExeValue24() {
        testModStat(new BinDiff(DiabloFilePositions.ROG_MAX_STAT_OFFSET + 8, -1, 24), odeCore -> {
            CharacterStore characterStore = odeCore.getCharacterStore();
            Character c1 = characterStore.getCharacter(1);
            c1.setMaxDexterity(24);
            odeCore.writeAllData();
        });
    }

    @Test
    public void givenStandardExe_whenSetRogueMaxVit32_thenModdedExeValue32() {
        testModStat(new BinDiff(DiabloFilePositions.ROG_MAX_STAT_OFFSET + 12, -1, 32), odeCore -> {
            CharacterStore characterStore = odeCore.getCharacterStore();
            Character c1 = characterStore.getCharacter(1);
            c1.setMaxVitality(32);
            odeCore.writeAllData();
        });
    }

    @Test
    public void givenStandardExe_whenSetMageMaxStr31_thenModdedExeValue31() {
        testModStat(new BinDiff(DiabloFilePositions.MAG_MAX_STAT_OFFSET, -1, 31), odeCore -> {
            CharacterStore characterStore = odeCore.getCharacterStore();
            Character c2 = characterStore.getCharacter(2);
            c2.setMaxStrength(31);
            odeCore.writeAllData();
        });
    }

    @Test
    public void givenStandardExe_whenSetMageMaxMag44_thenModdedExeValue44() {
        testModStat(new BinDiff(DiabloFilePositions.MAG_MAX_STAT_OFFSET + 4, -1, 44), odeCore -> {
            CharacterStore characterStore = odeCore.getCharacterStore();
            Character c2 = characterStore.getCharacter(2);
            c2.setMaxMagic(44);
            odeCore.writeAllData();
        });
    }

    @Test
    public void givenStandardExe_whenSetMageMaxDex24_thenModdedExeValue24() {
        testModStat(new BinDiff(DiabloFilePositions.MAG_MAX_STAT_OFFSET + 8, -1, 24), odeCore -> {
            CharacterStore characterStore = odeCore.getCharacterStore();
            Character c2 = characterStore.getCharacter(2);
            c2.setMaxDexterity(24);
            odeCore.writeAllData();
        });
    }

    @Test
    public void givenStandardExe_whenSetMageMaxVit32_thenModdedExeValue32() {
        testModStat(new BinDiff(DiabloFilePositions.MAG_MAX_STAT_OFFSET + 12, -1, 32), odeCore -> {
            CharacterStore characterStore = odeCore.getCharacterStore();
            Character c2 = characterStore.getCharacter(2);
            c2.setMaxVitality(32);
            odeCore.writeAllData();
        });
    }

    public void testModStat(BinDiff singleExpected, StatModOp statModOp) {
        List<BinDiff> expected = new ArrayList<>();
        expected.add(singleExpected);
        testModStat(expected, statModOp);
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

    public void testModStat(List<BinDiff> expected, StatModOp statModOp) {
        BinDiffTool binDiffTool = new BinDiffTool();

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

        File newFile = initiateFileCopy("Diablo.exe", "DiabloTest.exe");
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(newFile.getPath(), "rw");
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
        ReaderWriter rw = new ReaderWriter(randomAccessFile);
        completeStore.initialiseUsingBinary(rw, new StringExtractor(rw.getReader()));

        statModOp.executeStatMod(completeStore);
        List<BinDiff> binDiffList =
                binDiffTool.reportBinDiffs("Diablo.exe", "DiabloTest.exe");
        expected.sort(Comparator.comparingLong(BinDiff::getIndex));
        binDiffList.sort(Comparator.comparingLong(BinDiff::getIndex));
        for(int i = 0; i < expected.size(); i++) {
            BinDiff expectedBinDiff = expected.get(i);
            BinDiff actualBinDiff = binDiffList.get(i);
            if(expectedBinDiff.getIndex() != actualBinDiff.getIndex()) {
                fail("Expected index != actual index");
            }
            if(expectedBinDiff.getFileTwoByteValue() != actualBinDiff.getFileTwoByteValue()) {
                fail("Actual modified value != expected modified value");
            }
        }
    }

    private interface StatModOp {

        void executeStatMod(CompleteStore completeStore);
    }
}
