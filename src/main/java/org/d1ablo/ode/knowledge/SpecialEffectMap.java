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

import java.util.HashMap;
import java.util.Map;

/**
 * FIXME -- remind self what this does and document
 */
public class SpecialEffectMap {

    private static Map<Integer,String> specialEffectMap = null;

    public static Map<Integer,String> getSpecialEffectMap(){
        constructMapIfNull();
        return specialEffectMap;
    }

    public static boolean containsKey(int key) {
        constructMapIfNull();
        return specialEffectMap.containsKey(key);
    }

    private static void constructMapIfNull() {
        if (specialEffectMap == null) {
            specialEffectMap = constructSpecialEffectMap();
        }
    }

    private static Map<Integer,String> constructSpecialEffectMap() {
        Map<Integer,String> specialEffectMap = new HashMap<>();
        specialEffectMap.put(0x00000000, "No special effect");
        specialEffectMap.put(0x01000000, "Infravision");
        specialEffectMap.put(0x02000000, "Random Life Stealing");
        specialEffectMap.put(0x04000000, "Random Speed Arrows");
        specialEffectMap.put(0x08000000, "Burning Damage");
        specialEffectMap.put(0x10000000, "Extra Fire Damage");
        specialEffectMap.put(0x20000000, "Extra Lighning Damage");
        specialEffectMap.put(0x40000000, "Cursed Hit Points");
        specialEffectMap.put(0x80000000, "Effect Unknown");
        specialEffectMap.put(0x00010000, "User can't heal");
        specialEffectMap.put(0x00020000, "Effect Unknown");
        specialEffectMap.put(0x00040000, "Effect Unknown");
        specialEffectMap.put(0x00080000, "Knock Target Back");
        specialEffectMap.put(0x00100000, "Hit monster doesn't heal");
        specialEffectMap.put(0x00200000, "Steal 3% Mana");
        specialEffectMap.put(0x00400000, "Steal 5% Mana");
        specialEffectMap.put(0x00800000, "Steal 3% Life");
        specialEffectMap.put(0x00000100, "Steal 5% Life");
        specialEffectMap.put(0x00000200, "Quick Attack");
        specialEffectMap.put(0x00000400, "Fast Attack");
        specialEffectMap.put(0x00000800, "Faster Attack");
        specialEffectMap.put(0x00001000, "Fastest Attack");
        specialEffectMap.put(0x00002000, "Fast Hit Recovery");
        specialEffectMap.put(0x00004000, "Faster Hit Recovery");
        specialEffectMap.put(0x00008000, "Fastest Hit Recovery");
        specialEffectMap.put(0x00000001, "Fast Block");
        specialEffectMap.put(0x00000002, "1-6 Additional Lightning Damage");
        specialEffectMap.put(0x00000004, "Attacker Takes 1-3 Thorn Damage");
        specialEffectMap.put(0x00000008, "Cursed Mana");
        specialEffectMap.put(0x00000010, "Absorb Half Trap Damage");
        specialEffectMap.put(0x00000020, "Effect Unknown");
        specialEffectMap.put(0x00000040, "+200% Damage to Demons");
        specialEffectMap.put(0x00000080, "Cursed Resistance");
        return specialEffectMap;
    }
}
