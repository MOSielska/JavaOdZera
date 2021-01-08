package pl.javaCwiczenia2020.domain.reservation.dto;

import java.time.LocalDateTime;

public class ReservationDTO {

    private final int id;
    private final int guestId;
    private final String guestName;
    private final int roomId;
    private final int roomNumber;
    private final LocalDateTime dateFrom;
    private final LocalDateTime dateTo;

    public ReservationDTO(int id, int guestId, String guestName, int roomId, int roomNumber, LocalDateTime dateFrom, LocalDateTime dateTo) {
        this.id = id;
        this.guestId = guestId;
        this.guestName = guestName;
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public int getId() {
        return id;
    }

    public int getGuestId() {
        return guestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public LocalDateTime getDateTo() {
        return dateTo;
    }
}
