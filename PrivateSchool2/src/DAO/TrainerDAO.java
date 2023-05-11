package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Trainer;

public class TrainerDAO extends GenericDAO {

    public void insertTrainer(Trainer trainer) {
        String sql = "insert into trainer values(null,?,?,?)";
        Connection conn = getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, trainer.getFirst_name());
            ps.setString(2, trainer.getLast_name());
            ps.setString(3, trainer.getSubject());
            int result = ps.executeUpdate();
            if (result == 1) {
                System.out.println("Trainer successfully created.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(ps, conn);
        }
    }

    public List<Trainer> getAllTrainers() {
        List<Trainer> result = new ArrayList<>();
        String sql = "select * from trainer";
        Connection conn = getConnection();
        Statement st = null;
        ResultSet rs = null;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Trainer trainer = new Trainer();
                trainer.setTrainer_id(rs.getInt(1));
                trainer.setFirst_name(rs.getString(2));
                trainer.setLast_name(rs.getString(3));
                trainer.setSubject(rs.getString(4));
                result.add(trainer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(st, conn);
        }
        return result;
    }
}
