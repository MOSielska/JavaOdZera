package pl.javaCwiczenia2020.domain.reservation.dto;

import java.time.LocalDateTime;

public class ReservationDTO {

    private final long id;
    private final long guestId;
    private final String guestName;
    private final long roomId;
    private final int roomNumber;
    private final LocalDateTime dateFrom;
    private final LocalDateTime dateTo;

    public ReservationDTO(long id, long guestId, String guestName, long roomId, int roomNumber, LocalDateTime dateFrom, LocalDateTime dateTo) {
        this.id = id;
        this.guestId = guestId;
        this.guestName = guestName;
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public long getId() {
        return id;
    }

    public long getGuestId() {
        return guestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public long getRoomId() {
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
