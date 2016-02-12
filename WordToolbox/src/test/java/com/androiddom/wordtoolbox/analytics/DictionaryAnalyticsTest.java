package com.androiddom.wordtoolbox.analytics;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Map;

import org.junit.Test;

import com.androiddom.wordtoolbox.Dictionary;

/**
 * A set of test cases for the DictionaryAnalytics functions.
 *
 */
public class DictionaryAnalyticsTest {
	
	@Test
	public void lengthCountDictTest() {
		Dictionary testDict;
		String[] words = {"cat", "care", "cot", "dog", "a"};
		testDict = new Dictionary.Builder(Arrays.asList(words))
				.build();
		
		Map<Integer, Integer> lengthCount = DictionaryAnalytics.lengthCountDict(testDict);
		
		assertTrue("lengthCount wrong", lengthCount.get(1) == 1);
		assertTrue("lengthCount wrong", lengthCount.get(4) == 1);
		assertTrue("lengthCount wrong", lengthCount.get(3) == 3);
	}

}
