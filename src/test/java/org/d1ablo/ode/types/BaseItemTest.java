package org.d1ablo.ode.types;

import junit.framework.TestCase;
import org.d1ablo.ode.bintool.BinEditTool;
import org.d1ablo.ode.bintool.StringExtractor;
import org.d1ablo.ode.bintool.rw.Reader;
import org.d1ablo.ode.bintool.rw.ReaderWriter;
import org.d1ablo.ode.factories.BaseItemFactory;
import org.d1ablo.ode.knowledge.DiabloFilePositions;
import org.d1ablo.ode.stores.BaseItemStore;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;

public class BaseItemTest extends TestCase{

	protected byte[][] readInBytes;
	
	protected BaseItemStore bis;
	
	private void readInAllItemBytes(Reader reader) {
		readInBytes = new byte[DiabloFilePositions.NUMBER_OF_BASE_ITEMS][DiabloFilePositions.BASE_ITEM_LENGTH_IN_BYTES];
		long pos = DiabloFilePositions.BASE_ITEMS_OFFSET;
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_BASE_ITEMS; i++){
			readInBytes[i] = reader.readBytes(pos, DiabloFilePositions.BASE_ITEM_LENGTH_IN_BYTES);
			pos = pos + DiabloFilePositions.BASE_ITEM_LENGTH_IN_BYTES;
		}
	}
	
	@Override
	protected void setUp() throws FileNotFoundException, IOException {
		RandomAccessFile randomAccessFile;
		try {
			randomAccessFile = new RandomAccessFile("binaries/Diablo.exe", "r");
		} catch (FileNotFoundException e) {
			throw new IllegalStateException(e);
		}
		ReaderWriter rw = new ReaderWriter(randomAccessFile);
		bis = new BaseItemStore(new ArrayList<>(), new BaseItemFactory(new BinEditTool()));
		StringExtractor stringExtractor = new StringExtractor(rw.getReader());
		bis.initialiseUsingBinary(rw, stringExtractor);
		bis.readInItems();
		readInAllItemBytes(rw.getReader());
	}
	
	/**
	 * This tests to ensure that getItemAsBytes() 
	 * returns the correct sequence of bytes
	 * for each item.
	 */
	@Test
	public void testGetItemAsBytes(){
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_BASE_ITEMS; i++){
			byte[] itemAsBytes = bis.getItemAsBytes(i);
			String itemAsString = Arrays.toString(itemAsBytes);
			String originalString = Arrays.toString(readInBytes[i]);
			assertEquals(originalString, itemAsString);
		}
	}
}
