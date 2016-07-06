/** This class MySet that implements the the interface from MulitSet
 * this class implement the cardinality, multiplicity, add, isEmpty 
 * union and intersection of MySet
  *
  *	Name: Long Nguyen: Student # 5427059
  *
  * @version  1.0 (April. 2014)                                                    */

package MULTISET;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

import TestLists.*;

public class MySet < E extends Keyed > implements MultiSet<E>, Serializable {
	
	private Node<E>  top;  // top element of the stack
	private int length;  //Length of the list
	 
	    /** This constructor creates a new, empty stack.                             */
	    
	    public MySet ( ) {
	        
	        setTop(null);
	        setLength(0); 
	        
	    }; // constructor

	  //returns the number of elements in the collection
	public int cardinality() {
		return getLength();
	}

	//returns the number of elements that match item from this 
	public int multiplicity(E item) {
		
		int numberOfItems = 0; 
		
		Node<E> referenceLink = this.getTop(); // reference pointer to head
		String thisNumberValue = ""; 
		
		//looping through the linkedlisted to get the values 
				while (this.getTop() != null) {
					
					 if((item.getKey().toString().compareTo(this.getTop().item.getKey())) == 0){
						 thisNumberValue += this.getTop().item.getKey();
						 numberOfItems++;
					 }
					this.setTop(this.getTop().next);
				}
				this.setTop(referenceLink); // setting the pointer back to the head
			
		return numberOfItems; //return the number of items 
	}
	/*Adds an Item to the collection; note this is a mutable operation
	 * and checking not to add duplicate values 
	 * 
	 * */
	@Override
	public void add(E anItem) {
		
		Node<E> referenceLink = this.getTop(); // reference pointer to head
		boolean duplicate = false; 
		
		//looping through the linkedlisted to get the values 
				while (this.getTop() != null) {
					//Checking for duplicate values 
					 if((anItem.getKey().compareTo(this.getTop().item.getKey())) == 0){
						 //System.out.println(this.top.item.getKey());
						 duplicate = true; 
						 break; 
					 }
					 this.setTop(this.getTop().next); //pointing to the next node
				}
				this.setTop(referenceLink); // setting the pointer back to the head
		
	   //if there are not duplicates then add the item to the data
		if(duplicate == false ){
			//System.out.println(anItem.getKey());
			setTop(new Node<E>(anItem,getTop()));
			setLength(getLength() + 1);
		}
		
	}

	//returns true if this is empty
	@Override
	public Boolean isEmpty() {
		return getTop() == null;
	}
	
	
	//returns a new MultiSet by taking the union of this and aSet,
	//the operation is immutable, neither this or aSet is modified
	@Override
	public MultiSet<E> union(MultiSet<E> aSet) {
	
		char[] unsortedSet = null; // unsortedSet
		String valuesOfStringSet = "";  // initialize the string 
		
		MultiSet <E> unionSet = new MySet <E>(); //creating a new MySet
		char b;
		E values; 
		Object  element;
		  
		  Iterator itr = aSet.iterator();
		//iteratoring through the list to add in the string 
		  while(itr.hasNext()) {
			    element = ((Keyed) itr.next()).getKey();
			    //System.out.println(element);
			    valuesOfStringSet += element.toString();   
		      }//end while loop
		  

			Node<E> referenceLink = this.getTop(); // reference pointer to head
			
			//looping through the linkedlisted to get the values and adding them to a string 
					while (this.getTop() != null) {
						
						element = this.getTop().item.getKey();
						valuesOfStringSet += element.toString();		
						 this.setTop(this.getTop().next); //pointing to the next node
					}
					this.setTop(referenceLink); // setting the pointer back to the head
		 
		//making the string into a char array and sorting it 
		 unsortedSet = valuesOfStringSet.toCharArray();
		  Arrays.sort(unsortedSet);
		  
		  //adding the values to unionSet backward 
		  for(int i = unsortedSet.length-1; i >=0 ; i--){
				 b = (char) unsortedSet[i];
				 values = (E) new KeyedChar(b);
				 unionSet.add(values);
			 }
		  
		return unionSet;	 //returning the union of the collection
	}

	/*This method checks if both sets are equal*/
	public Boolean equal(MultiSet<E> aSet) {
		
		boolean isEqual = false; // setting the initial state to false
		
		Node<E> referenceLink = this.getTop(); // reference pointer to head
		
		//while the pointer is not null and iterator has next
		  while(aSet.iterator().hasNext() && this.getTop() != null) { 
			//Iterator through the aSet to get the key
				Object element = aSet.iterator().next().getKey();
				//System.out.println(element);
			    
				//if comparing each values one at a time to check if they are equal 
				 if((element.toString().compareTo(this.getTop().item.getKey())) == 0){
					 isEqual = true; 
					 //else set it to false if they are not equal 
				 }else{
					 isEqual = false; 
					 break;
				 }//end else
			    
			    this.setTop(this.getTop().next);//pointing to the next node in the linked list 
		  }
		  this.setTop(referenceLink); // setting the pointer back to the head
		
		return isEqual; //return true of false if its equal
	}

	/*This method gets the intersection between the two sets */
	@Override
	public MultiSet<E> intersection(MultiSet<E> aSet) {
	
		char[] unsortedSetA = null; // unsortedSet char array
		char[] unsortedSetB = null; // unsortedSet char array
		
		String valuesOfStringSetA = "";   // initialize the string
		String valuesOfStringSetB = "";   // initialize the string
		
		MultiSet <E> intersectionSet = new MyBag <E>(100);  //creating a new MyBag
		char b;
		E values; 
		Object  element;
		  
		  Iterator itr = aSet.iterator();
		//iteratoring through the aSet and adding it to a string
		  while(itr.hasNext()) {
			    element = ((Keyed) itr.next()).getKey();
			    valuesOfStringSetB += element.toString();
		      }
		  
			Node<E> referenceLink = this.getTop(); // reference pointer to head
			
			//looping through the linkedlisted to get the values 
					while (this.getTop() != null) {
						
						element = this.getTop().item.getKey();
						 valuesOfStringSetA += element.toString();
						
						 this.setTop(this.getTop().next); //pointing to the next node
					}
					this.setTop(referenceLink); // setting the pointer back to the head

		 /*Sorting the Array of both sets*/
		 unsortedSetA = valuesOfStringSetA.toCharArray();
		 unsortedSetB = valuesOfStringSetB.toCharArray();
		  Arrays.sort(unsortedSetA);
		  Arrays.sort(unsortedSetB);
		  
		  //Checking the Array and check for duplicate values for the intersection
		  for(int i =unsortedSetA.length-1; i>=0; i--){
			  for(int j = unsortedSetA.length-1; j >=0; j--){	  
				  if(unsortedSetA[i] == unsortedSetB[j]){			  
						 b = (char) unsortedSetA[i];
						 values = (E) new KeyedChar(b);
						 intersectionSet.add(values);
					  
				  }//end if 
			  }	//end for		
			 }
		return intersectionSet;
		
	}

	@Override
	public Iterator<E> iterator() {
		return new LinkIterator<E>(this);
	}

	public Node<E> getTop() {
		return top;
	}

	public void setTop(Node<E> top) {
		this.top = top;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
