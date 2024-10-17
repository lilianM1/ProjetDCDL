/*
 * Projet Informatique S2, Devis Bâtiment
 * Auteurs : Lilian Eymin, Simon Fontaine
 * INSA Strasbourg, 2024
 */

package fr.insa.eymin.projetdcdl;

import fr.insa.eymin.projetdcdl.classes.*;
import javafx.application.Application;
import javafx.stage.Stage;

// Classe principale de l'application
public class App extends Application {

    // Méthode appelée au démarrage de l'application
    @Override
    public void start(Stage stage) {
        // Appelle la méthode début de la classe Gui
        ChoixMode.debut();
    }

    // Méthode principale
    public static void main(String[] args) {
        // Lance l'application
        launch();
    }

}