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
 * Provides human-readable descriptions of each activation trigger setting for base items.
 * Activation trigger determines the base chance of the item dropping during dungeon exploration.
 */
public class BaseItemActivationTriggers {

    public static final String[] BASE_ITEM_ACTIVATION_TRIGGERS = {
        "Item will never be found",		//00
        "Item will be findable",		//01
        "Findable, 2X occurance rate"	//02
    };
}
