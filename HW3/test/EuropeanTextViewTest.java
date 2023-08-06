import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw03.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.hw02.MarbleSolitaireTextView;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.hw03.EuropeanSolitaireTextView;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

// Test the text view class using the two smallest, valid
// odd numbers according to the traditional game (for diagram creating efficiency)
public class EuropeanTextViewTest {
  private MarbleSolitaireModelState defaultModel;
  private MarbleSolitaireTextView dModelView;
  private MarbleSolitaireModelState thickModel;
  private MarbleSolitaireTextView tModelView;
  private String defaultView;
  private String thickView;

  @Before
  public void setUp() {
    defaultModel = new EuropeanSolitaireModel();
    dModelView = new EuropeanSolitaireTextView(defaultModel);

    thickModel = new EuropeanSolitaireModel(5);
    tModelView = new EuropeanSolitaireTextView(thickModel);
  }

  @Test
  public void testToString() {
    assertEquals("     O O O\n" +
            "   O O O O O\n" +
            " O O O O O O O\n" +
            " O O O _ O O O\n" +
            " O O O O O O O\n" +
            "   O O O O O\n" +
            "     O O O", dModelView.toString());

    assertEquals("         O O O O O\n" +
            "       O O O O O O O\n" +
            "     O O O O O O O O O\n" +
            "   O O O O O O O O O O O\n" +
            " O O O O O O O O O O O O O\n" +
            " O O O O O O O O O O O O O\n" +
            " O O O O O O _ O O O O O O\n" +
            " O O O O O O O O O O O O O\n" +
            " O O O O O O O O O O O O O\n" +
            "   O O O O O O O O O O O\n" +
            "     O O O O O O O O O\n" +
            "       O O O O O O O\n" +
            "         O O O O O", tModelView.toString());
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