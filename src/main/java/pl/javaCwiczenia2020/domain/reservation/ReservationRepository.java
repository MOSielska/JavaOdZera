package pl.javaCwiczenia2020.domain.reservation;

import pl.javaCwiczenia2020.domain.guest.Guest;
import pl.javaCwiczenia2020.domain.room.Room;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository {
    Reservation createNewReservation(Guest guest, Room room, LocalDateTime dateFrom, LocalDateTime dateTo);

    void saveAll();

    void readAll();

    List<Reservation> getAll();

    void remove(long id);
}
