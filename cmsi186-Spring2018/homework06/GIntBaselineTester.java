/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  GIntBaselineTester.java
 * Purpose    :  Test Harness for the GinormousInt java class, used as baseline for assessment
 * @author    :  B.J. Johnson
 * Date       :  2017-04-05
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-05  B.J. Johnson  Initial writing and release
 *  1.1.0  2017-04-13  B.J. Johnson  Added new GinormousInt instances to check addition; refactored to
 *                                     check the new versions of compareTo and equals; verified that all
 *                                     additions work for both small and large numbers, as well as for
 *                                     values of different lengths and including same-sign negative value
 *                                     additions; ready to start subtractByte and subtractInt methods
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class GIntBaselineTester {

   private static String g01String = "144127909719710664015092431502440849849506284148982076191826176553";
   private static String g02String = "144127909719710664015092431502440849849506284148982076191826176553";
   private static String g03String = "144127909719710664015092431502440849849506284108982076191826176553";
   private static String g04String = "14412790971971066401509243150244084984950628410898207";
   private static String g05String = "0";
   private static String g06String = "1";
   private static String g07String = "10";
   private static String g11String = "10";
   private static String g12String = "20";
   private static String g13String = "234567";
   private static String g14String = "-234567";
   private static String g15String = "-10";
   private static String g16String = "-999999";
   private static String g17String = "765";
   private static String g18String = "23";
   private static String g19String = "56789";
   private static String g20String = "37";
   private static String g21String = "14412790971971066401509243150";
   private static String g22String = "412790971971";

   private static GinormousInt g1 = null;
   private static GinormousInt g2 = null;
   private static GinormousInt g3 = null;
   private static GinormousInt g4 = null;
   private static GinormousInt g5 = null;
   private static GinormousInt g6 = null;
   private static GinormousInt g7 = null;
   private static GinormousInt g8 = null;
   private static GinormousInt g9 = null;
   private static GinormousInt g10 = null;
   private static GinormousInt g11 = null;
   private static GinormousInt g12 = null;
   private static GinormousInt g13 = null;
   private static GinormousInt g14 = null;
   private static GinormousInt g15 = null;
   private static GinormousInt g16 = null;
   private static GinormousInt g17 = null;
   private static GinormousInt g18 = null;
   private static GinormousInt g19 = null;
   private static GinormousInt g20 = null;
   private static GinormousInt g21 = null;
   private static GinormousInt g22 = null;

   private static int attempts = 0;
   private static int successes = 0;
   private static int testCount = 0;

   public GIntBaselineTester() {
      super();
   }

  /**
   *  silly little method to add zeros to the front of a number string
   *    to ensure it fills two places for test output alignment
   *  @return two-character string that is a two-place number from 00 to 99
   *  note: this method also increments the testCount private field
   */
   private static String makeTwoDigits() {
      testCount++;
      if( testCount < 10 ) {
         return new String( "0" + testCount );
      } else {
         return new Integer( testCount ).toString();
      }
   }

   public static void main( String[] args ) {
      GIntBaselineTester giblt = new GIntBaselineTester();

      System.out.println( "\n  Hello, world, from the GinormousInt program!!\n" );

      System.out.println( "    TESTING CONSTRUCTOR AND CONSTANTS:\n" +
                          "    ==================================" );
      try {
         System.out.println( "    Test " + makeTwoDigits() + ": Making a new GinormousInt: " );
         g1 = new GinormousInt( g01String );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }
      try {
         System.out.println( "      expecting: " + g01String + "\n" +
                             "        and got: " + g1.toString() );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      System.out.println( "\n    Test " + makeTwoDigits() + ": Making a second new GinormousInt [same as first]: " );
      try {
         g2 = new GinormousInt( g02String );
         System.out.println( "      expecting: " + g02String + "\n" +
                             "        and got: " + g2.toString() );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "    Test " + makeTwoDigits() + ": Comparing equality of g1 and g2 with 'equals()': " );
         System.out.println( "      expecting: true\n" + "        and got: " + g1.equals( g2 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "\n    Test " + makeTwoDigits() + ": Making a third new GinormousInt [differs at position 47    |]: " +
                             "\n           [position indicated by down arrow]                  v   " );
         g3 = new GinormousInt( g03String );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "      expecting: " + g03String + "\n" +
                             "        and got: " + g3.toString() );
         System.out.println( "          g1 is: " + g1.toString() );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "    Test " + makeTwoDigits() + ": Comparing equality of g1 and g3 [detect different digit]: " );
         System.out.println( "      expecting: false\n" + "        and got: " + g1.equals( g3 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "\n    Test " + makeTwoDigits() + ": Making a fourth new GinormousInt [same as g3 but truncated]: "  );
         g4 = new GinormousInt( g04String );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "      expecting: " + g04String + "\n" +
                             "        and got: " + g4.toString() );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "    Test " + makeTwoDigits() + ": Comparing equality of g3 and g4 [detect different length prior to detecting different digit]: " );
         System.out.println( "      expecting: false\n" + "        and got: " + g3.equals( g4 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "\n    Test " + makeTwoDigits() + ": Making a fifth new GinormousInt, checking GinormousInt.ZERO: "  );
         g5 = new GinormousInt( "0" );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "      expecting: " + GinormousInt.ZERO + "\n" +
                             "        and got: " + g5.toString() );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "\n    Test " + makeTwoDigits() + ": Making a sixth new GinormousInt, checking GinormousInt.ONE: "  );
         g6 = new GinormousInt( "1" );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "      expecting: " + GinormousInt.ONE + "\n" +
                             "        and got: " + g6.toString() );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "\n    Test " + makeTwoDigits() + ": Making a seventh new GinormousInt, checking GinormousInt.TEN: "  );
         g7 = new GinormousInt( g07String );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "      expecting: " + GinormousInt.TEN + "\n" +
                             "        and got: " + g7.toString() );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      System.out.println( "\n\n    TESTING VALUEOF( LONG ) METHOD:\n" +
                          "    ===============================" );
      System.out.println( "\n    Test " + makeTwoDigits() + ": Creating several long type values to check the 'valueOf()' method: " );
      long long01 = Long.MAX_VALUE;
      long long02 = Long.MIN_VALUE;
      long long03 = 1234567890;
      try {
         System.out.println( "      expecting: " + Long.MAX_VALUE + "\n" +
                             "        and got: " + long01 );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "      expecting: " + Long.MIN_VALUE + "\n" +
                             "        and got: " + long02 );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "      expecting: 1234567890\n" +
                             "        and got: " + long03 );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "    Test " + makeTwoDigits() + ": Now testing 'valueOf()' method: " );
         g8  = GinormousInt.valueOf( long01 );
         g9  = GinormousInt.valueOf( long02 );
         g10 = GinormousInt.valueOf( long03 );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "      expecting: " + Long.MAX_VALUE + "\n" +
                             "        and got: " + g8.toString() );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "      expecting: " + Long.MIN_VALUE + "\n" +
                             "        and got: " + g9.toString() );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "      expecting: 1234567890\n" +
                             "        and got: " + g10.toString() );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      System.out.println( "\n\n    TESTING ADDBYTE() AND ADDINT() METHODS:\n" +
                          "    =======================================" );
      try {
         System.out.println( "\n    Test " + makeTwoDigits() + ": Making an eleventh and twelfth new GinormousInt, calling addByte method: "  );
         g11 = new GinormousInt( g11String );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "      expecting: 10\n" +
                             "        and got: " + g11.toString() );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         g12 = new GinormousInt( g12String );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "      expecting: 20\n" +
                             "        and got: " + g12.toString() );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "    Test " + makeTwoDigits() + ": Adding g11 and g12: " );
         System.out.println( "      expecting: 30 and got " + g11.add( g12 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      System.out.println( "\n    Test " + makeTwoDigits() + ": Making a thirteenth new GinormousInt, calling add methods: "  );
      try {
         g13 = new GinormousInt( g13String );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "      expecting: 234567\n" +
                             "        and got: " + g13.toString() );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "    Test " + makeTwoDigits() + ": Adding g11 and g13 [10 + 234567] using bytes: " );
         System.out.println( "      expecting: 234577\n" +
                             "        and got: " + g11.add( g13 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "    Test " + makeTwoDigits() + ": Adding g11 and g13 [10 + 234567] using ints: " );
         System.out.println( "      expecting: 234577\n" +
                             "        and got: " + g11.add( g13 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "    Test " + makeTwoDigits() + ": Adding g13 and g11 [234567 + 10] using bytes: " );
         System.out.println( "      expecting: 234577\n" +
                             "        and got: " + g13.add( g11 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "    Test " + makeTwoDigits() + ": Adding g13 and g11 [234567 + 10] using ints: " );
         System.out.println( "      expecting: 234577\n" +
                             "        and got: " + g13.add( g11 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      System.out.println( "\n    Test " + makeTwoDigits() + ": Making a fourteenth new GinormousInt, calling add methods: "  );
      try {
         g14 = new GinormousInt( g14String );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "      expecting: -234567\n" +
                             "        and got: " + g14.toString() );
         System.out.println( "\n    Test " + makeTwoDigits() + ": Making a fifteenth new GinormousInt, calling add methods: "  );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         g15 = new GinormousInt( g15String );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "      expecting: -10\n" +
                             "        and got: " + g15.toString() );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "    Test " + makeTwoDigits() + ": Adding g14 and g15 [-234567 + -10] using bytes: " );
         System.out.println( "      expecting: -234577\n" +
                             "        and got: " + g14.add( g15 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "    Test " + makeTwoDigits() + ": Adding g14 and g15 [-234567 + -10] using ints: " );
         System.out.println( "      expecting: -234577\n" +
                             "        and got: " + g14.add( g15 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "    Test " + makeTwoDigits() + ": Adding g15 and g14 [-10 + -234567] using bytes: " );
         System.out.println( "      expecting: -234577\n" +
                             "        and got: " + g15.add( g14 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "    Test " + makeTwoDigits() + ": Adding g15 and g14 [-10 + -234567] using ints: " );
         System.out.println( "      expecting: -234577\n" +
                             "        and got: " + g15.add( g14 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      System.out.println( "\n    Test " + makeTwoDigits() + ": Making a sixteenth new GinormousInt, calling add methods: "  );
      try {
         g16 = new GinormousInt( g16String );
         System.out.println( "      expecting: -999999\n" +
                             "        and got: " + g16.toString() );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "    Test " + makeTwoDigits() + ": Adding g14 and g16 [-234567 + -999999] using bytes: " );
         System.out.println( "      expecting: -1234566\n" +
                             "        and got: " + g14.add( g16 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "    Test " + makeTwoDigits() + ": Adding g14 and g16 [-234567 + -999999] using ints: " );
         System.out.println( "      expecting: -1234566\n" +
                             "        and got: " + g14.add( g16 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "    Test " + makeTwoDigits() + ": Adding g16 and g14 [-999999 + -234567] using bytes: " );
         System.out.println( "      expecting: -1234566\n" +
                             "        and got: " + g16.add( g14 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "    Test " + makeTwoDigits() + ": Adding g16 and g14 [-999999 + -234567] using ints: " );
         System.out.println( "      expecting: -1234566\n" +
                             "        and got: " + g16.add( g14 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "\n      Test " + makeTwoDigits() + ": Adding g1 and g4 using bytes: " );
         System.out.println( "      expecting: 144127909719725076806064402568842359092656528233967026820237074760\n" +
                             "        and got: " + g1.add( g4 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "\n      Test " + makeTwoDigits() + ": Adding g4 and g1 using ints: " );
         System.out.println( "      expecting: 144127909719725076806064402568842359092656528233967026820237074760\n" +
                             "        and got: " + g4.add( g1 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      System.out.println( "\n\n    TESTING COMPARETO() METHOD:\n" +
                          "    ===========================" );
      try {
         System.out.println( "\n    Test " + makeTwoDigits() + ": Checking compareTo() method on g1 and g2: "  );
         System.out.println( "      expecting: 0\n" +
                             "        and got: " + g1.compareTo( g2 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "\n    Test " + makeTwoDigits() + ": Checking compareTo() method on g2 and g1: "  );
         System.out.println( "      expecting: 0\n" +
                             "        and got: " + g2.compareTo( g1 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "\n    Test " + makeTwoDigits() + ": Checking compareTo() method on g1 and g3: "  );
         System.out.println( "      expecting: positive value\n" +
                             "        and got: " + g1.compareTo( g3 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "\n    Test " + makeTwoDigits() + ": Checking compareTo() method on g3 and g1: "  );
         System.out.println( "      expecting: negative value\n" +
                             "        and got: " + g3.compareTo( g1 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      try {
         System.out.println( "\n    Test " + makeTwoDigits() + ": Checking compareTo() method on g3 and g4: "  );
         System.out.println( "      expecting: positive value\n" +
                             "        and got: " + g3.compareTo( g4 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      System.out.println( "\n\n    TESTING SUBTRACTBYTE() METHOD:\n" +
                          "    ==============================" );
      System.out.println( "\n      Test " + makeTwoDigits() + ": Subtracting g13 take away g11 [234567 - 10] using bytes: " );
      try {
         System.out.println( "      expecting: 234557\n" +
                             "        and got: " + g13.subtract( g11 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Subtracting g11 take away g13 [10 - 234567] using bytes: " );
      try {
         System.out.println( "      expecting: -234557\n" +
                             "        and got: " + g11.subtract( g13 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Subtracting g13 take away g15 [234567 - (-10)] using bytes: " );
      try {
         System.out.println( "      expecting: 234577\n" +
                             "        and got: " + g13.subtract( g15 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Subtracting g15 take away g13 [(-10) - 234567] using bytes: " );
      try {
         System.out.println( "      expecting: -234577\n" +
                             "        and got: " + g15.subtract( g13 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Subtracting g14 take away g16 [(-234567) - (-999999)] using bytes: " );
      try {
         System.out.println( "      expecting: 765432\n" +
                             "        and got: " + g14.subtract( g16 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Subtracting g16 take away g14 [(-999999) - (-234567)] using bytes: " );
      try {
         System.out.println( "      expecting: -765432\n" +
                             "        and got: " + g16.subtract( g14 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      System.out.println( "\n    Test " + makeTwoDigits() + ": Making a seventeenth new GinormousInt: "  );
      try {
         g17 = new GinormousInt( g17String );
         System.out.println( "      expecting: 765\n" +
                             "        and got: " + g17.toString() );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      System.out.println( "\n    Test " + makeTwoDigits() + ": Making a eightteenth new GinormousInt: "  );
      try {
         g18 = new GinormousInt( g18String );
         System.out.println( "      expecting: 23\n" +
                             "        and got: " + g18.toString() );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      System.out.println( "\n    Test " + makeTwoDigits() + ": Making a nineteenth new GinormousInt: "  );
      try {
         g19 = new GinormousInt( g19String );
         System.out.println( "      expecting: 56789\n" +
                             "        and got: " + g19.toString() );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      System.out.println( "\n    Test " + makeTwoDigits() + ": Making a twentieth new GinormousInt: "  );
      try {
         g20 = new GinormousInt( g20String );
         System.out.println( "      expecting: 37\n" +
                             "        and got: " + g20.toString() );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Subtracting g20 take away g18 [37 - 23] using bytes: " );
      try {
         System.out.println( "      expecting: 14\n" +
                             "        and got: " + g20.subtract( g18 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Subtracting g18 take away g20 [23 - 37] using bytes: " );
      try {
         System.out.println( "      expecting: -14\n" +
                             "        and got: " + g18.subtract( g20 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Subtracting g1 take away g1 [too long to list] using bytes: " );
      try {
         System.out.println( "      expecting: 000000000000000000000000000000000000000000000000000000000000000000\n" +
                             "        and got: " + g1.subtract( g1 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      System.out.println( "\n\n    TESTING MULTIPLY() METHOD:\n" +
                          "    ==========================" );
      System.out.println( "\n      Test " + makeTwoDigits() + ": Multiplying g7 by g12 [10 * 20]: " );
      try {
         System.out.println( "      expecting: 200\n" +
                             "        and got: " + g7.multiply( g12 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Multiplying g17 by g18 [765 * 23]: " );
      try {
         System.out.println( "      expecting: 17595\n" +
                             "        and got: " + g17.multiply( g18 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Multiplying g18 by g20 [23 * 37]: " );
      try {
         System.out.println( "      expecting: 851\n" +
                             "        and got: " + g18.multiply( g20 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Multiplying g19 by g20 [56789 * 37]: " );
      try {
         System.out.println( "      expecting: 2101193\n" +
                             "        and got: " + g19.multiply( g20 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Multiplying g18 by g17 [23 * 765]: " );
      try {
         System.out.println( "      expecting: 17595\n" +
                             "        and got: " + g18.multiply( g17 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Multiplying g17 by g19 [765 * 56789]: " );
      try {
         System.out.println( "      expecting: 43443585\n" +
                             "        and got: " + g17.multiply( g19 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Multiplying g14 by g18 [-234567 * 23]: " );
      try {
         System.out.println( "      expecting: -5395041\n" +
                             "        and got: " + g14.multiply( g18 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Multiplying g18 by g14 [23 * -234567]: " );
      try {
         System.out.println( "      expecting: -5395041\n" +
                             "        and got: " + g18.multiply( g14 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Multiplying g1 by g4:\n" +
                          "                  144127909719710664015092431502440849849506284148982076191826176553\n" +
                          "                               14412790971971066401509243150244084984950628410898207");
      try {
         System.out.println( "      expecting: 2077285436017306769697707308539031996620483564822094960119979421402123963227779392443205060360059497074989285293140471\n" +
                             "        and got: " + g1.multiply( g4 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n\n    TESTING DIVIDE() METHOD:\n" +
                          "    ========================" );
      System.out.println( "\n      Test " + makeTwoDigits() + ": Dividing g19 by g20 [56789 / 37]: " );
      try {
         System.out.println( "      expecting: 1534\n" +
                             "        and got: " + g19.divide( g20 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Dividing g20 by g19 [37 / 56789]: " );
      try {
         System.out.println( "      expecting: 0\n" +
                             "        and got: " + g20.divide( g19 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Dividing g20 by g20 [37 / 37]: " );
      try {
         System.out.println( "      expecting: 1\n" +
                             "        and got: " + g20.divide( g20 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Dividing g20 by zero [37 / 0]: " );
      try {
         System.out.println( "      expecting: exception\n" +
                             "        and got: " + g20.divide( GinormousInt.ZERO ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Dividing zero by g20 [0 / 37]: " );
      try {
         System.out.println( "      expecting: 0\n" +
                             "        and got: " + GinormousInt.ZERO.divide( g20 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n    Test " + makeTwoDigits() + ": Making a new GinormousInt g21: "  );
      try {
         g21 = new GinormousInt( g21String );
         System.out.println( "      expecting: 14412790971971066401509243150\n" +
                             "        and got: " + g21.toString() );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      System.out.println( "\n    Test " + makeTwoDigits() + ": Making a new GinormousInt g22: "  );
      try {
         g22 = new GinormousInt( g22String );
         System.out.println( "      expecting: 412790971971\n" +
                             "        and got: " + g22.toString() );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Dividing g21 by g22 [14412790971971066401509243150 / 412790971971]: " );
      try {
         System.out.println( "      expecting: 34915470421149654\n" +
                             "        and got: " + g21.divide( g22 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Dividing 23 by 4 [23 / 4]: " );
      try {
         GinormousInt g23 = new GinormousInt( "23" );
         GinormousInt g24 = new GinormousInt( "4" );
         System.out.println( "      expecting: 5\n" +
                             "        and got: " + g23.divide( g24 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Dividing 100 by 33 [100 / 33]: " );
      try {
         GinormousInt g24 = new GinormousInt( "100" );
         GinormousInt g25= new GinormousInt( "33" );
         System.out.println( "      expecting: 3\n" +
                             "        and got: " + g24.divide( g25 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n\n    TESTING REMAINDER() METHOD:\n" +
                          "    ===========================" );
      System.out.println( "\n      Test " + makeTwoDigits() + ": Mod'ing g19 by g20 [56789 % 37]: " );
      try {
         System.out.println( "      expecting: 31\n" +
                             "        and got: " + g19.remainder( g20 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Mod'ing g21 by g22 [14412790971971066401509243150 % 412790971971]: " );
      try {
         System.out.println( "      expecting: 11598895116\n" +
                             "        and got: " + g21.remainder( g22 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Mod'ing 23 by 4 [23 % 4]: " );
      try {
         GinormousInt g26 = new GinormousInt( "23" );
         GinormousInt g27 = new GinormousInt( "4" );
         System.out.println( "      expecting: 3\n" +
                             "        and got: " + g26.remainder( g27 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n      Test " + makeTwoDigits() + ": Mod'ing 100 by 33 [100 % 33]: " );
      try {
         GinormousInt g28 = new GinormousInt( "100" );
         GinormousInt g29 = new GinormousInt( "33" );
         System.out.println( "      expecting: 1\n" +
                             "        and got: " + g28.remainder( g29 ) );
      }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.exit( 0 );

   }
}
