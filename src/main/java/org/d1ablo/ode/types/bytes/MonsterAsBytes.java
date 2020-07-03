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
 * A temporary class to hold the relevant bytes for a monster, as required during the load or save process.
 */
public class MonsterAsBytes {

	private final byte[] mainBytes;
	private final byte enabledByte;

	public MonsterAsBytes(byte[] mainBytes, byte enabledByte){
		this.mainBytes = mainBytes;
		this.enabledByte = enabledByte;
	}

	public byte[] getMainBytes(){
		return mainBytes;
	}

	public byte getEnabledByte(){
		return enabledByte;
	}
}
