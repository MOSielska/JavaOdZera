package pl.javaCwiczenia2020.domain.reservation;

import pl.javaCwiczenia2020.domain.guest.Guest;
import pl.javaCwiczenia2020.domain.reservation.dto.ReservationDTO;
import pl.javaCwiczenia2020.domain.room.Room;

import java.time.LocalDateTime;

public class Reservation {

    private final long id;
    private final Guest guest;
    private final Room room;
    private final LocalDateTime dateFrom;
    private final LocalDateTime dateTo;

    public Reservation(long id, Guest guest, Room room, LocalDateTime dateFrom, LocalDateTime dateTo) {
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

    public long getId() {
        return this.id;
    }

    public ReservationDTO convertToDTO() {

        ReservationDTO resDTO = new ReservationDTO( this.id,
                                                    this.guest.getId(),
                                                    this.guest.getName(),
                                                    this.room.getId(),
                                                    this.room.getNumber(),
                                                    this.dateFrom,
                                                    this.dateTo);
        return resDTO;
    }
}
