package server.model.content;
public class ExchangeBoxes {
	
	public enum boxInformation {
		//Misc
		CANNON(12863, 6, 8, 10, 12, -1),
		SUPER_SET(13066, 2440, 2442, 2436, 0, -1),
		//Iron
		IRON_LG(12972, 1153, 1115, 1067, 1191, -1),
		IRON_SK(12974, 1153, 1115, 1081, 1191, -1),
		//Steel
		STEEL_LG(12984, 1157, 1119, 1069, 1193, -1),
		STEEL_SK(12986, 1157, 1119, 1083, 1193, -1),
		//Mithril
		MITH_LG(13000, 1159, 1121, 1071, 1197, -1),
		MITH_SK(13002, 1159, 1121, 1085, 1197, -1),
		//Adamant
		ADDY_LG(13012, 1161, 1123, 1073, 1199, -1),
		ADDY_SK(13014, 1161, 1123, 1091, 1199, -1),
		//Rune
		RUNE_LG(13024, 1127, 1079, 1163, 1201, -1),
		RUNE_SK(13026, 1127, 1093, 1163, 1201, -1),
		//Gilded
		GILDED_LG(13036, 3486, 3481, 3483, 3488, -1),
		GILDED_SK(13038, 3486, 3481, 3485, 3488, -1),
		//Rune Trimmed
		RUNE_TLG(13028, 2623, 2625, 2627, 2629, -1),
		RUNE_TSK(13030, 2623, 3477, 2627, 2629, -1),
		RUNE_GLG(13032, 2615, 2617, 2619, 2621, -1),
		RUNE_GSK(13034, 2615, 3476, 2619, 2621, -1),
		//Dragonhides
		GREEN_DRAGONHIDE(12865, 1135, 1099, -1, -1, 1065),
		BLUE_DRAGONHIDE(12867, 2499, 2493, -1, -1, 2487),
		RED_DRAGONHIDE(12869, 2501, 2495, -1, -1, 2489),
		BLACK_DRAGONHIDE(12871, 2503, 2497, -1, -1, 2491),
		//Barrows
		GUTHANS(12873, 4728, 4730, 4724, 4726, -1),
		VERACS(12875, 4757, 4759, 4753, 4755, -1),
		DHAROKS(12877, 4720, 4722, 4716, 4718, -1), 
		TORAGS(12879, 4749, 4751, 4745, 4747, -1),
		AHRIM(12881, 4712, 4714, 4708, 4710, -1),
		KARIL(12883, 4736, 4738, 4732, 4734, -1)
		;
		private int itemID, bodyID, legID, helmetID, weaponID, gloveID;
		/**
		 * 
		 * @param itemID - item ID
		 * @param bodyID - body item ID
		 * @param legID - leg item ID
		 * @param helmetID - helmet item ID
		 * @param weaponID - weapon item ID
		 * @param gloves - gloves item ID
		 */
		private boxInformation(int itemID, int bodyID, int legID, int helmetID, int weaponID, int gloveID) {
			this.itemID = itemID;
			this.bodyID = bodyID;
			this.legID = legID;
			this.helmetID = helmetID;
			this.weaponID = weaponID;
			this.gloveID = gloveID;
		}
		
		/**
		 * @itemID - item ID
		 * @return
		 */
		public int getItemID() {
			return itemID;
		}
		public void setItemID(int itemID) {
			this.itemID = itemID;
		}
		/**
		 * @bodyID - body ID
		 * @return
		 */
		public int getBodyID() {
			return bodyID;
		}
		public void setBodyID(int bodyID) {
			this.bodyID = bodyID;
		}
		/**
		 * @legID leg ID
		 * @return
		 */
		public int getLegID() {
			return legID;
		}
		public void setLegID(int legID) {
			this.legID = legID;
		}
		public int getHelmetID() {
			return helmetID;
		}
		public void setHelmetID(int helmetID) {
			this.helmetID = helmetID;
		}
		public int getWeaponID() {
			return weaponID;
		}
		public void setWeaponID(int weaponID) {
			this.weaponID = weaponID;
		}
		public int getGloveID() {
			return gloveID;
		}
		public void setGloveID(int gloveID) {
			this.gloveID = gloveID;
		}
	}
}