/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.system;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author bill
 */
public class User extends javax.swing.JFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    /**
     * Creates new form User
     */
    public User() {
        initComponents();
        Connect();
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("emmanuel-chigbo-jr7R01lOn1Y-unsplash.jpg")).getImage().getScaledInstance(1200, 680, Image.SCALE_SMOOTH)));
    }
    
    String username;
    BufferedImage imag;
    public User(String username, BufferedImage imag) {
        initComponents();
        Connect();
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(160, 170, Image.SCALE_SMOOTH)));
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("emmanuel-chigbo-jr7R01lOn1Y-unsplash.jpg")).getImage().getScaledInstance(1200, 680, Image.SCALE_SMOOTH)));
    }

    public void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/Hospital", "root", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
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
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        lblUserPhoto = new javax.swing.JLabel();
        btnSelect = new javax.swing.JButton();
        homeBtn = new javax.swing.JButton();
        clearBn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CREATE USER");
        setMaximizedBounds(new java.awt.Rectangle(1211, 700, 700, 700));
        setMaximumSize(new java.awt.Dimension(1211, 700));
        setMinimumSize(new java.awt.Dimension(1211, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(30, 86, 221));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("USERNAME:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("EMAIL:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("PHONE NUMBER:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("PASSWORD:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("PROFILE PHOTO:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 160, 170));

        lblUsername.setFont(new java.awt.Font("Arial Black", 3, 16)); // NOI18N
        lblUsername.setForeground(java.awt.Color.white);
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("USERNAME");
        jPanel1.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 200, 190, -1));

        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsernameKeyPressed(evt);
            }
        });
        jPanel1.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 32, 200, 40));

        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmailKeyPressed(evt);
            }
        });
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 82, 200, 40));

        txtPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPhoneKeyPressed(evt);
            }
        });
        jPanel1.add(txtPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 200, 40));

        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 200, 40));

        lblUserPhoto.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel1.add(lblUserPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, 100, 120));

        btnSelect.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnSelect.setText("SELECT");
        btnSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectActionPerformed(evt);
            }
        });
        jPanel1.add(btnSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 90, 50));

        homeBtn.setBackground(new java.awt.Color(154, 14, 239));
        homeBtn.setFont(new java.awt.Font("SansSerif", 3, 16)); // NOI18N
        homeBtn.setForeground(java.awt.Color.white);
        homeBtn.setText("<<HOME");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 110, 50));

        clearBn.setBackground(new java.awt.Color(14, 239, 239));
        clearBn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        clearBn.setForeground(java.awt.Color.white);
        clearBn.setText("CLEAR");
        clearBn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 390, 100, 50));

        addBtn.setBackground(new java.awt.Color(19, 170, 29));
        addBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        addBtn.setForeground(java.awt.Color.white);
        addBtn.setText("ADD");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 390, 100, 50));

        exitBtn.setBackground(new java.awt.Color(239, 14, 32));
        exitBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        exitBtn.setForeground(java.awt.Color.white);
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 390, 110, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 800, 460));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1200, 680));

        pack();
        setLocationRelativeTo(null);
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

    private void txtPhoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhoneKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtPassword.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtPassword.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtEmail.requestFocus();
        }
    }//GEN-LAST:event_txtPhoneKeyPressed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            btnSelect.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            btnSelect.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtPhone.requestFocus();
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    private String path1;
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

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        this.setVisible(false);
        new Home(lblUsername.getText(),imag).setVisible(true);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void clearBnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBnActionPerformed
        txtUsername.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtPassword.setText("");
        lblUserPhoto.setIcon(null);
    }//GEN-LAST:event_clearBnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String username=txtUsername.getText();
        String email=txtEmail.getText();
        String phone=txtPhone.getText();
        String password=txtPassword.getText();

        if(username.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter your username, null usernames cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtUsername.requestFocus();
        }else if(email.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter your email, null emails cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtEmail.requestFocus();
        }else if(phone.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter your phone number, null phone numbers cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtPhone.requestFocus();
        }else if(password.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter your password, null passwords cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtUsername.requestFocus();
        }else{
            byte[] rawBytes = null;
            FileInputStream fis = null;

            try {
                File fileObj = new File(path1);
                fis = new FileInputStream(fileObj);

                pst=con.prepareStatement("insert into users (username,email,phone,password,profile_pic) values(?,?,?,?,?)");

                int imageLength = Integer.parseInt(String.valueOf(fileObj.length()));
                rawBytes = new byte[imageLength];
                fis.read(rawBytes, 0, imageLength);
                pst.setString(1,username);
                pst.setString(2,email);
                pst.setString(3,phone);
                pst.setString(4,password);
                pst.setBytes(5, rawBytes);
                int count = pst.executeUpdate();
                if (count > 0) {
                    JOptionPane.showMessageDialog(this, "User Added Successfully into database","User Creation",JOptionPane.INFORMATION_MESSAGE);

                    txtUsername.setText("");
                    txtEmail.setText("");
                    txtPhone.setText("");
                    txtPassword.setText("");
                    lblUserPhoto.setIcon(null);
                    txtUsername.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(this, "An Error Occured while trying to send your details into the database, please try again","Error Message",JOptionPane.CANCEL_OPTION);
                }
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Do you want to exit system?","GYM Management System",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

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
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton btnSelect;
    private javax.swing.JButton clearBn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblUserPhoto;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}