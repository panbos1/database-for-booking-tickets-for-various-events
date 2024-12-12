import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = new String("jdbc:mysql://localhost");
        String databaseName = new String("project");
        int port = 3306;
        String username = new String("root");
        String password = new String("");

        Connection con = DriverManager.getConnection(url + ":" + port + "?characterEncoding=UTF-8", username, password);
        try {
            Statement stmt = con.createStatement();
            String dropDatabase = "DROP DATABASE IF EXISTS " + databaseName;
            stmt.executeUpdate(dropDatabase);
            System.out.println("Database '" + databaseName + "' dropped successfully.");


            String createDatabaseSQL = "CREATE DATABASE " + databaseName + " CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci";
            stmt.executeUpdate(createDatabaseSQL);
            System.out.println("Database '" + databaseName + "' created successfully.");

            String useDatabase = "USE " + databaseName + ";";
            stmt.executeUpdate(useDatabase);  // This selects the 'project' database to use.
            System.out.println("Database '" + databaseName + "' selected.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        MyJDBC.createEventsTable(con);
        MyJDBC.createConcertTableSQL(con);
        MyJDBC.createTheaterTableSQL(con);
        MyJDBC.createDanceTableSQL(con);
        MyJDBC.createUserTableSQL(con);
        MyJDBC.createSeatTypeTableSQL(con);
        MyJDBC.createTicketTableSQL(con);
        MyJDBC.createBackstageTableSQL(con);
        MyJDBC.createVipTableSQL(con);
        MyJDBC.createGenikhEisodosTableSQL(con);
        MyJDBC.createReservationTableSQL(con);

        insertTestData(con);

        con.close();

    }
    public static void insertTestData(Connection con) {
        try (Statement stmt = con.createStatement()) {

            stmt.executeUpdate("DELETE FROM Reservations;");
            stmt.executeUpdate("DELETE FROM Tickets;");
            stmt.executeUpdate("DELETE FROM Users;");
            stmt.executeUpdate("DELETE FROM Concerts;");
            stmt.executeUpdate("DELETE FROM Events;");
            stmt.executeUpdate("DELETE FROM Theaters;");
            stmt.executeUpdate("DELETE FROM Dances;");
            stmt.executeUpdate("DELETE FROM SeatTypes;");
            stmt.executeUpdate("DELETE FROM Backstage;");
            stmt.executeUpdate("DELETE FROM VIP;");
            stmt.executeUpdate("DELETE FROM GenikhEisodos");

            // Insert into Users
            String insertUser1SQL = "INSERT INTO Users (id_πελάτη, Ονοματεπώνυμο, Τηλέφωνο, Οδός, Αριθμός, ΤΚ, Ημερομηνία_Λήξης, CVV, Αριθμός_Κάρτας, email) VALUES " +
                                    "(1, 'Μαρία Κακουλίδου', '+306943678703', 'Ζωγράφου', 14, 71201, '2025-12-30', 140, '1234567812345679', 'mariakak2001@gmail.com')";
            stmt.executeUpdate(insertUser1SQL);
            User.registerUser(con,"Παναγιώτα Λάτση", "+306973715931", "Σήφακα", 8, 71306, Date.valueOf("2025-12-31"), 666, "1234567812345678", "pen_lts@yahoo.com");

            // Insert into Events
            Event.addNewEvent(con,"ELEFTHEROS TOUR", Date.valueOf("2024-12-25"), Time.valueOf("21:00:00"), 10, "Συναυλία","Ποπ");
            Event.addNewEvent(con,"H Κοκκινοσκουφίτσα", Date.valueOf("2024-12-28"), Time.valueOf("18:00:00"), 5, "Θέατρο","Κωμωδία");

            //Insert into Reservations
            Reservation.addNewReservation(con, 1,1,1,Date.valueOf("2024-12-11"), 50, true,false,false,false);
            Reservation.addNewReservation(con, 1,2,1,Date.valueOf("2024-12-12"), 20, false, true, false, false);


        } catch (SQLException e) {
            System.err.println("Error inserting test data: " + e.getMessage());
        }
    }
}
