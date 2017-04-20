/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  TupleTestHarness.java
 * Purpose    :  Test harness for the Tuple class
 * @author    :  Professor Don Murphy
 * @author    :  B.J. Johnson totally ripped off from the original
 * Date       :  2017-04-19
 * Description:  Duh.... see the Purpose above
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-19  B.J. Johnson  Initial writing and release; stolen blatently from Professor Murphy
 *                                    with his permission; added this comment block and some javadocs
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class TupleTestHarness {

    private static int attempts = 0;
    private static int successes = 0;

   /**
    *  the main method which calls all the test methods
    */
    public static void main(String[] args) {
        attempts = 0;
        successes = 0;

        test_toString();
        test_setElement();
        test_add();
        test_equals();
        test_getElement();
        test_length();
        test_total();

        System.out.println(successes + "/" + attempts + " tests passed.");
    }

   /**
    *  method to display success or failure as a result of a test
    *  @param  value  boolean success/failure
    *  Note that this method keeps track of the count of tests run
    *    as well as the number of successes
    */
    private static void displaySuccessIfTrue(boolean value) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println(value ? "success" : "failure");
    }

   /**
    *  method to display a failure, probably for things that are
    *   supposed to fail on purpose [that's a guess]
    */
    private static void displayFailure() {
        displaySuccessIfTrue(false);
    }

   /**
    *  method to test the "toString()" method a buncha times
    */
    public static void test_toString() {
        try {
            displaySuccessIfTrue("Impossible tally".equals(Tuple.IMPOSSIBLE.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(Tuple.IMPOSSIBLE.isImpossible());
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        // Only one impossible.
        Tuple fakeImpossible = new Tuple(new int[0]);
        try {
            displaySuccessIfTrue("<>".equals(fakeImpossible.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(fakeImpossible.equals(Tuple.IMPOSSIBLE));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(!fakeImpossible.isImpossible());
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            Tuple t0 = new Tuple(0);
            displaySuccessIfTrue("<>".equals(t0.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            Tuple t1 = new Tuple(1);
            displaySuccessIfTrue("<0>".equals(t1.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            Tuple t2 = new Tuple(2);
            displaySuccessIfTrue("<0,0>".equals(t2.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            Tuple t3 = new Tuple(new int[] { 3, 1, 2 });
            displaySuccessIfTrue("<3,1,2>".equals(t3.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

   /**
    *  method to test the "setElement()" method a couple of times
    *   makes a few Tuples and checks that you got what you paid for
    *   note that the "toString()" method puts angle brackets around
    *   the tuple for easy reading
    */
    public static void test_setElement() {
        Tuple t0 = new Tuple(new int[] { 3, 1, 2 });
        try {
            t0.setElement(0, -1);
            displaySuccessIfTrue("<-1,1,2>".equals(t0.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            t0.setElement(1, 0);
            displaySuccessIfTrue("<-1,0,2>".equals(t0.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            t0.setElement(2, 15);
            displaySuccessIfTrue("<-1,0,15>".equals(t0.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

   /**
    *  method to test adding two tuples together.  Only one test, but theoreticall
    *   should test a lot of different sizes, not just "three-Tuples"
    */
    public static void test_add() {
        try {
            Tuple t0 = new Tuple(new int[] { -1, 0, 2 });
            Tuple t1 = new Tuple(new int[] { 3, 1, 2 });
            Tuple sum = t0.add(t1);
            displaySuccessIfTrue("<2,1,4>".equals(sum.toString()) &&
                    "<-1,0,2>".equals(t0.toString()) &&
                    "<3,1,2>".equals(t1.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

   /**
    *  method to test the "equals()" method
    */
    public static void test_equals() {
        Tuple t0 = new Tuple(new int[] { -1, 0, 2 });
        Tuple t1 = new Tuple(new int[] { -1, 0, 2 });
        
        try {
            displaySuccessIfTrue(!t0.equals("some string object"));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(!t0.equals(new Tuple(new int[] { -1, 0 })));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(!t0.equals(new Tuple(3)));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(t0.equals(t1) & t1.equals(t0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

   /**
    *  method to test the "getElement()" method
    */
    public static void test_getElement() {
        Tuple t0 = new Tuple(new int[] { -1, 0, 2 });
        
        try {
            displaySuccessIfTrue(-1 == t0.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == t0.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(2 == t0.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

   /**
    *  method to test the "length()" method
    */
    public static void test_length() {
        try {
            Tuple t0 = new Tuple(0);
            displaySuccessIfTrue(0 == t0.length());
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            Tuple t1 = new Tuple(new int[] { -1, 0, 2 });
            displaySuccessIfTrue(3 == t1.length());
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

   /**
    *  method to test the "total()" method
    */
    public static void test_total() {
        try {
            Tuple t0 = new Tuple(0);
            displaySuccessIfTrue(0 == t0.total());
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            Tuple t1 = new Tuple(new int[] { -1, 0, 2 });
            displaySuccessIfTrue(1 == t1.total());
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

}
