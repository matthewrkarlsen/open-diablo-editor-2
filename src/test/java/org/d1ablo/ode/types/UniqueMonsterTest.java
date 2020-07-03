package org.d1ablo.ode.types;

import junit.framework.TestCase;
import org.d1ablo.ode.bintool.BinEditTool;
import org.d1ablo.ode.bintool.StringExtractor;
import org.d1ablo.ode.bintool.rw.Reader;
import org.d1ablo.ode.bintool.rw.ReaderWriter;
import org.d1ablo.ode.factories.UniqueMonsterFactory;
import org.d1ablo.ode.knowledge.DiabloFilePositions;
import org.d1ablo.ode.stores.UniqueMonsterStore;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;

public class UniqueMonsterTest extends TestCase {

	private byte[][] originalUniqueMonsterBytes;
	
	private UniqueMonsterStore ums;
	
	public void readInUniqueMonsterBytes(Reader reader) {
		originalUniqueMonsterBytes = new byte[DiabloFilePositions.NUMBER_OF_UNIQUE_MONSTERS][DiabloFilePositions.UNIQUE_MONSTER_LENGTH_IN_BYTES];
		long pos = DiabloFilePositions.UNIQUE_MONSTERS_OFFSET;
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_UNIQUE_MONSTERS; i++){
			originalUniqueMonsterBytes[i] = reader.readBytes(pos, DiabloFilePositions.UNIQUE_MONSTER_LENGTH_IN_BYTES);
			pos = pos + DiabloFilePositions.UNIQUE_MONSTER_LENGTH_IN_BYTES;
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
		ums = new UniqueMonsterStore(new ArrayList<>(), new UniqueMonsterFactory(new BinEditTool()));
		StringExtractor stringExtractor = new StringExtractor(rw.getReader());
		ums.initialiseUsingBinary(rw, stringExtractor);
		ums.readInUniques();
		readInUniqueMonsterBytes(rw.getReader());
	}
	
	@Test
	public void testGetUniqueAsBytes(){
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_UNIQUE_MONSTERS; i++){
			byte[] retrievedBytes = ums.getUniqueAsBytes(i);
			String retrievedString = Arrays.toString(retrievedBytes);
			String originalString = Arrays.toString(originalUniqueMonsterBytes[i]);
			assertEquals(originalString, retrievedString);
		}
	}
}
