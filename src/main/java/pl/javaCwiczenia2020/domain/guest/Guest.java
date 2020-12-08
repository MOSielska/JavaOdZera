package pl.javaCwiczenia2020.domain.guest;

public class Guest {

    private final String firstName;
    private final String lastName;
    private final int age;
    private final Gender gender;

    Guest(String fName, String lName, int nAge, Gender gender) {
        this.firstName = fName;
        this.lastName = lName;
        this.age = nAge;
        this.gender = gender;
    }

   public String getInfo() {
        return String.format("Utworzono gościa: %s %s (%d) %s", this.firstName, this.lastName, this.age, this.gender.toString());
   }


}
