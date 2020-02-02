package src.main.java;

import java.util.Scanner;

public class Player extends Character implements GameActions {
	public Player(int row, int col) {
		super(row, col);
	}
	
	void move(Board board) {
		Scanner cs = new Scanner(System.in);
		String choice;
		
		System.out.print("What action would you like the player to take?\n\n"
				+ "U: Move Up\n" + "D: Move Down\n" + "L: Move Left\n"
				+ "R: Move Right\n" + "W: Pellet\n" + "Move: ");
		
		choice = cs.nextLine();
		choice = choice.toUpperCase();
		System.out.println();
		
		if (board.isGameOver())
			cs.close();
		
		switch (choice)
		{
			case "U":
				if (board.isInBounds(mRow - 1, mCol))
					--mRow;
				else
					System.out.println("Player currently cannot move UP and loses turn.\n");
			break;
			case "D":
				if (board.isInBounds(mRow + 1, mCol))
					++mRow;
				else
					System.out.println("Player currently cannot move DOWN and loses turn.\n");
				break;
			case "L":
				if (board.isInBounds(mRow, mCol - 1))
					--mCol; 
				else
					System.out.println("Player currently cannot move LEFT and loses turn.\n");
				break;
			case "R":
				if (board.isInBounds(mRow, mCol + 1))
					++mCol;
				else
					System.out.println("Player currently cannot move RIGHT and loses turn.\n");
				break;
			case "W":
				board.addWeapon(mRow, mCol);
				break;
			default: System.out.println("Unrecognized action, please enter U, D, L, R, or W. Player loses turn.\n");
		}		

	}
	
	public String toString() {
		return "P";
	}
}
