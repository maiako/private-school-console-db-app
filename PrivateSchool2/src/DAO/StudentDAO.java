
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
import model.Student;



public class StudentDAO extends GenericDAO{
    
     public void insertStudent (Student student) {
     String sql = "insert into student values(null,?,?,?,?)";
     Connection conn = getConnection();
     PreparedStatement ps = null;
     
     try {
            ps = conn.prepareStatement(sql); 
            ps.setString(1, student.getFirst_name());
            ps.setString(2, student.getLast_name());
            ps.setDate(3, new java.sql.Date(student.getDate_of_birth().getTime()));
            ps.setInt(4, student.getTuition_fees());
            int result = ps.executeUpdate();
            if (result == 1) {
                System.out.println("Student successfully created.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            closeConnections(ps, conn);
        }
     
     }

                  
        public  Student findByMaxId() {
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        Student studentMax = null;
        String sql = "select * from student where student_id = (select max(student_id) from student)";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            int student_id = rs.getInt(1);
            String first_name = rs.getString(2);
            String last_name = rs.getString(3);
            studentMax = new Student(student_id, first_name, last_name);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs,stmt,conn);
        }
        return studentMax;
    }

     
        public List<Student> getAllStudents() {
        List<Student> result = new ArrayList<>();
        String sql = "select * from student";
        Connection conn = getConnection();
        Statement st = null;
        ResultSet rs = null;
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Student student = new Student();
                student.setStudent_id(rs.getInt(1));
                student.setFirst_name(rs.getString(2));
                student.setLast_name(rs.getString(3));
                student.setDate_of_birth(rs.getDate(4));
                student.setTuition_fees(rs.getInt(5));
                result.add(student);
                 }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(st, conn);
        }
        return result;
    }



 
        
        
    
}
