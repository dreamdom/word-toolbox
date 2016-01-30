package com.androiddom.rearrangedletters;

import java.util.Random;
import java.util.Set;

import com.androiddom.wordtoolbox.Dictionary;
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

	private MutibleDictionary gameDictionary;
	private Dictionary fullDictionary;
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
		this.fullDictionary = dictionary;
		this.gameDictionary = new MutibleDictionary.Builder(dictionary).complexity(gameLevelToWordComplexity()).build();
	}

	/**
	 * Method to create a new round in the game.
	 * 
	 * @return A Round object.
	 */
	public Round newRound() {

		String word = gameDictionary.getRandomWord();

		// Remove the word from the dictionary so it won't get used again
		gameDictionary.removeWord(word);

		// Use the full dictionary to look for anagrams
		Set<String> anagrams = new Dictionary.Builder(fullDictionary).anagrams(word).build().getWords();
		return new Round(word, random, anagrams);
	}

	/**
	 * A method to see how many words are left in the game dictionary.
	 * 
	 * @return The number of words left in the game dictionary.
	 */
	public int getNumberOfWordsLeft() {
		return this.gameDictionary.numberOfWords();
	}

	// Private methods

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
