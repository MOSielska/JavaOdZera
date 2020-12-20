package pl.javaCwiczenia2020.domain.reservation;

import pl.javaCwiczenia2020.domain.guest.Guest;
import pl.javaCwiczenia2020.domain.guest.GuestService;
import pl.javaCwiczenia2020.domain.room.Room;
import pl.javaCwiczenia2020.domain.room.RoomService;
import pl.javaCwiczenia2020.domain.util.Properties;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationService {

    private final GuestService guestService = new GuestService();
    private final RoomService roomService = new RoomService();

    private final ReservationRepository repo = new ReservationRepository();

    public Reservation createNewReservation(LocalDate dateFrom, LocalDate dateTo, int guestId, int roomId) throws IllegalArgumentException{
//TODO null room, null guest
        Guest guest = this.guestService.getGuestById(guestId);
        Room room = this.roomService.getRoomById(roomId);

        LocalDateTime dateFromWithTime = dateFrom.atTime(Properties.HOTEL_NIGHT_START_HOUR, Properties.HOTEL_NIGHT_START_MINUTE);
        LocalDateTime dateToWithTime = dateTo.atTime(Properties.HOTEL_NIGHT_END_HOUR, Properties.HOTEL_NIGHT_END_MINUTE);

        if(dateToWithTime.isBefore(dateFromWithTime)) {
            throw new IllegalArgumentException();
        }

        return this.repo.createNewReservation(guest, room, dateFromWithTime, dateToWithTime);

    }

    public void saveAll(){
        this.repo.saveAll();
    }

    public void readAll() {
        this.repo.readAll();
    }
}
