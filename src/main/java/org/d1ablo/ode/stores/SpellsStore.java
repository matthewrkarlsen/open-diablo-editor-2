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
import org.d1ablo.ode.factories.SpellFactory;
import org.d1ablo.ode.knowledge.DiabloFilePositions;
import org.d1ablo.ode.types.Spell;

import java.util.List;

public class SpellsStore {

	@JsonIgnore
	private Spell spellSelected;

	private ReaderWriter rw = null;

	@JsonProperty(value = "spellList")
	private final List<Spell> spells;

	private final SpellFactory spellFactory;

	@JsonCreator
	public SpellsStore(@JsonProperty("spellList") List<Spell> spells,
					   @JacksonInject SpellFactory spellFactory){
		this.spells = spells;
		this.spellFactory = spellFactory;
	}

	public SpellsStore(List<Spell> spells,
					   SpellFactory spellFactory,
					   Spell spellSelected){
		this.spells = spells;
		this.spellFactory = spellFactory;
		this.spellSelected = spellSelected;
	}

	public void readInSpells(){
		long pos = DiabloFilePositions.SPELLS_OFFSET;
		long spacing = 56L;
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_SPELLS; i++){
			readSpell(pos, i, spellFactory);
			pos = pos + spacing;
		}
	}

	private void readSpell(long position, int index, SpellFactory spellFactory) {
		long pos = position;
		byte[] readIn = new byte[DiabloFilePositions.SPELL_LENGTH_IN_BYTES];
		readIn[0] = rw.readByte(pos);
		for(int i = 1; i < 56; i++){ //FIXME -- use readBytes()
			pos++;
			readIn[i] = rw.readByte(pos);
		}

		Spell s = spellFactory.constructSpell(readIn, index);
		spells.add(s);
	}

	public void printSpells() {
		for(Spell s : spells){
			s.printSpell();
		}
	}

	public byte[] getSpellAsBytes(int i) {
		return spells.get(i).toBytes();
	}

	public void writeSpellsToEXE() {
		long pos = DiabloFilePositions.SPELLS_OFFSET;
		for(int i = 0; i < DiabloFilePositions.NUMBER_OF_SPELLS; i++){
			byte[] spellAsBytes = this.getSpellAsBytes(i);
			rw.writeBytes(spellAsBytes, pos);
			pos = pos + DiabloFilePositions.SPELL_LENGTH_IN_BYTES;
		}
	}

	@JsonIgnore
	public String[] getSpellNames() {
		String[] spellNames = new String[spells.size()];
		for(int i = 0; i < spells.size(); i++){
			spellNames[i] = String.valueOf(spells.get(i).getUnmoddedSpellIndex());
		}
		return spellNames;
	}

	public Spell getSpellByName(String spellName) {
		Spell spellToReturn = null;
		for(Spell s : spells)
		{
			if(String.valueOf(s.getUnmoddedSpellIndex()).equals(spellName))
			{
				spellToReturn = s;
				break;
			}
		}
		return spellToReturn;
	}

	public void initialiseUsingBinary(ReaderWriter rw, StringExtractor stringExtractor) {
		this.spellFactory.setStringExtractor(stringExtractor);
		this.rw = rw;
	}

	public void updateWith(SpellsStore spellStore) {
		for(int i = 0; i < spells.size(); i++) {
			Spell spell = spells.get(i);
			Spell s2 = spellStore.spells.get(i);
			spell.updateFrom(s2);
		}
	}

	@JsonProperty(value = "spellSelected")
	public Spell getSpellSelected() {
		return spellSelected;
	}

	@JsonIgnore
	public void setSpellSelected(Spell spellSelected) {
		this.spellSelected = spellSelected;
	}
}
