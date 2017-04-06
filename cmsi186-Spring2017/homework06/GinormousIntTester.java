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
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class GinormousIntTester {

   public GinormousIntTester() {
      super();
   }

   public static void main( String[] args ) {
      GinormousIntTester git = new GinormousIntTester();

      System.out.println( "\n  Hello, world, from the GinormousInt program!!\n" );

      System.out.println( "    TESTING CONSTRUCTOR AND CONSTANTS:\n" +
                          "    ==================================" );
      System.out.println( "    Making a new GinormousInt: " );
      GinormousInt g1 = new GinormousInt( "144127909719710664015092431502440849849506284148982076191826176553" );

      System.out.println( "      expecting: 144127909719710664015092431502440849849506284148982076191826176553\n" +
                          "        and got: " + g1.toString() );

      System.out.println( "\n    Making a second new GinormousInt [same as first]: " );
      GinormousInt g2 = new GinormousInt( "144127909719710664015092431502440849849506284148982076191826176553" );
      System.out.println( "      expecting: 144127909719710664015092431502440849849506284148982076191826176553\n" +
                          "        and got: " + g2.toString() );
      System.out.println( "\n    Comparing equality of g1 and g2 with 'equals()': " );
      System.out.println( "      expecting: true\n" + "        and got: " + g1.equals( g2 ) );

      System.out.println( "\n    Making a third new GinormousInt [differs at position 47    |]: " +
                          "\n                                                               v   " );
      GinormousInt g3 = new GinormousInt( "144127909719710664015092431502440849849506284108982076191826176553" );
      System.out.println( "      expecting: 144127909719710664015092431502440849849506284108982076191826176553\n" +
                          "        and got: " + g3.toString() );
      System.out.println( "\n    Comparing equality of g1 and g3 [detect different digit]: " );
      System.out.println( "      expecting: true\n" + "        and got: " + g1.equals( g3 ) );

      System.out.println( "\n    Making a fourth new GinormousInt [same as g3 but truncated]: "  );
      GinormousInt g4 = new GinormousInt( "14412790971971066401509243150244084984950628410898207" );
      System.out.println( "      expecting: 14412790971971066401509243150244084984950628410898207\n" +
                          "        and got: " + g4.toString() );
      System.out.println( "\n    Comparing equality of g3 and g4 [detect different length prior to detecting different digit]: " );
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

      System.out.println( "\n    Making a eighth new GinormousInt, calling add method: "  );
      GinormousInt g8 = new GinormousInt( "10" );
      System.out.println( "      expecting: blah and got " + g8.add( g1 ) );

      System.exit( 0 );

   }
}
