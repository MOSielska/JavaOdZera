package pl.javaCwiczenia2020.ui.gui;

public class GuestSelectionItem {

    private int id;
    private String firstName;
    private String lastName;

    public GuestSelectionItem(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.firstName, this.lastName);
    }
}
