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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author bill
 */
public class ManageTenders extends javax.swing.JFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    /**
     * Creates new form ManageClients
     */
    public ManageTenders() {
        initComponents();
    }
    
    String username;
    BufferedImage imag;
    String userType;
    public ManageTenders(String username,BufferedImage imag, String userType) {
        initComponents();
        this.username=username;
        lblUsername1.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("social-3064515_1920.jpg")).getImage().getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), Image.SCALE_SMOOTH)));
        this.userType=userType;
        lblUserType.setText(userType);
        lblIssuedBy.setText(lblUsername1.getText());
        Connect();
        current_date();
        Issued_tenders_table();
        load_suppliers();
        Auto_id();
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


    
    public void Issued_tenders_table(){
        try {
            pst=con.prepareStatement("select * from issuedTenders");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("dateIssued"));
                    v.add(rs.getString("tenderNo"));
                    v.add(rs.getString("itemName"));
                    v.add(rs.getString("description"));
                    v.add(rs.getString("issuedBy"));
                    v.add(rs.getString("availDate"));
                    v.add(rs.getInt("quantity"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("supplier1"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageTenders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Auto_id(){
        try {
            Statement s=con.createStatement();
            rs=s.executeQuery("select MAX(tenderNo) from issuedTenders");
            rs.next();
            rs.getString("MAX(tenderNo)");
            if(rs.getString("MAX(tenderNo)")==null){
                lblTenderNo.setText("TD00001");
            }else{
                long id=Long.parseLong(rs.getString("MAX(tenderNo)").substring(2,rs.getString("MAX(tenderNo)").length()));
                id++;
                lblTenderNo.setText("TD"+String.format("%05d", id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StockInStore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void load_suppliers(){
        try {
            pst=con.prepareStatement("select * from suppliers");
            rs=pst.executeQuery();
            //DefaultComboBoxModel model=(DefaultComboBoxModel)combDepartment.getModel();
            while(rs.next()){
                combSupplier.addItem(rs.getString("supplier"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(IssueItems.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void current_date(){
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now=LocalDateTime.now();
        String date=dtf.format(now);
        lblDateIssued.setText(date);
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
        homeBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        lblIssuedBy = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblDateIssued = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblProfile = new javax.swing.JLabel();
        lblUsername1 = new javax.swing.JLabel();
        lblUserType = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        searchText = new javax.swing.JTextField();
        searchBtn = new javax.swing.JToggleButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        deleteBtn = new javax.swing.JToggleButton();
        editBtn = new javax.swing.JToggleButton();
        txtItemName = new javax.swing.JTextField();
        txtDescription = new javax.swing.JTextField();
        combSupplier = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        availDate = new com.toedter.calendar.JDateChooser();
        lblTenderNo = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jasperPrint = new javax.swing.JButton();
        csvExport = new javax.swing.JButton();
        printTable = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MANAGE TENDERS");
        setMaximizedBounds(new java.awt.Rectangle(1236, 718, 718, 718));
        setMinimumSize(new java.awt.Dimension(1236, 718));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(59, 142, 40));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("TENDER NO:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        homeBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        homeBtn.setForeground(new java.awt.Color(36, 39, 246));
        homeBtn.setText("HOME");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 90, 50));

        addBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        addBtn.setForeground(new java.awt.Color(0, 22, 255));
        addBtn.setText("AWARD TENDER");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 460, 160, 50));

        exitBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(238, 13, 24));
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 530, 160, 50));

        clearBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(128, 13, 238));
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 90, 50));

        lblIssuedBy.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblIssuedBy.setForeground(java.awt.Color.white);
        lblIssuedBy.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblIssuedBy, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 250, 40));

        jLabel7.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("ITEM NAME:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        lblDateIssued.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblDateIssued.setForeground(java.awt.Color.white);
        lblDateIssued.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblDateIssued, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 250, 40));

        jLabel8.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel8.setForeground(java.awt.Color.white);
        jLabel8.setText("DATE ISSUED:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 170, -1));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 0, 80, 80));

        lblUsername1.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblUsername1.setForeground(java.awt.Color.white);
        lblUsername1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername1.setText("USERNAME");
        jPanel1.add(lblUsername1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 40, 180, -1));

        lblUserType.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblUserType.setForeground(java.awt.Color.white);
        lblUserType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserType.setText("USER TYPE");
        jPanel1.add(lblUserType, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 220, -1));

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE ISSUED", "TENDER NO", "ITEM NAME", "DESCRIPTION", "ISSUED BY", "AVAIL DATE", "QTY", "PRICE", "SUPPLIER"
            }
        ));
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 740, 410));

        searchText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchTextKeyPressed(evt);
            }
        });
        jPanel1.add(searchText, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, 260, -1));

        searchBtn.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        searchBtn.setText("SEARCH WITH ISSUE DATE");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        jPanel1.add(searchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 60, 250, -1));

        jLabel9.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel9.setForeground(java.awt.Color.white);
        jLabel9.setText("DATE TO AVAIL:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 180, -1));

        jLabel10.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel10.setForeground(java.awt.Color.white);
        jLabel10.setText("ISSUED BY:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 140, -1));

        txtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQtyKeyPressed(evt);
            }
        });
        jPanel1.add(txtQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 250, 40));

        jLabel11.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel11.setForeground(java.awt.Color.white);
        jLabel11.setText("QUANTITY:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel12.setForeground(java.awt.Color.white);
        jLabel12.setText("DESCRIPTION:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 170, -1));

        deleteBtn.setBackground(new java.awt.Color(241, 70, 22));
        deleteBtn.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        deleteBtn.setForeground(java.awt.Color.white);
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 530, 140, 50));

        editBtn.setBackground(new java.awt.Color(16, 37, 246));
        editBtn.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        editBtn.setForeground(java.awt.Color.white);
        editBtn.setText("EDIT");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        jPanel1.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 460, 140, 50));

        txtItemName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtItemNameKeyPressed(evt);
            }
        });
        jPanel1.add(txtItemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 250, 40));

        txtDescription.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescriptionKeyPressed(evt);
            }
        });
        jPanel1.add(txtDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 250, 40));

        combSupplier.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        combSupplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT SUPPLIER" }));
        combSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                combSupplierKeyPressed(evt);
            }
        });
        jPanel1.add(combSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 410, 250, 40));

        jLabel5.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("SUPPLIER:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));

        availDate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                availDateKeyPressed(evt);
            }
        });
        jPanel1.add(availDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 250, 40));

        lblTenderNo.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblTenderNo.setForeground(java.awt.Color.white);
        lblTenderNo.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblTenderNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 250, 40));

        jLabel13.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 18)); // NOI18N
        jLabel13.setForeground(java.awt.Color.white);
        jLabel13.setText("PRICE:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 100, -1));

        txtPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPriceKeyPressed(evt);
            }
        });
        jPanel1.add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, 250, 40));

        jasperPrint.setBackground(new java.awt.Color(37, 207, 39));
        jasperPrint.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        jasperPrint.setForeground(java.awt.Color.white);
        jasperPrint.setText("JASPER PRINT ");
        jasperPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jasperPrintActionPerformed(evt);
            }
        });
        jPanel1.add(jasperPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 520, 160, 50));

        csvExport.setBackground(new java.awt.Color(37, 207, 39));
        csvExport.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        csvExport.setForeground(java.awt.Color.white);
        csvExport.setText("EXPORT TO A \".csv\" File");
        csvExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                csvExportActionPerformed(evt);
            }
        });
        jPanel1.add(csvExport, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 520, 200, 50));

        printTable.setBackground(new java.awt.Color(37, 207, 39));
        printTable.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        printTable.setForeground(java.awt.Color.white);
        printTable.setText("PRINT TABLE");
        printTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printTableActionPerformed(evt);
            }
        });
        jPanel1.add(printTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 520, 160, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 1210, 590));

        jLabel1.setFont(new java.awt.Font("Tibetan Machine Uni", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(45, 13, 249));
        jLabel1.setText("MANAGE TENDERS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 490, 50));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        this.setVisible(false);
        new Main(lblUsername1.getText(),imag, userType).setVisible(true);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to exit Hotel System?","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String dateIssued=lblDateIssued.getText();
        String tenderNo=lblTenderNo.getText();
        String itemName=txtItemName.getText();
        String description=txtDescription.getText();
        String issuedBy=lblIssuedBy.getText();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String dateAvail=format.format(availDate.getDate());
        int quantity=Integer.parseInt(txtQty.getText());
        int price=Integer.parseInt(txtPrice.getText());
        String supplier=combSupplier.getModel().getSelectedItem().toString();
        
        
        if(txtItemName.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter item name, null item names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtItemName.requestFocus();
        }else if(txtDescription.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter item description, null descriptions cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtDescription.requestFocus();
        }else if(dateAvail.trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please select date to avail goods, null dates cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            availDate.requestFocus();
        }else if(txtQty.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter quantity, null quantities cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            availDate.requestFocus();
        }else if(supplier.trim().equals("SELECT SUPPLIER")){
            JOptionPane.showMessageDialog(this,"Please select supplier, null suppliers cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            combSupplier.requestFocus();
        }else{
        try {
            pst=con.prepareStatement("insert into issuedTenders(dateIssued,tenderNo,itemName,description,issuedBy,availDate,quantity,price,supplier1)values(?,?,?,?,?,?,?,?,?)");
            pst.setString(1,dateIssued);
            pst.setString(2,tenderNo);
            pst.setString(3,itemName);
            pst.setString(4,description);
            pst.setString(5,issuedBy);
            pst.setString(6,dateAvail);
            pst.setInt(7,quantity);
            pst.setInt(8,price);
            pst.setString(9,supplier);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Tender Issued Out Successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
            txtItemName.setText("");
            txtPrice.setText("");
            txtDescription.setText("");
            availDate.setCalendar(null);
            txtQty.setText("");
            combSupplier.getModel().setSelectedItem("SELECT SUPPLIER");
            txtItemName.requestFocus();
            Issued_tenders_table();
            Auto_id();
            current_date();
            lblIssuedBy.setText(username);
            addBtn.setEnabled(true);
            deleteBtn.setEnabled(false);
            editBtn.setEnabled(false);
        } catch (SQLException ex) {
            Logger.getLogger(ManageTenders.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        txtItemName.setText("");
        txtPrice.setText("");
        txtDescription.setText("");
        availDate.setCalendar(null);
        txtQty.setText("");
        combSupplier.getModel().setSelectedItem("SELECT SUPPLIER");
        txtItemName.requestFocus();
        Issued_tenders_table();
        Auto_id();
        current_date();
        lblIssuedBy.setText(username);
        addBtn.setEnabled(true);
        deleteBtn.setEnabled(false);
        editBtn.setEnabled(false);
    }//GEN-LAST:event_clearBtnActionPerformed
    
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        deleteBtn.setEnabled(false);
        editBtn.setEnabled(false);
    }//GEN-LAST:event_formWindowActivated

    private void txtQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtPrice.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtPrice.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            availDate.requestFocus();
        }
    }//GEN-LAST:event_txtQtyKeyPressed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        String dateIssued=lblDateIssued.getText();
        String tenderNo=lblTenderNo.getText();
        String itemName=txtItemName.getText();
        String description=txtDescription.getText();
        String issuedBy=lblIssuedBy.getText();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String dateAvail=format.format(availDate.getDate());
        int quantity=Integer.parseInt(txtQty.getText());
        int price=Integer.parseInt(txtPrice.getText());
        String supplier=combSupplier.getModel().getSelectedItem().toString();
        
        if(txtItemName.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter item name, null item names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtItemName.requestFocus();
        }else if(txtDescription.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter item description, null descriptions cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtDescription.requestFocus();
        }else if(dateAvail.trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please select date to avail goods, null dates cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            availDate.requestFocus();
        }else if(txtQty.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter quantity, null quantities cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            availDate.requestFocus();
        }else if(supplier.trim().equals("SELECT SUPPLIER")){
            JOptionPane.showMessageDialog(this,"Please select supplier, null suppliers cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            combSupplier.requestFocus();
        }else{
        try {
            pst=con.prepareStatement("update issuedTenders set itemName=?, description=?, availDate=?, quantity=?, price=?, supplier1=? where tenderNo=?");
            pst.setString(1,itemName);
            pst.setString(2,description);
            pst.setString(3,dateAvail);
            pst.setInt(4,quantity);
            pst.setInt(5,price);
            pst.setString(6,supplier);
            pst.setString(7,tenderNo);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Tender updated Successfully on database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            txtItemName.setText("");
            txtPrice.setText("");
            txtDescription.setText("");
            availDate.setCalendar(null);
            txtQty.setText("");
            combSupplier.getModel().setSelectedItem("SELECT SUPPLIER");
            txtItemName.requestFocus();
            Issued_tenders_table();
            Auto_id();
            current_date();
            lblIssuedBy.setText(username);
            addBtn.setEnabled(true);
            deleteBtn.setEnabled(false);
            editBtn.setEnabled(false);
        } catch (SQLException ex) {
            Logger.getLogger(ManageTenders.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        String tenderNo=lblTenderNo.getText();
        String supplier=combSupplier.getModel().getSelectedItem().toString();
        
        if(txtItemName.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter item name, null item names cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtItemName.requestFocus();
        }else if(txtDescription.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter item description, null descriptions cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtDescription.requestFocus();
        }else if(txtQty.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Please enter quantity, null quantities cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            availDate.requestFocus();
        }else if(supplier.trim().equals("SELECT SUPPLIER")){
            JOptionPane.showMessageDialog(this,"Please select supplier, null suppliers cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            combSupplier.requestFocus();
        }else{
        try {
            pst=con.prepareStatement("delete from issuedTenders where tenderNo=?");
            pst.setString(1,tenderNo);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Tender deleted Successfully from database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            txtItemName.setText("");
            txtPrice.setText("");
            txtDescription.setText("");
            availDate.setCalendar(null);
            txtQty.setText("");
            combSupplier.getModel().setSelectedItem("SELECT SUPPLIER");
            txtItemName.requestFocus();
            Issued_tenders_table();
            Auto_id();
            current_date();
            lblIssuedBy.setText(username);
            addBtn.setEnabled(true);
            deleteBtn.setEnabled(false);
            editBtn.setEnabled(false);
        } catch (SQLException ex) {
            Logger.getLogger(ManageTenders.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        addBtn.setEnabled(false);
        deleteBtn.setEnabled(true);
        editBtn.setEnabled(true);
        
        DefaultTableModel model=(DefaultTableModel)table2.getModel();
        int selectedIndex=table2.getSelectedRow();
        lblDateIssued.setText(model.getValueAt(selectedIndex, 0).toString());
        lblTenderNo.setText(model.getValueAt(selectedIndex, 1).toString());
        txtItemName.setText(model.getValueAt(selectedIndex, 2).toString());
        txtDescription.setText(model.getValueAt(selectedIndex, 3).toString());
        lblIssuedBy.setText(model.getValueAt(selectedIndex, 4).toString());
        String date=model.getValueAt(selectedIndex, 5).toString();
        try{
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d=format.parse(date);
            availDate.setDate(d);
        }catch(Exception e){
            e.printStackTrace();
        }
        txtQty.setText(model.getValueAt(selectedIndex, 6).toString());
        txtPrice.setText(model.getValueAt(selectedIndex, 7).toString());
        combSupplier.getModel().setSelectedItem(model.getValueAt(selectedIndex, 8).toString());
    }//GEN-LAST:event_table2MouseClicked

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String issueDate=searchText.getText();
        try {
            pst=con.prepareStatement("select * from issuedTenders where dateIssued LIKE CONCAT('%',?,'%')");
            pst.setString(1,issueDate);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("dateIssued"));
                    v.add(rs.getString("tenderNo"));
                    v.add(rs.getString("itemName"));
                    v.add(rs.getString("description"));
                    v.add(rs.getString("issuedBy"));
                    v.add(rs.getString("availDate"));
                    v.add(rs.getInt("quantity"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("supplier1"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageTenders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    private void searchTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String issueDate=searchText.getText();
            try {
                pst=con.prepareStatement("select * from issuedTenders where dateIssued LIKE CONCAT('%',?,'%')");
                pst.setString(1,issueDate);
                rs=pst.executeQuery();
                ResultSetMetaData rsm=rs.getMetaData();
                int c=rsm.getColumnCount();

                DefaultTableModel model=(DefaultTableModel)table2.getModel();
                model.setRowCount(0);
                while(rs.next()){
                    Vector v=new Vector();
                    for(int i=1;i<c;i++){
                        v.add(rs.getString("dateIssued"));
                        v.add(rs.getString("tenderNo"));
                        v.add(rs.getString("itemName"));
                        v.add(rs.getString("description"));
                        v.add(rs.getString("issuedBy"));
                        v.add(rs.getString("availDate"));
                        v.add(rs.getInt("quantity"));
                        v.add(rs.getInt("price"));
                        v.add(rs.getString("supplier1"));
                    }
                    model.addRow(v);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ManageTenders.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_searchTextKeyPressed

    private void txtItemNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtItemNameKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtDescription.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtDescription.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            combSupplier.requestFocus();
        }
    }//GEN-LAST:event_txtItemNameKeyPressed

    private void txtDescriptionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescriptionKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            availDate.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            availDate.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtItemName.requestFocus();
        }
    }//GEN-LAST:event_txtDescriptionKeyPressed

    private void txtPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPriceKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            combSupplier.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            combSupplier.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtQty.requestFocus();
        }
    }//GEN-LAST:event_txtPriceKeyPressed

    private void availDateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_availDateKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtQty.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtQty.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtDescription.requestFocus();
        }
    }//GEN-LAST:event_availDateKeyPressed

    private void combSupplierKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_combSupplierKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtItemName.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtItemName.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtPrice.requestFocus();
        }
    }//GEN-LAST:event_combSupplierKeyPressed

    private void jasperPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jasperPrintActionPerformed
        try {
            JasperDesign jdesign=JRXmlLoader.load("src/com/hotelmanagement/system/Tenders.jrxml");
            JasperReport ireport=JasperCompileManager.compileReport(jdesign);
            JasperPrint jPrint=JasperFillManager.fillReport(ireport, null, con);
            JasperViewer.viewReport(jPrint,false);

        } catch (JRException ex) {
            Logger.getLogger(SellMeals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jasperPrintActionPerformed

    private void csvExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_csvExportActionPerformed
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
    }//GEN-LAST:event_csvExportActionPerformed

    private void printTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printTableActionPerformed
        MessageFormat header=new MessageFormat("SUPPLIERS' TENDERS");
        MessageFormat footer=new MessageFormat("page {0}");
        try {
            table2.print(JTable.PrintMode.FIT_WIDTH,header,footer);
        } catch (PrinterException ex) {
            Logger.getLogger(AllocateRooms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_printTableActionPerformed

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
            java.util.logging.Logger.getLogger(ManageTenders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageTenders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageTenders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageTenders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ManageTenders().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private com.toedter.calendar.JDateChooser availDate;
    private javax.swing.JButton clearBtn;
    private javax.swing.JComboBox<String> combSupplier;
    private javax.swing.JButton csvExport;
    private javax.swing.JToggleButton deleteBtn;
    private javax.swing.JToggleButton editBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jasperPrint;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblDateIssued;
    private javax.swing.JLabel lblIssuedBy;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblTenderNo;
    private javax.swing.JLabel lblUserType;
    private javax.swing.JLabel lblUsername1;
    private javax.swing.JButton printTable;
    private javax.swing.JToggleButton searchBtn;
    private javax.swing.JTextField searchText;
    private javax.swing.JTable table2;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQty;
    // End of variables declaration//GEN-END:variables
}
