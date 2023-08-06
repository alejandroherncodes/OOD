package spreadsheet;

/**
 * This class represents a macro that can assign all the cells in a range
 * to a specific value. The value of two cells differ by a set increment
 */
public class RangeAssignMacro implements SpreadSheetMacro {
  private final int fromRow;
  private final int fromCol;
  private final int toRow;
  private final int toCol;
  private final double startVal;
  private final double increment;

  /**
   * Create a range assign macro given a row or column of cells,
   * a start value and the increment of the value
   * as the range of cells is filled in.
   *
   * @param fromRow    the row of the starting cell
   * @param fromCol    the column of the starting cell
   * @param toRow      the row of the ending cell
   * @param toCol      the column of the ending cell
   * @param startVal the starting value
   * @param increment  the increment of value from one cell to the next one
   * @throws IllegalArgumentException if the cell rows or columns are invalid
   */
  public RangeAssignMacro(int fromRow, int fromCol, int toRow, int toCol,
                          double startVal, double increment) throws IllegalArgumentException {
    if ((fromRow < 0) || (fromCol < 0) || (toRow < 0) || (toCol < 0)) {
      throw new IllegalArgumentException("Parameters to range assignment cannot be negative.");
    }
    if (!(((fromCol != toCol) && (fromRow == toRow))
            || ((fromRow != toRow) && (fromCol == toCol)))) {
      throw new IllegalArgumentException("RangeAssignMacro can only take in " +
              "positions with corresponding row or one column at a time.");
    }
    this.fromRow = fromRow;
    this.fromCol = fromCol;
    this.toRow = toRow;
    this.toCol = toCol;
    this.startVal = startVal;
    this.increment = increment;
  }

  /**
   * Applies the Range Assign Macro to the given SpreadSheet
   *
   * @param sheet              the SpreadSheet to execute on
   */
  @Override
  public void execute(SpreadSheet sheet) {
    double value = startVal;

    for (int i = fromRow; i <= toRow; i += 1) {
      for (int j = fromCol; j <= toCol; j += 1) {
        sheet.set(i, j, value);
        value += this.increment;
      }
    }
  }
}