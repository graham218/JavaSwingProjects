/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library_management_system;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
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
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class IssueBooksStudents extends javax.swing.JFrame {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    String path1;
    /**
     * Creates new form CreateAccount
     */
    public IssueBooksStudents() {
        initComponents();
        Connection();
    }

    String username;
    BufferedImage imag;
    public IssueBooksStudents(String username,BufferedImage imag) {
        initComponents();
        Connection();
        this.username=username;
        this.imag=imag;
        lblUsername.setText(username);
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(lblProfile.getWidth(), lblProfile.getHeight(), Image.SCALE_SMOOTH)));
        books_table();
        students_table();
        current_date();
        issued_students_table();
        AutoID();
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("../Images/caleb-woods-fulXJYIvRi8-unsplash.jpg")).getImage().getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), Image.SCALE_SMOOTH)));
    }
    public void Connection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/library","root","");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IssueBooksStudents.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(IssueBooksStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void books_table(){
        try {
            pst=con.prepareStatement("select * from books");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i =1; i <=c; i++) {
                    v.add(rs.getString("bookId"));
                    v.add(rs.getString("bookNo"));
                    v.add(rs.getString("bookName"));
                    v.add(rs.getString("author"));
                    v.add(rs.getString("description"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("status"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IssueBooksStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void students_table(){
        try {
            pst=con.prepareStatement("select * from students");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i =1; i <=c; i++) {
                    v.add(rs.getString("studentId"));
                    v.add(rs.getString("admNo"));
                    v.add(rs.getString("name"));
                    v.add(rs.getBlob("profile_pic"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void issued_students_table(){
        try {
            pst=con.prepareStatement("select * from issuebooksstudents");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table2.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i =1; i <=c; i++) {
                    v.add(rs.getString("issueNo"));
                    v.add(rs.getString("issueDate"));
                    v.add(rs.getString("bookNo"));
                    v.add(rs.getString("bookName"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("admNo"));
                    v.add(rs.getString("studentName"));
                    v.add(rs.getString("returnDate"));
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
        lblIssueDate.setText(date);
    }
    
    public void AutoID(){
        try {
            Statement s=con.createStatement();
            rs=s.executeQuery("select MAX(issueNo) from issuebooksstudents");
            rs.next();
            rs.getString("MAX(issueNo)");
            if(rs.getString("MAX(issueNo)")==null){
                lblIssueNo.setText("IS001");
            }else{
                long id=Long.parseLong(rs.getString("MAX(issueNo)").substring(2,rs.getString("MAX(issueNo)").length()));
                id++;
                lblIssueNo.setText("IS"+String.format("%03d", id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddBooks.class.getName()).log(Level.SEVERE, null, ex);
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
        editBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        lblBookName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        txtSearch1 = new javax.swing.JTextField();
        btnSearch1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        txtSearch2 = new javax.swing.JTextField();
        search3 = new javax.swing.JToggleButton();
        issueBtn = new javax.swing.JToggleButton();
        lblIssueDate = new javax.swing.JLabel();
        lblBookNo = new javax.swing.JLabel();
        lblStudentName = new javax.swing.JLabel();
        lblAdmNo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblBookPrice = new javax.swing.JLabel();
        jDate = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        lblIssueNo = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ISSUE BOOKS TO STUDENTS");
        setMaximizedBounds(new java.awt.Rectangle(1300, 700, 700, 700));
        setMaximumSize(new java.awt.Dimension(1300, 700));
        setMinimumSize(new java.awt.Dimension(1300, 700));
        setPreferredSize(new java.awt.Dimension(1300, 700));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 0, 102));
        jLabel9.setText("ISSUE BOOKS TO STUDENTS");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 520, -1));

        jPanel1.setBackground(new java.awt.Color(0, 51, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DATE OF ISSUE:");
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
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 130, 40));

        clearBtn.setBackground(new java.awt.Color(0, 102, 0));
        clearBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(255, 255, 255));
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 130, 40));

        editBtn.setBackground(new java.awt.Color(102, 0, 102));
        editBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        editBtn.setForeground(new java.awt.Color(255, 255, 255));
        editBtn.setText("EDIT");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        jPanel1.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 440, 140, 40));

        exitBtn.setBackground(new java.awt.Color(255, 0, 0));
        exitBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(255, 255, 255));
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 440, 100, 40));

        lblBookName.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblBookName.setForeground(new java.awt.Color(255, 255, 255));
        lblBookName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblBookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 210, 30));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BOOK ID", "BOOK NO", "BOOK NAME", "AUTHOR", "DESCRIPTION", "BOOK PRICE", "STATUS"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, 710, 100));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, 210, 30));

        btnSearch.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        btnSearch.setText("SEARCH WITH BOOK NUMBER");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 70, 290, 30));

        deleteBtn.setBackground(new java.awt.Color(255, 0, 0));
        deleteBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 390, 100, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("DATE TO RETURN:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 190, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("STUDENT NAME:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 170, -1));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ADMN MO", "STUDENT NAME", "PROFILE PHOTO"
            }
        ));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, 710, 110));

        txtSearch1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearch1KeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, 210, 30));

        btnSearch1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        btnSearch1.setText("SEARCH WITH ADMISSION NUMBER");
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 220, 330, 30));

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISSUE NO", "ISSUE DATE", "BOOK NO", "BOOK NAME", "PRICE", "ADM NO", "STUDENT NAME", "RETURN DATE"
            }
        ));
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(table2);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 420, 740, 150));

        txtSearch2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearch2KeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 380, 230, 30));

        search3.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        search3.setText("SEARCH WITH ADMISSION NUMBER");
        search3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search3ActionPerformed(evt);
            }
        });
        jPanel1.add(search3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 380, 330, 30));

        issueBtn.setBackground(new java.awt.Color(0, 51, 153));
        issueBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        issueBtn.setForeground(new java.awt.Color(255, 255, 255));
        issueBtn.setText("ISSUE BOOKS");
        issueBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issueBtnActionPerformed(evt);
            }
        });
        jPanel1.add(issueBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, 140, 40));

        lblIssueDate.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblIssueDate.setForeground(new java.awt.Color(255, 255, 255));
        lblIssueDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblIssueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 210, 30));

        lblBookNo.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblBookNo.setForeground(new java.awt.Color(255, 255, 255));
        lblBookNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblBookNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 210, 30));

        lblStudentName.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblStudentName.setForeground(new java.awt.Color(255, 255, 255));
        lblStudentName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblStudentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 280, 210, 30));

        lblAdmNo.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblAdmNo.setForeground(new java.awt.Color(255, 255, 255));
        lblAdmNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblAdmNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 210, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("BOOK PRICE:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 170, -1));

        lblBookPrice.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblBookPrice.setForeground(new java.awt.Color(255, 255, 255));
        lblBookPrice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblBookPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, 210, 30));
        jPanel1.add(jDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 320, 210, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ISSUE NUMBER:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 170, -1));

        lblIssueNo.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblIssueNo.setForeground(new java.awt.Color(255, 255, 255));
        lblIssueNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblIssueNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 210, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 1220, 610));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 6, 1300, 690));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        this.setVisible(false);
        new Home(lblUsername.getText(),imag).setVisible(true);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        lblBookNo.setText("");
        lblBookName.setText("");
        lblBookPrice.setText("");
        lblAdmNo.setText("");
        lblStudentName.setText("");
        lblBookName.setText("");
        jDate.setCalendar(null);
        txtSearch.requestFocus();
        AutoID();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        String issueNo=lblIssueNo.getText();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String date=format.format(jDate.getDate());

        if(date.trim().equals("")){
            JOptionPane.showMessageDialog(this,"Select date book will be returned, null dates cannot be submitted","Submission Error!!!",JOptionPane.CANCEL_OPTION);
            jDate.requestFocus();
        }else{
            try {
                pst=con.prepareStatement("update issuebooksstudents set returnDate=? where issueNo=?");
                pst.setString(1,date);
                pst.setString(2,issueNo);              
                int count=pst.executeUpdate();
                if(count>0){
                    JOptionPane.showMessageDialog(this, "Book updated successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
                    issued_students_table();
                }else{
                    JOptionPane.showMessageDialog(this, "An error occured while tring to send details into database, please try again","Error Message",JOptionPane.CANCEL_OPTION);
                }
                lblBookNo.setText("");
                lblBookName.setText("");
                lblBookPrice.setText("");
                lblAdmNo.setText("");
                lblStudentName.setText("");
                lblBookName.setText("");
                jDate.setCalendar(null);
                txtSearch.requestFocus();
                current_date();
                AutoID();
                deleteBtn.setEnabled(false);
                editBtn.setEnabled(false);
                issueBtn.setEnabled(true);
            } catch (SQLException ex) {
                Logger.getLogger(IssueBooksStudents.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Do yo want to exit system","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        DefaultTableModel model=(DefaultTableModel)table.getModel();
        int selectedIndex=table.getSelectedRow();

        lblBookNo.setText(model.getValueAt(selectedIndex, 1).toString());
        lblBookName.setText(model.getValueAt(selectedIndex, 2).toString());
        lblBookPrice.setText(model.getValueAt(selectedIndex, 5).toString());
    }//GEN-LAST:event_tableMouseClicked

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String bookNO=txtSearch.getText();
            try {
                pst=con.prepareStatement("select * from books where bookNO LIKE CONCAT('%',?,'%')");
                pst.setString(1,bookNO);
                rs=pst.executeQuery();
                ResultSetMetaData rsm=rs.getMetaData();
                int c=rsm.getColumnCount();

                DefaultTableModel model=(DefaultTableModel)table.getModel();
                model.setRowCount(0);
                while(rs.next()){
                    Vector v=new Vector();
                    for (int i =1; i <=c; i++) {
                        v.add(rs.getString("bookId"));
                        v.add(rs.getString("bookNo"));
                        v.add(rs.getString("bookName"));
                        v.add(rs.getString("author"));
                        v.add(rs.getString("description"));
                        v.add(rs.getInt("price"));
                        v.add(rs.getString("status"));
                    }
                    model.addRow(v);
                }
            } catch (SQLException ex) {
                Logger.getLogger(IssueBooksStudents.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String bookNo=txtSearch.getText();
        try {
            pst=con.prepareStatement("select * from books where bookNo LIKE CONCAT('%',?,'%')");
            pst.setString(1,bookNo);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();

            DefaultTableModel model=(DefaultTableModel)table.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i =1; i <=c; i++) {
                    v.add(rs.getString("bookId"));
                    v.add(rs.getString("bookNo"));
                    v.add(rs.getString("bookName"));
                    v.add(rs.getString("author"));
                    v.add(rs.getString("description"));
                    v.add(rs.getInt("price"));
                    v.add(rs.getString("status"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IssueBooksStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        String issueNo=lblIssueNo.getText();
        
        try {
            pst=con.prepareStatement("delete from issuebooksstudents where issueNo=?");
            pst.setString(1,issueNo);
            int count=pst.executeUpdate();
            if(count>0){
                JOptionPane.showMessageDialog(this, "Issued Book deleted successfully from database","Success Message",JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, "An error occured while tring to send details into database, please try again","Error Message",JOptionPane.CANCEL_OPTION);
            }
            lblBookNo.setText("");
            lblBookName.setText("");
            lblBookPrice.setText("");
            lblAdmNo.setText("");
            lblStudentName.setText("");
            lblBookName.setText("");
            jDate.setCalendar(null);
            txtSearch.requestFocus();
            issued_students_table();
            current_date();
            AutoID();
            deleteBtn.setEnabled(false);
            editBtn.setEnabled(false);
            issueBtn.setEnabled(true);
        } catch (SQLException ex) {
            Logger.getLogger(IssueBooksStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        DefaultTableModel model=(DefaultTableModel)table1.getModel();
        int selectedIndex=table1.getSelectedRow();

        lblAdmNo.setText(model.getValueAt(selectedIndex, 1).toString());
        lblStudentName.setText(model.getValueAt(selectedIndex, 2).toString());

    }//GEN-LAST:event_table1MouseClicked

    private void txtSearch1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String admNo=txtSearch1.getText();
            try {
                pst=con.prepareStatement("select * from students where admNo LIKE CONCAT('%',?,'%')");
                pst.setString(1,admNo);
                rs=pst.executeQuery();
                ResultSetMetaData rsm=rs.getMetaData();
                int c=rsm.getColumnCount();

                DefaultTableModel model=(DefaultTableModel)table1.getModel();
                model.setRowCount(0);
                while(rs.next()){
                    Vector v=new Vector();
                    for (int i =1; i <=c; i++) {
                        v.add(rs.getString("studentId"));
                        v.add(rs.getString("admNo"));
                        v.add(rs.getString("name"));
                        v.add(rs.getBlob("profile_pic"));
                    }
                    model.addRow(v);
                }
            } catch (SQLException ex) {
                Logger.getLogger(EditStudents.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtSearch1KeyPressed

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
        String admNo=txtSearch1.getText();
        try {
            pst=con.prepareStatement("select * from students where admNo LIKE CONCAT('%',?,'%')");
            pst.setString(1,admNo);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();

            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i =1; i <=c; i++) {
                    v.add(rs.getString("studentId"));
                    v.add(rs.getString("admNo"));
                    v.add(rs.getString("name"));
                    v.add(rs.getBlob("profile_pic"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearch1ActionPerformed

    private void search3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search3ActionPerformed
        String admNo=txtSearch2.getText();
            try {
                pst=con.prepareStatement("select * from issuebooksstudents where admNo LIKE CONCAT('%',?,'%')");
                pst.setString(1,admNo);
                rs=pst.executeQuery();
                ResultSetMetaData rsm=rs.getMetaData();
                int c=rsm.getColumnCount();

                DefaultTableModel model=(DefaultTableModel)table2.getModel();
                model.setRowCount(0);
                while(rs.next()){
                    Vector v=new Vector();
                    for (int i =1; i <=c; i++) {
                        v.add(rs.getString("issueNo"));
                        v.add(rs.getString("issueDate"));
                        v.add(rs.getString("bookNo"));
                        v.add(rs.getString("bookName"));
                        v.add(rs.getInt("price"));
                        v.add(rs.getString("admNo"));
                        v.add(rs.getString("studentName"));
                        v.add(rs.getString("returnDate"));
                    }
                    model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_search3ActionPerformed

    private void txtSearch2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String admNo=txtSearch2.getText();
            try {
                pst=con.prepareStatement("select * from issuebooksstudents where admNo LIKE CONCAT('%',?,'%')");
                pst.setString(1,admNo);
                rs=pst.executeQuery();
                ResultSetMetaData rsm=rs.getMetaData();
                int c=rsm.getColumnCount();

                DefaultTableModel model=(DefaultTableModel)table2.getModel();
                model.setRowCount(0);
                while(rs.next()){
                    Vector v=new Vector();
                    for (int i =1; i <=c; i++) {
                        v.add(rs.getString("issueNo"));
                        v.add(rs.getString("issueDate"));
                        v.add(rs.getString("bookNo"));
                        v.add(rs.getString("bookName"));
                        v.add(rs.getInt("price"));
                        v.add(rs.getString("admNo"));
                        v.add(rs.getString("studentName"));
                        v.add(rs.getString("returnDate"));
                    }
                    model.addRow(v);
                }
            } catch (SQLException ex) {
                Logger.getLogger(EditStudents.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtSearch2KeyPressed

    private void issueBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issueBtnActionPerformed
        String issueNo=lblIssueNo.getText();
        String issueDate=lblIssueDate.getText();
        String bookNo=lblBookNo.getText();
        String bookName=lblBookName.getText();
        int bookPrice=Integer.parseInt(lblBookPrice.getText());
        String admissionNo=lblAdmNo.getText();
        String studentName=lblStudentName.getText();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String date=format.format(jDate.getDate());
        
        try {
            pst=con.prepareStatement("insert into issuebooksstudents(issueNo,issueDate,bookNo,bookName,price,admNo,studentName,returnDate)values(?,?,?,?,?,?,?,?)");
            pst.setString(1,issueNo);
            pst.setString(2,issueDate);
            pst.setString(3,bookNo);
            pst.setString(4,bookName);
            pst.setInt(5,bookPrice);
            pst.setString(6,admissionNo);
            pst.setString(7,studentName);
            pst.setString(8,date);
            pst.executeUpdate();
            
            pst=con.prepareStatement("update books set status='ISSUED OUT' where bookNo=?");
            pst.setString(1,bookNo);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Book Issued out Successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
            lblBookNo.setText("");
            lblBookName.setText("");
            lblBookPrice.setText("");
            lblAdmNo.setText("");
            lblStudentName.setText("");
            lblBookName.setText("");
            jDate.setCalendar(null);
            txtSearch.requestFocus();
            issued_students_table();
            current_date();
            AutoID();
            books_table();
        } catch (SQLException ex) {
            Logger.getLogger(IssueBooksStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_issueBtnActionPerformed

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        deleteBtn.setEnabled(true);
        editBtn.setEnabled(true);
        issueBtn.setEnabled(false);

        DefaultTableModel model=(DefaultTableModel)table2.getModel();
        int selectedIndex=table2.getSelectedRow();

        lblIssueNo.setText(model.getValueAt(selectedIndex, 0).toString());
        lblIssueDate.setText(model.getValueAt(selectedIndex, 1).toString());
        lblBookNo.setText(model.getValueAt(selectedIndex, 2).toString());
        lblBookName.setText(model.getValueAt(selectedIndex, 3).toString());
        lblBookPrice.setText(model.getValueAt(selectedIndex, 4).toString());
        lblAdmNo.setText(model.getValueAt(selectedIndex, 5).toString());
        lblStudentName.setText(model.getValueAt(selectedIndex, 6).toString());
        String date=(String) model.getValueAt(selectedIndex, 7);
        try{
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d=format.parse(date);
            jDate.setDate(d);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_table2MouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        deleteBtn.setEnabled(false);
        editBtn.setEnabled(false);
    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(IssueBooksStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBooksStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBooksStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBooksStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new IssueBooksStudents().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JToggleButton issueBtn;
    private com.toedter.calendar.JDateChooser jDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAdmNo;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBookName;
    private javax.swing.JLabel lblBookNo;
    private javax.swing.JLabel lblBookPrice;
    private javax.swing.JLabel lblIssueDate;
    private javax.swing.JLabel lblIssueNo;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblStudentName;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JToggleButton search3;
    private javax.swing.JTable table;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearch1;
    private javax.swing.JTextField txtSearch2;
    // End of variables declaration//GEN-END:variables
}
