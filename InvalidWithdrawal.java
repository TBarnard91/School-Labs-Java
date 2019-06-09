//*****************************************
//   Programmer: Tyler Barnard
//   CTP 150 - section 875
//   Lab 7-1
//*****************************************

import java.util.Scanner;

/**
   InvalidWithdrawal exceptions are thrown by the
   BankAccount class when the withdrawal ammount
   is greater than the account balance.
*/

public class InvalidWithdrawal
                   extends Exception
{
   /**
      Constructor for default error message.
   */

   public InvalidWithdrawal()
   {
      super("Error: There is not enough money available to be withdrawn.");
   }

}