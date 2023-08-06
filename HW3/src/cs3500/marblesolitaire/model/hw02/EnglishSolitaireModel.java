package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw03.ASolitaireModel;

/**
 * Represents the English Solitaire game model.
 */
public class EnglishSolitaireModel extends ASolitaireModel {

  /**
   * Constructs a default EnglishSolitaireModel with arm thickness 3 and empty slot at the center.
   */
  public EnglishSolitaireModel() {
    this(3,3,3);
  }

  /**
   * Constructs an EnglishSolitaireModel with specified arm thickness and empty slot at the center.
   *
   * @param armThickness the arm thickness of the board
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number
   */
  public EnglishSolitaireModel(int armThickness) {
    this(armThickness, ((armThickness * 3) -3) / 2, ((armThickness * 3) - 3) / 2);
  }

  /**
   * Constructs an EnglishSolitaireModel with arm thickness 3 and specified empty slot position.
   *
   * @param sRow the row of the empty slot
   * @param sCol the column of the empty slot
   * @throws IllegalArgumentException if the empty cell position is invalid
   */
  public EnglishSolitaireModel(int sRow, int sCol) {
    this(3, sRow, sCol);
  }

  /**
   * Constructs an EnglishSolitaireModel with specified arm thickness and empty slot position.
   *
   * @param sideLength   the arm thickness of the board
   * @param sRow         the row of the empty slot
   * @param sCol         the column of the empty slot
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number or the
   *                                  empty cell position is invalid
   */
  public EnglishSolitaireModel(int sideLength, int sRow, int sCol) throws IllegalArgumentException {
    super(sideLength, sRow, sCol);
    this.score = 4 * (sideLength * sideLength) + (sideLength - 2) * (sideLength - 2) - 5;

  }
  @Override
  protected boolean validIndex(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row >= this.boardSize || col < 0 || col >= this.boardSize) {
      throw new
              IllegalArgumentException("Cannot use invalid slot index: ("
              + row + ", " + col + ")");
    }
    return ((row > sideLength - 2 && row < this.boardSize - (sideLength - 1) && col >= 0
            && col < boardSize) ||
            (col > sideLength - 2 && col < this.boardSize - (sideLength - 1) && row >= 0
                    && row < boardSize));
  }
}