/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Card.java
 *  Purpose       :  Provides a class describing a playing card
 *  Author        :  B.J. Johnson
 *  Date          :  2017-02-06
 *  Description   :  This class provides everything needed (pretty much) to describe a playing card,
 *                   including its suit and rank, along with a couple of constructors.  There is a method
 *                   to output what this card actually *is*, generically, no matter what its suit and
 *                   rank might have been assigned.  There is a main method included [empty] for use as
 *                   a test harness if desired.
 *  Notes         :  Stolen from Dr. Dorin pretty much verbatim, then modified to show some interesting
 *                   things about Java, and to add this header block and some JavaDoc comments.
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-06  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class Card {

  /**
   * public constants: ranks
   */
   public static final int ACE   = 1;
   public static final int DEUCE = ACE   + 1;
   public static final int TWO   = ACE   + 1;
   public static final int THREE = TWO   + 1;
   public static final int FOUR  = THREE + 1;
   public static final int FIVE  = FOUR  + 1;
   public static final int SIX   = FIVE  + 1;
   public static final int SEVEN = SIX   + 1;
   public static final int EIGHT = SEVEN + 1;
   public static final int NINE  = EIGHT + 1;
   public static final int TEN   = NINE  + 1;
   public static final int JACK  = TEN   + 1;
   public static final int KNAVE = TEN   + 1;
   public static final int QUEEN = JACK  + 1;
   public static final int KING  = QUEEN + 1;

  /**
   * public constants: suits
   */
   public static final int SPADES   = 1;
   public static final int HEARTS   = SPADES   + 1;
   public static final int DIAMONDS = HEARTS   + 1;
   public static final int CLUBS    = DIAMONDS + 1;

  /**
   * private instance data
   */
   private int rank;
   private int suit;

   // public constructor:
  /**
   * constructor
   * @param rank int value containing THIS card's rank
   * @param suit int value containing THIS card's suit
   * @throws IllegalArgumentException
   * @note parameters are checked for validity; invalid values throw "IllegalArgumentException"
   * @return Constructors DON'T HAVE RETURN VALUES, NIMROD!
   */
   public Card( int rank, int suit ) {
      if( (Card.ACE > rank | Card.KING < rank) | (Card.SPADES > suit | Card.CLUBS < suit) ) {
         throw new IllegalArgumentException();
      } else {
         this.rank = rank;
         this.suit = suit;
      }
   }

  /**
   * @return the suit of THIS card instance
   */
   public int getSuit() {
      return this.suit;
   }

  /**
   * @return the rank of THIS card instance
   */
   public int getRank() {
      return this.rank;
   }

  /**
   * @return Public Instance method that returns a String representation of THIS card instance
   */
   public String toString() {
      String result = "";
      switch( this.rank ) {
         case 1 : result = "Ace of "; break;
         case 2 : result = "Deuce of "; break;
         case 3 : result = "Three of "; break;
         case 4 : result = "Four of "; break;
         case 5 : result = "Five of "; break;
         case 6 : result = "Six of "; break;
         case 7 : result = "Seven of "; break;
         case 8 : result = "Eight of "; break;
         case 9 : result = "Nine of "; break;
         case 10 : result = "Ten of "; break;
         case 11 : result = "Jack of "; break;
         case 12 : result = "Queen of "; break;
         case 13 : result = "King of "; break;
         default : throw new UnsupportedOperationException();
      }

      switch( this.suit ) {
         case  1 : result += "Spades"; break;
         case  2 : result += "Hearts"; break;
         case  3 : result += "Diamonds"; break;
         case  4 : result += "Clubs"; break;
         default : throw new UnsupportedOperationException();
      }
      return result;
   }

  /**
   * @return Class-wide method that returns a String representation of THIS card instance
   */
   public static String toString( Card c ) {
      return c.toString();
   }

  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
      // You do this part!
   }

}
