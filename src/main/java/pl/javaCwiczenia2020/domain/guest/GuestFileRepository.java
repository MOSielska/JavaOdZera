package pl.javaCwiczenia2020.domain.guest;

import pl.javaCwiczenia2020.domain.util.SystemUtils;
import pl.javaCwiczenia2020.exceptions.ReadWriteFileException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GuestFileRepository implements GuestRepository {

    private final List<Guest> guests = new ArrayList<>();
    private final static GuestRepository instance = new GuestFileRepository();

    private GuestFileRepository() {

    }

    @Override
    public Guest createNewGuest(String firstName, String lastName, int age, Gender gender) {

        Guest newGuest = new Guest(findNewId(), firstName, lastName, age, gender);
        this.guests.add(newGuest);
        return newGuest;
    }

    private Guest addExistingGuest(long id, String firstName, String lastName, int age, Gender gender) {

        Guest newGuest = new Guest(id, firstName, lastName, age, gender);
        this.guests.add(newGuest);
        return newGuest;
    }

    @Override
    public List<Guest> getAll() {
        return this.guests;
    }

    @Override
    public void saveAll() {

        String fileName = "guests.csv";

        Path file = Paths.get((SystemUtils.DIRECTORY_PATH).toString(), fileName);

        StringBuilder info = new StringBuilder();

        for (Guest guest : this.guests) {
            info.append(guest.toCSV());
        }

        try {
            Files.writeString(file, info.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new ReadWriteFileException(SystemUtils.DIRECTORY_PATH.toString(), "write", "guests file");
        }
    }

    @Override
    public void readAll() {

        String fileName = "guests.csv";

        Path file = Paths.get((SystemUtils.DIRECTORY_PATH).toString(), fileName);

        if (!Files.exists(file)) {
            return;
        }


        try {
            String data = Files.readString(file, StandardCharsets.UTF_8);
            String[] guestList = data.split(System.getProperty("line.separator"));

            for (String guestInfo : guestList) {
                String[] guestData = guestInfo.split(",");
                if ((guestData[0] == null) || guestData[0].trim().isEmpty()) {
                    continue;
                }
                int id = Integer.parseInt(guestData[0]);
                int age = Integer.parseInt(guestData[3]);
                Gender gender = Gender.valueOf(guestData[4]);

                addExistingGuest(id, guestData[1], guestData[2], age, gender);
            }

        } catch (IOException e) {
            throw new ReadWriteFileException(SystemUtils.DIRECTORY_PATH.toString(), "read", "guests file");
        }
    }

    private long findNewId() {
        long max = 0;

        for (Guest guest : this.guests) {
            if (guest.getId() > max) {
                max = guest.getId();
            }
        }
        return max + 1;
    }

    @Override
    public void remove(long id) {

        int index = -1;
        for (Guest guest : this.guests) {
            if (guest.getId() == id) {
                index = this.guests.indexOf(guest);
            }
        }
        if (index > -1) this.guests.remove(index);
    }

    @Override
    public void edit(long id, String firstName, String lastName, int age, Gender gender) {

        this.remove(id);
        this.addExistingGuest(id, firstName, lastName, age, gender);

    }

    @Override
    public Guest getGuestById(long id) {

        Guest foundGuest = null;
        for (Guest guest : guests) {
            if (guest.getId() == id) foundGuest = guest;
        }
        return foundGuest;
    }

    public static GuestRepository getInstance() {
        return instance;
    }
}


