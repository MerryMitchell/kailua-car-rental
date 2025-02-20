import java.sql.*;
import java.util.Scanner;

public class CustomerManager {
    public static void manageCustomers(Scanner scanner) {
        while (true) {
            System.out.println("\n👤 Customer Management");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Delete Customer");
            System.out.println("4. Go Back");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addCustomer(scanner);
                case 2 -> viewCustomers();
                case 3 -> deleteCustomer(scanner);
                case 4 -> { return; }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void addCustomer(Scanner scanner) {
        System.out.print("First Name: ");
        String fName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO customers (fName, lName, C_Email, C_tel) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, fName);
            stmt.setString(2, lName);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.executeUpdate();
            System.out.println("✅ Customer added successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    private static void viewCustomers() {
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM customers")) {
            System.out.println("\n👤 Registered Customers:");
            while (rs.next()) {
                System.out.println(rs.getInt("Customer_ID") + " | " +
                        rs.getString("fName") + " " + rs.getString("lName") + " | " +
                        rs.getString("C_Email") + " | " + rs.getString("C_tel"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    private static void deleteCustomer(Scanner scanner) {
        System.out.print("Enter Customer ID to delete: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM customers WHERE Customer_ID = ?")) {
            stmt.setInt(1, customerId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Customer deleted successfully!");
            } else {
                System.out.println("❌ No customer found with that ID.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
