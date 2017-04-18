/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  GinormousInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  B.J. Johnson
 * Date       :  2017-04-04
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-04  B.J. Johnson  Initial writing and begin coding
 *  1.1.0  2017-04-13  B.J. Johnson  Completed addByt, addInt, compareTo, equals, toString, Constructor,
 *                                     validateDigits, two reversers, and valueOf methods; revamped equals
 *                                     and compareTo methods to use the Java String methods; ready to
 *                                     start work on subtractByte and subtractInt methods
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;

public class GinormousInt {

   public static final GinormousInt ZERO     = new GinormousInt(  "0" );      /// Constant for "zero"
   public static final GinormousInt ONE      = new GinormousInt(  "1" );      /// Constant for "one"
   public static final GinormousInt TWO      = new GinormousInt(  "2" );      /// Constant for "two"
   public static final GinormousInt THREE    = new GinormousInt(  "3" );      /// Constant for "three"
   public static final GinormousInt FOUR     = new GinormousInt(  "4" );      /// Constant for "four"
   public static final GinormousInt FIVE     = new GinormousInt(  "5" );      /// Constant for "five"
   public static final GinormousInt SIX      = new GinormousInt(  "6" );      /// Constant for "six"
   public static final GinormousInt SEVEN    = new GinormousInt(  "7" );      /// Constant for "seven"
   public static final GinormousInt EIGHT    = new GinormousInt(  "8" );      /// Constant for "eight"
   public static final GinormousInt NINE     = new GinormousInt(  "9" );      /// Constant for "nine"
   public static final GinormousInt TEN      = new GinormousInt( "10" );      /// Constant for "ten"

  /// Some constants for other intrinsic data types
  ///  these can help speed up the math if they fit into the proper memory space
   public static final GinormousInt MAX_INT  = new GinormousInt( new Integer( Integer.MAX_VALUE ).toString() );
   public static final GinormousInt MIN_INT  = new GinormousInt( new Integer( Integer.MIN_VALUE ).toString() );
   public static final GinormousInt MAX_LONG = new GinormousInt( new Long( Long.MAX_VALUE ).toString() );
   public static final GinormousInt MIN_LONG = new GinormousInt( new Long( Long.MIN_VALUE ).toString() );

  /// These are the internal fields
   private String internalValue = "";        // internal String representation of this GinormousInt
   private byte   sign          = 0;         // "0" is positive, "1" is negative
   private String reversed      = "";        // the backwards version of the internal String representation
   private byte[] byteVersion   = null;      // byte array for storing the string values; uses the reversed string

  /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a GinormousInt
   */
   public GinormousInt( String value ) {
      internalValue = value;
      reversed = new String( new StringBuffer( internalValue ).reverse() );
      if( value.charAt(0) == '+' ) {
         sign = 0;
         reversed = reversed.substring( 0, reversed.length() - 1 );
      } else if( value.charAt(0) == '-' ) {
         sign = 1;
         reversed = reversed.substring( 0, reversed.length() - 1 );
      }
      validateDigits();
      byteVersion = new byte[reversed.length()];
      for( int i = 0; i < reversed.length(); i++ ) {
         byteVersion[i] = (byte)Character.getNumericValue( reversed.charAt(i) );
      }
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateDigits() {
      if( 0 == internalValue.length() ) {
         throw new IllegalArgumentException( "\n  Sorry, you must enter at least one decimal digit." );
      } else {
         for( int i = 0; i < internalValue.length(); i++ ) {
            if( !(Character.isDigit( internalValue.charAt(i) )) &&
                !('+' == internalValue.charAt(i) ) &&
                !('-' == internalValue.charAt(i) ) ) {
               throw new IllegalArgumentException( "\n  Sorry, all characters must be decimal digit or sign characters." );
            }
         }
      }
      return true;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of this GinormousInt
   *  @return GinormousInt that is the reverse of the value of this GinormousInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public GinormousInt reverser() {
      return new GinormousInt( new String( new StringBuffer( internalValue ).reverse() ) );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a GinormousIntk passed as argument
   *  Note: static method
   *  @param  gint         GinormousInt to reverse its value
   *  @return GinormousInt that is the reverse of the value of the GinormousInt passed as argument
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static GinormousInt reverser( GinormousInt gint ) {
      return new GinormousInt( new String( new StringBuffer( gint.internalValue ).reverse() ) );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a GinormousIntk passed as argument to this GinormousInt using byte array
   *  @param  gint         GinormousInt to add to this
   *  @return GinormousInt that is the sum of the value of this GinormousInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public GinormousInt addByte( GinormousInt gint ) {

      boolean carry = false;              // handles the carry for addition

     // dimension the byte arrays and set the shortest length tracker (i.e. "j") to zero
      byte[] a = new byte[reversed.length()];
      byte[] b = new byte[gint.reversed.length()];
      byte[] c = new byte[(Math.max( reversed.length(), gint.reversed.length() )) + 2];

      int          j        = 0;
      String       result_s = null;
      StringBuffer result   = null;

     // if the signs are different, just call subtraction
      if( sign != gint.sign ) {
         return subtractByte( gint );
      }

     // assigne the values to each byte array
      for( int i = 0; i < a.length; i++ ) {
         a[i] = (byte)((int)(reversed.charAt(i)) - 48);         // NOTE: only works for ASCII
      }
      for( int i = 0; i < b.length; i++ ) {
         b[i] = (byte)((int)(gint.reversed.charAt(i)) -48);     // NOTE: only works for ASCII
      }

     // perform the addition for the shortest part
      for( int i = 0; i < (Math.min(a.length, b.length)); i++ ) {
         if( carry ) {
            c[i] = (byte)((int)a[i] + (int)b[i] + 1);
         } else {
            c[i] = (byte)((int)a[i] + (int)b[i]);
         }
         if( c[i] > 9 ) {
            carry = true;
            c[i] = (byte)((int)c[i] - 10);
         } else {
            carry = false;
         }
         j++;        // brute force end of the shortest array for later
      }

     // if they aren't the same length, copy the rest of the numbers from
     //  the longer number into the result, rippling the carry across if needed
      if( a.length < b.length ) {
         for( int i = j; i < b.length; i++ ) {
            if( carry ) {
               c[i] = (byte)((int)b[j] + 1);
               if( c[i] > 9 ) {
                  carry = true;
                  c[i] = (byte)((int)c[i] - 10);
               } else {
                  carry = false;
               }
            } else {
               c[i] = b[i];
            }
         }
      } else if( a.length > b.length ) {
         for( int i = j; i < a.length; i++ ) {
            if( carry ) {
               c[i] = (byte)((int)a[j] + 1);
               if( c[i] > 9 ) {
                  carry = true;
                  c[i] = (byte)((int)c[i] - 10);
               } else {
                  carry = false;
               }
            } else {
               c[i] = a[i];
            }
         }
      }

     // build and return the result string; note, the sign gets handled here
      result = new StringBuffer();
      for( int i = 0; i < Math.max(a.length, b.length); i++ ) {
         result = result.append( (int)c[i] );
      }
      if( carry ) {
         result = result.append( '1' );
      }
      if( sign == 1 ) {
         result = result.append( "-" );
      }
      result_s = new String( result.reverse() );

      return new GinormousInt( result_s );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a GinormousIntk passed as argument to this GinormousInt using int array
   *  @param  gint         GinormousInt to add to this
   *  @return GinormousInt that is the sum of the value of this GinormousInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public GinormousInt addInt( GinormousInt gint ) {

      boolean carry = false;              // handles the carry for addition

     // dimension the byte arrays and set the shortest length tracker (i.e. "j") to zero
      int[] a = new int[reversed.length()];
      int[] b = new int[gint.reversed.length()];
      int[] c = new int[(Math.max( reversed.length(), gint.reversed.length() )) + 2];

      int          j        = 0;
      String       result_s = null;
      StringBuffer result   = null;

     // assign the values to each byte array
      for( int i = 0; i < a.length; i++ ) {
         a[i] = reversed.charAt(i) - 48;           // NOTE: only works for ASCII
      }
      for( int i = 0; i < b.length; i++ ) {
         b[i] = gint.reversed.charAt(i) - 48;      // NOTE: only works for ASCII
      }

     // perform the addition for the shortest part
      for( int i = 0; i < (Math.min(a.length, b.length)); i++ ) {
         if( carry ) {
            c[i] = a[i] + b[i] + 1;
         } else {
            c[i] = a[i] + b[i];
         }
         if( c[i] > 9 ) {
            carry = true;
            c[i] = c[i] - 10;
         } else {
            carry = false;
         }
         j++;        // brute force end of the shortest array for later
      }

     // if they aren't the same length, copy the rest of the numbers from
     //  the longer number into the result, rippling the carry across if needed
      if( a.length < b.length ) {
         for( int i = j; i < b.length; i++ ) {
            if( carry ) {
               c[i] = b[j] + 1;
               if( c[i] > 9 ) {
                  carry = true;
                  c[i] = c[i] - 10;
               } else {
                  carry = false;
               }
            } else {
               c[i] = b[i];
            }
         }
      } else if( a.length > b.length ) {
         for( int i = j; i < a.length; i++ ) {
            if( carry ) {
               c[i] = a[j] + 1;
               if( c[i] > 9 ) {
                  carry = true;
                  c[i] = c[i] - 10;
               } else {
                  carry = false;
               }
            } else {
               c[i] = a[i];
            }
         }
      }

     // build the result string
      result = new StringBuffer();
      for( int i = 0; i < Math.max(a.length, b.length); i++ ) {
         result = result.append( (int)c[i] );
      }
      if( carry ) {
         result = result.append( '1' );
      }
      if( sign == 1 ) {
         result = result.append( "-" );
      }
      result_s = new String( result.reverse() );

      return new GinormousInt( result_s );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a GinormousIntk passed as argument to this GinormousInt using bytes
   *  @param  gint         GinormousInt to subtract from this
   *  @return GinormousInt that is the difference of the value of this GinormousInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public GinormousInt subtractByte( GinormousInt gint ) {

      boolean      borrow   = false;            // handles the borrow for addition
      boolean      negative = false;            // set to true if result should be negative
      byte[]       a        = null;
      byte[]       b        = null;
      int          bigger   = 0;                // result of compareTo, comparing 'this' to 'arg'
      int          j        = 0;
      int          mySign   = 0;                // initialize to positive
      int          gintSign = 0;                // initialize to positive
      String       result_s = null;
      StringBuffer result   = null;

     // set up for the sign handling to decide what to do based on the following cases:
     //    1. no signs at all, this item larger than argument:        simple subtraction a - b
     //    2. both signs positive, this item larger than argument:    simple subtraction a - b
     //    3. one positive one no sign, this item larger than arg:    simple subtraction a - b
     //    4. no signs at all, this item smaller than argument:       swap a & b, subtract a - b, result negative
     //    5. both signs positive, this item smaller than argument:   swap a & b, subtract a - b, result negative
     //    6. one positive one no sign, this item smaller than arg:   swap a & b, subtract a - b, result negative
     //    7. this no sign or positive, arg negative:                 remove neg from arg and call this.add( arg )
     //    8. this negative, arg positive:                            add negative to arg and call this.add( arg )
     //    9. both signs negative, this larger abs than arg abs:      remove signs, subtract, add neg to result
     //   10. both signs negative, this smaller abs than arg abs:     remove signs, swap a & b, subtract, result pos
     //
     // set the sign for 'this'; already initialized to zero for positive default, so check for minus
     //  NOTE: signs removed in the constructors and "sign" values set for easier handling
      mySign   = sign;
      gintSign = gint.sign;

     // check to set the negative boolean for later or perform addition instead
      if( (0 == mySign) && (0 == gintSign) && (0 <= this.compareTo( gint )) ) {           // case 1 - 3
         negative = false;
      } else if( (0 == mySign) && (0 == gintSign) && (0 > this.compareTo( gint )) ) {     // case 4 - 6
         negative = true;
      } else if( (0 == mySign) && (1 == gintSign) && (0 < this.compareTo( gint )) ) {     // case 7
         GinormousInt gint1 = new GinormousInt( new String( gint.internalValue.substring( 1, gint.internalValue.length() ) ) );
         return this.addByte( gint1 );
      } else if( (1 == mySign) && (0 == gintSign) && (0 > this.compareTo( gint )) ) {     // case 8
         negative = true;
         GinormousInt gMe = new GinormousInt( new String( internalValue.substring( 1, internalValue.length() ) ) );
         GinormousInt myResult = new GinormousInt( new String( "-" + gMe.addByte( gint ).toString() ) );
         return myResult;
      } else if( (1 == mySign) && (1 == gintSign) && (0 < this.compareTo( gint )) ) {     // case 9
         negative = true;
      } else if( (1 == mySign) && (1 == gintSign) && (0 > this.compareTo( gint )) ) {     // case 10
         negative = false;
      }


     // dimension the byte arrays and initialize them appropriately for their lengths; a gets the longer string
      if( (reversed.length() > gint.reversed.length()) || (0 <= this.compareTo( gint )) ) {
         a = new byte[reversed.length()];
         for( int i = 0; i < a.length; i++ ) {
            a[i] = (byte)((int)(reversed.charAt(i)) - 48);         // NOTE: only works for ASCII
         }
         b = new byte[gint.reversed.length()];
         for( int i = 0; i < b.length; i++ ) {
            b[i] = (byte)((int)(gint.reversed.charAt(i)) -48);     // NOTE: only works for ASCII
         }
      } else {
         a = new byte[gint.reversed.length()];
         for( int i = 0; i < a.length; i++ ) {
            a[i] = (byte)((int)(gint.reversed.charAt(i)) -48);     // NOTE: only works for ASCII
         }
         b = new byte[reversed.length()];
         for( int i = 0; i < b.length; i++ ) {
            b[i] = (byte)((int)(reversed.charAt(i)) - 48);         // NOTE: only works for ASCII
         }
      }
      byte[] c = new byte[(Math.max( reversed.length(), gint.reversed.length() )) + 2];

     // perform the subtraction for the shortest part
     //  if a borrow is required, loop through from the current location to the end of the input,
     //   if necessary, to find the first non-zero value from which to borrow; that one gets decremented
     //   and all the ones in between that used to be zero become nines.
     // perform the subtraction for the shortest part
      for( int i = 0; i < (Math.min(a.length, b.length)); i++ ) {
            c[i] = (byte)((int)a[i] - (int)b[i]);
         j++;        // brute force end of the shortest array for later
      }

     // if they aren't the same length, copy the rest of the numbers from
     //  the longer number into the result, rippling the borrow across if needed
      if( a.length < b.length ) {
         for( int i = j; i < b.length; i++ ) {
            if( borrow ) {
               c[i] = (byte)((int)b[j] + 1);
               if( c[i] > 9 ) {
                  borrow = true;
                  c[i] = (byte)((int)c[i] - 10);
               } else {
                  borrow = false;
               }
            } else {
               c[i] = b[i];
            }
         }
      } else if( a.length > b.length ) {
         for( int i = j; i < a.length; i++ ) {
            if( borrow ) {
               c[i] = (byte)((int)a[j] + 1);
               if( c[i] > 9 ) {
                  borrow = true;
                  c[i] = (byte)((int)c[i] - 10);
               } else {
                  borrow = false;
               }
            } else {
               c[i] = a[i];
            }
         }
      }

     // build and return the result string; note, the sign gets handled here
      result = new StringBuffer();
      for( int i = 0; i < Math.max(a.length, b.length); i++ ) {
         result = result.append( (int)c[i] );
      }
      if( borrow ) {
         result = result.append( '1' );
      }
      if( negative ) {
         result = result.append( "-" );
      }
      result_s = new String( result.reverse() );

      return new GinormousInt( result_s );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a GinormousIntk passed as argument to this GinormousInt
   *  @param  gint         GinormousInt to multiply by this
   *  @return GinormousInt that is the product of the value of this GinormousInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public GinormousInt multiply( GinormousInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this GinormousIntk by the GinormousInt passed as argument
   *  @param  gint         GinormousInt to divide this by
   *  @return GinormousInt that is the dividend of this GinormousInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public GinormousInt divide( GinormousInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this GinormousInt by the one passed as argument
   *  @param  gint         GinormousInt to divide this one by
   *  @return GinormousInt that is the remainder of division of this GinormousInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public GinormousInt remainder( GinormousInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a GinormousInt passed as argument to this GinormousInt
   *  @param  gint  GinormousInt to add to this
   *  @return int   that is one of neg/0/pos if this GinormousInt precedes/equals/follows the argument
   *  NOTE: this method performs a lexicographical comparison using the java String "compareTo()" method
   *        THAT was easy.....
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int compareTo( GinormousInt gint ) {
      return (internalValue.compareTo( gint.toString() ));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a GinormousInt passed as argument is equal to this GinormousInt
   *  @param  gint     GinormousInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  NOTE: this method performs a similar lexicographical comparison as the "compareTo()" method above
   *        also using the java String "equals()" method -- THAT was easy, too..........
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( GinormousInt gint ) {
      return (internalValue.equals( gint.toString() ));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a GinormousInt given a long value passed as argument
   *  @param  value         long type number to make into a GinormousInt
   *  @return GinormousInt  which is the GinormousInt representation of the long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static GinormousInt valueOf( long value ) throws NumberFormatException {
      GinormousInt gi = null;
      try {
         gi = new GinormousInt( new Long( value ).toString() );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n  Sorry, the value must be numeric of type long." );
      }
      return gi;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a String representation of this GinormousInt
   *  @return String  which is the String representation of this GinormousInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String toString() {
      String byteVersionOutput = "";
      for( int i = 0; i < byteVersion.length; i++ ) {
         byteVersionOutput = byteVersionOutput.concat( Byte.toString( byteVersion[i] ) );
      }
      byteVersionOutput = new String( new StringBuffer( byteVersionOutput ).reverse() );
      return internalValue;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display an Array representation of this GinormousInt as its bytes
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void toArray( byte[] d ) {
      System.out.println( Arrays.toString( d ) );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  note:  we don't really care about these
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the GinormousInt program!!\n" );
      System.out.println( "\n   You should run your tests from the GinormousIntTester...\n" );

      System.exit( 0 );
   }
}
