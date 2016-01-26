package com.androiddom.wordtoolbox;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * A set of test cases for the DictionaryBuilder.
 *
 */
public class DictionaryBuilderTest {

	/**
	 * Method to test the DictionaryBuilder contains method.
	 */
	@Test
	public void containsTest() {
		Dictionary testDict;
		String[] words = {"cat", "car", "cot", "dog"};
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.contains("c")
				.build();
		
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 3);
		assertTrue("dictionary hasWord wrong", testDict.hasWord("cat"));
		assertTrue("dictionary hasWord wrong", testDict.hasWord("car"));
		assertTrue("dictionary hasWord wrong", testDict.hasWord("cot"));
		assertFalse("dictionary hasWord wrong", testDict.hasWord("dog"));
		
		
		// Bad input checks
		
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.contains("")
				.build();
		
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 4);
		
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.contains(null)
				.build();
		
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 4);
	}
	
}
