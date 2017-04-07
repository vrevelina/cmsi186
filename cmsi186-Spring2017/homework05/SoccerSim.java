/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SoccerSim.java
 *  Purpose       :  Provides a class defining a soccer ball simulation
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-03-09
 *  Description   :  This class models a soccer field on which a number of soccer balls are placed and
 *                   can move, and on which a pole has been set up.  The field has a "starting point",
 *                   which is used as the origin, and has an x-width and a y-width, as well as an
 *                   orientation offset in degrees, such that 0.0° corresponds to north being straight
 *                   up and down, putting due south at 180°.
 *
 *  Notes         :  From the assignment web page at:
 *  <a href='http://bjohnson.lmu.build/cmsi186web/homework05.html'>http://bjohnson.lmu.build/cmsi186web/homework05.html</a>
 *                   Simultaneously, on a perfectly flat playground, at time 00:00:00.0 (hrs:mins:secs),
 *                   an arbitrary number of soccer balls are kicked, all at different speeds and
 *                   directions. We wish to find out, via a discrete simulation of the system, whether
 *                   a collision will ever take place, and, if so, where and when. Each ball has a
 *                   radius of 4.45 inches and weighs one pound. The center of the playground is presumed
 *                   to be the point (x,y) = (0.0,0.0). The following additional conditions apply:
 *                   1) Friction acts to slow each ball down until it comes to rest.  Your program should
 *                      simulate friction as a force that continuously decreases each ball's speed at the
 *                      rate of one per cent per second until it is traveling less than one inch per
 *                      second, at which point it comes to rest.
 *                   2) Data about each ball will be given to your program via four consecutive args,
 *                      namely the x- and y-coordinates of the ball's starting position [measured in
 *                      inches], followed by its speeds in the x- and y-directions [in feet per second].
 *                   3) If present, a final arg specifies the time slice [in seconds].  If missing, your
 *                      program should use a default time slice of one second.
 *                   4) As always, your program should check validity of the args.
 *                   5) Your program should output:
 *                      a) An initial report that gives the locations of all objects, including the
 *                         initial velocity of each ball
 *                      b) After every time slice, a report showing the location and velocity of every ball
 *                      c) A final report indicating the simulated time of the first collision, the objects
 *                         involved and their locations; or, the message NO COLLISION IS POSSIBLE, giving
 *                         the simulated time at which the program made that discovery.
 *
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

public class SoccerSim {

   private static final double DEFAULT_FIELD_WIDTH   = 1000.0;
   private static final double DEFAULT_FIELD_HEIGHT  = 1000.0;
   private static final int    DEFAULT_BALL_COUNT    = 5;
   private static final double DEFAULT_POLE_X_POS    = 321.0;
   private static final double DEFAULT_POLE_Y_POS    = 543.0;
   private static final double DEFAULT_ORIENTATION   = 0.0;
   private static final double DEFAULT_TIME_SLICE    = 1.0;
   private static final int    NUMBER_OF_BALL_PARAMS = 4;
   private static final String USAGE_MESSAGE         = "\n  Usage:" +
         "\n    java SoccerSim <field-X> <field-Y> <ball-parameters> ... <ball-parameters> [timeslice]" +
         "\n      where EACH <ball-parameters> field is a 4-tuple of the values:" +
         "\n            ball-starting-x, ball-starting-y, ball-direction, ball-speed" +
         "\n\n  Please try again.";

   private boolean  initialReport = true;
   private boolean  stillMoving   = true;
   private boolean  updateSpeed   = false;
   private double   sliceCount    = 0.0;
   private double   timeSlice     = DEFAULT_TIME_SLICE;
   private double[] time          = { 0.0, 0.0, 0.0 };
   private Clock    c             = null;
   private Field    f             = null;
   private Position pole          = null;

   public SoccerSim() {
      c = new Clock();
      pole = new Position( DEFAULT_POLE_X_POS, DEFAULT_POLE_Y_POS );
      f = new Field( DEFAULT_FIELD_WIDTH, DEFAULT_FIELD_HEIGHT, 0.0, pole, 5 );
   }

  /**
   *  Method to validate the input arguments
   *  @param arguments String array of the arguments supplied to the program
   */
   public void validateArgs( String arguments[] ) {

     // no arguments specified, so put out a usage message
      if( 0 == arguments.length ) {
         System.out.println( USAGE_MESSAGE );
         System.exit( 0 );

     // validation: first two are field x and y size of the field in feet
     //             if remaining count is odd, assume last is timeslice in seconds and remove
     //             if remaining count is not multiple of four, error
     //             note that if there are only 3 arguments, random ball info is assumed
      } else {
         int argCount = arguments.length - 2;  // take off the field X and Y
         if( 1 == (argCount % 2) ) {           // check for user-specified time slice argument
            try {
               timeSlice = Double.parseDouble( arguments[arguments.length - 1] );
            }
            catch( NumberFormatException nfe ) {
               System.out.println( "\n    Sorry, time slice must be a number.  Try again!" );
               System.exit( 1 );
            }
            argCount-- ;
         }
         if( 0 != (argCount % NUMBER_OF_BALL_PARAMS) ) {
            System.out.println( USAGE_MESSAGE );
            System.exit( 0 );
         } else if( 0 != (argCount / NUMBER_OF_BALL_PARAMS) ) {
            f = new Field( DEFAULT_FIELD_WIDTH, DEFAULT_FIELD_HEIGHT, 0.0, pole, 0 );
            try {
               for( int i = 2; i < argCount; i++ ) {
                  f.addBall( Double.parseDouble( arguments[i++] ),
                             Double.parseDouble( arguments[i++] ),
                             Double.parseDouble( arguments[i++] ),
                             Double.parseDouble( arguments[i] ) );
               }
               System.out.println( "\n Adding " + f.getBallCount() + " soccer balls to the sim." );
            }
            catch( NumberFormatException nfe ) {
               System.out.println( "\n    Sorry, ball parameters must be numbers.  Try again!" );
               System.exit( 1 );
            }
         }
         try {
            double fieldX = Double.parseDouble( arguments[0] );
            double fieldY = Double.parseDouble( arguments[1] );
            f.setNewFieldSize( fieldX, fieldY );
         }
         catch( NumberFormatException nfe ) {
            System.out.println( "\n    Bogus non-numeric field size argument, using defaults." );
         }
      }
   }

  /**
   *  method to report the status of the simulation for every clock tick
   *  @param  c  Clock object which keeps track of time
   *  NOTE: this method calls the clock.tick() method to get one second to elapse
   */
   public void report( Clock c ) {
      String output = "";
      if( initialReport ) {
         initialReport = false;
         output += "\nFIELD SIZE IS " + f.getFieldX() + " by " + f.getFieldY();
         output += "\nINITIAL REPORT at 00:00:00.0000";
         for( int i = 0; i < f.getBallCount(); i++ ) {
            output += "\nBall " + i + ":\t" + f.getBall( i ).toString();
         }
      } else {
         c.tick( time[0], time[1], time[2], timeSlice );
         output += "\nPROGRESS REPORT at " + c.toStringColons();
         for( int i = 0; i < f.getBallCount(); i++ ) {
            output += "\nBall " + i + ":\t" + f.getBall( i ).toString();
         }
      }
      System.out.println( output );
   }

  /**
   *  main method of the simulation
   *  @param  args  String array of the command line arguments
   */
   public static void main( String args[] ) {
      System.out.println( "\n  Hello, world, from the SoccerSim program!" );
      SoccerSim ss = new SoccerSim();
      System.out.println( ss.f.toString() );
      try {
         ss.validateArgs( args );
      }
      catch( Exception e ) {}
      ss.report( ss.c );
      while( true ) {
         ss.updateSpeed = ss.f.simUpdate( ss.timeSlice, ss.updateSpeed );
         ss.report( ss.c );
         if( ss.f.collisionCheck() ) {
            System.out.println( "\nCollision occurred, stopping sim....." );
            break;
         }
         if( !ss.f.isBallStillMoving() ) {
            System.out.println( "\nAll soccer balls have stopped moving, stopping sim......" );
            System.out.println( "NO COLLISION POSSIBLE!" );
            break;
         }
         if( (ss.sliceCount += ss.timeSlice) > 1.0 ) {
            ss.updateSpeed = true;
            ss.sliceCount = 0.0;
         }
      }
   }
}

