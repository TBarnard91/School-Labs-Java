//****************************************
//   Programmer: Tyler Barnard
//   CTP 150 - section 875
//   Lab 5 - Problem 1
//   Service class: RoomCarpet
//****************************************

/**
	RoomCarpet class
*/

import java.util.Scanner;

class RoomCarpet
{
   //declare variables
   private RoomDimension size;
   private double carpetCost;
   
   //overloaded constructor
   public RoomCarpet(RoomDimension dim, double cost)
   {
      size = new RoomDimension(dim);   //variable to hold RoomDimension object
      carpetCost = cost;      //variable to hold total cost of carpet job
   }
   
   /*
      setSize method (mutator) copies a RoomDimension object to size
      @param dim RoomDimension object
   */
   public void setCarpetCost(RoomDimension dim)
   {
      size = new RoomDimension(dim);
   }

   /*
      setCarpetCost method (mutator) sets the carpetCost to cost
      @param cost Cost of the carpet
   */
   public void setCarpetCost(double cost)
   {
      carpetCost = cost;
   }
   
   /*
      getCarpetCost method (accessor) returns 
      @return carpetCost value in carpetCost
   */
   public double getCarpetCost()
   {
      return carpetCost;
   }
   
   /*
      getSize method (accessor) returns size
      @return size RoomDimension object
   */
   public RoomDimension getSize()
   {
      return size;
   }
   
   /*
      getTotalCost method calculates the total cost
      @return The total cost
   */
   public double getTotalCost()
   {
      return size.getArea() * carpetCost;
   }
   
   /*
      toString method converts the total cost to a string
      @return A string containing the total cost of the carpet
   */
   public String toString()
   {
      return Double.toString(getTotalCost());
   }
}//end class

/********************************************************************************************
********************************************************************************************/

//****************************************
//   Programmer: Tyler Barnard
//   CTP 150 - section 875
//   Lab 5 - Problem 1
//   Service class: RoomDimension
//****************************************

/**
	RoomDimension class
*/

class RoomDimension
{
   //declare variables
   private double length;  //variable to hold length of the room
   private double width;   //variable to hold width of the room
   
   //overloaded constructor
   public RoomDimension(double len, double w)
   {
      length = len;
      width = w;
   }
   
   /**
   	Copy constructor
		@param dim2 The RoomDimension object to copy.
   */
   public RoomDimension(RoomDimension dim2)
   {
      length = dim2.length;
      width = dim2.width;
   }

   /*
      setLength method (mutator) sets the length to l
      @param l length of the room
   */
   public void setLength(double l)
   {
      length = l;
   }

   /*
      setWidth method (mutator) sets the width to w
      @param w width of the room
   */
   public void setWidth(double w)
   {
      width = w;
   }

   /*
      getLength method (accessor) returns length
      @return length value in length
   */
   public double getLength()
   {
      return length;
   }

   /*
      getWidth method (accessor) returns width
      @return width value in width
   */
   public double getWidth()
   {
      return width;
   }
   
   /*
      getArea method calulates the area of the room
      @return the area of the room
   */
   public double getArea()
   {
      return length * width;
   }
   
   /*
      toString method returns the area as a string
   */
   public String toString()
   {
      return Double.toString(getArea());
   }
   
   /*
      equals method to compare RoomDimension objects
      @param dim RoomDimension object
      @return boolean true if the objects fields match, false if not
   */
   public boolean equals(RoomDimension dim)
   {
      boolean status;
      
      if (width == dim.width && length == dim.length)
      {
         status = true;
      }
      else
      {
         status = false;
      }
      return status;
   }
   
   /*
      greaterThan method compares RoomDimension objects area
      @param dim RoomDimension object
      @return boolean true if the calling object's area is greater than the parameter's
   */
   public boolean greaterThan(RoomDimension dim)
   {
      boolean greater;
      
      if (this.getArea() > dim.getArea())
      {
         greater = true;
      }
      else
      {
         greater = false;
      }
      return greater;
   }
   
   /*
      lessThan method compares RoomDimension objects area
      @param dim RoomDimension object
      @return boolean true if the calling object's area is less than the parameter's
   */
   public boolean lessThan(RoomDimension dim)
   {
      boolean less;
      
      if (this.getArea() < dim.getArea())
      {
         less = true;
      }
      else
      {
         less = false;
      }
      return less;
   }
   
}//end class

/********************************************************************************************
********************************************************************************************/

//****************************************
//   Programmer: Tyler Barnard
//   CTP 150 - section 875
//   Lab 5 - Problem 1
//   Application class: RoomCarpetCalculator
//****************************************

/**
	RoomCarpetCalculator class
*/

public class RoomCarpetCalculator
{
   public static void main(String[] args)
   {
      Scanner kb = new Scanner(System.in); //Scanner object for user input
      
      double cost = 8.00;  //cost for 1 square foot of carpet
      double length; //local variable for length of room
      double width;  //local variable for width of room
           
      //Display a message to describle the program.
      System.out.println("This program will display the price to carpet a room.");
      System.out.println("You must input the room's dimensions in feet.");
     
      //Get information for room 1
      System.out.println("Room 1:");
      
      //Get the length of the room.
      System.out.print("Enter the length: ");
      length = kb.nextDouble();
      
      //Get the width of the room.
      System.out.print("Enter the width: ");
      width = kb.nextDouble();
      System.out.println();   //empty line
      
      //create new RoomDimension object
      RoomDimension room1 = new RoomDimension(length, width);
      
      //create new RoomCarpet object
      RoomCarpet carpet1 = new RoomCarpet(room1, cost);
      
            
      //Get information for room 2
      System.out.println("Room 2:");
      
      //Get the length of the room.
      System.out.print("Enter the length: ");
      length = kb.nextDouble();
      
      //Get the width of the room.
      System.out.print("Enter the width: ");
      width = kb.nextDouble();
      System.out.println();   //empty line
      
      //create new RoomDimension object
      RoomDimension room2 = new RoomDimension(length, width);
      
      //create new RoomCarpet object
      RoomCarpet carpet2 = new RoomCarpet(room2, cost); 

      //compare the two RoomDimension objects to see if they are equal, and if not, which is greater
      if (room1.equals(room2))
      {
         System.out.println("They are equal.");
         System.out.println();   //empty line
      }
      else
      {
         System.out.println("They are not equal.");
         System.out.println();   //empty line;
         
         if (room1.greaterThan(room2))
         {
            System.out.println("Room 1 is greater than room 2.");
         }
         else if (room1.lessThan(room2))
         {
            System.out.println("Room 2 is greater than room 1.");
         }
      }
      System.out.println();   //empty line
      
      
      
      //Calculate and display the room1 dimensions
      System.out.println("Room Dimensions:");
      System.out.printf("Length: %.2f\n", room1.getLength());
      System.out.printf("Width: %.2f\n", room1.getWidth());
      System.out.printf("Area: %.2f\n", room1.getArea());
      System.out.println();   //empty line
      
      //Calculate and display the total carpet cost for carpet1
      System.out.printf("Carpet cost: $%.2f\n", carpet1.getTotalCost());
      System.out.println();   //empty line
      
      //Calculate and display the room2 dimensions
      System.out.println("Room Dimensions:");
      System.out.printf("Length: %.2f\n", room2.getLength());
      System.out.printf("Width: %.2f\n", room2.getWidth());
      System.out.printf("Area: %.2f\n", room2.getArea());
      System.out.println();   //empty line
      
      //Calculate and display the total carpet cost for carpet2
      System.out.printf("Carpet cost: $%.2f\n", carpet2.getTotalCost());
      
      
      
   }//end main
}//end class