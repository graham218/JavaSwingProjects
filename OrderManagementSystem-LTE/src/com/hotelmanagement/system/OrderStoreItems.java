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
public class OrderStoreItems extends javax.swing.JFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    /**
     * Creates new form ManageClients
     */
    public OrderStoreItems() {
        initComponents();
    }
    
    String username;
    BufferedImage imag;
    String userType;
    public OrderStoreItems(String username,BufferedImage imag, String userType) {
        initComponents();
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("emmanuel-chigbo-jr7R01lOn1Y-unsplash.jpg")).getImage().getScaledInstance(1240, 720, Image.SCALE_SMOOTH)));
        this.userType=userType;
        lblUserType.setText(userType);
        Connect();
        current_date();
        Auto_id();
        Order_table();
        load_departments();
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
            rs=s.executeQuery("select MAX(orderNo) from departmentalOrders");
            rs.next();
            rs.getString("MAX(orderNo)");
            if(rs.getString("MAX(orderNo)")==null){
                lblOrderNo.setText("OR00001");
            }else{
                long id=Long.parseLong(rs.getString("MAX(orderNo)").substring(2,rs.getString("MAX(orderNo)").length()));
                id++;
                lblOrderNo.setText("OR"+String.format("%05d", id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderStoreItems.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Order_table(){
        try {
            pst=con.prepareStatement("select * from departmentalOrders");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table.getModel();
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
            Logger.getLogger(OrderStoreItems.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void current_date(){
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now=LocalDateTime.now();
        String date=dtf.format(now);
        lblOrderDate.setText(date);
    }
    
    public void load_departments(){
        try {
            pst=con.prepareStatement("select * from departments");
            rs=pst.executeQuery();
            //DefaultComboBoxModel model=(DefaultComboBoxModel)combDepartment.getModel();
            while(rs.next()){
                combDepartment.addItem(rs.getString("deptName"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(IssueItems.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel2 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        homeBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        lblOrderNo = new javax.swing.JLabel();
        txtItemName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        lblOrderDate = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblProfile = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblUserType = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        combDepartment = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ORDER ITEMS FROM STORE");
        setMaximizedBounds(new java.awt.Rectangle(1236, 718, 718, 718));
        setMinimumSize(new java.awt.Dimension(1236, 718));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(95, 49, 111));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("ORDER NO:");
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

        txtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQtyKeyPressed(evt);
            }
        });
        jPanel1.add(txtQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 260, 40));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE ORDERD", "ORDER NO", "ITEM NAME", "DESCRIPTION", "QUANTITY", "DEPARTMENT"
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

        lblOrderNo.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblOrderNo.setForeground(java.awt.Color.white);
        lblOrderNo.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblOrderNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 260, 40));

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
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 250, 40));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 190, 40));

        lblOrderDate.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblOrderDate.setForeground(java.awt.Color.white);
        lblOrderDate.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblOrderDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 260, 40));

        jLabel8.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel8.setForeground(java.awt.Color.white);
        jLabel8.setText("DATE ORDERD:");
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
        jLabel9.setText("DEPARTMENT:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 160, -1));

        jLabel10.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel10.setForeground(java.awt.Color.white);
        jLabel10.setText("QUANTITY:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        combDepartment.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        combDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT DEPARTMENT" }));
        jPanel1.add(combDepartment, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 260, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 1210, 590));

        jLabel1.setFont(new java.awt.Font("Tibetan Machine Uni", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(213, 17, 70));
        jLabel1.setText("ORDER ITEMS FROM STORE");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, -1, 50));
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
            pst=con.prepareStatement("delete from departmentalOrders where orderNo=?");
            pst.setString(1,orderNo);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Departmental Order deleted Successfully from database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            txtItemName.setText("");
            txtDescription.setText("");
            txtQty.setText("");
            combDepartment.getModel().setSelectedItem("SELECT DEPARTMENT");
            txtItemName.requestFocus();
            current_date();
            Auto_id();
            Order_table();
            load_departments();
        } catch (SQLException ex) {
            Logger.getLogger(OrderStoreItems.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to exit Hotel System?","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String dateOrdered=lblOrderDate.getText();
        String orderNo=lblOrderNo.getText();
        String itemName=txtItemName.getText();
        String description=txtDescription.getText();
        int quantity=Integer.parseInt(txtQty.getText());
        String department=combDepartment.getModel().getSelectedItem().toString();
        
        if(itemName.trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter item name, null item names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtItemName.requestFocus();
        }else if(description.trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter item description, null item descriptions cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtDescription.requestFocus();
        }else if(txtQty.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter quantity, null quantities cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtQty.requestFocus();
        }else if(department.trim().equals("SELECT DEPARTMENT")){
            JOptionPane.showMessageDialog(this,"Please select your department, null departments cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            combDepartment.requestFocus();
        }else{
        try {
            pst=con.prepareStatement("insert into departmentalOrders (dateOrdered,orderNo,itemName,description,quantity,department)values(?,?,?,?,?,?)");
            pst.setString(1,dateOrdered);
            pst.setString(2,orderNo);
            pst.setString(3,itemName);
            pst.setString(4,description);
            pst.setInt(5,quantity);
            pst.setString(6,department);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Departmental Order Added Successfully into database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            txtItemName.setText("");
            txtDescription.setText("");
            txtQty.setText("");
            combDepartment.getModel().setSelectedItem("SELECT DEPARTMENT");
            txtItemName.requestFocus();
            current_date();
            Auto_id();
            Order_table();
            load_departments();
        } catch (SQLException ex) {
            Logger.getLogger(OrderStoreItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        txtItemName.setText("");
        txtDescription.setText("");
        txtQty.setText("");
        combDepartment.getModel().setSelectedItem("SELECT DEPARTMENT");
        txtItemName.requestFocus();
        current_date();
        Auto_id();
        Order_table();
        load_departments();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        String dateOrdered=lblOrderDate.getText();
        String orderNo=lblOrderNo.getText();
        String itemName=txtItemName.getText();
        String description=txtDescription.getText();
        int quantity=Integer.parseInt(txtQty.getText());
        String department=combDepartment.getModel().getSelectedItem().toString();
        
        if(itemName.trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter item name, null item names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtItemName.requestFocus();
        }else if(description.trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter item description, null item descriptions cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtDescription.requestFocus();
        }else if(txtQty.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter quantity, null quantities cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtQty.requestFocus();
        }else if(department.trim().equals("SELECT DEPARTMENT")){
            JOptionPane.showMessageDialog(this,"Please select your department, null departments cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            combDepartment.requestFocus();
        }else{
        try {
            pst=con.prepareStatement("update departmentalOrders set itemName=?, description=?, quantity=?, department=? where orderNo=?");
            pst.setString(1,itemName);
            pst.setString(2,description);
            pst.setInt(3,quantity);
            pst.setString(4,department);
            pst.setString(5,orderNo);
            pst.execute();
            JOptionPane.showMessageDialog(this,"Departmental order Updated Successfully on database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            txtItemName.setText("");
            txtDescription.setText("");
            txtQty.setText("");
            combDepartment.getModel().setSelectedItem("SELECT DEPARTMENT");
            txtItemName.requestFocus();
            current_date();
            Auto_id();
            Order_table();
            load_departments();
        } catch (SQLException ex) {
            Logger.getLogger(OrderStoreItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_editBtnActionPerformed
    
    private void txtDescriptionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescriptionKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtQty.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtQty.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtItemName.requestFocus();
        }
    }//GEN-LAST:event_txtDescriptionKeyPressed

    private void txtQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            combDepartment.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            combDepartment.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtQty.requestFocus();
        }
    }//GEN-LAST:event_txtQtyKeyPressed

    private void txtItemNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtItemNameKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtDescription.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtDescription.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            combDepartment.requestFocus();
        }
    }//GEN-LAST:event_txtItemNameKeyPressed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        DefaultTableModel model=(DefaultTableModel)table.getModel();
        int selectedIndex=table.getSelectedRow();
        lblOrderNo.setText(model.getValueAt(selectedIndex, 1).toString());
        txtItemName.setText(model.getValueAt(selectedIndex, 2).toString());
        txtDescription.setText(model.getValueAt(selectedIndex, 3).toString());
        txtQty.setText(model.getValueAt(selectedIndex, 4).toString());
        combDepartment.getModel().setSelectedItem(model.getValueAt(selectedIndex, 5).toString());
        
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
            pst=con.prepareStatement("select * from departmentalOrders where itemName like CONCAT('%',?,'%')");
            pst.setString(1,mealName);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();

            DefaultTableModel model=(DefaultTableModel)table.getModel();
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
            String itemName=txtSearch.getText();
        try {
            pst=con.prepareStatement("select * from departmentalOrders where itemName like CONCAT('%',?,'%')");
            pst.setString(1,itemName);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();

            DefaultTableModel model=(DefaultTableModel)table.getModel();
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
            java.util.logging.Logger.getLogger(OrderStoreItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderStoreItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderStoreItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderStoreItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderStoreItems().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton clearBtn;
    private javax.swing.JComboBox<String> combDepartment;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblOrderDate;
    private javax.swing.JLabel lblOrderNo;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblUserType;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
