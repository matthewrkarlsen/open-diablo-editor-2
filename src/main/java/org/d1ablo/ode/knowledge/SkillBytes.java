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
 * FIXME -- remind self what this does and document
 */
public class SkillBytes {

    /* Skill numbers:
        Firebolt....... 	0100 0000 	Infravision........... 	0001 0000
        Healing........ 	0200 0000 	Phasing............... 	0002 0000
        Lightning...... 	0400 0000 	Mana Shield........... 	0004 0000
        Flash.......... 	0800 0000 	Fireball.............. 	0008 0000
        Identify....... 	1000 0000 	Guardian.............. 	0010 0000
        FireWall....... 	2000 0000 	Chain Lightning....... 	0020 0000
        Town Portal.... 	4000 0000 	Flame Wave............ 	0040 0000
        Stone Curse.... 	8000 0000 	Doom Serpents......... 	0080 0000
        Blood Ritual... 	0000 0100 	Etherealize........... 	0000 0001
        Nova........... 	0000 0200 	Item Repair........... 	0000 0002
        Invisibility... 	0000 0400 	Staff Recharge........ 	0000 0004
        Inferno........ 	0000 0800 	Trap Disarm........... 	0000 0008
        Golem.......... 	0000 1000 	Elemental............. 	0000 0010
        Rage(HF)....... 	0000 2000 	Charged Bolt.......... 	0000 0020
        Teleport....... 	0000 4000 	Holy Bolt............. 	0000 0040
        Apocalypse..... 	0000 8000 	Resurrect............. 	0000 0080
    */
    private static final byte[][] SKILL_BYTES = {
            {0,0,0,0}, //invalid					(00)
            {1,0,0,0}, //Firebolt 					(01)
            {2,0,0,0}, //Healing 					(02)
            {4,0,0,0}, //Lightning 					(03)
            {8,0,0,0}, //Flash						(04)
            {10,0,0,0}, //Identify 					(05)
            {20,0,0,0}, //Firewall 					(06)
            {40,0,0,0}, //Town Portal 				(07)
            {80,0,0,0}, //Stone curse 				(08)
            {0,1,0,0}, //Infravision 				(09)
            {0,2,0,0}, //Phasing 					(10)
            {0,4,0,0}, //Mana shield 				(11)
            {0,8,0,0}, //Fireball 					(12)
            {0,10,0,0}, //Guardian 					(13)
            {0,20,0,0}, //Chain Lightning 			(14)
            {0,40,0,0}, //Flame Wave 				(15)
            {0,80,0,0}, //Doom Serpents 				(16)
            {0,0,1,0}, //Blood ritual 				(17)
            {0,0,2,0}, //Nova 						(18)
            {0,0,4,0}, //Invisibility 				(19)
            {0,0,8,0}, //Inferno 					(20)
            {0,0,10,0}, //Golem 						(21)
            {0,0,20,0}, //Rage (HF) / Blood Boil?	(22)
            {0,0,40,0}, //Teleport					(23)
            {0,0,80,0}, //Apocalypse					(24)
            {0,0,0,1}, //Etheralize					(25)
            {0,0,0,2}, //Item Repair				(26)
            {0,0,0,4}, //Staff Recharge				(27)
            {0,0,0,8}, //Trap Disarm				(28)
            {0,0,0,10}, //Elemental					(29)
            {0,0,0,20}, //Charged Bolt				(30)
            {0,0,0,40}, //Holy Bolt					(31)
            {0,0,0,80}  //Resurrect					(32)
    };

    public static byte[][] getSkillBytesArray() {
        return SKILL_BYTES;
    }
}
