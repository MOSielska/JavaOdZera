package pl.javaCwiczenia2020.domain.reservation;

import pl.javaCwiczenia2020.domain.ObjectPool;
import pl.javaCwiczenia2020.domain.guest.Guest;
import pl.javaCwiczenia2020.domain.guest.GuestService;
import pl.javaCwiczenia2020.domain.reservation.dto.ReservationDTO;
import pl.javaCwiczenia2020.domain.room.Room;
import pl.javaCwiczenia2020.domain.room.RoomService;
import pl.javaCwiczenia2020.domain.util.SystemUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {

    private final  GuestService guestService = ObjectPool.getGuestService();
    private final  RoomService roomService = ObjectPool.getRoomService();
    private final ReservationRepository repo = ObjectPool.getReservationRepository();
    private final static ReservationService instance = new ReservationService();

    private ReservationService() {

    }

    public Reservation createNewReservation(LocalDate dateFrom, LocalDate dateTo, long guestId, long roomId) throws IllegalArgumentException{
        Guest guest = guestService.getGuestById(guestId);
        Room room = roomService.getRoomById(roomId);

        LocalDateTime dateFromWithTime = dateFrom.atTime(SystemUtils.HOTEL_NIGHT_START_HOUR, SystemUtils.HOTEL_NIGHT_START_MINUTE);
        LocalDateTime dateToWithTime = dateTo.atTime(SystemUtils.HOTEL_NIGHT_END_HOUR, SystemUtils.HOTEL_NIGHT_END_MINUTE);

        if(dateToWithTime.isBefore(dateFromWithTime)) {
            throw new IllegalArgumentException();
        }

        return repo.createNewReservation(guest, room, dateFromWithTime, dateToWithTime);

    }

    public void saveAll(){
        repo.saveAll();
    }

    public void readAll() {
        repo.readAll();
    }

    public List<ReservationDTO> getReservationDTOList() {

        List<ReservationDTO> reservationDTOList = new ArrayList<>();
        List<Reservation> reservationList = repo.getAll();

        for (Reservation res : reservationList) {
            reservationDTOList.add(res.convertToDTO());
        }

        return reservationDTOList;
    }

    public static ReservationService getInstance() {
        return instance;
    }

    public void remove(long id) {
        repo.remove(id);
    }
}
