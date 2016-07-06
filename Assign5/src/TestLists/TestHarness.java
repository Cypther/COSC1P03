/** This class is a test class for testing the implementations: MyBad and MySet
 * This tests the methods for the cardinality, isEmpty 
 * , isEmpty, union and intersection 
  * 
  *  *Name: Long Nguyen: Student # 5427059
  *
  * @version  1.0 (Mar. 2014)                                                    */

package TestLists;
import java.util.Iterator;

import MULTISET.*;

public class TestHarness {
	
	//The default constructor where it tests the methods 
	public TestHarness ( ) {
		
		System.out.println("MySet :");
		MultiSet <KeyedChar> Set = new MySet <KeyedChar>();
		testList(Set);
		System.out.println("");
		System.out.println("");
		System.out.println("MyBag :");
		MultiSet <KeyedChar> Bag = new MyBag <KeyedChar>(100);
		testList(Bag);
		System.out.println("");
		System.out.println("");
		System.out.println("Checking the Union of Bag and Set ");
		union(Bag,Set);
		System.out.println("");
		System.out.println("");
		System.out.println("Checking the Intersection of Bag and Set  ");
		intersection(Bag,Set);
		
		
	}
	
	/*Adding Elements to MyBag and MySet and checking if it's equal, finding the 
	 * multiplicity and the cardinality of the set*/
	
	 private void testList ( MultiSet<KeyedChar> l ) {
	     
		 System.out.print("Is the collection empty : ");
		 System.out.println(l.isEmpty());
		 
		 System.out.println("Adding Elements to the Bag or Set ");
		 
	        for ( char c='H' ; c>='A' ; c-- ) {
		    	l.add(new KeyedChar(c));
	        };
	        
	        System.out.print("The cardinality of the set: ");
			 System.out.println(l.cardinality());
			 
			 System.out.print("The multiplicity of the set: ");
			 System.out.println(l.multiplicity(new KeyedChar('A')));
			 
			 System.out.print("Is it equal : ");
			 System.out.println(l.equals(l));
			 
			 System.out.print("Printing out the values of the Collect : ");
		  	  Iterator itr = l.iterator();
		  		 while(itr.hasNext()) {
		  			 Object element = ((Keyed) itr.next()).getKey();
		  			        System.out.print(element + " ");
		  		  }
	        
	    }; // testList
	    
	    /*This Method test the union of the two sets*/
	    
	    private void union ( MultiSet<KeyedChar> Bag, MultiSet<KeyedChar> Set ) {
	    	
	    	System.out.print("Adding elements to Bag : ");
	    	  for ( char c='A' ; c<='H' ; c++ ) {
			    	Bag.add(new KeyedChar(c));
			    	System.out.print(c + " ");
		        };
		        System.out.println("");
		        
		        System.out.print("Adding elements to Set : ");
		        for ( char c='A' ; c<='L' ; c++ ) {
			    	Set.add(new KeyedChar(c));
			    	System.out.print(c + " ");
		        };
		        System.out.println("");
		        
		        MultiSet <KeyedChar> myUnionBag; 
		       myUnionBag =  Bag.union(Set);
		       
		       System.out.print("Is Bag and Set equal : ");
				 System.out.println(Bag.equals(Set));
		       
		        for ( char c='A' ; c<='L' ; c++ ) {
			    	Set.add(new KeyedChar(c));
		        };
		       
		       MultiSet <KeyedChar> myUnionSet; 
		       myUnionSet =  Set.union(Bag);
	    	
	    	System.out.print("The union of the MyBag and MySet : ");
	    	
	    	  Iterator bag = myUnionBag.iterator();
		  		 while(bag.hasNext()) {
		  			 Object element = ((Keyed) bag.next()).getKey();
		  			        System.out.print(element + " ");
		  		  }
		  		 
		  		System.out.println("  ");
		  		 
		  		System.out.print("The union of the MySet and MyBag : ");
		    	  Iterator set = myUnionSet.iterator();
			  		 while(set.hasNext()) {
			  			 Object element = ((Keyed) set.next()).getKey();
			  			        System.out.print(element + " ");
			  		  }
	    	
	    }
	    
	    /*This Method test the intersection of the two sets*/
	    
	    private void intersection ( MultiSet<KeyedChar> Bag, MultiSet<KeyedChar> Set ) {
	    	
	    	//adding elements to the bag/set
	    	System.out.print("Adding elements to Bag : ");
	    	  for ( char c='A' ; c<='H' ; c++ ) {
			    	Bag.add(new KeyedChar(c));
			    	System.out.print(c + " ");
		        };
		        System.out.println("");
		        
		      //adding elements to the bag/set
		        System.out.print("Adding elements to Set : ");
		        for ( char c='A' ; c<='L' ; c++ ) {
			    	Set.add(new KeyedChar(c));
			    	System.out.print(c + " ");
		        };
		        System.out.println("");
		        
		        //Creating a MultiSet class and getting the intersection 
		        MultiSet <KeyedChar> myIntersectionBag; 
		        myIntersectionBag =  Bag.intersection(Set);
		        
		        System.out.print("Is Bag and Set equal : ");
				 System.out.println(Bag.equals(Set));
		        
		        for ( char c='A' ; c<='L' ; c++ ) {
			    	Set.add(new KeyedChar(c));
		        };
		        
		      //Creating a MultiSet class and getting the intersection 
		        MultiSet <KeyedChar> myIntersectionSet; 
		        myIntersectionSet =  Set.intersection(Bag);
		       
		      	System.out.print("The Intersection of the MyBag and MySet : ");
		    	
		      	//iteratoring through the Bag and printing out the key values 
		    	  Iterator myBag = myIntersectionBag.iterator();
			  		 while(myBag.hasNext()) {
			  			 Object element = ((Keyed) myBag.next()).getKey();
			  			        System.out.print(element + " ");
			  		  }
			  		 
			  		System.out.println("  ");
			  		 
			       	System.out.print("The Intersection of the MySet and MyBag : ");
			    	
			      //iteratoring through the Bag and printing out the key values 
			    	  Iterator mySet = myIntersectionBag.iterator();
				  		 while(mySet.hasNext()) {
				  			 Object element = ((Keyed) mySet.next()).getKey();
				  			        System.out.print(element + " ");
				  		  }
	    	
	    }
	

	public static void main(String[] args) {
		
		new TestHarness(); // new instance of Testharness 

	}

}
