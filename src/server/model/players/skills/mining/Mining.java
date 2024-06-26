package server.model.players.skills.mining;

import server.Server;
import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import server.model.players.Client;
import server.model.players.skills.SkillHandler;

/**
 * Class Mining Handles Mining
 * 
 * @author 2012 20:16 22/01/2011
 */

public class Mining extends SkillHandler {

	public static void mineEss(final Client c, final int object) {
		if (!noInventorySpace(c, "mining")) {
			resetMining(c);
			return;
		}
		if (!hasPickaxe(c)) {
			c.sendMessage("You need a Mining pickaxe which you need a Mining level to use.");
			return;
		}
		if (c.playerSkilling[14]) {
			return;
		}

		c.playerSkilling[14] = true;
		c.stopPlayerSkill = true;
		c.turnPlayerTo(c.objectX, c.objectY);
		c.startAnimation(getAnimation(c));

		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {

				c.getItems().addItem(1436, 1);
				c.sendMessage("You manage to mine some "
						+ c.getItems().getItemName(1436).toLowerCase() + ".");
				c.getPA().addSkillXP(15 * MINING_XP, c.playerMining);
				c.startAnimation(getAnimation(c));

				if (!hasPickaxe(c)) {
					c.sendMessage("You need a Mining pickaxe which you need a Mining level to use.");
					resetMining(c);
					container.stop();
				}
				if (!c.stopPlayerSkill) {
					resetMining(c);
					container.stop();
				}
				if (!noInventorySpace(c, "mining")) {
					resetMining(c);
					container.stop();
				}

			}

			@Override
			public void stop() {

			}
		}, 2);
	}

	public static void attemptData(final Client c, final int object,
			final int obX, final int obY) {
		if (!noInventorySpace(c, "mining")) {
			resetMining(c);
			return;
		}
		if (!hasRequiredLevel(c, 14, getLevelReq(c, object), "mining",
				"mine here")) {
			return;
		}
		if (!hasPickaxe(c)) {
			c.sendMessage("You need a Mining pickaxe which you need a Mining level to use.");
			return;
		}
		c.sendMessage("You swing your pick at the rock.");

		if (c.playerSkilling[14]) {
			return;
		}

		c.playerSkilling[14] = true;
		c.stopPlayerSkill = true;

		c.startAnimation(getAnimation(c));

		for (int i = 0; i < data.length; i++) {
			if (object == data[i][0]) {

				c.playerSkillProp[14][0] = data[i][1];
				c.playerSkillProp[14][1] = data[i][3];

				c.startAnimation(getAnimation(c));

				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {

						if (c.playerSkillProp[14][0] > 0) {
							c.getItems().addItem(c.playerSkillProp[14][0], 1);
							c.sendMessage("You manage to mine some "
									+ c.getItems()
											.getItemName(
													c.playerSkillProp[14][0])
											.toLowerCase() + ".");
						}
						if (c.playerSkillProp[14][1] > 0) {
							c.getPA().addSkillXP(
									c.playerSkillProp[14][1] * MINING_XP,
									c.playerMining);
							Server.gameObjectManager.createAnObject(c, 451, obX,
									obY);
						}
						if (!hasPickaxe(c)) {
							c.sendMessage("You need a Mining pickaxe which you need a Mining level to use.");
							resetMining(c);
							container.stop();
						}
						if (!c.stopPlayerSkill) {
							resetMining(c);
							container.stop();
						}
						if (!noInventorySpace(c, "mining")) {
							resetMining(c);
							container.stop();
						}

						resetMining(c);

						container.stop();
					}

					@Override
					public void stop() {

					}
				}, getTimer(c, object));
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {
						Server.gameObjectManager
								.createAnObject(c, object, obX, obY);
						container.stop();
					}

					@Override
					public void stop() {

					}
				}, getTimer(c, object) + getRespawnTime(c, object));

				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {
						if (c.playerSkilling[14]) {
							c.startAnimation(getAnimation(c));
						}
						if (!c.stopPlayerSkill || !c.playerSkilling[14]) {
							resetMining(c);
							container.stop();
						}
					}

					@Override
					public void stop() {

					}
				}, 15);
			}
		}
	}

	private static int getTimer(Client c, int i) {
		if (c.inPvP() && c.getWealth() > 500000 && getMineTime(c, i) > 4) {
			return ((getMineTime(c, i) - 2) + getTime(c) + playerMiningLevel(c));
		}
		return (getMineTime(c, i) + getTime(c) + playerMiningLevel(c));
	}

	private static int getMineTime(Client c, int object) {
		for (int i = 0; i < data.length; i++) {
			if (object == data[i][0]) {
				return data[i][4];
			}
		}
		return -1;
	}

	private static int playerMiningLevel(Client c) {
		return (10 - (int) Math.floor(c.playerLevel[14] / 10));
	}

	private static int getTime(Client c) {
		for (int i = 0; i < pickaxe.length; i++) {
			if (c.getItems().playerHasItem(pickaxe[i][0])
					|| c.playerEquipment[3] == pickaxe[i][0]) {
				if (c.playerLevel[c.playerMining] >= pickaxe[i][1]) {
					return pickaxe[i][2];
				}
			}
		}
		return 10;
	}

	public static void resetMining(Client c) {
		c.playerSkilling[14] = false;
		c.stopPlayerSkill = false;
		for (int i = 0; i < 2; i++) {
			c.playerSkillProp[14][i] = -1;
		}
		c.startAnimation(65535);
	}

	public static boolean miningRocks(Client c, int object) {
		for (int i = 0; i < data.length; i++) {
			if (object == data[i][0]) {
				return true;
			}
		}
		return false;
	}

	private static int getRespawnTime(Client c, int object) {
		for (int i = 0; i < data.length; i++) {
			if (object == data[i][0]) {
				return data[i][5];
			}
		}
		return -1;
	}

	private static int getLevelReq(Client c, int object) {
		for (int i = 0; i < data.length; i++) {
			if (object == data[i][0]) {
				return data[i][2];
			}
		}
		return -1;
	}

	private static boolean hasPickaxe(Client c) {
		for (int i = 0; i < animation.length; i++) {
			if (c.getItems().playerHasItem(animation[i][0])
					|| c.playerEquipment[3] == animation[i][0]) {
				return true;
			}
		}
		return false;
	}

	private static int getAnimation(Client c) {
		for (int i = 0; i < animation.length; i++) {
			if (c.getItems().playerHasItem(animation[i][0])
					|| c.playerEquipment[3] == animation[i][0]) {
				return animation[i][1];
			}
		}
		return -1;
	}

	private static int[][] animation = { {12797, 6758}, { 11920, 6758 }, { 1275, 6746 },
			{ 1271, 6750 }, { 1273, 6751 }, { 1269, 6749 }, { 1267, 6748 },
			{ 1265, 6747 }, };

	private static int[][] pickaxe = { { 12797, 61, 0 }, // DRAGON KIT
			{ 11920, 61, 0 }, // DRAGON
			{ 1275, 41, 1 }, // RUNE
			{ 1271, 31, 2 }, // ADDY
			{ 1273, 21, 3 }, // MITH
			{ 1269, 6, 4 }, // STEEL
			{ 1267, 1, 4 }, // IRON
			{ 1265, 1, 5 }, // BRONZE
	};

	private static int[][] data = { { 9711, 434, 1, 18, 1, 5 }, // CLAY
			{ 9713, 434, 1, 18, 1, 5 }, // CLAY
			{ 2091, 436, 1, 18, 1, 5 }, // COPPER
			{ 2090, 436, 1, 18, 1, 5 }, // COPPER
			{ 11961, 436, 1, 18, 1, 5 }, // COPPER
			{ 11962, 436, 1, 18, 1, 5 }, // COPPER
			{ 13708, 436, 1, 18, 1, 5 }, // COPPER
			{ 13712, 438, 1, 18, 1, 5 }, // TIN
			{ 2095, 438, 1, 18, 1, 5 }, // TIN
			{ 9714, 438, 1, 18, 1, 5 }, // TIN
			{ 9716, 438, 1, 18, 1, 5 }, // TIN
			{ 11959, 438, 1, 18, 1, 5 }, // TIN
			{ 11957, 438, 1, 18, 1, 5 }, // TIN
			{ 13711, 438, 1, 18, 1, 5 }, // TIN
			{ 2093, 440, 15, 35, 2, 5 }, // IRON
			{ 2092, 440, 15, 35, 2, 5 }, // IRON
			{ 11956, 440, 15, 35, 2, 5 }, // IRON
			{ 11954, 440, 15, 35, 2, 5 }, // IRON
			{ 13710, 440, 15, 35, 2, 5 }, // IRON
			{ 9717, 453, 30, 50, 3, 8 }, // COAL
			{ 9719, 453, 30, 50, 3, 8 }, // COAL
			{ 9718, 453, 30, 50, 3, 8 }, // COAL
			{ 2097, 453, 30, 50, 3, 8 }, // COAL
			{ 2096, 453, 30, 50, 3, 8 }, // COAL
			{ 13714, 453, 30, 50, 3, 8 }, // COAL
			{ 13706, 453, 30, 50, 3, 8 }, // COAL
			{ 2098, 444, 40, 65, 3, 10 }, // GOLD
			{ 13715, 444, 40, 65, 3, 10 }, // GOLD
			{ 7492, 444, 40, 65, 3, 10 }, // GOLD
			{ 13707, 444, 40, 65, 3, 10 }, // GOLD
			{ 2103, 447, 55, 80, 5, 25 }, // MITH
			{ 13719, 447, 55, 80, 5, 25 }, // MITH
			{ 13720, 449, 70, 95, 7, 50 }, // ADDY
			{ 14168, 449, 70, 95, 7, 50 }, // ADDY
			{ 2101, 442, 20, 40, 5, 5 }, // SILVER
			{ 7418, 451, 85, 125, 25, 100 }, // RUNE
	};
}