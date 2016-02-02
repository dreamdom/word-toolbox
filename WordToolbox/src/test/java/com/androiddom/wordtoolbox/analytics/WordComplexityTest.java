package com.androiddom.wordtoolbox.analytics;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.androiddom.wordtoolbox.analytics.WordComplexity;

/**
 * A set of test cases for functions in the WordComplexity class.
 *
 */
public class WordComplexityTest {

	/**
	 * Test case for the getWordComplexity function.
	 */
	@Test
	public void testGetWordComplexity() {

		String input;
		int expectedOutput;

		input = "why";
		expectedOutput = 5;
		assertTrue("wordComplexity wrong", WordComplexity.getWordComplexity(input) == expectedOutput);

		input = "took";
		expectedOutput = 8;
		assertTrue("wordComplexity wrong", WordComplexity.getWordComplexity(input) == expectedOutput);
		
		input = "book";
		expectedOutput = 8;
		assertTrue("wordComplexity wrong", WordComplexity.getWordComplexity(input) == expectedOutput);

		input = "complex";
		expectedOutput = 16;
		assertTrue("wordComplexity wrong", WordComplexity.getWordComplexity(input) == expectedOutput);

		input = "evaporation";
		expectedOutput = 26;
		assertTrue("wordComplexity wrong", WordComplexity.getWordComplexity(input) == expectedOutput);

		input = "internationalization";
		expectedOutput = 46;
		assertTrue("wordComplexity wrong", WordComplexity.getWordComplexity(input) == expectedOutput);

		// bad input tests
		input = "";
		expectedOutput = 0;
		assertTrue("wordComplexity wrong", WordComplexity.getWordComplexity(input) == expectedOutput);

		input = null;
		expectedOutput = 0;
		assertTrue("wordComplexity wrong", WordComplexity.getWordComplexity(input) == expectedOutput);

	}

}
