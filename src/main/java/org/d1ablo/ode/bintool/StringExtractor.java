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

import org.d1ablo.ode.bintool.rw.Reader;

import java.io.IOException;

/**
 * A helper for obtaining strings, given the position of the first character of the string in the binary.
 */
public class StringExtractor {

	private final Reader reader;

	/**
	 * Construct a new instance of {@link StringExtractor}.
	 * @param reader a reader
	 */
	public StringExtractor(Reader reader){
		this.reader = reader;
	}

	/**
	 * Get the string stored within the binary associated with the reader, starting at the specified index (pointer)
	 * @param pointer the specified index/binary offset to start at
	 * @return the string extracted from the binary
	 */
	public String getNameUsingPointer(long pointer){
		checkPointerArgumentValid(pointer);

		long pos = pointer;
		int nextIndex = 0;
		byte[] byteBuffer = new byte[1024];
		while (true) {
			byte readByte = reader.readByte(pos);
			if(isByteCodingForNullChar(readByte)) {
				break;
			}
			byteBuffer[nextIndex] = readByte;
			nextIndex++;
			pos++;
			if(pos == 1024) {
				throw new IllegalStateException("Tried to extract a name > 1024 chars!");
			}
		}
		return new String(byteBuffer, 0, nextIndex);
	}

	private boolean isByteCodingForNullChar(byte readByte) {
		return readByte == 0;
	}

	private void checkPointerArgumentValid(long pointer) {
		long maxValue = getMaxPointerValue();
		if(pointer < 0 || pointer > maxValue) {
			throw new IllegalArgumentException("Pointer value must be within correct file bounds");
		}
	}

	private long getMaxPointerValue() {
		long maxValue;
		try {
			maxValue = reader.length() - 1;
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		return maxValue;
	}
}
