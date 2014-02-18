package com.quixxxy.bj.model;

import org.junit.Assert;
import org.junit.Test;

public class DealerBoxTest {

	@Test
	public void testIsEnough() {
		DealerBox box = new DealerBox();

		box.addCard(new Card(Suit.DIAMOND, 8));
		Assert.assertFalse(box.isEnough());

		box.addCard(new Card(Suit.DIAMOND, 14));
		Assert.assertTrue(box.isEnough());
	}

}
