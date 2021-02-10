package pl.javaCwiczenia2020.ui.gui.reservations;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.javaCwiczenia2020.domain.ObjectPool;
import pl.javaCwiczenia2020.domain.reservation.ReservationService;
import pl.javaCwiczenia2020.domain.reservation.dto.ReservationDTO;

import java.time.LocalDateTime;

public class ReservationTab {

    private Tab reservationTab;
    private ReservationService reservationService = ObjectPool.getReservationService();

    public ReservationTab(Stage primaryStage) {

        TableView<ReservationDTO> tableView = getReservationDTOTableView();

        Button addNewReservationButton = new Button("Dodaj nowy");

        VBox vBox = new VBox(addNewReservationButton, tableView);

        addNewReservationButton.setOnAction(ActionEvent -> {
            Stage addNewReservationPopUp = new Stage();
            addNewReservationPopUp.initModality(Modality.WINDOW_MODAL);
            addNewReservationPopUp.initOwner(primaryStage);
            addNewReservationPopUp.setScene(new AddNewReservationScene(addNewReservationPopUp, tableView).getMainScene());
            addNewReservationPopUp.setTitle("Dodawanie nowej rezerwacji");
            addNewReservationPopUp.showAndWait();
        });

        this.reservationTab = new Tab("Rezerwacje", vBox);
        this.reservationTab.setClosable(false);
    }

    private TableView<ReservationDTO> getReservationDTOTableView() {
        TableView<ReservationDTO> tableView = new TableView<>();
        TableColumn<ReservationDTO, LocalDateTime> fromColumn = new TableColumn<>("Od");
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("dateFrom"));
        TableColumn<ReservationDTO, LocalDateTime> toColumn = new TableColumn<>("Do");
        toColumn.setCellValueFactory(new PropertyValueFactory<>("dateTo"));
        TableColumn<ReservationDTO, Integer> roomColumn = new TableColumn<>("Pokój");
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        TableColumn<ReservationDTO, Integer> guestColumn = new TableColumn<>("Rezerwujący");
        guestColumn.setCellValueFactory(new PropertyValueFactory<>("guestName"));
        TableColumn<ReservationDTO, ReservationDTO> deleteColumn = new TableColumn<>("Usuń");
        deleteColumn.setCellValueFactory(value -> new ReadOnlyObjectWrapper(value.getValue()));
        deleteColumn.setCellFactory( param -> new TableCell<>() {

            Button deleteButton = new Button("Usuń");

            @Override
            protected void updateItem(ReservationDTO value, boolean empty) {
                super.updateItem(value, empty);

                if(value == null) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                    deleteButton.setOnAction(ActionEvent -> {
                        reservationService.remove(value.getId());
                        tableView.getItems().remove(value);
                    });
                }
            }
        });


        tableView.getColumns().addAll(fromColumn, toColumn, roomColumn, guestColumn, deleteColumn);
        tableView.getItems().addAll(reservationService.getReservationDTOList());
        return tableView;
    }

    public Tab getReservationTabs() {
        return reservationTab;
    }
}
