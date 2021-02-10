package pl.javaCwiczenia2020.domain.guest;

import pl.javaCwiczenia2020.domain.util.SystemUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GuestDatabaseRepository implements GuestRepository {

    private List<Guest> guests = new ArrayList<>();

    private static GuestRepository instance = new GuestDatabaseRepository();



    public static GuestRepository getInstance() {
        return instance;
    }

    @Override
    public Guest createNewGuest(String firstName, String lastName, int age, Gender gender) {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String insertGuestTemplate = "INSERT INTO GUESTS(FIRST_NAME, LAST_NAME, AGE, GENDER) VALUES('%s', '%s', %d, '%s')";
            String query = String.format(insertGuestTemplate, firstName, lastName, age, gender);
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();

            long newId = -1;
            while(rs.next()) {
                newId = rs.getLong(1);
            }
            statement.close();

            Guest newGuest = new Guest(newId, firstName, lastName, age, gender);
            this.guests.add(newGuest);
            return newGuest;
        } catch (SQLException throwables) {
            System.out.println("Błąd przy tworzeniu nowego gościa");
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public List<Guest> getAll() {
        return this.guests;
    }

    @Override
    public void saveAll() {

    }

    @Override
    public void readAll() {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM GUESTS");
            while(resultSet.next()) {
                long id = resultSet.getLong(1);
                String fName = resultSet.getString(2);
                String lName = resultSet.getString(3);
                int age = resultSet.getInt(4);
                Gender gender = Gender.valueOf(resultSet.getString(5));
                Guest newGuest = new Guest(id, fName, lName, age, gender);
                this.guests.add(newGuest);
            }
            statement.close();


        } catch (SQLException throwables) {
            System.out.println("Błąd przy wczytywaniu gości z bazy danych");
            throw new RuntimeException(throwables);
        }

    }

    @Override
    public void remove(long id) {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String removeGuestTemplate = "DELETE FROM GUESTS WHERE ID=%d";
            String removeGuestQuery = String.format(removeGuestTemplate, id);
            statement.execute(removeGuestQuery);
            statement.close();
            this.removeById(id);
        } catch (SQLException throwables) {
            System.out.println("Błąd przy usuwaniu gościa z bazy danych");
            throw new RuntimeException(throwables);
        }

    }

    private void removeById(long id) {
        int toRemoveIndex = -1;
        for (int i = 0; i < this.guests.size(); i++) {
            if (this.guests.get(i).getId() == id) {
                toRemoveIndex = i;
            }
        }
        this.guests.remove(toRemoveIndex);
    }

    @Override
    public void edit(long id, String firstName, String lastName, int age, Gender gender) {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String editGuestTemplate = "UPDATE GUESTS SET FIRST_NAME='%s', LAST_NAME='%s', AGE=%d, GENDER='%s' WHERE ID=%d";
            String editGuestQuery = String.format(editGuestTemplate, firstName, lastName, age, gender, id);
            statement.execute(editGuestQuery);
            statement.close();

            Guest guestToEdit = getGuestById(id);
            guestToEdit.setFirstName(firstName);
            guestToEdit.setLastName(lastName);
            guestToEdit.setAge(age);
            guestToEdit.setGender(gender);

        } catch (SQLException throwables) {
            System.out.println("Błąd przy edyjci gościa w bazie danych");
            throw new RuntimeException(throwables);
        }

    }

    @Override
    public Guest getGuestById(long id) {
        for (Guest guest : guests) {
            if(guest.getId() == id) {
                return guest;
            }
        }
        return null;
    }
}
