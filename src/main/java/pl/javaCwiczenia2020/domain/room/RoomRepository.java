package pl.javaCwiczenia2020.domain.room;

import java.util.List;

public interface RoomRepository {

    Room createNewRoom(int number, List<BedType> bedTypes);

    List<Room> getAll();

    void saveAll();

    void readAll();

    void remove(long id);

    void edit(long id, int number, List<BedType> bedTypes);

    Room getRoomById(long id);
}
