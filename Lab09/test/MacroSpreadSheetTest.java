package spreadsheet;

import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.*;

/**
 * This class is the tester for a sparse spreadsheet.
 */
public class MacroSpreadSheetTest {

  private SpreadSheet sheet;
  private SpreadSheetMacro average;
  private SpreadSheetMacro bulkAssign;
  private SpreadSheetMacro rangeAssign;

  @Before
  public void setup() {
    sheet = new SparseSpreadSheet();
    average = new AverageMacro(0, 0, 3, 7, 2, 2);
    bulkAssign = new BulkAssignMacro(0, 0, 2, 2, 3.0);
    //rangeAssign = new RangeAssignMacro(0, 0, 3, 4, 2, 2);
  }

  @Test
  public void testPrintMenu() {
    SparseSpreadSheet model = new SparseSpreadSheet();
    Readable rd = new StringReader("q");
    Appendable ap = new StringBuilder();
    MacroSpreadSheetController controller = new MacroSpreadSheetController(model, rd, ap);
    controller.control();

    String expectedMessage = "Welcome to the spreadsheet program!\n" +
            "Supported user instructions are: \n" +
            "assign-value row-num col-num value (set a cell to a value)\n" +
            "print-value row-num col-num (print the value at a given cell)\n" +
            "bulk-assign-value from-row from-col-num to-row to-col-num value (assigns " +
            "all cells in within the region to the given value)\n"
            +
            "average from-row-num from-col-num to-row-num to-col-num dest-row-num " +
            "dest-col-num (compute the average of a range of cells and put it in the" +
            " specified destination cell)\n"
            +
            "range-assign from-row-num from-col-num to-row-num to-col-num start-value " +
            "increment (set a row or column of cells to a range of values starting at " +
            "the given value and advancing by the given increment)\n"
            +
            "menu (Print supported instruction list)\n" +
            "q or quit (quit the program) \n" +
            "Type instruction: Thank you for using this program!";

    assertEquals(expectedMessage, ap.toString());
  }

  /**
   * Test the various macro constructors
   */

  @Test
  public void testInvalidBulkAssignMacro() {
    try {
      SpreadSheetMacro badBulkAssign = new BulkAssignMacro(-1, 0, 0, 0, 0);
      fail("No exception due to invalid constructor was caught.");
    } catch (IllegalArgumentException e) {
      // No exception
    }
    try {
      SpreadSheetMacro badBulkAssign = new BulkAssignMacro(0, -1, 0, 0, 0);
      fail("No exception due to invalid constructor was caught.");
    } catch (IllegalArgumentException e) {
      // No exception
    }
    try {
      SpreadSheetMacro badBulkAssign = new BulkAssignMacro(0, 0, -1, 0, 0);
      fail("No exception due to invalid constructor was caught.");
    } catch (IllegalArgumentException e) {
      // No exception
    }
  }

  @Test
  public void testInvalidAverageMacro() {
    try {
      SpreadSheetMacro badAverage = new AverageMacro(-1, 0, 0, 0, 0, 0);
      fail("No exception due to invalid constructor was caught.");
    } catch (IllegalArgumentException e) {
      // Works
    }
    try {
      SpreadSheetMacro badAverage = new AverageMacro(0, -1, 0, 0, 0, 0);
      fail("No exception due to invalid constructor was caught.");
    } catch (IllegalArgumentException e) {
      // Works
    }
    try {
      SpreadSheetMacro badAverage = new AverageMacro(0, 0, -1, 0, 0, 0);
      fail("No exception due to invalid constructor was caught.");
    } catch (IllegalArgumentException e) {
      // Works
    }
  }

  @Test
  public void testInvalidRangeAssignMacro() {
    try {
      SpreadSheetMacro badRangeAssign = new RangeAssignMacro(-1, 0, 0, 0, 0, 0);
      fail("No exception due to invalid constructor was caught.");
    } catch (IllegalArgumentException e) {
      // No exception
    }
    try {
      SpreadSheetMacro badRangeAssign = new RangeAssignMacro(0, -1, 0, 0, 0, 0);
      fail("No exception due to invalid constructor was caught.");
    } catch (IllegalArgumentException e) {
      // No exception
    }
    try {
      SpreadSheetMacro badRangeAssign = new RangeAssignMacro(0, 0, -1, 0, 0, 0);
      fail("No exception due to invalid constructor was caught.");
    } catch (IllegalArgumentException e) {
      // No exception
    }
  }


  /**
   * Passing getters & setters
   */

  @Test
  public void testGetSet() {
    Random r = new Random(100);
    double[][] expectedSet = new double[100][100];
    for (int i = 0; i < 100; i = i + 1) {
      for (int j = 0; j < 100; j = j + 1) {
        double num = r.nextDouble();
        expectedSet[i][j] = num;
        assertTrue(sheet.isEmpty(i, j));
        assertEquals(0.0, sheet.get(i, j), 0.001);
        sheet.set(i, j, num);
        assertFalse(sheet.isEmpty(i, j));
      }
    }

    for (int i = 0; i < 100; i = i + 1) {
      for (int j = 0; j < 100; j = j + 1) {
        assertEquals(expectedSet[i][j], sheet.get(i, j), 0.01);
      }
    }
  }


  @Test
  public void testGetWidthNHeight() {
    assertEquals(0, sheet.getHeight());
    assertEquals(0, sheet.getWidth());
  }

  /**
   * Get exceptions
   **/
  @Test
  public void testGetNegativeRow() {
    try {
      sheet.set(0, 0, 9);
      sheet.set(0, 4, 5);
      sheet.get(-10, 0);
    } catch (IllegalArgumentException e) {
      System.out.println("Exception: " + e);
    }
  }

  @Test
  public void testGetNegativeColumn() {
    try {
      sheet.set(0, 0, 9);
      sheet.set(0, 4, 5);
      sheet.get(0, -10);
    } catch (IllegalArgumentException e) {
      System.out.println("Exception: " + e);
    }
  }
}