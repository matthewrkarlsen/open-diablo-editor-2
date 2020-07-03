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
 * Contains the offsets and section lengths of the various data sections inside
 * a v1.09 Diablo 1 binary. Note that this is NOT applicable to the Hellfire binary,
 * which has different offsets and section lengths.
 */
public class DiabloFilePositions {

	public static final long QUESTS_OFFSET = 653536L;
	public static final long QUEST_SPACING = 20L;
	public static final int NUMBER_OF_QUESTS = 16;
	public static final int QUEST_LENGTH_IN_BYTES = 16;

	public static final long SPELLS_OFFSET = 655872L;
	public static final int NUMBER_OF_SPELLS = 36;
	public static final int SPELL_LENGTH_IN_BYTES = 56;

	public static final long SHRINE_POINTERS_OFFSET = 648832L;
	public static final long SHRINE_MIN_LEVELS_OFFSET = 648932L;
	public static final long SHRINE_MAX_LEVELS_OFFSET = 648960L;
	public static final long SHRINE_GAME_TYPE_OFFSET = 648990L;
	public static final int NUMBER_OF_SHRINES = 25;

	public static final long MODIFIERS_OFFSET = 498344L;
	public static final int NUMBER_OF_MODIFIERS = 83;
	public static final int MODIFIER_LENGTH_IN_BYTES = 48;
	public static final int NUMBER_OF_ITEM_EFFECTS = 4;

	public static final long DIABLO_POINTERS_OFFSET = 4203008L;

	public static final long UNIQUE_ITEMS_OFFSET = 506984;
	public static final int UNIQUE_ITEM_LENGTH_IN_BYTES = 84;
	public static final int NUMBER_OF_UNIQUE_ITEMS = 50;

	public static final long INIT_STATS_OFFSET = 650716L;
	public static final long INIT_STR_OFFSET = INIT_STATS_OFFSET;
	public static final long INIT_MAG_OFFSET = INIT_STATS_OFFSET + 12L;
	public static final long INIT_DEX_OFFSET = INIT_STATS_OFFSET + 24L;
	public static final long INIT_VIT_OFFSET = INIT_STATS_OFFSET + 36L;
	public static final int INIT_STATS_LENGTH_IN_BYTES = 48;
	public static final long MAX_STATS_OFFSET = 650788L;
	public static final long WAR_MAX_STAT_OFFSET = MAX_STATS_OFFSET;
	public static final long ROG_MAX_STAT_OFFSET = MAX_STATS_OFFSET + 16;
	public static final long MAG_MAX_STAT_OFFSET = MAX_STATS_OFFSET + 32;
	public static final int MAX_STATS_LENGTH_IN_BYTES = 48;
	public static final long BLOCKING_BONUSES_OFFSET = 650764;
	public static final int BLOCKING_BONUSES_LENGTH_IN_BYTES = 12;
	public static final int BONUSES_AND_FRAMESETS_OFFSET = 650632;
	public static final int BONUSES_AND_FRAMESETS_LENGTH_IN_BYTES = 33;

	public static final long CHARACTER_ZERO_SKILL_LOC_1 = 305285;
	public static final long CHARACTER_ZERO_SKILL_LOC_2 = 306884;
	public static final long CHARACTER_ZERO_SPELL_LOC_1 = 15564;
	public static final long CHARACTER_ONE_SKILL_LOC_1 = 305296;
	public static final long CHARACTER_ONE_SKILL_LOC_2 = 306894;
	public static final long CHARACTER_ONE_SPELL_LOC_1 = 15574;
	public static final long CHARACTER_TWO_SKILL_LOC_1 = 305392;
	public static final long CHARACTER_TWO_SKILL_LOC_2 = 306910;
	public static final long CHARACTER_TWO_SPELL_LOC_1 = 15590;


	public static final long BASE_ITEMS_OFFSET = 578568;
	public static final int BASE_ITEM_LENGTH_IN_BYTES = 76;
	public static final int NUMBER_OF_BASE_ITEMS = 35;

	public static final long BASE_MONSTERS_OFFSET = 613384;
	public static final int BASE_MONSTER_LENGTH_IN_BYTES = 128;
	public static final int NUMBER_OF_BASE_MONSTERS = 111;
	public static final long MONSTER_ACTIVATION_BYTES_OFFSET = 627848;

	public static final long UNIQUE_MONSTERS_OFFSET = 627960;
	public static final int NUMBER_OF_UNIQUE_MONSTERS = 97;
	public static final int UNIQUE_MONSTER_LENGTH_IN_BYTES = 32;
}
