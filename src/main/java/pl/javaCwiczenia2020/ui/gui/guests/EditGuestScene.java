package pl.javaCwiczenia2020.ui.gui.guests;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.javaCwiczenia2020.domain.ObjectPool;
import pl.javaCwiczenia2020.domain.guest.Gender;
import pl.javaCwiczenia2020.domain.guest.GuestService;
import pl.javaCwiczenia2020.domain.guest.dto.GuestDTO;
import pl.javaCwiczenia2020.domain.util.SystemUtils;

import java.util.List;

public class EditGuestScene {

    private Scene mainScene;
    private GuestService guestService = ObjectPool.getGuestService();

    public EditGuestScene(Stage stage, TableView<GuestDTO> tableView, GuestDTO value) {

        Label firstNameLabel = new Label("Imię:");
        Label lastNameLabel = new Label("Nazwisko:");
        Label ageLabel = new Label("Wiek:");
        Label genderLabel = new Label("Płeć:");
        VBox labelBox = new VBox(firstNameLabel, lastNameLabel, ageLabel, genderLabel);
        labelBox.setSpacing(12);

        TextField firstNameTextField = new TextField();
        firstNameTextField.setText(String.valueOf(value.getFirstName()));
        firstNameTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if(!newValue.matches("\\p{L}*")) {
                firstNameTextField.setText(oldValue);
            }
        });
        TextField lastNameTextField = new TextField();
        lastNameTextField.setText(String.valueOf(value.getLastName()));
        lastNameTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if(!newValue.matches("\\p{L}*")) {
                lastNameTextField.setText(oldValue);
            }
        });

        TextField ageTextFiels = new TextField();
        ageTextFiels.setText(String.valueOf(value.getAge()));
        ageTextFiels.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if(!newValue.matches("\\d*")) {
                ageTextFiels.setText(oldValue);
            }
        });

        ComboBox<String> genderComboBox = new ComboBox<>();
        genderComboBox.getItems().addAll(SystemUtils.FEMALE, SystemUtils.MALE);
        genderComboBox.setValue(value.getGender());
        VBox textFieldBox = new VBox(firstNameTextField, lastNameTextField, ageTextFiels, genderComboBox);

        Button editGuestButton = new Button("Edytuj gościa");
        editGuestButton.setOnAction(ActionEvent -> {
            int age = Integer.parseInt(ageTextFiels.getText());
            Gender gender = guestService.getGender(genderComboBox.getValue().equals("Żeńska"));
            guestService.edit(value.getId(), firstNameTextField.getText(), lastNameTextField.getText(), age, gender);

            tableView.getItems().clear();
            List<GuestDTO> guestDTOList = guestService.getGuestDTOList();
            tableView.getItems().addAll(guestDTOList);
            stage.close();
        });

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.add(labelBox, 0, 0);
        layout.add(textFieldBox, 1, 0);
        layout.add(editGuestButton, 0, 3);


        this.mainScene = new Scene(layout, 640, 480);
        this.mainScene.getStylesheets().add(getClass().getClassLoader().getResource("HotelReservations.css").toExternalForm());


    }


    public Scene getMainScene() {
        return this.mainScene;
    }
}
