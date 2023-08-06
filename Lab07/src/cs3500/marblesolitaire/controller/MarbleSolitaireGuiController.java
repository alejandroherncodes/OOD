package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;

public class MarbleSolitaireGuiController implements ControllerFeatures {
  private MarbleSolitaireModel model;
  private MarbleSolitaireGuiView view;
  private boolean isFirstClick;
  private int fromRow;
  private int fromCol;

  /**
   * Initializes a MarbleSolitaireGuiController
   *
   * @param model    the marble solitaire model
   * @param view     the marble solitaire view
   */
  public MarbleSolitaireGuiController(MarbleSolitaireModel model, MarbleSolitaireGuiView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("No null inputs");
    }
    this.model = model;
    this.view = view;
    this.isFirstClick = true;
    this.view.implementFeatures(this);
  }

  /**
   * Tries a move in Marble Solitaire
   *
   * @param row           row of destination
   * @param col        column of destination
   */
  @Override
  public void go(int row, int col) {
    if (this.model.isGameOver()) {
      view.renderMessage("Game over");
    } else {

      if (isFirstClick) {
        view.renderMessage("Selected: "
                + row + ", " + col);
        this.fromRow = row;
        this.fromCol = col;
        this.isFirstClick = false;
      }
      else {
        try {
          this.model.move(fromRow, fromCol, row, col);
          view.refresh();
          this.isFirstClick = true;
          view.renderMessage("Move Completed (" + fromRow
                  + ", " + fromCol + ") to (" + row + ", " + col + ")");
        }
        catch (IllegalArgumentException e) {
          view.renderMessage(e.getMessage());
          this.isFirstClick = true;
        }
      }
    }
  }
}