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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class StockInStore extends javax.swing.JFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    /**
     * Creates new form ManageClients
     */
    public StockInStore() {
        initComponents();
    }
    
    String username;
    BufferedImage imag;
    String userType;
    public StockInStore(String username,BufferedImage imag, String userType) {
        initComponents();
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("social-media-3758364_1920.jpg")).getImage().getScaledInstance(1240, 720, Image.SCALE_SMOOTH)));
        this.userType=userType;
        lblUserType.setText(userType);
        Connect();
        current_date();
        Auto_id();
        Store_Table();
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
    
    public void Auto_id(){
        try {
            Statement s=con.createStatement();
            rs=s.executeQuery("select MAX(itemNumber) from stockInStore");
            rs.next();
            rs.getString("MAX(itemNumber)");
            if(rs.getString("MAX(itemNumber)")==null){
                lblItemNo.setText("IT00001");
            }else{
                long id=Long.parseLong(rs.getString("MAX(itemNumber)").substring(2,rs.getString("MAX(itemNumber)").length()));
                id++;
                lblItemNo.setText("IT"+String.format("%05d", id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StockInStore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Store_Table(){
        try {
            pst=con.prepareStatement("select * from stockInStore");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("dateAdded"));
                    v.add(rs.getString("itemNumber"));
                    v.add(rs.getString("itemName"));
                    v.add(rs.getString("description"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getInt("quantity"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StockInStore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void current_date(){
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now=LocalDateTime.now();
        String date=dtf.format(now);
        lblDate.setText(date);
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
        jLabel2 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        txtQty = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        homeBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        lblItemNo = new javax.swing.JLabel();
        txtItemName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblProfile = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblUserType = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        csvExport = new javax.swing.JButton();
        jasperPrint = new javax.swing.JButton();
        printTable = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("STOCK ITEMS IN STORE");
        setMaximizedBounds(new java.awt.Rectangle(1236, 718, 718, 718));
        setMinimumSize(new java.awt.Dimension(1236, 718));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(141, 45, 175));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("ITEM NUMBER:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        txtDescription.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescriptionKeyPressed(evt);
            }
        });
        jPanel1.add(txtDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 260, 40));

        jLabel3.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("DESCRIPTION:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("ITEM PRICE:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        txtPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPriceKeyPressed(evt);
            }
        });
        jPanel1.add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 260, 40));

        txtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQtyKeyPressed(evt);
            }
        });
        jPanel1.add(txtQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 260, 40));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE ADDED", "ITEM NO", "ITEM NAME", "DESCRIPTION", "ITEM PRICE", "QUANTITY"
            }
        ));
        table.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 740, 410));

        homeBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        homeBtn.setForeground(new java.awt.Color(36, 39, 246));
        homeBtn.setText("HOME PAGE");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 130, 50));

        addBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        addBtn.setForeground(new java.awt.Color(0, 22, 255));
        addBtn.setText("ADD ITEM");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 150, 50));

        editBtn.setBackground(new java.awt.Color(37, 185, 35));
        editBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        editBtn.setForeground(java.awt.Color.white);
        editBtn.setText("EDIT ITEM");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        jPanel1.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 380, 130, 50));

        exitBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(238, 13, 24));
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 450, 130, 50));

        clearBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(128, 13, 238));
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 130, 50));

        deleteBtn.setBackground(new java.awt.Color(228, 11, 11));
        deleteBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        deleteBtn.setForeground(java.awt.Color.white);
        deleteBtn.setText("DELETE ITEM");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 450, 150, 50));

        lblItemNo.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblItemNo.setForeground(java.awt.Color.white);
        lblItemNo.setText("ITEM NUMBER");
        lblItemNo.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblItemNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 250, 40));

        txtItemName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtItemNameKeyPressed(evt);
            }
        });
        jPanel1.add(txtItemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 260, 40));

        jLabel7.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("ITEM NAME:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        btnSearch.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        btnSearch.setText("SEARCH WITH ITEM NAME");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 40, 250, 40));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 190, 40));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("NOTE: USE ITEM NAMES TO SEARCH FOR ITEMS IN STORE");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 500, 30));

        lblDate.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblDate.setForeground(java.awt.Color.white);
        lblDate.setText("DATE SUPPLIED");
        lblDate.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 250, 40));

        jLabel8.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel8.setForeground(java.awt.Color.white);
        jLabel8.setText("DATE SUPPLIED:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

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

        jLabel9.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel9.setForeground(java.awt.Color.white);
        jLabel9.setText("QUANTITY:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        csvExport.setBackground(new java.awt.Color(37, 207, 39));
        csvExport.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        csvExport.setForeground(java.awt.Color.white);
        csvExport.setText("EXPORT TO A \".csv\" File");
        csvExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                csvExportActionPerformed(evt);
            }
        });
        jPanel1.add(csvExport, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 510, 200, 50));

        jasperPrint.setBackground(new java.awt.Color(37, 207, 39));
        jasperPrint.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        jasperPrint.setForeground(java.awt.Color.white);
        jasperPrint.setText("JASPER PRINT ");
        jasperPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jasperPrintActionPerformed(evt);
            }
        });
        jPanel1.add(jasperPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 510, 160, 50));

        printTable.setBackground(new java.awt.Color(37, 207, 39));
        printTable.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        printTable.setForeground(java.awt.Color.white);
        printTable.setText("PRINT TABLE");
        printTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printTableActionPerformed(evt);
            }
        });
        jPanel1.add(printTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 510, 160, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 1210, 590));

        jLabel1.setFont(new java.awt.Font("Tibetan Machine Uni", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(45, 13, 249));
        jLabel1.setText("ITEMS IN STORE");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, -1, 50));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        this.setVisible(false);
        new Main(lblUsername.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        String itemNo=lblItemNo.getText();       
        try {
            pst=con.prepareStatement("delete from stockInStore where itemNumber=?");
            pst.setString(1,itemNo);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Item Deleted Successfully from database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            Auto_id();
            txtItemName.setText("");
            txtDescription.setText("");
            txtPrice.setText("");
            txtQty.setText("");
            txtItemName.requestFocus();
            Store_Table();
            current_date();
        } catch (SQLException ex) {
            Logger.getLogger(StockInStore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to exit Hotel System?","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String dateAdded=lblDate.getText();
        String itemNumber=lblItemNo.getText();
        String itemName=txtItemName.getText();
        String description=txtDescription.getText();
        int price=Integer.parseInt(txtPrice.getText());
        int quantity=Integer.parseInt(txtQty.getText());
        
        if(itemName.trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter item name, null item names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtItemName.requestFocus();
        }else if(description.trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter item description, null item descriptions cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtDescription.requestFocus();
        }else if(txtPrice.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter item price, null item prices cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPrice.requestFocus();
        }else if(txtQty.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter quantity, null quantities cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtQty.requestFocus();
        }else{
        try {
            pst=con.prepareStatement("insert into stockInStore (dateAdded,itemNumber,itemName,description,price,quantity)values(?,?,?,?,?,?)");
            pst.setString(1,dateAdded);
            pst.setString(2,itemNumber);
            pst.setString(3,itemName);
            pst.setString(4,description);
            pst.setInt(5,price);
            pst.setInt(6,quantity);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Item Added Successfully into database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            current_date();
            Auto_id();
            txtItemName.setText("");
            txtDescription.setText("");
            txtPrice.setText("");
            txtQty.setText("");
            txtItemName.requestFocus();
            Store_Table();
        } catch (SQLException ex) {
            Logger.getLogger(StockInStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        txtItemName.setText("");
        txtDescription.setText("");
        txtPrice.setText("");
        txtQty.setText("");
        txtItemName.requestFocus();
        current_date();
        Auto_id();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        String itemNumber=lblItemNo.getText();
        String itemName=txtItemName.getText();
        String description=txtDescription.getText();
        int price=Integer.parseInt(txtPrice.getText());
        int quantity=Integer.parseInt(txtQty.getText());
        
        if(itemNumber.trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter item name, null item names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtItemName.requestFocus();
        }else if(description.trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter item description, null item descriptions cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtDescription.requestFocus();
        }else if(txtPrice.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter item price, null item prices cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPrice.requestFocus();
        }else if(txtQty.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter quantity, null quantities cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtQty.requestFocus();
        }else{
        try {
            pst=con.prepareStatement("update stockInStore set itemName=?, description=?, price=?, quantity=? where itemNumber=?");
            pst.setString(1,itemName);
            pst.setString(2,description);
            pst.setInt(3,price);
            pst.setInt(4,quantity);
            pst.setString(5,itemNumber);
            pst.execute();
            JOptionPane.showMessageDialog(this,"Item Updated Successfully on database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            current_date();
            Auto_id();
            txtItemName.setText("");
            txtDescription.setText("");
            txtPrice.setText("");
            txtQty.setText("");
            txtItemName.requestFocus();
            Store_Table();
        } catch (SQLException ex) {
            Logger.getLogger(StockInStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_editBtnActionPerformed
    
    private void txtDescriptionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescriptionKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtPrice.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtPrice.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtItemName.requestFocus();
        }
    }//GEN-LAST:event_txtDescriptionKeyPressed

    private void txtPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPriceKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtQty.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtQty.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtDescription.requestFocus();
        }
    }//GEN-LAST:event_txtPriceKeyPressed

    private void txtQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        String itemNumber=lblItemNo.getText();
        String itemName=txtItemName.getText();
        String description=txtDescription.getText();
        int price=Integer.parseInt(txtPrice.getText());
        int quantity=Integer.parseInt(txtQty.getText());
        
        if(itemName.trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter item name, null item names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtItemName.requestFocus();
        }else if(description.trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter item description, null item descriptions cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtDescription.requestFocus();
        }else if(txtPrice.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter item price, null item prices cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPrice.requestFocus();
        }else if(txtQty.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter quantity, null quantities cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtQty.requestFocus();
        }else{
        try {
            pst=con.prepareStatement("insert into stockInStore (itemNumber,itemName,description,price,quantity)values(?,?,?,?,?)");
            pst.setString(1,itemNumber);
            pst.setString(2,itemName);
            pst.setString(3,description);
            pst.setInt(4,price);
            pst.setInt(5,quantity);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Item Added Successfully into database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            current_date();
            Auto_id();
            txtItemName.setText("");
            txtDescription.setText("");
            txtPrice.setText("");
            txtQty.setText("");
            txtItemName.requestFocus();
            Store_Table();
        } catch (SQLException ex) {
            Logger.getLogger(StockInStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtItemName.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtPrice.requestFocus();
        }
    }//GEN-LAST:event_txtQtyKeyPressed

    private void txtItemNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtItemNameKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtDescription.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtDescription.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtQty.requestFocus();
        }
    }//GEN-LAST:event_txtItemNameKeyPressed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        DefaultTableModel model=(DefaultTableModel)table.getModel();
        int selectedIndex=table.getSelectedRow();
        lblItemNo.setText(model.getValueAt(selectedIndex, 1).toString());
        txtItemName.setText(model.getValueAt(selectedIndex, 2).toString());
        txtDescription.setText(model.getValueAt(selectedIndex, 3).toString());
        txtPrice.setText(model.getValueAt(selectedIndex, 4).toString());
        txtQty.setText(model.getValueAt(selectedIndex, 5).toString());
        
        editBtn.setEnabled(true);
        deleteBtn.setEnabled(true);
    }//GEN-LAST:event_tableMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
    }//GEN-LAST:event_formWindowActivated

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String mealName=txtSearch.getText();
        try {
            pst=con.prepareStatement("select * from stockInStore where itemName like CONCAT('%',?,'%')");
            pst.setString(1,mealName);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();

            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("dateAdded"));
                    v.add(rs.getString("itemNumber"));
                    v.add(rs.getString("itemName"));
                    v.add(rs.getString("description"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getInt("quantity"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HotelMeals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String mealName=txtSearch.getText();
        try {
            pst=con.prepareStatement("select * from stockInStore where itemName like CONCAT('%',?,'%')");
            pst.setString(1,mealName);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();

            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("dateAdded"));
                    v.add(rs.getString("itemNumber"));
                    v.add(rs.getString("itemName"));
                    v.add(rs.getString("description"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getInt("quantity"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HotelMeals.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_txtSearchKeyPressed

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
                for (int i = 0; i < table.getRowCount(); i++) {
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        bWriter.write(table.getValueAt(i, j).toString()+",");
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
            JasperDesign jdesign=JRXmlLoader.load("src/com/hotelmanagement/system/StockInStoreInvoice.jrxml");
            JasperReport ireport=JasperCompileManager.compileReport(jdesign);
            JasperPrint jPrint=JasperFillManager.fillReport(ireport, null, con);
            JasperViewer.viewReport(jPrint,false);

        } catch (JRException ex) {
            Logger.getLogger(SellMeals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jasperPrintActionPerformed

    private void printTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printTableActionPerformed
        MessageFormat header=new MessageFormat("STOCK ITEMS IN STORE");
        MessageFormat footer=new MessageFormat("page {0}");
        try {
            table.print(JTable.PrintMode.FIT_WIDTH,header,footer);
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
            java.util.logging.Logger.getLogger(StockInStore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StockInStore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StockInStore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StockInStore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new StockInStore().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton csvExport;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jasperPrint;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblItemNo;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblUserType;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JButton printTable;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
