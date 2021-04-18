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
public class PaySuppliers1 extends javax.swing.JPanel {

    PreparedStatement pst;
    ResultSet rs;
    Database base=new Database();
    Connection con=base.myCon();
    /**
     * Creates new form PaySuppliers1
     */
    public PaySuppliers1() {
        initComponents();
    }
    
    String username;
    BufferedImage imag;
    String userType;
    public PaySuppliers1(String username,BufferedImage imag, String userType) {
        initComponents();
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
        //lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("emmanuel-chigbo-jr7R01lOn1Y-unsplash.jpg")).getImage().getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), Image.SCALE_SMOOTH)));
        this.userType=userType;
        lblUserType.setText(userType);
        Issued_tenders_table();
        button_pre_enabled();
    }
    
    public void button_pre_enabled(){
        btnAdd.setEnabled(false);
        btnDelete.setEnabled(false);
    }
    
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
                    v.add(rs.getString("quantity"));
                    v.add(rs.getString("price"));
                    v.add(rs.getString("supplier1"));
                    v.add(rs.getString("accNo"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaySuppliers1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Add(){
        DefaultTableModel model=(DefaultTableModel)table2.getModel();
        Double quantity=Double.parseDouble(lblQty.getText());
        Double price=Double.parseDouble(lblPrice.getText());
        Double total=quantity*price;
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

        Double sum=0.0;
        for(int i=0;i<table2.getRowCount();i++){
            Double total2=Double.parseDouble(table2.getValueAt(i,4).toString());
            sum=sum+total2;
        }
        txtSubtotal.setText(Double.toString(sum));
        txtPay.requestFocus();
    }
    
    public void Sales(){
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now=LocalDateTime.now();
        String date=dtf.format(now);
        String cashier=lblUsername.getText();
        String subtotal=txtSubtotal.getText();
        String pay=txtPay.getText();
        String balance=txtBalance.getText();
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
                pst.setString(3,subtotal);
                pst.setString(4,pay);
                pst.setString(5,balance);
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
                    String quantity=table2.getValueAt(i,2).toString();
                    String price=table2.getValueAt(i,3).toString();
                    String total=table2.getValueAt(i,4).toString();
                    String accNo=table2.getValueAt(i,5).toString();

                    pst.setInt(1, lastinsertedid);
                    pst.setString(2,tenderNo);
                    pst.setString(3,itemName);
                    pst.setString(4,quantity);
                    pst.setString(5,price);
                    pst.setString(6,total);
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
                    Logger.getLogger(PaySuppliers1.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
                Logger.getLogger(PaySuppliers1.class.getName()).log(Level.SEVERE, null, ex);
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
        jPanel3 = new javax.swing.JPanel();
        txtPay = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        paySupplierBtn = new javax.swing.JButton();
        txtSubtotal = new javax.swing.JLabel();
        txtBalance = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1140, 600));
        setMinimumSize(new java.awt.Dimension(1140, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(142, 40, 111));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(245, 248, 5));
        jLabel2.setText("TENDER NO:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 80, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(245, 248, 5));
        jLabel7.setText("ITEM NAME:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 120, -1, -1));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 0, 70, 70));

        lblUsername.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblUsername.setForeground(java.awt.Color.white);
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("USERNAME");
        jPanel1.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 40, 180, -1));

        lblUserType.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblUserType.setForeground(java.awt.Color.white);
        lblUserType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserType.setText("USER TYPE");
        jPanel1.add(lblUserType, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 220, -1));

        jLabel11.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(245, 248, 5));
        jLabel11.setText("SUPPLIER:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 240, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(245, 248, 5));
        jLabel12.setText("QTY:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 160, 100, -1));

        jLabel5.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(245, 248, 5));
        jLabel5.setText("PRICE:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 200, -1, -1));

        lblSupplier.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblSupplier.setForeground(new java.awt.Color(245, 248, 5));
        lblSupplier.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 240, 180, 30));

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

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 740, 280));

        searchText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchTextKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTextKeyReleased(evt);
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
        jPanel1.add(lblTenderNo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 80, 180, 30));

        lblItemName.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblItemName.setForeground(new java.awt.Color(245, 248, 5));
        lblItemName.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblItemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 120, 180, 30));

        lblQty.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblQty.setForeground(new java.awt.Color(245, 248, 5));
        lblQty.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 160, 180, 30));

        lblPrice.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblPrice.setForeground(new java.awt.Color(245, 248, 5));
        lblPrice.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 200, 180, 30));

        lblAccNo.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblAccNo.setForeground(new java.awt.Color(245, 248, 5));
        lblAccNo.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel1.add(lblAccNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 280, 180, 30));

        jLabel14.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(245, 248, 5));
        jLabel14.setText("ACC NO:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 280, -1, -1));

        btnAdd.setBackground(new java.awt.Color(205, 7, 243));
        btnAdd.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        btnAdd.setForeground(java.awt.Color.white);
        btnAdd.setText("ADD");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 320, 180, 40));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1120, 370));

        jLabel1.setFont(new java.awt.Font("Tibetan Machine Uni", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(45, 13, 249));
        jLabel1.setText("PAY SUPPLIERS");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 490, 50));

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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 600, 110));

        clearBtn.setBackground(new java.awt.Color(226, 42, 79));
        clearBtn.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        clearBtn.setForeground(java.awt.Color.white);
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel2.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 120, 40));

        btnDelete.setBackground(new java.awt.Color(179, 58, 82));
        btnDelete.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        btnDelete.setForeground(java.awt.Color.white);
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel2.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 130, 40));

        exitBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(238, 13, 24));
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel2.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, 140, 40));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 620, 170));

        jPanel3.setBackground(new java.awt.Color(24, 123, 24));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtPay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPayKeyReleased(evt);
            }
        });
        jPanel3.add(txtPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 140, 30));

        jLabel13.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(245, 248, 5));
        jLabel13.setText("PAY:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 70, -1));

        jLabel15.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(245, 248, 5));
        jLabel15.setText("SUBTOTAL:");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tibetan Machine Uni", 3, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(245, 248, 5));
        jLabel16.setText("BALANCE:");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 100, -1));

        paySupplierBtn.setBackground(new java.awt.Color(8, 6, 235));
        paySupplierBtn.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        paySupplierBtn.setForeground(java.awt.Color.white);
        paySupplierBtn.setText("PAY SUPPLIER");
        paySupplierBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paySupplierBtnActionPerformed(evt);
            }
        });
        jPanel3.add(paySupplierBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 150, 40));

        txtSubtotal.setFont(new java.awt.Font("Ubuntu", 3, 13)); // NOI18N
        txtSubtotal.setForeground(java.awt.Color.white);
        txtSubtotal.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel3.add(txtSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 140, 30));

        txtBalance.setFont(new java.awt.Font("Ubuntu", 3, 13)); // NOI18N
        txtBalance.setForeground(java.awt.Color.white);
        txtBalance.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel3.add(txtBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 140, 30));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 420, 490, 170));
    }// </editor-fold>//GEN-END:initComponents

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
                        v.add(rs.getString("quantity"));
                        v.add(rs.getString("price"));
                        v.add(rs.getString("supplier1"));
                        v.add(rs.getString("accNo"));
                    }
                    model.addRow(v);
                }
            } catch (SQLException ex) {
                Logger.getLogger(PaySuppliers1.class.getName()).log(Level.SEVERE, null, ex);
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
                    v.add(rs.getString("quantity"));
                    v.add(rs.getString("price"));
                    v.add(rs.getString("supplier1"));
                    v.add(rs.getString("accNo"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaySuppliers1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Add();
        btnAdd.setEnabled(false);
    }//GEN-LAST:event_btnAddActionPerformed

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        btnDelete.setEnabled(true);
    }//GEN-LAST:event_table2MouseClicked

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
        Double sum=null;
        for(int i=0;i<table2.getRowCount();i++){
            Double total2=Double.parseDouble(table2.getValueAt(i,4).toString());
            sum=sum+total2;
        }
        txtSubtotal.setText(Double.toString(sum));
        txtPay.setText("");
        txtBalance.setText("");
        btnDelete.setEnabled(false);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to exit Hotel System?","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

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

    private void searchTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextKeyReleased
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
                    v.add(rs.getString("quantity"));
                    v.add(rs.getString("price"));
                    v.add(rs.getString("supplier1"));
                    v.add(rs.getString("accNo"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaySuppliers1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchTextKeyReleased

    private void txtPayKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPayKeyReleased
        Double pay=Double.parseDouble(txtPay.getText());
        Double subtotal=Double.parseDouble(txtSubtotal.getText());
        Double balance=pay-subtotal;
        txtBalance.setText(Double.toString(balance));
    }//GEN-LAST:event_txtPayKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JToggleButton btnDelete;
    private javax.swing.JToggleButton clearBtn;
    private javax.swing.JButton exitBtn;
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
    private javax.swing.JLabel txtBalance;
    private javax.swing.JTextField txtPay;
    private javax.swing.JLabel txtSubtotal;
    // End of variables declaration//GEN-END:variables
}
