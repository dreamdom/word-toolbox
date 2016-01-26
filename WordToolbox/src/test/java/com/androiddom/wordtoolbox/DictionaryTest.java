package com.androiddom.wordtoolbox;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * A set of test cases for the Dictionary object.
 *
 */
public class DictionaryTest {

	/**
	 * The dictionary object to use in the tests.
	 */
	private static Dictionary testDictionary;

	/**
	 * Set up the dictionary that is used in several tests.
	 */
	@BeforeClass
	public static void setUp() {
		File file = new File("src/test/resources/testdictionary.txt");
		testDictionary = new Dictionary.Builder(file).build();
	}

	/**
	 * Test the hasWord function.
	 */
	@Test
	public void hasWordTest() {
		assertTrue("'the' not in dictionary", testDictionary.hasWord("the"));
		assertFalse("'asdf' in dictionary", testDictionary.hasWord("asdf"));
	}
	
	/**
	 * Test the getWords function.
	 */
	@Test
	public void getWordsTest() {
		Set<String> words = testDictionary.getWords();
		assertTrue("words not null", words != null);
	}
	
	/**
	 * Test the numberOfWords function.
	 */
	@Test
	public void numberOfWordsTest() {
		assertTrue("size correct", testDictionary.numberOfWords() == 5);
	}
	
}
