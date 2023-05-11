
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
import model.Course;



public class CourseDAO extends GenericDAO {
    
    
    public void insertCourse(Course course) {
        String sql = "insert into course values(null,?,?,?,?,?)";
        Connection conn = getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, course.getTitle());
            ps.setString(2, course.getStream());
            ps.setString(3, course.getType());
            ps.setDate(4, new java.sql.Date(course.getStart_date().getTime()));
            ps.setDate(5, new java.sql.Date(course.getEnd_date().getTime()));
            int result = ps.executeUpdate();
            if (result == 1) {
                System.out.println("Course successfully created.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            closeConnections(ps, conn);
        }
}
    
     public List<Course> getAllCourses() {
        List<Course> result = new ArrayList<>();
        String sql = "select * from course";
        Connection conn = getConnection();
        Statement st = null;
        ResultSet rs = null;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Course course = new Course();
                course.setCourse_id(rs.getInt(1));
                course.setTitle(rs.getString(2));
                course.setStream(rs.getString(3));
                course.setType(rs.getString(4));
                course.setStart_date(rs.getDate(5));
                course.setEnd_date(rs.getDate(6));
                result.add(course);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(st, conn);
        }
        return result;
    }
    
}