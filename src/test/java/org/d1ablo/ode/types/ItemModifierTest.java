package org.d1ablo.ode.types;

import junit.framework.TestCase;
import org.d1ablo.ode.bintool.BinEditTool;
import org.d1ablo.ode.bintool.StringExtractor;
import org.d1ablo.ode.bintool.rw.Reader;
import org.d1ablo.ode.bintool.rw.ReaderWriter;
import org.d1ablo.ode.factories.ItemModifierFactory;
import org.d1ablo.ode.knowledge.DiabloFilePositions;
import org.d1ablo.ode.stores.ItemModifiersStore;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;

public class ItemModifierTest extends TestCase {

	protected byte[][] origItemModifierBytes;
	
	protected ItemModifiersStore ims;
	
	public void readInAllModifierBytes(Reader reader){
		origItemModifierBytes = new byte[DiabloFilePositions.NUMBER_OF_MODIFIERS][DiabloFilePositions.MODIFIER_LENGTH_IN_BYTES];
		long pos = DiabloFilePositions.MODIFIERS_OFFSET;
		long spacing = DiabloFilePositions.MODIFIER_LENGTH_IN_BYTES;
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_MODIFIERS; i++){
			origItemModifierBytes[i] = readModifierBytes(reader, pos);
			pos = pos + spacing;
		}
	}

	private byte[] readModifierBytes(Reader reader, long position) {
		long pos = position;
		byte[] readIn = new byte[DiabloFilePositions.MODIFIER_LENGTH_IN_BYTES];
		for(int j = 0; j < DiabloFilePositions.MODIFIER_LENGTH_IN_BYTES; j++){
			readIn[j] = reader.readByte(pos);
			pos++;
		}
		return readIn;
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
		ims = new ItemModifiersStore(new ArrayList<>(), new ItemModifierFactory(new BinEditTool()));
		StringExtractor stringExtractor = new StringExtractor(rw.getReader());
		ims.initialiseUsingBinary(rw, stringExtractor);
		ims.readInModifiers();
		readInAllModifierBytes(rw.getReader());
	}

	@Test
	public void testGetModifierAsBytes() {
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_MODIFIERS; i++){
			byte[] returnedModifierBytes = ims.getModifierAsBytes(i);
			String returnedModifierString = Arrays.toString(returnedModifierBytes);
			String origModifierString = Arrays.toString(origItemModifierBytes[i]);
			assertEquals(origModifierString, returnedModifierString);
		}
	}
}
