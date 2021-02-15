package pl.javaCwiczenia2020.domain.room;

import java.util.ArrayList;
import java.util.List;

public class RoomDatabaseRepository implements RoomRepository {

    private final List<Room> rooms = new ArrayList<>();

    private static RoomDatabaseRepository instance = new RoomDatabaseRepository();

    private DatabaseRoomConnector connector = new JDBCRoomConnector();

    public static RoomDatabaseRepository getInstance() {
        return instance;
    }

    RoomDatabaseRepository() {

    }


    @Override
    public Room createNewRoom(int number, List<BedType> bedTypes) {

        Room newRoom = connector.createNewRoom(number, bedTypes);
        this.rooms.add(newRoom);
        return newRoom;
    }

    @Override
    public List<Room> getAllRooms() {
        return this.rooms;
    }

    @Override
    public void saveAll() {


    }

    @Override
    public void readAll() {
        this.rooms.addAll(connector.getAllRooms());
        List<Object[]> allBeds = connector.getAllBeds();

        for( Object[] touple : allBeds) {
            this.getRoomById((long)touple[0]).addBed((BedType)touple[1]);
        }


    }

    @Override
    public void remove(long id) {
        connector.remove(id);
        this.removeById(id);

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

        connector.edit(id, number, bedTypes);
        Room roomToEdit = this.getRoomById(id);
        roomToEdit.setNumber(number);
        roomToEdit.setBeds(bedTypes);

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

    void setConnector(DatabaseRoomConnector connector) {
        this.connector = connector;
    }
}
