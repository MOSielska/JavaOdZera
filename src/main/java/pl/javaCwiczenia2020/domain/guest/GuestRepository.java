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

        Guest newGuest = new Guest(findNewId(), firstName, lastName, age, gender);
        guestList.add(newGuest);
        return newGuest;
    }

    Guest addExistingGuest(int id, String firstName, String lastName, int age, Gender gender) {

        Guest newGuest = new Guest(id, firstName, lastName, age, gender);
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

        if(!Files.exists(file)) {
            return;
        }


        try {
            String data = Files.readString(file, StandardCharsets.UTF_8);
            String[] guestList =  data.split(System.getProperty("line.separator"));

            for (String guestInfo : guestList) {
                String[] guestData = guestInfo.split(",");
                if((guestData[0] == null) || guestData[0].trim().isEmpty()) {
                    continue;
                }
                int id = Integer.parseInt(guestData[0]);
                int age =  Integer.parseInt(guestData[3]);
                Gender gender = Gender.valueOf(guestData[4]);

                addExistingGuest(id, guestData[1], guestData[2], age, gender);
            }

        } catch (IOException e) {
            throw new ReadWriteFileException(Properties.DIRECTORY_PATH.toString(), "read", "guests file");
        }
    }

    private int findNewId() {
        int max = 0;

        for (Guest guest : this.guestList) {
            if(guest.getId()>max) {
                max = guest.getId();
            }
        }
        return max + 1;
    }

    void remove(int id) {

        for (Guest guest : guestList) {
            if (guest.getId() == id) guestList.remove(guest);
        }
    }

    void edit(int id, String firstName, String lastName, int age, Gender gender) {

        this.remove(id);
        this.addExistingGuest(id, firstName, lastName, age, gender);

    }

    Guest getGuestById(int id) {

        Guest foundGuest = null;
        for (Guest guest : guestList) {
            if(guest.getId() == id) foundGuest = guest;
        }
        return foundGuest;
    }
}


