/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.txt for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */
package org.d1ablo.ode.gui;

import org.d1ablo.ode.stores.CompleteStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Responsible for assembling the GUI and toggling display of the main {@link JFrame}.
 */
public class DesktopODE {

	private final CompleteStore completeStore;

	public DesktopODE(CompleteStore completeStore) {
		this.completeStore = completeStore;
	}
	
	public void displayGUI(){
		
		JFrame jFrame = new JFrame();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension d = new Dimension(600, 450);
		jFrame.setMinimumSize(d);
		
		JMenuBar jMenuBar = new JMenuBar();
		jFrame.setJMenuBar(jMenuBar);
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem saveItem = new JMenuItem("Save");
		saveItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				completeStore.writeAllData();
			}
		});
		fileMenu.add(saveItem);
		
		jMenuBar.add(fileMenu);
		
		JPanel baseItemsPanel = new BaseItemsPanel(completeStore.getBaseItemStore());
		JPanel baseMonstersPanel = new BaseMonstersPanel(completeStore.getBaseMonsterStore());
		JPanel characterPanel = new CharacterPanel(completeStore.getCharacterStore());
		JPanel itemModifierPanel = new ItemModifierPanel(completeStore.getModifierStore());
		JPanel questsPanel = new QuestsPanel(completeStore.getQuestStore());
		JPanel shrinesPanel = new ShrinesPanel(completeStore.getShrineStore());
		JPanel spellsPanel = new SpellsPanel(completeStore.getSpellStore());
		JPanel uniqueItemsPanel = new UniqueItemsPanel(completeStore.getUniqueItemStore());
		JPanel uniqueMonstersPanel = new UniqueMonstersPanel(completeStore.getUniqueMonsterStore());
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Base Items", baseItemsPanel);
		tabbedPane.addTab("Base Monsters", baseMonstersPanel);
		tabbedPane.addTab("Characters", characterPanel);
		tabbedPane.addTab("Prefixes and Suffixes", itemModifierPanel);
		tabbedPane.addTab("Quests", questsPanel);
		tabbedPane.addTab("Shrines", shrinesPanel);
		tabbedPane.addTab("Spells", spellsPanel);
		tabbedPane.addTab("Unique Items", uniqueItemsPanel);
		tabbedPane.addTab("Unique Monsters", uniqueMonstersPanel);
		jFrame.add(tabbedPane);
		
		jFrame.pack();
		jFrame.setVisible(true);
	}
}
