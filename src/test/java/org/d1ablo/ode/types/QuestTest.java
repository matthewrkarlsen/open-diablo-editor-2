package org.d1ablo.ode.types;

import junit.framework.TestCase;
import org.d1ablo.ode.bintool.BinEditTool;
import org.d1ablo.ode.bintool.StringExtractor;
import org.d1ablo.ode.bintool.rw.Reader;
import org.d1ablo.ode.bintool.rw.ReaderWriter;
import org.d1ablo.ode.factories.QuestFactory;
import org.d1ablo.ode.knowledge.DiabloFilePositions;
import org.d1ablo.ode.stores.QuestStore;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;

public class QuestTest extends TestCase {

	byte[][] originalQuestBytes;
	
	protected QuestStore qs;
	
	public void readInAllQuestBytes(Reader reader){
		originalQuestBytes = new byte[DiabloFilePositions.NUMBER_OF_QUESTS][DiabloFilePositions.QUEST_LENGTH_IN_BYTES];
		//seek and read
		long pos = DiabloFilePositions.QUESTS_OFFSET; //quests
		long spacing = 20L;
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_QUESTS; i++){
			originalQuestBytes[i] = reader.readBytes(pos, DiabloFilePositions.QUEST_LENGTH_IN_BYTES);
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
		qs = new QuestStore(new ArrayList<>(), new QuestFactory(new BinEditTool()));
		StringExtractor stringExtractor = new StringExtractor(rw.getReader());
		qs.initialiseUsingBinary(rw, stringExtractor);
		qs.readInQuests();
		readInAllQuestBytes(rw.getReader());
	}

	/**
	 * This tests to ensure that getQuestAsBytes() 
	 * returns the correct sequence of bytes
	 * for each quest.
	 */
	@Test
	public void testGetQuestAsBytes(){
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_QUESTS; i++){
			byte[] questAsBytes = qs.getQuestAsBytes(i);
			String questAsString = Arrays.toString(questAsBytes);
			String originalString = Arrays.toString(originalQuestBytes[i]);
			assertEquals(originalString, questAsString);
		}
	}
}
