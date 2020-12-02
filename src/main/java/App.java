import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        showSystemInfo("Overlook", 1, true);

        Scanner scanner = new Scanner(System.in);

        try {
            performAction(scanner);
        } catch (WrongOptionException | OnlyNumberException e) {
            System.out.println("Wystąpił błąd.");
            System.out.println("Kod błędu: " + e.getErrorCode());
            System.out.println("Komunikat błędu: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Niezany błąd");
        } finally {
            System.out.println("Koniec");
        }
    }

    private static void performAction(Scanner scanner) {

        int option = getUserOption(scanner);

        if (option == 1) {
            createNewGuest(scanner);
        } else if (option == 2) {
            createNewRoom(scanner);
        } else {
            throw new WrongOptionException("Wrong option in main menu");
        }

    }

    private static void showSystemInfo(String hotelName, int systemVersion, boolean isDeveloperVerison) {
        System.out.println("Witam w systemie rezerwacji dla hotelu " + hotelName);
        System.out.println("Aktualna wersja systemu: " + systemVersion);
        System.out.println("Wersja developerska: " + isDeveloperVerison);
    }

    private static int getUserOption(Scanner scanner) {
        System.out.println("Menu:");
        System.out.println("1 - Dodaj nowego gościa");
        System.out.println("2 - Dodaj nowy pokój");
        System.out.println("Podaj numer opcji z menu...");

        int option = 0;

        try {
            option = scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Wrong data - characters instead of numbersin getUserOption function");
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
            throw new OnlyNumberException("Wrong data - characters instead of numbers in createNewGuest function");
        }
    }

    private static Room createNewRoom(Scanner scanner) {
        System.out.println("Wybrano opcję 2 - dodaj nowy pokój");
        try {
            System.out.println("Podaj numer pokoju");
            int number = scanner.nextInt();
            BedType[] bedTypes = getBedTypes(scanner);
            Room newRoom = new Room(number, bedTypes);
            System.out.println(newRoom.getInfo());
            return newRoom;
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Wrong data - characters instead of numbers in createNewRoom function");
        }
    }

    private static BedType[] getBedTypes(Scanner scanner) {

        try {
            System.out.println("Ile łóżek w pokoju?");
            int quantity = scanner.nextInt();

            BedType bedTypes[] = new BedType[quantity];

            for (int i = 0; i < quantity; i++) {
                System.out.println("Oto dostępne typy łóżek:");
                System.out.println("1 - single bed");
                System.out.println("2 - dobble bed");
                System.out.println("3 - king size bed");
                System.out.println("Jake łóżko wybierasz?");

                BedType tempBed;


                int choice = scanner.nextInt();
                if (choice == 1) tempBed = BedType.SINGLE;
                else if (choice == 2) tempBed = BedType.DOBBLE;
                else if (choice == 3) tempBed = BedType.KING_SIZE;
                else {
                    throw new WrongOptionException("Wrong option in getBedType function");
                }

                bedTypes[i] = tempBed;
            }
            return bedTypes;
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Wrong data - characters instead of numbers in getBedTypes function");
        }
    }

    private static Gender getGender(Scanner scanner) {
        System.out.println("Wybierz płeć \n1 - żeńska \n2 - męska");

        try {
            int choice = scanner.nextInt();
            if (choice == 1) return Gender.FEMALE;
            else if (choice == 2) return Gender.MALE;
            else {
                throw new WrongOptionException("Wrong choice in getGender function");
            }
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Wrong data - characters instead of numbers in getGender function");
        }
    }

}
