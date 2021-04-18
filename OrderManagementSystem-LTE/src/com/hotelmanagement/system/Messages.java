/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelmanagement.system;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bill
 */
public class Messages extends javax.swing.JFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    /**
     * Creates new form ManageClients
     */
    public Messages() {
        initComponents();
    }
    
    String username;
    BufferedImage imag;
    String userType;
    public Messages(String username,BufferedImage imag, String UserType) {
        initComponents();
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("emmanuel-chigbo-jr7R01lOn1Y-unsplash.jpg")).getImage().getScaledInstance(1240, 720, Image.SCALE_SMOOTH)));
        this.userType=UserType;
        lblUserType.setText(UserType);
        Connect();
        Auto_id();
        message_store_table();
        message_finance_table();
        current_date();
        lblSender.setText(username);
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
    /*public void Connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:Hotel.db");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    public void Auto_id(){
        try {
            Statement s=con.createStatement();
            rs=s.executeQuery("select MAX(messageNo) from messageStore");
            rs.next();
            rs.getString("MAX(messageNo)");
            if(rs.getString("MAX(messageNo)")==null){
                lblMessageNo.setText("MS00001");
            }else{
                long id=Long.parseLong(rs.getString("MAX(messageNo)").substring(2,rs.getString("MAX(messageNo)").length()));
                id++;
                lblMessageNo.setText("MS"+String.format("%05d", id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Messages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void message_store_table(){
        try {
            pst=con.prepareStatement("select * from messageStore");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("messageNo"));
                    v.add(rs.getString("messageBody"));
                    v.add(rs.getString("date"));
                    v.add(rs.getString("sender"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Messages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void message_finance_table(){
        try {
            pst=con.prepareStatement("select * from messageFinance");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("date"));
                    v.add(rs.getString("messageNo"));
                    v.add(rs.getString("sender"));
                    v.add(rs.getString("messageBody"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Messages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void current_date(){
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now=LocalDateTime.now();
        String date=dtf.format(now);
        lblDate.setText(date);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        homeBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        lblDate = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblProfile = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblUserType = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMessageSend = new javax.swing.JTextArea();
        lblMessageNo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblSender = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMessageReceive = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        replyDeleteBtn = new javax.swing.JToggleButton();
        clearBtn = new javax.swing.JButton();
        clearBtn1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MESSAGE FINANCE DEPARTMENT");
        setMaximizedBounds(new java.awt.Rectangle(1236, 718, 718, 718));
        setMinimumSize(new java.awt.Dimension(1236, 718));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(89, 23, 166));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("MESSAGE NO:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("DATE:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MSG NO", "BODY", "DATE", "SENDER"
            }
        ));
        table1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 740, 190));

        homeBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        homeBtn.setForeground(new java.awt.Color(36, 39, 246));
        homeBtn.setText("HOME");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 90, 50));

        addBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        addBtn.setForeground(new java.awt.Color(0, 22, 255));
        addBtn.setText("SEND");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, 90, 50));

        editBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        editBtn.setForeground(new java.awt.Color(33, 158, 33));
        editBtn.setText("EDIT");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        jPanel1.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, 80, 50));

        exitBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(238, 13, 24));
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 300, 100, 50));

        deleteBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(238, 13, 24));
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, 90, 50));

        lblDate.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblDate.setForeground(java.awt.Color.white);
        lblDate.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 280, 40));

        jLabel7.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("MSG BODY:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, 30));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 0, 80, 80));

        lblUsername.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblUsername.setForeground(java.awt.Color.white);
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("USERNAME");
        jPanel1.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 30, 180, 20));

        lblUserType.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblUserType.setForeground(java.awt.Color.white);
        lblUserType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserType.setText("USER TYPE");
        jPanel1.add(lblUserType, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, 220, 20));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 310, 40));

        btnSearch1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnSearch1.setText("SEARCH WITH DATE");
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 50, 310, 40));

        txtMessageSend.setColumns(20);
        txtMessageSend.setRows(5);
        jScrollPane2.setViewportView(txtMessageSend);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 280, 120));

        lblMessageNo.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblMessageNo.setForeground(java.awt.Color.white);
        lblMessageNo.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblMessageNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 280, 40));

        jLabel4.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("SENT BY:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        lblSender.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblSender.setForeground(java.awt.Color.white);
        lblSender.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblSender, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 280, 40));

        jPanel2.setBackground(new java.awt.Color(167, 93, 92));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "REPLIES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 3, 18), java.awt.Color.white)); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtMessageReceive.setColumns(20);
        txtMessageReceive.setRows(5);
        jScrollPane3.setViewportView(txtMessageReceive);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 410, 190));

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE", "MSG NO", "SENDER", "BODY"
            }
        ));
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(table2);

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, 490, 170));

        replyDeleteBtn.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        replyDeleteBtn.setText("DELETE REPLY");
        replyDeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replyDeleteBtnActionPerformed(evt);
            }
        });
        jPanel2.add(replyDeleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 170, 170, 40));

        clearBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(128, 13, 238));
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel2.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 20, 90, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 1180, 230));

        clearBtn1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        clearBtn1.setForeground(new java.awt.Color(128, 13, 238));
        clearBtn1.setText("CLEAR");
        clearBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtn1ActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, 90, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 1210, 620));

        jLabel1.setFont(new java.awt.Font("Tibetan Machine Uni", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(45, 13, 249));
        jLabel1.setText("SEND MESSAGE TO FINANCE DEPARTMENT");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 50));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        this.setVisible(false);
        new Main(lblUsername.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        String messageNo=lblMessageNo.getText();       
        try {
            pst=con.prepareStatement("delete from messageStore where messageNo=?");
            pst.setString(1,messageNo);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Message has been Deleted Successfully from database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            lblMessageNo.setText("");
            txtMessageSend.setText("");
            lblDate.setText("");
            lblSender.setText("");
            txtMessageReceive.setText("");
            txtMessageSend.requestFocus();
            message_store_table();
            message_finance_table();
            Auto_id();
            current_date();
            lblSender.setText(username);
            addBtn.setEnabled(true);
            editBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
            replyDeleteBtn.setEnabled(false);
        } catch (SQLException ex) {
            Logger.getLogger(Messages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to exit Hotel System?","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String messageNo=lblMessageNo.getText();
        String messageBody=txtMessageSend.getText();
        String date=lblDate.getText();
        String sender=lblSender.getText();
        
        if(messageBody.trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please type your message on the message body, null messages cannot be sent","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtMessageSend.requestFocus();
        }else{
        try {
            pst=con.prepareStatement("insert into messageStore (messageNo,messageBody,date,sender)values(?,?,?,?)");
            pst.setString(1,messageNo);
            pst.setString(2,messageBody);
            pst.setString(3,date);
            pst.setString(4,sender);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Message sent Successfully to finance department","Success Message",JOptionPane.INFORMATION_MESSAGE);
            lblMessageNo.setText("");
            txtMessageSend.setText("");
            lblDate.setText("");
            lblSender.setText("");
            txtMessageReceive.setText("");
            txtMessageSend.requestFocus();
            message_store_table();
            message_finance_table();
            Auto_id();
            current_date();
            lblSender.setText(username);
            addBtn.setEnabled(true);
            editBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
            replyDeleteBtn.setEnabled(false);
        } catch (SQLException ex) {
            Logger.getLogger(Messages.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        lblMessageNo.setText("");
        txtMessageSend.setText("");
        lblDate.setText("");
        lblSender.setText("");
        txtMessageReceive.setText("");
        txtMessageSend.requestFocus();
        message_store_table();
        message_finance_table();
        Auto_id();
        addBtn.setEnabled(true);
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        replyDeleteBtn.setEnabled(false);
    }//GEN-LAST:event_clearBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        String messageNo=lblMessageNo.getText();
        String messageBody=txtMessageSend.getText();
        String date=lblDate.getText();
        String sender=lblSender.getText();
        
        if(messageBody.trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please type your message on the message body, null messages cannot be sent","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtMessageSend.requestFocus();
        }else{
        try {
            pst=con.prepareStatement("update messageStore set messageBody=? where messageNo=?");
            pst.setString(1,messageBody);
            pst.setString(2,messageNo);
            pst.execute();
            JOptionPane.showMessageDialog(this,"Finance department message Updated Successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
            lblMessageNo.setText("");
            txtMessageSend.setText("");
            lblDate.setText("");
            lblSender.setText("");
            txtMessageReceive.setText("");
            txtMessageSend.requestFocus();
            message_store_table();
            message_finance_table();
            Auto_id();
            current_date();
            lblSender.setText(username);
            addBtn.setEnabled(true);
            editBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
            replyDeleteBtn.setEnabled(false);
        } catch (SQLException ex) {
            Logger.getLogger(Messages.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_editBtnActionPerformed
    
    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        DefaultTableModel model=(DefaultTableModel)table1.getModel();
        int selectedIndex=table1.getSelectedRow();
        lblMessageNo.setText(model.getValueAt(selectedIndex, 0).toString());
        txtMessageSend.setText(model.getValueAt(selectedIndex, 1).toString());
        lblDate.setText(model.getValueAt(selectedIndex, 2).toString());
        lblSender.setText(model.getValueAt(selectedIndex, 3).toString());
        
        addBtn.setEnabled(false);
        editBtn.setEnabled(true);
        deleteBtn.setEnabled(true);
    }//GEN-LAST:event_table1MouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        replyDeleteBtn.setEnabled(false);
    }//GEN-LAST:event_formWindowActivated

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String date=txtSearch.getText();
            try {
                pst=con.prepareStatement("select * from messageStore WHERE date LIKE CONCAT('%',?,'%')");
                pst.setString(1, date);
                rs=pst.executeQuery();
                ResultSetMetaData rsm=rs.getMetaData();
                int c=rsm.getColumnCount();

                DefaultTableModel model=(DefaultTableModel)table1.getModel();
                model.setRowCount(0);
                while(rs.next()){
                    Vector v=new Vector();
                    for(int i=1;i<c;i++){
                        v.add(rs.getString("messageNo"));
                        v.add(rs.getString("messageBody"));
                        v.add(rs.getString("date"));
                        v.add(rs.getString("sender"));
                    }
                    model.addRow(v);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Messages.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
        String date=txtSearch.getText();
        try {
            pst=con.prepareStatement("select * from messageStore WHERE date LIKE CONCAT('%',?,'%')");
            pst.setString(1, date);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("messageNo"));
                    v.add(rs.getString("messageBody"));
                    v.add(rs.getString("date"));
                    v.add(rs.getString("sender"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Messages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearch1ActionPerformed

    private void replyDeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replyDeleteBtnActionPerformed
        DefaultTableModel model=(DefaultTableModel)table2.getModel();
        int selectedIndex=table2.getSelectedRow();
        String messageNo=model.getValueAt(selectedIndex, 1).toString();
        try {
            pst=con.prepareStatement("delete from messageFinance where messageNo=?");
            pst.setString(1,messageNo);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Message has been Deleted Successfully from database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            txtMessageSend.setText("");
            txtMessageReceive.setText("");
            txtMessageSend.requestFocus();
            message_store_table();
            message_finance_table();
            Auto_id();
            addBtn.setEnabled(true);
            editBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
            replyDeleteBtn.setEnabled(false);
        } catch (SQLException ex) {
            Logger.getLogger(Messages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_replyDeleteBtnActionPerformed

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        DefaultTableModel model=(DefaultTableModel)table2.getModel();
        int selectedIndex=table2.getSelectedRow();
        txtMessageReceive.setText(model.getValueAt(selectedIndex, 3).toString());
        
        replyDeleteBtn.setEnabled(true);
    }//GEN-LAST:event_table2MouseClicked

    private void clearBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtn1ActionPerformed
        lblMessageNo.setText("");
        txtMessageSend.setText("");
        lblDate.setText("");
        lblSender.setText("");
        txtMessageReceive.setText("");
        txtMessageSend.requestFocus();
        message_store_table();
        message_finance_table();
        Auto_id();
        current_date();
        lblSender.setText(username);
        addBtn.setEnabled(true);
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        replyDeleteBtn.setEnabled(false);
    }//GEN-LAST:event_clearBtn1ActionPerformed

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
            java.util.logging.Logger.getLogger(Messages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Messages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Messages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Messages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Messages().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton clearBtn1;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblMessageNo;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblSender;
    private javax.swing.JLabel lblUserType;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JToggleButton replyDeleteBtn;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JTextArea txtMessageReceive;
    private javax.swing.JTextArea txtMessageSend;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
