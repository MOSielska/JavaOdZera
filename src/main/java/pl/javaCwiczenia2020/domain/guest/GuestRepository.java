package pl.javaCwiczenia2020.domain.guest;

import pl.javaCwiczenia2020.domain.util.Properties;
import pl.javaCwiczenia2020.exceptions.ReadWriteFileException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GuestRepository {

    private final List<Guest> guestList = new ArrayList<>();

    Guest createNewGuest(String firstName, String lastName, int age, Gender gender) {

        Guest newGuest = new Guest(firstName, lastName, age, gender);
        guestList.add(newGuest);
        return newGuest;
    }

    List<Guest> getAll() {
        return this.guestList;
    }

    void saveAll() {

        String fileName = "guests.csv";

        Path file = Paths.get((Properties.DIRECTORY_PATH).toString(), fileName);

        StringBuilder info = new StringBuilder();

        for (Guest guest : this.guestList){
            info.append(guest.toCSV());
        }

        try {
            Files.writeString(file, info.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new ReadWriteFileException(Properties.DIRECTORY_PATH.toString(), "write", "guests file");
        }
    }

    void readAll() {

        String fileName = "guests.csv";

        Path file = Paths.get((Properties.DIRECTORY_PATH).toString(), fileName);

        try {
            String data = Files.readString(file, StandardCharsets.UTF_8);
            String[] guestList =  data.split(System.getProperty("line.separator"));

            for (String guestInfo : guestList) {
                String[] guestData = guestInfo.split(",");
                int age =  Integer.parseInt(guestData[2]);
                Gender gender = Gender.valueOf(guestData[3]);

                createNewGuest(guestData[0], guestData[1], age, gender);
            }

        } catch (IOException e) {
            throw new ReadWriteFileException(Properties.DIRECTORY_PATH.toString(), "read", "guests file");
        }
    }
}


