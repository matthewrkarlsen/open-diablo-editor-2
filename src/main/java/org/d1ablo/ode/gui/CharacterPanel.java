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

import org.d1ablo.ode.stores.CharacterStore;
import org.d1ablo.ode.types.Character;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The panel that permits editing of characters.
 */
public class CharacterPanel extends JPanel {
	
	private final CharacterStore characterStore;

	/**
	 * Construct a new instance
	 * @param characterStore the data store for all of the characters in the game
	 */
	public CharacterPanel(CharacterStore characterStore){
		super();
		this.characterStore = characterStore;
		
		GridLayout layout = new GridLayout(0,2);
		this.setLayout(layout);
		
		String[] charNames = characterStore.getCharacterNames();
		String[] comboBoxStrings = new String[charNames.length + 1]; 
		comboBoxStrings[0] = "None selected";
		System.arraycopy(charNames, 0, comboBoxStrings, 1, charNames.length);
		JComboBox<String> charComboBox = new JComboBox<>(comboBoxStrings);
		charComboBox.setSelectedIndex(0);
		JLabel characterCBLabel = new JLabel("Character selected:");
		this.add(characterCBLabel);
		this.add(charComboBox);
		
		JLabel classNameLabel = new JLabel("Class name:");
		JTextField classNameField = new JTextField();
		this.add(classNameLabel);
		this.add(classNameField);
		
		JLabel initStrengthLabel = new JLabel("Init strength:");
		JTextField initStrengthField = new JTextField();
		this.add(initStrengthLabel);
		this.add(initStrengthField);
		
		JLabel maxStrengthLabel = new JLabel("Max strength:");
		JTextField maxStrengthField = new JTextField();
		this.add(maxStrengthLabel);
		this.add(maxStrengthField);
		
		JLabel initMagicLabel = new JLabel("Init magic:");
		JTextField initMagicField = new JTextField();
		this.add(initMagicLabel);
		this.add(initMagicField);
		
		JLabel maxMagicLabel = new JLabel("Max magic:");
		JTextField maxMagicField = new JTextField();
		this.add(maxMagicLabel);
		this.add(maxMagicField);
		
		JLabel initDexterityLabel = new JLabel("Init dexterity:");
		JTextField initDexterityField = new JTextField();
		this.add(initDexterityLabel);
		this.add(initDexterityField);
		
		JLabel maxDexterityLabel = new JLabel("Max dexterity:");
		JTextField maxDexterityField = new JTextField();
		this.add(maxDexterityLabel);
		this.add(maxDexterityField);
		
		JLabel initVitalityLabel = new JLabel("Init vitality:");
		JTextField initVitalityField = new JTextField();
		this.add(initVitalityLabel);
		this.add(initVitalityField);
		
		JLabel maxVitalityLabel = new JLabel("Max vitality:");
		JTextField maxVitalityField = new JTextField();
		this.add(maxVitalityLabel);
		this.add(maxVitalityField);
		
		JLabel blockingBonusLabel = new JLabel("Blocking bonus:");
		JTextField blockingBonusField = new JTextField();
		this.add(blockingBonusLabel);
		this.add(blockingBonusField);
		
		JLabel dungeonIdleFramesetLabel = new JLabel("Dungeon idle frameset:");
		JTextField dungeonIdleFramesetField = new JTextField();
		this.add(dungeonIdleFramesetLabel);
		this.add(dungeonIdleFramesetField);
		
		JLabel attackingFramesetLabel = new JLabel("Attacking frameset:");
		JTextField attackingFramesetField = new JTextField();
		this.add(attackingFramesetLabel);
		this.add(attackingFramesetField);
		
		JLabel dungeonWalkFramesetLabel = new JLabel("Dungeon walk frameset:");
		JTextField dungeonWalkFramesetField = new JTextField();
		this.add(dungeonWalkFramesetLabel);
		this.add(dungeonWalkFramesetField);
		
		JLabel blockSpeedLabel = new JLabel("Block speed (1/20 secs):");
		JTextField blockSpeedField = new JTextField();
		this.add(blockSpeedLabel);
		this.add(blockSpeedField);
		
		JLabel deathFramesetLabel = new JLabel("Death frameset:");
		JTextField deathFramesetField = new JTextField();
		this.add(deathFramesetLabel);
		this.add(deathFramesetField);
		
		JLabel castingFramesetLabel = new JLabel("Casting frameset:");
		JTextField castingFramesetField = new JTextField();
		this.add(castingFramesetLabel);
		this.add(castingFramesetField);
		
		JLabel hitRecoverySpeedLabel = new JLabel("Hit recovery speed:");
		JTextField hitRecoverySpeedField = new JTextField();
		this.add(hitRecoverySpeedLabel);
		this.add(hitRecoverySpeedField);
		
		JLabel townIdleFramesetLabel = new JLabel("Town idle frameset:");
		JTextField townIdleFramesetField = new JTextField();
		this.add(townIdleFramesetLabel);
		this.add(townIdleFramesetField);
		
		JLabel townWalkFramesetLabel = new JLabel("Town walk frameset:");
		JTextField townWalkFramesetField = new JTextField();
		this.add(townWalkFramesetLabel);
		this.add(townWalkFramesetField);
		
		JLabel oneHandedAttackSpeedLabel = new JLabel("1H attack speed:");
		JTextField oneHandedAttackSpeedField = new JTextField();
		this.add(oneHandedAttackSpeedLabel);
		this.add(oneHandedAttackSpeedField);
		
		JLabel castingSpeedLabel = new JLabel("Casting speed (1/20 secs):");
		JTextField castingSpeedField = new JTextField();
		this.add(castingSpeedLabel);
		this.add(castingSpeedField);
		
		ActionListener charListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String actionCommand = e.getActionCommand();
				String charName = (String) charComboBox.getSelectedItem();
		        if(charName.equals("None selected")){
		        	classNameField.setText("");
		        	initStrengthField.setText("");
		        	maxStrengthField.setText("");
		        	initMagicField.setText("");
		        	maxMagicField.setText("");
		        	initDexterityField.setText("");
		        	maxDexterityField.setText("");
		        	initVitalityField.setText("");
		        	maxVitalityField.setText("");
		        	blockingBonusField.setText("");
		        	dungeonIdleFramesetField.setText("");
		        	attackingFramesetField.setText("");
		        	dungeonWalkFramesetField.setText("");
		        	blockSpeedField.setText("");
		        	deathFramesetField.setText("");
		        	castingFramesetField.setText("");
		        	hitRecoverySpeedField.setText("");
		        	townIdleFramesetField.setText("");
		        	townWalkFramesetField.setText("");
		        	oneHandedAttackSpeedField.setText("");
		        	castingSpeedField.setText("");
		        } else {
		        	Character character = characterStore.getCharacterByName(charName);
		        	classNameField.setText(character.getClassName());
		        	initStrengthField.setText(String.valueOf(character.getInitStrength()));
		        	maxStrengthField.setText(String.valueOf(character.getMaxStrength()));
		        	initMagicField.setText(String.valueOf(character.getInitMagic()));
		        	maxMagicField.setText(String.valueOf(character.getMaxMagic()));
		        	initDexterityField.setText(String.valueOf(character.getInitDexterity()));
		        	maxDexterityField.setText(String.valueOf(character.getMaxDexterity()));
		        	initVitalityField.setText(String.valueOf(character.getInitVitality()));
		        	maxVitalityField.setText(String.valueOf(character.getMaxVitality()));
		        	blockingBonusField.setText(String.valueOf(character.getBlockingBonus()));
		        	dungeonIdleFramesetField.setText(String.valueOf(character.getDungeonIdleFrameset()));
		        	attackingFramesetField.setText(String.valueOf(character.getAttackingFrameset()));
		        	dungeonWalkFramesetField.setText(String.valueOf(character.getDungeonWalkFrameset()));
		        	blockSpeedField.setText(String.valueOf(character.getBlockSpeed()));
		        	deathFramesetField.setText(String.valueOf(character.getDeathFrameset()));
		        	castingFramesetField.setText(String.valueOf(character.getCastingFrameset()));
		        	hitRecoverySpeedField.setText(String.valueOf(character.getHitRecoverySpeed()));
		        	townIdleFramesetField.setText(String.valueOf(character.getTownIdleFrameset()));
		        	townWalkFramesetField.setText(String.valueOf(character.getTownWalkFrameset()));
		        	oneHandedAttackSpeedField.setText(String.valueOf(character.getOneHandedAttackSpeed()));
		        	castingSpeedField.setText(String.valueOf(character.getCastingSpeed()));
		        }
			}	
		};
		charComboBox.addActionListener(charListener);
		
		JButton saveButton = new JButton("Cache changes");
		this.add(new JLabel());
		this.add(saveButton);
		
		ActionListener saveClick = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String actionCommand = e.getActionCommand();
				String charName = (String) charComboBox.getSelectedItem();
				if(charName.equals("None selected")){
					//do nothing
				} else {
					Character character = characterStore.getCharacterByName(charName);
					character.setClassName(classNameField.getText());
					character.setInitStrength(Long.parseLong(initStrengthField.getText()));
					character.setMaxStrength(Long.parseLong(maxStrengthField.getText()));
					character.setInitMagic(Long.parseLong(initMagicField.getText()));
					character.setMaxMagic(Long.parseLong(maxMagicField.getText()));
					character.setInitDexterity(Long.parseLong(initDexterityField.getText()));
					character.setMaxDexterity(Long.parseLong(maxDexterityField.getText()));
					character.setInitVitality(Long.parseLong(initVitalityField.getText()));
					character.setMaxVitality(Long.parseLong(maxVitalityField.getText()));
					character.setBlockingBonus(Long.parseLong(blockingBonusField.getText()));
					character.setDungeonIdleFrameset(Integer.parseInt(dungeonIdleFramesetField.getText()));
					character.setAttackingFrameset(Integer.parseInt(attackingFramesetField.getText()));
					character.setDungeonWalkFrameset(Integer.parseInt(dungeonWalkFramesetField.getText()));
					character.setBlockSpeed(Integer.parseInt(blockSpeedField.getText()));
					character.setDeathFrameset(Integer.parseInt(deathFramesetField.getText()));
					character.setCastingFrameset(Integer.parseInt(castingFramesetField.getText()));
					character.setHitRecoverySpeed(Integer.parseInt(hitRecoverySpeedField.getText()));
					character.setTownIdleFrameset(Integer.parseInt(townIdleFramesetField.getText()));
					character.setTownWalkFrameset(Integer.parseInt(townWalkFramesetField.getText()));
					character.setOneHandedAttackSpeed(Integer.parseInt(oneHandedAttackSpeedField.getText()));
					character.setCastingSpeed(Integer.parseInt(castingSpeedField.getText()));
				}
			}
		};
		saveButton.addActionListener(saveClick);
	}
}
