import java.sql.*;

public class Reservation {
    String query = "SELECT MAX(id_κράτησης) AS max_id FROM Reservations";
    public static int getMaxId(Connection con) {
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT MAX(id_κράτησης) AS max_id FROM reservations");
            if (rs.next()) {
                return rs.getInt("max_id");  //max id_κράτησης
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;  // Default to 1 if no users exist
    }

    public static int getMaxTicketId(Connection con) {
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT MAX(id_εισιτηρίου) AS max_id FROM tickets");
            if (rs.next()) {
                return rs.getInt("max_id");  //max id_εισιτηρίου
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;  // Default to 1 if no users exist
    }

    /*public static int generateSeatId(Connection con) {
        try {
            // Query to find the next available seat ID
            String query = "SELECT id_θέσης FROM seats WHERE Διαθεσιμότητα = TRUE LIMIT 1";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int seatId = rs.getInt("id_θέσης");

                // Mark the seat as unavailable
                String updateQuery = "UPDATE seats SET Διαθεσιμότητα = FALSE WHERE id_θέσης = ?";
                PreparedStatement updateStmt = con.prepareStatement(updateQuery);
                updateStmt.setInt(1, seatId);
                updateStmt.executeUpdate();

                return seatId; // Return the available seat ID
            } else {
                throw new SQLException("No available seats found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

            return -1; // Return -1 if no seat is available
        }*/
    public static void addNewReservation(Connection con, int user_id, int event_id, int number_of_tickets, Date date_reservation, float price, boolean google_apple_pay,boolean paypal, boolean revolut, boolean credit_debit_card) {
        // Add new reservation
        try {
            int id = getMaxId(con) + 1;
            String query = "INSERT INTO reservations (id_κράτησης, id_πελάτη, id_εκδήλωσης, Αριθμός_Εισιτηρίων, Ημερομηνία_Κράτησης, Ποσό_Πληρωμής, Google_Apple_Pay, PayPal, Revolut, Credit_Debit_Card) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.setInt(2, user_id);
            preparedStmt.setInt(3, event_id);
            preparedStmt.setInt(4, number_of_tickets);
            preparedStmt.setDate(5, date_reservation);
            preparedStmt.setFloat(6, price);
            preparedStmt.setBoolean(7, google_apple_pay);
            preparedStmt.setBoolean(8, paypal);
            preparedStmt.setBoolean(9, revolut);
            preparedStmt.setBoolean(10, credit_debit_card);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*for(int i = 0 ; i < number_of_tickets; i++) {
            int ticketId = getMaxTicketId(con) + 1;

            try {
                String query = "INSERT INTO tickets (id_κράτησης, Τιμή, Διαθεσιμότητα,id_πελάτη, id_θέσης) VALUES (?, ?)";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                    preparedStmt.setInt(1, getMaxId(con) +1);
                    preparedStmt.setString(2, ticket_type);

                    preparedStmt.execute();

                } catch(SQLException e){
                    e.printStackTrace();
                }
        }*/

    }
}