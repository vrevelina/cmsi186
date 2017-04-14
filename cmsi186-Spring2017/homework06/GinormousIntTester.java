/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  GinormousIntTester.java
 * Purpose    :  Test Harness for the GinormousInt java class
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

public class GinormousIntTester {

   private static String g01String = "144127909719710664015092431502440849849506284148982076191826176553";
   private static String g02String = "144127909719710664015092431502440849849506284148982076191826176553";
   private static String g03String = "144127909719710664015092431502440849849506284108982076191826176553";
   private static String g04String = "14412790971971066401509243150244084984950628410898207";
   private static String g05String = "0";
   private static String g06String = "1";
   private static String g07String = "10";
   private static String g08String = "";
   private static String g09String = "";
   private static String g10String = "";
   private static String g11String = "";

   public GinormousIntTester() {
      super();
   }

   public static void main( String[] args ) {
      GinormousIntTester git = new GinormousIntTester();

      System.out.println( "\n  Hello, world, from the GinormousInt program!!\n" );

      System.out.println( "    TESTING CONSTRUCTOR AND CONSTANTS:\n" +
                          "    ==================================" );
      System.out.println( "    Making a new GinormousInt: " );
      GinormousInt g1 = new GinormousInt( g01String );

      System.out.println( "      expecting: " + g01String + "\n" +
                          "        and got: " + g1.toString() );

      System.out.println( "\n    Making a second new GinormousInt [same as first]: " );
      GinormousInt g2 = new GinormousInt( g02String );
      System.out.println( "      expecting: " + g02String + "\n" +
                          "        and got: " + g2.toString() );
      System.out.println( "    Comparing equality of g1 and g2 with 'equals()': " );
      System.out.println( "      expecting: true\n" + "        and got: " + g1.equals( g2 ) );

      System.out.println( "\n    Making a third new GinormousInt [differs at position 47    |]: " +
                          "\n           [position indicated by down arrow]                  v   " );
      GinormousInt g3 = new GinormousInt( g03String );
      System.out.println( "      expecting: " + g03String + "\n" +
                          "        and got: " + g3.toString() );
      System.out.println( "          g1 is: " + g1.toString() );
      System.out.println( "    Comparing equality of g1 and g3 [detect different digit]: " );
      System.out.println( "      expecting: false\n" + "        and got: " + g1.equals( g3 ) );

      System.out.println( "\n    Making a fourth new GinormousInt [same as g3 but truncated]: "  );
      GinormousInt g4 = new GinormousInt( g04String );
      System.out.println( "      expecting: " + g04String + "\n" +
                          "        and got: " + g4.toString() );
      System.out.println( "    Comparing equality of g3 and g4 [detect different length prior to detecting different digit]: " );
      System.out.println( "      expecting: false\n" + "        and got: " + g3.equals( g4 ) );

      System.out.println( "\n    Making a fifth new GinormousInt, checking GinormousInt.ZERO: "  );
      GinormousInt g5 = new GinormousInt( "0" );
      System.out.println( "      expecting: " + GinormousInt.ZERO + "\n" +
                          "        and got: " + g5.toString() );

      System.out.println( "\n    Making a sixth new GinormousInt, checking GinormousInt.ONE: "  );
      GinormousInt g6 = new GinormousInt( "1" );
      System.out.println( "      expecting: " + GinormousInt.ONE + "\n" +
                          "        and got: " + g6.toString() );

      System.out.println( "\n    Making a seventh new GinormousInt, checking GinormousInt.TEN: "  );
      GinormousInt g7 = new GinormousInt( "10" );
      System.out.println( "      expecting: " + GinormousInt.TEN + "\n" +
                          "        and got: " + g7.toString() );

      System.out.println( "\n    Creating several long type values to check the 'valueOf()' method: " );
      long long01 = Long.MAX_VALUE;
      long long02 = Long.MIN_VALUE;
      long long03 = 1234567890;
      System.out.println( "      expecting: " + Long.MAX_VALUE + "\n" +
                          "        and got: " + long01 );
      System.out.println( "      expecting: " + Long.MIN_VALUE + "\n" +
                          "        and got: " + long02 );
      System.out.println( "      expecting: 1234567890\n" +
                          "        and got: " + long03 );

      System.out.println( "    Now testing 'valueOf()' method: " );
      GinormousInt g8  = GinormousInt.valueOf( long01 );
      GinormousInt g9  = GinormousInt.valueOf( long02 );
      GinormousInt g10 = GinormousInt.valueOf( long03 );
      System.out.println( "      expecting: " + Long.MAX_VALUE + "\n" +
                          "        and got: " + g8.toString() );
      System.out.println( "      expecting: " + Long.MIN_VALUE + "\n" +
                          "        and got: " + g9.toString() );
      System.out.println( "      expecting: 1234567890\n" +
                          "        and got: " + g10.toString() );

      System.out.println( "\n    Making an eleventh and twelfth new GinormousInt, calling addByte method: "  );
      GinormousInt g11 = new GinormousInt( "10" );
      System.out.println( "      expecting: 10\n" +
                          "        and got: " + g11.toString() );
      GinormousInt g12 = new GinormousInt( "20" );
      System.out.println( "      expecting: 20\n" +
                          "        and got: " + g12.toString() );
      System.out.println( "    Adding g11 and g12: " );
      System.out.println( "      expecting: 30 and got " + g11.addByte( g12 ) );

      System.out.println( "\n    Making a thirteenth new GinormousInt, calling add methods: "  );
      GinormousInt g13 = new GinormousInt( "234567" );
      System.out.println( "      expecting: 234567\n" +
                          "        and got: " + g13.toString() );
      System.out.println( "    Adding g11 and g13 [10 + 234567] using bytes: " );
      System.out.println( "      expecting: 234577 and got " + g11.addByte( g13 ) );
      System.out.println( "    Adding g11 and g13 [10 + 234567] using ints: " );
      System.out.println( "      expecting: 234577 and got " + g11.addInt( g13 ) );
      System.out.println( "    Adding g13 and g11 [234567 + 10] using bytes: " );
      System.out.println( "      expecting: 234577 and got " + g13.addByte( g11 ) );
      System.out.println( "    Adding g13 and g11 [234567 + 10] using ints: " );
      System.out.println( "      expecting: 234577 and got " + g13.addInt( g11 ) );

      System.out.println( "\n    Making a fourteenth new GinormousInt, calling add methods: "  );
      GinormousInt g14 = new GinormousInt( "-234567" );
      System.out.println( "      expecting: -234567\n" +
                          "        and got: " + g14.toString() );
      System.out.println( "\n    Making a fifteenth new GinormousInt, calling add methods: "  );
      GinormousInt g15 = new GinormousInt( "-10" );
      System.out.println( "      expecting: -10\n" +
                          "        and got: " + g15.toString() );
      System.out.println( "    Adding g14 and g15 [-234567 + -10] using bytes: " );
      System.out.println( "      expecting: -234577 and got " + g14.addByte( g15 ) );
      System.out.println( "    Adding g14 and g15 [-234567 + -10] using ints: " );
      System.out.println( "      expecting: -234577 and got " + g14.addInt( g15 ) );
      System.out.println( "    Adding g15 and g14 [-10 + -234567] using bytes: " );
      System.out.println( "      expecting: -234577 and got " + g15.addByte( g14 ) );
      System.out.println( "    Adding g15 and g14 [-10 + -234567] using ints: " );
      System.out.println( "      expecting: -234577 and got " + g15.addInt( g14 ) );

      System.out.println( "\n    Making a sixteenth new GinormousInt, calling add methods: "  );
      GinormousInt g16 = new GinormousInt( "-999999" );
      System.out.println( "      expecting: -999999\n" +
                          "        and got: " + g16.toString() );
      System.out.println( "    Adding g14 and g16 [-234567 + -999999] using bytes: " );
      System.out.println( "      expecting: -1234566 and got " + g14.addByte( g16 ) );
      System.out.println( "    Adding g14 and g16 [-234567 + -999999] using ints: " );
      System.out.println( "      expecting: -1234566 and got " + g14.addInt( g16 ) );
      System.out.println( "    Adding g16 and g14 [-999999 + -234567] using bytes: " );
      System.out.println( "      expecting: -1234566 and got " + g16.addByte( g14 ) );
      System.out.println( "    Adding g16 and g14 [-999999 + -234567] using ints: " );
      System.out.println( "      expecting: -1234566 and got " + g16.addInt( g14 ) );

      System.out.println( "\n      Adding g1 and g4 using bytes: " );
      System.out.println( "      expecting: 144127909719725076806064402568842359092656528233967026820237074760\n" +
                          "        and got: " + g1.addByte( g4 ) );

      System.out.println( "\n      Adding g4 and g1 using ints: " );
      System.out.println( "      expecting: 144127909719725076806064402568842359092656528233967026820237074760\n" +
                          "        and got: " + g4.addInt( g1 ) );

      System.out.println( "\n    Checking compareTo() method on g1 and g2: "  );
      System.out.println( "      expecting: 0 and got: " + g1.compareTo( g2 ) );

      System.out.println( "\n    Checking compareTo() method on g2 and g1: "  );
      System.out.println( "      expecting: 0 and got: " + g2.compareTo( g1 ) );

      System.out.println( "\n    Checking compareTo() method on g1 and g3: "  );
      System.out.println( "      expecting: positive value and got: " + g1.compareTo( g3 ) );

      System.out.println( "\n    Checking compareTo() method on g3 and g1: "  );
      System.out.println( "      expecting: negative value and got: " + g3.compareTo( g1 ) );

      System.out.println( "\n    Checking compareTo() method on g3 and g4: "  );
      System.out.println( "      expecting: positive value and got: " + g3.compareTo( g4 ) );

      System.exit( 0 );

   }
}
