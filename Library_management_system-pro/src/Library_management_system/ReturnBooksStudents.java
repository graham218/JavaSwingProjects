/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library_management_system;

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
import java.util.Locale;
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
 * @author USER
 */
public class ReturnBooksStudents extends javax.swing.JFrame {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    String path1;
    /**
     * Creates new form CreateAccount
     */
    public ReturnBooksStudents() {
        initComponents();
        Connection();
    }

    String username;
    BufferedImage imag;
    public ReturnBooksStudents(String username,BufferedImage imag) {
        initComponents();
        Connection();
        this.username=username;
        this.imag=imag;
        lblUsername.setText(username);
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(lblProfile.getWidth(), lblProfile.getHeight(), Image.SCALE_SMOOTH)));
        current_date();
        issued_students_table();
        confirmstudents();
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("../Images/caleb-woods-fulXJYIvRi8-unsplash.jpg")).getImage().getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), Image.SCALE_SMOOTH)));
    }
    public void Connection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/library","root","");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReturnBooksStudents.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReturnBooksStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
  
    
   
    public void issued_students_table(){
        try {
            pst=con.prepareStatement("select * from issuebooksstudents");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i =1; i <=c; i++) {
                    v.add(rs.getString("issueNo"));
                    v.add(rs.getString("issueDate"));
                    v.add(rs.getString("bookNo"));
                    v.add(rs.getString("bookName"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("admNo"));
                    v.add(rs.getString("studentName"));
                    v.add(rs.getString("returnDate"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void confirmstudents(){
        try {
            pst=con.prepareStatement("select * from confirmstudents");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i =1; i <=c; i++) {
                    v.add(rs.getString("issueNo"));
                    v.add(rs.getString("dateReturned"));
                    v.add(rs.getString("bookNo"));
                    v.add(rs.getString("bookName"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("admNo"));
                    v.add(rs.getString("studentName"));
                    v.add(rs.getString("confirmation"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void current_date(){
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now=LocalDateTime.now();
        String date=dtf.format(now);
        lblReturnDate.setText(date);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblProfile = new javax.swing.JLabel();
        homeBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        lblBookName = new javax.swing.JLabel();
        deleteBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        txtSearch2 = new javax.swing.JTextField();
        search3 = new javax.swing.JToggleButton();
        confirmBtn = new javax.swing.JToggleButton();
        lblReturnDate = new javax.swing.JLabel();
        lblBookNo = new javax.swing.JLabel();
        lblStudentName = new javax.swing.JLabel();
        lblAdmNo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblBookPrice = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblIssueNo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        searchBtn = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        combConfirm = new javax.swing.JComboBox<>();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RETURNING OF BOOKS BY STUDENTS");
        setMaximizedBounds(new java.awt.Rectangle(1300, 700, 700, 700));
        setMinimumSize(new java.awt.Dimension(1300, 700));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 0, 102));
        jLabel9.setText("RETURNING OF BOOKS BY STUDENTS");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 690, -1));

        jPanel1.setBackground(new java.awt.Color(255, 51, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DATE RETURNED:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 170, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("BOOK NO:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 110, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("BOOK NAME:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 170, -1));

        lblUsername.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("USERNAME");
        jPanel1.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 90, 120, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ADMISSION NO:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 160, -1));

        lblProfile.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 0, 80, 80));

        homeBtn.setBackground(new java.awt.Color(0, 102, 102));
        homeBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        homeBtn.setForeground(new java.awt.Color(255, 255, 255));
        homeBtn.setText("HOME");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 130, 40));

        clearBtn.setBackground(new java.awt.Color(0, 102, 0));
        clearBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(255, 255, 255));
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 130, 40));

        editBtn.setBackground(new java.awt.Color(102, 0, 102));
        editBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        editBtn.setForeground(new java.awt.Color(255, 255, 255));
        editBtn.setText("EDIT");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        jPanel1.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 440, 140, 40));

        exitBtn.setBackground(new java.awt.Color(255, 0, 0));
        exitBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(255, 255, 255));
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 440, 100, 40));

        lblBookName.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblBookName.setForeground(new java.awt.Color(255, 255, 255));
        lblBookName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblBookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 210, 30));

        deleteBtn.setBackground(new java.awt.Color(255, 0, 0));
        deleteBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 390, 100, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("CONFIRMATION:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 170, -1));

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISSUE NO", "ISSUE DATE", "BOOK NO", "BOOK NAME", "PRICE", "ADM NO", "STUDENT NAME", "RETURN DATE"
            }
        ));
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(table2);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 740, 170));

        txtSearch2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearch2KeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, 230, 30));

        search3.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        search3.setText("SEARCH WITH ADMISSION NUMBER");
        search3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search3ActionPerformed(evt);
            }
        });
        jPanel1.add(search3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 70, 300, 30));

        confirmBtn.setBackground(new java.awt.Color(0, 51, 153));
        confirmBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        confirmBtn.setForeground(new java.awt.Color(255, 255, 255));
        confirmBtn.setText("CONFIRM");
        confirmBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmBtnActionPerformed(evt);
            }
        });
        jPanel1.add(confirmBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, 140, 40));

        lblReturnDate.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblReturnDate.setForeground(new java.awt.Color(255, 255, 255));
        lblReturnDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblReturnDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 210, 30));

        lblBookNo.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblBookNo.setForeground(new java.awt.Color(255, 255, 255));
        lblBookNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblBookNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 210, 30));

        lblStudentName.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblStudentName.setForeground(new java.awt.Color(255, 255, 255));
        lblStudentName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblStudentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 280, 210, 30));

        lblAdmNo.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblAdmNo.setForeground(new java.awt.Color(255, 255, 255));
        lblAdmNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblAdmNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 210, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("BOOK PRICE:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 170, -1));

        lblBookPrice.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblBookPrice.setForeground(new java.awt.Color(255, 255, 255));
        lblBookPrice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblBookPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, 210, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ISSUE NUMBER:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 170, -1));

        lblIssueNo.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblIssueNo.setForeground(new java.awt.Color(255, 255, 255));
        lblIssueNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblIssueNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 210, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISSUE NO", "DATE RETURNED", "BOOK NO", "BOOK NAME", "BOOK PRICE", "ADM NO", "STUDENT NAME", "COFIRMATION"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 350, 730, 200));

        searchBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        searchBtn.setText("SEARCH WITH ADMISSION NUMBER");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        jPanel1.add(searchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 300, 300, 30));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 300, 240, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("STUDENT NAME:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 170, -1));

        combConfirm.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        combConfirm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CONFIRM RETURN", "RETURNED", "LOST" }));
        jPanel1.add(combConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 330, 210, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 1220, 610));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 6, 1300, 690));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        this.setVisible(false);
        new Home(lblUsername.getText(),imag).setVisible(true);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        lblBookNo.setText("");
        lblIssueNo.setText("");
        lblBookName.setText("");
        lblBookPrice.setText("");
        lblAdmNo.setText("");
        lblStudentName.setText("");
        lblBookName.setText("");
        combConfirm.getModel().setSelectedItem("CONFIRM RETURN");
        txtSearch.requestFocus();
        deleteBtn.setEnabled(false);
        editBtn.setEnabled(false);
        confirmBtn.setEnabled(true);
    }//GEN-LAST:event_clearBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        String issueNo=lblIssueNo.getText();
        String bookNo=lblBookNo.getText();
        String confirmation=combConfirm.getSelectedItem().toString();
        if(combConfirm.getSelectedItem().toString().equals("CONFIRM RETURN")){
            JOptionPane.showMessageDialog(this, "Please confirm book return","Error message",JOptionPane.CANCEL_OPTION);
            combConfirm.requestFocus();
        }else{
            try {
                pst=con.prepareStatement("update confirmstudents set confirmation=? where issueNo=?");
                pst.setString(1,confirmation);
                pst.setString(2,issueNo);              
                int count=pst.executeUpdate();
                if(count>0){
                    JOptionPane.showMessageDialog(this, "Book updated successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
                    issued_students_table();
                    confirmstudents();
                    if(combConfirm.getSelectedItem().equals("LOST")){
                    JOptionPane.showMessageDialog(this,"This book will be deleted from the books table for it is considered lost","Warning!!",JOptionPane.YES_NO_OPTION);
                    pst=con.prepareStatement("delete from books where bookNo=?");
                    pst.setString(1, bookNo);
                    pst.executeUpdate();
                    }else{
                        pst=con.prepareStatement("update books set status='AVAILABLE' where bookNo=?");
                        pst.setString(1,bookNo);
                        pst.executeUpdate();
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "An error occured while tring to send details into database, please try again","Error Message",JOptionPane.CANCEL_OPTION);
                }
                lblBookNo.setText("");
                lblIssueNo.setText("");
                lblBookName.setText("");
                lblBookPrice.setText("");
                lblAdmNo.setText("");
                lblStudentName.setText("");
                lblBookName.setText("");
                combConfirm.getModel().setSelectedItem("CONFIRM RETURN");
                txtSearch.requestFocus();
                deleteBtn.setEnabled(false);
                editBtn.setEnabled(false);
                confirmBtn.setEnabled(true);
                confirmstudents();
            } catch (SQLException ex) {
                Logger.getLogger(ReturnBooksStudents.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Do yo want to exit system","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        String issueNo=lblIssueNo.getText();
        
        try {
            pst=con.prepareStatement("delete from confirmstudents where issueNo=?");
            pst.setString(1,issueNo);
            int count=pst.executeUpdate();
            if(count>0){
                JOptionPane.showMessageDialog(this, "Returned book deleted successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
                confirmstudents();
                issued_students_table();
            }else{
                JOptionPane.showMessageDialog(this, "An error occured while tring to send details into database, please try again","Error Message",JOptionPane.CANCEL_OPTION);
            }
            lblBookNo.setText("");
            lblIssueNo.setText("");
            lblBookName.setText("");
            lblBookPrice.setText("");
            lblAdmNo.setText("");
            lblStudentName.setText("");
            lblBookName.setText("");
            combConfirm.getModel().setSelectedItem("CONFIRM RETURN");
            txtSearch.requestFocus();
            deleteBtn.setEnabled(false);
            editBtn.setEnabled(false);
            confirmBtn.setEnabled(true);
        } catch (SQLException ex) {
            Logger.getLogger(ReturnBooksStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void search3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search3ActionPerformed
        String admNo=txtSearch2.getText();
            try {
                pst=con.prepareStatement("select * from issuebooksstudents where admNo LIKE CONCAT('%',?,'%')");
                pst.setString(1,admNo);
                rs=pst.executeQuery();
                ResultSetMetaData rsm=rs.getMetaData();
                int c=rsm.getColumnCount();

                DefaultTableModel model=(DefaultTableModel)table2.getModel();
                model.setRowCount(0);
                while(rs.next()){
                    Vector v=new Vector();
                    for (int i =1; i <=c; i++) {
                        v.add(rs.getString("issueNo"));
                        v.add(rs.getString("issueDate"));
                        v.add(rs.getString("bookNo"));
                        v.add(rs.getString("bookName"));
                        v.add(rs.getInt("price"));
                        v.add(rs.getString("admNo"));
                        v.add(rs.getString("studentName"));
                        v.add(rs.getString("returnDate"));
                    }
                    model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_search3ActionPerformed

    private void txtSearch2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String admNo=txtSearch2.getText();
            try {
                pst=con.prepareStatement("select * from issuebooksstudents where admNo LIKE CONCAT('%',?,'%')");
                pst.setString(1,admNo);
                rs=pst.executeQuery();
                ResultSetMetaData rsm=rs.getMetaData();
                int c=rsm.getColumnCount();

                DefaultTableModel model=(DefaultTableModel)table2.getModel();
                model.setRowCount(0);
                while(rs.next()){
                    Vector v=new Vector();
                    for (int i =1; i <=c; i++) {
                        v.add(rs.getString("issueNo"));
                        v.add(rs.getString("issueDate"));
                        v.add(rs.getString("bookNo"));
                        v.add(rs.getString("bookName"));
                        v.add(rs.getInt("price"));
                        v.add(rs.getString("admNo"));
                        v.add(rs.getString("studentName"));
                        v.add(rs.getString("returnDate"));
                    }
                    model.addRow(v);
                }
            } catch (SQLException ex) {
                Logger.getLogger(EditStudents.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtSearch2KeyPressed

    private void confirmBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmBtnActionPerformed
        String issueNo=lblIssueNo.getText();
        String issueDate=lblReturnDate.getText();
        String bookNo=lblBookNo.getText();
        String bookName=lblBookName.getText();
        int bookPrice=Integer.parseInt(lblBookPrice.getText());
        String admissionNo=lblAdmNo.getText();
        String studentName=lblStudentName.getText();
        String confirmation=combConfirm.getSelectedItem().toString();
        if(combConfirm.getSelectedItem().toString().equals("CONFIRM RETURN")){
            JOptionPane.showMessageDialog(this, "Please confirm book return","Error message",JOptionPane.CANCEL_OPTION);
            combConfirm.requestFocus();
        }else{
            try {
                pst=con.prepareStatement("insert into confirmstudents(issueNo,dateReturned,bookNo,bookName,price,admNo,studentName,confirmation)values(?,?,?,?,?,?,?,?)");
                pst.setString(1,issueNo);
                pst.setString(2,issueDate);
                pst.setString(3,bookNo);
                pst.setString(4,bookName);
                pst.setInt(5,bookPrice);
                pst.setString(6,admissionNo);
                pst.setString(7,studentName);
                pst.setString(8,confirmation);
                pst.executeUpdate();
                if(combConfirm.getSelectedItem().equals("LOST")){
                    JOptionPane.showMessageDialog(this,"This book will be deleted from the books table for it is considered lost","Warning!!",JOptionPane.YES_NO_OPTION);
                    pst=con.prepareStatement("delete from books where bookNo=?");
                    pst.setString(1, bookNo);
                    pst.executeUpdate();
                }else{
                    pst=con.prepareStatement("update books set status='AVAILABLE' where bookNo=?");
                    pst.setString(1,bookNo);
                    pst.executeUpdate();
                }
                pst=con.prepareStatement("delete from issuebooksstudents where issueNo=?");
                pst.setString(1, issueNo);
                pst.executeUpdate();
                issued_students_table();
                JOptionPane.showMessageDialog(this, "Book return confirmed Successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
                lblBookNo.setText("");
                lblIssueNo.setText("");
                lblBookName.setText("");
                lblBookPrice.setText("");
                lblAdmNo.setText("");
                lblStudentName.setText("");
                lblBookName.setText("");
                combConfirm.getModel().setSelectedItem("CONFIRM RETURN");
                txtSearch.requestFocus();
                confirmstudents();
            } catch (SQLException ex) {
                Logger.getLogger(ReturnBooksStudents.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_confirmBtnActionPerformed

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        DefaultTableModel model=(DefaultTableModel)table2.getModel();
        int selectedIndex=table2.getSelectedRow();

        lblIssueNo.setText(model.getValueAt(selectedIndex, 0).toString());
        lblBookNo.setText(model.getValueAt(selectedIndex, 2).toString());
        lblBookName.setText(model.getValueAt(selectedIndex, 3).toString());
        lblBookPrice.setText(model.getValueAt(selectedIndex, 4).toString());
        lblAdmNo.setText(model.getValueAt(selectedIndex, 5).toString());
        lblStudentName.setText(model.getValueAt(selectedIndex, 6).toString());
    }//GEN-LAST:event_table2MouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        deleteBtn.setEnabled(false);
        editBtn.setEnabled(false);
    }//GEN-LAST:event_formWindowActivated

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String admNo=txtSearch.getText();
        try {
            pst=con.prepareStatement("select * from confirmstudents where admNo LIKE CONCAT('%',?,'%')");
            pst.setString(1,admNo);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i =1; i <=c; i++) {
                    v.add(rs.getString("issueNo"));
                    v.add(rs.getString("dateReturned"));
                    v.add(rs.getString("bookNo"));
                    v.add(rs.getString("bookName"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("admNo"));
                    v.add(rs.getString("studentName"));
                    v.add(rs.getString("confirmation"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
             String admNo=txtSearch.getText();
        try {
            pst=con.prepareStatement("select * from confirmstudents where admNo LIKE CONCAT('%',?,'%')");
            pst.setString(1,admNo);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i =1; i <=c; i++) {
                    v.add(rs.getString("issueNo"));
                    v.add(rs.getString("dateReturned"));
                    v.add(rs.getString("bookNo"));
                    v.add(rs.getString("bookName"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("admNo"));
                    v.add(rs.getString("studentName"));
                    v.add(rs.getString("confirmation"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        deleteBtn.setEnabled(true);
        editBtn.setEnabled(true);
        confirmBtn.setEnabled(false);

        DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
        int selectedIndex=jTable1.getSelectedRow();

        lblIssueNo.setText(model.getValueAt(selectedIndex, 0).toString());
        lblReturnDate.setText(model.getValueAt(selectedIndex, 1).toString());
        lblBookNo.setText(model.getValueAt(selectedIndex, 2).toString());
        lblBookName.setText(model.getValueAt(selectedIndex, 3).toString());
        lblBookPrice.setText(model.getValueAt(selectedIndex, 4).toString());
        lblAdmNo.setText(model.getValueAt(selectedIndex, 5).toString());
        lblStudentName.setText(model.getValueAt(selectedIndex, 6).toString());
        combConfirm.getModel().setSelectedItem(model.getValueAt(selectedIndex, 7).toString());
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(ReturnBooksStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReturnBooksStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReturnBooksStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReturnBooksStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new ReturnBooksStudents().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearBtn;
    private javax.swing.JComboBox<String> combConfirm;
    private javax.swing.JToggleButton confirmBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblAdmNo;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBookName;
    private javax.swing.JLabel lblBookNo;
    private javax.swing.JLabel lblBookPrice;
    private javax.swing.JLabel lblIssueNo;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblReturnDate;
    private javax.swing.JLabel lblStudentName;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JToggleButton search3;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTable table2;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearch2;
    // End of variables declaration//GEN-END:variables
}
