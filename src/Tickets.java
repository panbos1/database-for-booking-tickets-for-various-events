import java.sql.*;
import java.util.ArrayList;

public class Tickets {
    String query = "SELECT MAX(id_εισιτηρίου) AS max_id FROM tickets";
    public static int getMaxId(Connection con) {
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

    private static int getMaxTicketId(Connection con) {
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT MAX(id_εισιτηρίου) AS max_id FROM Tickets");
            if (rs.next()) {
                return rs.getInt("max_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Default to 0 if no tickets exist
    }

    public static void generateTickets(Connection con, int event_capacity, int user_id,String event_type) throws SQLException {
        // Generate tickets
        for (int i = 1; i <= event_capacity; i++) {// Add seat types for each ticket
            int ticket_id = getMaxTicketId(con) + 1;
            String seat_type = Event.getSeatType(con, i, event_type);
            float price = getPrice(ticket_id, con, event_type, seat_type);
            addNewTicket(con, ticket_id, price, true, user_id, i, event_type, seat_type);
        }
    }

    public static float getPrice(int ticket_id, Connection con, String event_type, String seat_type) throws SQLException  {
        float price = 0.0f;
        if (event_type.equals("Θέατρο") || event_type.equals("Χορός")) {
            if (seat_type.equals("Γενική_Είσοδος")) {
                price = 5.0f;
            }
        else if (event_type.equals("Συναυλία")) {
            if (seat_type.equals("Vip")) {
                price = 10.0f;
            } else if (seat_type.equals("Backstage")) {
                price = 20.0f;
            } else if (seat_type.equals("Γενική_Είσοδος")) {
                price = 5.0f;
            }
        }
        }
        return price;
    }

    public static void addNewTicket(Connection con, int ticket_id, float price, boolean availability, int user_id, int seat_id,String event_type, String seat_type) {
        try {
            int id = getMaxTicketId(con) + 1;
            String query = "INSERT INTO tickets (id_εισιτηρίου, Τιμή, Διαθεσιμότητα, id_πελάτη, id_θέσης) VALUES (?,?,?,?,?)";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.setFloat(2, price);
            preparedStmt.setInt(3, 1);
            preparedStmt.setInt(4, user_id);
            preparedStmt.setInt(5, seat_id);
            preparedStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
    }