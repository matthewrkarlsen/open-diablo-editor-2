package org.d1ablo.ode.types;

import junit.framework.TestCase;
import org.d1ablo.ode.bintool.BinEditTool;
import org.d1ablo.ode.bintool.StringExtractor;
import org.d1ablo.ode.bintool.rw.Reader;
import org.d1ablo.ode.bintool.rw.ReaderWriter;
import org.d1ablo.ode.factories.CharacterFactory;
import org.d1ablo.ode.knowledge.DiabloFilePositions;
import org.d1ablo.ode.stores.CharacterStore;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;

public class CharacterStoreTest extends TestCase {

	protected byte[] origInitStatBytes;
	
	protected byte[] origMaxStatBytes;
	
	protected byte[] origBlockingBonusBytes;
	
	protected byte[] origBonusAndFramesetBytes;
	
	protected CharacterStore cs;
	
	public void readInAllCharacterBytes(Reader reader) {
		
		long pos = DiabloFilePositions.INIT_STATS_OFFSET;
		origInitStatBytes = new byte[DiabloFilePositions.INIT_STATS_LENGTH_IN_BYTES];
		for(int j = 0; j < DiabloFilePositions.INIT_STATS_LENGTH_IN_BYTES; j++){
			origInitStatBytes[j] = reader.readByte(pos);
			pos++;
		}

		pos = DiabloFilePositions.MAX_STATS_OFFSET;
		origMaxStatBytes = new byte[DiabloFilePositions.MAX_STATS_LENGTH_IN_BYTES];
		for(int j = 0; j < DiabloFilePositions.MAX_STATS_LENGTH_IN_BYTES; j++){
			origMaxStatBytes[j] = reader.readByte(pos);
			pos++;
		}

		pos = DiabloFilePositions.BLOCKING_BONUSES_OFFSET;
		origBlockingBonusBytes = new byte[DiabloFilePositions.BLOCKING_BONUSES_LENGTH_IN_BYTES];
		for(int j = 0; j < DiabloFilePositions.BLOCKING_BONUSES_LENGTH_IN_BYTES; j++){
			origBlockingBonusBytes[j] = reader.readByte(pos);
			pos++;
		}

		pos = DiabloFilePositions.BONUSES_AND_FRAMESETS_OFFSET;
		origBonusAndFramesetBytes = new byte[DiabloFilePositions.BONUSES_AND_FRAMESETS_LENGTH_IN_BYTES];
		for(int j = 0; j < DiabloFilePositions.BONUSES_AND_FRAMESETS_LENGTH_IN_BYTES; j++){
			origBonusAndFramesetBytes[j] = reader.readByte(pos);
			pos++;
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
		BinEditTool binEditTool = new BinEditTool();
		cs = new CharacterStore(new ArrayList<>(), binEditTool, new CharacterFactory(binEditTool));
		StringExtractor stringExtractor = new StringExtractor(rw.getReader());
		cs.initialiseUsingBinary(rw, stringExtractor);
		cs.readInCharacters();
		readInAllCharacterBytes(rw.getReader());
	}

	@Test
	public void testGetInitStatBytes(){
		byte[] retrievedInitStatBytes = cs.getInitStatBytes();
		String retrievedInitStatString = Arrays.toString(retrievedInitStatBytes);
		String origInitStatString = Arrays.toString(origInitStatBytes);
		assertEquals(origInitStatString, retrievedInitStatString);
	}
	
	@Test
	public void testGetMaxStatBytes(){
		byte[] retrievedMaxStatBytes = cs.getMaxStatBytes();
		String retrievedMaxStatString = Arrays.toString(retrievedMaxStatBytes);
		String origMaxStatString = Arrays.toString(retrievedMaxStatBytes);
		assertEquals(origMaxStatString, retrievedMaxStatString);
	}
	
	@Test
	public void testGetBlockingBonusBytes(){
		byte[] retrievedBlockingBonusBytes = cs.getBlockingBonusBytes();
		String retrievedBlockingBonusString = Arrays.toString(retrievedBlockingBonusBytes);
		String origBlockingBonusString = Arrays.toString(origBlockingBonusBytes);
		assertEquals(origBlockingBonusString, retrievedBlockingBonusString);
	}
	
	@Test
	public void testGetBonusesAndFramesetBytes(){
		byte[] retrievedBonusAndFramesetBytes = cs.getBonusesAndFramesetBytes();
		String retrievedBonusAndFramesetString = Arrays.toString(retrievedBonusAndFramesetBytes);
		String origBonusAndFramesetString = Arrays.toString(origBonusAndFramesetBytes);
		assertEquals(origBonusAndFramesetString, retrievedBonusAndFramesetString);
	}
}
