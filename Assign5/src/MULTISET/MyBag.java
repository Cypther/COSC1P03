/** This class MyBag that implements the the interface from MulitSet
 * this class implement the cardinality, multiplicity, add, isEmpty 
 * union and intersection of MyBag
  *
  *	Name: Long Nguyen: Student # 5427059
  *
  * @version  1.0 (April. 2014)                                                    */


package MULTISET;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

import TestLists.*;

public class MyBag < E extends Keyed > implements MultiSet<E>, Serializable {
	
	private E[]  items;   // the items in the Bag
	private int  length;  // the length of the Bag
	private int  cursor;  // the list cursor

	// The default constructor
	public MyBag ( ) {
	        
		//creates an object with a positive zero
		   this(0); 
	        
	    }; // constructor
	    
	    public MyBag ( int size ) {
	        
	        setItems((E[]) new Keyed[size]);
	        setLength(0); 
	        
	    }; // Constructor
	
	//returns the number of elements in the collection
	public int cardinality() {
		return getLength();
	}
	
	//returns the number of elements that match item from this 
	public int multiplicity (E item) {
		
		int numberOfItems = 0; 
		
		//Setting the cursor to the found
		this.toFront();
		
		//looping through to check if there are duplicates
		 while ( cursor < getLength() ) {
			 
			 if((item.getKey().toString().compareTo(getItems()[cursor].getKey())) == 0){
				 numberOfItems++;
			 }
	            cursor = cursor + 1;
	        };
	        
		 //System.out.println("Number of items  found " + numberOfItems);
		return numberOfItems;
	}
	
	//Adds an Item to the collection; note this is a mutable operation
	public void add(E anItem) {
		
		  int  j;
	        
	        if ( getLength() >= getItems().length ) {
	           throw new NoSpaceException();
	        }
	        else {
	            for ( j = getLength()-1 ; j>=cursor ; j-- ) {
	                getItems()[j+1] = getItems()[j];
	            };
	            getItems()[cursor] = anItem;
	            setLength(getLength() + 1);
	        };
		
	}
	
	//returns true if this is empty
	public Boolean isEmpty() {
		 return getLength() == 0;
	}
	
	//returns a new MultiSet by taking the union of this and aSet,
	//the operation is immutable, neither this or aSet is modified
	@SuppressWarnings("unchecked")
	public MultiSet<E> union(MultiSet<E> aSet) {
		
		
		char[] unsortedSet = null; // unsortedSet
		String valuesOfString = "";  // initialize the string 
		E[] temp;
		temp = (E[]) new Keyed[100]; //initialize the E array to 100 
		
		MultiSet <E> unionSet = new MyBag <E>(100); //creating a new MyBag 
		char b;
		E values; 
		Object  element;
		  
		//iteratoring through the list to add in the string 
		  Iterator itr = aSet.iterator();
		  while(itr.hasNext()) {
			    element = ((Keyed) itr.next()).getKey();
			    valuesOfString += element.toString();
		      }//end while 
		
		  //looping through the "This" to get the values and adding it to the string 
		 while ( cursor < getLength()) {
			 element = this.getItems()[cursor].getKey(); 
			// System.out.println(element);
			 valuesOfString += element.toString();
			 b = (char) element.toString().charAt(0);
			 values = (E) new KeyedChar(b);
				 cursor = cursor + 1;
	        }//end while 

		 //making the string into a char array and sorting it 
		 unsortedSet = valuesOfString.toCharArray();
		  Arrays.sort(unsortedSet);
		  
		  //adding the values to unionSet backward 
		  for(int i =unsortedSet.length-1; i >= 0; i--){
				 b = (char) unsortedSet[i];
				 values = (E) new KeyedChar(b);
				 unionSet.add(values);
				
			 }
		
		return unionSet; //returning the union of the collection
	}
	
	//returns true if this contains the same elements as aSet
	public Boolean equal(MultiSet<E> aSet) {
		
		//initialize the equals to false 
		boolean equals = false; 
	
		//pointing the cursor to the front 
			this.toFront();
			
			Iterator itr = aSet.iterator();
			
				//looping through to check if there are duplicates
				 while ( cursor < getLength() && itr.hasNext() ) {
					 
					 //Iterator through the aSet to get the key
					 Object  element = ((Keyed) itr.next()).getKey();
					 
					 //if comparing each values one at a time to check if they are equal 
					 if(this.getItems()[cursor].getKey().toString().compareTo(element.toString()) == 0){
						 //System.out.println(this.items[cursor].getKey());
						 //System.out.println(element.toString());
						 cursor = cursor + 1;
						 equals = true;
						 //else set it to false if they are not equal 
					   }else{
						   equals = false;
						   break;
					   }//end else 
			            
			        }//end while loop
			        
				 //return the value if it's true or false 
		return equals;
	}
	
	/*This method gets the intersection between the two sets */
	@SuppressWarnings("unchecked")
	@Override
	public MultiSet<E> intersection(MultiSet<E> aSet) {
		
		char[] unsortedSetA = null; // unsortedSetA char array
		char[] unsortedSetB = null; // unsortedSetB char array
		
		String valuesOfStringSetA = "";  // initialize the string 
		String valuesOfStringSetB = "";  // initialize the string 
		
		E[] temp;
		temp = (E[]) new Keyed[100];
		
		MultiSet <E> intersectionSet = new MyBag <E>(100);  //creating a new MyBag
		char b;
		E values; 
		Object  element;
		
		//iteratoring through the aSet and adding it to a string
		  Iterator itr = aSet.iterator();
		  while(itr.hasNext()) {
			    element = ((Keyed) itr.next()).getKey();
			    valuesOfStringSetB += element.toString();
		      }
		  
		//looping through the values of "This " and getting the values and adding it to string 
		 while ( cursor < getLength()) {
			 element = this.getItems()[cursor].getKey(); 
			 valuesOfStringSetA += element.toString();
				 cursor = cursor + 1;
	        }
		 
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
						 //adding the value to the intersectionSet
						 intersectionSet.add(values);
					  
				  }//end if 
			  }	//end for		
			 }
		  //return the intersectionSet
		return intersectionSet;
			
		
	}; // advance
	
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator<E>(this);
		//return null;
	}
	
	//pointing the cursor to the front
public void toFront ( ) {
        cursor = 0;
    }; // toFront
    
    //checking if the cursor is bigger then lenght 
    public E get ( ) {
        if ( cursor >= getLength() ) {
           throw new NoItemException();
        }
        else {
            return getItems()[cursor];
        }
		//return null;
        
    }; // get
  
    public void advance( ) {
        
        if ( cursor < getLength() ) {
            cursor = cursor + 1;
        }      
    }//end if statement
    
    public boolean offEnd ( ) {     
        return cursor >= getLength();
        
    }

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public E[] getItems() {
		return items;
	}

	public void setItems(E[] items) {
		this.items = items;
	}; // offEnd

}
