package spreadsheet;

/**
 * This class represents a macro that will
 * find the average value of a cells in a given range
 * and set that average to a specified cell.
 */
public class AverageMacro implements SpreadSheetMacro {
  private final int fromRow;
  private final int fromCol;
  private final int toRow;
  private final int toCol;
  private final int destRowNum;
  private final int destColNum;

  /**
   * Construct an average of from-row-num from-col-num to-row-num
   * to-col-num dest-row-num dest-col-num.
   * This will compute the average of a range of cells to put in the specified destination cell.
   *
   * @param fromRow                                  the row of the starting cell
   * @param fromCol                               the column of the starting cell
   * @param toRow                                      the row of the ending cell
   * @param toCol                                   the csolumn of the ending cell
   * @param destRowNum                     the row of the specified destination cell
   * @param destColNum                  the column of the specified destination cell
   * @throws IllegalArgumentException                if a given value is negative
   */
  public AverageMacro(int fromRow, int fromCol, int toRow, int toCol, int destRowNum, int destColNum)
          throws IllegalArgumentException {
    if ((fromRow < 0) || (fromCol < 0) || (toRow < 0) || (toCol < 0) ||
            (destRowNum < 0) || (destColNum < 0)) {
      throw new IllegalArgumentException("None of the given rows or columns can be negative.");
    }

    if (fromRow > toRow) {
      this.fromRow = toRow;
      this.toRow = fromRow;
    } else {
      this.fromRow = fromRow;
      this.toRow = toRow;
    }

    if (fromCol > toCol) {
      this.fromCol = toCol;
      this.toCol = fromCol;
    } else {
      this.fromCol = fromCol;
      this.toCol = toCol;
    } 
    
    this.destRowNum = destRowNum;
    this.destColNum = destColNum;
  }

  /**
   * Average from-row-num from-col-num to-row-num to-col-num
   * dest-row-num dest-col-num. This will compute the average of a
   * range of cells and put it in the specified destination cell.
   *
   * @param sheet                     the SpreadSheet to execute on
   */
  @Override
  public void execute(SpreadSheet sheet) {
    int n = Math.abs(this.toCol - this.fromCol + 1) * Math.abs(this.toRow - this.fromRow + 1);
    double average = 0;
    // int row = Math.min(fromRow, toRow); row <= Math.max(fromRow, toRow);
    for (int row = this.fromRow; row <= toRow; row += 1) {
      for (int col = this.fromCol; col <= toCol; col += 1) {
        average += sheet.get(row, col);
      }
    }
    average /= n;
    sheet.set(destRowNum, destColNum, average);
  }
}
