/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
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
import javax.swing.JViewport;

/**
 *
 * @author Admin
 */
public class Ass_Form_Students_Mark extends javax.swing.JFrame {

    List<Grade> list = new ArrayList<>();
    DefaultTableModel model;

    /**
     * Creates new form Ass_Form_Students_Mark
     */
    public Ass_Form_Students_Mark() {
        initComponents();
        this.setLocationRelativeTo(null);
        StudentsMarksDAO.fillTable((DefaultTableModel) jTable1.getModel());
        bntUpdate.setEnabled(false);
        txtMasv.setEnabled(false);

    }

    private void displaySelectedRowData() {
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow >= 0) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            txtMasv.setText(model.getValueAt(selectedRow, 0).toString());
            txtTensv.setText(model.getValueAt(selectedRow, 1).toString());
            txtTienganh.setText(model.getValueAt(selectedRow, 2).toString());
            txtTinhoc.setText(model.getValueAt(selectedRow, 3).toString());
            txtGiaoducTC.setText(model.getValueAt(selectedRow, 4).toString());

            calculateAndDisplayAverage();
        }
    }

    private void calculateAndDisplayAverage() {
        double tienganh = Double.parseDouble(txtTienganh.getText());
        double tinhoc = Double.parseDouble(txtTinhoc.getText());
        double giaoducTC = Double.parseDouble(txtGiaoducTC.getText());

        double diemtb = (tienganh + tinhoc + giaoducTC) / 3.0;
        lbDiemtb.setText(String.format("%.2f", diemtb));
    }

    private void moveToPriRow() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow != -1 && selectedRow != 0) {
            try {
                model.moveRow(selectedRow, selectedRow, 0);

                jTable1.setRowSelectionInterval(0, 0);
                 txtMasv.setText(model.getValueAt(0, 0).toString());
                txtTensv.setText(model.getValueAt(0, 1).toString());
                txtTienganh.setText(model.getValueAt(0, 2).toString());
                txtTinhoc.setText(model.getValueAt(0, 3).toString());
                txtGiaoducTC.setText(model.getValueAt(0, 4).toString());
            } catch (Exception e) {

                JOptionPane.showMessageDialog(this, "Không thể di chuyển dữ liệu lên trên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace(); // In ra thông tin lỗi ra console nếu cần thiết
            }
        }
    }

    private void moveToLastRow() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int rowCount = model.getRowCount();

        if (rowCount > 0) {
            int lastRow = rowCount - 1;
            jTable1.setRowSelectionInterval(lastRow, lastRow);
            jTable1.scrollRectToVisible(jTable1.getCellRect(lastRow, 0, true));
            txtMasv.setText(model.getValueAt(0, 0).toString());
            txtTensv.setText(model.getValueAt(0, 1).toString());
            txtTienganh.setText(model.getValueAt(0, 2).toString());
            txtTinhoc.setText(model.getValueAt(0, 3).toString());
            txtGiaoducTC.setText(model.getValueAt(0, 4).toString());

        }
    }

    private void moveToNextRow() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow != -1 && selectedRow < model.getRowCount() - 1) {
            int nextRow = selectedRow + 1;
            jTable1.setRowSelectionInterval(nextRow, nextRow);
            jTable1.scrollRectToVisible(jTable1.getCellRect(nextRow, 0, true));
            txtMasv.setText(model.getValueAt(0, 0).toString());
            txtTensv.setText(model.getValueAt(0, 1).toString());
            txtTienganh.setText(model.getValueAt(0, 2).toString());
            txtTinhoc.setText(model.getValueAt(0, 3).toString());
            txtGiaoducTC.setText(model.getValueAt(0, 4).toString());

        } else {

            JOptionPane.showMessageDialog(this, "Đã đến cuối danh sách.");
        }
    }

    private void moveToPreviousRow() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow > 0) {
            int previousRow = selectedRow - 1;
            jTable1.setRowSelectionInterval(previousRow, previousRow);
            jTable1.scrollRectToVisible(jTable1.getCellRect(previousRow, 0, true));
            txtTensv.setText(model.getValueAt(0, 0).toString());
            txtMasv.setText(model.getValueAt(0, 1).toString());
            txtTienganh.setText(model.getValueAt(0, 2).toString());
            txtTinhoc.setText(model.getValueAt(0, 3).toString());
            txtGiaoducTC.setText(model.getValueAt(0, 4).toString());

        } else {

            JOptionPane.showMessageDialog(this, "Đã ở dòng đầu tiên.");
        }
    }

    public void reset() {
        txtSearch.setText("");
        txtMasv.setText("");
        txtTensv.setText("");
        txtTienganh.setText("");
        txtTinhoc.setText("");
        txtGiaoducTC.setText("");
        lbDiemtb.setText("");

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
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        bntSearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMasv = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTienganh = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTinhoc = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtGiaoducTC = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        lbDiemtb = new javax.swing.JLabel();
        txtTensv = new javax.swing.JTextField();
        bntNew = new javax.swing.JButton();
        bntSave = new javax.swing.JButton();
        bntDelete = new javax.swing.JButton();
        bntUpdate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        bntFirst = new javax.swing.JButton();
        bntNext = new javax.swing.JButton();
        bntPrevious = new javax.swing.JButton();
        bntLast = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        bntLoc = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 255));
        jLabel1.setText("Quản lý điểm sinh viên");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Mã sv");

        bntSearch.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bntSearch.setIcon(new javax.swing.ImageIcon("D:\\java2\\iconG\\search.png")); // NOI18N
        bntSearch.setText("Search");
        bntSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bntSearch)
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntSearch))
                .addGap(22, 22, 22))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Tìm kiếm");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Tên sv");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Mã sv");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Tiếng anh");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Tin học");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Giáo dục TC");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Điểm TB");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(36, 36, 36))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtGiaoducTC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtTinhoc, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTienganh, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTensv, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMasv, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDiemtb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(txtMasv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(txtTensv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTienganh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDiemtb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtTinhoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtGiaoducTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        bntNew.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bntNew.setIcon(new javax.swing.ImageIcon("D:\\java2\\iconG\\New.png")); // NOI18N
        bntNew.setText("New");
        bntNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntNewActionPerformed(evt);
            }
        });

        bntSave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bntSave.setIcon(new javax.swing.ImageIcon("D:\\java2\\iconG\\Save.png")); // NOI18N
        bntSave.setText("Save");
        bntSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSaveActionPerformed(evt);
            }
        });

        bntDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bntDelete.setIcon(new javax.swing.ImageIcon("D:\\java2\\iconG\\Delete.png")); // NOI18N
        bntDelete.setText("Delete");
        bntDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntDeleteActionPerformed(evt);
            }
        });

        bntUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bntUpdate.setIcon(new javax.swing.ImageIcon("D:\\java2\\iconG\\update.png")); // NOI18N
        bntUpdate.setText("Update");
        bntUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntUpdateActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sv", "Tên sv", "Tiếng anh", "Tin học", "GDTC"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        bntFirst.setIcon(new javax.swing.ImageIcon("D:\\java2\\iconG\\first.png")); // NOI18N
        bntFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntFirstActionPerformed(evt);
            }
        });

        bntNext.setIcon(new javax.swing.ImageIcon("D:\\java2\\iconG\\next.png")); // NOI18N
        bntNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntNextActionPerformed(evt);
            }
        });

        bntPrevious.setIcon(new javax.swing.ImageIcon("D:\\java2\\iconG\\pri.png")); // NOI18N
        bntPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPreviousActionPerformed(evt);
            }
        });

        bntLast.setIcon(new javax.swing.ImageIcon("D:\\java2\\iconG\\last.png")); // NOI18N
        bntLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntLastActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 255));
        jLabel12.setText("3 Sinh viên có điểm cao nhất ");

        bntLoc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bntLoc.setText("Lọc 3 sv ");
        bntLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntLocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(152, 152, 152))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bntUpdate, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(bntDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bntSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bntNew, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(48, 48, 48))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bntFirst)
                                .addGap(18, 18, 18)
                                .addComponent(bntNext)
                                .addGap(18, 18, 18)
                                .addComponent(bntPrevious)
                                .addGap(18, 18, 18)
                                .addComponent(bntLast))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bntLoc))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bntNew)
                        .addGap(18, 18, 18)
                        .addComponent(bntSave)
                        .addGap(18, 18, 18)
                        .addComponent(bntDelete)
                        .addGap(18, 18, 18)
                        .addComponent(bntUpdate))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntFirst)
                    .addComponent(bntNext)
                    .addComponent(bntPrevious)
                    .addComponent(bntLast))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(bntLoc))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        displaySelectedRowData();
        bntSave.setEnabled(false);
        bntUpdate.setEnabled(true);

    }//GEN-LAST:event_jTable1MouseClicked

    private void bntNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntNewActionPerformed
        reset();
        StudentsMarksDAO.fillTable((DefaultTableModel) jTable1.getModel());
        bntSave.setEnabled(true);
        bntUpdate.setEnabled(false);

    }//GEN-LAST:event_bntNewActionPerformed

    private void bntDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntDeleteActionPerformed
        String masv = txtMasv.getText().trim();
        if (masv.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sinh viên cần xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sinh viên này không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            StudentsMarksDAO.deleteData(masv);
            StudentsMarksDAO.fillTable((DefaultTableModel) jTable1.getModel());

        }
    }//GEN-LAST:event_bntDeleteActionPerformed

    private void bntUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntUpdateActionPerformed
        String id = txtMasv.getText();
        String tenSV = txtTensv.getText();
        String tiengAnh = txtTienganh.getText();
        String tinHoc = txtTinhoc.getText();
        String giaoDucTC = txtGiaoducTC.getText();

        if (id.isEmpty() || tenSV.isEmpty() || tiengAnh.isEmpty() || tinHoc.isEmpty() || giaoDucTC.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            float diemTiengAnh = Float.parseFloat(tiengAnh);
            if (diemTiengAnh < 0 || diemTiengAnh > 10) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Điểm tiếng Anh phải là số và nằm trong khoảng từ 0 đến 10.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            float diemTinHoc = Float.parseFloat(tinHoc);
            if (diemTinHoc < 0 || diemTinHoc > 10) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Điểm tin học phải là số và nằm trong khoảng từ 0 đến 10.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            float diemGiaoDucTC = Float.parseFloat(giaoDucTC);
            if (diemGiaoDucTC < 0 || diemGiaoDucTC > 10) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Điểm giáo dục TC phải là số và nằm trong khoảng từ 0 đến 10.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StudentsMarksDAO.updateData(id, tenSV, tiengAnh, tinHoc, giaoDucTC);

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        StudentsMarksDAO.fillTable((DefaultTableModel) jTable1.getModel());
        JOptionPane.showMessageDialog(null, "Đã cập nhật thông tin cho sinh viên có mã " + id + ".", "Thông báo", JOptionPane.INFORMATION_MESSAGE);


    }//GEN-LAST:event_bntUpdateActionPerformed

    private void bntSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSaveActionPerformed
JOptionPane.showMessageDialog(this, "Bạn không có quyền thêm mới ");

    }//GEN-LAST:event_bntSaveActionPerformed

    private void bntSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSearchActionPerformed
        String masv = txtSearch.getText().trim();
        StudentsMarksDAO.search(masv, jTable1);


    }//GEN-LAST:event_bntSearchActionPerformed

    private void bntFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntFirstActionPerformed
        moveToPriRow();
    }//GEN-LAST:event_bntFirstActionPerformed

    private void bntNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntNextActionPerformed
        moveToNextRow();
    }//GEN-LAST:event_bntNextActionPerformed

    private void bntLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntLastActionPerformed
        moveToLastRow();
    }//GEN-LAST:event_bntLastActionPerformed

    private void bntPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPreviousActionPerformed
        moveToPreviousRow();
    }//GEN-LAST:event_bntPreviousActionPerformed

    private void bntLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntLocActionPerformed
        StudentsMarksDAO.displayTop3Students((DefaultTableModel) jTable1.getModel());
    }//GEN-LAST:event_bntLocActionPerformed

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
            java.util.logging.Logger.getLogger(Ass_Form_Students_Mark.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ass_Form_Students_Mark.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ass_Form_Students_Mark.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ass_Form_Students_Mark.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ass_Form_Students_Mark().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntDelete;
    private javax.swing.JButton bntFirst;
    private javax.swing.JButton bntLast;
    private javax.swing.JButton bntLoc;
    private javax.swing.JButton bntNew;
    private javax.swing.JButton bntNext;
    private javax.swing.JButton bntPrevious;
    private javax.swing.JButton bntSave;
    private javax.swing.JButton bntSearch;
    private javax.swing.JButton bntUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbDiemtb;
    private javax.swing.JTextField txtGiaoducTC;
    private javax.swing.JTextField txtMasv;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTensv;
    private javax.swing.JTextField txtTienganh;
    private javax.swing.JTextField txtTinhoc;
    // End of variables declaration//GEN-END:variables
}
