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
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "username VARCHAR(50) NOT NULL, " +
                "password VARCHAR(100) NOT NULL)";
        stmt.executeUpdate(createTableSQL);

        String insertUsersSQL = "INSERT INTO Users (username, password) VALUES " +
                "('user1', 'password1'), " +
                "('user2', 'password2')";
        int rowsInserted = stmt.executeUpdate(insertUsersSQL);

        
        stmt.close();
        con.close();

    }
}