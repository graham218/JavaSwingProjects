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
public class AvailableRooms extends javax.swing.JFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    /**
     * Creates new form ManageRooms
     */
    public AvailableRooms() {
        initComponents();
    }

    String username;
    BufferedImage imag;
    public AvailableRooms(String username, BufferedImage imag) {
        initComponents();
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("emmanuel-chigbo-jr7R01lOn1Y-unsplash.jpg")).getImage().getScaledInstance(1270, 650, Image.SCALE_SMOOTH)));
        Connect();
        Auto_id();
        Rooms_table();
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
    
    public void Rooms_table(){
        try {
            pst=con.prepareStatement("select * from roomsAvailable");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("id"));
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
    
    public void Auto_id(){
        try {
            Statement s=con.createStatement();
            rs=s.executeQuery("select MAX(id) from roomsAvailable");
            rs.next();
            rs.getString("MAX(id)");
            if(rs.getString("MAX(id)")==null){
                lblRoomId.setText("RM001");
            }else{
                long id=Long.parseLong(rs.getString("MAX(id)").substring(2,rs.getString("MAX(id)").length()));
                id++;
                lblRoomId.setText("RM"+String.format("%03d", id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageClients.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        txtRoomNo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtRoomType = new javax.swing.JTextField();
        lblRoomId = new javax.swing.JLabel();
        exitBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        homeBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        txtRoomPhone = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtRoomPrice = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        homeBtn1 = new javax.swing.JButton();
        homeBtn2 = new javax.swing.JButton();
        lblProfile = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ROOM TYPES");
        setMaximizedBounds(new java.awt.Rectangle(1270, 653, 653, 653));
        setMinimumSize(new java.awt.Dimension(1270, 653));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUsername.setFont(new java.awt.Font("Ubuntu", 3, 24)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(38, 16, 241));
        lblUsername.setText("USERNAME");
        getContentPane().add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 30, 170, -1));

        jLabel1.setFont(new java.awt.Font("Tibetan Machine Uni", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(7, 99, 22));
        jLabel1.setText("HOTEL ROOMS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, -1, 50));

        jPanel1.setBackground(new java.awt.Color(31, 73, 205));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ROOM NUMBER", "ROOM TYPE", "ROOM PHONE NUMBER", "PRICE"
            }
        ));
        table.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        table.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                tableCaretPositionChanged(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 700, 370));

        txtRoomNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRoomNoKeyPressed(evt);
            }
        });
        jPanel1.add(txtRoomNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 250, 40));

        jLabel7.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("ID:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("ROOM NUMBER:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("ROOM TYPE:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        txtRoomType.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRoomTypeKeyPressed(evt);
            }
        });
        jPanel1.add(txtRoomType, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 250, 40));

        lblRoomId.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblRoomId.setForeground(java.awt.Color.white);
        lblRoomId.setText("ROOM ID");
        lblRoomId.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblRoomId, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 250, 40));

        exitBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(238, 13, 24));
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 430, 130, 50));

        deleteBtn.setBackground(new java.awt.Color(230, 29, 8));
        deleteBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        deleteBtn.setForeground(java.awt.Color.white);
        deleteBtn.setText("DELETE ROOM");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, 160, 50));

        homeBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        homeBtn.setForeground(new java.awt.Color(36, 39, 246));
        homeBtn.setText("HOME PAGE");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 130, 50));

        clearBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(128, 13, 238));
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 130, 50));

        addBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        addBtn.setForeground(new java.awt.Color(0, 22, 255));
        addBtn.setText("ADD NEW ROOM");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 360, -1, 50));

        editBtn.setBackground(new java.awt.Color(58, 205, 67));
        editBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        editBtn.setForeground(java.awt.Color.white);
        editBtn.setText("EDIT ROOM");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        jPanel1.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 360, 130, 50));

        txtRoomPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRoomPhoneKeyPressed(evt);
            }
        });
        jPanel1.add(txtRoomPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 250, 40));

        jLabel5.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("ROOM PHONE:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        txtRoomPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRoomPriceKeyPressed(evt);
            }
        });
        jPanel1.add(txtRoomPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, 250, 40));

        jLabel6.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("ROOM PRICE:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        homeBtn1.setBackground(new java.awt.Color(239, 55, 14));
        homeBtn1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        homeBtn1.setForeground(java.awt.Color.white);
        homeBtn1.setText("TABLE TO PDF");
        homeBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtn1ActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 470, 160, 50));

        homeBtn2.setBackground(new java.awt.Color(37, 206, 47));
        homeBtn2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        homeBtn2.setForeground(java.awt.Color.white);
        homeBtn2.setText("TABLE TO EXCEL");
        homeBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtn2ActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 470, 160, 50));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 0, 80, 80));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 1230, 570));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 650));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        DefaultTableModel model=(DefaultTableModel)table.getModel();
        int selectedIndex=table.getSelectedRow();
        lblRoomId.setText(model.getValueAt(selectedIndex, 0).toString());
        txtRoomNo.setText(model.getValueAt(selectedIndex, 1).toString());
        txtRoomType.setText(model.getValueAt(selectedIndex, 2).toString());
        txtRoomPhone.setText(model.getValueAt(selectedIndex, 3).toString());
        txtRoomPrice.setText(model.getValueAt(selectedIndex, 4).toString());
        
        editBtn.setEnabled(true);
        deleteBtn.setEnabled(true);
    }//GEN-LAST:event_tableMouseClicked

    private void txtRoomNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRoomNoKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtRoomType.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtRoomType.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtRoomPrice.requestFocus();
        }
    }//GEN-LAST:event_txtRoomNoKeyPressed

    private void txtRoomTypeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRoomTypeKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtRoomPhone.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtRoomPhone.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtRoomNo.requestFocus();
        }
    }//GEN-LAST:event_txtRoomTypeKeyPressed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to exit Hotel System?","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        String id=lblRoomId.getText();
        try {
            pst=con.prepareStatement("delete from roomsAvailable where id=?");
            pst.setString(1,id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Room Deleted Successfully from database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            Auto_id();
            txtRoomNo.setText("");
            txtRoomType.setText("");
            txtRoomPhone.setText("");
            txtRoomPrice.setText("");
            txtRoomNo.requestFocus();
            Rooms_table();
        } catch (SQLException ex) {
            Logger.getLogger(ManageClients.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        this.setVisible(false);
        new Main(lblUsername.getText(),imag).setVisible(true);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        txtRoomNo.setText("");
        txtRoomType.setText("");
        txtRoomPhone.setText("");
        txtRoomPrice.setText("");
        txtRoomNo.requestFocus();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String id=lblRoomId.getText();
        String roomNo=txtRoomNo.getText();
        String roomType=txtRoomType.getText();
        String roomPhone=txtRoomPhone.getText();
        String roomPrice=txtRoomPrice.getText();

        if(roomNo.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter Room number, null Room Numbers cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtRoomNo.requestFocus();
        }else if(roomType.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter Room type, null Room Types cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtRoomType.requestFocus();
        }else if(roomPhone.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter Rooms's Phone number, null Phone numbers cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtRoomPhone.requestFocus();
        }else if(roomPrice.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter Room's price, null Room Prices cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtRoomPrice.requestFocus();
        }else{
            try {
                pst=con.prepareStatement("insert into roomsAvailable (id,roomNumber,roomType,roomPhone,roomPrice)values(?,?,?,?,?)");
                pst.setString(1,id);
                pst.setString(2,roomNo);
                pst.setString(3,roomType);
                pst.setString(4,roomPhone);
                pst.setString(5,roomPrice);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this,"Room Added Successfully into database","Success Message",JOptionPane.INFORMATION_MESSAGE);
                Auto_id();
                txtRoomNo.setText("");
                txtRoomType.setText("");
                txtRoomPhone.setText("");
                txtRoomPrice.setText("");;
                txtRoomNo.requestFocus();
                Rooms_table();
            } catch (SQLException ex) {
                Logger.getLogger(ManageClients.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        String roomNo=txtRoomNo.getText();
        String roomType=txtRoomType.getText();
        String roomPhone=txtRoomPhone.getText();
        String roomPrice=txtRoomPrice.getText();
        String id=lblRoomId.getText();

        if(roomNo.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter Room number, null Room Numbers cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtRoomNo.requestFocus();
        }else if(roomType.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter Room type, null Room Types cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtRoomType.requestFocus();
        }else if(roomPhone.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter Rooms's Phone number, null Phone numbers cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtRoomPhone.requestFocus();
        }else if(roomPrice.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter Room's price, null Room Prices cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtRoomPrice.requestFocus();
        }else{
            try {
                pst=con.prepareStatement("update roomsAvailable set roomNumber=?, roomType=?, roomPhone=?, roomPrice=? where id=?");
                pst.setString(1,roomNo);
                pst.setString(2,roomType);
                pst.setString(3,roomPhone);
                pst.setString(4,roomPrice);
                pst.setString(5,id);
                pst.execute();
                JOptionPane.showMessageDialog(this,"Room Updated Successfully on database","Success Message",JOptionPane.INFORMATION_MESSAGE);
                Auto_id();
                txtRoomNo.setText("");
                txtRoomType.setText("");
                txtRoomPhone.setText("");
                txtRoomPrice.setText("");
                txtRoomNo.requestFocus();
                Rooms_table();
            } catch (SQLException ex) {
                Logger.getLogger(ManageClients.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void txtRoomPhoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRoomPhoneKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtRoomPrice.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtRoomPrice.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtRoomType.requestFocus();
        }
    }//GEN-LAST:event_txtRoomPhoneKeyPressed

    private void tableCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tableCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tableCaretPositionChanged

    private void txtRoomPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRoomPriceKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           String id=lblRoomId.getText();
        String roomNo=txtRoomNo.getText();
        String roomType=txtRoomType.getText();
        String roomPhone=txtRoomPhone.getText();
        String roomPrice=txtRoomPrice.getText();

        if(roomNo.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter Room number, null Room Numbers cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtRoomNo.requestFocus();
        }else if(roomType.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter Room type, null Room Types cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtRoomType.requestFocus();
        }else if(roomPhone.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter Rooms's Phone number, null Phone numbers cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtRoomPhone.requestFocus();
        }else if(roomPrice.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter Room's price, null Room Prices cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtRoomPrice.requestFocus();
        }else{
            try {
                pst=con.prepareStatement("insert into roomsAvailable (id,roomNumber,roomType,roomPhone,roomPrice)values(?,?,?,?,?)");
                pst.setString(1,id);
                pst.setString(2,roomNo);
                pst.setString(3,roomType);
                pst.setString(4,roomPhone);
                pst.setString(5,roomPrice);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this,"Client Addent Successfully into database","Success Message",JOptionPane.INFORMATION_MESSAGE);
                Auto_id();
                txtRoomNo.setText("");
                txtRoomType.setText("");
                txtRoomPhone.setText("");
                txtRoomPrice.setText("");;
                txtRoomNo.requestFocus();
                Rooms_table();
            } catch (SQLException ex) {
                Logger.getLogger(ManageClients.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtRoomNo.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtRoomPhone.requestFocus();
        }
    }//GEN-LAST:event_txtRoomPriceKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
    }//GEN-LAST:event_formWindowActivated

    private void homeBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtn1ActionPerformed
        MessageFormat header=new MessageFormat("AVAILABLE ROOMS");
        MessageFormat footer=new MessageFormat("page {0}");
        try {
            table.print(JTable.PrintMode.FIT_WIDTH,header,footer);
        } catch (PrinterException ex) {
            Logger.getLogger(AllocateRooms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_homeBtn1ActionPerformed

    private void homeBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtn2ActionPerformed
        JFileChooser fileChooser=new JFileChooser();
        fileChooser.setDialogTitle("Export Clients' Allocation to CSV?");
        int userSelection=fileChooser.showSaveDialog(this);
        if(userSelection==JFileChooser.APPROVE_OPTION){
            File file=fileChooser.getSelectedFile();
            FileWriter writer;
            try {
                writer = new FileWriter(file);
                BufferedWriter bWriter=new BufferedWriter(writer);
                for (int i = 0; i < table.getRowCount(); i++) {
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        bWriter.write(table.getValueAt(i, j).toString()+",");
                    }
                    bWriter.newLine();
                }
                bWriter.close();
                writer.close();
                JOptionPane.showMessageDialog(this, "Table Exported Successfully into and excel file","Success Message",JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(AllocateRooms.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_homeBtn2ActionPerformed

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
            java.util.logging.Logger.getLogger(AvailableRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AvailableRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AvailableRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AvailableRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AvailableRooms().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JButton homeBtn1;
    private javax.swing.JButton homeBtn2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblRoomId;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtRoomNo;
    private javax.swing.JTextField txtRoomPhone;
    private javax.swing.JTextField txtRoomPrice;
    private javax.swing.JTextField txtRoomType;
    // End of variables declaration//GEN-END:variables
}
