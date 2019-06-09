//****************************************
//   Programmer: Tyler Barnard
//   CTP 150 - section 875
//   Lab 5 - Problem 2
//   Service class: StringOperations
//****************************************

/**
	StringOperations class
*/

public class StringOperations
{
   /*
      wordCount method determines how many words are in a string
      @param str a string
      @return words number of words in string
   */
   public static int wordCount(String str)
   {
      int words = 0;   //variable to hold number of words in string
      
      String[] tokens = str.split(" ");
   
      words = tokens.length;
      
      return words;
   }

   /*
      mostFrequent method determines the character that occurs most frequently in a string
      @param str a string
      @most the most frequent character in string
   */
   public static char mostFrequent(String str)
   {
      char most = ' ';  //variable to hold most frequent character
      int high = 0;  //variable to hold highest frequency found
      char[] ch = new char[str.length()];  //array to hold characters in string
      
      for(int i = 0; i < str.length(); i++)  //build the array
      {
         ch[i] = str.charAt(i);
      }   
      
      for(char c = 'A'; c<= 'z'; c++)
      {
         char count = 0;
         for(int i = 0; i < ch.length; i++)
         {
            if(c == ch[i])
            {
               count++;
            }
            if(count > high)
            {
              high = count;
              most = ch[i];
            }
         }
      }
      return most;
   }
   
   /*
      replaceSubstring method replaces a section of a string with another string
      @param str1 the string to be searched through
      @param str2 the string to be removed
      @param str3 the string to be added
      @return newString str1 after any changes
   */
   public static StringBuilder replaceSubstring(String str1, String str2, String str3)
   {
      //convert str1 to StringBuilder
      StringBuilder newString = new StringBuilder(str1);
      
      //find location of str2 in newString
      int index = newString.indexOf(str2);
      
      while (index != -1)
      {
         newString.replace(index, index + str2.length(), str3);   //replace str2 with str3 based on location of str2
         index += str3.length();                                  //move to end of replacement string
         index = newString.indexOf(str2, index);                  //find the next location of str 2
      }
      return newString;
   }

   /*
      capSentance method capitalizes the first letter in each sentence of a string
      @param str the string to be changed
      @return cap str1 after capitalizing first letter of each sentence
   */
   public static StringBuilder capSentence(String str)
   {
      //convert str to StringBuilder
      StringBuilder cap = new StringBuilder(str);
      
      //capitalize the first letter in the string
      cap.setCharAt(0, Character.toUpperCase(cap.charAt(0)));
      
      //sort all the characters in the string
      for (int i = 0; i < cap.length(); i++)
      {
         //get the current character and confirm it is not a period, exclamation point, or question mark
         //if it is, then capitalize next character after capital
         char c = cap.charAt(i);
         if((c == '.') || (c == '!') || (c == '?'))
         {
            if (((i + 2) < cap.length()))
            {
            c = cap.charAt(i+2);
            cap.setCharAt(i+2, Character.toUpperCase(cap.charAt(i+2)));
            }
         }
      }
      return cap;
   }

   /*
      reverseWords method reverses the order of words in a string.
      @param str string to be reversed
      @return reverse String reversed
   */
   public static StringBuilder reverseWords(String str)
   {
      //create a StringBuilder object
      StringBuilder reverse = new StringBuilder(str.length());
      
      //tokenize the string to get each word
      String[] tokens = str.split(" ");
      
      //for loop to add each word in reverse
      for (int i = tokens.length - 1; i >= 0; i--)
      {
         reverse.append(tokens[i] + " ");
      }   
      return reverse;
   }
}//end class