package cs3500.marblesolitaire.controller;

/**
 * Represents an Interface housing all possible controller features
 */
public interface ControllerFeatures {

  /**
   * Tries a move in Marble Solitaire
   *
   * @param row           row of destination
   * @param col        column of destination
   */
  void go(int row, int col);
}