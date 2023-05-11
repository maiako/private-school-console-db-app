
package DAO;

import static DAO.GenericDAO.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Assignment;



public class AssignmentDAO extends GenericDAO {
    
    public void insertAssignment(Assignment assignment) {
        String sql = "insert into assignment values(null,?,?,?,?,?)";
        Connection conn = getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, assignment.getTitle());
            ps.setString(2, assignment.getDescription());
            ps.setDate(3,assignment.getSub_date_time());
            ps.setDouble(4, assignment.getOral_mark());
            ps.setDouble(5, assignment.getTotal_mark());
            int result = ps.executeUpdate();
            if (result == 1) {
                System.out.println("Assignment successfully created.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            closeConnections(ps, conn);
        }
}
            
        public List<Assignment> getAllAssignments() {
        List<Assignment> result = new ArrayList<>();
        String sql = "select * from assignment";
        Connection conn = getConnection();
        Statement st = null;
        ResultSet rs = null;
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Assignment assignment = new Assignment();
                assignment.setAssignment_id(rs.getInt(1));
                assignment.setTitle(rs.getString(2));
                assignment.setDescription(rs.getString(3));
                assignment.setSub_date_time(rs.getDate(4));
                assignment.setOral_mark(rs.getDouble(5));
                assignment.setTotal_mark(rs.getDouble(6));
                result.add(assignment);
                 }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(st, conn);
        }
        return result;
    }
}
