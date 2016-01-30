package com.androiddom.rearrangedletters;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.androiddom.wordtoolbox.MutibleDictionary;
import com.androiddom.wordtoolbox.util.StringUtils;

/**
 * The class used to run, print output, and get input for the RearrangedLetters
 * game.
 *
 */
public class MainClass {

	// Constants
	private static final String GET_NEXT_GAME_WORD = "/r";
	private static final String GET_HINT = "/h";
	private static final String EXIT = "/exit";

	private static boolean exitGame = false;

	/**
	 * Main method. Asks for a game level, and starts the game.
	 * 
	 * @param args
	 *            unused.
	 */
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

		int gameLevel = Integer.parseInt(scanner.nextLine());

		Game game = new Game(dictionary, gameLevel);

		System.out.println();
		printHelpMessage();
		System.out.println();

		// Start a new round
		while (!exitGame) {
			Round curRound = game.newRound();
			
			while (!curRound.isRoundOver() && !exitGame) {
				printGameWord(curRound.getRearrangedWord());
				String input = scanner.nextLine();
				parseInput(input, curRound);
			}

			System.out.println("The word was '" + curRound.getWord() + "'");
			
			// If they aren't already exiting, ask the user if they would like to play again
			if(!exitGame) {
				System.out.println("press enter to play again, or type'" + EXIT + "' to exit.");
				String input = scanner.nextLine();
				if(input.equals(EXIT)) {
					exitGame = true;
				}
			}
		}

		System.out.println();
		System.out.println("goodbye. Thanks for playing.");

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
		} else if(input.equals(EXIT)) {
			exitGame = true;
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

	/**
	 * A private helper method used to print out a help message.
	 */
	private static void printHelpMessage() {
		System.out.println("to play, type in your guess for the original word, and press enter.");
		System.out.println(
				"type '" + GET_NEXT_GAME_WORD + "' to rearrange the letters again. Hints will stay in the same place.");
		System.out.println("type '" + GET_HINT
				+ "' to get a hint. Each hint puts a letter in the correct spot starting at the beginning of the word.");
		System.out.println("type '" + EXIT + "' to exit the application.");

	}

}
