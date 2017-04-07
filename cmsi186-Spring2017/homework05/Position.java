/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Position.java
 *  Purpose       :  Provides a class defining an x,y cartesian coordinate position for discrete simulations
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-03-10
 *  Description   :  This class keeps track of a specific position on a cartesian coordinate system.
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-03-10  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.text.DecimalFormat;

public class Position {

   private double xValue = 0.0;     // the x value of this position on the cartesian plane
   private double yValue = 0.0;     // the y value of this position on the cartesian plane

  /**
   * Constructor
   *  @param x  double-precision value of the x value of the location
   *  @param y  double-precision value of the y value of the location
   */
   public Position( double x, double y ) {
      xValue = x;
      yValue = y;
   }

  /**
   * set a new position for this Position
   *  @param  x  double-precision value for the new x position
   *  @param  y  double-precision value for the new y position
   */
   public void setPosition( double x, double y ) {
      xValue = x;
      yValue = y;
   }

  /**
   * return this position on the cartesian plane
   *  @return  Position object which is this instance
   */
   public double[] getPosition() {
      double[] myPos = { xValue, yValue };
      return myPos;
   }

  /**
   * our venerable "toString()" representation
   *  @return  String-y version of what this Position is
   */
   public String toString() {
      return "[" + xValue + "," + yValue + "]";
   }

  /**
   * a main method for testing -- pretty simple
   *  @param args[] String array of command line arguments
   */
   public static void main( String args[] ) {
      Position p1 = new Position( 0.0, 0.0 );
      Position p2 = new Position( 1.0, 1.0 );
      Position p3 = new Position( 2.0, -3.0 );
      System.out.println( "Testing Position creation: " );
      System.out.println( "Position p1 is: " + p1.toString() );
      System.out.println( "Position p2 is: " + p2.toString() );
      System.out.println( "Position p3 is: " + p3.toString() );
      p1.setPosition( 10.0, 12.0 );
      p2.setPosition( -5.3, -25.99 );
      System.out.println( "Changed locations: " );
      System.out.println( "Position p1 is: " + p1.toString() );
      System.out.println( "Position p2 is: " + p2.toString() );
      System.out.println( "Position p3 is: " + p3.toString() );
      System.out.println( "Checking 'getPosition()': " );
      System.out.println( "Position is: " + p3.getPosition()[0] + " " + p3.getPosition()[1] );
   }
}
