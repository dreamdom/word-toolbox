package com.androiddom.rearrangedletters;

import java.io.File;

import com.androiddom.wordtoolbox.Dictionary;

public class MainClass {

	public static void main(String[] args) {
		
		System.out.println("Welcome to Rearranged Letters");
		
		Dictionary dictionary = new Dictionary.Builder(new File("../../../DictionaryData/dictionary.txt"))
				.filterPossesive()
				.filterProper()
				.minLength(4)
				.build();
		
		System.out.println(dictionary.getRandomWord());
		
		
	}

}
