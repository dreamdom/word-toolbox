package com.androiddom.wordtoolbox.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

/**
 * A collection of functions that are used by Dictionary objects.
 *
 */
public class DictionaryUtils {

	private static final String SIMPLE_DICTIONARY_START = "---";

	/**
	 * Method to load a file in the Simple Dictionary format to a Set of
	 * Strings.
	 * 
	 * @param file
	 *            The file to load
	 * @param words
	 *            The Set to add the words from the file to.
	 */
	public static void loadSimpleFileToSet(File file, Set<String> words) {
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
					}
				}
			}

			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
