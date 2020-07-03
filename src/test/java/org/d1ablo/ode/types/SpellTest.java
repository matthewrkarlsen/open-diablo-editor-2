package org.d1ablo.ode.types;

import junit.framework.TestCase;
import org.d1ablo.ode.bintool.BinEditTool;
import org.d1ablo.ode.bintool.StringExtractor;
import org.d1ablo.ode.bintool.rw.Reader;
import org.d1ablo.ode.bintool.rw.ReaderWriter;
import org.d1ablo.ode.factories.SpellFactory;
import org.d1ablo.ode.knowledge.DiabloFilePositions;
import org.d1ablo.ode.stores.SpellsStore;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;

public class SpellTest extends TestCase {

    protected byte[][] origSpellBytes;
	
	protected SpellsStore ss;
	
	public void readInAllSpellBytes(Reader reader){
		origSpellBytes = new byte[DiabloFilePositions.NUMBER_OF_SPELLS][DiabloFilePositions.SPELL_LENGTH_IN_BYTES];
		long pos = DiabloFilePositions.SPELLS_OFFSET; //skills
		long spacing = 56L;
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_SPELLS; i++){
			origSpellBytes[i] = reader.readBytes(pos, DiabloFilePositions.SPELL_LENGTH_IN_BYTES);
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
		ss = new SpellsStore(new ArrayList<>(), new SpellFactory(new BinEditTool()));
		StringExtractor stringExtractor = new StringExtractor(rw.getReader());
		ss.initialiseUsingBinary(rw, stringExtractor);
		ss.readInSpells();
		readInAllSpellBytes(rw.getReader());
	}

	@Test
	public void testGetSpellAsBytes(){
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_SPELLS; i++){
			String origString = Arrays.toString(origSpellBytes[i]);
			byte[] retrievedBytes = ss.getSpellAsBytes(i);
			String retrievedString = Arrays.toString(retrievedBytes);
			assertEquals(origString, retrievedString);
		}
	}
}
