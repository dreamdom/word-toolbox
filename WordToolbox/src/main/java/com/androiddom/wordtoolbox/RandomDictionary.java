package com.androiddom.wordtoolbox;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import com.androiddom.wordtoolbox.util.DictionaryUtils;

public class RandomDictionary extends Dictionary {
	
	private List<String> wordList;
	private Random random;
	
	RandomDictionary() {
		super();
		wordList = new ArrayList<String>();
		random = new Random();
	}
	
	public String getRandomWord() {
		int index = random.nextInt(wordList.size());
		return wordList.get(index);
	}
	
	public static class Builder {

		private File file;
		private Dictionary baseDictionary;
		
		private RandomDictionary dictionary;
		
		public Builder(File file) {
			this.file = file;
		}
		
		public Builder(Dictionary baseDictionary) {
			this.baseDictionary = baseDictionary;
		}
		
		public RandomDictionary build() {
			
			// Create a new instance of a dictionary object
			dictionary = new RandomDictionary();
			
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
			
			// Return the process dictionary
			return dictionary;
		}
		
		
		protected void processFilters() {
			// TODO: Add code here
		}
		

	}

}
