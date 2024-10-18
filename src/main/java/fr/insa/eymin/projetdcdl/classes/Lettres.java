package fr.insa.eymin.projetdcdl.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;

public class Lettres {
    // ==============================================================================================================
    // Méthode qui gère le déroulement du jeu de lettres
    public static void jeuLettres() {

        // ----------------------------------------------------------------------------------------------------------
        // Choix du nombre de voyelles
        int nbVoyelles = choixNbVoyelles();
        int nbConsonnes = 10 - nbVoyelles;
        System.out.println("Nombre de voyelles : " + nbVoyelles);

        // ----------------------------------------------------------------------------------------------------------
        // Génération des 10 lettres
        ArrayList<Character> lettres = new ArrayList<Character>();

        // Génération des n voyelles
        char[] voyelles = { 'A', 'E', 'I', 'O', 'U', 'Y' };
        for (int i = 0; i < nbVoyelles; i++) {
            int randomIndex = (int) (Math.random() * voyelles.length);
            lettres.add(voyelles[randomIndex]);
        }

        // Génération des 10-n consonnes
        char[] consonnes = { 'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W',
                'X', 'Z' };
        for (int i = 0; i < nbConsonnes; i++) {
            int randomIndex = (int) (Math.random() * consonnes.length);
            lettres.add(consonnes[randomIndex]);
        }

        // Mélange des lettres
        Collections.shuffle(lettres);
        System.out.println("Lettres : " + lettres);

        // ----------------------------------------------------------------------------------------------------------
        // Obtenir les dimensions de l'écran
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Player 1 stage
        Stage p1Stage = new Stage();
        GridPane p1GridPane = new GridPane();
        p1GridPane.setHgap(10);
        p1GridPane.setVgap(10);
        p1GridPane.setPadding(new Insets(10));

        // ----------------------------------------------------------------------------------------------------------
        // Affichage des 10 lettres
        p1GridPane.add(new Label("Les 10 lettres :"), 0, 0);
        String lettresString = "";
        for (char i : lettres) {
            lettresString += i + "  ";
        }
        Label lettresLabel = new Label(lettresString);
        lettresLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        p1GridPane.add(lettresLabel, 0, 1);

        // ----------------------------------------------------------------------------------------------------------
        // Affichage de la zone de saisie
        TextField saisie = new TextField();
        saisie.setPromptText("Saisir un mot");
        p1GridPane.add(saisie, 0, 2);

        Button validerButton = new Button("Valider");
        p1GridPane.add(validerButton, 0, 3);

        // ----------------------------------------------------------------------------------------------------------
        // Affichage de la zone de jeu
        Scene p1Scene = new Scene(p1GridPane);
        p1Stage.setTitle("Joueur 1");
        p1Stage.setScene(p1Scene);
        p1Scene.getStylesheets().add(ChoixMode.class.getResource("styles.css").toExternalForm());
        p1Stage.setX(0); // Définir la position X
        p1Stage.setY(0); // Définir la position Y
        p1Stage.setWidth(screenBounds.getWidth() / 2); // Définir la largeur à la moitié de la largeur de l'écran
        p1Stage.setHeight(screenBounds.getHeight()); // Définir la hauteur à la hauteur de l'écran
        p1Stage.show();
    }

    // ==============================================================================================================
    // Méthode qui donne le choix du nombre de voyelles au joueur
    public static int choixNbVoyelles() {
        Label message = new Label("Choissisez le nombre de voyelles :\n(entre 0 et 10)");
        message.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        TextField nbVoyelles = new TextField();
        nbVoyelles.setPrefColumnCount(2);

        HBox entreeNb = new HBox(message, nbVoyelles);
        entreeNb.setPadding(new Insets(10));

        Button validerButton = new Button("Valider");
        CompletableFuture<Integer> future = new CompletableFuture<>();
        validerButton.setOnAction(evt -> {
            int nbVoyellesInt = Integer.parseInt(nbVoyelles.getText());
            if (nbVoyellesInt > 10) {
                System.out.println("Nombre de voyelles >10 ERREUR");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Nombre de voyelles maximum : 10");

                ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                alert.getButtonTypes().setAll(buttonTypeOk);

                Label content = (Label) alert.getDialogPane().lookup(".content");
                content.setFont(new Font(15));

                alert.showAndWait();
                nbVoyelles.clear();
            } else if (nbVoyellesInt < 0) {
                System.out.println("Nombre de voyelles <0 ERREUR");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Nombre de voyelles minimum : 0");

                ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                alert.getButtonTypes().setAll(buttonTypeOk);

                Label content = (Label) alert.getDialogPane().lookup(".content");
                content.setFont(new Font(15));

                alert.showAndWait();
                nbVoyelles.clear();
            } else {
                future.complete(nbVoyellesInt);
                Window window = validerButton.getScene().getWindow();
                window.hide();
            }
        });

        HBox validerBox = new HBox(validerButton);
        validerBox.setAlignment(Pos.CENTER);

        BorderPane choixVoyellesPane = new BorderPane();
        choixVoyellesPane.setPadding(new Insets(10));
        choixVoyellesPane.setCenter(entreeNb);
        choixVoyellesPane.setBottom(validerBox);

        Scene choixVoyellesScene = new Scene(choixVoyellesPane);
        choixVoyellesScene.getStylesheets().add(ChoixMode.class.getResource("styles.css").toExternalForm());
        Stage choixVoyellesStage = new Stage();
        choixVoyellesStage.setTitle("Choix du nombre de voyelles");
        choixVoyellesStage.setScene(choixVoyellesScene);
        choixVoyellesStage.showAndWait();
        return future.join();
    }

    // ==============================================================================================================
}
