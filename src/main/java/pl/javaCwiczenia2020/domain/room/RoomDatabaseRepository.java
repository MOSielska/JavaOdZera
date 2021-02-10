package pl.javaCwiczenia2020.domain.room;

import pl.javaCwiczenia2020.domain.util.SystemUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomDatabaseRepository implements RoomRepository {

    private final List<Room> rooms = new ArrayList<>();

    private static RoomRepository instance = new RoomDatabaseRepository();

    public static RoomRepository getInstance() {
        return instance;
    }

    private String removeBedsTemplate = "DELETE FROM BEDS WHERE ROOM_ID = %d";
    private String createBedsTemplate = "INSERT INTO BEDS(ROOM_ID, BED) VALUES(%d, '%s')";

    @Override
    public Room createNewRoom(int number, List<BedType> bedTypes) {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String insertRoomTemplate = "INSERT INTO ROOMS(ROOM_NUMBER) VALUES(%d)";
            String query = String.format(insertRoomTemplate, number);
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            long newId = -1;
            while (rs.next()) {
                newId = rs.getLong(1);
            }

            for (BedType bedType : bedTypes) {
                statement.execute(String.format(createBedsTemplate, newId, bedType.toString()));
            }
            statement.close();
            Room newRoom = new Room(newId, number, bedTypes);
            this.rooms.add(newRoom);
            return newRoom;
        } catch (SQLException throwables) {
            System.out.println("Błąd przy tworzeniu nowego pokoju");
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public List<Room> getAll() {
        return this.rooms;
    }

    @Override
    public void saveAll() {

    }

    @Override
    public void readAll() {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ROOMS");
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                int roomNumber = resultSet.getInt(2);
                List<BedType> beds = new ArrayList<>();
                this.rooms.add(new Room(id, roomNumber, beds));
            }

            ResultSet rs = statement.executeQuery("SELECT * FROM BEDS");
            while (rs.next()) {
                long id = rs.getLong(2);
                String bedTypeString = rs.getString(3);
                BedType bedType = BedType.valueOf(bedTypeString);
                this.getRoomById(id).addBed(bedType);
            }
            statement.close();

        } catch (SQLException throwables) {
            System.out.println("Błąd przy wczytywaniu pokoji z bazy danych");
            throw new RuntimeException(throwables);
        }

    }

    @Override
    public void remove(long id) {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String removeBedsQuery = String.format(removeBedsTemplate, id);
            statement.execute(removeBedsQuery);
            String removeRoomTemplate = "DELETE FROM ROOMS WHERE ID = %d";
            String removeRoomQuery = String.format(removeRoomTemplate, id);
            statement.execute(removeRoomQuery);
            statement.close();
            this.removeById(id);
        } catch (SQLException throwables) {
            System.out.println("Błąd przy usuwaniu pokoju z bazy danych");
            throw new RuntimeException(throwables);
        }

    }

    private void removeById(long id) {
        int toRemoveIndex = -1;
        for (int i = 0; i < this.rooms.size(); i++) {
            if (this.rooms.get(i).getId() == id) {
                toRemoveIndex = i;
            }
        }
        this.rooms.remove(toRemoveIndex);
    }

    @Override
    public void edit(long id, int number, List<BedType> bedTypes) {
        try {
            Statement statement = SystemUtils.connection.createStatement();

            String updateRoomTemplate = "UPDATE ROOMS SET ROOM_NUMBER=%d WHERE ID=%d";
            String updateQuery = String.format(updateRoomTemplate, number, id);
            statement.executeUpdate(updateQuery);
            String deleteBedsQuery = String.format(removeBedsTemplate, id);
            statement.execute(deleteBedsQuery);
            for(BedType bedType : bedTypes) {
                String createBedQuery = String.format(createBedsTemplate, id, bedType.toString());
                statement.execute(createBedQuery);
            }
            statement.close();

            Room roomToEdit = getRoomById(id);
            roomToEdit.setNumber(number);
            roomToEdit.setBeds(bedTypes);

        } catch (SQLException throwables) {
            System.out.println("Błąd przy edycji pokoju w bazie danych");
            throw new RuntimeException(throwables);
        }

    }

    @Override
    public Room getRoomById(long id) {
        for (Room room : this.rooms) {
            if (room.getId() == id) {
                return room;
            }
        }
        return null;
    }
}