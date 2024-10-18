package fr.insa.eymin.projetdcdl.classes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.*;

public class ChoixMode {
    public static void debut() {

        Stage choixStage = new Stage();

        Label message = new Label("Choissisez votre mode de jeu");
        message.setStyle("-fx-font-weight: bold;");
        message.setFont(new Font(30));

        Button validerButton = new Button("Démarrer");
        validerButton.setDisable(true);
        validerButton.setOnAction(evt -> {
            System.out.println("Valider : " + message.getText());
            if (message.getText().equals("Mode Lettres")) {
                choixStage.close();
                Lettres.jeuLettres();
            } else if (message.getText().equals("Mode Chiffres")) {
                choixStage.close();
                Chiffres.debut();
            }
        });

        Button lettresButton = new Button("Mode Lettres");
        lettresButton.setOnAction(evt -> {
            message.setText("Mode Lettres");
            System.out.println(message.getText());
            validerButton.setDisable(false);
        });

        Button chiffresButton = new Button("Mode Chiffres");
        chiffresButton.setOnAction(evt -> {
            message.setText("Mode Chiffres");
            System.out.println(message.getText());
            validerButton.setDisable(true); // TODO
        });

        HBox buttonChoixBar = new HBox(10, chiffresButton, lettresButton);
        buttonChoixBar.setAlignment(Pos.CENTER);
        HBox buttonValiderBar = new HBox(10, validerButton);
        buttonValiderBar.setAlignment(Pos.CENTER);

        VBox buttonBar = new VBox(10, buttonChoixBar, buttonValiderBar);
        buttonBar.setPadding(new Insets(10));

        BorderPane choixPane = new BorderPane();
        choixPane.setCenter(message);
        choixPane.setBottom(buttonBar);

        Scene choixScene = new Scene(choixPane, 450, 200);
        choixScene.getStylesheets().add(ChoixMode.class.getResource("/fr/insa/eymin/projetdcdl/classes/styles.css").toExternalForm());
        choixStage.setScene(choixScene);
        choixStage.setTitle("Choix du mode de jeu");
        choixStage.show();

    }
}
