import org.junit.Before;
import org.junit.Test;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;

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
  public void testGameOver() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    // Set this board to have one move left before the player wins.
    model.setBoard(new ArrayList<ArrayList<String>>(Arrays.asList(
            new ArrayList<>(Arrays.asList(" ", " ", "_", "_", "_", " ", " ")),
            new ArrayList<String>(Arrays.asList(" ", " ", "_", "_", "_", " ", " ")),
            new ArrayList<>(Arrays.asList("_", "_", "O", "O", "_", "_", "_")),
            new ArrayList<String>(Arrays.asList("_", "_", "_", "_", "_", "_", "_")),
            new ArrayList<String>(Arrays.asList("_", "_", "_", "_", "_", "_", "_")),
            new ArrayList<String>(Arrays.asList(" ", " ", "_", "_", "_", " ", " ")),
            new ArrayList<>(Arrays.asList(" ", " ", "_", "_", "_", " ", " ")))));

    // Plays the wining move of the game
    Reader in = new StringReader("3 4\n3 2\n");
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, new StringBuilder());

    // A mock to represent the controller
    MarbleSolitaireControllerImpl mock = new MarbleSolitaireControllerImpl(model, view, in);
    mock.playGame();

    // Check the log of the above winning game
    assertEquals(mock.getLog(),
            "     _ _ _\n" +
                    "     _ _ _\n" +
                    " _ _ O O _ _ _\n" +
                    " _ _ _ _ _ _ _\n" +
                    " _ _ _ _ _ _ _\n" +
                    "     _ _ _\n" +
                    "     _ _ _\n" +
                    "Score: 2\n" +
                    "Enter move: \n" +
                    "Game over!\n" +
                    "     _ _ _\n" +
                    "     _ _ _\n" +
                    " _ O _ _ _ _ _\n" +
                    " _ _ _ _ _ _ _\n" +
                    " _ _ _ _ _ _ _\n" +
                    "     _ _ _\n" +
                    "     _ _ _\n" +
                    "Score: 1\n");
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
            "\n" +
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
              "Invalid move. Play again. Invalid move: Occupied 'to' slot\n" +
              "Game quit!\n" +
              "Game left at:\n" +
              "\n" +
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
              "Game quit!\n" +
              "Game left at:\n" +
              "\n" +
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
              "\n" +
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
}