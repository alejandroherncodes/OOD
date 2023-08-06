package cs3500.marblesolitaire.model.hw02;

import java.util.ArrayList;

/**
 * Represents the English Solitaire game model.
 */
public class EnglishSolitaireModel implements MarbleSolitaireModel, MarbleSolitaireModelState {
  private int sRow;
  private int sCol;
  private int armThickness;
  private ArrayList<ArrayList<String>> board;
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
    } else {
      this.armThickness = armThickness;
    }
    if (!this.validSlot(sRow, sCol)) {
      throw new IllegalArgumentException("Invalid empty cell position " +
              "(" + sRow + "," + sCol + ")");
    } else {
      this.sCol = this.armThickness;
      this.sRow = this.armThickness;
      this.board = this.drawBoard();
    }
  }

  /**
   * A method used to validate, and then make moves in the game
   * A move valid move is a...
      * Move a single marble from a given position to another given position.
      * A move is valid only if the from and to positions are valid.
      * The move fits specific constraints (i.e: cardinal direction, and move distance)
   *
   * @param fromRow            row position to be moved to
   * @param fromCol            col position to be moved to
   * @param toRow              row position to be moved to
   * @param toCol              col position to be moved to
   * @throws IllegalArgumentException if a move is invalid
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    // checking if move is valid
    if (!this.validSlot(fromRow, fromCol) || !this.validSlot(toRow, toCol)) {
      throw new IllegalArgumentException("Invalid move: Posn out of bounds");
    }
    if (this.getSlotAt(fromRow, fromCol) == SlotState.Empty) {
      throw new IllegalArgumentException("Invalid move: No marble at the 'from' slot");
    }
    if (this.getSlotAt(toRow, toCol) != SlotState.Empty) {
      throw new IllegalArgumentException("Invalid move: Occupied 'to' slot");
    }
    if (Math.abs(fromRow - toRow) != 2 && Math.abs(fromCol - toCol) != 2) {
      throw new IllegalArgumentException("Invalid move: Move is not two spaces apart");
    }
    if (((toCol - fromCol > 0 && this.getSlotAt(fromRow, fromCol + 1) == SlotState.Empty)
            || (toCol - fromCol < 0 && this.getSlotAt(fromRow, fromCol - 1) == SlotState.Empty))
            || ((toRow - fromRow > 0 && this.getSlotAt(fromRow + 1, fromCol) == SlotState.Empty)
            || (toRow - fromRow < 0
            && this.getSlotAt(fromRow - 1, fromCol) == SlotState.Empty))) {
      throw new IllegalArgumentException("Invalid move: No marble in between");
    }

    // True if the row move is in a cardinal direction
    boolean cardinal = Math.abs(toRow - fromRow) != 0;

    // Empty the slot in between the from and to position
    if (toCol - fromCol > 0 && !cardinal) {
      this.board.get(fromRow).set(fromCol + 1, "_");
    } else if (0 < toRow - fromRow) {
      this.board.get(fromRow + 1).set(fromCol, "_");
    } else if (cardinal) {
      this.board.get(fromRow - 1).set(fromCol, "_");
    } else {
      this.board.get(fromRow).set(fromCol - 1, "_");
    }

    // Empty the 'To' slot
    this.board.get(fromRow).set(fromCol, "_");
    // Put a marble in the 'From' slot
    this.board.get(toRow).set(toCol, "O");
  }

  /**
   * Determines whether the game is over yet. (If no more moves can be made)
   *
   * @return boolean      true if over, false if not over
   */
  @Override
  public boolean isGameOver() {
    // Iterate through each position on the board
    for (int r = 0; r < board.size(); r++) {
      for (int c = 0; c < board.get(c).size(); c++) {
        // Check if the current position contains a marble
        if (board.get(r).get(c).equals("O")) {
          // Check if there are any valid moves for the current marble
          return ((Math.abs(r - (r + 2)) != 2 || Math.abs(r - (r - 2)) != 2 )
                  && (Math.abs(c - (c + 2)) != 2 || Math.abs(c - (c - 2)) != 2));
        }
      }
    }
    // No valid moves found for any marble, so the game is over
    return true;
  }

  /**
   * A helper method that returns the SlotState of the Slot at a given position
   *
   * @param r               row position to get from
   * @param c               col position to get from
   * @return                the state of the slot at the given position (r,c)
   * @throws IllegalArgumentException   if the given position is not on the board
   */
  @Override
  public SlotState getSlotAt(int r, int c) throws IllegalArgumentException {
    if (!this.validSlot(r, c)) {
      return SlotState.Invalid;
    } else if (this.board.get(r).get(c).equals("_")) {
      return SlotState.Empty;
    } else {
      return SlotState.Marble;
    }
  }

  /**
   * Determines if a given position is on the board
   *
   * @param r row of location
   * @param c column of location
   * @return if location is on board
   */
  public boolean validSlot(int r, int c) {
    // The upper limit of the board: a position too big to be on the grid
    int outerLimit = this.armThickness / 2 + 1;

    // Checks if given position is within the
    // lower and upper bounds (on the board)
    if ((r < (this.armThickness / 2 + 1) && r >= 0) ||
            (r < this.getBoardSize() && r > (this.getBoardSize() - outerLimit - 1))) {
      return c >= (this.armThickness / 2 + 1) && c <= (this.getBoardSize() - outerLimit - 1);
    } else {
      return r >= (this.armThickness / 2 + 1) && r <= (this.getBoardSize() - outerLimit - 1);
    }
  }

  /**
   * Creates a text view of the board
   * A text view is a grid of string values representing slots based on
   * pictograph symbols: _ for an empty slot, O for a slot occupied with a marble
   *
   * @return        the current state of the board as a nested list of strings
   */
  public ArrayList<ArrayList<String>> drawBoard() {
    ArrayList<ArrayList<String>> currentBoard = new ArrayList<ArrayList<String>>();

    for (int r = 0; r < this.getBoardSize(); r++) {
      currentBoard.add(new ArrayList<String>());

      for (int c = 0; c < this.getBoardSize(); c++) {
        if (r == sRow && c == sCol) {
          currentBoard.get(r).add("_");
        } else if (validSlot(r, c)) {
          currentBoard.get(r).add("O");
        } else {
          currentBoard.get(r).add(" ");
        }
      }
    }
    return currentBoard;
  }

  /**
   * Return the number of marbles left on the board
   *
   * @return int         the score as an integer
   */
  @Override
  public int getScore() {
    int marblesLeft = 0;

    for (ArrayList<String> allSlots : this.board) {
      for (String thisSlot : allSlots) {
        if (thisSlot.equals("O")) {
          marblesLeft++;
        }
      }
    }
    return marblesLeft;
  }

  /**
   * Retrieve the size of the board.
   *
   * @return int        the board size as an integer
   */
  @Override
  public int getBoardSize() {
    return armThickness * 2 + 1;
  }

  /**
   * Used to set the value of the board
   *
   * @param board given board value
   */
  public void setBoard(ArrayList<ArrayList<String>> board) {
    this.board = board;
  }
}