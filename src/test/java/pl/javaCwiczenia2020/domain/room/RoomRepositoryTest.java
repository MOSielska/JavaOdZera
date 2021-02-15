package pl.javaCwiczenia2020.domain.room;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomRepositoryTest {

    @Test
    public void getAllRoomsTest() {

       RoomDatabaseRepository repository = new RoomDatabaseRepository();
       repository.setConnector(new MockDatabaseRoomConnector());

       repository.readAll();
       List<Room> allRooms = repository.getAllRooms();

        assertEquals(2, allRooms.size());
        assertEquals(3, allRooms.get(0).getBeds().size());
        assertEquals(0, allRooms.get(1).getBeds().size());

    }

    @Test
    public void removeByIdTest() {
        RoomDatabaseRepository repository = new RoomDatabaseRepository();
        repository.setConnector(new MockDatabaseRoomConnector());

        repository.readAll();
        List<Room> allRooms = repository.getAllRooms();
        allRooms.remove(1);

        assertEquals(1, allRooms.size());
        assertEquals(1l, allRooms.get(0).getId());

    }
}
