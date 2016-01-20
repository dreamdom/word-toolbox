package com.androiddom.wordtoolbox;

import java.io.File;

public class MainClass {

	public static void main(String[] args) {
		File file = new File("../DictionaryData/dictionary.txt");
		Dictionary testDictionary = new Dictionary.Builder(file).build();
		
		System.out.println(testDictionary.hasWord("the"));
		System.out.println(testDictionary.hasWord("asdf"));
		
		RandomDictionary random = new RandomDictionary.Builder(testDictionary).build();
		
		System.out.println(random.getRandomWord());
		System.out.println(random.getRandomWord());
		
	}

}
