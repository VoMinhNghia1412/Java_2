/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Ass;

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
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Admin
 */
public interface StudentsDAO {

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

            String sql = "SELECT * FROM students";
            try ( Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Object[] rowData = {
                        rs.getString("masv"),
                        rs.getString("hoten"),
                        rs.getString("email"),
                        rs.getString("sodt"),
                        rs.getBoolean("gioitinh") ? "Nam" : "Nữ",
                        rs.getString("diachi"),
                        rs.getString("hinh")
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

    public static void reset(javax.swing.JTextField txtMasv, javax.swing.JTextField txtHoten, javax.swing.JTextField txtEmail, javax.swing.JTextField txtSoDT, javax.swing.JRadioButton rdbNam, javax.swing.JTextArea txtDiachi, javax.swing.JLabel jLabel8) {
        txtMasv.setText("");
        txtHoten.setText("");
        txtEmail.setText("");
        txtSoDT.setText("");
        rdbNam.setSelected(true);
        txtDiachi.setText("");
        jLabel8.setIcon(null);
    }

    public static void delete(javax.swing.JTextField txtMasv, javax.swing.JTextField txtHoten, javax.swing.JTextField txtEmail, javax.swing.JTextField txtSoDT, javax.swing.JRadioButton rdbNam, javax.swing.JTextArea txtDiachi, javax.swing.JLabel jLabel8, DefaultTableModel model) {
        String masv = txtMasv.getText();

        if (!masv.isEmpty()) {
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
                String sql = "DELETE FROM students WHERE masv = ?";
                try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, masv);

                    int rowsAffected = pstmt.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công.");
                        fillTable(model);
                        StudentsDAO.reset(txtMasv, txtHoten, txtEmail, txtSoDT, rdbNam, txtDiachi, jLabel8);
                    } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy sinh viên có mã " + masv + " để xóa.");
                    }
                }
            } catch (SQLServerException ex) {
                System.err.println("Lỗi kết nối: " + ex.getMessage());
            } catch (SQLException ex) {
                System.err.println("Lỗi SQL: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã sinh viên để xóa.");

        }

    }

    public static void saveDataToDatabase(JTextField txtMasv, JTextField txtHoten, JTextField txtEmail, JTextField txtSoDT, JRadioButton rdbNam, JTextArea txtDiachi, JLabel jLabel8, DefaultTableModel model) {

        if (isValidInput(txtMasv, txtHoten, txtEmail, txtSoDT, txtDiachi, jLabel8)) {

            String masv = txtMasv.getText();
            String hoten = txtHoten.getText();
            String email = txtEmail.getText();
            String sodt = txtSoDT.getText();
            boolean gioitinh = rdbNam.isSelected();
            String diachi = txtDiachi.getText();
            String hinh = jLabel8.getIcon() != null ? jLabel8.getIcon().toString() : ""; // Lấy đường dẫn của hình ảnh

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
                String sql = "INSERT INTO students (masv, hoten, email, sodt, gioitinh, diachi, hinh) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, masv);
                    pstmt.setString(2, hoten);
                    pstmt.setString(3, email);
                    pstmt.setString(4, sodt);
                    pstmt.setBoolean(5, gioitinh);
                    pstmt.setString(6, diachi);
                    pstmt.setString(7, hinh);

                    int rowsAffected = pstmt.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Đã lưu dữ liệu vào cơ sở dữ liệu thành công.");
                        StudentsDAO.fillTable(model);
                    } else {
                        JOptionPane.showMessageDialog(null, "Lưu dữ liệu vào cơ sở dữ liệu thất bại.");
                    }
                }
            } catch (SQLServerException ex) {
                System.err.println("Lỗi kết nối: " + ex.getMessage());
            } catch (SQLException ex) {
                System.err.println("Lỗi SQL: " + ex.getMessage());
            }
        }
    }

    public static boolean isValidInput(JTextField txtMasv, JTextField txtHoten, JTextField txtEmail, JTextField txtSoDT, JTextArea txtDiachi, JLabel jLabel8) {
        String masv = txtMasv.getText();
        String hoten = txtHoten.getText();
        String email = txtEmail.getText();
        String sodt = txtSoDT.getText();
        String diachi = txtDiachi.getText();

        if (masv.isEmpty() || hoten.isEmpty() || email.isEmpty() || sodt.isEmpty() || diachi.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin sinh viên.");
            return false;
        }

        if (!sodt.matches("\\d{11}")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải có đúng 11 chữ số.");
            txtSoDT.requestFocus();
            return false;
        }

        return true;
    }

    public static void updateStudent(DefaultTableModel model, String masv, String hoten, String email, String sodt, boolean gioitinh, String diachi, String hinh, int selectedRow) {

        model.setValueAt(masv, selectedRow, 0);
        model.setValueAt(hoten, selectedRow, 1);
        model.setValueAt(email, selectedRow, 2);
        model.setValueAt(sodt, selectedRow, 3);
        model.setValueAt(gioitinh ? "Nam" : "Nữ", selectedRow, 4);
        model.setValueAt(diachi, selectedRow, 5);
        model.setValueAt(hinh, selectedRow, 6);

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
            String sql = "UPDATE students SET hoten = ?, email = ?, sodt = ?, gioitinh = ?, diachi = ?, hinh = ? WHERE masv = ?";
            try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, hoten);
                stmt.setString(2, email);
                stmt.setString(3, sodt);
                stmt.setBoolean(4, gioitinh);
                stmt.setString(5, diachi);
                stmt.setString(6, hinh);
                stmt.setString(7, masv);
                stmt.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Đã cập nhật thông tin sinh viên.");
        } catch (SQLException ex) {
            System.err.println("Lỗi SQL: " + ex.getMessage());
        }
    }

}
