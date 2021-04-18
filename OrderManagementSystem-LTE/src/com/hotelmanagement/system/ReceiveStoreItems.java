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

/**
 *
 * @author bill
 */
public class ReceiveStoreItems extends javax.swing.JFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    /**
     * Creates new form ManageClients
     */
    public ReceiveStoreItems() {
        initComponents();
    }
    
    String username;
    BufferedImage imag;
    String userType;
    public ReceiveStoreItems(String username,BufferedImage imag, String userType) {
        initComponents();
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("social-3064515_1920.jpg")).getImage().getScaledInstance(1240, 720, Image.SCALE_SMOOTH)));
        this.userType=userType;
        lblUserType.setText(userType);
        Connect();
        current_date();
        confirm_ordered_items_table();
        Order_table();
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
    

    public void Order_table(){
        try {
            pst=con.prepareStatement("select * from departmentalOrders");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("dateOrdered"));
                    v.add(rs.getString("orderNo"));
                    v.add(rs.getString("itemName"));
                    v.add(rs.getString("description"));
                    v.add(rs.getInt("quantity"));
                    v.add(rs.getString("department"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReceiveStoreItems.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void confirm_ordered_items_table(){
        try {
            pst=con.prepareStatement("select * from confirmOrderedItems");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("dateConfirmed"));
                    v.add(rs.getString("orderNo"));
                    v.add(rs.getString("itemName"));
                    v.add(rs.getString("description"));
                    v.add(rs.getString("department"));
                    v.add(rs.getInt("quantity"));
                    v.add(rs.getString("confirmation"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReceiveStoreItems.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void current_date(){
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now=LocalDateTime.now();
        String date=dtf.format(now);
        lblConfirmDate.setText(date);
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
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        homeBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        lblOrderNo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        lblConfirmDate = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblProfile = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblUserType = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        combReceive = new javax.swing.JComboBox<>();
        lblItemName = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        searchTxt = new javax.swing.JTextField();
        searchBtn = new javax.swing.JToggleButton();
        lblDepartment = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
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

        jPanel1.setBackground(new java.awt.Color(173, 33, 221));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("ORDER NO:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("DESCRIPTION:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE ORDERD", "ORDER NO", "ITEM NAME", "DESCRIPTION", "QUANTITY", "DEPARTMENT"
            }
        ));
        table1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 740, 200));

        homeBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        homeBtn.setForeground(new java.awt.Color(36, 39, 246));
        homeBtn.setText("HOME PAGE");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 130, 50));

        addBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        addBtn.setForeground(new java.awt.Color(0, 22, 255));
        addBtn.setText("ADD ITEM");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 430, 150, 50));

        editBtn.setBackground(new java.awt.Color(37, 185, 35));
        editBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        editBtn.setForeground(java.awt.Color.white);
        editBtn.setText("EDIT ITEM");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        jPanel1.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 430, 130, 50));

        exitBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(238, 13, 24));
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 500, 130, 50));

        clearBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(128, 13, 238));
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 130, 50));

        deleteBtn.setBackground(new java.awt.Color(228, 11, 11));
        deleteBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        deleteBtn.setForeground(java.awt.Color.white);
        deleteBtn.setText("DELETE ITEM");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 500, 150, 50));

        lblOrderNo.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblOrderNo.setForeground(java.awt.Color.white);
        lblOrderNo.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblOrderNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 230, 40));

        jLabel7.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("ITEM NAME:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        btnSearch.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        btnSearch.setText("SEARCH WITH DEPARTMENT NAME");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 40, 280, 40));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 190, 40));

        lblConfirmDate.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblConfirmDate.setForeground(java.awt.Color.white);
        lblConfirmDate.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblConfirmDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 230, 40));

        jLabel8.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel8.setForeground(java.awt.Color.white);
        jLabel8.setText("DATE CONFIRMED:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 0, 80, 80));

        lblUsername.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblUsername.setForeground(java.awt.Color.white);
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("USERNAME");
        jPanel1.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 30, 180, -1));

        lblUserType.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblUserType.setForeground(java.awt.Color.white);
        lblUserType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserType.setText("USER TYPE");
        jPanel1.add(lblUserType, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, 220, -1));

        jLabel9.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel9.setForeground(java.awt.Color.white);
        jLabel9.setText("CONFIRM ITEM:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 180, -1));

        jLabel10.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel10.setForeground(java.awt.Color.white);
        jLabel10.setText("QUANTITY:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        combReceive.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        combReceive.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CONFIRM ITEM", "RECEIVED" }));
        jPanel1.add(combReceive, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 340, 230, 40));

        lblItemName.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblItemName.setForeground(java.awt.Color.white);
        lblItemName.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblItemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 230, 40));

        lblDescription.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblDescription.setForeground(java.awt.Color.white);
        lblDescription.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 230, 40));
        jPanel1.add(txtQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 230, 40));

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE CONFIRMED", "ORDER NO", "ITEM NAME", "DESCRIPTION", "DEPARTMENT", "QTY", "CONFIRMATION"
            }
        ));
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 350, 740, 200));

        searchTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchTxtKeyPressed(evt);
            }
        });
        jPanel1.add(searchTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, 250, 40));

        searchBtn.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        searchBtn.setText("SEARCH WITH DEPARTMENT NAME");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        jPanel1.add(searchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 300, 290, 40));

        lblDepartment.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblDepartment.setForeground(java.awt.Color.white);
        lblDepartment.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblDepartment, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 230, 40));

        jLabel4.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("DEPARTMENT:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 1210, 590));

        jLabel1.setFont(new java.awt.Font("Tibetan Machine Uni", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(41, 144, 85));
        jLabel1.setText("RECEIVE ORDERED ITEMS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, -1, 50));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        this.setVisible(false);
        new Main(lblUsername.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        String orderNo=lblOrderNo.getText();       
        try {
            pst=con.prepareStatement("delete from confirmOrderedItems where orderNo=?");
            pst.setString(1,orderNo);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Order confirmation deleted Successfully from database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            lblOrderNo.setText("");
            lblItemName.setText("");
            lblDescription.setText("");
            txtQty.setText("");
            combReceive.getModel().setSelectedItem("CONFIRM ITEM");
            txtSearch.requestFocus();
            current_date();
            Order_table();
            confirm_ordered_items_table();
        } catch (SQLException ex) {
            Logger.getLogger(ReceiveStoreItems.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to exit Hotel System?","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String comfirmDate=lblConfirmDate.getText();
        String orderNo=lblOrderNo.getText();
        String itemName=lblItemName.getText();
        String description=lblDescription.getText();
        String department=lblDepartment.getText();
        int quantity=Integer.parseInt(txtQty.getText());
        String comfirmation=combReceive.getModel().getSelectedItem().toString();
        
        if(itemName.trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please select item to confirm from table 1, null items cannot be confirmed","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtSearch.requestFocus();
        }else if(description.trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please select item to confirm from table 1, null items cannot be confirmed","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtSearch.requestFocus();
        }else if(txtQty.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter quantity received from store, null quantities cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtQty.requestFocus();
        }else if(comfirmation.trim().equals("CONFIRM ITEM")){
            JOptionPane.showMessageDialog(this,"Please confirm if item was received or not, null confirmations cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            combReceive.requestFocus();
        }else{
        try {
            pst=con.prepareStatement("insert into confirmOrderedItems (dateConfirmed,orderNo,itemName,description,department,quantity,confirmation)values(?,?,?,?,?,?,?)");
            pst.setString(1,comfirmDate);
            pst.setString(2,orderNo);
            pst.setString(3,itemName);
            pst.setString(4,description);
            pst.setString(5,department);
            pst.setInt(6,quantity);
            pst.setString(7,comfirmation);
            pst.executeUpdate();
            
            //deletes ordered item once it has been received
            pst=con.prepareStatement("delete from departmentalOrders WHERE orderNo=?");
            pst.setString(1,orderNo);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Order Confirmation Added Successfully into database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            lblOrderNo.setText("");
            lblItemName.setText("");
            lblDescription.setText("");
            txtQty.setText("");
            combReceive.getModel().setSelectedItem("CONFIRM ITEM");
            txtSearch.requestFocus();
            current_date();
            Order_table();
            confirm_ordered_items_table();
        } catch (SQLException ex) {
            Logger.getLogger(ReceiveStoreItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        lblOrderNo.setText("");
        lblItemName.setText("");
        lblDescription.setText("");
        txtQty.setText("");
        combReceive.getModel().setSelectedItem("CONFIRM ITEM");
        txtSearch.requestFocus();
        current_date();
        Order_table();
        confirm_ordered_items_table();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        String comfirmDate=lblConfirmDate.getText();
        String orderNo=lblOrderNo.getText();
        String itemName=lblItemName.getText();
        String description=lblDescription.getText();
        String department=lblDepartment.getText();
        int quantity=Integer.parseInt(txtQty.getText());
        String comfirmation=combReceive.getModel().getSelectedItem().toString();
        
        if(itemName.trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please select item to confirm from table 1, null items cannot be confirmed","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtSearch.requestFocus();
        }else if(description.trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please select item to confirm from table 1, null items cannot be confirmed","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtSearch.requestFocus();
        }else if(txtQty.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter quantity received from store, null quantities cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtQty.requestFocus();
        }else if(comfirmation.trim().equals("CONFIRM ITEM")){
            JOptionPane.showMessageDialog(this,"Please confirm if item was received or not, null confirmations cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            combReceive.requestFocus();
        }else{
        try {
            pst=con.prepareStatement("update confirmOrderedItems set itemName=?, description=?, department=?, quantity=?, department=? where orderNo=?");
            pst.setString(1,itemName);
            pst.setString(2,description);
            pst.setString(3,department);
            pst.setInt(4,quantity);
            pst.setString(5,comfirmation);
            pst.setString(6,orderNo);
            pst.execute();
            JOptionPane.showMessageDialog(this,"Order confirmation Updated Successfully on database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            lblOrderNo.setText("");
            lblItemName.setText("");
            lblDescription.setText("");
            txtQty.setText("");
            combReceive.getModel().setSelectedItem("CONFIRM ITEM");
            txtSearch.requestFocus();
            current_date();
            Order_table();
            confirm_ordered_items_table();
        } catch (SQLException ex) {
            Logger.getLogger(ReceiveStoreItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_editBtnActionPerformed
    
    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        DefaultTableModel model=(DefaultTableModel)table1.getModel();
        int selectedIndex=table1.getSelectedRow();
        lblOrderNo.setText(model.getValueAt(selectedIndex, 1).toString());
        lblItemName.setText(model.getValueAt(selectedIndex, 2).toString());
        lblDescription.setText(model.getValueAt(selectedIndex, 3).toString());
        lblDepartment.setText(model.getValueAt(selectedIndex, 5).toString());
    }//GEN-LAST:event_table1MouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
    }//GEN-LAST:event_formWindowActivated

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String department=txtSearch.getText();
        try {
            pst=con.prepareStatement("select * from departmentalOrders where department like CONCAT('%',?,'%')");
            pst.setString(1,department);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();

            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("dateOrdered"));
                    v.add(rs.getString("orderNo"));
                    v.add(rs.getString("itemName"));
                    v.add(rs.getString("description"));
                    v.add(rs.getInt("quantity"));
                    v.add(rs.getString("department"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HotelMeals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String department=txtSearch.getText();
        try {
            pst=con.prepareStatement("select * from departmentalOrders where department like CONCAT('%',?,'%')");
            pst.setString(1,department);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();

            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("dateOrdered"));
                    v.add(rs.getString("orderNo"));
                    v.add(rs.getString("itemName"));
                    v.add(rs.getString("description"));
                    v.add(rs.getInt("quantity"));
                    v.add(rs.getString("department"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HotelMeals.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String department=searchTxt.getText();
        try {
            pst=con.prepareStatement("select * from confirmOrderedItems WHERE department like CONCAT('%',?,'%')");
            pst.setString(1,department);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("dateConfirmed"));
                    v.add(rs.getString("orderNo"));
                    v.add(rs.getString("itemName"));
                    v.add(rs.getString("description"));
                    v.add(rs.getString("department"));
                    v.add(rs.getInt("quantity"));
                    v.add(rs.getString("confirmation"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReceiveStoreItems.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    private void searchTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTxtKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String department=searchTxt.getText();
            try {
                pst=con.prepareStatement("select * from confirmOrderedItems WHERE department like CONCAT('%',?,'%')");
                pst.setString(1,department);
                rs=pst.executeQuery();
                ResultSetMetaData rsm=rs.getMetaData();
                int c=rsm.getColumnCount();

                DefaultTableModel model=(DefaultTableModel)table2.getModel();
                model.setRowCount(0);
                while(rs.next()){
                    Vector v=new Vector();
                    for(int i=1;i<c;i++){
                        v.add(rs.getString("dateConfirmed"));
                        v.add(rs.getString("orderNo"));
                        v.add(rs.getString("itemName"));
                        v.add(rs.getString("description"));
                        v.add(rs.getString("department"));
                        v.add(rs.getInt("quantity"));
                        v.add(rs.getString("confirmation"));
                    }
                    model.addRow(v);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ReceiveStoreItems.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_searchTxtKeyPressed

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        DefaultTableModel model=(DefaultTableModel)table2.getModel();
        int selectedIndex=table2.getSelectedRow();
        lblConfirmDate.setText(model.getValueAt(selectedIndex, 0).toString());
        lblOrderNo.setText(model.getValueAt(selectedIndex, 1).toString());
        lblItemName.setText(model.getValueAt(selectedIndex, 2).toString());
        lblDescription.setText(model.getValueAt(selectedIndex, 3).toString());
        lblDepartment.setText(model.getValueAt(selectedIndex, 4).toString());
        txtQty.setText(model.getValueAt(selectedIndex, 5).toString());
        combReceive.getModel().setSelectedItem(model.getValueAt(selectedIndex, 6).toString());
        
        editBtn.setEnabled(true);
        deleteBtn.setEnabled(true);
    }//GEN-LAST:event_table2MouseClicked

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
            java.util.logging.Logger.getLogger(ReceiveStoreItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReceiveStoreItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReceiveStoreItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReceiveStoreItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ReceiveStoreItems().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton clearBtn;
    private javax.swing.JComboBox<String> combReceive;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblConfirmDate;
    private javax.swing.JLabel lblDepartment;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblItemName;
    private javax.swing.JLabel lblOrderNo;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblUserType;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JToggleButton searchBtn;
    private javax.swing.JTextField searchTxt;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
