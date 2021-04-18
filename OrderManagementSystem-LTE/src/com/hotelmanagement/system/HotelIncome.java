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
import java.text.MessageFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class HotelIncome extends javax.swing.JFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    /**
     * Creates new form ViewSales
     */
    public HotelIncome() {
        initComponents();
    }
    
    String username;
    BufferedImage imag;
    String userType;
    public HotelIncome(String username,BufferedImage imag, String userType) {
        initComponents();
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("emmanuel-chigbo-jr7R01lOn1Y-unsplash.jpg")).getImage().getScaledInstance(1230, 660, Image.SCALE_SMOOTH)));
        this.userType=userType;
        lblUserType.setText(userType);
        Connect();
        room_allocations_payment();
        roomBookings_payment();
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
    public void room_allocations_payment(){
        try {
            pst=con.prepareStatement("select * from clientPay");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("datee"));
                    v.add(rs.getString("cashier"));
                    v.add(rs.getString("clientID"));
                    v.add(rs.getString("payment"));
                    v.add(rs.getString("balance"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SellMeals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void roomBookings_payment(){
        try {
            pst=con.prepareStatement("select * from clientBook");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("datee"));
                    v.add(rs.getString("cashier"));
                    v.add(rs.getString("clientID"));
                    v.add(rs.getString("payment"));
                    v.add(rs.getString("balance"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SellMeals.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        searchBtn1 = new javax.swing.JButton();
        txtSearch1 = new javax.swing.JTextField();
        homeBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        jasperPrint1 = new javax.swing.JButton();
        csvExport1 = new javax.swing.JButton();
        printTable1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        txtSearch2 = new javax.swing.JTextField();
        searchBtn2 = new javax.swing.JButton();
        lblProfile = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblUserType = new javax.swing.JLabel();
        csvExport = new javax.swing.JButton();
        jasperPrint = new javax.swing.JButton();
        printTable = new javax.swing.JButton();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("VIEW MEAL SALES");
        setMaximizedBounds(new java.awt.Rectangle(1214, 677, 677, 677));
        setMinimumSize(new java.awt.Dimension(1214, 677));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tibetan Machine Uni", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(45, 13, 249));
        jLabel1.setText("HOTEL INCOME");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, -1, 50));

        jPanel1.setBackground(new java.awt.Color(28, 121, 22));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ROOM ALLOCATIONS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 3, 18), java.awt.Color.white)); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE", "CASHIER", "NATIONAL ID", "PAY", "BALANCE"
            }
        ));
        jScrollPane1.setViewportView(table1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 550, 330));

        searchBtn1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        searchBtn1.setText("SEARCH WITH DATE");
        searchBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtn1ActionPerformed(evt);
            }
        });
        jPanel1.add(searchBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 280, 40));

        txtSearch1.setToolTipText("use date to search");
        txtSearch1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearch1KeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 260, 40));

        homeBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        homeBtn.setForeground(new java.awt.Color(36, 39, 246));
        homeBtn.setText("HOME PAGE");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 130, 50));

        exitBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(238, 13, 24));
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 520, 130, 50));

        jasperPrint1.setBackground(new java.awt.Color(37, 207, 39));
        jasperPrint1.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        jasperPrint1.setForeground(java.awt.Color.white);
        jasperPrint1.setText("JASPER PRINT ");
        jasperPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jasperPrint1ActionPerformed(evt);
            }
        });
        jPanel1.add(jasperPrint1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 160, 50));

        csvExport1.setBackground(new java.awt.Color(37, 207, 39));
        csvExport1.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        csvExport1.setForeground(java.awt.Color.white);
        csvExport1.setText("EXPORT TO A \".csv\" File");
        csvExport1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                csvExport1ActionPerformed(evt);
            }
        });
        jPanel1.add(csvExport1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 440, 200, 50));

        printTable1.setBackground(new java.awt.Color(37, 207, 39));
        printTable1.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        printTable1.setForeground(java.awt.Color.white);
        printTable1.setText("PRINT TABLE");
        printTable1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printTable1ActionPerformed(evt);
            }
        });
        jPanel1.add(printTable1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 440, 170, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 580, 590));

        jPanel2.setBackground(new java.awt.Color(22, 22, 242));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ROOM BOOKINGS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 3, 18), java.awt.Color.white)); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE", "CASHIER", "NATIONAL ID", "PAYMENT", "BALANCE"
            }
        ));
        jScrollPane2.setViewportView(table2);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 590, 400));

        txtSearch2.setToolTipText("use sales id to search");
        txtSearch2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearch2KeyPressed(evt);
            }
        });
        jPanel2.add(txtSearch2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 170, 40));

        searchBtn2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        searchBtn2.setText("SEARCH WITH DATE");
        searchBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtn2ActionPerformed(evt);
            }
        });
        jPanel2.add(searchBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 260, 40));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel2.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 80, 80));

        lblUsername.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        lblUsername.setForeground(java.awt.Color.white);
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("USERNAME");
        jPanel2.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, 170, -1));

        lblUserType.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        lblUserType.setForeground(java.awt.Color.white);
        lblUserType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserType.setText("USER TYPE");
        jPanel2.add(lblUserType, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 180, -1));

        csvExport.setBackground(new java.awt.Color(37, 207, 39));
        csvExport.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        csvExport.setForeground(java.awt.Color.white);
        csvExport.setText("EXPORT TO A \".csv\" File");
        csvExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                csvExportActionPerformed(evt);
            }
        });
        jPanel2.add(csvExport, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 520, 200, 50));

        jasperPrint.setBackground(new java.awt.Color(37, 207, 39));
        jasperPrint.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        jasperPrint.setForeground(java.awt.Color.white);
        jasperPrint.setText("JASPER PRINT ");
        jasperPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jasperPrintActionPerformed(evt);
            }
        });
        jPanel2.add(jasperPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 520, 160, 50));

        printTable.setBackground(new java.awt.Color(37, 207, 39));
        printTable.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        printTable.setForeground(java.awt.Color.white);
        printTable.setText("PRINT TABLE");
        printTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printTableActionPerformed(evt);
            }
        });
        jPanel2.add(printTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 520, 160, 50));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, 620, 590));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, 660));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void searchBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtn1ActionPerformed
        String date=txtSearch1.getText();
        try {
            pst=con.prepareStatement("select * from clientPay where datee like CONCAT('%',?,'%')");
            pst.setString(1,date);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("datee"));
                    v.add(rs.getString("cashier"));
                    v.add(rs.getString("clientID"));
                    v.add(rs.getString("payment"));
                    v.add(rs.getString("balance"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SellMeals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchBtn1ActionPerformed

    private void txtSearch1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String date=txtSearch1.getText();
        try {
            pst=con.prepareStatement("select * from clientPay where datee like CONCAT('%',?,'%')");
            pst.setString(1,date);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("date"));
                    v.add(rs.getString("cashier"));
                    v.add(rs.getString("subtotal"));
                    v.add(rs.getString("pay"));
                    v.add(rs.getString("balance"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SellMeals.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_txtSearch1KeyPressed

    private void searchBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtn2ActionPerformed
        String date=txtSearch2.getText();
        try {
            pst=con.prepareStatement("select * from clientBook where datee like CONCAT('%',?,'%')");
            pst.setString(1,date);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("datee"));
                    v.add(rs.getString("cashier"));
                    v.add(rs.getString("clientID"));
                    v.add(rs.getString("payment"));
                    v.add(rs.getString("balance"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SellMeals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchBtn2ActionPerformed

    private void txtSearch2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String date=txtSearch2.getText();
        try {
            pst=con.prepareStatement("select * from meal_sales where datee like CONCAT('%',?,'%')");
            pst.setString(1,date);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("datee"));
                    v.add(rs.getString("cashier"));
                    v.add(rs.getString("clientID"));
                    v.add(rs.getString("payment"));
                    v.add(rs.getString("balance"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SellMeals.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_txtSearch2KeyPressed

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        this.setVisible(false);
        new Main(lblUsername.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to exit Hotel System?","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

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
            JasperDesign jdesign=JRXmlLoader.load("src/com/hotelmanagement/system/CashRoomBookings.jrxml");
            JasperReport ireport=JasperCompileManager.compileReport(jdesign);
            JasperPrint jPrint=JasperFillManager.fillReport(ireport, null, con);
            JasperViewer.viewReport(jPrint,false);

        } catch (JRException ex) {
            Logger.getLogger(SellMeals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jasperPrintActionPerformed

    private void printTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printTableActionPerformed
        MessageFormat header=new MessageFormat("ROOM BOOKINGS CASH FLOW");
        MessageFormat footer=new MessageFormat("page {0}");
        try {
            table2.print(JTable.PrintMode.FIT_WIDTH,header,footer);
        } catch (PrinterException ex) {
            Logger.getLogger(AllocateRooms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_printTableActionPerformed

    private void jasperPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jasperPrint1ActionPerformed
        try {
            JasperDesign jdesign=JRXmlLoader.load("src/com/hotelmanagement/system/CashRoomAllocations.jrxml");
            JasperReport ireport=JasperCompileManager.compileReport(jdesign);
            JasperPrint jPrint=JasperFillManager.fillReport(ireport, null, con);
            JasperViewer.viewReport(jPrint,false);

        } catch (JRException ex) {
            Logger.getLogger(SellMeals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jasperPrint1ActionPerformed

    private void csvExport1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_csvExport1ActionPerformed
        JFileChooser fileChooser=new JFileChooser();
        fileChooser.setDialogTitle("Export table to an excel file.csv format?");
        int userSelection=fileChooser.showSaveDialog(this);
        if(userSelection==JFileChooser.APPROVE_OPTION){
            File file=fileChooser.getSelectedFile();

            FileWriter writer;
            try {
                writer=new FileWriter(file);
                BufferedWriter bWriter=new BufferedWriter(writer);
                for (int i = 0; i < table1.getRowCount(); i++) {
                    for (int j = 0; j < table1.getColumnCount(); j++) {
                        bWriter.write(table1.getValueAt(i, j).toString()+",");
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
    }//GEN-LAST:event_csvExport1ActionPerformed

    private void printTable1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printTable1ActionPerformed
        MessageFormat header=new MessageFormat("ROOM ALLOCATIONS CASH FLOW");
        MessageFormat footer=new MessageFormat("page {0}");
        try {
            table1.print(JTable.PrintMode.FIT_WIDTH,header,footer);
        } catch (PrinterException ex) {
            Logger.getLogger(AllocateRooms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_printTable1ActionPerformed

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
            java.util.logging.Logger.getLogger(HotelIncome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HotelIncome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HotelIncome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HotelIncome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HotelIncome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton csvExport;
    private javax.swing.JButton csvExport1;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jasperPrint;
    private javax.swing.JButton jasperPrint1;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblUserType;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JButton printTable;
    private javax.swing.JButton printTable1;
    private javax.swing.JButton searchBtn1;
    private javax.swing.JButton searchBtn2;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JTextField txtSearch1;
    private javax.swing.JTextField txtSearch2;
    // End of variables declaration//GEN-END:variables
}
