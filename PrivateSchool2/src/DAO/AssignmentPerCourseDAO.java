
package DAO;

import static DAO.GenericDAO.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AssignmentPerCourse;


public class AssignmentPerCourseDAO extends GenericDAO{
    
    
    public void insertAssignPerCourse(String title, String description, Date sub_date_time, double oral_mark, double total_mark, int course[]) throws SQLException {
  
        Connection conn = getConnection();
        conn.setAutoCommit(false);
        PreparedStatement ps = null;
        PreparedStatement psAssignCourse = null;

        ResultSet rs = null;
        String sqlInsert = "insert into assignment values(null,?,?,?,?,?)";

        try {

            ps = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, title);
            ps.setString(2, description);
            ps.setDate(3, new java.sql.Date(sub_date_time.getTime()));
            ps.setDouble(4, oral_mark);
            ps.setDouble(5, total_mark);
            int rowAffected = ps.executeUpdate();

            //get assignment id
            rs = ps.getGeneratedKeys();
            int assignment_id = 0;

            if (rs.next()) {
                assignment_id = rs.getInt(1);
            }
            //in case the insert operation successes, add assignment to course 
            if (rowAffected == 1) {
                //add assignment to course 
                String sqlPivot = "insert into assignments_per_course (course_id, assignment_id)values(?,?)";

                psAssignCourse = conn.prepareStatement(sqlPivot);
                for (int course_id : course) {
                    psAssignCourse.setInt(1, course_id);
                    psAssignCourse.setInt(2, assignment_id);
                    psAssignCourse.executeUpdate();
                }

                conn.commit();
            } else {
                conn.rollback();
            }

        } catch (SQLException ex) {
            Logger.getLogger(AssignmentPerCourseDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            closeConnections(ps, conn);
        }
    }
    
    public List<AssignmentPerCourse> getAllAssignmentsPerCourse() {
        List<AssignmentPerCourse> result = new ArrayList<>();
        String sql = "select * from assignment_per_course";
        Connection conn = getConnection();
        Statement st = null;
        ResultSet rs = null;
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                AssignmentPerCourse assignment = new AssignmentPerCourse();
                assignment.setStream(rs.getString(1));
                assignment.setTitle(rs.getString(2));
                result.add(assignment);
               
                 }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentPerCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(st, conn);
        }
        return result;
    }
}
