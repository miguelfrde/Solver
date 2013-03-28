import java.util.Stack;

public abstract class Solver {
	protected boolean solvable;
	protected int moves = -1;
	protected Stack<Board> movements;
	
	protected abstract boolean isSolvable();
    
    protected abstract int moves();
    
    protected abstract Iterable<Board> solution();
}
