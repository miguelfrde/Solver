import datastructures.Stack;


public abstract class Solver {
	protected boolean solvable;
	protected int moves = -1;
	protected Stack<Action> movements;
	
	protected abstract boolean isSolvable();
    
    protected abstract int moves();
    
    protected abstract Iterable<Action> solution();
}
