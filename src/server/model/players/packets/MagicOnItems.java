package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;

/**
 * Magic on items
 **/
public class MagicOnItems implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int slot = c.getInStream().readSignedWord();
		int itemId = c.getInStream().readSignedWordA();
		c.getInStream().readSignedWord();
		int spellId = c.getInStream().readSignedWordA();
		if(!c.getItems().playerHasItem(itemId, 1, slot))
            return;
		c.usingMagic = true;
		c.getPA().magicOnItems(slot, itemId, spellId);
		c.usingMagic = false;

	}

}
