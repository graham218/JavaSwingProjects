/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelmanagement.system;

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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bill
 */
public class IssueItems extends javax.swing.JFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    /**
     * Creates new form ManageClients
     */
    public IssueItems() {
        initComponents();
    }
    
    String username;
    BufferedImage imag;
    String userType;
    public IssueItems(String username,BufferedImage imag, String userType) {
        initComponents();
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("emmanuel-chigbo-jr7R01lOn1Y-unsplash.jpg")).getImage().getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), Image.SCALE_SMOOTH)));
        this.userType=userType;
        lblUserType.setText(userType);
        lblIssuedBy.setText(username);
        Connect();
        current_date();
        Store_items_Table();
        Issued_out_Table();
        issue_no();
        load_departments();
    }
    
    
    public void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/orderMgmt","root","");
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


    public void Store_items_Table(){
        try {
            pst=con.prepareStatement("select * from stockInStore");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("itemNumber"));
                    v.add(rs.getString("itemName"));
                    v.add(rs.getString("description"));
                    v.add(rs.getString("price"));
                    v.add(rs.getString("quantity"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IssueItems.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Issued_out_Table(){
        try {
            pst=con.prepareStatement("select * from storeItemsIssuedOut");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("dateIssued"));
                    v.add(rs.getString("itemNumber"));
                    v.add(rs.getString("itemName"));
                    v.add(rs.getString("issueNo"));
                    v.add(rs.getString("issuedBy"));
                    v.add(rs.getString("issuedTo"));
                    v.add(rs.getString("department"));
                    v.add(rs.getInt("quantity"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IssueItems.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public void issue_no(){
        try {
            Statement s=con.createStatement();
            rs=s.executeQuery("select MAX(issueNo) from storeItemsIssuedOut");
            rs.next();
            rs.getString("MAX(issueNo)");
            if(rs.getString("MAX(issueNo)")==null){
                lblIssueNo.setText("IS00001");
            }else{
                long id=Long.parseLong(rs.getString("MAX(issueNo)").substring(2,rs.getString("MAX(issueNo)").length()));
                id++;
                lblIssueNo.setText("IS"+String.format("%05d", id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StockInStore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void current_date(){
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now=LocalDateTime.now();
        String date=dtf.format(now);
        lblDateIssued.setText(date);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsername = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        homeBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        lblItemNo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lblDateIssued = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblIssuedBy = new javax.swing.JLabel();
        lblProfile = new javax.swing.JLabel();
        lblUsername1 = new javax.swing.JLabel();
        lblUserType = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        searchText = new javax.swing.JTextField();
        searchBtn = new javax.swing.JToggleButton();
        jLabel9 = new javax.swing.JLabel();
        txtIssuedTo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        lblItemName = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        combDepartment = new javax.swing.JComboBox<>();
        lblIssueNo = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        deleteBtn = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
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

        lblUsername.setFont(new java.awt.Font("Ubuntu", 3, 24)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(38, 16, 241));
        lblUsername.setText("USERNAME");
        getContentPane().add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 20, 170, -1));

        jPanel1.setBackground(new java.awt.Color(201, 59, 142));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("ITEM NUMBER:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("DEPARTMENT:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE ADDED", "ITEM NO", "ITEM NAME", "DESCRIPTION", "ITEM PRICE", "QUANTITY"
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
        homeBtn.setText("HOME");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 90, 50));

        addBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        addBtn.setForeground(new java.awt.Color(0, 22, 255));
        addBtn.setText("ISSUE OUT ITEM");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 460, 160, 50));

        exitBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(238, 13, 24));
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 530, 160, 50));

        clearBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(128, 13, 238));
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 90, 50));

        lblItemNo.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblItemNo.setForeground(java.awt.Color.white);
        lblItemNo.setText("ITEM NUMBER");
        lblItemNo.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblItemNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 250, 40));

        jLabel7.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("ITEM NAME:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        btnSearch.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 130, 40));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 250, 40));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("NOTE: USE ITEM NAMES TO SEARCH FOR ITEMS IN STORE");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 500, 30));

        lblDateIssued.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblDateIssued.setForeground(java.awt.Color.white);
        lblDateIssued.setText("DATE ISSUED");
        lblDateIssued.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblDateIssued, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 250, 40));

        jLabel8.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel8.setForeground(java.awt.Color.white);
        jLabel8.setText("DATE ISSUED:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 170, -1));

        lblIssuedBy.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblIssuedBy.setForeground(java.awt.Color.white);
        lblIssuedBy.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblIssuedBy, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 250, 40));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 0, 80, 80));

        lblUsername1.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblUsername1.setForeground(java.awt.Color.white);
        lblUsername1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername1.setText("USERNAME");
        jPanel1.add(lblUsername1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 40, 180, -1));

        lblUserType.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblUserType.setForeground(java.awt.Color.white);
        lblUserType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserType.setText("USER TYPE");
        jPanel1.add(lblUserType, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 220, -1));

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE ISSUED", "ITEM NO", "ITEM NAME", "ISSUED BY", "ISSUED TO", "DEPARTMENT", "QUANTITY"
            }
        ));
        jScrollPane2.setViewportView(table2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 340, 740, 230));
        jPanel1.add(searchText, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, 270, -1));

        searchBtn.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        searchBtn.setText("SEARCH WITH ITEM NAME");
        jPanel1.add(searchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 300, 250, -1));

        jLabel9.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel9.setForeground(java.awt.Color.white);
        jLabel9.setText("ISSUED TO:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 140, -1));

        txtIssuedTo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIssuedToKeyPressed(evt);
            }
        });
        jPanel1.add(txtIssuedTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 250, 40));

        jLabel10.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel10.setForeground(java.awt.Color.white);
        jLabel10.setText("ISSUED BY:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 140, -1));

        lblItemName.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblItemName.setForeground(java.awt.Color.white);
        lblItemName.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblItemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 250, 40));

        txtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQtyKeyPressed(evt);
            }
        });
        jPanel1.add(txtQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 410, 250, 40));

        jLabel11.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel11.setForeground(java.awt.Color.white);
        jLabel11.setText("QUANTITY:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));

        combDepartment.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        combDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT DEPARTMENT" }));
        jPanel1.add(combDepartment, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, 250, 40));

        lblIssueNo.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblIssueNo.setForeground(java.awt.Color.white);
        lblIssueNo.setText("ISSUE NUMBER");
        lblIssueNo.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblIssueNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 250, 40));

        jLabel12.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel12.setForeground(java.awt.Color.white);
        jLabel12.setText("ISSUE NO:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 130, -1));

        deleteBtn.setBackground(new java.awt.Color(241, 70, 22));
        deleteBtn.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        deleteBtn.setForeground(java.awt.Color.white);
        deleteBtn.setText("DELETE");
        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 530, 140, 50));

        jToggleButton2.setBackground(new java.awt.Color(16, 37, 246));
        jToggleButton2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jToggleButton2.setForeground(java.awt.Color.white);
        jToggleButton2.setText("EDIT");
        jPanel1.add(jToggleButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 460, 140, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 1210, 590));

        jLabel1.setFont(new java.awt.Font("Tibetan Machine Uni", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(45, 13, 249));
        jLabel1.setText("ISSUE OUT ITEMS IN STORE");
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

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String dateIssued=lblDateIssued.getText();
        String itemNumber=lblItemNo.getText();
        String itemName=lblItemName.getText();
        String issueNo=lblIssueNo.getText();
        String issuedBy=lblIssuedBy.getText();
        String issuedTo=txtIssuedTo.getText();
        String department=combDepartment.getModel().getSelectedItem().toString();
        int quantity=Integer.parseInt(txtQty.getText());
        
        if(txtQty.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter quantity, null quantities cannot be Issued out","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtQty.requestFocus();
        }else if(txtIssuedTo.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter name of the person being issued store items, null names cannot be Issued out","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtIssuedTo.requestFocus();
        }else if(department.trim().equals("SELECT DEPARTMENT")){
            JOptionPane.showMessageDialog(this,"Please select department, null departments cannot be Issued out","Submission Error!!",JOptionPane.CANCEL_OPTION);
            combDepartment.requestFocus();
        }else{
        try {
            pst=con.prepareStatement("insert into storeItemsIssuedOut(dateIssued,itemNumber,itemName,issueNo,issuedBy,issuedTo,department,quantity)values(?,?,?,?,?,?,?,?)");
            pst.setString(1,dateIssued);
            pst.setString(2,itemNumber);
            pst.setString(3,itemName);
            pst.setString(4,issueNo);
            pst.setString(5,issuedBy);
            pst.setString(6,issuedTo);
            pst.setString(7,department);
            pst.setInt(8,quantity);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Item ISSUED OUT Successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
            current_date();
            lblItemNo.setText("");
            lblItemName.setText("");
            txtIssuedTo.setText("");
            txtQty.setText("");
            txtIssuedTo.requestFocus();
            Store_items_Table();
            Issued_out_Table();
            issue_no();
        } catch (SQLException ex) {
            Logger.getLogger(IssueItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        lblItemNo.setText("");
        lblItemName.setText("");
        txtIssuedTo.setText("");
        txtQty.setText("");
        txtIssuedTo.requestFocus();
        Store_items_Table();
        Issued_out_Table();
        issue_no();
    }//GEN-LAST:event_clearBtnActionPerformed
    
    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        DefaultTableModel model=(DefaultTableModel)table1.getModel();
        int selectedIndex=table1.getSelectedRow();
        lblItemNo.setText(model.getValueAt(selectedIndex, 1).toString());
        lblIssuedBy.setText(model.getValueAt(selectedIndex, 2).toString());
        txtIssuedTo.requestFocus();
    }//GEN-LAST:event_table1MouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String mealName=txtSearch.getText();
        try {
            pst=con.prepareStatement("select * from stockInStore where itemName like CONCAT('%',?,'%')");
            pst.setString(1,mealName);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();

            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("dateAdded"));
                    v.add(rs.getString("itemNumber"));
                    v.add(rs.getString("itemName"));
                    v.add(rs.getString("description"));
                    v.add(rs.getString("price"));
                    v.add(rs.getString("quantity"));
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

            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("dateAdded"));
                    v.add(rs.getString("itemNumber"));
                    v.add(rs.getString("itemName"));
                    v.add(rs.getString("description"));
                    v.add(rs.getString("price"));
                    v.add(rs.getString("quantity"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HotelMeals.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void txtIssuedToKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIssuedToKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            combDepartment.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            combDepartment.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtQty.requestFocus();
        }
    }//GEN-LAST:event_txtIssuedToKeyPressed

    private void txtQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtIssuedTo.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            combDepartment.requestFocus();
        }
    }//GEN-LAST:event_txtQtyKeyPressed

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
            java.util.logging.Logger.getLogger(IssueItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new IssueItems().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton clearBtn;
    private javax.swing.JComboBox<String> combDepartment;
    private javax.swing.JToggleButton deleteBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblDateIssued;
    private javax.swing.JLabel lblIssueNo;
    private javax.swing.JLabel lblIssuedBy;
    private javax.swing.JLabel lblItemName;
    private javax.swing.JLabel lblItemNo;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblUserType;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblUsername1;
    private javax.swing.JToggleButton searchBtn;
    private javax.swing.JTextField searchText;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JTextField txtIssuedTo;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
