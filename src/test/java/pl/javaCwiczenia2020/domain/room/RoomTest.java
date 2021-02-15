package pl.javaCwiczenia2020.domain.room;

import org.junit.jupiter.api.Test;
import pl.javaCwiczenia2020.domain.room.dto.RoomDTO;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RoomTest {

    @Test
    public void toCSVTest() {

        List<BedType> beds = Arrays.asList(BedType.values());
        Room room = new Room(1, 33, beds);

        String roomToCsv = room.toCSV();

        String csvTemplate = "1,33,SINGLE#DOBBLE#KING_SIZE" + System.getProperty("line.separator");
        assertEquals(csvTemplate, roomToCsv, "Porównanie formatów CSV");
    }

    @Test
    public void toCSVWithNullBedListTest() {

        Room room = new Room(1, 33, null);

        assertNotNull(room.getBeds());

        String csvTemplate = "1,33," + System.getProperty("line.separator");

        assertEquals(csvTemplate, room.toCSV(), "Porównanie formatów CSV przy pustej liście łóżek");

    }

    @Test
    public void convertToDTOTest() {

        List<BedType> beds = Arrays.asList(BedType.values());
        Room room = new Room(1, 33, beds);

        RoomDTO roomDTO = room.convertToDTO();

        assertEquals(roomDTO.getId(), 1);
        assertEquals(roomDTO.getNumber(), 33);
        assertEquals(roomDTO.getBedCount(), 3);
        assertEquals(roomDTO.getRoomSize(), 5);
        assertEquals(roomDTO.getBeds(), "SINGLE,DOBBLE,KING_SIZE,");
    }

    @Test
    public  void convertToDTOWithNullBedsTest() {

        Room room = new Room(1, 33, null);

        RoomDTO roomDTO = room.convertToDTO();

        assertEquals(roomDTO.getId(), 1);
        assertEquals(roomDTO.getNumber(), 33);
        assertEquals(roomDTO.getBedCount(), 0);
        assertEquals(roomDTO.getRoomSize(), 0);
        assertEquals(roomDTO.getBeds(), "");

    }
}
