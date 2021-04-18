/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facastenterprise;

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
import java.util.Locale;
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
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author bill
 */
public class Home_Page extends javax.swing.JFrame {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    /**
     * Creates new form Home_Page
     */
    public Home_Page() {
        initComponents();
    }
    
    String username;
    BufferedImage imag;
    public Home_Page(String username,BufferedImage imag ) {
        initComponents();
        this.username=username;
        txtUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        Connect();
        Stock_Table();
        jLabel11.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("pexels-gustavo-fring-3985093.jpg")).getImage().getScaledInstance(1280, 730, Image.SCALE_SMOOTH)));
    }
    
    
    public void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/Stock","root","");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void Stock_Table(){
        try {
            pst=con.prepareStatement("select * from myStock");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<=c;i++){
                    v.add(rs.getString("itemCode"));
                    v.add(rs.getString("itemName"));
                    v.add(rs.getString("description"));
                    v.add(rs.getString("sellingPrice"));                   
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Add(){
        String itemCode=txtItemCode.getText();
        if(txtQty.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter quantity, null items cannot be sold","Submission Error",JOptionPane.CANCEL_OPTION);
            txtQty.requestFocus();
        }
        try {
            pst=con.prepareStatement("select * from myStock where itemCode=?");
            pst.setString(1,itemCode);
            rs=pst.executeQuery();
            while(rs.next()){
                int currentQty=rs.getInt("quantity");
                int newQuantity=Integer.parseInt(txtQty.getText());
                int price=Integer.parseInt(txtPrice.getText());
                int total=newQuantity*price;
                
                if(newQuantity>currentQty){
                    JOptionPane.showMessageDialog(this, "Quantity in stock is not enough, Available quantity is "+currentQty, "Stock Management System", JOptionPane.OK_OPTION);
                    txtQty.requestFocus();
                }else{
                    DefaultTableModel model=(DefaultTableModel)table1.getModel();
                    int quantity=Integer.parseInt(txtQty.getText());
                    model.addRow(new Object[]{
                        txtItemCode.getText(),
                        txtItemName.getText(),
                        txtPrice.getText(),
                        txtDescription.getText(),
                        quantity,
                        total
                    });
                        txtItemCode.setText("");
                        txtItemName.setText("");
                        txtPrice.setText("");
                        txtDescription.setText("");
                        txtQty.setText("");
                     
                    int sum=0;
                    for(int i=0;i<table1.getRowCount();i++){
                        int total2=(int)table1.getValueAt(i,5);
                        sum=sum+total2;
                    }
                    txtSubtotal.setText(Integer.toString(sum));
                    txtPay.requestFocus();
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Sales(){
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now=LocalDateTime.now();
        String date=dtf.format(now);
        String cashier=txtUsername.getText();
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
            
        
        int rows=table1.getRowCount();
        String query1="insert into sales_product (date,salesId,itemCode,itemName,price,description,quantity,total) values(?,?,?,?,?,?,?,?)";
            pst=con.prepareStatement(query1);
            for(int i=0;i<table1.getRowCount();i++){
                String itemCode=(String) table1.getValueAt(i, 0);
                String itemName=(String)table1.getValueAt(i,1);
                String price=(String)table1.getValueAt(i,2);
                String description=(String)table1.getValueAt(i,3);
                int quantity=(int) table1.getValueAt(i,4);
                int total=(int) table1.getValueAt(i,5);
                
                pst.setString(1,date);
                pst.setInt(2, lastinsertedid);
                pst.setString(3,itemCode);
                pst.setString(4,itemName);
                pst.setString(5,price);
                pst.setString(6,description);
                pst.setInt(7,quantity);
                pst.setInt(8,total);
                pst.executeUpdate();
            }
        
        String query2="update myStock set quantity=quantity-? where itemCode=?";
            pst=con.prepareStatement(query2);
            for(int i=0;i<table1.getRowCount();i++){
                String itemCode=table1.getValueAt(i, 0).toString();
                int quantity=(int) table1.getValueAt(i,4);
                
                pst.setInt(1,quantity);
                pst.setString(2,itemCode);
                pst.execute();
            }
            pst.addBatch();           
            JOptionPane.showMessageDialog(this, "Sales inserted Successfully","Stock Management System",JOptionPane.INFORMATION_MESSAGE);
            HashMap m=new HashMap();
            m.put("invoiceNo", lastinsertedid);
            try {
                JasperDesign jdesign=JRXmlLoader.load("src/facastenterprise/receipt1.jrxml");
                JasperReport ireport=JasperCompileManager.compileReport(jdesign);
                JasperPrint jPrint=JasperFillManager.fillReport(ireport, m, con);
                //JasperViewer.viewReport(jPrint);
                JasperPrintManager.printReport(jPrint, false);
            } catch (JRException ex) {
                Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        userBtn = new javax.swing.JButton();
        stockBtn = new javax.swing.JButton();
        viewBtn = new javax.swing.JButton();
        authorBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        lblProfile = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtItemCode = new javax.swing.JTextField();
        txtItemName = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        txtQty = new javax.swing.JTextField();
        addBtn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        deleteBtn = new javax.swing.JButton();
        clearTblBtn = new javax.swing.JButton();
        txtUsername = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        printBtn = new javax.swing.JButton();
        txtSubtotal = new javax.swing.JTextField();
        txtPay = new javax.swing.JTextField();
        txtBalance = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home");
        setMaximizedBounds(new java.awt.Rectangle(1278, 732, 732, 732));
        setMaximumSize(new java.awt.Dimension(1278, 732));
        setMinimumSize(new java.awt.Dimension(1278, 732));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 255));
        jLabel1.setText("FACASTE ENTERPRISE");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, -1, -1));

        jPanel1.setBackground(new java.awt.Color(0, 153, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ITEM CODE", "ITEM NAME", "DESCRIPTION", "PRICE"
            }
        ));
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 530, 390));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 200, 40));

        btnSearch.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(102, 0, 102));
        btnSearch.setText("Search with item name");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 240, 40));

        userBtn.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        userBtn.setForeground(new java.awt.Color(0, 0, 204));
        userBtn.setText("CREATE USER");
        userBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userBtnActionPerformed(evt);
            }
        });
        jPanel1.add(userBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 150, 40));

        stockBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        stockBtn.setForeground(new java.awt.Color(0, 102, 0));
        stockBtn.setText("STOCK");
        stockBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockBtnActionPerformed(evt);
            }
        });
        jPanel1.add(stockBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 120, 40));

        viewBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        viewBtn.setForeground(new java.awt.Color(0, 0, 153));
        viewBtn.setText("VIEW SALES");
        viewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBtnActionPerformed(evt);
            }
        });
        jPanel1.add(viewBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 480, 130, 40));

        authorBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        authorBtn.setForeground(new java.awt.Color(102, 102, 0));
        authorBtn.setText("AUTHOR");
        authorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                authorBtnActionPerformed(evt);
            }
        });
        jPanel1.add(authorBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 540, 120, 40));

        exitBtn.setBackground(new java.awt.Color(221, 1, 1));
        exitBtn.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        exitBtn.setForeground(java.awt.Color.white);
        exitBtn.setText("EXIT SYSTEM");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 150, 40));

        editBtn.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        editBtn.setForeground(new java.awt.Color(153, 0, 153));
        editBtn.setText("EDIT USERS");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        jPanel1.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 540, 130, 40));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 60, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 550, 670));

        jPanel2.setBackground(new java.awt.Color(0, 153, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ITEM CODE:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 20));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("QUANTITY:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ITEM NAME:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("PRICE:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        txtItemCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtItemCodeKeyPressed(evt);
            }
        });
        jPanel2.add(txtItemCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 170, 30));
        jPanel2.add(txtItemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 170, 30));
        jPanel2.add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 170, 30));

        txtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQtyKeyPressed(evt);
            }
        });
        jPanel2.add(txtQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, 160, 30));

        addBtn.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        addBtn.setForeground(new java.awt.Color(0, 0, 255));
        addBtn.setText("ADD");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel2.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 160, 40));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("DESCRIPTION:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, -1));
        jPanel2.add(txtDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 160, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 690, 170));

        jPanel3.setBackground(new java.awt.Color(0, 153, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ITEM CODE", "ITEM NAME", "PRICE", "DESCRIPTION", "QUANTITY", "TOTAL"
            }
        ));
        jScrollPane1.setViewportView(table1);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 650, 210));

        deleteBtn.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(255, 0, 0));
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel3.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 250, 130, 40));

        clearTblBtn.setBackground(new java.awt.Color(227, 24, 24));
        clearTblBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        clearTblBtn.setForeground(java.awt.Color.white);
        clearTblBtn.setText("CLEAR TABLE");
        clearTblBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearTblBtnActionPerformed(evt);
            }
        });
        jPanel3.add(clearTblBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, -1, 40));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 260, 690, 300));

        txtUsername.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        txtUsername.setForeground(new java.awt.Color(0, 51, 153));
        txtUsername.setText("USERNAME");
        getContentPane().add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(1039, 230, 220, -1));

        jPanel5.setBackground(new java.awt.Color(0, 153, 51));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("SUBTOTAL:");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("PAY:");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, -1, -1));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("BALANCE:");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        printBtn.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        printBtn.setForeground(new java.awt.Color(102, 0, 102));
        printBtn.setText("PRINT RECEIPT");
        printBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBtnActionPerformed(evt);
            }
        });
        jPanel5.add(printBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 160, 40));
        jPanel5.add(txtSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 130, 30));

        txtPay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPayKeyPressed(evt);
            }
        });
        jPanel5.add(txtPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 160, 30));
        jPanel5.add(txtBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 130, 30));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 570, 560, 120));
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 6, 1280, 730));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        DefaultTableModel model=(DefaultTableModel)table2.getModel();
        int selectedIndex=table2.getSelectedRow();

        txtItemCode.setText(model.getValueAt(selectedIndex, 0).toString());
        txtItemName.setText(model.getValueAt(selectedIndex, 1).toString());
        txtPrice.setText(model.getValueAt(selectedIndex, 3).toString());
        txtDescription.setText(model.getValueAt(selectedIndex, 2).toString());
        txtQty.requestFocus();
    }//GEN-LAST:event_table2MouseClicked

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

                DefaultTableModel model=(DefaultTableModel)table2.getModel();
                model.setRowCount(0);
                while(rs.next()){
                    Vector v=new Vector();
                    for(int i=1;i<=c;i++){
                        v.add(rs.getString("itemCode"));
                        v.add(rs.getString("itemName"));
                        v.add(rs.getString("description"));
                        v.add(rs.getString("sellingPrice"));
                    }
                    model.addRow(v);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String itemName=txtSearch.getText();
        try {
            pst=con.prepareStatement("select * from myStock where itemName  LIKE CONCAT('%',?,'%')");
            //pst=con.prepareStatement("select * from myStock where itemName like '%'||?||'%'");
            pst.setString(1,itemName);
            rs=pst.executeQuery();

            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();

            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for(int i=1;i<=c;i++){
                    v.add(rs.getString("itemCode"));
                    v.add(rs.getString("itemName"));
                    v.add(rs.getString("description"));
                    v.add(rs.getString("sellingPrice"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void userBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userBtnActionPerformed
        this.setVisible(false);
        new Users(txtUsername.getText(),imag).setVisible(true);
    }//GEN-LAST:event_userBtnActionPerformed

    private void stockBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockBtnActionPerformed
        this.setVisible(false);
        new Stock(username,imag).setVisible(true);
    }//GEN-LAST:event_stockBtnActionPerformed

    private void viewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewBtnActionPerformed
        this.setVisible(false);
        new Sales(txtUsername.getText(),imag).setVisible(true);
    }//GEN-LAST:event_viewBtnActionPerformed

    private void authorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_authorBtnActionPerformed
        this.setVisible(false);
        new About(txtUsername.getText(),imag).setVisible(true);
    }//GEN-LAST:event_authorBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Do You want to exit system?","Stock Management System",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Are you an admin??? Only the admin is authorised to edit and delete users from this system!!!","WARNING!!!",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            this.setVisible(false);
            new LoginAdmin(txtUsername.getText(),imag).setVisible(true);
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void txtItemCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtItemCodeKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String itemCode=txtItemCode.getText();
            try {
                pst=con.prepareStatement("select * from myStock where itemCode=?");
                pst.setString(1,itemCode);
                rs=pst.executeQuery();
                if(rs.next()){
                    txtItemName.setText(rs.getString("itemName"));
                    txtPrice.setText(rs.getString("sellingPrice"));
                    txtDescription.setText(rs.getString("description"));
                }else{
                    JOptionPane.showMessageDialog(this,"Item Code Not Found!!!","Bill Graham",JOptionPane.CANCEL_OPTION);
                    txtItemCode.setText("");
                    txtItemCode.requestFocus();
                }

            } catch (SQLException ex) {
                Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtItemCodeKeyPressed

    private void txtQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Add();

        }
    }//GEN-LAST:event_txtQtyKeyPressed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        Add();

    }//GEN-LAST:event_addBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        DefaultTableModel model=(DefaultTableModel)table1.getModel();
        model.removeRow(table1.getSelectedRow());
        int sum=0;
        for(int i=0;i<table1.getRowCount();i++){
            sum=sum+Integer.parseInt(table1.getValueAt(0, 5).toString());
        }
        txtSubtotal.setText(Integer.toString(sum));
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void clearTblBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearTblBtnActionPerformed
        DefaultTableModel model=(DefaultTableModel)table1.getModel();
        model.setRowCount(0);
        txtSubtotal.setText("");
        txtPay.setText("");
        txtBalance.setText("");
        txtSearch.requestFocus();
    }//GEN-LAST:event_clearTblBtnActionPerformed

    private void printBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBtnActionPerformed
        int subtotal=Integer.parseInt(txtSubtotal.getText());
        int pay=Integer.parseInt(txtPay.getText());
        int balance=pay-subtotal;
        txtBalance.setText(String.valueOf(balance));
        Sales();
        
        //clear table and text boxes
        DefaultTableModel model=(DefaultTableModel)table1.getModel();
        model.setRowCount(0);
        txtSubtotal.setText("");
        txtPay.setText("");
        txtBalance.setText("");
        txtSearch.requestFocus();
    }//GEN-LAST:event_printBtnActionPerformed

    private void txtPayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPayKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            int subtotal=Integer.parseInt(txtSubtotal.getText());
            int pay=Integer.parseInt(txtPay.getText());
            int balance=pay-subtotal;
            txtBalance.setText(String.valueOf(balance));
            Sales();

        }
    }//GEN-LAST:event_txtPayKeyPressed

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
            java.util.logging.Logger.getLogger(Home_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home_Page().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton authorBtn;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton clearTblBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JButton printBtn;
    private javax.swing.JButton stockBtn;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JTextField txtBalance;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtItemCode;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtPay;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JLabel txtUsername;
    private javax.swing.JButton userBtn;
    private javax.swing.JButton viewBtn;
    // End of variables declaration//GEN-END:variables
}
