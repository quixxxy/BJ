package com.quixxxy.bj.model;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class TableTest {

	@Test
	public void testDeal() {
		Table table = new Table();

		table.dealToAllBoxes();
		Assert.assertEquals(50, table.getShoe().size());

		table.dealToAllBoxes();
		Assert.assertEquals(48, table.getShoe().size());
	}

	@Test
	public void testGetWinner() {
		Table table = new Table();
		table.getPlayerBoxes().get(0).addCard(new Card(Suit.CLUBS, 13));
		table.getPlayerBoxes().get(0).addCard(new Card(Suit.CLUBS, 12));

		table.getDealerBox().addCard(new Card(Suit.DIAMOND, 13));
		table.getDealerBox().addCard(new Card(Suit.DIAMOND, 14));

		Assert.assertEquals(Arrays.asList(table.getDealerBox()),
				table.getWinner());
	}

	@Test
	public void testCoupleBoxesAndDecks() {
		Table table = new Table(3, 4);
		Assert.assertEquals(3, table.getPlayerBoxes().size());
		Assert.assertEquals(208, table.getShoe().size());
	}
	
	@Test
    public void testShoeEnds() {
	    Table table = new Table();
	    Assert.assertFalse(table.isShoeEnds());
	    for (int i = 0; i < 20; i++) {
	        table.dealToAllBoxes();
	    }
	    Assert.assertTrue(table.isShoeEnds());
	}

}
