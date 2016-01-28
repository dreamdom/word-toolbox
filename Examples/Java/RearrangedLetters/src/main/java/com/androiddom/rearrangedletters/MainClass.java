package com.androiddom.rearrangedletters;

import java.io.File;
import java.util.Scanner;

import com.androiddom.wordtoolbox.MutibleDictionary;

public class MainClass {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Welcome to Rearranged Letters");
		
		MutibleDictionary dictionary = new MutibleDictionary.Builder(new File("../../../DictionaryData/dictionary.txt"))
				.filterPossesive()
				.filterProper()
				.minLength(4)
				.build();
		
		// Ask the user for a game level
		System.out.println();
		System.out.print("Enter a game level:");
		
		int gameLevel = scanner.nextInt();
		
		Game game = new Game(dictionary, gameLevel);
		
		System.out.println();
		
		// Start a new round
		Round curRound = game.newRound();
		
		System.out.println(curRound.getRearrangedWord());
		
		scanner.nextLine();
		while(!curRound.isRoundOver()) {
			scanner.nextLine();
			curRound.useHint();
			System.out.print(curRound.getRearrangedWord());
		}
		
		scanner.close();
		
	}

}
