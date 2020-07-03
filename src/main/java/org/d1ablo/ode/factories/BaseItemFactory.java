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
import org.d1ablo.ode.types.BaseItem;

public class BaseItemFactory {

    private final BinEditTool binEditTool;

    private StringExtractor stringExtractor;

    public BaseItemFactory(BinEditTool binEditTool) {
        this.binEditTool = binEditTool;
    }

    public void setStringExtractor(StringExtractor stringExtractor) {
        this.stringExtractor = stringExtractor;
    }

    public BaseItem constructBaseItem(int slotNumber, byte[] itemBytes) {
        long activationTrigger = binEditTool.convertFourBytesInArrayToNumber(itemBytes, 0);
        int itemType = binEditTool.convertByteToUnsignedInt(itemBytes[4]);
        int equipLocation = binEditTool.convertByteToUnsignedInt(itemBytes[5]);
        int byteSix = binEditTool.convertByteToUnsignedInt(itemBytes[6]);
        int byteSeven = binEditTool.convertByteToUnsignedInt(itemBytes[7]);
        int graphicValue = (int) binEditTool.convertFourBytesInArrayToNumber(itemBytes, 8);
        int itemCode = binEditTool.convertByteToUnsignedInt(itemBytes[12]);
        int uniqueItemCode = binEditTool.convertByteToUnsignedInt(itemBytes[13]);
        int byteFourteen = binEditTool.convertByteToUnsignedInt(itemBytes[14]);
        int byteFifteen = binEditTool.convertByteToUnsignedInt(itemBytes[15]);
        long namePointer = binEditTool.convertFourBytesToOffset(itemBytes, 16);
        String name = stringExtractor.getNameUsingPointer(namePointer);
        long pointerUnadjusted = binEditTool.convertFourBytesInArrayToNumber(itemBytes, 20);
        long magicalNamePointer = 0;
        String magicalName = "not set";
        if(pointerUnadjusted != 0) {
            magicalNamePointer = binEditTool.convertFourBytesToOffset(itemBytes, 20);
            magicalName = stringExtractor.getNameUsingPointer(magicalNamePointer);
        }
        long qualityLevel = binEditTool.convertFourBytesInArrayToNumber(itemBytes, 24);
        long durability = binEditTool.convertFourBytesInArrayToNumber(itemBytes, 28);
        long minAttackDamage = binEditTool.convertFourBytesInArrayToNumber(itemBytes, 32);
        long maxAttackDamage = binEditTool.convertFourBytesInArrayToNumber(itemBytes, 36);
        long minAc = binEditTool.convertFourBytesInArrayToNumber(itemBytes, 40);
        long maxAc = binEditTool.convertFourBytesInArrayToNumber(itemBytes, 44);
        int requiredStr = binEditTool.convertByteToUnsignedInt(itemBytes[48]);
        int requiredMag = binEditTool.convertByteToUnsignedInt(itemBytes[49]);
        int requiredDex = binEditTool.convertByteToUnsignedInt(itemBytes[50]);
        int requiredVit = binEditTool.convertByteToUnsignedInt(itemBytes[51]);
        int specialEffects = (int) binEditTool.convertFourBytesInArrayToNumber(itemBytes, 52);
        long magicCode = binEditTool.convertFourBytesInArrayToNumber(itemBytes, 56);
        long spellNumber = binEditTool.convertFourBytesInArrayToNumber(itemBytes, 60);
        long singleUseFlag = binEditTool.convertFourBytesInArrayToNumber(itemBytes, 64);
        long priceOne = binEditTool.convertFourBytesInArrayToNumber(itemBytes, 68);
        long priceTwo = binEditTool.convertFourBytesInArrayToNumber(itemBytes, 72);

        return new BaseItem(slotNumber, activationTrigger, itemType, equipLocation, byteSix, byteSeven, graphicValue,
                itemCode, uniqueItemCode, byteFourteen, byteFifteen, namePointer, name, magicalNamePointer,
                magicalName, qualityLevel, durability, minAttackDamage, maxAttackDamage, minAc, maxAc, requiredStr,
                requiredMag, requiredDex, requiredVit, specialEffects, magicCode, spellNumber, singleUseFlag,
                priceOne, priceTwo, binEditTool);
    }

    // Used to initialise the selected base item in the JSON used by Vue.js on the client side
    public BaseItem getDummyBaseItem() {
        return new BaseItem(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "unset", 0, "unset", 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, binEditTool);
    }
}
