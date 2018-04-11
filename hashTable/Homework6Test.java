/* Homework6Test.java */

import dict.*;

/**
 *  Initializes a hash table, then stocks it with random SimpleBoards.
 **/

public class Homework6Test {

  /**
   *  Generates a random 8 x 8 SimpleBoard.
   **/

  private static SimpleBoard randomBoard() {
    SimpleBoard board = new SimpleBoard();
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        double fval = Math.random() * 12;
        int value = (int) fval;
        board.setElementAt(x, y, value);
      }
    }
    return board;
  }

  /**
   *  Empties the given table, then inserts "numBoards" boards into the table.
   *  @param table is the hash table to be initialized.
   *  @param numBoards is the number of random boards to place in the table.
   **/

  public static void initTable(HashTableChained table, int numBoards) {
    table.makeEmpty();
    for (int i = 0; i < numBoards; i++) {
      SimpleBoard board = randomBoard();
      Integer value = new Integer(i);
      table.insert(board, value);
      System.out.println("key is: \n" + board + ", and its value is " + value +".\n");
    }
  }

  /**
   *  Creates a hash table and stores a certain number of SimpleBoards in it.
   *  The number is 100 by default, but you can specify any number at the
   *  command line.  For example:
   *
   *    java Homework6Test 12000
   **/

  public static void main(String[] args) {
    int numBoards;

    if (args.length == 0) {
      numBoards = 100;
    } else {
      numBoards = Integer.parseInt(args[0]);
    }

    System.out.println("Construct a new HashTableChained table: table.\n");
    HashTableChained table = new HashTableChained(numBoards);
    System.out.println("The size of table should be " + numBoards + ": " + table.size() + "\n");
    System.out.println("table should be empty: is table empty? " + table.isEmpty() +"!\n");
    

    System.out.println("Inserting " + numBoards + " SimpleBoards into the chained hash table.");
    initTable(table, numBoards);
    System.out.println("The collision histograph of table.");
    table.collisionGraph();


    System.out.println("Inserting one more SimpleBoard original into table.");
    SimpleBoard board  = randomBoard();
    Integer value = new Integer(10);
    Entry original = table.insert(board, value);
    System.out.println("key is: \n" + board + ", and its value is " + value +".\n");
   
    
    System.out.println("Find out where is original in table.");
    Entry findEntry = table.find(original.key());
    System.out.println("The returned entry's key is: \n" + findEntry.key() + ", and its value is: " + findEntry.value());
    System.out.println("\nThe number of entries of table should be " + 11 + ": " + table.entrySize());
    System.out.println("table should not be empty: is table empty? " + table.isEmpty() +"!\n");
    System.out.println("The collision histograph of table.");
    table.collisionGraph();
    

    System.out.println("Remove original from table.");
    Entry oldEntry = table.remove(findEntry.key());
    System.out.println("The removed entry's key is: \n" + findEntry.key() + ", and its value is: " + findEntry.value());
    System.out.println("\nThe number of entries of table should be " + 10 + ": " + table.entrySize());
    System.out.println("table should not be empty: is table empty? " + table.isEmpty() +"!");
    System.out.println("The collision histograph of table.");
    table.collisionGraph();
    

    // To test your hash function, add a method to your HashTableChained class
    // that counts the number of collisions--or better yet, also prints
    // a histograph of the number of entries in each bucket.  Call this method
    // from here.
  }

}
