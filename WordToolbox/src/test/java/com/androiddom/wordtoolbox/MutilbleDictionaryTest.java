package com.androiddom.wordtoolbox;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * A set of test cases for the MutibleDictionary class.
 *
 */
public class MutilbleDictionaryTest {
	
	/**
	 * The dictionary object to use in the tests.
	 */
	private static MutibleDictionary testDictionary;
	
	@BeforeClass
	public static void setUp() {
		File file = new File("src/test/resources/testdictionary.txt");
		testDictionary = new MutibleDictionary.Builder(file).build();
	}
	
	/**
	 * Method to test the addWord method.
	 */
	@Test
	public void addWordTest() {
		boolean result;
		
		// Test that the word is not present
		result = testDictionary.hasWord("aaa");
		assertFalse("dictionary hasWord incorrect", result);
		
		// Add the word
		testDictionary.addWord("aaa");
		
		// Test that the word is present
		result = testDictionary.hasWord("aaa");
		assertTrue("dictionary hasWord incorrect", result);
	}
	
	/**
	 * Method to test the removeWord method.
	 */
	@Test
	public void removeWordTest() {
		boolean result;
		
		// Build a second dictionary
		Dictionary secondDictionary = new Dictionary.Builder(testDictionary).build();
		
		// Verify that the second dictionary has the word.
		result = secondDictionary.hasWord("the");
		assertTrue("secondDictionary hasWord incorrect", result);
		
		// Test that the word is present
		result = testDictionary.hasWord("the");
		assertTrue("dictionary hasWord incorrect", result);
		
		// Remove the word
		result = testDictionary.removeWord("the");
		
		// Assert that the word was removed
		assertTrue("dictionary word was not removed", result);
		
		// Test that the word is not present
		result = testDictionary.hasWord("the");
		assertFalse("dictionary hasWord incorrect", result);
		
		// Attempt to remove the word again
		result = testDictionary.removeWord("the");

		// Assert that the word was not removed
		assertFalse("dictionary word was not removed", result);
		
		// Verify that the second dictionary still has the word
		result = secondDictionary.hasWord("the");
		assertTrue("secondDictionary hasWord incorrect", result);
		
	}
	
}
