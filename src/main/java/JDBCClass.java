import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

class JDBCClass {


    public static void main(String[] args) {
            Driver driver;
            try {
                driver = new com.mysql.cj.jdbc.Driver();
            } catch (SQLException e) {
                System.out.println("Unable to load driver class.");
                e.printStackTrace();
            }

        String URL = "jdbc:mysql://45.129.98.168:3306/NutritionalSupplements?useLegacyDatetimeCode=false&serverTimezone=UTC";//"jdbc:mysql://localhost:3306/test?useLegacyDatetimeCode=false&serverTimezone=UTC"; //for lokal server
            String USER = "root";
            String PASSWORD = "pZirpIfppd87";
            String sql = "SELECT * FROM MainSupplements";
            Connection connection=null;
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            /*we dont have a database yet
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3));
            }
            connection.close();
            System.out.println("Connection closed");
            */



        }





}
