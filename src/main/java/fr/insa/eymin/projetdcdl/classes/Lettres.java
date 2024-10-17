package fr.insa.eymin.projetdcdl.classes;

import java.util.ArrayList;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Lettres {
    // ==============================================================================================================
    // Méthode qui gère le déroulement du jeu de lettres
    public static void jeuLettres() {

        // ----------------------------------------------------------------------------------------------------------
        // Choix du nombre de voyelles
        choixNbVoyelles();

        // ----------------------------------------------------------------------------------------------------------

        char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        // Get the dimensions of the screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Player 1 stage
        Stage p1Stage = new Stage();
        GridPane p1GridPane = new GridPane();
        p1GridPane.setPadding(new Insets(10));

        // Génération des 10 lettres
        ArrayList<Character> lettres = new ArrayList<Character>();
        for (int i = 0; i < 10; i++) {
            lettres.add(alphabet[(int) (Math.random() * 26)]);
        }
        // ----------------------------------------------------------------------------------------------------------
        // Affichage des 10 lettres

        p1GridPane.add(new Label("Les 10 lettres :"), 0, 0);
        Label lettresLabel = new Label(lettres.toString());
        p1GridPane.add(lettresLabel, 0, 1);

        Scene p1Scene = new Scene(p1GridPane);
        p1Stage.setTitle("Player 1");
        p1Stage.setScene(p1Scene);
        p1Stage.setX(0); // Set the X position
        p1Stage.setY(0); // Set the Y position
        p1Stage.setWidth(screenBounds.getWidth() / 2); // Set the width to half the screen width
        p1Stage.setHeight(screenBounds.getHeight()); // Set the height to the screen height
        p1Stage.show();
    }

    // ==============================================================================================================
    // Méthode qui donne le choix du nombre de voyelles au joueur
    public static void choixNbVoyelles() {
        Label message = new Label("Choissisez le nombre de voyelles :");
        TextField nbVoyelles = new TextField();
        nbVoyelles.setPrefColumnCount(2);

        HBox entreeNb = new HBox(10, message, nbVoyelles);
        entreeNb.setPadding(new Insets(10));

        Button validerButton = new Button("Valider");
        validerButton.setOnAction(evt -> {
            System.out.println("Valider : " + nbVoyelles.getText());
            // TODO : Vérifier que le nombre de voyelles est bien inférieur à 10
        });
        HBox validerBox = new HBox(validerButton);
        validerBox.setAlignment(Pos.CENTER);

        BorderPane choixVoyellesPane = new BorderPane();
        choixVoyellesPane.setPadding(new Insets(10));
        choixVoyellesPane.setCenter(entreeNb);
        choixVoyellesPane.setBottom(validerBox);

        Stage choixVoyellesStage = new Stage();
        choixVoyellesStage.setTitle("Choix du nombre de voyelles");
        choixVoyellesStage.setScene(new Scene(choixVoyellesPane));
        choixVoyellesStage.showAndWait();
    }
}
