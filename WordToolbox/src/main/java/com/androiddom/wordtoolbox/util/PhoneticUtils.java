package com.androiddom.wordtoolbox.util;

import java.util.HashMap;
import java.util.Map;

/**
 * A collection of utility functions related to phonetic representations of
 * Strings.
 *
 */
public class PhoneticUtils {

	// Soundex related functions based on the original Soundex algorithm
	// For more information, see the following wikipedia page:
	// https://en.wikipedia.org/wiki/Soundex

	private static Map<Character, Integer> soundexMap = new HashMap<Character, Integer>();

	static {

		// a, e, i, o, u, y, h, w
		soundexMap.put('a', 0);
		soundexMap.put('e', 0);
		soundexMap.put('i', 0);
		soundexMap.put('o', 0);
		soundexMap.put('u', 0);
		soundexMap.put('y', 0);
		soundexMap.put('h', 0);
		soundexMap.put('w', 0);

		// b, f, p, v
		soundexMap.put('b', 1);
		soundexMap.put('f', 1);
		soundexMap.put('p', 1);
		soundexMap.put('v', 1);

		// c, g, j, k, q, s, x, z
		soundexMap.put('c', 2);
		soundexMap.put('g', 2);
		soundexMap.put('j', 2);
		soundexMap.put('k', 2);
		soundexMap.put('q', 2);
		soundexMap.put('s', 2);
		soundexMap.put('x', 2);
		soundexMap.put('z', 2);

		// d, t
		soundexMap.put('d', 3);
		soundexMap.put('t', 3);

		// l
		soundexMap.put('l', 4);

		// m, n
		soundexMap.put('m', 5);
		soundexMap.put('n', 5);

		// r
		soundexMap.put('r', 6);
	}

	/**
	 * Method to get a "Soundex-like" representation of an input string.
	 * 
	 * @param input
	 *            The String to convert to a Soundex-like representation.
	 * @return The Soundex-like representation of the input String.
	 */
	public static String getSoundex(String input) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < input.length(); i++) {
			char curChar = input.charAt(i);

			// Check if the key exists and append
			if (soundexMap.containsKey(curChar)) {
				builder.append(soundexMap.get(curChar));
			}
		}

		return builder.toString();
	}

}
