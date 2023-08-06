package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.controller.ControllerFeatures;
import cs3500.marblesolitaire.controller.IBoardPanel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoardPanel extends JPanel implements IBoardPanel {
  private MarbleSolitaireModelState modelState;
  private Image emptySlot, marbleSlot, blankSlot;
  private final int cellDimension;
  private int originX, originY;
  private ControllerFeatures features;

  /**
   * Initializes a BoardPanel
   *
   * @param state                   the current game state
   * @throws IllegalStateException    if icons are missing
   */
  public BoardPanel(MarbleSolitaireModelState state) throws IllegalStateException {
    super();
    this.modelState = state;
    this.setBackground(Color.WHITE);
    this.cellDimension = 50;
    try {
      emptySlot = ImageIO.read(new FileInputStream("res/empty.png"));
      emptySlot = emptySlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      marbleSlot = ImageIO.read(new FileInputStream("res/marble.png"));
      marbleSlot = marbleSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      blankSlot = ImageIO.read(new FileInputStream("res/blank.png"));
      blankSlot = blankSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      this.setPreferredSize(
              new Dimension((this.modelState.getBoardSize() + 4) * cellDimension
                      , (this.modelState.getBoardSize() + 4) * cellDimension));
    } catch (IOException e) {
      throw new IllegalStateException("Icons not found!");
    }

  }

  /**
   * Overrides the paintComponent method of the parent
   * class to draw the marble solitaire board.
   *
   * @param g         the Graphics object used for painting
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;
    originX = (int) (this.getPreferredSize().getWidth() / 2
            - this.modelState.getBoardSize() * cellDimension / 2);
    originY = (int) (this.getPreferredSize().getHeight() / 2
            - this.modelState.getBoardSize() * cellDimension / 2);

    for (int i = 0; i < this.modelState.getBoardSize(); i++) {
      for (int j = 0; j < this.modelState.getBoardSize(); j++) {
        switch (this.modelState.getSlotAt(i, j)) {
          case Invalid:
            g.drawImage(blankSlot, originX + j * cellDimension,
                    originY + i * cellDimension, null);
            break;
          case Marble:
            g.drawImage(marbleSlot, originX + j * cellDimension,
                    originY + i * cellDimension, null);
            break;
          case Empty:
            g.drawImage(emptySlot, originX + j * cellDimension,
                    originY + i * cellDimension, null);
            break;
        }
      }
    }
  }

  @Override
  public void implementController(ControllerFeatures updateFeatures) {
    this.features = updateFeatures;
    MouseListener mouse = new MouseAdapter() {
      /**
       * Takes care of the clicked cell
       * @param e the clicked mouse
       */
      @Override
      public void mousePressed(MouseEvent e) {
        implementControllerHelper(e.getX(), e.getY());
      }
    };
    this.addMouseListener(mouse);
  }


  /**
   * Overrides the implementController method of the parent
   * class to associate the controller features and set up
   * the mouse listener for user interactions.
   *
   * @param features   the ControllerFeatures object to associate with this view
   */

  /**
   * Helper method for implementController
   *
   * @param pressedX         given x coordinate
   * @param pressedY         given y coordinate
   */
  private void implementControllerHelper(int pressedX, int pressedY) {
    int col = (pressedX / cellDimension - originX / cellDimension);
    int row = (pressedY / cellDimension - originY / cellDimension);
    BoardPanel.this.features.go(row, col);
  }
}