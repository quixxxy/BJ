package com.quixxxy.bj.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.quixxxy.bj.model.Deck;

public class DeckTest {

	private Deck deck;

	@Before
	public void setUp() {
		deck = new Deck(Deck.DeckType.CARDS_52);
	}

	@Test
	public void testDeck() {
		Assert.assertEquals(52, deck.getDeck().size());
	}
}
