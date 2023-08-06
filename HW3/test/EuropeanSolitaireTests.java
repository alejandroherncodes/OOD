import static org.junit.Assert.*;

import cs3500.marblesolitaire.model.hw03.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

import org.junit.Before;
import org.junit.Test;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;

/**
 * Tests if EuropeanSolitaireModel's constructors and methods work using valid instances.
 */
public class EuropeanSolitaireTests {

  MarbleSolitaireModel noParamBoard;
  MarbleSolitaireModel oneParamBoard;
  MarbleSolitaireModel twoParamBoard;
  MarbleSolitaireModel threeParamBoard;

  @Before
  public void setup() {
     noParamBoard = new EuropeanSolitaireModel();
     oneParamBoard = new EuropeanSolitaireModel(3);
     twoParamBoard = new EuropeanSolitaireModel(4, 4);
     threeParamBoard = new EuropeanSolitaireModel(7, 10, 6);
  }

  /** noParamBoard is an example of an empty constructor that it creates a board
   *  of size 7 with an initial score of 36 and an empty slot at (3,3). There is a marble
   *  at every valid slot on the board. */
  @Test
  public void testNoArgumentConstructor() {
    assertEquals(7, noParamBoard.getBoardSize());
    assertEquals(36, noParamBoard.getScore());
    assertEquals(SlotState.Empty, noParamBoard.getSlotAt(3, 3));
    assertEquals(SlotState.Invalid, noParamBoard.getSlotAt(0, 0));
    assertEquals(SlotState.Invalid, noParamBoard.getSlotAt(6, 6));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(1, 1));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(5, 5));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(1, 5));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(5, 1));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(6, 2));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(0, 3));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(1, 4));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(2, 3));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(3, 5));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(4, 0));
  }

  /** oneParamBoard is an example of an one parameter constructor that takes in a side length
   *  and creates a board of size 7 with an initial score of 36 and an empty slot at (3,3).
   *  There is a marble at every valid slot on the board. */
  @Test
  public void testOneArgumentConstructor() {
    assertEquals(7, oneParamBoard.getBoardSize());
    assertEquals(36, oneParamBoard.getScore());
    assertEquals(SlotState.Invalid, oneParamBoard.getSlotAt(6, 6));
    assertEquals(SlotState.Invalid, oneParamBoard.getSlotAt(0, 0));
    assertEquals(SlotState.Empty, oneParamBoard.getSlotAt(3, 3));
    assertEquals(SlotState.Marble, oneParamBoard.getSlotAt(3, 4));
    assertEquals(SlotState.Marble, oneParamBoard.getSlotAt(5, 1));
    assertEquals(SlotState.Marble, oneParamBoard.getSlotAt(5, 2));
    assertEquals(SlotState.Marble, oneParamBoard.getSlotAt(4, 4));
  }

  @Test
  public void testOneArgumentConstructorExceptions() {
    try {
      EuropeanSolitaireModel threeParamBoard = new EuropeanSolitaireModel(10);
      fail("No exception thrown for odd thickness");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      EuropeanSolitaireModel threeParamBoard = new EuropeanSolitaireModel(1);
      fail("No exception thrown for invalid thickness");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      EuropeanSolitaireModel threeParamBoard = new EuropeanSolitaireModel(-1);
      fail("No exception thrown for negative thickness");
    } catch (Exception e) {
      // Passes, expected
    }
  }

  /** twoParamBoard is an example of a two parameter constructor that takes in an empty slot posn
   *  and creates a board of size 7 with an initial score of 36 and an empty slot at (4,4).
   *  There is a marble at every valid slot on the board. */
  @Test
  public void testTwoArgumentConstructor() {
    assertEquals(7, twoParamBoard.getBoardSize());
    assertEquals(36, twoParamBoard.getScore());
    assertEquals(SlotState.Empty, twoParamBoard.getSlotAt(4, 4));
    assertEquals(SlotState.Invalid, twoParamBoard.getSlotAt(0, 0));
    assertEquals(SlotState.Invalid, twoParamBoard.getSlotAt(6, 6));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(1, 1));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(5, 5));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(1, 5));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(5, 1));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(6, 2));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(0, 3));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(1, 4));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(2, 3));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(3, 5));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(4, 0));
  }

  @Test
  public void testTwoArgumentConstructorExceptions() {
    try {
      EuropeanSolitaireModel threeParamBoard = new EuropeanSolitaireModel(-2, 3);
      fail("No exception thrown for invalid sRow");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      EuropeanSolitaireModel threeParamBoard = new EuropeanSolitaireModel(2, -4);
      fail("No exception thrown for invalid sCol");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      EuropeanSolitaireModel threeParamBoard = new EuropeanSolitaireModel(8, 3);
      fail("No exception thrown for invalid sRow");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      EuropeanSolitaireModel threeParamBoard = new EuropeanSolitaireModel(2, 9);
      fail("No exception thrown for invalid sCol");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      EuropeanSolitaireModel threeParamBoard = new EuropeanSolitaireModel(5, 6);
      fail("No exception thrown for invalid sRow and sCol");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      EuropeanSolitaireModel threeParamBoard = new EuropeanSolitaireModel(0, 0);
      fail("No exception thrown for invalid sRow and sCol");
    } catch (Exception e) {
      // Passes, expected
    }
  }

  /** threeParamBoard is an example of a three parameter constructor that takes in an empty slot,
   *  and a side length creates a board of size 19 with an initial score of 276.
   *  There is an empty slot at (10,6) and a marble at every valid slot on the board. */
  @Test
  public void testThreeArgumentConstructor() {
    assertEquals(19, threeParamBoard.getBoardSize());
    assertEquals(276, threeParamBoard.getScore());

    assertEquals(SlotState.Empty, threeParamBoard.getSlotAt(10, 6));

    assertEquals(SlotState.Invalid, threeParamBoard.getSlotAt(0, 0));
    assertEquals(SlotState.Invalid, threeParamBoard.getSlotAt(18, 18));

    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(12, 8));
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(18, 11));
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(13, 5));
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(0, 6));
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(8, 18));
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(7, 3));
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(9, 0));
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(5, 5));
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(13, 13));
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(5, 13));
  }
  
  @Test
  public void testThreeArgumentConstructorExceptions() {
    try {
      EuropeanSolitaireModel threeParamBoard = new EuropeanSolitaireModel(8, -3, -3);
      fail("No exception thrown for invalid thickness, sRow, or sCol");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      EuropeanSolitaireModel threeParamBoard = new EuropeanSolitaireModel(1, -3, -3);
      fail("No exception thrown for invalid thickness, sRow, or sCol");
    } catch (Exception e) {
      // Passes, expected
    }

    try {
      EuropeanSolitaireModel threeParamBoard = new EuropeanSolitaireModel(7, 0, 0);
      fail("No exception thrown for invalid sRow and sCol");
    } catch (Exception e) {
      // Passes, expected
    }

    try {
      EuropeanSolitaireModel threeParamBoard = new EuropeanSolitaireModel(5, 4, 14);
      fail("No exception thrown for invalid sRow and sCol");
    } catch (Exception e) {
      // Passes, expected
    }

    try {
      EuropeanSolitaireModel threeParamBoard = new EuropeanSolitaireModel(-1, -3, -3);
      fail("No exception thrown for invalid thickness, sRow, or sCol");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      EuropeanSolitaireModel threeParamBoard = new EuropeanSolitaireModel(8, 10, 6);
      fail("No exception thrown for invalid thickness");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      EuropeanSolitaireModel threeParamBoard = new EuropeanSolitaireModel(7, -4, 2);
      fail("No exception thrown for invalid sRow");
    } catch (Exception e) {
      // Passes, expected
    }
  }

  @Test
  public void testIsGameOver() {
    assertEquals(false, noParamBoard.isGameOver());
    noParamBoard.move(3, 1, 3, 3);
    noParamBoard.move(1, 2, 3, 2);
    noParamBoard.move(3, 3, 3, 1);
    noParamBoard.move(1, 3, 3, 3);
    noParamBoard.move(3, 4, 3, 2);
    noParamBoard.move(3, 6, 3, 4);
    noParamBoard.move(2, 0, 2, 2);
    noParamBoard.move(3, 2, 1, 2);
    noParamBoard.move(0, 2, 2, 2);
    noParamBoard.move(0, 4, 0, 2);
    noParamBoard.move(2, 5, 2, 3);
    noParamBoard.move(2, 2, 2, 4);
    noParamBoard.move(2, 4, 0, 4);
    noParamBoard.move(3, 0, 3, 2);
    noParamBoard.move(4, 2, 2, 2);
    noParamBoard.move(4, 0, 4, 2);
    noParamBoard.move(4, 4, 2, 4);
    noParamBoard.move(4, 2, 4, 4);
    noParamBoard.move(4, 5, 4, 3);
    noParamBoard.move(5, 3, 3, 3);
    noParamBoard.move(6, 2, 4, 2);
    noParamBoard.move(6, 4, 6, 2);
    noParamBoard.move(5, 5, 5, 3);
    assertEquals(true, noParamBoard.isGameOver());

    assertEquals(false, twoParamBoard.isGameOver());
    twoParamBoard.move(2, 4, 4, 4);
    twoParamBoard.move(2, 2, 2, 4);
    twoParamBoard.move(0, 2, 2, 2);
    twoParamBoard.move(0, 4, 0, 2);
    twoParamBoard.move(2, 1, 2, 3);
    twoParamBoard.move(4, 1, 2, 1);
    twoParamBoard.move(4, 3, 4, 1);
    twoParamBoard.move(4, 5, 4, 3);
    twoParamBoard.move(4, 0, 4, 2);
    twoParamBoard.move(2, 0, 2, 2);
    twoParamBoard.move(2, 3, 2, 1);
    twoParamBoard.move(1, 4, 1, 2);
    twoParamBoard.move(0, 2, 2, 2);
    twoParamBoard.move(2, 2, 2, 0);
    twoParamBoard.move(2, 0, 4, 0);
    twoParamBoard.move(2, 5, 2, 3);
    twoParamBoard.move(3, 3, 1, 3);
    twoParamBoard.move(3, 6, 3, 4);
    twoParamBoard.move(4, 2, 2, 2);
    twoParamBoard.move(6, 2, 4, 2);
    twoParamBoard.move(6, 4, 4, 4);
    twoParamBoard.move(4, 3, 4, 1);
    twoParamBoard.move(4, 0, 4, 2);
    twoParamBoard.move(4, 4, 2, 4);
    twoParamBoard.move(6, 3, 4, 3);
    twoParamBoard.move(4, 2, 4, 4);
    assertEquals(true, twoParamBoard.isGameOver());

    assertEquals(false, oneParamBoard.isGameOver());
    assertEquals(false, threeParamBoard.isGameOver());
  }
  
  @Test
  public void testGetBoardSize() {
    assertEquals(7, noParamBoard.getBoardSize());
    assertEquals(7, oneParamBoard.getBoardSize());
    assertEquals(7, twoParamBoard.getBoardSize());
    assertEquals(276, threeParamBoard.getScore());
  }

  @Test
  public void testMove() {
    // Testing a move on an EuropeanSolitaireModel using the default (empty) constructor
    assertEquals(SlotState.Empty, noParamBoard.getSlotAt(3, 3));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(3, 2));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(3, 1));
    assertEquals(36, noParamBoard.getScore());
    noParamBoard.move(3, 1, 3, 3);
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(3, 3));
    assertEquals(SlotState.Empty, noParamBoard.getSlotAt(3, 2));
    assertEquals(SlotState.Empty, noParamBoard.getSlotAt(3, 1));
    assertEquals(35, noParamBoard.getScore());

    // Testing a move on an EuropeanSolitaireModel using the constructor taking in one parameter
    assertEquals(SlotState.Invalid, oneParamBoard.getSlotAt(6, 6));
    assertEquals(SlotState.Invalid, oneParamBoard.getSlotAt(6, 5));
    assertEquals(SlotState.Marble, oneParamBoard.getSlotAt(6, 4));
    assertEquals(36, oneParamBoard.getScore());
    oneParamBoard.move(3, 5, 3, 3);
    assertEquals(SlotState.Empty, oneParamBoard.getSlotAt(3, 5));
    assertEquals(SlotState.Empty, oneParamBoard.getSlotAt(3, 4));
    assertEquals(SlotState.Marble, oneParamBoard.getSlotAt(3, 3));
    assertEquals(35, oneParamBoard.getScore());

    // Testing a move on an EuropeanSolitaireModel using the constructor taking in two parameters
    assertEquals(SlotState.Empty, twoParamBoard.getSlotAt(4, 4));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(3, 4));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(2, 4));
    assertEquals(36, twoParamBoard.getScore());
    twoParamBoard.move(2, 4, 4, 4);
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(4, 4));
    assertEquals(SlotState.Empty, twoParamBoard.getSlotAt(3, 4));
    assertEquals(SlotState.Empty, twoParamBoard.getSlotAt(2, 4));
    assertEquals(35, twoParamBoard.getScore());

    // Testing a move on an EuropeanSolitaireModel using the constructor taking in three parameters
    assertEquals(SlotState.Empty, threeParamBoard.getSlotAt(10, 6));
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(9, 6));
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(8, 6));
    assertEquals(276, threeParamBoard.getScore());
    threeParamBoard.move(8, 6, 10, 6);
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(10, 6));
    assertEquals(SlotState.Empty, threeParamBoard.getSlotAt(9, 6));
    assertEquals(SlotState.Empty, threeParamBoard.getSlotAt(8, 6));
    assertEquals(275, threeParamBoard.getScore());
  }

  @Test
  public void testMoveExceptions() {
    try {
      noParamBoard.move(-2, 3, 4, 5);
      fail("Did not throw Illegal Argument Exception for out of bound indexes");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      noParamBoard.move(7, 3, 4, 5);
      fail("Did not throw Illegal Argument Exception for out of bound indexes");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      noParamBoard.move(2, -8, 4, 5);
      fail("Did not throw Illegal Argument Exception for out of bound indexes");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      noParamBoard.move(2, 8, 4, 5);
      fail("Did not throw Illegal Argument Exception for out of bound indexes");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      noParamBoard.move(2, 2, -4, 5);

      fail("Did not throw Illegal Argument Exception for out of bound indexes");
    } catch (Exception e) {
      // Passes, expected
    }
    try {

      noParamBoard.move(2, 2, 14, 5);

      fail("Did not throw Illegal Argument Exception for out of bound indexes");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      noParamBoard.move(2, 2, 4, -16);
      fail("Did not throw Illegal Argument Exception for out of bound indexes");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      noParamBoard.move(2, 2, 4, 8);
      fail("Did not throw Illegal Argument Exception for out of bound indexes");
    } catch (Exception e) {
      // Passes, expected
    }
  }

  @Test
  public void testGetSlotAt() {
    assertEquals(SlotState.Invalid, noParamBoard.getSlotAt(0, 0));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(1, 1));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(2, 2));
    assertEquals(SlotState.Empty, noParamBoard.getSlotAt(3, 3));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(6, 3));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(3, 4));
    assertEquals(SlotState.Invalid, noParamBoard.getSlotAt(6, 6));

    assertEquals(SlotState.Invalid, oneParamBoard.getSlotAt(0, 0));
    assertEquals(SlotState.Empty, oneParamBoard.getSlotAt(3, 3));
    assertEquals(SlotState.Invalid, oneParamBoard.getSlotAt(6, 6));
    assertEquals(SlotState.Marble, oneParamBoard.getSlotAt(3, 4));
    assertEquals(SlotState.Marble, oneParamBoard.getSlotAt(5, 1));
    assertEquals(SlotState.Marble, oneParamBoard.getSlotAt(6, 2));

    assertEquals(SlotState.Invalid, twoParamBoard.getSlotAt(0, 0));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(3, 3));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(4, 3));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(3, 6));
    assertEquals(SlotState.Invalid, twoParamBoard.getSlotAt(6, 6));
    assertEquals(SlotState.Empty, twoParamBoard.getSlotAt(4, 4));

    assertEquals(SlotState.Invalid, threeParamBoard.getSlotAt(0, 0));
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(3, 3));
    assertEquals(SlotState.Empty, threeParamBoard.getSlotAt(10, 6));
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(7, 6));
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(10, 10));
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(8, 9));
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(6, 2));
  }
  
  @Test
  public void testGetSlotAtExceptions() {
    try {
      noParamBoard.getSlotAt(-4, 2);
      fail("No exception thrown for invalid row");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      noParamBoard.getSlotAt(8, 2);
      fail("No exception thrown for invalid row");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      noParamBoard.getSlotAt(3, -800);
      fail("No exception thrown for invalid column");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      noParamBoard.getSlotAt(3, 17);
      fail("No exception thrown for invalid column");
    } catch (Exception e) {
      // Passes, expected
    }

  }

  @Test
  public void testGetScore() {
    assertEquals(36, noParamBoard.getScore());
    noParamBoard.move(3, 1, 3, 3);
    assertEquals(35, noParamBoard.getScore());
    noParamBoard.move(1, 2, 3, 2);
    assertEquals(34, noParamBoard.getScore());

    assertEquals(36, oneParamBoard.getScore());
    oneParamBoard.move(3, 1, 3, 3);
    assertEquals(35, oneParamBoard.getScore());
    oneParamBoard.move(5, 1, 3, 1);
    assertEquals(34, oneParamBoard.getScore());

    assertEquals(36, twoParamBoard.getScore());
    twoParamBoard.move(4, 2, 4, 4);
    assertEquals(35, twoParamBoard.getScore());
    twoParamBoard.move(2, 3, 4, 3);
    assertEquals(34, twoParamBoard.getScore());

    assertEquals(276, threeParamBoard.getScore());
    threeParamBoard.move(8, 6, 10, 6);
    assertEquals(275, threeParamBoard.getScore());
    threeParamBoard.move(6, 6, 8, 6);
    assertEquals(274, threeParamBoard.getScore());
  }
}