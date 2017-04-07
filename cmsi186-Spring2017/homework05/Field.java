/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Field.java
 *  Purpose       :  Provides a class defining a field on which the soccer ball simulation works
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-03-09
 *  Description   :  This class models a soccer field on which the soccer balls can move, and on which
 *                   the pole has been set up.  The field has a "starting point", which is used as the
 *                   origin, and has an x-width and a y-width, as well as an orientation.
 *
 *  Notes         :  The x and y values are in inches, no maximum, which makes it easier to handle the
 *                   unit of inches for the ball radius.  Collisions will occur when the centers of the
 *                   balls are 2 * 4.45 = 8.9 inches apart.
 *                   The pole is considered an ideal object without size; viewed from the top it is a
 *                   point.  Collisions with the pole will thus occur when ball centers are 4.45 inches
 *                   away from the pole.
 *                   Orientation is expected to be based on a 360-degree circle, with zero degrees to
 *                   represent North.  That way the direction of movement for each ball can be in degrees
 *                   which can be calculated randomly and added to the orientation for movement.  [I need
 *                   to think about this one....]
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

public class Field {

  /// fastest measured speed a soccer ball has ever been kicked
   private static final double MAX_VELOCITY = 131.00;
  /// distance between centers when two balls bump (in feet to maintain unit consistency)
   private static final double BALL_BUMP_DISTANCE_FT = (4.45 * 2) / 12.0;
  /// distance between center and pole when ball bumps pole (in feet to maintain unit consistency)
   private static final double POLE_BUMP_DISTANCE_FT =  4.45 / 12.0;

   private Position  origin      = new Position( 0.0, 0.0 );
   private double    xWidth      = 0.0;
   private double    yWidth      = 0.0;
   private double    orientation = 0.0;
   private Pole      pole        = null;
   private Ball[]    ballArray   = null;

  /**
   *  Constructor
   *   This one is pretty extensive, moreso than the other ones we've done
   *   That's because there is more initialization to be done with constructing a Field
   *   The simulation has lots of parameters that need to be handled
   *  @param width         double-precision value of the field's width in inches
   *  @param height        double-precision value of the field's height in inches
   *  @param oriented      double-precision value of the orientation from north, in degrees
   *  @param poleLocation  Position object containing coordinates on which the Pole will be placed
   *  @param ballCount     integer value of the number of Ball objects to be used in the simulation
   */
   public Field( double width, double height, double oriented, Position poleLocation, int ballCount ) {

      double   randomX = 0.0;       // variable to hold random X values for loop calculations
      double   randomY = 0.0;       // variable to hold random Y values for loop calculations
      double   randomD = 0.0;       // variable to hold random direction values for loop calculations
      double   randomS = 0.0;       // variable to hold random speed values for loop calculations
      Position randomP = null;      // variable to hold random Position values for loop calculations

      xWidth      = width;                      // Initialize
      yWidth      = height;                     // Initialize
      orientation = oriented;                   // Initialize
      pole        = new Pole( poleLocation );   // Initialize
      ballArray   = new Ball[ballCount];        // Initialize

      for( int i = 0; i < ballArray.length; i++ ) {
         randomX = (double)((Math.floor(Math.random() * (xWidth / 2.0))  + 1.0));
         randomY = (double)((Math.floor(Math.random() * (yWidth / 2.0))  + 1.0));
         randomD = (double)((Math.floor(Math.random() * 360.0)        + 1.0));
         randomS = (double)((Math.floor(Math.random() * MAX_VELOCITY) + 1.0));
         randomP = new Position( randomX, randomY );
         ballArray[i] = new Ball( randomP, randomD, randomS );
      }
   }

  /**
   *  method to get the field X size
   */
   public double getFieldX() {
      return xWidth;
   }

  /**
   *  method to get the field Y size
   */
   public double getFieldY() {
      return yWidth;
   }

  /**
   *  method to reset the field size
   *  @param  xValue  double-precision value for the new x size
   *  @param  yValue  double-precision value for the new y size
   */
   public void setNewFieldSize( double x, double y ) {
      xWidth = x;
      yWidth = y;
   }

  /**
   *  method to get the ball count
   *  @return size of the ballArray
   */
   public int getBallCount() {
      return ballArray.length;
   }

  /**
   *  method to return a single Ball object
   *  @param  index  integer of the index of the Ball to return
   *  @return Ball object indicated by index argument
   */
   public Ball getBall( int index ) {
      if( (0 > index) || (index > ballArray.length) ) {
         return ballArray[0];
      }
      return ballArray[index];
   }

  /**
   *  method to add a Ball object to the Field using X/Y location
   *  @param xLocation  double-precision value of the Ball's X value
   *  @param yLocation  double-precision value of the Ball's Y value
   *  @param direction  double-precision value of the Ball's direction of movement
   *  @param speed      double-precision value of the Ball's starting speed
   */
   public void addBall( double xLocation, double yLocation, double direction, double speed ) {
      Position p = new Position( xLocation, yLocation );
      Ball b = new Ball( p, direction, speed );
      Ball[] newBallArray = new Ball[ballArray.length + 1];
      for( int i = 0; i < ballArray.length; i++ ) {
         newBallArray[i] = ballArray[i];
      }
      newBallArray[newBallArray.length - 1] = b;
      ballArray = newBallArray;
   }

  /**
   *  method to add a Ball object to the Field using position location
   *  @param p          Position object of the Ball's location
   *  @param direction  double-precision value of the Ball's direction of movement
   *  @param speed      double-precision value of the Ball's starting speed
   */
   public void addBall( Position p, double direction, double speed ) {
      Ball b = new Ball( p, direction, speed );
      Ball[] newBallArray = new Ball[ballArray.length + 1];
      for( int i = 0; i < ballArray.length; i++ ) {
         newBallArray[i] = ballArray[i];
      }
      newBallArray[newBallArray.length - 1] = b;
      ballArray = newBallArray;
   }

  /**
   *  method to see if a ball is off the field
   *  @param   index integer index of Ball object to see location
   *  @return  true if ball is out of bounds
   */
   public boolean isBallOutOfBounds( int index ) {
      double pos[] = new double[2];

      return false;
   }

  /**
   *  method to see if any ball is still moving
   *  @param   index integer index of Ball object to see location
   *  @return  true if ball is still moving (speed > 1.0 ft/sec)
   */
   public boolean isBallStillMoving() {
      boolean returnValue = false;
      for( int i = 0; i < ballArray.length; i++ ) {
         if( 1.0 < (ballArray[i].getCurrentSpeed() * 12.0) ) {
            returnValue = true;
         }
      }
      return returnValue;
   }

  /**
   *  method to update all the Ball locations and speeds
   *  @param  timeSlice  double-precision value of the time slice argument
   *  Note: if the time slice is not one second, it changes when the speed is
   *        recalculated
   */
   public boolean simUpdate( double timeSlice, boolean updateSpeed ) {
      for( int i = 0; i < ballArray.length; i++ ) {
         ballArray[i].updatePositionForOneTick( timeSlice );
         if( updateSpeed ) {
            ballArray[i].updateSpeedForOneSecond();
            return false;
         }
      }
      return true;
   }

  /**
   *  method to check if any collisions have occurred
   */
   public boolean collisionCheck() {
      double   distance = 0.0;
      double   dX       = 0.0;
      double   dY       = 0.0;
      for( int i = 0; i < (ballArray.length - 1); i++ ) {
         for( int j = (i +1); j < ballArray.length; j++ ) {
            dX = Math.abs( ballArray[i].getCurrentPosition().getPosition()[0] -
                           ballArray[j].getCurrentPosition().getPosition()[0] );
            dY = Math.abs( ballArray[i].getCurrentPosition().getPosition()[1] -
                           ballArray[j].getCurrentPosition().getPosition()[1] );
            distance = Math.sqrt( (dX * dX) + (dY * dY) );
            if( distance <= BALL_BUMP_DISTANCE_FT ) {
               return true;
            }
         }
         dX = Math.abs( ballArray[i].getCurrentPosition().getPosition()[0] -
                        pole.getCurrentPosition().getPosition()[0] );
         dY = Math.abs( ballArray[i].getCurrentPosition().getPosition()[1] -
                        pole.getCurrentPosition().getPosition()[1] );
         distance = Math.sqrt( (dX * dX) + (dY * dY) );
         if( distance <= POLE_BUMP_DISTANCE_FT ) {
            return true;
         }
      }
      return false;
   }

  /**
   * our venerable "toString()" method with 'pretty' output
   * @return String version of the Field
   */
   public String toString() {
      String returnValue = " width: "         + xWidth           + "\n" +
                           " height: "        + yWidth           + "\n" +
                           " orientation: "   + orientation      + "\n" +
                           " pole location: " + pole.toString()  + "\n" +
                           " ball count: "    + ballArray.length + "\n";
      for( int i = 0; i < ballArray.length; i++ ) {
         returnValue += "  Ball " + i + " parameters: " + ballArray[i].toString() + "\n";
      }
      return returnValue;
   }

   public static void main( String args[] ) {
      System.out.println( "\n   Testing the Field class....." );
      Position pl = new Position( 30., 40.0 );
      Field field = new Field( 500.0, 500.0, 0.0, pl, 5 );
      System.out.println( "\n     new Field created....");
      System.out.println( "\n     pretty parameters:\n" + field.toString() );
      Position p2 = new Position( 123.4, 234.5 );
      Ball b2 = new Ball( p2, 100.0, 25.0 );
      System.out.println( "\n     new Ball b2 created, parameters:\n" + b2.toString() );
      field.addBall( 234.5, 345.6, 79.0, 23.0 );
      field.addBall( p2, 83.0, 29.0 );
      System.out.println( "\n     parameters:\n" + field.toString() );
      System.out.println( "\n     values for ball[3]: " + field.getBall( 3 ).toString() );
      field.simUpdate( 1.0, true );
      System.out.println( "\n     values for ball[3]: " + field.getBall( 3 ).toString() );
   }

}
