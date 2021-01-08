package pl.javaCwiczenia2020.ui.text;

import pl.javaCwiczenia2020.domain.ObjectPool;
import pl.javaCwiczenia2020.domain.guest.Gender;
import pl.javaCwiczenia2020.domain.guest.Guest;
import pl.javaCwiczenia2020.domain.guest.GuestService;
import pl.javaCwiczenia2020.domain.reservation.ReservationService;
import pl.javaCwiczenia2020.domain.room.Room;
import pl.javaCwiczenia2020.domain.room.RoomService;
import pl.javaCwiczenia2020.domain.util.Properties;
import pl.javaCwiczenia2020.exceptions.OnlyNumberException;
import pl.javaCwiczenia2020.exceptions.WrongOptionException;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TextUI {

    private final GuestService guestService = ObjectPool.getGuestService();
    private final RoomService roomService = ObjectPool.getRoomService();
    private final ReservationService reservationService = ObjectPool.getReservationService();


    private void readNewGuestData(Scanner scanner) {
        System.out.println("Wybrano opcję 1 - dodaj nowego gościa");
        try {
            System.out.println("Podaj imię");
            String name = scanner.next();
            System.out.println("Podaj nazwisko");
            String lastName = scanner.next();
            System.out.println("Podaj wiek");
            int age = scanner.nextInt();
            Gender gender = guestService.getGender(readGenderData(scanner));
            Guest newGuest = guestService.createNewGuest(name, lastName, age, gender);
            System.out.println(newGuest.getInfo());
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Wrong data - characters instead of numbers in createNewGuest function");
        }


    }

    private boolean readGenderData(Scanner scanner) {
        System.out.println("Wybierz płeć \n1 - żeńska \n2 - męska");
        try {
            int choice = scanner.nextInt();
            if(choice == 1) return true;
            else if (choice == 2) return false;
            else {
                throw new WrongOptionException("Wrong choice in getGender function");
            }
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Wrong data - characters instead of numbers in getGender function");
        }
    }

    private void readNewRoomData(Scanner scanner) {
        System.out.println("Wybrano opcję 2 - dodaj nowy pokój");
        try {
            System.out.println("Podaj numer pokoju");
            int number = scanner.nextInt();
            int[] bedTypes = bedTypesData(scanner);
            Room newRoom = roomService.createNewRoom(number, bedTypes);
            System.out.println(newRoom.getInfo());
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Wrong data - characters instead of numbers in createNewRoom function");
        }

    }

    static private int[] bedTypesData(Scanner scanner){
     try {
        System.out.println("Ile łóżek w pokoju?");
        int quantity = scanner.nextInt();

        int bedTypes[] = new int[quantity];

        for (int i = 0; i < quantity; i++) {
            System.out.println("Oto dostępne typy łóżek:");
            System.out.println("1 - single bed");
            System.out.println("2 - dobble bed");
            System.out.println("3 - king size bed");
            System.out.println("Jake łóżko wybierasz?");

            int tempBedOption = scanner.nextInt();
            bedTypes[i] = tempBedOption;
        }
        return bedTypes;
    } catch (InputMismatchException e) {
        throw new OnlyNumberException("Wrong data - characters instead of numbers in getBedTypes function");
    }
}


    public void showSystemInfo() {
        System.out.println("Witam w systemie rezerwacji dla hotelu " + Properties.HOTEL_NAME);
        System.out.println("Aktualna wersja systemu: " + Properties.SYSTEM_VERSION);
        System.out.println("Wersja developerska: " + Properties.IS_DEVELOPER_VERSION);
    }


    public void showMainMenu(){

        Scanner scanner = new Scanner(System.in);

        try {
            performAction(scanner);
        } catch (WrongOptionException | OnlyNumberException e) {
            System.out.println("Wystąpił błąd.");
            System.out.println("Kod błędu: " + e.getErrorCode());
            System.out.println("Komunikat błędu: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Niezany błąd");
        }
    }

    private void performAction(Scanner scanner) {

        int option = -1;

        while(option!=0) {
            option = getUserOption(scanner);

            if (option == 1) {
                readNewGuestData(scanner);
            } else if (option == 2) {
                readNewRoomData(scanner);
            } else if (option == 3) {
                displayGuestList();
            } else if (option == 4) {
                displayRoomList();
            } else if (option == 5) {
                removeGuest(scanner);
            } else if (option == 6) {
                removeRoom(scanner);
            } else if (option == 7) {
                editGuest(scanner);
            } else if (option == 8) {
                editRoom(scanner);
            } else if (option == 9) {
              createReservation(scanner);
            } else if (option == 0) {
                System.out.println("Koniec");
                this.guestService.saveAll();
                this.roomService.saveAll();
                this.reservationService.saveAll();
            } else {
                throw new WrongOptionException("Wrong option in main menu");
            }
        }

    }

    private static int getUserOption(Scanner scanner) {
        System.out.println("Menu:");
        System.out.println("1 - Dodaj nowego gościa");
        System.out.println("2 - Dodaj nowy pokój");
        System.out.println("3 - Wypisz wszystkich gości");
        System.out.println("4 - Wypisz pokoje");
        System.out.println("5 - Usuń gośca");
        System.out.println("6 - Usuń pokój");
        System.out.println("7 - Edytuj dane gościa");
        System.out.println("8 - Edytuj dane pokoju");
        System.out.println("9 - Stwórz rezerwację");
        System.out.println("0 - Wyjście z aplikacji. Zapis danych.");
        System.out.println("Podaj numer opcji z menu...");

        int option;

        try {
            option = scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Wrong data - characters instead of numbers in getUserOption function");
        }
        return option;
    }

    private void displayRoomList(){

        for (Room room: roomService.getRoomList()) {
            System.out.println(room.getInfo());
        }
    }

    private void displayGuestList(){

        for (Guest guest : guestService.getGuestList()) {
            System.out.println(guest.getInfo());
        }
    }

    private void removeGuest(Scanner scanner){
        System.out.println("Podaj id gościa do usunięcia");

        try {
            int id = scanner.nextInt();
            this.guestService.remove(id);
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Wrong data - characters instead of numbers in removeGuest function");
        }


    }

    private void removeRoom(Scanner scanner){
        System.out.println("Podaj id pokoju do usunięcia");

        try {
            int id = scanner.nextInt();
            this.roomService.remove(id);
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Wrong data - characters instead of numbers in removeRoom function");
        }
    }

    private void editGuest(Scanner scanner) {

        System.out.println("Podaj id gościa do  edycji");

        try {
            int id = scanner.nextInt();
            System.out.println("Podaj imię");
            String name = scanner.next();
            System.out.println("Podaj nazwisko");
            String lastName = scanner.next();
            System.out.println("Podaj wiek");
            int age = scanner.nextInt();
            Gender gender = guestService.getGender(readGenderData(scanner));
            this.guestService.edit(id, name, lastName, age, gender);
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Wrong data - characters instead of numbers in editGuest function");
        }
    }

    private void editRoom(Scanner scanner) {

        System.out.println("Podaj id pokoju do edycji");

        try {
            int id = scanner.nextInt();
            System.out.println("Podaj numer pokoju");
            int number = scanner.nextInt();
            int[] bedTypes = bedTypesData(scanner);
            this.roomService.edit(id, number, bedTypes);
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Wrong data - characters instead of numbers in editRoom function");
        }


    }

    private void createReservation(Scanner scanner) {
        System.out.println("Podaj datę początku rezerwacji (DD.MM.YYYY");

        try {
            String dateFromAsString = scanner.next();
            LocalDate dateFrom = LocalDate.parse(dateFromAsString, Properties.DATE_FORMATER);
            System.out.println("Podaj datę końca rezerwacji (DD.MM.YYYY");
            String dateToAsString = scanner.next();
            LocalDate dateTo = LocalDate.parse(dateToAsString, Properties.DATE_FORMATER);
            System.out.println("Podaj id gościa");
            int guestId = scanner.nextInt();
            System.out.println("Podaj id pokoju");
            int roomId = scanner.nextInt();

            try {
                this.reservationService.createNewReservation(dateFrom, dateTo, guestId, roomId);
            } catch (IllegalArgumentException e) {
                System.out.println("Niepoprawne wprowadzenie dat");
            }

        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Wrong data - characters instead of numbers in createReservation function");
        }
    }

}


