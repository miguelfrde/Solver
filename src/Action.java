
public class Action {
	private char block;
	private int moves;
	
	/**
	 *  Initializes the action
	 *  @param block		A char representing the block to move
	 *  @param moves		An int containing how much the block moved
	 */
	public Action(char block, int moves) {
		this.block = block;
		this.moves = moves;
	}
	
	/**
	 *  Get the block to move
	 *  @return The char of the block to move.
	 */
	public char getBlock() {
		return block;
	}
	
	/**
	 *  Get the number of moves of the block
	 *  @return The number of moves that the block is moved
	 */
	public int getMoves() {
		return moves;
	}
	
	/**
	 *  Get the block expressed as a String
	 *  @return The String representing the block
	 */
	public String toString() {
		return "{" + block + ", " + moves + "}";
	}
}
