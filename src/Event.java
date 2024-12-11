import java.sql.*;

public class Event {
    String query = "SELECT MAX(id_εκδήλωσης) AS max_id FROM Events";

    public static int getMaxId(Connection con) {
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT MAX(id_εκδήλωσης) AS max_id FROM events");
            if (rs.next()) {
                return rs.getInt("max_id");  //max id_εκδήλωσης
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;  // Default to 1 if no users exist
    }

    public static void addNewEvent(Connection con, String event_name, Date event_date, Time event_time, int event_capacity, String event_type, String event_type2) throws SQLException {
        // Add new event
        try {
            String query = "INSERT INTO events (id_εκδήλωσης, Όνομα, Ημερομηνία, Ώρα, Χωρητικότητα, Είδος) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, getMaxId(con) + 1);
            preparedStmt.setString(2, event_name);
            preparedStmt.setDate(3, event_date);
            preparedStmt.setTime(4, event_time);
            preparedStmt.setInt(5, event_capacity);
            preparedStmt.setString(6, event_type);

            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (event_type.equals("Συναυλία")) {
            String query1 = "INSERT INTO concerts (id_εκδήλωσης, Είδος) VALUES (?, ?)";
            PreparedStatement preparedStmt1 = con.prepareStatement(query1);
            preparedStmt1.setInt(1, getMaxId(con));
            preparedStmt1.setString(2, event_type2);

            preparedStmt1.execute();
        }
        else if (event_type.equals("Θέατρο")) {
            String query1 = "INSERT INTO theaters (id_εκδήλωσης, Είδος) VALUES (?, ?)";
            PreparedStatement preparedStmt1 = con.prepareStatement(query1);
            preparedStmt1.setInt(1, getMaxId(con));
            preparedStmt1.setString(2, event_type2);

            preparedStmt1.execute();
        }
        else if (event_type.equals("Χορός")) {
            String query1 = "INSERT INTO dances (id_εκδήλωσης, Είδος) VALUES (?, ?)";
            PreparedStatement preparedStmt1 = con.prepareStatement(query1);
            preparedStmt1.setInt(1, getMaxId(con));
            preparedStmt1.setString(2, event_type2);

            preparedStmt1.execute();
        }
    }
}
