import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BFSSolver extends Solver{
	private class SearchNode{
	    private Board board;
	    private int moves;
	   	private SearchNode previous;
	    
	    public SearchNode(Board b, int moves, SearchNode previous) {
	    	this.board = b;
	    	this.moves = moves;
	    	this.previous = previous;
	   	}
	}
			 
    // FIND A SOLUTION TO THE INITIAL BOARD USING BFS ALGORITHM
	public BFSSolver(Board initial) {
    	Queue<SearchNode> queue = new Queue<SearchNode>();
    	Stack<Board> previous = new Stack<Board>();
    	
    	queue.enqueue(new SearchNode(initial, 0, null));    	
    	SearchNode sn = queue.dequeue();
    	previous.push(sn.board);
    	
    	while (!sn.board.isGoal()) {
    		for (Board b: sn.board.neighbors()) {
    			if (!previous.contains(b)) {
    				queue.enqueue(new SearchNode(b, sn.moves + 1, sn));
    				previous.push(b);
    			}
    		}
    		
    		sn = queue.dequeue();
    	} 
    	
    	SearchNode prev = sn;
    	movements = new Stack<Action>();
    	
    	while (prev != null) {
    		movements.push(prev.board.getAction());
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
        BFSSolver solver = new BFSSolver(initial);

        // print solution to standard output
        System.out.println("Minimum number of moves = " + solver.moves());
        
        for (Action a: solver.solution())
        	System.out.println(a);
	}
}
