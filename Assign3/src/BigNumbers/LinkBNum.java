/** This class  LinkBNum implements BNum methods 
 * It performs a number of tests including add, subtract, clone, getDigit
  * lessThan, getSign and tests of the exceptions.
  *
  *  *Name: Long Nguyen: Student # 5427059
  *
  * @version  1.0 (Mar. 2014)                                                    */

package BigNumbers;

import java.io.*;

public class LinkBNum implements BNum, Serializable {

	public int num;
	public LinkBNum nextLink;
	public LinkBNum head = null;
	public int lenghtOfNumber;
    public  int sign;

	// method to insert the values into the linkLists
	public void insert(char[] s) {

		// converting the object that's being passed in as an character array
		char[] characters = String.valueOf(s).toCharArray();
		if (characters[0] == '-') { // checking to see if the number is negative
			this.sign = -1; // Setting the sign to a negative one because the
							// value is negative
			this.lenghtOfNumber = characters.length - 1; // getting the length
															// of the number;

			/*
			 * Starting at the end of the character array and loop backward to
			 * create the linklisted with the values
			 */
			for (int i = characters.length - 1; i >= 1; i--) {
				LinkBNum link = new LinkBNum(characters[i]); // a new linklisted
																// one by one
				link.nextLink = head;
				head = link;

			}// end for loop
		} else {// if the character is not negative
			this.sign = 1; // if the value is positive then set the sign to
							// positive 1
			this.lenghtOfNumber = characters.length; // getting the length of
														// the number;
			// putting the numbers in the opposite order
			for (int i = characters.length - 1; i >= 0; i--) {
				LinkBNum link = new LinkBNum(characters[i]); // a new linklisted
																// one by one
				link.nextLink = head;
				head = link;
			}
		}// end else
	}// end insert method

	/////////////////////////////////////////////////////////
	///optional method 
	public void printList() {
		LinkBNum currentLink = head;
		System.out.print("List: ");
		while (currentLink != null) {
			currentLink.printLink();
			currentLink = currentLink.nextLink;
		}
		System.out.println("");
	}
//////////////////////////////////////////////////////////////////
	/**
	 * This default constructor ConBNum , construct an object with positive zero
	 */

	// Print Link data
	public void printLink() {
		System.out.print(num + " ");
	}

	public LinkBNum() {

		this(0); // creates an object with a positive zero

	}; // constructor

	/** This constructor produces a Long of the form: */

	public LinkBNum(long n) {

		char[] characters = String.valueOf(n).toCharArray();
		
		/*Check to see if the strings is a valid number
		 * If it not a valid number then throw a run time exception
		 * */
		for (char c :characters) {
			try {
				if (Character.isLetter(c)) throw new BigNumbersException(c + " Not a  valid number!");
			}
			catch (BigNumbersException e) {
				System.out.println("\n There seems to be a problem:  "+ "This is not a valid "+ c + e.getMessage());
			}
		}
		
		insert(characters);

	}; // constructor

	//part of the linked Listed when creating the lists with the values
	public LinkBNum(char n) {

		num = (int) n - 48; // Subtract 48 because of assiic value

	}

	/** This constructor produces a String of the form: */

	public LinkBNum(String s) {

		s = s.trim(); // Trimming the white spaces of the string
		char[] characters = String.valueOf(s).toCharArray();
		
		/*Check to see if the strings is a valid number
		 * If it not a valid number then throw a run time exception
		 * */
		for (char c :characters) {
			try {
			if (Character.isLetter(c)) throw new BigNumbersException(c + " Not a  valid number!");
			}
			catch (BigNumbersException e) {
				System.out.println("\n There seems to be a problem:  "+ "This is not a valid, "+ c +"! "+ e.getMessage());
			}
		}
		
		insert(characters);

	}; // constructor
	
	public String BNumValue(BNum n){
		
		int signOfN = n.getSign(); //Getting the sign of BNum N
		
		int counter = 0;  //Counter for the while loop
		String valueOfNString = ""; //Variable to hold the numbers of BNum N
		int lenght = n.getDigit(-1);
		
		  while(counter <= lenght-1){
			  valueOfNString += n.getDigit(counter); 
			counter++; 
			//System.out.println(valueOfNinString );
		}
		  int  firstCharacterOfN = (int) valueOfNString.charAt(0) -48;
		  valueOfNString =  (firstCharacterOfN*signOfN) + valueOfNString.substring(1, valueOfNString.length());
	
		return valueOfNString;
	}

	// Create a clone of this
	public BNum clone() {

		//variable to hold the thisNumberValue in string form
		String thisNumberValue = "";
		int getSign = this.getSign(); 

		LinkBNum referenceLink = this.head; // reference pointer to head

		//looping through the linkedlisted to get the values 
		while (this.head != null) {
			thisNumberValue += this.head.num;
			this.head = this.head.nextLink;
		}
		this.head = referenceLink; // setting the pointer back to the head
		
		//casting the first character to an int and subtracting 48 because of the ASSIIC value
		int  firstCharacter = (int)thisNumberValue.charAt(0) -48;
		
		//Multiplying the first part of the string by the sign
		thisNumberValue =  (firstCharacter*getSign) + thisNumberValue.substring(1, thisNumberValue.length());

		//creating a new object with that same value
		BNum cloneLink = new LinkBNum(thisNumberValue);
		return cloneLink;
	}

	/* Returns true if 'this' = n */
	public boolean equals(BNum n) {
		
    boolean equals = false; 
		
        String valueOfNinString = BNumValue(n); //Getting the value of BNum		
	    LinkBNum copyingBNum =  new LinkBNum(valueOfNinString); //creating a copy of the BNum n that's being passed in
		int lengthOfBNum =  copyingBNum.lenghtOfNumber;//getting the length of BNum that's being passed in
		
		/*If the length are equal then 
		loop through this check to see if the values are equal*/
		for(int i = 0; i < this.lenghtOfNumber; i++){	
				  if (lengthOfBNum == this.lenghtOfNumber && this.getDigit(i) == copyingBNum.getDigit(i)){
					  equals = true; 
				  }else{
					  equals = false; 
				  }     
		}//end for loop

		return equals; //return the values if it's equal

	}

	/* Returns true if 'this' < n */
	public boolean lessThan(BNum n) {
		
		boolean lessThan = false; 
		String copyingThisBNumString = "";
		String copyingBNumString = "";
		
		String valueOfNinString = BNumValue(n); //Getting the value of BNum		
		LinkBNum copyingBNum =  new LinkBNum(valueOfNinString); //creating a copy of the BNum n that's being passed in
		
		LinkBNum copyingThisBNum =  (LinkBNum)this.clone(); //creating a copy of the BNumThis that's calling the method
		
		//find the different between the two lengths
		int diferentLenghtBetweenBothValues=  Math.abs(Math.abs(copyingThisBNum.lenghtOfNumber) - Math.abs(copyingBNum.lenghtOfNumber));
		
		//adding the values to the copyingThisBNum string
		for(int i = 0; i < copyingThisBNum.lenghtOfNumber; i++){ 
			copyingThisBNumString  += copyingThisBNum.getDigit(i);
		}
		
		//adding the values to the copyingBNum string 
		for(int i = 0; i < copyingBNum.lenghtOfNumber; i++){
			copyingBNumString  += copyingBNum.getDigit(i);
		}
		
		//check what length is longer, if one of the lengths is longer then pad the beginning with leading zeros 
		if(copyingThisBNumString.length() < copyingBNumString.length()){
		for(int x = diferentLenghtBetweenBothValues-1; x >= 0; x--){	
			copyingThisBNumString = 0 + copyingThisBNumString; 
		}
		}//end for loop
		
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
		System.out.println(copyingThisBNumString.compareTo( copyingBNumString ));
	
		return lessThan; 
	}

	/* returns 'this' + n */
	public BNum add(BNum n) {
		
		String addingBothValuesString = ""; //String to adding all the digit together 
		boolean remainder = false; //Setting the remainder to false by default
		
		String valueOfNinString = BNumValue(n); //Getting the value of BNum		
		LinkBNum copyingBNum =  new LinkBNum(valueOfNinString); //creating a copy of the BNum n that's being passed in
		
		LinkBNum copyingThisBNum =  (LinkBNum)this.clone(); //creating a copy of the BNumThis that's calling the method
		
		/*Getting the sign of both values before deciding to add or subtract */
		int signOfBNum = copyingBNum.getSign(); 
		int signofThisBNum = copyingThisBNum.getSign(); 
		
		if(signOfBNum == signofThisBNum){
		
		/////////////////////////////////////////////////////////////////////////////	
		int  smallerLength = 0; // variable to hold the smaller length
		int largerIndex = 0;  // variable to hold the bigger length 
		LinkBNum  largerLengthConBNum = null;
		LinkBNum smallerLenghtConBNum = null; 
	
		/*Find out which BNum length is bigger 
		 * length is less or greater then*/
		if(copyingThisBNum.lenghtOfNumber <  copyingBNum.lenghtOfNumber){
		          smallerLength = copyingThisBNum.lenghtOfNumber;  // getting the lenght of the value 
		          largerLengthConBNum = copyingBNum; 
		          smallerLenghtConBNum = copyingThisBNum; 
		          largerIndex  = largerLengthConBNum.lenghtOfNumber-1; //starting at the last index 
		}
		/*Find out which BNum length is bigger 
		 * length is less or greater then*/
		if(copyingThisBNum.lenghtOfNumber >  copyingBNum.lenghtOfNumber){
		         smallerLength =  copyingBNum.lenghtOfNumber; 
		         largerLengthConBNum = copyingThisBNum; 
		         smallerLenghtConBNum = copyingBNum; 
		         largerIndex  = largerLengthConBNum.lenghtOfNumber-1; //starting at the last index 
		}
		/*Find out which BNum length is bigger 
		 * length is less or greater then*/
		if(copyingThisBNum.lenghtOfNumber ==  copyingBNum.lenghtOfNumber && copyingThisBNum.getDigit(0) < copyingBNum.getDigit(0)){
		       smallerLength = copyingThisBNum.lenghtOfNumber;  // getting the lenght of the value 
		          largerLengthConBNum = copyingBNum; 
		          smallerLenghtConBNum = copyingThisBNum; 
		          largerIndex  = largerLengthConBNum.lenghtOfNumber-1; //starting at the last index 
	}
		/*Find out which BNum length is bigger 
		 * length is less or greater then*/
		if(copyingThisBNum.lenghtOfNumber ==  copyingBNum.lenghtOfNumber && copyingThisBNum.getDigit(0) >= copyingBNum.getDigit(0)){
		     smallerLength =  copyingBNum.lenghtOfNumber; 
	         largerLengthConBNum = copyingThisBNum; 
	         smallerLenghtConBNum = copyingBNum; 
	         largerIndex  = largerLengthConBNum.lenghtOfNumber-1; //starting at the last index 
	}
		
		//getting the different between the two values and calling the Math absolute value
		int diferentLenghtBetweenBothValues=  Math.abs(Math.abs(copyingThisBNum.lenghtOfNumber) - Math.abs(copyingBNum.lenghtOfNumber)); 
		//System.out.println(diferentLenghtBetweenBothValues);
		/*This while loop add the numbers together in both Arrays, each element at a time
		 * If the smallerLenght is less then zero, then it will break out of the loop
		 *  
		 * */
		
		while (smallerLength > 0 ){
			
			/*This if statement checks if adding both values together is less then 10, 
			 * and BNumLenght is not equal to 0
			 * */
			if(remainder ==  false && largerLengthConBNum.getDigit(largerIndex) + smallerLenghtConBNum.getDigit(smallerLength-1) < 10 && smallerLength !=  0){
				addingBothValuesString = largerLengthConBNum.getDigit(largerIndex) + smallerLenghtConBNum.getDigit(smallerLength-1) + addingBothValuesString;
				smallerLength--; 
				if(largerIndex >0){
					largerIndex--;
					}
				remainder = false; 
			}	
			
			/*This if statement checks if adding both values together plus a 1 is less then 10, 
			 * and BNumLenght is not equal to 0
			 * */
			if(remainder ==  true && largerLengthConBNum.getDigit(largerIndex) + smallerLenghtConBNum.getDigit(smallerLength-1) < 10 && smallerLength !=  0){
				addingBothValuesString = ((largerLengthConBNum.getDigit(largerIndex) + smallerLenghtConBNum.getDigit(smallerLength-1))+1) + addingBothValuesString;
				
				smallerLength--; 
				if(largerIndex >0){
					largerIndex--;
					}
				remainder = false; 
			}
			
			/*This if statement checks if adding both values together  is greater then 10, 
			 * and BNumLenght is not equal to 0
			 * */
			if(remainder ==  false && largerLengthConBNum.getDigit(largerIndex) + smallerLenghtConBNum.getDigit(smallerLength-1) >= 10 && smallerLength !=  0){
				addingBothValuesString = ((largerLengthConBNum.getDigit(largerIndex) + smallerLenghtConBNum.getDigit(smallerLength-1))%10) + addingBothValuesString;
				
				smallerLength--; 
				if(largerIndex >0){
					largerIndex--;
					}
				remainder = true; 
			}//end if
			
			/*This if statement checks if adding both values together  plus the remainder is greater then 10, 
			 * and BNumLenght is not equal to 0
			 * */
			if(remainder ==  true && largerLengthConBNum.getDigit(largerIndex) + n.getDigit(smallerLength-1) >= 10 && smallerLength !=  0){
				addingBothValuesString = (((largerLengthConBNum.getDigit(largerIndex) + smallerLenghtConBNum.getDigit(smallerLength-1))+1) %10) + addingBothValuesString;

				smallerLength--; 
				if(largerIndex >0){
					largerIndex--;
					}
				remainder = true; 
			}
			
			if(largerIndex == 0 && remainder == true && smallerLength !=  0){
				addingBothValuesString = (((largerLengthConBNum.getDigit(0)+ smallerLenghtConBNum.getDigit(0))%10)+1) +  addingBothValuesString;
				addingBothValuesString =1 + addingBothValuesString; 
				break; 
			}
			
			if(largerIndex == 0 && remainder == false && smallerLength !=  0){	
					addingBothValuesString = (((largerLengthConBNum.getDigit(0) + smallerLenghtConBNum.getDigit(0)))) + addingBothValuesString;	
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
				if(remainder ==  true  &&  (largerLengthConBNum.getDigit(x) +1) >= 10){
			         addingBothValuesString = (largerLengthConBNum.getDigit(x) +1)%10 +  addingBothValuesString;
			         
			         //adding the very last digit to the end of the string
			     	if(x == 0){
						addingBothValuesString = 1 +  addingBothValuesString;
					}
			          remainder =  true; 
				}//end if
			      if(remainder ==  true  &&  (largerLengthConBNum.getDigit(x) +1) < 10){
				         addingBothValuesString = (largerLengthConBNum.getDigit(x) +1) +  addingBothValuesString;
				          remainder =  false; 
		      }if(remainder ==  false){
					addingBothValuesString = (largerLengthConBNum.getDigit(x)) +  addingBothValuesString;
				}//end else
				
			}//end for loop	          
			
			//casting the first character to an int and subtracting 48 because of the ASSIIC value
			int  firstCharacter = (int)addingBothValuesString.charAt(0) -48;
			
			//Multiplying the first part of the string by the sign
			 addingBothValuesString =  (firstCharacter*signofThisBNum) + addingBothValuesString.substring(1, addingBothValuesString.length());
		
		   //System.out.println( "addingBothValuesString " + addingBothValuesString);
		   
		 //creating a new object with the new values and returning it 
		
		}/**********end of  if very top *********/
		
		else{
			 //else if the sign match up then call the subtraction method 
			  LinkBNum addingConBNum =  (LinkBNum) copyingThisBNum.sub(copyingBNum);
		
			 for(int i = 0; i < addingConBNum.lenghtOfNumber; i++){
				 addingBothValuesString += addingConBNum.getDigit(i);
			}
			
		}
		//creating a new LinkBNum object and passing it back 
		LinkBNum addingConBNum = new LinkBNum(addingBothValuesString); 
			
		
		return addingConBNum;
	}

	/* returns 'this' - n */
	public BNum sub(BNum n) {
		
		String subtractingBothValuesString = ""; //String to adding all the digit together 
		boolean regrouping = false; //Setting the remainder to false by default
	
		String valueOfNinString = BNumValue(n); //Getting the value of BNum		
		LinkBNum copyingBNum =  new LinkBNum(valueOfNinString); //creating a copy of the BNum n that's being passed in
		
		LinkBNum copyingThisBNum =  (LinkBNum)this.clone(); //creating a copy of the BNumThis that's calling the method
		
		/*Getting the sign of both values before deciding to add or subtract
		 * if the sign are the same then add, if they are different the substact */
		int signOfBNum = copyingBNum.getSign(); 
		int signofThisBNum = copyingThisBNum.getSign(); 
		
		if(signOfBNum == signofThisBNum){
		
		/////////////////////////////////////////////////////////////////////////////	
		int  smallerLength = 0; // variable to hold the smaller length
		int largerIndex = 0;  // variable to hold the bigger length 
		LinkBNum  largerLengthConBNum = null;
		LinkBNum smallerLenghtConBNum = null; 
	
		/*Find out which BNum length is bigger 
		 * length is less or greater then*/
		if(copyingThisBNum.lenghtOfNumber <  copyingBNum.lenghtOfNumber){
		          smallerLength = copyingThisBNum.lenghtOfNumber;  // getting the lenght of the value 
		          largerLengthConBNum = copyingBNum; 
		          smallerLenghtConBNum = copyingThisBNum; 
		          largerIndex  = largerLengthConBNum.lenghtOfNumber-1;
		}
		/*Find out which BNum length is bigger 
		 * length is less or greater then*/
		if(copyingThisBNum.lenghtOfNumber >  copyingBNum.lenghtOfNumber){
		         smallerLength =  copyingBNum.lenghtOfNumber; 
		         largerLengthConBNum = copyingThisBNum; 
		         smallerLenghtConBNum = copyingBNum; 
		         largerIndex  = largerLengthConBNum.lenghtOfNumber-1;
		}
		/*Find out which BNum length is bigger 
		 * length is less or greater then*/
		if(copyingThisBNum.lenghtOfNumber ==  copyingBNum.lenghtOfNumber && copyingThisBNum.getDigit(0) < copyingBNum.getDigit(0)){
		       smallerLength = copyingThisBNum.lenghtOfNumber;  // getting the lenght of the value 
		          largerLengthConBNum = copyingBNum; 
		          smallerLenghtConBNum = copyingThisBNum; 
		          largerIndex  = largerLengthConBNum.lenghtOfNumber-1;
	}
		/*Find out which BNum length is bigger 
		 * length is less or greater then*/
		if(copyingThisBNum.lenghtOfNumber ==  copyingBNum.lenghtOfNumber && copyingThisBNum.getDigit(0) >= copyingBNum.getDigit(0)){
		     smallerLength =  copyingBNum.lenghtOfNumber; 
	         largerLengthConBNum = copyingThisBNum; 
	         smallerLenghtConBNum = copyingBNum; 
	         largerIndex  = largerLengthConBNum.lenghtOfNumber-1;
	}
		
		//getting the different between the two values and calling the Math absolute value
		int diferentLenghtBetweenBothValues=  Math.abs(Math.abs(copyingThisBNum.lenghtOfNumber) - Math.abs(copyingBNum.lenghtOfNumber)); 
		
		/*This while loop add the numbers together in both Arrays, each element at a time
		 * If the smallerLenght is less then zero, then it will break out of the loop
		 *  
		 * */
		
		while (smallerLength > 0 ){
			
			/*This if statement checks if adding both values together is less then 10, 
			 * and BNumLenght is not equal to 0
			 * */
			//System.out.println(largerLengthConBNum.num[largerIndex]); 
			if(regrouping ==  false && largerLengthConBNum.getDigit(largerIndex) - smallerLenghtConBNum.getDigit(smallerLength-1) < 0 && smallerLength !=  0){
				subtractingBothValuesString = (largerLengthConBNum.getDigit(largerIndex)+10) - smallerLenghtConBNum.getDigit(smallerLength-1) + subtractingBothValuesString;
				smallerLength--; 
				if(largerIndex >0){
				largerIndex--;
				}
				regrouping = true; 
			}	
			
			if(regrouping ==  true && largerLengthConBNum.getDigit(largerIndex) - smallerLenghtConBNum.getDigit(smallerLength-1) < 0 && smallerLength !=  0){
				subtractingBothValuesString = (((largerLengthConBNum.getDigit(largerIndex)-1)+10) - smallerLenghtConBNum.getDigit(smallerLength-1)) + subtractingBothValuesString;
				
				smallerLength--; 
				if(largerIndex >0){
					largerIndex--;
					}
				regrouping = true; 
			}
			
			if(regrouping ==  true && largerLengthConBNum.getDigit(largerIndex) - smallerLenghtConBNum.getDigit(smallerLength-1) >=  0 && smallerLength !=  0){
				subtractingBothValuesString = (((largerLengthConBNum.getDigit(largerIndex)-1)) - smallerLenghtConBNum.getDigit(smallerLength-1)) + subtractingBothValuesString;
				
				smallerLength--; 
				if(largerIndex >0){
					largerIndex--;
					}
				regrouping = false; 
			}
			
			if(regrouping ==  false && largerLengthConBNum.getDigit(largerIndex)- smallerLenghtConBNum.getDigit(smallerLength-1) >=  0 && smallerLength !=  0){
				subtractingBothValuesString = (((largerLengthConBNum.getDigit(largerIndex))) - smallerLenghtConBNum.getDigit(smallerLength-1)) + subtractingBothValuesString;
				
				smallerLength--; 
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
				if(regrouping ==  true  &&  (largerLengthConBNum.getDigit(x) - 1) < 0){
					subtractingBothValuesString = ((largerLengthConBNum.getDigit(x) -1)+10) +  subtractingBothValuesString;
			         
			         //adding the very last digit to the end of the string
			     	if(x == 0){
			     	      subtractingBothValuesString = ((largerLengthConBNum.getDigit(x) -1)) +  subtractingBothValuesString;
					}
			     	regrouping =  true; 
				}//end if
			      if(regrouping ==  true  &&  (largerLengthConBNum.getDigit(x) - 1) > 0){
			    	  subtractingBothValuesString = (largerLengthConBNum.getDigit(x) -1) +  subtractingBothValuesString;
			    	  regrouping =  false; 
		        }
		//check if the second last digit is not zero so it doesn't take away from the first and add that at the end 
		if(regrouping ==  false && largerLengthConBNum.getDigit(x) != 0){
   	          subtractingBothValuesString = (largerLengthConBNum.getDigit(x)) +  subtractingBothValuesString;
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
			LinkBNum addingConBNum =  (LinkBNum) copyingThisBNum.add(copyingBNum);
		
			for(int i = 0; i < addingConBNum.lenghtOfNumber; i++){
				subtractingBothValuesString += addingConBNum.getDigit(i);
			}//end for loop
			
		}//end else
		
			//creating a new object with the new values and returning it 
		
		LinkBNum subtractingConBNum = new LinkBNum(subtractingBothValuesString); 
		
		return subtractingConBNum;

	}

	/* Returns the sign of this BigNumber object */
	public int getSign() {
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

	// Returns the digit i of this object, digit 0 is LSD.
	public int getDigit(int i) {

		int toDigit = 0;
		int counter = 0;
		
		//if it's -1 then return the length
		if(i == -1){
			return this.lenghtOfNumber;
		}
		
		 LinkBNum referenceLink = this.head; // reference pointer to head

		try {

			//while (this.head != null && counter <= i) {
		     while (counter <= i) {
				 //System.out.print(this.head.num);
				toDigit = this.head.num;
				this.head = this.head.nextLink;
				counter++;
			}//end while 
			this.head = referenceLink; // return the reference pointer to head

		} catch (NullPointerException e) {
			this.head = referenceLink; // return the reference pointer to head if the Exception has been called
			// System.out.print("Early Termination \nReason:\t" +
			// mc.getMessage());
			//System.out.println("Linklisted out of bounds!");
			return -1; 
		}

		return toDigit;
	}; // constructor

}