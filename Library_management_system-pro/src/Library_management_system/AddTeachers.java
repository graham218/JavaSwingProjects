/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library_management_system;

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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author USER
 */
public class AddTeachers extends javax.swing.JFrame {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    String path1;
    /**
     * Creates new form CreateAccount
     */
    public AddTeachers() {
        initComponents();
        Connection();
    }

    String username;
    BufferedImage imag;
    public AddTeachers(String username,BufferedImage imag) {
        initComponents();
        Connection();
        this.username=username;
        this.imag=imag;
        lblUsername.setText(username);
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(lblProfile.getWidth(), lblProfile.getHeight(), Image.SCALE_SMOOTH)));
        AutoID();
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("../Images/caleb-woods-fulXJYIvRi8-unsplash.jpg")).getImage().getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), Image.SCALE_SMOOTH)));
    }
    public void Connection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/library","root","");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddTeachers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddTeachers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AutoID(){
        try {
            Statement s=con.createStatement();
            rs=s.executeQuery("select MAX(teacherId) from teachers");
            rs.next();
            rs.getString("MAX(teacherId)");
            if(rs.getString("MAX(teacherId)")==null){
                lblTeacherI.setText("TR001");
            }else{
                long id=Long.parseLong(rs.getString("MAX(teacherId)").substring(2,rs.getString("MAX(teacherId)").length()));
                id++;
                lblTeacherI.setText("TR"+String.format("%03d", id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddBooks.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();
        txtNationalID = new javax.swing.JTextField();
        btnSelect = new javax.swing.JButton();
        lblProfile = new javax.swing.JLabel();
        homeBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        lblPhoto = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblTeacherI = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADD TEACHERS");
        setMaximizedBounds(new java.awt.Rectangle(1200, 700, 700, 700));
        setMinimumSize(new java.awt.Dimension(1200, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("AUTO ID:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 120, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("NATIONAL ID:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 180, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("FULL NAME:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, 150, -1));

        lblUsername.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("USERNAME");
        jPanel1.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 130, 120, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("PROFILE PHOTO:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, 160, -1));

        txtFullName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFullNameKeyPressed(evt);
            }
        });
        jPanel1.add(txtFullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, 210, 30));

        txtNationalID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNationalIDKeyPressed(evt);
            }
        });
        jPanel1.add(txtNationalID, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 210, 30));

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
        jPanel1.add(btnSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 80, 40));

        lblProfile.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, 100, 110));

        homeBtn.setBackground(new java.awt.Color(0, 102, 102));
        homeBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        homeBtn.setForeground(new java.awt.Color(255, 255, 255));
        homeBtn.setText("HOME");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 360, 110, 40));

        clearBtn.setBackground(new java.awt.Color(0, 102, 0));
        clearBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(255, 255, 255));
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 360, 100, 40));

        addBtn.setBackground(new java.awt.Color(102, 0, 102));
        addBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        addBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBtn.setText("ADD");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 360, 90, 40));

        exitBtn.setBackground(new java.awt.Color(255, 0, 0));
        exitBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(255, 255, 255));
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 360, 100, 40));

        lblPhoto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        jPanel1.add(lblPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 160, 100, 110));

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("PHONE NUMBER:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 280, 170, -1));

        lblTeacherI.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblTeacherI.setForeground(new java.awt.Color(255, 255, 255));
        lblTeacherI.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblTeacherI, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 210, 30));

        txtPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPhoneKeyPressed(evt);
            }
        });
        jPanel1.add(txtPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, 210, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 1040, 570));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 204));
        jLabel9.setText("ADD TEACHERS");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 560, -1));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 700));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        txtNationalID.setText("");
        txtFullName.setText("");
        txtPhone.setText("");
        lblPhoto.setIcon(null);
        txtNationalID.requestFocus();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        this.setVisible(false);
        new Home(lblUsername.getText(),imag).setVisible(true);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Do yo want to exit system","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private void txtNationalIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNationalIDKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtFullName.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtFullName.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtPhone.requestFocus();
        } 
    }//GEN-LAST:event_txtNationalIDKeyPressed

    private void txtFullNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFullNameKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            btnSelect.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            btnSelect.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtNationalID.requestFocus();
        }
    }//GEN-LAST:event_txtFullNameKeyPressed

    private void btnSelectKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSelectKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtPhone.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtPhone.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtFullName.requestFocus();
        }
    }//GEN-LAST:event_btnSelectKeyPressed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String teacherId=lblTeacherI.getText();
        String nationalId=txtNationalID.getText();
        String fullName=txtFullName.getText();
        String phone=txtPhone.getText();
        
        byte[] rawBytes=null;
        FileInputStream fis=null;
        
        if(nationalId.trim().equals("")){
                JOptionPane.showMessageDialog(this,"Enter National Id, null IDs numbers cannot be submitted","Submission Error!!!",JOptionPane.CANCEL_OPTION);
                txtNationalID.requestFocus();
            }else if(fullName.trim().equals("")){
                JOptionPane.showMessageDialog(this,"Enter teacher's full name, null names cannot be submitted","Submission Error!!!",JOptionPane.CANCEL_OPTION);
                txtFullName.requestFocus();
            }else if(phone.trim().equals("")){
                JOptionPane.showMessageDialog(this,"Enter phone number, null phone numbers cannot be submitted","Submission Error!!!",JOptionPane.CANCEL_OPTION);
                txtPhone.requestFocus();
            }else if(path1.trim().equals("")){
                JOptionPane.showMessageDialog(this,"Select profile picture, null profile pictures cannot be submitted","Submission Error!!!",JOptionPane.CANCEL_OPTION);
                btnSelect.requestFocus();
            }else{
        try {
            File file=new File(path1);
            fis=new FileInputStream(file);
            pst=con.prepareStatement("insert into teachers(teacherId,nationalId,fullName,profile_pic,phone)values(?,?,?,?,?)");
            int imageLength=Integer.parseInt(String.valueOf(file.length()));
            rawBytes=new byte[imageLength];
            fis.read(rawBytes,0,imageLength);
            pst.setString(1,teacherId);
            pst.setString(2,nationalId);
            pst.setString(3,fullName);
            pst.setBytes(4,rawBytes);
            pst.setString(5,phone);
            int count=pst.executeUpdate();
            if(count>0){
                JOptionPane.showMessageDialog(this, "Teacher added successfully into database","Success Message",JOptionPane.INFORMATION_MESSAGE);
                AutoID();
            }else{
               JOptionPane.showMessageDialog(this, "An error occured while tring to send details into database, please try again","Error Message",JOptionPane.CANCEL_OPTION); 
            }
            txtNationalID.setText("");
            txtFullName.setText("");
            txtPhone.setText("");
            lblPhoto.setIcon(null);
            txtNationalID.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(AddTeachers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddTeachers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddTeachers.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
    }//GEN-LAST:event_addBtnActionPerformed

    private void btnSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectActionPerformed
        JFileChooser fileChooser=new JFileChooser();
        fileChooser.setDialogTitle("Select profile picture");
        FileNameExtensionFilter filter=new FileNameExtensionFilter("Images","png","jpg","gif","jpeg");
        fileChooser.addChoosableFileFilter(filter);
        int userselection=fileChooser.showSaveDialog(this);
        if(userselection==JFileChooser.APPROVE_OPTION){
            File file=fileChooser.getSelectedFile();
            String path=file.getAbsolutePath();
            path1=path;
            lblPhoto.setIcon(new ImageIcon(new javax.swing.ImageIcon(path).getImage().getScaledInstance(100,110, Image.SCALE_SMOOTH)));
        }
    }//GEN-LAST:event_btnSelectActionPerformed

    private void txtPhoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhoneKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String teacherId=lblTeacherI.getText();
        String nationalId=txtNationalID.getText();
        String fullName=txtFullName.getText();
        String phone=txtPhone.getText();
        
        byte[] rawBytes=null;
        FileInputStream fis=null;
        
        if(nationalId.trim().equals("")){
                JOptionPane.showMessageDialog(this,"Enter National Id, null IDs numbers cannot be submitted","Submission Error!!!",JOptionPane.CANCEL_OPTION);
                txtNationalID.requestFocus();
            }else if(fullName.trim().equals("")){
                JOptionPane.showMessageDialog(this,"Enter teacher's full name, null names cannot be submitted","Submission Error!!!",JOptionPane.CANCEL_OPTION);
                txtFullName.requestFocus();
            }else if(phone.trim().equals("")){
                JOptionPane.showMessageDialog(this,"Enter phone number, null phone numbers cannot be submitted","Submission Error!!!",JOptionPane.CANCEL_OPTION);
                txtPhone.requestFocus();
            }else if(path1.trim().equals("")){
                JOptionPane.showMessageDialog(this,"Select profile picture, null profile pictures cannot be submitted","Submission Error!!!",JOptionPane.CANCEL_OPTION);
                btnSelect.requestFocus();
            }else{
        try {
            File file=new File(path1);
            fis=new FileInputStream(file);
            pst=con.prepareStatement("insert into teachers(teacherId,nationalId,fullName,profile_pic,phone)values(?,?,?,?,?)");
            int imageLength=Integer.parseInt(String.valueOf(file.length()));
            rawBytes=new byte[imageLength];
            fis.read(rawBytes,0,imageLength);
            pst.setString(1,teacherId);
            pst.setString(2,nationalId);
            pst.setString(3,fullName);
            pst.setBytes(4,rawBytes);
            pst.setString(5,phone);
            int count=pst.executeUpdate();
            if(count>0){
                JOptionPane.showMessageDialog(this, "Teacher added successfully into database","Success Message",JOptionPane.INFORMATION_MESSAGE);
                AutoID();
            }else{
               JOptionPane.showMessageDialog(this, "An error occured while tring to send details into database, please try again","Error Message",JOptionPane.CANCEL_OPTION); 
            }
            txtNationalID.setText("");
            txtFullName.setText("");
            txtPhone.setText("");
            lblPhoto.setIcon(null);
            txtNationalID.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(AddTeachers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddTeachers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddTeachers.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtNationalID.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnSelect.requestFocus();
        }
    }//GEN-LAST:event_txtPhoneKeyPressed

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
            java.util.logging.Logger.getLogger(AddTeachers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddTeachers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddTeachers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddTeachers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddTeachers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton btnSelect;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblPhoto;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblTeacherI;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JTextField txtNationalID;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}