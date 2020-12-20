package pl.javaCwiczenia2020.domain.guest;

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

   public String toCSV(){

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
}
