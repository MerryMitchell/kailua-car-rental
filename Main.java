import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n🚗 Kailua Car Rental System 🚗");
            System.out.println("1. Manage Cars");
            System.out.println("2. Manage Customers");
            System.out.println("3. Manage Rentals");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> CarManager.manageCars(scanner);
                case 2 -> CustomerManager.manageCustomers(scanner);
                case 3 -> RentalManager.manageRentals(scanner);
                case 4 -> {
                    System.out.println("Goodbye! 👋");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
