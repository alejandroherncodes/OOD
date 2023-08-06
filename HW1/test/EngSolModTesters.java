import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class EngSolModTesters {
  private EnglishSolitaireModel emptyModel;
  private EnglishSolitaireModel armModel;
  private EnglishSolitaireModel rowColModel;
  private EnglishSolitaireModel armRowColModel;
  private EnglishSolitaireModel bSizeTest;

  @Before
  public void initConditions() {
    emptyModel = new EnglishSolitaireModel();
    armModel = new EnglishSolitaireModel(3);
    rowColModel = new EnglishSolitaireModel(2, 2);
    armRowColModel = new EnglishSolitaireModel(3,4, 4);
    bSizeTest = new EnglishSolitaireModel(7);
  }

  // To test exceptions in the 2 parameter constructor
  @Test
  public void testInvalidSlotConstruct() {

    // Initializes the board with an invalid empty slot given an invalid position.
    try {
      MarbleSolitaireModel rowColModel = new EnglishSolitaireModel(0, 0);

      fail("Expected Exception: " +
              "Invalid empty cell position");
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }

    // Initializes the board with an inv. empty slot given an invalid position
    try {
      MarbleSolitaireModel rowColModel = new EnglishSolitaireModel(-1, 20);

      fail("Given an invalid empty slot. Expected Exception:");
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }
  }

  // To test exceptions in the 1 parameter constructor
  @Test
  public void testInvalidArmTConstruct() {

    // Initializes the game with an invalid (even, negative) arm thickness.
    try {
      armModel = new EnglishSolitaireModel(-4);

      fail("Given a negative and even. Expected Exception");
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }

    // Initializes the game with an invalid (even, negative) arm thickness.
    try {
      armModel = new EnglishSolitaireModel(-5);

      fail("Given a negative and odd. Expected Exception: " +
              "Arm thickness must be a positive, odd number.");
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }


    // Initializes the game with an invalid (even) arm thickness.
    try {
      armModel = new EnglishSolitaireModel(4);

      fail("Given a positive and even. Expected Exception: " +
              "Arm thickness must be a positive, odd number.");
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }
  }

  // To test exceptions in the 3 parameter constructor
  @Test
  public void testInvalidFullConstruct() {
    // Initializes the game with an invalid arm thickness and an inv. empty slot
    try {
      armModel = new EnglishSolitaireModel(4, 0, 0);

      fail("Invalid all parameters. Expected Exception: " +
              "Arm thickness must be a positive, odd number.");
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }

    // Initializes the game with an invalid arm thickness
    try {
      armModel = new EnglishSolitaireModel(4, 4, 4);

    } catch (IllegalArgumentException e) {
      System.out.println("Given a positive and even arm thickness. Expected Exception:" + e);
    }

    // Initializes the game with an valid arm thickness and an invalid slot
    try {
      armModel = new EnglishSolitaireModel(5, 0, 0);

    } catch (IllegalArgumentException e) {
      System.out.println("Given a positive and even arm thickness. Expected Exception:" + e);
    }

    // Initializes the game with an inv. empty slot
    try {
      armModel = new EnglishSolitaireModel(3, 0, 0);

    } catch (IllegalArgumentException e) {
      System.out.println("Given an invalid empty slot. Expected Exception:" + e);
    }
  }

  // Tests the false case in isGameOver
  @Test
  public void testIsGameOverFalse() {
    assertFalse(emptyModel.isGameOver());
  }

  // Tests valid moves, and an includes exception test at the end
  @Test
  public void testMoveValid() {
    // Empty constructor
    emptyModel = new EnglishSolitaireModel();
    emptyModel.move(3, 1, 3, 3);

    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            emptyModel.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            emptyModel.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            emptyModel.getSlotAt(3, 3));

    // Empty constructor
    emptyModel = new EnglishSolitaireModel();
    emptyModel.move(5, 3, 3, 3);

    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            emptyModel.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            emptyModel.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            emptyModel.getSlotAt(3, 3));

    // One constructor
    armModel = new EnglishSolitaireModel(5);
    armModel.move(6, 4, 6, 6);

    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            armModel.getSlotAt(6, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            armModel.getSlotAt(6, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armModel.getSlotAt(6, 6));

    // Two parameter constructor
    rowColModel = new EnglishSolitaireModel(3, 2);
    rowColModel.move(1, 2, 3, 2);

    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            rowColModel.getSlotAt(1, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            rowColModel.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            rowColModel.getSlotAt(3, 2));

    // Three parameter model
    armRowColModel = new EnglishSolitaireModel(5, 4, 2);
    armRowColModel.move(4, 4, 4, 2);

    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            armRowColModel.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            armRowColModel.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armRowColModel.getSlotAt(4, 2));

    // Exception
    try {
      emptyModel.move(3, 2, 3, 1);
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }

    // Exception
    try {
      emptyModel.move(3, 2, 3, 3);
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }
  }

  // Tests a move exception
  @Test
  public void testMoveInvalidMoveOutOfBounds() {
    try {
      emptyModel.move(0, 0, -1, 0);
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }
  }

  // Tests a move exception
  @Test
  public void testMoveNoSlotBetween() {
    try {
      emptyModel.move(1, 0, 1, 0);
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }
  }

  // Tests a move exception
  @Test
  public void testIsGameOverAndMove() {
    // Set the board to a specific state where the game is not over
    emptyModel.move(3, 1, 3, 3);

    // The game should not be over
    assertFalse(emptyModel.isGameOver());
  }

  // Tests a move exception
  @Test
  public void testValidMove() {
    emptyModel.move(3, 1, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, emptyModel.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, emptyModel.getSlotAt(3, 3));
  }

  @Test
  public void testInvalidMoveOutOfBounds() {
    try {
      emptyModel.move(0, 0, 0, -1);
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }
  }

  @Test
  public void testInvalidMoveFromPositionMT() {
    try {
      emptyModel.move(3, 3, 3, 1);
      emptyModel.move(3, 3, 3, 1);
      emptyModel.move(3, 3, 3, 1);

    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }
  }

  // Tests a move exception
  @Test
  public void testInvalidMoveToPositionNotMT() {
    try {
      emptyModel.move(3, 1, 4, 4);
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }
  }

  // Tests a move exception
  @Test
  public void testInvalidMovePositionsNot2SpacesApart() {
    try {
      emptyModel.move(2, 2, 3, 3);
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }
  }

  // Tests a move exception
  @Test
  public void testInvalidMoveNoSlotBWPositions() {
    try {
      emptyModel.move(5, 5, 4, 4);
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }
  }

  // Test more exceptions for the Move method
  @Test
  public void testIllegalMove() {
    try {
      emptyModel = new EnglishSolitaireModel();

      emptyModel.move(3, 3, 3, 1);
      emptyModel.move(3, 1, 3, 2);
      emptyModel.move(1, 3, 3, 3);
      emptyModel.move(3, 3, 3, 2);
      emptyModel.move(3, 3, 3, 2);

      // IllegalArgumentException for Move
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }
  }

  // To test isGameOver when there are no more valid moves
  @Test
  public void testIsGameOverNoValidMoves() {

    // Set up a board with no valid moves
    emptyModel = new EnglishSolitaireModel(1, 0, 0);
    assertTrue(emptyModel.isGameOver());
  }

  // To test isGameOver when the score is == 0
  @Test
  public void testIsGameOverScoreTrue() {
    MarbleSolitaireModel game = new EnglishSolitaireModel();

    // Play the game until it is over
    while (!game.isGameOver()) {
      // Replace these lines with your game-playing logic
      // For example, you can randomly select moves or use a predefined sequence of moves
      int fromRow = 3;
      int fromCol = 3;
      int toRow = 3;
      int toCol = 4;

      try {
        game.move(fromRow, fromCol, toRow, toCol);
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid move: " + e.getMessage());
        // Handle the invalid move here
        break;
      }
    }

    // Check if the game is over
    if (game.getScore() == 0) {
      assertEquals(true, game.isGameOver());
      System.out.println("Game Over. Marbles remaining: " + game.getScore());
    } else {
      System.out.println("Game is still in session.");
    }
  }

  // To test the exceptions for the isGameOver method
  @Test
  public void testIllegalPosnMove() {
    try {
      emptyModel = new EnglishSolitaireModel();

      emptyModel.move(3, 3, 3, 1);
      emptyModel.move(3, 1, 3, 2);
      emptyModel.move(1, 3, 3, 3);
      emptyModel.move(3, 3, 3, 2); // No more valid moves

      assertFalse(emptyModel.isGameOver());

      // To position is occupied
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }
  }

  // The score is equal to the number of marbles on the board.
  @Test
  public void testGetScore() {
    assertEquals(32, new EnglishSolitaireModel().getScore());
    assertEquals( 32, emptyModel.getScore());
    assertEquals( 32, armModel.getScore());
    assertEquals( 32, rowColModel.getScore());
    assertEquals( 32, armRowColModel.getScore());
    assertEquals(216, bSizeTest.getScore());
  }

  // To test getBoardSize
  @Test
  public void testGetBoardSize() {
    assertEquals(19, bSizeTest.getBoardSize());
    assertEquals( 7, emptyModel.getBoardSize());
    assertEquals( 7, armModel.getBoardSize());
    assertEquals( 7, rowColModel.getBoardSize());
    assertEquals(7, armRowColModel.getBoardSize());
  }

  // To test getSlotAt
  @Test
  public void testGetSlotAt() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, emptyModel.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, armModel.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, rowColModel.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, armRowColModel.getSlotAt(4, 4));

    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, emptyModel.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, armModel.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, rowColModel.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, armRowColModel.getSlotAt(1, 5));

    assertEquals(MarbleSolitaireModelState.SlotState.Marble, emptyModel.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, armModel.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, rowColModel.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, armRowColModel.getSlotAt(2, 2));
  }

  // To test exceptions for getSlotAt
  @Test
  public void testBoundsGetSlotAt() {
    try {
      emptyModel.getSlotAt(-1, 3);
      fail("Can't getSlotAt. Expected exception: ");

      // Out of bounds exception
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }

    try {
      emptyModel.getSlotAt(1, -3);
      fail("Can't getSlotAt. Expected exception: ");

      // Out of bounds exception
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }

    try {
      emptyModel.getSlotAt(-1, -1);
      fail("Can't getSlotAt. Expected exception: ");

      // Out of bounds exception
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }
  }
}