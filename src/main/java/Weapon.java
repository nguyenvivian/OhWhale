public class Weapon extends Character implements GameActions {
	public Weapon(int row, int col) {
		super(row, col);
	}
	
	void move(Board board) {}
	
	public String toString() {
		return "+";
	}
}
