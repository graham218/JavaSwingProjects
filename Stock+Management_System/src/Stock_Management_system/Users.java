/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Stock_Management_system;


import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
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
public class Users extends javax.swing.JFrame {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    String path1;
    /** Creates new form Users */
    public Users() {
        initComponents();
        Connect();
        jLabel11.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("pexels-rfstudio-4177708.jpg")).getImage().getScaledInstance(1220, 660, Image.SCALE_SMOOTH)));
    }
    
    String username;
    BufferedImage imag;
    public Users(String username, BufferedImage imag) {
        initComponents();
        Connect();
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(140, 150, Image.SCALE_SMOOTH)));
        
        jLabel11.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("pexels-rfstudio-4177708.jpg")).getImage().getScaledInstance(1220, 660, Image.SCALE_SMOOTH)));
    }
    
    public void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/Stock","root","");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
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
        txtUsername = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        lblUsername = new javax.swing.JLabel();
        profileBtn = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        clearBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        homeBtn = new javax.swing.JButton();
        profile = new javax.swing.JLabel();
        lblProfile = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CREATE USERS");
        setLocationByPlatform(true);
        setMaximizedBounds(new java.awt.Rectangle(1225, 673, 673, 673));
        setMinimumSize(new java.awt.Dimension(1225, 673));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(39, 197, 49));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("USERNAME:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, -1));

        jLabel3.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("EMAIL:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        jLabel4.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("PHONE NO:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, -1, -1));

        jLabel5.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("PROFILE PHOTO:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, -1, -1));

        jLabel6.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("PASSWORD:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, -1, -1));

        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });
        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsernameKeyPressed(evt);
            }
        });
        jPanel1.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 82, 270, 40));

        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmailKeyPressed(evt);
            }
        });
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 132, 270, 40));

        txtPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPhoneKeyPressed(evt);
            }
        });
        jPanel1.add(txtPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, 270, 40));

        lblUsername.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblUsername.setForeground(java.awt.Color.orange);
        lblUsername.setText("USERNAME");
        jPanel1.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 150, 140, 30));

        profileBtn.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        profileBtn.setText("Choose profile from gallery");
        profileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileBtnActionPerformed(evt);
            }
        });
        profileBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                profileBtnKeyPressed(evt);
            }
        });
        jPanel1.add(profileBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, 270, 50));

        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 290, 270, 40));

        clearBtn.setBackground(new java.awt.Color(240, 17, 165));
        clearBtn.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        clearBtn.setForeground(java.awt.Color.white);
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, 130, 50));

        addBtn.setBackground(new java.awt.Color(19, 39, 253));
        addBtn.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        addBtn.setForeground(java.awt.Color.white);
        addBtn.setText("ADD USER");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 350, 150, 50));

        exitBtn.setBackground(new java.awt.Color(237, 38, 22));
        exitBtn.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        exitBtn.setForeground(java.awt.Color.white);
        exitBtn.setText("EXIT SYSTEM");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 350, 160, 50));

        homeBtn.setBackground(new java.awt.Color(12, 21, 231));
        homeBtn.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        homeBtn.setForeground(java.awt.Color.white);
        homeBtn.setText("HOME");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 120, 50));

        profile.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        profile.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 170, 140, 160));

        lblProfile.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 0, 140, 150));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 960, 440));

        jLabel8.setFont(new java.awt.Font("SansSerif", 3, 48)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(37, 36, 222));
        jLabel8.setText("CREATE NEW USERS");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 580, -1));
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 6, 1220, 660));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Do you want to exit System?","Stock Management System",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

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
                
                pst=con.prepareStatement("insert into users (username,email,phone,profile_pic,password) values(?,?,?,?,?)");
                int imageLength = Integer.parseInt(String.valueOf(fileObj.length()));  
                rawBytes = new byte[imageLength];
                fis.read(rawBytes, 0, imageLength);
                pst.setString(1,username);
                pst.setString(2,email);
                pst.setString(3,phone);
                pst.setBytes(4, rawBytes);
                pst.setString(5,password);
                int count = pst.executeUpdate();
                if (count > 0) {
                    JOptionPane.showMessageDialog(this, "User Added Successfully into database","User Creation",JOptionPane.INFORMATION_MESSAGE);
                    
                    txtUsername.setText("");
                    txtEmail.setText("");
                    txtPhone.setText("");
                    txtPassword.setText("");
                    lblProfile.setIcon(null);
                    txtUsername.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(this, "An Error Occured while trying to send your details into the database, please try again","Error Message",JOptionPane.CANCEL_OPTION);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }

            }
    }//GEN-LAST:event_addBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        txtUsername.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        lblProfile.setIcon(null);
        txtPassword.setText("");
        txtUsername.requestFocus();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        this.setVisible(false);
        new Home_Page(lblUsername.getText(),imag).setVisible(true);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void profileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileBtnActionPerformed
        JFileChooser filechooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png", "gif","jpeg");
        filechooser.addChoosableFileFilter(filter);
        int result = filechooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = filechooser.getSelectedFile();
            String path = file.getAbsolutePath();
            profile.setIcon(new ImageIcon(new javax.swing.ImageIcon(path).getImage().getScaledInstance(140, 160, Image.SCALE_SMOOTH)));
            path1 = path;
        }
    }//GEN-LAST:event_profileBtnActionPerformed

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

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
            profileBtn.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            profileBtn.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtEmail.requestFocus();
        }
    }//GEN-LAST:event_txtPhoneKeyPressed

    private void profileBtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_profileBtnKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtPassword.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtPassword.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtPhone.requestFocus();
        }
    }//GEN-LAST:event_profileBtnKeyPressed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
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
                
                pst=con.prepareStatement("insert into users (username,email,phone,profile_pic,password) values(?,?,?,?,?)");
                int imageLength = Integer.parseInt(String.valueOf(fileObj.length()));  
                rawBytes = new byte[imageLength];
                fis.read(rawBytes, 0, imageLength);
                pst.setString(1,username);
                pst.setString(2,email);
                pst.setString(3,phone);
                pst.setBytes(4, rawBytes);
                pst.setString(5,password);
                int count = pst.executeUpdate();
                if (count > 0) {
                    JOptionPane.showMessageDialog(this, "User Added Successfully into database","User Creation",JOptionPane.INFORMATION_MESSAGE);
                    
                    txtUsername.setText("");
                    txtEmail.setText("");
                    txtPhone.setText("");
                    txtPassword.setText("");
                    lblProfile.setIcon(null);
                    txtUsername.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(this, "An Error Occured while trying to send your details into the database, please try again","Error Message",JOptionPane.CANCEL_OPTION);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }

            }
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtUsername.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtUsername.requestFocus();
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

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
            java.util.logging.Logger.getLogger(Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Users().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel profile;
    private javax.swing.JButton profileBtn;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

}
