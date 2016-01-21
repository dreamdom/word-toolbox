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
		
		
		Dictionary test3 = new Dictionary.Builder(testDictionary).complexity(26).build();
		System.out.println(test3.getRandomWord());
		System.out.println(test3.getRandomWord());
		
		boolean anagramFound = false;
		while(!anagramFound) {
			String input = testDictionary.getRandomWord();
			Dictionary anagramDict = new Dictionary.Builder(testDictionary).anagrams(input).build();
			if(anagramDict.numberOfWords() > 0) {
				System.out.println(input + " | " + anagramDict.getRandomWord());
				anagramFound = true;
			}
		}
		
		
		
	}

}
