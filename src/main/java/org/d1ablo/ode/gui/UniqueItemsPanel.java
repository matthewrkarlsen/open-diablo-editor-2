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

import org.d1ablo.ode.stores.UniqueItemStore;
import org.d1ablo.ode.types.UniqueItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The panel that permits editing of unique items.
 */
public class UniqueItemsPanel extends JPanel {

	private UniqueItemStore uniqueItemStore;

	/**
	 * Construct a new instance
	 * @param uniqueItemStore the data store for all of the unique items in the game
	 */
	public UniqueItemsPanel(UniqueItemStore uniqueItemStore)
	{
		super();
		this.uniqueItemStore = uniqueItemStore;
		
		GridLayout layout = new GridLayout(0,2);
		this.setLayout(layout);
		
		String[] uItemNames = uniqueItemStore.getItemNames();
		String[] comboBoxStrings = new String[uItemNames.length + 1]; 
		comboBoxStrings[0] = "None selected";
		System.arraycopy(uItemNames, 0, comboBoxStrings, 1, uItemNames.length);
		JComboBox<String> comboBox = new JComboBox<String>(comboBoxStrings);
		comboBox.setSelectedIndex(0);
		
		JLabel cbLabel = new JLabel("Unique item selected:");
		this.add(cbLabel);
		this.add(comboBox);
		
		JLabel nameLabel = new JLabel("Name:");
		JTextField nameField = new JTextField();
		this.add(nameLabel);
		this.add(nameField);
		
		JLabel namePointerLabel = new JLabel("Name pointer:");
		JTextField namePointerField = new JTextField();
		this.add(namePointerLabel);
		this.add(namePointerField);
		
		JLabel itemTypeLabel = new JLabel("Item type:");
		JTextField itemTypeField = new JTextField();
		this.add(itemTypeLabel);
		this.add(itemTypeField);
		
		JLabel qualityLevelLabel = new JLabel("Quality level:");
		JTextField qualityLevelField = new JTextField();
		this.add(qualityLevelLabel);
		this.add(qualityLevelField);
		
		JLabel numberOfEffectsLabel = new JLabel("Number of effects:");
		JTextField numberOfEffectsField = new JTextField();
		this.add(numberOfEffectsLabel);
		this.add(numberOfEffectsField);
		
		JLabel effect1NumberLabel = new JLabel("Effect number:");
		JTextField effect1NumberField = new JTextField();
		this.add(effect1NumberLabel);
		this.add(effect1NumberField);
		
		JLabel effect1NameLabel = new JLabel("Effect:");
		JTextField effect1NameField = new JTextField();
		this.add(effect1NameLabel);
		this.add(effect1NameField);
		
		JLabel effect1MinValueLabel = new JLabel("Min value:");
		JTextField effect1MinValueField = new JTextField();
		this.add(effect1MinValueLabel);
		this.add(effect1MinValueField);
		
		JLabel effect1MaxValueLabel = new JLabel("Max value");
		JTextField effect1MaxValueField = new JTextField();
		this.add(effect1MaxValueLabel);
		this.add(effect1MaxValueField);
		
		JLabel effect2NumberLabel = new JLabel("Effect number:");
		JTextField effect2NumberField = new JTextField();
		this.add(effect2NumberLabel);
		this.add(effect2NumberField);
		
		JLabel effect2NameLabel = new JLabel("Effect:");
		JTextField effect2NameField = new JTextField();
		this.add(effect2NameLabel);
		this.add(effect2NameField);
		
		JLabel effect2MinValueLabel = new JLabel("Min value:");
		JTextField effect2MinValueField = new JTextField();
		this.add(effect2MinValueLabel);
		this.add(effect2MinValueField);
		
		JLabel effect2MaxValueLabel = new JLabel("Max value");
		JTextField effect2MaxValueField = new JTextField();
		this.add(effect2MaxValueLabel);
		this.add(effect2MaxValueField);
		
		JLabel effect3NumberLabel = new JLabel("Effect number:");
		JTextField effect3NumberField = new JTextField();
		this.add(effect3NumberLabel);
		this.add(effect3NumberField);
		
		JLabel effect3NameLabel = new JLabel("Effect:");
		JTextField effect3NameField = new JTextField();
		this.add(effect3NameLabel);
		this.add(effect3NameField);
		
		JLabel effect3MinValueLabel = new JLabel("Min value:");
		JTextField effect3MinValueField = new JTextField();
		this.add(effect3MinValueLabel);
		this.add(effect3MinValueField);
		
		JLabel effect3MaxValueLabel = new JLabel("Max value");
		JTextField effect3MaxValueField = new JTextField();
		this.add(effect3MaxValueLabel);
		this.add(effect3MaxValueField);
		
		JLabel effect4NumberLabel = new JLabel("Effect number:");
		JTextField effect4NumberField = new JTextField();
		this.add(effect4NumberLabel);
		this.add(effect4NumberField);
		
		JLabel effect4NameLabel = new JLabel("Effect:");
		JTextField effect4NameField = new JTextField();
		this.add(effect4NameLabel);
		this.add(effect4NameField);
		
		JLabel effect4MinValueLabel = new JLabel("Min value:");
		JTextField effect4MinValueField = new JTextField();
		this.add(effect4MinValueLabel);
		this.add(effect4MinValueField);
		
		JLabel effect4MaxValueLabel = new JLabel("Max value");
		JTextField effect4MaxValueField = new JTextField();
		this.add(effect4MaxValueLabel);
		this.add(effect4MaxValueField);
		
		JLabel effect5NumberLabel = new JLabel("Effect number:");
		JTextField effect5NumberField = new JTextField();
		this.add(effect5NumberLabel);
		this.add(effect5NumberField);
		
		JLabel effect5NameLabel = new JLabel("Effect:");
		JTextField effect5NameField = new JTextField();
		this.add(effect5NameLabel);
		this.add(effect5NameField);
		
		JLabel effect5MinValueLabel = new JLabel("Min value:");
		JTextField effect5MinValueField = new JTextField();
		this.add(effect5MinValueLabel);
		this.add(effect5MinValueField);
		
		JLabel effect5MaxValueLabel = new JLabel("Max value");
		JTextField effect5MaxValueField = new JTextField();
		this.add(effect5MaxValueLabel);
		this.add(effect5MaxValueField);
		
		JLabel effect6NumberLabel = new JLabel("Effect number:");
		JTextField effect6NumberField = new JTextField();
		this.add(effect6NumberLabel);
		this.add(effect6NumberField);
		
		JLabel effect6NameLabel = new JLabel("Effect:");
		JTextField effect6NameField = new JTextField();
		this.add(effect6NameLabel);
		this.add(effect6NameField);
		
		JLabel effect6MinValueLabel = new JLabel("Min value:");
		JTextField effect6MinValueField = new JTextField();
		this.add(effect6MinValueLabel);
		this.add(effect6MinValueField);
		
		JLabel effect6MaxValueLabel = new JLabel("Max value");
		JTextField effect6MaxValueField = new JTextField();
		this.add(effect6MaxValueLabel);
		this.add(effect6MaxValueField);
		
		ActionListener uniqueItemsListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String uniqueItemName = (String) comboBox.getSelectedItem();
				if(uniqueItemName.equals("None selected")){
					nameField.setText("");
					namePointerField.setText("");
					itemTypeField.setText("");
					qualityLevelField.setText("");
					numberOfEffectsField.setText("");
					effect1NumberField.setText("");
					effect1NameField.setText("");
					effect1MinValueField.setText("");
					effect1MaxValueField.setText("");
					effect2NumberField.setText("");
					effect2NameField.setText("");
					effect2MinValueField.setText("");
					effect2MaxValueField.setText("");
					effect3NumberField.setText("");
					effect3NameField.setText("");
					effect3MinValueField.setText("");
					effect3MaxValueField.setText("");
					effect4NumberField.setText("");
					effect4NameField.setText("");
					effect4MinValueField.setText("");
					effect4MaxValueField.setText("");
					effect5NumberField.setText("");
					effect5NameField.setText("");
					effect5MinValueField.setText("");
					effect5MaxValueField.setText("");
					effect6NumberField.setText("");
					effect6NameField.setText("");
					effect6MinValueField.setText("");
					effect6MaxValueField.setText("");
				} else {
					UniqueItem item = uniqueItemStore.getUniqueItemByName(uniqueItemName);
					nameField.setText(String.valueOf(item.getName()));
					namePointerField.setText(String.valueOf(item.getNamePointer()));
					itemTypeField.setText(String.valueOf(item.getItemType()));
					qualityLevelField.setText(String.valueOf(item.getQualityLevel()));
					numberOfEffectsField.setText(String.valueOf(item.getNumberOfEffects()));
					effect1NumberField.setText(String.valueOf(item.getEffectNumber(1)));
					effect1NameField.setText(String.valueOf(item.getEffectName(1)));
					effect1MinValueField.setText(String.valueOf(item.getMinValue(1)));
					effect1MaxValueField.setText(String.valueOf(item.getMaxValue(1)));
					effect2NumberField.setText(String.valueOf(item.getEffectNumber(2)));
					effect2NameField.setText(String.valueOf(item.getEffectName(2)));
					effect2MinValueField.setText(String.valueOf(item.getMinValue(2)));
					effect2MaxValueField.setText(String.valueOf(item.getMaxValue(2)));
					effect3NumberField.setText(String.valueOf(item.getEffectNumber(3)));
					effect3NameField.setText(String.valueOf(item.getEffectName(3)));
					effect3MinValueField.setText(String.valueOf(item.getMinValue(3)));
					effect3MaxValueField.setText(String.valueOf(item.getMaxValue(3)));
					effect4NumberField.setText(String.valueOf(item.getEffectNumber(4)));
					effect4NameField.setText(String.valueOf(item.getEffectName(4)));
					effect4MinValueField.setText(String.valueOf(item.getMinValue(4)));
					effect4MaxValueField.setText(String.valueOf(item.getMaxValue(4)));
					effect5NumberField.setText(String.valueOf(item.getEffectNumber(5)));
					effect5NameField.setText(String.valueOf(item.getEffectName(5)));
					effect5MinValueField.setText(String.valueOf(item.getMinValue(5)));
					effect5MaxValueField.setText(String.valueOf(item.getMaxValue(5)));
					effect6NumberField.setText(String.valueOf(item.getEffectNumber(6)));
					effect6NameField.setText(String.valueOf(item.getEffectName(6)));
					effect6MinValueField.setText(String.valueOf(item.getMinValue(6)));
					effect6MaxValueField.setText(String.valueOf(item.getMaxValue(6)));	
				}
			}
			
		};
		comboBox.addActionListener(uniqueItemsListener);
		
		JButton saveButton = new JButton("Cache changes");
		this.add(new JLabel());
		this.add(saveButton);
		
		ActionListener saveClick = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String uniqueItemName = (String) comboBox.getSelectedItem();
				if(uniqueItemName.equals("None selected")){
					//No action
				} else {
					UniqueItem item = uniqueItemStore.getUniqueItemByName(uniqueItemName);
					item.setName(nameField.getText());
					item.setNamePointer(Long.parseLong(namePointerField.getText()));
					item.setItemType(Integer.parseInt(itemTypeField.getText()));
					item.setQualityLevel(Integer.parseInt(qualityLevelField.getText()));
					item.setNumberOfEffects(Integer.parseInt(numberOfEffectsField.getText()));

					item.setEffect(Long.parseLong(effect1NumberField.getText()), 1);
					item.setMinValue(Long.parseLong(effect1MinValueField.getText()), 1);
					item.setMaxValue(Long.parseLong(effect1MaxValueField.getText()), 1);

					item.setEffect(Long.parseLong(effect1NumberField.getText()), 2);
					item.setMinValue(Long.parseLong(effect1MinValueField.getText()), 2);
					item.setMaxValue(Long.parseLong(effect1MaxValueField.getText()), 2);

					item.setEffect(Long.parseLong(effect1NumberField.getText()), 3);
					item.setMinValue(Long.parseLong(effect1MinValueField.getText()), 3);
					item.setMaxValue(Long.parseLong(effect1MaxValueField.getText()), 3);

					item.setEffect(Long.parseLong(effect1NumberField.getText()), 4);
					item.setMinValue(Long.parseLong(effect1MinValueField.getText()), 4);
					item.setMaxValue(Long.parseLong(effect1MaxValueField.getText()), 4);

					item.setEffect(Long.parseLong(effect1NumberField.getText()), 5);
					item.setMinValue(Long.parseLong(effect1MinValueField.getText()), 5);
					item.setMaxValue(Long.parseLong(effect1MaxValueField.getText()), 5);

					item.setEffect(Long.parseLong(effect1NumberField.getText()), 6);
					item.setMinValue(Long.parseLong(effect1MinValueField.getText()), 6);
					item.setMaxValue(Long.parseLong(effect1MaxValueField.getText()), 6);
				}
			}
		};
		saveButton.addActionListener(saveClick);
	}
}
