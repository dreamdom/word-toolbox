package com.androiddom.rearrangedletters;

import java.util.Random;

import com.androiddom.wordtoolbox.MutibleDictionary;

/**
 * The Game class is used to manage a number of Rounds of the Rearranged letters
 * game.
 *
 */
public class Game {

	/**
	 * An integer that corresponds to the minimum complexity for a game.
	 */
	private static final int BASE_WORD_COMPLEXITY = 6;

	private MutibleDictionary dictionary;
	private int gameLevel;
	private Random random;

	/**
	 * Constructor for the game object.
	 * 
	 * @param dictionary
	 *            A dictionary to use for the game.
	 * @param gameLevel
	 *            What level the game should be played at. Higher levels
	 *            correspond to a higher level of difficulty.
	 */
	public Game(MutibleDictionary dictionary, int gameLevel) {
		this.gameLevel = gameLevel;

		// Initialize a random object
		random = new Random();

		// Filter the dictionary down to the desired complexity
		this.dictionary = new MutibleDictionary.Builder(dictionary).complexity(gameLevelToWordComplexity()).build();
	}

	/**
	 * Method to create a new round in the game.
	 * 
	 * @return A Round object.
	 */
	public Round newRound() {

		String word = dictionary.getRandomWord();

		// Remove the word from the dictionary so it won't get used again
		dictionary.removeWord(word);

		return new Round(word, random, dictionary);
	}

	/**
	 * Method to convert the set game complexity to a word complexity used
	 * internally.
	 * 
	 * @return A word complexity level.
	 */
	private int gameLevelToWordComplexity() {
		return BASE_WORD_COMPLEXITY + gameLevel;
	}

}
