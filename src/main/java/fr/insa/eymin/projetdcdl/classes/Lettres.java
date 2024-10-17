package fr.insa.eymin.projetdcdl.classes;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Lettres {
    public static void debut() {
        // Get the dimensions of the screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Player 1 stage
        Stage p1Stage = new Stage();
        VBox p1VBox = new VBox(new Label("Player 1"));
        Scene p1Scene = new Scene(p1VBox);
        p1Stage.setTitle("Player 1");
        p1Stage.setScene(p1Scene);
        p1Stage.setX(0); // Set the X position
        p1Stage.setY(0); // Set the Y position
        p1Stage.setWidth(screenBounds.getWidth() / 2); // Set the width to half the screen width
        p1Stage.setHeight(screenBounds.getHeight()); // Set the height to the screen height
        p1Stage.show();
    }
}
