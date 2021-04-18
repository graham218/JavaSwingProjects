/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelmanagement.system;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author bill
 */
public class ViewPayments extends javax.swing.JFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    /**
     * Creates new form ManageClients
     */
    public ViewPayments() {
        initComponents();
    }
    
    String username;
    BufferedImage imag;
    String userType;
    public ViewPayments(String username,BufferedImage imag, String userType) {
        initComponents();
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("woman-1446557_1920.jpg")).getImage().getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), Image.SCALE_SMOOTH)));
        this.userType=userType;
        lblUserType.setText(userType);
        Connect();
        tenderPayment_table();
        tenderPay_table();
    }
    
    
    public void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://freedb.tech/freedbtech_orderMgmt", "freedbtech_billGraham", "86747486b");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*public void Connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:Hotel.db");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/


    public void tenderPayment_table(){
        try {
            pst=con.prepareStatement("select * from tenderPayment");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getInt("id"));
                    v.add(rs.getString("date"));
                    v.add(rs.getString("cashier"));
                    v.add(rs.getInt("subtotal"));
                    v.add(rs.getInt("pay"));
                    v.add(rs.getInt("balance"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewPayments.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tenderPay_table(){
        try {
            pst=con.prepareStatement("select * from tenderPay");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getInt("ida"));
                    v.add(rs.getInt("tenderId"));
                    v.add(rs.getString("tenderNo"));
                    v.add(rs.getString("itemName"));
                    v.add(rs.getInt("quantity"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getInt("total"));
                    v.add(rs.getString("accNo"));
                    v.add(rs.getString("date"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewPayments.class.getName()).log(Level.SEVERE, null, ex);
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
        table1 = new javax.swing.JTable();
        homeBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        lblProfile = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblUserType = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        searchText = new javax.swing.JTextField();
        searchBtn = new javax.swing.JToggleButton();
        csvExport = new javax.swing.JButton();
        jasperPrint = new javax.swing.JButton();
        printTable = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MANAGE CLIENTS");
        setMaximizedBounds(new java.awt.Rectangle(1236, 718, 718, 718));
        setMinimumSize(new java.awt.Dimension(1236, 718));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(151, 48, 108));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DATE PAID", "CASHIER", "SUBTOTAL", "PAYMENT", "BALANCE"
            }
        ));
        table1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 1090, 200));

        homeBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        homeBtn.setForeground(new java.awt.Color(36, 39, 246));
        homeBtn.setText("HOME");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, 110, 50));

        exitBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(238, 13, 24));
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 560, 120, 50));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 0, 80, 80));

        lblUsername.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblUsername.setForeground(java.awt.Color.white);
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("USERNAME");
        jPanel1.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 40, 180, -1));

        lblUserType.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblUserType.setForeground(java.awt.Color.white);
        lblUserType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserType.setText("USER TYPE");
        jPanel1.add(lblUserType, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 220, -1));

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDA", "TENDER ID", "TENDER NO", "ITEM NAME", "QUANTITY", "PRICE", "TOTAL", "ACCOUNT NO", "DATE PAID"
            }
        ));
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 1090, 230));

        searchText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchTextKeyPressed(evt);
            }
        });
        jPanel1.add(searchText, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 300, -1));

        searchBtn.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        searchBtn.setText("SEARCH WITH PAYMENT DATE");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        jPanel1.add(searchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 320, -1));

        csvExport.setBackground(new java.awt.Color(37, 207, 39));
        csvExport.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        csvExport.setForeground(java.awt.Color.white);
        csvExport.setText("EXPORT TO A \".csv\" File");
        csvExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                csvExportActionPerformed(evt);
            }
        });
        jPanel1.add(csvExport, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 550, 200, 50));

        jasperPrint.setBackground(new java.awt.Color(37, 207, 39));
        jasperPrint.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        jasperPrint.setForeground(java.awt.Color.white);
        jasperPrint.setText("JASPER PRINT ");
        jasperPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jasperPrintActionPerformed(evt);
            }
        });
        jPanel1.add(jasperPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 550, 160, 50));

        printTable.setBackground(new java.awt.Color(37, 207, 39));
        printTable.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        printTable.setForeground(java.awt.Color.white);
        printTable.setText("PRINT TABLE");
        printTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printTableActionPerformed(evt);
            }
        });
        jPanel1.add(printTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 550, 160, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 1210, 620));

        jLabel1.setFont(new java.awt.Font("Tibetan Machine Uni", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(45, 13, 249));
        jLabel1.setText("VIEW SUPPLIERS' PAYMENTS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 740, 50));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        this.setVisible(false);
        new Main(lblUsername.getText(),imag, userType).setVisible(true);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to exit Hotel System?","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed
    
    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked

    }//GEN-LAST:event_table1MouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        
    }//GEN-LAST:event_table2MouseClicked

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String paymentDate=searchText.getText();
        try {
            pst=con.prepareStatement("select * from tenderPayment where date LIKE CONCAT('%',?,'%')");
            pst.setString(1,paymentDate);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model;
            
            model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getInt("id"));
                    v.add(rs.getString("date"));
                    v.add(rs.getString("cashier"));
                    v.add(rs.getInt("subtotal"));
                    v.add(rs.getInt("pay"));
                    v.add(rs.getInt("balance"));
                }
                model.addRow(v);
            }
            
            
            pst=con.prepareStatement("select * from tenderPay where date LIKE CONCAT('%',?,'%')");
            pst.setString(1,paymentDate);
            rs=pst.executeQuery();
            model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getInt("ida"));
                    v.add(rs.getInt("tenderId"));
                    v.add(rs.getString("tenderNo"));
                    v.add(rs.getString("itemName"));
                    v.add(rs.getInt("quantity"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getInt("total"));
                    v.add(rs.getString("accNo"));
                    v.add(rs.getString("date"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ViewPayments.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    private void searchTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String paymentDate=searchText.getText();
            try {
                pst=con.prepareStatement("select * from tenderPayment where date LIKE CONCAT('%',?,'%')");
                pst.setString(1,paymentDate);
                rs=pst.executeQuery();
                ResultSetMetaData rsm=rs.getMetaData();
                int c=rsm.getColumnCount();

                DefaultTableModel model;

                model=(DefaultTableModel)table1.getModel();
                model.setRowCount(0);
                while(rs.next()){
                    Vector v=new Vector();
                    for(int i=1;i<c;i++){
                        v.add(rs.getInt("id"));
                        v.add(rs.getString("date"));
                        v.add(rs.getString("cashier"));
                        v.add(rs.getInt("subtotal"));
                        v.add(rs.getInt("pay"));
                        v.add(rs.getInt("balance"));
                    }
                    model.addRow(v);
                }


                pst=con.prepareStatement("select * from tenderPay where date LIKE CONCAT('%',?,'%')");
                pst.setString(1,paymentDate);
                rs=pst.executeQuery();
                model=(DefaultTableModel)table2.getModel();
                model.setRowCount(0);
                while(rs.next()){
                    Vector v=new Vector();
                    for(int i=1;i<c;i++){
                        v.add(rs.getInt("ida"));
                        v.add(rs.getInt("tenderId"));
                        v.add(rs.getString("tenderNo"));
                        v.add(rs.getString("itemName"));
                        v.add(rs.getInt("quantity"));
                        v.add(rs.getInt("price"));
                        v.add(rs.getInt("total"));
                        v.add(rs.getString("accNo"));
                        v.add(rs.getString("date"));
                    }
                    model.addRow(v);
                }

            } catch (SQLException ex) {
                Logger.getLogger(ViewPayments.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_searchTextKeyPressed

    private void csvExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_csvExportActionPerformed
        JFileChooser fileChooser=new JFileChooser();
        fileChooser.setDialogTitle("Export table to an excel file.csv format?");
        int userSelection=fileChooser.showSaveDialog(this);
        if(userSelection==JFileChooser.APPROVE_OPTION){
            File file=fileChooser.getSelectedFile();

            FileWriter writer;
            try {
                writer=new FileWriter(file);
                BufferedWriter bWriter=new BufferedWriter(writer);
                for (int i = 0; i < table2.getRowCount(); i++) {
                    for (int j = 0; j < table2.getColumnCount(); j++) {
                        bWriter.write(table2.getValueAt(i, j).toString()+",");
                    }
                    bWriter.newLine();
                }
                bWriter.close();
                writer.close();
                JOptionPane.showMessageDialog(this, "Table exported successfully into an excel file .csv format");

            } catch (IOException ex) {
                Logger.getLogger(HotelMeals.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_csvExportActionPerformed

    private void jasperPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jasperPrintActionPerformed
        try {
            JasperDesign jdesign=JRXmlLoader.load("src/com/hotelmanagement/system/TenderPayment.jrxml");
            JasperReport ireport=JasperCompileManager.compileReport(jdesign);
            JasperPrint jPrint=JasperFillManager.fillReport(ireport, null, con);
            JasperViewer.viewReport(jPrint,false);

        } catch (JRException ex) {
            Logger.getLogger(SellMeals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jasperPrintActionPerformed

    private void printTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printTableActionPerformed
        MessageFormat header=new MessageFormat("SUPPLIERS' PAYMENTS");
        MessageFormat footer=new MessageFormat("page {0}");
        try {
            table2.print(JTable.PrintMode.FIT_WIDTH,header,footer);
        } catch (PrinterException ex) {
            Logger.getLogger(AllocateRooms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_printTableActionPerformed

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
            java.util.logging.Logger.getLogger(ViewPayments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPayments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPayments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPayments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewPayments().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton csvExport;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jasperPrint;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblUserType;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JButton printTable;
    private javax.swing.JToggleButton searchBtn;
    private javax.swing.JTextField searchText;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    // End of variables declaration//GEN-END:variables
}
