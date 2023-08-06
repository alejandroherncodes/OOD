package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

public class MarbleSolitaireTextView implements MarbleSolitaireView {
  private final MarbleSolitaireModelState gameBoard;

  public MarbleSolitaireTextView(MarbleSolitaireModelState gameBoard) {
    if (gameBoard == null) {
      throw new IllegalArgumentException("Model State cannot be null.");
    } else {
      this.gameBoard = gameBoard;
    }
  }

  @Override
  public String toString() {
    String drawBoard = "";

    for (int r = 0; r < this.gameBoard.getBoardSize(); r += 1) {
      for (int c = 0; c < this.gameBoard.getBoardSize(); c += 1) {
        if (this.gameBoard.getSlotAt(r, c) == MarbleSolitaireModelState.SlotState.Invalid) {
          drawBoard = drawBoard + " ";
        } else if (this.gameBoard.getSlotAt(r, c) == MarbleSolitaireModelState.SlotState.Marble) {
          drawBoard = drawBoard + "0";
        } else if (this.gameBoard.getSlotAt(r, c) == MarbleSolitaireModelState.SlotState.Empty) {
          drawBoard = drawBoard + "-";
        }

        if (c > ((this.gameBoard.getBoardSize() / 2) +
                (this.gameBoard.getBoardSize() / 6)) - 2) {
          drawBoard = drawBoard + " ";
          if ((this.gameBoard.getSlotAt(r, c) == MarbleSolitaireModelState.SlotState.Invalid) ||
                  (c == (this.gameBoard.getBoardSize() - 1))) {
            drawBoard = drawBoard + "\n";
            break;
          }
        } else {
          drawBoard = drawBoard + " ";
        }
      }
    }
    return drawBoard;
  }
}
