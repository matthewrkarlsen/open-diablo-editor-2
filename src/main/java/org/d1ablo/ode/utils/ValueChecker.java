/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.txt for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */
package org.d1ablo.ode.utils;

/**
 * Checks that the supplied int or long is withing a specified range (specified by two additional ints or longs
 * respectively).
 */
public class ValueChecker {

    /**
     * Check that a value is within a specific range.
     * @param min the minimum acceptable value (int)
     * @param val the value actually set (int)
     * @param max the maximum acceptable value (int)
     */
    public static void checkRange(int min, int val, int max) {
        if(val < min || val > max){
            throw new IllegalStateException("Value not acceptable: " + min + " to " + max + " (was " + val + ")");
        }
    }

    /**
     * Check that a value is within a specific range.
     * @param min the minimum acceptable value (long)
     * @param val the value actually set (long)
     * @param max the maximum acceptable value (long)
     */
    public static void checkRange(long min, long val, long max) {
        if(val < min || val > max){
            throw new IllegalStateException("Value not acceptable: " + min + " to " + max + " (was " + val + ")");
        }
    }
}
