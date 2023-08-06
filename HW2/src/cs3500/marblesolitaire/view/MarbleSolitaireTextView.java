package cs3500.marblesolitaire.view;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import java.io.IOException;

/**
 * This class represents operations that support a text view
 * for the Marble solitaire game.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  MarbleSolitaireModelState gameBoard;
  Appendable output;

  public MarbleSolitaireTextView(MarbleSolitaireModelState gameBoard) {
    if (gameBoard == null) {
      throw new IllegalArgumentException("Model State cannot be null.");
    } else {
      this.gameBoard = gameBoard;
      this.output = System.out;
    }
  }

  public MarbleSolitaireTextView(MarbleSolitaireModelState gameBoard, Appendable output) {
    if (gameBoard == null) {
      throw new IllegalArgumentException("Model State cannot be null.");
    } if (output == null) {
      throw new IllegalArgumentException("Output state cannot be null.");
    }
    this.gameBoard = gameBoard;
    this.output = output;
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
    String boardView = "";

    for (int r = 0; r < gameBoard.getBoardSize(); r++) {
      String thisRow = "";

      for (int c = 0; c < gameBoard.getBoardSize(); c++) {
        String thisSlot = "";
        if (gameBoard.getSlotAt(r, c) == MarbleSolitaireModelState.SlotState.Empty) {
          thisSlot = " _";
        } else if (gameBoard.getSlotAt(r, c) == MarbleSolitaireModelState.SlotState.Marble) {
          thisSlot = " O";
        } else {
          thisSlot = "  ";
        }
        thisRow = thisRow + thisSlot;
      }
      boardView = boardView + thisRow.stripTrailing() + "\n";
    }

    return boardView.stripTrailing();
  }

  /**
   * Deliver a particular message to the designated data destination.
   *
   * @param message               a given message to be transmitted
   * @throws IOException      if delivery to the provided data destination fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    output.append(message).append("\n");
  }

  /**
   * Display the board onto the given data destination. The board should be presented exactly as
   * it appears in the format generated by the toString function mentioned earlier.
   *
   * @throws IOException      if delivery to the provided data destination fails
   */
  @Override
  public void renderBoard() throws IOException {
    output.append(this.toString()).append("\n");
  }

  /**
   * Gets the game's output and returns it as a string
   *
   * @return String       the game's messages to the user
   */
  @Override
  public String getLog() {
    return this.output.toString();
  }
}
