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
import org.d1ablo.ode.knowledge.DiabloFilePositions;
import org.d1ablo.ode.types.ItemModifier;

import java.util.Arrays;

public class ItemModifierFactory {

    private final BinEditTool binEditTool;

    private StringExtractor stringExtractor;

    public ItemModifierFactory(BinEditTool binEditTool) {
        this.binEditTool = binEditTool;
    }

    public void setStringExtractor(StringExtractor stringExtractor) {
        this.stringExtractor = stringExtractor;
    }

    public ItemModifier constructItemModifier(byte[] itemModBytes) {
        long namePointer = binEditTool.convertFourBytesToOffset(itemModBytes, 0);
        String name = stringExtractor.getNameUsingPointer(namePointer);
        byte[] itemEffects = Arrays.copyOfRange(itemModBytes, 4, 4 + DiabloFilePositions.NUMBER_OF_ITEM_EFFECTS);
        long minimumEffectValue = binEditTool.convertFourBytesInArrayToNumber(itemModBytes, 8);
        long maximumEffectValue = binEditTool.convertFourBytesInArrayToNumber(itemModBytes, 12);
        long qualityLevel = binEditTool.convertFourBytesInArrayToNumber(itemModBytes, 16);
        String occurrencePossibilities = String.format("%02X", itemModBytes[22]) +
                String.format("%02X", itemModBytes[21]) + String.format("%02X", itemModBytes[20]);
        int byteTwentyThree = binEditTool.convertByteToUnsignedInt(itemModBytes[23]);
        String excludedComboIndicator = String.format("%02X", itemModBytes[31]) + ";" +
                String.format("%02X", itemModBytes[30]) +
                ";" + String.format("%02X", itemModBytes[29]) + ";" + String.format("%02X", itemModBytes[28]) +
                ";" + String.format("%02X", itemModBytes[27]) + ";" + String.format("%02X", itemModBytes[26]) +
                ";" + String.format("%02X", itemModBytes[25]) + ";" + String.format("%02X", itemModBytes[24]);
        long cursedIndicator = binEditTool.convertFourBytesInArrayToNumber(itemModBytes, 32);
        long minGold = binEditTool.convertFourBytesInArrayToNumber(itemModBytes, 36);
        long maxGold = binEditTool.convertFourBytesInArrayToNumber(itemModBytes, 40);
        long valueMultiplier = binEditTool.convertFourBytesInArrayToNumber(itemModBytes, 44);

        return new ItemModifier(namePointer, name, itemEffects, minimumEffectValue, maximumEffectValue,
                qualityLevel, occurrencePossibilities, byteTwentyThree, excludedComboIndicator, cursedIndicator,
                minGold, maxGold, valueMultiplier);
    }

    // Used to initialise the selected item modifier in the JSON used by Vue.js on the client side
    public ItemModifier getDummyItemModifier() {
        return new ItemModifier(0, "unset", new byte[16], 0, 0, 0, "unset", 0, "unset",
                0, 0, 0, 0);
    }
}
