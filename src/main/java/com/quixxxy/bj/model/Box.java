package com.quixxxy.bj.model;

import java.util.ArrayList;
import java.util.List;

public class Box implements Comparable<Box> {

	private List<Card> cards = new ArrayList<Card>();

	public int getScore() {
		int score = 0;
		for (Card card : cards) {
			int rank = card.getRank();
			if (rank >= 2 && rank <= 10) {

				// 2-10
				score += rank;
			} else if (rank >= 11 && rank <= 13) {

				// J-K
				score += 10;
			} else {
				// A
				score += getScoreForAce(score);
			}
		}
		return score;
	}

	private int getScoreForAce(int currentScore) {
		if ((currentScore + 11) <= 21) {
			return 11;
		}
		return 1;
	}

	public void addCard(Card card) {
		cards.add(card);

	}

	@Override
	public String toString() {
		return cards.toString() + " Score: " + getScore();
	}

	public boolean isBurned() {
		return getScore() > 21;
	}

	@Override
	public int compareTo(Box o) {
		return Integer.valueOf(getScore()).compareTo(o.getScore());
	}
}
