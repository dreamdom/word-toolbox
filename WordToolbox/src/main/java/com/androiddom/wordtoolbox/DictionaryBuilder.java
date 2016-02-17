package com.androiddom.wordtoolbox;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.androiddom.wordtoolbox.analytics.WordComplexity;
import com.androiddom.wordtoolbox.util.SimpleDictionaryFileUtils;
import com.androiddom.wordtoolbox.util.SimpleDictionaryFileUtils.SimpleDictionaryData;
import com.androiddom.wordtoolbox.util.PhoneticUtils;
import com.androiddom.wordtoolbox.util.StringUtils;

/**
 * A class used to build a dictionary object.
 *
 * @param <T>
 *            The dictionary class to build. Must extend Dictionary.
 */
public abstract class DictionaryBuilder<T extends Dictionary> {

	/**
	 * A set of words used by the builder for building the Dictionary.
	 */
	protected Set<String> builderWords;
	
	/**
	 * A header associated with the dictionary. May include notes or copyright
	 * information.
	 */
	protected String builderHeader;

	/**
	 * A list of rules to apply when building the dictionary.
	 */
	protected List<Rule> ruleList = new ArrayList<Rule>();

	/**
	 * Constructor for the Builder object.
	 * 
	 * @param file
	 *            The file to be used as the basis of this dictionary.
	 */
	public DictionaryBuilder(File file) {
		// Load the dictionary from file or an existing dictionary
		SimpleDictionaryData simpleDictionaryData = SimpleDictionaryFileUtils.loadSimpleDictionaryFileToSet(file);
		builderWords = simpleDictionaryData.getWords();
		builderHeader = simpleDictionaryData.getHeader();
		
	}

	/**
	 * Constructor for the Builder object.
	 * 
	 * @param baseDictionary
	 *            The dictionary to be used as the basis of this dictionary.
	 */
	public DictionaryBuilder(Dictionary baseDictionary) {
		builderWords = new HashSet<String>(baseDictionary.getWords());
		builderHeader = baseDictionary.getHeader();
	}

	/**
	 * Constructor for the Builder object.
	 * 
	 * @param words
	 *            A Collection of words to be used as the basis of this
	 *            dictionary.
	 */
	public DictionaryBuilder(Collection<String> words) {
		builderWords = new HashSet<>(words);
	}

	// Rules

	/**
	 * A rule that words in the dictionary must contain a String.
	 * 
	 * @param contains
	 *            The string that valid words must contain.
	 * @return The Builder object.
	 */
	public DictionaryBuilder<T> contains(final String contains) {

		// Bad input check
		if (contains == null || contains.length() == 0) {
			return this;
		}

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
	 *            The specific number of times that the String contains must be
	 *            present.
	 * @return The Builder object.
	 */
	public DictionaryBuilder<T> containsCount(final String contains, final int count) {

		// Bad input check
		if (count == 0 || contains == null || contains.length() == 0) {
			return this;
		}

		ruleList.add(new Rule() {
			@Override
			public boolean evaluate(String input) {
				return (StringUtils.substringCount(input, contains) == count);
			}
		});
		return this;
	}

	/**
	 * A rule that words in the dictionary must start with a specific String.
	 * 
	 * @param startsWith
	 *            The specific String that words must start with.
	 * @return The Builder object.
	 */
	public DictionaryBuilder<T> startsWith(final String startsWith) {

		// Bad input check
		if (startsWith == null || startsWith.length() == 0) {
			return this;
		}

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
	public DictionaryBuilder<T> endsWith(final String endsWith) {

		// Bad input check
		if (endsWith == null || endsWith.length() == 0) {
			return this;
		}

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
	public DictionaryBuilder<T> length(final int length) {

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
	public DictionaryBuilder<T> minLength(final int length) {
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
	public DictionaryBuilder<T> complexity(final int complexity) {
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
	public DictionaryBuilder<T> filterApostropheS() {
		ruleList.add(new Rule() {
			@Override
			public boolean evaluate(String input) {
				return !input.contains("'s");
			}
		});
		return this;
	}

	/**
	 * A rule to exclude words that start with a capital letter.
	 * 
	 * @return The Builder object.
	 */
	public DictionaryBuilder<T> filterProper() {
		ruleList.add(new Rule() {
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
	public DictionaryBuilder<T> anagrams(final String input) {
		ruleList.add(new Rule() {
			@Override
			public boolean evaluate(String evalInput) {
				return StringUtils.isAnagram(input, evalInput);
			}
		});
		return this;
	}

	/**
	 * A rule to only include words with a specific soundex pattern.
	 * 
	 * @param soundex
	 *            The soundex pattern to match.
	 * @return The Builder object.
	 */
	public DictionaryBuilder<T> soundexEquals(final String soundex) {
		ruleList.add(new Rule() {
			@Override
			public boolean evaluate(String input) {
				return PhoneticUtils.getSoundex(input).equals(soundex);
			}

		});
		return this;
	}

	/**
	 * A rule to exclude words with non ascii letters.
	 * 
	 * @return The Builder object.
	 */
	public DictionaryBuilder<T> removeNonAsciiLetter() {
		ruleList.add(new Rule() {
			@Override
			public boolean evaluate(String input) {
				return input.equals(input.replaceAll("[^a-zA-Z ]", ""));
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
	public DictionaryBuilder<T> addRule(Rule rule) {
		ruleList.add(rule);
		return this;
	}

	/**
	 * Protected method that processes all of the rules applied to the builder.
	 */
	protected void processRules() {
		Set<String> passSet = new HashSet<String>();

		for (String word : builderWords) {
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

		builderWords = passSet;
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

	// Abstract methods

	/**
	 * A method to build the dictionary.
	 * 
	 * @return A class the extends Dictionary.
	 */
	public abstract T build();

}
