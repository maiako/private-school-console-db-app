package DAO;

import static DAO.GenericDAO.getConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AssignPerCoursePerStudent;

public class AssignPerCoursePerStudentDAO extends GenericDAO {

    

    public List<AssignPerCoursePerStudent> getAllAssignmentsPerCoursePerStudent() {
        List<AssignPerCoursePerStudent> result = new ArrayList<>();
        String sql = "select * from assignment_per_course_per_student";
        Connection conn = getConnection();
        Statement st = null;
        ResultSet rs = null;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                AssignPerCoursePerStudent assignment = new AssignPerCoursePerStudent();
                assignment.setFirst_name(rs.getString(3));
                assignment.setLast_name(rs.getString(4));
                assignment.setStream(rs.getString(1));
                assignment.setTitle(rs.getString(2));
                result.add(assignment);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignPerCoursePerStudentDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(st, conn);
        }
        return result;
    }

}
