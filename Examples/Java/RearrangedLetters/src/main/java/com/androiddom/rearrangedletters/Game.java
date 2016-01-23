package com.androiddom.rearrangedletters;

import java.util.Random;

import com.androiddom.wordtoolbox.Dictionary;

/**
 * The Game class is used to manage a number of Rounds of the Rearranged letters
 * game.
 *
 */
public class Game {

	private static final int BASE_WORD_COMPLEXITY = 6;

	private Dictionary dictionary;
	private int gameLevel;
	private Random random;

	public Game(Dictionary dictionary, int gameLevel) {
		this.gameLevel = gameLevel;

		// Initialize a random object
		random = new Random();

		// Filter the dictionary down to the desired complexity
		this.dictionary = new Dictionary.Builder(dictionary).complexity(gameLevelToWordComplexity()).build();
	}

	public Round newRound() {
		
		// TODO: add use a mutible dictionary and remove used words!
		String word = dictionary.getRandomWord();
		
		return new Round(word, random);
	}

	private int gameLevelToWordComplexity() {
		return BASE_WORD_COMPLEXITY + gameLevel;
	}

}
