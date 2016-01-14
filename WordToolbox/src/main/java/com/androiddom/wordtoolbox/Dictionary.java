package com.androiddom.wordtoolbox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * A class to maintain a collection of words.
 *
 */
public class Dictionary {
	
	private static final String DICTIONARY_START = "---";
	
	protected Set<String> words;
	
	/**
	 * A protected constructor for the Dictionary object.
	 */
	Dictionary() {
		words = new HashSet<String>();
	}
	
	public static Builder buildFromFile(File file) {
		return new Builder(file);
	}
	
	public static Builder buildFromDictionary(Dictionary baseDictionary) {
		return new Builder(baseDictionary);
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
		
		public Builder(Dictionary dictionary) {
			this.dictionary = dictionary;
		}
		
		public Dictionary build() {
			
			// Create a new instance of a dictionary object
			dictionary = new Dictionary();
			
			// Load the dictionary from file or an existing dictionary
			if(file != null) {
				loadDictionaryFromFile();
			} else if (baseDictionary != null) {
				dictionary.words = new HashSet<String>(baseDictionary.words);
			}
			
			// Process any filters added to the builder
			processFilters();
			
			// Return the process dictionary
			return dictionary;
		}
		
		private void loadDictionaryFromFile() {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				boolean addToSet = false;
				String line;
				while ((line = br.readLine()) != null) {
					if (addToSet) {
						if (line != null && line.length() > 0) {
							dictionary.words.add(line);
						}
					} else {
						// Check for the dictionary start
						if (line.equals(DICTIONARY_START)) {
							addToSet = true;
						}
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		private void processFilters() {
			// TODO: Add code here
		}
		
	}
}
