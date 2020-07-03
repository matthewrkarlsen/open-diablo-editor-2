package org.d1ablo.ode.types;

import junit.framework.TestCase;
import org.d1ablo.ode.bintool.BinEditTool;
import org.d1ablo.ode.bintool.StringExtractor;
import org.d1ablo.ode.bintool.rw.Reader;
import org.d1ablo.ode.bintool.rw.ReaderWriter;
import org.d1ablo.ode.factories.ShrineFactory;
import org.d1ablo.ode.knowledge.DiabloFilePositions;
import org.d1ablo.ode.stores.ShrinesStore;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;

public class ShrinesStoreTest extends TestCase {

	protected byte[] origShrinePointerBytes;
	protected byte[] origMinShrineLevelBytes;
	protected byte[] origMaxShrineLevelBytes;
	protected byte[] origGameTypesInWhichPresentBytes;
	
	protected ShrinesStore ss;
	
	public void readInAllShrineBytes(Reader reader){

		long pos = DiabloFilePositions.SHRINE_POINTERS_OFFSET;
		origShrinePointerBytes = reader.readBytes(pos, DiabloFilePositions.NUMBER_OF_SHRINES*4);

		pos = DiabloFilePositions.SHRINE_MIN_LEVELS_OFFSET;
		origMinShrineLevelBytes = reader.readBytes(pos, DiabloFilePositions.NUMBER_OF_SHRINES);

		pos = DiabloFilePositions.SHRINE_MAX_LEVELS_OFFSET;
		origMaxShrineLevelBytes = reader.readBytes(pos, DiabloFilePositions.NUMBER_OF_SHRINES);

		pos = DiabloFilePositions.SHRINE_GAME_TYPE_OFFSET;
		origGameTypesInWhichPresentBytes = reader.readBytes(pos, DiabloFilePositions.NUMBER_OF_SHRINES);
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
		ss = new ShrinesStore(new ArrayList<>(), new BinEditTool(), new ShrineFactory());
		StringExtractor stringExtractor = new StringExtractor(rw.getReader());
		ss.initialiseUsingBinary(rw, stringExtractor);
		ss.readInShrines();
		readInAllShrineBytes(rw.getReader());
	}

	@Test
	public void testGetShrinePointerBytes(){
		byte[] shrinePointerBytes = ss.getShrinePointerBytes();
		assertEquals(Arrays.toString(origShrinePointerBytes), Arrays.toString(shrinePointerBytes));
	}
	
	@Test
	public void testGetMinShrineLevelBytes(){
		byte[] minShrineLevelBytes = ss.getMinShrineLevelBytes();
		assertEquals(Arrays.toString(origMinShrineLevelBytes), Arrays.toString(minShrineLevelBytes));
	}
	
	@Test
	public void testGetMaxShrineLevelBytes(){
		byte[] maxShrineLevelBytes = ss.getMaxShrineLevelBytes();
		assertEquals(Arrays.toString(origMaxShrineLevelBytes), Arrays.toString(maxShrineLevelBytes));
	}
	
	@Test
	public void testGetGameTypeBytes(){
		byte[] gameTypesInWhichPresentBytes = ss.getGameTypesInWhichPresentBytes();
		assertEquals(Arrays.toString(origGameTypesInWhichPresentBytes), Arrays.toString(gameTypesInWhichPresentBytes));
	}
}
