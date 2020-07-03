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

import org.d1ablo.ode.bintool.StringExtractor;
import org.d1ablo.ode.types.Shrine;

public class ShrineFactory {

    private StringExtractor stringExtractor;

    public ShrineFactory() {

    }

    public void setStringExtractor(StringExtractor stringExtractor) {
        this.stringExtractor = stringExtractor;
    }

    public Shrine constructShrine(int i, long[] shrinePointers, int[] minShrineLevels,
                                  int[] maxShrineLevels, int[] gameTypesInWhichPresent) {
        long shrinePointer = shrinePointers[i];
        int minShrineLevel = minShrineLevels[i];
        int maxShrineLevel = maxShrineLevels[i];
        int gameTypesInWhichPresentInt = gameTypesInWhichPresent[i];
        String shrineName = stringExtractor.getNameUsingPointer(shrinePointer);
        return new Shrine(i, shrineName, shrinePointer, minShrineLevel, maxShrineLevel, gameTypesInWhichPresentInt);
    }

    // Used to initialise the selected shrine in the JSON used by Vue.js on the client side
    public Shrine getDummyShrine() {
        return new Shrine(0, "unset", 0, 0, 0, 0);
    }
}
