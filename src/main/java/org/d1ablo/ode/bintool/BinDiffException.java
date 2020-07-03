/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.txt for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */
package org.d1ablo.ode.bintool;

import java.io.IOException;

/**
 * Thrown when an unexpected issue occurs during extraction of binary differences when comparing two binary files.
 * Situations include no file existing at the specified path, a directory file existing at the specified path, or
 * the two files having a different number of bytes (current implementation is for equal size files only).
 */
public class BinDiffException extends RuntimeException {

    public BinDiffException(IOException e) {
        super(e);
    }

    public BinDiffException(String msg) {
        super(msg);
    }
}
