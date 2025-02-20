import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class RentalManager {
    public static void manageRentals(Scanner scanner){
        while (true) {
            System.out.println("Manage rental agreements");
            System.out.println("1. Create rental agreement");
            System.out.println("2. View rental agreements");
            System.out.println("3. Delete rental agreement");
            System.out.println("4. Go back");
            System.out.println("Choose on option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1 -> createRentalAgreement(scanner);
                case 2 -> viewRentals();
                case 3 -> deleteRentalAgreement(scanner);
                case 4 -> { return; }
                default ->
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void createRentalAgreement(Scanner scanner){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            System.out.println("Rental contract start date (YYYY-MM-DD HH:MM:SS): ");
            String startDateInput = scanner.nextLine();
            LocalDateTime from_datetime = LocalDateTime.parse(startDateInput, formatter);

            System.out.println("Rental contract end date (YYYY-MM-DD HH:MM:SS): ");
            String endDateInput = scanner.nextLine();
            LocalDateTime to_datetime = LocalDateTime.parse(endDateInput, formatter);
    }

    private static void viewRentals(){

    }

    private static void deleteRentalAgreement(Scanner scanner){

    }

}
