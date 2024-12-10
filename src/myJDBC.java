import java.sql.*;

public class MyJDBC {
    public enum EventType {
        Συναυλία, Θέατρο, Χορός
    }

    public enum ConcertType {
        Έντεχνο, Ποπ, Λαϊκό, Παραδοσιακή, Τραπ, Ραπ, Ροκ, Κλασσική
    }

    public enum TheaterType {
        Τραγωδία, Όπερα, Κωμωδία, Δράμα
    }

    public enum DanceType {
        Παραδοσιακοί_Χοροί, Μπαλέτο
    }

    public enum SeatType {
        Backstage, VIP, Γενική_Είσοδος
    }


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = new String("jdbc:mysql://localhost");
        String databaseName = new String("test");
        int port = 3306;
        String username = new String("root");
        String password = new String("");

        Connection con = DriverManager.getConnection(url + ":" + port + "/" + databaseName + "?characterEncoding=UTF-8", username, password);
        
        createEventsTable(con);
        createConcertTableSQL(con);
        createTheaterTableSQL(con);
        createDanceTableSQL(con);
        createUserTableSQL(con);
        SeatTypeTableSQL(con);
        createTicketTableSQL(con);
        createBackstageTableSQL(con);
        createVipTableSQL(con);
        createGenikhEisodosTableSQL(con);
        createReservationTableSQL(con);

        insertTestData(con);

        con.close();
    }

    public static void createEventsTable(Connection con) {
        String createEventTableSQL =    "CREATE TABLE IF NOT EXISTS Events (" +
                                        "id_εκδήλωσης INT PRIMARY KEY AUTO_INCREMENT, " +
                                        "Όνομα VARCHAR(255) NOT NULL, " +
                                        "Ημερομηνία DATE NOT NULL," +
                                        "Ώρα TIME NOT NULL, " +
                                        "Χωρητικότητα INT NOT NULL, " +
                                        "Είδος ENUM('Συναυλία', 'Θέατρο', 'Χορός') NOT NULL" +
                                        ") CHARSET=utf8mb4;";
        try (Statement stmt = con.createStatement()) {
            // Execute the SQL to create the table
            stmt.executeUpdate(createEventTableSQL);
            System.out.println("Table Events created successfully or already exists.");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    public static void createConcertTableSQL(Connection con) {
        String createEventTableSQL =    "CREATE TABLE IF NOT EXISTS Concerts (" +
                                        "id_εκδήλωσης INT PRIMARY KEY AUTO_INCREMENT, " +
                                        "Είδος ENUM ('Έντεχνο','Ποπ', 'Λαϊκό', 'Παραδοσιακή', 'Τραπ', 'Ραπ', 'Ροκ', 'Κλασσική') NOT NULL," +
                                        "FOREIGN KEY (id_εκδήλωσης) REFERENCES Events(id_εκδήλωσης)" +
                                        ") CHARSET=utf8mb4;";
        try (Statement stmt = con.createStatement()) {
            // Execute the SQL to create the table
            stmt.executeUpdate(createEventTableSQL);
            System.out.println("Table Concerts created successfully or already exists.");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    public static void createTheaterTableSQL(Connection con) {
        String createEventTableSQL =    "CREATE TABLE IF NOT EXISTS Theaters (" +
                                        "id_εκδήλωσης INT PRIMARY KEY, " +
                                        "Είδος ENUM ('Τραγωδία', 'Όπερα', 'Κωμωδία', 'Δράμα') NOT NULL," +
                                        "FOREIGN KEY (id_εκδήλωσης) REFERENCES Events(id_εκδήλωσης)" +
                                        ") CHARSET=utf8mb4;";
        try (Statement stmt = con.createStatement()) {
            // Execute the SQL to create the table
            stmt.executeUpdate(createEventTableSQL);
            System.out.println("Table Theaters created successfully or already exists.");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    public static void createDanceTableSQL(Connection con) {
        String createEventTableSQL =    "CREATE TABLE IF NOT EXISTS Dances (" +
                                        "id_εκδήλωσης INT PRIMARY KEY, " +
                                        "Είδος ENUM ('Παραδοσιακοί_Χοροί', 'Μπαλέτο') NOT NULL," +
                                        "FOREIGN KEY (id_εκδήλωσης) REFERENCES Events(id_εκδήλωσης)" +
                                        ") CHARSET=utf8mb4;";
        try (Statement stmt = con.createStatement()) {
            // Execute the SQL to create the table
            stmt.executeUpdate(createEventTableSQL);
            System.out.println("Table Dances created successfully or already exists.");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    public static void createUserTableSQL(Connection con) {
        String createEventTableSQL =    "CREATE TABLE IF NOT EXISTS Users (" +
                                        "id_πελάτη INT PRIMARY KEY AUTO_INCREMENT, " +
                                        "Ονοματεπώνυμο VARCHAR(255) NOT NULL, " +
                                        "Τηλέφωνο VARCHAR(255) NOT NULL, " +
                                        "Οδός VARCHAR(255) NOT NULL, " +
                                        "Αριθμός INT NOT NULL, " +
                                        "ΤΚ INT NOT NULL, " +
                                        "Ημερομηνία_Λήξης DATE NOT NULL, " +
                                        "CVV INT NOT NULL, " +
                                        "Αριθμός_Κάρτας VARCHAR(255) NOT NULL, " +
                                        "email VARCHAR(255) NOT NULL" +
                                        ") CHARSET=utf8mb4; ";
        try (Statement stmt = con.createStatement()) {
            // Execute the SQL to create the table
            stmt.executeUpdate(createEventTableSQL);
            System.out.println("Table Users created successfully or already exists.");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    public static void SeatTypeTableSQL (Connection con){
        String createEventTableSQL =    "CREATE TABLE IF NOT EXISTS SeatTypes (" +
                                        "id_θέσης INT PRIMARY KEY, " +
                                        "Είδος ENUM ('Backstage', 'VIP', 'Γενική_Είσοδος') NOT NULL" +
                                        ") CHARSET=utf8mb4; ";
        try (Statement stmt = con.createStatement()) {
            // Execute the SQL to create the table
            stmt.executeUpdate(createEventTableSQL);
            System.out.println("Table SeatTypes created successfully or already exists.");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

        public static void createTicketTableSQL (Connection con){
            String createEventTableSQL =    "CREATE TABLE IF NOT EXISTS Tickets (" +
                                            "id_εισιτηρίου INT PRIMARY KEY AUTO_INCREMENT, " +
                                            "Τιμή FLOAT NOT NULL, " +
                                            "Διαθεσιμότητα BOOLEAN NOT NULL, " +
                                            "id_πελάτη INT NOT NULL, " +
                                            "id_θέσης INT NOT NULL, " +
                                            "FOREIGN KEY (id_πελάτη) REFERENCES Users(id_πελάτη), " +
                                            "FOREIGN KEY (id_θέσης) REFERENCES SeatTypes(id_θέσης) " +
                                            ") CHARSET=utf8mb4; ";
            try (Statement stmt = con.createStatement()) {
                // Execute the SQL to create the table
                stmt.executeUpdate(createEventTableSQL);
                System.out.println("Table Tickets created successfully or already exists.");
            } catch (SQLException e) {
                System.err.println("Error creating table: " + e.getMessage());
            }
        }

        public static void createBackstageTableSQL (Connection con){
            String createEventTableSQL =    "CREATE TABLE IF NOT EXISTS Backstage (" +
                                            "id_εισιτηρίου INT PRIMARY KEY, " +
                                            "MeetAndGreet BOOLEAN NOT NULL, " +
                                            "FOREIGN KEY (id_εισιτηρίου) REFERENCES Tickets(id_εισιτηρίου) " +
                                            ") CHARSET=utf8mb4; ";
            try (Statement stmt = con.createStatement()) {
                // Execute the SQL to create the table
                stmt.executeUpdate(createEventTableSQL);
                System.out.println("Table Backstage created successfully or already exists.");
            } catch (SQLException e) {
                System.err.println("Error creating table: " + e.getMessage());
            }
        }

        public static void createVipTableSQL (Connection con){
            String createEventTableSQL =    "CREATE TABLE IF NOT EXISTS VIP (" +
                                            "id_εισιτηρίου INT PRIMARY KEY, " +
                                            "Premium BOOLEAN NOT NULL, " +
                                            "FOREIGN KEY (id_εισιτηρίου) REFERENCES Tickets(id_εισιτηρίου) " +
                                            ") CHARSET=utf8mb4; ";
            try (Statement stmt = con.createStatement()) {
                // Execute the SQL to create the table
                stmt.executeUpdate(createEventTableSQL);
                System.out.println("Table VIP created successfully or already exists.");
            } catch (SQLException e) {
                System.err.println("Error creating table: " + e.getMessage());
            }
        }

        public static void createGenikhEisodosTableSQL(Connection con) {                          // :^)
            String createEventTableSQL =    "CREATE TABLE IF NOT EXISTS GenikhEisodos (" +
                                            "id_εισιτηρίου INT PRIMARY KEY, " +
                                            "Απλή_Θέση BOOLEAN NOT NULL, " +
                                            "FOREIGN KEY (id_εισιτηρίου) REFERENCES Tickets(id_εισιτηρίου) " +
                                            ") CHARSET=utf8mb4; ";
            try (Statement stmt = con.createStatement()) {
                // Execute the SQL to create the table
                stmt.executeUpdate(createEventTableSQL);
                System.out.println("Table VIP created successfully or already exists.");
            } catch (SQLException e) {
                System.err.println("Error creating table: " + e.getMessage());
            }
        }

        public static void createReservationTableSQL(Connection con) {
            String createEventTableSQL =    "CREATE TABLE IF NOT EXISTS Reservations (" +
                                            "id_κράτησης INT PRIMARY KEY AUTO_INCREMENT, " +
                                            "id_πελάτη INT NOT NULL, " +
                                            "id_εκδήλωσης INT NOT NULL, " +
                                            "Αριθμός_Εισιτηρίων INT NOT NULL, " +
                                            "Ημερομηνία_Κράτησης DATE NOT NULL, " +
                                            "Ποσό_Πληρωμής FLOAT NOT NULL, " +
                                            "Google_Apple_Pay BOOLEAN NOT NULL, " +
                                            "PayPal BOOLEAN NOT NULL, " +
                                            "Revolut BOOLEAN NOT NULL, " +
                                            "Credit_Debit_Card BOOLEAN NOT NULL, " +
                                            "FOREIGN KEY (id_πελάτη) REFERENCES Users(id_πελάτη), " +
                                            "FOREIGN KEY (id_εκδήλωσης) REFERENCES Events(id_εκδήλωσης), " +
                                            "CHECK ( (" +
                                            "Google_Apple_Pay + PayPal + Revolut + Credit_Debit_Card) <= 1" + // Enforces only one TRUE at a time
                                            ") " +
                                            ") CHARSET=utf8mb4; ";
            try (Statement stmt = con.createStatement()) {
                // Execute the SQL to create the table
                stmt.executeUpdate(createEventTableSQL);
                System.out.println("Table Reservations created successfully or already exists.");
            } catch (SQLException e) {
                System.err.println("Error creating table: " + e.getMessage());
            }
        }

    public static void insertTestData(Connection con) {
        try (Statement stmt = con.createStatement()) {

            stmt.executeUpdate("DELETE FROM Reservations;");
            stmt.executeUpdate("DELETE FROM Tickets;");
            stmt.executeUpdate("DELETE FROM Users;");
            stmt.executeUpdate("DELETE FROM Concerts;");
            stmt.executeUpdate("DELETE FROM Events;");


            /*// Insert into Events
            String insertEventSQL = "INSERT INTO Events (id_εκδήλωσης, Όνομα, Ημερομηνία, Ώρα, Χωρητικότητα, Είδος) VALUES " +
                    "(1, 'Συναυλία Pop', '2024-12-25', '20:00:00', 1000, 'Συναυλία')";
            stmt.executeUpdate(insertEventSQL);

            // Insert into Concerts
            String insertConcertSQL = "INSERT INTO Concerts (id_εκδήλωσης, Είδος) VALUES (1, 'Ποπ')";
            stmt.executeUpdate(insertConcertSQL);

            // Insert into Users
            String insertUser1SQL = "INSERT INTO Users (id_πελάτη, Ονοματεπώνυμο, Τηλέφωνο, Οδός, Αριθμός, ΤΚ, Ημερομηνία_Λήξης, CVV, Αριθμός_Κάρτας, email) VALUES " +
                    "(1, 'Γιάννης Παπαδόπουλος', '2101234567', 'Σοφοκλή Βενιζέλου', 123, 54623, '2025-12-31', 123, '1234567812345678', 'giannis@example.com')";
            stmt.executeUpdate(insertUser1SQL);

            // Insert into Users
            String insertUser2SQL = "INSERT INTO Users (id_πελάτη, Ονοματεπώνυμο, Τηλέφωνο, Οδός, Αριθμός, ΤΚ, Ημερομηνία_Λήξης, CVV, Αριθμός_Κάρτας, email) VALUES " +
                    "(2, 'Γιάννης Μαθ', '2101234567', 'Σοφοκλή Βενιζέλου', 123, 54623, '2025-12-31', 123, '1234567812345678', 'giannis@example.com')";
            stmt.executeUpdate(insertUser2SQL);

            //Insert into Reservations
            String insertReservationSQL = "INSERT INTO Reservations (id_κράτησης, id_πελάτη, id_εκδήλωσης, Αριθμός_Εισιτηρίων, Ημερομηνία_Κράτησης, Ποσό_Πληρωμής, Google_Apple_Pay, PayPal, Revolut, Credit_Debit_Card) VALUES " +
                    "(1, 1, 1, 2, '2024-12-01', 50.0, 1, 0, 0, 0)";
            stmt.executeUpdate(insertReservationSQL);

            //Insert into Reservations
            String insertReservation2SQL = "INSERT INTO Reservations (id_κράτησης, id_πελάτη, id_εκδήλωσης, Αριθμός_Εισιτηρίων, Ημερομηνία_Κράτησης, Ποσό_Πληρωμής, Google_Apple_Pay, PayPal, Revolut, Credit_Debit_Card) VALUES " +
                    "(2, 2, 1, 2, '2024-12-01', 50.0, 1, 0, 0, 0)";
            stmt.executeUpdate(insertReservation2SQL);
            */

        } catch (SQLException e) {
            System.err.println("Error inserting test data: " + e.getMessage());
        }
    }
    }