package cs3500.marblesolitaire.view.hw03;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import cs3500.marblesolitaire.view.hw02.MarbleSolitaireTextView;


/**
 * Represents a text view creator for Triangle Solitaire
 */
public class TriangleSolitaireTextView extends MarbleSolitaireTextView {

  /**
   * Constructs a text view of a Triangle Marble Solitaire game
   *
   * @param gameBoard  the game model of the game to draw must be a valid MarbleSolitaireModelState
   * @param output       the appendable object to link the visualizations to update
   * @throws IllegalArgumentException    if given a null parameters: board or appendable
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState gameBoard, Appendable output) {
    super(gameBoard, output);
  }

  /**
   * Constructs a text view of a Triangle Marble Solitaire game
   *
   * @param gameBoard  the game model of the game to be drawn must be a valid MarbleSolitaireModelState
   * @throws IllegalArgumentException    if given a null parameters: board or appendable
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState gameBoard)
          throws IllegalArgumentException {
    this(gameBoard, System.out);
  }

  /**
   * Return a string that represents the current state of the board. The
   * string should have one line per row of the game board. Each slot on the
   * game board is a single character (O, _ or space for a marble, empty and
   * invalid position respectively). Slots in a row should be separated by a
   * space. Each row has no space before the first slot and after the last slot.
   *
   * @return the game state as a string
   */
  @Override
  public String toString() {
    StringBuilder boardText = new StringBuilder();

    String emptySpace = " ";
    for (int i = 0; i < gameBoard.getBoardSize(); i++) {
      boardText.append(emptySpace.repeat((gameBoard.getBoardSize() - (i + 1))));
      for (int j = 0; j < i + 1; j++) {
        if (gameBoard.getSlotAt(i, j) == SlotState.Marble) {
          boardText.append("0 ");
        } else if (gameBoard.getSlotAt(i, j) == SlotState.Empty) {
          boardText.append("_ ");
        }

      }
      boardText = new StringBuilder(boardText.toString().substring(0, boardText.length() - 1));
      boardText.append("\n");
    }
    boardText = new StringBuilder(boardText.toString().substring(0, boardText.length() - 1));
    return boardText.toString();

  }
}