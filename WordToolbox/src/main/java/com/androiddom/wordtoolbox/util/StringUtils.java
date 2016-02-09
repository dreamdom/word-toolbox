package com.androiddom.wordtoolbox.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

/**
 * A collection of utility functions for Strings.
 *
 */
public class StringUtils {

	// Vowels and Y
	private static Set<Character> vowelsAndY = new HashSet<Character>();

	// Initialize the set
	static {
		vowelsAndY.add('a');
		vowelsAndY.add('e');
		vowelsAndY.add('i');
		vowelsAndY.add('o');
		vowelsAndY.add('u');
		vowelsAndY.add('y');
	}

	/**
	 * Function to take a String as an input and return a normalized String as
	 * output.
	 * 
	 * @param input
	 *            The String to normalize.
	 * @return The normalized String. Null if input is null.
	 */
	public static String normalize(String input) {

		// bad input check
		if (input == null) {
			return null;
		}

		input = input.toLowerCase(Locale.US);
		return input;
	}

	/**
	 * Function that returns the number of groups of vowels (including y) in a
	 * word.
	 * 
	 * @param input
	 *            The word to analyze.
	 * @return The number of groups of vowels (including y) in the word.
	 */
	public static int getVowelGroupCount(String input) {

		// bad input check
		if (input == null) {
			return 0;
		}

		// First, normalize the input
		input = normalize(input);

		// Initialize the rough syllable count to zero
		int roughSyllableCount = 0;

		boolean prevIsVowel = false;

		// Loop through the string character by character
		for (int i = 0; i < input.length(); i++) {
			char curChar = input.charAt(i);

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

		// Check for bad inputs
		if (input == null || substring == null) {
			return 0;
		}

		if (input.length() == 0 || substring.length() == 0) {
			return 0;
		}

		// Perform the count
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

		// bad input check
		if (input == null || test == null) {
			return false;
		}

		if (test.equals(input)) {
			return false;
		}

		if (test.length() != input.length()) {
			return false;
		}

		// Perform the anagram check

		Set<Character> letterSet = new HashSet<Character>();
		for (int i = 0; i < input.length(); i++) {
			char curChar = input.charAt(i);
			if (!letterSet.contains(curChar)) {
				int substringCount = StringUtils.substringCount(input, "" + curChar);
				int testCount = StringUtils.substringCount(test, "" + curChar);

				if (substringCount != testCount)
					return false;

				letterSet.add(curChar);
			}
		}

		return true;
	}

	/**
	 * Method to take an input String and rearrange the order of the letters.
	 * 
	 * @param input
	 *            The String to rearrange.
	 * @param random
	 *            A random object used to generate pseudo random numbers for
	 *            rearranging.
	 * @return The input String with the order of the letters rearranged, or
	 *         null if input or random is null.
	 */
	public static String rearrangeString(String input, Random random) {

		// Check for bad input
		if (input == null || random == null) {
			return null;
		}

		StringBuilder builder = new StringBuilder();

		List<Character> inputList = stringToCharacterList(input);

		while (inputList.size() > 0) {
			builder.append(inputList.remove(random.nextInt(inputList.size())));
		}

		return builder.toString();
	}

	/**
	 * Method to convert a String to a list of characters.
	 * 
	 * @param input
	 *            The string to convert.
	 * @return A List of Characters.
	 */
	public static List<Character> stringToCharacterList(String input) {
		
		// Bad input check
		if(input == null) {
			return null;
		}
		
		List<Character> inputList = new ArrayList<Character>(input.length());

		for (int i = 0; i < input.length(); i++) {
			char curChar = input.charAt(i);
			inputList.add(curChar);
		}

		return inputList;
	}

	/**
	 * Method to reverse a String.
	 * 
	 * @param input
	 *            The String to reverse.
	 * @return The reverse of the input String.
	 */
	public static String reverseString(String input) {

		// Bad input check
		if (input == null || input.length() == 0) {
			return input;
		}

		StringBuilder builder = new StringBuilder();

		for (int i = input.length() - 1; i >= 0; i--) {
			builder.append(input.charAt(i));
		}

		return builder.toString();
	}

}
