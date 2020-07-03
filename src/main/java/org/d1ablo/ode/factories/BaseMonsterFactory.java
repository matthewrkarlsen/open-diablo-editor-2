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
import org.d1ablo.ode.bintool.StringExtractor;
import org.d1ablo.ode.types.BaseMonster;

public class BaseMonsterFactory {

    private final BinEditTool binEditTool;

    private StringExtractor stringExtractor;

    public BaseMonsterFactory(BinEditTool binEditTool) {
        this.binEditTool = binEditTool;
    }

    public void setStringExtractor(StringExtractor stringExtractor) {
        this.stringExtractor = stringExtractor;
    }

    public BaseMonster constructBaseMonster(int slotNumber, byte[] monsterBytes, byte activationByte) {
        long animationSize = binEditTool.convertFourBytesInArrayToNumber(monsterBytes, 0);
        long seedingSize = binEditTool.convertFourBytesInArrayToNumber(monsterBytes, 4);
        long animationFilePointer = binEditTool.convertFourBytesToOffset(monsterBytes, 8);
        String animationFileName = stringExtractor.getNameUsingPointer(animationFilePointer);
        long secondAttack = binEditTool.convertFourBytesInArrayToNumber(monsterBytes, 12);
        long soundPointer = binEditTool.convertFourBytesToOffset(monsterBytes, 16);
        long hasSecondAttackSound = binEditTool.convertFourBytesInArrayToNumber(monsterBytes, 20);
        long usesTrnToModColor = binEditTool.convertFourBytesInArrayToNumber(monsterBytes, 24);
        long trnPointerUnadjusted = binEditTool.convertFourBytesInArrayToNumber(monsterBytes, 28);
        long trnPointer = 0;
        if(trnPointerUnadjusted != 0) {
            trnPointer = binEditTool.convertFourBytesToOffset(monsterBytes, 28);
        }
        long idleFrameSet = binEditTool.convertFourBytesInArrayToNumber(monsterBytes, 32);
        long walkFrameSet = binEditTool.convertFourBytesInArrayToNumber(monsterBytes, 36);
        long attackFrameSet = binEditTool.convertFourBytesInArrayToNumber(monsterBytes, 40);
        long hitRecoveryFrameSet = binEditTool.convertFourBytesInArrayToNumber(monsterBytes, 44);
        long deathFrameSet = binEditTool.convertFourBytesInArrayToNumber(monsterBytes, 48);
        long secondAttackFrameSet = binEditTool.convertFourBytesInArrayToNumber(monsterBytes, 52);
        long idlePlaybackSpeed = binEditTool.convertFourBytesInArrayToNumber(monsterBytes, 56);
        long walkPlaybackSpeed = binEditTool.convertFourBytesInArrayToNumber(monsterBytes, 60);
        long attackPlaybackSpeed = binEditTool.convertFourBytesInArrayToNumber(monsterBytes, 64);
        long hitRecoverySpeed = binEditTool.convertFourBytesInArrayToNumber(monsterBytes, 68);
        long deathPlaybackSpeed = binEditTool.convertFourBytesInArrayToNumber(monsterBytes, 72);
        long secondAttackSpeed = binEditTool.convertFourBytesInArrayToNumber(monsterBytes, 76);
        long namePointer = binEditTool.convertFourBytesToOffset(monsterBytes, 80);
        String name = stringExtractor.getNameUsingPointer(namePointer);
        int minDungeonLevel = binEditTool.convertByteToUnsignedInt(monsterBytes[84]);
        int maxDungeonLevel = binEditTool.convertByteToUnsignedInt(monsterBytes[85]);
        int monsterItemLevel = binEditTool.convertTwoBytesToIntWithLSBFirst(monsterBytes[86], monsterBytes[87]);
        long minHitPoints = binEditTool.convertFourBytesInArrayToNumber(monsterBytes, 88);
        long maxHitPoints = binEditTool.convertFourBytesInArrayToNumber(monsterBytes, 92);
        int attackType1 = binEditTool.convertByteToUnsignedInt(monsterBytes[96]);
        int attackType2 = binEditTool.convertByteToUnsignedInt(monsterBytes[97]);
        int attackType3 = binEditTool.convertByteToUnsignedInt(monsterBytes[98]);
        int attackType4 = binEditTool.convertByteToUnsignedInt(monsterBytes[99]);
        int attackType5 = binEditTool.convertByteToUnsignedInt(monsterBytes[100]);
        int monsterIntelligence = binEditTool.convertByteToUnsignedInt(monsterBytes[101]);
        int attackType7 = binEditTool.convertByteToUnsignedInt(monsterBytes[102]);
        int attackType8 = binEditTool.convertByteToUnsignedInt(monsterBytes[103]);
        int subType = binEditTool.convertByteToUnsignedInt(monsterBytes[104]);
        int priChanceToHit = binEditTool.convertByteToUnsignedInt(monsterBytes[105]);
        int priToHitFrame = binEditTool.convertByteToUnsignedInt(monsterBytes[106]);
        int priMinAttackDamage = binEditTool.convertByteToUnsignedInt(monsterBytes[107]);
        int priMaxAttackDamage = binEditTool.convertByteToUnsignedInt(monsterBytes[108]);
        int secToHitChance = binEditTool.convertByteToUnsignedInt(monsterBytes[109]);
        int secToHitFrame = binEditTool.convertByteToUnsignedInt(monsterBytes[110]);
        int secMinAttackDamage = binEditTool.convertByteToUnsignedInt(monsterBytes[111]);
        int secMaxAttackDamage = binEditTool.convertByteToUnsignedInt(monsterBytes[112]);
        int monsterAc = binEditTool.convertByteToUnsignedInt(monsterBytes[113]);
        int monsterType = binEditTool.convertTwoBytesToIntWithLSBFirst(monsterBytes[114], monsterBytes[115]);
        String normalResistances = binEditTool.convertByteToBinaryString(monsterBytes[116]);
        String nightmareResistances = binEditTool.convertByteToBinaryString(monsterBytes[117]);
        String resistancesNormAndNightmare = normalResistances + nightmareResistances;
        String hellResistancesPartOne = binEditTool.convertByteToBinaryString(monsterBytes[118]);
        String hellResistancesPartTwo = binEditTool.convertByteToBinaryString(monsterBytes[119]);
        String resistancesHell = hellResistancesPartOne + hellResistancesPartTwo;
        int itemDropSpecials = binEditTool.convertTwoBytesToIntWithLSBFirst(monsterBytes[120], monsterBytes[121]);
        int monsterSelectionOutline = binEditTool.convertTwoBytesToIntWithLSBFirst(monsterBytes[122], monsterBytes[123]);
        long experiencePoints = binEditTool.convertFourBytesInArrayToNumber(monsterBytes, 124);
        int enabled = binEditTool.convertByteToUnsignedInt(activationByte);

        return new BaseMonster(slotNumber, animationSize, seedingSize, animationFilePointer, animationFileName,
                secondAttack, soundPointer, hasSecondAttackSound, usesTrnToModColor, trnPointer, idleFrameSet,
                walkFrameSet, attackFrameSet, hitRecoveryFrameSet, deathFrameSet, secondAttackFrameSet,
                idlePlaybackSpeed, walkPlaybackSpeed, attackPlaybackSpeed, hitRecoverySpeed, deathPlaybackSpeed,
                secondAttackSpeed, namePointer, name, minDungeonLevel, maxDungeonLevel, monsterItemLevel, minHitPoints,
                maxHitPoints, attackType1, attackType2, attackType3, attackType4, attackType5, monsterIntelligence,
                attackType7, attackType8, subType, priChanceToHit, priToHitFrame, priMinAttackDamage,
                priMaxAttackDamage, secToHitChance, secToHitFrame, secMinAttackDamage, secMaxAttackDamage,
                monsterAc, monsterType, resistancesNormAndNightmare, resistancesHell, itemDropSpecials,
                monsterSelectionOutline, experiencePoints, enabled);
    }

    // Used to initialise the selected base monster in the JSON used by Vue.js on the client side
    public BaseMonster getDummyBaseMonster() {
        return new BaseMonster(0, 0, 0, 0, "unset", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                "unset", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "unset", "unset",
                0, 0, 0, 0);
    }
}
