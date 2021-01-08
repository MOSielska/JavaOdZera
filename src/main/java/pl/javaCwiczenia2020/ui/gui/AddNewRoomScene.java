package pl.javaCwiczenia2020.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.javaCwiczenia2020.domain.ObjectPool;
import pl.javaCwiczenia2020.domain.room.RoomService;
import pl.javaCwiczenia2020.domain.room.dto.RoomDTO;
import pl.javaCwiczenia2020.domain.util.Properties;

import java.util.ArrayList;
import java.util.List;

public class AddNewRoomScene {

    private Scene mainScene;
    private List<ComboBox<String>> comboBoxes = new ArrayList<>();
    private RoomService roomService = ObjectPool.getRoomService();

    public AddNewRoomScene(Stage stage, TableView<RoomDTO> tableView){

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        Label roomNumberLabel = new Label("Numer pokoju:");
        Label bedTypeLabel = new Label("Typy łóżek:");

        TextField roomNumberTextField = new TextField();
        roomNumberTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if(!newValue.matches("\\d*")) {
                roomNumberTextField.setText(oldValue);
            }
        });

        gridPane.add(roomNumberLabel, 0, 0);
        gridPane.add(roomNumberTextField, 1, 0);

        Button addNewBedButton = new Button();
        Image icon = new Image(getClass().getClassLoader().getResourceAsStream("add"));
        ImageView imageView = new ImageView(icon);
        imageView.setFitWidth(32);
        imageView.setFitHeight(16);
        addNewBedButton.setGraphic(imageView);
        addNewBedButton.setPadding(Insets.EMPTY);
        Button addNewRoomButton = new Button("Dodaj nowy pokój");
        addNewRoomButton.setPadding(new Insets(5, 5, 5, 5));
        addNewRoomButton.setOnAction(ActionEvent -> {

            List<String> bedTypesList = new ArrayList<>();
            int newRoomNumber = Integer.parseInt(roomNumberTextField.getText());
            this.comboBoxes.forEach(comboBox -> {
                bedTypesList.add(comboBox.getValue());
            });

            roomService.createNewRoom(newRoomNumber, bedTypesList);
            tableView.getItems().clear();
            List<RoomDTO> roomDTOList = roomService.getRoomDTOList();
            tableView.getItems().addAll(roomDTOList);
            stage.close();

        });

        gridPane.add(bedTypeLabel, 0, 1);
        gridPane.add(addNewBedButton, 1, 1);
        gridPane.add(addNewRoomButton, 0, 3);

        VBox bedsLayout = new VBox(getComboBox());

        addNewBedButton.setOnAction(ActionEvent -> {
            bedsLayout.getChildren().add(getComboBox());
        });

        gridPane.add(bedsLayout, 1, 2);


        this.mainScene = new Scene(gridPane, 640, 480);
        this.mainScene.getStylesheets().add(getClass().getClassLoader().getResource("HotelReservations.css").toExternalForm());

    }

    private ComboBox<String> getComboBox() {
        ComboBox<String> bedTypeField = new ComboBox();
        bedTypeField.getItems().addAll(Properties.SINGLE_BED, Properties.DOBBLE_BED, Properties.KING_SIZE_BED);
        bedTypeField.setValue("SINGLE");
        this.comboBoxes.add(bedTypeField);
        return bedTypeField;
    }

    public Scene getMainScene() {
        return mainScene;
    }
}
