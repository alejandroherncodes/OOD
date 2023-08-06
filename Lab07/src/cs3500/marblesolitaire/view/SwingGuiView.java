package cs3500.marblesolitaire.view;

import java.awt.*;

import javax.swing.*;

import cs3500.marblesolitaire.controller.ControllerFeatures;
import cs3500.marblesolitaire.controller.IBoardPanel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class represents a GUI view that is implemented using Java
 * Swing.
 */
public class SwingGuiView extends JFrame implements MarbleSolitaireGuiView {

  //the custom panel on which the board will be drawn
  private IBoardPanel boardPanel;
  //the model state
  private MarbleSolitaireModelState modelState;
  //a label to display the score
  private JLabel scoreLabel;
  //a label to display any messages to the user
  private JLabel messageLabel;

  /**
   * Initializes SwingGuiView
   *
   * @param state MarbleSolitaireModelState that depicts view
   */
  public SwingGuiView(MarbleSolitaireModelState state) {
    super("Marble Solitaire");
    this.modelState = state;
    /* main frame uses a border layout. Read about it here:
     */
    this.setLayout(new BorderLayout());
    //initialize the custom board with the model state
    boardPanel = new BoardPanel(this.modelState);
    //add custom board to the center of the frame
    this.add((Component) boardPanel, BorderLayout.CENTER);
    //create the score label
    this.scoreLabel = new JLabel();
    //create the message label
    this.messageLabel = new JLabel();
    //put them both in a panel. This is done mostly to arrange them properly
    JPanel panel = new JPanel();
    /*
    the panel uses a grid layout with two columns. The gridlayout
    will stretch the labels so that they are exactly half of the width
    of this panel.
    Since we mention that we want a grid of 2 columns, and we
    add exactly two things to it, it will use one row.
     */

    panel.setLayout(new GridLayout(0, 2));
    panel.add(scoreLabel);
    panel.add(messageLabel);
    //add this panel to the bottom of the frame
    this.add(panel, BorderLayout.PAGE_END);
    pack();
    setVisible(true);
    this.refresh();

  }

  /**
   * Refresh the screen.
   * A refresh is invoked when the something on the screen
   * is sent an update, thus requiring a redraw.
   */
  public void refresh() {

    // Reset Score
    this.scoreLabel.setText("Score: " + modelState.getScore());

    // Repaint this frame, wiping everything added.
    this.repaint();
  }

  /**
   * Display a message in a suitable area of the GUI.
   *
   * @param message the message to be displayed
   */
  @Override
  public void renderMessage(String message) {
    this.messageLabel.setText(message);
  }

  /**
   * Sends the given ControllerFeature object to method
   *
   * @param features controller of model
   */
  @Override
  public void implementFeatures(ControllerFeatures features) {
    this.boardPanel.implementController(features);
  }
}
