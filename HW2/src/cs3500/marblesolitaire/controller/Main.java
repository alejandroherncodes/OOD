package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import java.io.InputStreamReader;

/**
 * This class is utilized as a test instance of the marbles game
 */
public class Main {
    /**
     * Checks game structure using the console
     *
     * @param args     inputs to continue or start the game
     */
    public static void main(String[] args) {
        // Create the arguments
        MarbleSolitaireControllerImpl controller;
        // and Parameters
        MarbleSolitaireModel model;
        MarbleSolitaireView view;
        Readable readable;

        // Test the structure to see if it compiles
        model = new EnglishSolitaireModel();
        view = new MarbleSolitaireTextView(model);
        readable = new InputStreamReader(System.in);

        // Assemble it all
        controller = new MarbleSolitaireControllerImpl(model, view, readable);

        // Compiles!
        controller.playGame();
    }
}