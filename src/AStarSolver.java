import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
		/*MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
		Stack<Board> explored = new Stack<Board>();

		pq.insert(new SearchNode(initial, 0, null));
		SearchNode sn = null;
		while (!pq.isEmpty()) {
			sn = pq.delMin();
			explored.push(sn.board);
			if (sn.board.isGoal()) break;
			for (Board b: sn.board.neighbors())
				if (!explored.contains(b))
					pq.insert(new SearchNode(b, sn.moves + 1, sn));
		}*/
		MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
    	Stack<Board> explored = new Stack<Board>();
    	
    	pq.insert(new SearchNode(initial, 0, null));
    	
    	SearchNode sn = pq.delMin();
    	explored.push(sn.board);
    	int count = 0;
    	
    	do {
    		for (Board b: sn.board.neighbors()) {
    			if (!explored.contains(b)) {
    				pq.insert(new SearchNode(b, sn.moves + 1, sn));
    				explored.push(b);
    				count++;
    			}
    		}
    		sn = pq.delMin();
    		if (sn.board.isGoal()) break;
    	} while (!pq.isEmpty());
    	
    	if (pq.isEmpty()) {
    		solvable = false;
    		return;
    	}
    	
    	System.out.println(count);
    	SearchNode prev = sn;
    	movements = new Stack<Action>();
    		
    	while (prev != null) {
    		movements.push(prev.board.getAction());
    		moves++;
    		//System.out.println(prev.board);
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

	public Iterable<Action> solution() {
		if (!solvable) return null;		
    	return movements;
	}
	
	public static void main(String[] args) {
		 // create initial board from file
		File file = new File("C:\\Users\\JORGE\\workspace\\Solver\\puzzles\\" + args[0]);
		
		Scanner in = null;
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		in.next();
		
        int N = 6;
        char[][] blocks = new char[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.next().charAt(0);
        

        Board initial = new Board(blocks);
        
        // solve the puzzle
        AStarSolver solver = new AStarSolver(initial);

        // print solution to standard output       
        System.out.println("Minimum number of moves = " + solver.moves());
        
        for (Action a : solver.solution())
        	System.out.println(a);             
	}
}
