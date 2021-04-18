/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advancedPOS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bill
 */
public class Invoice extends javax.swing.JPanel {

    PreparedStatement pst;
    ResultSet rs;
    /**
     * Creates new form Invoice
     */
    public Invoice() {
        initComponents();
        sales_load();
    }

    //`sales_id`, `inid`, `cid`, `customer_name`, `total_qty`, `total_bill`, `status`, `balance`
    
    public void sales_load(){
        try {
            pst=Database.myCon().prepareStatement("select * from sales");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i < c; i++) {
                    v.add(rs.getString("sales_id"));
                    v.add(rs.getString("inid"));
                    v.add(rs.getString("cid"));
                    v.add(rs.getString("customer_name"));
                    v.add(rs.getString("total_qty"));
                    v.add(rs.getString("total_bill"));
                    v.add(rs.getString("status"));
                    v.add(rs.getString("balance"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void search_param(){
        String nid=txtInid.getText();
        String customer_name=txtCustomerName.getText();
        String status=combStatus.getModel().getSelectedItem().toString();
        
        try {
            pst=Database.myCon().prepareStatement("select * from sales where inid LIKE CONCAT('%',?,'%') and customer_name LIKE CONCAT('%',?,'%') and status LIKE CONCAT('%',?,'%')");
            pst.setString(1, nid);
            pst.setString(2, customer_name);
            pst.setString(3, status);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i < c; i++) {
                    v.add(rs.getString("sales_id"));
                    v.add(rs.getString("inid"));
                    v.add(rs.getString("cid"));
                    v.add(rs.getString("customer_name"));
                    v.add(rs.getString("total_qty"));
                    v.add(rs.getString("total_bill"));
                    v.add(rs.getString("status"));
                    v.add(rs.getString("balance"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel6 = new javax.swing.JLabel();
        txtInid = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCustomerName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        combStatus = new javax.swing.JComboBox<>();
        btnRefresh = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();

        setMaximumSize(new java.awt.Dimension(1020, 550));
        setMinimumSize(new java.awt.Dimension(1020, 550));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(69, 22, 115));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("INID:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        txtInid.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtInid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtInidKeyReleased(evt);
            }
        });
        jPanel1.add(txtInid, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 140, -1));

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("STATUS:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, -1, -1));

        txtCustomerName.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtCustomerName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCustomerNameKeyReleased(evt);
            }
        });
        jPanel1.add(txtCustomerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 140, -1));

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel8.setForeground(java.awt.Color.white);
        jLabel8.setText("CUSTOMER NAME:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, -1, -1));

        combStatus.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        combStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UNPAID", "PARTIAL", "PAID" }));
        combStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combStatusActionPerformed(evt);
            }
        });
        jPanel1.add(combStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 140, -1));

        btnRefresh.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N
        btnRefresh.setText("REFRESH");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        jPanel1.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 20, -1, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1000, 70));

        jPanel2.setBackground(new java.awt.Color(69, 22, 115));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SALES ID", "INID", "CID", "CUSTOMER NAME", "TOTAL QTY", "TOTAL BILL", "STATUS", "BALANCE"
            }
        ));
        jScrollPane1.setViewportView(table);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 960, 310));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 1000, 340));

        jPanel3.setBackground(new java.awt.Color(69, 22, 115));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 1000, 80));
    }// </editor-fold>//GEN-END:initComponents

    private void txtInidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInidKeyReleased
        search_param();
    }//GEN-LAST:event_txtInidKeyReleased

    private void txtCustomerNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCustomerNameKeyReleased
        search_param();
    }//GEN-LAST:event_txtCustomerNameKeyReleased

    private void combStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combStatusActionPerformed
        search_param();
    }//GEN-LAST:event_combStatusActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        sales_load();
    }//GEN-LAST:event_btnRefreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox<String> combStatus;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtCustomerName;
    private javax.swing.JTextField txtInid;
    // End of variables declaration//GEN-END:variables
}