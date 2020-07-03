package org.d1ablo.ode.bintool.rw;

import org.apache.commons.io.FileUtils;
import org.d1ablo.ode.bintool.BinEditTool;
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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * NB: Since {@link Reader} essentially wraps a RandomAccessFile, some of
 * the testing here may be a bit excessive.
 */
public class ReaderTest {

    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void givenTemporaryFile_whenReadEachByteAsInt_thenEachIntRetrievedCorrectly() throws IOException {
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
        Reader reader = new Reader(randomAccessFile);
        BinEditTool binEditTool = new BinEditTool();
        for(int i = 0; i < bytes.length; i++) {
            int readInt = reader.read(i);
            int arrayInt = binEditTool.convertByteToUnsignedInt(bytes[i]);
            assertEquals(arrayInt, readInt);
        }
    }

    @Test(expected = IllegalStateException.class)
    public void givenTemporaryFile_whenReadWithNegativeIndex_thenIllegalStateException() throws IOException {
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
        Reader reader = new Reader(randomAccessFile);
        reader.read(-1);
    }

    @Test(expected = IllegalStateException.class)
    public void givenTemporaryFile_whenReadWithIndexOneGreaterThanFileLength_thenISE() throws IOException {
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
        Reader reader = new Reader(randomAccessFile);
        reader.read(1024);
    }

    @Test
    public void givenTemporaryFile_whenReadEachByte_thenEachByteRetrievedCorrectly() throws IOException {
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
        Reader reader = new Reader(randomAccessFile);
        BinEditTool binEditTool = new BinEditTool();
        for(int i = 0; i < bytes.length; i++) {
            byte readByte = reader.readByte(i);
            assertEquals(bytes[i], readByte);
        }
    }

    @Test(expected = IllegalStateException.class)
    public void givenTemporaryFile_whenReadByteWithNegativeIndex_thenIllegalStateException() throws IOException {
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
        Reader reader = new Reader(randomAccessFile);
        reader.readByte(-1);
    }

    @Test(expected = IllegalStateException.class)
    public void givenTemporaryFile_whenReadByteWithIndexGreaterThanFileLength_thenISE() throws IOException {
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
        Reader reader = new Reader(randomAccessFile);
        reader.readByte(1024);
    }

    @Test
    public void givenTemporaryFile_whenReadMultipleBytes_thenCorrectByteSequenceRetrieved() throws IOException {
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
        Reader reader = new Reader(randomAccessFile);
        for(int i = 0; i < 8; i++) {
            byte[] arrayRead = reader.readBytes(i * 128, 128);
            assertArrayEquals(Arrays.copyOfRange(bytes, i * 128, (i * 128) + 128), arrayRead);
        }
    }

    @Test(expected = IllegalStateException.class)
    public void givenTemporaryFile_whenReadBytesWithNegativeIndex_thenIllegalStateException() throws IOException {
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
        Reader reader = new Reader(randomAccessFile);
        reader.readBytes(-1, 10);
    }

    @Test(expected = IllegalStateException.class)
    public void givenTemporaryFile_whenReadBytesWithIndexGreaterThanFileLength_thenISE() throws IOException {
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
        Reader reader = new Reader(randomAccessFile);
        reader.readBytes(1024, 10);
    }

    @Test(expected = IllegalStateException.class)
    public void givenTemporaryFile_whenReadTooManyBytes_thenIllegalStateException() throws IOException {
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
        Reader reader = new Reader(randomAccessFile);
        reader.readBytes(1023, 10);
    }
}