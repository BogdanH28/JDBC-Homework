import java.sql.*;

public class JDBCTest {

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            Connection connection = null;
            DriverManager.setLoginTimeout(60);
            try {
                String url = new StringBuilder()
                        .append("jdbc:")
                        .append("postgresql")       // “mysql” / “db2” / “mssql” / “oracle” / ...
                        .append("://")
                        .append("localhost")
                        .append(":")
                        .append(5432)
                        .append("/")
                        .append("postgres")
                        .append("?user=")
                        .append("postgres")
                        .append("&password=")
                        .append("Boboh2895+").toString();
                connection = DriverManager.getConnection(url);
                Statement s = connection.createStatement();
                ResultSet resultSet = s.executeQuery("SELECT * from bookings;");
                while (resultSet.next()) {
                    System.out.println("id: " + resultSet.getInt("id") + ", type: " + resultSet.getString("type"));
                }
                resultSet.close();
                connection.close();
            } catch (SQLException e) {
                System.err.println("Cannot connect to the database: " + e.getMessage());
            }

        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.err.println("Can’t load driver. Verify CLASSPATH");
            System.err.println(e.getMessage());
        }

    }
}
