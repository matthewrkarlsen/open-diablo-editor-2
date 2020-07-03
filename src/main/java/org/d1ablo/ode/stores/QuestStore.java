/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.md for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */
package org.d1ablo.ode.stores;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.d1ablo.ode.bintool.StringExtractor;
import org.d1ablo.ode.bintool.rw.ReaderWriter;
import org.d1ablo.ode.factories.QuestFactory;
import org.d1ablo.ode.knowledge.DiabloFilePositions;
import org.d1ablo.ode.types.Quest;

import java.util.List;

/**
 * Holds data relating to all the quests within the game.
 */
public class QuestStore {

	@JsonIgnore
	private Quest questSelected;

	private ReaderWriter rw = null;

	@JsonProperty(value = "questList")
	private final List<Quest> quests;
	private final QuestFactory questFactory;

	@JsonCreator
	public QuestStore(@JsonProperty("questList") List<Quest> quests,
					  @JacksonInject QuestFactory questFactory) {
		this.quests = quests;
		this.questFactory = questFactory;
	}

	public QuestStore(List<Quest> quests,
					  QuestFactory questFactory,
					  Quest questSelected) {
		this.quests = quests;
		this.questFactory = questFactory;
		this.questSelected = questSelected;
	}

	public void readInQuests() {
		long pos = DiabloFilePositions.QUESTS_OFFSET; //quests
		long spacing = DiabloFilePositions.QUEST_SPACING;
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_QUESTS; i++){
			readQuest(i, pos, questFactory);
			pos = pos + spacing;
		}
	}

	public void readQuest(int slot, long position, QuestFactory questFactory) {
		byte[] readIn = rw.readBytes(position, DiabloFilePositions.QUEST_LENGTH_IN_BYTES);
		Quest q = questFactory.constructQuest(readIn, slot);
		quests.add(q);
	}

	public void printQuests(){
		for(Quest q : quests){
			q.printQuest();
		}
	}

	public byte[] getQuestAsBytes(int i) {
		return quests.get(i).getQuestAsBytes();
	}

	public void writeQuestsToEXE() {
		long pos = DiabloFilePositions.QUESTS_OFFSET;
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_QUESTS; i++){
			byte[] questAsBytes = this.getQuestAsBytes(i);
			rw.writeBytes(questAsBytes, pos);
			pos = pos + DiabloFilePositions.QUEST_SPACING;
		}
	}

	@JsonIgnore
	public String[] getQuestNames() {
		String[] questNames = new String[quests.size()];
		for(int i = 0; i < quests.size(); i++){
			questNames[i] = String.valueOf(quests.get(i).getQuestNumber());
		}
		return questNames;
	}

	public Quest getQuestByName(String questName) {
		Quest questToReturn = null;
		for(Quest q : quests)
		{
			if(String.valueOf(q.getQuestNumber()).equals(questName)){
				questToReturn = q;
				break;
			}
		}
		return questToReturn;
	}

	public void initialiseUsingBinary(ReaderWriter rw, StringExtractor stringExtractor) {
		this.questFactory.setStringExtractor(stringExtractor);
		this.rw = rw;
	}

	public void updateWith(QuestStore questStore) {
		for(int i = 0; i < quests.size(); i++) {
			Quest quest = quests.get(i);
			Quest q2 = questStore.quests.get(i);
			quest.updateWith(q2);
		}
	}

	@JsonProperty(value = "questSelected")
	public Quest getQuestSelected() {
		return questSelected;
	}

	@JsonIgnore
	public void setQuestSelected(Quest questSelected) {
		this.questSelected = questSelected;
	}
}
