import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

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

        if (option == 1) {
            System.out.println("Wybrano opcję 1 - dodaj nowego gościa");
            try {
                System.out.println("Podaj imię");
                String name = scanner.next();
                System.out.println("Podaj nazwisko");
                String lastName = scanner.next();
                System.out.println("Podaj wiek");
                int age = scanner.nextInt();
                Guest newGuest = new Guest(name, lastName, age);
                System.out.println("Utworzono gościa: " + name + " " + lastName);

            } catch (Exception e) {
                System.err.println("Niepoprawne dane");
                e.printStackTrace();
            }
        } else if (option == 2) {
            System.out.println("Wybrano opcję 2 - dodaj nowy pokój");
            try {
                System.out.println("Podaj numer pokoju");
                int number = scanner.nextInt();
                System.out.println("Podaj ilość łóżek w pokoju");
                int bedNumber = scanner.nextInt();
                Room newRoom = new Room(number, bedNumber);
                System.out.println("Utworzono pokój nr: " + number);
            } catch (Exception e) {
                System.err.println("Nieprawidłowe dane");
                e.printStackTrace();
            }
        } else {
            System.out.println("Brak opcji");
        }

    }
}
