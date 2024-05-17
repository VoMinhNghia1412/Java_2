/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package test_1;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Admin
 */
public interface test_Dao {

    public static void fillTable(DefaultTableModel model) {

        model.setRowCount(0);

        String server = "ADMIN\\SQLEXPRESS";
        String user = "sa";
        String password = "sa";
        String db = "java_2";
        int port = 1433;
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser(user);
        ds.setPassword(password);
        ds.setDatabaseName(db);
        ds.setServerName(server);
        ds.setPortNumber(port);

        try ( Connection conn = ds.getConnection()) {

            String sql = "SELECT * FROM test_1";
            try ( Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Object[] rowData = {
                        rs.getString("id"),
                        rs.getString("ten")
                    };
                    model.addRow(rowData);
                }
            }
        } catch (SQLServerException ex) {
            System.err.println("Lỗi kết nối: " + ex.getMessage());
        } catch (SQLException ex) {
            System.err.println("Lỗi SQL: " + ex.getMessage());
        }
    }

    public static void save(String ten) {
    // Kiểm tra nếu tên là số
    if (!ten.matches("\\d+")) {
        JOptionPane.showMessageDialog(null, "Tên không thể là một số", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Kiểm tra nếu tên rỗng
    if (ten.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Vui lòng điền tên", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Triển khai hàm generateId() trong phương thức save
    Random random = new Random();
    String prefix = "Sp";
    int randomNumber = random.nextInt(9000) + 1000;
    String id = prefix + randomNumber;

    String server = "ADMIN\\SQLEXPRESS";
    String user = "sa";
    String password = "sa";
    String db = "java_2";
    int port = 1433;
    SQLServerDataSource ds = new SQLServerDataSource();
    ds.setUser(user);
    ds.setPassword(password);
    ds.setDatabaseName(db);
    ds.setServerName(server);
    ds.setPortNumber(port);

    try (Connection conn = ds.getConnection()) {
        String sql = "INSERT INTO test_1 (id, ten) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, ten);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Dữ liệu đã được lưu thành công");
            } else {
                JOptionPane.showMessageDialog(null, "Không thể lưu dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (SQLServerException ex) {
        System.err.println("Lỗi kết nối: " + ex.getMessage());
    } catch (SQLException ex) {
        System.err.println("Lỗi SQL: " + ex.getMessage());
    }

    }
}
