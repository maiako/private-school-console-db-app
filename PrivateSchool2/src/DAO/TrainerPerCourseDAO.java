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
import model.TrainerPerCourse;

public class TrainerPerCourseDAO extends GenericDAO {

    public void insertTrainerPerCourse(String first_name, String last_name, String subject, int course[]) throws SQLException {
        
        Connection conn = getConnection();
        conn.setAutoCommit(false);
        PreparedStatement ps = null;
        PreparedStatement psAssignCourse = null;

        ResultSet rs = null;
        String sqlInsert = "insert into trainer values(null,?,?,?)";

        try {

            ps = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, first_name);
            ps.setString(2, last_name);
            ps.setString(3, subject);
            int rowAffected = ps.executeUpdate();

            //get Trainer id
            rs = ps.getGeneratedKeys();
            int trainer_id = 0;

            if (rs.next()) {
                trainer_id = rs.getInt(1);
            }
            //in case the insert operation successes, assign course to trainer
            if (rowAffected == 1) {
                //assign course to trainer
                String sqlPivot = "insert into trainers_per_course(course_id, trainer_id) values(?,?)";

                psAssignCourse = conn.prepareStatement(sqlPivot);
                for (int course_id : course) {
                    psAssignCourse.setInt(1, course_id);
                    psAssignCourse.setInt(2, trainer_id);
                    psAssignCourse.executeUpdate();

                }
                conn.commit();

            } else {
                conn.rollback();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerPerCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(ps, conn);
        }
    }

    public List<TrainerPerCourse> getAllTrainersPerCourse() {
        List<TrainerPerCourse> result = new ArrayList<>();
        String sql = "select * from trainer_per_course";
        Connection conn = getConnection();
        Statement st = null;
        ResultSet rs = null;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                TrainerPerCourse trainer = new TrainerPerCourse();
                trainer.setFirst_name(rs.getString(2));
                trainer.setLast_name(rs.getString(3));
                trainer.setStream(rs.getString(1));
                result.add(trainer);

            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerPerCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(st, conn);
        }
        return result;
    }

}
