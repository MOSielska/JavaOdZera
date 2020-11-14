public class Guest {

    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;

    public Guest(String fName, String lName, int nAge, Gender gender) {
        this.firstName = fName;
        this.lastName = lName;
        this.age = nAge;
        this.gender = gender;
    }

   public String getInfo() {
        return String.format("Utworzono gościa: %s %s (%d) %s", this.firstName, this.lastName, this.age, this.gender.toString());
   }


}
