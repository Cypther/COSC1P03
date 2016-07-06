/** This class  ConBNum implements BNum methods 
 * It performs a number of tests including add, subtract, clone, getDigit
  * lessThan, getSign and tests of the exceptions.
  *
  *  *Name: Long Nguyen: Student # 5427059
  *
  * @version  1.0 (Mar. 2014)                                                    */

package BigNumbers;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ConBNum implements BNum, Serializable  {
	
	//Class fields
	public int num []; 
	public int sign; 

  /** This default constructor ConBNum , construct an object with positive zero                   */

// The default constructor
public ConBNum ( ) {
        
	//creates an object with a positive zero
	this(0); 
        
    }; // constructor
    
    /** This constructor produces  a Long of the form:  */
    
 // The constructor that takes a long type
public ConBNum ( long n ) {
	
	int sizeOfArray = 0; //variable for size of the array to be made
	//converting the number to a char array
	char[] characters = String.valueOf(n).toCharArray(); 

	/*Check to see if the strings is a valid number
	 * If it not a valid number then throw a run time exception
	 * */
	for (char c :characters) {
		try {
		if (Character.isLetter(c)) throw new BigNumbersException("This is not a valid "+ c + " Not a valid number!");
		}
		catch (BigNumbersException e) {
			System.out.println("\n There seems to be a problem:  "+ "This is not a valid "+ c + e.getMessage());
		}
	}

//checks to see if the first value is a negative 
	if(characters[0] ==  '-'){
		sizeOfArray = characters.length-1;  //getting the length of the array without getting the negative char value
		num = new int[sizeOfArray]; //creating the new array with the size from get length
		this.sign = -1; // setting the sign to negative 
		
	//looping through the char array to add the values to the class
	for(int i = 1; i<characters.length; i++) {
		//if its the last value of the array, then multiply it by negative one	
		if(i == 1){
			this.num[i-1] = ((characters[i]-48));
			//this.num[i-1] = ((characters[i]-48)*-1);
		}else{
		num[i-1] = characters[i]-48;//Subtract 48 because of the ASCII value 
		}
      }//end for loop
	
	}else{
		sizeOfArray = characters.length;  //getting the length of the array 
		num = new int[sizeOfArray]; //creating the new array with the size from get length
		this.sign = 1; //positive value will be positive one 
		for(int i = 0; i<characters.length; i++)
	      {
			num[i] = characters[i]-48;//Subtract 48 because of the ASCII value 
			  //System.out.println( characters[1]);
	      }
	}//end else

    }; // end ConBNum ( long n )  constructor
    
    /** This constructor produces  a String of the form:  */
    
public ConBNum ( String s ) {
	
	s = s.trim(); //Trimming the white spaces of the string
	int sizeOfArray = 0; //variable for size of the array to be made
	char[] characters = String.valueOf(s).toCharArray(); //converting the number to a char array
	
	/*Check to see if the strings is a valid number
	 * If it not a valid number then throw a run time exception
	 * */
	for (char c :characters) {
		try {
		if (Character.isLetter(c)) throw new BigNumbersException("Not a valid number!");
		}
		catch (BigNumbersException e) {
			System.out.println("\n There seems to be a problem:  "+ "This is not a valid, "+ c +"! "+ e.getMessage());
		}
	}
	
	//if the number is negative 
	if(characters[0] ==  '-'){ //check the first character if it's a negative 
		sizeOfArray = characters.length-1; //getting the length of the array without the negative value
		num = new int[sizeOfArray];  //setting the length of the array without the negative value
		this.sign = -1;  //setting negative one as the sign
		
		//looping through the char array to add the values to the class
	for(int i = 1; i<characters.length; i++) {
		if(i == 1){//multiply the last index of the array by negative one 
			this.num[i-1] = ((characters[i]-48));
			//this.num[i-1] = ((characters[i]-48)*-1);
		}else{
		this.num[i-1] = characters[i]-48;//Subtract 48 because of the ASCII value 
		}
    } //end for loop 
	
	}else{
		sizeOfArray = characters.length;  //getting the length of the array 
		this.num = new int[sizeOfArray]; //setting the length of the array
		this.sign = 1; //setting sign to positive one
		for(int i = 0; i<characters.length; i++)//looping through the array and added the values to the num field 
	    {		
			this.num[i] = characters[i]-48;//Subtract 48 because of the ASCII value 
	    }
	}
    }; // end of the ConBNum ( String s ) constructor
    
	/*Create a clone of this. Object.  The For loop will loop through the values of the this object 
	 * and adding it to the string
	 * 
	 */
	public BNum clone(){
	
		String cloneNumberValue = ""; 
		//getting the sign of the number
		int getSign = this.getSign();
		
		//looping through the values of the object that called this
		for(int i = 0; i < this.num.length; i++){
			
			//Multiplying the first digital by the sign
			if(i == 0 ){
				cloneNumberValue  += (this.num[i])*getSign;
			}else{
			//adding the values to the cloneNumberValue string 
			cloneNumberValue  += this.num[i];
			}
		}//end for loop 
		
		//creating a new object with all the values that called the clone method 
		BNum copy = new ConBNum(cloneNumberValue);
		
		//returning the object that called this method 
		return copy;
	}
	
	/* Returns true if 'this' =  n*/
	public boolean equals( BNum n) {
		
		boolean equals = false; 
		
        String valueOfNinString = BNumValue(n); //Getting the value of BNum		
		ConBNum copyingBNum =  new ConBNum(valueOfNinString); //creating a copy of the BNum n that's being passed in
		
		int lengthOfBNum =  copyingBNum.num.length;//getting the length of BNum that's being passed in
		
		/*If the length are equal then 
		loop through this check to see if the values are equal*/
		for(int i = 0; i < this.num.length; i++){	
				  if (lengthOfBNum == this.num.length && this.num[i] == copyingBNum.getDigit(i)){
					  equals = true; 
				  }else{
					  equals = false; 
				  }     
		}//end for loop

		return equals; //return the values if it's equal
	}
	
	/* Returns true if  'this' < n */
	public boolean lessThan(BNum n ){
		
		boolean lessThan = false; 
		String copyingThisBNumString = "";
		String copyingBNumString = "";
		
		String valueOfNinString = BNumValue(n); //Getting the value of BNum
		
		ConBNum copyingBNum =  new ConBNum(valueOfNinString); //creating a copy of the BNum n that's being passed in
		ConBNum copyingThisBNum =  (ConBNum)this.clone(); //creating a copy of the BNumThis that's calling the method
		
		//find the different between the two lengths
		int diferentLenghtBetweenBothValues=  Math.abs(Math.abs(copyingThisBNum.num.length) - Math.abs(copyingBNum.num.length));
		
		//adding the values to the copyingThisBNum string
		for(int i = 0; i < copyingThisBNum.num.length; i++){ 
			copyingThisBNumString  += copyingThisBNum.num[i];
		}
		
		//adding the values to the copyingBNum string 
		for(int i = 0; i < copyingBNum.num.length; i++){
			copyingBNumString  += copyingBNum.num[i];
		}
		
		//check what length is longer, if one of the lengths is longer then pad the beginning with leading zeros 
		if(copyingThisBNumString.length() < copyingBNumString.length()){
		for(int x = diferentLenghtBetweenBothValues-1; x >= 0; x--){	
			copyingThisBNumString = 0 + copyingThisBNumString; 
		}
		}
		
		//check what length is longer, if one of the lengths is longer then pad the beginning with leading zeros 
		if(copyingThisBNumString.length() > copyingBNumString.length()){
			for(int x = diferentLenghtBetweenBothValues-1; x >= 0; x--){	
				copyingBNumString = 0 + copyingBNumString; 
			}
			}
		
		//Comparing both strings to see which one is bigger 
		if(copyingThisBNumString.compareTo( copyingBNumString ) < 0){
			lessThan = true; 
		}
		//System.out.println(copyingThisBNumString.compareTo( copyingBNumString ));
	
		return lessThan; 
	}
	/* returns 'this' + n*/
	
	/*This add method add the to values together 
	 * It adds it by checking which lenght is bigger 
	 * */
	
	public String BNumValue(BNum n){
		
		int signOfN = n.getSign(); //Getting the sign of BNum N
		
		int counter = 0;  //Counter for the while loop
		String valueOfNString = ""; //Variable to hold the numbers of BNum N
		int lenght = n.getDigit(-1);
		
		//System.out.println("Lenght is "+ lenght);
		
		 // while(n.getDigit(counter) != -1){
			  while(counter <= lenght-1){
			  valueOfNString += n.getDigit(counter); 
			counter++; 
			//System.out.println(valueOfNinString );
		}
		  int  firstCharacterOfN = (int) valueOfNString.charAt(0) -48;
		  valueOfNString =  (firstCharacterOfN*signOfN) + valueOfNString.substring(1, valueOfNString.length());
	
		return valueOfNString;
	}
	
	public BNum add(BNum n){
		
		String addingBothValuesString = ""; //String to adding all the digit together 
		boolean remainder = false; //Setting the remainder to false by default
		
		String valueOfNinString = BNumValue(n); //Getting the value of BNum
		
		ConBNum copyingBNum =   new ConBNum(valueOfNinString); 
		ConBNum copyingThisBNum =  (ConBNum)this.clone(); //creating a copy of the BNumThis that's calling the method
		
		/*Getting the sign of both values before deciding to add or subtract */
		int signOfBNum = copyingBNum.getSign(); 
		int signofThisBNum = copyingThisBNum.getSign(); 
		
		if(signOfBNum == signofThisBNum){
				
		/////////////////////////////////////////////////////////////////////////////	
		int  smallerLength = 0; // variable to hold the smaller length
		int largerIndex = 0;  // variable to hold the bigger length 
		ConBNum  largerLengthConBNum = null;
		ConBNum smallerLenghtConBNum = null; 
	
		/*Find out which BNum length is bigger 
		 * length is less or greater then*/
		if(copyingThisBNum.num.length <  copyingBNum.num.length){
		          smallerLength = copyingThisBNum.num.length;  // getting the lenght of the value 
		          largerLengthConBNum = copyingBNum; 
		          smallerLenghtConBNum = copyingThisBNum; 
		          largerIndex  = largerLengthConBNum.num.length-1; //starting at the last index 
		}
		/*Find out which BNum length is bigger 
		 * length is less or greater then*/
		if(copyingThisBNum.num.length >  copyingBNum.num.length){
		         smallerLength =  copyingBNum.num.length; 
		         largerLengthConBNum = copyingThisBNum; 
		         smallerLenghtConBNum = copyingBNum; 
		         largerIndex  = largerLengthConBNum.num.length-1; //starting at the last index 
		}
		/*Find out which BNum length is bigger 
		 * length is less or greater then*/
		if(copyingThisBNum.num.length ==  copyingBNum.num.length && copyingThisBNum.num[0] < copyingBNum.num[0]){
		       smallerLength = copyingThisBNum.num.length;  // getting the lenght of the value 
		          largerLengthConBNum = copyingBNum; 
		          smallerLenghtConBNum = copyingThisBNum; 
		          largerIndex  = largerLengthConBNum.num.length-1; //starting at the last index 
	}
		/*Find out which BNum length is bigger 
		 * length is less or greater then*/
		if(copyingThisBNum.num.length ==  copyingBNum.num.length && copyingThisBNum.num[0] >= copyingBNum.num[0]){
		     smallerLength =  copyingBNum.num.length; 
	         largerLengthConBNum = copyingThisBNum; 
	         smallerLenghtConBNum = copyingBNum; 
	         largerIndex  = largerLengthConBNum.num.length-1; //starting at the last index 
	}
		
		//getting the different between the two values and calling the Math absolute value
		int diferentLenghtBetweenBothValues=  Math.abs(Math.abs(copyingThisBNum.num.length) - Math.abs(copyingBNum.num.length)); 
		//System.out.println(diferentLenghtBetweenBothValues);
		/*This while loop add the numbers together in both Arrays, each element at a time
		 * If the smallerLenght is less then zero, then it will break out of the loop
		 *  
		 * */
		
		while (smallerLength > 0 ){
			
			/*This if statement checks if adding both values together is less then 10, 
			 * and BNumLenght is not equal to 0
			 * */
			if(remainder ==  false && largerLengthConBNum.num[largerIndex] + smallerLenghtConBNum.getDigit(smallerLength-1) < 10 && smallerLength !=  0){
				addingBothValuesString = largerLengthConBNum.num[largerIndex] + smallerLenghtConBNum.getDigit(smallerLength-1) + addingBothValuesString;
				if(smallerLength >0){
					smallerLength--; 
					}
				if(largerIndex >0){
					largerIndex--;
					}
				remainder = false; 
			}	
			
			/*This if statement checks if adding both values together plus a 1 is less then 10, 
			 * and BNumLenght is not equal to 0
			 * */
			if(remainder ==  true && largerLengthConBNum.num[largerIndex] + smallerLenghtConBNum.getDigit(smallerLength-1) < 10 && smallerLength !=  0){
				addingBothValuesString = ((largerLengthConBNum.num[largerIndex] + smallerLenghtConBNum.getDigit(smallerLength-1))+1) + addingBothValuesString;
				
				if(smallerLength >0){
					smallerLength--; 
					}
				if(largerIndex >0){
					largerIndex--;
					}
				remainder = false; 
			}
			
			/*This if statement checks if adding both values together  is greater then 10, 
			 * and BNumLenght is not equal to 0
			 * */
			if(remainder ==  false && largerLengthConBNum.num[largerIndex] + smallerLenghtConBNum.getDigit(smallerLength-1) >= 10 && smallerLength !=  0){
				addingBothValuesString = ((largerLengthConBNum.num[largerIndex] + smallerLenghtConBNum.getDigit(smallerLength-1))%10) + addingBothValuesString;
				
				if(smallerLength >0){
					smallerLength--; 
					}
				if(largerIndex >0){
					largerIndex--;
					}
				remainder = true; 
			}//end if
			
			/*This if statement checks if adding both values together  plus the remainder is greater then 10, 
			 * and BNumLenght is not equal to 0
			 * */
			if(remainder ==  true && largerLengthConBNum.num[largerIndex] + n.getDigit(smallerLength-1) >= 10 && smallerLength !=  0){
				addingBothValuesString = (((largerLengthConBNum.num[largerIndex] + smallerLenghtConBNum.getDigit(smallerLength-1))+1) %10) + addingBothValuesString;

				if(smallerLength >0){
					smallerLength--; 
					}
				if(largerIndex >0){
					largerIndex--;
					}
				remainder = true; 
			}
			
			if(largerIndex == 0 && remainder == true && smallerLength !=  0){
				addingBothValuesString = (((largerLengthConBNum.num[0] + smallerLenghtConBNum.getDigit(0))%10)+1) +  addingBothValuesString;
				addingBothValuesString =1 + addingBothValuesString; 
				break; 
			}
			
			if(largerIndex == 0 && remainder == false && smallerLength !=  0){	
					addingBothValuesString = (((largerLengthConBNum.num[0] + smallerLenghtConBNum.getDigit(0)))) + addingBothValuesString;	
				break; 
			}
			//end if
			
	}//while  loop
		
		//System.out.println(remainder);
		
		/*adding the rest of the number if the BNumLeght is smaller then the this.Lenght; 
		 * This for loop check if there's carrying over, that needs to been adding
		 * 
		 * */
			for(int x = diferentLenghtBetweenBothValues-1; x >= 0; x--){		
				if(remainder ==  true  &&  (largerLengthConBNum.num[x] +1) >= 10){
			         addingBothValuesString = (largerLengthConBNum.num[x] +1)%10 +  addingBothValuesString;
			         
			         //adding the very last digit to the end of the string
			     	if(x == 0){
						addingBothValuesString = 1 +  addingBothValuesString;
					}
			          remainder =  true; 
				}//end if
			      if(remainder ==  true  &&  (largerLengthConBNum.num[x] +1) < 10){
				         addingBothValuesString = (largerLengthConBNum.num[x] +1) +  addingBothValuesString;
				          remainder =  false; 
		      }if(remainder ==  false){
					addingBothValuesString = (largerLengthConBNum.num[x]) +  addingBothValuesString;
				}//end else
				
			}//end for loop	          
			
			//casting the first character to an int and subtracting 48 because of the ASSIIC value
			int  firstCharacter = (int)addingBothValuesString.charAt(0) -48;
			
			//Multiplying the first part of the string by the sign
			 addingBothValuesString =  (firstCharacter*signofThisBNum) + addingBothValuesString.substring(1, addingBothValuesString.length());
		
		  // System.out.println( "addingBothValuesString " + addingBothValuesString);
		   
		 //creating a new object with the new values and returning it 
		
		}/**********end of  if very top *********/
		
		else{
			//else if the sign match up then call the subtraction method 
			ConBNum addingConBNum =  (ConBNum) copyingThisBNum.sub(copyingBNum);
		
			for(int i = 0; i < addingConBNum.num.length; i++){
				addingBothValuesString += addingConBNum.getDigit(i);
			}
			
		}
		ConBNum addingConBNum = new ConBNum(addingBothValuesString); 
			
		
		return addingConBNum;
	}
	
	/*returns 'this' - n*/
	public BNum sub(BNum n){
		
		String subtractingBothValuesString = ""; //String to adding all the digit together 
		boolean regrouping = false; //Setting the remainder to false by default
		
		String valueOfNinString = BNumValue(n); //Getting the value of BNum

            ConBNum copyingBNum =   new ConBNum(valueOfNinString); //creating a copy of the BNum n that's being passed in
		
		ConBNum copyingThisBNum =  (ConBNum)this.clone(); //creating a copy of the BNumThis that's calling the method
		
		/*Getting the sign of both values before deciding to add or subtract
		 * if the sign are the same then add, if they are different the substact */
		int signOfBNum = (copyingBNum.getSign())*-1; 
		int signofThisBNum = copyingThisBNum.getSign(); 
		
		if(signOfBNum != signofThisBNum){
		
		/////////////////////////////////////////////////////////////////////////////	
		int  smallerLength = 0; // variable to hold the smaller length
		int largerIndex = 0;  // variable to hold the bigger length 
		ConBNum  largerLengthConBNum = null;
		ConBNum smallerLenghtConBNum = null; 
	
		/*Find out which BNum length is bigger 
		 * length is less or greater then*/
		if(copyingThisBNum.num.length <  copyingBNum.num.length){
		          smallerLength = copyingThisBNum.num.length;  // getting the lenght of the value 
		          largerLengthConBNum = copyingBNum; 
		          smallerLenghtConBNum = copyingThisBNum; 
		          largerIndex  = largerLengthConBNum.num.length-1;
		}
		/*Find out which BNum length is bigger 
		 * length is less or greater then*/
		if(copyingThisBNum.num.length >  copyingBNum.num.length){
		         smallerLength =  copyingBNum.num.length; 
		         largerLengthConBNum = copyingThisBNum; 
		         smallerLenghtConBNum = copyingBNum; 
		         largerIndex  = largerLengthConBNum.num.length-1;
		}
		/*Find out which BNum length is bigger 
		 * length is less or greater then*/
		if(copyingThisBNum.num.length ==  copyingBNum.num.length && copyingThisBNum.num[0] < copyingBNum.num[0]){
		       smallerLength = copyingThisBNum.num.length;  // getting the lenght of the value 
		          largerLengthConBNum = copyingBNum; 
		          smallerLenghtConBNum = copyingThisBNum; 
		          largerIndex  = largerLengthConBNum.num.length-1;
	}
		/*Find out which BNum length is bigger 
		 * length is less or greater then*/
		if(copyingThisBNum.num.length ==  copyingBNum.num.length && copyingThisBNum.num[0] >= copyingBNum.num[0]){
		     smallerLength =  copyingBNum.num.length; 
	         largerLengthConBNum = copyingThisBNum; 
	         smallerLenghtConBNum = copyingBNum; 
	         largerIndex  = largerLengthConBNum.num.length-1;
	}
		
		//getting the different between the two values and calling the Math absolute value
		int diferentLenghtBetweenBothValues=  Math.abs(Math.abs(copyingThisBNum.num.length) - Math.abs(copyingBNum.num.length)); 
		
		/*This while loop add the numbers together in both Arrays, each element at a time
		 * If the smallerLenght is less then zero, then it will break out of the loop
		 *  
		 * */
		
		while (smallerLength > 0 ){
			
			/*This if statement checks if adding both values together is less then 10, 
			 * and BNumLenght is not equal to 0
			 * */
			//System.out.println(largerLengthConBNum.num[largerIndex]); 
			if(regrouping ==  false && largerLengthConBNum.num[largerIndex] - smallerLenghtConBNum.getDigit(smallerLength-1) < 0 && smallerLength !=  0){
				subtractingBothValuesString = (largerLengthConBNum.num[largerIndex]+10) - smallerLenghtConBNum.getDigit(smallerLength-1) + subtractingBothValuesString;
				if(smallerLength >0){
					smallerLength--; 
					}
				if(largerIndex >0){
				largerIndex--;
				}
				regrouping = true; 
			}	
			
			if(regrouping ==  true && largerLengthConBNum.num[largerIndex] - smallerLenghtConBNum.getDigit(smallerLength-1) < 0 && smallerLength !=  0){
				subtractingBothValuesString = (((largerLengthConBNum.num[largerIndex]-1)+10) - smallerLenghtConBNum.getDigit(smallerLength-1)) + subtractingBothValuesString;
				
				if(smallerLength >0){
					smallerLength--; 
					}
				if(largerIndex >0){
					largerIndex--;
					}
				regrouping = true; 
			}
			
			if(regrouping ==  true && largerLengthConBNum.num[largerIndex] - smallerLenghtConBNum.getDigit(smallerLength-1) >=  0 && smallerLength !=  0){
				subtractingBothValuesString = (((largerLengthConBNum.num[largerIndex])-1) - smallerLenghtConBNum.getDigit(smallerLength-1)) + subtractingBothValuesString;
				
				if(smallerLength >0){
					smallerLength--; 
					}
				if(largerIndex >0){
					largerIndex--;
					}
				regrouping = false; 
			}
			
			if(regrouping ==  false && largerLengthConBNum.num[largerIndex] - smallerLenghtConBNum.getDigit(smallerLength-1) >=  0 && smallerLength !=  0){
				subtractingBothValuesString = (((largerLengthConBNum.num[largerIndex])) - smallerLenghtConBNum.getDigit(smallerLength-1)) + subtractingBothValuesString;
				
				if(smallerLength >0){
					smallerLength--; 
					}
				if(largerIndex >0){
					largerIndex--;
					}
				regrouping = false; 
			}
			
	}//while  loop
		
		/*adding the rest of the number if the BNumLeght is smaller then the this.Lenght; 
		 * This for loop check if there's carrying over, that needs to been adding
		 * 
		 * */	
		for(int x = diferentLenghtBetweenBothValues-1; x >= 0; x--){		
				if(regrouping ==  true  &&  (largerLengthConBNum.num[x] - 1) < 0){
					subtractingBothValuesString = ((largerLengthConBNum.num[x] -1)+10) +  subtractingBothValuesString;
			         
			         //adding the very last digit to the end of the string
			     	if(x == 0){
			     	      subtractingBothValuesString = ((largerLengthConBNum.num[x] -1)) +  subtractingBothValuesString;
					}
			     	regrouping =  true; 
				}//end if
			      if(regrouping ==  true  &&  (largerLengthConBNum.num[x] - 1) > 0){
			    	  subtractingBothValuesString = (largerLengthConBNum.num[x] -1) +  subtractingBothValuesString;
			    	  regrouping =  false; 
		        }
		//check if the second last digit is not zero so it doesn't take away from the first and add that at the end 
		if(regrouping ==  false && largerLengthConBNum.num[1] != 0){
   	          subtractingBothValuesString = (largerLengthConBNum.num[x]) +  subtractingBothValuesString;
		}//end else
				
			}//end for loop	          
		
		
		//casting the first character to an int and subtracting 48 because of the ASSIIC value
		int  firstCharacter = (int)subtractingBothValuesString.charAt(0) -48;
		
		//Multiplying the first part of the string by the sign
		subtractingBothValuesString =  (firstCharacter*signofThisBNum) + subtractingBothValuesString.substring(1, subtractingBothValuesString.length());
		
		   //System.out.println( "subtractingBothValuesString " + subtractingBothValuesString);
		   
		}/*end of the long if statement */
		else{
			//else if the sign match up then call the addition method 
			ConBNum addingConBNum =  (ConBNum) copyingThisBNum.add(copyingBNum);
		
			for(int i = 0; i < addingConBNum.num.length; i++){
				subtractingBothValuesString += addingConBNum.getDigit(i);
			}//end for loop
			
		}//end else
		
			//creating a new object with the new values and returning it 
		
	  ConBNum subtractingConBNum = new ConBNum(subtractingBothValuesString); 
		
		return subtractingConBNum;

	}
	
	/*Returns the sign of this BigNumber object */
	public int getSign(){
		
		//variable to hold the sign 
		int signOfNumber = 0; 
		
		//if sign is positive  
		if(this.sign == 1){
			signOfNumber = 1;
		}
		//if sign is negative 
		if(this.sign == -1){
			signOfNumber = -1;
		}
		//return the value of this sign 
		return signOfNumber;
	}
	
	//Returns the digit i of this object, digit 0 is LSD.
	public int getDigit(int i){

		//variable to store the number
		int number = 0; 
		
		//if it's -1 then return the length
		if(i == -1){
			return this.num.length;
		}
		
		try{
			number = this.num[i]; //getting the number at the location requested 
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("\nOops, array out of bounds went to far, better go back to 0! , Early Termination \nReason:\t " + e.getMessage());
			return -1; 

		}
		return number;  //returning that number 

	}; // constructor


}
