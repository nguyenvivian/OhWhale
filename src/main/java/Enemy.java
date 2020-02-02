import java.util.Random;

public class Enemy extends Character implements GameActions {
	public Enemy(int row, int col) {
		super(row, col);
	}
	
	public void move(Board board) {
		boolean spotFound = false;
		Random rng = new Random();
		
		int newRow = 0,
			newCol = 0;
		
		do {
			newRow = rng.nextInt((mRow + 1) - (mRow - 1) + 1) + (mRow - 1);
			newCol = rng.nextInt((mCol + 1) - (mCol - 1) + 1) + (mCol - 1);
			
			if (board.isInBounds(newRow, newCol)) {
				mRow = newRow;
				mCol = newCol;
				spotFound = true;
			}
		} while (!spotFound);
	}
	
	public String toString() {
		return "E";
	}
}
