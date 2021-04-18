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
public class IssueBooksTeachers extends javax.swing.JFrame {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    String path1;
    /**
     * Creates new form CreateAccount
     */
    public IssueBooksTeachers() {
        initComponents();
        Connection();
    }

    String username;
    BufferedImage imag;
    public IssueBooksTeachers(String username,BufferedImage imag) {
        initComponents();
        Connection();
        this.username=username;
        this.imag=imag;
        lblUsername.setText(username);
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(lblProfile.getWidth(), lblProfile.getHeight(), Image.SCALE_SMOOTH)));
        books_table();
        teachers_table();
        current_date();
        issued_teachers_table();
        AutoID();
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("../Images/caleb-woods-fulXJYIvRi8-unsplash.jpg")).getImage().getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), Image.SCALE_SMOOTH)));
    }
    public void Connection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/library","root","");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IssueBooksTeachers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(IssueBooksTeachers.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(IssueBooksTeachers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void teachers_table(){
        try {
            pst=con.prepareStatement("select * from teachers");
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();
            
            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i =1; i <=c; i++) {
                    v.add(rs.getString("teacherId"));
                    v.add(rs.getString("nationalId"));
                    v.add(rs.getString("fullName"));
                    v.add(rs.getBlob("profile_pic"));
                    v.add(rs.getString("phone"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditTeachers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void issued_teachers_table(){
        try {
            pst=con.prepareStatement("select * from issuedTeachersBooks");
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
                    v.add(rs.getString("nationalId"));
                    v.add(rs.getString("teachername"));
                    v.add(rs.getString("phone"));
                    v.add(rs.getString("returndate"));
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
            rs=s.executeQuery("select MAX(issueNo) from issuedteachersbooks");
            rs.next();
            rs.getString("MAX(issueNo)");
            if(rs.getString("MAX(issueNo)")==null){
                lblIssueNo.setText("IT001");
            }else{
                long id=Long.parseLong(rs.getString("MAX(issueNo)").substring(2,rs.getString("MAX(issueNo)").length()));
                id++;
                lblIssueNo.setText("IT"+String.format("%03d", id));
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
        jScrollPane3 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        txtSearch2 = new javax.swing.JTextField();
        search3 = new javax.swing.JToggleButton();
        issueBtn = new javax.swing.JToggleButton();
        lblIssueDate = new javax.swing.JLabel();
        lblBookNo = new javax.swing.JLabel();
        lblTeachername = new javax.swing.JLabel();
        lblNationalId = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblBookPrice = new javax.swing.JLabel();
        jDate = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        lblIssueNo = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        txtSearch3 = new javax.swing.JTextField();
        btnSearch2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        lblPhone = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ISSUE BOOKS TO TEACHERS");
        setMaximizedBounds(new java.awt.Rectangle(1300, 700, 700, 700));
        setMinimumSize(new java.awt.Dimension(1300, 700));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 0, 102));
        jLabel9.setText("ISSUE BOOKS TO TEACHERS");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 520, -1));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
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
        jLabel5.setText("NATIONAL ID:");
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
        jPanel1.add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 130, 40));

        clearBtn.setBackground(new java.awt.Color(0, 102, 0));
        clearBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(255, 255, 255));
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 130, 40));

        editBtn.setBackground(new java.awt.Color(102, 0, 102));
        editBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        editBtn.setForeground(new java.awt.Color(255, 255, 255));
        editBtn.setText("EDIT");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        jPanel1.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 470, 140, 40));

        exitBtn.setBackground(new java.awt.Color(255, 0, 0));
        exitBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(255, 255, 255));
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 470, 100, 40));

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
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 70, 300, 30));

        deleteBtn.setBackground(new java.awt.Color(255, 0, 0));
        deleteBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 420, 100, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("DATE TO RETURN:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 190, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("TEACHER'S NAME:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 190, -1));

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISSUE NO", "ISSUE DATE", "BOOK NO", "BOOK NAME", "PRICE", "NATIONAL ID", "TR NAME", "PHONE", "RETURN DATE"
            }
        ));
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        table2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                table2KeyPressed(evt);
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
        search3.setText("SEARCH WITH NATIONAL ID");
        search3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search3ActionPerformed(evt);
            }
        });
        jPanel1.add(search3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 380, 300, 30));

        issueBtn.setBackground(new java.awt.Color(0, 51, 153));
        issueBtn.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        issueBtn.setForeground(new java.awt.Color(255, 255, 255));
        issueBtn.setText("ISSUE BOOKS");
        issueBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issueBtnActionPerformed(evt);
            }
        });
        jPanel1.add(issueBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 420, 140, 40));

        lblIssueDate.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblIssueDate.setForeground(new java.awt.Color(255, 255, 255));
        lblIssueDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblIssueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 210, 30));

        lblBookNo.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblBookNo.setForeground(new java.awt.Color(255, 255, 255));
        lblBookNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblBookNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 210, 30));

        lblTeachername.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblTeachername.setForeground(new java.awt.Color(255, 255, 255));
        lblTeachername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblTeachername, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 280, 210, 30));

        lblNationalId.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblNationalId.setForeground(new java.awt.Color(255, 255, 255));
        lblNationalId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblNationalId, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 210, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("BOOK PRICE:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 170, -1));

        lblBookPrice.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblBookPrice.setForeground(new java.awt.Color(255, 255, 255));
        lblBookPrice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblBookPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, 210, 30));
        jPanel1.add(jDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 360, 210, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ISSUE NUMBER:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 170, -1));

        lblIssueNo.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblIssueNo.setForeground(new java.awt.Color(255, 255, 255));
        lblIssueNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblIssueNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 210, 30));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NATIONAL ID", "FULL NAME", "PROFILE PHOTO", "PHONE NUMBER"
            }
        ));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(table1);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, 710, 110));

        txtSearch3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearch3KeyPressed(evt);
            }
        });
        jPanel1.add(txtSearch3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 220, 210, 30));

        btnSearch2.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        btnSearch2.setText("SEARCH WITH  NATIONAL ID");
        btnSearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch2ActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 220, 270, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("PHONE NUMBER:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 190, -1));

        lblPhone.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblPhone.setForeground(new java.awt.Color(255, 255, 255));
        lblPhone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(lblPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 320, 210, 30));

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
        lblNationalId.setText("");
        lblTeachername.setText("");
        lblBookName.setText("");
        lblPhone.setText("");
        jDate.setCalendar(null);
        txtSearch.requestFocus();
        AutoID();
        deleteBtn.setEnabled(false);
        editBtn.setEnabled(false);
        issueBtn.setEnabled(true);
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
                pst=con.prepareStatement("update issuedteachersbooks set returnDate=? where issueNo=?");
                pst.setString(1,date);
                pst.setString(2,issueNo);              
                int count=pst.executeUpdate();
                if(count>0){
                    JOptionPane.showMessageDialog(this, "Book updated successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
                    issued_teachers_table();
                }else{
                    JOptionPane.showMessageDialog(this, "An error occured while tring to send details into database, please try again","Error Message",JOptionPane.CANCEL_OPTION);
                }
                lblBookNo.setText("");
                lblBookName.setText("");
                lblBookPrice.setText("");
                lblNationalId.setText("");
                lblTeachername.setText("");
                lblBookName.setText("");
                lblPhone.setText("");
                jDate.setCalendar(null);
                txtSearch.requestFocus();
                current_date();
                AutoID();
                deleteBtn.setEnabled(false);
                editBtn.setEnabled(false);
                issueBtn.setEnabled(true);
            } catch (SQLException ex) {
                Logger.getLogger(IssueBooksTeachers.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(IssueBooksTeachers.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(IssueBooksTeachers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        String issueNo=lblIssueNo.getText();
        
        try {
            pst=con.prepareStatement("delete from issuedteachersbooks where issueNo=?");
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
            lblNationalId.setText("");
            lblTeachername.setText("");
            lblBookName.setText("");
            lblPhone.setText("");
            jDate.setCalendar(null);
            txtSearch.requestFocus();
            issued_teachers_table();
            current_date();
            AutoID();
            deleteBtn.setEnabled(false);
            editBtn.setEnabled(false);
            issueBtn.setEnabled(true);
        } catch (SQLException ex) {
            Logger.getLogger(IssueBooksTeachers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void search3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search3ActionPerformed
        String admNo=txtSearch2.getText();
            try {
                pst=con.prepareStatement("select * from issuedteachersbooks where nationalId LIKE CONCAT('%',?,'%')");
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
                        v.add(rs.getString("nationalId"));
                        v.add(rs.getString("teachername"));
                        v.add(rs.getString("phone"));
                        v.add(rs.getString("returndate"));
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
                pst=con.prepareStatement("select * from issuedteachersbooks where nationalId LIKE CONCAT('%',?,'%')");
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
                        v.add(rs.getString("nationalId"));
                        v.add(rs.getString("teachername"));
                        v.add(rs.getString("phone"));
                        v.add(rs.getString("returndate"));
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
        String admissionNo=lblNationalId.getText();
        String teacherName=lblTeachername.getText();
        String phone=lblPhone.getText();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String date=format.format(jDate.getDate());
        
        try {
            pst=con.prepareStatement("insert into issuedteachersbooks(issueNo,issueDate,bookNo,bookName,price,nationalId,teachername,phone,returnDate)values(?,?,?,?,?,?,?,?,?)");
            pst.setString(1,issueNo);
            pst.setString(2,issueDate);
            pst.setString(3,bookNo);
            pst.setString(4,bookName);
            pst.setInt(5,bookPrice);
            pst.setString(6,admissionNo);
            pst.setString(7,teacherName);
            pst.setString(8,phone);
            pst.setString(9,date);
            pst.executeUpdate();
            
            pst=con.prepareStatement("update books set status='ISSUED OUT' where bookNo=?");
            pst.setString(1,bookNo);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Book Issued out Successfully","Success Message",JOptionPane.INFORMATION_MESSAGE);
            lblBookNo.setText("");
            lblBookName.setText("");
            lblBookPrice.setText("");
            lblNationalId.setText("");
            lblTeachername.setText("");
            lblBookName.setText("");
            lblPhone.setText("");
            jDate.setCalendar(null);
            txtSearch.requestFocus();
            issued_teachers_table();
            current_date();
            AutoID();
            books_table();
        } catch (SQLException ex) {
            Logger.getLogger(IssueBooksTeachers.class.getName()).log(Level.SEVERE, null, ex);
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
        lblNationalId.setText(model.getValueAt(selectedIndex, 5).toString());
        lblTeachername.setText(model.getValueAt(selectedIndex, 6).toString());
        lblPhone.setText(model.getValueAt(selectedIndex, 7).toString());
        String date=(String) model.getValueAt(selectedIndex, 8);
        try{
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d=format.parse(date);
            jDate.setDate(d);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_table2MouseClicked

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        DefaultTableModel model=(DefaultTableModel)table1.getModel();
        int selectedIndex=table1.getSelectedRow();

        lblNationalId.setText(model.getValueAt(selectedIndex, 1).toString());
        lblTeachername.setText(model.getValueAt(selectedIndex, 2).toString());
        lblPhone.setText(model.getValueAt(selectedIndex, 4).toString());
    }//GEN-LAST:event_table1MouseClicked

    private void txtSearch3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch3KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String admNo=txtSearch3.getText();
            try {
                pst=con.prepareStatement("select * from teachers where nationalId LIKE CONCAT('%',?,'%')");
                pst.setString(1,admNo);
                rs=pst.executeQuery();
                ResultSetMetaData rsm=rs.getMetaData();
                int c=rsm.getColumnCount();

                DefaultTableModel model=(DefaultTableModel)table1.getModel();
                model.setRowCount(0);
                while(rs.next()){
                    Vector v=new Vector();
                    for (int i =1; i <=c; i++) {
                        v.add(rs.getString("teacherId"));
                        v.add(rs.getString("nationalId"));
                        v.add(rs.getString("fullName"));
                        v.add(rs.getBlob("profile_pic"));
                        v.add(rs.getString("phone"));
                    }
                    model.addRow(v);
                }
            } catch (SQLException ex) {
                Logger.getLogger(EditTeachers.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtSearch3KeyPressed

    private void btnSearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch2ActionPerformed
        String admNo=txtSearch3.getText();
        try {
            pst=con.prepareStatement("select * from teachers where nationalId LIKE CONCAT('%',?,'%')");
            pst.setString(1,admNo);
            rs=pst.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            int c=rsm.getColumnCount();

            DefaultTableModel model=(DefaultTableModel)table1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                Vector v=new Vector();
                for (int i =1; i <=c; i++) {
                    v.add(rs.getString("teacherId"));
                    v.add(rs.getString("nationalId"));
                    v.add(rs.getString("fullName"));
                    v.add(rs.getBlob("profile_pic"));
                    v.add(rs.getString("phone"));
                }
                model.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditTeachers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearch2ActionPerformed

    private void table2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_table2KeyPressed

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
            java.util.logging.Logger.getLogger(IssueBooksTeachers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBooksTeachers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBooksTeachers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBooksTeachers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueBooksTeachers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch2;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JToggleButton issueBtn;
    private com.toedter.calendar.JDateChooser jDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBookName;
    private javax.swing.JLabel lblBookNo;
    private javax.swing.JLabel lblBookPrice;
    private javax.swing.JLabel lblIssueDate;
    private javax.swing.JLabel lblIssueNo;
    private javax.swing.JLabel lblNationalId;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblTeachername;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JToggleButton search3;
    private javax.swing.JTable table;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearch2;
    private javax.swing.JTextField txtSearch3;
    // End of variables declaration//GEN-END:variables
}
