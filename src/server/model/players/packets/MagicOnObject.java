package server.model.players.packets;


import server.model.players.Client;
import server.model.players.PacketType;

public class MagicOnObject implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int magicID = c.getInStream().readUnsignedWord();
	}

}