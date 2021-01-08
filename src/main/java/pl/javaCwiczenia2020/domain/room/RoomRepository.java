package pl.javaCwiczenia2020.domain.room;

import pl.javaCwiczenia2020.domain.util.Properties;
import pl.javaCwiczenia2020.exceptions.ReadWriteFileException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository {

    private final List<Room> roomList = new ArrayList<>();
    private final static RoomRepository instance = new RoomRepository();

    private RoomRepository() {

    }

    Room createNewRoom(int number, BedType[] bedTypes) {

        Room newRoom = new Room(findNewId(), number, bedTypes);
        roomList.add(newRoom);
        return newRoom;
    }

    Room addExistingRoom(int id, int number, BedType[] bedTypes) {

        Room newRoom = new Room(id, number, bedTypes);
        roomList.add(newRoom);
        return newRoom;
    }

    public List<Room> getAll() {
        return this.roomList;
    }

    void saveAll() {

        String fileName = "rooms.csv";

        Path file = Paths.get((Properties.DIRECTORY_PATH).toString(), fileName);

        StringBuilder info = new StringBuilder();
        for (Room room : this.roomList) {
            info.append(room.toCSV());
        }

        try {
            Files.writeString(file, info.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new ReadWriteFileException(Properties.DIRECTORY_PATH.toString(), "write", "rooms file");
        }

    }

    void readAll() {

        String fileName = "rooms.csv";

        Path file = Paths.get((Properties.DIRECTORY_PATH).toString(), fileName);

        if (!Files.exists(file)) {
            return;
        }

        try {
            String[] roomList = (Files.readString(file, StandardCharsets.UTF_8)).split(System.getProperty("line.separator"));

            for (String roomInfo : roomList) {
                String[] roomData = roomInfo.split(",");
                if((roomData[0] == null) || roomData[0].trim().isEmpty()) {
                    continue;
                }
                int id = Integer.parseInt(roomData[0]);
                int number = Integer.parseInt(roomData[1]);
                String bedTypes = roomData[2];
                String[] bedTypesStringList = bedTypes.split("#");
                BedType[] bedTypesList = new BedType[bedTypesStringList.length];
                int i = 0;
                for (String bedType : bedTypesStringList) {
                    bedTypesList[i] = BedType.valueOf(bedType);
                    i++;
                }
                addExistingRoom(id, number, bedTypesList);
            }

        } catch (IOException e) {
            throw new ReadWriteFileException(Properties.DIRECTORY_PATH.toString(), "read", "rooms file");
        }
    }

    private int findNewId() {
        int max = 0;

        for (Room room : this.roomList) {
            if (room.getId() > max) {
                max = room.getId();
            }
        }
        return max + 1;
    }

    void remove(int id) {

        for (Room room : roomList) {
            if (room.getId() == id) roomList.remove(room);
        }
    }

    void edit(int id, int number, BedType[] bedTypes) {

        this.remove(id);
        this.addExistingRoom(id, number, bedTypes);

    }

    Room getRoomById(int id) {

        Room foundRoom = null;
        for (Room room : roomList) {
            if (room.getId() == id) foundRoom = room;
        }
        return foundRoom;
    }

    public static RoomRepository getInstance() {
        return instance;
    }
}