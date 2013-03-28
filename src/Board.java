import java.util.Stack;


public class Board {

    private int[][] blocks;
    private int N;
    private int hamming = -1;
    private int manhattan = -1;
    public Board(int[][] blocks) {
        // construct a board from an N-by-N array of blocks
        // (where blocks[i][j] = block in row i, column j)
        N = blocks.length;
        this.blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
    		    this.blocks[i][j] = blocks[i][j];
    }

    public int dimension() {
        return N;
    }
    
    public int hamming() {
        // number of blocks out of place
        if (hamming != -1) return hamming;

        int count = 0;
        for (int i = 0; i < N; i++) 
            for (int j = 0; j < N; j++)
                if (blocks[i][j] != (N * i) + j + 1
                    && blocks[i][j] != 0) count++;

        hamming = count;
        return hamming;
    }
    
    public int manhattan() {
        // sum of Manhattan distances between blocks and goal
        if (manhattan != -1) return manhattan;

        int count = 0, value;
        int tempi, tempj;
        for (int i = 0; i < N; i++) 
            for (int j = 0; j < N; j++) {
                value = blocks[i][j];
                if (value == 0) continue;

                tempi = (value - 1) / N;
                tempj = value - 1 - (tempi * N);
                count += Math.abs(tempi - i) + Math.abs(tempj - j);
            }
    	
        manhattan = count;
        return manhattan;
    }
    
    public boolean isGoal() {
        int[][] goal = new int[N][N];
        for (int i = 0; i < N; i++) 
            for (int j = 0; j < N; j++)
                goal[i][j] = (N * i) + j + 1;

        goal[N-1][N-1] = 0;
        return equals(new Board(goal));
    }

    private int[][] cloneBlocks() {
        int[][] array = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                array[i][j] = blocks[i][j];

        return array;
    }
    
    public Board twin() {
        // a board obtained by exchanging two adjacent blocks in the same row
        int[][] twinBlocks = cloneBlocks();
        boolean swapped = false;
        
        for (int i = 0; i < N; i++) {
        	if (swapped) break;
            for (int j = 0; j < N - 1; j++)
                if (twinBlocks[i][j] != 0 && twinBlocks[i][j + 1] != 0) {
                    swap(twinBlocks, i, i, j, j + 1);
                    swapped = true;
                    break;
                }
        }
        return new Board(twinBlocks);
    }
    
    public boolean equals(Object y) {
        // does this board equal y?
        if (!(y instanceof Board)) return false;
        Board b = (Board) y;
        if (dimension() != b.dimension()) return false;
        boolean flag = true;

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (blocks[i][j] != b.blocks[i][j]) flag = false;

        return flag;
    }
    
    public Iterable<Board> neighbors() {
        // all neighboring boards
        Stack<Board> neighbors = new Stack<Board>();

        int bi = 0, bj = 0;

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (blocks[i][j] == 0) { 
                    bi = i;
                    bj = j;
                }

        int[][] blocks2 = cloneBlocks();
      
        if (bi < N - 1) {
            swap(blocks2, bi, bi + 1, bj, bj);
            Board b = new Board(blocks2);
            neighbors.push(b);    		
            blocks2 = cloneBlocks();
        }

        if (bi > 0) {
            swap(blocks2, bi, bi - 1, bj, bj);
            Board b = new Board(blocks2);
            neighbors.push(b);
    	    blocks2 = cloneBlocks();
        }

        if (bj < N - 1) {
    	    swap(blocks2, bi, bi, bj, bj + 1);
    	    Board b = new Board(blocks2);
    	    neighbors.push(b);
    	    blocks2 = cloneBlocks();
    	}

        if (bj > 0) {
    	    swap(blocks2, bi, bi, bj, bj - 1);
    	    Board b = new Board(blocks2);
    	    neighbors.push(b);
    	}

        return neighbors;
    }

    public String toString() {
        // string representation of the board (in the output format specified below)
        String s = N + "\n";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                s += blocks[i][j] + " ";

            s += "\n";
        }

        return s;
    }

    //SWAPS TWO VALUES IN AN ARRAY
    private void swap(int[][] array, int row1, int row2, int col1, int col2) {
        int temp = array[row1][col1];
        array[row1][col1] = array[row2][col2];
        array[row2][col2] = temp;
    }	
}
