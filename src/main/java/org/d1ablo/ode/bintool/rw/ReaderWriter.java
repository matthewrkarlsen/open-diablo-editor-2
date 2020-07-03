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

/**
 * Responsible for random access reads and writes to an associated binary file.
 */
public class ReaderWriter {
	
	private final static Logger logger = LoggerFactory.getLogger(ReaderWriter.class);

	private final RandomAccessFile raf;

	private final Reader reader;

	/**
	 * Construct a new {@link ReaderWriter}.
	 * @param raf the random access file to use for file access
	 */
	public ReaderWriter(RandomAccessFile raf){
		this.raf = raf;
		this.reader = new Reader(raf);
	}

	/**
	 * Obtain the signed int value representing the byte at the current position of the 'read/write head'
	 * @return the signed int value representing the byte at the current position of the 'read/write head'
	 */
	public int read(long position){
		return reader.read(position);
	}

	/**
	 * Obtain the byte at the current position of the 'read/write head'
	 * @return the byte at the current position of the 'read/write head'
	 */
	public byte readByte(long position){
		return reader.readByte(position);
	}

	/**
	 * Obtain a specified number of bytes, starting at the current position of the 'read/write head'
	 * @return the specified number of bytes, starting at the current position of the 'read/write head'
	 */
	public byte[] readBytes(long position, int numBytes){
		return reader.readBytes(position, numBytes);
	}

	/**
	 * Write the supplied bytes to the file associated with the ReaderWriter, starting at the specified position in the
	 * file.
	 *
	 * @param bytes the bytes to write
	 * @param pos the initial position of the 'read-write head'.
	 */
	public void writeBytes(byte[] bytes, long pos){
		logger.trace("Writing bytes to " + pos);
		try {
			raf.seek(pos);
			raf.write(bytes);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Write the supplied byte to the file associated with the ReaderWriter, at the specified position in the file.
	 * @param b the byte to write
	 * @param pos the specified position in the file at which to write the byte
	 */
	public void writeByte(byte b, long pos){
		logger.trace("Writing byte to " + pos);
		try {
			raf.seek(pos);
			raf.write(b);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Obtain the {@link Reader} nested within this {@link ReaderWriter}.
	 * @return the reader
	 */
	public Reader getReader() {
		return reader;
	}
}
