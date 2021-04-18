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
public class EditDeleteTrainers extends javax.swing.JFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    /**
     * Creates new form User
     */
    public EditDeleteTrainers() {
        initComponents();
    }

    String username;
    BufferedImage imag;
    public EditDeleteTrainers(String username, BufferedImage imag) {
        initComponents();
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        Connect(); 
        Auto_Id();
        current_date();
        trainers_table();
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("jonathan-borba-R0y_bEUjiOM-unsplash.jpg")).getImage().getScaledInstance(1200, 680, Image.SCALE_SMOOTH)));
    }
    public void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/gym", "root", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditDeleteTrainers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EditDeleteTrainers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Auto_Id(){
        try {
            Statement s=con.createStatement();
            rs=s.executeQuery("select MAX(coachNo) from trainers");
            rs.next();
            rs.getString("MAX(coachNo)");
            if(rs.getString("MAX(coachNo)")==null){
                coachNo.setText("CH001");
            }else{
                long id=Long.parseLong(rs.getString("MAX(coachNo)").substring(2,rs.getString("MAX(coachNo)").length()));
                id++;
                coachNo.setText("CH"+String.format("%03d", id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Trainers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void trainers_table(){
        try {
            pst=con.prepareStatement("select * from trainers");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i <= c; i++) {
                    v.add(rs.getString("coachNo"));
                    v.add(rs.getString("startingDate"));
                    v.add(rs.getString("nationalId"));
                    v.add(rs.getString("name"));
                    v.add(rs.getString("phone"));
                    v.add(rs.getString("trainingTime"));
                    v.add(rs.getBlob("profilePic"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EditDeleteTrainers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void current_date(){
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now=LocalDateTime.now();
        String date=dtf.format(now);
        lblStartingDate.setText(date);
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
        txtNationalId = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtTrainingTime = new javax.swing.JTextField();
        lblUserPhoto = new javax.swing.JLabel();
        btnSelect = new javax.swing.JButton();
        homeBtn = new javax.swing.JButton();
        clearBn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        coachNo = new javax.swing.JLabel();
        txtPhoneNo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lblStartingDate = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        editPhotoBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("EDIT - DELETE TRAINERS");
        setAlwaysOnTop(true);
        setMaximizedBounds(new java.awt.Rectangle(1211, 700, 700, 700));
        setMinimumSize(new java.awt.Dimension(1211, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(30, 86, 221));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Serif", 3, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("COACH NO:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Serif", 3, 18)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("NAME:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jLabel4.setFont(new java.awt.Font("Serif", 3, 18)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("PHONE NUMBER:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        jLabel5.setFont(new java.awt.Font("Serif", 3, 18)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("TRAINING TIME:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        jLabel6.setFont(new java.awt.Font("Serif", 3, 18)); // NOI18N
        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("PROFILE PHOTO:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, -1, -1));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 0, 80, 80));

        lblUsername.setFont(new java.awt.Font("Arial Black", 3, 16)); // NOI18N
        lblUsername.setForeground(java.awt.Color.white);
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("USERNAME");
        jPanel1.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 10, 190, -1));

        txtNationalId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNationalIdKeyPressed(evt);
            }
        });
        jPanel1.add(txtNationalId, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 200, 40));

        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNameKeyPressed(evt);
            }
        });
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 200, 40));

        txtTrainingTime.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTrainingTimeKeyPressed(evt);
            }
        });
        jPanel1.add(txtTrainingTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 200, 40));

        lblUserPhoto.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel1.add(lblUserPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 310, 100, 120));

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
        jPanel1.add(btnSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 310, 90, 50));

        homeBtn.setBackground(new java.awt.Color(154, 14, 239));
        homeBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        homeBtn.setForeground(java.awt.Color.white);
        homeBtn.setText("HOME");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 90, 50));

        clearBn.setBackground(new java.awt.Color(14, 239, 239));
        clearBn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        clearBn.setForeground(java.awt.Color.white);
        clearBn.setText("CLEAR");
        clearBn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 90, 50));

        editBtn.setBackground(new java.awt.Color(19, 170, 29));
        editBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        editBtn.setForeground(java.awt.Color.white);
        editBtn.setText("EDIT");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        jPanel1.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 440, 100, 50));

        exitBtn.setBackground(new java.awt.Color(239, 14, 32));
        exitBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        exitBtn.setForeground(java.awt.Color.white);
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 520, 140, 50));

        jLabel7.setFont(new java.awt.Font("Serif", 3, 18)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("NATIONAL ID:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        coachNo.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        coachNo.setForeground(java.awt.Color.white);
        coachNo.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(coachNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 200, 40));

        txtPhoneNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPhoneNoKeyPressed(evt);
            }
        });
        jPanel1.add(txtPhoneNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 200, 40));

        jLabel8.setFont(new java.awt.Font("Serif", 3, 18)); // NOI18N
        jLabel8.setForeground(java.awt.Color.white);
        jLabel8.setText("STARTING DATE:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        lblStartingDate.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        lblStartingDate.setForeground(java.awt.Color.white);
        lblStartingDate.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblStartingDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 200, 40));

        table.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(243, 179, 5), 2, true));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CUSTOMER NO", "DATE OF START", "NATIONAL ID", "NAME", "PHONE NO", "TRAINING TIME", "PROFILE PHOTO"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 90, 680, 400));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 210, 40));

        searchBtn.setText("SEARCH");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        jPanel1.add(searchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 120, 40));

        deleteBtn.setBackground(new java.awt.Color(233, 55, 5));
        deleteBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        deleteBtn.setForeground(java.awt.Color.white);
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 440, 100, 50));

        editPhotoBtn.setBackground(new java.awt.Color(19, 170, 29));
        editPhotoBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        editPhotoBtn.setForeground(java.awt.Color.white);
        editPhotoBtn.setText("EDIT PHOTO");
        editPhotoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPhotoBtnActionPerformed(evt);
            }
        });
        jPanel1.add(editPhotoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 440, 140, 50));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("NOTE: USE NATIONAL ID TO SEARCH FOR TRAINERS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 500, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 1170, 610));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1200, 680));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        this.setVisible(false);
        new Home(lblUsername.getText(),imag).setVisible(true);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void clearBnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBnActionPerformed
        txtNationalId.setText("");
        txtName.setText("");
        txtTrainingTime.setText("");
        txtPhoneNo.setText("");
        lblUserPhoto.setIcon(null);
        txtNationalId.requestFocus();
        Auto_Id();
        current_date();
    }//GEN-LAST:event_clearBnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        String coachNumber=coachNo.getText();
        String startingDate=lblStartingDate.getText();
        String nationalId=txtNationalId.getText();
        String name=txtName.getText();
        String phone=txtPhoneNo.getText();
        String trainingTime=txtTrainingTime.getText();
        
        if(nationalId.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter Trainer's national ID, null IDs cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtNationalId.requestFocus();
        }else if(name.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter Trainer's name, null names cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtName.requestFocus();
        }else if(phone.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter phone number, null phone numbers cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtPhoneNo.requestFocus();
        }else if(trainingTime.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter training time, null times cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtTrainingTime.requestFocus();
        }else{
            byte[] rawBytes = null;  
            FileInputStream fis = null; 
            
            try {
                pst=con.prepareStatement("update trainers set startingDate=?,nationalId=?,name=?,phone=?,trainingTime=? where coachNo=?");
                
                pst.setString(1,startingDate);
                pst.setString(2,nationalId);             
                pst.setString(3,name);
                pst.setString(4,phone);
                pst.setString(5,trainingTime);
                pst.setString(6,coachNumber);
                int count = pst.executeUpdate();
                if (count > 0) {
                    JOptionPane.showMessageDialog(this, "Trainer Updated Successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
                    Auto_Id();
                    current_date();
                    trainers_table();
                    txtNationalId.setText("");
                    txtName.setText("");
                    txtTrainingTime.setText("");
                    txtPhoneNo.setText("");
                    lblUserPhoto.setIcon(null);
                    txtNationalId.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(this, "An Error Occured while trying to send your details into the database, please try again","Error Message",JOptionPane.CANCEL_OPTION);
                }
            } catch (SQLException ex) {
                Logger.getLogger(EditDeleteTrainers.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Do you want to exit system?","GYM Management System",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

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

    private void txtNationalIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNationalIdKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtName.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtName.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtTrainingTime.requestFocus();
        }
    }//GEN-LAST:event_txtNationalIdKeyPressed

    private void txtNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtPhoneNo.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtPhoneNo.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtNationalId.requestFocus();
        }
    }//GEN-LAST:event_txtNameKeyPressed

    private void txtTrainingTimeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTrainingTimeKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            btnSelect.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            btnSelect.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtPhoneNo.requestFocus();
        }
    }//GEN-LAST:event_txtTrainingTimeKeyPressed

    private void txtPhoneNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhoneNoKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtTrainingTime.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtTrainingTime.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtName.requestFocus();
        }
    }//GEN-LAST:event_txtPhoneNoKeyPressed

    private void btnSelectKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSelectKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtNationalId.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtTrainingTime.requestFocus();
        }
    }//GEN-LAST:event_btnSelectKeyPressed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String nationalId=txtSearch.getText();
        try {
            pst=con.prepareStatement("select * from trainers where nationalId like CONCAT('%',?,'%')");
            pst.setString(1,nationalId);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i <= c; i++) {
                    v.add(rs.getString("coachNo"));
                    v.add(rs.getString("startingDate"));
                    v.add(rs.getString("nationalId"));
                    v.add(rs.getString("name"));
                    v.add(rs.getString("phone"));
                    v.add(rs.getString("trainingTime"));
                    v.add(rs.getBlob("profilePic"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EditDeleteTrainers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String nationalId=txtSearch.getText();
        try {
            pst=con.prepareStatement("select * from trainers where nationalId like CONCAT('%',?,'%')");
            pst.setString(1,nationalId);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i <= c; i++) {
                    v.add(rs.getString("coachNo"));
                    v.add(rs.getString("startingDate"));
                    v.add(rs.getString("nationalId"));
                    v.add(rs.getString("name"));
                    v.add(rs.getString("phone"));
                    v.add(rs.getString("trainingTime"));
                    v.add(rs.getBlob("profilePic"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EditDeleteTrainers.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        DefaultTableModel model=(DefaultTableModel)table.getModel();
        int selectedIndex=table.getSelectedRow();
        
        coachNo.setText(model.getValueAt(selectedIndex, 0).toString());
        lblStartingDate.setText(model.getValueAt(selectedIndex, 1).toString());
        txtNationalId.setText(model.getValueAt(selectedIndex, 2).toString());
        txtName.setText(model.getValueAt(selectedIndex, 3).toString());
        txtPhoneNo.setText(model.getValueAt(selectedIndex, 4).toString());
        txtTrainingTime.setText(model.getValueAt(selectedIndex, 5).toString());
        Blob img=(Blob) model.getValueAt(selectedIndex, 6);
        byte[] imageData;
        try {
            imageData = img.getBytes(1,(int)img.length());
            BufferedImage imag=ImageIO.read(new ByteArrayInputStream(imageData));
            lblUserPhoto.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(100, 120, Image.SCALE_SMOOTH)));
        } catch (SQLException ex) {
            Logger.getLogger(EditDeleteTrainers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EditDeleteTrainers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tableMouseClicked

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        String customerNo=coachNo.getText();
            try {
                pst=con.prepareStatement("delete from trainers where coachNo=?");           
                pst.setString(1, customerNo);
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(this," Trainer deleted Successfully from database","Success Message",JOptionPane.INFORMATION_MESSAGE);
                Auto_Id();
                current_date();
                trainers_table();
                txtNationalId.setText("");
                txtName.setText("");
                txtTrainingTime.setText("");
                txtPhoneNo.setText("");
                lblUserPhoto.setIcon(null);
                txtNationalId.requestFocus();
                
                
                    } catch (SQLException ex) {
                Logger.getLogger(EditDeleteTrainers.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void editPhotoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPhotoBtnActionPerformed
        byte[] rawBytes = null;  
        FileInputStream fis = null; 
        try {
            File fileObj = new File(path1);
            fis = new FileInputStream(fileObj);
            pst=con.prepareStatement("update trainers set profilePic=? where coachNo=?");
            int imageLength = Integer.parseInt(String.valueOf(fileObj.length()));  
            rawBytes = new byte[imageLength];
            fis.read(rawBytes, 0, imageLength);
            pst.setBytes(1, rawBytes);
            pst.setString(2, coachNo.getText());
            pst.execute();
            
            JOptionPane.showMessageDialog(this,"Trainer's photo updated Successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
            Auto_Id();
            current_date();
            trainers_table();
            txtNationalId.setText("");
            txtName.setText("");
            txtTrainingTime.setText("");
            txtPhoneNo.setText("");
            lblUserPhoto.setIcon(null);
            txtNationalId.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(EditDeleteTrainers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EditDeleteTrainers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EditDeleteTrainers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_editPhotoBtnActionPerformed

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
            java.util.logging.Logger.getLogger(EditDeleteTrainers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditDeleteTrainers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditDeleteTrainers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditDeleteTrainers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new EditDeleteTrainers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelect;
    private javax.swing.JButton clearBn;
    private javax.swing.JLabel coachNo;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton editPhotoBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
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
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblStartingDate;
    private javax.swing.JLabel lblUserPhoto;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNationalId;
    private javax.swing.JTextField txtPhoneNo;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTrainingTime;
    // End of variables declaration//GEN-END:variables
}
