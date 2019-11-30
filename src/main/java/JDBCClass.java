import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Properties;

class JDBCClass {

    private static final Logger logger = Logger.getLogger(JDBCClass.class.getName());
    private static Connection connection = null;

    static {
        Driver driver;
        try {
            driver = new com.mysql.cj.jdbc.Driver();
        } catch (SQLException e) {
            logger.error(e.getStackTrace());
        }

        String URL = "jdbc:mysql://45.129.98.168:3306/NutritionalSupplements?useLegacyDatetimeCode=false&serverTimezone=UTC";
        String USER = "root";
        String PASSWORD = "pZirpIfppd87";

        Properties info = new Properties();
        info.put("user", USER);
        info.put("password", PASSWORD);
        info.put("useUnicode", "true");
        info.put("characterEncoding", "UTF-8");


        try {
            connection = DriverManager.getConnection(URL, info);
        } catch (SQLException e) {
            logger.error(e.getStackTrace());
        }


    }

    static String databaseQuery(String keyword) throws SQLException {
        Statement statement = null;
        try {
            statement = connection.createStatement();

        } catch (SQLException e) {
            logger.warn(e.getStackTrace());
        }


        String sql = "SELECT Description FROM FoodSupplements WHERE Name = '" + keyword + "';";
        ResultSet resultSet = statement != null ? statement.executeQuery(sql) : null;
        String answer = "";
        resultSet.next();
        answer += resultSet.getString("Description") + "\n";


        return answer;
    }





}
