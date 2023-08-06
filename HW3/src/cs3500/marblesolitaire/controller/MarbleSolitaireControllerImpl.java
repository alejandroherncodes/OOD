package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.hw02.MarbleSolitaireView;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements a controller to the marbles game.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
    private MarbleSolitaireModel model;
    private MarbleSolitaireView view;
    private Scanner inputReader;
    private boolean quit;
    private int[] currentMove;

    /**
     * A constructor that generates a controller
     *
     * @param model         the game model
     * @param view          a view of the game
     * @param inputReader   readable used for evaluating input
     */
    public MarbleSolitaireControllerImpl(MarbleSolitaireModel model,
                                         MarbleSolitaireView view, Readable inputReader) {
        if (model == null || view == null || inputReader == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        this.model = model;
        this.view = view;
        this.inputReader = new Scanner(inputReader);
        this.currentMove = new int[4];
        this.quit = false;
    }

    /**
     * Plays the marbles game and generates a printable log tracking game steps
     *
     * @throws IllegalStateException    when the game cannot be rendered
     */
    @Override
    public void playGame() throws IllegalStateException {
        while (!model.isGameOver() && !quit) {
            displayBoardAndScore();
            promptMove();
            // Validate user inputs for move
            for (int i = 0; i < this.currentMove.length && !quit; i++) {
                processUserInput(i);
            }
        }
        // Creates a log for a quit game
        if (quit) {
            logQuitGame();
        } else { // Creates a log for a won game
            logWonGame();
        }
    }
    private void displayBoardAndScore() {
        try {
            this.view.renderBoard();
            this.view.renderMessage("Score: " + model.getScore());
        } catch (IOException e) {
            throw new IllegalStateException();
        }
    }
    private void promptMove() {
        try {
            this.view.renderMessage("Enter move: ");
        } catch (IOException e) {
            throw new IllegalStateException();
        }
    }
    private void processUserInput(int i) {
        String given = inputReader.next();
        if (given.equalsIgnoreCase("q")) {
            this.quit = true;
        } else if (isValidEntry(given) && Integer.parseInt(given) > 0) {
            this.currentMove[i] = Integer.parseInt(given) - 1;
        } else {
            retryInvalidInput(i);
        }
        if (!quit && i == 3) {
            tryMove();
        }
    }
    private void retryInvalidInput(int i) {
        try {
            if (i >= 2) {  // If entered 'to' coordinate is an invalid entry, try again
                i = 1;
                this.view.renderMessage("Invalid input. Try different 'to' values:");
            } else { // If entered 'from' coordinate is an invalid entry, try again
                i = -1;
                this.view.renderMessage("Invalid input. Try different 'from' values:");
            }
        } catch (IOException e) {
            throw new IllegalStateException();
        }
    }
    private void tryMove() {
        try {
            this.model.move(currentMove[0], currentMove[1],
                    currentMove[2], currentMove[3]);
        } catch (IllegalArgumentException e) { // If invalid move was sent here.
            try {
                view.renderMessage("Invalid move. Play again. " + e.getMessage());
            } catch (IOException io) {
                throw new IllegalStateException();
            }
        }
    }
    private void logQuitGame() {
        try {
            this.view.renderMessage("Game quit!" + "\nGame left at:");
            this.view.renderBoard();
            this.view.renderMessage("Score: " + model.getScore());
        } catch (IOException e) {
            throw new IllegalStateException();
        }
    }
    private void logWonGame() {
        try {
            this.view.renderMessage("Game over!");
            this.view.renderBoard();
            this.view.renderMessage("Score: " + model.getScore());
        } catch (IOException e) {
            throw new IllegalStateException();
        }
    }

    /**
     * Determines if a given string is an integer by parsing through the characters
     *
     * @return boolean          true if string is an integer, false if not
     */
    private boolean isValidEntry(String entry) {
        try {
            Integer.parseInt(entry);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Gets the game's output and returns it as a string
     *
     * @return String       the game's messages to the user
     */
    public String getLog() {
        return view.getLog();
    }
}