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
	 * Test for the DictionaryBuilder contains method.
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
	
	/**
	 * Test for the DictionaryBuilder containsCount method.
	 */
	@Test
	public void containsCountTest() {
		
		Dictionary testDict;
		String[] words = {"ba", "baba", "bar", "bat", "car"};
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.containsCount("ba", 1)
				.build();
		
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 3);
		
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.containsCount("ba", 2)
				.build();
		
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 1);
		assertTrue("dictionary hasWord wrong", testDict.hasWord("baba"));
		
		// Bad input checks
		
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.containsCount("ba", 0)
				.build();
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 5);
		
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.containsCount("", 0)
				.build();
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 5);
		
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.containsCount(null, 0)
				.build();
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 5);
	}
	
	@Test
	public void startsWithTest() {
		Dictionary testDict;
		String[] words = {"ba", "baba", "bar", "bet", "car", "cba"};
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.startsWith("b")
				.build();
		
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 4);
		assertFalse("dictionary has word wrong", testDict.hasWord("car"));
		assertFalse("dictionary has word wrong", testDict.hasWord("cba"));
		
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.startsWith("ba")
				.build();
		
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 3);
		assertFalse("dictionary has word wrong", testDict.hasWord("car"));
		assertFalse("dictionary has word wrong", testDict.hasWord("cba"));
		assertFalse("dictionary has word wrong", testDict.hasWord("bet"));
		
		// Bad input check
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.startsWith("")
				.build();
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 6);
		
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.startsWith(null)
				.build();
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 6);
		
	}
	
}
