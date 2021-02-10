package pl.javaCwiczenia2020.ui.gui.guests;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.javaCwiczenia2020.domain.ObjectPool;
import pl.javaCwiczenia2020.domain.guest.GuestService;
import pl.javaCwiczenia2020.domain.guest.dto.GuestDTO;

public class GuestTab {

    private Tab guestTab;
    private GuestService guestService = ObjectPool.getGuestService();
    private Stage primaryStage;

    public GuestTab(Stage primaryStage) {

        this.primaryStage = primaryStage;
        Button addNewGuestButton = new Button("Dodaj nowy");

        TableView<GuestDTO> tableView = getGuestDTOTableView();

        addNewGuestButton.setOnAction(ActionEvent -> {
            Stage addNewGuestPopup = new Stage();
            addNewGuestPopup.initModality(Modality.WINDOW_MODAL);
            addNewGuestPopup.initOwner(primaryStage);
            addNewGuestPopup.setScene(new AddNewGuestScene(addNewGuestPopup, tableView).getMainScene());
            addNewGuestPopup.setTitle("Dodawanie nowego gościa");
            addNewGuestPopup.showAndWait();
        });


        VBox layout = new VBox(addNewGuestButton, tableView);


        this.guestTab = new Tab("Goście", layout);
        this.guestTab.setClosable(false);
    }

    private TableView<GuestDTO> getGuestDTOTableView() {
        TableView<GuestDTO> tableView = new TableView<>();
        TableColumn<GuestDTO, String> firstNameColumn = new TableColumn<>("Imię");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<GuestDTO, String> lastNameColumn = new TableColumn<>("Nazwisko");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn<GuestDTO, Integer> ageColumn = new TableColumn<>("Wiek");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        TableColumn<GuestDTO, String> genderColumn = new TableColumn<>("Płeć");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        TableColumn<GuestDTO, GuestDTO> deleteColumn = new TableColumn<>();
        deleteColumn.setCellValueFactory(value -> new ReadOnlyObjectWrapper(value.getValue()));
        deleteColumn.setCellFactory( param -> new TableCell<>() {

            Button deleteButton = new Button("Usuń");
            Button editButton = new Button("Edytuj");
            VBox buttons = new VBox(deleteButton, editButton);

            @Override
            protected void updateItem(GuestDTO value, boolean empty) {
                super.updateItem(value, empty);

                if(value == null) {
                    setGraphic(null);
                } else {
                    setGraphic(buttons);
                    deleteButton.setOnAction(ActionEvent -> {
                        guestService.remove(value.getId());
                        tableView.getItems().remove(value);
                    });

                    editButton.setOnAction(ActionEvent -> {
                        Stage editWindow = new Stage();
                        editWindow.initModality(Modality.WINDOW_MODAL);
                        editWindow.initOwner(primaryStage);
                        editWindow.setScene(new EditGuestScene(editWindow, tableView, value).getMainScene());
                        editWindow.setTitle("Edytowanie gaścia");
                        editWindow.showAndWait();
                    });
                }
            }
        });


        tableView.getColumns().addAll(firstNameColumn, lastNameColumn, ageColumn, genderColumn, deleteColumn);
        tableView.getItems().addAll(guestService.getGuestDTOList());
        return tableView;
    }

    public Tab getGuestTabs() {
        return guestTab;
    }
}
