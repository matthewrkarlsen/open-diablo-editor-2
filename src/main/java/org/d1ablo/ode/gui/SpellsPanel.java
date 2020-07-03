/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.md for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */
package org.d1ablo.ode.gui;

import org.d1ablo.ode.stores.SpellsStore;
import org.d1ablo.ode.types.Spell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The panel that permits editing of spells.
 */
public class SpellsPanel extends JPanel {

	private SpellsStore spellStore;

	/**
	 * Construct a new instance
	 * @param spellStore the data store for all of the spells in the game
	 */
	public SpellsPanel(SpellsStore spellStore)
	{
		super();
		this.spellStore = spellStore;
		
		GridLayout layout = new GridLayout(0,2);
		this.setLayout(layout);
		
		String[] spellNames = spellStore.getSpellNames();
		String[] comboBoxStrings = new String[spellNames.length + 1]; 
		comboBoxStrings[0] = "None selected";
		System.arraycopy(spellNames, 0, comboBoxStrings, 1, spellNames.length);
		JComboBox<String> comboBox = new JComboBox<String>(comboBoxStrings);
		comboBox.setSelectedIndex(0);

		JLabel cbLabel = new JLabel("Spell selected:");
		this.add(cbLabel);
		this.add(comboBox);
		
		JLabel indexLabel = new JLabel("Index:");
		JTextField indexField = new JTextField();
		this.add(indexLabel);
		this.add(indexField);
		
		//String[] unmoddedSpellNames = TomeOfKnowledge.createSpellNamesArray();
		//unmoddedSpellNames[unmoddedSpellIndex]
		JLabel unmoddedSpellNameLabel = new JLabel("Unmodded spell name:");
		JTextField unmoddedSpellNameField = new JTextField();
		this.add(unmoddedSpellNameLabel);
		this.add(unmoddedSpellNameField);
		
		JLabel manaToCastLabel = new JLabel("Mana to cast:");
		JTextField manaToCastField = new JTextField();
		this.add(manaToCastLabel);
		this.add(manaToCastField);
		
		JLabel animationWhenCastingLabel = new JLabel("Animation when casting:");
		JTextField animationWhenCastingField = new JTextField();
		this.add(animationWhenCastingLabel);
		this.add(animationWhenCastingField);
		
		JLabel pointerToNameAsSpellLabel = new JLabel("Pointer to name as spell:");
		JTextField pointerToNameAsSpellField = new JTextField();
		this.add(pointerToNameAsSpellLabel);
		this.add(pointerToNameAsSpellField);
		
		JLabel nameAsSpellLabel = new JLabel("Name as spell:");
		JTextField nameAsSpellField = new JTextField();
		this.add(nameAsSpellLabel);
		this.add(nameAsSpellField);
		
		JLabel pointerToNameAsSkillLabel = new JLabel("Pointer to name as skill:");
		JTextField pointerToNameAsSkillField = new JTextField();
		this.add(pointerToNameAsSkillLabel);
		this.add(pointerToNameAsSkillField);
		
		JLabel nameAsSkillLabel = new JLabel("Name as skill:");
		JTextField nameAsSkillField = new JTextField();
		this.add(nameAsSkillLabel);
		this.add(nameAsSkillField);
		
		JLabel spellbookQualityLabel = new JLabel("Spellbook quality:");
		JTextField spellbookQualityField = new JTextField();
		this.add(spellbookQualityLabel);
		this.add(spellbookQualityField);
		
		JLabel staffQualityLabel = new JLabel("Staff quality:");
		JTextField staffQualityField = new JTextField();
		this.add(staffQualityLabel);
		this.add(staffQualityField);
		
		JLabel byteTwentyLabel = new JLabel("Byte twenty:");
		JTextField byteTwentyField = new JTextField();
		this.add(byteTwentyLabel);
		this.add(byteTwentyField);
		
		JLabel byteTwentyoneLabel = new JLabel("Byte twentyone:");
		JTextField byteTwentyoneField = new JTextField();
		this.add(byteTwentyoneLabel);
		this.add(byteTwentyoneField);
		
		JLabel byteTwentytwoLabel = new JLabel("Byte twentytwo:");
		JTextField byteTwentytwoField = new JTextField();
		this.add(byteTwentytwoLabel);
		this.add(byteTwentytwoField);
		
		JLabel byteTwentythreeLabel = new JLabel("Byte twentythree:");
		JTextField byteTwentythreeField = new JTextField();
		this.add(byteTwentythreeLabel);
		this.add(byteTwentythreeField);
		
		JLabel spellActiveInTownLabel = new JLabel("Active in town?");
		JTextField spellActiveInTownField = new JTextField();
		this.add(spellActiveInTownLabel);
		this.add(spellActiveInTownField);
		
		JLabel baseRequiredMagicLabel = new JLabel("Base req. magic:");
		JTextField baseRequiredMagicField = new JTextField();
		this.add(baseRequiredMagicLabel);
		this.add(baseRequiredMagicField);
		
		JLabel castingSoundLabel = new JLabel("Casting sound:");
		JTextField castingSoundField = new JTextField();
		this.add(castingSoundLabel);
		this.add(castingSoundField);
		
		JLabel spellEffect1Label = new JLabel("Spell effect 1:");
		JTextField spellEffect1Field = new JTextField();
		this.add(spellEffect1Label);
		this.add(spellEffect1Field);
		
		JLabel spellEffect2Label = new JLabel("Spell effect 2:");
		JTextField spellEffect2Field = new JTextField();
		this.add(spellEffect2Label);
		this.add(spellEffect2Field);
		
		JLabel spellEffect3Label = new JLabel("Spell effect 3:");
		JTextField spellEffect3Field = new JTextField();
		this.add(spellEffect3Label);
		this.add(spellEffect3Field);
		
		JLabel manaStepLabel = new JLabel("Mana step:");
		JTextField manaStepField = new JTextField();
		this.add(manaStepLabel);
		this.add(manaStepField);
		
		JLabel minCastingCostLabel = new JLabel("Min casting cost:");
		JTextField minCastingCostField = new JTextField();
		this.add(minCastingCostLabel);
		this.add(minCastingCostField);
		
		JLabel byteThirtyEightLabel = new JLabel("Byte thirtyeight:");
		JTextField byteThirtyEightField = new JTextField();
		this.add(byteThirtyEightLabel);
		this.add(byteThirtyEightField);
		
		JLabel byteThirtyNineLabel = new JLabel("Byte thirtynine:");
		JTextField byteThirtyNineField = new JTextField();
		this.add(byteThirtyNineLabel);
		this.add(byteThirtyNineField);
		
		JLabel minChargesLabel = new JLabel("Min charges label:");
		JTextField minChargesField = new JTextField();
		this.add(minChargesLabel);
		this.add(minChargesField);
		
		JLabel maxChargesLabel = new JLabel("Max charges label:");
		JTextField maxChargesField = new JTextField();
		this.add(maxChargesLabel);
		this.add(maxChargesField);
		
		JLabel bookCostLabel = new JLabel("Book cost:");
		JTextField bookCostField = new JTextField();
		this.add(bookCostLabel);
		this.add(bookCostField);
		
		JLabel staffCostMultiplierLabel = new JLabel("Staff cost multiplier:");
		JTextField staffCostMultiplierField = new JTextField();
		this.add(staffCostMultiplierLabel);
		this.add(staffCostMultiplierField);
		
		ActionListener spellsListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String spellName = (String) comboBox.getSelectedItem();
				if(spellName.equals("None selected")){
					indexField.setText("");
					unmoddedSpellNameField.setText("");
					manaToCastField.setText("");
					animationWhenCastingField.setText("");
					pointerToNameAsSpellField.setText("");
					nameAsSpellField.setText("");
					pointerToNameAsSkillField.setText("");
					nameAsSkillField.setText("");
					spellbookQualityField.setText("");
					staffQualityField.setText("");
					byteTwentyField.setText("");
					byteTwentyoneField.setText("");
					byteTwentytwoField.setText("");
					byteTwentythreeField.setText("");
					spellActiveInTownField.setText("");
					baseRequiredMagicField.setText("");
					castingSoundField.setText("");
					spellEffect1Field.setText("");
					spellEffect2Field.setText("");
					spellEffect3Field.setText("");
					manaStepField.setText("");
					minCastingCostField.setText("");
					byteThirtyEightField.setText("");
					byteThirtyNineField.setText("");
					minChargesField.setText("");
					maxChargesField.setText("");
					bookCostField.setText("");
					staffCostMultiplierField.setText("");
				} else {
					Spell spell = spellStore.getSpellByName(spellName);
					indexField.setText(String.valueOf(spell.getUnmoddedSpellIndex()));
					unmoddedSpellNameField.setText(String.valueOf(spell.getNameAsSpell()));
					manaToCastField.setText(String.valueOf(spell.getManaToCast()));
					animationWhenCastingField.setText(String.valueOf(spell.getAnimationWhenCasting()));
					pointerToNameAsSpellField.setText(String.valueOf(spell.getPointerToNameAsSpell()));
					nameAsSpellField.setText(String.valueOf(spell.getNameAsSpell()));
					pointerToNameAsSkillField.setText(String.valueOf(spell.getPointerToNameAsSkill()));
					nameAsSkillField.setText(String.valueOf(spell.getNameAsSkill()));
					spellbookQualityField.setText(String.valueOf(spell.getSpellBookQuality()));
					staffQualityField.setText(String.valueOf(spell.getStaffQuality()));
					byteTwentyField.setText(String.valueOf(spell.getByteTwenty()));
					byteTwentyoneField.setText(String.valueOf(spell.getByteTwentyOne()));
					byteTwentytwoField.setText(String.valueOf(spell.getByteTwentyTwo()));
					byteTwentythreeField.setText(String.valueOf(spell.getByteTwentyThree()));
					spellActiveInTownField.setText(String.valueOf(spell.getSpellActiveInTown()));
					baseRequiredMagicField.setText(String.valueOf(spell.getBaseRequiredMagic()));
					castingSoundField.setText(String.valueOf(spell.getCastingSound()));
					spellEffect1Field.setText(String.valueOf(spell.getSpellEffect1()));
					spellEffect2Field.setText(String.valueOf(spell.getSpellEffect2()));
					spellEffect3Field.setText(String.valueOf(spell.getSpellEffect3()));
					manaStepField.setText(String.valueOf(spell.getManaStep()));
					minCastingCostField.setText(String.valueOf(spell.getMinCastingCost()));
					byteThirtyEightField.setText(String.valueOf(spell.getByteThirtyEight()));
					byteThirtyNineField.setText(String.valueOf(spell.getByteThirtyNine()));
					minChargesField.setText(String.valueOf(spell.getMinCharges()));
					maxChargesField.setText(String.valueOf(spell.getMaxCharges()));
					bookCostField.setText(String.valueOf(spell.getBookCost()));
					staffCostMultiplierField.setText(String.valueOf(spell.getStaffCostMultiplier()));
				}
			}
			
		};
		comboBox.addActionListener(spellsListener);
		
		JButton saveButton = new JButton("Cache changes");
		this.add(new JLabel());
		this.add(saveButton);
		
		ActionListener saveClick = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String spellName = (String) comboBox.getSelectedItem();
				if(spellName.equals("None selected")){
					//Do nothing
				} else {
					Spell spell = spellStore.getSpellByName(spellName);
					//TODO -- spell.setUnmoddedSpellIndex(Integer.parseInt());
					spell.setNameAsSpell(unmoddedSpellNameField.getText());
					spell.setManaToCast(Integer.parseInt(manaToCastField.getText()));
					spell.setAnimationWhenCasting(Integer.parseInt(animationWhenCastingField.getText()));
					spell.setPointerToNameAsSpell(Long.parseLong(pointerToNameAsSpellField.getText()));
					spell.setNameAsSpell(nameAsSpellField.getText());
					spell.setPointerToNameAsSkill(Long.parseLong(pointerToNameAsSkillField.getText()));
					spell.setNameAsSkill(nameAsSkillField.getText());
					spell.setSpellBookQuality(Long.parseLong(spellbookQualityField.getText()));
					spell.setStaffQuality(Long.parseLong(staffQualityField.getText()));
					spell.setByteTwenty(Integer.parseInt(byteTwentyField.getText()));
					spell.setByteTwentyOne(Integer.parseInt(byteTwentyoneField.getText()));
					spell.setByteTwentyTwo(Integer.parseInt(byteTwentytwoField.getText()));
					spell.setByteTwentyThree(Integer.parseInt(byteTwentythreeField.getText()));
					spell.setSpellActiveInTown(Long.parseLong(spellActiveInTownField.getText()));
					spell.setBaseRequiredMagic(Long.parseLong(baseRequiredMagicField.getText()));
					spell.setCastingSound(Integer.parseInt(castingSoundField.getText()));
					spell.setSpellEffect1(Byte.parseByte(spellEffect1Field.getText()));
					spell.setSpellEffect2(Byte.parseByte(spellEffect2Field.getText()));
					spell.setSpellEffect3(Byte.parseByte(spellEffect3Field.getText()));
					spell.setManaStep(Integer.parseInt(manaStepField.getText()));
					spell.setMinCastingCost(Integer.parseInt(minCastingCostField.getText()));
					spell.setByteThirtyEight(Integer.parseInt(byteThirtyEightField.getText()));
					spell.setByteThirtyNine(Integer.parseInt(byteThirtyNineField.getText()));
					spell.setMinCharges(Long.parseLong(minChargesField.getText()));
					spell.setMaxCharges(Long.parseLong(maxChargesField.getText()));
					spell.setBookCost(Long.parseLong(bookCostField.getText()));
					spell.setStaffCostMultiplier(Long.parseLong(staffCostMultiplierField.getText()));
				}
			}
		};
		saveButton.addActionListener(saveClick);
	}
}
