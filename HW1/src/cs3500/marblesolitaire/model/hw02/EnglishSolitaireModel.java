package cs3500.marblesolitaire.model.hw02;
import java.util.ArrayList;

/**
 * Represents the English Solitaire game model.
 */
public class EnglishSolitaireModel implements MarbleSolitaireModel {
  private final int armThickness;
  private final int boardSize;
  private int score;
  private final int sCol;
  private final int sRow;
  private ArrayList<ArrayList<SlotState>> board;

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
   * @param armThickness the arm thickness of the board
   * @param sRow         the row of the empty slot
   * @param sCol         the column of the empty slot
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number or the
   *                                  empty cell position is invalid
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {

    if (armThickness % 2 == 0 || armThickness < 1) {
      throw new IllegalArgumentException("Arm thickness must be an odd, positive number");
    }

    this.armThickness = armThickness;
    this.boardSize = (this.armThickness * 3) - 2;
    this.score = 0;
    this.sCol = sCol;
    this.sRow = sRow;
    this.board = new ArrayList<>();

    if (sRow < 0 || sRow > this.boardSize || sCol < 0 || sCol > this.boardSize) {
      throw new IllegalArgumentException(
              "Invalid empty cell position (" + sRow + "," + sCol + ")");
    }

    for (int rIdx = 0; rIdx < this.boardSize; rIdx += 1) {
      ArrayList<SlotState> newColumn = new ArrayList<>();

      for (int cIdx = 0; cIdx < this.boardSize; cIdx += 1) {
          if ((rIdx >= 2 * armThickness - 1 && rIdx <= this.boardSize - 1 && cIdx >= 2 * 
                  armThickness - 1 && cIdx <= this.boardSize - 1) || (rIdx <= (armThickness - 2) 
                  && cIdx <= (armThickness - 2)) || (rIdx >= 2 * armThickness - 1 && rIdx <=
                  this.boardSize - 1 && cIdx <= (armThickness - 2)) || (rIdx <= (armThickness - 2) 
                  && cIdx >= 2 * armThickness - 1 && cIdx <= this.boardSize - 1)) {
          newColumn.add(SlotState.Invalid);
        } else {
          newColumn.add(SlotState.Marble);
          score += 1;
        }
      }
      board.add(newColumn);
    }

    if (this.getSlotAt(sRow, sCol) != SlotState.Marble) {
      throw new IllegalArgumentException("Invalid empty cell position ("
              + sRow + "," + sCol + ")");
    }

    board.get(sRow).set(sCol, SlotState.Empty);
    score -= 1;
  }

  /**
   * A method used to validate, and then make moves in the game
   *
   * @param fromRow            row position to be moved to
   * @param fromCol            col position to be moved to
   * @param toRow              row position to be moved to
   * @param toCol              col position to be moved to
   * @throws IllegalArgumentException if a move is invalid
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {
    if (fromRow < 0 || fromRow > this.getBoardSize()
            || fromCol < 0 || fromCol > this.getBoardSize()
            || toRow < 0 || toRow > this.getBoardSize()
            || toCol < 0 || toCol > this.getBoardSize()) {
      throw new IllegalArgumentException("Invalid move: Posn out of bounds");
    } 
    if (this.getSlotAt(fromRow, fromCol) == SlotState.Empty) {
      throw new IllegalArgumentException("Invalid move: Empty 'from' slot");
    }
    if (this.getSlotAt(toRow, toCol) != SlotState.Empty) {
      throw new IllegalArgumentException("Invalid move: Occupied 'to' slot");
    }
    if (Math.abs(fromRow - toRow) != 2 && Math.abs(fromCol - toCol) != 2) {
      throw new IllegalArgumentException("Invalid move: Move is not two spaces apart");
    } // Checks the top and bottom, right and left corners around the given Slot for Marbles
    if (((toCol - fromCol > 0 && this.getSlotAt(fromRow, fromCol + 1) == SlotState.Empty)
            || ((toRow - fromRow > 0 && this.getSlotAt(fromRow + 1, fromCol)
            == SlotState.Empty) || (toRow - fromRow < 0  && this.getSlotAt(fromRow - 1,
            fromCol) == SlotState.Empty)) || (toCol - fromCol < 0 && this.getSlotAt(fromRow,
            fromCol - 1) == SlotState.Empty))) {
      throw new IllegalArgumentException("Invalid move: No marble in between");
    }
    
    // Empty the 'To' slot 
    this.board.get(fromRow).set(fromCol, SlotState.Empty);
    
    // True if the row move is in a cardinal direction
    boolean cardinal = Math.abs(toRow - fromRow) != 0;
    
    // Empty the slot in between the from and to position
    if (toCol - fromCol > 0 && !cardinal) {
      this.board.get(fromRow).set(fromCol + 1, SlotState.Empty);
    } else if (0 < toRow - fromRow) {
      this.board.get(fromRow + 1).set(fromCol, SlotState.Empty);
    } else if (cardinal) {
      this.board.get(fromRow - 1).set(fromCol, SlotState.Empty);
    } else {
      this.board.get(fromRow).set(fromCol - 1, SlotState.Empty);
    }
    
    // Put a marble in the 'From' slot
    this.board.get(toRow).set(toCol, SlotState.Marble);
  }

  /**
   * Determines whether the game is over yet.
   *
   * @return boolean      true if over, false if not over
   */
  @Override
  public boolean isGameOver() {
    boolean value = false;

    for (int r = 0; r < this.getBoardSize(); r++) {
      for (int c = 0; c < this.getBoardSize(); c++) {
        if (score == 0) { // Only the center position is left
          value = true;
        }
      }
    }
    return value;
  }

  /**
   * A helper method that returns the SlotState of the Slot at a given position
   *
   * @param r               row position to get from
   * @param c               col position to get from
   * @return SlotState      indicating if the slot is empty, invalid, or occupied
   */
  @Override
  public SlotState getSlotAt(int r, int c) throws IllegalArgumentException {
    if (r < 0 || r >= this.boardSize || c < 0 || c >= this.boardSize) {
      throw new IllegalArgumentException("Position is out of bounds.");
    }
    return this.board.get(r).get(c);
  }

  /**
   * A getter method used to retrieve the score
   *
   * @return int         the score as an integer
   */
  @Override
  public int getScore() {
    return this.score;
  }

  /**
   * A getter method used to retrieve the board size
   *
   * @return int        the board size as an integer
   */
  @Override
  public int getBoardSize() {
    return this.boardSize;
  }
}

