import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = new String("jdbc:mysql://localhost");
        String databaseName = new String("test");
        int port = 3306;
        String username = new String("root");
        String password = new String("");

        Connection con = DriverManager.getConnection(url + ":" + port + "/" + databaseName + "?characterEncoding=UTF-8", username, password);

        User.registerUser(con,"Παναγιώτης Μπερμπερίδης" ,"+306985959323", "Λεοφόρως Μίνωος", 36, 71303, Date.valueOf("2025-01-25"), 281,"53567100454569696", "panosber01@gmail.com");
        User.registerUser(con,"Λοΐζος Λαϊνίδης", "+306978152324", "Λεοφόρως Μίνωος", 36, 71303, Date.valueOf("2025-01-25"), 281,"53567100454569696", "loizospaok@gmail.com");
    }
}
