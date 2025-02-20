import java.sql.*;
import java.util.Scanner;

public class CarManager {
    public static void manageCars(Scanner scanner) {
        while (true) {
            System.out.println("\n🚘 Car Management");
            System.out.println("1. Add Car");
            System.out.println("2. View Cars");
            System.out.println("3. Delete Car");
            System.out.println("4. Go Back");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addCar(scanner);
                case 2 -> viewCars();
                case 3 -> deleteCar(scanner);
                case 4 -> { return; }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void addCar(Scanner scanner) {
        System.out.print("Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Fuel Type: ");
        String fuelType = scanner.nextLine();
        System.out.print("Reg Number: ");
        String regNr = scanner.nextLine();
        System.out.print("Registration Date (YYYY-MM-DD): ");
        String regDate = scanner.nextLine();
        System.out.print("Car Group (Luxury, Family, Sport): ");
        String carGroup = scanner.nextLine();
        System.out.print("Odometer: ");
        int odometer = scanner.nextInt();
        scanner.nextLine();

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO cars (brand, model, fueltype, reg_nr, reg_date, car_group, Odometer_tracker) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, brand);
            stmt.setString(2, model);
            stmt.setString(3, fuelType);
            stmt.setString(4, regNr);
            stmt.setString(5, regDate);
            stmt.setString(6, carGroup);
            stmt.setInt(7, odometer);
            stmt.executeUpdate();
            System.out.println("✅ Car added successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void viewCars() {
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM cars")) {
            System.out.println("\n🚘 Available Cars:");
            while (rs.next()) {
                System.out.println(rs.getInt("car_id") + " | " +
                        rs.getString("brand") + " " + rs.getString("model") + " | " +
                        rs.getString("fueltype") + " | " + rs.getString("car_group"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void deleteCar(Scanner scanner) {
        System.out.print("Enter Car ID to delete: ");
        int carId = scanner.nextInt();
        scanner.nextLine();

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM cars WHERE car_id = ?")) {
            stmt.setInt(1, carId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Car deleted successfully!");
            } else {
                System.out.println("❌ No car found with that ID.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
