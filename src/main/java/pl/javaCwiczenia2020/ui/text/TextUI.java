package pl.javaCwiczenia2020.ui.text;

import pl.javaCwiczenia2020.domain.guest.Gender;
import pl.javaCwiczenia2020.domain.guest.Guest;
import pl.javaCwiczenia2020.domain.guest.GuestService;
import pl.javaCwiczenia2020.domain.room.Room;
import pl.javaCwiczenia2020.domain.room.RoomService;
import pl.javaCwiczenia2020.exceptions.OnlyNumberException;
import pl.javaCwiczenia2020.exceptions.WrongOptionException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TextUI {

    private final GuestService guestService = new GuestService();
    private final RoomService roomService = new RoomService();

    private void readNewGuestData(Scanner scanner) {
        System.out.println("Wybrano opcję 1 - dodaj nowego gościa");
        try {
            System.out.println("Podaj imię");
            String name = scanner.next();
            System.out.println("Podaj nazwisko");
            String lastName = scanner.next();
            System.out.println("Podaj wiek");
            int age = scanner.nextInt();
            Gender gender = guestService.getGender(readGenderdata(scanner));
            Guest newGuest = guestService.createNewGuest(name, lastName, age, gender);
            System.out.println(newGuest.getInfo());
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Wrong data - characters instead of numbers in createNewGuest function");
        }


    }

    private boolean readGenderdata(Scanner scanner) {
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


    public void showSystemInfo(String hotelName, int systemVersion, boolean isDeveloperVerison) {
        System.out.println("Witam w systemie rezerwacji dla hotelu " + hotelName);
        System.out.println("Aktualna wersja systemu: " + systemVersion);
        System.out.println("Wersja developerska: " + isDeveloperVerison);
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
        } finally {
            System.out.println("Koniec");
        }
    }

    private void performAction(Scanner scanner) {

        int option = getUserOption(scanner);

        if (option == 1) {
            readNewGuestData(scanner);
        } else if (option == 2) {
            readNewRoomData(scanner);
        } else {
            throw new WrongOptionException("Wrong option in main menu");
        }

    }

    private static int getUserOption(Scanner scanner) {
        System.out.println("Menu:");
        System.out.println("1 - Dodaj nowego gościa");
        System.out.println("2 - Dodaj nowy pokój");
        System.out.println("Podaj numer opcji z menu...");

        int option;

        try {
            option = scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Wrong data - characters instead of numbersin getUserOption function");
        }
        return option;
    }


}


