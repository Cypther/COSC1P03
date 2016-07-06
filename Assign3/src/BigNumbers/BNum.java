/** This class BNum an interface for  BNum, ConBNum
  * LnkBNum. 
  *
  *
  * @version  1.0 (Mar. 2014)                                                    */

package BigNumbers;

public interface BNum {
	
	//Create a clone of this.
	public BNum clone();
	
	/* Returns true if 'this' =  n*/
	public boolean equals( BNum n);
	
	/* Returns true if  'this' < n */
	public boolean lessThan(BNum n );
	
	/* returns 'this' + n*/
	public BNum add(BNum n);
	
	/*returns 'this' - n*/
	public BNum sub(BNum n);
	
	/*Returns the sign of this BigNumber object */
	public int getSign();
	
	//Returns the digit i of this object, digit 0 is LSD.
	public int getDigit(int i);	

}
