package com.androiddom.rearrangedletters;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.androiddom.wordtoolbox.MutibleDictionary;
import com.androiddom.wordtoolbox.util.StringUtils;

public class MainClass {

	private static final String GET_NEXT_GAME_WORD = "/r";
	private static final String GET_HINT = "/h";

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

		scanner.nextLine();
		while (!curRound.isRoundOver()) {
			printGameWord(curRound.getRearrangedWord());
			String input = scanner.nextLine();
			parseInput(input, curRound);
		}

		scanner.close();

	}

	/**
	 * A method to parse input from the command line.
	 * 
	 * @param input
	 *            The input that was read.
	 * @param round
	 *            The current round object.
	 */
	private static void parseInput(String input, Round round) {
		// Check for special inputs
		if (input.equals(GET_NEXT_GAME_WORD)) {
			round.nextGameWord();
		} else if (input.equals(GET_HINT)) {
			round.useHint();
		} else if (round.setGameWord(input)) {
			// Print out a message if the right word was entered
			System.out.println("winner!");
		}
	}

	/**
	 * A private helper method to print out the game word in a nicely formatted
	 * fashion.
	 * 
	 * @param gameWord
	 *            The word to print.
	 */
	private static void printGameWord(String gameWord) {
		// build up the lines
		StringBuilder lineBuilder = new StringBuilder();
		for (int i = 0; i < gameWord.length() * 2 - 1; i++) {
			lineBuilder.append("-");
		}

		// build up the formatted game word
		StringBuilder gameWordFormatted = new StringBuilder();
		List<Character> gameWordCharacters = StringUtils.stringToCharacterList(gameWord.toUpperCase(Locale.US));
		for (int i = 0; i < gameWordCharacters.size(); i++) {
			if (i != 0) {
				gameWordFormatted.append(" ");
			}
			gameWordFormatted.append(gameWordCharacters.get(i));
		}

		// print out the formatted game word
		System.out.println();
		System.out.println(gameWordFormatted.toString());
		System.out.println(lineBuilder.toString());

	}

}
