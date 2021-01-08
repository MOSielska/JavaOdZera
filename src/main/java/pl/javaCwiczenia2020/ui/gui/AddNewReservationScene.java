package pl.javaCwiczenia2020.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.javaCwiczenia2020.domain.ObjectPool;
import pl.javaCwiczenia2020.domain.guest.GuestService;
import pl.javaCwiczenia2020.domain.reservation.ReservationService;
import pl.javaCwiczenia2020.domain.reservation.dto.ReservationDTO;
import pl.javaCwiczenia2020.domain.room.RoomService;

import java.util.ArrayList;
import java.util.List;


public class AddNewReservationScene {

    private Scene mainScene;
    private GuestService guestService = ObjectPool.getGuestService();
    private RoomService roomService = ObjectPool.getRoomService();
    private ReservationService reservationService = ObjectPool.getReservationService();

    public AddNewReservationScene(Stage primaryStage, TableView<ReservationDTO> tableView) {

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(20);
        layout.setVgap(10);

        Label dateFromLabel = new Label("Początek rezerwacji:");
        Label dateToLabel = new Label("Koniec rezerwacji:");
        Label guestLabel = new Label("Gość:");
        Label roomLabel = new Label("Pokój:");

        DatePicker dateFromField = new DatePicker();
        DatePicker dateToField = new DatePicker();

        layout.add(dateFromLabel, 0, 0);
        layout.add(dateFromField, 1, 0);
        layout.add(dateToLabel, 0, 1);
        layout.add(dateToField, 1, 1);
        layout.add(guestLabel, 0, 2);
        layout.add(roomLabel, 0, 3);

        List<GuestSelectionItem> guestSelectionItems = new ArrayList<>();
        guestService.getGuestDTOList().forEach(guestDTO -> {
            guestSelectionItems.add(new GuestSelectionItem(guestDTO.getId(), guestDTO.getFirstName(), guestDTO.getLastName()));
        });

        ComboBox<GuestSelectionItem>  guestField = new ComboBox<>();
        guestField.getItems().addAll(guestSelectionItems);

        List<RoomSelectionItem> roomSelectionItems = new ArrayList<>();
        roomService.getRoomDTOList().forEach(roomDTO -> {
            roomSelectionItems.add(new RoomSelectionItem(roomDTO.getId(), roomDTO.getNumber()));
        });

        ComboBox<RoomSelectionItem> roomField = new ComboBox<>();
        roomField.getItems().addAll(roomSelectionItems);

        layout.add(guestField, 1, 2);
        layout.add(roomField, 1, 3);

        Button addNewReservationButton = new Button("Dodaj nową rezerwację");
        addNewReservationButton.setOnAction(ActionEvent -> {

            try {
                reservationService.createNewReservation(
                        dateFromField.getValue(),
                        dateToField.getValue(),
                        guestField.getValue().getId(),
                        roomField.getValue().getId()
                );

                tableView.getItems().clear();
                tableView.getItems().addAll(reservationService.getReservationDTOList());
                primaryStage.close();
            } catch (IllegalArgumentException e) {
                Stage errorPopup = new Stage();
                errorPopup.initModality(Modality.WINDOW_MODAL);
                errorPopup.initOwner(primaryStage);
                errorPopup.setScene(new ErrorScene(errorPopup).getScene());
                errorPopup.setTitle("Error");
                errorPopup.showAndWait();
            }
        });

        layout.add(addNewReservationButton, 0, 4);

        this.mainScene = new Scene(layout, 640, 480);
        this.mainScene.getStylesheets().add(getClass().getClassLoader().getResource("HotelReservations.css").toExternalForm());




    }

    public Scene getMainScene() {
        return this.mainScene;
    }
}
