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
public class SuppliersItems extends javax.swing.JFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    /**
     * Creates new form ManageClients
     */
    public SuppliersItems() {
        initComponents();
    }
    
    String username;
    BufferedImage imag;
    public SuppliersItems(String username, BufferedImage imag) {
        initComponents();
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH)));
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("emmanuel-chigbo-jr7R01lOn1Y-unsplash.jpg")).getImage().getScaledInstance(1240, 720, Image.SCALE_SMOOTH)));
        Connect();
        Connect();
        current_date();
        Auto_id();
        Supplier_table();
    }
    
    
    public void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/Hotel","root","");
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
            rs=s.executeQuery("select MAX(itemNumber) from suppliers");
            rs.next();
            rs.getString("MAX(itemNumber)");
            if(rs.getString("MAX(itemNumber)")==null){
                lblItemNo.setText("IT001");
            }else{
                long id=Long.parseLong(rs.getString("MAX(itemNumber)").substring(2,rs.getString("MAX(itemNumber)").length()));
                id++;
                lblItemNo.setText("IT"+String.format("%03d", id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StockInStore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Supplier_table(){
        try {
            pst=con.prepareStatement("select * from suppliers");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("date"));
                    v.add(rs.getString("itemNumber"));
                    v.add(rs.getString("itemname"));
                    v.add(rs.getString("description"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getInt("quantity"));
                    v.add(rs.getString("supplier"));
                    v.add(rs.getString("phone"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SuppliersItems.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void current_date(){
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now=LocalDateTime.now();
        String date=dtf.format(now);
        lblDate.setText(date);
    }
    public void receipt_table(){
        int price=Integer.parseInt(txtPrice.getText());
        int quantity=Integer.parseInt(txtQty.getText());
        int total=price*quantity;
        
        DefaultTableModel model=(DefaultTableModel)table1.getModel();
        model.addRow(new Object[]{
            lblItemNo.getText(),
            txtItemName.getText(),
            price,
            quantity,
            total
        });
        
        int sum=0;
        for(int i=0;i<table1.getRowCount();i++){
            int sum2=(int)table1.getValueAt(i, 4);
            sum=sum+sum2;
        }
        txtSubTotal.setText(String.valueOf(sum));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsername = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        txtQty = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        homeBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        lblItemNo = new javax.swing.JLabel();
        txtItemName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnAllocate1 = new javax.swing.JButton();
        bnDelete = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSupplierName = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        bookRoomBtn1 = new javax.swing.JButton();
        printReceipt1 = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        txtSubTotal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        lblProfile = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MANAGE CLIENTS");
        setMaximizedBounds(new java.awt.Rectangle(1236, 718, 718, 718));
        setMinimumSize(new java.awt.Dimension(1236, 718));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUsername.setFont(new java.awt.Font("Ubuntu", 3, 24)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(38, 16, 241));
        lblUsername.setText("USERNAME");
        getContentPane().add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 20, 170, -1));

        jPanel1.setBackground(new java.awt.Color(79, 197, 115));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("ITEM NUMBER:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        txtDescription.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescriptionKeyPressed(evt);
            }
        });
        jPanel1.add(txtDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 220, 40));

        jLabel3.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("DESCRIPTION:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("ITEM PRICE:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        txtPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPriceKeyPressed(evt);
            }
        });
        jPanel1.add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, 220, 40));

        txtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQtyKeyPressed(evt);
            }
        });
        jPanel1.add(txtQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 260, 220, 40));

        jLabel5.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("QUANTITY:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE", "ITEM NO", "ITEM NAME", "DESCRIPTION", "PRICE", "QUANTITY", "SUPPLIER", "PHONE NO"
            }
        ));
        table2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table2);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 350, 720, 260));

        homeBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        homeBtn.setForeground(new java.awt.Color(36, 39, 246));
        homeBtn.setText("HOME PAGE");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 130, 50));

        addBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        addBtn.setForeground(new java.awt.Color(0, 22, 255));
        addBtn.setText("ADD ITEM");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 150, 50));

        editBtn.setBackground(new java.awt.Color(148, 61, 192));
        editBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        editBtn.setForeground(java.awt.Color.white);
        editBtn.setText("EDIT ITEM");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        jPanel1.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 410, 130, 50));

        exitBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(238, 13, 24));
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 470, 130, 50));

        clearBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(128, 13, 238));
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 130, 50));

        deleteBtn.setBackground(new java.awt.Color(239, 36, 29));
        deleteBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        deleteBtn.setForeground(java.awt.Color.white);
        deleteBtn.setText("DELETE ITEM");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 470, 150, 50));

        lblItemNo.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblItemNo.setForeground(java.awt.Color.white);
        lblItemNo.setText("ITEM NUMBER");
        lblItemNo.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblItemNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 220, 40));

        txtItemName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtItemNameKeyPressed(evt);
            }
        });
        jPanel1.add(txtItemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 220, 40));

        jLabel7.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("ITEM NAME:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        btnAllocate1.setBackground(new java.awt.Color(233, 26, 26));
        btnAllocate1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnAllocate1.setForeground(java.awt.Color.white);
        btnAllocate1.setText("TABLE TO PDF");
        btnAllocate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllocate1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnAllocate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 170, 50));

        bnDelete.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        bnDelete.setForeground(new java.awt.Color(245, 15, 70));
        bnDelete.setText("DELETE");
        bnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(bnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 190, 90, 50));

        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 300, 100, 40));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 300, 250, 40));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("NOTE: USE ITEM NAMES TO SEARCH FOR ITEMS");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 350, 30));

        lblDate.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblDate.setForeground(java.awt.Color.white);
        lblDate.setText("DATE");
        lblDate.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 220, 40));

        jLabel8.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel8.setForeground(java.awt.Color.white);
        jLabel8.setText("DATE:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txtSupplierName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSupplierNameKeyPressed(evt);
            }
        });
        jPanel1.add(txtSupplierName, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, 220, 40));

        jLabel9.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel9.setForeground(java.awt.Color.white);
        jLabel9.setText("SUPPLIER'S NAME:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        txtPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPhoneKeyPressed(evt);
            }
        });
        jPanel1.add(txtPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 360, 220, 40));

        jLabel10.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel10.setForeground(java.awt.Color.white);
        jLabel10.setText("SUBTOTAL:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 190, -1, -1));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ITEM NO", "ITEM NAME", "PRICE", "QUANTITY", "TOTAL"
            }
        ));
        jScrollPane2.setViewportView(table1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 720, 170));

        bookRoomBtn1.setBackground(new java.awt.Color(145, 36, 200));
        bookRoomBtn1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        bookRoomBtn1.setForeground(java.awt.Color.white);
        bookRoomBtn1.setText("TABLE TO EXCEL");
        bookRoomBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookRoomBtn1ActionPerformed(evt);
            }
        });
        jPanel1.add(bookRoomBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 530, 170, 50));

        printReceipt1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        printReceipt1.setForeground(new java.awt.Color(15, 37, 245));
        printReceipt1.setText("PRINT");
        printReceipt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printReceipt1ActionPerformed(evt);
            }
        });
        jPanel1.add(printReceipt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 190, 80, 50));

        btnClear.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnClear.setForeground(new java.awt.Color(15, 37, 245));
        btnClear.setText("CLEAR");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        jPanel1.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 190, 90, 50));

        txtSubTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSubTotalKeyPressed(evt);
            }
        });
        jPanel1.add(txtSubTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 190, 220, 40));

        jLabel11.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel11.setForeground(java.awt.Color.white);
        jLabel11.setText("SUPPLIER'S PHONE:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 250, 90, 90));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 1210, 620));

        jLabel1.setFont(new java.awt.Font("Tibetan Machine Uni", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(45, 13, 249));
        jLabel1.setText("SUPPLIERS BASE");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, -1, 50));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        this.setVisible(false);
        new Main(lblUsername.getText(),imag).setVisible(true);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        String itemNo=lblItemNo.getText();       
        try {
            pst=con.prepareStatement("delete from suppliers where itemNumber=?");
            pst.setString(1,itemNo);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Item Deleted Successfully from database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            txtItemName.setText("");
            txtDescription.setText("");
            txtPrice.setText("");
            txtQty.setText("");
            txtPrice.setText("");
            txtSupplierName.setText("");
            txtPhone.setText("");
            txtItemName.requestFocus();
            Supplier_table();
        } catch (SQLException ex) {
            Logger.getLogger(SuppliersItems.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to exit Hotel System?","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String date=lblDate.getText();
        String itemNumber=lblItemNo.getText();
        String itemName=txtItemName.getText();
        String description=txtDescription.getText();
        int price=Integer.parseInt(txtPrice.getText());
        int quantity=Integer.parseInt(txtQty.getText());
        String supplier=txtSupplierName.getText();
        String phone=txtPhone.getText();
        
        if(itemName.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter item name, null item names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtItemName.requestFocus();
        }else if(description.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter item description, null item descriptions cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtDescription.requestFocus();
        }else if(txtPrice.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter item price, null item prices cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPrice.requestFocus();
        }else if(txtQty.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter quantity, null quantities cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtQty.requestFocus();
        }else if(supplier.equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter supplier's name, null names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtSupplierName.requestFocus();
        }else if(phone.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter supplier's phone number, null phone numbers cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPhone.requestFocus();
        }else{
        try {
            pst=con.prepareStatement("insert into suppliers (date,itemNumber,itemname,description,price,quantity,supplier,phone)values(?,?,?,?,?,?,?,?)");
            pst.setString(1,date);
            pst.setString(2,itemNumber);
            pst.setString(3,itemName);
            pst.setString(4,description);
            pst.setInt(5,price);
            pst.setInt(6,quantity);
            pst.setString(7,supplier);
            pst.setString(8,phone);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Item Added Successfully into database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            receipt_table();
            Auto_id();
            current_date();
            txtItemName.setText("");
            txtDescription.setText("");
            txtPrice.setText("");
            txtQty.setText("");
            txtPrice.setText("");
            txtSupplierName.setText("");
            txtPhone.setText("");
            txtItemName.requestFocus();
            Supplier_table();
        } catch (SQLException ex) {
            Logger.getLogger(SuppliersItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        txtItemName.setText("");
        txtDescription.setText("");
        txtPrice.setText("");
        txtQty.setText("");
        txtPrice.setText("");
        txtSupplierName.setText("");
        txtPhone.requestFocus();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        String date=lblDate.getText();
        String itemName=txtItemName.getText();
        String description=txtDescription.getText();
        int price=Integer.parseInt(txtPrice.getText());
        int quantity=Integer.parseInt(txtQty.getText());
        String supplier=txtSupplierName.getText();
        String phone=txtPhone.getText();
        String itemNumber=lblItemNo.getText();
        
        if(itemName.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter item name, null item names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtItemName.requestFocus();
        }else if(description.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter item description, null item descriptions cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtDescription.requestFocus();
        }else if(txtPrice.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter item price, null item prices cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPrice.requestFocus();
        }else if(txtQty.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter quantity, null quantities cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtQty.requestFocus();
        }else if(supplier.equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter supplier's name, null names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtSupplierName.requestFocus();
        }else if(phone.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter supplier's phone number, null phone numbers cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPhone.requestFocus();
        }else{
        try {
            pst=con.prepareStatement("update suppliers set date=?, itemname=?, description=?, price=?, quantity=?, supplier=?, phone=?  where itemNumber=?");
            pst.setString(1,date);
            pst.setString(2,itemName);
            pst.setString(3,description);
            pst.setInt(4,price);
            pst.setInt(5,quantity);
            pst.setString(6,supplier);
            pst.setString(7,phone);
            pst.setString(8,itemNumber);
            pst.execute();
            JOptionPane.showMessageDialog(this,"Item Updated Successfully on database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            Auto_id();
            current_date();
            txtItemName.setText("");
            txtDescription.setText("");
            txtPrice.setText("");
            txtQty.setText("");
            txtPrice.setText("");
            txtSupplierName.setText("");
            txtPhone.setText("");
            txtItemName.requestFocus();
            Supplier_table();
        } catch (SQLException ex) {
            Logger.getLogger(SuppliersItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_editBtnActionPerformed
    
    private void txtDescriptionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescriptionKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtPrice.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtPrice.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtItemName.requestFocus();
        }
    }//GEN-LAST:event_txtDescriptionKeyPressed

    private void txtPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPriceKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtQty.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtQty.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtDescription.requestFocus();
        }
    }//GEN-LAST:event_txtPriceKeyPressed

    private void txtQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtSupplierName.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtSupplierName.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtPrice.requestFocus();
        }
    }//GEN-LAST:event_txtQtyKeyPressed

    private void txtItemNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtItemNameKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtDescription.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtDescription.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtPhone.requestFocus();
        }
    }//GEN-LAST:event_txtItemNameKeyPressed

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        DefaultTableModel model=(DefaultTableModel)table2.getModel();
        int selectedIndex=table2.getSelectedRow();
        lblDate.setText(model.getValueAt(selectedIndex, 0).toString());
        lblItemNo.setText(model.getValueAt(selectedIndex, 1).toString());
        txtItemName.setText(model.getValueAt(selectedIndex, 2).toString());
        txtDescription.setText(model.getValueAt(selectedIndex, 3).toString());
        txtPrice.setText(model.getValueAt(selectedIndex, 4).toString());
        txtQty.setText(model.getValueAt(selectedIndex, 5).toString());
        txtSupplierName.setText(model.getValueAt(selectedIndex, 6).toString());
        txtPhone.setText(model.getValueAt(selectedIndex, 7).toString());
        
        editBtn.setEnabled(true);
        deleteBtn.setEnabled(true);
    }//GEN-LAST:event_table2MouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
    }//GEN-LAST:event_formWindowActivated

    private void btnAllocate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllocate1ActionPerformed
        MessageFormat header=new MessageFormat("SUPPLIED ITEMS FROM VARIOUS SUPPLIERS");
        MessageFormat footer=new MessageFormat("page {0}");
        try {
            table2.print(JTable.PrintMode.FIT_WIDTH,header,footer);
        } catch (PrinterException ex) {
            Logger.getLogger(BookingRooms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAllocate1ActionPerformed

    private void bnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnDeleteActionPerformed
        DefaultTableModel model=(DefaultTableModel)table1.getModel();
        model.removeRow(table1.getSelectedRow());
        
        int sum=0;
        for(int i=0;i<table1.getRowCount();i++){
            int sum2=(int)table1.getValueAt(i, 4);
            sum=sum+sum2;
        }
        txtSubTotal.setText(String.valueOf(sum));
    }//GEN-LAST:event_bnDeleteActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            pst=con.prepareStatement("select * from suppliers where itemname CONCAT('%',?,'%')");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("date"));
                    v.add(rs.getString("itemNumber"));
                    v.add(rs.getString("itemname"));
                    v.add(rs.getString("description"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getInt("quantity"));
                    v.add(rs.getString("supplier"));
                    v.add(rs.getString("phone"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SuppliersItems.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            try {
            pst=con.prepareStatement("select * from suppliers where itemname CONCAT('%',?,'%')");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("date"));
                    v.add(rs.getString("itemNumber"));
                    v.add(rs.getString("itemname"));
                    v.add(rs.getString("description"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getInt("quantity"));
                    v.add(rs.getString("supplier"));
                    v.add(rs.getString("phone"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SuppliersItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void txtSupplierNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSupplierNameKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtPhone.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtPhone.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtQty.requestFocus();
        }
    }//GEN-LAST:event_txtSupplierNameKeyPressed

    private void txtPhoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhoneKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String date=lblDate.getText();
        String itemNumber=lblItemNo.getText();
        String itemName=txtItemName.getText();
        String description=txtDescription.getText();
        int price=Integer.parseInt(txtPrice.getText());
        int quantity=Integer.parseInt(txtQty.getText());
        String supplier=txtSupplierName.getText();
        String phone=txtPhone.getText();
        
        if(itemName.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter item name, null item names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtItemName.requestFocus();
        }else if(description.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter item description, null item descriptions cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtDescription.requestFocus();
        }else if(txtPrice.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter item price, null item prices cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPrice.requestFocus();
        }else if(txtQty.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter quantity, null quantities cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtQty.requestFocus();
        }else if(supplier.equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter supplier's name, null names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtSupplierName.requestFocus();
        }else if(phone.trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter supplier's phone number, null phone numbers cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPhone.requestFocus();
        }else{
        try {
            pst=con.prepareStatement("insert into suppliers (date,itemNumber,itemname,description,price,quantity,supplier,phone)values(?,?,?,?,?,?,?,?)");
            pst.setString(1,date);
            pst.setString(2,itemNumber);
            pst.setString(3,itemName);
            pst.setString(4,description);
            pst.setInt(5,price);
            pst.setInt(6,quantity);
            pst.setString(7,supplier);
            pst.setString(8,phone);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Item Added Successfully into database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            receipt_table();
            Auto_id();
            current_date();
            txtItemName.setText("");
            txtDescription.setText("");
            txtPrice.setText("");
            txtQty.setText("");
            txtPrice.setText("");
            txtSupplierName.setText("");
            txtPhone.setText("");
            txtItemName.requestFocus();
            Supplier_table();
        } catch (SQLException ex) {
            Logger.getLogger(SuppliersItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtItemName.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtSupplierName.requestFocus();
        }
    }//GEN-LAST:event_txtPhoneKeyPressed

    private void bookRoomBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookRoomBtn1ActionPerformed
        JFileChooser fileChooser=new JFileChooser();
        fileChooser.setDialogTitle("Export table to an excel file.csv format?");
        int userSelection=fileChooser.showSaveDialog(this);
        if(userSelection==JFileChooser.APPROVE_OPTION){
            File file=fileChooser.getSelectedFile();
            
            FileWriter writer;
            try {
                writer=new FileWriter(file);
                BufferedWriter bWriter=new BufferedWriter(writer);
                for (int i = 0; i < table2.getRowCount(); i++) {
                    for (int j = 0; j < table2.getColumnCount(); j++) {
                        bWriter.write(table2.getValueAt(i, j).toString()+",");
                    }
                    bWriter.newLine();
                }
                bWriter.close();
                writer.close();
                JOptionPane.showMessageDialog(this, "Table exported successfully into an excel file .csv format");
                
            } catch (IOException ex) {
                Logger.getLogger(HotelMeals.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_bookRoomBtn1ActionPerformed

    private void printReceipt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printReceipt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_printReceipt1ActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        DefaultTableModel model=(DefaultTableModel)table1.getModel();
        model.setRowCount(0);
        txtSubTotal.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void txtSubTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSubTotalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubTotalKeyPressed

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
            java.util.logging.Logger.getLogger(SuppliersItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SuppliersItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SuppliersItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SuppliersItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new SuppliersItems().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton bnDelete;
    private javax.swing.JButton bookRoomBtn1;
    private javax.swing.JButton btnAllocate1;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblItemNo;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JButton printReceipt1;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtSupplierName;
    // End of variables declaration//GEN-END:variables
}
