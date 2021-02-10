package pl.javaCwiczenia2020.domain.guest;

import pl.javaCwiczenia2020.domain.guest.dto.GuestDTO;

public class Guest {

    private final long id;
    private  String firstName;
    private  String lastName;
    private  int age;
    private  Gender gender;

    Guest(long id, String fName, String lName, int nAge, Gender gender) {
        this.id = id;
        this.firstName = fName;
        this.lastName = lName;
        this.age = nAge;
        this.gender = gender;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getInfo() {
        return String.format("Gość: %d %s %s (%d) %s",
                this.id,
                this.firstName,
                this.lastName,
                this.age,
                this.gender.toString());
   }

   String toCSV(){

        return String.format("%d,%s,%s,%d,%s%s",
                this.id,
                this.firstName,
                this.lastName,
                this.age,
                this.gender.toString(),
                System.getProperty("line.separator"));

   }

    public long getId() {
        return id;
    }

    public GuestDTO convertToDTO() {

        return new GuestDTO(this.id,
                            this.firstName,
                            this.lastName,
                            this.age,
                            (this.gender).toString());
    }

    public String getName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }
}
