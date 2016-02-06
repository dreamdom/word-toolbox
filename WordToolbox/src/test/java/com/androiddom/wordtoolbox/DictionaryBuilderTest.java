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
	
	/**
	 * Test for the DictionaryBuilder startsWith method.
	 */
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
	
	/**
	 * Test for the DictionaryBuilder ends with method.
	 */
	@Test
	public void endsWithTest() {
		
		Dictionary testDict;
		String[] words = {"ba", "baba", "bar", "bet", "car", "cba"};
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.endsWith("a")
				.build();
		
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 3);
		assertFalse("dictionary has word wrong", testDict.hasWord("car"));
		assertFalse("dictionary has word wrong", testDict.hasWord("bet"));
		
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.endsWith("ba")
				.build();
		
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 3);
		assertFalse("dictionary has word wrong", testDict.hasWord("car"));
		assertFalse("dictionary has word wrong", testDict.hasWord("bar"));
		assertFalse("dictionary has word wrong", testDict.hasWord("bet"));
		
		// Bad input check
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.endsWith("")
				.build();
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 6);
		
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.endsWith(null)
				.build();
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 6);
	}
	
	/**
	 * Test for the DictionaryBuilder length method.
	 */
	@Test
	public void lengthTest() {
		
		Dictionary testDict;
		String[] words = {"ba", "baba", "bar", "bet", "car", "cba"};
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.length(3)
				.build();
		
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 4);
		assertFalse("dictionary has word wrong", testDict.hasWord("ba"));
		assertFalse("dictionary has word wrong", testDict.hasWord("baba"));
		
		// Bad input check
		
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.length(0)
				.build();
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 0);
		
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.length(-1)
				.build();
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 0);
	}
	
	/**
	 * Test for the DictionaryBuilder minLength method.
	 */
	@Test
	public void minLenghtTest() {
		
		Dictionary testDict;
		String[] words = {"ba", "baba", "bar", "bet", "car", "cba"};
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.minLength(3).build();
		
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 5);
		assertFalse("dictionary has word wrong", testDict.hasWord("ba"));
		
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.minLength(4).build();
		
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 1);
		assertFalse("dictionary has word wrong", testDict.hasWord("bar"));
		
		// Bad input check
		
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.minLength(0)
				.build();
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 6);
		
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.minLength(-1)
				.build();
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 6);
		
	}
	
	/**
	 * Test for the DictionaryBuilder complexity method.
	 */
	@Test
	public void complexityTest() {
		Dictionary testDict;
		String[] words = {"why", "took", "book"};
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.complexity(5).build();
		
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 1);
		
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.complexity(8).build();
		
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 2);
		
		// Bad input check
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.complexity(0)
				.build();
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 0);
		
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.complexity(-1)
				.build();
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 0);
		
	}
	
	/**
	 * Test for the DictionaryBuilder filterPossessive method.
	 */
	@Test
	public void filterPossessiveTest() {
		Dictionary testDict;
		String[] words = {"john", "john's", "steve's"};
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.filterApostropheS().build();
		
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 1);
	}
	
	/**
	 * Test for the DictionaryBuilder anagrams method.
	 */
	@Test
	public void anagramTest() {
		Dictionary testDict;
		String[] words = {"adverb", "race", "braved", "care"};
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.anagrams("adverb").build();
		
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 1);
		
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.anagrams("care").build();
		
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 1);
		
		
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.anagrams("none").build();
		
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 0);
		
		// Bad input check
		
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.anagrams("").build();
		
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 0);
		
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.anagrams(null).build();
		
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 0);
		
	}
	
	/**
	 * Test for the DictionaryBuilder soundexEquals method.
	 */
	@Test
	public void soundexEqualsTest() {
		Dictionary testDict;
		String[] words = {"a", "e", "b", "p"};
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.soundexEquals("0").build();
		
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 2);
		assertFalse("dictionary size wrong", testDict.hasWord("b"));
		
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.soundexEquals("1").build();
		
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 2);
		assertFalse("dictionary size wrong", testDict.hasWord("a"));
		
		// Bad input check
		
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.soundexEquals("z").build();
		assertTrue("dictionary size wrong", testDict.numberOfWords() == 0);
	}
	
}
