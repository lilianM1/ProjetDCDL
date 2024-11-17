package fr.insa.eymin.projetdcdl.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
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
        // Génération des fenêtres de jeu
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Joueur 1 stage
        Stage p1Stage = fenetreP1(lettres, screenBounds);

        // Joueur 2 stage
        Stage p2Stage = fenetreP2(lettres, screenBounds);

        // Affichage de la zone de jeu
        p1Stage.show();
        p2Stage.show();
    }

    // ==============================================================================================================
    // Fenêtre joueur 1
    public static Stage fenetreP1(ArrayList<Character> lettres, Rectangle2D screenBounds) {
        Stage p1Stage = new Stage();
        p1Stage.setTitle("Joueur 1");
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
        TextField saisie1 = new TextField();
        saisie1.setPromptText("Saisir un mot");
        p1GridPane.add(saisie1, 0, 2);

        // ----------------------------------------------------------------------------------------------------------
        // Test de la validité du mot saisi
        saisie1.setOnAction(e -> {
            verification(saisie1, lettres);
        });
        Button validerButton1 = new Button("Valider");
        validerButton1.setOnAction(e -> {
            verification(saisie1, lettres);
        });
        p1GridPane.add(validerButton1, 0, 3);

        Scene p1Scene = new Scene(p1GridPane);
        p1Stage.setScene(p1Scene);
        p1Scene.getStylesheets().add(ChoixMode.class.getResource("styles.css").toExternalForm());
        p1Stage.setX(0); // Définir la position X
        p1Stage.setY(0); // Définir la position Y
        p1Stage.setWidth(screenBounds.getWidth() / 2); // Définir la largeur à la moitié de la largeur de l'écran
        p1Stage.setHeight(screenBounds.getHeight()); // Définir la hauteur à la hauteur de l'écran
        return p1Stage;
    }

    // ==============================================================================================================
    // Fenêtre joueur 2
    public static Stage fenetreP2(ArrayList<Character> lettres, Rectangle2D screenBounds) {
        Stage p2Stage = new Stage();
        p2Stage.setTitle("Joueur 2");
        GridPane p2GridPane = new GridPane();
        p2GridPane.setHgap(10);
        p2GridPane.setVgap(10);
        p2GridPane.setPadding(new Insets(10));

        // ----------------------------------------------------------------------------------------------------------
        // Affichage des 10 lettres
        p2GridPane.add(new Label("Les 10 lettres :"), 0, 0);
        String lettresString = "";
        for (char i : lettres) {
            lettresString += i + "  ";
        }
        Label lettresLabel2 = new Label(lettresString);
        lettresLabel2.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        p2GridPane.add(lettresLabel2, 0, 1);

        // ----------------------------------------------------------------------------------------------------------
        // Affichage de la zone de saisie
        TextField saisie2 = new TextField();
        saisie2.setPromptText("Saisir un mot");
        p2GridPane.add(saisie2, 0, 2);

        // ----------------------------------------------------------------------------------------------------------
        // Test de la validité du mot saisi
        saisie2.setOnAction(e -> {
            verification(saisie2, lettres);
        });
        Button validerButton2 = new Button("Valider");
        validerButton2.setOnAction(e -> {
            verification(saisie2, lettres);
        });
        p2GridPane.add(validerButton2, 0, 3);
        Scene p2Scene = new Scene(p2GridPane);
        p2Stage.setScene(p2Scene);
        p2Scene.getStylesheets().add(ChoixMode.class.getResource("styles.css").toExternalForm());
        p2Stage.setX(screenBounds.getWidth() / 2); // Définir la position X à la moitié de la largeur de l'écran
        p2Stage.setY(0); // Définir la position Y
        p2Stage.setWidth(screenBounds.getWidth() / 2); // Définir la largeur à la moitié de la largeur de l'écran
        p2Stage.setHeight(screenBounds.getHeight()); // Définir la hauteur à la hauteur de l'écran
        return p2Stage;
    }

    // ==============================================================================================================
    // Méthode qui vérifie si le mot saisi est valide
    public static void verification(TextField saisie, ArrayList<Character> lettres) {
        File dictionnaire = new File("src\\main\\ressources\\dictionnaire.txt");

        String mot = saisie.getText();
        System.out.println("Mot saisi : " + mot);

        // ----------------------------------------------------------------------------------------------------------
        // Verifier si le mot saisi peut etre formé avec les lettres
        ArrayList<Character> lettresTemp = new ArrayList<Character>(lettres);
        for (char c : mot.toCharArray()) {
            c = Character.toUpperCase(c);
            if (lettresTemp.contains(c)) {
                lettresTemp.remove((Character) c);
            } else {
                erreurMot("Le mot ne peut pas être formé avec les lettres", saisie);
                return;
            }
        }

        // ----------------------------------------------------------------------------------------------------------
        // Vérifier si le mot saisi est dans le dictionnaire
        try {
            Scanner scanner = new Scanner(dictionnaire);
            boolean found = false;
            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                if (ligne.equalsIgnoreCase(mot)) {
                    System.out.println(ligne + " : trouvé dans le dictionnaire");
                    found = true;
                    break;
                }

            }
            if (!found) {
                erreurMot("Le mot n'est pas dans le dictionnaire", saisie);
                scanner.close();
                return;
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + ex.getMessage());
        }
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
    public static void erreurMot(String erreur, TextField saisie) {
        System.out.println(erreur);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(erreur);

        ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(buttonTypeOk);

        Label content = (Label) alert.getDialogPane().lookup(".content");
        content.setFont(new Font(15));

        alert.showAndWait();
        saisie.clear();
    }

    // ==============================================================================================================
}
