import java.sql.*;

public class User {
    String query = "SELECT MAX(id_πελάτη) AS max_id FROM users";
    public static int getMaxId(Connection con) {
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT MAX(id_πελάτη) AS max_id FROM users");
            if (rs.next()) {
                return rs.getInt("max_id");  //max user_id
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;  // Default to 1 if no users exist
    }

    public static void registerUser(Connection con,String user_name, String user_phone, String user_address, int user_address_number, int user_address_zip, Date expiration_date, int cvv, String card_number, String email) {
        // Register user
        try {
            String query = "INSERT INTO users (id_πελάτη, Ονοματεπώνυμο, Τηλέφωνο, Οδός, Αριθμός, ΤΚ, Ημερομηνία_Λήξης, CVV, Αριθμός_Κάρτας, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, getMaxId(con) + 1);
            preparedStmt.setString(2, user_name);
            preparedStmt.setString(3, user_phone);
            preparedStmt.setString(4, user_address);
            preparedStmt.setInt(5, user_address_number);
            preparedStmt.setInt(6, user_address_zip);
            preparedStmt.setDate(7, expiration_date);
            preparedStmt.setInt(8, cvv);
            preparedStmt.setString(9, card_number);
            preparedStmt.setString(10, email);

            preparedStmt.execute();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}