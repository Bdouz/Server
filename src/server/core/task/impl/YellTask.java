package server.core.task.impl;

import server.core.GameEngine;
import server.core.PlayerHandler;
import server.core.task.Task;
import server.model.players.Client;
import server.util.Misc;

public class YellTask implements Task {

	private String[] MESSAGES = {
			"Hope you're enjoying Agatha, Please tell your friends!",
			"Make sure you sign up on our ::forums!!",
			"If you have any suggestions please post them on our forums!",
			"Make sure to ::vote! It will reward you and gain us players.",
			"Click the world map to find where a location is.",
			"An Administrator will yell when they, Or somebody else is streaming!" };

	@Override
	public void execute(GameEngine context) {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				Client c2 = (Client) PlayerHandler.players[j];
				int random = Misc.random(MESSAGES.length - 1);
				String message = MESSAGES[random];
				c2.sendMessage("@cr9@<shad=0>@blu@" + message);
			}
		}
	}
}
