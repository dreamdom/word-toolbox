package com.androiddom.rearrangedletters;

import java.util.Random;

import com.androiddom.wordtoolbox.MutibleDictionary;

/**
 * The Game class is used to manage a number of Rounds of the Rearranged letters
 * game.
 *
 */
public class Game {

	private static final int BASE_WORD_COMPLEXITY = 6;

	private MutibleDictionary dictionary;
	private int gameLevel;
	private Random random;

	public Game(MutibleDictionary dictionary, int gameLevel) {
		this.gameLevel = gameLevel;

		// Initialize a random object
		random = new Random();

		// Filter the dictionary down to the desired complexity
		this.dictionary = new MutibleDictionary.Builder(dictionary)
				.complexity(gameLevelToWordComplexity())
				.build();
	}

	public Round newRound() {
		
		String word = dictionary.getRandomWord();
		
		// Remove the word from the dictionary so it won't get used again
		dictionary.removeWord(word);
		
		return new Round(word, random);
	}

	private int gameLevelToWordComplexity() {
		return BASE_WORD_COMPLEXITY + gameLevel;
	}

}
