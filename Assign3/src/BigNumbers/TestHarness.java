/** This class is a test class for testing the implementations: BNum, ConBNum
  * LnkBNumt. It performs a number of tests including add, subtract, clone, getDigit
  * lessThan, getSign and tests of the exceptions.
  * 
  *  *Name: Long Nguyen: Student # 5427059
  *
  * @version  1.0 (Mar. 2014)                                                    */


package BigNumbers;

public class TestHarness {

	public static void main(String[] args) {
		
		//Class BNum A,B,C,D,E,F
		BNum A,B,C,D,E,F;
		
		//Creating new Objects of A to ConBNum and B to LinkBNum with number 1234 and the String "1234" 
		A = new ConBNum("1234");
		B = new LinkBNum(1234);
		
		//Adding A to B and returning the values to BNum C
		System.out.print("Values of  1234 + 1234 =  " ); 
		      C = A.add(B);
		      
		 //Printing out the values of C with getDigit Method to check if the Exception is working
		      int i = 0; //counter for getDigit
		      while(C.getDigit(i) != -1){
		    	  System.out.print(C.getDigit(i)); 
		      i++;
	           }//end while 
		      
		    //Subtracting A to B and returning the values to BNum C
		      C = A.sub(B);
		      System.out.print("Values of  1234 - 1234 =  " ); 
		      int x = 0; //counter for getDigit
		      while(C.getDigit(x) != -1){
		    	  System.out.print(C.getDigit(x)); 
		      x++;
	           }
		      
		      //Testing if it's less then and if it's equal to 
		      System.out.println("Is A equal to B "+ A.equals(B)); 
		      System.out.println("Is A less then to B "+ A.lessThan(B)); 
		      
		      //Testing for invalid input, Throwing exception 
		       D = new ConBNum("12D");
		       E = new LinkBNum("12E");
		       
		       //Testing the method to getSign of BNum
		       F = new LinkBNum("-12");
		       System.out.println("The the sign of F positive will be 1, negative will be -1 and F is : "+F.getSign());
		      
		       //Testing the clone method, Cloning F to G and printing out the value of BNum G
		       BNum  G = F.clone(); 
		       System.out.print("Cloning G from F.clone method and printing out the value of G" ); 
		       System.out.print("The values of G is: "); 
			      int y = 0; //counter for getDigit
			      while(G.getDigit(y) != -1){
			    	  System.out.print(G.getDigit(y)); 
			      y++;
		           }
			      System.out.print("The sign of G is : " + G.getSign()); 
		      

		

	}

}
