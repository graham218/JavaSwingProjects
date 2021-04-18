/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelmanagement.system;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
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
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
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
public class PaySuppliers extends javax.swing.JFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    /**
     * Creates new form ManageClients
     */
    public PaySuppliers() {
        initComponents();
    }
    
    String username;
    BufferedImage imag;
    String userType;
    public PaySuppliers(String username,BufferedImage imag, String userType) {
        initComponents();
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("emmanuel-chigbo-jr7R01lOn1Y-unsplash.jpg")).getImage().getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), Image.SCALE_SMOOTH)));
        this.userType=userType;
        lblUserType.setText(userType);
        Connect();
        Issued_tenders_table();
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
            pst=con.prepareStatement("select * from issuedTenders INNER JOIN suppliers WHERE supplier1=supplier");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("tenderNo"));
                    v.add(rs.getString("itemName"));
                    v.add(rs.getString("availDate"));
                    v.add(rs.getInt("quantity"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("supplier1"));
                    v.add(rs.getString("accNo"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaySuppliers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Add(){
        DefaultTableModel model=(DefaultTableModel)table2.getModel();
        int quantity=Integer.parseInt(lblQty.getText());
        int price=Integer.parseInt(lblPrice.getText());
        int total=quantity*price;
        model.addRow(new Object[]{
            lblTenderNo1.getText(),
            lblItemName.getText(),
            quantity,
            price,
            total,
            lblAccNo.getText(),
        });
            lblTenderNo1.setText("");
            lblItemName.setText("");
            lblSupplier.setText("");
            lblPrice.setText("");
            lblQty.setText("");
            lblAccNo.setText("");

        int sum=0;
        for(int i=0;i<table2.getRowCount();i++){
            int total2=(int)table2.getValueAt(i,4);
            sum=sum+total2;
        }
        txtSubtotal.setText(Integer.toString(sum));
        txtPay.requestFocus();
    }
    
    public void Sales(){
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now=LocalDateTime.now();
        String date=dtf.format(now);
        String cashier=lblUsername.getText();
        int subtotal=Integer.parseInt(txtSubtotal.getText());
        int pay=Integer.parseInt(txtPay.getText());
        int balance=Integer.parseInt(txtBalance.getText());
        int lastinsertedid=0;
        
        if(txtSubtotal.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Null subtotals cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtSubtotal.requestFocus();
        }else if(txtPay.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Null payments cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPay.requestFocus();
        }else if(txtBalance.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Null balances cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtBalance.requestFocus();
        }else{
            String query="insert into tenderPayment (date,cashier,subtotal,pay,balance)values(?,?,?,?,?)";
            try {

                pst=con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
                pst.setString(1,date);
                pst.setString(2,cashier);
                pst.setInt(3,subtotal);
                pst.setInt(4,pay);
                pst.setInt(5,balance);
                pst.executeUpdate();
                ResultSet generatedKeyResult=pst.getGeneratedKeys();
                if(generatedKeyResult.next()){
                    lastinsertedid=generatedKeyResult.getInt(1);
                }


            int rows=table2.getRowCount();
            String query1="insert into tenderPay (tenderId,tenderNo,itemName,quantity,price,total,accNo,date) values(?,?,?,?,?,?,?,?)";
                pst=con.prepareStatement(query1);
                for(int i=0;i<table2.getRowCount();i++){
                    String tenderNo=(String) table2.getValueAt(i, 0);
                    String itemName=(String)table2.getValueAt(i,1);
                    int quantity=(int)table2.getValueAt(i,2);
                    int price=(int) table2.getValueAt(i,3);
                    int total=(int) table2.getValueAt(i,4);
                    String accNo=(String)table2.getValueAt(i,5);

                    pst.setInt(1, lastinsertedid);
                    pst.setString(2,tenderNo);
                    pst.setString(3,itemName);
                    pst.setInt(4,quantity);
                    pst.setInt(5,price);
                    pst.setInt(6,total);
                    pst.setString(7,accNo);
                    pst.setString(8,date);
                    pst.executeUpdate();
                }
                pst.addBatch();
                JOptionPane.showMessageDialog(this, "Supplier paid Successfully","Hotel Management System",JOptionPane.INFORMATION_MESSAGE);

                HashMap m=new HashMap();
                m.put("invoiceNo", lastinsertedid);
                try {
                    JasperDesign jdesign=JRXmlLoader.load("src/com/hotelmanagement/system/SuppliersReceipt.jrxml");
                    JasperReport ireport=JasperCompileManager.compileReport(jdesign);
                    JasperPrint jPrint=JasperFillManager.fillReport(ireport, m, con);
                    JasperViewer.viewReport(jPrint,false);

                } catch (JRException ex) {
                    Logger.getLogger(SellMeals.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
                Logger.getLogger(SellMeals.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        jLabel7 = new javax.swing.JLabel();
        lblProfile = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblUserType = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblSupplier = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        searchText = new javax.swing.JTextField();
        searchBtn = new javax.swing.JToggleButton();
        lblTenderNo1 = new javax.swing.JLabel();
        lblItemName = new javax.swing.JLabel();
        lblQty = new javax.swing.JLabel();
        lblPrice = new javax.swing.JLabel();
        lblAccNo = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        clearBtn = new javax.swing.JToggleButton();
        btnDelete = new javax.swing.JToggleButton();
        exitBtn = new javax.swing.JButton();
        homeBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtPay = new javax.swing.JTextField();
        txtSubtotal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtBalance = new javax.swing.JTextField();
        paySupplierBtn = new javax.swing.JButton();
        balanceBtn = new javax.swing.JButton();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PAY SUPPLIERS");
        setMaximizedBounds(new java.awt.Rectangle(1236, 718, 718, 718));
        setMinimumSize(new java.awt.Dimension(1236, 718));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(142, 40, 111));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(245, 248, 5));
        jLabel2.setText("TENDER NO:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 80, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(245, 248, 5));
        jLabel7.setText("ITEM NAME:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 120, -1, -1));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 0, 70, 70));

        lblUsername.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblUsername.setForeground(java.awt.Color.white);
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("USERNAME");
        jPanel1.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 40, 180, -1));

        lblUserType.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblUserType.setForeground(java.awt.Color.white);
        lblUserType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserType.setText("USER TYPE");
        jPanel1.add(lblUserType, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 220, -1));

        jLabel11.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(245, 248, 5));
        jLabel11.setText("SUPPLIER:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 240, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(245, 248, 5));
        jLabel12.setText("QTY:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 160, 100, -1));

        jLabel5.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(245, 248, 5));
        jLabel5.setText("PRICE:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 200, -1, -1));

        lblSupplier.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblSupplier.setForeground(new java.awt.Color(245, 248, 5));
        lblSupplier.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 240, 180, 30));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TENDER NO", "ITEM NAME", "AVAIL DATE", "QTY", "PRICE", "SUPPLIER", "ACC NO"
            }
        ));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 860, 280));

        searchText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchTextKeyPressed(evt);
            }
        });
        jPanel1.add(searchText, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, -1));

        searchBtn.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        searchBtn.setText("SEARCH WITH SUPPLIER'S NAME");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        jPanel1.add(searchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 290, -1));

        lblTenderNo1.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblTenderNo1.setForeground(new java.awt.Color(245, 248, 5));
        lblTenderNo1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblTenderNo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 80, 180, 30));

        lblItemName.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblItemName.setForeground(new java.awt.Color(245, 248, 5));
        lblItemName.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblItemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 120, 180, 30));

        lblQty.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblQty.setForeground(new java.awt.Color(245, 248, 5));
        lblQty.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 160, 180, 30));

        lblPrice.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblPrice.setForeground(new java.awt.Color(245, 248, 5));
        lblPrice.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 200, 180, 30));

        lblAccNo.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblAccNo.setForeground(new java.awt.Color(245, 248, 5));
        lblAccNo.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblAccNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 280, 180, 30));

        jLabel14.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(245, 248, 5));
        jLabel14.setText("ACC NO:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 280, -1, -1));

        btnAdd.setBackground(new java.awt.Color(205, 7, 243));
        btnAdd.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        btnAdd.setForeground(java.awt.Color.white);
        btnAdd.setText("ADD");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 320, 180, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1210, 370));

        jLabel1.setFont(new java.awt.Font("Tibetan Machine Uni", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(45, 13, 249));
        jLabel1.setText("PAY SUPPLIERS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 490, 50));

        jPanel2.setBackground(new java.awt.Color(28, 158, 43));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TENDOR NO", "ITEM NAME", "QTY", "PRICE", "TOTAL", "ACC NO"
            }
        ));
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table2);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 680, 190));

        clearBtn.setBackground(new java.awt.Color(226, 42, 79));
        clearBtn.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        clearBtn.setForeground(java.awt.Color.white);
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel2.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 120, 50));

        btnDelete.setBackground(new java.awt.Color(179, 58, 82));
        btnDelete.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        btnDelete.setForeground(java.awt.Color.white);
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel2.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 130, 50));

        exitBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(238, 13, 24));
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel2.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 220, 140, 50));

        homeBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        homeBtn.setForeground(new java.awt.Color(36, 39, 246));
        homeBtn.setText("HOME");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel2.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 220, 130, 50));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 730, 280));

        jPanel3.setBackground(new java.awt.Color(24, 123, 24));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(txtPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 200, 30));
        jPanel3.add(txtSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 200, -1));

        jLabel13.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(245, 248, 5));
        jLabel13.setText("PAY:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 70, -1));

        jLabel15.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(245, 248, 5));
        jLabel15.setText("SUBTOTAL:");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(245, 248, 5));
        jLabel16.setText("BALANCE:");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 100, -1));
        jPanel3.add(txtBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 200, 30));

        paySupplierBtn.setBackground(new java.awt.Color(8, 6, 235));
        paySupplierBtn.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        paySupplierBtn.setForeground(java.awt.Color.white);
        paySupplierBtn.setText("PAY SUPPLIER");
        paySupplierBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paySupplierBtnActionPerformed(evt);
            }
        });
        jPanel3.add(paySupplierBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, 200, 40));

        balanceBtn.setBackground(new java.awt.Color(8, 6, 235));
        balanceBtn.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        balanceBtn.setForeground(java.awt.Color.white);
        balanceBtn.setText("BALANCE");
        balanceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balanceBtnActionPerformed(evt);
            }
        });
        jPanel3.add(balanceBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, 200, 40));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 420, 470, 280));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        this.setVisible(false);
        new Main(lblUsername.getText(),imag, userType).setVisible(true);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to exit Hotel System?","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed
    
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        btnAdd.setEnabled(false);
        btnDelete.setEnabled(false);
    }//GEN-LAST:event_formWindowActivated

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        btnAdd.setEnabled(true);

        DefaultTableModel model=(DefaultTableModel)table1.getModel();
        int selectedIndex=table1.getSelectedRow();
        lblTenderNo1.setText(model.getValueAt(selectedIndex, 0).toString());
        lblItemName.setText(model.getValueAt(selectedIndex, 1).toString());
        lblQty.setText(model.getValueAt(selectedIndex, 3).toString());
        lblPrice.setText(model.getValueAt(selectedIndex, 4).toString());
        lblSupplier.setText(model.getValueAt(selectedIndex, 5).toString());
        lblAccNo.setText(model.getValueAt(selectedIndex, 6).toString());
    }//GEN-LAST:event_table1MouseClicked

    private void searchTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String supplier=searchText.getText();
            try {
                //pst=con.prepareStatement("select * from issuedTenders where dateIssued LIKE CONCAT('%',?,'%')");
                pst=con.prepareStatement("select * from issuedTenders INNER JOIN suppliers WHERE supplier1=supplier AND supplier1 LIKE CONCAT('%',?,'%')");
                pst.setString(1,supplier);
                rs=pst.executeQuery();
                ResultSetMetaData rsm=rs.getMetaData();
                int c=rsm.getColumnCount();

                DefaultTableModel model=(DefaultTableModel)table1.getModel();
                model.setRowCount(0);
                while(rs.next()){
                    Vector v=new Vector();
                    for(int i=1;i<c;i++){
                        v.add(rs.getString("tenderNo"));
                        v.add(rs.getString("itemName"));
                        v.add(rs.getString("availDate"));
                        v.add(rs.getInt("quantity"));
                        v.add(rs.getInt("price"));
                        v.add(rs.getString("supplier1"));
                        v.add(rs.getString("accNo"));
                    }
                    model.addRow(v);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ManageTenders.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_searchTextKeyPressed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String supplier=searchText.getText();
        try {
            //pst=con.prepareStatement("select * from issuedTenders INNER JOIN suppliers WHERE supplier1=supplier");
            pst=con.prepareStatement("select * from issuedTenders INNER JOIN suppliers WHERE supplier1=supplier AND supplier1 LIKE CONCAT('%',?,'%')");
            pst.setString(1,supplier);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();

            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("tenderNo"));
                        v.add(rs.getString("itemName"));
                        v.add(rs.getString("availDate"));
                        v.add(rs.getInt("quantity"));
                        v.add(rs.getInt("price"));
                        v.add(rs.getString("supplier1"));
                        v.add(rs.getString("accNo"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageTenders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Add();
        btnAdd.setEnabled(false);
    }//GEN-LAST:event_btnAddActionPerformed

    private void paySupplierBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paySupplierBtnActionPerformed
        if(txtSubtotal.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Null subtotals cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtSubtotal.requestFocus();
        }else if(txtPay.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Null payments cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPay.requestFocus();
        }else if(txtBalance.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Null balances cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtBalance.requestFocus();
        }else{
            Sales();
            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            txtSubtotal.setText("");
            txtPay.setText("");
            txtBalance.setText("");
            btnAdd.setEnabled(false);
            lblTenderNo1.setText("");
            lblItemName.setText("");
            lblQty.setText("");
            lblPrice.setText("");
            lblSupplier.setText("");
            lblAccNo.setText("");
            Issued_tenders_table();
            btnDelete.setEnabled(false);
        }
    }//GEN-LAST:event_paySupplierBtnActionPerformed

    private void balanceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_balanceBtnActionPerformed
        if(txtSubtotal.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Null subtotals cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtSubtotal.requestFocus();
        }else if(txtPay.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"Null payments cannot be submitted","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtPay.requestFocus();
        }else{
            int pay=Integer.parseInt(txtPay.getText());
            int subtotal=Integer.parseInt(txtSubtotal.getText());
            int balance=pay-subtotal;
            txtBalance.setText(String.valueOf(balance));
        }
    }//GEN-LAST:event_balanceBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        DefaultTableModel model=(DefaultTableModel)table2.getModel();
        model.setRowCount(0);
        txtSubtotal.setText("");
        txtPay.setText("");
        txtBalance.setText("");
        btnAdd.setEnabled(false);
        lblTenderNo1.setText("");
        lblItemName.setText("");
        lblQty.setText("");
        lblPrice.setText("");
        lblSupplier.setText("");
        lblAccNo.setText("");
        Issued_tenders_table();
        btnDelete.setEnabled(false);
    }//GEN-LAST:event_clearBtnActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        DefaultTableModel model=(DefaultTableModel)table2.getModel();
        int selectedRow=table2.getSelectedRow();
        model.removeRow(selectedRow);
        int sum=0;
            for (int i = 0; i < table2.getRowCount(); i++) {
                    int total1=(int)table2.getValueAt(i, 4);
                    sum=sum+total1;
            }
        txtSubtotal.setText(String.valueOf(sum));
        txtPay.setText("");
        txtBalance.setText("");
        btnDelete.setEnabled(false);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        btnDelete.setEnabled(true);
    }//GEN-LAST:event_table2MouseClicked

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
            java.util.logging.Logger.getLogger(PaySuppliers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaySuppliers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaySuppliers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaySuppliers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new PaySuppliers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton balanceBtn;
    private javax.swing.JButton btnAdd;
    private javax.swing.JToggleButton btnDelete;
    private javax.swing.JToggleButton clearBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAccNo;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblItemName;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblQty;
    private javax.swing.JLabel lblSupplier;
    private javax.swing.JLabel lblTenderNo1;
    private javax.swing.JLabel lblUserType;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JButton paySupplierBtn;
    private javax.swing.JToggleButton searchBtn;
    private javax.swing.JTextField searchText;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JTextField txtBalance;
    private javax.swing.JTextField txtPay;
    private javax.swing.JTextField txtSubtotal;
    // End of variables declaration//GEN-END:variables
}
