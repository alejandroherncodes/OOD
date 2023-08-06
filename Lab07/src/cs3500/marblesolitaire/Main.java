package cs3500.marblesolitaire;

import cs3500.marblesolitaire.controller.ControllerFeatures;
import cs3500.marblesolitaire.controller.SwingGuiController;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;
import cs3500.marblesolitaire.view.SwingGuiView;

public class Main {
    public static void main(String[] args) {
        MarbleSolitaireModel gameModel = new EnglishSolitaireModel();
        MarbleSolitaireGuiView guiView = new SwingGuiView(gameModel);
        ControllerFeatures gameController = new SwingGuiController(gameModel, guiView);
    }
}