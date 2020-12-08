package pl.javaCwiczenia2020.domain.guest;

public class GuestService {

    private final GuestRepository repo = new GuestRepository();

    public Guest createNewGuest(String firstName, String lastName, int age, Gender gender) {
        return repo.createNewGuest(firstName, lastName, age, gender);
    }

    public Gender getGender(boolean isFemale){

        Gender gender = Gender.MALE;
        if (isFemale) gender =  Gender.FEMALE;
        return gender;

    }
}
