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
    public static void addNewReservation(Connection con, int user_id, int event_id, int number_of_tickets, Date date_reservation, float price, boolean google_apple_pay, boolean paypal, boolean revolut, boolean credit_debit_card, String ticket_type) {
        // Add new reservation
        try {
                String query = "INSERT INTO reservations (id_κράτησης, id_πελάτη, id_εκδήλωσης, Αριθμός_Εισιτηρίων, Ημερομηνία_Κράτησης, Ποσό_Πληρωμής, Google_Apple_Pay, PayPal, Revolut, Credit_Debit_Card) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setInt(1, getMaxId(con) + 1);
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

            } catch(SQLException e){
                e.printStackTrace();
            }

        /*for(int i = 0 ; i < number_of_tickets; i++) {
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