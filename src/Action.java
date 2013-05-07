
public class Action {
	private char block;
	private int moves;
	
	/**
	 *  INITILIAZES THE ACTION
	 *  @param block		A char representing the block to move
	 *  @param moves		An int containing how much the block moved
	 */
	public Action(char block, int moves) {
		this.block = block;
		this.moves = moves;
	}
	
	/**
	 *  GETS THE BLOCK TO MOVE
	 *  @return The char of the block to move.
	 */
	public char getBlock() {
		return block;
	}
	
	/**
	 *  GETS THE NUMBER OF MOVES OF THE BLOCK
	 *  @return The number of moves that the block is moved
	 */
	public int getMoves() {
		return moves;
	}
	
	/**
	 *  GETS THE BLOCK EXPRESSED AS A STRING
	 *  @return The String representing the block
	 */
	public String toString() {
		return "{" + block + ", " + moves + "}";
	}
}
