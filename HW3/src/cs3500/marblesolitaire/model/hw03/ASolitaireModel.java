package cs3500.marblesolitaire.model.hw03;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Represents an abstracted template for marble solitaire model types
 * Lifting solitaire models allows EnglishSolitaireModel to be a
 * runnable non-abstract class.
 */
public abstract class ASolitaireModel implements MarbleSolitaireModel {

  protected final int sRow;
  protected final int sCol;
  protected int boardSize;
  protected int score;
  protected final int sideLength;
  protected SlotState[][] board;

  /**
   * Constructs a marble solitaire model object
   *
   * @param sideLength thickness of one side of the board. must be a positive, odd number
   * @param sRow                         the row index for the starting empty slot
   * @param sCol                         the column index for the starting empty slot
   * @throws IllegalArgumentException    if given odd or negative side length, or invalid empty
   *                                     slot position.
   */
  public ASolitaireModel(int sideLength, int sRow, int sCol) {

    // Validate side length and set the value if valid
    if (sideLength < 2 || sideLength % 2 == 0) {
      throw new IllegalArgumentException("Invalid thickness, must be a positive and odd number.");
    }
    this.sideLength = sideLength;
    this.boardSize = sideLength * 3 - 2;
    this.board = new SlotState[boardSize][boardSize];

    if (this instanceof TriangleSolitaireModel) {
      this.boardSize = sideLength;
    }

    // Validate the empty slot location and set the value if valid
    if (!this.validIndex(sRow, sCol)) {
      throw new
              IllegalArgumentException("Invalid empty cell position (" + sRow + ", " + sCol + ")");
    }

    this.sRow = sRow;
    this.sCol = sCol;

    // Generate the board
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        if (this.validIndex(i, j)) {
          board[i][j] = SlotState.Marble;
        } else {
          board[i][j] = SlotState.Invalid;
        }
      }
    }
    board[sRow][sCol] = SlotState.Empty;

  }

  /**
   * A method used to validate, and then make moves in the game
   * A move valid move is a...
   *   Move a single marble from a given position to another given position.
   *   A move is valid only if the from and to positions are valid.
   *   The move fits specific constraints (i.e: cardinal direction, and move distance)
   * Now returns one exception with both to and from posns in the case a move be invalid
   * in order to prevent bugs from having many if-exception statements
   *
   * @param fromRow            row position to be moved to
   * @param fromCol            col position to be moved to
   * @param toRow              row position to be moved to
   * @param toCol              col position to be moved to
   * @throws IllegalArgumentException if a move is invalid
   */
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (validMove(fromRow, fromCol, toRow, toCol)) {
      score--;
      board[fromRow][fromCol] = SlotState.Empty;
      board[toRow][toCol] = SlotState.Marble;
      if (fromRow == toRow) {
        board[fromRow][(toCol + fromCol) / 2] = SlotState.Empty;
      }
      if (fromCol == toCol) {
        board[(fromRow + toRow) / 2][toCol] = SlotState.Empty;
      }
    } else {
      throw new IllegalArgumentException(
              "Invalid move from (" + fromRow + ","
                      + fromCol + ") to (" + toRow + "," + toCol + ")");
    }
  }

  /**
   * Determines whether the game is over yet. (If no more moves can be made)
   *
   * @return boolean      true if over, false if not over
   */
  public boolean isGameOver() {
    if (score == 1 && board[sRow][sCol].equals(SlotState.Marble)) {
      return true;
    }

    for (int i = 0; i < this.boardSize; i++) {
      for (int j = 0; j < this.boardSize; j++) {
        if (i - 2 >= 0 && validMove(i, j, i - 2, j)) {
          return false;
        }
        if (j - 2 >= 0 && validMove(i, j, i, j - 2)) {
          return false;
        }
        if (i + 2 < this.boardSize && validMove(i, j, i + 2, j)) {
          return false;
        }
        if (j + 2 < this.boardSize && validMove(i, j, i, j + 2)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Determines if the given indexes are a valid amount away from each other for a move to be made
   * with their center SlotState being a SlotState.Marble
   *
   * @param fromRow               row moving from
   * @param toRow                 row moving to
   * @param fromCol               col moving from
   * @param toCol                 col moving to
   * @return              true if the given distance between adheres to game-move rules
   */
  protected boolean isCorrectDistance(int fromRow, int toRow, int fromCol, int toCol) {
    return ((fromRow == toRow && Math.abs(fromCol - toCol) == 2
            && this.board[fromRow][(fromCol + toCol) / 2] == SlotState.Marble)
            || (toCol == fromCol && Math.abs(toRow - fromRow) == 2
            && this.board[(toRow + fromRow) / 2][toCol] == SlotState.Marble));
  }

  /**
   * Determines if a given move is valid can be played.
   * A method used to validate, and then make moves in the game
   * A move valid move is a...
   * Move a single marble from a given position to another given position.
   * A move is valid only if the from and to positions are valid.
   * The move fits specific constraints (i.e: cardinal direction, and move distance)
   *
   * @param fromRow         the row index of the position you're moving from
   * @param fromCol         the column index of the position you're moving from
   * @param toRow           the row index of the position you're moving to
   * @param toCol           the column index of the position you're moving to
   * @return                returns true if the given move is valid, false if not
   * @throws IllegalArgumentException            if given an invalid r or c index
   */
  protected boolean validMove(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {

    return validIndex(fromRow, fromCol) && validIndex(toRow, toCol)
            && this.getSlotAt(fromRow, fromCol).equals(SlotState.Marble)
            && this.getSlotAt(toRow, toCol).equals(SlotState.Empty)
            && this.isCorrectDistance(fromRow, toRow, fromCol, toCol);
  }


  /**
   * Retrieve the size of the board.
   *
   * @return int        the board size as an integer
   */
  @Override
  public int getBoardSize() {
    return this.boardSize;
  }

  /**
   * A helper method that returns the SlotState of the Slot at a given position
   *
   * @param row                  row position to get from
   * @param col                  col position to get from
   * @return SlotState           the state of the slot at the given position (r,c)
   * @throws IllegalArgumentException   if the given position is not on the board
   */
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row >= this.boardSize || col < 0 || col >= this.boardSize) {
      throw new
              IllegalArgumentException("Cannot use invalid slot index: (" + row + ", " + col + ")");
    }
    return this.board[row][col];
  }

  /**
   * Return the number of marbles left on the board
   *
   * @return int         the score as an integer
   */
  @Override
  public int getScore() {
    return this.score;
  }

  /**
   * Checks if given position through row and column are valid indices on the game board
   *
   * @param row               integer row index you are checking to be valid
   * @param col               integer col index you are checking to be valid
   * @return                  returns true if the given index is valid, false otherwise
   */
  protected abstract boolean validIndex(int row, int col);

}
