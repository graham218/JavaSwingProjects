/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelmanagement.system;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
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
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author bill
 */
public class EditUsers1 extends javax.swing.JPanel {

    PreparedStatement pst;
    ResultSet rs;
    Database base=new Database();
    Connection con=base.myCon();
    private static String path1;
    /**
     * Creates new form EditUsers1
     */
    public EditUsers1() {
        initComponents();
    }
    
    String username;
    BufferedImage imag;
    String userType;
    public EditUsers1(String username, BufferedImage imag, String userType) {
        initComponents();
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        this.userType=userType;
        lblUserType.setText(userType);
        users_table();
    }
    
    
    public void users_table(){
        try {
            pst=con.prepareStatement("select * from users");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i <= c; i++) {
                    v.add(rs.getInt("id"));
                    v.add(rs.getString("username1"));
                    v.add(rs.getString("email"));
                    v.add(rs.getString("phone"));
                    v.add(rs.getBlob("profile_pic"));
                    v.add(rs.getString("userType"));
                    v.add(rs.getString("password"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EditUsers1.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel6 = new javax.swing.JLabel();
        lblProfile = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        lblUserPhoto = new javax.swing.JLabel();
        btnSelect = new javax.swing.JButton();
        clearBn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        deleteBtn = new javax.swing.JButton();
        editPhotoBtn = new javax.swing.JButton();
        lblUsername = new javax.swing.JLabel();
        lblUserType = new javax.swing.JLabel();
        combUserType = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1140, 600));
        setMinimumSize(new java.awt.Dimension(1140, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(30, 86, 221));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Serif", 3, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("ID:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Serif", 3, 18)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("EMAIL:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel4.setFont(new java.awt.Font("Serif", 3, 18)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("PHONE NUMBER:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        jLabel5.setFont(new java.awt.Font("Serif", 3, 18)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("PASSWORD");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));

        jLabel6.setFont(new java.awt.Font("Serif", 3, 18)); // NOI18N
        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("PROFILE PHOTO:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 0, 80, 80));

        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsernameKeyPressed(evt);
            }
        });
        jPanel1.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 200, 40));

        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmailKeyPressed(evt);
            }
        });
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 200, 40));

        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 200, 40));

        lblUserPhoto.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel1.add(lblUserPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, 100, 120));

        btnSelect.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnSelect.setText("SELECT");
        btnSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectActionPerformed(evt);
            }
        });
        btnSelect.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSelectKeyPressed(evt);
            }
        });
        jPanel1.add(btnSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 90, 50));

        clearBn.setBackground(new java.awt.Color(14, 239, 239));
        clearBn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        clearBn.setForeground(java.awt.Color.white);
        clearBn.setText("CLEAR");
        clearBn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 90, 50));

        editBtn.setBackground(new java.awt.Color(19, 170, 29));
        editBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        editBtn.setForeground(java.awt.Color.white);
        editBtn.setText("EDIT");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        jPanel1.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 460, 100, 50));

        exitBtn.setBackground(new java.awt.Color(239, 14, 32));
        exitBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        exitBtn.setForeground(java.awt.Color.white);
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 460, 120, 50));

        jLabel7.setFont(new java.awt.Font("Serif", 3, 18)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("USERNAME:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        lblId.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        lblId.setForeground(java.awt.Color.white);
        lblId.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 200, 40));

        txtPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPhoneKeyPressed(evt);
            }
        });
        jPanel1.add(txtPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, 200, 40));

        table.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(243, 179, 5), 2, true));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "USERNAME", "EMAIL", "PHONE", "PROFILE PHOTO", "USER TYPE", "PASSWORD"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, 670, 360));

        deleteBtn.setBackground(new java.awt.Color(233, 55, 5));
        deleteBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        deleteBtn.setForeground(java.awt.Color.white);
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 460, 100, 50));

        editPhotoBtn.setBackground(new java.awt.Color(19, 170, 29));
        editPhotoBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        editPhotoBtn.setForeground(java.awt.Color.white);
        editPhotoBtn.setText("EDIT PHOTO");
        editPhotoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPhotoBtnActionPerformed(evt);
            }
        });
        jPanel1.add(editPhotoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 460, 140, 50));

        lblUsername.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblUsername.setForeground(java.awt.Color.white);
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("USERNAME");
        jPanel1.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 40, 180, -1));

        lblUserType.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblUserType.setForeground(java.awt.Color.white);
        lblUserType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserType.setText("USER TYPE");
        jPanel1.add(lblUserType, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 10, 220, -1));

        combUserType.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        combUserType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT USER TYPE", "ADMIN", "STORE KEEPER", "HOTEL CASHIER", "KITCHEN DPT", "FINANCE DPT", "SECURITY", "OTHER", " ", " " }));
        combUserType.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                combUserTypeKeyPressed(evt);
            }
        });
        jPanel1.add(combUserType, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 350, 200, 40));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel8.setForeground(java.awt.Color.white);
        jLabel8.setText("USER TYPE:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 120, -1));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 150, -1));

        btnSearch.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        btnSearch.setText("SEARCH WITH USERNAME");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 210, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 1100, 540));

        jLabel1.setFont(new java.awt.Font("Tibetan Machine Uni", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(31, 154, 28));
        jLabel1.setText("VIEW AND UPDATE USERS");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, -1, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtEmail.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtEmail.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtPassword.requestFocus();
        }
    }//GEN-LAST:event_txtUsernameKeyPressed

    private void txtEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtPhone.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtPhone.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtUsername.requestFocus();
        }
    }//GEN-LAST:event_txtEmailKeyPressed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtUsername.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtUsername.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            combUserType.requestFocus();
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void btnSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectActionPerformed
        JFileChooser filechooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png", "gif","jpeg");
        filechooser.addChoosableFileFilter(filter);
        int result = filechooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = filechooser.getSelectedFile();
            String path = file.getAbsolutePath();
            lblUserPhoto.setIcon(new ImageIcon(new javax.swing.ImageIcon(path).getImage().getScaledInstance(100, 120, Image.SCALE_SMOOTH)));
            path1 = path;
        }
    }//GEN-LAST:event_btnSelectActionPerformed

    private void btnSelectKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSelectKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            combUserType.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            combUserType.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtPhone.requestFocus();
        }
    }//GEN-LAST:event_btnSelectKeyPressed

    private void clearBnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBnActionPerformed
        txtUsername.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
        txtPhone.setText("");
        lblId.setText("");
        lblUserPhoto.setIcon(null);
        txtUsername.requestFocus();
    }//GEN-LAST:event_clearBnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        int id=Integer.parseInt(lblId.getText());
        String username=txtUsername.getText();
        String email=txtEmail.getText();
        String phone=txtPhone.getText();
        String userType=combUserType.getModel().getSelectedItem().toString();
        String password=txtPassword.getText();

        if(username.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter username, null usernames cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtUsername.requestFocus();
        }else if(email.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter email, null emails cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtEmail.requestFocus();
        }else if(phone.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter phone number, null phone numbers cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtPhone.requestFocus();
        }else if(userType.trim().equals("SELECT USER TYPE")){
            JOptionPane.showMessageDialog(this, "Please select user's type, null user types cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            combUserType.requestFocus();
        }else if(password.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter password, null passwords cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtPassword.requestFocus();
        }else{
            try {
                pst=con.prepareStatement("update users set username1=?,email=?,phone=?,userType=?,password=? where id=?");
                pst.setString(1,username);
                pst.setString(2,email);
                pst.setString(3,phone);
                pst.setString(4,userType);
                pst.setString(5,password);
                pst.setInt(6,id);
                int count = pst.executeUpdate();
                if (count > 0) {
                    JOptionPane.showMessageDialog(this, "User Updated Successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
                    users_table();
                    txtUsername.setText("");
                    txtEmail.setText("");
                    txtPassword.setText("");
                    txtPhone.setText("");
                    lblId.setText("");
                    lblUserPhoto.setIcon(null);
                    txtUsername.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(this, "An Error Occured while trying to send your details into the database, please try again","Error Message",JOptionPane.CANCEL_OPTION);
                }
            } catch (SQLException ex) {
                Logger.getLogger(EditUsers1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Do you want to exit system?","GYM Management System",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private void txtPhoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhoneKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            btnSelect.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            btnSelect.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtEmail.requestFocus();
        }
    }//GEN-LAST:event_txtPhoneKeyPressed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        DefaultTableModel model=(DefaultTableModel)table.getModel();
        int selectedIndex=table.getSelectedRow();

        lblId.setText(model.getValueAt(selectedIndex, 0).toString());
        txtUsername.setText(model.getValueAt(selectedIndex, 1).toString());
        txtEmail.setText(model.getValueAt(selectedIndex, 2).toString());
        txtPhone.setText(model.getValueAt(selectedIndex, 3).toString());
        Blob img=(Blob) model.getValueAt(selectedIndex, 4);
        byte[] imageData;
        try {
            imageData = img.getBytes(1,(int)img.length());
            BufferedImage imag=ImageIO.read(new ByteArrayInputStream(imageData));
            lblUserPhoto.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(100, 120, Image.SCALE_SMOOTH)));
        } catch (SQLException ex) {
            Logger.getLogger(EditUsers1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EditUsers1.class.getName()).log(Level.SEVERE, null, ex);
        }
        combUserType.getModel().setSelectedItem(model.getValueAt(selectedIndex, 5).toString());
        txtPassword.setText(model.getValueAt(selectedIndex, 6).toString());
    }//GEN-LAST:event_tableMouseClicked

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        int id=Integer.parseInt(lblId.getText());
        String username=txtUsername.getText();
        String email=txtEmail.getText();
        String phone=txtPhone.getText();
        String userType=combUserType.getModel().getSelectedItem().toString();
        String password=txtPassword.getText();

        if(username.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter username, null usernames cannot be deleted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtUsername.requestFocus();
        }else if(email.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter email, null emails cannot be deleted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtEmail.requestFocus();
        }else if(phone.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter phone number, null phone numbers cannot be deleted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtPhone.requestFocus();
        }else if(userType.trim().equals("SELECT USER TYPE")){
            JOptionPane.showMessageDialog(this, "Please select user's type, null user types cannot be deleted","Submission Error",JOptionPane.CANCEL_OPTION);
            combUserType.requestFocus();
        }else if(password.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter password, null passwords cannot be deleted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtPassword.requestFocus();
        }else{
            try {
                pst=con.prepareStatement("delete from users where id=?");
                pst.setInt(1, id);
                pst.executeUpdate();
                users_table();
                JOptionPane.showMessageDialog(this,"User deleted Successfully from database","Success Message",JOptionPane.INFORMATION_MESSAGE);
                txtUsername.setText("");
                txtEmail.setText("");
                txtPassword.setText("");
                txtPhone.setText("");
                lblId.setText("");
                lblUserPhoto.setIcon(null);
                txtUsername.requestFocus();

            } catch (SQLException ex) {
                Logger.getLogger(EditUsers1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void editPhotoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPhotoBtnActionPerformed
        byte[] rawBytes = null;
        FileInputStream fis = null;
        try {
            File fileObj = new File(path1);
            fis = new FileInputStream(fileObj);
            pst=con.prepareStatement("update users set profile_pic=? where id=?");
            int imageLength = Integer.parseInt(String.valueOf(fileObj.length()));
            rawBytes = new byte[imageLength];
            fis.read(rawBytes, 0, imageLength);
            pst.setBytes(1, rawBytes);
            pst.setString(2, lblId.getText());
            pst.execute();
            users_table();
            JOptionPane.showMessageDialog(this,"User's photo updated Successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
            txtUsername.setText("");
            txtEmail.setText("");
            txtPassword.setText("");
            txtPhone.setText("");
            lblId.setText("");
            lblUserPhoto.setIcon(null);
            txtUsername.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(EditUsers1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EditUsers1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EditUsers1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_editPhotoBtnActionPerformed

    private void combUserTypeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_combUserTypeKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtPassword.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtPassword.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnSelect.requestFocus();
        }
    }//GEN-LAST:event_combUserTypeKeyPressed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String user_name=txtSearch.getText();
        try {
            pst=con.prepareStatement("select * from users where username LIKE CONCAT('%',?,'%')");
            pst.setString(1, user_name);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i <= c; i++) {
                    v.add(rs.getInt("id"));
                    v.add(rs.getString("username1"));
                    v.add(rs.getString("email"));
                    v.add(rs.getString("phone"));
                    v.add(rs.getBlob("profile_pic"));
                    v.add(rs.getString("userType"));
                    v.add(rs.getString("password"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EditUsers1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        String user_name=txtSearch.getText();
        try {
            pst=con.prepareStatement("select * from users where username LIKE CONCAT('%',?,'%')");
            pst.setString(1, user_name);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i <= c; i++) {
                    v.add(rs.getInt("id"));
                    v.add(rs.getString("username1"));
                    v.add(rs.getString("email"));
                    v.add(rs.getString("phone"));
                    v.add(rs.getBlob("profile_pic"));
                    v.add(rs.getString("userType"));
                    v.add(rs.getString("password"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EditUsers1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnSearch;
    private javax.swing.JButton btnSelect;
    private javax.swing.JButton clearBn;
    private javax.swing.JComboBox<String> combUserType;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton editPhotoBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblUserPhoto;
    private javax.swing.JLabel lblUserType;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}