package pl.javaCwiczenia2020.domain.room;

import org.junit.jupiter.api.Test;
import pl.javaCwiczenia2020.domain.ObjectPool;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomServiceTest {

    @Test
    public void getBedTypesFromIntBedsArrayTest() {

        RoomService roomService = ObjectPool.getRoomService();
        int[] bedTypesArr = new int[]{1, 2, 3};

        List<BedType> bedTypes =  roomService.getBedTypes(bedTypesArr);

        assertEquals(bedTypes.size(), 3);
        assertEquals(bedTypes.get(0), BedType.SINGLE);
        assertEquals(bedTypes.get(1), BedType.DOBBLE);
        assertEquals(bedTypes.get(2), BedType.KING_SIZE);
    }
}
