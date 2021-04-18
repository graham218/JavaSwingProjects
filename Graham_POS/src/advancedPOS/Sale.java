/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advancedPOS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bill
 */
public class Sale extends javax.swing.JPanel {

    PreparedStatement pst;
    Statement st;
    ResultSet rs;
    public static String bar_code;
    public static String cus_id;
    /**
     * Creates new form Sale
     */
    public Sale() {
        initComponents();
        data_load();
    }
    public void data_load(){
        try {
            pst=Database.myCon().prepareStatement("select * from customers");
            rs=pst.executeQuery();        
            while(rs.next()){
                combCustomer.addItem(rs.getString("customer_name"));
            }
            
            pst=Database.myCon().prepareStatement("select * from products");
            rs=pst.executeQuery();        
            while(rs.next()){
                combProduct.addItem(rs.getString("product_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sale.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            //load invoice number
            pst=Database.myCon().prepareStatement("SELECT * from extra where exid=1");
            rs=pst.executeQuery();
            
            if(rs.next()){
                txtInvoiceNo.setText(rs.getString("val"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sale.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int inv=Integer.parseInt(txtInvoiceNo.getText());
        inv++;
        txtInvoiceNo.setText(String.valueOf(inv));    
    }
    
    public void calculate_total(){
        Double qty=Double.valueOf(txtQty.getText());
        Double price=Double.valueOf(txtUnitPrice.getText());
        Double total;
        total=qty*price;
        txtTotalPrice.setText(String.valueOf(total));
    }
    
    public void cart_total(){
        double total=0;
        for (int i = 0; i < table.getRowCount(); i++) {
            double total2=Double.parseDouble(table.getValueAt(i, 5).toString());
            total=total+total2;
        }
        txtTotalAmount.setText(Double.toString(total));
        
        //total quantity
        double qty=0;
        for (int i = 0; i < table.getRowCount(); i++) {
            double qty2=Double.parseDouble(table.getValueAt(i, 3).toString());
            qty=qty+qty2;
        }
        txtTotalQty.setText(Double.toString(qty));
    }
    
    public void balance(){
        Double paid=Double.parseDouble(txtPaidAmount.getText());
        Double total=Double.parseDouble(txtTotalAmount.getText());
        Double balance;
        
        balance=paid-total;
        txtBalance.setText(Double.toString(balance));
    }
    
    public void Data_to_database(){
        //`cart_id`, `in_id`, `product_name`, `barcode`, `qty`, `unit_price`, `total_price`
        DefaultTableModel model=(DefaultTableModel)table.getModel();
        try {
            //cart table
                for (int i = 0; i < table.getRowCount(); i++) {
                String inid=model.getValueAt(i, 0).toString();
                String product_name=model.getValueAt(i, 1).toString();
                String barcode=model.getValueAt(i, 2).toString();
                String qty=model.getValueAt(i, 3).toString();
                String unit_price=model.getValueAt(i, 4).toString();
                String total_price=model.getValueAt(i, 5).toString();

                pst=Database.myCon().prepareStatement("insert into cart (in_id, product_name, barcode, qty, unit_price, total_price)values(?,?,?,?,?,?)");
                pst.setString(1, inid);
                pst.setString(2, product_name);
                pst.setString(3, barcode);
                pst.setString(4, qty);
                pst.setString(5, unit_price);
                pst.setString(6, total_price);
                pst.executeUpdate();
            }
                
            //sales table  `sales_id`, `inid`, `cid`, `customer_name`, `total_qty`, `total_bill`, `status`, `balance`
            String inid=txtInvoiceNo.getText();
            String customer_name=combCustomer.getModel().getSelectedItem().toString();
            String total_qty=txtTotalQty.getText();
            String total_bill=txtTotalAmount.getText();
            String balance=txtBalance.getText();
            
            //paid check
            Double tot=Double.parseDouble(txtTotalAmount.getText());
            Double paid_amount=Double.parseDouble(txtPaidAmount.getText());
            String status=null;
            if(paid_amount.equals(0.0)){
                status="UNPAID";
            }else if(tot>paid_amount){
                status="PARTIAL";
            }else if(tot<=paid_amount){
                status="PAID";
            }

            pst=Database.myCon().prepareStatement("insert into sales (inid, cid, customer_name, total_qty, total_bill, status,balance)values(?,?,?,?,?,?,?)");
            pst.setString(1, inid);
            pst.setString(2, cus_id);
            pst.setString(3, customer_name);
            pst.setString(4, total_qty);
            pst.setString(5, total_bill);
            pst.setString(6, status);
            pst.setString(7, balance);
            pst.executeUpdate();
            
            
            //extra
            String invid=txtInvoiceNo.getText();
            pst=Database.myCon().prepareStatement("update extra set val=? where exid=1");
            pst.setString(1, invid);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Sales inserted successfully into database","Success Message",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(Sale.class.getName()).log(Level.SEVERE, null, ex);
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
        txtInvoiceNo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        txtBalance = new javax.swing.JLabel();
        txtPaidAmount = new javax.swing.JTextField();
        txtTotalQty = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTotalAmount = new javax.swing.JLabel();
        payPrintBtn = new javax.swing.JToggleButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        addCartBtn = new javax.swing.JToggleButton();
        removeAllBtn = new javax.swing.JToggleButton();
        removeBtn = new javax.swing.JToggleButton();
        jPanel3 = new javax.swing.JPanel();
        txtUnitPrice = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        combProduct = new javax.swing.JComboBox<>();
        combCustomer = new javax.swing.JComboBox<>();
        txtQty = new javax.swing.JTextField();
        txtTotalPrice = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtBarCode = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1020, 550));
        setMinimumSize(new java.awt.Dimension(1020, 550));
        setPreferredSize(new java.awt.Dimension(1020, 550));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(21, 139, 40));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtInvoiceNo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txtInvoiceNo.setForeground(java.awt.Color.white);
        txtInvoiceNo.setText("01");
        jPanel1.add(txtInvoiceNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 120, -1));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("INVOICE NO:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 970, 50));

        jPanel2.setBackground(new java.awt.Color(21, 139, 40));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "INID", "NAME", "BAR CODE", "QTY", "UNIT PRICE", "TOTAL PRICE"
            }
        ));
        jScrollPane1.setViewportView(table);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 730, 140));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 760, 170));

        jPanel4.setBackground(new java.awt.Color(21, 139, 40));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBalance.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txtBalance.setForeground(java.awt.Color.white);
        txtBalance.setText("00.00");
        txtBalance.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel4.add(txtBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, 160, 30));

        txtPaidAmount.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txtPaidAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPaidAmountKeyReleased(evt);
            }
        });
        jPanel4.add(txtPaidAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 150, -1));

        txtTotalQty.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txtTotalQty.setForeground(java.awt.Color.white);
        txtTotalQty.setText("00.00");
        txtTotalQty.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel4.add(txtTotalQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 150, 30));

        jLabel10.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel10.setForeground(java.awt.Color.white);
        jLabel10.setText("BALANCE/DUE:");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, -1, -1));

        jLabel11.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel11.setForeground(java.awt.Color.white);
        jLabel11.setText("TOTAL AMOUNT:");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, -1));

        txtTotalAmount.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txtTotalAmount.setForeground(java.awt.Color.white);
        txtTotalAmount.setText("00.00");
        txtTotalAmount.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel4.add(txtTotalAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 160, 30));

        payPrintBtn.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        payPrintBtn.setText("PAY & PRINT");
        payPrintBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payPrintBtnActionPerformed(evt);
            }
        });
        jPanel4.add(payPrintBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 120, 160, -1));

        jLabel13.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel13.setForeground(java.awt.Color.white);
        jLabel13.setText("PAID AMOUNT:");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel14.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel14.setForeground(java.awt.Color.white);
        jLabel14.setText("TOTAL QTY:");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 760, 170));

        jPanel5.setBackground(new java.awt.Color(21, 139, 40));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addCartBtn.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N
        addCartBtn.setText("ADD TO CART");
        addCartBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCartBtnActionPerformed(evt);
            }
        });
        jPanel5.add(addCartBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 150, -1));

        removeAllBtn.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N
        removeAllBtn.setText("REMOVE ALL");
        removeAllBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAllBtnActionPerformed(evt);
            }
        });
        jPanel5.add(removeAllBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 150, -1));

        removeBtn.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N
        removeBtn.setText("REMOVE");
        removeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtnActionPerformed(evt);
            }
        });
        jPanel5.add(removeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 150, -1));

        add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 190, 200, 350));

        jPanel3.setBackground(new java.awt.Color(21, 139, 40));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtUnitPrice.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtUnitPrice.setForeground(java.awt.Color.white);
        txtUnitPrice.setText("00.00");
        txtUnitPrice.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel3.add(txtUnitPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 90, 30));

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("CUSTOMER:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("PRODUCT:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("QTY:");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, -1, -1));

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("UNIT PRICE:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, -1, -1));

        combProduct.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        combProduct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT" }));
        combProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combProductActionPerformed(evt);
            }
        });
        jPanel3.add(combProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 160, -1));

        combCustomer.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        combCustomer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT" }));
        combCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combCustomerActionPerformed(evt);
            }
        });
        jPanel3.add(combCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 160, -1));

        txtQty.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQtyKeyReleased(evt);
            }
        });
        jPanel3.add(txtQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 90, -1));

        txtTotalPrice.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtTotalPrice.setForeground(java.awt.Color.white);
        txtTotalPrice.setText("00.00");
        txtTotalPrice.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel3.add(txtTotalPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 150, 30));

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel8.setForeground(java.awt.Color.white);
        jLabel8.setText("TOTAL PRICE:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, -1, -1));

        jLabel12.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel12.setForeground(java.awt.Color.white);
        jLabel12.setText("BARCODE:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, -1, -1));

        txtBarCode.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtBarCode.setForeground(java.awt.Color.white);
        txtBarCode.setText("00.00");
        txtBarCode.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white, 3));
        jPanel3.add(txtBarCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, 150, 30));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 970, 110));
    }// </editor-fold>//GEN-END:initComponents

    private void removeAllBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAllBtnActionPerformed
        DefaultTableModel model=(DefaultTableModel)table.getModel();
        model.setRowCount(0);
        cart_total();
        balance();
    }//GEN-LAST:event_removeAllBtnActionPerformed

    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtnActionPerformed
        DefaultTableModel model=(DefaultTableModel)table.getModel();
        int selectedRow=table.getSelectedRow();
        model.removeRow(selectedRow);
        cart_total();
        balance();
    }//GEN-LAST:event_removeBtnActionPerformed

    private void combProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combProductActionPerformed
        String product_name=combProduct.getModel().getSelectedItem().toString();
        
        try {
            pst=Database.myCon().prepareStatement("select * from products where product_name=?");
            pst.setString(1, product_name);
            rs=pst.executeQuery();
            
            if(rs.next()){
                txtUnitPrice.setText(rs.getString("price"));
                bar_code=rs.getString("barcode");
                txtBarCode.setText(bar_code);
            }
            calculate_total();
        } catch (SQLException ex) {
            Logger.getLogger(Sale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_combProductActionPerformed

    private void txtQtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyReleased
        calculate_total();
    }//GEN-LAST:event_txtQtyKeyReleased

    private void addCartBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCartBtnActionPerformed
        DefaultTableModel model=(DefaultTableModel)table.getModel();
        String inid=txtInvoiceNo.getText();
        String product_name=combProduct.getModel().getSelectedItem().toString();
        String barcode=txtBarCode.getText();
        int quantity=Integer.valueOf(txtQty.getText());
        Double unitPrice=Double.parseDouble(txtUnitPrice.getText());
        Double totalPrice=Double.parseDouble(txtTotalPrice.getText());
        model.addRow(new Object[]{
            inid,
            product_name,
            barcode,
            quantity,
            unitPrice,
            totalPrice
        });
        cart_total();
        balance();
    }//GEN-LAST:event_addCartBtnActionPerformed

    private void txtPaidAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaidAmountKeyReleased
        balance();
    }//GEN-LAST:event_txtPaidAmountKeyReleased

    private void payPrintBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payPrintBtnActionPerformed
        Data_to_database();
    }//GEN-LAST:event_payPrintBtnActionPerformed

    private void combCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combCustomerActionPerformed
        String customer_name=combCustomer.getModel().getSelectedItem().toString();
        
        try {
            pst=Database.myCon().prepareStatement("select * from customers where customer_name=?");
            pst.setString(1, customer_name);
            rs=pst.executeQuery();
            
            if(rs.next()){
            cus_id=rs.getString("cid");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_combCustomerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton addCartBtn;
    private javax.swing.JComboBox<String> combCustomer;
    private javax.swing.JComboBox<String> combProduct;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton payPrintBtn;
    private javax.swing.JToggleButton removeAllBtn;
    private javax.swing.JToggleButton removeBtn;
    private javax.swing.JTable table;
    private javax.swing.JLabel txtBalance;
    private javax.swing.JLabel txtBarCode;
    private javax.swing.JLabel txtInvoiceNo;
    private javax.swing.JTextField txtPaidAmount;
    private javax.swing.JTextField txtQty;
    private javax.swing.JLabel txtTotalAmount;
    private javax.swing.JLabel txtTotalPrice;
    private javax.swing.JLabel txtTotalQty;
    private javax.swing.JLabel txtUnitPrice;
    // End of variables declaration//GEN-END:variables
}
