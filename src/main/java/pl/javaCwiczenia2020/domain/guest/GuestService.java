package pl.javaCwiczenia2020.domain.guest;

import pl.javaCwiczenia2020.domain.ObjectPool;
import pl.javaCwiczenia2020.domain.guest.dto.GuestDTO;

import java.util.ArrayList;
import java.util.List;

public class GuestService {

    private final GuestRepository repo = ObjectPool.getGuestRepository();
    private final static GuestService instance = new GuestService();

    private GuestService() {

    }

    public Guest createNewGuest(String firstName, String lastName, int age, Gender gender) {
        return repo.createNewGuest(firstName, lastName, age, gender);
    }

    public Gender getGender(boolean isFemale){

        Gender gender = Gender.MALE;
        if (isFemale) gender =  Gender.FEMALE;
        return gender;

    }

    public List<Guest> getGuestList() {

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

    public void edit(int id, String firstName, String lastName, int age, Gender gender) {
        repo.edit(id, firstName, lastName, age, gender);
    }

    public Guest getGuestById(int id) {
        return repo.getGuestById(id);
    }

    public List<GuestDTO> getGuestDTOList() {

        List<Guest> guestList = repo.getAll();
        List<GuestDTO> guestDTOList = new ArrayList<>();

        for (Guest guest : guestList) {
            guestDTOList.add(guest.convertToDTO());
        }

        return guestDTOList;
    }

    public static GuestService getInstance() {
        return instance;
    }
}
