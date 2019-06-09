//*****************************************
//   Programmer: Tyler Barnard
//   CTP 150 - section 875
//   Lab 7-1
//*****************************************

/**
   InvalidDeposit exceptions are thrown by the
   BankAccount class when the depoisit amount is
   greater than 10,000 or less than 0.
*/

public class InvalidDeposit
                   extends Exception
{
   /**
      Constructor for default error message.
   */

   public InvalidDeposit()
   {
      super("Error: Amount to deposit should be > 0 and < 10,000.");
   }

}