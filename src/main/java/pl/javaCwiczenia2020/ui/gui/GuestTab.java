package pl.javaCwiczenia2020.ui.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    public GuestTab(Stage primaryStage) {

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

        tableView.getColumns().addAll(firstNameColumn, lastNameColumn, ageColumn, genderColumn);
        tableView.getItems().addAll(guestService.getGuestDTOList());
        return tableView;
    }

    public Tab getGuestTabs() {
        return guestTab;
    }
}
