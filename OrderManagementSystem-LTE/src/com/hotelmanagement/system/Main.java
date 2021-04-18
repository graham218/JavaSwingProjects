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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bill
 */
public class Main extends javax.swing.JFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
    }
    
    String username;
    BufferedImage imag;
    String userType;
    public Main(String username, BufferedImage imag, String userType) {
        initComponents();
        lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("social-3064515_1920.jpg")).getImage().getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), Image.SCALE_SMOOTH)));
        this.username=username;
        lblUsername.setText(username);
        this.imag=imag;
        lblProfile.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(150, 170, Image.SCALE_SMOOTH)));
        this.userType=userType;
        lblUserType.setText(userType);
        Connect();
        user_filtration();
        
        //STORE PANEL
        lblUsernameStore.setText(username);
        lblProfileStore.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(150, 170, Image.SCALE_SMOOTH)));
        userTypeStore.setText(userType);
        
        //FINANCE PANEL
        lblUsernameFinance.setText(username);
        lblProfileFinance.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(150, 170, Image.SCALE_SMOOTH)));
        lblUserTypeFinance.setText(userType);
        //USER ACCOUNTS PANEL
        lblUsernameAccounts.setText(username);
        lblProfileAccounts.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(150, 170, Image.SCALE_SMOOTH)));
        lblUserTypeAccounts.setText(userType);
        //KITCHEN PANEL
        lblUsernameKitchen.setText(username);
        lblProfileKitchen.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(150, 170, Image.SCALE_SMOOTH)));
        lblUserTypeKitchen.setText(userType);
        //ABOUT PANEL
        lblUsernameAbout.setText(username);
        lblProfileAbout.setIcon(new ImageIcon(new javax.swing.ImageIcon(imag).getImage().getScaledInstance(150, 170, Image.SCALE_SMOOTH)));
        lblUserTypeAbout.setText(userType);
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


    public void user_filtration(){
        if(userType.trim().equals("ADMIN")){
            //finance
            addSuppliersBtn.setVisible(true);
            tenderBtn.setVisible(true);
            supplierPaymentBtn.setVisible(true);
            viewPaymentsBtn.setVisible(true);
            messageStoreBtn.setVisible(true);
            exitBtn1.setVisible(true);
            
            //hotel
            addClientBtn.setVisible(true);
            roomAllocation.setVisible(true);
            roomBookingBtn.setVisible(true);
            hotelRooms.setVisible(true);
            roomIncomeBtn.setVisible(true);
            meals.setVisible(true);
            meals1.setVisible(true);
            salesViewBtn.setVisible(true);
            exitBtn.setVisible(true);
            
            //store
            stockInStoreBtn.setVisible(true);
            checkDepartmentalOrdersBtn1.setVisible(true);
            issueStoreItemsBtn.setVisible(true);
            viewReceivedOrderedItemsBtn.setVisible(true);
            messageBtn.setVisible(true);
            exitSystemBtn.setVisible(true);
            
            //user acounts
            newUserBtn.setVisible(true);
            editUsersBtn.setVisible(true);
            exitBtn2.setVisible(true);
            
            //departmental orders
            orderStoreItemsBtn.setVisible(true);
            receivedOrderedItemsBtn.setVisible(true);
            exitBtn3.setVisible(true);

            //departments
            addDepartmentsBtn.setVisible(true);
            exitBtn4.setVisible(true);
        }else if(userType.trim().equals("SECURITY")){
            //finance
            addSuppliersBtn.setVisible(false);
            tenderBtn.setVisible(false);
            supplierPaymentBtn.setVisible(false);
            viewPaymentsBtn.setVisible(false);
            messageStoreBtn.setVisible(false);
            exitBtn1.setVisible(true);
            
            //hotel
            addClientBtn.setVisible(false);
            roomAllocation.setVisible(false);
            roomBookingBtn.setVisible(false);
            hotelRooms.setVisible(false);
            roomIncomeBtn.setVisible(false);
            meals.setVisible(false);
            meals1.setVisible(false);
            salesViewBtn.setVisible(false);
            exitBtn.setVisible(true);
            
            //store
            stockInStoreBtn.setVisible(false);
            checkDepartmentalOrdersBtn1.setVisible(false);
            issueStoreItemsBtn.setVisible(false);
            viewReceivedOrderedItemsBtn.setVisible(false);
            messageBtn.setVisible(false);
            exitSystemBtn.setVisible(true);
            
            //user acounts
            newUserBtn.setVisible(false);
            editUsersBtn.setVisible(false);
            exitBtn2.setVisible(true);
            
            //departmental orders
            orderStoreItemsBtn.setVisible(true);
            receivedOrderedItemsBtn.setVisible(true);
            exitBtn3.setVisible(true);

            //departments
            addDepartmentsBtn.setVisible(true);
            exitBtn4.setVisible(true);
        }else if(userType.trim().equals("STORE KEEPER")){
            //finance
            addSuppliersBtn.setVisible(false);
            tenderBtn.setVisible(false);
            supplierPaymentBtn.setVisible(false);
            viewPaymentsBtn.setVisible(false);
            messageStoreBtn.setVisible(false);
            exitBtn1.setVisible(true);
            
            //hotel
            addClientBtn.setVisible(false);
            roomAllocation.setVisible(false);
            roomBookingBtn.setVisible(false);
            hotelRooms.setVisible(false);
            roomIncomeBtn.setVisible(false);
            meals.setVisible(false);
            meals1.setVisible(false);
            salesViewBtn.setVisible(false);
            exitBtn.setVisible(true);
            
            //store
            stockInStoreBtn.setVisible(true);
            checkDepartmentalOrdersBtn1.setVisible(true);
            issueStoreItemsBtn.setVisible(true);
            viewReceivedOrderedItemsBtn.setVisible(true);
            messageBtn.setVisible(true);
            exitSystemBtn.setVisible(true);
            
            //user acounts
            newUserBtn.setVisible(false);
            editUsersBtn.setVisible(false);
            exitBtn2.setVisible(true);
            
            //departmental orders
            orderStoreItemsBtn.setVisible(false);
            receivedOrderedItemsBtn.setVisible(false);
            exitBtn3.setVisible(true);

            //departments
            addDepartmentsBtn.setVisible(false);
            exitBtn4.setVisible(true);
        }else if(userType.trim().equals("HOTEL CASHIER")){
            //finance
            addSuppliersBtn.setVisible(false);
            tenderBtn.setVisible(false);
            supplierPaymentBtn.setVisible(false);
            viewPaymentsBtn.setVisible(false);
            messageStoreBtn.setVisible(false);
            exitBtn1.setVisible(true);
            
            //hotel
            addClientBtn.setVisible(true);
            roomAllocation.setVisible(true);
            roomBookingBtn.setVisible(true);
            hotelRooms.setVisible(true);
            roomIncomeBtn.setVisible(true);
            meals.setVisible(true);
            meals1.setVisible(true);
            salesViewBtn.setVisible(true);
            exitBtn.setVisible(true);
            
            //store
            stockInStoreBtn.setVisible(false);
            checkDepartmentalOrdersBtn1.setVisible(false);
            issueStoreItemsBtn.setVisible(false);
            viewReceivedOrderedItemsBtn.setVisible(false);
            messageBtn.setVisible(false);
            exitSystemBtn.setVisible(true);
            
            //user acounts
            newUserBtn.setVisible(false);
            editUsersBtn.setVisible(false);
            exitBtn2.setVisible(true);
            
            //departmental orders
            orderStoreItemsBtn.setVisible(true);
            receivedOrderedItemsBtn.setVisible(true);
            exitBtn3.setVisible(true);

            //departments
            addDepartmentsBtn.setVisible(true);
            exitBtn4.setVisible(true);
        }else if(userType.trim().equals("KITCHEN DPT")){
            //finance
            addSuppliersBtn.setVisible(false);
            tenderBtn.setVisible(false);
            supplierPaymentBtn.setVisible(false);
            viewPaymentsBtn.setVisible(false);
            messageStoreBtn.setVisible(false);
            exitBtn1.setVisible(true);
            
            //hotel
            addClientBtn.setVisible(false);
            roomAllocation.setVisible(false);
            roomBookingBtn.setVisible(false);
            hotelRooms.setVisible(false);
            roomIncomeBtn.setVisible(false);
            meals.setVisible(false);
            meals1.setVisible(false);
            salesViewBtn.setVisible(false);
            exitBtn.setVisible(true);
            
            //store
            stockInStoreBtn.setVisible(false);
            checkDepartmentalOrdersBtn1.setVisible(false);
            issueStoreItemsBtn.setVisible(false);
            viewReceivedOrderedItemsBtn.setVisible(false);
            messageBtn.setVisible(false);
            exitSystemBtn.setVisible(true);
            
            //user acounts
            newUserBtn.setVisible(false);
            editUsersBtn.setVisible(false);
            exitBtn2.setVisible(true);
            
            //departmental orders
            orderStoreItemsBtn.setVisible(true);
            receivedOrderedItemsBtn.setVisible(true);
            exitBtn3.setVisible(true);

            //departments
            addDepartmentsBtn.setVisible(true);
            exitBtn4.setVisible(true);
        }else if(userType.trim().equals("FINANCE DPT")){
            //finance
            addSuppliersBtn.setVisible(true);
            tenderBtn.setVisible(true);
            supplierPaymentBtn.setVisible(true);
            viewPaymentsBtn.setVisible(true);
            messageStoreBtn.setVisible(true);
            exitBtn1.setVisible(true);
            
            //hotel
            addClientBtn.setVisible(false);
            roomAllocation.setVisible(false);
            roomBookingBtn.setVisible(false);
            hotelRooms.setVisible(false);
            roomIncomeBtn.setVisible(false);
            meals.setVisible(false);
            meals1.setVisible(false);
            salesViewBtn.setVisible(false);
            exitBtn.setVisible(true);
            
            //store
            stockInStoreBtn.setVisible(false);
            checkDepartmentalOrdersBtn1.setVisible(false);
            issueStoreItemsBtn.setVisible(false);
            viewReceivedOrderedItemsBtn.setVisible(false);
            messageBtn.setVisible(false);
            exitSystemBtn.setVisible(true);
            
            //user acounts
            newUserBtn.setVisible(false);
            editUsersBtn.setVisible(false);
            exitBtn2.setVisible(true);
            
            //departmental orders
            orderStoreItemsBtn.setVisible(true);
            receivedOrderedItemsBtn.setVisible(true);
            exitBtn3.setVisible(true);

            //departments
            addDepartmentsBtn.setVisible(true);
            exitBtn4.setVisible(true);
        }else if(userType.trim().equals("OTHER")){
            //finance
            addSuppliersBtn.setVisible(false);
            tenderBtn.setVisible(false);
            supplierPaymentBtn.setVisible(false);
            viewPaymentsBtn.setVisible(false);
            messageStoreBtn.setVisible(false);
            exitBtn1.setVisible(true);
            
            //hotel
            addClientBtn.setVisible(false);
            roomAllocation.setVisible(false);
            roomBookingBtn.setVisible(false);
            hotelRooms.setVisible(false);
            roomIncomeBtn.setVisible(false);
            meals.setVisible(false);
            meals1.setVisible(false);
            salesViewBtn.setVisible(false);
            exitBtn.setVisible(true);
            
            //store
            stockInStoreBtn.setVisible(false);
            checkDepartmentalOrdersBtn1.setVisible(false);
            issueStoreItemsBtn.setVisible(false);
            viewReceivedOrderedItemsBtn.setVisible(false);
            messageBtn.setVisible(false);
            exitSystemBtn.setVisible(true);
            
            //user acounts
            newUserBtn.setVisible(false);
            editUsersBtn.setVisible(false);
            exitBtn2.setVisible(true);
            
            //departmental orders
            orderStoreItemsBtn.setVisible(true);
            receivedOrderedItemsBtn.setVisible(true);
            exitBtn3.setVisible(true);

            //departments
            addDepartmentsBtn.setVisible(true);
            exitBtn4.setVisible(true);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        hotelPanel = new javax.swing.JPanel();
        hotelRooms = new javax.swing.JButton();
        roomAllocation = new javax.swing.JButton();
        addClientBtn = new javax.swing.JButton();
        meals = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        meals1 = new javax.swing.JButton();
        lblProfile = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        salesViewBtn = new javax.swing.JButton();
        lblUserType = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JButton();
        roomBookingBtn = new javax.swing.JButton();
        roomIncomeBtn = new javax.swing.JButton();
        aboutCheckBox6 = new javax.swing.JCheckBox();
        storePanel = new javax.swing.JPanel();
        lblProfileStore = new javax.swing.JLabel();
        lblUsernameStore = new javax.swing.JLabel();
        stockInStoreBtn = new javax.swing.JButton();
        issueStoreItemsBtn = new javax.swing.JButton();
        userTypeStore = new javax.swing.JLabel();
        viewReceivedOrderedItemsBtn = new javax.swing.JButton();
        exitSystemBtn = new javax.swing.JButton();
        checkDepartmentalOrdersBtn1 = new javax.swing.JButton();
        logoutBtn1 = new javax.swing.JButton();
        messageBtn = new javax.swing.JButton();
        aboutCheckBox5 = new javax.swing.JCheckBox();
        financePanel = new javax.swing.JPanel();
        lblProfileFinance = new javax.swing.JLabel();
        lblUsernameFinance = new javax.swing.JLabel();
        supplierPaymentBtn = new javax.swing.JButton();
        addSuppliersBtn = new javax.swing.JButton();
        tenderBtn = new javax.swing.JButton();
        lblUserTypeFinance = new javax.swing.JLabel();
        viewPaymentsBtn = new javax.swing.JButton();
        exitBtn1 = new javax.swing.JButton();
        logoutBtn2 = new javax.swing.JButton();
        messageStoreBtn = new javax.swing.JButton();
        aboutCheckBox4 = new javax.swing.JCheckBox();
        usersAccountsPanel = new javax.swing.JPanel();
        lblProfileAccounts = new javax.swing.JLabel();
        lblUsernameAccounts = new javax.swing.JLabel();
        editUsersBtn = new javax.swing.JButton();
        newUserBtn = new javax.swing.JButton();
        lblUserTypeAccounts = new javax.swing.JLabel();
        exitBtn2 = new javax.swing.JButton();
        logoutBtn3 = new javax.swing.JButton();
        aboutCheckBox3 = new javax.swing.JCheckBox();
        departmentalPanel = new javax.swing.JPanel();
        lblProfileKitchen = new javax.swing.JLabel();
        lblUsernameKitchen = new javax.swing.JLabel();
        lblUserTypeKitchen = new javax.swing.JLabel();
        receivedOrderedItemsBtn = new javax.swing.JToggleButton();
        orderStoreItemsBtn = new javax.swing.JToggleButton();
        exitBtn3 = new javax.swing.JButton();
        logoutBtn4 = new javax.swing.JButton();
        aboutCheckBox2 = new javax.swing.JCheckBox();
        departmentsPanel = new javax.swing.JPanel();
        lblProfileAbout = new javax.swing.JLabel();
        lblUsernameAbout = new javax.swing.JLabel();
        lblUserTypeAbout = new javax.swing.JLabel();
        addDepartmentsBtn = new javax.swing.JToggleButton();
        exitBtn4 = new javax.swing.JButton();
        logoutBtn5 = new javax.swing.JButton();
        aboutCheckBox1 = new javax.swing.JCheckBox();
        aboutPanel = new javax.swing.JPanel();
        aboutCheckBox = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HOME PAGE");
        setMaximizedBounds(new java.awt.Rectangle(1280, 720, 720, 720));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N

        hotelPanel.setBackground(new java.awt.Color(142, 54, 163));
        hotelPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        hotelRooms.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        hotelRooms.setForeground(new java.awt.Color(13, 52, 248));
        hotelRooms.setText("HOTEL ROOMS");
        hotelRooms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hotelRoomsActionPerformed(evt);
            }
        });
        hotelPanel.add(hotelRooms, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 300, 50));

        roomAllocation.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        roomAllocation.setForeground(new java.awt.Color(13, 52, 248));
        roomAllocation.setText("VIEW ROOM ALLOCATION");
        roomAllocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomAllocationActionPerformed(evt);
            }
        });
        hotelPanel.add(roomAllocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 300, 50));

        addClientBtn.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        addClientBtn.setForeground(new java.awt.Color(13, 52, 248));
        addClientBtn.setText("MANAGE CLIENTS");
        addClientBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addClientBtnActionPerformed(evt);
            }
        });
        hotelPanel.add(addClientBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 300, 50));

        meals.setBackground(new java.awt.Color(18, 155, 58));
        meals.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        meals.setForeground(java.awt.Color.white);
        meals.setText("AVALABLE MEALS");
        meals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mealsActionPerformed(evt);
            }
        });
        hotelPanel.add(meals, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 280, 50));

        exitBtn.setBackground(new java.awt.Color(247, 63, 9));
        exitBtn.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        exitBtn.setForeground(java.awt.Color.white);
        exitBtn.setText("EXIT SYSTEM");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        hotelPanel.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, 280, 50));

        meals1.setBackground(new java.awt.Color(18, 155, 58));
        meals1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        meals1.setForeground(java.awt.Color.white);
        meals1.setText("SELL MEALS");
        meals1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meals1ActionPerformed(evt);
            }
        });
        hotelPanel.add(meals1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, 280, 50));

        lblProfile.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        hotelPanel.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 20, 150, 170));

        lblUsername.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblUsername.setForeground(java.awt.Color.white);
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("USERNAME");
        hotelPanel.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 240, 180, -1));

        salesViewBtn.setBackground(new java.awt.Color(18, 155, 58));
        salesViewBtn.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        salesViewBtn.setForeground(java.awt.Color.white);
        salesViewBtn.setText("VIEW MEAL SALES");
        salesViewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesViewBtnActionPerformed(evt);
            }
        });
        hotelPanel.add(salesViewBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, 280, 50));

        lblUserType.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblUserType.setForeground(java.awt.Color.white);
        lblUserType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserType.setText("USER TYPE");
        hotelPanel.add(lblUserType, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 210, 180, -1));

        logoutBtn.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        logoutBtn.setForeground(new java.awt.Color(10, 15, 246));
        logoutBtn.setText("LOG OUT");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });
        hotelPanel.add(logoutBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 260, 100, -1));

        roomBookingBtn.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        roomBookingBtn.setForeground(new java.awt.Color(13, 52, 248));
        roomBookingBtn.setText("VIEW ROOM BOOKINGS");
        roomBookingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomBookingBtnActionPerformed(evt);
            }
        });
        hotelPanel.add(roomBookingBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 300, 50));

        roomIncomeBtn.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        roomIncomeBtn.setForeground(new java.awt.Color(13, 52, 248));
        roomIncomeBtn.setText("VIEW ROOM INCOME");
        roomIncomeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomIncomeBtnActionPerformed(evt);
            }
        });
        hotelPanel.add(roomIncomeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 300, 50));

        aboutCheckBox6.setText("HALL OF FAME");
        aboutCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutCheckBox6ActionPerformed(evt);
            }
        });
        hotelPanel.add(aboutCheckBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(948, 414, 130, 30));

        jTabbedPane1.addTab("HOTEL", hotelPanel);

        storePanel.setBackground(new java.awt.Color(36, 149, 30));
        storePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblProfileStore.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        storePanel.add(lblProfileStore, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 20, 150, 170));

        lblUsernameStore.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblUsernameStore.setForeground(java.awt.Color.white);
        lblUsernameStore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsernameStore.setText("USERNAME");
        storePanel.add(lblUsernameStore, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 240, 180, -1));

        stockInStoreBtn.setBackground(new java.awt.Color(34, 44, 246));
        stockInStoreBtn.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        stockInStoreBtn.setForeground(java.awt.Color.white);
        stockInStoreBtn.setText("STOCK IN STORE");
        stockInStoreBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockInStoreBtnActionPerformed(evt);
            }
        });
        storePanel.add(stockInStoreBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 390, 50));

        issueStoreItemsBtn.setBackground(new java.awt.Color(34, 44, 246));
        issueStoreItemsBtn.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        issueStoreItemsBtn.setForeground(java.awt.Color.white);
        issueStoreItemsBtn.setText("ISSUE STORE ITEMS");
        issueStoreItemsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issueStoreItemsBtnActionPerformed(evt);
            }
        });
        storePanel.add(issueStoreItemsBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 390, 50));

        userTypeStore.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        userTypeStore.setForeground(java.awt.Color.white);
        userTypeStore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userTypeStore.setText("USER TYPE");
        storePanel.add(userTypeStore, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 210, 180, -1));

        viewReceivedOrderedItemsBtn.setBackground(new java.awt.Color(34, 44, 246));
        viewReceivedOrderedItemsBtn.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        viewReceivedOrderedItemsBtn.setForeground(java.awt.Color.white);
        viewReceivedOrderedItemsBtn.setText("VIEW RECEIVED ODERED ITEMS");
        viewReceivedOrderedItemsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewReceivedOrderedItemsBtnActionPerformed(evt);
            }
        });
        storePanel.add(viewReceivedOrderedItemsBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 390, 50));

        exitSystemBtn.setBackground(new java.awt.Color(244, 7, 7));
        exitSystemBtn.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        exitSystemBtn.setForeground(java.awt.Color.white);
        exitSystemBtn.setText("EXIT SYSTEM");
        exitSystemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitSystemBtnActionPerformed(evt);
            }
        });
        storePanel.add(exitSystemBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 390, 50));

        checkDepartmentalOrdersBtn1.setBackground(new java.awt.Color(34, 44, 246));
        checkDepartmentalOrdersBtn1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        checkDepartmentalOrdersBtn1.setForeground(java.awt.Color.white);
        checkDepartmentalOrdersBtn1.setText("CHECK DEPARTMENAL ORDERS");
        checkDepartmentalOrdersBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkDepartmentalOrdersBtn1ActionPerformed(evt);
            }
        });
        storePanel.add(checkDepartmentalOrdersBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 390, 50));

        logoutBtn1.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        logoutBtn1.setForeground(new java.awt.Color(10, 15, 246));
        logoutBtn1.setText("LOG OUT");
        logoutBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtn1ActionPerformed(evt);
            }
        });
        storePanel.add(logoutBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 260, 100, -1));

        messageBtn.setBackground(new java.awt.Color(34, 44, 246));
        messageBtn.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        messageBtn.setForeground(java.awt.Color.white);
        messageBtn.setText("MESSAGE FINANCE DEPARTMENT");
        messageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messageBtnActionPerformed(evt);
            }
        });
        storePanel.add(messageBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 390, 50));

        aboutCheckBox5.setText("HALL OF FAME");
        aboutCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutCheckBox5ActionPerformed(evt);
            }
        });
        storePanel.add(aboutCheckBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(948, 414, 130, 30));

        jTabbedPane1.addTab("STORE", storePanel);

        financePanel.setBackground(new java.awt.Color(176, 41, 180));
        financePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblProfileFinance.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        financePanel.add(lblProfileFinance, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, 150, 170));

        lblUsernameFinance.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblUsernameFinance.setForeground(java.awt.Color.white);
        lblUsernameFinance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsernameFinance.setText("USERNAME");
        financePanel.add(lblUsernameFinance, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 230, 180, -1));

        supplierPaymentBtn.setBackground(java.awt.Color.blue);
        supplierPaymentBtn.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        supplierPaymentBtn.setForeground(java.awt.Color.white);
        supplierPaymentBtn.setText("PAY SUPPLIERS");
        supplierPaymentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierPaymentBtnActionPerformed(evt);
            }
        });
        financePanel.add(supplierPaymentBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 250, 50));

        addSuppliersBtn.setBackground(java.awt.Color.blue);
        addSuppliersBtn.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        addSuppliersBtn.setForeground(java.awt.Color.white);
        addSuppliersBtn.setText("ADD SUPPLIERS");
        addSuppliersBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSuppliersBtnActionPerformed(evt);
            }
        });
        financePanel.add(addSuppliersBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 250, 50));

        tenderBtn.setBackground(java.awt.Color.blue);
        tenderBtn.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        tenderBtn.setForeground(java.awt.Color.white);
        tenderBtn.setText("MANAGE TENDERS");
        tenderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tenderBtnActionPerformed(evt);
            }
        });
        financePanel.add(tenderBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 250, 50));

        lblUserTypeFinance.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblUserTypeFinance.setForeground(java.awt.Color.white);
        lblUserTypeFinance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserTypeFinance.setText("USER TYPE");
        financePanel.add(lblUserTypeFinance, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 210, 180, -1));

        viewPaymentsBtn.setBackground(java.awt.Color.blue);
        viewPaymentsBtn.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        viewPaymentsBtn.setForeground(java.awt.Color.white);
        viewPaymentsBtn.setText("VIEW PAYMENTS");
        viewPaymentsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewPaymentsBtnActionPerformed(evt);
            }
        });
        financePanel.add(viewPaymentsBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 250, 50));

        exitBtn1.setBackground(new java.awt.Color(247, 63, 9));
        exitBtn1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        exitBtn1.setForeground(java.awt.Color.white);
        exitBtn1.setText("EXIT SYSTEM");
        exitBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtn1ActionPerformed(evt);
            }
        });
        financePanel.add(exitBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 250, 50));

        logoutBtn2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        logoutBtn2.setForeground(new java.awt.Color(10, 15, 246));
        logoutBtn2.setText("LOG OUT");
        logoutBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtn2ActionPerformed(evt);
            }
        });
        financePanel.add(logoutBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 260, 100, -1));

        messageStoreBtn.setBackground(java.awt.Color.blue);
        messageStoreBtn.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        messageStoreBtn.setForeground(java.awt.Color.white);
        messageStoreBtn.setText("MESSAGE STORE");
        messageStoreBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messageStoreBtnActionPerformed(evt);
            }
        });
        financePanel.add(messageStoreBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 250, 50));

        aboutCheckBox4.setText("HALL OF FAME");
        aboutCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutCheckBox4ActionPerformed(evt);
            }
        });
        financePanel.add(aboutCheckBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(948, 414, 130, 30));

        jTabbedPane1.addTab("FINANCE", financePanel);

        usersAccountsPanel.setBackground(new java.awt.Color(50, 186, 202));
        usersAccountsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblProfileAccounts.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        usersAccountsPanel.add(lblProfileAccounts, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, 150, 170));

        lblUsernameAccounts.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblUsernameAccounts.setForeground(java.awt.Color.white);
        lblUsernameAccounts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsernameAccounts.setText("USERNAME");
        usersAccountsPanel.add(lblUsernameAccounts, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 230, 180, -1));

        editUsersBtn.setBackground(new java.awt.Color(30, 79, 225));
        editUsersBtn.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        editUsersBtn.setForeground(java.awt.Color.white);
        editUsersBtn.setText("VIEW & EDIT USERS");
        editUsersBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editUsersBtnActionPerformed(evt);
            }
        });
        usersAccountsPanel.add(editUsersBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 250, 50));

        newUserBtn.setBackground(new java.awt.Color(13, 43, 251));
        newUserBtn.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        newUserBtn.setForeground(java.awt.Color.white);
        newUserBtn.setText("CREATE NEW USER");
        newUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newUserBtnActionPerformed(evt);
            }
        });
        usersAccountsPanel.add(newUserBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 250, 50));

        lblUserTypeAccounts.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblUserTypeAccounts.setForeground(java.awt.Color.white);
        lblUserTypeAccounts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserTypeAccounts.setText("USER TYPE");
        usersAccountsPanel.add(lblUserTypeAccounts, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 210, 180, -1));

        exitBtn2.setBackground(new java.awt.Color(247, 63, 9));
        exitBtn2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        exitBtn2.setForeground(java.awt.Color.white);
        exitBtn2.setText("EXIT SYSTEM");
        exitBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtn2ActionPerformed(evt);
            }
        });
        usersAccountsPanel.add(exitBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 250, 50));

        logoutBtn3.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        logoutBtn3.setForeground(new java.awt.Color(10, 15, 246));
        logoutBtn3.setText("LOG OUT");
        logoutBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtn3ActionPerformed(evt);
            }
        });
        usersAccountsPanel.add(logoutBtn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 260, 100, -1));

        aboutCheckBox3.setText("HALL OF FAME");
        aboutCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutCheckBox3ActionPerformed(evt);
            }
        });
        usersAccountsPanel.add(aboutCheckBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(948, 414, 130, 30));

        jTabbedPane1.addTab("USER ACCOUNTS", usersAccountsPanel);

        departmentalPanel.setBackground(new java.awt.Color(25, 198, 28));
        departmentalPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblProfileKitchen.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        departmentalPanel.add(lblProfileKitchen, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 20, 150, 170));

        lblUsernameKitchen.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblUsernameKitchen.setForeground(java.awt.Color.white);
        lblUsernameKitchen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsernameKitchen.setText("USERNAME");
        departmentalPanel.add(lblUsernameKitchen, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 240, 180, -1));

        lblUserTypeKitchen.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblUserTypeKitchen.setForeground(java.awt.Color.white);
        lblUserTypeKitchen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserTypeKitchen.setText("USER TYPE");
        departmentalPanel.add(lblUserTypeKitchen, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 210, 180, -1));

        receivedOrderedItemsBtn.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        receivedOrderedItemsBtn.setText("RECEIVE ORDERED ITEMS");
        receivedOrderedItemsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receivedOrderedItemsBtnActionPerformed(evt);
            }
        });
        departmentalPanel.add(receivedOrderedItemsBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 270, 50));

        orderStoreItemsBtn.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        orderStoreItemsBtn.setText("ORDER STORE ITEMS");
        orderStoreItemsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderStoreItemsBtnActionPerformed(evt);
            }
        });
        departmentalPanel.add(orderStoreItemsBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 270, 50));

        exitBtn3.setBackground(new java.awt.Color(247, 63, 9));
        exitBtn3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        exitBtn3.setForeground(java.awt.Color.white);
        exitBtn3.setText("EXIT SYSTEM");
        exitBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtn3ActionPerformed(evt);
            }
        });
        departmentalPanel.add(exitBtn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 270, 50));

        logoutBtn4.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        logoutBtn4.setForeground(new java.awt.Color(10, 15, 246));
        logoutBtn4.setText("LOG OUT");
        logoutBtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtn4ActionPerformed(evt);
            }
        });
        departmentalPanel.add(logoutBtn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 260, 100, -1));

        aboutCheckBox2.setText("HALL OF FAME");
        aboutCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutCheckBox2ActionPerformed(evt);
            }
        });
        departmentalPanel.add(aboutCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(948, 414, 130, 30));

        jTabbedPane1.addTab("DEPARTMENTAL ORDERS", departmentalPanel);

        departmentsPanel.setBackground(new java.awt.Color(7, 7, 243));
        departmentsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblProfileAbout.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 3, true));
        departmentsPanel.add(lblProfileAbout, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 10, 150, 170));

        lblUsernameAbout.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblUsernameAbout.setForeground(java.awt.Color.white);
        lblUsernameAbout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsernameAbout.setText("USERNAME");
        departmentsPanel.add(lblUsernameAbout, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 220, 180, -1));

        lblUserTypeAbout.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblUserTypeAbout.setForeground(java.awt.Color.white);
        lblUserTypeAbout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserTypeAbout.setText("USER TYPE");
        departmentsPanel.add(lblUserTypeAbout, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 190, 180, -1));

        addDepartmentsBtn.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        addDepartmentsBtn.setText("ADD AND EDIT DEPARTMENTS");
        addDepartmentsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDepartmentsBtnActionPerformed(evt);
            }
        });
        departmentsPanel.add(addDepartmentsBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 260, 50));

        exitBtn4.setBackground(new java.awt.Color(247, 63, 9));
        exitBtn4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        exitBtn4.setForeground(java.awt.Color.white);
        exitBtn4.setText("EXIT SYSTEM");
        exitBtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtn4ActionPerformed(evt);
            }
        });
        departmentsPanel.add(exitBtn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 260, 50));

        logoutBtn5.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        logoutBtn5.setForeground(new java.awt.Color(10, 15, 246));
        logoutBtn5.setText("LOG OUT");
        logoutBtn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtn5ActionPerformed(evt);
            }
        });
        departmentsPanel.add(logoutBtn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 240, 100, -1));

        aboutCheckBox1.setText("HALL OF FAME");
        aboutCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutCheckBox1ActionPerformed(evt);
            }
        });
        departmentsPanel.add(aboutCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(948, 414, 130, 30));

        jTabbedPane1.addTab("DEPARTMENTS", departmentsPanel);

        aboutPanel.setBackground(new java.awt.Color(136, 51, 183));
        aboutPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        aboutCheckBox.setText("HALL OF FAME");
        aboutCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutCheckBoxActionPerformed(evt);
            }
        });
        aboutPanel.add(aboutCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(948, 414, 130, 30));

        jTabbedPane1.addTab("ABOUT", aboutPanel);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 1090, 490));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jLabel1.setForeground(java.awt.Color.blue);
        jLabel1.setText("ORDER MANAGEMENT SYSTEM");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 28, 800, 50));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void roomAllocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomAllocationActionPerformed
        this.setVisible(false);
        new ViewRoomAllocations(lblUsername.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_roomAllocationActionPerformed

    private void addClientBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addClientBtnActionPerformed
        this.setVisible(false);
        new ManageClients(lblUsername.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_addClientBtnActionPerformed

    private void mealsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mealsActionPerformed
        this.setVisible(false);
        new HotelMeals(lblUsername.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_mealsActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to exit Order Management System?","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private void hotelRoomsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hotelRoomsActionPerformed
        this.setVisible(false);
        new AvailableRooms(lblUsername.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_hotelRoomsActionPerformed

    private void meals1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meals1ActionPerformed
        this.setVisible(false);
        new SellMeals(lblUsername.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_meals1ActionPerformed

    private void newUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newUserBtnActionPerformed
        this.setVisible(false);
        new User(lblUsernameAccounts.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_newUserBtnActionPerformed

    private void stockInStoreBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockInStoreBtnActionPerformed
        this.setVisible(false);
        new StockInStore(lblUsernameStore.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_stockInStoreBtnActionPerformed

    private void editUsersBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editUsersBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Only admins are allowed to log in and edit users. Are you an admin?","Confirm if you are an ADMIN",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            this.setVisible(false);
            new LoginAdmin(lblUsernameAccounts.getText(),imag,userType).setVisible(true);
        }
    }//GEN-LAST:event_editUsersBtnActionPerformed

    private void supplierPaymentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierPaymentBtnActionPerformed
        this.setVisible(false);
        new PaySuppliers(lblUsernameFinance.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_supplierPaymentBtnActionPerformed

    private void issueStoreItemsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issueStoreItemsBtnActionPerformed
        this.setVisible(false);
        new IssueItems(lblUsernameStore.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_issueStoreItemsBtnActionPerformed

    private void salesViewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesViewBtnActionPerformed
        this.setVisible(false);
        new ViewSales(lblUsername.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_salesViewBtnActionPerformed

    private void addSuppliersBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSuppliersBtnActionPerformed
        this.setVisible(false);
        new Suppliers(lblUsernameFinance.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_addSuppliersBtnActionPerformed

    private void tenderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tenderBtnActionPerformed
        this.setVisible(false);
        new ManageTenders(lblUsernameFinance.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_tenderBtnActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        
    }//GEN-LAST:event_formWindowActivated

    private void addDepartmentsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDepartmentsBtnActionPerformed
        this.setVisible(false);
        new Departments(lblUsernameFinance.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_addDepartmentsBtnActionPerformed

    private void viewReceivedOrderedItemsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewReceivedOrderedItemsBtnActionPerformed
        this.setVisible(false);
        new ViewReceivedOrderedItems(lblUsernameFinance.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_viewReceivedOrderedItemsBtnActionPerformed

    private void exitSystemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitSystemBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to exit Order Management System?","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitSystemBtnActionPerformed

    private void viewPaymentsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewPaymentsBtnActionPerformed
        this.setVisible(false);
        new ViewPayments(lblUsernameFinance.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_viewPaymentsBtnActionPerformed

    private void orderStoreItemsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderStoreItemsBtnActionPerformed
        this.setVisible(false);
        new OrderStoreItems(lblUsernameFinance.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_orderStoreItemsBtnActionPerformed

    private void checkDepartmentalOrdersBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkDepartmentalOrdersBtn1ActionPerformed
        this.setVisible(false);
        new CheckDepartmentalOrders(lblUsernameFinance.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_checkDepartmentalOrdersBtn1ActionPerformed

    private void receivedOrderedItemsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receivedOrderedItemsBtnActionPerformed
        this.setVisible(false);
        new ReceiveStoreItems(lblUsernameFinance.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_receivedOrderedItemsBtnActionPerformed

    private void exitBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtn1ActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to exit Order Management System?","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtn1ActionPerformed

    private void exitBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtn2ActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to exit Order Management System?","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtn2ActionPerformed

    private void exitBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtn3ActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to exit Order Management System?","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtn3ActionPerformed

    private void exitBtn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtn4ActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to exit Order Management System?","Confirm Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtn4ActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to log out of Order Management System?","Confirm Logout",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            this.setVisible(false);
            new Login().setVisible(true);
        }
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void logoutBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtn1ActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to log out of Order Management System?","Confirm Logout",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            this.setVisible(false);
            new Login().setVisible(true);
        }
    }//GEN-LAST:event_logoutBtn1ActionPerformed

    private void logoutBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtn2ActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to log out of Order Management System?","Confirm Logout",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            this.setVisible(false);
            new Login().setVisible(true);
        }
    }//GEN-LAST:event_logoutBtn2ActionPerformed

    private void logoutBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtn3ActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to log out of Order Management System?","Confirm Logout",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            this.setVisible(false);
            new Login().setVisible(true);
        }
    }//GEN-LAST:event_logoutBtn3ActionPerformed

    private void logoutBtn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtn4ActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to log out of Order Management System?","Confirm Logout",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            this.setVisible(false);
            new Login().setVisible(true);
        }
    }//GEN-LAST:event_logoutBtn4ActionPerformed

    private void logoutBtn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtn5ActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Do yo want to log out of Order Management System?","Confirm Logout",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            this.setVisible(false);
            new Login().setVisible(true);
        }
    }//GEN-LAST:event_logoutBtn5ActionPerformed

    private void roomBookingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomBookingBtnActionPerformed
        this.setVisible(false);
        new ViewRoomBookings(lblUsername.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_roomBookingBtnActionPerformed

    private void roomIncomeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomIncomeBtnActionPerformed
        this.setVisible(false);
        new HotelIncome(lblUsername.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_roomIncomeBtnActionPerformed

    private void messageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messageBtnActionPerformed
        this.setVisible(false);
        new Messages(lblUsername.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_messageBtnActionPerformed

    private void messageStoreBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messageStoreBtnActionPerformed
        this.setVisible(false);
        new MessagesFinance(lblUsername.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_messageStoreBtnActionPerformed

    private void aboutCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutCheckBoxActionPerformed
        this.setVisible(false);
        new About(lblUsername.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_aboutCheckBoxActionPerformed

    private void aboutCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutCheckBox1ActionPerformed
        this.setVisible(false);
        new About(lblUsername.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_aboutCheckBox1ActionPerformed

    private void aboutCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutCheckBox2ActionPerformed
        this.setVisible(false);
        new About(lblUsername.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_aboutCheckBox2ActionPerformed

    private void aboutCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutCheckBox3ActionPerformed
        this.setVisible(false);
        new About(lblUsername.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_aboutCheckBox3ActionPerformed

    private void aboutCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutCheckBox4ActionPerformed
        this.setVisible(false);
        new About(lblUsername.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_aboutCheckBox4ActionPerformed

    private void aboutCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutCheckBox5ActionPerformed
        this.setVisible(false);
        new About(lblUsername.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_aboutCheckBox5ActionPerformed

    private void aboutCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutCheckBox6ActionPerformed
        this.setVisible(false);
        new About(lblUsername.getText(),imag,userType).setVisible(true);
    }//GEN-LAST:event_aboutCheckBox6ActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox aboutCheckBox;
    private javax.swing.JCheckBox aboutCheckBox1;
    private javax.swing.JCheckBox aboutCheckBox2;
    private javax.swing.JCheckBox aboutCheckBox3;
    private javax.swing.JCheckBox aboutCheckBox4;
    private javax.swing.JCheckBox aboutCheckBox5;
    private javax.swing.JCheckBox aboutCheckBox6;
    private javax.swing.JPanel aboutPanel;
    private javax.swing.JButton addClientBtn;
    private javax.swing.JToggleButton addDepartmentsBtn;
    private javax.swing.JButton addSuppliersBtn;
    private javax.swing.JButton checkDepartmentalOrdersBtn1;
    private javax.swing.JPanel departmentalPanel;
    private javax.swing.JPanel departmentsPanel;
    private javax.swing.JButton editUsersBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton exitBtn1;
    private javax.swing.JButton exitBtn2;
    private javax.swing.JButton exitBtn3;
    private javax.swing.JButton exitBtn4;
    private javax.swing.JButton exitSystemBtn;
    private javax.swing.JPanel financePanel;
    private javax.swing.JPanel hotelPanel;
    private javax.swing.JButton hotelRooms;
    private javax.swing.JButton issueStoreItemsBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblProfileAbout;
    private javax.swing.JLabel lblProfileAccounts;
    private javax.swing.JLabel lblProfileFinance;
    private javax.swing.JLabel lblProfileKitchen;
    private javax.swing.JLabel lblProfileStore;
    private javax.swing.JLabel lblUserType;
    private javax.swing.JLabel lblUserTypeAbout;
    private javax.swing.JLabel lblUserTypeAccounts;
    private javax.swing.JLabel lblUserTypeFinance;
    private javax.swing.JLabel lblUserTypeKitchen;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblUsernameAbout;
    private javax.swing.JLabel lblUsernameAccounts;
    private javax.swing.JLabel lblUsernameFinance;
    private javax.swing.JLabel lblUsernameKitchen;
    private javax.swing.JLabel lblUsernameStore;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JButton logoutBtn1;
    private javax.swing.JButton logoutBtn2;
    private javax.swing.JButton logoutBtn3;
    private javax.swing.JButton logoutBtn4;
    private javax.swing.JButton logoutBtn5;
    private javax.swing.JButton meals;
    private javax.swing.JButton meals1;
    private javax.swing.JButton messageBtn;
    private javax.swing.JButton messageStoreBtn;
    private javax.swing.JButton newUserBtn;
    private javax.swing.JToggleButton orderStoreItemsBtn;
    private javax.swing.JToggleButton receivedOrderedItemsBtn;
    private javax.swing.JButton roomAllocation;
    private javax.swing.JButton roomBookingBtn;
    private javax.swing.JButton roomIncomeBtn;
    private javax.swing.JButton salesViewBtn;
    private javax.swing.JButton stockInStoreBtn;
    private javax.swing.JPanel storePanel;
    private javax.swing.JButton supplierPaymentBtn;
    private javax.swing.JButton tenderBtn;
    private javax.swing.JLabel userTypeStore;
    private javax.swing.JPanel usersAccountsPanel;
    private javax.swing.JButton viewPaymentsBtn;
    private javax.swing.JButton viewReceivedOrderedItemsBtn;
    // End of variables declaration//GEN-END:variables
}
