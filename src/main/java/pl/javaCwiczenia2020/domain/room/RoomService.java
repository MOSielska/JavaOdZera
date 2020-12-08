package pl.javaCwiczenia2020.domain.room;

import pl.javaCwiczenia2020.exceptions.WrongOptionException;

public class RoomService {

    private final RoomRepository repo = new RoomRepository();

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
}
