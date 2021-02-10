package pl.javaCwiczenia2020.domain.reservation;

import pl.javaCwiczenia2020.domain.ObjectPool;
import pl.javaCwiczenia2020.domain.guest.Guest;
import pl.javaCwiczenia2020.domain.guest.GuestService;
import pl.javaCwiczenia2020.domain.room.Room;
import pl.javaCwiczenia2020.domain.room.RoomService;
import pl.javaCwiczenia2020.domain.util.SystemUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReservationDatabaseRepository implements ReservationRepository {

    private final static ReservationRepository instance = new ReservationDatabaseRepository();
    private final GuestService guestService = ObjectPool.getGuestService();
    private final RoomService roomService = ObjectPool.getRoomService();
    private List<Reservation> reservations = new ArrayList<>();

    public static ReservationRepository getInstance() {
        return instance;
    }

    @Override
    public Reservation createNewReservation(Guest guest, Room room, LocalDateTime dateFrom, LocalDateTime dateTo) {
        try {
            String dateFromStr = dateFrom.format(DateTimeFormatter.ISO_DATE_TIME);
            String dateToStr = dateTo.format(DateTimeFormatter.ISO_DATE_TIME);
            Statement statement = SystemUtils.connection.createStatement();
            String insertReservationTemplate = "INSERT INTO RESERVATIONS(GUEST_ID, ROOM_ID, DATE_FROM, DATE_TO) " +
                    "VALUES(%d, %d, '%s', '%s')";
            String query = String.format(insertReservationTemplate, guest.getId(), room.getId(), dateFromStr, dateToStr);
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();

            long newId = -1;
            while(rs.next()) {
                newId = rs.getLong(1);
            }
            statement.close();

            Reservation newReservation = new Reservation(newId, guest, room, dateFrom, dateTo);
            this.reservations.add(newReservation);
            return newReservation;


        } catch (SQLException throwables) {
            System.out.println("Błąd przy tworzeniu nowej rezerwacji");
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public void saveAll() {

    }

    @Override
    public void readAll() {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM RESERVATIONS");
            while(resultSet.next()) {
                long id = resultSet.getLong(1);
                long guestId = resultSet.getLong(2);
                long roomId = resultSet.getLong(3);
                String dateFromStr = resultSet.getString(4);
                LocalDateTime dateFrom = LocalDateTime.parse(dateFromStr.replace(" ", "T"), DateTimeFormatter.ISO_DATE_TIME);
                String dateToStr = resultSet.getString(5);
                LocalDateTime dateTo = LocalDateTime.parse(dateToStr.replace(" ", "T"), DateTimeFormatter.ISO_DATE_TIME);
                Reservation newReservation = new Reservation(id, guestService.getGuestById(guestId), roomService.getRoomById(roomId), dateFrom, dateTo);
                this.reservations.add(newReservation);
            }
            statement.close();
        } catch (SQLException throwables) {
            System.out.println("Błąd przy wczytywaniu rezerwacji z bazy danych");
            throw new RuntimeException(throwables);
        }

    }

    @Override
    public List<Reservation> getAll() {
        return this.reservations;
    }

    @Override
    public void remove(long id) {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String removeReservationQuery = String.format("DELETE FROM RESERVATIONS WHERE ID=%d", id);
            statement.execute(removeReservationQuery);
            statement.close();
            this.removeById(id);

        } catch (SQLException throwables) {
            System.out.println("Błąd przy usuwaniu rezerwacji z bazy danych");
            throw new RuntimeException(throwables);
        }

    }

    private void removeById(long id) {
        long toRemoveIndex = -1;
        for (int i = 0; i < this.reservations.size(); i++){
            if((this.reservations.get(i).getId()) == id) {
                toRemoveIndex = i;
            }
        }
        if (toRemoveIndex > -1) {
            this.reservations.remove(toRemoveIndex);
        }
    }
}
