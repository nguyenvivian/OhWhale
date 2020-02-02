
import java.util.ArrayList;
import java.util.List;

public class Board {
	private Player mPlayer;
	private List<Enemy> mEnemyList;
	private List<Weapon> mWeaponList;
	private int mRowSize;
	private int mColSize;
	
	public Board(int rowSize, int colSize, int numEnemy) {
		mRowSize = rowSize;
		mColSize = colSize;
		mEnemyList = new ArrayList<Enemy>(numEnemy);
		mPlayer = new Player(mRowSize - 1, mColSize - 1);
		mWeaponList = new ArrayList<Weapon>(mRowSize * mColSize);
	
		int row = 0,
			col = 0;
		
		for (int i = 0; i < numEnemy; ++i) {
			if (col < mColSize) {
				mEnemyList.add(new Enemy(row, col));
				++col;
			}
			else {
				++row;
				col = 0;
				mEnemyList.add(new Enemy(row, col));
				++col;
			}
		}
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return mPlayer;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		mPlayer = player;
	}

	/**
	 * @return the enemyList
	 */
	public List<Enemy> getEnemyList() {
		return mEnemyList;
	}

	/**
	 * @param enemyList the enemyList to set
	 */
	public void setEnemyList(List<Enemy> enemyList) {
		mEnemyList = enemyList;
	}

	/**
	 * @return the weaponList
	 */
	public List<Weapon> getWeaponList() {
		return mWeaponList;
	}

	/**
	 * @param weaponList the weaponList to set
	 */
	public void setWeaponList(List<Weapon> weaponList) {
		mWeaponList = weaponList;
	}

	/**
	 * @return the rowSize
	 */
	public int getRowSize() {
		return mRowSize;
	}

	/**
	 * @param rowSize the rowSize to set
	 */
	public void setRowSize(int rowSize) {
		mRowSize = rowSize;
	}

	/**
	 * @return the colSize
	 */
	public int getColSize() {
		return mColSize;
	}

	/**
	 * @param colSize the colSize to set
	 */
	public void setColSize(int colSize) {
		mColSize = colSize;
	}
	
	/**
	 * @param row = row to check
	 * @param col = column to check
	 */
	public boolean isInBounds(int row, int col) {
		return  ((row >= 0 && row <= mRowSize) && (col >= 0 && col <= mColSize));
	}
	
	/**
	 * @return if player still exists (not null)
	 */
	public boolean isPlayerAlive() {
		return (mPlayer != null);
	}
	
	/**
	 * @return whether game over - (player no longer exists or all enemies eliminated
	 */
	public boolean isGameOver() {
		return (!this.isPlayerAlive() || mEnemyList.isEmpty());
	}
	
	/**
	 * @param row = row to set weapon in
	 * @param col = column to set weapon in
	 */
	public void addWeapon(int row, int col) {
		if (this.isInBounds(row, col)) {
			mWeaponList.add(new Weapon(row, col));
		}
	}
	
	public void moveAllCharacters() {
		mPlayer.move(this);
		
		List<Enemy> enemyRemoval = new ArrayList<>();
		List<Weapon> weaponRemoval = new ArrayList<>();
		
		for (Enemy e : mEnemyList) {
			e.move(this);
			
			if (this.isPlayerAlive() && mPlayer.mCol == e.mCol 
				&& mPlayer.mRow == e.mRow) {
				mPlayer = null;
			}
			else {
				for (Weapon w : mWeaponList) {
					if (w.mCol == e.mCol && w.mRow == e.mRow) {
						enemyRemoval.add(e);
						weaponRemoval.add(w);
					}
				}
			}
		}
		
		if (!enemyRemoval.isEmpty() && !weaponRemoval.isEmpty()) {
			mEnemyList.removeAll(enemyRemoval);
			mWeaponList.removeAll(weaponRemoval);
		}
	}
	
	public String toString() {
		StringBuilder board = new StringBuilder();

		for (int row = 0; row < mRowSize; ++row)
		{
			for (int col = 0; col < mColSize; ++col)
			{
				Weapon tempWeapon = new Weapon(row, col);
				Enemy tempEnemy = new Enemy(row, col);

				if (mPlayer != null && mPlayer.mCol == col && mPlayer.mRow == row)
					board.append(mPlayer.toString());
				else if (mWeaponList.contains(tempWeapon))
					board.append(tempWeapon.toString());
				else if (mEnemyList.contains(tempEnemy))
				{
					int rodentCount = 0;

					for (Enemy e : mEnemyList)
					{
						if (tempEnemy.mCol == e.mCol && tempEnemy.mRow == e.mRow)
							++rodentCount;
					}

					if (rodentCount == 1)
						board.append(tempEnemy.toString());
					else
						board.append(rodentCount);
				}
				else
					board.append(".");
			}

			board.append("\n");
		}

		board.append("\n").append(mEnemyList.size()).append(" enemies left to eliminate.\n");
		return board.toString();
	}
}
