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
import pl.javaCwiczenia2020.domain.room.RoomService;
import pl.javaCwiczenia2020.domain.room.dto.RoomDTO;

import java.util.List;

public class RoomTab {

    private Tab roomsTab;
    private RoomService roomService = ObjectPool.getRoomService();

    public RoomTab(Stage primaryStage) {

        TableView<RoomDTO> tableView = getRoomDTOTableView();

        Button button = new Button("Dodaj nowy");
        button.setOnAction(ActionEvent -> {
            Stage addNewRoomPopUp = new Stage();
            addNewRoomPopUp.initModality(Modality.WINDOW_MODAL);
            addNewRoomPopUp.setScene(new AddNewRoomScene(addNewRoomPopUp, tableView).getMainScene());
            addNewRoomPopUp.initOwner(primaryStage);
            addNewRoomPopUp.setTitle("Dodawanie nowego pokoju");
            addNewRoomPopUp.showAndWait();
        });

        VBox layout = new VBox(button, tableView);

        this.roomsTab = new Tab("Pokoje", layout);
        this.roomsTab.setClosable(false);
    }

    private TableView<RoomDTO> getRoomDTOTableView() {
        TableView<RoomDTO> tableView = new TableView<>();
        TableColumn<RoomDTO, Integer> roomNumberColumn = new TableColumn<>("Numer");
        roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        TableColumn<RoomDTO, String> bedsColumn = new TableColumn<>("Łóżka");
        bedsColumn.setCellValueFactory(new PropertyValueFactory<>("beds"));
        TableColumn<RoomDTO, Integer> bedsCountColumn = new TableColumn<>("Ilość łóżek") ;
        bedsCountColumn.setCellValueFactory(new PropertyValueFactory<>("bedCount"));
        TableColumn<RoomDTO, Integer> roomSizeColumn = new TableColumn<>("Rozmiar pokoju") ;
        roomSizeColumn.setCellValueFactory(new PropertyValueFactory<>("roomSize"));

        tableView.getColumns().addAll(roomNumberColumn, roomSizeColumn, bedsCountColumn, bedsColumn);

        List<RoomDTO> roomDTOList = roomService.getRoomDTOList();

        tableView.getItems().addAll(roomDTOList);
        return tableView;
    }

    public Tab getRoomsTabs() {
        return roomsTab;
    }
}
