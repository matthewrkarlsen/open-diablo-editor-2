package org.d1ablo.ode.bintool;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.security.SecureRandom;
import java.util.Random;

import static org.junit.Assert.*;

public class FileCopierTest {

    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void givenOneTemporaryFile_whenCopyFile_thenCopySuccess() throws IOException {
        byte[] bytes1 = new byte[1024];
        Random random = new SecureRandom();
        random.nextBytes(bytes1);
        File testFileOne = temporaryFolder.newFile("TestFileOne.bin");
        FileUtils.writeByteArrayToFile(testFileOne, bytes1);
        File testFileTwo = new File(temporaryFolder.getRoot(), "TestFileTwo.bin");
        assertFalse(testFileTwo.exists());
        FileCopier.copyFile(testFileOne.getPath(), testFileTwo.getPath());
        assertTrue(testFileTwo.exists());
        byte[] bytes2 = FileUtils.readFileToByteArray(testFileTwo);
        assertArrayEquals(bytes1, bytes2);
    }

    @Test(expected = FileAlreadyExistsException.class)
    public void givenTargetFileAlreadyPresent_whenCopyFile_thenException() throws IOException {
        byte[] bytes1 = new byte[1024];
        Random random = new SecureRandom();
        random.nextBytes(bytes1);
        File testFileOne = temporaryFolder.newFile("TestFileOne.bin");
        FileUtils.writeByteArrayToFile(testFileOne, bytes1);
        File testFileTwo = temporaryFolder.newFile("TestFileTwo.bin");
        assertTrue(testFileTwo.exists());
        FileCopier.copyFile(testFileOne.getPath(), testFileTwo.getPath());
    }
}