package com.androiddom.wordtoolbox;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import com.androiddom.wordtoolbox.util.DictionaryUtils;

/**
 * A class to maintain a collection of words.
 *
 */
public class Dictionary {
	
	
	protected Set<String> words;
	
	/**
	 * A protected constructor for the Dictionary object.
	 */
	Dictionary() {
		words = new HashSet<String>();
	}
	
	public boolean hasWord(String word) {
		return words.contains(word);
	}
	
	public int numberOfWords() {
		return words.size();
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
			
			// Return the process dictionary
			return dictionary;
		}
		
		
		protected void processFilters() {
			// TODO: Add code here
		}
		
	}
}
