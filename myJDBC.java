import java.sql.*;

public class MyJDBC {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = new String("jdbc:mysql://localhost");
        String databaseName = new String("test");
        int port = 3306;
        String username = new String("root");
        String password = new String("");

        Connection con = DriverManager.getConnection(url + ":" + port + "/" + databaseName + "?characterEncoding=UTF-8", username, password);

        Statement stmt = con.createStatement();

        // SQL to create the Users table
        String createEventTableSQL = "CREATE TABLE IF NOT EXISTS EVENTS (" +
                "id_εκδήλωσης INT PRIMARY KEY, " +
                "Όνομα VARCHAR(50) NOT NULL, " +
                "Ημερομηνία DATE NOT NULL," +
                "Ώρα TIME NOT NULL, " +
                "Χωρητικότητα INT NOT NULL" +
                ")";

        stmt.executeUpdate(createEventTableSQL);
        
        stmt.close();
        con.close();

    }
}