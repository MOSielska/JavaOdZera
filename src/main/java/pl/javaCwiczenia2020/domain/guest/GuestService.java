package pl.javaCwiczenia2020.domain.guest;

import java.util.List;

public class GuestService {

    private final GuestRepository repo = new GuestRepository();

    public Guest createNewGuest(String firstName, String lastName, int age, Gender gender) {
        return this.repo.createNewGuest(firstName, lastName, age, gender);
    }

    public Gender getGender(boolean isFemale){

        Gender gender = Gender.MALE;
        if (isFemale) gender =  Gender.FEMALE;
        return gender;

    }

    public List<Guest> getGuestList() {

        return this.repo.getAll();
    }

    public void saveAll() {
        this.repo.saveAll();
    }

    public void readAll() {
        this.repo.readAll();
    }
}
