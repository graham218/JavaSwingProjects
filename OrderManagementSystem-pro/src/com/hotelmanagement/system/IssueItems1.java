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
public class IssueItems1 extends javax.swing.JPanel {

    PreparedStatement pst;
    ResultSet rs;
    Database base=new Database();
    Connection con=base.myCon();
    /**
     * Creates new form IssueItems1
     */
    public IssueItems1() {
        initComponents();
    }
    
    String username;
    BufferedImage imag;
    String userType;
    public IssueItems1(String username,BufferedImage imag, String UserType) {
        initComponents();
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        //lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("social-3064515_1920.jpg")).getImage().getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), Image.SCALE_SMOOTH)));
        this.userType=userType;
        lblUserType.setText(userType);
        lblIssuedBy.setText(lblUsername.getText());
        current_date();
        Store_items_Table();
        Issued_out_Table();
        issue_no();
        load_departments();
        button_pre_enabled();
    }

    public void button_pre_enabled(){
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
    }
    
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
                    v.add(rs.getString("dateAdded"));
                    v.add(rs.getString("itemNumber"));
                    v.add(rs.getString("itemName"));
                    v.add(rs.getString("description"));
                    v.add(rs.getString("price"));
                    v.add(rs.getInt("quantity"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IssueItems1.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(IssueItems1.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(IssueItems1.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(IssueItems1.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        addBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        lblItemNo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        lblDateIssued = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblIssuedBy = new javax.swing.JLabel();
        lblProfile = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
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
        editBtn = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1140, 600));
        setMinimumSize(new java.awt.Dimension(1140, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(201, 59, 142));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("ITEM NUMBER:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("DEPARTMENT:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 760, 160));

        addBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        addBtn.setForeground(new java.awt.Color(0, 22, 255));
        addBtn.setText("ISSUE");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 430, 110, 50));

        exitBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(238, 13, 24));
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 90, 50));

        clearBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(128, 13, 238));
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 90, 50));

        lblItemNo.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblItemNo.setForeground(java.awt.Color.white);
        lblItemNo.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblItemNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 190, 40));

        jLabel7.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("ITEM NAME:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        btnSearch.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        btnSearch.setText("SEARCH WITH ITEM NAME");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 50, 280, 40));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 180, 40));

        lblDateIssued.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblDateIssued.setForeground(java.awt.Color.white);
        lblDateIssued.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblDateIssued, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 190, 40));

        jLabel8.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel8.setForeground(java.awt.Color.white);
        jLabel8.setText("DATE ISSUED:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 170, -1));

        lblIssuedBy.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblIssuedBy.setForeground(java.awt.Color.white);
        lblIssuedBy.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblIssuedBy, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 190, 40));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 0, 80, 80));

        lblUsername.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblUsername.setForeground(java.awt.Color.white);
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("USERNAME");
        jPanel1.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 40, 180, -1));

        lblUserType.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblUserType.setForeground(java.awt.Color.white);
        lblUserType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserType.setText("USER TYPE");
        jPanel1.add(lblUserType, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, 220, -1));

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE ISSUED", "ITEM NO", "ITEM NAME", "ISSUE NO", "ISSUED BY", "ISSUED TO", "DEPARTMENT", "QUANTITY"
            }
        ));
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 310, 760, 210));

        searchText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchTextKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTextKeyReleased(evt);
            }
        });
        jPanel1.add(searchText, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 270, 270, -1));

        searchBtn.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        searchBtn.setText("SEARCH WITH ISSUE DATE");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        jPanel1.add(searchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 270, 250, -1));

        jLabel9.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel9.setForeground(java.awt.Color.white);
        jLabel9.setText("ISSUED TO:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 140, -1));

        txtIssuedTo.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        txtIssuedTo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIssuedToKeyPressed(evt);
            }
        });
        jPanel1.add(txtIssuedTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 190, 40));

        jLabel10.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel10.setForeground(java.awt.Color.white);
        jLabel10.setText("ISSUED BY:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 140, -1));

        lblItemName.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblItemName.setForeground(java.awt.Color.white);
        lblItemName.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblItemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 190, 40));

        txtQty.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        txtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQtyKeyPressed(evt);
            }
        });
        jPanel1.add(txtQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, 190, 40));

        jLabel11.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel11.setForeground(java.awt.Color.white);
        jLabel11.setText("QUANTITY:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, -1));

        combDepartment.setFont(new java.awt.Font("SansSerif", 3, 13)); // NOI18N
        combDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT DEPARTMENT" }));
        jPanel1.add(combDepartment, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 190, 40));

        lblIssueNo.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblIssueNo.setForeground(java.awt.Color.white);
        lblIssueNo.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblIssueNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 190, 40));

        jLabel12.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel12.setForeground(java.awt.Color.white);
        jLabel12.setText("ISSUE NO:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 130, -1));

        deleteBtn.setBackground(new java.awt.Color(241, 70, 22));
        deleteBtn.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        deleteBtn.setForeground(java.awt.Color.white);
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 490, 110, 50));

        editBtn.setBackground(new java.awt.Color(16, 37, 246));
        editBtn.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        editBtn.setForeground(java.awt.Color.white);
        editBtn.setText("EDIT");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        jPanel1.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 430, 100, 50));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1120, 550));

        jLabel1.setFont(new java.awt.Font("Tibetan Machine Uni", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(45, 13, 249));
        jLabel1.setText("ISSUE OUT ITEMS IN STORE");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 740, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        DefaultTableModel model=(DefaultTableModel)table1.getModel();
        int selectedIndex=table1.getSelectedRow();
        lblItemNo.setText(model.getValueAt(selectedIndex, 1).toString());
        lblItemName.setText(model.getValueAt(selectedIndex, 2).toString());
        txtIssuedTo.requestFocus();
    }//GEN-LAST:event_table1MouseClicked

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
                //updating items in store after issuing out
                pst=con.prepareStatement("update stockInStore set quantity=quantity-? where itemNumber=?");
                pst.setInt(1,quantity);
                pst.setString(2,itemNumber);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this,"Item Issued Out Successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
                current_date();
                lblItemNo.setText("");
                lblItemName.setText("");
                txtIssuedTo.setText("");
                txtQty.setText("");
                combDepartment.getModel().setSelectedItem("SELECT DEPARTMENT");
                txtIssuedTo.requestFocus();
                Store_items_Table();
                Issued_out_Table();
                issue_no();
                lblIssuedBy.setText(lblUsername.getText());
                addBtn.setEnabled(true);
                deleteBtn.setEnabled(false);
                editBtn.setEnabled(false);
            } catch (SQLException ex) {
                Logger.getLogger(IssueItems1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to exit Hotel System?","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        lblItemNo.setText("");
        lblItemName.setText("");
        txtIssuedTo.setText("");
        txtQty.setText("");
        combDepartment.getModel().setSelectedItem("SELECT DEPARTMENT");
        txtIssuedTo.requestFocus();
        Store_items_Table();
        Issued_out_Table();
        issue_no();
        lblIssuedBy.setText(username);
        addBtn.setEnabled(true);
        deleteBtn.setEnabled(false);
        editBtn.setEnabled(false);
    }//GEN-LAST:event_clearBtnActionPerformed

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
                    v.add(rs.getInt("quantity"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IssueItems1.class.getName()).log(Level.SEVERE, null, ex);
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
                        v.add(rs.getInt("quantity"));
                    }
                    model.addRow(v);
                }
            } catch (SQLException ex) {
                Logger.getLogger(IssueItems1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        addBtn.setEnabled(false);
        deleteBtn.setEnabled(true);
        editBtn.setEnabled(true);

        DefaultTableModel model=(DefaultTableModel)table2.getModel();
        int selectedIndex=table2.getSelectedRow();
        lblDateIssued.setText(model.getValueAt(selectedIndex, 0).toString());
        lblItemNo.setText(model.getValueAt(selectedIndex, 1).toString());
        lblItemName.setText(model.getValueAt(selectedIndex, 2).toString());
        lblIssueNo.setText(model.getValueAt(selectedIndex, 3).toString());
        lblIssuedBy.setText(model.getValueAt(selectedIndex, 4).toString());
        txtIssuedTo.setText(model.getValueAt(selectedIndex, 5).toString());
        combDepartment.getModel().setSelectedItem(model.getValueAt(selectedIndex, 6).toString());
        txtQty.setText(model.getValueAt(selectedIndex, 7).toString());
    }//GEN-LAST:event_table2MouseClicked

    private void searchTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String issueDate=searchText.getText();
            try {
                pst=con.prepareStatement("select * from storeItemsIssuedOut WHERE dateIssued LIKE CONCAT('%',?,'%')");
                pst.setString(1, issueDate);
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
                Logger.getLogger(IssueItems1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_searchTextKeyPressed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String issueDate=searchText.getText();
        try {
            pst=con.prepareStatement("select * from storeItemsIssuedOut WHERE dateIssued LIKE CONCAT('%',?,'%')");
            pst.setString(1, issueDate);
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
            Logger.getLogger(IssueItems1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchBtnActionPerformed

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
                    //updating items in store after issuing out
                    pst=con.prepareStatement("update stockInStore set quantity=quantity-? where itemNumber=?");
                    pst.setInt(1,quantity);
                    pst.setString(2,itemNumber);
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
                    lblIssuedBy.setText(lblUsername.getText());
                    addBtn.setEnabled(true);
                    deleteBtn.setEnabled(false);
                    editBtn.setEnabled(false);
                } catch (SQLException ex) {
                    Logger.getLogger(IssueItems1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtIssuedTo.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            combDepartment.requestFocus();
        }
    }//GEN-LAST:event_txtQtyKeyPressed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        String issueNo=lblIssueNo.getText();
        String department=combDepartment.getModel().getSelectedItem().toString();

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
                pst=con.prepareStatement("delete from storeItemsIssuedOut where issueNo=?");
                pst.setString(1,issueNo);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this,"Ensure you update the quantity of "+lblItemName.getText()+"s in store if by any chance you issued it out by mistake","ALERT Message",JOptionPane.OK_OPTION);
                JOptionPane.showMessageDialog(this,"Issued item deleted Successfully from database","Success Message",JOptionPane.INFORMATION_MESSAGE);
                current_date();
                lblItemNo.setText("");
                lblItemName.setText("");
                txtIssuedTo.setText("");
                txtQty.setText("");
                combDepartment.getModel().setSelectedItem("SELECT DEPARTMENT");
                txtIssuedTo.requestFocus();
                Store_items_Table();
                Issued_out_Table();
                issue_no();
                lblIssuedBy.setText(lblUsername.getText());
                addBtn.setEnabled(true);
                deleteBtn.setEnabled(false);
                editBtn.setEnabled(false);
            } catch (SQLException ex) {
                Logger.getLogger(IssueItems1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        String issueNo=lblIssueNo.getText();
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
                pst=con.prepareStatement("update storeItemsIssuedOut set issuedTo=?, department=?, quantity=? where issueNo=?");
                pst.setString(1,issuedTo);
                pst.setString(2,department);
                pst.setInt(3,quantity);
                pst.setString(4,issueNo);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this,"Ensure you update the quantity of "+lblItemName.getText()+"s in store if by any chance you edited the quantity issued out","ALERT Message",JOptionPane.OK_OPTION);
                JOptionPane.showMessageDialog(this,"Issued item updated Successfully on database","Success Message",JOptionPane.INFORMATION_MESSAGE);
                current_date();
                lblItemNo.setText("");
                lblItemName.setText("");
                txtIssuedTo.setText("");
                txtQty.setText("");
                combDepartment.getModel().setSelectedItem("SELECT DEPARTMENT");
                txtIssuedTo.requestFocus();
                Store_items_Table();
                Issued_out_Table();
                issue_no();
                lblIssuedBy.setText(lblUsername.getText());
                addBtn.setEnabled(true);
                deleteBtn.setEnabled(false);
                editBtn.setEnabled(false);
            } catch (SQLException ex) {
                Logger.getLogger(IssueItems1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
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
                    v.add(rs.getInt("quantity"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IssueItems1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void searchTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextKeyReleased
        String issueDate=searchText.getText();
        try {
            pst=con.prepareStatement("select * from storeItemsIssuedOut WHERE dateIssued LIKE CONCAT('%',?,'%')");
            pst.setString(1, issueDate);
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
            Logger.getLogger(IssueItems1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchTextKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton clearBtn;
    private javax.swing.JComboBox<String> combDepartment;
    private javax.swing.JToggleButton deleteBtn;
    private javax.swing.JToggleButton editBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDateIssued;
    private javax.swing.JLabel lblIssueNo;
    private javax.swing.JLabel lblIssuedBy;
    private javax.swing.JLabel lblItemName;
    private javax.swing.JLabel lblItemNo;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblUserType;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JToggleButton searchBtn;
    private javax.swing.JTextField searchText;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JTextField txtIssuedTo;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
