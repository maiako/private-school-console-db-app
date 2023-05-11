
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
import model.StudentPerCourse;


public class StudentPerCourseDAO extends GenericDAO{
    
        public void insertStudentPerCourse(String first_name, String last_name,Date date_of_birth, int tuition_fees, int course[]) throws SQLException {
       
        Connection conn = getConnection();
        conn.setAutoCommit(false);
        PreparedStatement ps = null;
        PreparedStatement psAssignCourse = null;

        ResultSet rs = null;
        String sqlInsert = "insert into student values(null,?,?,?,?)";

        try {

            ps = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, first_name);
            ps.setString(2, last_name);
            ps.setDate(3, new java.sql.Date(date_of_birth.getTime()));
            ps.setInt(4, tuition_fees);
            int rowAffected = ps.executeUpdate();

            //get student id
            rs = ps.getGeneratedKeys();
            int student_id = 0;

            if (rs.next()) {
                student_id = rs.getInt(1);
            }
            //in case the insert operation successes, assign course to student
            if (rowAffected == 1) {
                //assign course to student
                String sqlPivot = "insert into students_per_course(course_id, student_id) values(?,?)";

                psAssignCourse = conn.prepareStatement(sqlPivot);
                for (int course_id : course) {
                    psAssignCourse.setInt(1, course_id);
                    psAssignCourse.setInt(2, student_id);
                    psAssignCourse.executeUpdate();

                }
                conn.commit();

            } else {
                conn.rollback();
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentPerCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(ps, conn);
        }
    }
    

    
    public List<StudentPerCourse> getAllStudentsPerCourse() {
        List<StudentPerCourse> result = new ArrayList<>();
        String sql = "select * from student_per_course";
        Connection conn = getConnection();
        Statement st = null;
        ResultSet rs = null;
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                StudentPerCourse student = new StudentPerCourse();
                student.setFirst_name(rs.getString(2));
                student.setLast_name(rs.getString(3));
                student.setStream(rs.getString(1));
                result.add(student);
               
                 }
        } catch (SQLException ex) {
            Logger.getLogger(StudentPerCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(st, conn);
        }
        return result;
    }
    
    
}
