package pl.javaCwiczenia2020.ui.gui.rooms;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.*;
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
    private Stage primaryStage;

    public RoomTab(Stage primaryStage) {

        this.primaryStage = primaryStage;
        TableView<RoomDTO> tableView = getRoomDTOTableView();

        Button button = new Button("Dodaj nowy");
        button.setOnAction(ActionEvent -> {
            Stage addNewRoomPopUp = new Stage();
            addNewRoomPopUp.initModality(Modality.WINDOW_MODAL);
            addNewRoomPopUp.setScene(new AddNewRoomScene(addNewRoomPopUp, tableView).getMainScene());
            addNewRoomPopUp.initOwner(this.primaryStage);
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
        TableColumn<RoomDTO, RoomDTO> deleteColumn = new TableColumn<>();
        deleteColumn.setCellValueFactory(value -> new ReadOnlyObjectWrapper(value.getValue()));
        deleteColumn.setCellFactory( param -> new TableCell<>() {

            Button deleteButton = new Button("Usuń");
            Button editButton = new Button("Edytuj");
            VBox buttons = new VBox(deleteButton, editButton);

            @Override
            protected void updateItem(RoomDTO value, boolean empty) {
                super.updateItem(value, empty);

                if(value == null) {
                    setGraphic(null);
                } else {
                    setGraphic(buttons);
                    deleteButton.setOnAction(ActionEvent -> {
                        roomService.remove(value.getId());
                        tableView.getItems().remove(value);
                    });

                    editButton.setOnAction(ActionEvent -> {
                        Stage editWindow = new Stage();
                        editWindow.initModality(Modality.WINDOW_MODAL);
                        editWindow.initOwner(primaryStage);
                        editWindow.setScene(new EditRoomScene(editWindow, tableView, value).getMainScene());
                        editWindow.setTitle("Edytowanie pokoju");
                        editWindow.showAndWait();
                    });
                }
            }
        });

        tableView.getColumns().addAll(roomNumberColumn, roomSizeColumn, bedsCountColumn, bedsColumn, deleteColumn);

        List<RoomDTO> roomDTOList = roomService.getRoomDTOList();

        tableView.getItems().addAll(roomDTOList);
        return tableView;
    }

    public Tab getRoomsTabs() {
        return roomsTab;
    }
}
