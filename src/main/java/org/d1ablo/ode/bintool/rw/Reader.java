/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.txt for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */
package org.d1ablo.ode.bintool.rw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Reader {

    private final static Logger logger = LoggerFactory.getLogger(ReaderWriter.class);

    private final RandomAccessFile raf;

    /**
     * Construct a new read-only {@link ReaderWriter}.
     * @param raf the random access file to use for file access
     */
    public Reader(RandomAccessFile raf){
        this.raf = raf;
    }

    /**
     * Obtain the signed int value representing the byte at the current position of the 'read/write head'
     * @return the signed int value representing the byte at the current position of the 'read/write head'
     */
    public int read(long position){
        int read;
        try {
            seekWithinLimits(position);
            read = raf.read();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return read;
    }

    /**
     * Obtain the byte at the current position of the 'read/write head'
     * @return the byte at the current position of the 'read/write head'
     */
    public byte readByte(long position){
        byte read;
        try {
            seekWithinLimits(position);
            read = raf.readByte();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return read;
    }

    /**
     * Obtain a specified number of bytes, starting at the current position of the 'read/write head'
     * @return the specified number of bytes, starting at the current position of the 'read/write head'
     */
    public byte[] readBytes(long position, int numBytes){
        byte[] bytes = new byte[numBytes];
        try {
            seekWithinLimits(position);
            if(position + (numBytes - 1) > raf.length()) {
                throw new IllegalStateException("Attempt to read bytes greater than max file length");
            }
            raf.read(bytes);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return bytes;
    }

    private void seekWithinLimits(long position) throws IOException {
        if (position > raf.length() - 1) {
            throw new IllegalStateException("Attempt to read byte outside the max file length, at " + position);
        }
        raf.seek(position);
    }

    public long length() throws IOException {
        return raf.length();
    }
}
