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
public class Login extends javax.swing.JFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    /**
     * Creates new form User
     */
    public Login() {
        initComponents();
        Connect();
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("social-media-3758364_1920.jpg")).getImage().getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), Image.SCALE_SMOOTH)));
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
        combUserType = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        checkShowPass = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("USER LOGIN");
        setMaximizedBounds(new java.awt.Rectangle(1211, 700, 700, 700));
        setMinimumSize(new java.awt.Dimension(1211, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(30, 86, 221));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("USERNAME:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("PASSWORD:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, -1, -1));

        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsernameKeyPressed(evt);
            }
        });
        jPanel1.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 230, 40));

        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 230, 40));

        clearBn.setBackground(new java.awt.Color(14, 239, 239));
        clearBn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        clearBn.setForeground(java.awt.Color.white);
        clearBn.setText("CLEAR");
        clearBn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 100, 50));

        addBtn.setBackground(new java.awt.Color(19, 170, 29));
        addBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        addBtn.setForeground(java.awt.Color.white);
        addBtn.setText("LOGIN");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, 100, 50));

        exitBtn.setBackground(new java.awt.Color(239, 14, 32));
        exitBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        exitBtn.setForeground(java.awt.Color.white);
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 240, 110, 50));

        combUserType.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        combUserType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT USER TYPE", "ADMIN", "STORE KEEPER", "HOTEL CASHIER", "KITCHEN DPT", "FINANCE DPT", "SECURITY", "OTHER" }));
        combUserType.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                combUserTypeKeyPressed(evt);
            }
        });
        jPanel1.add(combUserType, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 230, 40));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("USER TYPE:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 120, -1));

        checkShowPass.setText("show password");
        checkShowPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkShowPassActionPerformed(evt);
            }
        });
        jPanel1.add(checkShowPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 690, 350));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jLabel1.setText("USER LOGIN");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 320, 50));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 700));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void clearBnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBnActionPerformed
        txtUsername.setText("");
        combUserType.getModel().setSelectedItem("SELECT USER TYPE");
        txtPassword.setText("");
    }//GEN-LAST:event_clearBnActionPerformed

    private int attempt=0;
    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String username=txtUsername.getText();
        String userType=combUserType.getModel().getSelectedItem().toString();
        String password=txtPassword.getText();
        
        if(username.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter your username, null usernames cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtUsername.requestFocus();
        }else if(userType.trim().equals("SELECT USER TYPE")){
            JOptionPane.showMessageDialog(this, "Please select your user type, null user types cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            combUserType.requestFocus();
        }else if(password.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter your password, null passwords cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtUsername.requestFocus();
        }else{
            try {
                byte[] imageData =null;
                pst=con.prepareStatement("SELECT * from users where username=? and userType=? and password=?");
                pst.setString(1,username);            
                pst.setString(2,userType);
                pst.setString(3,password);
                rs=pst.executeQuery();
                if(rs.next()){
                    Blob img=rs.getBlob("profile_pic");
                    imageData=img.getBytes(1,(int)img.length());
                    BufferedImage imag=ImageIO.read(new ByteArrayInputStream(imageData));
                    JOptionPane.showMessageDialog(this,"Logged in Successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
                    this.setVisible(false);
                    new Main(txtUsername.getText(),imag,userType).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(this, "Incorrect username, user type or password","Login Error!!",JOptionPane.CANCEL_OPTION);
                    attempt++;
                    if(attempt>=3){
                      JOptionPane.showMessageDialog(this, "You are not authorised to log into this system, please contact the admin","Login Error!!",JOptionPane.CANCEL_OPTION);
                      System.exit(0);
                    }
                    txtUsername.setText("");
                    combUserType.getModel().setSelectedItem("SELECT USER TYPE");
                    txtPassword.setText("");
                    txtUsername.requestFocus();
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
            combUserType.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            combUserType.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtPassword.requestFocus();
        }
    }//GEN-LAST:event_txtUsernameKeyPressed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        String username=txtUsername.getText();
        String userType=combUserType.getModel().getSelectedItem().toString();
        String password=txtPassword.getText();
        
        if(username.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter your username, null usernames cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtUsername.requestFocus();
        }else if(userType.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please select your user type, null user types cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            combUserType.requestFocus();
        }else if(password.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter your password, null passwords cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtUsername.requestFocus();
        }else{
            try {
                byte[] imageData =null;
                pst=con.prepareStatement("SELECT * from users where username=? and userType=? and password=?");
                pst.setString(1,username);            
                pst.setString(2,userType);
                pst.setString(3,password);
                rs=pst.executeQuery();
                if(rs.next()){
                    Blob img=rs.getBlob("profile_pic");
                    imageData=img.getBytes(1,(int)img.length());
                    BufferedImage imag=ImageIO.read(new ByteArrayInputStream(imageData));
                    JOptionPane.showMessageDialog(this,"Logged in Successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
                    this.setVisible(false);
                    new Main(txtUsername.getText(),imag,userType).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(this, "Incorrect username, user type or password","Login Error!!",JOptionPane.CANCEL_OPTION);
                    attempt++;
                    if(attempt>=3){
                      JOptionPane.showMessageDialog(this, "You are not authorised to log into this system, please contact the admin","Login Error!!",JOptionPane.CANCEL_OPTION);
                      System.exit(0);
                    }
                    txtUsername.setText("");
                    combUserType.getModel().setSelectedItem("SELECT USER TYPE");
                    txtPassword.setText("");
                    txtUsername.requestFocus();
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtUsername.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtUsername.requestFocus();
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void combUserTypeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_combUserTypeKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtPassword.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtPassword.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtUsername.requestFocus();
        }
    }//GEN-LAST:event_combUserTypeKeyPressed

    private void checkShowPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkShowPassActionPerformed
        if(checkShowPass.isSelected()){
            txtPassword.setEchoChar((char)0);
        }else{
            txtPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_checkShowPassActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JCheckBox checkShowPass;
    private javax.swing.JButton clearBn;
    private javax.swing.JComboBox<String> combUserType;
    private javax.swing.JButton exitBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}