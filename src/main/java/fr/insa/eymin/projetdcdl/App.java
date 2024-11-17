/*
 * INSA Strasbourg, Septembre 2024
 * Projet Informatique S1, Des Chiffres et des Lettres
 * Auteur : Lilian Eymin
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
        // Appelle la méthode début de la classe choix
        ChoixMode.debut();
    }

    // Méthode principale
    public static void main(String[] args) {
        // Lance l'application
        launch();
    }

}