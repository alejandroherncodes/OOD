package spreadsheet;

/**
 * This interface represents a macro.
 * A macro is a set of instructions that be executed on a given
 * spreadsheet. Macros can apply operations that a spreadsheet
 * offers without editing the spreadsheet.
 */
public interface SpreadSheetMacro {
  /**
   * Execute this macro on the given sheet.
   *
   * @param sheet          the SpreadSheet to execute on
   */
  void execute(SpreadSheet sheet);
}