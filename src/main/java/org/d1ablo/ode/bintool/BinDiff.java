/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.md for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */
package org.d1ablo.ode.bintool;

/**
 * A difference between two equal-length binary files at a specific index.
 */
public class BinDiff {

    //The index at which the difference occurs
    private final long index;

    private final int fileOneByteValue;
    private final int fileTwoByteValue;

    /**
     * Construct a new instance.
     * @param index the zero-indexed byte location
     * @param fileOneByteValue the 'unsigned' value at that location for file one
     * @param fileTwoByteValue the 'unsigned' value at that location for file two
     */
    public BinDiff(long index, int fileOneByteValue, int fileTwoByteValue) {
        this.index = index;
        this.fileOneByteValue = fileOneByteValue;
        this.fileTwoByteValue = fileTwoByteValue;
    }

    public long getIndex() {
        return index;
    }

    public int getFileOneByteValue() {
        return fileOneByteValue;
    }

    public int getFileTwoByteValue() {
        return fileTwoByteValue;
    }
}
