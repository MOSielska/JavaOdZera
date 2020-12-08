package pl.javaCwiczenia2020.domain.room;

public class RoomRepository {

    Room createNewRoom(int number, BedType[] bedTypes) {

        Room newRoom = new Room(number, bedTypes);
        return newRoom;
    }
}
