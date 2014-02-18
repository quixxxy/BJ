package com.quixxxy.bj.model;

import org.junit.Assert;
import org.junit.Test;

public class BoxTest {

	@Test
	public void testGetScore() {
		Box box = new Box();
		Assert.assertEquals(0, box.getScore());

		box.addCard(new Card(Suit.DIAMOND, 8));
		Assert.assertEquals(8, box.getScore());
		Assert.assertFalse(box.isBurned());

		box.addCard(new Card(Suit.DIAMOND, 14));
		Assert.assertEquals(19, box.getScore());
		Assert.assertFalse(box.isBurned());

		box.addCard(new Card(Suit.CLUBS, 14));
		Assert.assertEquals(20, box.getScore());
		Assert.assertFalse(box.isBurned());

		box.addCard(new Card(Suit.SPADES, 14));
		Assert.assertEquals(21, box.getScore());
		Assert.assertFalse(box.isBurned());

		box.addCard(new Card(Suit.CLUBS, 11));
		Assert.assertEquals(31, box.getScore());
		Assert.assertTrue(box.isBurned());
	}

}
