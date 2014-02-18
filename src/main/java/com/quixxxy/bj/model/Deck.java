package com.quixxxy.bj.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {

	private List<Card> deck = new LinkedList<Card>();

	public Deck(DeckType deckType) {
		switch (deckType) {
		case CARDS_36:
			throw new IllegalArgumentException("Not supported yet");
		case CARDS_52:
			for (Suit suit : Suit.values()) {
				for (int i = 2; i <= 14; i++) {
					getDeck().add(new Card(suit, i));
				}
			}
			break;
		}
		shuffle();
	}

	public void shuffle() {
		Collections.shuffle(getDeck());
	}

	public List<Card> getDeck() {
		return deck;
	}

	public enum DeckType {
		CARDS_36, CARDS_52
	}
}
