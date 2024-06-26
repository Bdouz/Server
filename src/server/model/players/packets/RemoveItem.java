package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.skills.crafting.GemCrafting;
import server.model.players.skills.crafting.GemData;

/**
 * Remove Item
 **/
public class RemoveItem implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int interfaceId = c.getInStream().readUnsignedWordA();
		int removeSlot = c.getInStream().readUnsignedWordA();
		int removeId = c.getInStream().readUnsignedWordA();
		switch (interfaceId) {
		case 4233 : // make 1 ring crafting
			GemCrafting.startCrafter(c, GemData.getGemSlot()[removeSlot], 1, 0);
			break;
		case 4239 : // make 1 neckalce crafting
			GemCrafting.startCrafter(c, GemData.getGemSlot()[removeSlot], 1, 1);
			break;
		case 4245 : // make 1 amulet crafting
			GemCrafting.startCrafter(c, GemData.getGemSlot()[removeSlot], 1, 2);
			break;
		
		case 1688:
			c.getItems().removeItem(removeId, removeSlot);
			break;

		case 5064:
			c.getItems().bankItem(removeId, removeSlot, 1);
			break;

		case 5382:
			c.getItems().fromBank(removeId, removeSlot, 1);
			break;

		case 3900:
			c.getShops().buyFromShopPrice(removeId, removeSlot);
			break;

		case 3823:
			c.getShops().sellToShopPrice(removeId, removeSlot);
			break;

		case 3322:
			if (c.duelStatus > 0) {
				c.getDuel().stakeItem(removeId, removeSlot, 1);
				return;
			}
			c.getTradeHandler().tradeItem(removeId, removeSlot, 1);
			break;

		case 3415:
			if (c.duelStatus > 0) {
				return;
			}
			c.getTradeHandler().fromTrade(removeId, removeSlot, 1);
			break;

		case 6669:
			c.getDuel().fromDuel(removeId, removeSlot, 1);
			break;

		case 1119:
		case 1120:
		case 1121:
		case 1122:
		case 1123:
			c.getSmithing().readInput(c.playerLevel[c.playerSmithing],
					Integer.toString(removeId), c, 1);
			break;
		}
	}

}
