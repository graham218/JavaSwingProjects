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
import java.io.Writer;
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
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bill
 */
public class BookRooms extends javax.swing.JFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    /**
     * Creates new form ManageRooms
     */
    public BookRooms() {
        initComponents();
    }

    String username;
    String nationalId;
    String lastName;
    BufferedImage imag;
    String userType;
    public BookRooms(String username,String nationalId,String lastName, BufferedImage imag, String userType) {
        initComponents();
        this.username=username;
        lblUsername.setText(username);
        this.nationalId=nationalId;
        lblClientId.setText(nationalId);
        this.lastName=lastName;
        lblClientName.setText(lastName);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("social-3064515_1920.jpg")).getImage().getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), Image.SCALE_SMOOTH)));
        this.userType=userType;
        lblUserType.setText(userType);
        Connect();
        Auto_id();
        Rooms_table();
        Client_table();
        date_method();
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
            rs=s.executeQuery("select MAX(bookNo) from clientBookings");
            rs.next();
            rs.getString("MAX(bookNo)");
            if(rs.getString("MAX(bookNo)")==null){
                lblId.setText("BR00001");
            }else{
                long id=Long.parseLong(rs.getString("MAX(bookNo)").substring(2,rs.getString("MAX(bookNo)").length()));
                id++;
                lblId.setText("BR"+String.format("%05d", id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageClients.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Rooms_table(){
        try {
            pst=con.prepareStatement("select * from roomsAvailable");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("roomNumber"));
                    v.add(rs.getString("roomType"));
                    v.add(rs.getString("roomPhone"));
                    v.add(rs.getInt("roomPrice"));
                    v.add(rs.getString("roomStatus"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageClients.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Client_table(){
        try {
            pst=con.prepareStatement("select * from clientBookings");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("bookNo"));
                    v.add(rs.getString("date"));
                    v.add(rs.getString("nId"));
                    v.add(rs.getString("clientName"));
                    v.add(rs.getString("roomNo"));
                    v.add(rs.getString("roomType"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("dateBooking"));
                    v.add(rs.getInt("pay"));
                    v.add(rs.getInt("balance"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageClients.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void date_method(){
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        lblUsername = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        exitBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        homeBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblClientId = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        txtSearch2 = new javax.swing.JTextField();
        txtSearch1 = new javax.swing.JTextField();
        btnSearch1 = new javax.swing.JButton();
        btnSearch2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        lblRoomPrice = new javax.swing.JLabel();
        lblClientName = new javax.swing.JLabel();
        lblRoomNo = new javax.swing.JLabel();
        lblRoomType = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtBalance = new javax.swing.JTextField();
        txtPay = new javax.swing.JTextField();
        lblDate = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblProfile = new javax.swing.JLabel();
        lblUsername1 = new javax.swing.JLabel();
        lblUserType = new javax.swing.JLabel();
        computeBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtBookingDate = new com.toedter.calendar.JDateChooser();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BOOKING OF ROOMS");
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
        getContentPane().add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 10, 170, -1));

        jLabel1.setFont(new java.awt.Font("Tibetan Machine Uni", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(7, 99, 22));
        jLabel1.setText("BOOKING OF ROOMS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, -1, 50));

        jPanel1.setBackground(new java.awt.Color(126, 54, 50));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exitBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(238, 13, 24));
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 580, 150, 50));

        deleteBtn.setBackground(new java.awt.Color(227, 50, 4));
        deleteBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        deleteBtn.setForeground(java.awt.Color.white);
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 580, 130, 50));

        homeBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        homeBtn.setForeground(new java.awt.Color(36, 39, 246));
        homeBtn.setText("BACK");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 580, 130, 50));

        clearBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(128, 13, 238));
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 520, 130, 50));

        addBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        addBtn.setForeground(new java.awt.Color(0, 22, 255));
        addBtn.setText("ADD");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 520, 130, 50));

        editBtn.setBackground(new java.awt.Color(37, 202, 68));
        editBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        editBtn.setForeground(java.awt.Color.white);
        editBtn.setText("EDIT");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        jPanel1.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 520, 150, 50));

        jLabel4.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("BALANCE:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 150, -1));

        jLabel3.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("ROOM TYPE:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("CLIENT N.ID:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 150, -1));

        jLabel5.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("DATE BOOKING:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel8.setForeground(java.awt.Color.white);
        jLabel8.setText("ROOM NO:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        lblClientId.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblClientId.setForeground(java.awt.Color.white);
        lblClientId.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblClientId, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 260, 40));

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BOOK NO", "DATE", "N.ID", "NAME", "ROOM NO", "ROOM TYPE", "PRICE", "DATE BOOKING", "PAY", "BALANCE"
            }
        ));
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 390, 700, 240));

        txtSearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearch2ActionPerformed(evt);
            }
        });
        txtSearch2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearch2KeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 340, 290, 40));

        txtSearch1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearch1KeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, 260, 40));

        btnSearch1.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        btnSearch1.setText("SEARCH WITH ROOM TYPE");
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 60, 330, 40));

        btnSearch2.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        btnSearch2.setText("SEARCH WITH NATIONAL ID");
        btnSearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch2ActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch2, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 340, 300, 40));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ROOM NUMBER", "ROOM TYPE", "ROOM PHONE NUMBER", "PRICE", "ROOM STATUS"
            }
        ));
        table1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        table1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                table1CaretPositionChanged(evt);
            }
        });
        jScrollPane1.setViewportView(table1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, 690, 210));

        jLabel9.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel9.setForeground(java.awt.Color.white);
        jLabel9.setText("CLIENT NAME:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        lblRoomPrice.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblRoomPrice.setForeground(java.awt.Color.white);
        lblRoomPrice.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblRoomPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, 260, 40));

        lblClientName.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblClientName.setForeground(java.awt.Color.white);
        lblClientName.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblClientName, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 260, 40));

        lblRoomNo.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblRoomNo.setForeground(java.awt.Color.white);
        lblRoomNo.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblRoomNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 260, 40));

        lblRoomType.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblRoomType.setForeground(java.awt.Color.white);
        lblRoomType.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblRoomType, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, 260, 40));

        jLabel10.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel10.setForeground(java.awt.Color.white);
        jLabel10.setText("PAY:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 150, -1));

        txtBalance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBalanceKeyPressed(evt);
            }
        });
        jPanel1.add(txtBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 460, 260, 40));

        txtPay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPayKeyPressed(evt);
            }
        });
        jPanel1.add(txtPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 410, 260, 40));

        lblDate.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblDate.setForeground(java.awt.Color.white);
        lblDate.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 260, 40));

        jLabel11.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel11.setForeground(java.awt.Color.white);
        jLabel11.setText("DATE:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 150, -1));

        lblId.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblId.setForeground(java.awt.Color.white);
        lblId.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 260, 40));

        jLabel12.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel12.setForeground(java.awt.Color.white);
        jLabel12.setText("BOOKING NO:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 200, -1));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 0, 80, 80));

        lblUsername1.setFont(new java.awt.Font("SansSerif", 3, 12)); // NOI18N
        lblUsername1.setForeground(java.awt.Color.white);
        lblUsername1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername1.setText("USERNAME");
        jPanel1.add(lblUsername1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 30, 80, -1));

        lblUserType.setFont(new java.awt.Font("SansSerif", 3, 12)); // NOI18N
        lblUserType.setForeground(java.awt.Color.white);
        lblUserType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserType.setText("USER TYPE");
        jPanel1.add(lblUserType, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 10, 120, -1));

        computeBtn.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        computeBtn.setText("COMPUTE");
        computeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(computeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 430, 100, -1));

        jLabel6.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("PRICE");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));
        jPanel1.add(txtBookingDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, 260, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1210, 700));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 810));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to exit Hotel System?","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        String bookNo=lblId.getText();
        try {
            pst=con.prepareStatement("delete from clientBookings where bookNo=?");
            pst.setString(1,bookNo);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Client's Booking Deleted Successfully from database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            txtPay.setText("");
            txtBalance.setText("");
            txtBookingDate.setCalendar(null);
            txtPay.requestFocus();
            editBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
            addBtn.setEnabled(true);
            Client_table();
            Rooms_table();
            Auto_id();
            date_method();
        } catch (SQLException ex) {
            Logger.getLogger(ManageClients.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        this.setVisible(false);
        new ManageClients(lblUsername.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        txtPay.setText("");
        txtBalance.setText("");
        txtBookingDate.setCalendar(null);
        txtPay.requestFocus();
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        addBtn.setEnabled(true);
        Client_table();
        Rooms_table();
        Auto_id();
        date_method();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        
        String bookNo=lblId.getText();
        String date=lblDate.getText();
        String clientId=lblClientId.getText();
        String clientName=lblClientName.getText();
        String roomNo=lblRoomNo.getText();
        String roomType=lblRoomType.getText();
        int roomPrice=Integer.parseInt(lblRoomPrice.getText());
        String bookingDate=format.format(this.txtBookingDate.getDate());
        int pay=Integer.parseInt(txtPay.getText());
        int balance=Integer.parseInt(txtBalance.getText());

        if(txtPay.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter client's pay, a room cannot be allocated without payment","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPay.requestFocus();
        }else if(txtBalance.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please Calculate Balance by pressing ENTER after payment, null balances cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtBalance.requestFocus();
        }else{
            try {
                pst=con.prepareStatement("insert into clientBookings (bookNo,date,nId,clientName,roomNo,roomType,price,dateBooking,pay,balance)values(?,?,?,?,?,?,?,?,?,?)");
                pst.setString(1,bookNo);
                pst.setString(2,date);
                pst.setString(3,clientId);
                pst.setString(4,clientName);
                pst.setString(5,roomNo);
                pst.setString(6,roomType);
                pst.setInt(7,roomPrice);
                pst.setString(8,bookingDate);
                pst.setInt(9,pay);
                pst.setInt(10,balance);
                pst.executeUpdate();
                
                //updating room status
                pst=con.prepareStatement("update roomsAvailable set roomStatus='BOOKED' WHERE roomNumber=?");
                pst.setString(1,roomNo);
                pst.executeUpdate();
                
                pst=con.prepareStatement("insert into clientBook (datee,cashier,clientID,payment,balance) values(?,?,?,?,?)");
                pst.setString(1,date);
                pst.setString(2,lblUsername.getText());
                pst.setString(3,clientId);
                pst.setInt(4,pay);
                pst.setInt(5,balance);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this,clientName+" has Successfully booked room Number "+roomNo+" to be used on "+bookingDate,"Success Message",JOptionPane.INFORMATION_MESSAGE);
                txtPay.setText("");
                txtBalance.setText("");
                txtBookingDate.setCalendar(null);
                txtPay.requestFocus();
                editBtn.setEnabled(false);
                deleteBtn.setEnabled(false);
                addBtn.setEnabled(true);
                Client_table();
                Rooms_table();
                Auto_id();
                date_method();
            } catch (SQLException ex) {
                Logger.getLogger(ManageClients.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        
        String bookNo=lblId.getText();
        String date=lblDate.getText();
        String clientId=lblClientId.getText();
        String clientName=lblClientName.getText();
        String roomNo=lblRoomNo.getText();
        String roomType=lblRoomType.getText();
        int roomPrice=Integer.parseInt(lblRoomPrice.getText());
        String bookingDate=format.format(this.txtBookingDate.getDate());
        int pay=Integer.parseInt(txtPay.getText());
        int balance=Integer.parseInt(txtBalance.getText());

        if(txtPay.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter client's pay, a room cannot be allocated without payment","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPay.requestFocus();
        }else if(txtBalance.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please Calculate Balance by pressing ENTER key after payment, null balances cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtBalance.requestFocus();
        }else{
            try {
                pst=con.prepareStatement("update clientBookings set date=?, nId=?, clientName=?, roomNo=?, roomType=?, price=?,dateBooking=? ,pay=?, balance=? where bookNo=?");               
                pst.setString(1,date);
                pst.setString(2,clientId);
                pst.setString(3,clientName);
                pst.setString(4,roomNo);
                pst.setString(5,roomType);
                pst.setInt(6,roomPrice);
                pst.setString(7,bookingDate);
                pst.setInt(8,pay);
                pst.setInt(9,balance);
                pst.setString(10,bookNo);
                pst.execute();
                JOptionPane.showMessageDialog(this,"Client's Booking has been Updated Successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
                txtPay.setText("");
                txtBalance.setText("");
                txtBookingDate.setCalendar(null);
                txtPay.requestFocus();
                editBtn.setEnabled(false);
                deleteBtn.setEnabled(false);
                addBtn.setEnabled(true);
                Client_table();
                Rooms_table();
                Auto_id();
                date_method();
            } catch (SQLException ex) {
                Logger.getLogger(ManageClients.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void txtSearch2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String nationalId=txtSearch2.getText();
        try {
            pst=con.prepareStatement("select * from clientBookings where nId like CONCAT('%',?,'%')");
            pst.setString(1,nationalId);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("bookNo"));
                    v.add(rs.getString("date"));
                    v.add(rs.getString("nId"));
                    v.add(rs.getString("clientName"));
                    v.add(rs.getString("roomNo"));
                    v.add(rs.getString("roomType"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("dateBooking"));
                    v.add(rs.getInt("pay"));
                    v.add(rs.getInt("balance"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageClients.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_txtSearch2KeyPressed

    private void txtSearch1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String roomType=txtSearch1.getText();
        try {
            pst=con.prepareStatement("select * from roomsAvailable where roomType like CONCAT('%',?,'%')");
            pst.setString(1, roomType);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("roomNumber"));
                    v.add(rs.getString("roomType"));
                    v.add(rs.getString("roomPhone"));
                    v.add(rs.getInt("roomPrice"));
                    v.add(rs.getString("roomStatus"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageClients.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }//GEN-LAST:event_txtSearch1KeyPressed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        DefaultTableModel model=(DefaultTableModel)table1.getModel();
        int selectedIndex=table1.getSelectedRow();
        lblRoomNo.setText(model.getValueAt(selectedIndex, 0).toString());
        lblRoomType.setText(model.getValueAt(selectedIndex, 1).toString());
        lblRoomPrice.setText(model.getValueAt(selectedIndex, 3).toString());
        txtBookingDate.requestFocus();
    }//GEN-LAST:event_table1MouseClicked

    private void table1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_table1CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_table1CaretPositionChanged

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
         String roomType=txtSearch1.getText();
        try {
            pst=con.prepareStatement("select * from roomsAvailable where roomType like CONCAT('%',?,'%')");
            pst.setString(1, roomType);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("roomNumber"));
                    v.add(rs.getString("roomType"));
                    v.add(rs.getString("roomPhone"));
                    v.add(rs.getInt("roomPrice"));
                    v.add(rs.getString("roomStatus"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageClients.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearch1ActionPerformed

    private void btnSearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch2ActionPerformed
         String allNo=txtSearch2.getText();
        try {
            pst=con.prepareStatement("select * from clientBookings where nId like CONCAT('%',?,'%')");
            pst.setString(1,allNo);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("bookNo"));
                    v.add(rs.getString("date"));
                    v.add(rs.getString("nId"));
                    v.add(rs.getString("clientName"));
                    v.add(rs.getString("roomNo"));
                    v.add(rs.getString("roomType"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("dateBooking"));
                    v.add(rs.getInt("pay"));
                    v.add(rs.getInt("balance"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageClients.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearch2ActionPerformed

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        DefaultTableModel model=(DefaultTableModel)table2.getModel();
        int selectedIndex=table2.getSelectedRow();
        lblId.setText(model.getValueAt(selectedIndex, 0).toString());
        lblDate.setText(model.getValueAt(selectedIndex, 1).toString());
        lblClientId.setText(model.getValueAt(selectedIndex, 2).toString());
        lblClientName.setText(model.getValueAt(selectedIndex, 3).toString());
        lblRoomNo.setText(model.getValueAt(selectedIndex, 4).toString());
        lblRoomType.setText(model.getValueAt(selectedIndex, 5).toString());
        lblRoomPrice.setText(model.getValueAt(selectedIndex, 6).toString());
        txtPay.setText(model.getValueAt(selectedIndex, 7).toString());
        txtBalance.setText(model.getValueAt(selectedIndex, 8).toString());
        
        editBtn.setEnabled(true);
        deleteBtn.setEnabled(true);
        addBtn.setEnabled(false);
    }//GEN-LAST:event_table2MouseClicked

    private void txtSearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearch2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearch2ActionPerformed

    private void txtPayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPayKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            int price=Integer.parseInt(lblRoomPrice.getText());
            int payment=Integer.parseInt(txtPay.getText());
            
            int balance=payment-price;
            txtBalance.setText(String.valueOf(balance));
            txtBalance.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtBalance.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtPay.requestFocus();
        }
    }//GEN-LAST:event_txtPayKeyPressed

    private void txtBalanceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBalanceKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtPay.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtPay.requestFocus();
        }
    }//GEN-LAST:event_txtBalanceKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
    }//GEN-LAST:event_formWindowActivated

    private void computeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computeBtnActionPerformed
        int price=Integer.parseInt(lblRoomPrice.getText());
        int payment=Integer.parseInt(txtPay.getText());

        int balance=payment-price;
        txtBalance.setText(String.valueOf(balance));
        txtBalance.requestFocus();
    }//GEN-LAST:event_computeBtnActionPerformed

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
            java.util.logging.Logger.getLogger(BookRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new BookRooms().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnSearch2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton computeBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblClientId;
    private javax.swing.JLabel lblClientName;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblRoomNo;
    private javax.swing.JLabel lblRoomPrice;
    private javax.swing.JLabel lblRoomType;
    private javax.swing.JLabel lblUserType;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblUsername1;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JTextField txtBalance;
    private com.toedter.calendar.JDateChooser txtBookingDate;
    private javax.swing.JTextField txtPay;
    private javax.swing.JTextField txtSearch1;
    private javax.swing.JTextField txtSearch2;
    // End of variables declaration//GEN-END:variables
}
