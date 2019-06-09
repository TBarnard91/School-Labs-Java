//*****************************************
//   Programmer: Tyler Barnard
//   CTP 150 - section 875
//   Lab 6-1
//***************************************** 

import java.text.DecimalFormat; //for decimal format

/**
   The BankAccount class stores data about a individual's bank account.
*/

abstract class BankAccount
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
   */
   public BankAccount(double b, double i)
   {
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
   */
   public BankAccount(double b, double i, double m)
   {
      balance = b;
      deposits = 0;
      withdrawals = 0;
      annualInterestRate = i;
      monthlyService = m;
   }
   
   /*
      setBalance method sets the account balance
      param b account balance
   */
   public void setBalance(double b)
   {
      balance = b;
   }

   /*
      setAnnualInterestRate method sets the annual interest rate
      param i annual interest rate
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
   */
   public void deposit(double d)
   {
      balance += d;
      deposits++;
   }

   /*
      withdrawal mehtod subtracts from the account balance and increments the monthly withdrawals
      @param w amount to subtract from the balance
   */
   public void withdrawal(double w)
   {
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

class SavingsAccount extends BankAccount
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
   */
   public SavingsAccount(double b, double i)
   {
      super(b, i);
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
   */
   public SavingsAccount(double b, double i, double m)
   {
      super(b, i, m);
      if (b > MIN_BALANCE)
         status = true;
      else
         status = false;
   }
   
   /*
      withdraw method overides the superclass method and checks if account is active before subtracting to the account balance
      @param w amount to subtract to account
   */
   public void withdraw(double w)
   {
      if (checkStatus())
         super.withdrawal(w);
   }
   
   /*
      deposit method overides the superclass method to check is account is active, and reactivate if balance is made higher then 25
      @param d amount to add to account
   */
   public void deposit(double d)
   {
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
         super.setMonthlyService(super.getWithdrawals() - 4);
         super.setBalance(super.getBalance() - super.getMonthlyService());
         checkStatus();
      }
      else
         super.monthlyProcess();
   }
   
   private boolean checkStatus()
   {  
      if (super.getBalance() > MIN_BALANCE)
         status = true;
      else
         status = false;
         
      return status;
   }
   
   public String toString()
   {
      String str;
      if (checkStatus())
      {
         str = super.toString();
      }
      else
      {
         str = ("--Balance is less than $25. The account is inactive.\n");
         str += super.toString();
      }
      return str;
   }

}//end subclass

/************************************************************************************
************************************************************************************/

public class SavingsDemo
{
   public static void main(String[] args)
   {
      //Create a SavingsAccount object with a $100 balance, 
      // 3% interest rate, and a monthly service charge
      // of $2.50.
      SavingsAccount savings = 
                   new SavingsAccount(100.0, 0.03, 2.50);
      
      // Display what we've got.
      System.out.println(savings);
      System.out.println();
      
      // Make some deposits.
      savings.deposit(25.00);
      savings.deposit(10.00);
      savings.deposit(35.00);
      
      // Display what we've done so far.
      System.out.println(savings);

      System.out.println();
      
      // Make some withdrawals.
      savings.withdraw(100.00);
      savings.withdraw(50.00);
      savings.withdraw(10.00);
      savings.withdraw(1.00);
      savings.withdraw(1.00);
      
      // Display what we've done so far.
      System.out.println(savings);
      System.out.println();

      // Do the monthly processing.
      savings.monthlyProcess();
   
      // Display what we've done so far.
      System.out.println(savings);
   }
}
