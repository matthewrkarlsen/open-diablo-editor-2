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

import org.d1ablo.ode.knowledge.DiabloFilePositions;

/**
 * Converts bytes to numbers and numbers to bytes.
 * Primarily intended for ODE-based applications.
 */
public class BinEditTool {

	public static final long MAX_UNSIGNED_LONG_IN_FOUR_BYTES = 4294967295L;
	public static final int MAX_UNSIGNED_INT_IN_TWO_BYTES = 65535;

	public BinEditTool(){

	}

	/**
	 * Convert a long number to four bytes.
	 * @param numberToConvert the number that needs to be converted to bytes
	 * @return the four bytes
	 */
	public byte[] convertLongToFourBytes(long numberToConvert) {
		if(numberToConvert > MAX_UNSIGNED_LONG_IN_FOUR_BYTES) {
			throw new IllegalStateException("Number to convert (" + numberToConvert + ") will not fit in four bytes");
		}
		byte[] bytes = new byte[4];
		bytes[0] = (byte)(numberToConvert);
		bytes[1] = (byte)(numberToConvert >>>  8);
		bytes[2] = (byte)(numberToConvert >>> 16);
		bytes[3] = (byte)(numberToConvert >>> 24);
		return bytes;
	}

	public byte[] convertIntToTwoBytes(int numberToConvert) {
		if(numberToConvert > MAX_UNSIGNED_INT_IN_TWO_BYTES) {
			throw new IllegalStateException("Number to convert (" + numberToConvert + ") will not fit in two bytes");
		}
		byte[] bytes = new byte[2];
		bytes[0] = (byte)(numberToConvert);
		bytes[1] = (byte)(numberToConvert >>>  8);
		return bytes;
	}

	/**
	 * Writes a long number in to a byte array, starting at the specified first destination byte.
	 * @param numberToConvert the long number to write in to the array
	 * @param destinationArray the target array to write the new bytes to
	 * @param firstDestinationByte the first byte to write to in the target array
	 */
	public void setLongAsFourBytes(long numberToConvert, byte[] destinationArray, int firstDestinationByte){
		byte[] bytesToSet = convertLongToFourBytes(numberToConvert);
		System.arraycopy(bytesToSet, 0, destinationArray, firstDestinationByte, bytesToSet.length);
	}

	/**
	 * Writes an int number in to a byte array, starting at the specified first destination byte.
	 * @param numberToConvert the int number to write in to the array
	 * @param destinationArray the target array to write the new bytes to
	 * @param firstDestinationByte the first byte to write to in the target array
	 */
	public void setIntAsTwoBytes(int numberToConvert, byte[] destinationArray, int firstDestinationByte){
		byte[] bytesToSet = convertIntToTwoBytes(numberToConvert);
		System.arraycopy(bytesToSet, 0, destinationArray, firstDestinationByte, bytesToSet.length);
	}

	/**
	 * Writes a 'pointer' to the specified byte array.
	 * @param numberToConvert the pointer value LESS the base pointer offset
	 * @param destinationArray the target array in which to write the pointer bytes
	 * @param firstDestinationByte the first byte to write to in the target array
	 */
	public void setPointerAsFourBytes(long numberToConvert, byte[] destinationArray, int firstDestinationByte){
		long numberToConvertModified = numberToConvert + DiabloFilePositions.DIABLO_POINTERS_OFFSET;
		byte[] bytesToSet = this.convertLongToFourBytes(numberToConvertModified);
		System.arraycopy(bytesToSet, 0, destinationArray, firstDestinationByte, bytesToSet.length);
	}
	
	/**
	 * Converts four bytes on the specified array to a long number.
	 * The particular bytes in question are determined by the offset
	 * (from the start of the array).
	 *
	 * Assumes numbers in the array are in little-endian format (least significant byte first).
	 * 
	 * @param holdingArray : the array to get the four bytes from
	 * @param offset : the offset of the bytes on the array
	 * @return a long value converted from the four bytes
	 */
	public long convertFourBytesInArrayToNumber(byte[] holdingArray, int offset){
		byte[] relevantBytes = new byte[4];
		System.arraycopy(holdingArray, offset, relevantBytes, 0, 4);
		return convertFourBytesToLongWithLSBFirst(relevantBytes);
	}

	/**
	 * Converts four bytes to a long number. Bytes must be in little endian format (least significant byte first).
	 * @param relevantBytes the bytes to convert
	 * @return the resulting long number
	 */
	public long convertFourBytesToLongWithLSBFirst(byte[] relevantBytes) {
		int lsbIntVal0 = this.convertByteToUnsignedInt(relevantBytes[0]);
		int intVal1 = this.convertByteToUnsignedInt(relevantBytes[1]);
		int intVal2 = this.convertByteToUnsignedInt(relevantBytes[2]);
		int msbIntVal3 = this.convertByteToUnsignedInt(relevantBytes[3]);
		return (256L * 256L * 256L * msbIntVal3) + (256L * 256L * intVal2) + (256L * intVal1) + lsbIntVal0;
	}

	/**
	 * Convert two bytes to an integer with unsigned-equivalent-value.
	 * @param leastSignificantByte the byte 'worth the least' as part of the final int
	 * @param mostSignificantByte the byte 'worth the most' as part of the final int
	 * @return the integer form with unsigned-equivalent-value
	 */
	public int convertTwoBytesToIntWithLSBFirst(byte leastSignificantByte, byte mostSignificantByte){
		int lsbInt = this.convertByteToUnsignedInt(leastSignificantByte);
		int msbInt = this.convertByteToUnsignedInt(mostSignificantByte);
		return (msbInt * 256) + lsbInt;
	}

	/**
	 * Convert four bytes to a pointer (less base pointer offset).
	 * @param holdingArray the array containing the pointer bytes
	 * @param holdingArrayOffset the offset at which the bytes are to be obtained
	 * @return the pointer value (less base pointer offset)
	 */
	public long convertFourBytesToOffset(byte[] holdingArray, int holdingArrayOffset){
		long value = this.convertFourBytesInArrayToNumber(holdingArray, holdingArrayOffset);
		return value - DiabloFilePositions.DIABLO_POINTERS_OFFSET;
	}

	/**
	 * When a byte (00 to FF) is read in with Java, Java uses a signed integer representation (-128 to 127).
	 * This needs to be converted to an 'unsigned equivalent' value to make sense in most contexts within this program.
	 * @param b the byte to convert to an 'unsigned equivalent' int
	 * @return the 'unsigned equivalent' int
	 */
	public int convertByteToUnsignedInt(byte b) {
		return (int) b & 0xFF;
	}

	/**
	 * NB: The int converted is the 'unsigned' value, though clearly Java does not support unsigned int fully.
	 */
	public byte convertIntToByte(int i) {
		return (byte) i;
	}

	/**
	 * Convert a byte to an 8-bit binary string.
	 * @param b the byte to convert
	 * @return the 8-bit binary string
	 */
	public String convertByteToBinaryString(byte b){
		int i = this.convertByteToUnsignedInt(b);
		String s = Integer.toBinaryString(i);
		return String.format("%8s", s).replace(' ', '0');
	}
}
