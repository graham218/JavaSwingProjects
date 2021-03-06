/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.system;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
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
import javax.swing.JOptionPane;

/**
 *
 * @author bill
 */
public class Login extends javax.swing.JFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        Connect();
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("emmanuel-chigbo-jr7R01lOn1Y-unsplash.jpg")).getImage().getScaledInstance(1200, 680, Image.SCALE_SMOOTH)));
    }

    public void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/Hospital", "root", "");
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
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximizedBounds(new java.awt.Rectangle(1211, 700, 700, 700));
        setMaximumSize(new java.awt.Dimension(1211, 700));
        setMinimumSize(new java.awt.Dimension(1211, 700));
        setPreferredSize(new java.awt.Dimension(1211, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(30, 86, 221));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("USERNAME:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("PASSWORD:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsernameKeyPressed(evt);
            }
        });
        jPanel1.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 32, 200, 40));

        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 200, 40));

        clearBn.setBackground(new java.awt.Color(14, 239, 239));
        clearBn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        clearBn.setForeground(java.awt.Color.white);
        clearBn.setText("CLEAR");
        clearBn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBn, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 100, 50));

        addBtn.setBackground(new java.awt.Color(19, 170, 29));
        addBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        addBtn.setForeground(java.awt.Color.white);
        addBtn.setText("LOGIN");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 100, 50));

        exitBtn.setBackground(new java.awt.Color(239, 14, 32));
        exitBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        exitBtn.setForeground(java.awt.Color.white);
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, 110, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, 530, 310));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1200, 680));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
                    byte[] imageData =null;
                    pst=con.prepareStatement("SELECT * from users where username=? and password=?");
                    pst.setString(1,username);
                    pst.setString(2,password);
                    rs=pst.executeQuery();
                    if(rs.next()){
                        Blob img=rs.getBlob("profile_pic");
                        imageData=img.getBytes(1,(int)img.length());
                        BufferedImage imag=ImageIO.read(new ByteArrayInputStream(imageData));
                        JOptionPane.showMessageDialog(this,"Logged in Successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
                        this.setVisible(false);
                        new Home(txtUsername.getText(),imag).setVisible(true);
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
                byte[] imageData =null;
                pst=con.prepareStatement("SELECT * from users where username=? and password=?");
                pst.setString(1,username);
                pst.setString(2,password);
                rs=pst.executeQuery();
                if(rs.next()){
                    Blob img=rs.getBlob("profile_pic");
                    imageData=img.getBytes(1,(int)img.length());
                    BufferedImage imag=ImageIO.read(new ByteArrayInputStream(imageData));
                    JOptionPane.showMessageDialog(this,"Logged in Successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
                    this.setVisible(false);
                    new Home(txtUsername.getText(),imag).setVisible(true);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton clearBn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
