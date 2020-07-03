package org.d1ablo.ode.bintool;

import org.d1ablo.ode.arraytool.ArrayTool;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class BinEditToolTest {

    @Test
    public void givenValue0_whenConvertLongToFourBytes_thenBytesAsExpected() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte[] expectedBytes = new byte[4];
        long numberToConvert = 0L;

        //When
        byte[] actualBytes = binEditTool.convertLongToFourBytes(numberToConvert);

        //Then
        assertArrayEquals(expectedBytes, actualBytes);
    }

    @Test(expected = IllegalStateException.class)
    public void givenValue4294967296L_whenConvertLongToFourBytes_thenISE() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        long numberToConvert = 4294967296L;

        //When
        binEditTool.convertLongToFourBytes(numberToConvert);

        //Then IllegalStateException expected
    }

    @Test
    public void givenValue255_whenConvertLongToFourBytes_thenBytesAsExpected() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte[] expectedBytes = {(byte) 255, (byte) 0, (byte) 0, (byte) 0};
        long numberToConvert = 255L;

        //When
        byte[] actualBytes = binEditTool.convertLongToFourBytes(numberToConvert);

        //Then
        assertArrayEquals(expectedBytes, actualBytes);
    }

    @Test
    public void givenValue65280_whenConvertLongToFourBytes_thenBytesAsExpected() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte[] expectedBytes = {(byte) 0, (byte) 255, (byte) 0, (byte) 0};
        long numberToConvert = 65280L;

        //When
        byte[] actualBytes = binEditTool.convertLongToFourBytes(numberToConvert);

        //Then
        assertArrayEquals(expectedBytes, actualBytes);
    }

    @Test
    public void givenValue16711680_whenConvertLongToFourBytes_thenBytesAsExpected() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte[] expectedBytes = {(byte) 0, (byte) 0, (byte) 255, (byte) 0};
        int numberToConvert = 16711680;

        //When
        byte[] actualBytes = binEditTool.convertLongToFourBytes(numberToConvert);

        //Then
        assertArrayEquals(expectedBytes, actualBytes);
    }

    @Test
    public void givenValue4278190080_whenConvertLongToFourBytes_thenBytesAsExpected() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte[] expectedBytes = {(byte) 0, (byte) 0, (byte) 0, (byte) 255};
        long numberToConvert = 4278190080L;

        //When
        byte[] actualBytes = binEditTool.convertLongToFourBytes(numberToConvert);

        //Then
        assertArrayEquals(expectedBytes, actualBytes);
    }

    @Test
    public void givenValue4294967295_whenConvertLongToFourBytes_thenBytesAsExpected() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte[] expectedBytes = {(byte) 255, (byte) 255, (byte) 255, (byte) 255};
        long numberToConvert = 4294967295L;

        //When
        byte[] actualBytes = binEditTool.convertLongToFourBytes(numberToConvert);

        //Then
        assertArrayEquals(expectedBytes, actualBytes);
    }

    @Test
    public void givenValue0_whenConvertIntToTwoBytes_thenBytesAsExpected() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte[] expectedBytes = {(byte) 0, (byte) 0};

        //When
        byte[] actualBytes = binEditTool.convertIntToTwoBytes(0);

        //Then
        assertArrayEquals(expectedBytes, actualBytes);
    }

    @Test(expected = IllegalStateException.class)
    public void givenValue65536_whenConvertIntToTwoBytes_thenISE() {
        //Given
        BinEditTool binEditTool = new BinEditTool();

        //When
        binEditTool.convertIntToTwoBytes(65536);

        //Then IllegalStateException expected
    }

    @Test
    public void givenValue255_whenConvertIntToTwoBytes_thenBytesAsExpected() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte[] expectedBytes = {(byte) 255, (byte) 0};
        int numberToConvert = 255;

        //When
        byte[] actualBytes = binEditTool.convertIntToTwoBytes(numberToConvert);

        //Then
        assertArrayEquals(expectedBytes, actualBytes);
    }

    @Test
    public void givenValue65280_whenConvertIntToTwoBytes_thenBytesAsExpected() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte[] expectedBytes = {(byte) 0, (byte) 255};
        int numberToConvert = 65280;

        //When
        byte[] actualBytes = binEditTool.convertIntToTwoBytes(numberToConvert);

        //Then
        assertArrayEquals(expectedBytes, actualBytes);
    }

    @Test
    public void givenValue65535_whenConvertIntToTwoBytes_thenBytesAsExpected() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte[] expectedBytes = {(byte) 255, (byte) 255};
        int numberToConvert = 65535;

        //When
        byte[] actualBytes = binEditTool.convertIntToTwoBytes(numberToConvert);

        //Then
        assertArrayEquals(expectedBytes, actualBytes);
    }

    @Test
    public void givenByteArrayWithZerosAtTarget32AndValueToSet4294967295_whenSetLongAsFourBytes_thenArrayAsExpected() {
        //Given
        byte[] bytes = ArrayTool.getRandomByteArray(128);
        ArrayTool.writeZeros(bytes, 32, 4);
        byte[] expected = ArrayTool.copyArray(bytes);
        ArrayTool.writeBytes(expected, 32, 4, (byte) 255);
        BinEditTool binEditTool = new BinEditTool();
        long numberToConvert = 4294967295L;
        int firstDestinationByte = 32;

        //When
        binEditTool.setLongAsFourBytes(numberToConvert, bytes, firstDestinationByte);

        //Then
        assertArrayEquals(expected, bytes);
    }

    @Test
    public void givenZerosAtTargetAndNumber65535ToSet_whenSetIntAsTwoBytes_thenCorrectBytesSetWithCorrectValues() {
        //Given
        byte[] bytes = ArrayTool.getRandomByteArray(128);
        ArrayTool.writeZeros(bytes, 32, 2);
        byte[] expected = ArrayTool.copyArray(bytes);
        ArrayTool.writeBytes(expected, 32, 2, (byte) 255);
        BinEditTool binEditTool = new BinEditTool();
        int numberToConvert = 65535;
        int firstDestinationByte = 32;

        //When
        binEditTool.setIntAsTwoBytes(numberToConvert, bytes, firstDestinationByte);

        //Then
        assertArrayEquals(expected, bytes);
    }

    @Test
    public void givenZeroedTargetAndPointer231ToSet_whenSetPointerAsFourBytes_thenCorrectBytesSetWithCorrectValues() {
        //Given
        byte[] bytes = ArrayTool.getRandomByteArray(128);
        ArrayTool.writeZeros(bytes, 32, 4);
        byte[] expected = ArrayTool.copyArray(bytes);
        byte[] overwriteBytes = {(byte) 231, (byte) 34, (byte) 64, (byte) 0};
        System.arraycopy(overwriteBytes, 0, expected, 32, overwriteBytes.length);
        BinEditTool binEditTool = new BinEditTool();
        long numberToConvert = 231L;
        int firstDestinationByte = 32;

        //When
        binEditTool.setPointerAsFourBytes(numberToConvert, bytes, firstDestinationByte);

        //Then
        assertArrayEquals(expected, bytes);
    }

    @Test
    public void givenFourMaxValueBytes_whenConvertFourBytesToNumber_thenValueIs4294967295L() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte[] fourBytes = ArrayTool.getArrayFromIntegers(255, 255, 255, 255);

        //When
        long longNum = binEditTool.convertFourBytesToLongWithLSBFirst(fourBytes);

        //Then
        assertEquals(4294967295L, longNum);
    }

    @Test
    public void givenFourMinValueBytes_whenConvertFourBytesToNumber_thenValueIs0L() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte[] fourBytes = ArrayTool.getArrayFromIntegers(0, 0, 0, 0);

        //When
        long longNum = binEditTool.convertFourBytesToLongWithLSBFirst(fourBytes);

        //Then
        assertEquals(0L, longNum);
    }

    @Test
    public void givenLSBAtMaxAndAllOtherBytesMin_whenConvertFourBytesToNumber_thenValueIs255L() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte[] fourBytes = ArrayTool.getArrayFromIntegers(255, 0, 0, 0);

        //When
        long longNum = binEditTool.convertFourBytesToLongWithLSBFirst(fourBytes);

        //Then
        assertEquals(255L, longNum);
    }

    @Test
    public void given2ndByteAtMaxAndAllOtherBytesMin_whenConvertFourBytesToNumber_thenValueIs65280L() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte[] fourBytes = ArrayTool.getArrayFromIntegers(0, 255, 0, 0);

        //When
        long longNum = binEditTool.convertFourBytesToLongWithLSBFirst(fourBytes);

        //Then
        assertEquals(65280L, longNum);
    }

    @Test
    public void given3rdByteAtMaxAndAllOtherBytesMin_whenConvertFourBytesToNumber_thenValueIs16711680L() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte[] fourBytes = ArrayTool.getArrayFromIntegers(0, 0, 255, 0);

        //When
        long longNum = binEditTool.convertFourBytesToLongWithLSBFirst(fourBytes);

        //Then
        assertEquals(16711680L, longNum);
    }

    @Test
    public void givenMSBByteAtMaxAndAllOtherBytesAtMin_whenConvertFourBytesToNumber_thenValueIs4278190080L() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte[] fourBytes = ArrayTool.getArrayFromIntegers(0, 0, 0, 255);

        //When
        long longNum = binEditTool.convertFourBytesToLongWithLSBFirst(fourBytes);

        //Then
        assertEquals(4278190080L, longNum);
    }

    @Test
    public void givenBytesFromIntegers0And0_whenConvertTwoBytesToInt_thenValueIs0() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte b1 = (byte) 0;
        byte b2 = (byte) 0;

        //When
        int result = binEditTool.convertTwoBytesToIntWithLSBFirst(b1, b2);

        //Then
        assertEquals(0, result);
    }

    @Test
    public void givenBytesFromIntegers255And0_whenConvertTwoBytesToInt_thenValueIs255() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte b1 = (byte) 255;
        byte b2 = (byte) 0;

        //When
        int result = binEditTool.convertTwoBytesToIntWithLSBFirst(b1, b2);

        //Then
        assertEquals(255, result);
    }

    @Test
    public void givenBytesFromIntegers0And255_whenConvertTwoBytesToInt_thenValueIs65280() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte b1 = (byte) 0;
        byte b2 = (byte) 255;

        //When
        int result = binEditTool.convertTwoBytesToIntWithLSBFirst(b1, b2);

        //Then
        assertEquals(65280, result);
    }

    @Test
    public void givenBytesFromIntegers255And255_whenConvertTwoBytesToInt_thenValueIs65535() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte b1 = (byte) 255;
        byte b2 = (byte) 255;

        //When
        int result = binEditTool.convertTwoBytesToIntWithLSBFirst(b1, b2);

        //Then
        assertEquals(65535, result);
    }

    @Test
    public void givenBytesFromIntegers111And111_whenConvertTwoBytesToInt_thenValueIs28527() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte b1 = (byte) 111;
        byte b2 = (byte) 111;

        //When
        int result = binEditTool.convertTwoBytesToIntWithLSBFirst(b1, b2);

        //Then
        assertEquals(28527, result);
    }

    @Test
    public void givenBytesFromIntegers111And215And73And0_whenConvertFourBytesToOffset_thenOffsetIs636271() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte[] bytes = {(byte) 111, (byte) 215, (byte) 73, (byte) 0};

        //When
        long offset = binEditTool.convertFourBytesToOffset(bytes, 0);

        //Then
        assertEquals(636271, offset);
    }

    @Test
    public void givenBytesFromIntegers0And215And73And0_whenConvertFourBytesToOffset_thenOffsetIs636160() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte[] bytes2 = {(byte) 0, (byte) 215, (byte) 73, (byte) 0};

        //When
        long offset2 = binEditTool.convertFourBytesToOffset(bytes2, 0);

        //Then
        assertEquals(636160, offset2);
    }

    @Test
    public void givenByteMinusOne_whenConvertByteToUnsignedInt_thenValueIs255() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte b1 = (byte) 255; //b1 becomes -1

        //When
        int actual = binEditTool.convertByteToUnsignedInt(b1);

        //Then
        assertEquals(255, actual);
    }

    @Test
    public void givenByteMinus56_whenConvertByteToUnsignedInt_thenValueIs200() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte b1 = (byte) 200; //b1 becomes -56

        //When
        int actual = binEditTool.convertByteToUnsignedInt(b1);

        //Then
        assertEquals(200, actual);
    }

    @Test
    public void givenByteZero_whenConvertByteToUnsignedInt_thenValueIsZero() {
        //Given
        BinEditTool binEditTool = new BinEditTool();
        byte b1 = (byte) 0;

        //When
        int actual = binEditTool.convertByteToUnsignedInt(b1);

        //Then
        assertEquals(0, actual);
    }

    @Test
    public void givenIntegerValue255_whenConvertIntToByte_thenReturnsByteValueMinusOne() {
        //Given
        BinEditTool binEditTool = new BinEditTool();

        //When
        byte result = binEditTool.convertIntToByte(255);

        //Then
        assertEquals(Byte.parseByte("-1"), result);
    }

    @Test
    public void givenIntegerValue200_whenConvertIntToByte_thenReturnsByteValueMinus56() {
        //Given
        BinEditTool binEditTool = new BinEditTool();

        //When
        byte result = binEditTool.convertIntToByte(200);

        //Then
        assertEquals(Byte.parseByte("-56"), result);
    }

    @Test
    public void givenIntegerValue255AsByte_whenConvertByteToBinaryString_thenReturnsString11111111() {
        //Given
        BinEditTool binEditTool = new BinEditTool();

        //When
        String byteString = binEditTool.convertByteToBinaryString((byte) 255);

        //Then
        assertEquals("11111111", byteString);
    }

    @Test
    public void givenIntegerValue0AsByte_whenConvertByteToBinaryString_thenReturnsString00000000() {
        //Given
        BinEditTool binEditTool = new BinEditTool();

        //When
        String byteString = binEditTool.convertByteToBinaryString((byte) 0);

        //Then
        assertEquals("00000000", byteString);
    }

    @Test
    public void givenIntegerValue15AsByte_whenConvertByteToBinaryString_thenReturnsString00001111() {
        //Given
        BinEditTool binEditTool = new BinEditTool();

        //When
        String byteString = binEditTool.convertByteToBinaryString((byte) 15);

        //Then
        assertEquals("00001111", byteString);
    }
}