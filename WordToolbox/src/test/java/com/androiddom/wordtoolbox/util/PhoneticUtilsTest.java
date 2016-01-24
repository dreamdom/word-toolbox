package com.androiddom.wordtoolbox.util;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class PhoneticUtilsTest {

	/**
	 * Test case for testing the getSoundex() function. Tests for all 26 letters
	 * of the English alphabet.
	 */
	@Test
	public void getSoundexTest() {

		String test;
		String phonetic;
		Set<Character> letters = new HashSet<Character>();

		test = "aeiouyhw";
		addCharactersToSet(test, letters);
		phonetic = PhoneticUtils.getSoundex(test);
		assertTrue("phonetic representation wrong", phonetic.equals("00000000"));

		test = "bfpv";
		addCharactersToSet(test, letters);
		phonetic = PhoneticUtils.getSoundex(test);
		assertTrue("phonetic representation wrong", phonetic.equals("1111"));

		test = "cgjkqsxz";
		addCharactersToSet(test, letters);
		phonetic = PhoneticUtils.getSoundex(test);
		assertTrue("phonetic representation wrong", phonetic.equals("22222222"));

		test = "dt";
		addCharactersToSet(test, letters);
		phonetic = PhoneticUtils.getSoundex(test);
		assertTrue("phonetic representation wrong", phonetic.equals("33"));

		test = "l";
		addCharactersToSet(test, letters);
		phonetic = PhoneticUtils.getSoundex(test);
		assertTrue("phonetic representation wrong", phonetic.equals("4"));

		test = "mn";
		addCharactersToSet(test, letters);
		phonetic = PhoneticUtils.getSoundex(test);
		assertTrue("phonetic representation wrong", phonetic.equals("55"));

		test = "r";
		addCharactersToSet(test, letters);
		phonetic = PhoneticUtils.getSoundex(test);
		assertTrue("phonetic representation wrong", phonetic.equals("6"));

		// Make sure that we have tested all 26 letters of the alphabet
		assertTrue("letters size not equal to 26", letters.size() == 26);

	}

	/**
	 * Private helper method used to add all of the characters from an input
	 * string to a set.
	 * 
	 * @param input
	 *            The input string.
	 * @param set
	 *            The set to add the characters to.
	 */
	private void addCharactersToSet(String input, Set<Character> set) {
		for (int i = 0; i < input.length(); i++) {
			set.add(input.charAt(i));
		}
	}

}
