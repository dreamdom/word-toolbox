package com.androiddom.wordtoolbox.util;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * A collection of utility functions for Strings.
 *
 */
public class StringUtils {

	// Vowels and Y
	private static Set<String> vowelsAndY = new HashSet<String>();

	// Initialize the set
	static {
		vowelsAndY.add("a");
		vowelsAndY.add("e");
		vowelsAndY.add("i");
		vowelsAndY.add("o");
		vowelsAndY.add("u");
		vowelsAndY.add("y");
	}

	/**
	 * Function to take a String as an input and return a normalized String as
	 * output.
	 * 
	 * @param input
	 *            The String to normalize.
	 * @return The normalized String.
	 */
	public static String normalize(String input) {
		input = input.toLowerCase(Locale.US);
		return input;
	}

	/**
	 * Function that returns the rough number of syllables of an input word.
	 * 
	 * @param input
	 *            The input to get the rough syllable count for.
	 * @return The "rough" count of syllables the input contains.
	 */
	public static int getRoughSyllableCount(String input) {

		// First, normalize the input
		input = normalize(input);

		// Initialize the rough syllable count to zero
		int roughSyllableCount = 0;

		boolean prevIsVowel = false;

		// Loop through the string character by character
		for (int i = 0; i < input.length(); i++) {
			String curChar = input.substring(i, i + 1);

			if (vowelsAndY.contains(curChar) && !prevIsVowel) {
				// Only increase the rough syllable count if the previous
				// character was not a vowel
				roughSyllableCount++;
				prevIsVowel = true;
			} else {
				prevIsVowel = false;
			}
		}

		return roughSyllableCount;
	}

	/**
	 * Method to get the number of occurrences of one substring in an input
	 * String.
	 * 
	 * @param input
	 *            The input string.
	 * @param substring
	 *            The substring.
	 * @return The number of times the substring appears in the input String.
	 */
	public static int substringCount(String input, String substring) {
		int curCount = 0;
		int curIndex = 0;

		while ((curIndex = input.indexOf(substring, curIndex)) != -1) {
			curCount++;
			curIndex++;
		}

		return curCount;
	}

	/**
	 * Method to determine if one word is an anagram of another word.
	 * 
	 * @param input
	 *            The input String.
	 * @param test
	 *            The String to test.
	 * @return True if test is an anagram of input, false otherwise.
	 */
	public static boolean isAnagram(String input, String test) {
		if (test.equals(input))
			return false;

		if (test.length() != input.length())
			return false;

		Set<String> letterSet = new HashSet<String>();
		for (int i = 0; i < input.length(); i++) {
			String curChar = input.substring(i, i + 1);
			if (!letterSet.contains(curChar)) {
				int substringCount = StringUtils.substringCount(input, curChar);
				int testCount = StringUtils.substringCount(test, curChar);

				if (substringCount != testCount)
					return false;

				letterSet.add(curChar);
			}
		}

		return true;

	}

}
