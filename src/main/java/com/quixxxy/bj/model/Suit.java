package com.quixxxy.bj.model;

public enum Suit {

	HEART('\u2665'), CLUBS('\u2663'), DIAMOND('\u2666'), SPADES('\u2660'), ;

	private char str;

	private Suit(char str) {
		this.str = str;
	}

	@Override
	public String toString() {
		return String.valueOf(str);
	}
}