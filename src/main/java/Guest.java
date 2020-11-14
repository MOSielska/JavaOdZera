public class Guest {

    private String firstName;
    private String lastName;
    private int age;

    public Guest(String fName, String lName, int nAge) {
        this.firstName = fName;
        this.lastName = lName;
        this.age = nAge;
    }

   public String getInfo() {
        return String.format("Utworzono gościa: %s %s (%d)", this.firstName, this.lastName, this.age);
   }


}
