package org.d1ablo.ode.bintool.rw;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * NB: Since {@link ReaderWriter} essentially wraps a RandomAccessFile, some of
 * the testing here may be a bit excessive.
 */
public class ReaderWriterTest {

    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void givenTemporaryFile_whenWriteMultipleBytes_thenCorrectBytesAreInPlacePostWrite() throws IOException {
        byte[] bytes = new byte[1024];
        Random random = new SecureRandom();
        random.nextBytes(bytes);
        File tempFileOne = temporaryFolder.newFile("TemporaryFileOne.bin");
        FileUtils.writeByteArrayToFile(tempFileOne, bytes);
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(tempFileOne.getPath(), "rw");
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
        ReaderWriter readerWriter = new ReaderWriter(randomAccessFile);
        byte[] bytes2 = new byte[256];
        random.nextBytes(bytes2);
        readerWriter.writeBytes(bytes2, 256);
        byte[] bytes3 = Arrays.copyOf(bytes, bytes.length);
        System.arraycopy(bytes2, 0, bytes3, 256, bytes2.length);
        for(int i = 0; i < bytes.length; i++) {
            byte readByte = readerWriter.readByte(i);
            assertEquals(bytes3[i], readByte);
        }
    }

    @Test
    public void givenTemporaryFile_whenWriteSeveralSeparateBytes_thenFileCorrectlyModified() throws IOException {
        byte[] bytes = new byte[1024];
        Random random = new SecureRandom();
        random.nextBytes(bytes);
        File tempFileOne = temporaryFolder.newFile("TemporaryFileOne.bin");
        FileUtils.writeByteArrayToFile(tempFileOne, bytes);
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(tempFileOne.getPath(), "rw");
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
        ReaderWriter readerWriter = new ReaderWriter(randomAccessFile);
        byte[] bytes2 = new byte[10];
        random.nextBytes(bytes2);
        readerWriter.writeByte(bytes2[0], 24);
        readerWriter.writeByte(bytes2[1], 39);
        readerWriter.writeByte(bytes2[2], 158);
        readerWriter.writeByte(bytes2[3], 241);
        readerWriter.writeByte(bytes2[4], 403);
        readerWriter.writeByte(bytes2[5], 511);
        readerWriter.writeByte(bytes2[6], 675);
        readerWriter.writeByte(bytes2[7], 700);
        readerWriter.writeByte(bytes2[8], 855);
        readerWriter.writeByte(bytes2[9], 957);

        byte b0 = readerWriter.readByte(24);
        assertEquals(bytes2[0], b0);
        byte b1 = readerWriter.readByte(39);
        assertEquals(bytes2[1], b1);
        byte b2 = readerWriter.readByte(158);
        assertEquals(bytes2[2], b2);
        byte b3 = readerWriter.readByte(241);
        assertEquals(bytes2[3], b3);
        byte b4 = readerWriter.readByte(403);
        assertEquals(bytes2[4], b4);
        byte b5 = readerWriter.readByte(511);
        assertEquals(bytes2[5], b5);
        byte b6 = readerWriter.readByte(675);
        assertEquals(bytes2[6], b6);
        byte b7 = readerWriter.readByte(700);
        assertEquals(bytes2[7], b7);
        byte b8 = readerWriter.readByte(855);
        assertEquals(bytes2[8], b8);
        byte b9 = readerWriter.readByte(957);
        assertEquals(bytes2[9], b9);

    }
}