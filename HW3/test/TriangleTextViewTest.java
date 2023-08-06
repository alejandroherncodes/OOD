import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw03.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.hw02.MarbleSolitaireTextView;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.hw03.TriangleSolitaireTextView;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

// Test the text view class using the two smallest, valid
// odd numbers according to the traditional game (for diagram creating efficiency)
public class TriangleTextViewTest {
  private MarbleSolitaireModelState defaultModel;
  private MarbleSolitaireTextView dModelView;
  private MarbleSolitaireModelState thickModel;
  private MarbleSolitaireTextView tModelView;
  private String defaultView;
  private String thickView;

  @Before
  public void setUp() {
    defaultModel = new TriangleSolitaireModel();
    dModelView = new TriangleSolitaireTextView(defaultModel);

    thickModel = new TriangleSolitaireModel(11);
    tModelView = new TriangleSolitaireTextView(thickModel);
  }

  @Test
  public void testToString() {
    assertEquals("    _\n" +
            "   0 0\n" +
            "  0 0 0\n" +
            " 0 0 0 0\n" +
            "0 0 0 0 0", dModelView.toString());

    assertEquals("          _\n" +
            "         0 0\n" +
            "        0 0 0\n" +
            "       0 0 0 0\n" +
            "      0 0 0 0 0\n" +
            "     0 0 0 0 0 0\n" +
            "    0 0 0 0 0 0 0\n" +
            "   0 0 0 0 0 0 0 0\n" +
            "  0 0 0 0 0 0 0 0 0\n" +
            " 0 0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0 0 0", tModelView.toString());
  }

  @Test
  public void testTextViewException() {
    try {
      MarbleSolitaireTextView marbleSolitaireTextView = new MarbleSolitaireTextView(null);
    } catch (IllegalArgumentException e) {
      System.out.println("Exception thrown:" + e);
    }
  }

  @Test
  public void testRenderBoard() {
    EnglishSolitaireModel testModel1 = new EnglishSolitaireModel();
    EnglishSolitaireModel testModel2 = new EnglishSolitaireModel(5, 3);

    StringWriter testResult1 = new StringWriter();
    StringWriter testResult2 = new StringWriter();

    MarbleSolitaireTextView testView1 = new MarbleSolitaireTextView(testModel1, testResult1);
    MarbleSolitaireTextView testView2 = new MarbleSolitaireTextView(testModel2, testResult2);

    try {
      testView1.renderBoard();
      testView2.renderBoard();
    } catch (IOException e) {
      System.out.print("Expected exception:" + e);
    }

    assertEquals("     O O O\n" +
            "     O O O\n" +
            " O O O O O O O\n" +
            " O O O _ O O O\n" +
            " O O O O O O O\n" +
            "     O O O\n" +
            "     O O O\n", testResult1.toString());

    assertEquals("     O O O\n" +
            "     O O O\n" +
            " O O O O O O O\n" +
            " O O O O O O O\n" +
            " O O O O O O O\n" +
            "     O _ O\n" +
            "     O O O\n", testResult2.toString());
  }

  @Test
  public void testRenderMessage() {
    EnglishSolitaireModel englishSolitaireModel = new EnglishSolitaireModel();
    StringWriter output = new StringWriter();
    StringWriter output2 = new StringWriter();
    MarbleSolitaireTextView marbleSolitaireTextView = new MarbleSolitaireTextView(englishSolitaireModel, output);
    MarbleSolitaireTextView marbleSolitaireTextView2 = new MarbleSolitaireTextView(englishSolitaireModel, output2);
    try {
      marbleSolitaireTextView.renderMessage("hi");
      marbleSolitaireTextView2.renderMessage("bye");
    } catch (IOException e) {
      System.out.print("Exception caught:" + e);
    }
    assertEquals("hi\n", output.toString());
    assertEquals("bye\n", output2.toString());
  }

}