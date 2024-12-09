import java.sql.*;

public class myJDBC {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login_scema", "root", "29102001");

            Statement statement = connection.createStatement();

            String createTable = "CREATE TABLE IF NOT EXISTS USERS (username VARCHAR(50), password VARCHAR(50))";
            String insertUser1 = "INSERT INTO USERS (username, password) VALUES ('user1', 'pass1')";

            statement.executeUpdate(createTable);
            statement.executeUpdate(insertUser1);

            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("username") + " " + resultSet.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}