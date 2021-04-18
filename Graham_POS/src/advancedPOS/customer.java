/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advancedPOS;

import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bill
 */
public class customer extends javax.swing.JPanel {

    Statement s;
    PreparedStatement pst;
    ResultSet rs;
    /**
     * Creates new form customer
     */
    public customer() {
        initComponents();
        customer_table();
        txtName.setText("");
    }
    
    public void customer_table(){
        try {
            pst=Database.myCon().prepareStatement("select * from customers");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i < c; i++) {
                    v.add(rs.getInt("cid"));
                    v.add(rs.getString("customer_name"));
                    v.add(rs.getString("tp_no"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(customer.class.getName()).log(Level.SEVERE, null, ex);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTPNumber = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        saveBtn = new javax.swing.JButton();
        searchBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtSearchId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        search = new javax.swing.JTextField();
        searchbtna = new javax.swing.JToggleButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 35, 192));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        jLabel1.setText("NAME:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 60, -1));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        jLabel2.setText("T.P NUMBER:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 100, -1));
        jPanel2.add(txtTPNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 230, 30));
        jPanel2.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 230, -1));

        saveBtn.setBackground(new java.awt.Color(28, 64, 195));
        saveBtn.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        saveBtn.setForeground(java.awt.Color.white);
        saveBtn.setText("SAVE");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });
        jPanel2.add(saveBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 70, 50));

        searchBtn.setBackground(new java.awt.Color(39, 195, 28));
        searchBtn.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        searchBtn.setForeground(java.awt.Color.white);
        searchBtn.setText("SEARCH");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        jPanel2.add(searchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, 50));

        updateBtn.setBackground(new java.awt.Color(26, 138, 33));
        updateBtn.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        updateBtn.setForeground(java.awt.Color.white);
        updateBtn.setText("UPDATE");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });
        jPanel2.add(updateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, -1, 50));

        clearBtn.setBackground(new java.awt.Color(49, 151, 146));
        clearBtn.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        clearBtn.setForeground(java.awt.Color.white);
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel2.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, -1, 50));

        deleteBtn.setBackground(new java.awt.Color(210, 50, 25));
        deleteBtn.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        deleteBtn.setForeground(java.awt.Color.white);
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel2.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, -1, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 450, 320));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "CUSTOMER'S INFO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 3, 12))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtSearchId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchIdKeyPressed(evt);
            }
        });
        jPanel3.add(txtSearchId, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 230, -1));

        jLabel3.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        jLabel3.setText("SEARCH ID:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 90, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 450, 90));

        jPanel4.setBackground(new java.awt.Color(224, 101, 48));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CUSTOMER NAME", "T.P NUMBER"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 53, 450, 340));

        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchKeyPressed(evt);
            }
        });
        jPanel4.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 230, -1));

        searchbtna.setFont(new java.awt.Font("Ubuntu", 3, 13)); // NOI18N
        searchbtna.setText("SEARCH WITH NAME");
        searchbtna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbtnaActionPerformed(evt);
            }
        });
        jPanel4.add(searchbtna, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 170, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 490, 410));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 450));
    }// </editor-fold>//GEN-END:initComponents

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        String name=txtName.getText();
        String tp_no=txtTPNumber.getText();
        
        try {
            s=Database.myCon().createStatement();
            s.executeUpdate("insert into customers(customer_name,tp_no) values('"+name+"','"+tp_no+"')");
            JOptionPane.showMessageDialog(this, "Customer inserted successfully into database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            txtName.setText("");
            txtTPNumber.setText("");
            txtName.requestFocus();
            customer_table();
        } catch (SQLException ex) {
            Logger.getLogger(customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_saveBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String cid=txtSearchId.getText();
        try {
            pst=Database.myCon().prepareStatement("select * from customers where cid LIKE CONCAT('%',?,'%')");
            pst.setString(1, cid);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i < c; i++) {
                    v.add(rs.getInt("cid"));
                    v.add(rs.getString("customer_name"));
                    v.add(rs.getString("tp_no"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    private void txtSearchIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchIdKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String cid=txtSearchId.getText();
            try {
                pst=Database.myCon().prepareStatement("select * from customers where cid LIKE CONCAT('%',?,'%')");
                pst.setString(1, cid);
                rs=pst.executeQuery();
                ResultSetMetaData rsm=rs.getMetaData();
                int c=rsm.getColumnCount();

                DefaultTableModel model=(DefaultTableModel)table.getModel();
                model.setRowCount(0);
                while(rs.next()){
                    Vector v=new Vector();
                    for (int i = 1; i < c; i++) {
                        v.add(rs.getInt("cid"));
                        v.add(rs.getString("customer_name"));
                        v.add(rs.getString("tp_no"));
                    }
                    model.addRow(v);
                }
            } catch (SQLException ex) {
                Logger.getLogger(customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtSearchIdKeyPressed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        txtName.setText("");
        txtTPNumber.setText("");
        txtName.requestFocus();
        customer_table();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        String name=txtName.getText();
        String tp_no=txtTPNumber.getText();
        DefaultTableModel model=(DefaultTableModel)table.getModel();
        int selectedIndex=table.getSelectedRow();
        int id=(int)model.getValueAt(selectedIndex, 0);
        try {
            pst=Database.myCon().prepareStatement("update customers set customer_name=?, tp_no=? where cid=?");
            pst.setString(1,name);
            pst.setString(2,tp_no);
            pst.setInt(3,id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Customer updated successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
            txtName.setText("");
            txtTPNumber.setText("");
            txtName.requestFocus();
            customer_table();
        } catch (SQLException ex) {
            Logger.getLogger(customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        DefaultTableModel model=(DefaultTableModel)table.getModel();
        int selectedIndex=table.getSelectedRow();
        int id=(int)model.getValueAt(selectedIndex, 0);
        try {
            pst=Database.myCon().prepareStatement("delete from customers where cid=?");
            pst.setInt(1,id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Customer deleted successfully from database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            txtName.setText("");
            txtTPNumber.setText("");
            txtName.requestFocus();
            customer_table();
        } catch (SQLException ex) {
            Logger.getLogger(customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        DefaultTableModel model=(DefaultTableModel)table.getModel();
        int selectedIndex=table.getSelectedRow();
        
        txtName.setText(model.getValueAt(selectedIndex, 1).toString());
        txtTPNumber.setText(model.getValueAt(selectedIndex, 2).toString());
    }//GEN-LAST:event_tableMouseClicked

    private void searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String customer_name=txtSearchId.getText();
            try {
                pst=Database.myCon().prepareStatement("select * from customers where customer_name LIKE CONCAT('%',?,'%')");
                pst.setString(1, customer_name);
                rs=pst.executeQuery();
                ResultSetMetaData rsm=rs.getMetaData();
                int c=rsm.getColumnCount();

                DefaultTableModel model=(DefaultTableModel)table.getModel();
                model.setRowCount(0);
                while(rs.next()){
                    Vector v=new Vector();
                    for (int i = 1; i < c; i++) {
                        v.add(rs.getInt("cid"));
                        v.add(rs.getString("customer_name"));
                        v.add(rs.getString("tp_no"));
                    }
                    model.addRow(v);
                }
            } catch (SQLException ex) {
                Logger.getLogger(customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_searchKeyPressed

    private void searchbtnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbtnaActionPerformed
        String customer_name=txtSearchId.getText();
        try {
            pst=Database.myCon().prepareStatement("select * from customers where customer_name LIKE CONCAT('%',?,'%')");
            pst.setString(1, customer_name);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i = 1; i < c; i++) {
                    v.add(rs.getInt("cid"));
                    v.add(rs.getString("customer_name"));
                    v.add(rs.getString("tp_no"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchbtnaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField search;
    private javax.swing.JButton searchBtn;
    private javax.swing.JToggleButton searchbtna;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSearchId;
    private javax.swing.JTextField txtTPNumber;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}