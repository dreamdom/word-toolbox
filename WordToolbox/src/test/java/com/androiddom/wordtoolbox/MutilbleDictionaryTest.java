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
		
		// Test that the word is present
		result = testDictionary.hasWord("the");
		assertTrue("dictionary hasWord incorrect", result);
		
		// Remove the word
		testDictionary.removeWord("the");
		
		// Test that the word is not present
		result = testDictionary.hasWord("the");
		assertFalse("dictionary hasWord incorrect", result);
		
	}
	
}
