package org.d1ablo.ode.types;

import junit.framework.TestCase;
import org.d1ablo.ode.bintool.BinEditTool;
import org.d1ablo.ode.bintool.StringExtractor;
import org.d1ablo.ode.bintool.rw.Reader;
import org.d1ablo.ode.bintool.rw.ReaderWriter;
import org.d1ablo.ode.factories.UniqueItemFactory;
import org.d1ablo.ode.knowledge.DiabloFilePositions;
import org.d1ablo.ode.stores.UniqueItemStore;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;

public class UniqueItemTest extends TestCase {

	private byte[][] originalUniqueItemBytes;
	
	private UniqueItemStore uis;
	
	public void readInAllItemBytes(Reader reader) {
		originalUniqueItemBytes = new byte[DiabloFilePositions.NUMBER_OF_UNIQUE_ITEMS][DiabloFilePositions.UNIQUE_ITEM_LENGTH_IN_BYTES];
		long pos = DiabloFilePositions.UNIQUE_ITEMS_OFFSET;
		long spacing = DiabloFilePositions.UNIQUE_ITEM_LENGTH_IN_BYTES;
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_UNIQUE_ITEMS; i++){
			originalUniqueItemBytes[i] = reader.readBytes(pos, DiabloFilePositions.UNIQUE_ITEM_LENGTH_IN_BYTES);
			pos = pos + spacing;
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
		uis = new UniqueItemStore(new ArrayList<>(), new UniqueItemFactory(new BinEditTool()));
		StringExtractor stringExtractor = new StringExtractor(rw.getReader());
		uis.initialiseUsingBinary(rw, stringExtractor);
		uis.readInItems();
		readInAllItemBytes(rw.getReader());
	}

	@Test
	public void testGetItemAsBytes(){
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_UNIQUE_ITEMS; i++){
			byte[] retrievedItemBytes = uis.getItemAsBytes(i);
			String retrievedItemString = Arrays.toString(retrievedItemBytes);
			String originalItemString = Arrays.toString(originalUniqueItemBytes[i]);
			assertEquals(originalItemString, retrievedItemString);
		}
	}
	
}
