/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.txt for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */
package org.d1ablo.ode.types.bytes;

/**
 * A temporary class to hold the relevant bytes for a shrine, as required during the load or save process.
 */
public class ShrinesAsBytes {

	private final byte[] shrinePointerBytes;
	private final byte[] minShrineLevelBytes;
	private final byte[] maxShrineLevelBytes;
	private final byte[] gameTypesInWhichPresentBytes;

	public ShrinesAsBytes(byte[] shrinePointerBytes,
			byte[] minShrineLevelBytes, byte[] maxShrineLevelBytes,
			byte[] gameTypesInWhichPresentBytes) {
		this.shrinePointerBytes = shrinePointerBytes;
		this.minShrineLevelBytes = minShrineLevelBytes;
		this.maxShrineLevelBytes = maxShrineLevelBytes;
		this.gameTypesInWhichPresentBytes = gameTypesInWhichPresentBytes;
	}

	public byte[] getShrinePointerBytes() {
		return shrinePointerBytes;
	}

	public byte[] getMinShrineLevelBytes() {
		return minShrineLevelBytes;
	}

	public byte[] getMaxShrineLevelBytes() {
		return maxShrineLevelBytes;
	}

	public byte[] getGameTypeBytes() {
		return gameTypesInWhichPresentBytes;
	}

}
