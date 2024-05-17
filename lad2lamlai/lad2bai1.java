/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package lad2lamlai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import lopjava2.User;
import javax.swing.InputVerifier;

/**
 *
 * @author Admin
 */
public class lad2bai1 extends javax.swing.JFrame {

    private DefaultTableModel model;
    private List<Students> list = new ArrayList<>();

    /**
     * Creates new form lad2bai1
     */
    public lad2bai1() {
        initComponents();
        this.setLocationRelativeTo(null);
        initTable();
        initData();
        fillTable();

    }

    public void sortByMarksDescending() {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).getMarks() < list.get(j + 1).getMarks()) {
                    // Hoán đổi vị trí của hai sinh viên
                    Students temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        // Sau khi sắp xếp xong, cập nhật lại bảng
        fillTable();
    }

    public boolean isValidNumber(String input) {
        try {
            double number = Double.parseDouble(input);
            // Kiểm tra xem số có nằm trong khoảng từ 0 đến 10 không
            return number >= 0 && number <= 10;
        } catch (NumberFormatException e) {
            // Nếu không thể chuyển đổi thành số, trả về false
            return false;
        }
    }

    public void sortByNameAscending() {
        Collections.sort(list, new Comparator<Students>() {
            @Override
            public int compare(Students student1, Students student2) {
                return student1.getName().compareToIgnoreCase(student2.getName());
            }
        });
        fillTable(); // Cập nhật lại bảng sau khi sắp xếp
    }

    public void initTable() {
        model = new DefaultTableModel();
        String[] cols = new String[]{"HỌ VÀ TÊN", "ĐIỂM ", "KHÓA HỌC", "XẾP LOẠI", "THƯỞNG"};
        model.setColumnIdentifiers(cols);
        jtbList.setModel(model);

    }

    public void fillTable() {
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

        for (Students student : list) {
            String marksAsString = Double.toString(student.getMarks());
            String grade = student.getGrade();
            String bonus = (student.getMarks() >= 7.5) ? "Có" : "Không";
            model.addRow(new String[]{student.getName(), marksAsString, student.getCourse(), grade, bonus});
        }

        model.fireTableDataChanged();
    }

    public void initData() {
        // Khởi tạo dữ liệu mẫu
        list.add(new Students("Võ Minh Nghĩa", 10, "Lập trình java cơ bản"));
        list.add(new Students("Trần Hoàng An", 7.2, "Lập trình java 1"));
        list.add(new Students("Nguyễn Mỹ Tâm", 9.0, "Cơ sở dữ liệu"));
        list.add(new Students("Hoàng Văn Việt", 6.0, "Cơ sở dữ liệu"));
        // Đổ dữ liệu vào bảng
        fillTable();
    }

    public void addDataToTable(String name, double marks, String course) {
        list.add(new Students(name, marks, course));

    DefaultTableModel model = (DefaultTableModel) jtbList.getModel();
    String grade = list.get(list.size() - 1).getGrade(); // Lấy xếp loại từ học viên cuối cùng trong danh sách

    // Kiểm tra có phần thưởng không
    String bonus = (marks >= 7.5) ? "Có" : "Không";

    // Thêm dữ liệu mới vào bảng
    model.addRow(new Object[]{name, marks, course, grade, bonus});

    // Kiểm tra xem học viên cuối cùng trong danh sách có phần thưởng không
    Students student = list.get(list.size() - 1);
    if (student.isBonus()) {
        // Nếu có phần thưởng, tự động tích vào checkbox
        chbCophanthuongko.setSelected(true);
    } else {
        // Nếu không có phần thưởng, bỏ chọn checkbox
        chbCophanthuongko.setSelected(false);
    }
    txtxeploai.setText(grade);
    txtxeploai.setEnabled(false);

    // Cập nhật hiển thị của bảng
    model.fireTableDataChanged();

    // Đặt lại các ô văn bản và combobox
    txthovaten.setText("");
    txtdiem.setText("");
    cboXeploai.setSelectedIndex(0);
    }

    public void reset() {
        // Xóa dữ liệu trong các ô văn bản
        txthovaten.setText("");
        txtdiem.setText("");
        txtxeploai.setText("");
        // Đặt lại giá trị mặc định cho combobox
        cboXeploai.setSelectedIndex(0);

        // Bỏ chọn checkbox
        chbCophanthuongko.setSelected(false);

    }

    public void updateDataInTable(int rowIndex, String name, double marks, String course) {
        // Kiểm tra nếu chỉ số hàng không hợp lệ
        if (rowIndex < 0 || rowIndex >= list.size()) {
            JOptionPane.showMessageDialog(null, "Hàng không tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Cập nhật dữ liệu trong danh sách
        Students student = list.get(rowIndex);
        student.setName(name);
        student.setMarks(marks);
        student.setCourse(course);

        // Cập nhật dữ liệu trong bảng
        DefaultTableModel model = (DefaultTableModel) jtbList.getModel();
        String grade = student.getGrade();
        String bonus = (marks >= 7.5) ? "Có" : "Không";
        model.setValueAt(name, rowIndex, 0);
        model.setValueAt(marks, rowIndex, 1);
        model.setValueAt(course, rowIndex, 2);
        model.setValueAt(grade, rowIndex, 3);
        model.setValueAt(bonus, rowIndex, 4);

        // Hiển thị thông báo cập nhật thành công
        JOptionPane.showMessageDialog(null, "Dữ liệu đã được cập nhật thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        chbCophanthuongko = new javax.swing.JCheckBox();
        txthovaten = new javax.swing.JTextField();
        txtxeploai = new javax.swing.JTextField();
        txtdiem = new javax.swing.JTextField();
        cboXeploai = new javax.swing.JComboBox<>();
        jbtThem = new javax.swing.JButton();
        jbtCapnhat = new javax.swing.JButton();
        jbtXoa = new javax.swing.JButton();
        jbtNhapmoi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbList = new javax.swing.JTable();
        jbtXapxeptheodiem = new javax.swing.JButton();
        jbtXapxeptheoten = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("QUẢN LÝ HỌC VIÊN");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("HỌ VÀ TÊN");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("ĐIỂM");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("kHÓA HỌC");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("XẾP LOẠI");

        chbCophanthuongko.setText("Có phần thưởng không ?");

        cboXeploai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lập trình java cơ bản", "Lập trình java 1", "Cơ sở dữ liệu" }));

        jbtThem.setText("THÊM");
        jbtThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtThemActionPerformed(evt);
            }
        });

        jbtCapnhat.setText("CẬP NHẬT");
        jbtCapnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCapnhatActionPerformed(evt);
            }
        });

        jbtXoa.setText("XÓA");
        jbtXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtXoaActionPerformed(evt);
            }
        });

        jbtNhapmoi.setText("NHẬP MỚI");
        jbtNhapmoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtNhapmoiActionPerformed(evt);
            }
        });

        jtbList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtbList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtbList);

        jbtXapxeptheodiem.setText("SẮP XẾP THEO ĐIỂM");
        jbtXapxeptheodiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtXapxeptheodiemActionPerformed(evt);
            }
        });

        jbtXapxeptheoten.setText("SẮP XẾP THEO TÊN");
        jbtXapxeptheoten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtXapxeptheotenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(119, 119, 119)
                            .addComponent(jbtThem)
                            .addGap(36, 36, 36)
                            .addComponent(jbtCapnhat)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                            .addComponent(jbtXoa)
                            .addGap(29, 29, 29)
                            .addComponent(jbtNhapmoi))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2))
                            .addGap(46, 46, 46)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtxeploai, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                                .addComponent(txthovaten)
                                .addComponent(txtdiem, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                                .addComponent(chbCophanthuongko)
                                .addComponent(cboXeploai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(52, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 35, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(jbtXapxeptheodiem)
                .addGap(43, 43, 43)
                .addComponent(jbtXapxeptheoten)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txthovaten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtdiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cboXeploai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtxeploai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(chbCophanthuongko)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtThem)
                    .addComponent(jbtCapnhat)
                    .addComponent(jbtXoa)
                    .addComponent(jbtNhapmoi))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtXapxeptheodiem)
                    .addComponent(jbtXapxeptheoten))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtThemActionPerformed
        String name = txthovaten.getText();
        String marksText = txtdiem.getText();
        String course = cboXeploai.getSelectedItem().toString();

        // Kiểm tra xem các trường dữ liệu có được nhập đầy đủ không
        if (name.isEmpty() || marksText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng không để trống dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return; // Dừng lại nếu có trường dữ liệu trống
        }

        // Kiểm tra tính hợp lệ của dữ liệu nhập vào từ txtdiem
        if (!isValidNumber(marksText)) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số từ 0 đến 10.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return; // Dừng lại nếu dữ liệu không hợp lệ
        }

        double marks = Double.parseDouble(marksText);

        // Gọi hàm để thêm dữ liệu vào bảng
        addDataToTable(name, marks, course);

    }//GEN-LAST:event_jbtThemActionPerformed

    private void jbtNhapmoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNhapmoiActionPerformed
        reset();
    }//GEN-LAST:event_jbtNhapmoiActionPerformed

    private void jtbListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbListMouseClicked
        int rowIndex = jtbList.getSelectedRow();

        if (rowIndex >= 0 && evt.getClickCount() == 1) {
            // Lấy dữ liệu từ bảng
            Object name = model.getValueAt(rowIndex, 0);
            Object marks = model.getValueAt(rowIndex, 1);
            Object course = model.getValueAt(rowIndex, 2);
            Object grade = model.getValueAt(rowIndex, 3);
            Object bonus = model.getValueAt(rowIndex, 4);

            // Đặt dữ liệu vào các trường tương ứng
            txthovaten.setText(name.toString());
            txtdiem.setText(marks.toString());
            txtxeploai.setText(grade.toString());
            cboXeploai.setSelectedItem(course.toString());

            // Kiểm tra và đặt trạng thái của checkbox phần thưởng
            chbCophanthuongko.setSelected(bonus.toString().equals("Có"));
        }
    }//GEN-LAST:event_jtbListMouseClicked

    private void jbtXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtXoaActionPerformed
        if (jtbList.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không có dữ liệu để xóa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa dòng này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) jtbList.getModel();
            int rowIndex = 0;
            model.removeRow(rowIndex);
            list.remove(rowIndex);
        }


    }//GEN-LAST:event_jbtXoaActionPerformed

    private void jbtCapnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCapnhatActionPerformed
        int rowIndex = jtbList.getSelectedRow();
        if (rowIndex >= 0) {
            int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật dữ liệu không?", "Xác nhận cập nhật", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                String name = txthovaten.getText();
                double marks = Double.parseDouble(txtdiem.getText());
                String course = cboXeploai.getSelectedItem().toString();

                updateDataInTable(rowIndex, name, marks, course);
                reset();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để cập nhật.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbtCapnhatActionPerformed

    private void jbtXapxeptheodiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtXapxeptheodiemActionPerformed
        sortByMarksDescending();
    }//GEN-LAST:event_jbtXapxeptheodiemActionPerformed

    private void jbtXapxeptheotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtXapxeptheotenActionPerformed
        sortByNameAscending();
    }//GEN-LAST:event_jbtXapxeptheotenActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(lad2bai1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(lad2bai1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(lad2bai1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(lad2bai1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new lad2bai1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboXeploai;
    private javax.swing.JCheckBox chbCophanthuongko;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtCapnhat;
    private javax.swing.JButton jbtNhapmoi;
    private javax.swing.JButton jbtThem;
    private javax.swing.JButton jbtXapxeptheodiem;
    private javax.swing.JButton jbtXapxeptheoten;
    private javax.swing.JButton jbtXoa;
    private javax.swing.JTable jtbList;
    private javax.swing.JTextField txtdiem;
    private javax.swing.JTextField txthovaten;
    private javax.swing.JTextField txtxeploai;
    // End of variables declaration//GEN-END:variables

    private void updateDataInTable() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static class NumberFilter extends DocumentFilter {

        public NumberFilter() {
        }
    }
}
