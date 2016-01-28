package com.androiddom.rearrangedletters;

import java.util.List;
import java.util.Random;

import com.androiddom.wordtoolbox.util.StringUtils;

/**
 *
 * Class is used to manage an individual round of a game, consisting of one
 * rearranged word.
 *
 */
public class Round {

	public static final int MAX_REARRANGE_TRIES = 100;

	private final String word;
	private String rearrangedWord;
	private Random random;
	private int hintCount;

	public Round(String word, Random random) {
		// Save a reference to the original word
		this.word = word;

		// Save a reference to the Random object, in case we need to rearrange
		// again
		this.random = random;

		// Initialize the hint count to 0
		hintCount = 0;

		// Rearrange the word!
		this.rearrangedWord = rearrangeWord(word);

	}

	public String getRearrangedWord() {
		return rearrangedWord;
	}

	public String getWord() {
		return word;
	}

	/**
	 * Method to check if an input is the same as the original word.
	 * 
	 * @param input
	 *            The input to check.
	 * @return True if the input is the same, false otherwise.
	 */
	public boolean checkWord(String input) {
		return input.equals(word);
	}

	/**
	 * The round is over if the rearranged word is equal to the real word. At
	 * this point there is nothing left to do in the round.
	 * 
	 * @return true if the round is over, false otherwise.
	 */
	public boolean isRoundOver() {
		return this.rearrangedWord.equals(word);
	}

	/**
	 * Method to get the number of hints used in the round.
	 * 
	 * @return The number of hints used in the round.
	 */
	public int getHintsUsed() {
		return hintCount;
	}
	
	public int getScore() {
		return word.length() - hintCount - 1;
	}

	public void useHint() {
		// increment the hint count
		hintCount++;

		// No more hints to give, the original word is revealed
		if (hintCount >= word.length() - 1) {
			this.rearrangedWord = word;
			return;
		}

		// Convert the original word to a character list
		List<Character> characterList = StringUtils.stringToCharacterList(word);

		StringBuilder hintBuilder = new StringBuilder();
		StringBuilder rearrangedBuilder = new StringBuilder();

		// Build up the hint
		for (int i = 0; i < hintCount; i++) {
			hintBuilder.append(characterList.get(i));
		}

		// Build up the part that should still be rearranged
		for (int i = hintCount; i < word.length(); i++) {
			rearrangedBuilder.append(characterList.get(i));
		}

		// Put together the rearranged word as a combination of the hint and
		// rearranged part
		this.rearrangedWord = hintBuilder.toString() + rearrangeWord(rearrangedBuilder.toString());
	}

	// private methods

	private String rearrangeWord(String input) {
		String rearrangeWord = input;
		int count = 0;

		while (rearrangeWord.equals(input) && count < MAX_REARRANGE_TRIES) {
			rearrangedWord = StringUtils.rearrangeString(word, random);
			count++;
		}

		return rearrangeWord;
	}

}
