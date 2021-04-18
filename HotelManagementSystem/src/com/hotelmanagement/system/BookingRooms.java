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
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
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
public class BookingRooms extends javax.swing.JFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    /**
     * Creates new form ManageClients
     */
    public BookingRooms() {
        initComponents();
    }
    
    String username;
    BufferedImage imag;
    public BookingRooms(String username, BufferedImage imag) {
        initComponents();
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(160, 170, Image.SCALE_SMOOTH)));
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("emmanuel-chigbo-jr7R01lOn1Y-unsplash.jpg")).getImage().getScaledInstance(1240, 780, Image.SCALE_SMOOTH)));
        Connect();
        Auto_id();
        Rooms_table();
        Client_table();
        date_method();
    }
    
    
    public void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/Hotel","root","");
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
            rs=s.executeQuery("select MAX(id) from bookings");
            rs.next();
            rs.getString("MAX(id)");
            if(rs.getString("MAX(id)")==null){
                lblId.setText("CL001");
            }else{
                long id=Long.parseLong(rs.getString("MAX(id)").substring(2,rs.getString("MAX(id)").length()));
                id++;
                lblId.setText("CL"+String.format("%03d", id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookingRooms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Client_table(){
        try {
            pst=con.prepareStatement("select * from bookings");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table3.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("date"));
                    v.add(rs.getString("id"));
                    v.add(rs.getString("nationalId"));
                    v.add(rs.getString("fullName"));
                    v.add(rs.getString("phone"));
                    v.add(rs.getString("dateBooking"));
                    v.add(rs.getString("roomNumber"));
                    v.add(rs.getString("roomType"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getInt("payment"));
                    v.add(rs.getInt("balance"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookingRooms.class.getName()).log(Level.SEVERE, null, ex);
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
                    v.add(rs.getString("roomPrice"));
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
    
    public void print_mentod(){
        DefaultTableModel model=(DefaultTableModel)table2.getModel();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String date=format.format(jDate.getDate());
        model.addRow(new Object[]{
            lblDate.getText(),
            txtNationalId.getText(),
            txtFullName.getText(),
            date,
            lblRoomNo.getText(),
            txtPayment.getText(),
            txtBalance.getText()
        });
    }
    
    public void clientPay(){
        String datee=lblDate.getText();
        String cashier=lblUsername.getText();
        String clientID=lblId.getText();
        String payment=txtPayment.getText();
        String balancen=txtBalance.getText();
        
        try {
            pst=con.prepareStatement("insert into clientBook (datee,cashier,clientID,payment,balancen) values(?,?,?,?,?)");
            pst.setString(1,datee);
            pst.setString(2,cashier);
            pst.setString(3,clientID);
            pst.setString(4,payment);
            pst.setString(5,balancen);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AllocateRooms.class.getName()).log(Level.SEVERE, null, ex);
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

        lblUsername = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPayment = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table3 = new javax.swing.JTable();
        homeBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        lblId = new javax.swing.JLabel();
        txtNationalId = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jDate = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        txtSearch1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnSearch1 = new javax.swing.JButton();
        lblRoomType = new javax.swing.JLabel();
        lblRoomNo = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtBalance = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        txtSearch2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btnSearch2 = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        printBtn = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        lblPrice = new javax.swing.JLabel();
        printBtn1 = new javax.swing.JButton();
        printBtn2 = new javax.swing.JButton();
        lblProfile = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CLIENT BOOKINGS");
        setMaximizedBounds(new java.awt.Rectangle(1236, 778, 778, 778));
        setMaximumSize(new java.awt.Dimension(1236, 778));
        setMinimumSize(new java.awt.Dimension(1236, 778));
        setPreferredSize(new java.awt.Dimension(1236, 778));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUsername.setFont(new java.awt.Font("Ubuntu", 3, 24)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(38, 16, 241));
        lblUsername.setText("USERNAME");
        getContentPane().add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 10, 170, -1));

        jPanel1.setBackground(new java.awt.Color(89, 23, 166));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("ID:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        txtFullName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFullNameKeyPressed(evt);
            }
        });
        jPanel1.add(txtFullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 210, 40));

        jLabel3.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("FULL NAME:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("PAYMENT:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, -1, -1));

        txtPayment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPaymentKeyPressed(evt);
            }
        });
        jPanel1.add(txtPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 460, 210, 40));

        txtPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPhoneKeyPressed(evt);
            }
        });
        jPanel1.add(txtPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 210, 40));

        jLabel6.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("PHONE NO:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        table3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE", "ID", "N.ID", "FULL NAME", "PHONE NO", "DATE BOOKING", "ROOM NO", "TYPE", "PRICE", "PAY", "BALANCE"
            }
        ));
        table3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        table3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table3MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table3);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 430, 790, 220));

        homeBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        homeBtn.setForeground(new java.awt.Color(36, 39, 246));
        homeBtn.setText("HOME");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 620, 100, 50));

        addBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        addBtn.setForeground(new java.awt.Color(0, 22, 255));
        addBtn.setText("ADD");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 560, 110, 50));

        editBtn.setBackground(new java.awt.Color(81, 205, 45));
        editBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        editBtn.setForeground(java.awt.Color.white);
        editBtn.setText("EDIT");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        jPanel1.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 560, 110, 50));

        exitBtn.setBackground(new java.awt.Color(230, 56, 31));
        exitBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        exitBtn.setForeground(java.awt.Color.white);
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 620, 110, 50));

        clearBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(128, 13, 238));
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 100, 50));

        deleteBtn.setBackground(new java.awt.Color(230, 56, 31));
        deleteBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        deleteBtn.setForeground(java.awt.Color.white);
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 620, 110, 50));

        lblId.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblId.setForeground(java.awt.Color.white);
        lblId.setText("CLIENT'S NUMBER");
        lblId.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 210, 40));

        txtNationalId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNationalIdKeyPressed(evt);
            }
        });
        jPanel1.add(txtNationalId, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 210, 40));

        jLabel7.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("NATIONAL ID:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        lblDate.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblDate.setForeground(java.awt.Color.white);
        lblDate.setText("CURRENT DATE");
        lblDate.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 210, 40));

        jLabel8.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel8.setForeground(java.awt.Color.white);
        jLabel8.setText("DATE:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel9.setForeground(java.awt.Color.white);
        jLabel9.setText("DATE BOOKING:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        jDate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDateKeyPressed(evt);
            }
        });
        jPanel1.add(jDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 210, 40));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ROOM NUMBER", "ROOM TYPE", "ROOM PHONE", "PRICE"
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
        jScrollPane2.setViewportView(table1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 490, 150));

        txtSearch1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearch1KeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 260, 40));

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel10.setForeground(java.awt.Color.white);
        jLabel10.setText("NOTE: USE ROOM TYPES TO SEARCH FOR AVAILABLE ROOMS");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 500, 30));

        btnSearch1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnSearch1.setText("SEARCH ROOMS");
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, -1, 40));

        lblRoomType.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblRoomType.setForeground(java.awt.Color.white);
        lblRoomType.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblRoomType, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, 210, 40));

        lblRoomNo.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblRoomNo.setForeground(java.awt.Color.white);
        lblRoomNo.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblRoomNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 210, 40));

        jLabel11.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel11.setForeground(java.awt.Color.white);
        jLabel11.setText("ROOM NO:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("ROOM TYPE:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel12.setForeground(java.awt.Color.white);
        jLabel12.setText("BALANCE:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 150, -1));

        txtBalance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBalanceKeyPressed(evt);
            }
        });
        jPanel1.add(txtBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 510, 210, 40));

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE", "N.ID", "NAME", "DATE BOOKING", "ROOM NO", "PAY", "BALANCE"
            }
        ));
        jScrollPane3.setViewportView(table2);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 250, 620, 90));

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
        jPanel1.add(txtSearch2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 350, 200, 40));

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel13.setForeground(java.awt.Color.white);
        jLabel13.setText("NOTE: USE CLIENT'S NATIONAL ID TO SEARCH FOR CLIENT'S ROOM ALLOCATION");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 400, 610, 30));

        btnSearch2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnSearch2.setText("SEARCH");
        btnSearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch2ActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 350, -1, 40));

        btnClear.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnClear.setForeground(new java.awt.Color(240, 16, 21));
        btnClear.setText("CLEAR");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        jPanel1.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 250, 150, 40));

        printBtn.setBackground(new java.awt.Color(43, 200, 44));
        printBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        printBtn.setForeground(java.awt.Color.white);
        printBtn.setText("EXPORT CLIENTS-PDF");
        printBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBtnActionPerformed(evt);
            }
        });
        jPanel1.add(printBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 180, 220, 50));

        jLabel14.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel14.setForeground(java.awt.Color.white);
        jLabel14.setText("PRICE:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));

        lblPrice.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblPrice.setForeground(java.awt.Color.white);
        lblPrice.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 410, 210, 40));

        printBtn1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        printBtn1.setForeground(new java.awt.Color(240, 16, 240));
        printBtn1.setText("PRINT RECEIPT");
        printBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBtn1ActionPerformed(evt);
            }
        });
        jPanel1.add(printBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 300, 150, 40));

        printBtn2.setBackground(new java.awt.Color(231, 35, 24));
        printBtn2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        printBtn2.setForeground(java.awt.Color.white);
        printBtn2.setText("EXPORT CLIENTS-EXCEL");
        printBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBtn2ActionPerformed(evt);
            }
        });
        jPanel1.add(printBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 120, 220, 50));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 10, 80, 80));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1210, 680));

        jLabel1.setFont(new java.awt.Font("Tibetan Machine Uni", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(45, 13, 249));
        jLabel1.setText("BOOKING OF ROOMS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, -1, 50));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 780));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        this.setVisible(false);
        new Main(lblUsername.getText(),imag).setVisible(true);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        String id=lblId.getText();       
        try {
            pst=con.prepareStatement("delete from bookings where id=?");
            pst.setString(1,id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Client Deleted Successfully from database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            Auto_id();
            date_method();
            txtNationalId.setText("");
            txtFullName.setText("");
            txtPhone.setText("");
            jDate.setCalendar(null);
            lblRoomNo.setText("");
            lblRoomType.setText("");
            lblPrice.setText("");
            txtPayment.setText("");
            txtBalance.setText("");
            txtNationalId.requestFocus();
            Client_table();
        } catch (SQLException ex) {
            Logger.getLogger(BookingRooms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to exit Hotel System?","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        
        String date=lblDate.getText();
        String id=lblId.getText();
        String nationalId=txtNationalId.getText();
        String fullName=txtFullName.getText();
        String phone=txtPhone.getText();
        String bookingDate=format.format(this.jDate.getDate());
        String roomNumber=lblRoomNo.getText();
        String roomType=lblRoomType.getText();
        int price=Integer.parseInt(lblPrice.getText());
        int payment=Integer.parseInt(txtPayment.getText());
        int balance=Integer.parseInt(txtBalance.getText());
        
        if(nationalId.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter your national id, null national ids cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtNationalId.requestFocus();
        }else if(fullName.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter client's name, null names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtFullName.requestFocus();
        }else if(phone.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter client's phone number, null phone numbers names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPhone.requestFocus();
        }else if(bookingDate.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please select date booking, null dates cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            jDate.requestFocus();
        }else if(roomNumber.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please select room number, null room numbers cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            lblRoomNo.requestFocus();
        }else if(roomType.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please select room type, null room types cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            lblRoomType.requestFocus();
        }else if(lblPrice.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please select room price, null room prices cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            lblPrice.requestFocus();
        }else if(txtPayment.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter client's payment, null payments cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPayment.requestFocus();
        }else if(txtBalance.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please Tabulate balance by pressing ENTER KEY after payment, null balances cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPayment.requestFocus();
        }else{
        try {
            pst=con.prepareStatement("insert into bookings (date,id,nationalId,fullName,phone,dateBooking, roomNumber, roomType, price, payment, balance)values(?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1,date);
            pst.setString(2,id);
            pst.setString(3,nationalId);
            pst.setString(4,fullName);
            pst.setString(5,phone);
            pst.setString(6,bookingDate);
            pst.setString(7,roomNumber);
            pst.setString(8,roomType);
            pst.setInt(9,price);
            pst.setInt(10,payment);
            pst.setInt(11,balance);
            pst.executeUpdate();
            print_mentod();
            clientPay();
            JOptionPane.showMessageDialog(this,"Client has booked a room Successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
            Auto_id();
            txtNationalId.setText("");
            txtFullName.setText("");
            txtPhone.setText("");
            jDate.setCalendar(null);
            lblRoomNo.setText("");
            lblRoomType.setText("");
            lblPrice.setText("");
            txtPayment.setText("");
            txtBalance.setText("");
            txtNationalId.requestFocus();
            Client_table();
        } catch (SQLException ex) {
            Logger.getLogger(BookingRooms.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        txtNationalId.setText("");
        txtFullName.setText("");
        txtPhone.setText("");
        jDate.setCalendar(null);
        lblRoomNo.setText("");
        lblRoomType.setText("");
        lblPrice.setText("");
        txtPayment.setText("");
        txtBalance.setText("");
        txtNationalId.requestFocus();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        
        String date=lblDate.getText();
        String id=lblId.getText();
        String nationalId=txtNationalId.getText();
        String fullName=txtFullName.getText();
        String phone=txtPhone.getText();
        String bookingDate=format.format(this.jDate.getDate());
        String roomNumber=lblRoomNo.getText();
        String roomType=lblRoomType.getText();
        int price=Integer.parseInt(lblPrice.getText());
        int payment=Integer.parseInt(txtPayment.getText());
        int balance=Integer.parseInt(txtBalance.getText());
        
        if(nationalId.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter your national id, null national ids cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtNationalId.requestFocus();
        }else if(fullName.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter client's name, null names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtFullName.requestFocus();
        }else if(phone.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter client's phone number, null phone numbers names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPhone.requestFocus();
        }else if(bookingDate.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please select date booking, null dates cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            jDate.requestFocus();
        }else if(roomNumber.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please select room number, null room numbers cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            lblRoomNo.requestFocus();
        }else if(roomType.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please select room type, null room types cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            lblRoomType.requestFocus();
        }else if(lblPrice.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please select room price, null room prices cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            lblPrice.requestFocus();
        }else if(txtPayment.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter client's payment, null payments cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPayment.requestFocus();
        }else if(txtBalance.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please Tabulate balance by pressing ENTER KEY after payment, null balances cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPayment.requestFocus();
        }else{
        try {
            pst=con.prepareStatement("update bookings set date=?, nationalId=?, fullName=?, phone=?, dateBooking=?, roomNumber=?, roomType=?, price=?, payment=?, balance=? where id=?");
            pst.setString(1,date);
            pst.setString(2,nationalId);
            pst.setString(3,fullName);
            pst.setString(4,phone);
            pst.setString(5,bookingDate);
            pst.setString(6,roomNumber);
            pst.setString(7,roomType);
            pst.setInt(8,price);
            pst.setInt(9,payment);
            pst.setInt(10,balance);
            pst.setString(11,id);
            pst.execute();
            JOptionPane.showMessageDialog(this,"Client booking Updated Successfully on database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            Auto_id();
            date_method();
            txtNationalId.setText("");
            txtFullName.setText("");
            txtPhone.setText("");
            jDate.setCalendar(null);
            lblRoomNo.setText("");
            lblRoomType.setText("");
            lblPrice.setText("");
            txtPayment.setText("");
            txtBalance.setText("");
            txtNationalId.requestFocus();
            Client_table();
        } catch (SQLException ex) {
            Logger.getLogger(BookingRooms.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_editBtnActionPerformed
    
    private void txtFullNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFullNameKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtPhone.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtPhone.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtNationalId.requestFocus();
        }
    }//GEN-LAST:event_txtFullNameKeyPressed

    private void txtPaymentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaymentKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            int price=Integer.parseInt(lblPrice.getText());
            int payment=Integer.parseInt(txtPayment.getText());
            int balance=payment-price;
            txtBalance.setText(String.valueOf(balance));
            txtBalance.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtBalance.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            jDate.requestFocus();
        }
    }//GEN-LAST:event_txtPaymentKeyPressed

    private void txtPhoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhoneKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jDate.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jDate.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtFullName.requestFocus();
        }
    }//GEN-LAST:event_txtPhoneKeyPressed

    private void txtNationalIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNationalIdKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtFullName.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtFullName.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtNationalId.requestFocus();
        }
    }//GEN-LAST:event_txtNationalIdKeyPressed

    private void table3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table3MouseClicked
        DefaultTableModel model=(DefaultTableModel)table3.getModel();
        int selectedIndex=table3.getSelectedRow();
        lblDate.setText(model.getValueAt(selectedIndex, 0).toString());
        lblId.setText(model.getValueAt(selectedIndex, 1).toString());
        txtNationalId.setText(model.getValueAt(selectedIndex, 2).toString());
        txtFullName.setText(model.getValueAt(selectedIndex, 3).toString());
        txtPhone.setText(model.getValueAt(selectedIndex, 4).toString());       
        String date=(model.getValueAt(selectedIndex, 5).toString());
        try{
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d=format.parse(date);
            jDate.setDate(d);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        lblRoomNo.setText(model.getValueAt(selectedIndex, 6).toString());
        lblRoomType.setText(model.getValueAt(selectedIndex, 7).toString());
        lblPrice.setText(model.getValueAt(selectedIndex, 8).toString());
        txtPayment.setText(model.getValueAt(selectedIndex, 9).toString());
        txtBalance.setText(model.getValueAt(selectedIndex, 10).toString());
        
        editBtn.setEnabled(true);
        deleteBtn.setEnabled(true);
    }//GEN-LAST:event_table3MouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
    }//GEN-LAST:event_formWindowActivated

    private void jDateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtPayment.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtPayment.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtPhone.requestFocus();
        }
    }//GEN-LAST:event_jDateKeyPressed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        DefaultTableModel model=(DefaultTableModel)table1.getModel();
        int selectedIndex=table1.getSelectedRow();
        lblRoomNo.setText(model.getValueAt(selectedIndex, 0).toString());
        lblRoomType.setText(model.getValueAt(selectedIndex, 1).toString());
        lblPrice.setText(model.getValueAt(selectedIndex, 3).toString());
    }//GEN-LAST:event_table1MouseClicked

    private void table1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_table1CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_table1CaretPositionChanged

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
                        v.add(rs.getString("roomPrice"));
                    }
                    model.addRow(v);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ManageClients.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtSearch1KeyPressed

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
                        v.add(rs.getString("roomPrice"));
                    }
                    model.addRow(v);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ManageClients.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_btnSearch1ActionPerformed

    private void txtBalanceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBalanceKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        
        String date=lblDate.getText();
        String id=lblId.getText();
        String nationalId=txtNationalId.getText();
        String fullName=txtFullName.getText();
        String phone=txtPhone.getText();
        String bookingDate=format.format(this.jDate.getDate());
        String roomNumber=lblRoomNo.getText();
        String roomType=lblRoomType.getText();
        int price=Integer.parseInt(lblPrice.getText());
        int payment=Integer.parseInt(txtPayment.getText());
        int balance=Integer.parseInt(txtBalance.getText());
        
        if(nationalId.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter your national id, null national ids cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtNationalId.requestFocus();
        }else if(fullName.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter client's name, null names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtFullName.requestFocus();
        }else if(phone.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter client's phone number, null phone numbers names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPhone.requestFocus();
        }else if(bookingDate.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please select date booking, null dates cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            jDate.requestFocus();
        }else if(roomNumber.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please select room number, null room numbers cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            lblRoomNo.requestFocus();
        }else if(roomType.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please select room type, null room types cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            lblRoomType.requestFocus();
        }else if(lblPrice.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please select room price, null room prices cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            lblPrice.requestFocus();
        }else if(txtPayment.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter client's payment, null payments cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPayment.requestFocus();
        }else if(txtBalance.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please Tabulate balance by pressing ENTER KEY after payment, null balances cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPayment.requestFocus();
        }else{
        try {
            pst=con.prepareStatement("insert into bookings (date,id,nationalId,fullName,phone,dateBooking, roomNumber, roomType, price, payment, balance)values(?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1,date);
            pst.setString(2,id);
            pst.setString(3,nationalId);
            pst.setString(4,fullName);
            pst.setString(5,phone);
            pst.setString(6,bookingDate);
            pst.setString(7,roomNumber);
            pst.setString(8,roomType);
            pst.setInt(9,price);
            pst.setInt(10,payment);
            pst.setInt(11,balance);
            pst.executeUpdate();
            print_mentod();
            clientPay();
            JOptionPane.showMessageDialog(this,"Client has booked a room Successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
            Auto_id();
            txtNationalId.setText("");
            txtFullName.setText("");
            txtPhone.setText("");
            jDate.setCalendar(null);
            lblRoomNo.setText("");
            lblRoomType.setText("");
            lblPrice.setText("");
            txtPayment.setText("");
            txtBalance.setText("");
            txtNationalId.requestFocus();
            Client_table();
        } catch (SQLException ex) {
            Logger.getLogger(BookingRooms.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
            
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtNationalId.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtPayment.requestFocus();
        }
    }//GEN-LAST:event_txtBalanceKeyPressed

    private void txtSearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearch2ActionPerformed
        
    }//GEN-LAST:event_txtSearch2ActionPerformed

    private void txtSearch2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String nationalId=txtSearch2.getText();
            try {
                pst=con.prepareStatement("select * from bookings where nationalId like CONCAT('%',?,'%')");
                pst.setString(1,nationalId);
                rs=pst.executeQuery();
                ResultSetMetaData rsm=rs.getMetaData();
                int c=rsm.getColumnCount();

                DefaultTableModel model=(DefaultTableModel)table3.getModel();
                model.setRowCount(0);
                while(rs.next()){
                    Vector v=new Vector();
                    for(int i=1;i<c;i++){
                        v.add(rs.getString("date"));
                        v.add(rs.getString("id"));
                        v.add(rs.getString("nationalId"));
                        v.add(rs.getString("fullName"));
                        v.add(rs.getString("phone"));
                        v.add(rs.getString("dateBooking"));
                        v.add(rs.getString("roomNumber"));
                        v.add(rs.getString("roomType"));
                        v.add(rs.getInt("price"));
                        v.add(rs.getInt("payment"));
                        v.add(rs.getInt("balance"));
                    }
                    model.addRow(v);
                }
            } catch (SQLException ex) {
                Logger.getLogger(BookingRooms.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtSearch2KeyPressed

    private void btnSearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch2ActionPerformed
        String nationalId=txtSearch2.getText();
        try {
            pst=con.prepareStatement("select * from bookings where nationalId like CONCAT('%',?,'%')");
            pst.setString(1,nationalId);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table3.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("date"));
                    v.add(rs.getString("id"));
                    v.add(rs.getString("nationalId"));
                    v.add(rs.getString("fullName"));
                    v.add(rs.getString("phone"));
                    v.add(rs.getString("dateBooking"));
                    v.add(rs.getString("roomNumber"));
                    v.add(rs.getString("roomType"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getInt("payment"));
                    v.add(rs.getInt("balance"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookingRooms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearch2ActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        DefaultTableModel model=(DefaultTableModel)table2.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_btnClearActionPerformed

    private void printBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBtnActionPerformed
        MessageFormat header=new MessageFormat("CLIENT ROOM BOOKING");
        MessageFormat footer=new MessageFormat("page {0}");
        try {
            table3.print(JTable.PrintMode.FIT_WIDTH,header,footer);
        } catch (PrinterException ex) {
            Logger.getLogger(BookingRooms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_printBtnActionPerformed

    private void printBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_printBtn1ActionPerformed

    private void printBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBtn2ActionPerformed
        JFileChooser fileChooser=new JFileChooser();
        fileChooser.setDialogTitle("Export table to an excel file.csv format?");
        int userSelection=fileChooser.showSaveDialog(this);
        if(userSelection==JFileChooser.APPROVE_OPTION){
            File file=fileChooser.getSelectedFile();
            
            FileWriter writer;
            try {
                writer=new FileWriter(file);
                BufferedWriter bWriter=new BufferedWriter(writer);
                for (int i = 0; i < table3.getRowCount(); i++) {
                    for (int j = 0; j < table3.getColumnCount(); j++) {
                        bWriter.write(table3.getValueAt(i, j).toString()+",");
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
    }//GEN-LAST:event_printBtn2ActionPerformed

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
            java.util.logging.Logger.getLogger(BookingRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookingRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookingRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookingRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookingRooms().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnSearch2;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private com.toedter.calendar.JDateChooser jDate;
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
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblRoomNo;
    private javax.swing.JLabel lblRoomType;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JButton printBtn;
    private javax.swing.JButton printBtn1;
    private javax.swing.JButton printBtn2;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JTable table3;
    private javax.swing.JTextField txtBalance;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JTextField txtNationalId;
    private javax.swing.JTextField txtPayment;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSearch1;
    private javax.swing.JTextField txtSearch2;
    // End of variables declaration//GEN-END:variables
}
