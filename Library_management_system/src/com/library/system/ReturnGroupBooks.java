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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class ReturnGroupBooks extends javax.swing.JFrame {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    /**
     * Creates new form Books
     */
    public ReturnGroupBooks() {
        initComponents();
    }
    
    String username;
    BufferedImage imag;
    public ReturnGroupBooks(String username, BufferedImage imag) {
        initComponents();
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        jLabel12.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("dollar-gill-9SF_lNr5Cz8-unsplash.jpg")).getImage().getScaledInstance(1270, 740, Image.SCALE_SMOOTH)));
        
        Connect(); 
        issued_books_table();
        current_date();
        returned_books_table();
    }

    public void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/Library","root","");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReturnGroupBooks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void current_date(){
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now=LocalDateTime.now();
        String date=dtf.format(now);
        lblDate.setText(date);
    }
    
    public void issued_books_table(){
        try {
            pst=con.prepareStatement("select * from issuedGroups");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table3.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i <= c; i++) {             
                    v.add(rs.getString("issueDate"));
                    v.add(rs.getString("bookNumber"));
                    v.add(rs.getString("bookName"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("studNames"));
                    v.add(rs.getString("admNos"));
                    v.add(rs.getString("returnDate"));
                    v.add(rs.getString("issueNo"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(IssueGroupBooks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void returned_books_table(){
        try {
            pst=con.prepareStatement("select * from returnGroupBooks");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i <= c; i++) {      
                    v.add(rs.getString("dateReturned"));
                    v.add(rs.getString("bookNo"));
                    v.add(rs.getString("bookName"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("studName"));
                    v.add(rs.getString("admNo"));
                    v.add(rs.getString("confirmation"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(IssueBooks.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        clearBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        homeBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        lblBookNo = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblProfile = new javax.swing.JLabel();
        txtSearch3 = new javax.swing.JTextField();
        btnsearch3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table3 = new javax.swing.JTable();
        lblBookName = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblStudentNames = new javax.swing.JLabel();
        lblPrice = new javax.swing.JLabel();
        lblAdmNos = new javax.swing.JLabel();
        combReturn = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RETURNING OF BOOKS");
        setMaximizedBounds(new java.awt.Rectangle(1270, 742, 742, 742));
        setMaximumSize(new java.awt.Dimension(1270, 742));
        setMinimumSize(new java.awt.Dimension(1270, 742));
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

        jPanel1.setBackground(new java.awt.Color(87, 21, 149));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("BOOK NAME:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel3.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("PRICE:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jLabel4.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("STUD NAMES:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        jLabel5.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("ADM NOS:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        clearBtn.setBackground(new java.awt.Color(126, 42, 207));
        clearBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        clearBtn.setForeground(java.awt.Color.white);
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 120, 50));

        addBtn.setBackground(new java.awt.Color(26, 29, 246));
        addBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        addBtn.setForeground(java.awt.Color.white);
        addBtn.setText("CONFIRM");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 460, 120, 50));

        editBtn.setBackground(new java.awt.Color(12, 149, 3));
        editBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        editBtn.setForeground(java.awt.Color.white);
        editBtn.setText("EDIT");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        jPanel1.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 460, 140, 50));

        homeBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        homeBtn.setText("HOME");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 120, 50));

        deleteBtn.setBackground(new java.awt.Color(190, 32, 32));
        deleteBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        deleteBtn.setForeground(java.awt.Color.white);
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 530, 120, 50));

        exitBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(217, 38, 38));
        exitBtn.setText("EXIT SYSTEM");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 530, 140, 50));

        lblBookNo.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblBookNo.setForeground(java.awt.Color.white);
        lblBookNo.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblBookNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 230, 40));

        jLabel8.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel8.setForeground(java.awt.Color.white);
        jLabel8.setText("CURRENT DATE:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel9.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel9.setForeground(java.awt.Color.white);
        jLabel9.setText("RETURNED:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, -1));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 0, 60, 60));

        txtSearch3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearch3KeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 260, 40));

        btnsearch3.setFont(new java.awt.Font("SansSerif", 3, 13)); // NOI18N
        btnsearch3.setText("SEARCH");
        btnsearch3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearch3ActionPerformed(evt);
            }
        });
        jPanel1.add(btnsearch3, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 20, 100, 40));

        table3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISSUE DATE", "BOOK NO", "BOOK NAME", "PRICE", "STUD NAMES", "ADM NOS", "RETURN DATE", "ISSUE NO"
            }
        ));
        table3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table3MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table3);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, 810, 230));

        lblBookName.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblBookName.setForeground(java.awt.Color.white);
        lblBookName.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblBookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 230, 40));

        lblDate.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblDate.setForeground(java.awt.Color.white);
        lblDate.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 230, 40));

        jLabel10.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel10.setForeground(java.awt.Color.white);
        jLabel10.setText("BOOK NO:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 110, -1));

        lblStudentNames.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblStudentNames.setForeground(java.awt.Color.white);
        lblStudentNames.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblStudentNames, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 230, 40));

        lblPrice.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblPrice.setForeground(java.awt.Color.white);
        lblPrice.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 230, 40));

        lblAdmNos.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblAdmNos.setForeground(java.awt.Color.white);
        lblAdmNos.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblAdmNos, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 230, 40));

        combReturn.setFont(new java.awt.Font("SansSerif", 3, 12)); // NOI18N
        combReturn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CONFIRM BOOK RETURN", "RETURNED", "LOST" }));
        jPanel1.add(combReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, 230, 40));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE RETURNED", "BOOK NO", "BOOK NAME", "PRICE", "STUD NAME", "ADMN NO", "PARENT CONT", "CONFIRMATION"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 360, 810, 220));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 312, 240, 40));

        btnSearch.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 310, 100, 40));

        jLabel7.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("USE BOOK NUMBER TO SEARCH");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 320, -1, -1));

        jLabel11.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel11.setForeground(java.awt.Color.white);
        jLabel11.setText("USE BOOK NUMBER TO SEARCH");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 30, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 1250, 650));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(247, 196, 11));
        jLabel1.setText("RETURNING OF BOOKS AS GROUPS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, -1, -1));

        lblUsername.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(123, 27, 220));
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("USERNAME");
        getContentPane().add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 20, 170, 30));
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, -4, 1270, 740));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Do you want to exit System?","Library Management System",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        lblBookNo.setText("");
        lblBookName.setText("");
        lblPrice.setText("");
        lblStudentNames.setText("");
        lblAdmNos.setText("");
        combReturn.getModel().setSelectedItem("CONFIRM BOOK RETURN");
        current_date();
        editBtn.setEnabled(false);
        addBtn.setEnabled(true);
        deleteBtn.setEnabled(false);
    }//GEN-LAST:event_clearBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String dateReturned=lblDate.getText();
        String bookNo=lblBookNo.getText();
        String bookName=lblBookName.getText();
        int price=Integer.parseInt(lblPrice.getText());
        String studentName=lblStudentNames.getText();
        String admNo=lblAdmNos.getText();
        String confirm=combReturn.getSelectedItem().toString();
        
        if(bookNo.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please select a book from the top right table, null books cannot be returned","Submission Error",JOptionPane.CANCEL_OPTION);
            txtSearch3.requestFocus();
        }else if(combReturn.getSelectedItem().toString().trim().equals("CONFIRM BOOK RETURN")){
            JOptionPane.showMessageDialog(this, "Please confirm if the book is returned or lost, null confirmations cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            combReturn.requestFocus();
        }else{
            try {
                pst=con.prepareStatement("insert into returnGroupBooks (dateReturned,bookNo,bookName,price,studName,admNo,confirmation) values(?,?,?,?,?,?,?)");
                pst.setString(1, dateReturned);
                pst.setString(2, bookNo);
                pst.setString(3, bookName);
                pst.setInt(4, price);
                pst.setString(5, studentName);
                pst.setString(6, admNo);
                pst.setString(7, confirm);
                pst.executeUpdate();
                if(combReturn.getSelectedItem().toString().trim().equals("LOST")){
                    pst=con.prepareStatement("delete from books where bookNo=?");           
                    pst.setString(1, bookNo);
                    pst.executeUpdate();
                }
                pst=con.prepareStatement("delete from issuedGroups where bookNumber=?");           
                pst.setString(1, bookNo);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this,"Book return confirmed successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
                lblBookNo.setText("");
                lblBookName.setText("");
                lblPrice.setText("");
                lblStudentNames.setText("");
                lblAdmNos.setText("");
                combReturn.getModel().setSelectedItem("CONFIRM BOOK RETURN");
                current_date();
                returned_books_table();
                issued_books_table();
                    } catch (SQLException ex) {
                Logger.getLogger(ReturnGroupBooks.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        String dateReturned=lblDate.getText();
        String bookNo=lblBookNo.getText();
        String bookName=lblBookName.getText();
        int price=Integer.parseInt(lblPrice.getText());
        String studentName=lblStudentNames.getText();
        String admNo=lblAdmNos.getText();
        String confirm=combReturn.getSelectedItem().toString();
        
        if(bookNo.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please select a book from the top right table, null books cannot be returned","Submission Error",JOptionPane.CANCEL_OPTION);
            txtSearch3.requestFocus();
        }else if(combReturn.getSelectedItem().toString().trim().equals("CONFIRM BOOK RETURN")){
            JOptionPane.showMessageDialog(this, "Please confirm if the book is returned or lost, null confirmations cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            combReturn.requestFocus();
        }else{
            try {
                pst=con.prepareStatement("update returnGroupBooks set dateReturned=?, bookName=?, price=?, studName=?, admNo=?, confirmation=? where bookNo=?");           
                pst.setString(1, dateReturned); 
                pst.setString(2, bookName);
                pst.setInt(3, price);
                pst.setString(4, studentName);
                pst.setString(5, admNo);
                pst.setString(6, confirm);
                pst.setString(7, bookNo);
                pst.execute();
                
                JOptionPane.showMessageDialog(this,"Book return updated Successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
                lblBookNo.setText("");
                lblBookName.setText("");
                lblPrice.setText("");
                lblStudentNames.setText("");
                lblAdmNos.setText("");
                combReturn.getModel().setSelectedItem("CONFIRM BOOK RETURN");
                current_date();
                returned_books_table();
                addBtn.setEnabled(true);
                    } catch (SQLException ex) {
                Logger.getLogger(ReturnGroupBooks.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        this.setVisible(false);
        new Home(lblUsername.getText(),imag).setVisible(true);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        String bookNo=lblBookNo.getText();
            try {
                pst=con.prepareStatement("delete from returnGroupBooks where bookNo=?");           
                pst.setString(1, bookNo);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this,"Book return deleted Successfully from database","Success Message",JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(this,"Ensure you insert the book again in the books form if it was rendered lost and in *issued books as groups* form if it was a mistake you made","WARNING!! Message",JOptionPane.CANCEL_OPTION);
                lblBookNo.setText("");
                lblBookName.setText("");
                lblPrice.setText("");
                lblStudentNames.setText("");
                lblAdmNos.setText("");
                combReturn.getModel().setSelectedItem("CONFIRM BOOK RETURN");
                current_date();
                returned_books_table();
                addBtn.setEnabled(true);
                    } catch (SQLException ex) {
                Logger.getLogger(ReturnGroupBooks.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);                              
    }//GEN-LAST:event_formWindowActivated

    private void txtSearch3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch3KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
             String bookNumber=txtSearch3.getText();
        
        try {
            pst=con.prepareStatement("select * from issuedGroups where bookNumber like CONCAT('%',?,'%')");
            pst.setString(1, bookNumber);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table3.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i <= c; i++) {             
                    v.add(rs.getString("issueDate"));
                    v.add(rs.getString("bookNumber"));
                    v.add(rs.getString("bookName"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("studNames"));
                    v.add(rs.getString("admNos"));
                    v.add(rs.getString("returnDate"));
                    v.add(rs.getString("issueNo"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(IssueGroupBooks.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_txtSearch3KeyPressed

    private void btnsearch3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearch3ActionPerformed
        String bookNumber=txtSearch3.getText();
        
        try {
            pst=con.prepareStatement("select * from issuedGroups where bookNumber like CONCAT('%',?,'%')");
            pst.setString(1, bookNumber);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table3.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i <= c; i++) {             
                    v.add(rs.getString("issueDate"));
                    v.add(rs.getString("bookNumber"));
                    v.add(rs.getString("bookName"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("studNames"));
                    v.add(rs.getString("admNos"));
                    v.add(rs.getString("returnDate"));
                    v.add(rs.getString("issueNo"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(IssueGroupBooks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnsearch3ActionPerformed

    private void table3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table3MouseClicked
        DefaultTableModel model=(DefaultTableModel)table3.getModel();
        int selectedIndex=table3.getSelectedRow();

        lblBookNo.setText(model.getValueAt(selectedIndex, 1).toString());
        lblBookName.setText(model.getValueAt(selectedIndex, 2).toString());
        lblPrice.setText(model.getValueAt(selectedIndex, 3).toString());
        lblStudentNames.setText(model.getValueAt(selectedIndex, 4).toString());
        lblAdmNos.setText(model.getValueAt(selectedIndex, 5).toString());
    }//GEN-LAST:event_table3MouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String bookNo=txtSearch.getText();
        
        try {
            pst=con.prepareStatement("select * from returnGroupBooks where bookNo like CONCAT('%',?,'%')");
            pst.setString(1, bookNo);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i <= c; i++) {      
                    v.add(rs.getString("dateReturned"));
                    v.add(rs.getString("bookNo"));
                    v.add(rs.getString("bookName"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("studName"));
                    v.add(rs.getString("admNo"));
                    v.add(rs.getString("confirmation"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(IssueBooks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String bookNo=txtSearch.getText();
        
        try {
            pst=con.prepareStatement("select * from returnGroupBooks where bookNo like CONCAT('%',?,'%')");
            pst.setString(1, bookNo);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i <= c; i++) {      
                    v.add(rs.getString("dateReturned"));
                    v.add(rs.getString("bookNo"));
                    v.add(rs.getString("bookName"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("studName"));
                    v.add(rs.getString("admNo"));
                    v.add(rs.getString("confirmation"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(IssueBooks.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        DefaultTableModel model=(DefaultTableModel)table.getModel();
        int selectedIndex=table.getSelectedRow();
        
        lblDate.setText(model.getValueAt(selectedIndex, 0).toString());
        lblBookNo.setText(model.getValueAt(selectedIndex, 1).toString());
        lblBookName.setText(model.getValueAt(selectedIndex, 2).toString());
        lblPrice.setText(model.getValueAt(selectedIndex, 3).toString());
        lblStudentNames.setText(model.getValueAt(selectedIndex, 4).toString());
        lblAdmNos.setText(model.getValueAt(selectedIndex, 5).toString());
        combReturn.getModel().setSelectedItem(model.getValueAt(selectedIndex, 6).toString());
        
        editBtn.setEnabled(true);
        deleteBtn.setEnabled(true);
        addBtn.setEnabled(false);
    }//GEN-LAST:event_tableMouseClicked

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
            java.util.logging.Logger.getLogger(ReturnGroupBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReturnGroupBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReturnGroupBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReturnGroupBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReturnGroupBooks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnsearch3;
    private javax.swing.JButton clearBtn;
    private javax.swing.JComboBox<String> combReturn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAdmNos;
    private javax.swing.JLabel lblBookName;
    private javax.swing.JLabel lblBookNo;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblStudentNames;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTable table;
    private javax.swing.JTable table3;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearch3;
    // End of variables declaration//GEN-END:variables
}
