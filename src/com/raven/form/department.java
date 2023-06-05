
package com.raven.form;

import com.raven.component.Header;
import com.raven.main.Main;
import com.raven.model.StatusType;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author RAVEN
 */
public class department extends javax.swing.JPanel {


   
      public void showrecord() {
    
    dbclass dbclass=new dbclass();
        ResultSet rs = dbclass.getwishtime();
        
        try{
            while(rs.next()) {
    String data[]={rs.getString("tname"),rs.getString("time")};
       
        DefaultTableModel tbmodel=(DefaultTableModel)jTable1.getModel();
        
        tbmodel.addRow(data);
        }}catch(Exception e){
    System.out.println(e);
    }
        
        
     }
 
 
      
      public void tcombo() {
     
        dbclass dbclass = new dbclass();
        ResultSet rs = dbclass.getteachers();
         
          
        
        try {
            while (rs.next()) {
                String data = rs.getString("tname");
                tcombo.addItem(data);
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
      
      
      public void timecombo() {
     
        dbclass dbclass = new dbclass();
        ResultSet rs = dbclass.gettime();
         
          
            
        try {
            while (rs.next()) {
                String data = rs.getString("timing");
                timecombo.addItem(data);
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
      
      
     
    
    public department() {
        initComponents();
         AutoCompleteDecorator.decorate(tcombo);
          AutoCompleteDecorator.decorate(timecombo);
         TableFilterHeader filterHeader = new TableFilterHeader(jTable1, AutoChoices.ENABLED);
        filterHeader.setPosition(TableFilterHeader.Position.TOP);
        filterHeader.setBackground(Color.WHITE);
         showrecord();
         tcombo();
         timecombo();
         
       
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        timecombo = new javax.swing.JComboBox<>();
        tcombo = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        search = new com.raven.swing.SearchText();
        rsearch = new javax.swing.JLabel();

        setBackground(new java.awt.Color(242, 242, 242));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Algerian", 1, 24)); // NOI18N
        jLabel4.setText("add Wish Time");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 13, 229, 48));

        jPanel3.add(timecombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 210, -1));

        tcombo.setSelectedItem(null);
        tcombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tcomboActionPerformed(evt);
            }
        });
        jPanel3.add(tcombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 210, -1));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 51, 620, 390));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jButton2.setBackground(new java.awt.Color(34, 40, 243));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("ADD ");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(153, 0, 0));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("DELETE ");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(274, Short.MAX_VALUE))
        );

        add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(702, 51, 200, 410));

        jTable1.setForeground(new java.awt.Color(0, 51, 204));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Teacher Name", "Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 467, 880, 140));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/search.png"))); // NOI18N

        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
            .addComponent(search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 0, -1, -1));

        rsearch.setForeground(new java.awt.Color(255, 0, 0));
        add(rsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(633, 32, 72, 13));
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
  int i= jTable1.getSelectedRow();
       TableModel model=jTable1.getModel();

        search.setText(model.getValueAt(i, 0).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        rsearch.setText("");
        DefaultTableModel table = (DefaultTableModel)jTable1.getModel();
        String sch = search.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
        jTable1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(sch));
    }//GEN-LAST:event_searchKeyReleased

    private void dptcombo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dptcombo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dptcombo2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dbclass dbclass=new dbclass();
        int i= jTable1.getSelectedRow();
       TableModel model=jTable1.getModel();
         ResultSet rs = dbclass.searchwishtime(model.getValueAt(i,0).toString(),model.getValueAt(i,1).toString());
         
        
         if(search.getText().equals("Search here ...") || search.getText().equals("")){
            rsearch.setText("*required");
        }
        else{
            rsearch.setText("");
            try {
                if(rs.isBeforeFirst()==true){
            
            
            dbclass.deletewishtime(model.getValueAt(i,0).toString(),model.getValueAt(i,1).toString());
            JOptionPane.showMessageDialog(null,"Wish Time succesfully deleted","Wish TIme delete",JOptionPane.INFORMATION_MESSAGE);
            DefaultTableModel tbmodel=(DefaultTableModel)jTable1.getModel();
            tbmodel.setRowCount(0);
            showrecord();
           
             }  else{
                    JOptionPane.showMessageDialog(null,"record not found","record not found",JOptionPane.ERROR_MESSAGE);
                }   } catch (SQLException ex) {
                    System.out.println(ex);
                }
         }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       dbclass dbclass=new dbclass();
       int i= jTable1.getSelectedRow();
       TableModel model=jTable1.getModel();
         ResultSet rs = dbclass.searchwishtime(tcombo.getSelectedItem().toString(),timecombo.getSelectedItem().toString());
          try {
              if(rs.isBeforeFirst()==false)
              {
                dbclass.insertwishtime(tcombo.getSelectedItem().toString(),timecombo.getSelectedItem().toString());
                JOptionPane.showMessageDialog(null,"Wish Time succesfully Added","Wish TIme Add",JOptionPane.INFORMATION_MESSAGE);
                DefaultTableModel tbmodel=(DefaultTableModel)jTable1.getModel();
                tbmodel.setRowCount(0);
                showrecord();
                  
              }
              else
              {
              JOptionPane.showMessageDialog(null,"Duplicate Entry this Time Is Already Assign to  "+tcombo.getSelectedItem().toString(),"Duplicate Entry",JOptionPane.INFORMATION_MESSAGE);
              }
          } catch (SQLException ex) {
              System.out.println(ex);
          }
       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tcomboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tcomboActionPerformed
        

    }//GEN-LAST:event_tcomboActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTable1;
    private javax.swing.JLabel rsearch;
    public com.raven.swing.SearchText search;
    private javax.swing.JComboBox<String> tcombo;
    private javax.swing.JComboBox<String> timecombo;
    // End of variables declaration//GEN-END:variables
}
