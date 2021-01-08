package pl.javaCwiczenia2020.domain.room;

import pl.javaCwiczenia2020.domain.ObjectPool;
import pl.javaCwiczenia2020.domain.room.dto.RoomDTO;
import pl.javaCwiczenia2020.exceptions.WrongOptionException;

import java.util.ArrayList;
import java.util.List;

public class RoomService {

    private  final RoomRepository repo = ObjectPool.getRoomRepository();
    private static RoomService instance = new RoomService();

    private RoomService() {

    }

    public Room createNewRoom(int number, List<String> bedTypeList) {

        BedType[] bedTypes = new BedType[bedTypeList.size()];

        for(int i = 0; i < bedTypeList.size(); i++) {
            bedTypes[i] = BedType.valueOf(bedTypeList.get(i));
        }
        return repo.createNewRoom(number, bedTypes);
    }

    public Room createNewRoom(int number, int[] bedTypesData) {

        BedType[] bedTypes = new BedType[bedTypesData.length];

        for (int i = 0; i < bedTypesData.length; i++) {

            BedType tempBed;

            if (bedTypesData[i] == 1) tempBed = BedType.SINGLE;
            else if (bedTypesData[i] == 2) tempBed = BedType.DOBBLE;
            else if (bedTypesData[i] == 3) tempBed = BedType.KING_SIZE;
            else {
                throw new WrongOptionException("Wrong option in getBedType function");
            }

            bedTypes[i] = tempBed;

        }
            return repo.createNewRoom(number, bedTypes);
    }

    public List<Room> getRoomList() {

        return repo.getAll();
    }

    public void saveAll() {
        repo.saveAll();
    }

    public void readAll() {
        repo.readAll();
    }

    public void remove(int id) {
        repo.remove(id);
    }

    public void edit(int id, int number, int[] bedTypesData) {

        BedType[] bedTypes = new BedType[bedTypesData.length];

        for (int i = 0; i < bedTypesData.length; i++) {

            BedType tempBed;

            if (bedTypesData[i] == 1) tempBed = BedType.SINGLE;
            else if (bedTypesData[i] == 2) tempBed = BedType.DOBBLE;
            else if (bedTypesData[i] == 3) tempBed = BedType.KING_SIZE;
            else {
                throw new WrongOptionException("Wrong option in getBedType function");
            }
            bedTypes[i] = tempBed;
        }
        repo.edit(id, number, bedTypes);
    }

    public Room getRoomById(int id) {
        return repo.getRoomById(id);
    }

    public List<RoomDTO> getRoomDTOList() {

        List<RoomDTO> bedDTOList = new ArrayList<>();
        List<Room> bedList = repo.getAll();

        for (Room room : bedList) {
            RoomDTO roomDTO = room.convertToDTO();
            bedDTOList.add(roomDTO);
        }

        return bedDTOList;
    }

    public static RoomService getInstance() {
        return instance;
    }
}
