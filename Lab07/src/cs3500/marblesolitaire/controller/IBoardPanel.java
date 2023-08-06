package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.controller.ControllerFeatures;

/**
 * Represents an interface for BoardPanel
 */
public interface IBoardPanel {

  /**
   * Sends the ControllerFeature object into a board panel
   *
   * @param features       the Controller Features to be accepted
   */
  void implementController(ControllerFeatures features);
}