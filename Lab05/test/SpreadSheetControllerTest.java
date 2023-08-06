import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import spreadsheet.SparseSpreadSheet;
import spreadsheet.SpreadSheet;
import spreadsheet.SpreadSheetController;
import static org.junit.Assert.assertEquals;

public class SpreadSheetControllerTest {
  private String[] outputMessage;

  @Before
  public void setup() {
    Readable readModel = new StringReader("q");
    Appendable appendModel = new StringBuilder();
    SpreadSheet spreadSheet = new SparseSpreadSheet();
    SpreadSheetController controller = new SpreadSheetController(spreadSheet, readModel, appendModel);

    controller.go();
    String output = appendModel.toString();
    outputMessage = output.split("\n");
  }

  @Test
  public void testGo() {
    SpreadSheet spreadSheet = new SparseSpreadSheet();
    Readable readModel = new StringReader("q");
    StringBuilder output2 = new StringBuilder();
    String[] text = output2.toString().split("\n");
    SpreadSheetController spreadSheetController = new SpreadSheetController(spreadSheet, readModel, output2);

    assertEquals("Welcome to the spreadsheet program!", outputMessage[0]);
    assertEquals("Supported user instructions are: ", outputMessage[1]);
    assertEquals("assign-value row-num col-num value " +
            "(set a cell to a value)", outputMessage[2]);
    assertEquals("print-value row-num col-num (print the " +
            "value at a given cell)", outputMessage[3]);
    assertEquals("menu (Print supported instruction list)", outputMessage[4]);
    assertEquals("q or quit (quit the program) ", outputMessage[5]);
    assertEquals("Type instruction: Thank you for using this program!", outputMessage[6]);

    spreadSheetController.go();

    assertEquals("Welcome to the spreadsheet program!" + System.lineSeparator()
            + "Supported user instructions are: " + System.lineSeparator()
            + "assign-value row-num col-num value (set a cell to a value)"
            + System.lineSeparator() + "print-value row-num col-num " +
            "(print the value at a given cell)" + System.lineSeparator() + "menu (Print supported " +
            "instruction list)" + System.lineSeparator() + "q or quit (quit the program) "
            + System.lineSeparator() + "Type instruction: " + "Thank you for using this program!",
            output2.toString());
  }
}
