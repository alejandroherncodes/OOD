package cs3500.marblesolitaire.controller;

/**
 * Represents a controller for the marble solitaire game                 
 */
public interface MarbleSolitaireController {
    /**
     * Plays the marbles game and generates a printable log tracking game steps
     *
     * @throws IllegalStateException    when the game cannot be rendered
     */
    void playGame() throws IllegalStateException;
}