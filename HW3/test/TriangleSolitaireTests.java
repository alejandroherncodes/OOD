import static org.junit.Assert.*;

import cs3500.marblesolitaire.model.hw03.TriangleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

import org.junit.Before;
import org.junit.Test;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;

/**
 * Tests if TriangleSolitaireModel's constructors and methods work using valid instances.
 */
public class TriangleSolitaireTests {

  MarbleSolitaireModel noParamBoard;
  MarbleSolitaireModel oneParamBoard;
  MarbleSolitaireModel twoParamBoard;
  MarbleSolitaireModel threeParamBoard;

  @Before
  public void setup() {
    noParamBoard = new TriangleSolitaireModel();
    oneParamBoard = new TriangleSolitaireModel(5);
    twoParamBoard = new TriangleSolitaireModel(4, 4);
    threeParamBoard = new TriangleSolitaireModel(7, 3, 2);
  }

  /** noParamBoard is an example of an empty constructor that it creates a board
   *  of size 5 with an initial score of 14 and an empty slot at (0,0). There is a marble
   *  in every valid slot on the board. */
  @Test
  public void testNoArgumentConstructor() {
    assertEquals(5, noParamBoard.getBoardSize());
    assertEquals(14, noParamBoard.getScore());
    assertEquals(SlotState.Empty, noParamBoard.getSlotAt(0, 0));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(1, 1));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(2, 1));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(2, 2));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(3, 1));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(3, 2));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(3, 3));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(4, 1));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(4, 2));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(4, 3));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(4, 4));
    assertEquals(SlotState.Invalid, noParamBoard.getSlotAt(2, 3));
    assertEquals(SlotState.Invalid, noParamBoard.getSlotAt(1, 2));
  }

  /** oneParamBoard is an example of an one parameter constructor that takes in a side length
   *  and creates a board of size 5 with an initial score of 14 and an empty slot at (0,0).
   *  There is a marble in every valid slot on the board. */
  @Test
  public void testOneArgumentConstructor() {
    assertEquals(5, oneParamBoard.getBoardSize());
    assertEquals(14, oneParamBoard.getScore());

    assertEquals(SlotState.Empty, oneParamBoard.getSlotAt(0, 0));

    assertEquals(SlotState.Marble, oneParamBoard.getSlotAt(2, 0));
    assertEquals(SlotState.Marble, oneParamBoard.getSlotAt(2, 1));
    assertEquals(SlotState.Marble, oneParamBoard.getSlotAt(2, 2));
    assertEquals(SlotState.Marble, oneParamBoard.getSlotAt(3, 0));
    assertEquals(SlotState.Marble, oneParamBoard.getSlotAt(3, 1));
    assertEquals(SlotState.Marble, oneParamBoard.getSlotAt(3, 2));
    assertEquals(SlotState.Marble, oneParamBoard.getSlotAt(3, 3));
    assertEquals(SlotState.Marble, oneParamBoard.getSlotAt(4, 0));
    assertEquals(SlotState.Marble, oneParamBoard.getSlotAt(4, 1));
    assertEquals(SlotState.Marble, oneParamBoard.getSlotAt(4, 2));
    assertEquals(SlotState.Marble, oneParamBoard.getSlotAt(4, 3));
    assertEquals(SlotState.Marble, oneParamBoard.getSlotAt(4, 4));

    assertEquals(SlotState.Invalid, oneParamBoard.getSlotAt(0, 1));
    assertEquals(SlotState.Invalid, oneParamBoard.getSlotAt(3, 4));
  }

  @Test
  public void testOneArgumentConstructorExceptions() {
    try {
      TriangleSolitaireModel threeParamBoard = new TriangleSolitaireModel(10);
      fail("No exception thrown for odd thickness");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      TriangleSolitaireModel threeParamBoard = new TriangleSolitaireModel(1);
      fail("No exception thrown for invalid thickness");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      TriangleSolitaireModel threeParamBoard = new TriangleSolitaireModel(-1);
      fail("No exception thrown for negative thickness");
    } catch (Exception e) {
      // Passes, expected
    }
  }

  /** twoParamBoard is an example of a two parameter constructor that takes in an empty slot posn
   *  and creates a board of size 5 with an initial score of 14 and an empty slot at (4,4).
   *  There is a marble in every valid slot on the board. */
  @Test
  public void testTwoArgumentConstructor() {
    assertEquals(5, twoParamBoard.getBoardSize());
    assertEquals(14, twoParamBoard.getScore());
    assertEquals(SlotState.Empty, twoParamBoard.getSlotAt(4, 4));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(0, 0));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(1, 1));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(2, 0));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(2, 1));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(3, 0));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(3, 1));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(4, 0));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(4, 1));
    assertEquals(SlotState.Invalid, twoParamBoard.getSlotAt(1, 4));
    assertEquals(SlotState.Invalid, twoParamBoard.getSlotAt(0, 3));
    assertEquals(SlotState.Invalid, twoParamBoard.getSlotAt(1, 4));
    assertEquals(SlotState.Invalid, twoParamBoard.getSlotAt(2, 3));
  }

  @Test
  public void testTwoArgumentConstructorExceptions() {
    try {
      TriangleSolitaireModel threeParamBoard = new TriangleSolitaireModel(-2, 3);
      fail("No exception thrown for invalid sRow");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      TriangleSolitaireModel threeParamBoard = new TriangleSolitaireModel(2, -4);
      fail("No exception thrown for invalid sCol");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      TriangleSolitaireModel threeParamBoard = new TriangleSolitaireModel(8, 3);
      fail("No exception thrown for invalid sRow");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      TriangleSolitaireModel threeParamBoard = new TriangleSolitaireModel(2, 9);
      fail("No exception thrown for invalid sCol");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      TriangleSolitaireModel threeParamBoard = new TriangleSolitaireModel(5, 6);
      fail("No exception thrown for invalid sRow and sCol");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      TriangleSolitaireModel threeParamBoard = new TriangleSolitaireModel(0, -1);
      fail("No exception thrown for invalid sRow and sCol");
    } catch (Exception e) {
      // Passes, expected
    }
  }

  /** threeParamBoard is an example of a three parameter constructor that takes in an empty slot,
   *  and a side length creates a board of size 7 with an initial score of 27.
   *  There is an empty slot at (3,2) and a marble in every valid slot on the board. */
  @Test
  public void testThreeArgumentConstructor() {
    assertEquals(7, threeParamBoard.getBoardSize());
    assertEquals(27, threeParamBoard.getScore());

    assertEquals(SlotState.Empty, threeParamBoard.getSlotAt(3, 2));

    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(0, 0));
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(1, 0));
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(6, 6));
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(0, 0));
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(3, 3));
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(5, 5));

    assertEquals(SlotState.Invalid, threeParamBoard.getSlotAt(0, 4));
    assertEquals(SlotState.Invalid, threeParamBoard.getSlotAt(0, 5));
    assertEquals(SlotState.Invalid, threeParamBoard.getSlotAt(0, 6));
    assertEquals(SlotState.Invalid, threeParamBoard.getSlotAt(3, 6));
    assertEquals(SlotState.Invalid, threeParamBoard.getSlotAt(5, 6));
  }

  @Test
  public void testThreeArgumentConstructorExceptions() {
    try {
      TriangleSolitaireModel threeParamBoard = new TriangleSolitaireModel(8, -3, -3);
      fail("No exception thrown for invalid thickness, sRow, or sCol");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      TriangleSolitaireModel threeParamBoard = new TriangleSolitaireModel(1, -3, -3);
      fail("No exception thrown for invalid thickness, sRow, or sCol");
    } catch (Exception e) {
      // Passes, expected
    }

    try {
      TriangleSolitaireModel threeParamBoard = new TriangleSolitaireModel(7, 0, -1);
      fail("No exception thrown for invalid sRow and sCol");
    } catch (Exception e) {
      // Passes, expected
    }

    try {
      TriangleSolitaireModel threeParamBoard = new TriangleSolitaireModel(5, 4, 14);
      fail("No exception thrown for invalid sRow and sCol");
    } catch (Exception e) {
      // Passes, expected
    }

    try {
      TriangleSolitaireModel threeParamBoard = new TriangleSolitaireModel(-1, -3, -3);
      fail("No exception thrown for invalid thickness, sRow, or sCol");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      TriangleSolitaireModel threeParamBoard = new TriangleSolitaireModel(8, 10, 6);
      fail("No exception thrown for invalid thickness");
    } catch (Exception e) {
      // Passes, expected
    }
    try {
      TriangleSolitaireModel threeParamBoard = new TriangleSolitaireModel(7, -4, 2);
      fail("No exception thrown for invalid sRow");
    } catch (Exception e) {
      // Passes, expected
    }
  }
  @Test
  public void testIsGameOver() {
    assertFalse(noParamBoard.isGameOver());
    assertFalse(twoParamBoard.isGameOver());
    assertFalse(threeParamBoard.isGameOver());

    noParamBoard.move(2, 0, 0, 0);
    noParamBoard.move(2, 2, 2, 0);
    noParamBoard.move(3, 0, 1, 0);
    noParamBoard.move(3, 2, 3, 0);
    noParamBoard.move(4, 0, 2, 0);
    noParamBoard.move(1, 0, 3, 0);
    noParamBoard.move(4, 2, 4, 0);
    noParamBoard.move(4, 0, 2, 0);
    noParamBoard.move(4, 4, 4, 2);
    noParamBoard.move(0, 0, 2, 2);
    noParamBoard.move(2, 2, 4, 4);
    assertTrue(noParamBoard.isGameOver());
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(5, noParamBoard.getBoardSize());
    assertEquals(5, oneParamBoard.getBoardSize());
    assertEquals(5, twoParamBoard.getBoardSize());
    assertEquals(27, threeParamBoard.getScore());
  }

  @Test
  public void testMove() {
    // Testing a move on an TriangleSolitaireModel using the default (empty) constructor
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(2, 0));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(1, 0));
    assertEquals(SlotState.Empty, noParamBoard.getSlotAt(0, 0));
    assertEquals(14, noParamBoard.getScore());
    noParamBoard.move(2, 0, 0, 0);
    assertEquals(SlotState.Empty, noParamBoard.getSlotAt(2, 0));
    assertEquals(SlotState.Empty, noParamBoard.getSlotAt(1, 0));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(0, 0));
    assertEquals(13, noParamBoard.getScore());

    // Testing a move on an TriangleSolitaireModel using the constructor taking in one parameter
    assertEquals(SlotState.Marble, oneParamBoard.getSlotAt(2, 0));
    assertEquals(SlotState.Marble, oneParamBoard.getSlotAt(1, 0));
    assertEquals(SlotState.Empty, oneParamBoard.getSlotAt(0, 0));
    assertEquals(14, oneParamBoard.getScore());
    oneParamBoard.move(2, 0, 0, 0);
    assertEquals(SlotState.Empty, oneParamBoard.getSlotAt(2, 0));
    assertEquals(SlotState.Empty, oneParamBoard.getSlotAt(1, 0));
    assertEquals(SlotState.Marble, oneParamBoard.getSlotAt(0, 0));
    assertEquals(13, oneParamBoard.getScore());

    // Testing a move on an TriangleSolitaireModel using the constructor taking in two parameters
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(4, 2));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(4, 3));
    assertEquals(SlotState.Empty, twoParamBoard.getSlotAt(4, 4));
    assertEquals(14, twoParamBoard.getScore());
    twoParamBoard.move(4, 2, 4, 4);
    assertEquals(SlotState.Empty, twoParamBoard.getSlotAt(4, 2));
    assertEquals(SlotState.Empty, twoParamBoard.getSlotAt(4, 3));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(4, 4));
    assertEquals(13, twoParamBoard.getScore());

    // Testing a move on an TriangleSolitaireModel using the constructor taking in three parameters
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(1, 0));
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(2, 1));
    assertEquals(SlotState.Empty, threeParamBoard.getSlotAt(3, 2));
    assertEquals(27, threeParamBoard.getScore());
    threeParamBoard.move(1, 0, 3, 2);
    assertEquals(SlotState.Empty, threeParamBoard.getSlotAt(1, 0));
    assertEquals(SlotState.Empty, threeParamBoard.getSlotAt(2, 1));
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(3, 2));
    assertEquals(26, threeParamBoard.getScore());
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
    // Test with a no parameter constructor
    assertEquals(SlotState.Empty, noParamBoard.getSlotAt(0, 0));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(4, 3));
    assertEquals(SlotState.Invalid, noParamBoard.getSlotAt(3, 4));

    // Test with a one parameter constructor
    assertEquals(SlotState.Empty, oneParamBoard.getSlotAt(0, 0));
    assertEquals(SlotState.Marble, oneParamBoard.getSlotAt(3, 3));
    assertEquals(SlotState.Invalid, oneParamBoard.getSlotAt(3, 4));

    // Test with a two parameter constructor
    assertEquals(SlotState.Empty, twoParamBoard.getSlotAt(4, 4));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(0, 0));
    assertEquals(SlotState.Invalid, twoParamBoard.getSlotAt(3, 4));

    // Test with a three parameter constructor
    assertEquals(SlotState.Empty, threeParamBoard.getSlotAt(3, 2));
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(0, 0));
    assertEquals(SlotState.Invalid, threeParamBoard.getSlotAt(3, 4));

    // Test more marbles on the board's valid slots
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(3, 3));
    assertEquals(SlotState.Marble, threeParamBoard.getSlotAt(6, 2));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(3, 3));
    assertEquals(SlotState.Marble, twoParamBoard.getSlotAt(4, 3));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(1, 1));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(2, 2));
    assertEquals(SlotState.Marble, noParamBoard.getSlotAt(3, 3));
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

  // Reusing the game scenarios from above, this tests just the get score method.
  @Test
  public void testGetScore() {
    assertEquals(14, oneParamBoard.getScore());
    oneParamBoard.move(2, 0, 0, 0);
    assertEquals(13, oneParamBoard.getScore());
    oneParamBoard.move(3, 2, 1, 0);
    assertEquals(12, oneParamBoard.getScore());

    assertEquals(14, twoParamBoard.getScore());
    twoParamBoard.move(4, 2, 4, 4);
    assertEquals(13, twoParamBoard.getScore());
    twoParamBoard.move(4, 0, 4, 2);
    assertEquals(12, twoParamBoard.getScore());

    assertEquals(27, threeParamBoard.getScore());
    threeParamBoard.move(1, 0, 3, 2);
    assertEquals(26, threeParamBoard.getScore());
    threeParamBoard.move(3, 0, 1, 0);
    assertEquals(25, threeParamBoard.getScore());
  }
}