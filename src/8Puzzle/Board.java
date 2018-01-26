
public class Board {
    private int dimension;
    private int hamming;
    private int manhattan;
    private int [] cells;
    private int empty;


    public Board(int[][] blocks) {

        this.dimension = blocks.length;
        this.cells = new int[dimension * dimension];
        
     for(int i = 0; i < dimension; i ++) {
         for (int j = 0; j < dimension; j ++) {
             if(blocks[i][j] == 0) {
                 empty = i * dimension + j;
             }
             cells[i * dimension + j] = blocks[i][j];
         }
     }

     this.hamming = countHamming(cells);
     this.manhattan = countManhattan(cells);

    }

    
    
    public int dimension() {
        return dimension;
    }
    public int hamming() {
        return hamming;
    }
    public int manhattan() {
        return manhattan;
    }

    public int countHamming(int [] cells) {

        int hamming = 0;

        for(int i = 0; i < cells.length - 1; i ++) {

            if(!(cells[i] == i+1) && !(cells[i] == 0)) {
                hamming ++;
            }
        }
        return hamming;
    }

    public int countManhattan(int [] cells) {
        int manhattan = 0;
        for(int i = 0; i < cells.length - 1; i ++) {
            if(!(cells[i] == i+1) && !(cells[i] == 0)) {
                manhattan += Math.abs(cells[i] - (i + 1));
            }

        }
        return manhattan;
    }

    
    public boolean isGoal() {
        return this.hamming == 0;
    }
    public Board twin() {
        return this.equals()
    }

    public boolean equals(Object y) {
       if ((y == null) || (this.getClass().equals(y.getClass()))) {
           return false;
        }

        return  ((cells.equals((((Board) y).cells))));


    }

    public Iterable<Board> neighbors()  {

    }    // all neighboring boards

    public String toString()   {
        StringBuilder s = new StringBuilder();
        s.append(dimension + "\n");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                s.append(String.format("%2d ", cells[i][j]));
            }
            s.append("\n");
        }
        return s.toString();


    }

    public static void main(String[] args) {

    }
}