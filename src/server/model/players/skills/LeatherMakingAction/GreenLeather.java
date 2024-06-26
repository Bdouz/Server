package server.model.players.skills.LeatherMakingAction;

import java.util.HashMap;

import server.model.players.Client;
import server.model.players.skills.crafting.LeatherMaking;

public class GreenLeather extends LeatherMaking {

	public GreenLeather(final Client player, final int used, final int used2, final int result, final int amount, final int manualAmount, final int level, final double experience) {
		super(player, used, used2, result, amount, manualAmount, level, experience);
	}

	public static GreenLeather create(final Client player, int buttonId, int manualAmount) {
		final GreenLeatherMake greenLeatherMake = GreenLeatherMake.forId(buttonId);
		if (greenLeatherMake == null || (greenLeatherMake.getAmount() == 0 && manualAmount == 0))
			return null;
		return new GreenLeather(player, greenLeatherMake.getUsed(), greenLeatherMake.getUsed2(), greenLeatherMake.getResult(), greenLeatherMake.getAmount(), manualAmount, greenLeatherMake.getLevel(), greenLeatherMake.getExperience());
	}

	public static enum GreenLeatherMake {
		VAMB(34185, 1754, 1, 1065, 1, 57, 62), VAMB5(34184, 1754, 1, 1065, 5, 57, 62), VAMB10(34183, 1754, 1, 1065, 10, 57, 62), VAMBX(34182, 1754, 1, 1065, 0, 57, 62), CHAPS(34189, 1754, 2, 1099, 1, 60, 124), CHAPS5(34188, 1754, 2, 1099, 5, 60, 124), CHAPS10(34187, 1754, 2, 1099, 10, 60, 124), CHAPSX(34186, 1754, 2, 1099, 0, 60, 124), BODY(34193, 1754, 3, 1135, 1, 63, 186), BODY5(34192, 1754, 3, 1135, 5, 63, 186), BODY10(34191, 1754, 3, 1135, 10, 63, 186), BODYX(34190, 1754, 3, 1135, 0, 63, 186);

		private int buttonId;
		private int used;
		private int used2;
		private int result;
		private int amount;
		private int level;
		private double experience;

		public static HashMap<Integer, GreenLeatherMake> greenLeatherItems = new HashMap<Integer, GreenLeatherMake>();

		public static GreenLeatherMake forId(int id) {
			if (greenLeatherItems == null) {
				return null;
			}
			return greenLeatherItems.get(id);
		}

		static {
			for (GreenLeatherMake data : GreenLeatherMake.values()) {
				greenLeatherItems.put(data.buttonId, data);
			}
		}

		private GreenLeatherMake(int buttonId, int used, int used2, int result, int amount, int level, double experience) {
			this.buttonId = buttonId;
			this.used = used;
			this.used2 = used2;
			this.result = result;
			this.amount = amount;
			this.level = level;
			this.experience = experience;
		}

		public int getButtonId() {
			return buttonId;
		}

		public int getUsed() {
			return used;
		}

		public int getUsed2() {
			return used2;
		}

		public int getResult() {
			return result;
		}

		public int getAmount() {
			return amount;
		}

		public int getLevel() {
			return level;
		}

		public double getExperience() {
			return experience;
		}

	}

}

