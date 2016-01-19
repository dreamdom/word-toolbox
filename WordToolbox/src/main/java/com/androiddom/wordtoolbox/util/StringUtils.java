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
	 * @param input The input to get the rough syllable count for.
	 * @return The "rough" count of syllables the input contains.
	 */
	public static int getRoughSyllableCount(String input) {
		
		// First, normalize the input
		input = normalize(input);
		
		// Initialize the rough syllable count to zero
		int roughSyllableCount = 0;
		
		boolean prevIsVowel = false;
		
		// Loop through the string character by character
		for(int i=0; i<input.length(); i++) {
			String curChar = input.substring(i, i+1);
			
			if(vowelsAndY.contains(curChar) && !prevIsVowel) {
				// Only increase the rough syllable count if the previous character was not a vowel
				roughSyllableCount++;
				prevIsVowel = true;
			} else {
				prevIsVowel = false;
			}
		}
		
		return roughSyllableCount;
	}

}
