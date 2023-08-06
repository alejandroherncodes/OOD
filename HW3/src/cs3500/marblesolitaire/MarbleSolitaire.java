package cs3500.marblesolitaire;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.view.hw02.MarbleSolitaireTextView;

/** Creates an instance of the EnglishSolitaireModel class, which represents
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
public final class MarbleSolitaire {
  public static void main(String[] args) {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            new InputStreamReader(System.in));

    // Running playGame
    controller.playGame();
  }
}