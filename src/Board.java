import java.util.Stack;


public class Board {

    private char[][] blocks;
    private int N;
    private int priority = -1;
    
    public Board(char[][] blocks) {
        // CONTRUSCT A BOARD FROM AND N BY N ARRAY OF BLOCKS
        // (WHERE BLOCKS [I][J] = BLOCK IN ROW I, COLUMN J)
        N = blocks.length;
        this.blocks = new char[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
    		    this.blocks[i][j] = blocks[i][j];
    }

    public int dimension() {
        return N;	//SIDE LENGTH OF THE BLOCKS MATRIX
    }
    
    
    public int priority() {
        //IF PRIORITY HAS NOT BEEN CACHED GET IT, OTHERWISE RETURN CACHED VALUE
        if (priority != -1) return priority;

        int count = 0;						//AUXILIARY VARIABLE FOR THE PRIORITY
        char value;  						//VALUE FROM EACH CELL IN THE BLOCKS
        boolean borderOfMB = false; 		//ALREADY PASSED FULL MAIN BLOCK?
        int i = N%2 == 0? N/2 : N/2 - 1;	//ROW WERE MAIN BLOCK AND GOAL ARE
        
        for (int j = 0; j < N; j++) {
            value = blocks[i][j];			//VALUE FROM CURRENT CELL IN BLOCKS
            if (value == '-') continue;		//IF EMPTY SPACE ("-") CONTINUE
            
            //IF VALUE IS THE BORDER OF THE MAIN BLOCK ADD THE DISTANCE
            //BETWEEN IT AND THE GOAL ELSE ADD 1 FOR EVERY BLOCK IN 
            //FRONT OF THE MAIN BLOCK
            if (value != 'X' && borderOfMB)	count++;
            else if (value == 'X' && j < N - 1 && blocks[i][j + 1] != 'X') {
            	count += N - j;
            	borderOfMB = true;
            }
        }
    	
        //CACHE THE VALUE AND RETURN IT
        priority = count;        
        return priority;
    }
    
    public boolean isGoal() {
    	int i = N%2 == 0? N/2 : N/2 - 1;	//ROW WERE MAIN BLOCK AND GOAL ARE
    	
    	return blocks[i][-1] == 'X';		//IS MAIN BLOCK IN GOAL POSITION?
    }
    
    
    // DOES THIS BOARD EQUAL O?
    public boolean equals(Object o) {
    	//VALIDATE O IS A VALID BOARD
        if (!(o instanceof Board)) return false;
        Board b = (Board) o;
        if (dimension() != b.dimension()) return false;
       
        boolean flag = true;	//AUXILIARY VARIABLE FOR MISMATCH

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (blocks[i][j] != b.blocks[i][j]) flag = false;

        return flag;
    }
    
    private char[][] cloneBlocks() {
        char[][] array = new char[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                array[i][j] = blocks[i][j];

        return array;
    }
    
    public Iterable<Board> neighbors() {
        // all neighboring boards
        Stack<Board> neighbors = new Stack<Board>();

        int tempi = 0, tempj = 0;
        char[][] blocks2 = cloneBlocks();
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] == '-') { 
                   if (blocks[i][j-1] != '-') {
                	   char value = blocks[i][j-1];
                	   tempj = j-2;
                	   while (blocks[i][tempj] == value)
                		   tempj--;
                	   swap(blocks2, i, i, j, j-1);
                	   swap(blocks2, i, i, j-1, tempj+1);
                	   Board b = new Board(blocks2);
                       neighbors.push(b);
                       blocks2 = cloneBlocks();
                   }              
                   
                   if (blocks[i][j+1] != '-') {
                	   char value = blocks[i][j+1];
                	   tempj = j+2;
                	   while (blocks[i][tempj] == value)
                		   tempj++;
                	   swap(blocks2, i, i, j, j+1);
                	   swap(blocks2, i, i, j+1, tempj-1);
                	   Board b = new Board(blocks2);
                       neighbors.push(b);
                       blocks2 = cloneBlocks();
                   }  
                   
                   if (blocks[i-1][j] != '-') {
                	   char value = blocks[i-1][j];
                	   tempi = i-2;
                	   while (blocks[tempi][j] == value)
                		   tempi--;
                	   swap(blocks2, i, i-1, j, j);
                	   swap(blocks2, i-1, tempi+1, j, j);
                	   Board b = new Board(blocks2);
                       neighbors.push(b);
                       blocks2 = cloneBlocks();
                   }  
                   
                   if (blocks[i+1][j] != '-') {
                	   char value = blocks[i+1][j];
                	   tempi = i+2;
                	   while (blocks[tempi][j] == value)
                		   tempi++;
                	   swap(blocks2, i, i+1, j, j);
                	   swap(blocks2, i+1, tempi-1, j, j);
                	   Board b = new Board(blocks2);
                       neighbors.push(b);
                       blocks2 = cloneBlocks();
                   } 
                }
            }
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
    private void swap(char[][] array, int row1, int row2, int col1, int col2) {
        char temp = array[row1][col1];
        array[row1][col1] = array[row2][col2];
        array[row2][col2] = temp;
    }	
}
