package com.androiddom.rearrangedletters;

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

	public Round(String word, Random random) {
		// Save a reference to the original word
		this.word = word;

		// Save a reference to the Random object, in case we need to rearrange
		// again
		this.random = random;

		// Rearrange the word!
		
		// TODO: Make sure it is not the same!
		this.rearrangedWord = StringUtils.rearrangeString(word, random);
		
	}
	
	public String getRearrangedWord() {
		return rearrangedWord;
	}
	
	public String getWord() {
		return word;
	}

}
