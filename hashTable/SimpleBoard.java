/* SimpleBoard.java */

/**
 *  Simple class that implements an 8x8 game board with three possible values
 *  for each cell:  0, 1 or 2.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class SimpleBoard {
  private final static int DIMENSION = 8;
  private int[][] grid;
  
  /**
   *  Invariants:  
   *  (1) grid.length == DIMENSION.
   *  (2) for all 0 <= i < DIMENSION, grid[i].length == DIMENSION.
   *  (3) for all 0 <= i, j < DIMENSION, grid[i][j] >= 0 and grid[i][j] <= 2.
   **/

  /**
   *  Construct a new board in which all cells are zero.
   */

  public SimpleBoard() {
    grid = new int[DIMENSION][DIMENSION];
  }

  /**
   *  Set the cell (x, y) in the board to the given value mod 3.
   *  @param value to which the element should be set (normally 0, 1, or 2).
   *  @param x is the x-index.
   *  @param y is the y-index.
   *  @exception ArrayIndexOutOfBoundsException is thrown if an invalid index
   *  is given.
   **/

  public void setElementAt(int x, int y, int value) {
    grid[x][y] = value % 3;
    if (grid[x][y] < 0) {
      grid[x][y] = grid[x][y] + 3;
    }
  }

  /**
   *  Get the valued stored in cell (x, y).
   *  @param x is the x-index.
   *  @param y is the y-index.
   *  @return the stored value (between 0 and 2).
   *  @exception ArrayIndexOutOfBoundsException is thrown if an invalid index
   *  is given.
   */

  public int elementAt(int x, int y) {
    return grid[x][y];
  }

  /**
   *  Returns true if "this" SimpleBoard and "board" have identical values in
   *    every cell.
   *  @param board is the second SimpleBoard.
   *  @return true if the boards are equal, false otherwise.
   */

  public boolean equals(Object board) {
    // Be sure to return false
    // (rather than throwing a ClassCastException) if "board" is not
    // a SimpleBoard.
    if(board.getClass().getName() != "SimpleBoard") { //worth remembering this critical process for checking.
      return false; 
    }

    for(int i=0; i<8; i++) {
      for(int j=0; j<8; j++) {
        if(this.grid[i][j] != ((SimpleBoard) board).elementAt(i, j)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   *  Returns a hash code for this SimpleBoard.
   *  @return a number between Integer.MIN_VALUE and Integer.MAX_VALUE.
   */

  public int hashCode() {
    int hashcode = 0;

    for(int[] a: this.grid) {
      for(int i: a) {
        hashcode = (hashcode + i)*3;
      }
    }
    hashcode = hashcode/3;

    return hashcode;
  }

  /**
   *  Print out this SimpleBoard
   **/

  public String toString() {
    String result = "";

    for(int i=0; i<8; i++) {
      for(int j=0; j<8; j++) {
        if(j==7){
          result += "|" + this.elementAt(i, j) + "|\n";
        }else {
          result += "|" + this.elementAt(i, j) + "|";
        }
      }
    }
    return result;
  }

}
