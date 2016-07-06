/** This class represents an interator on a ConList as required by the interface
  * Iterable<E> of the List interface.
  * 
  * @see  List
  * @see  Keyed
  * 
  * @author  D. Hughes
  * 
  * @version  1.0 (Mar. 2011)
  * 
  * new concepts: implementing Iterator.                                         */

/*Name: Long Nguyen: Student # 5427059
 * I modify this file from the conListlterator to make it works with the array part of the lterator 
 * I  added comments where I made changes 
 * */

package MULTISET;

import java.util.*;

class ListIterator < E extends Keyed > implements Iterator<E> {
    
    
    private int         cursor;  // the cursor that iterates through the list
    private MyBag<E>  list;    // the list being iterated over
    
    
    /** This constructor constructs an iterator on the specified ConList.
      * 
      * @param  l  the list to be iterated over.                                 */
    
    ListIterator ( MyBag<E> l ) {
        
        list = l;
        cursor = 0;
        
    };  // constructor
    
    
    /** This method returns true if there are more items in the list.
      * 
      * @return  boolean  more items on the list.                                */
    
    public boolean hasNext ( ) {  // from Iterator
        
        return cursor < list.getLength();
        
    };  // hasNext
    
    
    /** This method returns the next item in the list.
      * 
      * @retuen  E  the next item on the list.                                   */
    
    public E next ( ) {  // from Iterator
        
        E  i;
        
        if ( cursor >= list.getLength() ) {
            throw new NoSuchElementException();
        }
        else {
            i = list.getItems()[cursor];
            cursor = cursor + 1;
            return i;
        }
        
    };  // next
    
    
    /** Removal is not supported so this method throws an
      * UnsupportedOperationException.
      * 
      * @exception  UnsupportedOperationException  remove is not supported.      */
    
    public void remove ( ) {  // from Iterator
        
        throw new UnsupportedOperationException();
        
    };  // remove
    
    
}  // LnkListIterator