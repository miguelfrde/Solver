
public class Action {
	private char block;
	private int moves;
	
	public Action(char block, int moves) {
		this.block = block;
		this.moves = moves;
	}
	
	public char getBlock() {
		return block;
	}
	
	public int getMoves() {
		return moves;
	}
	
	public String toString() {
		return "{" + block + ", " + moves + "}";
	}
}
