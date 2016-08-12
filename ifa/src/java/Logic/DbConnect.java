/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 02948
 */
public class DbConnect {

    //DB config

    /**
     *
     */
        public static Connection dbConnection = null;

    /**
     *
     * @return
     */
    public static Connection GetPAYConnection() {
        /*This Method for get database connection*/
        String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
        String DB_CONNECTION = "jdbc:oracle:thin:@200.200.1.79:1531:PAYCLONE";
        String DB_USER = "DNLBPM";
        String DB_PASSWORD = "DNLBPM";
        try {
            Class.forName(DB_DRIVER);
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
        }
        return dbConnection;
    }

//    public static Connection GetCLONEConnection() {
//        /*This Method for get database connection*/
//        String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
//        String DB_CONNECTION = "jdbc:oracle:thin:@dnldbtest.dnlpune.com:1598:DNLCLONE";
//        String DB_USER = "APPS";
//        String DB_PASSWORD = "SPIDER123";
//        try {
//            Class.forName(DB_DRIVER);
//            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
//        } catch (ClassNotFoundException | SQLException ex) {
//        }
//        return dbConnection;
//    }
}
