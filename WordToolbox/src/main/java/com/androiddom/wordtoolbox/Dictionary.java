package com.androiddom.wordtoolbox;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import com.androiddom.wordtoolbox.util.DictionaryUtils;
import com.androiddom.wordtoolbox.util.StringUtils;
import com.androiddom.wordtoolbox.util.WordComplexity;

/**
 * A class to maintain a collection of words.
 *
 */
public class Dictionary {

	/**
	 * The set of words in the dictionary.
	 */
	private Set<String> words;

	/**
	 * A list of words in the dictionary.
	 */
	private List<String> wordList;

	/**
	 * An object used to generate a psuedo random number. Used for getting a
	 * random word from the dictionary.
	 */
	private Random random;

	/**
	 * A private constructor for the Dictionary object.
	 */
	private Dictionary() {
		words = new HashSet<String>();
		wordList = new ArrayList<String>();
		random = new Random();
	}

	/**
	 * Method to see if the dictionary contains a word or not.
	 * 
	 * @param word
	 *            The word to check
	 * @return True if word is in the dictionary, false otherwise.
	 */
	public boolean hasWord(String word) {
		return words.contains(word);
	}

	/**
	 * Method to get the number of words in the dictionary.
	 * 
	 * @return The number of words in the dictionary.
	 */
	public int numberOfWords() {
		return words.size();
	}

	/**
	 * Method to get a random word from the dictionary.
	 * 
	 * @return A random word from the dictionary.
	 */
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

		/**
		 * Variable set by one of the constructors. Represents a file to be
		 * loaded as the set of words in the dictionary.
		 */
		private File file;

		/**
		 * Variable set by one of the constructors. Represents an existing
		 * dictionary that should become the base set of words in the
		 * dictionary.
		 */
		private Dictionary baseDictionary;

		/**
		 * Reference to the dictionary object that will be returned by the build
		 * command.
		 */
		private Dictionary dictionary;

		/**
		 * A list of rules to apply when building the dictionary.
		 */
		private List<Rule> ruleList = new ArrayList<Rule>();

		/**
		 * Constructor for the Builder object.
		 * 
		 * @param file
		 *            The file to be used as the basis of this dictionary.
		 */
		public Builder(File file) {
			this.file = file;
		}

		/**
		 * Constructor for the Builder object.
		 * 
		 * @param baseDictionary
		 *            The dictionary to be used as the basis of this dictionary.
		 */
		public Builder(Dictionary baseDictionary) {
			this.baseDictionary = baseDictionary;
		}

		// Rules

		/**
		 * A rule that words in the dictionary must contain a String.
		 * 
		 * @param contains
		 *            The string that valid words must contain.
		 * @return The Builder object.
		 */
		public Builder contains(final String contains) {
			ruleList.add(new Rule() {
				@Override
				public boolean evaluate(String input) {
					return input.contains(contains);
				}
			});
			return this;
		}

		/**
		 * A rule that words in the dictionary must contain a specific string a
		 * specific number of times.
		 * 
		 * @param contains
		 *            The String that words must contain.
		 * @param count
		 *            The specific number of times that the String contains must
		 *            be present.
		 * @return The Builder object.
		 */
		public Builder containsCount(final String contains, final int count) {
			ruleList.add(new Rule() {
				@Override
				public boolean evaluate(String input) {
					return (StringUtils.substringCount(input, contains) == count);
				}
			});
			return this;
		}

		/**
		 * A rule that words in the dictionary must start with a specific
		 * String.
		 * 
		 * @param startsWith
		 *            The specific String that words must start with.
		 * @return The Builder object.
		 */
		public Builder startsWith(final String startsWith) {
			ruleList.add(new Rule() {
				@Override
				public boolean evaluate(String input) {
					return input.startsWith(startsWith);
				}
			});
			return this;
		}

		/**
		 * A rule that words in the dictionary must end with a specific String.
		 * 
		 * @param endsWith
		 *            The specific String that words must end with.
		 * @return The Builder object.
		 */
		public Builder endsWith(final String endsWith) {
			ruleList.add(new Rule() {
				@Override
				public boolean evaluate(String input) {
					return input.endsWith(endsWith);
				}
			});
			return this;
		}

		/**
		 * A rule that words in the dictionary must be of a specific length.
		 * 
		 * @param length
		 *            The specific length that words must be.
		 * @return The Builder object.
		 */
		public Builder length(final int length) {
			ruleList.add(new Rule() {
				@Override
				public boolean evaluate(String input) {
					return (input.length() == length);
				}
			});
			return this;
		}

		/**
		 * A rule that words in the dictionary must be equal to or longer than a
		 * specific length.
		 * 
		 * @param length
		 *            The specific length words must be equal to, or longer.
		 * @return The Builder object.
		 */
		public Builder minLength(final int length) {
			ruleList.add(new Rule() {
				@Override
				public boolean evaluate(String input) {
					return (input.length() >= length);
				}
			});
			return this;
		}

		/**
		 * A rule that words in the dictionary must be of a specific complexity.
		 * 
		 * @param complexity
		 *            The specific complexity.
		 * @return The Builder object.
		 */
		public Builder complexity(final int complexity) {
			ruleList.add(new Rule() {
				@Override
				public boolean evaluate(String input) {
					return (WordComplexity.getWordComplexity(input) == complexity);
				}
			});
			return this;
		}

		/**
		 * A rule to exclude words that end with 's
		 * 
		 * @return The Builder object.
		 */
		public Builder filterPossesive() {
			ruleList.add(new Rule() {
				@Override
				public boolean evaluate(String input) {
					return !input.contains("'s");
				}
			});
			return this;
		}
		
		public Builder filterProper() {
			ruleList.add(new Rule(){
				@Override
				public boolean evaluate(String input) {
					return input.toLowerCase(Locale.US).equals(input);
				}
			});
			return this;
		}

		/**
		 * A rule to only include words that anagrams of the input word.
		 * 
		 * @param input
		 *            String representing the input word.
		 * @return The Builder object.
		 */
		public Builder anagrams(final String input) {
			ruleList.add(new Rule() {
				@Override
				public boolean evaluate(String evalInput) {
					return StringUtils.isAnagram(input, evalInput);
				}
			});
			return this;
		}

		/**
		 * A method to add new rule to the list of rules to be processed.
		 * 
		 * @param rule
		 *            The rule to add to the list of rules.
		 * @return The Builder object.
		 */
		public Builder addRule(Rule rule) {
			ruleList.add(rule);
			return this;
		}

		// Building

		/**
		 * Method to build the Dictionary object. Dictionary will be based on a
		 * file that is read or a dictionary object depending on what
		 * constructor was used. Will apply all the rules set on the builder.
		 * 
		 * @return A dictionary object.
		 */
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
			this.baseDictionary = null;

			// Return the process dictionary
			return dictionary;
		}

		/**
		 * Private method that processes all of the rules applied to the
		 * builder.
		 */
		private void processRules() {
			Set<String> passSet = new HashSet<String>();

			for (String word : dictionary.words) {
				boolean pass = true;
				for (Rule r : ruleList) {
					if (!r.evaluate(word)) {
						pass = false;
						break;
					}
				}

				if (pass)
					passSet.add(word);
			}

			dictionary.words = passSet;
		}

	}

	/**
	 * Public interface for a rule to be applied to a dictionary.
	 *
	 */
	public interface Rule {

		/**
		 * Function that determines whether or not an input word should be
		 * included in a dictionary or not.
		 * 
		 * @param input
		 *            The input word
		 * @return True if input should be included in the dictionary, false
		 *         otherwise.
		 */
		public boolean evaluate(String input);
	}
}
