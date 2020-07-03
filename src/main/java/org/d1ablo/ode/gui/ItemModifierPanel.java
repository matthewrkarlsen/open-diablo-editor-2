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

import org.d1ablo.ode.stores.ItemModifiersStore;
import org.d1ablo.ode.types.ItemModifier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The panel that permits editing of item modifiers.
 */
public class ItemModifierPanel extends JPanel {

	private ItemModifiersStore modifierStore;

	/**
	 * Construct a new instance
	 * @param modifierStore the data store for all of the item modifiers in the game
	 */
	public ItemModifierPanel(ItemModifiersStore modifierStore)
	{
		super();
		this.modifierStore = modifierStore;
		
		GridLayout layout = new GridLayout(0,2);
		this.setLayout(layout);
		
		String[] itemModNames = modifierStore.getModifierNames();
		String[] comboBoxStrings = new String[itemModNames.length + 1]; 
		comboBoxStrings[0] = "None selected";
		System.arraycopy(itemModNames, 0, comboBoxStrings, 1, itemModNames.length);
		JComboBox<String> modifierComboBox = new JComboBox<String>(comboBoxStrings);
		modifierComboBox.setSelectedIndex(0);
		JLabel modifierCBLabel = new JLabel("Item modifier selected:");
		this.add(modifierCBLabel);
		this.add(modifierComboBox);
		
		JLabel nameLabel = new JLabel("Modifier name:");
		JTextField nameField = new JTextField();
		this.add(nameLabel);
		this.add(nameField);
		
		JLabel namePointerLabel = new JLabel("Name pointer:");
		JTextField namePointerField = new JTextField();
		this.add(namePointerLabel);
		this.add(namePointerField);
		
		JLabel itemEffects1Label = new JLabel("Item effects 1:");
		JTextField itemEffects1Field = new JTextField();
		this.add(itemEffects1Label);
		this.add(itemEffects1Field);
		
		JLabel itemEffects2Label = new JLabel("Item effects 2:");
		JTextField itemEffects2Field = new JTextField();
		this.add(itemEffects2Label);
		this.add(itemEffects2Field);
		
		JLabel itemEffects3Label = new JLabel("Item effects 3:");
		JTextField itemEffects3Field = new JTextField();
		this.add(itemEffects3Label);
		this.add(itemEffects3Field);
		
		JLabel itemEffects4Label = new JLabel("Item effects 4:");
		JTextField itemEffects4Field = new JTextField();
		this.add(itemEffects4Label);
		this.add(itemEffects4Field);
		
		JLabel minimumEffectValueLabel = new JLabel("Minimum effect value:");
		JTextField minimumEffectValueField = new JTextField();
		this.add(minimumEffectValueLabel);
		this.add(minimumEffectValueField);
		
		JLabel maximumEffectValueLabel = new JLabel("Max effect value:");
		JTextField maximumEffectValueField = new JTextField();
		this.add(maximumEffectValueLabel);
		this.add(maximumEffectValueField);
		
		JLabel qualityLevelLabel = new JLabel("Quality level:");
		JTextField qualityLevelField = new JTextField();
		this.add(qualityLevelLabel);
		this.add(qualityLevelField);
		
		JLabel occurencePossibilitiesLabel = new JLabel("Occurence possibilities:");
		JTextField occurencePossibilitiesField = new JTextField();
		this.add(occurencePossibilitiesLabel);
		this.add(occurencePossibilitiesField);
		
		JLabel byteTwentyThreeLabel = new JLabel("Byte twentythree");
		JTextField byteTwentyThreeField = new JTextField();
		this.add(byteTwentyThreeLabel);
		this.add(byteTwentyThreeField);
		
		JLabel excludedComboIndicatorLabel = new JLabel("Excluded combo indicator:");
		JTextField excludedComboIndicatorField = new JTextField();
		this.add(excludedComboIndicatorLabel);
		this.add(excludedComboIndicatorField);
		
		JLabel cursedIndicatorLabel = new JLabel("Cursed indicator:");
		JTextField cursedIndicatorField = new JTextField();
		this.add(cursedIndicatorLabel);
		this.add(cursedIndicatorField);
		
		JLabel minGoldLabel = new JLabel("Min gold:");
		JTextField minGoldField = new JTextField();
		this.add(minGoldLabel);
		this.add(minGoldField);
		
		JLabel maxGoldLabel = new JLabel("Max gold:");
		JTextField maxGoldField = new JTextField();
		this.add(maxGoldLabel);
		this.add(maxGoldField);
		
		JLabel valueMultiplierLabel = new JLabel("Value multiplier:");
		JTextField valueMultiplierField = new JTextField();
		this.add(valueMultiplierLabel);
		this.add(valueMultiplierField);
		
		ActionListener modifierListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String actionCommand = e.getActionCommand();
				String modifierName = (String) modifierComboBox.getSelectedItem();
		        if(modifierName.equals("None selected")){
		        	nameField.setText("");
		        	namePointerField.setText("");
		        	itemEffects1Field.setText("");
		        	itemEffects2Field.setText("");
		        	itemEffects3Field.setText("");
		        	itemEffects4Field.setText("");
		        	minimumEffectValueField.setText("");
		        	maximumEffectValueField.setText("");
		        	qualityLevelField.setText("");
		        	occurencePossibilitiesField.setText("");
		        	byteTwentyThreeField.setText("");
		        	excludedComboIndicatorField.setText("");
		        	cursedIndicatorField.setText("");
		        	minGoldField.setText("");
		        	maxGoldField.setText("");
		        	valueMultiplierField.setText("");
		        } else {
		        	ItemModifier modifier = modifierStore.getModifierByName(modifierName);
		        	nameField.setText(String.valueOf(modifier.getName()));
		        	namePointerField.setText(String.valueOf(modifier.getNamePointer()));
		        	itemEffects1Field.setText(String.valueOf(modifier.getItemEffectOne()));
		        	itemEffects2Field.setText(String.valueOf(modifier.getItemEffectTwo()));
		        	itemEffects3Field.setText(String.valueOf(modifier.getItemEffectThree()));
		        	itemEffects4Field.setText(String.valueOf(modifier.getItemEffectFour()));
		        	minimumEffectValueField.setText(String.valueOf(modifier.getMinimumEffectValue()));
		        	maximumEffectValueField.setText(String.valueOf(modifier.getMaximumEffectValue()));
		        	qualityLevelField.setText(String.valueOf(modifier.getQualityLevel()));
		        	occurencePossibilitiesField.setText(String.valueOf(modifier.getOccurrencePossibilities()));
		        	byteTwentyThreeField.setText(String.valueOf(modifier.getByteTwentyThree()));
		        	excludedComboIndicatorField.setText(String.valueOf(modifier.getExcludedComboIndicator()));
		        	cursedIndicatorField.setText(String.valueOf(modifier.getCursedIndicator()));
		        	minGoldField.setText(String.valueOf(modifier.getMinGold()));
		        	maxGoldField.setText(String.valueOf(modifier.getMaxGold()));
		        	valueMultiplierField.setText(String.valueOf(modifier.getValueMultiplier()));
		        }
			}	
		};
		modifierComboBox.addActionListener(modifierListener);
		
		JButton saveButton = new JButton("Cache changes");
		this.add(new JLabel());
		this.add(saveButton);
		
		ActionListener saveClick = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String actionCommand = e.getActionCommand();
				String modifierName = (String) modifierComboBox.getSelectedItem();
				if(modifierName.equals("None selected")){
					//Do nothing
				} else {
					ItemModifier modifier = modifierStore.getModifierByName(modifierName);

					modifier.setName(nameField.getText());
					modifier.setNamePointer(Long.parseLong(namePointerField.getText()));
					modifier.setItemEffectOne(Byte.parseByte(itemEffects1Field.getText()));
					modifier.setItemEffectTwo(Byte.parseByte(itemEffects2Field.getText()));
					modifier.setItemEffectThree(Byte.parseByte(itemEffects3Field.getText()));
					modifier.setItemEffectFour(Byte.parseByte(itemEffects4Field.getText()));
					modifier.setMinimumEffectValue(Long.parseLong(minimumEffectValueField.getText()));
					modifier.setMaximumEffectValue(Long.parseLong(maximumEffectValueField.getText()));
					modifier.setQualityLevel(Long.parseLong(qualityLevelField.getText()));
					modifier.setOccurrencePossibilities(occurencePossibilitiesField.getText());
					modifier.setByteTwentyThree(Integer.parseInt(byteTwentyThreeField.getText()));
					modifier.setExcludedComboIndicator(excludedComboIndicatorField.getText());
					modifier.setCursedIndicator(Long.parseLong(cursedIndicatorField.getText()));
					modifier.setMinGold(Long.parseLong(minGoldField.getText()));
					modifier.setMaxGold(Long.parseLong(maxGoldField.getText()));
					modifier.setValueMultiplier(Long.parseLong(valueMultiplierField.getText()));
				}
			}
		};
		saveButton.addActionListener(saveClick);
	}
}
