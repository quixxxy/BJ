package com.quixxxy.bj.engine;

import com.quixxxy.bj.model.Box;
import com.quixxxy.bj.model.Table;

public abstract class Engine {

	public void startNewGame(int boxCount, int decksCount) {

		Table table = new Table(boxCount, decksCount);

		print(table.getShoe());

		table.dealToAll();
		table.dealToAll();

		boxesLoop: for (Box box : table.getPlayerBoxes()) {
			String command;
			do {
				print(box);
				print("Enter command");
				command = getCommand();

				switch (command) {
				case "stay":
					continue boxesLoop;

				case "more":
					table.dealToBox(box);
					break;

				default:
					print("Unknown command");
					break;
				}

			} while (!"stay".equals(command) && !box.isBurned());
		}
		table.dealToDealer();

		print(table);
		print("Winners: " + table.getWinner());
	}

	protected abstract String getCommand();

	protected abstract void print(Object message);

}
