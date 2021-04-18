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
import java.text.SimpleDateFormat;
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
public class IssueBooks extends javax.swing.JFrame {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    /**
     * Creates new form Books
     */
    public IssueBooks() {
        initComponents();
    }
    
    String username;
    BufferedImage imag;
    public IssueBooks(String username, BufferedImage imag) {
        initComponents();
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(60, 70, Image.SCALE_SMOOTH)));
        Connect(); 
        book_table();
        student_table();
        issue_books_table();
        Auto_Id();
        current_date();
        jLabel14.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("aleix-ventayol-yPoM-wmzKMM-unsplash.jpg")).getImage().getScaledInstance(1240, 720, Image.SCALE_SMOOTH)));
    }

    public void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/Library","root","");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(IssueBooks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Auto_Id(){
        try {
            Statement s=con.createStatement();
            rs=pst.executeQuery("select MAX(issueNo) from issuedBooks");
            rs.next();
            rs.getString("MAX(issueNo)");
            if(rs.getString("MAX(issueNo)")==null){
                lblIssueNo.setText("IS001");
            }else{
                long id=Long.parseLong(rs.getString("MAX(issueNo)").substring(2,rs.getString("MAX(issueNo)").length()));
                id++;
                lblIssueNo.setText("IS"+String.format("%03d", id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(IssueBooks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void book_table(){
        try {
            pst=con.prepareStatement("select * from books");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i <= c; i++) {
                    v.add(rs.getString("bookNo"));
                    v.add(rs.getString("bookName"));
                    v.add(rs.getString("description"));
                    v.add(rs.getString("author"));
                    v.add(rs.getString("publisher"));
                    v.add(rs.getInt("price"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(IssueBooks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void student_table(){
        try {
            pst=con.prepareStatement("select * from issuedBooks");
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
                    v.add(rs.getString("studName"));
                    v.add(rs.getString("admNo"));
                    v.add(rs.getString("parentPhone"));
                    v.add(rs.getString("returnDate"));
                    v.add(rs.getString("issueNo"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(IssueBooks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void issue_books_table(){
        try {
            pst=con.prepareStatement("select * from students");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i <= c; i++) {                   
                    v.add(rs.getString("studName"));
                    v.add(rs.getString("admNo"));
                    v.add(rs.getString("class"));
                    v.add(rs.getString("parentPhone"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(IssueBooks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void current_date(){
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now=LocalDateTime.now();
        String date=dtf.format(now);
        lblissueDate.setText(date);
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
        txtSearch = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
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
        lblissueDate = new javax.swing.JLabel();
        lblBookName = new javax.swing.JLabel();
        lblAdmNo = new javax.swing.JLabel();
        lblStudentName = new javax.swing.JLabel();
        lblIssueNo = new javax.swing.JLabel();
        returnDate = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        table3 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        lblParentContact = new javax.swing.JLabel();
        txtSearch1 = new javax.swing.JTextField();
        searchBtn1 = new javax.swing.JButton();
        btnsearch3 = new javax.swing.JButton();
        txtSearch3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        price = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ISSUE BOOKS");
        setMaximizedBounds(new java.awt.Rectangle(1248, 738, 738, 738));
        setMaximumSize(new java.awt.Dimension(1248, 738));
        setMinimumSize(new java.awt.Dimension(1248, 738));
        setPreferredSize(new java.awt.Dimension(1248, 738));
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

        jPanel1.setBackground(new java.awt.Color(18, 140, 148));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BOOK NO", "NAME", "DESCRIPTION", "AUTHOR", "PUBLISHER", "PRICE"
            }
        ));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, 680, 170));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 250, 40));

        searchBtn.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        searchBtn.setText("SEARCH");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        jPanel1.add(searchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 110, 40));

        jLabel2.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("BOOK NO:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel3.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("BOOK NAME:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel4.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("STUD NAME:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        jLabel5.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("ADM NO:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));

        jLabel6.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("DATE TO RETURN:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, -1, -1));

        clearBtn.setBackground(new java.awt.Color(126, 42, 207));
        clearBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        clearBtn.setForeground(java.awt.Color.white);
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 130, 50));

        addBtn.setBackground(new java.awt.Color(26, 29, 246));
        addBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        addBtn.setForeground(java.awt.Color.white);
        addBtn.setText("ADD BOOK");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 530, 140, 50));

        editBtn.setBackground(new java.awt.Color(12, 149, 3));
        editBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        editBtn.setForeground(java.awt.Color.white);
        editBtn.setText("EDIT BOOK");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        jPanel1.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 530, 140, 50));

        homeBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        homeBtn.setText("HOME");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, 130, 50));

        deleteBtn.setBackground(new java.awt.Color(190, 32, 32));
        deleteBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        deleteBtn.setForeground(java.awt.Color.white);
        deleteBtn.setText("DELETE BOOK");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 600, 140, 50));

        exitBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(217, 38, 38));
        exitBtn.setText("EXIT SYSTEM");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 600, 140, 50));

        lblBookNo.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblBookNo.setForeground(java.awt.Color.white);
        lblBookNo.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblBookNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 220, 40));

        jLabel8.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel8.setForeground(java.awt.Color.white);
        jLabel8.setText("DATE OF ISSUE:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel9.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel9.setForeground(java.awt.Color.white);
        jLabel9.setText("ISSUE NO:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, -1, -1));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 0, 60, 70));

        lblissueDate.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblissueDate.setForeground(java.awt.Color.white);
        lblissueDate.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblissueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 220, 40));

        lblBookName.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblBookName.setForeground(java.awt.Color.white);
        lblBookName.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblBookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 220, 40));

        lblAdmNo.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblAdmNo.setForeground(java.awt.Color.white);
        lblAdmNo.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblAdmNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 220, 40));

        lblStudentName.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblStudentName.setForeground(java.awt.Color.white);
        lblStudentName.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblStudentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 220, 40));

        lblIssueNo.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblIssueNo.setForeground(java.awt.Color.white);
        lblIssueNo.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblIssueNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 430, 220, 40));
        jPanel1.add(returnDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, 220, 40));

        table3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISSUE DATE", "BOOK NO", "BOOK NAME", "PRICE", "STUD NAME", "ADM NO", "PARENT'S CONTACT", "RETURN DATE", "ISSUE NO"
            }
        ));
        table3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table3MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table3);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 490, 730, 150));

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STUD NAME", "ADM NO", "CLASS/YR/FORM", "PARENT'S CONTACT"
            }
        ));
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(table2);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, 730, 140));

        jLabel7.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("PARENT'S CONTACT:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));

        lblParentContact.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblParentContact.setForeground(java.awt.Color.white);
        lblParentContact.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblParentContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 320, 220, 40));

        txtSearch1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearch1KeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 240, 250, 40));

        searchBtn1.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        searchBtn1.setText("SEARCH");
        searchBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtn1ActionPerformed(evt);
            }
        });
        jPanel1.add(searchBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 240, 110, 40));

        btnsearch3.setFont(new java.awt.Font("SansSerif", 3, 13)); // NOI18N
        btnsearch3.setText("SEARCH");
        btnsearch3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearch3ActionPerformed(evt);
            }
        });
        jPanel1.add(btnsearch3, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 440, 110, 40));

        txtSearch3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearch3KeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 440, 260, 40));

        jLabel10.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel10.setForeground(java.awt.Color.white);
        jLabel10.setText("BOOK PRICE:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        price.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        price.setForeground(java.awt.Color.white);
        price.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 220, 40));

        jLabel11.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel11.setForeground(java.awt.Color.white);
        jLabel11.setText("USE ADMISSION NUMBER TO SEARCH");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 250, -1, -1));

        jLabel12.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel12.setForeground(java.awt.Color.white);
        jLabel12.setText("USE BOOK NUMBER TO SEARCH");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, -1, -1));

        jLabel13.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel13.setForeground(java.awt.Color.white);
        jLabel13.setText("USE BOOK NUMBER TO SEARCH");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 450, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 1210, 660));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(247, 11, 23));
        jLabel1.setText("ISSUE BOOKS TO STUDENTS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, -1, 50));

        lblUsername.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(123, 27, 220));
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("USERNAME");
        getContentPane().add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 20, 170, 30));
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 6, 1240, 720));

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
        lblStudentName.setText("");
        lblAdmNo.setText("");
        lblParentContact.setText("");
        returnDate.setDate(null);
        lblIssueNo.setText("");
        price.setText("");
        lblBookNo.requestFocus();
        current_date();
        Auto_Id();
        editBtn.setEnabled(false);
        addBtn.setEnabled(true);
        deleteBtn.setEnabled(false);
    }//GEN-LAST:event_clearBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        
        String issueDate=lblissueDate.getText();
        String bookNo=lblBookNo.getText();
        String bookName=lblBookName.getText();
        int bookPrice=Integer.parseInt(price.getText());
        String studentName=lblStudentName.getText();
        String admNo=lblAdmNo.getText();
        String parentPhone=lblParentContact.getText();
        String returndate=format.format(returnDate.getDate());
        String issueNo=lblIssueNo.getText();
        
        if(bookNo.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter book number, null book numbers cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            lblBookNo.requestFocus();
        }else if(bookName.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter book name, null book names cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            lblBookName.requestFocus();
        }else if(studentName.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please select students's name from table, null student names cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            lblStudentName.requestFocus();
        }else if(admNo.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please select admission number, null admission numbers cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            lblAdmNo.requestFocus();
        }else if(parentPhone.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please select parents's contact, null contacts cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            lblParentContact.requestFocus();
        }else if(returndate.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter the date student will return books, null dates  cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            returnDate.requestFocus();
        }else{
            try {
                pst=con.prepareStatement("insert into issuedBooks (issueDate,bookNumber,bookName, price,studName,admNo,parentPhone,returnDate,issueNo) values(?,?,?,?,?,?,?,?,?)");
                pst.setString(1, issueDate);
                pst.setString(2, bookNo);
                pst.setString(3, bookName);
                pst.setInt(4, bookPrice);
                pst.setString(5, studentName);
                pst.setString(6, admNo);
                pst.setString(7, parentPhone);
                pst.setString(8, returndate);
                pst.setString(9, issueNo);
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(this,"Student issued book Successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
                Auto_Id();
                current_date();
                student_table();
                lblBookNo.setText("");
                lblBookName.setText("");
                lblStudentName.setText("");
                lblAdmNo.setText("");
                price.setText("");
                lblParentContact.setText("");
                returnDate.setDate(null);
                lblBookNo.requestFocus();
                issue_books_table();
                    } catch (SQLException ex) {
                Logger.getLogger(IssueBooks.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        
        String issueDate=lblissueDate.getText();
        String bookNo=lblBookNo.getText();
        String bookName=lblBookName.getText();
        String studentName=lblStudentName.getText();
        int bookPrice=Integer.parseInt(price.getText());
        String admNo=lblAdmNo.getText();
        String parentPhone=lblParentContact.getText();
        String returndate=format.format(returnDate.getDate());
        String issueNo=lblIssueNo.getText();
        
        if(bookNo.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter book number, null book numbers cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            lblBookNo.requestFocus();
        }else if(bookName.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter book name, null book names cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            lblBookName.requestFocus();
        }else if(studentName.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please select students's name from table, null student names cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            lblStudentName.requestFocus();
        }else if(admNo.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please select admission number, null admission numbers cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            lblAdmNo.requestFocus();
        }else if(parentPhone.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please select parents's contact, null contacts cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            lblParentContact.requestFocus();
        }else if(returndate.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter the date student will return books, null dates  cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            returnDate.requestFocus();
        }else{
            try {
                pst=con.prepareStatement("update issuedBooks set issueDate=?, bookNumber=?, bookName=?, price=? ,studName=?, admNo=?, parentPhone=?, returnDate=? where issueNo=?");           
                pst.setString(1, issueDate);
                pst.setString(2, bookNo);
                pst.setString(3, bookName);
                pst.setInt(4, bookPrice);
                pst.setString(5, studentName);
                pst.setString(6, admNo);
                pst.setString(7, parentPhone);
                pst.setString(8, returndate);
                pst.setString(9, issueNo);
                pst.execute();
                
                JOptionPane.showMessageDialog(this,"Issued book updated Successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
                Auto_Id();
                current_date();
                student_table();
                lblBookNo.setText("");
                lblBookName.setText("");
                lblStudentName.setText("");
                lblAdmNo.setText("");
                price.setText("");
                lblParentContact.setText("");
                returnDate.setDate(null);
                lblBookNo.requestFocus();
                issue_books_table();
                addBtn.setEnabled(true);
                    } catch (SQLException ex) {
                Logger.getLogger(IssueBooks.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        this.setVisible(false);
        new Home(lblUsername.getText(),imag).setVisible(true);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        String issueNo=lblIssueNo.getText();
            try {
                pst=con.prepareStatement("delete from issuedBooks where issueNo=?");           
                pst.setString(1, issueNo);
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(this,"Issued book deleted Successfully from database","Success Message",JOptionPane.INFORMATION_MESSAGE);
                Auto_Id();
                current_date();
                student_table();
                lblBookNo.setText("");
                lblBookName.setText("");
                lblStudentName.setText("");
                lblAdmNo.setText("");
                price.setText("");
                lblParentContact.setText("");
                returnDate.setDate(null);
                lblBookNo.requestFocus();
                issue_books_table();
                addBtn.setEnabled(true);
                    } catch (SQLException ex) {
                Logger.getLogger(IssueBooks.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String bookNo=txtSearch.getText();
        try {
            pst=con.prepareStatement("select * from books where bookNo like CONCAT('%',?,'%')");
            pst.setString(1,bookNo);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i <= c; i++) {
                    v.add(rs.getString("bookNo"));
                    v.add(rs.getString("bookName"));
                    v.add(rs.getString("description"));
                    v.add(rs.getString("author"));
                    v.add(rs.getString("publisher"));
                    v.add(rs.getInt("price"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Books.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        String bookNo=txtSearch.getText();
        try {
            pst=con.prepareStatement("select * from books where bookNo like CONCAT('%',?,'%')");
            pst.setString(1,bookNo);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i <= c; i++) {
                    v.add(rs.getString("bookNo"));
                    v.add(rs.getString("bookName"));
                    v.add(rs.getString("description"));
                    v.add(rs.getString("author"));
                    v.add(rs.getString("publisher"));
                    v.add(rs.getInt("price"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Books.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        DefaultTableModel model=(DefaultTableModel)table1.getModel();
        int selectedIndex=table1.getSelectedRow();
        
        lblBookNo.setText(model.getValueAt(selectedIndex, 0).toString());
        lblBookName.setText(model.getValueAt(selectedIndex, 1).toString());
        price.setText(model.getValueAt(selectedIndex, 5).toString());
    }//GEN-LAST:event_table1MouseClicked

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);                              
    }//GEN-LAST:event_formWindowActivated

    private void txtSearch1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String admNo=txtSearch1.getText();
            try {
                pst=con.prepareStatement("select * from students where admNo like CONCAT('%',?,'%')");
                pst.setString(1,admNo);
                rs=pst.executeQuery();
                ResultSetMetaData rsm=rs.getMetaData();
                int c=rsm.getColumnCount();

                DefaultTableModel model=(DefaultTableModel)table2.getModel();
                model.setRowCount(0);
                while(rs.next()){
                    Vector v=new Vector();
                    for (int i = 1; i <= c; i++) {
                        v.add(rs.getString("studName"));
                        v.add(rs.getString("admNo"));
                        v.add(rs.getString("class"));
                        v.add(rs.getString("parentPhone"));
                    }
                    model.addRow(v);
                }

            } catch (SQLException ex) {
                Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtSearch1KeyPressed

    private void searchBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtn1ActionPerformed
        String admNo=txtSearch1.getText();
        try {
            pst=con.prepareStatement("select * from students where admNo like CONCAT('%',?,'%')");
            pst.setString(1,admNo);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();

            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i <= c; i++) {
                    v.add(rs.getString("studName"));
                    v.add(rs.getString("admNo"));
                    v.add(rs.getString("class"));
                    v.add(rs.getString("parentPhone"));
                }
                model.addRow(v);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchBtn1ActionPerformed

    private void btnsearch3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearch3ActionPerformed
        String bookNumber=txtSearch3.getText();
        
        try {
            pst=con.prepareStatement("select * from issuedBooks where bookNumber like CONCAT('%',?,'%')");
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
                    v.add(rs.getString("studName"));
                    v.add(rs.getString("admNo"));
                    v.add(rs.getString("parentPhone"));
                    v.add(rs.getString("returnDate"));
                    v.add(rs.getString("issueNo"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(IssueBooks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnsearch3ActionPerformed

    private void txtSearch3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch3KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String bookNumber=txtSearch3.getText();
        
        try {
            pst=con.prepareStatement("select * from issuedBooks where bookNumber like CONCAT('%',?,'%')");
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
                    v.add(rs.getString("studName"));
                    v.add(rs.getString("admNo"));
                    v.add(rs.getString("parentPhone"));
                    v.add(rs.getString("returnDate"));
                    v.add(rs.getString("issueNo"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(IssueBooks.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_txtSearch3KeyPressed

    private void table3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table3MouseClicked
        DefaultTableModel model=(DefaultTableModel)table3.getModel();
        int selectedIndex=table3.getSelectedRow();
        
        lblissueDate.setText(model.getValueAt(selectedIndex, 0).toString());
        lblBookNo.setText(model.getValueAt(selectedIndex, 1).toString());
        lblBookName.setText(model.getValueAt(selectedIndex, 2).toString());
        price.setText(model.getValueAt(selectedIndex, 3).toString());
        lblStudentName.setText(model.getValueAt(selectedIndex, 4).toString());
        lblAdmNo.setText(model.getValueAt(selectedIndex, 5).toString());
        lblParentContact.setText(model.getValueAt(selectedIndex, 6).toString());
        String date=(model.getValueAt(selectedIndex, 7).toString());
        try{
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d=format.parse(date);
            returnDate.setDate(d);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        lblIssueNo.setText(model.getValueAt(selectedIndex, 8).toString());
        
        editBtn.setEnabled(true);
        deleteBtn.setEnabled(true);
        addBtn.setEnabled(false);
    }//GEN-LAST:event_table3MouseClicked

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        DefaultTableModel model=(DefaultTableModel)table2.getModel();
        int selectedIndex=table2.getSelectedRow();
        
        lblStudentName.setText(model.getValueAt(selectedIndex, 0).toString());
        lblAdmNo.setText(model.getValueAt(selectedIndex, 1).toString());
        lblParentContact.setText(model.getValueAt(selectedIndex, 3).toString());
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
            java.util.logging.Logger.getLogger(IssueBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueBooks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton btnsearch3;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAdmNo;
    private javax.swing.JLabel lblBookName;
    private javax.swing.JLabel lblBookNo;
    private javax.swing.JLabel lblIssueNo;
    private javax.swing.JLabel lblParentContact;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblStudentName;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblissueDate;
    private javax.swing.JLabel price;
    private com.toedter.calendar.JDateChooser returnDate;
    private javax.swing.JButton searchBtn;
    private javax.swing.JButton searchBtn1;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JTable table3;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearch1;
    private javax.swing.JTextField txtSearch3;
    // End of variables declaration//GEN-END:variables
}
