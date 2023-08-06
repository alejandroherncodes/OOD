package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.controller.ControllerFeatures;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;

/**
 * Represents a Gui Controller
 */
public class SwingGuiController implements ControllerFeatures {

  private final MarbleSolitaireModel model;
  private final MarbleSolitaireGuiView view;
  private boolean isFrom;
  private int fromCellRow;
  private int fromCellCol;

  /**
   * Creates a Solitaire Controller
   *
   * @param model the marble solitaire model
   * @param view  the marble solitaire view
   * @throws IllegalArgumentException
   */
  public SwingGuiController(MarbleSolitaireModel model, MarbleSolitaireGuiView view)
          throws IllegalArgumentException {
    if (model == null || view == null) {
      throw new IllegalArgumentException("The model or view cannot be null");
    }
    this.model = model;
    this.view = view;
    this.isFrom = true;
    this.view.implementFeatures(this);
  }

  /**
   * Attempts to make a move in English Solitaire
   *
   * @param row row of selected cell
   * @param col column of selected cell
   */
  @Override
  public void go(int row, int col) {
    if (this.model.isGameOver()) {
      view.renderMessage("Game over!");
    } else if (isFrom) {
      view.renderMessage("Clicked: " + row + ", " + col);
      this.fromCellRow = row;
      this.fromCellCol = col;
      this.isFrom = false;
    } else {
      try {
        this.model.move(fromCellRow, fromCellCol, row, col);
        view.refresh();
        this.isFrom = true;
      } catch (IllegalArgumentException e) {
        view.renderMessage(e.getMessage());
        this.isFrom = true;
      }
    }
  }
}