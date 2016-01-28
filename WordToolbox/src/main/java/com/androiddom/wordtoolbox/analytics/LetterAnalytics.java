package com.androiddom.wordtoolbox.analytics;

import java.util.HashMap;
import java.util.Map;

import com.androiddom.wordtoolbox.Dictionary;

/**
 * A collection of methods that perform analytics on words.
 *
 */
public class LetterAnalytics {

	/**
	 * Function to count the number of occurrences of individual characters in a
	 * dictionary.
	 * 
	 * @param dictionary
	 *            The input dictionary.
	 * @return A map with characters as keys, and the occurrence count as the
	 *         corresponding value.
	 */
	public static Map<Character, Integer> characterCount(Dictionary dictionary) {
		Map<Character, Integer> countMap = new HashMap<Character, Integer>();

		// Loop through all of the words
		for (String word : dictionary.getWords()) {
			for (int i = 0; i < word.length(); i++) {
				char curChar = word.charAt(i);
				int curCount = 0;

				if (countMap.containsKey(curChar))
					curCount = countMap.get(curChar);

				countMap.put(curChar, curCount + 1);
			}
		}

		return countMap;
	}

	/**
	 * Function to count the number of occurrences of letterGroups (of a
	 * specified length) in a dictionary.
	 * 
	 * @param dictionary
	 *            The input dictionary.
	 * @param letterGroupLength
	 *            The length of letter groups to count.
	 * @return A map with the letterGroups as keys, and the occurrence count as
	 *         the corresponding value.
	 */
	public static Map<String, Integer> letterGroupCount(Dictionary dictionary, int letterGroupLength) {
		Map<String, Integer> countMap = new HashMap<String, Integer>();

		// Loop through all the words
		for (String word : dictionary.getWords()) {
			for (int i = 0; i < word.length() - letterGroupLength; i++) {
				String curLetterGroup = word.substring(i, i + letterGroupLength);
				int curCount = 0;

				if (countMap.containsKey(curLetterGroup))
					curCount = countMap.get(curLetterGroup);

				countMap.put(curLetterGroup, curCount + 1);
			}
		}

		return countMap;
	}

}
