package pl.javaCwiczenia2020.ui.gui.reservations;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ErrorScene {

    private Scene scene;

    public ErrorScene(Stage stage) {

        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(20));


        Label messageLabel = new Label("Niepoprawne daty rezerwacji");

        Button okButton = new Button("OK");
        okButton.setPadding(new Insets(5, 20, 5, 20));
        okButton.setOnAction(ActionEvent -> {
            stage.close();
        });

        layout.setTop(messageLabel);
        layout.setCenter(okButton);

        this.scene = new Scene(layout, 230, 100);
        this.scene.getStylesheets().add(getClass().getClassLoader().getResource("HotelReservations.css").toExternalForm());
    }

    public Scene getScene() {
        return this.scene;
    }
}
