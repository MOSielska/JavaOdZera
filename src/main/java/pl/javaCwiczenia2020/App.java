package pl.javaCwiczenia2020;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.javaCwiczenia2020.domain.ObjectPool;
import pl.javaCwiczenia2020.domain.guest.GuestService;
import pl.javaCwiczenia2020.domain.reservation.ReservationService;
import pl.javaCwiczenia2020.domain.room.RoomService;
import pl.javaCwiczenia2020.domain.util.Properties;
import pl.javaCwiczenia2020.exceptions.ReadWriteFileException;
import pl.javaCwiczenia2020.ui.gui.PrimaryStage;
import pl.javaCwiczenia2020.ui.text.TextUI;

import java.io.IOException;

public class App extends Application {

    private static final GuestService guestService = ObjectPool.getGuestService();
    private static final RoomService roomService = ObjectPool.getRoomService();
    private static final ReservationService reservationService = ObjectPool.getReservationService();
    private static final TextUI textUI = new TextUI();

    public static void main(String[] args) {

        try {
            Properties.createDataDirectory();
            System.out.println("Ładowanie danych...");
            guestService.readAll();
            roomService.readAll();
            reservationService.readAll();

        } catch (IOException e) {
            throw new ReadWriteFileException(Properties.DIRECTORY_PATH.toString(), "create", "directory");
        }
 //       textUI.showSystemInfo();
 //       textUI.showMainMenu();

        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        PrimaryStage primaryStageObj = new PrimaryStage();
        primaryStageObj.initialize(primaryStage);

    }

    @Override
    public void stop() {

        guestService.saveAll();
        roomService.saveAll();
        reservationService.saveAll();

    }
}
