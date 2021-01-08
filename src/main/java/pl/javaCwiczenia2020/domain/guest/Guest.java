package pl.javaCwiczenia2020.domain.guest;

import pl.javaCwiczenia2020.domain.guest.dto.GuestDTO;

public class Guest {

    private final int id;
    private final String firstName;
    private final String lastName;
    private final int age;
    private final Gender gender;

    Guest(int id, String fName, String lName, int nAge, Gender gender) {
        this.id = id;
        this.firstName = fName;
        this.lastName = lName;
        this.age = nAge;
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

    public int getId() {
        return id;
    }

    public GuestDTO convertToDTO() {

        return new GuestDTO(this.id, this.firstName, this.lastName, this.age, (this.gender).toString());
    }

    public String getName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }
}
