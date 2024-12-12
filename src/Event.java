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

    public static int getMaxSeatId(Connection con) {
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT MAX(id_θέσης) AS max_id FROM seattypes");
            if (rs.next()) {
                return rs.getInt("max_id");  //max id_θέσης
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;  // Default to 1 if no users exist
    }

    public static void eventConnect(Connection con,String event_type,String event_type2,int id) throws SQLException {
        if (event_type.equals("Συναυλία")) {
            String query1 = "INSERT INTO concerts (id_εκδήλωσης, Είδος) VALUES (?, ?)";
            PreparedStatement preparedStmt1 = con.prepareStatement(query1);
            preparedStmt1.setInt(1, id);
            preparedStmt1.setString(2, event_type2);

            preparedStmt1.execute();
        }
        else if (event_type.equals("Θέατρο")) {
            String query1 = "INSERT INTO theaters (id_εκδήλωσης, Είδος) VALUES (?, ?)";
            PreparedStatement preparedStmt1 = con.prepareStatement(query1);
            preparedStmt1.setInt(1, id);
            preparedStmt1.setString(2, event_type2);

            preparedStmt1.execute();
        }
        else if (event_type.equals("Χορός")) {
            String query1 = "INSERT INTO dances (id_εκδήλωσης, Είδος) VALUES (?, ?)";
            PreparedStatement preparedStmt1 = con.prepareStatement(query1);
            preparedStmt1.setInt(1, id);
            preparedStmt1.setString(2, event_type2);

            preparedStmt1.execute();
        }
    }

    public static void addSeatTypes(Connection con,String seat_type, int event_id) throws SQLException {
        String query = "INSERT INTO SeatTypes (id_θέσης, Είδος, id_εκδήλωσης) VALUES (?,?,?)";
        int id = getMaxSeatId(con) + 1;
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setInt(1, id);
        preparedStmt.setString(2, seat_type);
        preparedStmt.setInt(3, event_id);

        preparedStmt.execute();
    }
    public static String getSeatType(Connection con, int seat_id,String event_type) throws SQLException {
        String seat_type =new String();
        if((event_type.equals("Θέατρο")) || (event_type.equals("Χορός"))){
            for (int i = 0; i <getMaxSeatId(con); i++) {
                seat_type = "Γενική_Είσοδος";
            }
        }
        else if(event_type.equals("Συναυλία")){
            for (int i = 0; i < (getMaxSeatId(con) / 4); i++) {
                seat_type = "Backstage";
            }
            for (int i = (getMaxSeatId(con) / 4); i < (getMaxSeatId(con) / 2); i++) {
                seat_type = "Vip";
            }
            for (int i = (getMaxSeatId(con) / 2); i < (getMaxSeatId(con)); i++) {
                seat_type = "Γενική_Είσοδος";
            }
        }

        return seat_type;
    }

    public static void addNewEvent(Connection con, String event_name, Date event_date, Time event_time, int event_capacity, String event_type, String event_type2) throws SQLException {

        // Add new event
        try {
            int id = getMaxId(con) + 1;
            String query = "INSERT INTO events (id_εκδήλωσης, Όνομα, Ημερομηνία, Ώρα, Χωρητικότητα, Είδος) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.setString(2, event_name);
            preparedStmt.setDate(3, event_date);
            preparedStmt.setTime(4, event_time);
            preparedStmt.setInt(5, event_capacity);
            preparedStmt.setString(6, event_type);
            preparedStmt.execute();

            eventConnect(con, event_type, event_type2,id);

            if((event_type.equals("Θέατρο")) || (event_type.equals("Χορός"))){
                for (int i = 0; i < event_capacity; i++) {
                    addSeatTypes(con, "Γενική_Είσοδος", id);
                }
            }
            else if(event_type.equals("Συναυλία")){
                for (int i = 0; i < (event_capacity / 4); i++) {
                    addSeatTypes(con, "Backstage", id);
                }
                for (int i = (event_capacity / 4); i < (event_capacity / 2); i++) {
                    addSeatTypes(con, "Vip", id);
                }
                for (int i = (event_capacity / 2); i < (event_capacity); i++) {
                    addSeatTypes(con, "Γενική_Είσοδος", id);
                }
            }

            Tickets.generateTickets(con, event_capacity, id,event_type);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}