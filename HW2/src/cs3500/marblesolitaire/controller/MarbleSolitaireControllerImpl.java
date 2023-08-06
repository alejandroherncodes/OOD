package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements a controller to the marbles game.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
    MarbleSolitaireModel model;
    MarbleSolitaireView view;
    Scanner scanner;
    boolean quit;
    int[] currentMove;

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
        this.scanner = new Scanner(inputReader);
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
        while (!(model.isGameOver() || quit)) {

            // Displays this board if possible, exception if not
            try {
                this.view.renderBoard();
            } catch (IOException e) {
                throw new IllegalStateException();
            }

            // Displays this score below the board if possible, exception if not
            try {
                this.view.renderMessage("Score: " + model.getScore());
            } catch (IOException e) {
                throw new IllegalStateException();
            }

            // Asks a user to enter another move if possible, exception if not
            try {
                this.view.renderMessage("Enter move: ");
            } catch (IOException e) {
                throw new IllegalStateException();
            }

            // Validate user inputs for move
            for (int i = 0; i < this.currentMove.length && !quit; i++) {

                // Track user entry as a string to scan
                String given = scanner.next();

                // Is the game still valid? Checks if the user quit the game
                if (given.equalsIgnoreCase("q")) {
                    this.quit = true;
                } else if (this.isValidEntry(given) // Determining if input is a valid entry
                        && Integer.parseInt(given) > 0) {
                    // Scale the user input to match the board values
                    this.currentMove[i] = Integer.parseInt(given) - 1;
                } else if (i >= 2) {  // If entered 'to' coordinate is an invalid entry, try again
                    i = 1;
                    try {
                        this.view.renderMessage("Invalid input. Try different 'to' values:");
                    } catch (IOException e) {
                        throw new IllegalStateException();
                    }
                } else { // If entered 'from' coordinate is an invalid entry, try again
                    i = -1;
                    try {
                        this.view.renderMessage("Invalid input. Try different 'from' values:");
                    } catch (IOException e) {
                        throw new IllegalStateException();
                    }
                }

                // When game isn't over and the moves are given, handle the move
                if (!quit && i == 3) {
                    try {
                        this.model.move(currentMove[0], currentMove[1],
                                currentMove[2], currentMove[3]);
                    }
                    catch (IllegalArgumentException e) { // If invalid move was sent here.
                        try {
                            i = -1;
                            view.renderMessage("Invalid move. Play again. " + e.getMessage());
                        } catch (IOException io) {
                            throw new IllegalStateException();
                        }
                    }
                }
            }
        }

        // Creates a log for a quit game
        if (quit) {
            try { // Displays the game quit message
                this.view.renderMessage("Game quit!\nGame left at:\n");
            } catch (IOException e) {
                throw new IllegalStateException();
            } try { // Displays the final board
                this.view.renderBoard();
            } catch (IOException e) {
                throw new IllegalStateException();
            } try { // Displays the final score
                this.view.renderMessage("Score: " + model.getScore());
            } catch (IOException e) {
                throw new IllegalStateException();
            }
        }
        
        // Creates a log for a won game
        else {
            try { // Displays game over text
                this.view.renderMessage("Game over!");
            } catch (IOException e) {
                throw new IllegalStateException();
            } try { // Displays the ending/empty board
                this.view.renderBoard();
            } catch (IOException e) {
                throw new IllegalStateException();
            } try { // Displays the final score
                this.view.renderMessage("Score: " + model.getScore());
            } catch (IOException e) {
                throw new IllegalStateException();
            }
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