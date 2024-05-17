/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lab5;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
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

/**
 *
 * @author Admin
 */
public interface BookDAO {

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
            String sql = "SELECT * FROM books";
            try ( Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    Object[] rowData = {
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("price")
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
}
