/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelmanagement.system;

import javax.swing.JPanel;

/**
 *
 * @author bill
 */
public class JPanelLoader {
    public  void jPanelLoader(JPanel Main,JPanel setPanel){

      Main.removeAll();
     
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(Main);

        Main.setLayout(layout);

        layout.setHorizontalGroup(

            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

            .addComponent(setPanel, javax.swing.GroupLayout.Alignment.LEADING, 

javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE,

Short.MAX_VALUE));

        layout.setVerticalGroup(

            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

            .addComponent(setPanel, javax.swing.GroupLayout.Alignment.LEADING, 

javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, 

Short.MAX_VALUE) );
        
        System.gc();
    
    }
}
