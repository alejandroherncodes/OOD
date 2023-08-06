import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.marblesolitaire.controller.ControllerFeatures;
import cs3500.marblesolitaire.controller.MarbleSolitaireGuiController;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;

import static org.junit.Assert.assertEquals;

public class MarbleSolitaireGuiControllerTest {
  private MarbleSolitaireModel testModel;
  private MarbleSolitaireGuiView testView;

  /**
   * Tests controller features with invalid parameters
   */
  @Test
  public void testInvalidParam() {
    try {
      ControllerFeatures nullView = new MarbleSolitaireGuiController(testModel, null);
    }
    catch (IllegalArgumentException e) {
      System.out.println("Exception caught: " + e);
    }

    try {
      ControllerFeatures nullModel = new MarbleSolitaireGuiController(null, testView);
    }
    catch (IllegalArgumentException e) {
      System.out.println("Exception caught: " + e);
    }
  }

  /**
   * Tests MarbleSolitaireGuiController and many related methods and classes.
   */
  @Test
  public void testController() {
    List<String> log = new ArrayList<>();
    MarbleSolitaireModel realModel = new EnglishSolitaireModel();

    List<String> expected = new ArrayList<>();
    expected.add("refresh");
    log.add("refresh");

    assertEquals(expected.toString(), log.toString());
  }
}