package com.androiddom.wordtoolbox.analytics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.androiddom.wordtoolbox.Dictionary;
import com.androiddom.wordtoolbox.util.StringUtils;

/**
 * A collection of functions to analyze a full dictionary.
 *
 */
public class DictionaryAnalytics {

	/**
	 * Returns lengths mapped to the count of words with that length.
	 * 
	 * @param dictionary
	 *            The dictionary to analyze.
	 * @return A map of lengths mapped to a count of words with that length.
	 */
	public static Map<Integer, Integer> lengthCountDict(Dictionary dictionary) {
		Map<Integer, Integer> lengthMap = new HashMap<Integer, Integer>();

		for (String word : dictionary.getWords()) {
			int length = word.length();
			int count = 0;

			if (lengthMap.containsKey(length))
				count = lengthMap.get(length);
			count++;
			lengthMap.put(length, count);
		}

		return lengthMap;
	}

	/**
	 * Returns word complexity mapped to the count of words with that
	 * complexity.
	 * 
	 * @param dictionary
	 *            The dictionary to analyze.
	 * @return A map of word complexity mapped to a count of words with that
	 *         complexity.
	 */
	public static Map<Integer, Integer> wordComplexityCount(Dictionary dictionary) {
		Map<Integer, Integer> complexityMap = new HashMap<Integer, Integer>();

		for (String word : dictionary.getWords()) {
			int complexity = WordComplexity.getWordComplexity(word);
			int count = 0;

			if (complexityMap.containsKey(complexity))
				count = complexityMap.get(complexity);
			count++;
			complexityMap.put(complexity, count);
		}

		return complexityMap;
	}

	/**
	 * A function to find palindromes in a dictionary.
	 * 
	 * @param dictionary
	 *            The dictionary to check.
	 * @return A list of palindromes.
	 */
	public static List<String> findPalindromes(Dictionary dictionary) {
		List<String> palindromeList = new ArrayList<String>();
		for (String word : dictionary.getWords()) {
			String lowerCase = word.toLowerCase(Locale.US);
			if (lowerCase.length() > 1 && lowerCase.equals(StringUtils.reverseString(lowerCase))) {
				palindromeList.add(word);
			}
		}

		return palindromeList;
	}

}
