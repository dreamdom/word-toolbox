package com.androiddom.wordtoolbox;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.androiddom.wordtoolbox.util.DictionaryUtils;

/**
 * A class to maintain a collection of words.
 *
 */
public class Dictionary {
	
	
	private Set<String> words;
	private List<String> wordList;
	private Random random;
	
	/**
	 * A private constructor for the Dictionary object.
	 */
	private Dictionary() {
		words = new HashSet<String>();
		wordList = new ArrayList<String>();
		random = new Random();
	}
	
	public boolean hasWord(String word) {
		return words.contains(word);
	}
	
	public int numberOfWords() {
		return words.size();
	}
	
	public String getRandomWord() {
		if(wordList.size()==0)
			return null;
		
		int index = random.nextInt(wordList.size());
		return wordList.get(index);
	}
	
	/**
	 * A class used to Build Dictionary objects.
	 *
	 */
	public static class Builder {
		
		private File file;
		private Dictionary baseDictionary;
		
		private Dictionary dictionary;
		
		public Builder(File file) {
			this.file = file;
		}
		
		public Builder(Dictionary baseDictionary) {
			this.baseDictionary = baseDictionary;
		}
		
		public Dictionary build() {
			
			// Create a new instance of a dictionary object
			dictionary = new Dictionary();
			
			// Load the dictionary from file or an existing dictionary
			if(file != null) {
				DictionaryUtils.loadSimpleFileToSet(file, dictionary.words);
			} else if (baseDictionary != null) {
				dictionary.words = new HashSet<String>(baseDictionary.words);
			}
			
			// Process any filters added to the builder
			processFilters();
			
			// Add all the words to the list, and shuffle it
			dictionary.wordList.addAll(dictionary.words);
			Collections.shuffle(dictionary.wordList);
			
			// null out the reference to the base dictionary
			this.baseDictionary = null;
			
			// Return the process dictionary
			return dictionary;
		}
		
		
		protected void processFilters() {
			// TODO: Add code here
		}
		
	}
}
