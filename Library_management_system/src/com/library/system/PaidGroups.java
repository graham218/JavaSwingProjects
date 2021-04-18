/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.system;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bill
 */
public class PaidGroups extends javax.swing.JFrame {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    /**
     * Creates new form Books
     */
    public PaidGroups() {
        initComponents();
    }
    
    String username;
    BufferedImage imag;
    public PaidGroups(String username,BufferedImage imag) {
        initComponents();
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        jLabel11.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("caleb-woods-fulXJYIvRi8-unsplash.jpg")).getImage().getScaledInstance(1250, 670, Image.SCALE_SMOOTH)));
        
        Connect(); 
        book_table();
    }

    public void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/Library","root","");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PaidGroups.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void book_table(){
        try {
            pst=con.prepareStatement("select * from paidBooksGroups");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i <= c; i++) {
                    v.add(rs.getString("datePaid"));
                    v.add(rs.getString("bookNo"));
                    v.add(rs.getString("bookName"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("studNames"));
                    v.add(rs.getString("admNos"));
                    v.add(rs.getString("confirmation"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PaidGroups.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        homeBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        lblProfile = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BOOKS IN LIBRARY");
        setMaximizedBounds(new java.awt.Rectangle(1248, 677, 677, 677));
        setMaximumSize(new java.awt.Dimension(1248, 677));
        setMinimumSize(new java.awt.Dimension(1248, 677));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(25, 22, 221));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE PAID", "BOOK NO", "BOOK NAME", "PRICE", "STUDENT NAMES", "ADMISSION NOs", "CONFIRMATION"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 1170, 450));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 250, 40));

        searchBtn.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        searchBtn.setText("SEARCH");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        jPanel1.add(searchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 110, 40));

        homeBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        homeBtn.setText("BACK");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 130, 50));

        exitBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(217, 38, 38));
        exitBtn.setText("EXIT SYSTEM");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 530, 140, 50));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 0, 60, 60));

        jLabel10.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel10.setForeground(java.awt.Color.white);
        jLabel10.setText("USE BOOK NUMBER TO SEARCH");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 1210, 590));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(247, 11, 11));
        jLabel1.setText("LOST BOOKS PAID BY GROUPS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, -1, -1));

        lblUsername.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(123, 27, 220));
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("USERNAME");
        getContentPane().add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 20, 170, 30));
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 6, 1250, 670));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Do you want to exit System?","Library Management System",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        this.setVisible(false);
        new More(lblUsername.getText(),imag).setVisible(true);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String bookNo=txtSearch.getText();
        try {
            pst=con.prepareStatement("select * from paidBooksGroups where bookNo like CONCAT('%',?,'%')");
            pst.setString(1,bookNo);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i <= c; i++) {
                    v.add(rs.getString("datePaid"));
                    v.add(rs.getString("bookNo"));
                    v.add(rs.getString("bookName"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("studNames"));
                    v.add(rs.getString("admNos"));
                    v.add(rs.getString("confirmation"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PaidGroups.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String bookNo=txtSearch.getText();
        try {
            pst=con.prepareStatement("select * from paidBooksGroups where bookNo like CONCAT('%',?,'%')");
            pst.setString(1,bookNo);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i <= c; i++) {
                    v.add(rs.getString("datePaid"));
                    v.add(rs.getString("bookNo"));
                    v.add(rs.getString("bookName"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("studNames"));
                    v.add(rs.getString("admNos"));
                    v.add(rs.getString("confirmation"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PaidGroups.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        
    }//GEN-LAST:event_tableMouseClicked

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
                             
    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(PaidGroups.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaidGroups.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaidGroups.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaidGroups.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaidGroups().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
