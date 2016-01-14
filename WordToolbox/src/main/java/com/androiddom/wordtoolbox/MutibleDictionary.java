package com.androiddom.wordtoolbox;

/**
 * A class that maintains a collection of words. Words can be added or removed.
 *
 */
public class MutibleDictionary extends Dictionary {

	public MutibleDictionary() {
		super();
	}

	public void addWord(String word) {
		words.add(word);
	}

	public void removeWord(String word) {
		words.remove(word);
	}

}
