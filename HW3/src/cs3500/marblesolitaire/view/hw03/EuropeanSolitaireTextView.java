package cs3500.marblesolitaire.view.hw03;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.hw02.MarbleSolitaireTextView;

/**
 * Represents a view for a European Solitaire game
 */
public class EuropeanSolitaireTextView extends MarbleSolitaireTextView {

  /**
   * Constructs a text view of a European Marble Solitaire game
   *
   * @param gameBoard  the game model of the game to draw must be a valid MarbleSolitaireModelState
   * @param output       the appendable object to link the visualizations to update
   * @throws IllegalArgumentException    if given a null parameters: board or appendable
   */
  public EuropeanSolitaireTextView(MarbleSolitaireModelState gameBoard, Appendable output) {
    super(gameBoard, output);
  }

  /**
   * Constructs a text view of a European Marble Solitaire game
   *
   * @param gameBoard  the game model of the game to draw must be a valid MarbleSolitaireModelState
   * @throws IllegalArgumentException    if given a null parameters: board or appendable
   */
  public EuropeanSolitaireTextView(MarbleSolitaireModelState gameBoard)
          throws IllegalArgumentException {
    this(gameBoard, System.out);
  }
}