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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.d1ablo.ode.bintool.FileCopier;
import org.d1ablo.ode.bintool.StringExtractor;
import org.d1ablo.ode.bintool.rw.ReaderWriter;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;

/**
 * A meta-store containing all of the stores for the individual entity types
 * used within the application.
 */
@JsonRootName(value = "d1Data")
public class CompleteStore {

    @JsonProperty("quests")
    private final QuestStore questStore;

    @JsonProperty("spells")
    private final SpellsStore spellStore;

    @JsonProperty("shrines")
    private final ShrinesStore shrineStore;

    @JsonProperty("itemModifiers")
    private final ItemModifiersStore modifierStore;

    @JsonProperty("uniqueItems")
    private final UniqueItemStore uniqueItemStore;

    @JsonProperty("characters")
    private final CharacterStore characterStore;

    @JsonProperty("baseItems")
    private final BaseItemStore baseItemStore;

    @JsonProperty("baseMonsters")
    private final BaseMonsterStore baseMonsterStore;

    @JsonProperty("uniqueMonsters")
    private final UniqueMonsterStore uniqueMonsterStore;

    @JsonCreator
    public CompleteStore(@JsonProperty("quests") QuestStore questStore,
                         @JsonProperty("spells") SpellsStore spellStore,
                         @JsonProperty("shrines") ShrinesStore shrineStore,
                         @JsonProperty("itemModifiers") ItemModifiersStore modifierStore,
                         @JsonProperty("uniqueItems") UniqueItemStore uniqueItemStore,
                         @JsonProperty("characters") CharacterStore characterStore,
                         @JsonProperty("baseItems") BaseItemStore baseItemStore,
                         @JsonProperty("baseMonsters") BaseMonsterStore baseMonsterStore,
                         @JsonProperty("uniqueMonsters") UniqueMonsterStore uniqueMonsterStore) {
        this.questStore = questStore;
        this.spellStore = spellStore;
        this.shrineStore = shrineStore;
        this.modifierStore = modifierStore;
        this.uniqueItemStore = uniqueItemStore;
        this.characterStore = characterStore;
        this.baseItemStore = baseItemStore;
        this.baseMonsterStore = baseMonsterStore;
        this.uniqueMonsterStore = uniqueMonsterStore;
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

    public QuestStore getQuestStore() {
        return questStore;
    }

    public SpellsStore getSpellStore() {
        return spellStore;
    }

    public ShrinesStore getShrineStore() {
        return shrineStore;
    }

    public ItemModifiersStore getModifierStore() {
        return modifierStore;
    }

    public UniqueItemStore getUniqueItemStore() {
        return uniqueItemStore;
    }

    public CharacterStore getCharacterStore() {
        return characterStore;
    }

    public BaseItemStore getBaseItemStore() {
        return baseItemStore;
    }

    public BaseMonsterStore getBaseMonsterStore() {
        return baseMonsterStore;
    }

    public UniqueMonsterStore getUniqueMonsterStore() {
        return uniqueMonsterStore;
    }

    public void updateWith(CompleteStore receivedStore) {
        baseItemStore.updateWith(receivedStore.baseItemStore);
        baseMonsterStore.updateWith(receivedStore.baseMonsterStore);
        characterStore.updateWith(receivedStore.characterStore);
        modifierStore.updateWith(receivedStore.modifierStore);
        questStore.updateWith(receivedStore.questStore);
        shrineStore.updateWith(receivedStore.shrineStore);
        spellStore.updateWith(receivedStore.spellStore);
        uniqueItemStore.updateWith(receivedStore.uniqueItemStore);
        uniqueMonsterStore.updateWith(receivedStore.uniqueMonsterStore);
    }

    public void writeAllData() {
        System.out.println("Writing all modified bytes to binary (exe file)...");
        shrineStore.writeShrinesToEXE();
        questStore.writeQuestsToEXE();
        spellStore.writeSpellsToEXE();
        modifierStore.writeModifiersToEXE();
        uniqueItemStore.writeItemsToEXE();
        characterStore.writeCharactersToEXE();
        baseItemStore.writeItemsToEXE();
        baseMonsterStore.writeMonstersToEXE();
        uniqueMonsterStore.writeMonstersToEXE();
    }

    public void initialiseUsingBinary(ReaderWriter rw, StringExtractor stringExtractor) {
        questStore.initialiseUsingBinary(rw, stringExtractor);
        spellStore.initialiseUsingBinary(rw, stringExtractor);
        shrineStore.initialiseUsingBinary(rw, stringExtractor);
        modifierStore.initialiseUsingBinary(rw, stringExtractor);
        uniqueItemStore.initialiseUsingBinary(rw, stringExtractor);
        characterStore.initialiseUsingBinary(rw, stringExtractor);
        baseItemStore.initialiseUsingBinary(rw, stringExtractor);
        baseMonsterStore.initialiseUsingBinary(rw, stringExtractor);
        uniqueMonsterStore.initialiseUsingBinary(rw, stringExtractor);
        readInAll();
    }

    private void readInAll() {
        questStore.readInQuests();
        spellStore.readInSpells();
        shrineStore.readInShrines();
        modifierStore.readInModifiers();
        uniqueItemStore.readInItems();
        characterStore.readInCharacters();
        baseItemStore.readInItems();
        baseMonsterStore.readInMonsters();
        uniqueMonsterStore.readInUniques();
    }

    public void printAll() {
        questStore.printQuests();
        spellStore.printSpells();
        shrineStore.printShrines();
        modifierStore.printModifiers();
        uniqueItemStore.printItems();
        characterStore.printCharacters();
        baseItemStore.printItems();
        baseMonsterStore.printMonsters();
        uniqueMonsterStore.printUniques();
    }
}
