package com.androiddom.wordtoolbox.util;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.junit.Test;

/**
 * A set of test cases for functions in the StringUtils class.
 *
 */
public class StringUtilsTest {

	/**
	 * Test case for testing the StringUtils.normalize function.
	 */
	@Test
	public void normalizeTest() {
		String input;
		String expectedOutput;

		input = "test";
		expectedOutput = "test";

		assertTrue("normalized String not the same as input", expectedOutput.equals(StringUtils.normalize(input)));

		input = "Test";
		expectedOutput = "test";
		assertTrue("normalized String incorrect", expectedOutput.equals(StringUtils.normalize(input)));
		
		// bad input tests
		
		input = null;
		assertTrue("normalized String incorrect", StringUtils.normalize(input) == null);
	}

	/**
	 * Test case for testing the StringUtils.getVowelGroupCount function.
	 */
	@Test
	public void getVowelGroupCountTest() {
		String input;
		int expectedOutput;

		input = "car";
		expectedOutput = 1;
		assertTrue("vowelGroupCount incorrect", StringUtils.getVowelGroupCount(input) == expectedOutput);

		input = "local";
		expectedOutput = 2;
		assertTrue("vowelGroupCount incorrect", StringUtils.getVowelGroupCount(input) == expectedOutput);

		input = "complete";
		expectedOutput = 3;
		assertTrue("vowelGroupCount incorrect", StringUtils.getVowelGroupCount(input) == expectedOutput);

		input = "botanical";
		expectedOutput = 4;
		assertTrue("vowelGroupCount incorrect", StringUtils.getVowelGroupCount(input) == expectedOutput);

		input = "evaporation";
		expectedOutput = 5;
		assertTrue("vowelGroupCount incorrect", StringUtils.getVowelGroupCount(input) == expectedOutput);
		
		// bad input tests
		
		input = "";
		expectedOutput = 0;
		assertTrue("vowelGroupCount incorrect", StringUtils.getVowelGroupCount(input) == expectedOutput);
		
		input = null;
		expectedOutput = 0;
		assertTrue("vowelGroupCount incorrect", StringUtils.getVowelGroupCount(input) == expectedOutput);
	}

	/**
	 * Test case for testing the StringUtils.getSubstringCount function.
	 */
	@Test
	public void getSubstringCountTest() {
		String input;
		String substring;
		int expectedOutput;

		input = "car";
		substring = "c";
		expectedOutput = 1;
		assertTrue("substringCount incorrect", StringUtils.substringCount(input, substring) == expectedOutput);

		input = "car";
		substring = "car";
		expectedOutput = 1;
		assertTrue("substringCount incorrect", StringUtils.substringCount(input, substring) == expectedOutput);

		input = "complete";
		substring = "e";
		expectedOutput = 2;
		assertTrue("substringCount incorrect", StringUtils.substringCount(input, substring) == expectedOutput);

		input = "ababab";
		substring = "ab";
		expectedOutput = 3;
		assertTrue("substringCount incorrect", StringUtils.substringCount(input, substring) == expectedOutput);

		input = "abbabbabb";
		substring = "abb";
		expectedOutput = 3;
		assertTrue("substringCount incorrect", StringUtils.substringCount(input, substring) == expectedOutput);

		input = "ababbabb";
		substring = "abb";
		expectedOutput = 2;
		assertTrue("substringCount incorrect", StringUtils.substringCount(input, substring) == expectedOutput);

		
		// bad input tests
		
		input = "ababbabb";
		substring = "";
		expectedOutput = 0;
		assertTrue("substringCount incorrect", StringUtils.substringCount(input, substring) == expectedOutput);
		
		input = "";
		substring = "abc";
		expectedOutput = 0;
		assertTrue("substringCount incorrect", StringUtils.substringCount(input, substring) == expectedOutput);
		
		input = null;
		substring = "abc";
		expectedOutput = 0;
		assertTrue("substringCount incorrect", StringUtils.substringCount(input, substring) == expectedOutput);
		
		input = "abc";
		substring = null;
		expectedOutput = 0;
		assertTrue("substringCount incorrect", StringUtils.substringCount(input, substring) == expectedOutput);
		
		input = null;
		substring = null;
		expectedOutput = 0;
		assertTrue("substringCount incorrect", StringUtils.substringCount(input, substring) == expectedOutput);

	}

	/**
	 * Test case for testing the StringUtils.isAnagram function.
	 */
	@Test
	public void isAnagramTest() {
		String input;
		String test;
		boolean expectedOutput;

		input = "car";
		test = "car";
		expectedOutput = false;
		assertTrue("anagram output incorrect", StringUtils.isAnagram(input, test) == expectedOutput);

		input = "car";
		test = "rac";
		expectedOutput = true;
		assertTrue("anagram output incorrect", StringUtils.isAnagram(input, test) == expectedOutput);

		input = "car";
		test = "care";
		expectedOutput = false;
		assertTrue("anagram output incorrect", StringUtils.isAnagram(input, test) == expectedOutput);

		input = "adverb";
		test = "braved";
		expectedOutput = true;
		assertTrue("anagram output incorrect", StringUtils.isAnagram(input, test) == expectedOutput);

		input = "aabbcc";
		test = "ccbbaa";
		expectedOutput = true;
		assertTrue("anagram output incorrect", StringUtils.isAnagram(input, test) == expectedOutput);
		
		// bad input test
		input = "";
		test = "ccbbaa";
		expectedOutput = false;
		assertTrue("anagram output incorrect", StringUtils.isAnagram(input, test) == expectedOutput);
		
		input = "aaa";
		test = "";
		expectedOutput = false;
		assertTrue("anagram output incorrect", StringUtils.isAnagram(input, test) == expectedOutput);
		
		input = "aaa";
		test = null;
		expectedOutput = false;
		assertTrue("anagram output incorrect", StringUtils.isAnagram(input, test) == expectedOutput);
		
		input = null;
		test = "aaa";
		expectedOutput = false;
		assertTrue("anagram output incorrect", StringUtils.isAnagram(input, test) == expectedOutput);
		
	}

	/**
	 * Test case for testing the StringUtils.rerrangeString function.
	 */
	@Test
	public void rearrangeStringTest() {
		
		String input;
		String output;
		
		// Create a custom Random object for this test
		// Suppress the serial warning, as it is not valid because this class is anonymous and only used in this test case
		@SuppressWarnings("serial")
		Random testRandom = new Random() {
			@Override
			public int nextInt(int n) {
				return n-1;
			}
		};
		
		input = "cat";
		output = StringUtils.rearrangeString(input, testRandom);
		assertTrue("rearrangeString output incorrect", !input.equals(output));
		
		input = "evaporation";
		output = StringUtils.rearrangeString(input, testRandom);
		assertTrue("rearrangeString output incorrect", !input.equals(output));
		
		// bad input tests
		input = null;
		output = StringUtils.rearrangeString(input, testRandom);
		assertTrue("rearrangeString output incorrect", output == null);
		
		input = "abc";
		output = StringUtils.rearrangeString(input, null);
		assertTrue("rearrangeString output incorrect", output == null);
		
	}
	
	/**
	 * Test case for the StringUtils string to characterList function.
	 */
	@Test
	public void stringToCharacterListTest() {
		List<Character> charList;
		
		charList = StringUtils.stringToCharacterList("test");
		
		assertTrue("character at index wrong", charList.get(0) == 't');
		assertTrue("character at index wrong", charList.get(1) == 'e');
		assertTrue("character at index wrong", charList.get(2) == 's');
		assertTrue("character at index wrong", charList.get(3) == 't');
		
		// bad input check
		
		charList = StringUtils.stringToCharacterList("");
		assertTrue("charList size wrong", charList.size() == 0);
		
		charList = StringUtils.stringToCharacterList(null);
		assertTrue("charList not null", charList == null);
		
	}
	
	/**
	 * Test case for the reverseString method.
	 */
	@Test
	public void reverseStringTest() {
		String input;
		String expected;
		
		input = "hello";
		expected = "olleh";
		
		assertTrue("reverse wrong", expected.equals(StringUtils.reverseString(input)));
		
		// bad input check
		
		input = "";
		expected = "";
		assertTrue("reverse wrong", expected.equals(StringUtils.reverseString(input)));
		
		input = null;
		assertTrue("reverse wrong", StringUtils.reverseString(input) == null);
		
	}

}
