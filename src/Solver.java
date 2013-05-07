import datastructures.Stack;


public abstract class Solver {
	protected boolean solvable;
	protected int moves = -1;
	protected Stack<Action> movements;
	protected int expNodes = 0;
	protected long time = 0;
		
	/**
	 *  CHECKS WHETHER THE PUZZLE IS SOLVABLE OR NOT
	 *  @return true if the Board is solvable.
	 */
	protected abstract boolean isSolvable();

	/**
	 *  GETS THE NUMBER OF MOVES IN THE SOLUTION
	 *  @return The number of moves in the solution
	 */
	protected abstract int moves();
	
	/**
	 *  GETS THE AMOUNT OF EXPANDED NODES
	 *  @return The amount of expanded nodes
	 */
	protected abstract int expandedNodes();
	
	/**
	 *  GETS THE TIME TOOK BY THE ALGORITHM TO SOLVE THE PUZZLE
	 *  @return The running time of the Solver
	 */	
    protected abstract long getRunningTime();
    


	/**
	 *  GETS THE SOLUTION TO THE SOLVER
	 *  @return An iterable<Action> with the solution to the puzzle
	 */
    protected abstract Iterable<Action> solution();
}
