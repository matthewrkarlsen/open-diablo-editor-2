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
import org.d1ablo.ode.types.ItemEffect;
import org.d1ablo.ode.types.UniqueItem;

import java.util.ArrayList;
import java.util.List;

public class UniqueItemFactory {

    private final BinEditTool binEditTool;

    private StringExtractor stringExtractor;

    public UniqueItemFactory(BinEditTool binEditTool) {
        this.binEditTool = binEditTool;
    }

    public void setStringExtractor(StringExtractor stringExtractor) {
        this.stringExtractor = stringExtractor;
    }

    public UniqueItem constructUniqueItem(byte[] uniqueItemBytes) {
        long namePointer = binEditTool.convertFourBytesToOffset(uniqueItemBytes, 0);
        String name = stringExtractor.getNameUsingPointer(namePointer);
        int itemType = binEditTool.convertByteToUnsignedInt(uniqueItemBytes[4]);
        int qualityLevel = binEditTool.convertByteToUnsignedInt(uniqueItemBytes[5]);
        int numberOfEffects = binEditTool.convertTwoBytesToIntWithLSBFirst(uniqueItemBytes[6], uniqueItemBytes[7]);
        long goldValue = binEditTool.convertFourBytesInArrayToNumber(uniqueItemBytes, 8);
        List<ItemEffect> itemEffects = new ArrayList<>();
        for(int offset = 12; offset < 84; offset = offset + 12){
            long effectNumber = offset / 12;
            long effect = binEditTool.convertFourBytesInArrayToNumber(uniqueItemBytes, offset);
            long minValue = binEditTool.convertFourBytesInArrayToNumber(uniqueItemBytes, offset+4);
            long maxValue = binEditTool.convertFourBytesInArrayToNumber(uniqueItemBytes, offset+8);
            ItemEffect ie = new ItemEffect(effectNumber, effect, minValue, maxValue);
            itemEffects.add(ie);
        }

        return new UniqueItem(namePointer, name, itemType, qualityLevel, numberOfEffects, goldValue,
                itemEffects, binEditTool);
    }

    // Used to initialise the selected unique item in the JSON used by Vue.js on the client side
    public UniqueItem getDummyUniqueItem() {
        List<ItemEffect> itemEffects = new ArrayList<>();
        for(int offset = 12; offset < 84; offset = offset + 12){
            ItemEffect ie = ItemEffect.getDummyItemEffect();
            itemEffects.add(ie);
        }
        return new UniqueItem(0L, "unset", 0, 0, 0, 0L, itemEffects, binEditTool);
    }
}
