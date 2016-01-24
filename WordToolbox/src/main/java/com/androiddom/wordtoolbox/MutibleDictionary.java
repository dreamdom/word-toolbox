package com.androiddom.wordtoolbox;

import java.util.Set;

/**
 * A dictionary where words can be added and removed.
 *
 */
public class MutibleDictionary extends Dictionary {

	/**
	 * Package access constructor that takes a Set of words.
	 * 
	 * @param words
	 *            The set of words to use for the dictionary.
	 */
	MutibleDictionary(Set<String> words) {
		super(words);
	}

	/**
	 * A method to add a word to the dictionary.
	 * 
	 * @param word
	 *            The word to add to the dictionary.
	 */
	public void addWord(String word) {
		// Update the set and the list
		words.add(word);
		wordList.add(word);
	}

	/**
	 * Method to remove a word from the dictionary.
	 * 
	 * @param word
	 *            The word to remove from the dictionary.
	 * @return True if a word was removed, false if otherwise.
	 */
	public boolean removeWord(String word) {
		if (words.contains(word)) {
			// Update the set and the list
			words.remove(word);
			wordList.remove(word);

			return true;
		}

		return false;
	}

}
