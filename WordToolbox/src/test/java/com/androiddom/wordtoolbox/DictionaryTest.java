package com.androiddom.wordtoolbox;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

public class DictionaryTest {
	
	/**
	 * The dictionary object to use in the tests.
	 */
	private static Dictionary testDictionary;
	
	@BeforeClass
	public static void setUp() {
		File file = new File("../DictionaryData/dictionary.txt");
		testDictionary = new Dictionary.Builder(file).build();
	}

	@Test
	public void hasWordTest() {
		assertTrue("'the' not in dictionary", testDictionary.hasWord("the"));
		assertFalse("'asdf' in dictionary", testDictionary.hasWord("asdf"));
	}

}
