package pl.javaCwiczenia2020.domain.reservation;

import pl.javaCwiczenia2020.domain.ObjectPool;
import pl.javaCwiczenia2020.domain.guest.Guest;
import pl.javaCwiczenia2020.domain.guest.GuestService;
import pl.javaCwiczenia2020.domain.room.Room;
import pl.javaCwiczenia2020.domain.room.RoomService;
import pl.javaCwiczenia2020.domain.util.SystemUtils;
import pl.javaCwiczenia2020.exceptions.ReadWriteFileException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationFileRepository implements ReservationRepository {

    private List<Reservation> reservationList = new ArrayList<>();
    GuestService guestService = ObjectPool.getGuestService();
    RoomService roomService = ObjectPool.getRoomService();
    private static final ReservationFileRepository instance = new ReservationFileRepository();

    private ReservationFileRepository(){

    }

    long findNewId() {
        long max = 0;

        for (Reservation reservation : this.reservationList) {
            if (reservation.getId() > max) {
                max = reservation.getId();
            }
        }
        return max + 1;
    }


     @Override
     public Reservation createNewReservation(Guest guest, Room room, LocalDateTime dateFrom, LocalDateTime dateTo) {

        Reservation newReservation = new Reservation(findNewId(), guest, room, dateFrom, dateTo);
        reservationList.add(newReservation);
        return newReservation;
    }

    Reservation addExistingReservation(long id, Guest guest, Room room, LocalDateTime dateFrom, LocalDateTime dateTo) {
        Reservation newReservation = new Reservation(id, guest, room, dateFrom, dateTo);
        reservationList.add(newReservation);
        return newReservation;
    }



    @Override
    public void saveAll() {

        String fileName = "reservations.csv";

        Path file = Paths.get(SystemUtils.DIRECTORY_PATH.toString(), fileName);

        StringBuilder info = new StringBuilder();
        for (Reservation reservation : this.reservationList) {
            info.append(reservation.toCSV());
        }

        try {
            Files.writeString(file, info.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new ReadWriteFileException(SystemUtils.DIRECTORY_PATH.toString(), "write", "reservations file");
        }
    }

    @Override
    public void readAll() {

        String fileName = "reservations.csv";

        Path file = Paths.get(SystemUtils.DIRECTORY_PATH.toString(), fileName);

        if(!Files.exists(file)) {
            return;
        }

        try {

            String[] reservationList = Files.readString(file, StandardCharsets.UTF_8).split(System.getProperty("line.separator"));
            for (String reservationInfo : reservationList) {
                String[] reservationsData = reservationInfo.split(",");
                if((reservationsData[0] == null) || reservationsData[0].trim().isEmpty()) {
                    continue;
                }
                int id = Integer.parseInt(reservationsData[0]);
                int guestId = Integer.parseInt(reservationsData[1]);
                int roomId = Integer.parseInt(reservationsData[2]);
                LocalDateTime dateFrom = LocalDateTime.parse(reservationsData[3]);
                LocalDateTime dateTo = LocalDateTime.parse(reservationsData[4]);

                addExistingReservation(id,
                        this.guestService.getGuestById(guestId),
                        this.roomService.getRoomById(roomId),
                        dateFrom,
                        dateTo);

            }


        } catch (IOException e) {
            throw new ReadWriteFileException(SystemUtils.DIRECTORY_PATH.toString(), "read", "reservation file");
        }


    }

    @Override
    public List<Reservation> getAll() {

        return this.reservationList;
    }

    public static ReservationFileRepository getInstance() {
        return instance;
    }

    @Override
    public void remove(long id) {
        int index = -1;
        for (Reservation res : this.reservationList) {
            if (res.getId() == id) {
                index = this.reservationList.indexOf(res);
            }
        }
        if (index > -1) this.reservationList.remove(index);
    }
}
