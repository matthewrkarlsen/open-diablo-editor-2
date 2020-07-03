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
 * Contains the various item effects that can be associated with item modifiers.
 * These modifiers are applied to base items to provide the generic "blue" magic items
 * that are (usually) inferior to equivalent unique items with the same level requirement.
 */
public class ItemEffectsForModifiers {

    public static final String[] ITEM_EFFECTS = {
            "+% ToHit", //00
            "-% ToHit", //01
            "+% damage done", //02
            "-% damage done", //03
            "+% ToHit, +% damage done", //04
            "-% ToHit, -% damage done", //05
            "+% armor class", //06
            "-% armor class", //07
            "+% resist fire", //08
            "+% resist lightning", //09
            "+% resist magic", //0A
            "+% resist all", //0B
            "", //0C
            "", //0D
            "+/- spell levels", //0E
            "+ charges", //0F
            "+ fire damage", //10
            "+ lightning damage", //11
            "", //12
            "+ strength", //13
            "- strength", //14
            "+ magic", //15
            "- magic", //16
            "+ dexterity", //17
            "- dexterity", //18
            "+ vitality", //19
            "- vitality", //1A
            "+ all attributes", //1B
            "+ all attributes", //1C
            "+ damage taken", //1D
            "- damage taken", //1E
            "+ life", //1F
            "- life", //20
            "+ mana", //21
            "- mana", //22
            "+% durability (high durability)", //23
            "-% durability (decreased durability)", //24
            "indestructable", //25
            "+% light", //26
            "-% light", //27
            "unknown or n/a", //28
            "multiple arrows", //29
            "+ fire arrows damage", //2A
            "+ lightning arrows damage", //2B
            "unique picture", //2C
            "attacker take 1-3 damage", //2D
            "-all mana", //2E
            "player can't heal", //2F
            "unknown or n/a", //30
            "unknown or n/a", //31
            "unknown or n/a", //32
            "unknown or n/a", //33
            "absorb half trap damage", //34
            "knocks target back", //35
            "monster can't heal", //36
            "% steal mana", //37
            "% steal life", //38
            "damage/penetrate armor", //39
            "attack speed (1=readiness, 4=haste)", //3A
            "hit recovery (1=balance, 3=harmony)", //3B
            "fast block", //3C
            "+ damage done", //3D
            "random speed arrows", //3E
            "x-y damage done (unusual item damage)", //3F
            "altered durability", //40
            "no strength requirements", //41
            "spell charges", //42
            "attack speed (1=readiness, 4=haste)", //43
            "one handed", //44
            "+200% damage versus demons", //45
            "all resistances equal 0%", //46
            "unknown or n/a", //47
            "constantly lose life", //48
            "0-12.5% steal life", //49
            "infravision", //4A
            "positive armor class", //4B
            "armor class added to life", //4C
            "10% of mana added to armor class", //4D
            "+30-clvl% resist fire", //4E
            "negative armor class" //4F
    };
}
