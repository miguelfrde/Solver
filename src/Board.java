import java.util.Stack;


public class Board {

    char[][] blocks;
    private int N;
    private int priority = -1;
    char blockMoved = '?';
    int spacesMoved = 0;
    
    public Board(char[][] blocks) {
        // CONTRUCTS A BOARD FROM AN N BY N ARRAY OF BLOCKS
        // (WHERE BLOCKS [I][J] = BLOCK IN ROW I, COLUMN J)
        N = blocks.length;
        this.blocks = new char[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
    		    this.blocks[i][j] = blocks[i][j];
    }

    public Board(char[][] blocks, char block, int moves) {
        // CONTRUCTS A BOARD FROM AN N BY N ARRAY OF BLOCKS
        // (WHERE BLOCKS [I][J] = BLOCK IN ROW I, COLUMN J)
    	// AND BY A LETTER (REPRESENTING LAST BLOCK MOVED)
    	// AND BY MOVES (REPRESENTING THE TILES THE BLOCK WAS MOVED)
        this(blocks);
        this.blockMoved = block;
        this.spacesMoved = moves;
    }
    
    public int dimension() {
        return N;	//SIDE LENGTH OF THE BLOCKS MATRIX
    }
    
    
    public int priority() {
    	//IF PRIORITY HAS NOT BEEN CACHED GET IT, OTHERWISE RETURN CACHED VALUE
        if (priority != -1) return priority;
        if (isGoal()) return 0;
        int count = 0;
        char value;
        int i = N%2 == 0? N/2 - 1: N/2;		//ROW WERE MAIN BLOCK AND GOAL ARE
        
        for (int j = 0; j < N; j++) {
            value = blocks[i][j];			//VALUE FROM CURRENT CELL IN BLOCKS
            
            //IF EMPTY SPACE ("-") CONTINUE
            if (value == '-') continue;		
            if (value == 'X') {
            	j++;
            	continue;
            }
            count++;
        }
    	
        //CACHE THE VALUE AND RETURN IT
        priority = count;        
        return priority;
    }
    
    public boolean isGoal() {
    	int i = N%2 == 0? N/2 - 1 : N/2;	//ROW WERE MAIN BLOCK AND GOAL ARE
    	
    	return blocks[i][N-1] == 'X';		//IS MAIN BLOCK IN GOAL POSITION?
    }
    
    
    // DOES THIS BOARD EQUAL O?
    public boolean equals(Object o) {
    	//VALIDATE O IS A VALID BOARD
        if (!(o instanceof Board)) return false;
        Board b = (Board) o;
        if (dimension() != b.dimension()) return false;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (blocks[i][j] != b.blocks[i][j]) return false;
        return true;
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
                if (blocks2[i][j] == '-') {
                	//MOV ABAJO
                	if (i > 0 && blocks2[i-1][j] != '-') {
                	    char value = blocks2[i-1][j];
                	    if (i > 1 && value == blocks2[i-2][j]) {
                		    tempi = i - 1;
                		    while (tempi < N - 1 && blocks2[tempi+1][j] == '-') {
		                        int aux = tempi - 1;
		                	    while (aux > 0 && blocks2[aux-1][j] == value)
		                		    aux--;
		                	    swap(blocks2, aux, ++tempi, j, j); //move Hor
		                	    Board b = new Board(blocks2, value, tempi - i + 1);
		                        neighbors.push(b);
                		    }
                	    }   
		                
                	    blocks2 = cloneBlocks();
                    }
                	
                	//MOV DERECHA
                	if (j > 0 && blocks2[i][j-1] != '-') {
                	    char value = blocks2[i][j-1];
                	    if (j > 1 && value == blocks2[i][j-2]) {
                		    tempj = j - 1;
                		    while (tempj < N - 1 && blocks2[i][tempj+1] == '-') {
		                        int aux = tempj - 1;
		                        while (aux > 0 && blocks2[i][aux-1] == value)
		                		    aux--;
		                	    swap(blocks2, i, i, aux, ++tempj); //move Hor
		                	    Board b = new Board(blocks2, value, tempj - j + 1);
		                        neighbors.push(b);
                		    }
                	    }   
		                
                	    blocks2 = cloneBlocks();
                    }
                	                	
                	//MOV ARRIBA
                	if (i < N - 1 && blocks2[i+1][j] != '-') {
                		char value = blocks2[i+1][j];
                	    if (i < N - 2 && value == blocks2[i+2][j]) {
                		    tempi = i + 1;
                		    while (tempi > 0 && blocks2[tempi-1][j] == '-') {
		                        int aux = tempi + 1;
		                	    while (aux < N - 1 && blocks2[aux+1][j] == value)
		                		    aux++;
		                	    swap(blocks2, aux, --tempi, j, j); //move Hor
		                	    Board b = new Board(blocks2, value, tempi - i - 1);
		                        neighbors.push(b);
                		    }
                	    }   
		                blocks2 = cloneBlocks();
                    }
                	
                	//MOV IZQUIERDA
                	if (j < N - 1 && blocks2[i][j+1] != '-') {
                		char value = blocks2[i][j+1];
                	    if (j < N - 2 && value == blocks2[i][j+2]) {
                		    tempj = j + 1;
                		    while (tempj > 0 && blocks2[i][tempj-1] == '-') {
		                        int aux = tempj + 1;
		                	    while (aux < N - 1 && blocks2[i][aux+1] == value)
		                		    aux++;
		                	    swap(blocks2, i, i, aux, --tempj); //move Hor
		                	    Board b = new Board(blocks2, value, tempj -j - 1);
		                        neighbors.push(b);
                		    }
                	    }   
		                blocks2 = cloneBlocks();
                    }               
                }
            }
        }
        
        return neighbors;
    }

    public String toString() {
        // string representation of the board (in the output format specified below)
        String s = blockMoved + ", " + spacesMoved + "\n";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                s += blocks[i][j] + " ";

            s += "\n";
        }

        return s;
    }    
    
    public Action getAction() {
    	return new Action(blockMoved, spacesMoved);
    }
    
    //SWAPS TWO VALUES IN AN ARRAY
    private void swap(char[][] array, int row1, int row2, int col1, int col2) {
        char temp = array[row1][col1];
        array[row1][col1] = array[row2][col2];
        array[row2][col2] = temp;
    }
}
