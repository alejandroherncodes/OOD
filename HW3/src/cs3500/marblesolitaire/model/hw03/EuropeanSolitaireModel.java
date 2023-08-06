package cs3500.marblesolitaire.model.hw03;

/**
 * Represents a European o solitaire model
 */
public class EuropeanSolitaireModel extends ASolitaireModel {

  /**
   * A constructor with three parameters (side length, row, col), to
   * specify the size of the board and the initial position of the empty slot.
   *
   * @param sideLength      length of 1 side of the board, as a positive, odd number
   * @param sRow            the row index for the empty starting slot, slot posn must be valid.
   * @param sCol            the column index for the empty starting slot, slot posn must be valid.
   * @throws IllegalArgumentException   if given odd or negative thickness, or invalid sRow
   *                                    and/or sCol for the empty slots location.
   */
  public EuropeanSolitaireModel(int sideLength, int sRow, int sCol)
          throws IllegalArgumentException {
    super(sideLength, sRow, sCol);
    this.score = 4 * (sideLength * sideLength) + (sideLength - 2) * (sideLength - 2) - 5;
    for (int i = 1; i <= sideLength - 2; i++) {
      this.score += i * 4;
    }
  }

  /**
   * A default constructor (no parameters) that creates an octagonal board
   * whose sides have length 3, with the empty slot in the center of the board.
   */
  public EuropeanSolitaireModel() {
    this(3, 3, 3);
  }

  /**
   * A constructor with two parameters (row, col), to specify the
   * initial position of the empty slot, in a board of default size 3.
   *
   * @param sRow            the row index for the empty starting slot, slot posn must be valid.
   * @param sCol            the column index for the empty starting slot, slot posn must be valid.
   * @throws IllegalArgumentException   if given odd or negative thickness, or invalid sRow
   *                                    and/or sCol for the empty slots location.
   */
  public EuropeanSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this(3, sRow, sCol);

  }

  /**
   * A constructor with a single parameter (the side length) that creates
   * a game with the specified side length, and the empty slot in the center of the board.
   *
   * @param sideLength      length of 1 side of the board, as a positive, odd number
   * @throws IllegalArgumentException   if given odd or negative thickness, or invalid sRow
   *                                    and/or sCol for the empty slots location.
   */
  public EuropeanSolitaireModel(int sideLength) throws IllegalArgumentException {
    this(sideLength, (sideLength * 3 - 2) / 2, (sideLength * 3 - 2) / 2);
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
    if (row < 0 || row >= this.boardSize || col < 0 || col >= this.boardSize) {
      throw new
              IllegalArgumentException("Cannot use invalid slot index: ("
              + row + ", " + col + ")");
    }
    if (row < sideLength - 1) {
      return (col > (boardSize / 2 - (sideLength + 2 * row) / 2) - 1) &&
              (col < (boardSize / 2 + (sideLength + 2 * row) / 2) + 1);
    } else if (row >= sideLength * 2 - 1) {
      int calculateMirrorRow = (boardSize - 1) - row;
      return (col > (boardSize / 2 - (sideLength + 2 * calculateMirrorRow) / 2) - 1) &&
              (col < (boardSize / 2 + (sideLength + 2 * calculateMirrorRow) / 2) + 1);
    }
    return true;
  }
}
