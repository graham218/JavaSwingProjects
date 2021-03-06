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
public class ManageClients extends javax.swing.JFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    /**
     * Creates new form ManageClients
     */
    public ManageClients() {
        initComponents();
    }
    
    String username;
    BufferedImage imag;
    public ManageClients(String username,BufferedImage imag) {
        initComponents();
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("emmanuel-chigbo-jr7R01lOn1Y-unsplash.jpg")).getImage().getScaledInstance(1240, 720, Image.SCALE_SMOOTH)));
        Connect();
        Auto_id();
        Client_table();
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
            rs=s.executeQuery("select MAX(id) from clients");
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
            Logger.getLogger(ManageClients.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Client_table(){
        try {
            pst=con.prepareStatement("select * from clients");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("id"));
                    v.add(rs.getString("nationalId"));
                    v.add(rs.getString("firstName"));
                    v.add(rs.getString("lastName"));
                    v.add(rs.getString("phone"));
                    v.add(rs.getString("email"));
                }
                model.addRow(v);
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
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        homeBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        lblId = new javax.swing.JLabel();
        txtNationalId = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        printBtn = new javax.swing.JButton();
        btnAllocate = new javax.swing.JButton();
        printBtn1 = new javax.swing.JButton();
        lblProfile = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MANAGE CLIENTS");
        setMaximizedBounds(new java.awt.Rectangle(1236, 718, 718, 718));
        setMaximumSize(new java.awt.Dimension(1236, 718));
        setMinimumSize(new java.awt.Dimension(1236, 718));
        setPreferredSize(new java.awt.Dimension(1236, 718));
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

        jPanel1.setBackground(new java.awt.Color(89, 23, 166));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("ID:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        txtFirstName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFirstNameKeyPressed(evt);
            }
        });
        jPanel1.add(txtFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 260, 40));

        jLabel3.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("FIRST NAME:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("LAST NAME:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        txtLastName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLastNameKeyPressed(evt);
            }
        });
        jPanel1.add(txtLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 260, 40));

        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmailKeyPressed(evt);
            }
        });
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 380, 260, 40));

        jLabel5.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("EMAIL:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, -1));

        txtPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPhoneKeyPressed(evt);
            }
        });
        jPanel1.add(txtPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 260, 40));

        jLabel6.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("PHONE NO:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NATIONAL ID", "FIRST NAME", "LAST NAME", "PHONE NO", "EMAIL"
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
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 130, 50));

        addBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        addBtn.setForeground(new java.awt.Color(0, 22, 255));
        addBtn.setText("ADD NEW CLIENT");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 440, -1, 50));

        editBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        editBtn.setForeground(new java.awt.Color(33, 158, 33));
        editBtn.setText("EDIT CLIENT");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        jPanel1.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 440, 130, 50));

        exitBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(238, 13, 24));
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 510, 130, 50));

        clearBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(128, 13, 238));
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 130, 50));

        deleteBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(238, 13, 24));
        deleteBtn.setText("DELETE CLIENT");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 510, 160, 50));

        lblId.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblId.setForeground(java.awt.Color.white);
        lblId.setText("CLIENT'S NUMBER");
        lblId.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 250, 40));

        txtNationalId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNationalIdKeyPressed(evt);
            }
        });
        jPanel1.add(txtNationalId, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 260, 40));

        jLabel7.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("NATIONAL ID:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        printBtn.setBackground(new java.awt.Color(227, 43, 34));
        printBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        printBtn.setForeground(java.awt.Color.white);
        printBtn.setText("TABLE TO PDF");
        printBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBtnActionPerformed(evt);
            }
        });
        jPanel1.add(printBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 510, 150, 50));

        btnAllocate.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnAllocate.setForeground(new java.awt.Color(15, 37, 245));
        btnAllocate.setText("ALLOCATE ROOM");
        btnAllocate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllocateActionPerformed(evt);
            }
        });
        jPanel1.add(btnAllocate, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 510, 170, 50));

        printBtn1.setBackground(new java.awt.Color(44, 177, 55));
        printBtn1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        printBtn1.setForeground(java.awt.Color.white);
        printBtn1.setText("TABLE TO EXCEL");
        printBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBtn1ActionPerformed(evt);
            }
        });
        jPanel1.add(printBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 510, 160, 50));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 0, 80, 80));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 1210, 590));

        jLabel1.setFont(new java.awt.Font("Tibetan Machine Uni", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(45, 13, 249));
        jLabel1.setText("MANAGE CLIENTS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, -1, 50));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 720));

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
            pst=con.prepareStatement("delete from clients where id=?");
            pst.setString(1,id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Client Deleted Successfully from database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            Auto_id();
            txtNationalId.setText("");
            txtFirstName.setText("");
            txtLastName.setText("");
            txtPhone.setText("");
            txtEmail.setText("");
            txtNationalId.requestFocus();
            Client_table();
        } catch (SQLException ex) {
            Logger.getLogger(ManageClients.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to exit Hotel System?","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String id=lblId.getText();
        String nationalId=txtNationalId.getText();
        String firstName=txtFirstName.getText();
        String lastName=txtLastName.getText();
        String phone=txtPhone.getText();
        String email=txtEmail.getText();
        
        if(nationalId.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter your national id, null national ids cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtNationalId.requestFocus();
        }else if(firstName.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter your first name, null first names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtFirstName.requestFocus();
        }else if(lastName.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter your last name, null last names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtLastName.requestFocus();
        }else if(phone.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter your phone number, null phone numbers cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPhone.requestFocus();
        }else if(email.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter your email, null emails cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtEmail.requestFocus();
        }else{
        try {
            pst=con.prepareStatement("insert into clients (id,nationalId,firstName,lastName,phone,email)values(?,?,?,?,?,?)");
            pst.setString(1,id);
            pst.setString(2,nationalId);
            pst.setString(3,firstName);
            pst.setString(4,lastName);
            pst.setString(5,phone);
            pst.setString(6,email);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Client Added Successfully into database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            Auto_id();
            txtNationalId.setText("");
            txtFirstName.setText("");
            txtLastName.setText("");
            txtPhone.setText("");
            txtEmail.setText("");
            txtNationalId.requestFocus();
            Client_table();
        } catch (SQLException ex) {
            Logger.getLogger(ManageClients.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        txtNationalId.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtNationalId.requestFocus();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        String nationalId=txtNationalId.getText();
        String firstName=txtFirstName.getText();
        String lastName=txtLastName.getText();
        String phone=txtPhone.getText();
        String email=txtEmail.getText();
        String id=lblId.getText();
        
        if(nationalId.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter your national id, null national ids cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtNationalId.requestFocus();
        }else if(firstName.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter your first name, null first names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtFirstName.requestFocus();
        }else if(lastName.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter your last name, null last names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtLastName.requestFocus();
        }else if(phone.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter your phone number, null phone numbers cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPhone.requestFocus();
        }else if(email.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter your email, null emails cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtEmail.requestFocus();
        }else{
        try {
            pst=con.prepareStatement("update clients set nationalId=?, firstName=?, lastName=?, phone=?, email=? where id=?");
            pst.setString(1,nationalId);
            pst.setString(2,firstName);
            pst.setString(3,lastName);
            pst.setString(4,phone);
            pst.setString(5,email);
            pst.setString(6,id);
            pst.execute();
            JOptionPane.showMessageDialog(this,"Client Updated Successfully on database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            Auto_id();
            txtNationalId.setText("");
            txtFirstName.setText("");
            txtLastName.setText("");
            txtPhone.setText("");
            txtEmail.setText("");
            txtNationalId.requestFocus();
            Client_table();
        } catch (SQLException ex) {
            Logger.getLogger(ManageClients.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_editBtnActionPerformed
    
    private void txtFirstNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFirstNameKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtLastName.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtLastName.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtNationalId.requestFocus();
        }
    }//GEN-LAST:event_txtFirstNameKeyPressed

    private void txtLastNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLastNameKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtPhone.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtPhone.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtFirstName.requestFocus();
        }
    }//GEN-LAST:event_txtLastNameKeyPressed

    private void txtPhoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhoneKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtEmail.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtEmail.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtLastName.requestFocus();
        }
    }//GEN-LAST:event_txtPhoneKeyPressed

    private void txtEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String id=lblId.getText();
        String nationalId=txtNationalId.getText();
        String firstName=txtFirstName.getText();
        String lastName=txtLastName.getText();
        String phone=txtPhone.getText();
        String email=txtEmail.getText();
        
        if(nationalId.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter your national id, null national ids cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtNationalId.requestFocus();
        }else if(firstName.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter your first name, null first names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtFirstName.requestFocus();
        }else if(lastName.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter your last name, null last names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtLastName.requestFocus();
        }else if(phone.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter your phone number, null phone numbers cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPhone.requestFocus();
        }else if(email.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter your email, null emails cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtEmail.requestFocus();
        }else{
        try {
            pst=con.prepareStatement("insert into clients (id,nationalId,firstName,lastName,phone,email)values(?,?,?,?,?,?)");
            pst.setString(1,id);
            pst.setString(2,nationalId);
            pst.setString(3,firstName);
            pst.setString(4,lastName);
            pst.setString(5,phone);
            pst.setString(6,email);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Client Addent Successfully into database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            Auto_id();
            txtNationalId.setText("");
            txtFirstName.setText("");
            txtLastName.setText("");
            txtPhone.setText("");
            txtEmail.setText("");
            txtNationalId.requestFocus();
            Client_table();
        } catch (SQLException ex) {
            Logger.getLogger(ManageClients.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtNationalId.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtPhone.requestFocus();
        }
    }//GEN-LAST:event_txtEmailKeyPressed

    private void txtNationalIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNationalIdKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtFirstName.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtFirstName.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtEmail.requestFocus();
        }
    }//GEN-LAST:event_txtNationalIdKeyPressed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        DefaultTableModel model=(DefaultTableModel)table.getModel();
        int selectedIndex=table.getSelectedRow();
        lblId.setText(model.getValueAt(selectedIndex, 0).toString());
        txtNationalId.setText(model.getValueAt(selectedIndex, 1).toString());
        txtFirstName.setText(model.getValueAt(selectedIndex, 2).toString());
        txtLastName.setText(model.getValueAt(selectedIndex, 3).toString());
        txtPhone.setText(model.getValueAt(selectedIndex, 4).toString());
        txtEmail.setText(model.getValueAt(selectedIndex, 5).toString());
        
        editBtn.setEnabled(true);
        deleteBtn.setEnabled(true);
        btnAllocate.setEnabled(true);
    }//GEN-LAST:event_tableMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        btnAllocate.setEnabled(false);
    }//GEN-LAST:event_formWindowActivated

    private void btnAllocateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllocateActionPerformed
        this.setVisible(false);
        new AllocateRooms(lblUsername.getText(),txtNationalId.getText(),txtLastName.getText(),imag).setVisible(true);
    }//GEN-LAST:event_btnAllocateActionPerformed

    private void printBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBtnActionPerformed
        MessageFormat header=new MessageFormat("ROOM CLIENTS");
        MessageFormat footer=new MessageFormat("page {0}");
        try {
            table.print(JTable.PrintMode.FIT_WIDTH,header,footer);
        } catch (PrinterException ex) {
            Logger.getLogger(AllocateRooms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_printBtnActionPerformed

    private void printBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBtn1ActionPerformed
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
    }//GEN-LAST:event_printBtn1ActionPerformed

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
            java.util.logging.Logger.getLogger(ManageClients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageClients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageClients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageClients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageClients().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton btnAllocate;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JButton printBtn;
    private javax.swing.JButton printBtn1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtNationalId;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
