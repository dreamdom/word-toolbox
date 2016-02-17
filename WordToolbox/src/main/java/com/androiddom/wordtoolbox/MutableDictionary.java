package com.androiddom.wordtoolbox;

import java.io.File;
import java.util.Collection;
import java.util.Set;

/**
 * A dictionary where words can be added and removed.
 *
 */
public class MutableDictionary extends Dictionary {

	/**
	 * Package access constructor that takes a Set of words.
	 * 
	 * @param words
	 *            The set of words to use for the dictionary.
	 */
	MutableDictionary(Set<String> words, String header) {
		super(words, header);
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

	/**
	 * A class used to Build MutibleDictionary objects.
	 *
	 */
	public static class Builder extends DictionaryBuilder<MutableDictionary> {

		/**
		 * Public constructor.
		 * 
		 * @param file
		 *            File to use as the basis of the dictionary to be built.
		 */
		public Builder(File file) {
			super(file);
		}

		/**
		 * Public constructor.
		 * 
		 * @param baseDictionary
		 *            Dictionary to use as the basis of the dictionary to be
		 *            built.
		 */
		public Builder(Dictionary baseDictionary) {
			super(baseDictionary);
		}
		
		/**
		 * Public constructor.
		 * 
		 * @param words
		 *            The collection of words to use as the basis of the
		 *            dictionary to be built.
		 */
		public Builder(Collection<String> words) {
			super(words);
		}

		// Building

		/**
		 * Method to build the MutibleDictionary object. The MutibleDictionary
		 * will be based on a file that is read or a dictionary object depending
		 * on what constructor was used. Will apply all the rules set on the
		 * builder.
		 * 
		 * @return A dictionary object.
		 */
		public MutableDictionary build() {

			// Process any filters added to the builder
			processRules();

			// Create a new instance of a dictionary object
			MutableDictionary dictionary = new MutableDictionary(builderWords, builderHeader);

			// Return the process dictionary
			return dictionary;
		}

	}

}
