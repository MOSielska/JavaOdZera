package pl.javaCwiczenia2020.domain.guest;

import org.junit.jupiter.api.Test;
import pl.javaCwiczenia2020.domain.guest.dto.GuestDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuestTest {

    @Test
    public void getNameTest() {

        Guest guest = new Guest(1,"Jane", "Doe", 44, Gender.FEMALE);

        String guestName = guest.getName();

        String getNameTemplate = "Jane Doe";
        assertEquals(guestName, getNameTemplate);
    }

    @Test
    public void toCSVTest() {

        Guest guest = new Guest(1,"Jane", "Doe", 44, Gender.FEMALE);

        String guestCSV = guest.toCSV();

        String guestCSVTemplate = "1,Jane,Doe,44,FEMALE"+System.getProperty("line.separator");
        assertEquals(guestCSVTemplate, guestCSV);
    }

    @Test
    public void convertToDTOTest() {

        Guest guest = new Guest(1,"Jane", "Doe", 44, Gender.FEMALE);

        GuestDTO guestDTO = guest.convertToDTO();

        assertEquals(guestDTO.getId(), 1);
        assertEquals(guestDTO.getAge(), 44);
        assertEquals(guestDTO.getFirstName(), "Jane");
        assertEquals(guestDTO.getLastName(), "Doe");
        assertEquals(guestDTO.getGender(), "FEMALE");
    }
}

