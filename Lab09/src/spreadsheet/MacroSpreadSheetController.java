package spreadsheet;

import java.util.Scanner;

/**
 * This class represents the controller of an editable spreadsheet interface
 */
public class MacroSpreadSheetController extends SpreadSheetController {
  /**
   * Create a macro spreadsheet controller to work with a
   * given sheet, readable and appendable.
   *
   * @param application    a model to apply operations to (of a spreadsheet)
   * @param readable                         object used for reading inputs
   * @param appendable                  object used for output transmission
   */
  public MacroSpreadSheetController(MacroSpreadSheet application, Readable readable, Appendable appendable) {
    super(application, readable, appendable);
  }

  /**
   * Used to process user inputs
   *
   * @param userCommand             input from the user
   * @param sc       scanner for scanning inputs
   * @param sheet        the SpreadSheet to be modified
   */
  @Override
  protected void processCommand(String userCommand, Scanner sc, SpreadSheet sheet) {
    if (!(sheet instanceof MacroSpreadSheet)) {
      throw new IllegalArgumentException("Must have a macro spreadsheet");
    }

    MacroSpreadSheet application = (MacroSpreadSheet) sheet;
    switch (userCommand) {
      case "bulk-assign-value":
        try {
          int fromRow = getRowNum(sc.next());
          int fromCol = sc.nextInt() - 1;
          int toRow = getRowNum(sc.next());
          int toCol = sc.nextInt() - 1;
          double value = sc.nextDouble();
          application.executeMacro(
                  new BulkAssignMacro(fromRow, fromCol, toRow, toCol, value));
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "range-assign":
        try {
          int fromRow = getRowNum(sc.next());
          int fromCol = sc.nextInt() - 1;
          int toRow = getRowNum(sc.next());
          int toCol = sc.nextInt() - 1;
          double fromValue = sc.nextDouble();
          double increment = sc.nextDouble();
          application.executeMacro(
                  new RangeAssignMacro(fromRow, fromCol, toRow, toCol, fromValue, increment));
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
      case "average":
        try {
          int fromRow = getRowNum(sc.next());
          int fromCol = sc.nextInt() - 1;
          int toRow = getRowNum(sc.next());
          int toCol = sc.nextInt() - 1;
          int destRow = getRowNum(sc.next());
          int destCol = sc.nextInt() - 1;
          application.executeMacro(
                  new AverageMacro(fromRow, fromCol, toRow, toCol, destRow, destCol));
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      default:
        super.processCommand(userCommand, sc, sheet);
    }
  }

  /**
   * Display a full list of the supported operation
   *
   * @throws IllegalStateException   if an IllegalObjectException is found
   */
  @Override
  protected void printMenu() throws IllegalStateException {
    writeMessage("Supported user instructions are: \n");
    writeMessage("assign-value row-num col-num value (set a cell to a value)\n");
    writeMessage("print-value row-num col-num (print the value at a given cell)\n");
    writeMessage("bulk-assign-value from-row from-col-num to-row to-col-num value " +
            "(assigns all cells in within the region to the given value)\n");
    writeMessage("average from-row-num from-col-num to-row-num to-col-num dest-row-num " +
            "dest-col-num (compute the average of a range of cells and put it in " +
            "the specified destination cell)\n");
    writeMessage("range-assign from-row-num from-col-num to-row-num to-col-num " +
            "start-value increment (set a row or column of cells to a range of" +
            " values starting at the given value and advancing by the given increment)\n");
    writeMessage("menu (Print supported instruction list)\n");
    writeMessage("q or quit (quit the program) \n");
  }

}