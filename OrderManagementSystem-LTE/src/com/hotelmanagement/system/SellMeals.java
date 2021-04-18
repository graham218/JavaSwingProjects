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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class SellMeals extends javax.swing.JFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    /**
     * Creates new form ManageRooms
     */
    public SellMeals() {
        initComponents();
    }

    String username;
    BufferedImage imag;
    String userType;
    public SellMeals(String username, BufferedImage imag, String userType) {
        initComponents();
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(90, 100, Image.SCALE_SMOOTH)));
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("social-media-3758364_1920.jpg")).getImage().getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), Image.SCALE_SMOOTH)));
        this.userType=userType;
        lblUserType.setText(userType);
        Connect();
        Connect();
        Meal_Table();
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
    
    public void Meal_Table(){
        try {
            pst=con.prepareStatement("select * from meals");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("mealNo"));
                    v.add(rs.getString("mealName"));
                    v.add(rs.getString("description"));
                    v.add(rs.getInt("price"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HotelMeals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Add(){
        String mealNo=txtMealNo.getText();
        try {
           
            pst=con.prepareStatement("select * from meals where mealNo=?");
            pst.setString(1,mealNo);
            rs=pst.executeQuery();
            while(rs.next()){
                int currentQty=rs.getInt("quantity");
                int newQuantity=Integer.parseInt(txtQty.getText());
                int price=Integer.parseInt(txtPrice.getText());
                int total=newQuantity*price;
                
                if(newQuantity>currentQty){
                    JOptionPane.showMessageDialog(this, "Quantity in stock is not enough, Available quantity is "+currentQty, "Hotel Management System", JOptionPane.CANCEL_OPTION);
                }else{
                    DefaultTableModel model=(DefaultTableModel)table2.getModel();
                    int quantity=Integer.parseInt(txtQty.getText());
                    model.addRow(new Object[]{
                        txtMealNo.getText(),
                        txtMealName.getText(),
                        txtDescription.getText(),
                        price,
                        quantity,
                        total
                    });
                        txtMealNo.setText("");
                        txtMealName.setText("");
                        txtDescription.setText("");
                        txtPrice.setText("");
                        txtQty.setText("");
                     
                    int sum=0;
                    for(int i=0;i<table2.getRowCount();i++){
                        int total2=(int)table2.getValueAt(i,5);
                        sum=sum+total2;
                    }
                    txtSubtotal.setText(Integer.toString(sum));
                    txtPay.requestFocus();
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SellMeals.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        
        String query="insert into sales (date,cashier,subtotal,pay,balance)values(?,?,?,?,?)";
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
        String query1="insert into meal_sales (salesId,mealNo,mealName,description,price,quantity,total,date) values(?,?,?,?,?,?,?,?)";
            pst=con.prepareStatement(query1);
            for(int i=0;i<table2.getRowCount();i++){
                String mealNo=(String) table2.getValueAt(i, 0);
                String mealName=(String)table2.getValueAt(i,1);
                String description=(String)table2.getValueAt(i,2);
                int price=(int)table2.getValueAt(i,3);
                int quantity=(int) table2.getValueAt(i,4);
                int total=(int) table2.getValueAt(i,5);
                
                pst.setInt(1, lastinsertedid);
                pst.setString(2,mealNo);
                pst.setString(3,mealName);
                pst.setString(4,description);
                pst.setInt(5,price);
                pst.setInt(6,quantity);
                pst.setInt(7,total);
                pst.setString(8,date);
                pst.executeUpdate();
            }
        
        String query2="update meals set quantity=quantity-? where mealNo=?";
            pst=con.prepareStatement(query2);
            for(int i=0;i<table2.getRowCount();i++){
                String itemCode=table2.getValueAt(i, 0).toString();
                int quantity2=(int) table2.getValueAt(i,4);
                
                pst.setInt(1,quantity2);
                pst.setString(2,itemCode);
                pst.execute();
            }
            pst.addBatch();
            JOptionPane.showMessageDialog(this, "Sales inserted Successfully","Hotel Management System",JOptionPane.INFORMATION_MESSAGE);
            
            HashMap m=new HashMap();
            m.put("invoiceNo", lastinsertedid);
            try {
                JasperDesign jdesign=JRXmlLoader.load("src/com/hotelmanagement/system/MealReceipt.jrxml");
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
        exitBtn = new javax.swing.JButton();
        homeBtn = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        clear = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMealNo = new javax.swing.JTextField();
        txtDescription = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lblUserType = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        txtMealName = new javax.swing.JTextField();
        txtQty = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        lblProfile = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        deleteRow = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        sellBtn = new javax.swing.JButton();
        txtBalance = new javax.swing.JTextField();
        txtSubtotal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPay = new javax.swing.JTextField();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ROOM TYPES");
        setMaximizedBounds(new java.awt.Rectangle(1270, 716, 716, 716));
        setMaximumSize(new java.awt.Dimension(1270, 716));
        setMinimumSize(new java.awt.Dimension(1270, 716));
        setPreferredSize(new java.awt.Dimension(1270, 716));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUsername.setFont(new java.awt.Font("Ubuntu", 3, 24)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(38, 16, 241));
        lblUsername.setText("USERNAME");
        getContentPane().add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 30, 170, -1));

        jPanel1.setBackground(new java.awt.Color(23, 169, 88));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exitBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(238, 13, 24));
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 520, 130, 50));

        homeBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        homeBtn.setForeground(new java.awt.Color(36, 39, 246));
        homeBtn.setText("HOME PAGE");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 130, 50));

        btnSearch.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        btnSearch.setText("SEARCH WITH MEAL NAME");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 250, 40));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 220, 40));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MEAL NUMBER", "MEAL NAME", "DESCRIPTION", "PRICE"
            }
        ));
        table1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        table1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                table1CaretPositionChanged(evt);
            }
        });
        jScrollPane2.setViewportView(table1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 480, 450));

        clear.setBackground(new java.awt.Color(29, 24, 201));
        clear.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        clear.setForeground(java.awt.Color.white);
        clear.setText("CLEAR");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        jPanel1.add(clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 520, 140, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 500, 630));

        jLabel1.setFont(new java.awt.Font("Tibetan Machine Uni", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(45, 13, 249));
        jLabel1.setText("SELL MEALS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, -1, 50));

        jPanel2.setBackground(new java.awt.Color(23, 169, 88));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("MEAL NO:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 20));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("MEAL NAME:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("DESCRIPTION:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        txtMealNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMealNoActionPerformed(evt);
            }
        });
        txtMealNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMealNoKeyPressed(evt);
            }
        });
        jPanel2.add(txtMealNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 180, 40));
        jPanel2.add(txtDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 180, 40));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("PRICE:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, -1));

        lblUserType.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        lblUserType.setForeground(java.awt.Color.white);
        lblUserType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserType.setText("user type");
        jPanel2.add(lblUserType, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 120, 110, -1));

        btnAdd.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(166, 120, 78));
        btnAdd.setText("ADD");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel2.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, 180, 50));

        txtMealName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMealNameKeyPressed(evt);
            }
        });
        jPanel2.add(txtMealName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 180, 40));

        txtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQtyKeyPressed(evt);
            }
        });
        jPanel2.add(txtQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 180, 40));
        jPanel2.add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 180, 40));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel2.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 90, 100));

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("QUANTITY:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, 730, 170));

        jPanel3.setBackground(new java.awt.Color(23, 169, 88));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MEAL NUMBER", "MEAL NAME", "DESCRIPTION", "PRICE", "QUANTIY", "TOTAL"
            }
        ));
        table2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        table2.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                table2CaretPositionChanged(evt);
            }
        });
        jScrollPane1.setViewportView(table2);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 710, 160));

        deleteRow.setBackground(new java.awt.Color(237, 22, 22));
        deleteRow.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        deleteRow.setForeground(java.awt.Color.white);
        deleteRow.setText("DELETE ROW");
        deleteRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRowActionPerformed(evt);
            }
        });
        jPanel3.add(deleteRow, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 180, 130, 50));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 240, 730, 240));

        jPanel4.setBackground(new java.awt.Color(23, 169, 88));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sellBtn.setBackground(new java.awt.Color(24, 58, 195));
        sellBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        sellBtn.setForeground(java.awt.Color.white);
        sellBtn.setText("SELL MEALS");
        sellBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellBtnActionPerformed(evt);
            }
        });
        jPanel4.add(sellBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 140, 50));
        jPanel4.add(txtBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 180, 40));
        jPanel4.add(txtSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 180, 40));

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel9.setForeground(java.awt.Color.white);
        jLabel9.setText("PAY:");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel10.setForeground(java.awt.Color.white);
        jLabel10.setText("BALANCE:");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel8.setForeground(java.awt.Color.white);
        jLabel8.setText("SUBTOTAL:");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, 20));
        jPanel4.add(txtPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 180, 40));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 500, 730, 170));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        deleteRow.setEnabled(true);
    }//GEN-LAST:event_table2MouseClicked

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to exit Hotel System?","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        this.setVisible(false);
        new Main(lblUsername.getText(),imag, userType).setVisible(true);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void table2CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_table2CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_table2CaretPositionChanged

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        btnAdd.setEnabled(false);
        deleteRow.setEnabled(false);
    }//GEN-LAST:event_formWindowActivated

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        btnAdd.setEnabled(true);
        DefaultTableModel model=(DefaultTableModel)table1.getModel();
        int selectedIndex=table1.getSelectedRow();
        txtMealNo.setText(model.getValueAt(selectedIndex, 0).toString());
        txtMealName.setText(model.getValueAt(selectedIndex, 1).toString());
        txtDescription.setText(model.getValueAt(selectedIndex, 2).toString());
        txtPrice.setText(model.getValueAt(selectedIndex, 3).toString());
        txtQty.requestFocus();
    }//GEN-LAST:event_table1MouseClicked

    private void table1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_table1CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_table1CaretPositionChanged

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String mealName=txtSearch.getText();
        try {
            pst=con.prepareStatement("select * from meals where mealName like CONCAT('%',?,'%')");
            pst.setString(1,mealName);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("mealNo"));
                    v.add(rs.getString("mealName"));
                    v.add(rs.getString("description"));
                    v.add(rs.getInt("price"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HotelMeals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String mealName=txtSearch.getText();
        try {
            pst=con.prepareStatement("select * from meals where mealName like CONCAT('%',?,'%')");
            pst.setString(1,mealName);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<c;i++){
                    v.add(rs.getString("mealNo"));
                    v.add(rs.getString("mealName"));
                    v.add(rs.getString("description"));
                    v.add(rs.getInt("price"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HotelMeals.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if(txtQty.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Please enter Quantity, null meal Quantities cannot be sold","Submission Error!!",JOptionPane.CANCEL_OPTION);
            txtQty.requestFocus();
        }else{
            Add();
            btnAdd.setEnabled(false);
            Meal_Table();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void sellBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellBtnActionPerformed
        int subtotal=Integer.parseInt(txtSubtotal.getText());
        int pay=Integer.parseInt(txtPay.getText());
        int balance=pay-subtotal;
        txtBalance.setText(String.valueOf(balance));
        Sales();
        
        //clear widgets
        DefaultTableModel model=(DefaultTableModel)table2.getModel();
        model.setRowCount(0);
        txtSubtotal.setText("");
        txtPay.setText("");
        txtBalance.setText("");
        txtSearch.requestFocus();
        
        txtMealNo.setText("");
        txtMealName.setText("");
        txtDescription.setText("");
        txtPrice.setText("");
        txtQty.setText("");
        btnAdd.setEnabled(false);
        deleteRow.setEnabled(false);
        Meal_Table();
    }//GEN-LAST:event_sellBtnActionPerformed

    private void txtMealNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMealNoKeyPressed
        
    }//GEN-LAST:event_txtMealNoKeyPressed

    private void txtMealNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMealNameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMealNameKeyPressed

    private void txtMealNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMealNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMealNoActionPerformed

    private void deleteRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRowActionPerformed
        DefaultTableModel model=(DefaultTableModel)table2.getModel();
        model.removeRow(table2.getSelectedRow());
        int sum=0;
        for(int i=0;i<table2.getRowCount();i++){
                int total2=(int)table2.getValueAt(i,5);
                sum=sum+total2;
            }
        txtSubtotal.setText(Integer.toString(sum));
        deleteRow.setEnabled(false);
    }//GEN-LAST:event_deleteRowActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        DefaultTableModel model=(DefaultTableModel)table2.getModel();
        model.setRowCount(0);
        txtSubtotal.setText("");
        txtPay.setText("");
        txtBalance.setText("");
        txtSearch.requestFocus();
        
        txtMealNo.setText("");
        txtMealName.setText("");
        txtDescription.setText("");
        txtPrice.setText("");
        txtQty.setText("");
        btnAdd.setEnabled(false);
        deleteRow.setEnabled(false);
        Meal_Table();
    }//GEN-LAST:event_clearActionPerformed

    private void txtQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyPressed
        
    }//GEN-LAST:event_txtQtyKeyPressed

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
            java.util.logging.Logger.getLogger(SellMeals.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SellMeals.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SellMeals.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SellMeals.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new SellMeals().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton clear;
    private javax.swing.JButton deleteRow;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblUserType;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JButton sellBtn;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JTextField txtBalance;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtMealName;
    private javax.swing.JTextField txtMealNo;
    private javax.swing.JTextField txtPay;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSubtotal;
    // End of variables declaration//GEN-END:variables
}
