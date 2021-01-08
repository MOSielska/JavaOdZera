package pl.javaCwiczenia2020.ui.gui;

import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class MainTabView {

    private TabPane mainTabs;

    public MainTabView(Stage primaryStage){


        this.mainTabs = new TabPane();

        RoomTab roomTab = new RoomTab(primaryStage);
        ReservationTab reservationTab = new ReservationTab(primaryStage);
        GuestTab guestTab = new GuestTab(primaryStage);

        this.mainTabs.getTabs().addAll(reservationTab.getReservationTabs(), guestTab.getGuestTabs(), roomTab.getRoomsTabs());
    }

    TabPane getTabs() {
        return this.mainTabs;
    }
}
