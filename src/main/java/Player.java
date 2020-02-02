
public class Player extends Character implements GameActions {
	public Player(int row, int col) {
		super(row, col);
	}
	
	void move(Board board, String choice) {
//		if (board.isGameOver())
//			cs.close();
		
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
			case "S":
				board.addWeapon(mRow, mCol);
				break;
			default: System.out.println("Unrecognized action, please enter U, D, L, R, or W. Player loses turn.\n");
		}		

	}
	
	public String toString() {
		return "🐳";
	}
}
