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

import org.d1ablo.ode.stores.UniqueMonsterStore;
import org.d1ablo.ode.types.UniqueMonster;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The panel that permits editing of unique monsters.
 */
public class UniqueMonstersPanel extends JPanel {

	private UniqueMonsterStore uniqueMonsterStore;

	/**
	 * Construct a new instance
	 * @param uniqueMonsterStore the data store for all of the unique monsters in the game
	 */
	public UniqueMonstersPanel(UniqueMonsterStore uniqueMonsterStore)
	{
		super();
		this.uniqueMonsterStore = uniqueMonsterStore;
		
		GridLayout layout = new GridLayout(0,2);
		this.setLayout(layout);
		
		String[] uMonsterNames = uniqueMonsterStore.getMonsterNames();
		String[] comboBoxStrings = new String[uMonsterNames.length + 1]; 
		comboBoxStrings[0] = "None selected";
		System.arraycopy(uMonsterNames, 0, comboBoxStrings, 1, uMonsterNames.length);
		JComboBox<String> comboBox = new JComboBox<String>(comboBoxStrings);
		comboBox.setSelectedIndex(0);
		
		JLabel cbLabel = new JLabel("Unique monster selected:");
		this.add(cbLabel);
		this.add(comboBox);
		
		JLabel nameLabel = new JLabel("Name:");
		JTextField nameField = new JTextField();
		this.add(nameLabel);
		this.add(nameField);
		
		JLabel monsterTypeLabel = new JLabel("Monster type:");
		JTextField monsterTypeField = new JTextField();
		this.add(monsterTypeLabel);
		this.add(monsterTypeField);
		
		JLabel namePointerLabel = new JLabel("Name pointer:");
		JTextField namePointerField = new JTextField();
		this.add(namePointerLabel);
		this.add(namePointerField);
		
		JLabel trnPointerLabel = new JLabel("TRN pointer:");
		JTextField trnPointerField = new JTextField();
		this.add(trnPointerLabel);
		this.add(trnPointerField);
		
		JLabel dungeonLevelLabel = new JLabel("Dungeon level:");
		JTextField dungeonLevelField = new JTextField();
		this.add(dungeonLevelLabel);
		this.add(dungeonLevelField);
		
		JLabel hitPointsLabel = new JLabel("Hit points:");
		JTextField hitPointsField = new JTextField();
		this.add(hitPointsLabel);
		this.add(hitPointsField);
		
		JLabel monsterAILabel = new JLabel("Monster AI:");
		JTextField monsterAIField = new JTextField();
		this.add(monsterAILabel);
		this.add(monsterAIField);
		
		JLabel intelligenceFactorLabel = new JLabel("Intelligence factor:");
		JTextField intelligenceFactorField = new JTextField();
		this.add(intelligenceFactorLabel);
		this.add(intelligenceFactorField);
		
		JLabel minAttackDmgLabel = new JLabel("Min attack dmg:");
		JTextField minAttackDmgField = new JTextField();
		this.add(minAttackDmgLabel);
		this.add(minAttackDmgField);
		
		JLabel maxAttackDmgLabel = new JLabel("Max attack dmg:");
		JTextField maxAttackDmgField = new JTextField();
		this.add(maxAttackDmgLabel);
		this.add(maxAttackDmgField);
		
		JLabel resistancesLabel = new JLabel("Resistances:");
		JTextField resistancesField = new JTextField();
		this.add(resistancesLabel);
		this.add(resistancesField);
		
		JLabel packTriggerLabel = new JLabel("Pack trigger:");
		JTextField packTriggerField = new JTextField();
		this.add(packTriggerLabel);
		this.add(packTriggerField);
		
		JLabel packSpecialsLabel = new JLabel("Pack specials:");
		JTextField packSpecialsField = new JTextField();
		this.add(packSpecialsLabel);
		this.add(packSpecialsField);
		
		JLabel specialSoundWavLabel = new JLabel("Special sound WAV:");
		JTextField specialSoundWavField = new JTextField();
		this.add(specialSoundWavLabel);
		this.add(specialSoundWavField);
		
		ActionListener uniqueMonstersListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String monsterName = (String) comboBox.getSelectedItem();
				if(monsterName.equals("None selected")){
					nameField.setText("");
					monsterTypeField.setText("");
					namePointerField.setText("");
					trnPointerField.setText("");
					dungeonLevelField.setText("");
					hitPointsField.setText("");
					monsterAIField.setText("");
					intelligenceFactorField.setText("");
					minAttackDmgField.setText("");
					maxAttackDmgField.setText("");
					resistancesField.setText("");
					packTriggerField.setText("");
					packSpecialsField.setText("");
					specialSoundWavField.setText("");
				} else {
					UniqueMonster monster = uniqueMonsterStore.getMonsterByName(monsterName);
					nameField.setText(String.valueOf(monster.getName()));
					monsterTypeField.setText(String.valueOf(monster.getMonsterType()));
					namePointerField.setText(String.valueOf(monster.getNamePointer()));
					trnPointerField.setText(String.valueOf(monster.getTrnPointer()));
					dungeonLevelField.setText(String.valueOf(monster.getDungeonLevel()));
					hitPointsField.setText(String.valueOf(monster.getHitPoints()));
					monsterAIField.setText(String.valueOf(monster.getMonsterAI()));
					intelligenceFactorField.setText(String.valueOf(monster.getIntelligenceFactor()));
					minAttackDmgField.setText(String.valueOf(monster.getMinAttackDmg()));
					maxAttackDmgField.setText(String.valueOf(monster.getMaxAttackDmg()));
					resistancesField.setText(String.valueOf(monster.getResistances()));
					packTriggerField.setText(String.valueOf(monster.getPackTrigger()));
					packSpecialsField.setText(String.valueOf(monster.getPackSpecials()));
					specialSoundWavField.setText(String.valueOf(monster.getSpecialSoundWav()));				
				}
			}
			
		};
		comboBox.addActionListener(uniqueMonstersListener);
		
		JButton saveButton = new JButton("Cache changes");
		this.add(new JLabel());
		this.add(saveButton);
		
		ActionListener saveClick = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String monsterName = (String) comboBox.getSelectedItem();
				if(monsterName.equals("None selected")){
					//No action
				} else {
					UniqueMonster monster = uniqueMonsterStore.getMonsterByName(monsterName);
					monster.setName(nameField.getText());
					monster.setMonsterType(Long.parseLong(monsterTypeField.getText()));
					monster.setNamePointer(Long.parseLong(namePointerField.getText()));
					monster.setTrnPointer(Long.parseLong(trnPointerField.getText()));
					monster.setDungeonLevel(Integer.parseInt(dungeonLevelField.getText()));
					monster.setHitPoints(Integer.parseInt(hitPointsField.getText()));
					monster.setMonsterAI(Integer.parseInt(monsterAIField.getText()));
					monster.setIntelligenceFactor(Integer.parseInt(intelligenceFactorField.getText()));
					monster.setMinAttackDmg(Integer.parseInt(minAttackDmgField.getText()));
					monster.setMaxAttackDmg(Integer.parseInt(maxAttackDmgField.getText()));
					monster.setResistances(resistancesField.getText());
					monster.setPackTrigger(Integer.parseInt(packTriggerField.getText()));
					monster.setPackSpecials(Long.parseLong(packSpecialsField.getText()));
					monster.setSpecialSoundWav(Long.parseLong(specialSoundWavField.getText()));
				}
			}
		};
		saveButton.addActionListener(saveClick);
	}
}
