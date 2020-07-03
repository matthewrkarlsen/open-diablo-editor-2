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
import org.d1ablo.ode.types.Quest;
import org.d1ablo.ode.types.subtype.OneByteSetting;

public class QuestFactory {

    private final BinEditTool binEditTool;

    private StringExtractor stringExtractor;

    public QuestFactory(BinEditTool binEditTool) {
        this.binEditTool = binEditTool;
    }

    public void setStringExtractor(StringExtractor stringExtractor) {
        this.stringExtractor = stringExtractor;
    }

    public Quest constructQuest(byte[] questBytes, int indexValue) {
        int dungeonLevelSingleVal = binEditTool.convertByteToUnsignedInt(questBytes[0]);
        OneByteSetting dungeonLevelSingle = new OneByteSetting(dungeonLevelSingleVal, 1, 16);
        int dungeonLevelMulti = binEditTool.convertByteToUnsignedInt(questBytes[1]);
        int dungeonType = binEditTool.convertByteToUnsignedInt(questBytes[2]);
        int questNumber = binEditTool.convertByteToUnsignedInt(questBytes[3]);
        int byteFourValue = binEditTool.convertByteToUnsignedInt(questBytes[4]);
        int specialLevel = binEditTool.convertByteToUnsignedInt(questBytes[5]); //SP only
        int zeroOne = binEditTool.convertByteToUnsignedInt(questBytes[6]);
        int zeroTwo = binEditTool.convertByteToUnsignedInt(questBytes[7]);
        long mpTriggerFlag = binEditTool.convertFourBytesInArrayToNumber(questBytes, 8);
        long textEntryIDX = binEditTool.convertFourBytesInArrayToNumber(questBytes, 12);

        return new Quest(indexValue, dungeonLevelSingle, dungeonLevelMulti, dungeonType, questNumber, byteFourValue,
                specialLevel, zeroOne, zeroTwo, mpTriggerFlag, textEntryIDX, binEditTool);
    }

    // Used to initialise the selected quest in the JSON used by Vue.js on the client side
    public Quest getDummyQuest() {
        return new Quest(0, new OneByteSetting(0, 1, 16), 0, 0, 0, 0, 0, 0, 0, 0, 0, binEditTool);
    }
}
