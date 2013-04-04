import java.util.Stack;


public class AStarSolver extends Solver{
	 private class SearchNode implements Comparable<SearchNode> {
    	private Board board;
    	private int moves;
    	private SearchNode previous;
    	private int priority;
    	
    	public SearchNode(Board b, int moves, SearchNode previous) {
    		this.board = b;
    		this.moves = moves;
    		this.previous = previous;
    		this.priority = b.priority() + this.moves;
    	}
    	
		public int compareTo(SearchNode sn) {
			if (priority < sn.priority) return -1;
    		else if (priority == sn.priority) return 0;
    		else return 1;
		}
    }
		 
	public AStarSolver(Board initial) {
    	// find a solution to the initial board (using the A* algorithm)
    	MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
    	movements = new Stack<Board>();
    	
    	pq.insert(new SearchNode(initial, 0, null));
    	
    	SearchNode sn1 = pq.delMin();
    	
    	while (!sn1.board.isGoal()) {
    		for (Board b: sn1.board.neighbors()) {
    			if (sn1.previous == null) 
    				pq.insert(new SearchNode(b, 1, sn1));
    			else if (!(b.equals(sn1.previous.board))) {
    				pq.insert(new SearchNode(b, sn1.moves + 1, sn1));
    			}
    		}
    		 		
    		sn1 = pq.delMin();
    	}
    	
    	SearchNode prev = sn1;
    		
    	while (prev != null) {
    		movements.push(prev.board);
    		moves++;
    		prev = prev.previous;
    	}
    	solvable = true;
    }
	
	public boolean isSolvable() {
		return solvable;
	}

	public int moves() {
		return moves;
	}

	public Iterable<Board> solution() {
		if (!solvable) return null;
    	return movements;
	}
}
