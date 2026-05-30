package dao;

import java.sql.*;

public class TransactionDAO {

    public void addTransaction(int userId, String type, double amount) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO transactions(user_id, type, amount) VALUES(?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setString(2, type);
        ps.setDouble(3, amount);
        ps.executeUpdate();
    }

    public void getTransactions(int userId) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM transactions WHERE user_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(
                    rs.getString("type") + " | " +
                            rs.getDouble("amount") + " | " +
                            rs.getTimestamp("date")
            );
        }
    }
}
