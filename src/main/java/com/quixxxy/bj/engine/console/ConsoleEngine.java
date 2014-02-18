package com.quixxxy.bj.engine.console;

import java.util.Scanner;

import com.quixxxy.bj.engine.Engine;

public class ConsoleEngine extends Engine {

	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		Engine engine = new ConsoleEngine();
		try {
			engine.startNewGame(1, 1);
		} finally {
			scan.close();
		}

	}

	@Override
	public void print(Object message) {
		System.out.println(message);
	}

	@Override
	protected String getCommand() {
		{
			return scan.next();
		}
	}

}
