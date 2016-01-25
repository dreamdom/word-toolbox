package com.androiddom.rearrangedletters;

import java.io.File;

import com.androiddom.wordtoolbox.MutibleDictionary;

public class MainClass {

	public static void main(String[] args) {
		
		System.out.println("Welcome to Rearranged Letters");
		
		MutibleDictionary dictionary = new MutibleDictionary.Builder(new File("../../../DictionaryData/dictionary.txt"))
				.filterPossesive()
				.filterProper()
				.minLength(4)
				.build();
		
		System.out.println(dictionary.getRandomWord());
		
		
	}

}
