import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw03.TriangleSolitaireModel;
import java.io.InputStreamReader;

import cs3500.marblesolitaire.view.hw02.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.hw03.TriangleSolitaireTextView;

/** Creates an instance of the TriangleSolitaireModel class, which represents
 * the game's logic and state.
 *
 * It also creates an instance of the EnglishSolitaireTextView class,
 * which is responsible for displaying the game's current state to the user.
 *
 * A MarbleSolitaireControllerImpl object is then created, passing in the model, view, and an
 * InputStreamReader object to read user input from the console.
 *
 * The playGame method is called on the controller, which starts the game and allows the
 * user to play. */
public class TriangleMain {

  public static void main(String[] args) {
    TriangleSolitaireModel model = new TriangleSolitaireModel();
    MarbleSolitaireTextView view = new TriangleSolitaireTextView(model);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            new InputStreamReader(System.in));

    // Running playGame
    controller.playGame();
  }
}