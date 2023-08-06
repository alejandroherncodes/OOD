import org.junit.Before;
import org.junit.Test;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.hw02.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.hw02.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MarbleSolitaireControllerImplTest {
  MarbleSolitaireModel gameModel;
  MarbleSolitaireView testView;
  MarbleSolitaireControllerImpl testController;
  Readable readable;
  Appendable output;

  @Before
  public void initData() {
    this.gameModel = new EnglishSolitaireModel();
    this.readable = new InputStreamReader(System.in);
    this.output = new StringBuilder();
    this.testView = new MarbleSolitaireTextView(gameModel, output);
    this.testController = new MarbleSolitaireControllerImpl(this.gameModel,
            this.testView, this.readable);;
  }

  @Test
  public void testQuitGame() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, new StringBuilder());
    Reader reader = new StringReader("q");

    MarbleSolitaireControllerImpl mockController
            = new MarbleSolitaireControllerImpl(model, view, reader);

    mockController.playGame();

    assertEquals(mockController.getLog(), "     O O O\n" +
            "     O O O\n" +
            " O O O O O O O\n" +
            " O O O _ O O O\n" +
            " O O O O O O O\n" +
            "     O O O\n" +
            "     O O O\n" +
            "Score: 32\n" +
            "Enter move: \n" +
            "Game quit!\n" +
            "Game left at:\n" +
            "     O O O\n" +
            "     O O O\n" +
            " O O O O O O O\n" +
            " O O O _ O O O\n" +
            " O O O O O O O\n" +
            "     O O O\n" +
            "     O O O\n" +
            "Score: 32\n");
  }

  @Test
  public void testInvalidMove() {
    // Inputs that are on the board, but aren't valid moves
    int[][] inputs = new int[][] {
            {1, 2, 3, 6},
            {3, 4, 3, 3},
            {9, 4, 5, 2}};

    // Test that inputs are rejected
    for (int[] i : inputs) {
      EnglishSolitaireModel model = new EnglishSolitaireModel();
      MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, new StringBuilder());
      Reader reader = new StringReader("6 3\n5 1\nq");

      MarbleSolitaireControllerImpl mockController
              = new MarbleSolitaireControllerImpl(model, view, reader);

      mockController.playGame();

      assertEquals(mockController.getLog(), "     O O O\n" +
              "     O O O\n" +
              " O O O O O O O\n" +
              " O O O _ O O O\n" +
              " O O O O O O O\n" +
              "     O O O\n" +
              "     O O O\n" +
              "Score: 32\n" +
              "Enter move: \n" +
              "Invalid move. Play again. Invalid move from (5,2) to (4,0)\n" +
              "     O O O\n" +
              "     O O O\n" +
              " O O O O O O O\n" +
              " O O O _ O O O\n" +
              " O O O O O O O\n" +
              "     O O O\n" +
              "     O O O\n" +
              "Score: 32\n" +
              "Enter move: \n" +
              "Game quit!\n" +
              "Game left at:\n" +
              "     O O O\n" +
              "     O O O\n" +
              " O O O O O O O\n" +
              " O O O _ O O O\n" +
              " O O O O O O O\n" +
              "     O O O\n" +
              "     O O O\n" +
              "Score: 32\n");
    }
  }

  @Test
  public void testInvalidTo() {
    // Inputs that are on the board, but aren't valid moves
    String[] inputs = new String[]{"a", "B", "c", "B",
            "A", "K", "N", "C", "E"};

    // Test that inputs are rejected
    for (String s : inputs) {
      EnglishSolitaireModel model = new EnglishSolitaireModel();
      MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, new StringBuilder());
      Reader reader = new StringReader("1 4\n" + s + "1\n11\nq");

      MarbleSolitaireControllerImpl mockController
              = new MarbleSolitaireControllerImpl(model, view, reader);

      mockController.playGame();

      assertEquals(mockController.getLog(), "     O O O\n" +
              "     O O O\n" +
              " O O O O O O O\n" +
              " O O O _ O O O\n" +
              " O O O O O O O\n" +
              "     O O O\n" +
              "     O O O\n" +
              "Score: 32\n" +
              "Enter move: \n" +
              "Invalid input. Try different 'to' values:\n" +
              "Invalid move. Play again. Cannot use invalid slot index: (0, 10)\n" +
              "     O O O\n" +
              "     O O O\n" +
              " O O O O O O O\n" +
              " O O O _ O O O\n" +
              " O O O O O O O\n" +
              "     O O O\n" +
              "     O O O\n" +
              "Score: 32\n" +
              "Enter move: \n" +
              "Game quit!\n" +
              "Game left at:\n" +
              "     O O O\n" +
              "     O O O\n" +
              " O O O O O O O\n" +
              " O O O _ O O O\n" +
              " O O O O O O O\n" +
              "     O O O\n" +
              "     O O O\n" +
              "Score: 32\n");
    }
  }

  @Test
  public void testInvalidFrom() {
    // Inputs that are on the board, but aren't valid moves
    String[] inputs = new String[]{"a", "B", "c", "B",
            "A", "K", "N", "C", "E"};

    // Test that inputs are rejected
    for (String s : inputs) {
      EnglishSolitaireModel model = new EnglishSolitaireModel();
      MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, new StringBuilder());
      Reader reader = new StringReader("1" + s + "\n1\n11\nq");

      MarbleSolitaireControllerImpl mockController
              = new MarbleSolitaireControllerImpl(model, view, reader);

      mockController.playGame();

      assertEquals(mockController.getLog(), "     O O O\n" +
              "     O O O\n" +
              " O O O O O O O\n" +
              " O O O _ O O O\n" +
              " O O O O O O O\n" +
              "     O O O\n" +
              "     O O O\n" +
              "Score: 32\n" +
              "Enter move: \n" +
              "Invalid input. Try different 'from' values:\n" +
              "Game quit!\n" +
              "Game left at:\n" +
              "     O O O\n" +
              "     O O O\n" +
              " O O O O O O O\n" +
              " O O O _ O O O\n" +
              " O O O O O O O\n" +
              "     O O O\n" +
              "     O O O\n" +
              "Score: 32\n");
    }
  }

  @Test
  public void testValidConstructors() {
    try {
      MarbleSolitaireController controller =
              new MarbleSolitaireControllerImpl(gameModel,
                      testView, new InputStreamReader(System.in));
    } catch (Exception e) {
      fail("Should not have failed");
    }
  }

  @Test
  public void testInvalidConstructors2() {
    try {
      MarbleSolitaireController controller1 =
              new MarbleSolitaireControllerImpl(null,
                      testView, new InputStreamReader(System.in));
      fail("Should have failed since invalid MarbleSolitaireModel");
    } catch (Exception e) {
      // works
    }

    try {
      MarbleSolitaireController controller1 =
              new MarbleSolitaireControllerImpl(gameModel,
                      null, new InputStreamReader(System.in));
      fail("Should have failed since invalid MarbleSolitaireView");
    } catch (Exception e) {
      // works
    }

    try {
      MarbleSolitaireController controller1 =
              new MarbleSolitaireControllerImpl(gameModel,
                      testView, null);
      fail("Should have failed since invalid Readable");
    } catch (Exception e) {
      // works
    }
  }
}