/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.txt for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */
package org.d1ablo.ode.arraytool;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

/**
 * Contains helpful static array-related methods.
 */
public class ArrayTool {

    private static Random random = new SecureRandom();

    /**
     * Replace the RNG set with a new RNG.
     * @param random the new RNG to set
     */
    public static void overrideRNG(Random random) {
        ArrayTool.random = random;
    }

    /**
     * Get an array of the specified length, with pseudo-random bytes in it.
     *
     * NB: The quality of the randomness is determined by the RNG set in the array tool.
     * The quality of the {@link Random} generator is low. {@link SecureRandom} produces more randomness.
     *
     * @param length the number of bytes that should be in the array
     * @return the new byte array
     */
    public static byte[] getRandomByteArray(int length) {
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        return bytes;
    }

    /**
     * Write a byte value to an array, starting at the specified index and repeating for a certain number of bytes.
     * @param array the array to write to
     * @param firstIndex the first index to write to
     * @param count write this many bytes
     * @param value set this byte value each time
     * @throws IllegalArgumentException thrown if the firstIndex or count parameters are bad
     */
    public static void writeBytes(byte[] array, int firstIndex, int count, byte value) {
        if(firstIndex > array.length || firstIndex < 0) {
            throw new IllegalArgumentException(new IndexOutOfBoundsException("First index " + firstIndex + " OOB"));
        }
        int lastIndex = firstIndex + count;
        if(lastIndex > array.length || lastIndex < 0) {
            throw new IllegalArgumentException(new IndexOutOfBoundsException("Last index " + lastIndex + " OOB"));
        }
        Arrays.fill(array, firstIndex, lastIndex, value);
    }

    /**
     * Write zero-valued bytes to an array, starting at a specified index and repeating for a certain number of bytes.
     * @param array the array to write to
     * @param firstIndex the first index to write to
     * @param numberOfZeros write this many zeros
     * @throws IllegalArgumentException thrown if the firstIndex or count parameters are bad
     */
    public static void writeZeros(byte[] array, int firstIndex, int numberOfZeros) {
        writeBytes(array, firstIndex, numberOfZeros, (byte) 0);
    }

    /**
     * Copy the specified byte array to a new byte array.
     * @param array the byte array to copy
     * @return the new byte array
     */
    public static byte[] copyArray(byte[] array) {
        return Arrays.copyOf(array, array.length);
    }

    /**
     * Create a byte array from integers.
     * Note that each integer is cast to a byte, so 255 -> -1, 0 -> 0, etc, due to "Two's complement" system used.
     * @param integers the integers to use to create the array
     * @return the new byte array
     */
    public static byte[] getArrayFromIntegers(int... integers) {
        byte[] bytes = new byte[integers.length];
        for(int i = 0; i < integers.length; i++) {
            bytes[i] = (byte) integers[i];
        }
        return bytes;
    }
}
