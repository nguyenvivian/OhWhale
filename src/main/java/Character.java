package src.main.java;
public abstract class Character {
	protected int mRow;
	protected int mCol;
	
	protected Character(int row, int col) {
		mRow = row;
		mCol = col;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return mRow;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		mRow = row;
	}

	/**
	 * @return the col
	 */
	public int getCol() {
		return mCol;
	}

	/**
	 * @param col the col to set
	 */
	public void setCol(int col) {
		mCol = col;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mCol;
		result = prime * result + mRow;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Character other = (Character) obj;
		if (mCol != other.mCol)
			return false;
		if (mRow != other.mRow)
			return false;
		return true;
	}
	
	
}
