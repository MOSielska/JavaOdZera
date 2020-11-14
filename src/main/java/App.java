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
            Gender gender = getGender(scanner);
            Guest newGuest = new Guest(name, lastName, age, gender);
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
            //System.out.println("Podaj ilość łóżek w pokoju");
            BeedType bedType = getBedType(scanner);
            Room newRoom = new Room(number, bedType);
            System.out.println(newRoom.getInfo());
            return newRoom;
        } catch (Exception e) {
            System.err.println("Nieprawidłowe dane");
            e.printStackTrace();
            return null;
        }
    }

    private static BeedType getBedType(Scanner scanner) {

        System.out.println("Oto dostępne typy łóżek:");
        System.out.println("1 - single bed");
        System.out.println("2 - dobble bed");
        System.out.println("3 - king size bed");
        System.out.println("Jake łóżko wybierasz?");

        try {
            int choice = scanner.nextInt();
            if (choice == 1) return BeedType.SINGLE;
            else if (choice == 2) return BeedType.DOBBLE;
            else if (choice == 3) return BeedType.KING_SIZE;
            else {
                System.out.println("Niepoprawne dane");
                return null;
            }
        } catch (Exception e) {
            System.err.println("Niepoprawne dane");
            e.printStackTrace();
            return null;
            }
        }

    private static Gender getGender(Scanner scanner) {
        System.out.println("Wybierz płeć \n1 - żeńska \n2 - męska");

        try {
            int choice = scanner.nextInt();
            if (choice == 1) return Gender.FEMALE;
            else if (choice == 2) return Gender.MALE;
            else {
                System.out.println("Niepoprawne dane");
                return null;
            }
        } catch (Exception e) {
            System.err.println("Niepoprawne dane");
            e.printStackTrace();
            return null;
        }
    }

}
