/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library_management_system;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Blob;
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
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class LostBooksGroups extends javax.swing.JFrame {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    String path1;
    /**
     * Creates new form CreateAccount
     */
    public LostBooksGroups() {
        initComponents();
        Connection();
        paidBooks();
    }

    String username;
    BufferedImage imag;
    public LostBooksGroups(String username,BufferedImage imag) {
        initComponents();
        Connection();
        this.username=username;
        this.imag=imag;
        lblUsername.setText(username);
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(lblProfile.getWidth(), lblProfile.getHeight(), Image.SCALE_SMOOTH)));
        current_date();
        confirmstudents();
        paidBooks();
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("../Images/caleb-woods-fulXJYIvRi8-unsplash.jpg")).getImage().getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), Image.SCALE_SMOOTH)));
    }
    public void Connection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/library","root","");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LostBooksGroups.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LostBooksGroups.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
  
    
   

    public void confirmstudents(){
        try {
            pst=con.prepareStatement("select * from confirmgroups where confirmation='LOST'");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i =1; i <=c; i++) {
                    v.add(rs.getString("issueNo"));
                    v.add(rs.getString("dateReturned"));
                    v.add(rs.getString("bookNo"));
                    v.add(rs.getString("bookName"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("admNo"));
                    v.add(rs.getString("studentName"));
                    v.add(rs.getString("confirmation"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void paidBooks(){
        try {
            pst=con.prepareStatement("select * from paidgroups");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i =1; i <=c; i++) {
                    v.add(rs.getString("issueNo"));
                    v.add(rs.getString("datePaid"));
                    v.add(rs.getString("bookNo"));
                    v.add(rs.getString("bookName"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("admNo"));
                    v.add(rs.getString("studentName"));
                    v.add(rs.getString("payment"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void current_date(){
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now=LocalDateTime.now();
        String date=dtf.format(now);
        lblDatePaid.setText(date);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblProfile = new javax.swing.JLabel();
        homeBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        lblBookName = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        confirmBtn = new javax.swing.JToggleButton();
        lblDatePaid = new javax.swing.JLabel();
        lblBookNo = new javax.swing.JLabel();
        lblStudentName = new javax.swing.JLabel();
        lblAdmNo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblBookPrice = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblIssueNo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        searchBtn = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        combConfirm = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        search = new javax.swing.JToggleButton();
        txtSearch2 = new javax.swing.JTextField();
        printJTable = new javax.swing.JToggleButton();
        exportJtable = new javax.swing.JToggleButton();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BOOKS LOST BY GROUPS");
        setMaximizedBounds(new java.awt.Rectangle(1300, 700, 700, 700));
        setMinimumSize(new java.awt.Dimension(1300, 700));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 153));
        jLabel9.setText("BOOKS LOST BY GROUPS");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 650, -1));

        jPanel1.setBackground(new java.awt.Color(51, 102, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DATE PAID:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 170, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("BOOK NO:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 110, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("BOOK NAME:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 170, -1));

        lblUsername.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("USERNAME");
        jPanel1.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 90, 120, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ADMISSION NO:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 160, -1));

        lblProfile.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 0, 80, 80));

        homeBtn.setBackground(new java.awt.Color(0, 102, 102));
        homeBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        homeBtn.setForeground(new java.awt.Color(255, 255, 255));
        homeBtn.setText("HOME");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 450, 130, 40));

        clearBtn.setBackground(new java.awt.Color(0, 102, 0));
        clearBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(255, 255, 255));
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, 130, 40));

        exitBtn.setBackground(new java.awt.Color(255, 0, 0));
        exitBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(255, 255, 255));
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 140, 40));

        lblBookName.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblBookName.setForeground(new java.awt.Color(255, 255, 255));
        lblBookName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblBookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 240, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("CONFIRMATION:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 170, -1));

        confirmBtn.setBackground(new java.awt.Color(0, 51, 153));
        confirmBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        confirmBtn.setForeground(new java.awt.Color(255, 255, 255));
        confirmBtn.setText("PAY BOOK");
        confirmBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmBtnActionPerformed(evt);
            }
        });
        jPanel1.add(confirmBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 390, 140, 40));

        lblDatePaid.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblDatePaid.setForeground(new java.awt.Color(255, 255, 255));
        lblDatePaid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblDatePaid, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 240, 30));

        lblBookNo.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblBookNo.setForeground(new java.awt.Color(255, 255, 255));
        lblBookNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblBookNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 240, 30));

        lblStudentName.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblStudentName.setForeground(new java.awt.Color(255, 255, 255));
        lblStudentName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblStudentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 280, 240, 30));

        lblAdmNo.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblAdmNo.setForeground(new java.awt.Color(255, 255, 255));
        lblAdmNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblAdmNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, 240, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("BOOK PRICE:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 170, -1));

        lblBookPrice.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblBookPrice.setForeground(new java.awt.Color(255, 255, 255));
        lblBookPrice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblBookPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 240, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ISSUE NUMBER:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 170, -1));

        lblIssueNo.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblIssueNo.setForeground(new java.awt.Color(255, 255, 255));
        lblIssueNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblIssueNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 240, 30));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISSUE NO", "DATE RETURNED", "BOOK NO", "BOOK NAME", "BOOK PRICE", "ADM NO", "STUDENT NAME", "COFIRMATION"
            }
        ));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, 730, 180));

        searchBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        searchBtn.setText("SEARCH WITH ISSUE NUMBER");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        jPanel1.add(searchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 60, 300, 30));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 240, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("STUDENT NAME:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 170, -1));

        combConfirm.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        combConfirm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CONFIRM PAYMENT OF BOOK", "CASH", "NEW BOOK" }));
        jPanel1.add(combConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 330, -1, 30));

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISSUE NUMBER", "DATE PAID", "BOOK NO", "BOOK NAME", "PRICE", "ADM NO", "STUDENT NAME", "PAYMENT"
            }
        ));
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 350, 730, 220));

        search.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        search.setText("SEARCH WITH ISSUE NUMBER");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        jPanel1.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 313, 330, 30));

        txtSearch2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearch2KeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 310, 250, 30));

        printJTable.setBackground(new java.awt.Color(0, 102, 51));
        printJTable.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        printJTable.setForeground(new java.awt.Color(255, 255, 255));
        printJTable.setText("PRINT TABLE");
        printJTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printJTableActionPerformed(evt);
            }
        });
        jPanel1.add(printJTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 560, 320, 40));

        exportJtable.setBackground(new java.awt.Color(0, 102, 51));
        exportJtable.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        exportJtable.setForeground(new java.awt.Color(255, 255, 255));
        exportJtable.setText("EXPORT LOST BOOKS INTO EXCEL FILE");
        exportJtable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportJtableActionPerformed(evt);
            }
        });
        jPanel1.add(exportJtable, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 510, 320, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 1220, 610));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 6, 1300, 690));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        this.setVisible(false);
        new More(lblUsername.getText(),imag).setVisible(true);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        lblBookNo.setText("");
        lblIssueNo.setText("");
        lblBookName.setText("");
        lblBookPrice.setText("");
        lblAdmNo.setText("");
        lblStudentName.setText("");
        lblBookName.setText("");
        combConfirm.getModel().setSelectedItem("CONFIRM RETURN");
        txtSearch.requestFocus();
        paidBooks();
        confirmBtn.setEnabled(true);
    }//GEN-LAST:event_clearBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Do yo want to exit system","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private void confirmBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmBtnActionPerformed
        String issueNo=lblIssueNo.getText();
        String issueDate=lblDatePaid.getText();
        String bookNo=lblBookNo.getText();
        String bookName=lblBookName.getText();
        int bookPrice=Integer.parseInt(lblBookPrice.getText());
        String admissionNo=lblAdmNo.getText();
        String studentName=lblStudentName.getText();
        String confirmation=combConfirm.getSelectedItem().toString();
        if(combConfirm.getSelectedItem().toString().equals("CONFIRM PAYMENT OF BOOK")){
            JOptionPane.showMessageDialog(this, "Please confirm book payment","Error message",JOptionPane.CANCEL_OPTION);
            combConfirm.requestFocus();
        }else{
            try {
                pst=con.prepareStatement("insert into paidGroups(issueNo,datePaid,bookNo,bookName,price,admNo,studentName,payment)values(?,?,?,?,?,?,?,?)");
                pst.setString(1,issueNo);
                pst.setString(2,issueDate);
                pst.setString(3,bookNo);
                pst.setString(4,bookName);
                pst.setInt(5,bookPrice);
                pst.setString(6,admissionNo);
                pst.setString(7,studentName);
                pst.setString(8,confirmation);
                pst.executeUpdate();
                if(combConfirm.getSelectedItem().equals("CASH")||combConfirm.getSelectedItem().equals("NEW BOOK")){
                    JOptionPane.showMessageDialog(this,"This book will be deleted from the Lost books table for it is now considered paid","Warning!!",JOptionPane.YES_NO_OPTION);
                    pst=con.prepareStatement("delete from confirmgroups where bookNo=?");
                    pst.setString(1, bookNo);
                    pst.executeUpdate();
                }
                JOptionPane.showMessageDialog(this, "Book payment confirmed Successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
                lblBookNo.setText("");
                lblIssueNo.setText("");
                lblBookName.setText("");
                lblBookPrice.setText("");
                lblAdmNo.setText("");
                lblStudentName.setText("");
                lblBookName.setText("");
                combConfirm.getModel().setSelectedItem("CONFIRM RETURN");
                txtSearch.requestFocus();
                confirmstudents();
                paidBooks();
            } catch (SQLException ex) {
                Logger.getLogger(LostBooksGroups.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_confirmBtnActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String issueNo=txtSearch.getText();
        try {
            pst=con.prepareStatement("select * from confirmgroups where confirmation='LOST' AND issueNo LIKE CONCAT('%',?,'%')");
            pst.setString(1,issueNo);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i =1; i <=c; i++) {
                    v.add(rs.getString("issueNo"));
                    v.add(rs.getString("dateReturned"));
                    v.add(rs.getString("bookNo"));
                    v.add(rs.getString("bookName"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("admNo"));
                    v.add(rs.getString("studentName"));
                    v.add(rs.getString("confirmation"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
             String issueNo=txtSearch.getText();
        try {
            pst=con.prepareStatement("select * from confirmgroups where confirmation='LOST' AND issueNo LIKE CONCAT('%',?,'%')");
            pst.setString(1,issueNo);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i =1; i <=c; i++) {
                    v.add(rs.getString("issueNo"));
                    v.add(rs.getString("dateReturned"));
                    v.add(rs.getString("bookNo"));
                    v.add(rs.getString("bookName"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("admNo"));
                    v.add(rs.getString("studentName"));
                    v.add(rs.getString("confirmation"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        DefaultTableModel model=(DefaultTableModel)table1.getModel();
        int selectedIndex=table1.getSelectedRow();

        lblIssueNo.setText(model.getValueAt(selectedIndex, 0).toString());
        lblDatePaid.setText(model.getValueAt(selectedIndex, 1).toString());
        lblBookNo.setText(model.getValueAt(selectedIndex, 2).toString());
        lblBookName.setText(model.getValueAt(selectedIndex, 3).toString());
        lblBookPrice.setText(model.getValueAt(selectedIndex, 4).toString());
        lblAdmNo.setText(model.getValueAt(selectedIndex, 5).toString());
        lblStudentName.setText(model.getValueAt(selectedIndex, 6).toString());
    }//GEN-LAST:event_table1MouseClicked

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        String issueNo=txtSearch2.getText();
        
        try {
            pst=con.prepareStatement("select * from paidgroups where issueNo LIKE CONCAT('%',?,'%')");
            pst.setString(1, issueNo);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i =1; i <=c; i++) {
                    v.add(rs.getString("issueNo"));
                    v.add(rs.getString("datePaid"));
                    v.add(rs.getString("bookNo"));
                    v.add(rs.getString("bookName"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("admNo"));
                    v.add(rs.getString("studentName"));
                    v.add(rs.getString("payment"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchActionPerformed

    private void txtSearch2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String issueNo=txtSearch2.getText();
        
        try {
            pst=con.prepareStatement("select * from paidgroups where issueNo LIKE CONCAT('%',?,'%')");
            pst.setString(1, issueNo);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i =1; i <=c; i++) {
                    v.add(rs.getString("issueNo"));
                    v.add(rs.getString("datePaid"));
                    v.add(rs.getString("bookNo"));
                    v.add(rs.getString("bookName"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("admNo"));
                    v.add(rs.getString("studentName"));
                    v.add(rs.getString("payment"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_txtSearch2KeyPressed

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        confirmBtn.setEnabled(false);
        
        DefaultTableModel model=(DefaultTableModel)table2.getModel();
        int selectedIndex=table2.getSelectedRow();

        lblIssueNo.setText(model.getValueAt(selectedIndex, 0).toString());
        lblDatePaid.setText(model.getValueAt(selectedIndex, 1).toString());
        lblBookNo.setText(model.getValueAt(selectedIndex, 2).toString());
        lblBookName.setText(model.getValueAt(selectedIndex, 3).toString());
        lblBookPrice.setText(model.getValueAt(selectedIndex, 4).toString());
        lblAdmNo.setText(model.getValueAt(selectedIndex, 5).toString());
        lblStudentName.setText(model.getValueAt(selectedIndex, 6).toString());
        combConfirm.getModel().setSelectedItem(model.getValueAt(selectedIndex, 7).toString());
    }//GEN-LAST:event_table2MouseClicked

    private void printJTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printJTableActionPerformed
        MessageFormat header=new MessageFormat("BOOKS LOST BY STUDENTS");
        MessageFormat footer=new MessageFormat("Page {0}");

        try {
            table1.print(JTable.PrintMode.FIT_WIDTH,header,footer);
        } catch (PrinterException ex) {
            Logger.getLogger(PaidGroups.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_printJTableActionPerformed

    private void exportJtableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportJtableActionPerformed
        JFileChooser fileChooser=new JFileChooser();
        fileChooser.setDialogTitle("Specify the File Name");
        FileNameExtensionFilter filter=new FileNameExtensionFilter("Excel Files",".csv",".xls");
        fileChooser.addChoosableFileFilter(filter);
        int userSelection=fileChooser.showSaveDialog(this);
        if(userSelection==JFileChooser.APPROVE_OPTION){
            File fileToSave=fileChooser.getSelectedFile();

            FileWriter writer;
            try {
                writer=new FileWriter(fileToSave);
                BufferedWriter bWriter=new BufferedWriter(writer);
                for (int i = 0; i < table1.getRowCount(); i++) {
                    for (int j = 0; j < table1.getColumnCount(); j++) {
                        bWriter.write(table1.getValueAt(i,j).toString()+",");
                    }
                    bWriter.newLine();
                }
                JOptionPane.showMessageDialog(this,"Successfully Loaded","Information",JOptionPane.INFORMATION_MESSAGE);
                bWriter.close();
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_exportJtableActionPerformed

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
            java.util.logging.Logger.getLogger(LostBooksGroups.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LostBooksGroups.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LostBooksGroups.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LostBooksGroups.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new LostBooksGroups().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearBtn;
    private javax.swing.JComboBox<String> combConfirm;
    private javax.swing.JToggleButton confirmBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JToggleButton exportJtable;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAdmNo;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBookName;
    private javax.swing.JLabel lblBookNo;
    private javax.swing.JLabel lblBookPrice;
    private javax.swing.JLabel lblDatePaid;
    private javax.swing.JLabel lblIssueNo;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblStudentName;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JToggleButton printJTable;
    private javax.swing.JToggleButton search;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearch2;
    // End of variables declaration//GEN-END:variables
}
