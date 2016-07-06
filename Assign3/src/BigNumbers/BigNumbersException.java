/** This class BigNumbersException extends runtime exceptions and 
 * use those exceptions for BNum methods 
 * It performs exceptions.
  *
  *Name: Long Nguyen: Student # 5427059
  *
  * @version  1.0 (Mar. 2014)                                                    */

package BigNumbers;

public class BigNumbersException extends RuntimeException {
	
	public BigNumbersException() {
		super("out of bounds!");
	}
	
	public BigNumbersException(String msg) {
		super(msg);
	}
	
	public String getMessage(){
		return "Not a valid number!"; 
	}
	

}
