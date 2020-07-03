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
import org.d1ablo.ode.types.Character;

public class CharacterFactory {

    private final BinEditTool binEditTool;

    private StringExtractor stringExtractor;

    public CharacterFactory(BinEditTool binEditTool) {
        this.binEditTool = binEditTool;
    }

    public void setStringExtractor(StringExtractor stringExtractor) {
        this.stringExtractor = stringExtractor;
    }

    public Character constructCharacter(int index, String name, byte[] startingStats, byte[] maxStats,
                                        byte[] blockingBonuses, byte[] bonusesAndFrameSets) {

        long initStrength = binEditTool.convertFourBytesInArrayToNumber(startingStats, index * 4);
        long initMagic = binEditTool.convertFourBytesInArrayToNumber(startingStats, 12 + (index * 4));
        long initDexterity = binEditTool.convertFourBytesInArrayToNumber(startingStats, 24 + (index * 4));
        long initVitality = binEditTool.convertFourBytesInArrayToNumber(startingStats, 36 + (index * 4));

        long maxStrength = binEditTool.convertFourBytesInArrayToNumber(maxStats, index * 16);
        long maxMagic = binEditTool.convertFourBytesInArrayToNumber(maxStats, (index * 16) + 4);
        long maxDexterity = binEditTool.convertFourBytesInArrayToNumber(maxStats, (index * 16) + 8);
        long maxVitality = binEditTool.convertFourBytesInArrayToNumber(maxStats, (index * 16) + 12);

        long blockingBonus = binEditTool.convertFourBytesInArrayToNumber(blockingBonuses, index * 4);

        int dungeonIdleFrameset = binEditTool.convertByteToUnsignedInt(bonusesAndFrameSets[index * 11]);
        int attackingFrameset = binEditTool.convertByteToUnsignedInt(bonusesAndFrameSets[(index * 11) + 1]);
        int dungeonWalkFrameset = binEditTool.convertByteToUnsignedInt(bonusesAndFrameSets[(index * 11) + 2]);
        int blockSpeed = binEditTool.convertByteToUnsignedInt(bonusesAndFrameSets[(index * 11) + 3]);
        int deathFrameset = binEditTool.convertByteToUnsignedInt(bonusesAndFrameSets[(index * 11) + 4]);
        int castingFrameset = binEditTool.convertByteToUnsignedInt(bonusesAndFrameSets[(index * 11) + 5]);
        int hitRecoverySpeed = binEditTool.convertByteToUnsignedInt(bonusesAndFrameSets[(index * 11) + 6]);
        int townIdleFrameset = binEditTool.convertByteToUnsignedInt(bonusesAndFrameSets[(index * 11) + 7]);
        int townWalkFrameset = binEditTool.convertByteToUnsignedInt(bonusesAndFrameSets[(index * 11) + 8]);
        int oneHandedAttackSpeed = binEditTool.convertByteToUnsignedInt(bonusesAndFrameSets[(index * 11) + 9]);
        int castingSpeed = binEditTool.convertByteToUnsignedInt(bonusesAndFrameSets[(index * 11) + 10]);

        return new Character(index, name, initStrength, initMagic,
                initDexterity, initVitality, maxStrength, maxMagic, maxDexterity,
                maxVitality, blockingBonus, dungeonIdleFrameset, attackingFrameset,
                dungeonWalkFrameset, blockSpeed, deathFrameset, castingFrameset,
                hitRecoverySpeed, townIdleFrameset, townWalkFrameset, oneHandedAttackSpeed,
                castingSpeed
        );
    }

    // Used to initialise the selected character in the JSON used by Vue.js on the client side
    public Character getDummyCharacter() {
        return new Character(0, "unset", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }
}
