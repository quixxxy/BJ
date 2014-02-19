package com.quixxxy.bj.model;

import com.quixxxy.bj.model.Deck.DeckType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Table {

	private List<Box> playerBoxes = new ArrayList<Box>();
	private DealerBox dealerBox;
	private Stack<Card> shoe = new Stack<Card>();

	public Table() {
		this(1, 1);
	}

	public Table(int boxCount, int decksCount) {
		for (int i = 0; i < boxCount; i++) {
			playerBoxes.add(new Box());
		}
		dealerBox = new DealerBox();

		for (int i = 0; i < decksCount; i++) {
			shoe.addAll(new Deck(DeckType.CARDS_52).getCards());
		}
	}
	
    public Table(int boxCount, Stack<Card> shoe) {
        for (int i = 0; i < boxCount; i++) {
            playerBoxes.add(new Box());
        }
        dealerBox = new DealerBox();
        this.shoe = shoe;
    }

    public void dealToAllBoxes() {
	    dealToPlayerBoxes();
		dealToBox(dealerBox);
	}
	
	public void dealToPlayerBoxes() {
	    for (Box playerBox : playerBoxes) {
            dealToBox(playerBox);
        }
	}

	public void dealToBox(Box box) {
		if (!isShoeEnds()) {
			box.addCard(shoe.pop());
		} else {
		    throw new IllegalStateException("Number of cards in shoe exceed their limit");
		}
	}

	public void dealCardsToDealer() {
		while (!dealerBox.isEnough() && !dealerBox.isBurned()) {
			dealToBox(dealerBox);
		}
	}
	
    public boolean isShoeEnds() {
        return shoe.size() < 1;
    }

	@Override
	public String toString() {

		return "Dealer: " + dealerBox + " Boxes: " + playerBoxes;
	}

	public Stack<Card> getShoe() {
		return shoe;
	}

	public List<Box> getPlayerBoxes() {
		return playerBoxes;
	}

	public DealerBox getDealerBox() {
		return dealerBox;
	}

	public List<Box> getWinner() {
		List<Box> winners = new LinkedList<Box>();

		for (Box box : playerBoxes) {
			if (!box.isBurned()) {
				winners.add(box);
			}
		}

		if (!dealerBox.isBurned()) {
			winners.add(dealerBox);
			return getMaxScoreBoxes(winners);
		} else {
			return winners;
		}
	}

	private List<Box> getMaxScoreBoxes(List<Box> boxes) {
		List<Box> result = new ArrayList<Box>();
		int maxScore = Collections.max(boxes).getScore();
		for (Box box : boxes) {
			if (box.getScore() == maxScore) {
				result.add(box);
			}
		}
		return result;
	}
}
