package TestLists;

import MULTISET.*;


/** This class serves as a wrapper class for the basic data type char so that it
  * may be used as a Keyed item.
  *
  * @see  List
  * @see  Keyed
  *
  * @author  D. Hughes
  *
  * @version  1.0 (Mar. 2011)                                                    */

/*Name: Long Nguyen: Student # 5427059
 * I modify this file from the conListlterator to make it works with linkedlists
 * I  added comments where I made changes 
 * */

public class KeyedChar implements Keyed {
    
    
    char  theChar;  // the key (and data) of the item for the test
    
    
    /** The constructor wraps the char as a Keyed item.
      * 
      * @param  c  the character to be wrapped.                                  */
    
    public KeyedChar ( char c ) {
        
        theChar = c;
        
    }; // constructor
    
    
    /** This method returns the key (the item itself as a String).
      * 
      * @param  String  the key (item as a string)                               */
    
    public String getKey( ) {  // for Keyed
        
        return String.valueOf(theChar);
        
    }; // getKey
    
    
} // KeyedChar