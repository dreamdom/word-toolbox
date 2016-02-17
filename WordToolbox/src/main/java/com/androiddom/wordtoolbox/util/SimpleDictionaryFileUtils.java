package com.androiddom.wordtoolbox.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * A collection of functions related to using Files.
 *
 */
public class SimpleDictionaryFileUtils {

	private static final String SIMPLE_DICTIONARY_START = "---";

	/**
	 * Method to load a file in the Simple Dictionary format.
	 * 
	 * @param file
	 *            The file to load
	 */
	public static SimpleDictionaryData loadSimpleDictionaryFileToSet(File file) {
		
		StringBuilder header = new StringBuilder();
		Set<String> words = new HashSet<String>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			boolean addToSet = false;
			String line;
			while ((line = br.readLine()) != null) {
				if (addToSet) {
					if (line != null && line.length() > 0) {
						words.add(line);
					}
				} else {
					// Check for the dictionary start
					if (line.equals(SIMPLE_DICTIONARY_START)) {
						addToSet = true;
					} else {
						// the line is part of the header
						header.append(line);
						header.append("\n");
					}
				}
			}

			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new SimpleDictionaryData(header.toString(), words);
	}
	
	// TODO: Add method saveDictionaryToSimpleFile
	
	/**
	 * A simple data structure to hold SimpleDictionary data loaded from a file
	 */
	public static class SimpleDictionaryData {
		
		private final String header;
		private final Set<String> words;
		
		public SimpleDictionaryData(String header, Set<String> words) {
			this.header = header;
			this.words = words;
		}
		
		public String getHeader() {
			return header;
		}
		
		public Set<String> getWords() {
			return words;
		}
	}

}
