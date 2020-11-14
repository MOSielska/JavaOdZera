import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        showSystemInfo("Overlook", 1, true);

        Scanner scanner = new Scanner(System.in);

        int option = getUserOption(scanner);

        if (option == 1) {
            createNewGuest(scanner);
        } else if (option == 2) {
            createNewRoom(scanner);
        } else {
            System.out.println("Brak opcji");
        }

    }

    private static void showSystemInfo(String hotelName, int systemVersion, boolean isDeveloperVerison) {
        System.out.println("Witam w systemie rezerwacji dla hotelu " + hotelName);
        System.out.println("Aktualna wersja systemu: " + systemVersion);
        System.out.println("Wersja developerska: " + isDeveloperVerison);
    }

    private static int getUserOption (Scanner scanner) {
        System.out.println("Menu:");
        System.out.println("1 - Dodaj nowego gościa");
        System.out.println("2 - Dodaj nowy pokój");
        System.out.println("Podaj numer opcji z menu...");

        int option = 0;

        try {
            option = scanner.nextInt();
        } catch (Exception e) {
            System.err.println("Niepoprawny wybór.");
            e.printStackTrace();
        }
        return option;
    }

    private static Guest createNewGuest(Scanner scanner) {
        System.out.println("Wybrano opcję 1 - dodaj nowego gościa");
        try {
            System.out.println("Podaj imię");
            String name = scanner.next();
            System.out.println("Podaj nazwisko");
            String lastName = scanner.next();
            System.out.println("Podaj wiek");
            int age = scanner.nextInt();
            Guest newGuest = new Guest(name, lastName, age);
            System.out.println(newGuest.getInfo());
            return newGuest;

        } catch (Exception e) {
            System.err.println("Niepoprawne dane");
            e.printStackTrace();
            return null;
        }
    }

    private static Room createNewRoom(Scanner scanner){
        System.out.println("Wybrano opcję 2 - dodaj nowy pokój");
        try {
            System.out.println("Podaj numer pokoju");
            int number = scanner.nextInt();
            System.out.println("Podaj ilość łóżek w pokoju");
            int bedNumber = scanner.nextInt();
            Room newRoom = new Room(number, bedNumber);
            System.out.println(newRoom.getInfo());
            return newRoom;
        } catch (Exception e) {
            System.err.println("Nieprawidłowe dane");
            e.printStackTrace();
            return null;
        }
    }
}
