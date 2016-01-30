package com.androiddom.rearrangedletters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.androiddom.wordtoolbox.util.StringUtils;

/**
 *
 * Class is used to manage an individual round of a game, consisting of one
 * rearranged word.
 *
 */
public class Round {

	public static final int MAX_REARRANGE_TRIES = 100;

	private final String originalWord;
	private String gameWord;
	private Random random;
	private int hintCount;
	private Set<String> anagrams;
	private List<String> gameWordOptionsList;
	private int rearrangeCount = 0;

	public Round(String word, Random random, Set<String> anagrams) {
		// Save a reference to the original word
		this.originalWord = word;

		// Save a reference to the Random object, in case we need to rearrange
		// again
		this.random = random;

		// Initialize the hint count to 0
		hintCount = 0;

		// Find all the anagrams for this word, as they are valid answers too
		this.anagrams = anagrams;

		// Generate the rearrange options
		generateGameOptions("", word);
		nextGameWord();
	}

	public String getRearrangedWord() {
		return gameWord;
	}
	
	public boolean setGameWord(String input) {
		// Check for a valid input coming in
		if(!StringUtils.isAnagram(input, gameWord)) {
			return false;
		}
		gameWord = input;
		return checkWord(input);
	}

	public String getWord() {
		return originalWord;
	}

	/**
	 * Method to check if an input is the same as the original word or an
	 * anagram.
	 * 
	 * @param input
	 *            The input to check.
	 * @return True if the input is the same as the original word or an anagram
	 *         of the original word, false otherwise.
	 */
	public boolean checkWord(String input) {
		return (input.equals(originalWord) || anagrams.contains(input));
	}

	/**
	 * Method to determine if the round is over. The round is over when the game
	 * word is the same as the original word, or there are no more rearranged options.
	 * 
	 * @return True if the round is over, because the rearranged word is the
	 *         same as the original or an anagram of the original.
	 */
	public boolean isRoundOver() {
		return (checkWord(gameWord) || gameWordOptionsList.size()==0);
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
		return originalWord.length() - hintCount - 1;
	}

	public void useHint() {
		// increment the hint count
		hintCount++;

		// No more hints to give, the original word is revealed
		if (hintCount >= originalWord.length() - 1) {
			this.gameWord = originalWord;
			return;
		}

		// Convert the original word to a character list
		List<Character> characterList = StringUtils.stringToCharacterList(originalWord);

		StringBuilder hintBuilder = new StringBuilder();
		StringBuilder gameWordBuilder = new StringBuilder();

		// Build up the hint
		for (int i = 0; i < hintCount; i++) {
			hintBuilder.append(characterList.get(i));
		}

		// Build up the part that should still be rearranged
		for (int i = hintCount; i < originalWord.length(); i++) {
			gameWordBuilder.append(characterList.get(i));
		}

		// Generate the rearrange options
		generateGameOptions(hintBuilder.toString(), gameWordBuilder.toString());
		nextGameWord();

	}


	public void nextGameWord() {
		if (gameWordOptionsList.size() == 0) {
			this.gameWord = originalWord;
		} else {
			this.gameWord = gameWordOptionsList.get(rearrangeCount % gameWordOptionsList.size());
		}
		rearrangeCount++;
	}
	
	// private methods

	private void generateGameOptions(String prefix, String rearrange) {
		Set<String> gameWordOptions = new HashSet<String>();
		for (int i = 0; i < MAX_REARRANGE_TRIES; i++) {
			String tempWord = prefix + StringUtils.rearrangeString(rearrange, random);
			if (!tempWord.equals(this.gameWord) && !checkWord(tempWord)) {
				gameWordOptions.add(tempWord);
			}
		}

		// Save the game word options
		this.gameWordOptionsList = new ArrayList<>(gameWordOptions);
		
		// shuffle
		Collections.shuffle(gameWordOptionsList);

		// reset the rearrange count
		rearrangeCount = 0;
	}

}
