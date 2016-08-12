package Logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBmanager {

    static final Logger logger = Logger.getLogger(DBmanager.class.getName());
    public static Connection ERP_dbConnection = null;
    public static Connection dbConnection = null;
    public static String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";

    public static Connection GetConnection() {
        /*This Method for get database connection*/
        IpAddress ip = new IpAddress();
        String DB_CONNECTION = "jdbc:oracle:thin:@200.200.1.79:1531:PAYCLONE";
        String DB_USER = "DNLMMT";
        String DB_PASSWORD = "DNLMMT";
        if (ip.isProdEnv()) {
            DB_USER = "DNLMMT";
            DB_PASSWORD = "DNLMMT";
        }
        try {
            Class.forName(DB_DRIVER);
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException ex) {
            logger.log(Level.SEVERE, "Error :{0}", ex);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error :{0}", ex);
        }
        // logger.log(Level.INFO, "Connected !");
        return dbConnection;
    }

    public static Connection GetProdAppsConnection() {
        /*This Method for get database connection*/
        IpAddress ip = new IpAddress();
        String ERP_CONNECTION = "jdbc:oracle:thin:@200.200.1.224:1599:DNLCL";
        String ERP_USER = "APPS";
        String ERP_PASSWORD = "SPIDER123";
        if (ip.isProdEnv()) {
            ERP_CONNECTION = "jdbc:oracle:thin:@200.200.1.74:1541:EDNL";
            ERP_USER = "APPSR";
            ERP_PASSWORD = "APPSR";
        }
        try {
            Class.forName(DB_DRIVER);
            ERP_dbConnection = DriverManager.getConnection(ERP_CONNECTION, ERP_USER, ERP_PASSWORD);
        } catch (ClassNotFoundException ex) {
            logger.log(Level.SEVERE, "Error :{0}", ex);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error :{0}", ex);
        }
        return ERP_dbConnection;
    }

}
