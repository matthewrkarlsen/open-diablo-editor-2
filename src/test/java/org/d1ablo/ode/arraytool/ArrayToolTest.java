package org.d1ablo.ode.arraytool;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

public class ArrayToolTest {

    /*
     * This test is a bit artificial since I got the correct bytes from running it the first time.
     * Suggestions on how to improve this test are welcome.
     */
    @Test
    public void givenSeed0LSet_whenGetRandomByteArray_thenCertainSequenceOfPseudoRandomBytes() {
        //Given
        Random random = new Random();
        random.setSeed(0L);
        ArrayTool.overrideRNG(random);

        //When
        byte[] bytes = ArrayTool.getRandomByteArray(10);

        //Then
        byte[] expected = {96, -76, 32, -69, 56, 81, -39, -44, 122, -53};
        assertArrayEquals(expected, bytes);
    }

    @Test
    public void givenByteSequence_whenWrite4BytesAt3_thenCorrectBytesModifiedToIntegerValue255AsByte() {
        //Given
        byte[] bytes = {96, -76, 32, -69, 56, 81, -39, -44, 122, -53};

        //When
        ArrayTool.writeBytes(bytes, 3, 4, (byte) 255);

        //Then
        byte[] expected = {96, -76, 32, -1, -1, -1, -1, -44, 122, -53};
        assertArrayEquals(bytes, expected);
    }

    @Test
    public void givenByteSequence_whenWrite2BytesAt8_thenCorrectBytesModifiedToIntegerValue255AsByte() {
        //Given
        byte[] bytes = {96, -76, 32, -69, 56, 81, -39, -44, 122, -53};

        //When
        ArrayTool.writeBytes(bytes, 8, 2, (byte) 253);

        //Then
        byte[] expected = {96, -76, 32, -69, 56, 81, -39, -44, -3, -3};
        assertArrayEquals(bytes, expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenByteSequence_whenWrite10BytesAt8_thenIllegalStateException() {
        //Given
        byte[] bytes = {96, -76, 32, -69, 56, 81, -39, -44, 122, -53};

        //When
        ArrayTool.writeBytes(bytes, 8, 10, (byte) 253);

        //Then ISE
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenByteSequence_whenWrite1BytesAt10_thenIllegalStateException() {
        //Given
        byte[] bytes = {96, -76, 32, -69, 56, 81, -39, -44, 122, -53};

        //When
        ArrayTool.writeBytes(bytes, 10, 1, (byte) 253);

        //Then ISE
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenByteSequence_whenWriteMinus1BytesAt8_thenIllegalStateException() {
        //Given
        byte[] bytes = {96, -76, 32, -69, 56, 81, -39, -44, 122, -53};

        //When
        ArrayTool.writeBytes(bytes, 8, -1, (byte) 253);

        //Then ISE
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenByteSequence_whenWrite1BytesAtMinus1_thenIllegalStateException() {
        //Given
        byte[] bytes = {96, -76, 32, -69, 56, 81, -39, -44, 122, -53};

        //When
        ArrayTool.writeBytes(bytes, -1, 1, (byte) 253);

        //Then ISE
    }

    @Test
    public void givenByteSequence_whenWrite4ZerosAt3_thenCorrectBytesModifiedToIntegerValue255AsByte() {
        //Given
        byte[] bytes = {96, -76, 32, -69, 56, 81, -39, -44, 122, -53};

        //When
        ArrayTool.writeZeros(bytes, 3, 4);

        //Then
        byte[] expected = {96, -76, 32, 0, 0, 0, 0, -44, 122, -53};
        assertArrayEquals(bytes, expected);
    }

    @Test
    public void givenByteSequence_whenWrite2ZerosAt8_thenCorrectBytesModifiedToIntegerValue255AsByte() {
        //Given
        byte[] bytes = {96, -76, 32, -69, 56, 81, -39, -44, 122, -53};

        //When
        ArrayTool.writeZeros(bytes, 8, 2);

        //Then
        byte[] expected = {96, -76, 32, -69, 56, 81, -39, -44, 0, 0};
        assertArrayEquals(bytes, expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenByteSequence_whenWrite10ZerosAt8_thenIllegalStateException() {
        //Given
        byte[] bytes = {96, -76, 32, -69, 56, 81, -39, -44, 122, -53};

        //When
        ArrayTool.writeZeros(bytes, 8, 10);

        //Then ISE
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenByteSequence_whenWrite1ZerosAt10_thenIllegalStateException() {
        //Given
        byte[] bytes = {96, -76, 32, -69, 56, 81, -39, -44, 122, -53};

        //When
        ArrayTool.writeZeros(bytes, 10, 1);

        //Then ISE
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenByteSequence_whenWriteMinus1ZerosAt8_thenIllegalStateException() {
        //Given
        byte[] bytes = {96, -76, 32, -69, 56, 81, -39, -44, 122, -53};

        //When
        ArrayTool.writeZeros(bytes, 8, -1);

        //Then ISE
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenByteSequence_whenWrite1ZeroAtMinus1_thenIllegalStateException() {
        //Given
        byte[] bytes = {96, -76, 32, -69, 56, 81, -39, -44, 122, -53};

        //When
        ArrayTool.writeZeros(bytes, -1, 1);

        //Then ISE
    }

    @Test
    public void givenByteSequence_whenCopy10ByteArray_thenExactCopyMade() {
        //Given
        byte[] bytes = {96, -76, 32, -69, 56, 81, -39, -44, 122, -53};

        //When
        byte[] copy = ArrayTool.copyArray(bytes);

        //Then
        assertArrayEquals(bytes, copy);
    }

    @Test
    public void givenByteSequence_whenCopy0ByteArray_thenExactCopyMade() {
        //Given
        byte[] bytes = new byte[0];

        //When
        byte[] copy = ArrayTool.copyArray(bytes);

        //Then
        assertArrayEquals(bytes, copy);
    }

    @Test
    public void givenIntegers_whenGetArrayFromHighIntegers_thenExpectedBytesProvided() {
        //Given
        int[] ints = {255, 250, 245, 240, 235, 230, 225, 220, 215, 210, 205, 200, 195, 190, 185, 180, 175, 170, 165 };

        //When
        byte[] bytes = ArrayTool.getArrayFromIntegers(ints);

        //Then
        byte[] expected = {-1, -6, -11, -16, -21, -26, -31, -36, -41, -46, -51, -56, -61, -66, -71, -76, -81, -86, -91};
        assertArrayEquals(expected, bytes);
    }

    @Test
    public void givenIntegers_whenGetArrayFromLowIntegers_thenExpectedBytesProvided() {
        //Given
        int[] ints = {0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90};

        //When
        byte[] bytes = ArrayTool.getArrayFromIntegers(ints);

        //Then
        byte[] expected = {0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90};
        assertArrayEquals(expected, bytes);
    }
}