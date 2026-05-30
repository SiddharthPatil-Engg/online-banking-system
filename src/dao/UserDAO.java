package dao;

import model.User;
import java.sql.*;

public class UserDAO {

    public User login(String username, String password) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getDouble("balance")
            );
        }
        return null;
    }

    public void updateBalance(int userId, double newBalance) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "UPDATE users SET balance=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDouble(1, newBalance);
        ps.setInt(2, userId);
        ps.executeUpdate();
    }
}
