package pl.javaCwiczenia2020.domain;

import pl.javaCwiczenia2020.domain.guest.GuestDatabaseRepository;
import pl.javaCwiczenia2020.domain.guest.GuestRepository;
import pl.javaCwiczenia2020.domain.guest.GuestService;
import pl.javaCwiczenia2020.domain.reservation.ReservationDatabaseRepository;
import pl.javaCwiczenia2020.domain.reservation.ReservationRepository;
import pl.javaCwiczenia2020.domain.reservation.ReservationService;
import pl.javaCwiczenia2020.domain.room.RoomDatabaseRepository;
import pl.javaCwiczenia2020.domain.room.RoomRepository;
import pl.javaCwiczenia2020.domain.room.RoomService;

public class ObjectPool {

    private ObjectPool(){

    }

    public static GuestService getGuestService() {
        return GuestService.getInstance();
    }

    public static GuestRepository getGuestRepository() {

        return GuestDatabaseRepository.getInstance();
    }

    public static RoomService getRoomService() {

        return RoomService.getInstance();
    }

    public static RoomRepository getRoomRepository() {
      //  return RoomFileRepository.getInstance();
        return RoomDatabaseRepository.getInstance();
    }

    public static ReservationService getReservationService() {

        return ReservationService.getInstance();
    }

    public static ReservationRepository getReservationRepository() {
        return ReservationDatabaseRepository.getInstance();
    }
}
