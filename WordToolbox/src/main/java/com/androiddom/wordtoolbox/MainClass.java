package com.androiddom.wordtoolbox;

import java.io.File;

public class MainClass {

	public static void main(String[] args) {
		File file = new File("../DictionaryData/dictionary.txt");
		Dictionary testDictionary = new Dictionary.Builder(file).build();
		
		System.out.println(testDictionary.hasWord("the"));
		System.out.println(testDictionary.hasWord("asdf"));
		
		Dictionary test2 = new Dictionary.Builder(testDictionary).complexity(14).contains("j").filterPossesive().build();
		
		System.out.println(test2.getRandomWord());
		System.out.println(test2.getRandomWord());
		
		
	}

}
