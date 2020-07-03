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
import org.d1ablo.ode.types.Spell;

public class SpellFactory {

    private final BinEditTool binEditTool;

    private StringExtractor stringExtractor;

    public SpellFactory(BinEditTool binEditTool) {
        this.binEditTool = binEditTool;
    }

    public void setStringExtractor(StringExtractor stringExtractor) {
        this.stringExtractor = stringExtractor;
    }

    public Spell constructSpell(byte[] spellBytes, int index) {
        int indexInGame = index + 1; //spell index starts from 1, loop in SpellsStore starts from 0
        if(spellBytes.length != 56){
            throw new IllegalStateException("byteArray supplied to Spell constructor of incorrect length (not 56).");
        }
        int unmoddedSpellIndex = binEditTool.convertByteToUnsignedInt(spellBytes[0]);
        int manaToCast = binEditTool.convertByteToUnsignedInt(spellBytes[1]);
        int animationWhenCasting = binEditTool.convertTwoBytesToIntWithLSBFirst(spellBytes[2], spellBytes[3]);
        long pointerToNameAsSpell = binEditTool.convertFourBytesToOffset(spellBytes, 4);
        String nameAsSpell = stringExtractor.getNameUsingPointer(pointerToNameAsSpell);
        long unadjustedPointer = binEditTool.convertFourBytesInArrayToNumber(spellBytes, 8);
        long pointerToNameAsSkill;
        String nameAsSkill;
        if(unadjustedPointer != 0) {
            pointerToNameAsSkill = binEditTool.convertFourBytesToOffset(spellBytes, 8);
            nameAsSkill = stringExtractor.getNameUsingPointer(pointerToNameAsSkill);
        } else {
            pointerToNameAsSkill = 0;
            nameAsSkill = "not set";
        }
        long spellBookQuality = binEditTool.convertFourBytesInArrayToNumber(spellBytes, 12);
        long staffQuality = binEditTool.convertFourBytesInArrayToNumber(spellBytes, 16);
        int byteTwenty = binEditTool.convertByteToUnsignedInt(spellBytes[20]);
        int byteTwentyOne = binEditTool.convertByteToUnsignedInt(spellBytes[21]);
        int byteTwentyTwo = binEditTool.convertByteToUnsignedInt(spellBytes[22]);
        int byteTwentyThree = binEditTool.convertByteToUnsignedInt(spellBytes[23]);
        long spellActiveInTown = binEditTool.convertFourBytesInArrayToNumber(spellBytes, 24);
        long baseRequiredMagic = binEditTool.convertFourBytesInArrayToNumber(spellBytes, 28);
        int castingSound = binEditTool.convertByteToUnsignedInt(spellBytes[32]);
        byte[] spellEffects = new byte[3];
        spellEffects[0] = spellBytes[33];
        spellEffects[1] = spellBytes[34];
        spellEffects[2] = spellBytes[35];
        int manaStep = binEditTool.convertByteToUnsignedInt(spellBytes[36]);
        int minCastingCost = binEditTool.convertByteToUnsignedInt(spellBytes[37]);
        int byteThirtyEight = binEditTool.convertByteToUnsignedInt(spellBytes[38]);
        int byteThirtyNine = binEditTool.convertByteToUnsignedInt(spellBytes[39]);
        long minCharges = binEditTool.convertFourBytesInArrayToNumber(spellBytes, 40);
        long maxCharges = binEditTool.convertFourBytesInArrayToNumber(spellBytes, 44);
        long bookCost = binEditTool.convertFourBytesInArrayToNumber(spellBytes, 48);
        long staffCostMultiplier = binEditTool.convertFourBytesInArrayToNumber(spellBytes, 52);

        return new Spell(unmoddedSpellIndex, manaToCast, indexInGame, animationWhenCasting, pointerToNameAsSpell,
                pointerToNameAsSkill, nameAsSpell, nameAsSkill, spellBookQuality, staffQuality, byteTwenty,
                byteTwentyOne, byteTwentyTwo, byteTwentyThree, spellActiveInTown, baseRequiredMagic, castingSound,
                spellEffects, manaStep, minCastingCost, byteThirtyEight, byteThirtyNine, minCharges, maxCharges,
                bookCost, staffCostMultiplier, binEditTool);
    }

    // Used to initialise the selected spell in the JSON used by Vue.js on the client side
    public Spell getDummySpell() {
        return new Spell(0, 0, 0, 0, 0, 0, "unset", "unset", 0, 0, 0, 0, 0, 0, 0, 0, 0, new byte[3], 0, 0, 0, 0,
                0, 0, 0, 0, binEditTool);
    }
}
