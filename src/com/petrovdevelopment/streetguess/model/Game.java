package com.petrovdevelopment.streetguess.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private int index;
	private List<Round> rounds;

	public Game(int roundsCount) {
		rounds = new ArrayList<Round>();
		for (int i = 0; i < roundsCount; i++) {
			rounds.add(new Round());
		}
	}

	/**
	 * Gives the next round and increments the round index
	 * 
	 * @return
	 */
	public Round nextRound() {
		if (!hasNextRound()) throw new IllegalStateException("No more rounds left");
		else {
			Round currentRound = rounds.get(index);
			index++;
			return currentRound;
		}
	}

	public boolean hasNextRound() {
		return (index >= rounds.size());
	}

	/**
	 * Gets the index of the next round, 1-base index
	 * 
	 * @return
	 */
	public int getNextRoundIndex() {
		return index + 1;
	}
}
