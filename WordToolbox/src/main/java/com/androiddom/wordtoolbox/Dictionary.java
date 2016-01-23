package com.androiddom.wordtoolbox;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * A class to maintain a collection of words.
 *
 */
public class Dictionary {

	/**
	 * The set of words in the dictionary.
	 */
	private Set<String> words;

	/**
	 * A list of words in the dictionary.
	 */
	private List<String> wordList;

	/**
	 * An object used to generate a psuedo random number. Used for getting a
	 * random word from the dictionary.
	 */
	private Random random;

	/**
	 * A package access constructor for the Dictionary object.
	 */
	Dictionary(Set<String> words) {
		this.words = words;
		
		// Add all the words to the list, and shuffle it
		wordList = new ArrayList<String>(words);
		Collections.shuffle(wordList);
		
		random = new Random();
	}
	
	public Set<String> getWords() {
		return words;
	}

	/**
	 * Method to see if the dictionary contains a word or not.
	 * 
	 * @param word
	 *            The word to check
	 * @return True if word is in the dictionary, false otherwise.
	 */
	public boolean hasWord(String word) {
		return words.contains(word);
	}

	/**
	 * Method to get the number of words in the dictionary.
	 * 
	 * @return The number of words in the dictionary.
	 */
	public int numberOfWords() {
		return words.size();
	}

	/**
	 * Method to get a random word from the dictionary.
	 * 
	 * @return A random word from the dictionary.
	 */
	public String getRandomWord() {
		if (wordList.size() == 0)
			return null;

		int index = random.nextInt(wordList.size());
		return wordList.get(index);
	}

	/**
	 * A class used to Build Dictionary objects.
	 *
	 */
	public static class Builder extends DictionaryBuilder<Dictionary> {

		public Builder(File file) {
			super(file);
		}

		public Builder(Dictionary baseDictionary) {
			super(baseDictionary);
		}


		// Building

		/**
		 * Method to build the Dictionary object. Dictionary will be based on a
		 * file that is read or a dictionary object depending on what
		 * constructor was used. Will apply all the rules set on the builder.
		 * 
		 * @return A dictionary object.
		 */
		public Dictionary build() {

			// Process any filters added to the builder
			processRules();
			
			// Create a new instance of a dictionary object
			Dictionary dictionary = new Dictionary(builderWords);

			// Return the process dictionary
			return dictionary;
		}



	}


}
