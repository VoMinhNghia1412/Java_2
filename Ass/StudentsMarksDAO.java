/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Ass;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.Connection;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public interface StudentsMarksDAO {

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
            String sql = "SELECT grade.id , students.hoten , grade.tienganh, grade.tinhoc, grade.gdtc "
                    + "FROM grade "
                    + "INNER JOIN students ON grade.masv = students.masv";
            try ( Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    Object[] rowData = {
                        rs.getInt("id"),
                        rs.getString("hoten"),
                        rs.getInt("tienganh"),
                        rs.getInt("tinhoc"),
                        rs.getInt("gdtc"),};
                    model.addRow(rowData);
                }
            }
        } catch (SQLServerException ex) {
            System.err.println("Lỗi kết nối: " + ex.getMessage());
        } catch (SQLException ex) {
            System.err.println("Lỗi SQL: " + ex.getMessage());
        }
    }

    public static void displayTop3Students(DefaultTableModel model) {
        // Kiểm tra xem model có null không
        if (model == null) {
            System.out.println("Model is null. Cannot display top 3 students.");
            return;
        }

        try {
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
                String sql = "SELECT TOP 3 students.masv, students.hoten, grade.tienganh, grade.tinhoc, grade.gdtc "
                        + "FROM students "
                        + "INNER JOIN grade ON students.masv = grade.masv "
                        + "ORDER BY (grade.tienganh + grade.tinhoc + grade.gdtc) DESC";

                try ( Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {
                    
                    model.setRowCount(0);

                    while (rs.next()) {
                        Object[] rowData = {
                            rs.getString("masv"),
                            rs.getString("hoten"),
                            rs.getInt("tienganh"),
                            rs.getInt("tinhoc"),
                            rs.getInt("gdtc")
                        };
                        model.addRow(rowData);
                    }
                }
            }
        } catch (SQLServerException ex) {
            System.err.println("Lỗi kết nối: " + ex.getMessage());
        } catch (SQLException ex) {
            System.err.println("Lỗi SQL: " + ex.getMessage());
        }
    }

    public static void search(String masv, JTable jTable1) {
        if (masv.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã sinh viên cần tìm kiếm.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

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
            String sql = "SELECT grade.id AS id, students.hoten AS tensv, grade.tienganh, grade.tinhoc, grade.gdtc "
                    + "FROM grade "
                    + "INNER JOIN students ON grade.masv = students.masv "
                    + "WHERE grade.id = ? OR students.masv = ?";
            try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, masv);
                pstmt.setString(2, masv);
                try ( ResultSet rs = pstmt.executeQuery()) {
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                    model.setRowCount(0);
                    boolean found = false;
                    while (rs.next()) {
                        found = true;
                        Object[] rowData = {
                            rs.getString("id"),
                            rs.getString("tensv"),
                            rs.getString("tienganh"),
                            rs.getString("tinhoc"),
                            rs.getString("gdtc")
                        };
                        model.addRow(rowData);
                    }
                    if (!found) {
                        JOptionPane.showMessageDialog(null, "Sinh viên hiện chưa có điểm " + masv + ".", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        } catch (SQLServerException ex) {
            System.err.println("Lỗi kết nối: " + ex.getMessage());
        } catch (SQLException ex) {
            System.err.println("Lỗi SQL: " + ex.getMessage());
        }
    }

    public static void deleteData(String id) {
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
            // Xóa dữ liệu từ bảng grade
            String deleteGradeSql = "DELETE FROM grade WHERE id = ?";
            try ( PreparedStatement pstmt = conn.prepareStatement(deleteGradeSql)) {
                pstmt.setString(1, id);
                int rowsDeleted = pstmt.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Đã xóa dữ liệu của sinh viên có mã " + id + " từ bảng grade thành công.");
                } else {
                    System.out.println("Không tìm thấy sinh viên có mã " + id + " trong bảng grade để xóa.");
                }
            }

           
            String deleteStudentSql = "DELETE FROM students WHERE masv = ?";
            try ( PreparedStatement pstmt = conn.prepareStatement(deleteStudentSql)) {
                pstmt.setString(1, id);
                int rowsDeleted = pstmt.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Đã xóa dữ liệu của sinh viên có mã " + id + " từ bảng students thành công.");
                } else {
                    System.out.println("Không tìm thấy sinh viên có mã " + id + " trong bảng students để xóa.");
                }
            }
        } catch (SQLServerException ex) {
            System.err.println("Lỗi kết nối: " + ex.getMessage());
        } catch (SQLException ex) {
            System.err.println("Lỗi SQL: " + ex.getMessage());
        }
    }

    public static void updateData(String id, String tenSV, String tiengAnh, String tinHoc, String giaoDucTC) {
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
            // Cập nhật thông tin trong bảng grade
            String updateGradeSql = "UPDATE grade SET tienganh = ?, tinhoc = ?, gdtc = ? WHERE id = ?";
            try ( PreparedStatement pstmtGrade = conn.prepareStatement(updateGradeSql)) {
                pstmtGrade.setString(1, tiengAnh);
                pstmtGrade.setString(2, tinHoc);
                pstmtGrade.setString(3, giaoDucTC);
                pstmtGrade.setString(4, id);

                // Thực thi câu lệnh SQL cho bảng grade
                int rowsUpdatedGrade = pstmtGrade.executeUpdate();
                if (rowsUpdatedGrade > 0) {
                    System.out.println("Đã cập nhật điểm thành công cho sinh viên có mã " + id + " trong bảng grade.");
                } else {
                    System.out.println("Không tìm thấy sinh viên có mã " + id + " trong bảng grade để cập nhật.");
                }
            }

            
            String updateStudentSql = "UPDATE students SET hoten = ? WHERE masv = ?";
            try ( PreparedStatement pstmtStudent = conn.prepareStatement(updateStudentSql)) {
                pstmtStudent.setString(1, tenSV);
                pstmtStudent.setString(2, id);

               
                int rowsUpdatedStudent = pstmtStudent.executeUpdate();
                if (rowsUpdatedStudent > 0) {
                    System.out.println("Đã cập nhật thông tin thành công cho sinh viên có mã " + id + " trong bảng students.");
                } else {
                    System.out.println("Không tìm thấy sinh viên có mã " + id + " trong bảng students để cập nhật.");
                }
            }
        } catch (SQLServerException ex) {
            System.err.println("Lỗi kết nối: " + ex.getMessage());
        } catch (SQLException ex) {
            System.err.println("Lỗi SQL: " + ex.getMessage());
        }
    }
}
