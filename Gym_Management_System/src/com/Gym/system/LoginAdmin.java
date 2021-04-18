/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gym.system;

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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author bill
 */
public class LoginAdmin extends javax.swing.JFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    /**
     * Creates new form User
     */
    public LoginAdmin() {
        initComponents();
        Connect();
        //lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("emmanuel-chigbo-jr7R01lOn1Y-unsplash.jpg")).getImage().getScaledInstance(1200, 680, Image.SCALE_SMOOTH)));
    }
    
    String username;
    BufferedImage imag;
    public LoginAdmin(String username, BufferedImage imag) {
        initComponents();
        Connect();
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(120, 140, Image.SCALE_SMOOTH)));
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("sergio-pedemonte-YA-9Ut5B03M-unsplash.jpg")).getImage().getScaledInstance(1200, 680, Image.SCALE_SMOOTH)));
    }

    public void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/gym", "root", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel5 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        clearBn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        lblProfile = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        homeBtn = new javax.swing.JButton();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADD USERS");
        setMaximizedBounds(new java.awt.Rectangle(1211, 700, 700, 700));
        setMinimumSize(new java.awt.Dimension(1211, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(30, 86, 221));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("USERNAME:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("PASSWORD:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, -1, -1));

        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsernameKeyPressed(evt);
            }
        });
        jPanel1.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 200, 40));

        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 200, 40));

        clearBn.setBackground(new java.awt.Color(14, 239, 239));
        clearBn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        clearBn.setForeground(java.awt.Color.white);
        clearBn.setText("CLEAR");
        clearBn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBn, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 100, 50));

        addBtn.setBackground(new java.awt.Color(19, 170, 29));
        addBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        addBtn.setForeground(java.awt.Color.white);
        addBtn.setText("LOGIN");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 100, 50));

        exitBtn.setBackground(new java.awt.Color(239, 14, 32));
        exitBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        exitBtn.setForeground(java.awt.Color.white);
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 200, 110, 50));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 120, 140));

        lblUsername.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblUsername.setForeground(java.awt.Color.white);
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("USERNAME");
        jPanel1.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 170, 140, -1));

        homeBtn.setBackground(new java.awt.Color(61, 162, 23));
        homeBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        homeBtn.setForeground(java.awt.Color.white);
        homeBtn.setText("HOME");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 100, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, 670, 330));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1200, 680));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void clearBnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBnActionPerformed
        txtUsername.setText("");
        txtPassword.setText("");
    }//GEN-LAST:event_clearBnActionPerformed

    private int attempt=0;
    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String username=txtUsername.getText();
        String password=txtPassword.getText();
        
        if(username.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter your username, null usernames cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtUsername.requestFocus();
        }else if(password.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter your password, null passwords cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtUsername.requestFocus();
        }else{
            try {
                pst=con.prepareStatement("SELECT * from admins where username=? and password=?");
                pst.setString(1,username);            
                pst.setString(2,password);
                rs=pst.executeQuery();
                if(rs.next()){
                    JOptionPane.showMessageDialog(this,"Logged in Successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
                    this.setVisible(false);
                    new EditUsers(lblUsername.getText(),imag).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(this, "Incorrect username or password","Login Error!!",JOptionPane.CANCEL_OPTION);
                    attempt++;
                    if(attempt>=3){
                      JOptionPane.showMessageDialog(this, "You are not authorised to log into this system, please contact the admin","Login Error!!",JOptionPane.CANCEL_OPTION);
                      System.exit(0);
                    }
                    txtUsername.setText("");
                    txtPassword.setText("");
                    txtUsername.requestFocus();
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(LoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Do you want to exit system?","GYM Management System",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private String path1;
    private void txtUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtPassword.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtPassword.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtPassword.requestFocus();
        }
    }//GEN-LAST:event_txtUsernameKeyPressed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String username=txtUsername.getText();
        String password=txtPassword.getText();
        
        if(username.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter your username, null usernames cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtUsername.requestFocus();
        }else if(password.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter your password, null passwords cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtUsername.requestFocus();
        }else{
            try {
                pst=con.prepareStatement("SELECT * from admins where username=? and password=?");
                pst.setString(1,username);            
                pst.setString(2,password);
                rs=pst.executeQuery();
                if(rs.next()){
                    JOptionPane.showMessageDialog(this,"Logged in Successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
                    this.setVisible(false);
                    new EditUsers(lblUsername.getText(),imag).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(this, "Incorrect username or password","Login Error!!",JOptionPane.CANCEL_OPTION);
                    attempt++;
                    if(attempt>=3){
                      JOptionPane.showMessageDialog(this, "You are not authorised to log into this system, please contact the admin","Login Error!!",JOptionPane.CANCEL_OPTION);
                      System.exit(0);
                    }
                    txtUsername.setText("");
                    txtPassword.setText("");
                    txtUsername.requestFocus();
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(LoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtUsername.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtUsername.requestFocus();
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        this.setVisible(false);
        new Home(lblUsername.getText(),imag).setVisible(true);
    }//GEN-LAST:event_homeBtnActionPerformed

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
            java.util.logging.Logger.getLogger(LoginAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton clearBn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
