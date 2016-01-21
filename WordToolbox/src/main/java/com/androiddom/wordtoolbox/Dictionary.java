package com.androiddom.wordtoolbox;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.androiddom.wordtoolbox.util.DictionaryUtils;
import com.androiddom.wordtoolbox.util.WordComplexity;

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
		if (wordList.size() == 0)
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

		private List<Rule> ruleList = new ArrayList<Rule>();

		public Builder(File file) {
			this.file = file;
		}

		public Builder(Dictionary baseDictionary) {
			this.baseDictionary = baseDictionary;
		}

		public Builder contains(final String contains) {
			ruleList.add(new Rule() {
				@Override
				public boolean evaluate(String input) {
					return input.contains(contains);
				}
			});
			return this;
		}

		public Builder startsWith(final String startsWith) {
			ruleList.add(new Rule() {
				@Override
				public boolean evaluate(String input) {
					return input.startsWith(startsWith);
				}
			});
			return this;
		}
		
		public Builder endsWith(final String endsWith) {
			ruleList.add(new Rule(){
				@Override
				public boolean evaluate(String input) {
					return input.endsWith(endsWith);
				}
			});
			return this;
		}
		
		public Builder length(final int length) {
			ruleList.add(new Rule(){
				@Override
				public boolean evaluate(String input) {
					return (input.length() == length);
				}
			});
			return this;
		}
		
		public Builder complexity(final int complexity) {
			ruleList.add(new Rule(){
				@Override
				public boolean evaluate(String input) {
					return (WordComplexity.getWordComplexity(input) == complexity);
				}
			});
			return this;
		}
		
		public Builder filterPossesive() {
			ruleList.add(new Rule(){
				@Override
				public boolean evaluate(String input) {
					return !input.contains("'s");
				}
			});
			return this;
		}

		public Dictionary build() {

			// Create a new instance of a dictionary object
			dictionary = new Dictionary();

			// Load the dictionary from file or an existing dictionary
			if (file != null) {
				DictionaryUtils.loadSimpleFileToSet(file, dictionary.words);
			} else if (baseDictionary != null) {
				dictionary.words = new HashSet<String>(baseDictionary.words);
			}

			// Process any filters added to the builder
			processRules();

			// Add all the words to the list, and shuffle it
			dictionary.wordList.addAll(dictionary.words);
			Collections.shuffle(dictionary.wordList);

			// null out the reference to the base dictionary
			// this.baseDictionary = null;

			// Return the process dictionary
			return dictionary;
		}

		protected void processRules() {
			Set<String> passSet = new HashSet<String>();
			
			for(String word : dictionary.words) {
				boolean pass = true;
				for(Rule r : ruleList) {
					if(!r.evaluate(word)) {
						pass = false;
						break;
					}
				}
				
				if(pass)
					passSet.add(word);
			}
			
			dictionary.words = passSet;
		}

	}

	public interface Rule {
		public boolean evaluate(String input);
	}
}
