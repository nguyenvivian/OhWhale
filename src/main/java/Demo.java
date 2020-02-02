package src.main.java;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Demo {

	public static void main(String[] args) {
		int numEnemies, rowSize, colSize, maxEnemies;
		Scanner consoleScanner = new Scanner(System.in);

		System.out.println("Welcome to Oh Whale!");
		rowSize = 7;
		colSize = 7;
		maxEnemies = (rowSize * colSize) - 1;
		numEnemies = validateInput(consoleScanner, "How many oil spills have infected the ocean?", 1, maxEnemies,
				"Please enter a number of spills > 0 and less than or equal to " + maxEnemies);

		Board gameBoard = new Board(rowSize, colSize, numEnemies);
		System.out.println("\n" + gameBoard + "\n");
		do {
			gameBoard.moveAllCharacters();
			System.out.println(gameBoard);

		} while (!gameBoard.isGameOver());

		if (gameBoard.isPlayerAlive())
			System.out.println("You win! Congratulations elminating all of your enemies.");
		else
			System.err.println("Player has died.");
	}
	
	public static int validateInput(String in, String message, int min, int max, String errorMessage) {
		int input = 0;
		boolean inputError;
		do {
			try {
				inputError = false;
				System.out.print(message + " ");
				input = Integer.parseInt(in);
				if (input < min || input > max)
					inputError = true;
			} catch (InputMismatchException e) {
				inputError = true;
			}
			if (inputError)
				System.err.println(errorMessage + "\n");

		} while (inputError);
		return input;
	}

	/**
	 * Validates user input to prompt them with a message, read an int from the consoleScanner, validate that it is > min.
	 * If the input is valid, it is returned, otherwise a user is prompted again in a loop until valid input
	 * is entered.  Reuses existing code from <code>isValidInput</code> giving Integer.MAX_VALUE for the maximum value (e.g. no max).
	 *
	 * @param consoleScanner Used to read user input from the console.
	 * @param message The message to display to the user.
	 * @param min The minimum value allowed.
	 * @param errorMessage The error message to display to the user if input is invalid.
	 * @return The validated input from the user.
	 */
	public static int validateInputMin(Scanner consoleScanner, String message, int min, String errorMessage) {
		return validateInput(consoleScanner, message, min, Integer.MAX_VALUE, errorMessage);
	}
}

