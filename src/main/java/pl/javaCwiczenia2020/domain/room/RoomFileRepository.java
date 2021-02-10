package pl.javaCwiczenia2020.domain.room;

import pl.javaCwiczenia2020.domain.util.SystemUtils;
import pl.javaCwiczenia2020.exceptions.ReadWriteFileException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoomFileRepository implements RoomRepository {

    private final List<Room> rooms = new ArrayList<>();
    private final static RoomRepository instance = new RoomFileRepository();

    private RoomFileRepository() {

    }

    public static RoomRepository getInstance() {
        return instance;
    }

    public Room createNewRoom(int number, List<BedType> bedTypes) {

        Room newRoom = new Room(findNewId(), number, bedTypes);
        rooms.add(newRoom);
        return newRoom;
    }

    Room addExistingRoom(long id, int number, List<BedType> bedTypes) {

        Room newRoom = new Room(id, number, bedTypes);
        rooms.add(newRoom);
        return newRoom;
    }

    public List<Room> getAll() {
        return this.rooms;
    }

    @Override
    public void saveAll() {

        String fileName = "rooms.csv";

        Path file = Paths.get((SystemUtils.DIRECTORY_PATH).toString(), fileName);

        StringBuilder info = new StringBuilder();
        for (Room room : this.rooms) {
            info.append(room.toCSV());
        }

        try {
            Files.writeString(file, info.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new ReadWriteFileException(SystemUtils.DIRECTORY_PATH.toString(), "write", "rooms file");
        }

    }

    @Override
    public void readAll() {

        String fileName = "rooms.csv";

        Path file = Paths.get((SystemUtils.DIRECTORY_PATH).toString(), fileName);

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
                addExistingRoom(id, number, Arrays.asList(bedTypesList));
            }

        } catch (IOException e) {
            throw new ReadWriteFileException(SystemUtils.DIRECTORY_PATH.toString(), "read", "rooms file");
        }
    }

    private long findNewId() {
        long max = 0;

        for (Room room : this.rooms) {
            if (room.getId() > max) {
                max = room.getId();
            }
        }
        return max + 1;
    }

    @Override
    public void remove(long id) {
        int index = -1;
        for (Room room : this.rooms) {
            if (room.getId() == id) {
                index = this.rooms.indexOf(room);
            }
        }
        if (index > -1) this.rooms.remove(index);
    }

    @Override
    public void edit(long id, int number, List<BedType> bedTypes) {

        this.remove(id);
        this.addExistingRoom(id, number, bedTypes);

    }

    @Override
    public Room getRoomById(long id) {

        Room foundRoom = null;
        for (Room room : rooms) {
            if (room.getId() == id) foundRoom = room;
        }
        return foundRoom;
    }

}