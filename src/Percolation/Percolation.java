package Percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    private int n;
    private WeightedQuickUnionUF top;
    private WeightedQuickUnionUF bottom;
    private byte[][] grid;
    private int virtualTop;
    private int virtualBottom;
    private int num;


    public Percolation(int n)    {
        if (n < 1) {
            throw new IllegalArgumentException("N must "
                    + "be an integer greater than or equal to 1");
        }
        this.num = 0;
        this.n = n+1;

        grid = new byte[n][n];
        top = new WeightedQuickUnionUF(n * n + 2);
        bottom = new WeightedQuickUnionUF(n * n + 1);
        virtualTop = 0;
        virtualBottom = (n * n + 1);

    }

    private void validate(int i, int j) {
        if ((i < 1) || (i > n) || (j < 1) || (j > n)) {
            throw new java.lang.IllegalArgumentException ("i and j "
                    + "must be in the range of 1 - n");
        }
    }

    public    void open(int row, int col) {
      //  validate(row, col);
        if(isOpen(row, col)){
            return;
        }
        grid[row - 1][col - 1] = 1;
        num++;
        int i = row;
        int j = col;

        if (isOpen(i - 1, j)) {
            union(i, j, i - 1, j);
        }
        if (isOpen(i + 1, j)) {
            union(i, j, i + 1, j);
        }
        if (isOpen(i, j - 1)) {
            union(i, j, i, j - 1);
        }
        if (isOpen(i, j + 1)) {
            union(i, j, i, j + 1);
        }
        if (i == 1) {
            union(top, virtualTop, i, j);
            union(bottom, virtualTop, i, j);
        }
        if (i == n) {
            union(top, virtualBottom, i, j);
        }
    }


    public boolean isOpen(int row, int col) {
 //       validate(row, col);

        return grid[row][col]>0;
    }



    public boolean isFull(int row, int col) {
       // validate(row, col);
//        return (isConnected(top, virtualTop, row, col))
//                && (isConnected(bottom, virtualTop, row, col));

        if (isOpen(row, col)) {
            int fieldIndex = getFieldIndexInQuickUnionStructure(row, col);
            return quickUnionStructureForIsFull.connected(virtualTop, fieldIndex);
        }
        return false;

    }

    private boolean isConnected(WeightedQuickUnionUF uf, int p, int row, int col) {
        int q = xyTo1D(row, col);
        return uf.connected(p, q);
    }

    public     int numberOfOpenSites()  {

        return num; }

    public boolean percolates() {
        return top.connected(virtualTop, virtualBottom);
    }



    private int xyTo1D(int row, int col) {
      //  validate(row, col);
        return (row - 1) * n + col;
    }

    private void union(int iP, int jP, int iQ, int jQ) {
        int p = xyTo1D(iP, jP);
        int q = xyTo1D(iQ, jQ);
        bottom.union(p, q);
        top.union(p, q);
    }

    private void union(WeightedQuickUnionUF uf, int p, int iQ, int jQ) {
        int q = xyTo1D(iQ, jQ);
        uf.union(p, q);
    }

    public static void main(String[] args)  {



    }
}

