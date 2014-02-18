package com.quixxxy.bj.model;

public class Card {

	private Suit suit;
	private int rank;

	public Card(Suit suit, int rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public int getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	@Override
	public String toString() {
		String strRank;
		switch (rank) {
		case 10:
			strRank = "T";
			break;
		case 11:
			strRank = "J";
			break;
		case 12:
			strRank = "Q";
			break;
		case 13:
			strRank = "K";
			break;
		case 14:
			strRank = "A";
			break;
		default:
			strRank = String.valueOf(rank);
			break;
		}
		return strRank + suit.toString();
	}
}
