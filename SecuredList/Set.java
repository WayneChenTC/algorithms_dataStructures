 /* Set.java */

import list.*;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set {
  /* Fill in the data fields here. */
  private int cardinality;
  private List list;
  /**
   * Set ADT invariants:
   *  1)  The Set's elements must be precisely the elements of the List.
   *  2)  The List must always contain Comparable elements, and those elements 
   *      must always be sorted in ascending order.
   *  3)  No two elements in the List may be equal according to compareTo().
   **/

  /**
   *  Constructs an empty Set. 
   *
   *  Performance:  runs in O(1) time.
   **/
  public Set() { 
    list = new SList();
    cardinality = 0;	
    // Your solution here.
  }

  /**
   *  cardinality() returns the number of elements in this Set.
   *
   *  Performance:  runs in O(1) time.
   **/
  public int cardinality() {
    // Replace the following line with your solution.
    return cardinality;
  }

  /**
   *  insert() inserts a Comparable element into this Set.
   *
   *  Sets are maintained in sorted order.  The ordering is specified by the
   *  compareTo() method of the java.lang.Comparable interface.
   *
   *  Performance:  runs in O(this.cardinality()) time.
   **/
  public void insert(Comparable c) throws InvalidNodeException {
	if(cardinality()==0) {
	  list.insertFront(c);
	  cardinality++;
	}
	for(ListNode n = list.front(); n.isValidNode(); n = n.next()) {
	  if(n.compareTo(c) > 0) {
	    n.insertBefore(c);
		cardinality++;
		return;
	  } else if(n.compareTo(c) == 0) {
	    return;
	  } else if(!n.next().isValidNode()) {
	    n.insertAfter(c);
		cardinality++;
		return;
	  } else {
	    continue;
	  }
	}
	
    // Your solution here.
  }

  /**
   *  union() modifies this Set so that it contains all the elements it
   *  started with, plus all the elements of s.  The Set s is NOT modified.
   *  Make sure that duplicate elements are not created.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Your implementation should NOT copy elements of s or "this", though it
   *  will copy _references_ to the elements of s.  Your implementation will
   *  create new _nodes_ for the elements of s that are added to "this", but
   *  you should reuse the nodes that are already part of "this".
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
   **/
  public void union(Set s) throws InvalidNodeException {
	ListNode sCur = s.list.front();
	ListNode tCur = list.front();
	  while(sCur.isValidNode()) {
		if(sCur.compareTo(tCur.item()) < 0) {
		  tCur.insertBefore(sCur.item());
		  cardinality++;
		  sCur = sCur.next();
		}else if(sCur.compareTo(tCur.item()) == 0) {
		  sCur = sCur.next();
		  if(!tCur.next().isValidNode()) {
		  }else { 
		    tCur = tCur.next();
		  }
		}else {
		  if(!tCur.next().isValidNode()) {
		    while(sCur.isValidNode()) {  
			  list.insertBack(sCur.item());
			  cardinality++;
			  sCur = sCur.next();
			}
		  } 	
		  tCur = tCur.next();
		}
	  }
	
    // Your solution here.
  }

  /**
   *  intersect() modifies this Set so that it contains the intersection of
   *  its own elements and the elements of s.  The Set s is NOT modified.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Do not construct any new ListNodes during the execution of intersect.
   *  Reuse the nodes of "this" that will be in the intersection.
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT CONSTRUCT ANY NEW NODES.
   *  DO NOT ATTEMPT TO COPY ELEMENTS.
   **/
  public void intersect(Set s) throws InvalidNodeException {
    ListNode sCur = s.list.front();
	ListNode tCur = list.front();
	  while(tCur.isValidNode()) {
		if(tCur.compareTo(sCur.item()) < 0) {
		  ListNode old = tCur;
		  tCur = tCur.next();
		  old.remove();
		  cardinality--;
		}else if(tCur.compareTo(sCur.item()) == 0) {
		  tCur = tCur.next();
		  if(!sCur.next().isValidNode()) {
		  }else { 
		    sCur = sCur.next();
		  }
		}else {
		  if(!sCur.next().isValidNode()) {
		    while(tCur.isValidNode()) {  
			  ListNode old = tCur;
		      tCur = tCur.next();
		      old.remove();
			  cardinality--;
			}
		  } 	
		  sCur = sCur.next();
		}
	  }
    // Your solution here.
  }

  /**
   *  toString() returns a String representation of this Set.  The String must
   *  have the following format:
   *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
   *            between them.
   *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
   *            "{" or after "}"; two spaces before and after each element.
   *            Elements are printed with their own toString method, whatever
   *            that may be.  The elements must appear in sorted order, from
   *            lowest to highest according to the compareTo() method.
   *
   *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
   *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
   *            DEVIATIONS WILL LOSE POINTS.
   **/
  public String toString() {
    // Replace the following line with your solution.
	String result = "[  ";
	try {
    ListNode current = list.front();
    while (current.isValidNode()) {
      result = result + current.item() + "  ";
      current = current.next();
    }
	} catch (InvalidNodeException e) {
	  System.err.println(e);
	}
    return result + "]";
  }

  public static void main(String[] argv) {
    Set s = new Set();
	try {
	  long startTime = System.nanoTime();
      s.insert(new Integer(3));
	  long endTime = System.nanoTime();	
	  System.out.println("insert() took "+(endTime - startTime) + " ns"); 
      s.insert(new Integer(4));
      s.insert(new Integer(3));
      System.out.println("Set s = " + s);
    } catch (InvalidNodeException e) {
	  System.err.println(e);
	}
 
    Set s2 = new Set();
	try {
      s2.insert(new Integer(4));
      s2.insert(new Integer(5));
      s2.insert(new Integer(5));
      System.out.println("Set s2 = " + s2);
    } catch (InvalidNodeException e){
	  System.err.println(e);
	}
    Set s3 = new Set();
	try {
      s3.insert(new Integer(5));
      s3.insert(new Integer(3));
      s3.insert(new Integer(8));
      System.out.println("Set s3 = " + s3);
	} catch(InvalidNodeException e) {
	  System.err.println(e);
	}
    try{
	  long startTime = System.nanoTime();
      s.union(s2);
	  long endTime = System.nanoTime();	
	  System.out.println("union() took "+(endTime - startTime) + " ns"); 
      System.out.println("After s.union(s2), s = " + s);
    } catch(InvalidNodeException e) {
	  e.printStackTrace();
	  System.err.println(e);
	}
	try {
	  long startTime = System.nanoTime();
      s.intersect(s3);
	  long endTime = System.nanoTime();	
	  System.out.println("intersect took "+(endTime - startTime) + " ns"); 
      System.out.println("After s.intersect(s3), s = " + s);
    } catch(InvalidNodeException e) {
	  e.printStackTrace();
	  System.err.println(e);
	}
    System.out.println("s.cardinality() = " + s.cardinality());
    // You may want to add more (ungraded) test code here.
  
  }
}
