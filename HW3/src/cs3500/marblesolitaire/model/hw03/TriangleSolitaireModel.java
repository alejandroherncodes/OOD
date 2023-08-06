package cs3500.marblesolitaire.model.hw03;

/**
 * Represents a triangle solitaire model
 */
public class TriangleSolitaireModel extends ASolitaireModel {

  /**
   * Constructs a TriangleSolitaireModel game object.
   *  A constructor with three parameters (dimensions,row,col) that creates a game with
   *  the specified dimension and an empty slot at the specified row and column.
   *
   * @param dimension        number of rows of the board
   * @param sRow             the row index for the starting empty slot
   * @param sCol             the column index for the starting empty slot on a valid board posn.
   * @throws IllegalArgumentException if given negative dimension, or invalid sRow and/or sCol for
   *                                  empty slot
   */
  public TriangleSolitaireModel(int dimension, int sRow, int sCol) {
    super(dimension, sRow, sCol);
    for (int i = 1; i <= dimension; i++) {
      this.score += i;
    }
    this.score -= 1;
    this.boardSize = dimension;
  }

  /**
   * Constructs a TriangleSolitaireModel game object by defaulting to hard coded values
   * of 5 for dimension and index of the empty slot at (0, 0)
   *  A default constructor (no parameters) that creates a 5-row game with the empty slot at (0,0).
   *
   */
  public TriangleSolitaireModel() {
    this(5, 0, 0);
  }

  /**
   * Constructs a TriangleSolitaireModel game object with the given dimension
   * and a default index of the empty slot at (0,0).
   *  A constructor with a single parameter (dimensions) that creates a game with the specified
   *  dimension (number of slots in the bottom-most row) and the empty slot at (0,0).
   *
   * @param dimension                   the number of rows for the game
   * @throws IllegalArgumentException   if the dimension is negative
   */
  public TriangleSolitaireModel(int dimension) throws IllegalArgumentException {
    this(dimension, 0, 0);

  }

  /**
   * Constructs a TriangleSolitaireModel game object with a defaulted dimension of 5 rows
   * and a given index (sRow, sCol) for the slot of the empty cell.
   *  A constructor with two parameters (row,col) that
   *  creates a 5-row game with the empty slot at the specified position.
   *
   * @param sRow                             the row of the empty cell
   * @param sCol                             the column of the empty cell
   * @throws IllegalArgumentException        if the empty slot is not at a valid position
   */
  public TriangleSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this(5, sRow, sCol);
  }

   /**
   * A method used to validate, and then make moves in the game
   * A move is valid only if the from and to positions are valid.
   * Specific implementations may place additional constraints on the validity of a move.
   *
   * @param fromRow            row position to be moved to
   * @param fromCol            col position to be moved to
   * @param toRow              row position to be moved to
   * @param toCol              col position to be moved to
   * @throws IllegalArgumentException if a move is invalid
   */
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    super.move(fromRow, fromCol, toRow, toCol);
    if (fromCol != toCol && fromRow != toRow) {
      board[(fromRow + toRow) / 2][(toCol + fromCol) / 2] = SlotState.Empty;
    }
  }

  /**
   * Determines whether the game is over yet. (If no more moves can be made)
   *
   * @return boolean      true if over, false if not over
   */
  public boolean isGameOver() {
    for (int i = 0; i < this.boardSize; i++) {
      for (int j = 0; j < this.boardSize; j++) {
        if (j - 2 >= 0 && i - 2 >= 0 && validMove(i, j, i - 2, j - 2)) {
          return false;
        }
        if (j + 2 < this.boardSize && i + 2 < this.boardSize && validMove(i, j, i + 2, j + 2)) {
          return false;
        }
      }
    }
    return super.isGameOver();
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
    return super.isCorrectDistance(fromRow, toRow, fromCol, toCol) ||
            (Math.abs(fromCol - toCol) == 2 && Math.abs(toRow - fromRow) == 2
                    && this.board[(toRow + fromRow) / 2][(toCol + fromCol) / 2] == SlotState.Marble);
  }


  /**
   * Checks if given position through row and column are valid indices on the game board
   *
   * @param row               integer row index you are checking to be valid
   * @param col               integer col index you are checking to be valid
   * @return                  returns true if the given index is valid, false otherwise
   */
  @Override
  protected boolean validIndex(int row, int col) {
    boolean isValid;

    if (row < 0 || row >= this.boardSize || col < 0 || col >= this.boardSize) {
      throw new
              IllegalArgumentException("Cannot use invalid slot index: ("
              + row + ", " + col + ")");
    } else {
      isValid = col <= row;
    }
    return isValid;
  }
}