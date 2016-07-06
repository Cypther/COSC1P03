/** This class MazeWalk, solve a maze  using recursive calls 
 * It asks the user for a starting location in the maze and solves it
 * from that location and prints the file.
  *
  *  *Name: Long Nguyen: Student # 5427059
  *
  * @version  1.0 (Mar. 2014)                                                    */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import BasicIO.*;

public class MazeWalk {
 
    int row = 0; //Row size of the Maze
    int column = 0; //Column size of the Maze
    
 ASCIIDataFile in=new ASCIIDataFile("mz1.txt");//reading in the maze text file
  char[][] mazeArray;//size of the array
  boolean exit = false; 
  
  boolean[][] visited; //tracking location if it's been visited 
 
 public MazeWalk(){
  
  //fields for input when getting values 
  int field1 = 0; 
  int field2 =0;
  
  //creating a basic form 
  BasicForm form = new BasicForm();
  
  //Creating the Maze.txt file to write too
  PrintWriter writer = null;
  try {
   writer = new PrintWriter("Maze.txt", "UTF-8");
  } catch (FileNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  } catch (UnsupportedEncodingException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }

  //Fields of the forms
   form.addTextField("field1", "X");
  form.addTextField("field2", "Y");
  form.writeString("field1", "1");
  form.writeString("field2", "1");
  form.accept();
  
  field1 = form.readInt("field1"); //reading from field1
     field2 = form.readInt("field2"); //reading from field2
     
     form.hide();//hiding the form 
     ReadFile();//calling the reading file method
     
      findPath(field1, field2);//
     
     //printing out the Maze after it's been solve
      for (int i = 0; i < mazeArray.length; i++) { //row
       for (int j = 0; j < mazeArray[i].length; j++) {//column
        if(mazeArray[i][j] == ' '){//if there's a white space 
         //outPut.writeChar(' '); //writing out the output to file
         writer.print(" ");
          System.out.print(" "); //print line
          }
       else{ //print the value
         //writing out the output to file
        writer.print(mazeArray[i][j]);
      System.out.print( mazeArray[i][j]);
       }
       }
      //writing a new line to the file
       writer.println();
         System.out.println();
  }//end for loop

      //closing the file
   writer.close();
   //closing the form
   form.close();
 }
 
 /*recursive method that calls itself until it finds 'E'/exit*/
 
 public void findPath(int x, int y){
  //System.out.println("OutSide Value of x " + x + "Value of y " + y);
  //System.out.println("x = " + x + " y = " + y);
  if(mazeArray[x][y] == 'E') {
   System.out.println("Found"); 
  exit = true; return;}
   if(mazeArray[x][y] == '#' || visited[x][y] == true ){
   return;
  } //else{
   
    visited[x][y] = true;//been here
   //mazeArray[x][y] = '.';
   
   
while(mazeArray[x][y] != 'E'){
 
 /*Going Forward*/
  if(exit == false) {
     mazeArray[x][y] ='>'; 
  findPath(x,y+1); 
  }
  /*Going backward*/
          if(exit == false) {
           mazeArray[x][y] ='<'; 
           findPath(x,y-1); 
           }
    /*going up*/
     if(exit == false) {
      mazeArray[x][y] ='^'; 
      findPath(x-1,y); 
      }
     /*going down*/
          if(exit == false) { 
           mazeArray[x][y] ='V';
           findPath(x+1,y);
           }
            /*If checked all the position*/
          if(mazeArray[x][y] == 'V') {
           mazeArray[x][y] ='.';
           }
   return; 
}
      
   
 } //end of findPath method 
 
 private void ReadFile() {
  
   row = in.readInt(); //getting the row size of the Maze
   column = in.readInt(); //getting the column size of the Maze
   mazeArray = new char [row][column]; //creating the Maze base on the values when reading in
   visited = new boolean [row][column];  //creating the Maze base on the values when reading in, for the visted part

   //reading a line and creating a charArray base on each character  
      for (int i = 0; i < mazeArray.length; i++) {
    mazeArray[i] = in.readLine().toCharArray();
   }    
      //System.out.println(mazeArray[3][10]);
  in.close(); //close the file
 }
 public static void main(String[] args) {
  new MazeWalk();

 }

}
