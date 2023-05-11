
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
import model.StudentMoreCourses;


public class StudentMoreCoursesDAO extends GenericDAO{
    
    public List<StudentMoreCourses> getStudentMoreCourses() {
        List<StudentMoreCourses> result = new ArrayList<>();
        String sql = " select * from students_belong_course";
        Connection conn = getConnection();
        Statement st = null;
        ResultSet rs = null;
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                StudentMoreCourses student = new StudentMoreCourses();
                student.setFirst_name(rs.getString(1));
                student.setLast_name(rs.getString(2));
                result.add(student);
               
                 }
        } catch (SQLException ex) {
            Logger.getLogger(StudentMoreCoursesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(st, conn);
        }
        return result;
    }
    
    
}
