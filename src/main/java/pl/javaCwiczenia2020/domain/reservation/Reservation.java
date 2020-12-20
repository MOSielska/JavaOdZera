package pl.javaCwiczenia2020.domain.reservation;

import pl.javaCwiczenia2020.domain.guest.Guest;
import pl.javaCwiczenia2020.domain.room.Room;

import java.time.LocalDateTime;

public class Reservation {

    private final int id;
    private final Guest guest;
    private final Room room;
    private final LocalDateTime dateFrom;
    private final LocalDateTime dateTo;

    public Reservation(int id, Guest guest, Room room, LocalDateTime dateFrom, LocalDateTime dateTo) {
        this.id = id;
        this.guest = guest;
        this.room = room;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String toCSV() {

        return String.format("%s,%s,%s,%s,%s%s",
                this.id,
                this.guest.getId(),
                this.room.getId(),
                this.dateFrom.toString(),
                this.dateTo.toString(),
                System.getProperty("line.separator"));
    }

    public int getId() {
        return this.id;
    }
}
