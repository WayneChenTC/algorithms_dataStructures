/* HashTableChained.java */

package dict;
import java.util.Random;
import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
  protected int size;
  protected int entryNum;
  protected List[] hashTable;
  protected static int collisionNum;
  protected int p;  // coefficient of MAD compression function
  protected int a;
  protected int b;
  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    size = sizeEstimate;
    hashTable = new DList[sizeEstimate];
    entryNum = 0;

    Random rand = new Random();  
    do {
      p = 100*size + (rand.nextInt(1000) + 1);
    } while(! isPrime(p));
    
    a = rand.nextInt(p-1) + 1;
    b = rand.nextInt(p-1);  
    //need to check that do I need to examine whether load factor is out of the range??  
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    size = 101;
    hashTable = new DList[size];
    entryNum = 0;
    Random rand = new Random();  
    int p;
    do {
      p = 100*size + (rand.nextInt(1000) + 1);
    } while(! isPrime(p));
    
    int a = rand.nextInt(p-1) + 1;
    int b = rand.nextInt(p-1);  
    //need to check that do I need to examine whether load factor is out of the range?? 
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    int hashValue = ((a*code+b) % p);
    if(hashValue < 0) {
      hashValue += p;
    }
    hashValue = hashValue % size;
    if(hashValue < 0) {
      hashValue += size;
    }

    return hashValue;
  }

  /**
   *  test whether a given input is prime
   **/

  boolean isPrime(int in) {
    if(in < 4) {
      return in==2 || in==3;
    }

    for(int i=2; i*i <= in; i++) {
      if((in % i) == 0) {
        return false;
      }
    }

    return true;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    return size;
  }

  public int entrySize() {
    return entryNum;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    if(entryNum == 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    int hashValue = key.hashCode();
    int bucketNum = compFunction(hashValue);
    Entry entry = null;
    if(hashTable[bucketNum] == null) {
      hashTable[bucketNum] = new DList();
      entry = new Entry();
      entry.key = key;
      entry.value = value;
      hashTable[bucketNum].insertFront(entry);
      entryNum++;
    } else {
      collisionNum ++;
      entryNum++;
      entry = new Entry();
      entry.key = key;
      entry.value = value;
      hashTable[bucketNum].insertBack(entry);
    }
    return entry;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
      int hashValue = key.hashCode();
      int bucketNum = compFunction(hashValue);
      List bucket = hashTable[bucketNum];
      Entry goal = null;

      if(bucket == null) {
        return null;
      } 
      
      ListNode bucketNode = bucket.front();
      
      for(int i = 1; i <= bucket.length(); i++) {
        try {
          goal = (Entry) bucketNode.item();
        } catch(InvalidNodeException e) {
          System.err.println(e);
        }

        if(goal.key != key) {
          try {
            bucketNode = bucketNode.next();
          } catch(InvalidNodeException e) {
            System.err.println(e);
          }
        } else {
          return goal;
        }
      }

      return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    int hashValue = key.hashCode();
    int bucketNum = compFunction(hashValue);
    List bucket = hashTable[bucketNum];
    Entry goal = null;

    if(bucket == null) {
      return null;
    } 
      
    ListNode bucketNode = bucket.front();
    
    for(int i = 1; i <= bucket.length(); i++) {
      try {
        goal = (Entry) bucketNode.item();
      } catch(InvalidNodeException e) {
        System.err.println(e);
      }
      
      if(goal.key != key) {
        try {
          bucketNode = bucketNode.next();
        } catch(InvalidNodeException e) {
          System.err.println(e);
        }
      } else {
        try{
          bucketNode.remove();  
        } catch(InvalidNodeException e) {
          System.err.println(e);
        }
        collisionNum--;
        entryNum--;
        return goal;
      }
    }
    return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    for(int i = 0; i < hashTable.length; i++) {
      hashTable[i] = null;
    }
  }

  /**
   *  Draw a histogram recording the number of collisions of each bucket
   **/

  public void collisionGraph() {
    for(int i=0; i<hashTable.length; i++) {
      System.out.print(String.format("\n[" + "%1$4s" + "] |", i));
      if(hashTable[i]==null) {
        System.out.print(" ");
      }else {
        for(int j=1; j<= hashTable[i].length(); j++) {
          System.out.print(" *");
        }
      }
    }

    System.out.print("\n");

  }
 
  
}
