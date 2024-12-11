import javax.swing.*;
import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = new String("jdbc:mysql://localhost");
        String databaseName = new String("project");
        int port = 3306;
        String username = new String("root");
        String password = new String("");

        Connection con = DriverManager.getConnection(url + ":" + port + "/" + databaseName + "?characterEncoding=UTF-8", username, password);

        //dropAvailabilityColumn(con);

        //User.registerUser(con,"Παναγιώτης Μπερμπερίδης" ,"+306985959323", "Λεωφόρος Μίνωος", 36, 71303, Date.valueOf("2025-01-25"), 281,"53567100454569696", "panosber01@gmail.com");
        //User.registerUser(con,"Λοΐζος Λαϊνίδης", "+306978152324", "Λεοφόρως Μίνωος", 36, 71303, Date.valueOf("2025-01-25"), 281,"53567100454569696", "loizospaok@gmail.com");
        //Event.addNewEvent(con, "Συναυλία Light", Date.valueOf("2025-07-23"), Time.valueOf("21:00:00"), 10000, "Συναυλία", "Τραπ");
        //Event.addNewEvent(con, "Nutcracker", Date.valueOf("2024-12-31"), Time.valueOf("21:00:00"), 500, "Χορός", "Μπαλέτο");
        //Reservation.addNewReservation(con, 3, 3, 2, Date.valueOf("2024-12-11"), 30, false, false, false, true);
    }
    /*public static void dropAvailabilityColumn(Connection con) {
        String dropColumnSQL = "ALTER TABLE tickets DROP COLUMN Διαθεσιμότητα;";

        try (Statement stmt = con.createStatement()) {
            // Execute the SQL to drop the column
            stmt.executeUpdate(dropColumnSQL);
            System.out.println("Column 'Διαθεσιμότητα' dropped successfully.");
        } catch (SQLException e) {
            System.err.println("Error dropping column: " + e.getMessage());
        }
    }*/
}