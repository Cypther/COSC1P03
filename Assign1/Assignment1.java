//package assign1;

/**
 *
 * @author ln13ot
 */
import BasicIO.ASCIIDataFile;

/**
 * This program search the text file "wordsearch.dat" as a word Search, reads
 * the file into a char 2D array Display the result when done searching
 * 
 * 
 * @author Long Nguyen Student# 5427059
 * 
 * @version 1.0 (Jan. 2014)
 * 
 * */

public class Assignment1{

 // Class attributes:

 ASCIIDataFile in = new ASCIIDataFile("wordsearch.dat"); //read to be read in

 public char[][] wordArray = new char[21][]; //A ragged 2d array to sort in the words 
 public int wordRowNumber = 0; // This is a counter for wordArray look up
 public char[][] boardArray = new char[25][25]; // A 2d array for reading in the board with the random charters 
 public char[][] outPutDisplayArray = new char[25][25]; // A 2d array for outputting the final result when the word is found
 public int row, column, letter; 
 public String wordToBeSearch; 
 public boolean wordFound = false; // flag if word is found
 public Letters[][] character = new Letters[25][80]; // Letter Class
 char firstLetterOfTheWord; // the first letter of the word

 // Class constructor
 public Assignment1() {

  // These methods are called in the constructor

  ReadFile(); //Method to read in the file 
  SortLettersInBoard(); //Method to sort every character in the boardArray in alphabet order, by making a 2d array of the letter class

  wordToBeSearch = getWord(); // Words to be search
  int wordCounter = 0;  // counter for the while loop
  
  while(wordCounter < 21){
  firstLetterOfTheWord = wordToBeSearch.toUpperCase().charAt(0); //getting the first character of the string 
  int letterSearch = (int) firstLetterOfTheWord; //casting the first letter to an integer

  for (int i = 0; i < character.length; i++) { // looping throught the // character Array to // find the letters in // the word Search to
   // be // search

   for (int j = 0; j < character[i].length; j++) {
    /*if the character class column index is not out of bounds and the letter 
    is equal to the same letter as the first letter to be search */
    if (character[i][j] != null && character[i][j].getLetter() == letterSearch) { 
      
        if (wordFound == true  && wordRowNumber < 21) { //If the word is found, get a new word
            wordToBeSearch = getWord();
         }

     int rowIndex = character[i][j].getRow(); //getting the character class row index
     int columnIndex = character[i][j].getColumn(); //getting the character class column index

     readForWards(wordToBeSearch, rowIndex, columnIndex); //Method looks for the word to read for ward, by the index of the rows and columns
     readBackWards(wordToBeSearch, rowIndex, columnIndex);  //Method looks for the word  to read back ward, by the index of the rows and columns

     readColumnDown(wordToBeSearch, rowIndex, columnIndex); //Method looks for the word  to read column down, by the index of the rows and columns
     readColumnUP(wordToBeSearch, rowIndex, columnIndex);  //Method looks for the word   to read column up, by the index of the rows and columns

     readRightDownDiagonal(wordToBeSearch, rowIndex, columnIndex); //Method looks for the word  to read right down Diagonal, by the index of the rows and columns
     readRightUpDiagonal(wordToBeSearch, rowIndex, columnIndex);  //Method looks for the word  to read right up Diagonal, by the index of the rows and columns

     readLeftDownDiagonal(wordToBeSearch, rowIndex, columnIndex); //Method looks for the word  to read left down Diagonal, by the index of the rows and columns
     readLeftUpDiagonal(wordToBeSearch, rowIndex, columnIndex); //Method looks for the word  to read left up Diagonal, by the index of the rows and columns
     }//end if 

    }//end for loop
   }//end for loop
  wordCounter ++;
  }

  PrintDisplay();

 }

 private String getWord() { // getting the word from the word Array and  making into a string

  String word = "";// clear for white space

  for (int i = 0; i < wordArray[wordRowNumber].length; i++) {
   word += Character.toString(wordArray[wordRowNumber][i]);
  }
  wordFound = false;
  word.replaceAll("\\s+", "");// replacing the white spaces
   wordRowNumber++; // word counter
        return word; 
 }

 private void SortLettersInBoard() {

  char c; // character for checking letters and converting it to ASCII

  int letter = 65; // starting at A which is 65 and increasing to get the
       // next value
  int index = 0; // counters for adding new letters to the rows
  int addedLetter = 0; // counter for adding the same letters

  while (letter <= 90) { // The end of the ASCII character 'Z' which is 90
   for (int i = 0; i < boardArray.length; i++) { //loop through the boardArray Row
    for (int j = 0; j < boardArray[i].length; j++) { //loop through the boardArray Column
     c = (char) letter; // casing the letter to the equivalent ASCII character
     // System.out.print(c);
     if (c == boardArray[i][j]) { // If the character is the same letter,
             // as the board Array character 
             // created a new  character class  2d Array of objects class with the attributes of the "ROW", "COLUMN" and "CHARTACTER"
      character[index][addedLetter] = new Letters(i, j,(int) c); // Creating 2D array of character object
      addedLetter++; // add new columns once added the new Object is created 
     }// End if statement

    }// end forloop
   }// end while loop
   addedLetter = 0;//Resetting the column back to zero 
   index++; // Indexing for the next new letter
   letter++;// counter for added the next ASSCII letter
  }
 }

 //This printDisplay Method prints out the 2D array after it finished searching for the words 
 private void PrintDisplay() {
  
  //prints out how many words it found
  System.out.println("Found:" + wordRowNumber + "\n");

  //looping through the rowIndex
  for (int i = 0; i < outPutDisplayArray.length; i++) {
   //looping through the columnIndex
   for (int j = 0; j < outPutDisplayArray[i].length; j++) {
    if (outPutDisplayArray[i][j] == '\0') {
     System.out.print("  ");
    } else {
     System.out.print(outPutDisplayArray[i][j]);
    }
   }
   System.out.println(" ");
  }
 }

 //This method reads in the file line by line and converting it to char
 private void ReadFile() {

  // reading the 21 words into the array list
  for (int i = 0; i < wordArray.length; i++) {
   wordArray[i] = in.readLine().toCharArray();
  }
  for (int i = 0; i < boardArray.length; i++) {
   boardArray[i] = in.readLine().toCharArray();
  }
  in.close();

 }

 /*This method reads the letter that it is searching for for ward, by taking in the string of the word, 
  * and the row and column of the letter in the boardArray */
 private void readForWards(String wordToBeSearch, int row, int col) {

  String word = wordToBeSearch; 
  int rowIndex = row;
  int columnIndex = col;

  String wordMatch = "";

  int wordLength = word.length(); // reinitialize to check every time
  int outPutDisplayArrayRowIndex = rowIndex; //row index of the letter to be printed 
  int outPutDisplayArrayColumnIndex = columnIndex;  //column index of the letter to be printed 

  /*This is the code for searching for ward and making sure the column index is not out of bound*/
  while (wordLength != 0 && columnIndex < 25) {

   //This is concatenate the char into a string 
   wordMatch += Character.toString(boardArray[rowIndex][columnIndex]);

   columnIndex++; //increment the column index
   wordLength--;  //decrement the word length index

  }// /END WHILE LOOP
  
  //checking if the word matches the in the board array
  if (word.equalsIgnoreCase(wordMatch)) {
   wordFound = true; //boolean to set to true 

   int outPutDisplayArrayWordLength = word.length(); // reinitialize  to check everytime to print the word that's found letter on

   //This code prints out the word in the outPutDisplayArray
   while (outPutDisplayArrayWordLength != 0
     && outPutDisplayArrayColumnIndex < 25) {

    outPutDisplayArray[outPutDisplayArrayRowIndex][outPutDisplayArrayColumnIndex] = boardArray[outPutDisplayArrayRowIndex][outPutDisplayArrayColumnIndex];

    outPutDisplayArrayColumnIndex++; // Increasing the  row by one

    outPutDisplayArrayWordLength--;

   }//end while loop

  }//end if 
 }// end of Method
 
 /*This method reads the letter that it is searching for back wards, by taking in the string of the word, 
  * and the row and column of the letter in the boardArray */
private void readBackWards(String wordToBeSearch, int row, int col) {

  String word = wordToBeSearch;
  int rowIndex = row;
  int columnIndex = col;

  String wordMatch = "";

  int wordLength = word.length(); // reinitialize to check every time
  int outPutDisplayArrayRowIndex = rowIndex; //row index of the letter to be printed 
  int outPutDisplayArrayColumnIndex = columnIndex;   //column index of the letter to be printed 

  // This code does the searching backward
  while (wordLength != 0 && columnIndex >= 0) {

   //This is concatenate the char into a string 
   wordMatch += Character.toString(boardArray[rowIndex][columnIndex]); //
   columnIndex--; //decrement the column index
   wordLength--; //decrement the word length index

  }// /END WHILE LOOP
  
  //checking if the word matches the in the board array
  if (word.equalsIgnoreCase(wordMatch)) { 
   wordFound = true; //boolean to set to true 

   int outPutDisplayArrayWordLength = word.length(); // reinitialize  to check every time

   //This code prints out the word in the outPutDisplayArray
   while (outPutDisplayArrayWordLength != 0 && outPutDisplayArrayColumnIndex >= 0) {

    outPutDisplayArray[outPutDisplayArrayRowIndex][outPutDisplayArrayColumnIndex] = boardArray[outPutDisplayArrayRowIndex][outPutDisplayArrayColumnIndex];

    outPutDisplayArrayColumnIndex--; // decreasing the column by one
    outPutDisplayArrayWordLength--; // decreasing array word lenght by one

   }//end while loop
  }//end if
 }//end method 

/*This method reads the letter that it is searching for column down, by taking in the string of the word, 
 * and the row and column of the letter in the boardArray */
 private void readColumnDown(String wordToBeSearch, int row, int col) {

  String word = wordToBeSearch;
  int rowIndex = row;
  int columnIndex = col;

  String wordMatch = "";

  int wordLength = word.length(); // reinitialize to check every time
  int outPutDisplayArrayRowIndex = rowIndex; //row index of the letter to be printed 
  int outPutDisplayArrayColumnIndex = columnIndex; //column index of the letter to be printed

  // This code does the searching column down
  while (wordLength != 0 && rowIndex < 25) {

   //This is concatenate the char into a string 
   wordMatch += Character.toString(boardArray[rowIndex][columnIndex]);
            rowIndex++; //increment the row index
   wordLength--; //decrement the word length index

  }// /END WHILE LOOP
  
  //checking if the word matches the in the board array
  if (word.equalsIgnoreCase(wordMatch)) {
   wordFound = true; //boolean to set to true 

   int outPutDisplayArrayWordLength = word.length(); // reinitialize  to check every time

   //This code prints out the word in the outPutDisplayArray
   while (outPutDisplayArrayWordLength != 0
     && outPutDisplayArrayRowIndex < 25) {

    outPutDisplayArray[outPutDisplayArrayRowIndex][outPutDisplayArrayColumnIndex] = boardArray[outPutDisplayArrayRowIndex][outPutDisplayArrayColumnIndex];
    outPutDisplayArrayRowIndex++; // Increasing the row
    outPutDisplayArrayWordLength--; // decrease the the word outPutDisplayArrayWordLength

   }//end while

  }//end if
 }//end method 

 /*This method reads the letter that it is searching for column up, by taking in the string of the word, 
  * and the row and column of the letter in the boardArray */
 private void readColumnUP(String wordToBeSearch, int row, int col) {

  String word = wordToBeSearch;
  int rowIndex = row;
  int columnIndex = col;

  String wordMatch = "";

  int wordLength = word.length(); // reinitialize to check every time
  int outPutDisplayArrayRowIndex = rowIndex;  //row index of the letter to be printed 
  int outPutDisplayArrayColumnIndex = columnIndex;  //column index of the letter to be printed

  // This code does the searching column up
  while (wordLength != 0 && rowIndex >= 0) {

   //This is concatenate the char into a string 
   wordMatch += Character.toString(boardArray[rowIndex][columnIndex]);
   rowIndex--; //increment the row index
   wordLength--; //decrement the word length index

  }// /END WHILE LOOP
  
  //checking if the word matches the in the board array
  if (word.equalsIgnoreCase(wordMatch)) {
   wordFound = true; //boolean to set to true 

   int outPutDisplayArrayWordLength = word.length(); // reinitialize to check  every time
   
   
   //This code prints out the word in the outPutDisplayArray
   while (outPutDisplayArrayWordLength != 0
     && outPutDisplayArrayRowIndex >= 0) {

    outPutDisplayArray[outPutDisplayArrayRowIndex][outPutDisplayArrayColumnIndex] = boardArray[outPutDisplayArrayRowIndex][outPutDisplayArrayColumnIndex];

    outPutDisplayArrayRowIndex--; // decreasing the row by one
    outPutDisplayArrayWordLength--; // decreasing word lenght
    
   }//while loop
  }//end if
 }//end method 

 /*This method reads the letter that it is searching right down diagonal, by taking in the string of the word, 
  * and the row and column of the letter in the boardArray */
 private void readRightDownDiagonal(String wordToBeSearch, int row, int col) {

  String word = wordToBeSearch;
  int rowIndex = row;
  int columnIndex = col;

  String wordMatch = "";
  
  int wordLength = word.length(); // reinitialize to check  every time
  int outPutDisplayArrayrowIndex = rowIndex; //row index of the letter to be printed 
  int outPutDisplayArraycolumnIndex = columnIndex; //column index of the letter to be printed


  // This code does the searching right down diagonal 
  while (wordLength != 0 && rowIndex < 25 && columnIndex < 25) {

   //This is concatenate the char into a string 
   wordMatch += Character.toString(boardArray[rowIndex][columnIndex]);
   columnIndex++; //increment the column index
   rowIndex++; //increment the row index
   wordLength--; //decrement the word length

  }// /END WHILE LOOP

  //checking if the word matches the in the board array
  if (word.equalsIgnoreCase(wordMatch)) {
   wordFound = true; //boolean to set to true

   int outPutDisplayArraywordLength = word.length(); // reinitialize to check every time

   //This code prints out the word in the outPutDisplayArray
   while (outPutDisplayArraywordLength != 0
     && outPutDisplayArrayrowIndex < 25
     && outPutDisplayArraycolumnIndex < 25) {

    outPutDisplayArray[outPutDisplayArrayrowIndex][outPutDisplayArraycolumnIndex] = boardArray[outPutDisplayArrayrowIndex][outPutDisplayArraycolumnIndex];

    outPutDisplayArrayrowIndex++; // increasing the row by one
    outPutDisplayArraycolumnIndex++; // Increasing the column by one

    outPutDisplayArraywordLength--;
   }//end while loop
  }//end if 
 }//end method 

 /*This method reads the letter that it is searching right up diagonal, by taking in the string of the word, 
  * and the row and column of the letter in the boardArray */
 private void readRightUpDiagonal(String wordToBeSearch, int row, int col) {

  String word = wordToBeSearch;
  int rowIndex = row;
  int columnIndex = col;

  String wordMatch = "";

  int wordLength = word.length(); // reinitialize to check every time
  int outPutDisplayArrayrowIndex = rowIndex;  //row index of the letter to be printed 
  int outPutDisplayArraycolumnIndex = columnIndex; //column index of the letter to be printed

  // This code does the searching right up diagonal 
  while (wordLength != 0 && rowIndex != 0 && columnIndex < 25) {

   //This is concatenate the char into a string 
   wordMatch += Character.toString(boardArray[rowIndex][columnIndex]);
   rowIndex--; //row the row index
   columnIndex++; //increment the column index
   wordLength--; //decrement the column index

  }// /END WHILE LOOP

  //checking if the word matches the in the board array
  if (word.equalsIgnoreCase(wordMatch)) {
   wordFound = true;  //boolean to set to true


   int outPutDisplayArraywordLength = word.length(); // reinitialize to check every time

   //This code prints out the word in the outPutDisplayArray
   while (outPutDisplayArraywordLength != 0
     && outPutDisplayArrayrowIndex != 0
     && outPutDisplayArraycolumnIndex < 25) {

    outPutDisplayArray[outPutDisplayArrayrowIndex][outPutDisplayArraycolumnIndex] = boardArray[outPutDisplayArrayrowIndex][outPutDisplayArraycolumnIndex];

    outPutDisplayArrayrowIndex--; // decreasing the row by one
    outPutDisplayArraycolumnIndex++; // increasing the column by one
    outPutDisplayArraywordLength--;// decreasing the word lenght by one
   }//end while
  }//end if
 }//end method 

 /*This method reads the letter that it is searching left down diagonal, by taking in the string of the word, 
  * and the row and column of the letter in the boardArray */
 private void readLeftDownDiagonal(String wordToBeSearch, int row, int col) {

  String word = wordToBeSearch;
  int rowIndex = row;
  int columnIndex = col;

  String wordMatch = "";

  int wordLength = word.length(); // reinitialize to check every time
  int outPutDisplayArrayrowIndex = rowIndex;  //row index of the letter to be printed 
  int outPutDisplayArraycolumnIndex = columnIndex; //column index of the letter to be printed

  // This code does the searching right down diagonal 
  while (wordLength != 0 && rowIndex < 25 && columnIndex != 0) {

   //This is concatenate the char into a string 
   wordMatch += Character.toString(boardArray[rowIndex][columnIndex]);
   rowIndex++; //increment the row index
   columnIndex--; //decrement the column index
   wordLength--; //decrement the word index

  }// /END WHILE LOOP
  
  //checking if the word matches the in the board array
  if (word.equalsIgnoreCase(wordMatch)) {
   wordFound = true; //boolean to set to true

   int outPutDisplayArraywordLength = word.length(); // reinitialize to check every time

   //This code prints out the word in the outPutDisplayArray
   while (outPutDisplayArraywordLength != 0
     && outPutDisplayArrayrowIndex < 25
     && outPutDisplayArraycolumnIndex != 0) {

    outPutDisplayArray[outPutDisplayArrayrowIndex][outPutDisplayArraycolumnIndex] = boardArray[outPutDisplayArrayrowIndex][outPutDisplayArraycolumnIndex];

    outPutDisplayArrayrowIndex++; // Increasing the row  by one
    outPutDisplayArraycolumnIndex--; // Deceasing the column by one
    outPutDisplayArraywordLength--; // Deceasing the word by one
   }//end while
  }//end if
 }//end method 
 
 /*This method reads the letter that it is searching right up diagonal, by taking in the string of the word, 
  * and the row and column of the letter in the boardArray */
   private void readLeftUpDiagonal(String wordToBeSearch, int row, int col) {

  String word = wordToBeSearch;
  int rowIndex = row;
  int columnIndex = col;

  String wordMatch = "";

  int wordLength = word.length(); // reinitialize to check everytime
  int outPutDisplayArrayrowIndex = rowIndex;
  int outPutDisplayArraycolumnIndex = columnIndex;

  // This code does the searching left up diagonal
  while (wordLength != 0 && rowIndex != 0 && columnIndex != 0) {
   //This is concatenate the char into a string 
   wordMatch += Character.toString(boardArray[rowIndex][columnIndex]);
   rowIndex--;  //decrement the row index
   columnIndex--; //decrement the column index
   wordLength--; //decrement the word lenght 

  }// /END WHILE LOOP

  //checking if the word matches the in the board array
  if (word.equalsIgnoreCase(wordMatch)) {
   wordFound = true; //boolean to set to true

   int outPutDisplayArraywordLength = word.length(); // reinitialize  to check every time
   
   //This code prints out the word in the outPutDisplayArray
   while (outPutDisplayArraywordLength != 0
     && outPutDisplayArrayrowIndex != 0
     && outPutDisplayArraycolumnIndex != 0) {

    outPutDisplayArray[outPutDisplayArrayrowIndex][outPutDisplayArraycolumnIndex] = boardArray[outPutDisplayArrayrowIndex][outPutDisplayArraycolumnIndex];

    outPutDisplayArrayrowIndex--; // decreasing the row  by one
    outPutDisplayArraycolumnIndex--; // decreasing the column by one
    outPutDisplayArraywordLength--;
   }//end while loop
  }//end if 
 }//end method 
   
   /*This is the class letters, the purpose of this class is to help sort All the letters in order by A,B,C,D.....etc.. 
    *And add in the location of the rows and columns of where the letters are in boardArray on the word search for a easier way to search*/
   
   public class Letters {

      // Class attributes:
  private int rowLocation;
  private int columnLocation;
  private int letterInASCII;

  // Class constructor
     public Letters(int row, int column, int letter) {
      rowLocation = row;
      columnLocation = column;
      letterInASCII = letter;
     }
     
     //method of the Letter class 
     public int getRow() {
      return rowLocation;
     }
         
     public int getColumn() {
      return columnLocation;
     }
     
     public int getLetter() {
      return  letterInASCII;
     }
     
 }

 public static void main(String[] args) {

  new Assignment1();

 }

}
