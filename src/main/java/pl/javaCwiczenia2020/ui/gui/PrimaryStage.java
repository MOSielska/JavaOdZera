package pl.javaCwiczenia2020.ui.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.javaCwiczenia2020.domain.util.Properties;

public class PrimaryStage {

    public void initialize(Stage primaryStage){

        String hotelName = Properties.HOTEL_NAME;
        int systemVersion = Properties.SYSTEM_VERSION;

        MainTabView mainTabs = new MainTabView(primaryStage);

        Scene scene = new Scene(mainTabs.getTabs(), 640, 480);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("HotelReservations.css").toExternalForm());

        String title = String.format("System rezrewacji dla hotelu: %s (%d)", hotelName, systemVersion);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
