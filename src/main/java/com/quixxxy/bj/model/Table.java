package com.quixxxy.bj.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import com.quixxxy.bj.model.Deck.DeckType;

public class Table {

	private List<Box> playerBoxes = new ArrayList<Box>();
	private DealerBox dealerBox;
	private Stack<Card> shoe = new Stack<Card>();

	public Table() {
		this(1, 1);
	}

	public Table(int boxCount, int decksCount) {
		// add players
		for (int i = 0; i < boxCount; i++) {
			playerBoxes.add(new Box());
		}
		dealerBox = new DealerBox();

		// add cards
		for (int i = 0; i < decksCount; i++) {
			shoe.addAll(new Deck(DeckType.CARDS_52).getDeck());
		}
	}

	public void dealToAll() {
		for (Box playerBox : playerBoxes) {
			dealToBox(playerBox);
		}
		dealToBox(dealerBox);
	}

	public void dealToBox(Box box) {
		if (!shoe.isEmpty()) {
			box.addCard(shoe.pop());
		}
	}

	public void dealToDealer() {
		while (!dealerBox.isEnough() || dealerBox.isBurned()) {
			dealToBox(dealerBox);
		}
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
