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
public class PayTrainers extends javax.swing.JFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    /**
     * Creates new form User
     */
    public PayTrainers() {
        initComponents();
    }

    String username;
    BufferedImage imag;
    public PayTrainers(String username, BufferedImage imag) {
        initComponents();
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        Connect(); 
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("clayton-scelzi-kcqlDjurZ8c-unsplash.jpg")).getImage().getScaledInstance(1200, 680, Image.SCALE_SMOOTH)));
        current_date();
        trainers_table();
        payTrainer_table();
        
    }
    public void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/gym", "root", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PayTrainers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PayTrainers.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void payTrainer_table(){
        try {
            pst=con.prepareStatement("select * from payTrainer");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i <= c; i++) {
                    v.add(rs.getString("coachNo"));
                    v.add(rs.getString("payingDate"));
                    v.add(rs.getString("nationalId"));
                    v.add(rs.getString("name"));
                    v.add(rs.getString("cater"));
                    v.add(rs.getInt("payment"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PayTrainers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void current_date(){
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now=LocalDateTime.now();
        String date=dtf.format(now);
        lblPayingDate.setText(date);
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
        lblProfile = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        txtPay = new javax.swing.JTextField();
        homeBtn = new javax.swing.JButton();
        clearBn = new javax.swing.JButton();
        payBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lblCoachNo = new javax.swing.JLabel();
        txtCater = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        deleteBtn = new javax.swing.JButton();
        editPay = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        txtSearch2 = new javax.swing.JTextField();
        searchBtn2 = new javax.swing.JButton();
        lblPayingDate = new javax.swing.JLabel();
        lblNationalId = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PAY TRAINERS/COACHES");
        setAlwaysOnTop(true);
        setMaximizedBounds(new java.awt.Rectangle(1211, 700, 700, 700));
        setMinimumSize(new java.awt.Dimension(1211, 700));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(75, 138, 30));
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
        jLabel4.setText("PAYMENT CATER:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        jLabel5.setFont(new java.awt.Font("Serif", 3, 18)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("PAY / SALARY:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 0, 70, 70));

        lblUsername.setFont(new java.awt.Font("Arial Black", 3, 16)); // NOI18N
        lblUsername.setForeground(java.awt.Color.white);
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("USERNAME");
        jPanel1.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 0, 190, -1));

        txtPay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPayKeyPressed(evt);
            }
        });
        jPanel1.add(txtPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 200, 40));

        homeBtn.setBackground(new java.awt.Color(14, 46, 239));
        homeBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        homeBtn.setForeground(java.awt.Color.white);
        homeBtn.setText("HOME");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 130, 50));

        clearBn.setBackground(new java.awt.Color(14, 239, 239));
        clearBn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        clearBn.setForeground(java.awt.Color.white);
        clearBn.setText("CLEAR");
        clearBn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 130, 50));

        payBtn.setBackground(new java.awt.Color(30, 21, 249));
        payBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        payBtn.setForeground(java.awt.Color.white);
        payBtn.setText("PAY");
        payBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payBtnActionPerformed(evt);
            }
        });
        jPanel1.add(payBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 340, 150, 50));

        exitBtn.setBackground(new java.awt.Color(239, 14, 32));
        exitBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        exitBtn.setForeground(java.awt.Color.white);
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 140, 50));

        jLabel7.setFont(new java.awt.Font("Serif", 3, 18)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("NATIONAL ID:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        lblCoachNo.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        lblCoachNo.setForeground(java.awt.Color.white);
        lblCoachNo.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblCoachNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 200, 40));

        txtCater.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCaterKeyPressed(evt);
            }
        });
        jPanel1.add(txtCater, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 200, 40));

        jLabel8.setFont(new java.awt.Font("Serif", 3, 18)); // NOI18N
        jLabel8.setForeground(java.awt.Color.white);
        jLabel8.setText("PAYING DATE:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        lblName.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        lblName.setForeground(java.awt.Color.white);
        lblName.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 200, 40));

        deleteBtn.setBackground(new java.awt.Color(233, 55, 5));
        deleteBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        deleteBtn.setForeground(java.awt.Color.white);
        deleteBtn.setText("DELETE PAY");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 410, 160, 50));

        editPay.setBackground(new java.awt.Color(123, 28, 184));
        editPay.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        editPay.setForeground(java.awt.Color.white);
        editPay.setText("EDIT PAY");
        editPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPayActionPerformed(evt);
            }
        });
        jPanel1.add(editPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, 150, 50));

        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NO", "DATE", "NATIONAL ID", "NAME", "CATER", "PAY/SALARY"
            }
        ));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 400, 680, 220));

        txtSearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearch2ActionPerformed(evt);
            }
        });
        txtSearch2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearch2KeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 352, 230, 40));

        searchBtn2.setText("SEARCH");
        searchBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtn2ActionPerformed(evt);
            }
        });
        jPanel1.add(searchBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 350, 130, 40));

        lblPayingDate.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        lblPayingDate.setForeground(java.awt.Color.white);
        lblPayingDate.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblPayingDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 200, 40));

        lblNationalId.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        lblNationalId.setForeground(java.awt.Color.white);
        lblNationalId.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblNationalId, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 200, 40));

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

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("NOTE: USE NATIONAL ID TO SEARCH FOR TRAINERS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 500, 20));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 90, 680, 240));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1170, 640));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1200, 680));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        this.setVisible(false);
        new Home(lblUsername.getText(),imag).setVisible(true);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void clearBnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBnActionPerformed
        lblCoachNo.setText("");
        lblNationalId.setText("");
        lblName.setText("");
        txtCater.setText("");
        txtPay.setText("");
        txtCater.requestFocus();
        current_date();
        payBtn.setEnabled(true);
        editPay.setEnabled(false);
        deleteBtn.setEnabled(false);
    }//GEN-LAST:event_clearBnActionPerformed

    private void payBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payBtnActionPerformed
        String coachNo=lblCoachNo.getText();
        String payingDate=lblPayingDate.getText();
        String nationalId=lblNationalId.getText();
        String name=lblName.getText();
        String cater=txtCater.getText();
        int pay=Integer.parseInt(txtPay.getText());
        
        if(cater.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter Payment catering period eg: 1 week, null caters cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtCater.requestFocus();
        }else if(txtPay.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter payment, null payments cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtCater.requestFocus();
        }else{
            try {
                pst=con.prepareStatement("insert into payTrainer (coachNo,payingDate,nationalId,name,cater,payment) values (?,?,?,?,?,?)");
                pst.setString(1,coachNo);
                pst.setString(2,payingDate);             
                pst.setString(3,nationalId);
                pst.setString(4,name);
                pst.setString(5,cater);
                pst.setInt(6,pay);
                int count = pst.executeUpdate();
                if (count > 0) {
                    JOptionPane.showMessageDialog(this, "Trainer paid Successfully ","Success Message",JOptionPane.INFORMATION_MESSAGE);
                    lblCoachNo.setText("");
                    lblNationalId.setText("");
                    lblName.setText("");
                    txtCater.setText("");
                    txtPay.setText("");
                    txtCater.requestFocus();
                    current_date();
                    payTrainer_table();
                    pst=con.prepareStatement("insert into trainerPay(coachNo,payment) values (?,?)");
                    pst.setString(1,coachNo);
                    pst.setInt(2,pay);
                    pst.executeUpdate();
                } else {
                    JOptionPane.showMessageDialog(this, "An Error occured while trying to send your details into the database, please try again","Error Message",JOptionPane.CANCEL_OPTION);
                }
            } catch (SQLException ex) {
                Logger.getLogger(PayTrainers.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
    }//GEN-LAST:event_payBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Do you want to exit system?","GYM Management System",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private String path1;
    private void txtPayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPayKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String coachNo=lblCoachNo.getText();
        String payingDate=lblPayingDate.getText();
        String nationalId=lblNationalId.getText();
        String name=lblName.getText();
        String cater=txtCater.getText();
        int pay=Integer.parseInt(txtPay.getText());
        
        if(cater.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter Payment catering period eg: 1 week, null caters cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtCater.requestFocus();
        }else if(txtPay.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter payment, null payments cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtCater.requestFocus();
        }else{
            try {
                pst=con.prepareStatement("insert into payTrainer (coachNo,payingDate,nationalId,name,cater,payment) values (?,?,?,?,?,?)");
                pst.setString(1,coachNo);
                pst.setString(2,payingDate);             
                pst.setString(3,nationalId);
                pst.setString(4,name);
                pst.setString(5,cater);
                pst.setInt(6,pay);
                int count = pst.executeUpdate();
                if (count > 0) {
                    JOptionPane.showMessageDialog(this, "Trainer paid Successfully ","Success Message",JOptionPane.INFORMATION_MESSAGE);
                    lblCoachNo.setText("");
                    lblNationalId.setText("");
                    lblName.setText("");
                    txtCater.setText("");
                    txtPay.setText("");
                    txtCater.requestFocus();
                    current_date();
                    payTrainer_table();
                    pst=con.prepareStatement("insert into trainerPay(coachNo,payment) values (?,?)");
                    pst.setString(1,coachNo);
                    pst.setInt(2,pay);
                    pst.executeUpdate();
                } else {
                    JOptionPane.showMessageDialog(this, "An Error occured while trying to send your details into the database, please try again","Error Message",JOptionPane.CANCEL_OPTION);
                }
            } catch (SQLException ex) {
                Logger.getLogger(PayTrainers.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtCater.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtCater.requestFocus();
        }
    }//GEN-LAST:event_txtPayKeyPressed

    private void txtCaterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCaterKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtPay.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtPay.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtPay.requestFocus();
        }
    }//GEN-LAST:event_txtCaterKeyPressed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        String coachNo=lblCoachNo.getText();
            try {
                pst=con.prepareStatement("delete from payTrainer where coachNo=?");           
                pst.setString(1, coachNo);
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(this,"Trainer's payment deleted Successfully from database","Success Message",JOptionPane.INFORMATION_MESSAGE);
                lblCoachNo.setText("");
                lblNationalId.setText("");
                lblName.setText("");
                txtCater.setText("");
                txtPay.setText("");
                txtCater.requestFocus();
                current_date();
                payTrainer_table();
                
                    } catch (SQLException ex) {
                Logger.getLogger(PayTrainers.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void editPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPayActionPerformed
        String coachNo=lblCoachNo.getText();
        String payingDate=lblPayingDate.getText();
        String nationalId=lblNationalId.getText();
        String name=lblName.getText();
        String cater=txtCater.getText();
        int pay=Integer.parseInt(txtPay.getText());
        
        if(cater.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter Payment catering period eg: 1 week, null caters cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtCater.requestFocus();
        }else if(txtPay.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter payment, null payments cannot be submitted","Submission Error",JOptionPane.CANCEL_OPTION);
            txtCater.requestFocus();
        }else{
            try {
                pst=con.prepareStatement("update payTrainer set payingDate=?, nationalId=?, name=?, cater=?, payment=? where coachNo=?");
                pst.setString(1,payingDate);             
                pst.setString(2,nationalId);
                pst.setString(3,name);
                pst.setString(4,cater);
                pst.setInt(5,pay);
                pst.setString(6,coachNo);
                int count = pst.executeUpdate();
                if (count > 0) {
                    JOptionPane.showMessageDialog(this, "Payment updated Successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
                    lblCoachNo.setText("");
                    lblNationalId.setText("");
                    lblName.setText("");
                    txtCater.setText("");
                    txtPay.setText("");
                    txtCater.requestFocus();
                    current_date();
                    payTrainer_table();
                    pst=con.prepareStatement("update trainerPay set payment=? where coachNo=?");
                    pst.setInt(1,pay);
                    pst.setString(2,coachNo);
                    pst.executeUpdate();
                } else {
                    JOptionPane.showMessageDialog(this, "An Error occured while trying to send your details into the database, please try again","Error Message",JOptionPane.CANCEL_OPTION);
                }
            } catch (SQLException ex) {
                Logger.getLogger(PayTrainers.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
    }//GEN-LAST:event_editPayActionPerformed

    private void txtSearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearch2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearch2ActionPerformed

    private void searchBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtn2ActionPerformed
        String coachNo=txtSearch2.getText();
        
        try {
            pst=con.prepareStatement("select * from payTrainer where coachNo like CONCAT('%',?,'%')");
            pst.setString(1,coachNo);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i <= c; i++) {
                    v.add(rs.getString("coachNo"));
                    v.add(rs.getString("payingDate"));
                    v.add(rs.getString("nationalId"));
                    v.add(rs.getString("name"));
                    v.add(rs.getString("cater"));
                    v.add(rs.getInt("payment"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PayTrainers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchBtn2ActionPerformed

    private void txtSearch2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String coachNo=txtSearch2.getText();
        
        try {
            pst=con.prepareStatement("select * from payTrainer where coachNo like CONCAT('%',?,'%')");
            pst.setString(1,coachNo);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i <= c; i++) {
                    v.add(rs.getString("coachNo"));
                    v.add(rs.getString("payingDate"));
                    v.add(rs.getString("nationalId"));
                    v.add(rs.getString("name"));
                    v.add(rs.getString("cater"));
                    v.add(rs.getInt("payment"));
                }
                model.addRow(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PayTrainers.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_txtSearch2KeyPressed

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        DefaultTableModel model=(DefaultTableModel)table1.getModel();
        int selectedIndex=table1.getSelectedRow();
        
        lblCoachNo.setText(model.getValueAt(selectedIndex, 0).toString());
        lblPayingDate.setText(model.getValueAt(selectedIndex, 1).toString());
        lblNationalId.setText(model.getValueAt(selectedIndex, 2).toString());
        lblName.setText(model.getValueAt(selectedIndex, 3).toString());
        txtCater.setText(model.getValueAt(selectedIndex, 4).toString());
        txtPay.setText(model.getValueAt(selectedIndex, 5).toString());
        
        payBtn.setEnabled(false);
        editPay.setEnabled(true);
        deleteBtn.setEnabled(true);
    }//GEN-LAST:event_table1MouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        editPay.setEnabled(false);
        deleteBtn.setEnabled(false);
    }//GEN-LAST:event_formMouseClicked

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

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        DefaultTableModel model=(DefaultTableModel)table.getModel();
        int selectedIndex=table.getSelectedRow();

        lblCoachNo.setText(model.getValueAt(selectedIndex, 0).toString());
        lblNationalId.setText(model.getValueAt(selectedIndex, 2).toString());
        lblName.setText(model.getValueAt(selectedIndex, 3).toString());
    }//GEN-LAST:event_tableMouseClicked

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
            java.util.logging.Logger.getLogger(PayTrainers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PayTrainers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PayTrainers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PayTrainers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PayTrainers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearBn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editPay;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblCoachNo;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNationalId;
    private javax.swing.JLabel lblPayingDate;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JButton payBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JButton searchBtn2;
    private javax.swing.JTable table;
    private javax.swing.JTable table1;
    private javax.swing.JTextField txtCater;
    private javax.swing.JTextField txtPay;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearch2;
    // End of variables declaration//GEN-END:variables
}
