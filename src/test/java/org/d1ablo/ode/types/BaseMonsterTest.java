package org.d1ablo.ode.types;

import junit.framework.TestCase;
import org.d1ablo.ode.bintool.BinEditTool;
import org.d1ablo.ode.bintool.StringExtractor;
import org.d1ablo.ode.bintool.rw.Reader;
import org.d1ablo.ode.bintool.rw.ReaderWriter;
import org.d1ablo.ode.factories.BaseMonsterFactory;
import org.d1ablo.ode.knowledge.DiabloFilePositions;
import org.d1ablo.ode.stores.BaseMonsterStore;
import org.d1ablo.ode.types.bytes.MonsterAsBytes;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;

public class BaseMonsterTest extends TestCase {

	protected byte[][] readInMonsterBytes;
	protected byte[] readInMonsterActivationBytes;
	
	protected BaseMonsterStore bms;
	
	public void readInAllMonsterBytes(Reader reader) {
		readInMonsterBytes = new byte[DiabloFilePositions.NUMBER_OF_BASE_MONSTERS][DiabloFilePositions.BASE_MONSTER_LENGTH_IN_BYTES];
		long pos = DiabloFilePositions.MONSTER_ACTIVATION_BYTES_OFFSET;
		readInMonsterActivationBytes = reader.readBytes(pos, DiabloFilePositions.NUMBER_OF_BASE_MONSTERS);
		pos = DiabloFilePositions.BASE_MONSTERS_OFFSET;
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_BASE_MONSTERS; i++){
			readInMonsterBytes[i] = reader.readBytes(pos, DiabloFilePositions.BASE_MONSTER_LENGTH_IN_BYTES);
			pos = pos + DiabloFilePositions.BASE_MONSTER_LENGTH_IN_BYTES;
		}
	}
	
	@Override
	protected void setUp() throws IOException {
		RandomAccessFile randomAccessFile;
		try {
			randomAccessFile = new RandomAccessFile("binaries/Diablo.exe", "r");
		} catch (FileNotFoundException e) {
			throw new IllegalStateException(e);
		}
		ReaderWriter rw = new ReaderWriter(randomAccessFile);
		bms = new BaseMonsterStore(new ArrayList<>(), new BaseMonsterFactory(new BinEditTool()));
		StringExtractor stringExtractor = new StringExtractor(rw.getReader());
		bms.initialiseUsingBinary(rw, stringExtractor);
		bms.readInMonsters();
		readInAllMonsterBytes(rw.getReader());
	}

	/**
	 * This tests to ensure that getMonsterAsBytes() 
	 * returns the correct sequence of bytes
	 * for each monster.
	 */
	@Test
	public void testGetMonsterAsBytes(){
		byte[] reconstructedActivationBytes = new byte[DiabloFilePositions.NUMBER_OF_BASE_MONSTERS];
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_BASE_MONSTERS; i++){
			MonsterAsBytes mab = bms.getMonsterAsBytes(i);
			reconstructedActivationBytes[i] = mab.getEnabledByte();
			byte[] monsterAsBytes = mab.getMainBytes();
			String monsterAsString = Arrays.toString(monsterAsBytes);
			String originalString = Arrays.toString(readInMonsterBytes[i]);
			assertEquals(originalString, monsterAsString);
		}
		
		//Now compare original activation bytes to reconstructed ones
		String rabString = Arrays.toString(reconstructedActivationBytes);
		String oabString = Arrays.toString(readInMonsterActivationBytes);
		assertEquals(oabString, rabString);
	}
}
