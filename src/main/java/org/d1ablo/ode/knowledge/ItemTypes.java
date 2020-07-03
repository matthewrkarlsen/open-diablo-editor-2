/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.txt for full contributor list.
 *
 * This file is EXCLUDED from the tri-license that the majority of the project files are released under.
 *
 * The file contains information obtained from the Mod Workshop by Charlie and Jarulf (see ACKNOWLEDGEMENTS.txt).
 * The exact terms of use for this information are somewhat ambiguous.
 */
package org.d1ablo.ode.knowledge;

/**
 * Textual descriptions for each item type.
 */
public class ItemTypes {

    /**
     * Textual descriptions for each 'coarse' item type.
     * Item type ranges from 01 to 05.
     */
    public static final String[] BROAD_ITEM_TYPES = {
            "invalid",					    //00
            "Weapons",					    //01
            "All armors",				    //02
            "Jewelry, potion, scrolls",	    //03
            "Gold",						    //04
            "Novelty items"				    //05
    };

    /**
     * Textual descriptions for each 'fine grained' item type.
     * Item type ranges from 0 (0x01) to 68 (0x44).
     */
    public static final String[] UNIQUE_ITEM_TYPES_ARRAY = {
            "Invalid",                      //00
            "Short Bow",                    //01
            "Long Bow",                     //02
            "Hunter Bow",                   //03
            "Composite Bow",                //04
            "Long War Bow",                 //05
            "Long Battle Bow",              //06
            "Dagger",                       //07
            "Falchion",                     //08
            "Claymore",                     //09
            "Broad Sword",                  //0A
            "Sabre",                        //0B
            "Scimitar",                     //0C
            "Long Sword",                   //0D
            "Bastard Sword",                //0E
            "Two Handed Sword",             //0F
            "Great Sword",                  //10
            "Cleaver",                      //11
            "Large Axe",                    //12
            "Broad Axe",                    //13
            "Small Axe",                    //14
            "Battle Axe",                   //15
            "Great Axe",                    //16
            "Mace",                         //17
            "Morning Star",                 //18
            "Club , Spiked Club",           //19
            "Maul",                         //1A
            "War Hammer",                   //1B
            "Flail",                        //1C
            "Long Staff",                   //1D
            "Short Staff",                  //1E
            "Composite Staff",              //1F
            "Quarter Staff",                //20
            "War Staff",                    //21
            "Skull Cap",                    //22
            "Helm",                         //23
            "Great Helm",                   //24
            "Crown",                        //25
            "broken, do not use",           //26
            "Rags",                         //27
            "Studded Leather Armor",        //28
            "Cloak",                        //29
            "Robe",                         //2A
            "Chain Mail",                   //2B
            "Leather Armor",                //2C
            "Breast Plate",                 //2D
            "Cape",                         //2E
            "Plate Mail",                   //2F
            "Full Plate Mail",              //30
            "Buckler",                      //31
            "Small Shield",                 //32
            "Large Shield",                 //33
            "Kite Shield",                  //34
            "Tower Shield, Gothic Shield",  //35
            "Ring",                         //36
            "some phucked up scroll",       //37
            "Amulet",                       //38
            "Undead Crown",                 //39
            "Empyrean Band",                //3A
            "Optic Amulet",                 //3B
            "Ring of Truth",                //3C
            "Harlequin Crest",              //3D
            "Map of the Stars",             //3E
            "Golden Elixir",                //3F
            "Arkaine's Valor",              //40
            "Veil of Steel",                //41
            "Griswold's Edge",              //42
            "Lightforge",                   //43
            "Staff of Lazarus"              //44
    };
}
