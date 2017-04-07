/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Pole.java
 *  Purpose       :  Provides a class defining a pole on the soccer field where the balls are
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-03-09
 *  Description   :  This class models a pole which is set up on the soccer field of the simulation.  It
 *                   doesn't move, but the moving balls may bump into it.  The pole has a location on the
 *                   field.  That's about it, but we'll see what happens as things develop.
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-03-09  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.text.DecimalFormat;

public class Pole {

   private Position polePosition = null;     // the position location of the pole

  /**
   * Constructor
   *  @param p Position object of this pole
   */
   public Pole( Position p ) {
        polePosition = p;
   }

  /**
   *  method to fetch the position of the pole
   */
   public Position getCurrentPosition() {
      return polePosition;
   }

  /**
   * method to return a "string-y" version of this pole's location
   *  @return a string with this pole's cartesian coordinate location
   */
   public String toString() {
      return "[" + polePosition.getPosition()[0] + "," + polePosition.getPosition()[1] + "]";
   }

  /**
   * a main method for testing -- pretty simple
   *  @param args[] String array of command line arguments
   */
   public static void main( String args[] ) {
      System.out.println( "\n   Testing the Pole class....." );
      Position p1 = new Position( 3.0, 4.0 );
      Pole pole = new Pole( p1 );
      System.out.println( "    Pole is located at: " + pole.toString() );
      p1 = new Position( -5.0, -14.0 );
      pole = new Pole( p1 );
      System.out.println( "    Pole moved, is now located at: " + pole.toString() );
   }

}
