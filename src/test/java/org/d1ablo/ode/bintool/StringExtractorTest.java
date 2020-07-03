package org.d1ablo.ode.bintool;

import org.apache.commons.io.FileUtils;
import org.d1ablo.ode.arraytool.ArrayTool;
import org.d1ablo.ode.bintool.rw.Reader;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class StringExtractorTest {

    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void givenStringOne_whenGetNameUsingPointer_thenCorrectStringRetrieved() throws IOException {
        //Given
        byte[] bytes1 = ArrayTool.getRandomByteArray(4096);
        byte[] testBytes = "TestString\0".getBytes(StandardCharsets.US_ASCII);
        System.arraycopy(testBytes, 0, bytes1, 363, testBytes.length);
        File testFileOne = temporaryFolder.newFile("TestFileOne.bin");
        FileUtils.writeByteArrayToFile(testFileOne, bytes1);
        Reader reader = new Reader(new RandomAccessFile(testFileOne, "r"));
        StringExtractor stringExtractor = new StringExtractor(reader);

        //When
        String name = stringExtractor.getNameUsingPointer(363L);

        //Then
        assertEquals("TestString", name);
    }

    @Test
    public void givenStringTwo_whenGetNameUsingPointer_thenCorrectStringRetrieved() throws IOException {
        //Given
        byte[] bytes1 = ArrayTool.getRandomByteArray(4096);
        byte[] testBytes = "Some/Test/String/Here\0".getBytes(StandardCharsets.US_ASCII);
        System.arraycopy(testBytes, 0, bytes1, 363, testBytes.length);
        File testFileOne = temporaryFolder.newFile("TestFileOne.bin");
        FileUtils.writeByteArrayToFile(testFileOne, bytes1);
        Reader reader = new Reader(new RandomAccessFile(testFileOne, "r"));
        StringExtractor stringExtractor = new StringExtractor(reader);

        //When
        String name = stringExtractor.getNameUsingPointer(363L);

        //Then
        assertEquals("Some/Test/String/Here", name);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNegativePointer_whenGetNameUsingPointer_thenIllegalArgumentException() throws IOException {
        //Given
        byte[] bytes1 = ArrayTool.getRandomByteArray(4096);
        byte[] testBytes = "TestString\0".getBytes(StandardCharsets.US_ASCII);
        System.arraycopy(testBytes, 0, bytes1, 363, testBytes.length);
        File testFileOne = temporaryFolder.newFile("TestFileOne.bin");
        FileUtils.writeByteArrayToFile(testFileOne, bytes1);
        Reader reader = new Reader(new RandomAccessFile(testFileOne, "r"));
        StringExtractor stringExtractor = new StringExtractor(reader);

        //When
        stringExtractor.getNameUsingPointer(-1);

        //Then IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenPointerTooLarge_whenGetNameUsingPointer_thenIllegalArgumentException() throws IOException {
        //Given
        byte[] bytes1 = ArrayTool.getRandomByteArray(4096);
        byte[] testBytes = "TestString\0".getBytes(StandardCharsets.US_ASCII);
        System.arraycopy(testBytes, 0, bytes1, 363, testBytes.length);
        File testFileOne = temporaryFolder.newFile("TestFileOne.bin");
        FileUtils.writeByteArrayToFile(testFileOne, bytes1);
        Reader reader = new Reader(new RandomAccessFile(testFileOne, "r"));
        StringExtractor stringExtractor = new StringExtractor(reader);

        //When
        stringExtractor.getNameUsingPointer(4096);

        //Then IAE
    }

    @Test(expected = IllegalStateException.class)
    public void givenNameTooLarge_whenGetNameUsingPointer_thenIllegalStateException() throws IOException {
        //Given
        byte[] bytes1 = ArrayTool.getRandomByteArray(4096);
        String testString = "A".repeat(1025) + '\0';
        byte[] testBytes = testString.getBytes(StandardCharsets.US_ASCII);
        System.arraycopy(testBytes, 0, bytes1, 363, testBytes.length);
        File testFileOne = temporaryFolder.newFile("TestFileOne.bin");
        FileUtils.writeByteArrayToFile(testFileOne, bytes1);
        Reader reader = new Reader(new RandomAccessFile(testFileOne, "r"));
        StringExtractor stringExtractor = new StringExtractor(reader);

        //When
        stringExtractor.getNameUsingPointer(363);

        //Then ISE
    }
}