/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PS23109;

import Ass.StudentsDAO;
import static Ass.StudentsDAO.fillTable;
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
public interface PS23109_thiDAO {
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

            String sql = "SELECT * FROM quanlybanhang";
            try ( Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Object[] rowData = {
                        rs.getString("masp"),
                        rs.getString("tensp"),
                        rs.getString("soluong"),
                        rs.getString("gianhap"),
                        rs.getString("giabanle"),
                        rs.getString("mahinh")
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
    
     public static void delete(javax.swing.JTextField txtMasp, javax.swing.JTextField txtTensp, javax.swing.JTextField txtSoluong, javax.swing.JTextField txtGiaban,javax.swing.JTextField txtGianhap, javax.swing.JLabel jLabel8, DefaultTableModel model) {
        String masv = txtMasp.getText();

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
                String sql = "DELETE FROM quanlybanhang WHERE masp = ?";
                try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, masv);

                    int rowsAffected = pstmt.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công.");
                        fillTable(model);
                       
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
     
    
     
     
   
}
