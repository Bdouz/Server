package server.model.players;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import server.Config;
import server.core.PlayerHandler;
import server.model.Entity;
import server.model.Location;
import server.model.content.AuctionHouse;
import server.model.items.Item;
import server.model.npcs.NPC;
import server.model.npcs.NPCHandler;
import server.model.objects.GameObject;
import server.model.players.Attributes.A;
import server.util.ISAACRandomGen;
import server.util.Misc;
import server.util.Stream;

public abstract class Player extends Entity {

	public boolean duelSafety;
	public String UUID = "";
	public String MAC  = "";
	public long ignores[] = new long[200];
	public String bankPin = "";
	public boolean slayerHelmAffect;
	public boolean isUsingSpecial;
	public int attempts = 3;
	public boolean setPin = false;
	public ArrayList<String> killedPlayers = new ArrayList<String>();
	public ArrayList<Integer> attackedPlayers = new ArrayList<Integer>();
	public long timePlayed;
	public boolean bankCheck = false;
	public boolean xpLock;
	public boolean dueling;
	public int barrowsChests = 0;
	public boolean openDuel = false;
	public long diceDelay;
	public long drunkDelay;
	public long lastButton;
	public ArrayList<String> lastKilledPlayers = new ArrayList<String>();
	public int bandosKills, armaKills, zammyKills, saraKills, seatrollKills,
	rexKills, primeKills, supremeKills, kbdKills, mithKills,
	callistoKills, seaSnakeKills, kalphiteKills, venenatisKills,
	vetionKills, smokeKills, zulrahKills, chaosKills, easyClue, medClue, hardClue;
	public long bestZulrahTime;
	
	public int cwPoints;
	public int bankingItems[] = new int[Config.BANK_SIZE];
	public int bankingItemsN[] = new int[Config.BANK_SIZE];

	public int bankingTab = 0;// -1 = bank closed

	private List<GameObject> localObjects = new LinkedList<GameObject>();

	/****
	 * @Bank Searching Varibles
	 */

	public boolean isSearching;
	public boolean lastSearch;
	public boolean isSearching2 = true;
	public String searchName;
	public int[] items = new int[500];
	public int[] itemsN = new int[500]; // The amount of the item.

	public int bankItems1[] = new int[Config.BANK_SIZE];
	public int bankItems1N[] = new int[Config.BANK_SIZE];
	public int bankItems2[] = new int[Config.BANK_SIZE];
	public int bankItems2N[] = new int[Config.BANK_SIZE];
	public int bankItems3[] = new int[Config.BANK_SIZE];
	public int bankItems3N[] = new int[Config.BANK_SIZE];
	public int bankItems4[] = new int[Config.BANK_SIZE];
	public int bankItems4N[] = new int[Config.BANK_SIZE];
	public int bankItems5[] = new int[Config.BANK_SIZE];
	public int bankItems5N[] = new int[Config.BANK_SIZE];
	public int bankItems6[] = new int[Config.BANK_SIZE];
	public int bankItems6N[] = new int[Config.BANK_SIZE];
	public int bankItems7[] = new int[Config.BANK_SIZE];
	public int bankItems7N[] = new int[Config.BANK_SIZE];
	public int bankItems8[] = new int[Config.BANK_SIZE];
	public int bankItems8N[] = new int[Config.BANK_SIZE];
	public String searchTerm = "N/A";

	public long lastCast = 0;

	/**
	 * @author Linus
	 * @see AuctionHouse.java
	 */
	public int marketItemId = -1;
	public int marketItemAmount = 0;
	public int marketItemValue = 1;
	public long marketTimer = 0;
	public int[] myMarketItems = new int[6];
	public int myMarketItemAmount = 0;
	public int marketAmount = 0;

	public long waitTime;

	public boolean initialized = false, disconnected = false,
			ruleAgreeButton = false, rebuildNPCList = false, isActive = false,
			isKicked = false, isSkulled = false, friendUpdate = false,
			newPlayer = false, hasMultiSign = false, saveCharacter = false,
			mouseButton = false, splitChat = false, chatEffects = true,
			acceptAid = false, nextDialogue = false, autocasting = false,
			usedSpecial = false, mageFollow = false, dbowSpec = false,
			craftingLeather = false, properLogout = false, secDbow = false,
			maxNextHit = false, ssSpec = false, vengOn = false,
			addStarter = false, accountFlagged = false, msbSpec = false;

	public int

	tzhaarToKill = 0, tzhaarKilled = 0, tzKekSpawn = 0, tzKekTimer = 0, waveId,
	saveDelay, playerKilled, pkPoints, totalPlayerDamageDealt,
	killedBy, lastChatId = 1, privateChat, friendSlot = 0, dialogueId,
	killStreak, randomCoffin, newLocation, specEffect, specBarId,
	attackLevelReq, defenceLevelReq, strengthLevelReq, rangeLevelReq,
	magicLevelReq, followId, skullTimer, votingPoints, nextChat = 0,
	talkingNpc = -1, dialogueAction = 0, autocastId, followDistance,
	followId2, barrageCount = 0, delayedDamage = 0, delayedDamage2 = 0,
	pcPoints = 0, magePoints = 0, desertTreasure = 0,
	lastArrowUsed = -1, clanId = -1, autoRet = 0, pcDamage = 0,
	xInterfaceId = 0, xRemoveId = 0, xRemoveSlot = 0, frozenBy = 0,
	poisonDamage = 0, teleAction = 0, lastNpcAttacked = 0, killCount = 0;


	public int stunTimer;
	
	public int bossPasses;
	
	public String clanName, properName;
	public int[] voidStatus = new int[5];
	public int[] itemKeptId = new int[4];
	public int[] pouches = new int[4];
	public final int[] POUCH_SIZE = { 3, 6, 9, 12 };
	public boolean[] invSlot = new boolean[28], equipSlot = new boolean[14];
	public long friends[] = new long[200];
	public double specAmount = 0;
	public double specAccuracy = 1;
	public double specDamage = 1;
	public double prayerPoint = 1.0;
	public int teleGrabItem, teleGrabX, teleGrabY, duelCount, underAttackBy,
	underAttackBy2, wildLevel, teleTimer, respawnTimer, saveTimer = 0,
	teleBlockLength, doorDelayLength, poisonDelay;
	public long lastPlayerMove, lastPoison, lastPoisonSip, poisonImmune,
	lastSpear, lastProtItem, dfsDelay, lastVeng, lastYell,
	teleGrabDelay, protMageDelay, protMeleeDelay, protRangeDelay,
	lastAction, lastThieve, lastLockPick, alchDelay, specDelay = System
	.currentTimeMillis(), duelDelay, teleBlockDelay, doorDelay,
	godSpellDelay, singleCombatDelay, singleCombatDelay2, reduceStat,
	restoreStatsDelay, logoutDelay, buryDelay, foodDelay, potDelay;
	public boolean canChangeAppearance = false;
	public byte poisonMask = 0;

	public boolean isArrow(int itemId) {
		if (itemId == 882 || itemId ==884||itemId == 886||itemId == 888||itemId == 890||itemId == 892||itemId == 4740||itemId == 11212
				|| itemId ==9140||itemId == 9141||itemId == 4142||itemId == 9143||itemId == 9144||itemId == 9240||itemId == 9241|| itemId == 9242||itemId == 9243||itemId == 9244||itemId == 9245)
			return true;
		return false;
	}

	public static final int[] BOWS = { 11785, 9185, 839, 845, 847, 851, 855, 859, 841,
		843, 849, 853, 857, 861, 4212, 4214, 4215, 11235, 4216, 4217, 4218,
		4219, 4220, 4221, 4222, 4223, 6724, 4734, 4934, 4935, 4936, 4937 };
	public static final int[] ARROWS = { 882, 884, 886, 888, 890, 892, 4740, 11212,
		9140, 9141, 4142, 9143, 9144, 9240, 9241, 9242, 9243, 9244, 9245 };
	public static final int[] NO_ARROW_DROP = { 12926, 863, 864, 865, 866, 867, 868,
		869, 4214, 4215, 4216, 4217, 4218, 4219, 4220, 4221, 4222, 4223,
		4734, 4934, 4935, 4936, 4937 };
	public static final int[] OTHER_RANGE_WEAPONS = { 4212, 12926, 863, 864, 865, 866,
		867, 868, 869, 806, 807, 808, 809, 810, 811, 825, 826, 827, 828,
		829, 830, 800, 801, 802, 803, 804, 805, 6522, 11230 };

	public static final int[][] MAGIC_SPELLS = {
		// example {magicId, level req, animation, startGFX, projectile Id,
		// endGFX, maxhit, exp gained, rune 1, rune 1 amount, rune 2, rune 2
		// amount, rune 3, rune 3 amount, rune 4, rune 4 amount}

		// Modern Spells
		{ 1152, 1, 711, 90, 91, 92, 2, 5, 556, 1, 558, 1, 0, 0, 0, 0 }, // wind
		// strike
		{ 1154, 5, 711, 93, 94, 95, 4, 7, 555, 1, 556, 1, 558, 1, 0, 0 }, // water
		// strike
		{ 1156, 9, 711, 96, 97, 98, 6, 9, 557, 2, 556, 1, 558, 1, 0, 0 },// earth
		// strike
		{ 1158, 13, 711, 99, 100, 101, 8, 11, 554, 3, 556, 2, 558, 1, 0, 0 }, // fire
		// strike
		{ 1160, 17, 711, 117, 118, 119, 9, 13, 556, 2, 562, 1, 0, 0, 0, 0 }, // wind
		// bolt
		{ 1163, 23, 711, 120, 121, 122, 10, 16, 556, 2, 555, 2, 562, 1, 0,
			0 }, // water bolt
			{ 1166, 29, 711, 123, 124, 125, 11, 20, 556, 2, 557, 3, 562, 1, 0,
				0 }, // earth bolt
				{ 1169, 35, 711, 126, 127, 128, 12, 22, 556, 3, 554, 4, 562, 1, 0,
					0 }, // fire bolt
					{ 1172, 41, 711, 132, 133, 134, 13, 25, 556, 3, 560, 1, 0, 0, 0, 0 }, // wind
					// blast
					{ 1175, 47, 711, 135, 136, 137, 14, 28, 556, 3, 555, 3, 560, 1, 0,
						0 }, // water blast
						{ 1177, 53, 711, 138, 139, 140, 15, 31, 556, 3, 557, 4, 560, 1, 0,
							0 }, // earth blast
							{ 1181, 59, 711, 129, 130, 131, 16, 35, 556, 4, 554, 5, 560, 1, 0,
								0 }, // fire blast
								{ 1183, 62, 711, 158, 159, 160, 17, 36, 556, 5, 565, 1, 0, 0, 0, 0 }, // wind
								// wave
								{ 1185, 65, 711, 161, 162, 163, 18, 37, 556, 5, 555, 7, 565, 1, 0,
									0 }, // water wave
									{ 1188, 70, 711, 164, 165, 166, 19, 40, 556, 5, 557, 7, 565, 1, 0,
										0 }, // earth wave
										{ 1189, 75, 711, 155, 156, 157, 20, 42, 556, 5, 554, 7, 565, 1, 0,
											0 }, // fire wave
											{ 1153, 3, 716, 102, 103, 104, 0, 13, 555, 3, 557, 2, 559, 1, 0, 0 }, // confuse
											{ 1157, 11, 716, 105, 106, 107, 0, 20, 555, 3, 557, 2, 559, 1, 0, 0 }, // weaken
											{ 1161, 19, 716, 108, 109, 110, 0, 29, 555, 2, 557, 3, 559, 1, 0, 0 }, // curse
											{ 1542, 66, 729, 167, 168, 169, 0, 76, 557, 5, 555, 5, 566, 1, 0, 0 }, // vulnerability
											{ 1543, 73, 729, 170, 171, 172, 0, 83, 557, 8, 555, 8, 566, 1, 0, 0 }, // enfeeble
											{ 1562, 80, 729, 173, 174, 107, 0, 90, 557, 12, 555, 12, 556, 1, 0,
												0 }, // stun
												{ 1572, 20, 711, 177, 178, 181, 0, 30, 557, 3, 555, 3, 561, 2, 0, 0 }, // bind
												{ 1582, 50, 711, 177, 178, 180, 2, 60, 557, 4, 555, 4, 561, 3, 0, 0 }, // snare
												{ 1592, 79, 711, 177, 178, 179, 4, 90, 557, 5, 555, 5, 561, 4, 0, 0 }, // entangle
												{ 1171, 39, 724, 145, 146, 147, 15, 25, 556, 2, 557, 2, 562, 1, 0,
													0 }, // crumble undead
													{ 1539, 50, 708, 87, 88, 89, 25, 42, 554, 5, 560, 1, 0, 0, 0, 0 }, // iban
													// blast
													{ 12037, 50, 1576, 327, 328, 329, 19, 30, 560, 1, 558, 4, 0, 0, 0,
														0 }, // magic dart
														{ 1190, 60, 811, 0, 0, 76, 20, 60, 554, 2, 565, 2, 556, 4, 0, 0 }, // sara
														// strike
														{ 1191, 60, 811, 0, 0, 77, 20, 60, 554, 1, 565, 2, 556, 4, 0, 0 }, // cause
														// of
														// guthix
														{ 1192, 60, 811, 0, 0, 78, 20, 60, 554, 4, 565, 2, 556, 1, 0, 0 }, // flames
														// of
														// zammy
														{ 12445, 85, 1819, 0, 344, 345, 0, 65, 563, 1, 562, 1, 560, 1, 0, 0 }, // teleblock

														// Ancient Spells
														{ 12939, 50, 1978, 0, 384, 385, 13, 30, 560, 2, 562, 2, 554, 1,
															556, 1 }, // smoke rush
															{ 12987, 52, 1978, 0, 378, 379, 14, 31, 560, 2, 562, 2, 566, 1,
																556, 1 }, // shadow rush
																{ 12901, 56, 1978, 0, 0, 373, 15, 33, 560, 2, 562, 2, 565, 1, 0, 0 }, // blood
																// rush
																{ 12861, 58, 1978, 0, 360, 361, 16, 34, 560, 2, 562, 2, 555, 2, 0,
																	0 }, // ice rush
																	{ 12963, 62, 1979, 0, 0, 389, 19, 36, 560, 2, 562, 4, 556, 2, 554,
																		2 }, // smoke burst
																		{ 13011, 64, 1979, 0, 0, 382, 20, 37, 560, 2, 562, 4, 556, 2, 566,
																			2 }, // shadow burst
																			{ 12919, 68, 1979, 0, 0, 376, 21, 39, 560, 2, 562, 4, 565, 2, 0, 0 }, // blood
																			// burst
																			{ 12881, 70, 1979, 0, 0, 363, 22, 40, 560, 2, 562, 4, 555, 4, 0, 0 }, // ice
																			// burst
																			{ 12951, 74, 1978, 0, 386, 387, 23, 42, 560, 2, 554, 2, 565, 2,
																				556, 2 }, // smoke blitz
																				{ 12999, 76, 1978, 0, 380, 381, 24, 43, 560, 2, 565, 2, 556, 2,
																					566, 2 }, // shadow blitz
																					{ 12911, 80, 1978, 0, 374, 375, 25, 45, 560, 2, 565, 4, 0, 0, 0, 0 }, // blood
																					// blitz
																					{ 12871, 82, 1978, 366, 0, 367, 26, 46, 560, 2, 565, 2, 555, 3, 0,
																						0 }, // ice blitz
																						{ 12975, 86, 1979, 0, 0, 391, 27, 48, 560, 4, 565, 2, 556, 4, 554,
																							4 }, // smoke barrage
																							{ 13023, 88, 1979, 0, 0, 383, 28, 49, 560, 4, 565, 2, 556, 4, 566,
																								3 }, // shadow barrage
																								{ 12929, 92, 1979, 0, 0, 377, 29, 51, 560, 4, 565, 4, 566, 1, 0, 0 }, // blood
																								// barrage
																								{ 12891, 94, 1979, 0, 0, 369, 30, 52, 560, 4, 565, 2, 555, 6, 0, 0 }, // ice
																								// barrage

																								{ -1, 80, 811, 301, 0, 0, 0, 0, 554, 3, 565, 3, 556, 3, 0, 0 }, // charge
																								{ -1, 21, 712, 112, 0, 0, 0, 10, 554, 3, 561, 1, 0, 0, 0, 0 }, // low
																								// alch
																								{ -1, 55, 713, 113, 0, 0, 0, 20, 554, 5, 561, 1, 0, 0, 0, 0 }, // high
																								// alch
																								{ -1, 33, 728, 142, 143, 144, 0, 35, 556, 1, 563, 1, 0, 0, 0, 0 }, // telegrab
																								// example {magicId, level req, animation, startGFX, projectile Id,
																								// endGFX, maxhit, exp gained, rune 1, rune 1 amount, rune 2, rune 2
																								// amount, rune 3, rune 3 amount, rune 4, rune 4 amount}
																								{ -1, 75, 728, 1251, 1252, 1253, 33, 35, -1, -1, -1, -1, 0, 0, 0, 0 }, //Trident
																								{ -1, 75, 728, 665, 1040, 1042, 36, 35, -1, -1, -1, -1, 0, 0, 0, 0 }// Trident of the Swamp

	};

	public boolean isAutoButton(int button) {
		for (int j = 0; j < autocastIds.length; j += 2) {
			if (autocastIds[j] == button)
				return true;
		}
		return false;
	}

	public static int[] autocastIds = { 51133, 32, 51185, 33, 51091, 34, 24018, 35,
		51159, 36, 51211, 37, 51111, 38, 51069, 39, 51146, 40, 51198, 41,
		51102, 42, 51058, 43, 51172, 44, 51224, 45, 51122, 46, 51080, 47,
		7038, 0, 7039, 1, 7040, 2, 7041, 3, 7042, 4, 7043, 5, 7044, 6,
		7045, 7, 7046, 8, 7047, 9, 7048, 10, 7049, 11, 7050, 12, 7051, 13,
		7052, 14, 7053, 15, 47019, 27, 47020, 25, 47021, 12, 47022, 13,
		47023, 14, 47024, 15 };

	// public String spellName = "Select Spell";
	public void assignAutocast(int button) {
		for (int j = 0; j < autocastIds.length; j++) {
			if (autocastIds[j] == button) {
				Client c = (Client) PlayerHandler.players[this.playerId];
				autocasting = true;
				autocastId = autocastIds[j + 1];
				c.getPA().sendFrame36(108, 1);
				c.setSidebarInterface(0, 328);
				// spellName = getSpellName(autocastId);
				// spellName = spellName;
				// c.getPA().sendNewString(spellName, 354);
				c = null;
				break;
			}
		}
	}

	public String getSpellName(int id) {
		switch (id) {
		case 0:
			return "Air Strike";
		case 1:
			return "Water Strike";
		case 2:
			return "Earth Strike";
		case 3:
			return "Fire Strike";
		case 4:
			return "Air Bolt";
		case 5:
			return "Water Bolt";
		case 6:
			return "Earth Bolt";
		case 7:
			return "Fire Bolt";
		case 8:
			return "Air Blast";
		case 9:
			return "Water Blast";
		case 10:
			return "Earth Blast";
		case 11:
			return "Fire Blast";
		case 12:
			return "Air Wave";
		case 13:
			return "Water Wave";
		case 14:
			return "Earth Wave";
		case 15:
			return "Fire Wave";
		case 32:
			return "Shadow Rush";
		case 33:
			return "Smoke Rush";
		case 34:
			return "Blood Rush";
		case 35:
			return "Ice Rush";
		case 36:
			return "Shadow Burst";
		case 37:
			return "Smoke Burst";
		case 38:
			return "Blood Burst";
		case 39:
			return "Ice Burst";
		case 40:
			return "Shadow Blitz";
		case 41:
			return "Smoke Blitz";
		case 42:
			return "Blood Blitz";
		case 43:
			return "Ice Blitz";
		case 44:
			return "Shadow Barrage";
		case 45:
			return "Smoke Barrage";
		case 46:
			return "Blood Barrage";
		case 47:
			return "Ice Barrage";
		default:
			return "Select Spell";
		}
	}

	public boolean fullVoidRange() {
		return playerEquipment[playerHat] == 11664
				&& playerEquipment[playerLegs] == 8840
				&& playerEquipment[playerChest] == 8839
				&& playerEquipment[playerHands] == 8842;
	}

	public boolean fullVoidMage() {
		return playerEquipment[playerHat] == 11663
				&& playerEquipment[playerLegs] == 8840
				&& playerEquipment[playerChest] == 8839
				&& playerEquipment[playerHands] == 8842;
	}

	public boolean fullVoidMelee() {
		return playerEquipment[playerHat] == 11665
				&& playerEquipment[playerLegs] == 8840
				&& playerEquipment[playerChest] == 8839
				&& playerEquipment[playerHands] == 8842;
	}

	public int[][] barrowsNpcs = { { 1677, 0 }, // verac
			{ 1676, 0 }, // toarg
			{ 1675, 0 }, // karil
			{ 1674, 0 }, // guthan
			{ 1673, 0 }, // dharok
			{ 1672, 0 } // ahrim
	};
	public int barrowsKillCount;

	public int reduceSpellId;
	public final int[] REDUCE_SPELL_TIME = { 250000, 250000, 250000, 500000,
			500000, 500000 }; // how long does the other player stay immune to
	// the spell
	public long[] reduceSpellDelay = new long[6];
	public final int[] REDUCE_SPELLS = { 1153, 1157, 1161, 1542, 1543, 1562 };
	public boolean[] canUseReducingSpell = { true, true, true, true, true, true };

	public int slayerTask, taskAmount;

	public int prayerId = -1;
	public int headIcon = -1;
	public int bountyIcon = 0;
	public long stopPrayerDelay, prayerDelay;
	public boolean usingPrayer;
	public final int[] PRAYER_DRAIN_RATE = { 500, 500, 500, 500, 500, 500, 500,
			500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500,
			500, 500, 500, 500, 500, 500 };
	public final int[] PRAYER_LEVEL_REQUIRED = { 1, 4, 7, 8, 9, 10, 13, 16, 19,
			22, 25, 26, 27, 28, 31, 34, 37, 40, 43, 44, 45, 46, 49, 52, 60, 70 };
	public final int[] PRAYER = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
			14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 };
	public final String[] PRAYER_NAME = { "Thick Skin", "Burst of Strength",
			"Clarity of Thought", "Sharp Eye", "Mystic Will", "Rock Skin",
			"Superhuman Strength", "Improved Reflexes", "Rapid Restore",
			"Rapid Heal", "Protect Item", "Hawk Eye", "Mystic Lore",
			"Steel Skin", "Ultimate Strength", "Incredible Reflexes",
			"Protect from Magic", "Protect from Missiles",
			"Protect from Melee", "Eagle Eye", "Mystic Might", "Retribution",
			"Redemption", "Smite", "Chivalry", "Piety" };
	public final int[] PRAYER_GLOW = { 83, 84, 85, 601, 602, 86, 87, 88, 89,
			90, 91, 603, 104, 92, 93, 94, 95, 96, 97, 605, 606, 98, 99, 100,
			607, 608 };
	public final int[] PRAYER_HEAD_ICONS = { -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, 2, 1, 0, -1, -1, 3, 5, 4, -1, -1 };
	// {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,3,2,1,4,6,5};

	public boolean[] prayerActive = { false, false, false, false, false, false,
			false, false, false, false, false, false, false, false, false,
			false, false, false, false, false, false, false, false, false,
			false, false };

	public int duelTimer, duelTeleX, duelTeleY, duelSlot, duelSpaceReq,
	duelOption, duelingWith;
	public int headIconPk = -1, headIconHints;
	public boolean duelRequested;
	public boolean[] duelRule = new boolean[22];
	public final int[] DUEL_RULE_ID = { 1, 2, 16, 32, 64, 128, 256, 512, 1024,
			4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 2097152,
			8388608, 16777216, 67108864, 134217728 };

	public boolean doubleHit, usingSpecial, npcDroppingItems, usingRangeWeapon,
	usingBow, usingMagic, castingMagic;
	public int bankWithdraw, specMaxHitIncrease, freezeDelay, freezeTimer = -6,
			killerId, playerIndex, oldPlayerIndex, lastWeaponUsed,
			projectileStage, crystalBowArrowCount, playerMagicBook, teleGfx,
			teleEndAnimation, teleHeight, teleX, teleY, rangeItemUsed,
			killingNpcIndex, totalDamageDealt, oldNpcIndex, fightMode,
			npcIndex, npcClickIndex, npcType, castingSpellId,
			oldSpellId, spellId, hitDelay;
	
	public double attackTimer;
	public boolean magicFailed, oldMagicFailed;
	public int bowSpecShot, clickNpcType, clickObjectType, objectId, objectX,
	objectY, objectXOffset, objectYOffset, objectDistance;
	public int pItemX, pItemY, pItemId;
	public boolean isMoving, walkingToItem;
	public boolean isShopping, updateShop;
	public int myShopId;
	public int tradeStatus, tradeWith;
	public int duelStatus, duelWith;
	public boolean forcedChatUpdateRequired, inDuel, tradeAccepted, goodTrade,
	goodDuel, duelAccepted, inTrade, tradeRequested, tradeResetNeeded,
	tradeConfirmed, tradeConfirmed2, duelResetNeeded, duelConfirmed,
	duelConfirmed2, canOffer, acceptTrade, acceptedTrade, acceptDuel,
	acceptedDuel;
	public int attackAnim, animationRequest = -1, animationWaitCycles;
	public int[] playerBonus = new int[12];
	public boolean isRunning2 = true;
	public boolean takeAsNote;
	public int combatLevel;
	public boolean saveFile = false;
	public int playerAppearance[] = new int[13];
	public int apset;
	public int actionID;
	public int wearItemTimer, wearId, wearSlot, interfaceId;
	public int XremoveSlot, XinterfaceID, XremoveID, Xamount;

	public int tutorial = 15;
	public boolean usingGlory = false;
	public int[] woodcut = new int[7];
	public int wcTimer = 0;
	public int[] mining = new int[3];
	public int miningTimer = 0;
	public boolean fishing = false;
	public int fishTimer = 0;
	public int smeltType; // 1 = bronze, 2 = iron, 3 = steel, 4 = gold, 5 =
	// mith, 6 = addy, 7 = rune
	public int smeltAmount;
	public int smeltTimer = 0;
	public boolean smeltInterface;
	public boolean patchCleared;
	public int[] farm = new int[2];

	public boolean antiFirePot = false;

	public int clanWarsTeam;
	public boolean inClanWarsGame;
	public boolean inClanWarsWait;

	public int kbdWarsTeam;
	public boolean inKBDWarsGame;
	public boolean inKBDWarsWait;

	/**
	 * Castle Wars
	 */
	public int castleWarsTeam;
	public boolean inCwGame;
	public boolean inCwWait;

	/**
	 * Fight Pits
	 */
	public boolean inPits = false;
	public int pitsStatus = 0;

	/**
	 * SouthWest, NorthEast, SouthWest, NorthEast
	 */

	public boolean isInTut() {
		if (absX >= 2625 && absX <= 2687 && absY >= 4670 && absY <= 4735) {
			return true;
		}
		return false;
	}

	public boolean inBarrows() {
		if (absX > 3520 && absX < 3598 && absY > 9653 && absY < 9750) {
			return true;
		}
		return false;
	}

	public boolean inArea(int x, int y, int x1, int y1) {
		if (absX > x && absX < x1 && absY < y && absY > y1) {
			return true;
		}
		return false;
	}

	public boolean inEastFaladorBank() {
		return (absX > 3686 && absX < 3699 && absY > 3460 && absY < 3471);
	}

	public boolean inWestFaladorBank() {
		return (absX > 12941 && absX < 12948 && absY > 13367 && absY < 13374 || absX >= 12948
				&& absX <= 12949 && absY >= 13368 && absY <= 13369);
	}

	public boolean inSafeZone() {
		return (inEastFaladorBank() || inWestFaladorBank());
	}

	public boolean inPvP() {
		if (absX >= 3653 && absX <= 3709 && absY >= 3461 && absY <= 3507) {
			return true;
		}
		return false;
	}
	
	public boolean inFFA() {
		//SW 3264, 4760
		//NE 3381 4863
		if (absX >= 3264 && absX <= 3381 && absY >= 4760 && absY <= 4863) {
			return true;
		}
		return false;
	}
	
	//if (absX >= 3264 && absX <= 3381 && absY >= 4777 && absY <= 4863) {
	
	public boolean inFFAPortal() {
		//SW 3264, 4760
		//NE 3381 4863
		if (absX >= 3264 && absX <= 3381 && absY >= 4736 && absY <= 4863) {
			return true;
		}
		return false;
	}

	public boolean inWild() {
		if (absX >= 3187 && absX <= 3194 && absY >= 3958 && absY <= 3962) {
			return false;
		}
		if (absX > 2941 && absX < 3392 && absY > 3524 && absY < 3967
				|| absX > 2941 && absX < 3392 && absY > 9918 && absY < 10366) {
			return true;
		}
		return false;
	}
	public boolean inZulrahShrine() {
		if (absX >= 2259 && absX <= 2277 && absY >= 3067 && absY <= 3079) {
			return true;
		}
		else
			return false;
	}

	public boolean arenas() {
		if (absX > 3331 && absX < 3391 && absY > 3242 && absY < 3260) {
			return true;
		}
		return false;
	}

	public boolean inDuelArena() {
		if ((absX > 3322 && absX < 3394 && absY > 3195 && absY < 3291)
				|| (absX > 3311 && absX < 3323 && absY > 3223 && absY < 3248)) {
			return true;
		}
		return false;
	}

	public boolean insideArena() {
		return absX > 3359 && absX < 3331 && absY > 3259 && absY < 3243;
	}

	public boolean inMulti() {
		if (((Client) this).getPA().inZulrah() || inKqArea()
				|| (absX >= 3264 && absX <= 3381 && absY >= 4777 && absY <= 4863)
				|| (absX >= 2936 && absX <= 3062 && absY >= 3309 && absY <= 3394)
				|| (absX >= 2975 && absX <= 3000 && absY >= 4365 && absY <= 4400)
				|| (absX >= 2502 && absX <= 2530 && absY >= 3024 && absY <= 3059)
				|| (absX >= 2323 && absX <= 2369 && absY >= 3686 && absY <= 3715)
				|| (absX >= 3136 && absX <= 3327 && absY >= 3519 && absY <= 3607)
				|| (absX >= 3190 && absX <= 3327 && absY >= 3648 && absY <= 3839)
				|| (absX >= 3200 && absX <= 3390 && absY >= 3840 && absY <= 3967)
				|| (absX >= 2992 && absX <= 3007 && absY >= 3912 && absY <= 3967)
				|| (absX >= 2946 && absX <= 2959 && absY >= 3816 && absY <= 3831)
				|| (absX >= 3008 && absX <= 3199 && absY >= 3856 && absY <= 3903)
				|| (absX >= 2824 && absX <= 2944 && absY >= 5258 && absY <= 5369)
				|| (absX >= 3008 && absX <= 3071 && absY >= 3600 && absY <= 3711)
				|| (absX >= 3072 && absX <= 3327 && absY >= 3608 && absY <= 3647)
				|| (absX >= 2624 && absX <= 2690 && absY >= 2550 && absY <= 2619)
				|| (absX >= 2371 && absX <= 2422 && absY >= 5062 && absY <= 5117)
				|| (absX >= 2896 && absX <= 2927 && absY >= 3595 && absY <= 3630)
				|| (absX >= 2892 && absX <= 2932 && absY >= 4435 && absY <= 4464)
				|| (absX >= 2256 && absX <= 2287 && absY >= 4680 && absY <= 4711)) {
			return true;
		}
		return false;
	}

	public boolean inFightCaves() {
		return absX >= 2360 && absX <= 2445 && absY >= 5045 && absY <= 5125;
	}

	public boolean inFightCaves2() {
		return teleportToX >= 2360 && teleportToX <= 2445 && teleportToY >= 5045 && teleportToY <= 5125;
	}

	public int[] keepItems = new int[4];
	public int[] keepItemsN = new int[4];
	public int WillKeepAmt1, WillKeepAmt2, WillKeepAmt3, WillKeepAmt4,
	WillKeepItem1, WillKeepItem2, WillKeepItem3, WillKeepItem4,
	WillKeepItem1Slot, WillKeepItem2Slot, WillKeepItem3Slot,
	WillKeepItem4Slot, EquipStatus;

	public void ResetKeepItems() {
		WillKeepAmt1 = -1;
		WillKeepItem1 = -1;
		WillKeepAmt2 = -1;
		WillKeepItem2 = -1;
		WillKeepAmt3 = -1;
		WillKeepItem3 = -1;
		WillKeepAmt4 = -1;
		WillKeepItem4 = -1;
	}

	/**
	 * 
	 * @param Bank
	 *            Search
	 */
	public boolean bankSearching = false;

	public void StartBestItemScan(Client c) {
		if (c.isSkulled && !c.prayerActive[10]) {
			ItemKeptInfo(c, 0);
			return;
		}
		FindItemKeptInfo(c);
		ResetKeepItems();
		BestItem1(c);
	}

	public void FindItemKeptInfo(Client c) {
		if (isSkulled && c.prayerActive[10])
			ItemKeptInfo(c, 1);
		else if (!isSkulled && !c.prayerActive[10])
			ItemKeptInfo(c, 3);
		else if (!isSkulled && c.prayerActive[10])
			ItemKeptInfo(c, 4);
	}

	public void ItemKeptInfo(Client c, int Lose) {
		for (int i = 17109; i < 17131; i++) {
			c.getPA().sendNewString("", i);
		}
		c.getPA().sendNewString("Items you will keep on death:", 17104);
		c.getPA().sendNewString("Items you will lose on death:", 17105);
		c.getPA().sendNewString("Player Information", 17106);
		c.getPA().sendNewString("Max items kept on death:", 17107);
		c.getPA().sendNewString("~ " + Lose + " ~", 17108);
		c.getPA().sendNewString("The normal amount of", 17111);
		c.getPA().sendNewString("items kept is three.", 17112);
		switch (Lose) {
		case 0:
		default:
			c.getPA().sendNewString("Items you will keep on death:", 17104);
			c.getPA().sendNewString("Items you will lose on death:", 17105);
			c.getPA().sendNewString("You're marked with a", 17111);
			c.getPA().sendNewString("@red@skull. @lre@This reduces the", 17112);
			c.getPA().sendNewString("items you keep from", 17113);
			c.getPA().sendNewString("three to zero!", 17114);
			break;
		case 1:
			c.getPA().sendNewString("Items you will keep on death:", 17104);
			c.getPA().sendNewString("Items you will lose on death:", 17105);
			c.getPA().sendNewString("You're marked with a", 17111);
			c.getPA().sendNewString("@red@skull. @lre@This reduces the", 17112);
			c.getPA().sendNewString("items you keep from", 17113);
			c.getPA().sendNewString("three to zero!", 17114);
			c.getPA().sendNewString("However, you also have", 17115);
			c.getPA()
			.sendNewString("the @red@Protect @lre@Items prayer", 17116);
			c.getPA().sendNewString("active, which saves you", 17117);
			c.getPA().sendNewString("one extra item!", 17118);
			break;
		case 3:
			c.getPA().sendNewString(
					"Items you will keep on death(if not skulled):", 17104);
			c.getPA().sendNewString(
					"Items you will lose on death(if not skulled):", 17105);
			c.getPA().sendNewString("You have no factors", 17111);
			c.getPA().sendNewString("affecting the items you", 17112);
			c.getPA().sendNewString("keep.", 17113);
			break;
		case 4:
			c.getPA().sendNewString(
					"Items you will keep on death(if not skulled):", 17104);
			c.getPA().sendNewString(
					"Items you will lose on death(if not skulled):", 17105);
			c.getPA().sendNewString("You have the @red@Protect", 17111);
			c.getPA().sendNewString("@red@Item @lre@prayer active,", 17112);
			c.getPA().sendNewString("which saves you one", 17113);
			c.getPA().sendNewString("extra item!", 17114);
			break;
		}
	}

	public void BestItem1(Client c) {
		int BestValue = 0;
		int NextValue = 0;
		int ItemsContained = 0;
		WillKeepItem1 = 0;
		WillKeepItem1Slot = 0;
		for (int ITEM = 0; ITEM < 28; ITEM++) {
			if (playerItems[ITEM] > 0) {
				ItemsContained += 1;
				NextValue = (int) Math.floor(c.getShops().getItemShopValue(
						playerItems[ITEM] - 1));
				if (NextValue > BestValue) {
					BestValue = NextValue;
					WillKeepItem1 = playerItems[ITEM] - 1;
					WillKeepItem1Slot = ITEM;
					if (playerItemsN[ITEM] > 2 && !c.prayerActive[10]) {
						WillKeepAmt1 = 3;
					} else if (playerItemsN[ITEM] > 3 && c.prayerActive[10]) {
						WillKeepAmt1 = 4;
					} else {
						WillKeepAmt1 = playerItemsN[ITEM];
					}
				}
			}
		}
		for (int EQUIP = 0; EQUIP < 14; EQUIP++) {
			if (playerEquipment[EQUIP] > 0) {
				ItemsContained += 1;
				NextValue = (int) Math.floor(c.getShops().getItemShopValue(
						playerEquipment[EQUIP]));
				if (NextValue > BestValue) {
					BestValue = NextValue;
					WillKeepItem1 = playerEquipment[EQUIP];
					WillKeepItem1Slot = EQUIP + 28;
					if (playerEquipmentN[EQUIP] > 2 && !c.prayerActive[10]) {
						WillKeepAmt1 = 3;
					} else if (playerEquipmentN[EQUIP] > 3
							&& c.prayerActive[10]) {
						WillKeepAmt1 = 4;
					} else {
						WillKeepAmt1 = playerEquipmentN[EQUIP];
					}
				}
			}
		}
		if (!isSkulled
				&& ItemsContained > 1
				&& (WillKeepAmt1 < 3 || (c.prayerActive[10] && WillKeepAmt1 < 4))) {
			BestItem2(c, ItemsContained);
		}
	}

	public void BestItem2(Client c, int ItemsContained) {
		int BestValue = 0;
		int NextValue = 0;
		WillKeepItem2 = 0;
		WillKeepItem2Slot = 0;
		for (int ITEM = 0; ITEM < 28; ITEM++) {
			if (playerItems[ITEM] > 0) {
				NextValue = (int) Math.floor(c.getShops().getItemShopValue(
						playerItems[ITEM] - 1));
				if (NextValue > BestValue
				&& !(ITEM == WillKeepItem1Slot && playerItems[ITEM] - 1 == WillKeepItem1)) {
					BestValue = NextValue;
					WillKeepItem2 = playerItems[ITEM] - 1;
					WillKeepItem2Slot = ITEM;
					if (playerItemsN[ITEM] > 2 - WillKeepAmt1
							&& !c.prayerActive[10]) {
						WillKeepAmt2 = 3 - WillKeepAmt1;
					} else if (playerItemsN[ITEM] > 3 - WillKeepAmt1
							&& c.prayerActive[10]) {
						WillKeepAmt2 = 4 - WillKeepAmt1;
					} else {
						WillKeepAmt2 = playerItemsN[ITEM];
					}
				}
			}
		}
		for (int EQUIP = 0; EQUIP < 14; EQUIP++) {
			if (playerEquipment[EQUIP] > 0) {
				NextValue = (int) Math.floor(c.getShops().getItemShopValue(
						playerEquipment[EQUIP]));
				if (NextValue > BestValue
						&& !(EQUIP + 28 == WillKeepItem1Slot && playerEquipment[EQUIP] == WillKeepItem1)) {
					BestValue = NextValue;
					WillKeepItem2 = playerEquipment[EQUIP];
					WillKeepItem2Slot = EQUIP + 28;
					if (playerEquipmentN[EQUIP] > 2 - WillKeepAmt1
							&& !c.prayerActive[10]) {
						WillKeepAmt2 = 3 - WillKeepAmt1;
					} else if (playerEquipmentN[EQUIP] > 3 - WillKeepAmt1
							&& c.prayerActive[10]) {
						WillKeepAmt2 = 4 - WillKeepAmt1;
					} else {
						WillKeepAmt2 = playerEquipmentN[EQUIP];
					}
				}
			}
		}
		if (!isSkulled
				&& ItemsContained > 2
				&& (WillKeepAmt1 + WillKeepAmt2 < 3 || (c.prayerActive[10] && WillKeepAmt1
						+ WillKeepAmt2 < 4))) {
			BestItem3(c, ItemsContained);
		}
	}

	public void BestItem3(Client c, int ItemsContained) {
		int BestValue = 0;
		int NextValue = 0;
		WillKeepItem3 = 0;
		WillKeepItem3Slot = 0;
		for (int ITEM = 0; ITEM < 28; ITEM++) {
			if (playerItems[ITEM] > 0) {
				NextValue = (int) Math.floor(c.getShops().getItemShopValue(
						playerItems[ITEM] - 1));
				if (NextValue > BestValue
				&& !(ITEM == WillKeepItem1Slot && playerItems[ITEM] - 1 == WillKeepItem1)
				&& !(ITEM == WillKeepItem2Slot && playerItems[ITEM] - 1 == WillKeepItem2)) {
					BestValue = NextValue;
					WillKeepItem3 = playerItems[ITEM] - 1;
					WillKeepItem3Slot = ITEM;
					if (playerItemsN[ITEM] > 2 - (WillKeepAmt1 + WillKeepAmt2)
							&& !c.prayerActive[10]) {
						WillKeepAmt3 = 3 - (WillKeepAmt1 + WillKeepAmt2);
					} else if (playerItemsN[ITEM] > 3 - (WillKeepAmt1 + WillKeepAmt2)
							&& c.prayerActive[10]) {
						WillKeepAmt3 = 4 - (WillKeepAmt1 + WillKeepAmt2);
					} else {
						WillKeepAmt3 = playerItemsN[ITEM];
					}
				}
			}
		}
		for (int EQUIP = 0; EQUIP < 14; EQUIP++) {
			if (playerEquipment[EQUIP] > 0) {
				NextValue = (int) Math.floor(c.getShops().getItemShopValue(
						playerEquipment[EQUIP]));
				if (NextValue > BestValue
						&& !(EQUIP + 28 == WillKeepItem1Slot && playerEquipment[EQUIP] == WillKeepItem1)
						&& !(EQUIP + 28 == WillKeepItem2Slot && playerEquipment[EQUIP] == WillKeepItem2)) {
					BestValue = NextValue;
					WillKeepItem3 = playerEquipment[EQUIP];
					WillKeepItem3Slot = EQUIP + 28;
					if (playerEquipmentN[EQUIP] > 2 - (WillKeepAmt1 + WillKeepAmt2)
							&& !c.prayerActive[10]) {
						WillKeepAmt3 = 3 - (WillKeepAmt1 + WillKeepAmt2);
					} else if (playerEquipmentN[EQUIP] > 3 - WillKeepAmt1
							&& c.prayerActive[10]) {
						WillKeepAmt3 = 4 - (WillKeepAmt1 + WillKeepAmt2);
					} else {
						WillKeepAmt3 = playerEquipmentN[EQUIP];
					}
				}
			}
		}
		if (!isSkulled && ItemsContained > 3 && c.prayerActive[10]
				&& ((WillKeepAmt1 + WillKeepAmt2 + WillKeepAmt3) < 4)) {
			BestItem4(c);
		}
	}

	public void BestItem4(Client c) {
		int BestValue = 0;
		int NextValue = 0;
		WillKeepItem4 = 0;
		WillKeepItem4Slot = 0;
		for (int ITEM = 0; ITEM < 28; ITEM++) {
			if (playerItems[ITEM] > 0) {
				NextValue = (int) Math.floor(c.getShops().getItemShopValue(
						playerItems[ITEM] - 1));
				if (NextValue > BestValue
				&& !(ITEM == WillKeepItem1Slot && playerItems[ITEM] - 1 == WillKeepItem1)
				&& !(ITEM == WillKeepItem2Slot && playerItems[ITEM] - 1 == WillKeepItem2)
				&& !(ITEM == WillKeepItem3Slot && playerItems[ITEM] - 1 == WillKeepItem3)) {
					BestValue = NextValue;
					WillKeepItem4 = playerItems[ITEM] - 1;
					WillKeepItem4Slot = ITEM;
				}
			}
		}
		for (int EQUIP = 0; EQUIP < 14; EQUIP++) {
			if (playerEquipment[EQUIP] > 0) {
				NextValue = (int) Math.floor(c.getShops().getItemShopValue(
						playerEquipment[EQUIP]));
				if (NextValue > BestValue
						&& !(EQUIP + 28 == WillKeepItem1Slot && playerEquipment[EQUIP] == WillKeepItem1)
						&& !(EQUIP + 28 == WillKeepItem2Slot && playerEquipment[EQUIP] == WillKeepItem2)
						&& !(EQUIP + 28 == WillKeepItem3Slot && playerEquipment[EQUIP] == WillKeepItem3)) {
					BestValue = NextValue;
					WillKeepItem4 = playerEquipment[EQUIP];
					WillKeepItem4Slot = EQUIP + 28;
				}
			}
		}
	}

	public int getLocalX() {
		return getX() - 8 * getMapRegionX();
	}

	public int getLocalY() {
		return getY() - 8 * getMapRegionY();
	}

	public boolean inPirateHouse() {
		return absX >= 3038 && absX <= 3044 && absY >= 3949 && absY <= 3959;
	}

	public String connectedFrom = "";
	public String globalMessage = "";

	public abstract void initialize();

	public abstract void update();

	public int playerId = -1;
	public String playerName = null;
	public String playerName2 = null;
	public String playerPass = null;
	public int playerRights;
	public PlayerHandler handler = null;
	public int playerItems[] = new int[28];
	public int playerItemsN[] = new int[28];
	public int bankItems[] = new int[Config.BANK_SIZE];
	public int bankItemsN[] = new int[Config.BANK_SIZE];
	public boolean bankNotes = false;

	public int playerStandIndex = 0x328;
	public int playerTurnIndex = 0x337;
	public int playerWalkIndex = 0x333;
	public int playerTurn180Index = 0x334;
	public int playerTurn90CWIndex = 0x335;
	public int playerTurn90CCWIndex = 0x336;
	public int playerRunIndex = 0x338;

	public int playerHat = 0;
	public int playerCape = 1;
	public int playerAmulet = 2;
	public int playerWeapon = 3;
	public int playerChest = 4;
	public int playerShield = 5;
	public int playerLegs = 7;
	public int playerHands = 9;
	public int playerFeet = 10;
	public int playerRing = 12;
	public int playerArrows = 13;

	public int playerAttack = 0;
	public int playerDefence = 1;
	public int playerStrength = 2;
	public int playerHitpoints = 3;
	public int playerRanged = 4;
	public int playerPrayer = 5;
	public int playerMagic = 6;
	public int playerCooking = 7;
	public int playerWoodcutting = 8;
	public int playerFletching = 9;
	public int playerFishing = 10;
	public int playerFiremaking = 11;
	public int playerCrafting = 12;
	public int playerSmithing = 13;
	public int playerMining = 14;
	public int playerHerblore = 15;
	public int playerAgility = 16;
	public int playerThieving = 17;
	public int playerSlayer = 18;
	public int playerFarming = 19;
	public int playerRunecrafting = 20;

	public int[] playerEquipment = new int[14];
	public int[] playerEquipmentN = new int[14];
	public int[] playerLevel = new int[25];
	public int[] playerXP = new int[25];

	public int getPlayerLevel(int playerLevel) {
		if (playerLevel > 2079) {
			return 2079;
		}
		return playerLevel;
	}

	public void updateshop(int i) {
		Client p = (Client) PlayerHandler.players[playerId];
		p.getShops().resetShop(i);
	}

	public void println_debug(String str) {
		System.out.println("[player-" + playerId + "]: " + str);
	}

	public void println(String str) {
		System.out.println("[player-" + playerId + "]: " + str);
	}

	public Player(int _playerId) {
		playerId = _playerId;
		playerRights = 0;

		for (int i = 0; i < playerItems.length; i++) {
			playerItems[i] = 0;
		}
		for (int i = 0; i < playerItemsN.length; i++) {
			playerItemsN[i] = 0;
		}

		for (int i = 0; i < playerLevel.length; i++) {
			if (i == 3) {
				playerLevel[i] = 10;
			} else {
				playerLevel[i] = 1;
			}
		}

		for (int i = 0; i < playerXP.length; i++) {
			if (i == 3) {
				playerXP[i] = 1300;
			} else {
				playerXP[i] = 0;
			}
		}
		for (int i = 0; i < Config.BANK_SIZE; i++) {
			bankItems[i] = 0;
		}

		for (int i = 0; i < Config.BANK_SIZE; i++) {
			bankItemsN[i] = 0;
		}

		playerAppearance[0] = 0; // gender
		playerAppearance[1] = 7; // head
		playerAppearance[2] = 25;// Torso
		playerAppearance[3] = 29; // arms
		playerAppearance[4] = 35; // hands
		playerAppearance[5] = 39; // legs
		playerAppearance[6] = 44; // feet
		playerAppearance[7] = 14; // beard
		playerAppearance[8] = 7; // hair colour
		playerAppearance[9] = 8; // torso colour
		playerAppearance[10] = 9; // legs colour
		playerAppearance[11] = 5; // feet colour
		playerAppearance[12] = 0; // skin colour

		apset = 0;
		actionID = 0;

		playerEquipment[playerHat] = -1;
		playerEquipment[playerCape] = -1;
		playerEquipment[playerAmulet] = -1;
		playerEquipment[playerChest] = -1;
		playerEquipment[playerShield] = -1;
		playerEquipment[playerLegs] = -1;
		playerEquipment[playerHands] = -1;
		playerEquipment[playerFeet] = -1;
		playerEquipment[playerRing] = -1;
		playerEquipment[playerArrows] = -1;
		playerEquipment[playerWeapon] = -1;

		heightLevel = 0;

		teleportToX = Config.START_LOCATION_X;
		teleportToY = Config.START_LOCATION_Y;

		absX = absY = -1;
		mapRegionX = mapRegionY = -1;
		currentX = currentY = 0;
		resetWalkingQueue();
		
	}

	public void destruct() {
		playerListSize = 0;
		for (int i = 0; i < maxPlayerListSize; i++)
			playerList[i] = null;
		absX = absY = -1;
		mapRegionX = mapRegionY = -1;
		currentX = currentY = 0;
		resetWalkingQueue();
	}

	public static final int maxPlayerListSize = Config.MAX_PLAYERS;
	public Player playerList[] = new Player[maxPlayerListSize];
	public int playerListSize = 0;

	public byte playerInListBitmap[] = new byte[(Config.MAX_PLAYERS + 7) >> 3];

	public static final int maxNPCListSize = NPCHandler.maxNPCs;
	public NPC npcList[] = new NPC[maxNPCListSize];
	public int npcListSize = 0;

	public byte npcInListBitmap[] = new byte[(NPCHandler.maxNPCs + 7) >> 3];

	public boolean withinDistance(Player otherPlr) {
		if (heightLevel != otherPlr.heightLevel)
			return false;
		int deltaX = otherPlr.absX - absX, deltaY = otherPlr.absY - absY;
		return deltaX <= 15 && deltaX >= -16 && deltaY <= 15 && deltaY >= -16;
	}

	public boolean withinDistance(NPC npc) {
		if (heightLevel != npc.heightLevel)
			return false;
		if (npc.needRespawn == true)
			return false;
		int deltaX = npc.absX - absX, deltaY = npc.absY - absY;
		return deltaX <= 15 && deltaX >= -16 && deltaY <= 15 && deltaY >= -16;
	}

	public int distanceToPoint(int pointX, int pointY) {
		return (int) Math.sqrt(Math.pow(absX - pointX, 2)
				+ Math.pow(absY - pointY, 2));
	}

	public int mapRegionX, mapRegionY;
	public int currentX, currentY;

	public int playerSE = 0x328;
	public int playerSEW = 0x333;
	public int playerSER = 0x334;

	public boolean updateRequired = true;

	public final int walkingQueueSize = 50;
	public int walkingQueueX[] = new int[walkingQueueSize],
			walkingQueueY[] = new int[walkingQueueSize];
	public int wQueueReadPtr = 0;
	public int wQueueWritePtr = 0;
	public boolean isRunning = true;
	public int teleportToX = -1, teleportToY = -1;

	public void resetWalkingQueue() {
		wQueueReadPtr = wQueueWritePtr = 0;

		for (int i = 0; i < walkingQueueSize; i++) {
			walkingQueueX[i] = currentX;
			walkingQueueY[i] = currentY;
		}
	}

	public void addToWalkingQueue(int x, int y) {
		// if (VirtualWorld.I(heightLevel, absX, absY, x, y, 0)) {
		int next = (wQueueWritePtr + 1) % walkingQueueSize;
		if (next == wQueueWritePtr)
			return;
		walkingQueueX[wQueueWritePtr] = x;
		walkingQueueY[wQueueWritePtr] = y;
		wQueueWritePtr = next;
		// }
	}

	public static boolean goodDistance(int objectX, int objectY, int playerX,
			int playerY, int distance) {
		int deltaX = objectX - playerX, deltaY = objectY - playerY;
		return deltaX <= distance && deltaX >= -distance && deltaY <= distance && deltaY >= -distance;
	}

	public int otherDirection;

	public int getNextWalkingDirection() {
		if (wQueueReadPtr == wQueueWritePtr)
			return -1;
		int dir;
		do {
			dir = Misc.direction(currentX, currentY,
					walkingQueueX[wQueueReadPtr], walkingQueueY[wQueueReadPtr]);
			if (dir == -1) {
				wQueueReadPtr = (wQueueReadPtr + 1) % walkingQueueSize;
			} else if ((dir & 1) != 0) {
				println_debug("Invalid waypoint in walking queue!");
				resetWalkingQueue();
				return -1;
			}
		} while ((dir == -1) && (wQueueReadPtr != wQueueWritePtr));
		if (dir == -1)
			return -1;
		dir >>= 1;
			currentX += Misc.directionDeltaX[dir];
			currentY += Misc.directionDeltaY[dir];
			absX += Misc.directionDeltaX[dir];
			absY += Misc.directionDeltaY[dir];
			setLocation(Location.create(absX, absY, heightLevel));
			return dir;
	}

	public int runEnergy = 100;
	public long lastRunRecovery;

	public boolean isRunning() {
		return isNewWalkCmdIsRunning() || (isRunning2 && isMoving);
	}

	public boolean didTeleport = false;
	public boolean mapRegionDidChange = false;
	public int dir1 = -1, dir2 = -1;
	public boolean createItems = false;
	public int poimiX = 0, poimiY = 0;

	public synchronized void getNextPlayerMovement() {
		mapRegionDidChange = false;
		didTeleport = false;
		dir1 = dir2 = -1;

		if (teleportToX != -1 && teleportToY != -1) {
			mapRegionDidChange = true;
			if (mapRegionX != -1 && mapRegionY != -1) {
				int relX = teleportToX - mapRegionX * 8, relY = teleportToY
						- mapRegionY * 8;
				if (relX >= 2 * 8 && relX < 11 * 8 && relY >= 2 * 8
						&& relY < 11 * 8)
					mapRegionDidChange = false;
			}
			if (mapRegionDidChange) {
				mapRegionX = (teleportToX >> 3) - 6;
				mapRegionY = (teleportToY >> 3) - 6;
			}
			currentX = teleportToX - 8 * mapRegionX;
			currentY = teleportToY - 8 * mapRegionY;
			absX = teleportToX;
			absY = teleportToY;
			resetWalkingQueue();

			teleportToX = teleportToY = -1;
			didTeleport = true;
		} else {
			dir1 = getNextWalkingDirection();
			if (dir1 == -1)
				return;
			if (isRunning) {
				dir2 = getNextWalkingDirection();
			}
			int deltaX = 0, deltaY = 0;
			if (currentX < 2 * 8) {
				deltaX = 4 * 8;
				mapRegionX -= 4;
				mapRegionDidChange = true;
			} else if (currentX >= 11 * 8) {
				deltaX = -4 * 8;
				mapRegionX += 4;
				mapRegionDidChange = true;
			}
			if (currentY < 2 * 8) {
				deltaY = 4 * 8;
				mapRegionY -= 4;
				mapRegionDidChange = true;
			} else if (currentY >= 11 * 8) {
				deltaY = -4 * 8;
				mapRegionY += 4;
				mapRegionDidChange = true;
			}

			if (mapRegionDidChange) {
				currentX += deltaX;
				currentY += deltaY;
				for (int i = 0; i < walkingQueueSize; i++) {
					walkingQueueX[i] += deltaX;
					walkingQueueY[i] += deltaY;
				}
				localObjects.clear();
			}
		}
	}

	private Location lastLocation;

	public Location getLastLocation() {
		if (lastLocation == null)
			lastLocation = getLocation().transform(Misc.random(-1, 1), Misc.random(-1, 1), 0);
		return lastLocation;
	}

	public void setLastLocation() {
		this.lastLocation = getLocation();
	}

	public void setLocation(Location location) {
		setLastLocation();
		this.location = location;
	}

	public void updateThisPlayerMovement(Stream str) {
		if (mapRegionDidChange) {
			str.createFrame(73);
			str.writeWordA(mapRegionX + 6);
			str.writeWord(mapRegionY + 6);
		}

		if (didTeleport) {
			str.createFrameVarSizeWord(81);
			str.initBitAccess();
			str.writeBits(1, 1);
			str.writeBits(2, 3);
			str.writeBits(2, heightLevel);
			str.writeBits(1, 1);
			str.writeBits(1, (updateRequired) ? 1 : 0);
			str.writeBits(7, currentY);
			str.writeBits(7, currentX);
			return;
		}

		if (dir1 == -1) {
			// don't have to update the character position, because we're
			// just standing
			str.createFrameVarSizeWord(81);
			str.initBitAccess();
			isMoving = false;
			if (updateRequired) {
				// tell client there's an update block appended at the end
				str.writeBits(1, 1);
				str.writeBits(2, 0);
			} else {
				str.writeBits(1, 0);
			}
			if (DirectionCount < 50) {
				DirectionCount++;
			}
		} else {
			DirectionCount = 0;
			str.createFrameVarSizeWord(81);
			str.initBitAccess();
			str.writeBits(1, 1);

			if (dir2 == -1) {
				isMoving = true;
				str.writeBits(2, 1);
				str.writeBits(3, Misc.xlateDirectionToClient[dir1]);
				if (updateRequired)
					str.writeBits(1, 1);
				else
					str.writeBits(1, 0);
			} else {
				isMoving = true;
				str.writeBits(2, 2);
				str.writeBits(3, Misc.xlateDirectionToClient[dir1]);
				str.writeBits(3, Misc.xlateDirectionToClient[dir2]);
				if (updateRequired)
					str.writeBits(1, 1);
				else
					str.writeBits(1, 0);
			}
		}
	}

	public void updatePlayerMovement(Stream str) {
		if (dir1 == -1) {
			if (updateRequired || isChatTextUpdateRequired()) {

				str.writeBits(1, 1);
				str.writeBits(2, 0);
			} else
				str.writeBits(1, 0);
		} else if (dir2 == -1) {

			str.writeBits(1, 1);
			str.writeBits(2, 1);
			str.writeBits(3, Misc.xlateDirectionToClient[dir1]);
			str.writeBits(1, (updateRequired || isChatTextUpdateRequired()) ? 1
					: 0);
		} else {

			str.writeBits(1, 1);
			str.writeBits(2, 2);
			str.writeBits(3, Misc.xlateDirectionToClient[dir1]);
			str.writeBits(3, Misc.xlateDirectionToClient[dir2]);
			str.writeBits(1, (updateRequired || isChatTextUpdateRequired()) ? 1
					: 0);
		}
	}

	public byte cachedPropertiesBitmap[] = new byte[(Config.MAX_PLAYERS + 7) >> 3];

	public void addNewNPC(NPC npc, Stream str, Stream updateBlock) {
		int id = npc.npcSlot;
		npcInListBitmap[id >> 3] |= 1 << (id & 7);
		npcList[npcListSize++] = npc;

		str.writeBits(14, id);

		int z = npc.absY - absY;
		if (z < 0)
			z += 32;
		str.writeBits(5, z);
		z = npc.absX - absX;
		if (z < 0)
			z += 32;
		str.writeBits(5, z);

		str.writeBits(1, 0);
		str.writeBits(14, npc.npcId);

		boolean savedUpdateRequired = npc.updateRequired;
		npc.updateRequired = true;
		npc.appendNPCUpdateBlock(updateBlock);
		npc.updateRequired = savedUpdateRequired;
		str.writeBits(1, 1);
	}

	public void addNewPlayer(Player plr, Stream str, Stream updateBlock) {
		int id = plr.playerId;
		playerInListBitmap[id >> 3] |= 1 << (id & 7);
		playerList[playerListSize++] = plr;
		str.writeBits(11, id);
		str.writeBits(1, 1);
		boolean savedFlag = plr.isAppearanceUpdateRequired();
		boolean savedUpdateRequired = plr.updateRequired;
		plr.setAppearanceUpdateRequired(true);
		plr.updateRequired = true;
		plr.appendPlayerUpdateBlock(updateBlock);
		plr.setAppearanceUpdateRequired(savedFlag);
		plr.updateRequired = savedUpdateRequired;
		str.writeBits(1, 1);
		int z = plr.absY - absY;
		if (z < 0)
			z += 32;
		str.writeBits(5, z);
		z = plr.absX - absX;
		if (z < 0)
			z += 32;
		str.writeBits(5, z);
	}

	public int DirectionCount = 0;

	public int npcId2 = 0;
	public boolean isNpc;
	/**
	 * Title Variables
	 */
	public String playerTitle = "";
	public int titleColor = 0;

	protected static Stream playerProps;
	public int safeTimer = 1000;
	public int logoutTimer;
	public int targetIndex, targetPercentage;
	public static int TARGET_PERCENTAGE_REQUIRED = 1;
	static {
		playerProps = new Stream(new byte[100]);
	}

	protected void appendPlayerAppearance(Stream str) {
		playerProps.currentOffset = 0;

		playerProps.writeByte(playerAppearance[0]);

		playerProps.writeByte(playerTitle.length() > 0 ? 1 : 0);
		playerProps.writeString(playerTitle);
		playerProps.writeByte(titleColor);
		playerProps.writeByte(headIcon);
		playerProps.writeByte(headIconPk);
		if (!isNpc) {
			if (playerEquipment[playerHat] > 1) {
				playerProps.writeWord(0x200 + playerEquipment[playerHat]);
			} else {
				playerProps.writeByte(0);
			}

			if (playerEquipment[playerCape] > 1) {
				playerProps.writeWord(0x200 + playerEquipment[playerCape]);
			} else {
				playerProps.writeByte(0);
			}

			if (playerEquipment[playerAmulet] > 1) {
				playerProps.writeWord(0x200 + playerEquipment[playerAmulet]);
			} else {
				playerProps.writeByte(0);
			}

			if (playerEquipment[playerWeapon] > 1) {
				playerProps.writeWord(0x200 + playerEquipment[playerWeapon]);
			} else {
				playerProps.writeByte(0);
			}

			if (playerEquipment[playerChest] > 1) {
				playerProps.writeWord(0x200 + playerEquipment[playerChest]);
			} else {
				playerProps.writeWord(0x100 + playerAppearance[2]);
			}

			if (playerEquipment[playerShield] > 1) {
				playerProps.writeWord(0x200 + playerEquipment[playerShield]);
			} else {
				playerProps.writeByte(0);
			}

			if (!Item.isFullBody(playerEquipment[playerChest])) {
				playerProps.writeWord(0x100 + playerAppearance[3]);
			} else {
				playerProps.writeByte(0);
			}

			if (playerEquipment[playerLegs] > 1) {
				playerProps.writeWord(0x200 + playerEquipment[playerLegs]);
			} else {
				playerProps.writeWord(0x100 + playerAppearance[5]);
			}

			if (!Item.isFullHelm(playerEquipment[playerHat])
					&& !Item.isFullMask(playerEquipment[playerHat])) {
				playerProps.writeWord(0x100 + playerAppearance[1]);
			} else {
				playerProps.writeByte(0);
			}

			if (playerEquipment[playerHands] > 1) {
				playerProps.writeWord(0x200 + playerEquipment[playerHands]);
			} else {
				playerProps.writeWord(0x100 + playerAppearance[4]);
			}

			if (playerEquipment[playerFeet] > 1) {
				playerProps.writeWord(0x200 + playerEquipment[playerFeet]);
			} else {
				playerProps.writeWord(0x100 + playerAppearance[6]);
			}

			if (playerAppearance[0] != 1
					&& !Item.isFullMask(playerEquipment[playerHat])) {
				playerProps.writeWord(0x100 + playerAppearance[7]);
			} else {
				playerProps.writeByte(0);
			}
		} else {
			playerProps.writeWord(-1);
			playerProps.writeWord(npcId2);
		}

		playerProps.writeByte(playerAppearance[8]);
		playerProps.writeByte(playerAppearance[9]);
		playerProps.writeByte(playerAppearance[10]);
		playerProps.writeByte(playerAppearance[11]);
		playerProps.writeByte(playerAppearance[12]);
		playerProps.writeWord(playerStandIndex); // standAnimIndex
		playerProps.writeWord(playerTurnIndex); // standTurnAnimIndex
		playerProps.writeWord(playerWalkIndex); // walkAnimIndex
		playerProps.writeWord(playerTurn180Index); // turn180AnimIndex
		playerProps.writeWord(playerTurn90CWIndex); // turn90CWAnimIndex
		playerProps.writeWord(playerTurn90CCWIndex); // turn90CCWAnimIndex
		playerProps.writeWord(playerRunIndex); // runAnimIndex

		playerProps.writeQWord(Misc.playerNameToInt64(playerName));

		/*
		 * int mag = (int) ((getLevelForXP(playerXP[6])) * 1.5); int ran = (int)
		 * ((getLevelForXP(playerXP[4])) * 1.5); int attstr = (int) ((double)
		 * (getLevelForXP(playerXP[0])) + (double)
		 * (getLevelForXP(playerXP[2])));
		 * 
		 * combatLevel = 0; if (ran > attstr) { combatLevel = (int)
		 * (((getLevelForXP(playerXP[1])) * 0.25) +
		 * ((getLevelForXP(playerXP[3])) * 0.25) + ((getLevelForXP(playerXP[5]))
		 * * 0.125) + ((getLevelForXP(playerXP[4])) * 0.4875)); } else if (mag >
		 * attstr) { combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
		 * + ((getLevelForXP(playerXP[3])) * 0.25) +
		 * ((getLevelForXP(playerXP[5])) * 0.125) +
		 * ((getLevelForXP(playerXP[6])) * 0.4875)); } else { combatLevel =
		 * (int) (((getLevelForXP(playerXP[1])) * 0.25) +
		 * ((getLevelForXP(playerXP[3])) * 0.25) + ((getLevelForXP(playerXP[5]))
		 * * 0.125) + ((getLevelForXP(playerXP[0])) * 0.325) +
		 * ((getLevelForXP(playerXP[2])) * 0.325)); }
		 */
		playerProps.writeByte(combatLevel); // combat level
		playerProps.writeWord(0);
		str.writeByteC(playerProps.currentOffset);
		str.writeBytes(playerProps.buffer, playerProps.currentOffset, 0);
	}

	public int calculateCombatLevel() {
		int j = getLevelForXP(playerXP[playerAttack]);
		int k = getLevelForXP(playerXP[playerDefence]);
		int l = getLevelForXP(playerXP[playerStrength]);
		int i1 = getLevelForXP(playerXP[playerHitpoints]);
		int j1 = getLevelForXP(playerXP[playerPrayer]);
		int k1 = getLevelForXP(playerXP[playerRanged]);
		int l1 = getLevelForXP(playerXP[playerMagic]);
		combatLevel = (int) (((k + i1) + Math.floor(j1 / 2)) * 0.25D) + 1;
		double d = (j + l) * 0.32500000000000001D;
		double d1 = Math.floor(k1 * 1.5D) * 0.32500000000000001D;
		double d2 = Math.floor(l1 * 1.5D) * 0.32500000000000001D;
		if (d >= d1 && d >= d2) {
			combatLevel += d;
		} else if (d1 >= d && d1 >= d2) {
			combatLevel += d1;
		} else if (d2 >= d && d2 >= d1) {
			combatLevel += d2;
		}
		return combatLevel;
	}

	public int getLevelForXP(int exp) {
		if (exp > 13034430) {
			return 99;
		} else {
			int points = 0;
			for (int lvl = 1; lvl <= 99; ++lvl) {
				points = (int) ((double) points + Math.floor((double) lvl
						+ 300.0D * Math.pow(2.0D, (double) lvl / 7.0D)));
				int var5 = (int) Math.floor((double) (points / 4));
				if (var5 >= exp) {
					return lvl;
				}
			}

			return 99;
		}
	}

	private boolean chatTextUpdateRequired = false;
	private byte chatText[] = new byte[4096];
	private byte chatTextSize = 0;
	private int chatTextColor = 0;
	private int chatTextEffects = 0;

	protected void appendPlayerChatText(Stream str) {
		str.writeWordBigEndian(((getChatTextColor() & 0xFF) << 8) + (getChatTextEffects() & 0xFF));
		str.writeByte(playerRights);
		str.writeByteC(getChatTextSize());
		str.writeBytes_reverse(getChatText(), getChatTextSize(), 0);
	}

	public void forcedChat(String text) {
		forcedText = text;
		forcedChatUpdateRequired = true;
		updateRequired = true;
		setAppearanceUpdateRequired(true);
	}

	public String forcedText = "null";

	public void appendForcedChat(Stream str) {
		str.writeString(forcedText);
	}

	/**
	 * Graphics
	 **/

	public int mask100var1 = 0;
	public int mask100var2 = 0;
	protected boolean mask100update = false;

	public void appendMask100Update(Stream str) {
		str.writeWordBigEndian(mask100var1);
		str.writeDWord(mask100var2);
	}

	public void gfx100(int gfx) {
		mask100var1 = gfx;
		mask100var2 = 6553600;
		mask100update = true;
		updateRequired = true;
	}

	public void gfx0(int gfx) {
		mask100var1 = gfx;
		mask100var2 = 65536;
		mask100update = true;
		updateRequired = true;
	}

	public boolean wearing2h() {
		Client c = (Client) this;
		String s = c.getItems().getItemName(c.playerEquipment[c.playerWeapon]);
		if (s.contains("2h"))
			return true;
		else if (s.contains("godsword"))
			return true;
		return false;
	}

	/**
	 * Animations
	 **/
	public void startAnimation(int animId) {
		if (wearing2h() && animId == 829)
			return;
		animationRequest = animId;
		animationWaitCycles = 0;
		updateRequired = true;
	}

	public void startAnimation(int animId, int time) {
		animationRequest = animId;
		animationWaitCycles = time;
		updateRequired = true;
	}

	public void appendAnimationRequest(Stream str) {
		str.writeWordBigEndian((animationRequest == -1) ? 65535
				: animationRequest);
		str.writeByteC(animationWaitCycles);
	}

	/**
	 * Face Update
	 **/

	protected boolean faceUpdateRequired = false;
	public int face = -1;
	public int FocusPointX = -1, FocusPointY = -1;

	public void faceUpdate(int index) {
		face = index;
		faceUpdateRequired = true;
		updateRequired = true;
	}

	public void appendFaceUpdate(Stream str) {
		str.writeWordBigEndian(face);
	}

	public void turnPlayerTo(int pointX, int pointY) {
		FocusPointX = 2 * pointX + 1;
		FocusPointY = 2 * pointY + 1;
		faceUpdateRequired = true;
		updateRequired = true;
	}

	private void appendSetFocusDestination(Stream str) {
		str.writeWordBigEndianA(FocusPointX);
		str.writeWordBigEndian(FocusPointY);
	}

	/**
	 * Hit Update
	 **/

	protected void appendHitUpdate(Stream str) {
		str.writeByte(getHitDiff()); // What the perseon got 'hit' for
		if (poisonMask == 1) {
			str.writeByteA(2);
		} else if (poisonMask == 3) {
			str.writeByteA(5);
		} else if (getHitDiff() > 0) {
			str.writeByteA(1); // 0: red hitting - 1: blue hitting
		} else {
			str.writeByteA(0); // 0: red hitting - 1: blue hitting
		}
		if (playerLevel[3] <= 0) {
			playerLevel[3] = 0;
			isDead = true;
		}
		str.writeByteC(playerLevel[3]); // Their current hp, for HP bar
		str.writeByte(getLevelForXP(playerXP[3]));
	}

	protected void appendHitUpdate2(Stream str) {
		str.writeByte(hitDiff2); // What the perseon got 'hit' for
		if (poisonMask == 2) {
			str.writeByteS(2);
			poisonMask = -1;
		} else if (hitDiff2 > 0) {
			str.writeByteS(1); // 0: red hitting - 1: blue hitting
		} else {
			str.writeByteS(0); // 0: red hitting - 1: blue hitting
		}
		if (playerLevel[3] <= 0) {
			playerLevel[3] = 0;
			isDead = true;
		}
		str.writeByte(playerLevel[3]); // Their current hp, for HP bar
		str.writeByteC(getLevelForXP(playerXP[3])); // Their max hp, for HP
	}

	public void appendPlayerUpdateBlock(Stream str) {
		if (!updateRequired && !isChatTextUpdateRequired())
			return; // nothing required
		int updateMask = 0;
		if (mask100update) {
			updateMask |= 0x100;
		}
		if (animationRequest != -1) {
			updateMask |= 8;
		}
		if (forcedChatUpdateRequired) {
			updateMask |= 4;
		}
		if (isChatTextUpdateRequired()) {
			updateMask |= 0x80;
		}
		if (isAppearanceUpdateRequired()) {
			updateMask |= 0x10;
		}
		if (faceUpdateRequired) {
			updateMask |= 1;
		}
		if (FocusPointX != -1) {
			updateMask |= 2;
		}
		if (isHitUpdateRequired()) {
			updateMask |= 0x20;
		}

		if (hitUpdateRequired2) {
			updateMask |= 0x200;
		}

		if (updateMask >= 0x100) {
			updateMask |= 0x40;
			str.writeByte(updateMask & 0xFF);
			str.writeByte(updateMask >> 8);
		} else {
			str.writeByte(updateMask);
		}

		// now writing the various update blocks itself - note that their
		// order crucial
		if (mask100update) {
			appendMask100Update(str);
		}
		if (animationRequest != -1) {
			appendAnimationRequest(str);
		}
		if (forcedChatUpdateRequired) {
			appendForcedChat(str);
		}
		if (isChatTextUpdateRequired()) {
			appendPlayerChatText(str);
		}
		if (faceUpdateRequired) {
			appendFaceUpdate(str);
		}
		if (isAppearanceUpdateRequired()) {
			appendPlayerAppearance(str);
		}
		if (FocusPointX != -1) {
			appendSetFocusDestination(str);
		}
		if (isHitUpdateRequired()) {
			appendHitUpdate(str);
		}
		if (hitUpdateRequired2) {
			appendHitUpdate2(str);
		}
	}

	public void clearUpdateFlags() {
		updateRequired = false;
		setChatTextUpdateRequired(false);
		setAppearanceUpdateRequired(false);
		setHitUpdateRequired(false);
		hitUpdateRequired2 = false;
		forcedChatUpdateRequired = false;
		mask100update = false;
		animationRequest = -1;
		FocusPointX = -1;
		FocusPointY = -1;
		poisonMask = -1;
		faceUpdateRequired = false;
		face = 65535;
	}

	public void stopMovement() {
		// System.out.println("Stopped1.");
		if (teleportToX <= 0 && teleportToY <= 0) {
			teleportToX = absX;
			teleportToY = absY;
		}
		newWalkCmdSteps = 0;
		getNewWalkCmdX()[0] = getNewWalkCmdY()[0] = travelBackX[0] = travelBackY[0] = 0;
		getNextPlayerMovement();
	}

	private int newWalkCmdX[] = new int[walkingQueueSize];
	private int newWalkCmdY[] = new int[walkingQueueSize];
	public int newWalkCmdSteps = 0;
	private boolean newWalkCmdIsRunning = false;
	protected int travelBackX[] = new int[walkingQueueSize];
	protected int travelBackY[] = new int[walkingQueueSize];
	protected int numTravelBackSteps = 0;

	public void preProcessing() {
		newWalkCmdSteps = 0;
	}

	public abstract boolean processQueuedPackets();

	public void postProcessing() {
		if (this.newWalkCmdSteps > 0) {
			int firstX = this.getNewWalkCmdX()[0];
			int firstY = this.getNewWalkCmdY()[0];
			boolean found = false;
			this.numTravelBackSteps = 0;
			int ptr = this.wQueueReadPtr;
			int dir = Misc.direction(this.currentX, this.currentY, firstX,
					firstY);
			if (dir != -1 && (dir & 1) != 0) {
				do {
					int var13 = dir;
					--ptr;
					if (ptr < 0) {
						ptr = 49;
					}

					this.travelBackX[this.numTravelBackSteps] = this.walkingQueueX[ptr];
					this.travelBackY[this.numTravelBackSteps++] = this.walkingQueueY[ptr];
					dir = Misc.direction(this.walkingQueueX[ptr],
							this.walkingQueueY[ptr], firstX, firstY);
					if (var13 != dir) {
						found = true;
						break;
					}
				} while (ptr != this.wQueueWritePtr);
			} else {
				found = true;
			}

			if (found) {
				this.wQueueWritePtr = this.wQueueReadPtr;
				this.addToWalkingQueue(this.currentX, this.currentY);
				int i;
				if (dir != -1 && (dir & 1) != 0) {
					for (i = 0; i < this.numTravelBackSteps - 1; ++i) {
						this.addToWalkingQueue(this.travelBackX[i],
								this.travelBackY[i]);
					}

					i = this.travelBackX[this.numTravelBackSteps - 1];
					int wayPointY2 = this.travelBackY[this.numTravelBackSteps - 1];
					int wayPointX1;
					int wayPointY1;
					if (this.numTravelBackSteps == 1) {
						wayPointX1 = this.currentX;
						wayPointY1 = this.currentY;
					} else {
						wayPointX1 = this.travelBackX[this.numTravelBackSteps - 2];
						wayPointY1 = this.travelBackY[this.numTravelBackSteps - 2];
					}

					dir = Misc.direction(wayPointX1, wayPointY1, i, wayPointY2);
					if (dir != -1 && (dir & 1) == 0) {
						dir >>= 1;
					found = false;
					int x = wayPointX1;
					int y = wayPointY1;

					while (x != i || y != wayPointY2) {
						x += Misc.directionDeltaX[dir];
						y += Misc.directionDeltaY[dir];
						if ((Misc.direction(x, y, firstX, firstY) & 1) == 0) {
							found = true;
							break;
						}
					}

					if (!found) {
						this.println_debug("Fatal: Internal error: unable to determine connection vertex!  wp1=("
								+ wayPointX1
								+ ", "
								+ wayPointY1
								+ "), wp2=("
								+ i
								+ ", "
								+ wayPointY2
								+ "), "
								+ "first=("
								+ firstX
								+ ", "
								+ firstY + ")");
					} else {
						this.addToWalkingQueue(wayPointX1, wayPointY1);
					}
					} else {
						this.println_debug("Fatal: The walking queue is corrupt! wp1=("
								+ wayPointX1
								+ ", "
								+ wayPointY1
								+ "), "
								+ "wp2=(" + i + ", " + wayPointY2 + ")");
					}
				} else {
					for (i = 0; i < this.numTravelBackSteps; ++i) {
						this.addToWalkingQueue(this.travelBackX[i],
								this.travelBackY[i]);
					}
				}

				for (i = 0; i < this.newWalkCmdSteps; ++i) {
					this.addToWalkingQueue(this.getNewWalkCmdX()[i],
							this.getNewWalkCmdY()[i]);
				}
			}

			this.isRunning = this.isNewWalkCmdIsRunning() || this.isRunning2;
		}
	}
	public int getMapRegionX() {
		return mapRegionX;
	}

	public int getMapRegionY() {
		return mapRegionY;
	}

	public int getId() {
		return playerId;
	}

	public boolean inPcBoat() {
		return absX >= 2659 && absX <= 2664 && absY >= 2637 && absY <= 2644;
	}

	public boolean inPcGame() {
		return absX >= 2624 && absX <= 2690 && absY >= 2550 && absY <= 2619;
	}

	public boolean inPcGame2() {
		return teleportToX >= 2624 && teleportToX <= 2690 && teleportToY >= 2550 && teleportToY <= 2619;
	}

	
	public void setChatTextEffects(int chatTextEffects) {
		this.chatTextEffects = chatTextEffects;
	}

	public int getChatTextEffects() {
		return chatTextEffects;
	}

	public void setChatTextSize(byte chatTextSize) {
		this.chatTextSize = chatTextSize;
	}

	public byte getChatTextSize() {
		return chatTextSize;
	}

	public void setChatTextUpdateRequired(boolean chatTextUpdateRequired) {
		this.chatTextUpdateRequired = chatTextUpdateRequired;
	}

	public boolean isChatTextUpdateRequired() {
		return chatTextUpdateRequired;
	}

	public void setChatText(byte chatText[]) {
		this.chatText = chatText;
	}

	public byte[] getChatText() {
		return chatText;
	}

	public void setChatTextColor(int chatTextColor) {
		this.chatTextColor = chatTextColor;
	}

	public int getChatTextColor() {
		return chatTextColor;
	}

	public void setNewWalkCmdX(int newWalkCmdX[]) {
		this.newWalkCmdX = newWalkCmdX;
	}

	public int[] getNewWalkCmdX() {
		return newWalkCmdX;
	}

	public void setNewWalkCmdY(int newWalkCmdY[]) {
		this.newWalkCmdY = newWalkCmdY;
	}

	public int[] getNewWalkCmdY() {
		return newWalkCmdY;
	}

	public void setNewWalkCmdIsRunning(boolean newWalkCmdIsRunning) {
		this.newWalkCmdIsRunning = newWalkCmdIsRunning;
	}

	public boolean isNewWalkCmdIsRunning() {
		return newWalkCmdIsRunning;
	}

	public void setInStreamDecryption(ISAACRandomGen inStreamDecryption) {
	}

	public void setOutStreamDecryption(ISAACRandomGen outStreamDecryption) {
	}

	public boolean samePlayer() {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (j == playerId)
				continue;
			if (PlayerHandler.players[j] != null) {
				if (PlayerHandler.players[j].playerName
						.equalsIgnoreCase(playerName)) {
					disconnected = true;
					return true;
				}
			}
		}
		return false;
	}

	public void putInCombat(int attacker) {
		underAttackBy = attacker;
		logoutDelay = System.currentTimeMillis();
		singleCombatDelay = System.currentTimeMillis();
	}

	public void dealDamage(int damage) {
		Client c = (Client) this;
		if (damage > playerLevel[3])
			damage = playerLevel[3];
		if (teleTimer <= 0)
			playerLevel[3] -= damage;
		else {
			if (hitUpdateRequired)
				hitUpdateRequired = false;
			if (hitUpdateRequired2)
				hitUpdateRequired2 = false;
			if (playerEquipment[playerShield] == 12817) {
				if (Misc.random(9) <= 6) {
					damage = (int) (damage * .75);
					c.sendMessage("Your shield absorbs 25% of the hit...");
				}
			}
		}

	}

	public int[] damageTaken = new int[Config.MAX_PLAYERS];

	public void handleHitMask(int damage) {
		if (!hitUpdateRequired) {
			hitUpdateRequired = true;
			setHitDiff(damage);
		} else if (!hitUpdateRequired2) {
			hitUpdateRequired2 = true;
			hitDiff2 = damage;
		}
		updateRequired = true;
	}

	public boolean inGodWarsBoss() {
		if (heightLevel == 2 || heightLevel == 1 || heightLevel == 0) {
			if (absX > 2886 && absX < 2908 && absY > 5255 && absY < 5277) {
				return true;
			} else if (absX > 2863 && absX < 2879 && absY > 5351 && absY < 5372) {
				return true;
			} else if (absX > 2917 && absX < 2947 && absY > 5316 && absY < 5332) {
				return true;
			} else if (absX > 2822 && absX < 2843 && absY > 5295 && absY < 5309) {
				return true;
			}
		}
		return false;
	}

	/* Duo Slayer Variables */
	public int duoTask = -1, duoTaskAmount = -1;
	public int duoPoints;
	public WeakReference<Player> duoPartner;
	public long lastSlayerInvite;
	public WeakReference<Player> potentialPartner;
	public int tasksCompleted = 0;
	public boolean hasNpc = false;
	public int summonId;

	public void setDuoPartner(Player client) {
		duoPartner = client == null ? null : new WeakReference<Player>(client);
	}

	public Player getDuoPartner() {
		return duoPartner == null ? null : duoPartner.get();
	}

	public Player getPotentialPartner() {
		return potentialPartner == null ? null : potentialPartner.get();
	}

	public void setPotentialPartner(Player client) {
		potentialPartner = client == null ? null : new WeakReference<Player>(
				client);
	}

	public boolean hasSecondHit = false;
	public boolean muted;

	public boolean WithinDistance(int objectX, int objectY, int playerX,
			int playerY, int distance) {
		for (int i = 0; i <= distance; i++) {
			for (int j = 0; j <= distance; j++) {
				if ((objectX + i) == playerX
						&& ((objectY + j) == playerY
						|| (objectY - j) == playerY || objectY == playerY)) {
					return true;
				} else if ((objectX - i) == playerX
						&& ((objectY + j) == playerY
						|| (objectY - j) == playerY || objectY == playerY)) {
					return true;
				} else if (objectX == playerX
						&& ((objectY + j) == playerY
						|| (objectY - j) == playerY || objectY == playerY)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean inKqArea() {
		if (absX >= 3467 && absX <= 3506 && absY >= 9477 && absY <= 9513) {
			return true;
		}
		return false;
	}

	public int getWalkAnimation() {
		return playerWalkIndex;
	}

	public int setWalkAnimation(int animation) {
		this.playerWalkIndex = animation;
		return animation;
	}
	public int getRunAnimation() {
		return playerRunIndex;
	}

	public int setRunAnimation(int animation) {
		this.playerRunIndex = animation;
		return animation;
	}

	public int getStandAnimation() {
		return playerStandIndex;
	}

	public int setStandAnimation(int animation) {
		this.playerStandIndex = animation;
		return animation;
	}

	public int getStandTurnAnimation() {
		return playerTurnIndex;
	}

	public int setStandTurnAnimation(int animation) {
		this.playerTurnIndex = animation;
		return animation;
	}

	public int getTurn90ClockwiseAnimation() {
		return playerTurn90CWIndex;
	}

	public int setTurn90ClockwiseAnimation(int animation) {
		this.playerTurn90CWIndex = animation;
		return animation;
	}

	public int getTurn90CounterClockwiseAnimation() {
		return playerTurn90CCWIndex;
	}

	public int setTurn90CounterClockwiseAnimation(int animation) {
		this.playerTurn90CCWIndex = animation;
		return animation;
	}

	public int getTurn180Animation() {
		return playerTurn180Index;
	}

	public int setTurn180Animation(int animation) {
		this.playerTurn180Index = animation;
		return animation;
	}
	
	public void addObjectToArea(GameObject obj) {
		localObjects.add(obj);
	}
	
	public boolean isObjectInArea(GameObject obj) {
		return localObjects.contains(obj);
	}
	
	public void doFollow(boolean postProcess) {
		Client c = (Client) this;
		c.attr(A.POST_PROCESS_FOR_FOLLOWING, postProcess);
		if (followId > 0) {
			c.getPA().followPlayer();
		} else if (followId2 > 0) {
			c.getPA().followNpc();
		}
	}

	public int getTotalLevel() {
		int level = 0;
		for (int i = 0; i < playerLevel.length; i++) {
			level += playerLevel[i];
		}
		return level;
	}


}