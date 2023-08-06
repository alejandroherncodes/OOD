import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

// Test the text view class using the two smallest, valid
// odd numbers according to the traditional game (for diagram creating efficiency)
public class TextViewTest {
  private MarbleSolitaireModelState defaultModel;
  private MarbleSolitaireTextView dModelView;
  private MarbleSolitaireModelState thickModel;
  private MarbleSolitaireTextView tModelView;
  private String defaultView;
  private String thickView;

  @Before
  public void setUp() {
    defaultModel = new EnglishSolitaireModel();
    thickModel = new EnglishSolitaireModel(5);

    dModelView = new MarbleSolitaireTextView(defaultModel);
    tModelView = new MarbleSolitaireTextView(thickModel);

    defaultView
            = "    " + "0 0 0" + "   \n"
            + "    " + "0 0 0" + "   \n"
            + "0 0 0 0 0 0 0 \n"
            + "0 0 0 - 0 0 0 \n"
            + "0 0 0 0 0 0 0 \n"
            + "    " + "0 0 0" + "   \n"
            + "    " + "0 0 0" + "   \n";

    thickView
            = "        " + "0 0 0 0 0   \n"
            + "        " + "0 0 0 0 0   \n"
            + "        " + "0 0 0 0 0   \n"
            + "        " + "0 0 0 0 0   \n"
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 - 0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 \n"
            + "        " + "0 0 0 0 0   \n"
            + "        " + "0 0 0 0 0   \n"
            + "        " + "0 0 0 0 0   \n"
            + "        " + "0 0 0 0 0   \n";
  }

  // Test the method with the default arm thickness, 3.
  @Test
  public void testTraditionalModel() {
    assertEquals(defaultView, dModelView.toString());
  }

  // Test the method with the next positive, odd number after the previous armThickness.
  @Test public void testArmThickness5Model() {
    assertEquals(thickView, tModelView.toString());
  }
}
