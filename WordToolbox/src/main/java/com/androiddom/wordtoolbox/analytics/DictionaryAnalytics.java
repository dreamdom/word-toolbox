package com.androiddom.wordtoolbox.analytics;

import java.util.HashMap;
import java.util.Map;

import com.androiddom.wordtoolbox.Dictionary;

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

}
