//*****************************************
//   Programmer: Tyler Barnard
//   CTP 150 - section 875
//   Lab 7-1
//***************************************** 

import java.text.DecimalFormat;  //for decimal format
import java.util.Scanner;        //for user input
import java.io.*;

/**
   The BankAccount class stores data about a individual's bank account.
*/

abstract class BankAccount implements Serializable
{
   private double balance;
   private int deposits;
   private int withdrawals;
   private double annualInterestRate;
   private double monthlyService;
   
   /*
      default constructor initializes object with null values
   */
   public BankAccount()
   {
      balance = 0.0;
      deposits = 0;
      withdrawals = 0;
      annualInterestRate = 0.0;
      monthlyService = 0.0;
   }
   
   /*
      overloaded constructor to initialize the balance and annual interest rate
      @param b account balance
      @param i annual interest rate
      @exception InvalidDeposit when b contains an invalid value
   */
   public BankAccount(double b, double i) throws InvalidDeposit
   {
      if (b > 10000 || b < 0)
         throw new InvalidDeposit();
      balance = b;
      deposits = 0;
      withdrawals = 0;
      annualInterestRate = i;
      monthlyService = 0.0;
   }
   
   /*
      overloaded constructor to initialize the account balance, annual interest rate, and monthly service charge
      @param b account balance
      @param i annual interest rate
      @param m monthly service charge
      @exception InvalidDeposit when b contains an invalid value
   */
   public BankAccount(double b, double i, double m) throws InvalidDeposit
   {
      if (b > 10000 || b < 0)
         throw new InvalidDeposit();
      balance = b;
      deposits = 0;
      withdrawals = 0;
      annualInterestRate = i;
      monthlyService = m;
   }
   
   /*
      setBalance method sets the account balance
      @param b account balance
   */
   public void setBalance(double b)
   {
      balance = b;
   }

   /*
      setAnnualInterestRate method sets the annual interest rate
      @param i annual interest rate
   */
   public void setAnnualInterestRate(double i)
   {
      annualInterestRate = i;
   }
   
   /*
      setMonthlyService method sets the monthly service charge
      @param m monthly service charge
   */
   public void setMonthlyService(double m)
   {
      monthlyService = m;
   }
   
   /*
      getBalance method returns the account balance
      @return account balance
   */
   public double getBalance()
   {
      return balance;
   }
   
   /*
      getDeposits method returns the numbe of deposits made this month
      @return monthly deposits
   */
   public double getDeposits()
   {
      return deposits;
   }

   /*
      getWithdrawals method returns the number of withdrawls made this month
      @return monthly withdrawals
   */
   public double getWithdrawals()
   {
      return withdrawals;
   }
   
   /*
      getAnnualInterestRate method returns the annual interest rate
      @return annual interest rate
   */
   public double getAnnualInterestRate()
   {
      return annualInterestRate;
   }

   /*
      getMonthlyService method returns the monthly service charges
      @return monthly service charges
   */
   public double getMonthlyService()
   {
      return monthlyService;
   }

   /*
      deposit method adds to the account balance and increments the monthly deposits
      @param d amount to add to the balance
      @exception InvalidDeposit when d contains an invalid value
   */
   public void deposit(double d) throws InvalidDeposit
   {
      if (d > 10000 || d < 0)
         throw new InvalidDeposit();
      balance += d;
      deposits++;
   }

   /*
      withdrawal mehtod subtracts from the account balance and increments the monthly withdrawals
      @param w amount to subtract from the balance
      @exception InvalidWithdrawal when w is greater than the account balance
   */
   public void withdrawal(double w) throws InvalidWithdrawal
   {
      if (w > balance || w > 10000)
         throw new InvalidWithdrawal();
      balance -= w;
      withdrawals++;
   }
   
   /*
      calcInterest method determines the monthly interest earned by the account
                   and adds it to the acocunt balance
   */
   public void calcInterest()
   {
      double monthlyInterestRate = annualInterestRate / 12;    // calculate monthly interest rate
      double monthlyInterest = monthlyInterestRate * balance;  // calculate monthly interest total
      balance += monthlyInterest;                              // add monthly interest to account balance
   }
   
   /*
      monthlyProcess method performs monthly services for the account including:
                     call the calcInterest method
                     resets monthly deposits and withdrawals to 0
                     subtracts monthly service charges from balance
                     resets monthly service charges to 0
   */
   public void monthlyProcess()
   {
      calcInterest();
      deposits = 0;
      withdrawals = 0;
      balance = balance - monthlyService;
      monthlyService = 0.0;

   }
   
   /*
      toString method returns a string description of the object's data
      @return string description the object's data
   */
   public String toString()
   {
      DecimalFormat ft = new DecimalFormat("#0.00"); //for format
      
      String str = ("Balance: $" + ft.format(balance));
      str += ("\nNumber of deposits: " + deposits + "\nNumber of withdrawals: " + withdrawals);
   
      return str;
   }

}//end superclass

/************************************************************************************
************************************************************************************/

class SavingsAccount extends BankAccount implements Serializable
{
   private boolean status = false;                   //determines if the account it active
   public static final double MIN_BALANCE = 25.00;   //balance must be greater then min to be active
   
   /*
      default constructor sets variables to null values
   */
   public SavingsAccount()
   {
      super();
      status = false;
   }
   
   /*
      overloaded constructor creates new SavingsAccount object using given parameters
      @param b account balance
      @param i annual interest rate
      @exception InvalidDeposit when b contains an invalid value
   */
   public SavingsAccount(double b, double i) throws InvalidDeposit
   {
      super(0, i);
      if (b > 10000 || b < 0)
         throw new InvalidDeposit();
      super.setBalance(b);
      if (b > MIN_BALANCE)
         status = true;
      else
         status = false;
   }
   
   /*
      overloaded constructor creates new SavingsAccount object using given parameters
      @param b account balance
      @param i annual interest rate
      @param m monthly service charges
      @exception InvalidDeposit when b contains an invalid value
   */
   public SavingsAccount(double b, double i, double m) throws InvalidDeposit
   {     
      super(0, i, m);
      
      if (b > 10000 || b < 0)
         throw new InvalidDeposit();
      super.setBalance(b);   
      if (b > MIN_BALANCE)
         status = true;
      else
         status = false;
   }
   
   /*
      withdraw method overides the superclass method and checks if account is active before subtracting to the account balance
      @param w amount to subtract to account
      @exception InvalidWithdrawal when w is greater than the account balance or over 10000
   */
   public void withdraw(double w) throws InvalidWithdrawal
   {
      if (w > super.getBalance() || w > 10000)
         throw new InvalidWithdrawal();
         
      if (checkStatus())
         super.withdrawal(w);
         checkStatus();
      if (!status)
         System.out.println("--Balance is less than $25. Account is inactive.");
   }
   
   /*
      deposit method overides the superclass method to check is account is active, and reactivate if balance is made higher then 25
      @param d amount to add to account
      @exception InvalidDeposit when d contains an invalid value
   */
   public void deposit(double d) throws InvalidDeposit
   {
      if (d > 10000 || d < 0)
         throw new InvalidDeposit();
         
      if ((!status) && ((super.getBalance() + d) > MIN_BALANCE))
      {   
         super.deposit(d);
         checkStatus();
      }
      else
         super.deposit(d);
   }

   /*
      monthlyProcess method overides the superclass method and adds a service charge of $1.00 per withdrawal over 4, then checks is account is still active
   */
   public void monthlyProcess()
   {
      if (super.getWithdrawals() > 4)
      {
         
         super.setMonthlyService(super.getMonthlyService() + (super.getWithdrawals() - 4));
         super.monthlyProcess();
         checkStatus();
      }
      else
         super.monthlyProcess();
   }
   
   /*
      setStatus method sets status to a boolean value
      @param s new status
   */
   public void setStatus(boolean s)
   {
      status = s;
   }
   
   /*
      checkStatus method determines if the balance is above the minimum balance
      return status true if above minimum balance, false if below
   */
   private boolean checkStatus()
   {  
      if (super.getBalance() > MIN_BALANCE)
         status = true;
      else
         status = false;
         
      return status;
   }
   
   /*
      toString method returns a description of the objects data
      @return str string description of objects data
   */
   public String toString()
   {
         DecimalFormat ft = new DecimalFormat("#0.00"); //for format
      String str;
      if (checkStatus())
      {
         str = ("Balance: $" + ft.format(super.getBalance()));
      }
      else
      {
         str = ("--Balance is less than $25. The account is inactive.\n");
         str += ("Balance: $" + ft.format(super.getBalance()));
      }
      return str;
   }

}//end subclass

/************************************************************************************
************************************************************************************/

public class SavingsException
{
   public static void main(String[] args)
   {
      double newBalance;      //to hold initial account balance
      double newInterest;     //to hold initial account interest rate
      double newMonthly;      //to hold initial monthly charge
      double deposit;         //amount to be deposited
      double withdrawal;        //amount to be withdrawn
      int choice = 0;          //hold value for option menu choice
      
      //booleans to control loops
      boolean valDep = true;
      boolean valWit = true;
      
      Scanner kb = new Scanner(System.in); //Scanner object for user input
   
      //retrieve initial values
      System.out.println("Please enter initial balance: ");
      newBalance = kb.nextDouble();
      
      System.out.println("Please enter interest rate: ");
      newInterest = kb.nextDouble();
      
      System.out.println("Please enter monthly charge: ");
      newMonthly = kb.nextDouble();
      
      //create new SavingsAccount object with initial values
         SavingsAccount user1 = null;
     do
     {
         try
         {
            user1 = new SavingsAccount(newBalance, newInterest, newMonthly);
            valDep = true;
         }
         catch (InvalidDeposit b)
         {
            System.out.println(b.getMessage());
            System.out.println("Please re-enter: ");
            newBalance = kb.nextDouble();
            valDep = false;
         }
      }while(!valDep);
      
      do
      {
         System.out.println();                 
         System.out.println("Option Menu:");    //display possible actions
         System.out.println("1: Deposit");      
         System.out.println("2: Withdraw");
         System.out.println("3: View Balance");
         System.out.println("4: Exit");
         System.out.println();                  
      
      
         System.out.println("Please enter your option: ");
         choice = kb.nextInt();
         System.out.println();

         switch (choice)
         {
            case 1:
               System.out.println("Please enter amount to deposit: ");
               deposit = kb.nextDouble();
               do 
               {
                  try
                  {
                     user1.deposit(deposit);
                     valDep = true;  
                  }
                  catch (InvalidDeposit d)
                  {
                     System.out.println(d.getMessage());
                     System.out.println("Please re-enter: ");
                     deposit = kb.nextDouble();
                     valDep = false;
                  }
               }while (!valDep);
               break;
            case 2:
               System.out.println("Please enter amount to withdraw: ");
               withdrawal = kb.nextDouble();
               do
               {
                  try
                  {
                     user1.withdraw(withdrawal);
                     valWit = true;
                  }
                  catch (InvalidWithdrawal w)
                  {
                     System.out.println(w.getMessage());
                     System.out.println("Please re-enter: ");
                     withdrawal = kb.nextDouble();
                     valWit = false;
                  }
               }while (!valWit);
               break;
            case 3:
               System.out.printf("\nCurrent balance: $%.2f", user1.getBalance());
               break;
            case 4:
               try
               {
                  user1.monthlyProcess();
                  writeObject(user1);
                  System.out.println();
               }
               catch (IOException i)
               {
                  System.out.println(i.getMessage());
               }
               break;   
            default:
               System.out.println("Not a valid option.");
         }//end switch
      } while (choice != 4); //end do while
      
      try
      {
         readObject();
      }
      catch (Exception e)
      {
         System.out.println(e.getMessage());
      }
      
   }//end main
   
   
   /*
      writeObject method to write a binary file to store SavingsAccount object
      @param s SavingsAccount object
   */
   public static void writeObject(SavingsAccount s) throws IOException
   {
      System.out.println("Saving data ...");
      
      // Create the stream objects.
      FileOutputStream outStream = 
                new FileOutputStream("savingsAccount.dat");
      ObjectOutputStream objectOutputFile = 
                new ObjectOutputStream(outStream);
      // write the object to the file
      objectOutputFile.writeObject(s);
      
      // Close the file.
      objectOutputFile.close();
      
      System.out.println("Account data is saved!");     
   }
   
   /*
      readObject method to read a binary file and display results on screen.
   */
   public static void readObject() throws Exception
   {
      System.out.println("Read data from the file...");
      // Create the stream objects.
      FileInputStream inStream = 
                 new FileInputStream("savingsAccount.dat");
      ObjectInputStream objectInputFile = 
                 new ObjectInputStream(inStream);
                 
      // Read the serialized objects from the file.
      SavingsAccount account = (SavingsAccount)objectInputFile.readObject();
      
      // Close the file.
      objectInputFile.close();

      System.out.println(account.toString());
   }
   
}//end application


