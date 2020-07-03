/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.txt for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */
package org.d1ablo.ode.factories;

import org.d1ablo.ode.bintool.BinEditTool;
import org.d1ablo.ode.bintool.StringExtractor;
import org.d1ablo.ode.types.UniqueMonster;

public class UniqueMonsterFactory {

    private final BinEditTool binEditTool;

    private StringExtractor stringExtractor;

    public UniqueMonsterFactory(BinEditTool binEditTool) {
        this.binEditTool = binEditTool;
    }

    public void setStringExtractor(StringExtractor stringExtractor) {
        this.stringExtractor = stringExtractor;
    }

    public UniqueMonster constructUniqueMonster(byte[] monsterBytes) {
        long monsterType = binEditTool.convertFourBytesInArrayToNumber(monsterBytes, 0);
        long namePointer = binEditTool.convertFourBytesToOffset(monsterBytes, 4);
        String name = stringExtractor.getNameUsingPointer(namePointer);
        long trnPointer = binEditTool.convertFourBytesToOffset(monsterBytes, 8);
        String trnName = stringExtractor.getNameUsingPointer(trnPointer);
        int dungeonLevel = binEditTool.convertTwoBytesToIntWithLSBFirst(monsterBytes[12], monsterBytes[13]);
        int hitPoints = binEditTool.convertTwoBytesToIntWithLSBFirst(monsterBytes[14], monsterBytes[15]);
        int monsterAI = binEditTool.convertByteToUnsignedInt(monsterBytes[16]);
        int intelligenceFactor = binEditTool.convertByteToUnsignedInt(monsterBytes[17]);
        int minAttackDmg = binEditTool.convertByteToUnsignedInt(monsterBytes[18]);
        int maxAttackDmg = binEditTool.convertByteToUnsignedInt(monsterBytes[19]);
        String resistances = binEditTool.convertByteToBinaryString(monsterBytes[20]) + ";"
                + binEditTool.convertByteToBinaryString(monsterBytes[21]);
        int packTrigger = binEditTool.convertTwoBytesToIntWithLSBFirst(monsterBytes[22], monsterBytes[23]);
        long packSpecials = binEditTool.convertFourBytesInArrayToNumber(monsterBytes, 24);
        long specialSoundWav = binEditTool.convertFourBytesInArrayToNumber(monsterBytes, 28);

        return new UniqueMonster(monsterType, namePointer, name, trnPointer, trnName, dungeonLevel, hitPoints,
                monsterAI, intelligenceFactor, minAttackDmg, maxAttackDmg, resistances, packTrigger, packSpecials,
                specialSoundWav, binEditTool);
    }

    // Used to initialise the selected unique monster in the JSON used by Vue.js on the client side
    public UniqueMonster getDummyUniqueMonster() {
        return new UniqueMonster(0, 0, "unset", 0, "unset", 0, 0, 0, 0, 0, 0, "unset", 0, 0, 0, binEditTool);
    }
}
