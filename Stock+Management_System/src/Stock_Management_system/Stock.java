/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock_Management_system;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author bill
 */
public class Stock extends javax.swing.JFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    /**
     * Creates new form Login
     */
    public Stock() {
        initComponents();
    }
  String username;
  BufferedImage imag;
    public Stock(String username,BufferedImage imag) {
        initComponents();
        Connect();
        Auto_ID();
        Stock_Table();
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
        
        jLabel11.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("pexels-rfstudio-4177708.jpg")).getImage().getScaledInstance(1250, 760, Image.SCALE_SMOOTH)));
    }
    
    public void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/Stock","root","");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Auto_ID(){
        try {
            Statement s=con.createStatement();
            rs=s.executeQuery("select MAX(itemCode) from myStock");
            rs.next();
            rs.getString("MAX(itemCode)");
            
            if(rs.getString("MAX(itemCode)")==null){
                lblItemCode.setText("PR001");
            }else{
                long id=Long.parseLong(rs.getString("MAX(itemCode)").substring(2,rs.getString("MAX(itemCode)").length()));
                id++;
                lblItemCode.setText("PR"+String.format("%03d", id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void Stock_Table(){
        try {
            pst=con.prepareStatement("select * from myStock");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<=c;i++){
                    v.add(rs.getString("itemCode"));
                    v.add(rs.getString("itemName"));
                    v.add(rs.getInt("buyingPrice"));
                    v.add(rs.getInt("sellingPrice"));
                    v.add(rs.getString("description"));
                    v.add(rs.getInt("quantity"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel3 = new javax.swing.JLabel();
        txtItemName = new javax.swing.JTextField();
        cancelBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        txtDescription = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblItemCode = new javax.swing.JLabel();
        txtBuyingPrice = new javax.swing.JTextField();
        txtSellingPrice = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        homeBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        exportBtn = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        lblProfile = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("STOCK");
        setMaximizedBounds(new java.awt.Rectangle(1248, 769, 769, 769));
        setMaximumSize(new java.awt.Dimension(1248, 769));
        setMinimumSize(new java.awt.Dimension(1248, 769));
        setPreferredSize(new java.awt.Dimension(1248, 769));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ITEM CODE:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ITEM NAME:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        txtItemName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtItemNameKeyPressed(evt);
            }
        });
        jPanel1.add(txtItemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 240, 30));

        cancelBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cancelBtn.setForeground(new java.awt.Color(0, 204, 0));
        cancelBtn.setText("CANCEL");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        jPanel1.add(cancelBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 130, 40));

        addBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        addBtn.setForeground(new java.awt.Color(51, 51, 255));
        addBtn.setText("ADD PRODUCT");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 440, 160, 40));

        exitBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(255, 0, 0));
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 500, 110, 40));

        txtDescription.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescriptionKeyPressed(evt);
            }
        });
        jPanel1.add(txtDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, 240, 30));

        jLabel4.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("BUYING PRICE @:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("DESCRIPTION:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        lblItemCode.setFont(new java.awt.Font("Dialog", 3, 16)); // NOI18N
        lblItemCode.setForeground(new java.awt.Color(255, 255, 255));
        lblItemCode.setText("ITEM CODE");
        lblItemCode.setAutoscrolls(true);
        lblItemCode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblItemCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 240, 30));

        txtBuyingPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuyingPriceKeyPressed(evt);
            }
        });
        jPanel1.add(txtBuyingPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 240, 30));

        txtSellingPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSellingPriceKeyPressed(evt);
            }
        });
        jPanel1.add(txtSellingPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 240, 30));

        jLabel7.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("SELLING PRICE @:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jLabel8.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("QUANTITY:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQuantityKeyPressed(evt);
            }
        });
        jPanel1.add(txtQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 240, 30));

        homeBtn.setText("HOME PAGE");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 130, 40));

        deleteBtn.setText("DELETE PRODUCT");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 500, 160, 40));

        updateBtn.setText("UPDATE");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });
        jPanel1.add(updateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 440, 110, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 470, 640));

        jLabel1.setFont(new java.awt.Font("Dialog", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 51, 0));
        jLabel1.setText("BUSINESS STOCK");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, -1, -1));

        jPanel2.setBackground(new java.awt.Color(0, 0, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ITEM CODE", "ITEM NAME", "BUYING PRICE", "SELLING PRICE", "DESCRIPTION", "QUANTITY"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 700, 470));

        exportBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        exportBtn.setForeground(new java.awt.Color(0, 51, 204));
        exportBtn.setText("EXPORT CONTENTS");
        exportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportBtnActionPerformed(evt);
            }
        });
        jPanel2.add(exportBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, -1, 40));

        btnSearch.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(102, 0, 102));
        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel2.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });
        jPanel2.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 200, 30));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel2.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 70, 70));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, 730, 640));

        lblUsername.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(0, 51, 204));
        lblUsername.setText("USERNAME");
        getContentPane().add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 40, -1, -1));
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 6, 1250, 760));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Do You want to exit system?","Stock Management System",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        txtItemName.setText("");
        txtBuyingPrice.setText("");
        txtSellingPrice.setText("");
        txtDescription.setText("");
        txtQuantity.setText("");
        txtItemName.requestFocus();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String itemCode=lblItemCode.getText();
        String itemName=txtItemName.getText();
        int buyingPrice=Integer.parseInt(txtBuyingPrice.getText());
        int sellingPrice=Integer.parseInt(txtSellingPrice.getText());
        String description=txtDescription.getText();
        int quantity=Integer.parseInt(txtQuantity.getText());
        
        if(txtItemName.getText().trim().equals("") || txtBuyingPrice.getText().trim().equals("") || txtSellingPrice.getText().trim().equals("") || txtDescription.getText().trim().equals("") || txtQuantity.getText().trim().equals("")){
                JOptionPane.showMessageDialog(this,"You cannot submit null product details, please fill all the spaces provided!!","Submission Error!!!",JOptionPane.CANCEL_OPTION);
                txtItemName.requestFocus();
            }else{
        try {
            
            pst=con.prepareStatement("insert into myStock (itemCode,itemName,buyingPrice,sellingPrice,description,quantity) values(?,?,?,?,?,?)");
            pst.setString(1,itemCode);
            pst.setString(2,itemName);
            pst.setInt(3,buyingPrice);
            pst.setInt(4,sellingPrice);
            pst.setString(5,description);
            pst.setInt(6,quantity);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Product Successfully Added to Stock","Stock Management System",JOptionPane.INFORMATION_MESSAGE);
            Auto_ID();
            txtItemName.setText("");
            txtBuyingPrice.setText("");
            txtSellingPrice.setText("");
            txtDescription.setText("");
            txtQuantity.setText("");
            txtItemName.requestFocus();
            Stock_Table();
            
        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    }//GEN-LAST:event_addBtnActionPerformed

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        this.setVisible(false);
        new Home_Page(username,imag).setVisible(true);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        String itemName=txtItemName.getText();
        int buyingPrice=Integer.parseInt(txtBuyingPrice.getText());
        int sellingPrice=Integer.parseInt(txtSellingPrice.getText());
        String description=txtDescription.getText();
        int quantity=Integer.parseInt(txtQuantity.getText());
        String itemCode=lblItemCode.getText();
        try {
            pst=con.prepareStatement("update myStock set itemName=?, buyingPrice=?, sellingPrice=?, description=?, quantity=? where itemCode=?");
            pst.setString(1,itemName);
            pst.setInt(2,buyingPrice);
            pst.setInt(3,sellingPrice);
            pst.setString(4,description);
            pst.setInt(5,quantity);
            pst.setString(6,itemCode);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Product in Stock Updated Successfully","Stock Management System",JOptionPane.INFORMATION_MESSAGE);
            Auto_ID();
            txtItemName.setText("");
            txtBuyingPrice.setText("");
            txtSellingPrice.setText("");
            txtDescription.setText("");
            txtQuantity.setText("");
            txtItemName.requestFocus();
            Stock_Table();
        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        String itemCode=lblItemCode.getText();
        try {
            pst=con.prepareStatement("delete  from myStock where itemCode=?");
            pst.setString(1,itemCode);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Product Deleted Successfully from your Stock","Stock Management System",JOptionPane.INFORMATION_MESSAGE);
            Auto_ID();
            txtItemName.setText("");
            txtBuyingPrice.setText("");
            txtSellingPrice.setText("");
            txtDescription.setText("");
            txtQuantity.setText("");
            txtItemName.requestFocus();
            Stock_Table();
        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        DefaultTableModel model=(DefaultTableModel)table.getModel();
        int selectIndex=table.getSelectedRow();
        
        lblItemCode.setText(model.getValueAt(selectIndex, 0).toString());
        txtItemName.setText(model.getValueAt(selectIndex, 1).toString());
        txtBuyingPrice.setText(model.getValueAt(selectIndex, 2).toString());
        txtSellingPrice.setText(model.getValueAt(selectIndex, 3).toString());
        txtDescription.setText(model.getValueAt(selectIndex, 4).toString());
        txtQuantity.setText(model.getValueAt(selectIndex, 5).toString());
        
        deleteBtn.setEnabled(true);
        updateBtn.setEnabled(true);
    }//GEN-LAST:event_tableMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        deleteBtn.setEnabled(false);
        updateBtn.setEnabled(false);
    }//GEN-LAST:event_formWindowActivated

    private void txtItemNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtItemNameKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtBuyingPrice.requestFocus();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtBuyingPrice.requestFocus();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtItemName.requestFocus();
        }
    }//GEN-LAST:event_txtItemNameKeyPressed

    private void txtBuyingPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuyingPriceKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtSellingPrice.requestFocus();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtSellingPrice.requestFocus();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtItemName.requestFocus();
        }
    }//GEN-LAST:event_txtBuyingPriceKeyPressed

    private void txtSellingPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSellingPriceKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtDescription.requestFocus();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtDescription.requestFocus();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtBuyingPrice.requestFocus();
        }
    }//GEN-LAST:event_txtSellingPriceKeyPressed

    private void txtDescriptionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescriptionKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtQuantity.requestFocus();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtQuantity.requestFocus();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtSellingPrice.requestFocus();
        }
    }//GEN-LAST:event_txtDescriptionKeyPressed

    private void txtQuantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        String itemCode=lblItemCode.getText();
        String itemName=txtItemName.getText();
        int buyingPrice=Integer.parseInt(txtBuyingPrice.getText());
        int sellingPrice=Integer.parseInt(txtSellingPrice.getText());
        String description=txtDescription.getText();
        int quantity=Integer.parseInt(txtQuantity.getText());
        try {
            pst=con.prepareStatement("insert into myStock (itemCode,itemName,buyingPrice,sellingPrice,description,quantity) values(?,?,?,?,?,?)");
            pst.setString(1,itemCode);
            pst.setString(2,itemName);
            pst.setInt(3,buyingPrice);
            pst.setInt(4,sellingPrice);
            pst.setString(5,description);
            pst.setInt(6,quantity);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Product Successfully Added to Stock","Stock Management System",JOptionPane.INFORMATION_MESSAGE);
            Auto_ID();
            txtItemName.setText("");
            txtBuyingPrice.setText("");
            txtSellingPrice.setText("");
            txtDescription.setText("");
            txtQuantity.setText("");
            txtItemName.requestFocus();
            Stock_Table();
        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            txtItemName.requestFocus();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_UP){
            txtDescription.requestFocus();
        }
    }//GEN-LAST:event_txtQuantityKeyPressed

    private void exportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportBtnActionPerformed
        JFileChooser fileChooser=new JFileChooser();
        fileChooser.setDialogTitle("Specify the File Name");
        int userSelection=fileChooser.showSaveDialog(this);
        if(userSelection==JFileChooser.APPROVE_OPTION){
            File fileToSave=fileChooser.getSelectedFile();

            FileWriter writer;
            try {
                writer=new FileWriter(fileToSave);
                BufferedWriter bWriter=new BufferedWriter(writer);

                for (int i = 0; i < table.getRowCount(); i++) {
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        bWriter.write(table.getValueAt(i,j).toString()+",");
                    }
                    bWriter.newLine();
                }
                JOptionPane.showMessageDialog(this,"Successfully Loaded","Information",JOptionPane.INFORMATION_MESSAGE);
                bWriter.close();
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_exportBtnActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String itemName=txtSearch.getText();
        try {
            pst=con.prepareStatement("select * from myStock where itemName  LIKE CONCAT('%',?,'%')");
            //pst=con.prepareStatement("select * from myStock where itemName like '%'||?||'%'");
            pst.setString(1,itemName);
            rs=pst.executeQuery();

            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();

            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<=c;i++){
                    v.add(rs.getString("itemCode"));
                        v.add(rs.getString("itemName"));
                        v.add(rs.getString("buyingPrice"));
                        v.add(rs.getString("sellingPrice"));
                        v.add(rs.getString("description"));
                        v.add(rs.getString("quantity"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String itemName=txtSearch.getText();
            try {
                pst=con.prepareStatement("select * from myStock where itemName like CONCAT('%',?,'%')");
                //pst=con.prepareStatement("select * from myStock where itemName like '%'||?||'%'");
                pst.setString(1, itemName);
                rs=pst.executeQuery();
                ResultSetMetaData rsm=rs.getMetaData();
                int c=rsm.getColumnCount();

                DefaultTableModel model=(DefaultTableModel)table.getModel();
                model.setRowCount(0);
                while(rs.next()){
                    Vector v=new Vector();
                    for(int i=1;i<=c;i++){
                        v.add(rs.getString("itemCode"));
                        v.add(rs.getString("itemName"));
                        v.add(rs.getString("buyingPrice"));
                        v.add(rs.getString("sellingPrice"));
                        v.add(rs.getString("description"));
                        v.add(rs.getString("quantity"));
                    }
                    model.addRow(v);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtSearchKeyPressed

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
            java.util.logging.Logger.getLogger(Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Stock().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton exportBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblItemCode;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtBuyingPrice;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSellingPrice;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
