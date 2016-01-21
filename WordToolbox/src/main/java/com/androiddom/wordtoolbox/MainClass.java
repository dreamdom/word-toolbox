package com.androiddom.wordtoolbox;

import java.io.File;

public class MainClass {

	public static void main(String[] args) {
		File file = new File("../DictionaryData/dictionary.txt");
		Dictionary testDictionary = new Dictionary.Builder(file).build();
		
		System.out.println(testDictionary.hasWord("the"));
		System.out.println(testDictionary.hasWord("asdf"));
		
		System.out.println(testDictionary.getRandomWord());
		System.out.println(testDictionary.getRandomWord());
		
		
	}

}
