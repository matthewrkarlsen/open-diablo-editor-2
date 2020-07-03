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
 * Equip location determines whether the item can be equipped
 * and, if so, in which location it may be equipped. An
 * equip location of 07 indicates the item cannot be equipped.
 * Equip location values of 01 to 06 indicate the item
 * may be equipped in the relevant slot. Items
 * given an equip location value of 02 occupy both hand slots.
 */
public class EquipLocations {

    public static final String[] EQUIP_LOCATIONS = {
            "Invalid",									    //00
            "One handed",				                    //01
            "Two handed",	                                //02
            "Body",											//03
            "Head",											//04
            "Ring",											//05
            "Amulet",										//06
            "Cannot be equipped" 							//07
    };
}
