package pl.javaCwiczenia2020.ui.gui.rooms;

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
import pl.javaCwiczenia2020.domain.util.SystemUtils;

import java.util.ArrayList;
import java.util.List;

public class EditRoomScene {

    private Scene mainScene;
    private List<ComboBox<String>> comboBoxes = new ArrayList<>();
    private RoomService roomService = ObjectPool.getRoomService();

    public EditRoomScene(Stage stage, TableView<RoomDTO> tableView, RoomDTO value){

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        Label roomNumberLabel = new Label("Numer pokoju:");
        Label bedTypeLabel = new Label("Typy łóżek:");

        TextField roomNumberTextField = new TextField();
        roomNumberTextField.setText(String.valueOf(value.getNumber()));
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
        Button editRoomButton = new Button("Edytuj pokój");
        editRoomButton.setPadding(new Insets(5, 5, 5, 5));

        int bedsCount = value.getBedCount();
        String[] split = value.getBeds().split(",");
        VBox bedsLayout = new VBox();
        for(int i = 0; i<bedsCount; i++) {
            ComboBox<String> combo = getComboBox();
            combo.setValue(split[i]);
            bedsLayout.getChildren().add(combo);
        }

        addNewBedButton.setOnAction(ActionEvent -> {
            bedsLayout.getChildren().add(getComboBox());
        });

        editRoomButton.setOnAction(ActionEvent -> {

            List<String> bedTypesList = new ArrayList<>();
            int newRoomNumber = Integer.parseInt(roomNumberTextField.getText());
            this.comboBoxes.forEach(comboBox -> {
                bedTypesList.add(comboBox.getValue());
            });

            //long id = value.getId();
            roomService.edit(value.getId(), newRoomNumber, bedTypesList);
            tableView.getItems().clear();
            List<RoomDTO> roomDTOList = roomService.getRoomDTOList();
            tableView.getItems().addAll(roomDTOList);
            stage.close();
        });

        gridPane.add(bedTypeLabel, 0, 1);
        gridPane.add(addNewBedButton, 1, 1);
        gridPane.add(editRoomButton, 0, 3);

        gridPane.add(bedsLayout, 1, 2);

        this.mainScene = new Scene(gridPane, 640, 480);
        this.mainScene.getStylesheets().add(getClass().getClassLoader().getResource("HotelReservations.css").toExternalForm());
    }

    private ComboBox<String> getComboBox() {
        ComboBox<String> bedTypeField = new ComboBox();
        bedTypeField.getItems().addAll(SystemUtils.SINGLE_BED, SystemUtils.DOBBLE_BED, SystemUtils.KING_SIZE_BED);
        bedTypeField.setValue(SystemUtils.SINGLE_BED);
        this.comboBoxes.add(bedTypeField);
        return bedTypeField;
    }

    public Scene getMainScene() {
        return mainScene;
    }
}
