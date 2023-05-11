
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GenericDAO {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "mari2021iako";
    private static final String MYSQLURL = "jdbc:mysql://localhost:3306/private_school?zeroDateTimeBehavior=CONVERT_TO_NULL&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";

    protected static Connection getConnection() { 
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(MYSQLURL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        return conn;
    }
    
    protected void closeConnections(PreparedStatement ps, Connection conn) {
        try {
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex); 
        }
    } 
    
    protected void closeConnection(Connection conn) {
        try {
            
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex); 
        }
    } 
    protected void closeConnections(Statement st, Connection conn) {
        try {
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex); 
        }
    } 
    
    protected void closeConnections(ResultSet rs, Statement st, Connection conn) {
        try {
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex); 
        }
    } 
    
    
}
