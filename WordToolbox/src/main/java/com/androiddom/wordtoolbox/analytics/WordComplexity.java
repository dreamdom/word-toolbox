package com.androiddom.wordtoolbox.analytics;

import com.androiddom.wordtoolbox.util.PhoneticUtils;
import com.androiddom.wordtoolbox.util.StringUtils;

/**
 * A collection of utility functions to help analyze the complexity of a word.
 */
public class WordComplexity {

	/**
	 * Method to get the complexity of a word.
	 * 
	 * @param input
	 *            The word to calculate the complexity for.
	 * @return The calculated complexity for the input.
	 */
	public static int getWordComplexity(String input) {
		
		// bad input check
		if(input == null || input.length() == 0) {
			return 0;
		}
		
		int wordComplexity = 0;

		wordComplexity += input.length();
		wordComplexity += StringUtils.getVowelGroupCount(input);
		wordComplexity += getSoundexComplexity(input);

		return wordComplexity;
	}

	/**
	 * A method used to calculate the complexity of the Soundex-like
	 * representation of the input.
	 * 
	 * @param input
	 *            The input to use for the calculation.
	 * @return The complexity for the Soundex-like calculation.
	 */
	private static int getSoundexComplexity(String input) {
		
		// bad input check
		if(input == null || input.length() == 0) {
			return 0;
		}
		
		int soundexComplexity = 0;

		String soundexString = PhoneticUtils.getSoundex(input);

		String prev = null;
		for (int i = 0; i < soundexString.length(); i++) {
			String curChar = soundexString.substring(i, i + 1);

			// Do not count repeated sounds
			if (prev == null || !curChar.equals(prev)) {
				soundexComplexity++;
			}

			prev = curChar;
		}

		return soundexComplexity;
	}

}
