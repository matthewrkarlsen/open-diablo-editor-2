/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.md for full contributor list.
 *
 * This file is EXCLUDED from the tri-license that the majority of the project files are released under.
 *
 * The file contains information obtained from the Mod Workshop by Charlie and Jarulf (see ACKNOWLEDGEMENTS.txt).
 * The exact terms of use for this information are somewhat ambiguous.
 */
package org.d1ablo.ode.knowledge;

/**
 * The item's graphic value is taken from the range
 * 00 to 168 (0xA8) and indicates how the item appears
 * in the character's inventory.
 */
public class ItemGraphicValues {

    public static final String[] GRAPHIC_VALUES = {
            "Potion of Full Mana",				            //00 (00)
            "White Scroll",						            //01 (01)
            "Brown Scroll",						            //02 (02)
            "Blue Scroll",						            //03 (03)
            "Small Gold Pile",					            //04 (04)
            "Medium Gold Pile",					            //05 (05)
            "Large Gold Pile",					            //06 (06)
            "Thick Gold Ring",					            //07 (07)
            "The Bleeder",						            //08 (08)
            "Bramble",							            //09 (09)
            "Ring of Truth",					            //10 (0A)
            "Ring of Regha",					            //11 (0B)
            "Ring",								            //12 (0C)
            "Ring of Engagement",				            //13 (0D)
            "Constricting Ring",				            //14 (0E)
            "Spectral Elixir",					            //15 (0F)
            "Rainbow Elixir",					            //16 (10)
            "Golden Elixir",					            //17 (11)
            "Empyrean Band",					            //18 (12)
            "Ear w/ steel earring",				            //19 (13)
            "Ear",								            //20 (14)
            "Ear w/ gold earring",				            //21 (15)
            "orange sphere",					            //22 (16)
            "Hellraiser Box",					            //23 (17)
            "gold pyramid",						            //24 (18)
            "Blood Stone",						            //25 (19)
            "red sphere",						            //26 (1A)
            "blue cube",						            //27 (1B)
            "orange pyramid",					            //28 (1C)
            "thin black vial",					            //29 (1D)
            "large black vial",					            //30 (1E)
            "Elixir of Vitality",				            //31 (1F)
            "Potion of Healing",				            //32 (20)
            "Potion of Full Rejuvenation",		            //33 (21)
            "Elixir of Magic",					            //34 (22)
            "Potion of Full Healing",			            //35 (23)
            "Elixir of Dexterity",				            //36 (24)
            "Potion of Rejuvenation",			            //37 (25)
            "Elixir of Strength",				            //38 (26)
            "Potion of Mana, Potion of Mana",	            //39 (27)
            "Brain",							            //40 (28)
            "Claw",								            //41 (29)
            "Tooth",							            //42 (2A)
            "loaf of bread",					            //43 (2B)
            "Optic Amulet",						            //44 (2C)
            "Amulet",							            //45 (2D)
            "black pendant",					            //46 (2E)
            "bug brooch",						            //47 (2F)
            "ankh necklace",					            //48 (30)
            "pouch",							            //49 (31)
            "Wizardspike",						            //50 (32)
            "Dagger",							            //51 (33)
            "bottle of brew",					            //52 (34)
            "Black Razor",						            //53 (35)
            "thick dagger",						            //54 (36)
            "knuckle knife",					            //55 (37)
            "Blade",							            //56 (38)
            "Bastard Sword",					            //57 (39)
            "The Executioner's Blade",			            //58 (3A)
            "Mace",								            //59 (3B)
            "Long Sword",						            //60 (3C)
            "Broad Sword",						            //61 (3D)
            "Falchion",							            //62 (3E)
            "Morning Star",						            //63 (3F)
            "Short Sword",						            //64 (40)
            "Claymore",							            //65 (41)
            "Club",								            //66 (42)
            "Sabre",							            //67 (43)
            "Gryphons Claw, The Falcon's Talon",            //68 (44)
            "bone club",						            //69 (45)
            "Spiked Club",						            //70 (46)
            "board w/ nail",					            //71 (47)
            "Scimitar",							            //72 (48)
            "angel sword",						            //73 (49)
            "rusted, grey sword",				            //74 (4A)
            "Full Helm",						            //75 (4B)
            "Magic Rock",						            //76 (4C)
            "Helm of Sprits",					            //77 (4D)
            "skull crown",						            //78 (4E)
            "Royal Circlet",					            //79 (4F)
            "Fool's Crest",						            //80 (50)
            "Harlequin Crest",					            //81 (51)
            "Helm",								            //82 (52)
            "Buckler",							            //83 (53)
            "horned helm",						            //84 (54)
            "Veil of Steel, Gotterdamerung",	            //85 (55)
            "black book",						            //86 (56)
            "red book",							            //87 (57)
            "blue book",						            //88 (58)
            "Black Mushroom",					            //89 (59)
            "Skull Cap",						            //90 (5A)
            "Cap",								            //91 (5B)
            "Torn Flesh of Souls",				            //92 (5C)
            "Thinking Cap",						            //93 (5D)
            "red tunic",						            //94 (5E)
            "Crown",							            //95 (5F)
            "Map of the Stars",					            //96 (60)
            "Fungal Tome",						            //97 (61)
            "Great Helm",						            //98 (62)
            "Overlord's Helm",					            //99 (63)
            "lightning shield",					            //100 (64)
            "Battle Axe",						            //101 (65)
            "Hunter's Bow, Long Bow",			            //102 (66)
            "Plate Mail, Field Plate",			            //103 (67)
            "Stonecleaver",						            //104 (68)
            "Small Shield",						            //105 (69)
            "Cleaver",							            //106 (6A)
            "Studded Leather Armor",			            //107 (6B)
            "Deadly Hunter, Eaglehorn",			            //108 (6C)
            "Short Staff, Quarter Staff",		            //109 (6D)
            "Two-Handed Sword",					            //110 (6E)
            "Chain Mail",						            //111 (6F)
            "Small Axe",						            //112 (70)
            "Kite Shield",						            //113 (71)
            "Scale Mail",						            //114 (72)
            "large, thick shield",				            //115 (73)
            "Split Skull Shield",				            //116 (74)
            "Dragon's Breach",					            //117 (75)
            "Short Bow, Short Bow",				            //118 (76)
            "Long Battle Bow, Long War Bow",	            //119 (77)
            "fish bow",							            //120 (78)
            "War Hammer",						            //121 (79)
            "Maul, The Cranium Basher",			            //122 (7A)
            "Long Staff",						            //123 (7B)
            "War Staff",						            //124 (7C)
            "shaman's staff",					            //125 (7D)
            "Tavern Sign",						            //126 (7E)
            "Hard Leather Armor",				            //127 (7F)
            "Rags",								            //128 (80)
            "Quilted Armor",					            //129 (81)
            "3 spiked balls 'n chain flail",	            //130 (82)
            "Flail",							            //131 (83)
            "Tower Shield",						            //132 (84)
            "Composite Bow",					            //133 (85)
            "Great Sword",						            //134 (86)
            "Leather Armor",					            //135 (87)
            "Splint Mail",						            //136 (88)
            "Robe",								            //137 (89)
            "The Rainbow Cloak, Wisdom's Wrap, Nightscape", //138 (8A)
            "leather shirt",					            //139 (8B)
            "Anvil of Fury",					            //140 (8C)
            "Broad Axe",						            //141 (8D)
            "Large Axe",						            //142 (8E)
            "Great Axe",						            //143 (8F)
            "Axe",								            //144 (90)
            "grey cleaver",						            //145 (91)
            "Blackoak Shield, Holy Defender",	            //146 (92)
            "Large Shield",						            //147 (93)
            "Gothic Shield",					            //148 (94)
            "Cloak",							            //149 (95)
            "Cape",								            //150 (96)
            "Full Plate Mail",					            //151 (97)
            "Gothic Plate",						            //152 (98)
            "Breast Plate",						            //153 (99)
            "Ring Mail",						            //154 (9A)
            "Staff of Lazarus",					            //155 (9B)
            "ruby axe",							            //156 (9C)
            "Arkaine's Valor",					            //157 (9D)
            "The Needler",						            //158 (9E)
            "Naj's Light Plate",				            //159 (9F)
            "The Grizzly",						            //160 (A0)
            "The Grandfather",					            //161 (A1)
            "The Protector",					            //162 (A2)
            "Messerschmidt's Reaver",			            //163 (A3)
            "Windforce",						            //164 (A4)
            "Short War Bow",					            //165 (A5)
            "Composite Staff",					            //166 (A6)
            "Short Battle Bow",					            //167 (A7)
            "Gold"								            //168 (A8)
    };
}
