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
public class AddBooks extends javax.swing.JFrame {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    String path1;
    /**
     * Creates new form CreateAccount
     */
    public AddBooks() {
        initComponents();
        Connection();
    }
    
    String username;
    BufferedImage imag;
    public AddBooks(String username,BufferedImage imag) {
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
            Logger.getLogger(AddBooks.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddBooks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AutoID(){
        try {
            Statement s=con.createStatement();
            rs=s.executeQuery("select MAX(bookId) from books");
            rs.next();
            rs.getString("MAX(bookId)");
            if(rs.getString("MAX(bookId)")==null){
                lblBookId.setText("BK001");
            }else{
                long id=Long.parseLong(rs.getString("MAX(bookId)").substring(2,rs.getString("MAX(bookId)").length()));
                id++;
                lblBookId.setText("BK"+String.format("%03d", id));
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
        lblBookId = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        txtBookName = new javax.swing.JTextField();
        txtBookNo = new javax.swing.JTextField();
        lblProfile = new javax.swing.JLabel();
        homeBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtAuthor = new javax.swing.JTextField();
        txtDescription = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        combStatus = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADD BOOKS");
        setMaximizedBounds(new java.awt.Rectangle(1200, 700, 700, 700));
        setMinimumSize(new java.awt.Dimension(1200, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 0, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBookId.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblBookId.setForeground(new java.awt.Color(255, 255, 255));
        lblBookId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblBookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 210, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("BOOK NO:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 110, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("BOOK NAME:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 130, -1));

        lblUsername.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("USERNAME");
        jPanel1.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 130, 120, -1));

        txtBookName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBookNameKeyPressed(evt);
            }
        });
        jPanel1.add(txtBookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, 210, 30));

        txtBookNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBookNoKeyPressed(evt);
            }
        });
        jPanel1.add(txtBookNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 210, 30));

        lblProfile.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 100, 110));

        homeBtn.setBackground(new java.awt.Color(0, 102, 102));
        homeBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        homeBtn.setForeground(new java.awt.Color(255, 255, 255));
        homeBtn.setText("HOME");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 110, 40));

        clearBtn.setBackground(new java.awt.Color(0, 102, 0));
        clearBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(255, 255, 255));
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 310, 100, 40));

        addBtn.setBackground(new java.awt.Color(51, 0, 204));
        addBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        addBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBtn.setText("ADD");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 310, 90, 40));

        exitBtn.setBackground(new java.awt.Color(255, 0, 0));
        exitBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(255, 255, 255));
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 310, 100, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("AUTHOR:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 120, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("DESCRIPTION:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 150, -1));

        txtAuthor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAuthorKeyPressed(evt);
            }
        });
        jPanel1.add(txtAuthor, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 210, 30));

        txtDescription.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescriptionKeyPressed(evt);
            }
        });
        jPanel1.add(txtDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, 210, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("BOOK ID:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 120, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("BOOK PRICE:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 150, -1));

        txtPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPriceKeyPressed(evt);
            }
        });
        jPanel1.add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, 210, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("STATUS:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 150, -1));

        combStatus.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        combStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT BOOK STATUS", "AVAILABLE", "ISSUED OUT" }));
        jPanel1.add(combStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, 210, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 830, 430));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 204));
        jLabel9.setText("ADD LIBRARY BOOKS");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 560, -1));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 6, 1200, 690));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        txtBookNo.setText("");
        txtBookName.setText("");
        txtAuthor.setText("");
        txtDescription.setText("");
        combStatus.getModel().setSelectedItem("SELECT BOOK STATUS");
        txtBookNo.requestFocus();
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

    private void txtBookNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBookNoKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtBookName.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtBookName.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtDescription.requestFocus();
        } 
    }//GEN-LAST:event_txtBookNoKeyPressed

    private void txtBookNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBookNameKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtAuthor.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtAuthor.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtBookNo.requestFocus();
        }
    }//GEN-LAST:event_txtBookNameKeyPressed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String bookId=lblBookId.getText();
        String bookNo=txtBookNo.getText();
        String bookName=txtBookName.getText();
        String author=txtAuthor.getText();
        String description=txtDescription.getText();
        int price=Integer.parseInt(txtPrice.getText());
        String status=combStatus.getModel().getSelectedItem().toString();
        
        if(bookNo.trim().equals("")){
                JOptionPane.showMessageDialog(this,"Enter Book Number, null book numbers cannot be submitted","Submission Error!!!",JOptionPane.CANCEL_OPTION);
                txtBookNo.requestFocus();
            }else if(bookName.trim().equals("")){
                JOptionPane.showMessageDialog(this,"Enter book name, null book names cannot be submitted","Submission Error!!!",JOptionPane.CANCEL_OPTION);
                txtBookName.requestFocus();
            }else if(author.trim().equals("")){
                JOptionPane.showMessageDialog(this,"Enter book author, null book authors cannot be submitted","Submission Error!!!",JOptionPane.CANCEL_OPTION);
                txtAuthor.requestFocus();
            }else if(description.trim().equals("")){
                JOptionPane.showMessageDialog(this,"Enter item description , null book descriptions cannot be submitted","Submission Error!!!",JOptionPane.CANCEL_OPTION);
                txtDescription.requestFocus();
            }else if(txtPrice.getText().trim().equals("")){
                JOptionPane.showMessageDialog(this,"Enter book's buying price, null book prices cannot be submitted","Submission Error!!!",JOptionPane.CANCEL_OPTION);
                txtPrice.requestFocus();
            }else if(status.trim().equals("SELECT BOOK STATUS")){
                JOptionPane.showMessageDialog(this,"Please select book status if availabe or issued out, null book statuses cannot be submitted","Submission Error!!!",JOptionPane.CANCEL_OPTION);
                combStatus.requestFocus();
            }else{
        try {
            pst=con.prepareStatement("insert into books(bookId,bookNO,bookName,author,description, price,status)values(?,?,?,?,?,?,?)");
            pst.setString(1,bookId);
            pst.setString(2,bookNo);
            pst.setString(3,bookName);
            pst.setString(4,author);
            pst.setString(5,description);
            pst.setInt(6,price);
            pst.setString(7, status);
            int count=pst.executeUpdate();
            if(count>0){
                JOptionPane.showMessageDialog(this, "Book inserted successfully into database","Success Message",JOptionPane.INFORMATION_MESSAGE);
                AutoID();
            }else{
               JOptionPane.showMessageDialog(this, "An error occured while tring to send details into database, please try again","Error Message",JOptionPane.CANCEL_OPTION); 
            }
            txtBookNo.setText("");
            txtBookName.setText("");
            txtAuthor.setText("");
            txtDescription.setText("");
            txtPrice.setText("");
            combStatus.getModel().setSelectedItem("SELECT BOOK STATUS");
            txtBookNo.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(AddBooks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }//GEN-LAST:event_addBtnActionPerformed

    private void txtAuthorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAuthorKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtDescription.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtDescription.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtBookName.requestFocus();
        }
    }//GEN-LAST:event_txtAuthorKeyPressed

    private void txtDescriptionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescriptionKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtPrice.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtBookNo.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtAuthor.requestFocus();
        }
    }//GEN-LAST:event_txtDescriptionKeyPressed

    private void txtPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPriceKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            combStatus.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            combStatus.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtDescription.requestFocus();
        }
    }//GEN-LAST:event_txtPriceKeyPressed

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
            java.util.logging.Logger.getLogger(AddBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddBooks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton clearBtn;
    private javax.swing.JComboBox<String> combStatus;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBookId;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTextField txtAuthor;
    private javax.swing.JTextField txtBookName;
    private javax.swing.JTextField txtBookNo;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtPrice;
    // End of variables declaration//GEN-END:variables
}
