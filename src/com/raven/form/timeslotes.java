/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author HP
 */
public class timeslotes extends javax.swing.JPanel {

        int total=0;
        int totalLab=0;
        int totalClass=0;
        
        int totalfree=0,labfree=0,classfree=0;
        
        
        public void exportToexcel() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showSaveDialog(this);
        File f = fileChooser.getSelectedFile();
        String path = f.getAbsolutePath();
        path = path + ".xlsx";
        Row row;
        Row row1;
        Cell cell;
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet ws = wb.createSheet();
       
        
       if(changeTableCombo.getSelectedItem().toString()=="All Slots")
       {
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
      
        TreeMap<String, Object[]> map = new TreeMap<>();
        
       

        // write the column headers
       
        
        
        for (int i = 0; i < model.getRowCount(); i++) {
            
            map.put(Integer.toString(i), new Object[]{
                model.getValueAt(i, 0),
                model.getValueAt(i, 1),
                model.getValueAt(i, 2),
                model.getValueAt(i, 3),
                model.getValueAt(i, 4)
                
            }
            );
      
        }
        
        
         
        Set<String> id = map.keySet();
        XSSFRow fRow;
        int rowId = 1;
       
         
        for (String Key : id) {
            fRow = ws.createRow(rowId++);
            Object[] value = map.get(Key);
            int cellId = 0;
            for (Object object : value) {
                XSSFCell cel = fRow.createCell(cellId++);
                cel.setCellValue(object.toString());
            }
        }
        row = ws.createRow(0);
        for (int c = 0; c < model.getColumnCount(); c++) {
            cell = row.createCell(c);
            cell.setCellValue(model.getColumnName(c));
        }
        
        
        
           
       
        
        try {
            FileOutputStream fos = new FileOutputStream(new File(path));
            wb.write(fos);
            JOptionPane.showMessageDialog(this, "Exported Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
      }
       
       
       else  if(changeTableCombo.getSelectedItem().toString()=="Free Slots")
       {
        DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
      
        TreeMap<String, Object[]> map = new TreeMap<>();
        
       

        // write the column headers
       
        
        
        for (int i = 0; i < model.getRowCount(); i++) {
            
            map.put(Integer.toString(i), new Object[]{
                model.getValueAt(i, 0),
                model.getValueAt(i, 1),
                model.getValueAt(i, 2),
                model.getValueAt(i, 3),
                model.getValueAt(i, 4)
            }
            );
      
        }
        
        
         
        Set<String> id = map.keySet();
        XSSFRow fRow;
        int rowId = 1;
       
         
        for (String Key : id) {
            fRow = ws.createRow(rowId++);
            Object[] value = map.get(Key);
            int cellId = 0;
            for (Object object : value) {
                XSSFCell cel = fRow.createCell(cellId++);
                cel.setCellValue(object.toString());
            }
        }
        row = ws.createRow(0);
        for (int c = 0; c < model.getColumnCount(); c++) {
            cell = row.createCell(c);
            cell.setCellValue(model.getColumnName(c));
        }
        
        
        
           
       
        
        try {
            FileOutputStream fos = new FileOutputStream(new File(path));
            wb.write(fos);
            JOptionPane.showMessageDialog(this, "Exported Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
      }
    }
       
    
      public void showrecord() {
    
    dbclass dbclass=new dbclass();
    dbclass.deletetimeslotes();
    dbclass.inserttimeslotes();
        ResultSet rs = dbclass.gettimeslotes();
        
        try{
            while(rs.next()) {
    String data[]={rs.getString("day"),rs.getString("time"),rs.getString("room"),rs.getString("RoomType"),rs.getString("shift")};
       
        DefaultTableModel tbmodel=(DefaultTableModel)jTable1.getModel();
        
        tbmodel.addRow(data);
        }}catch(Exception e){
    System.out.println(e);
    }
        
        
     }
      
      public void showrecord1() {
    
    dbclass dbclass=new dbclass();
    DefaultTableModel tbmodel=(DefaultTableModel)jTable2.getModel();
    tbmodel.setRowCount(0);
        ResultSet rs = dbclass.getFreeSlotst();
        
        try{
            while(rs.next()) {
    String data[]={rs.getString("day"),rs.getString("time"),rs.getString("room"),rs.getString("roomType"),rs.getString("shift")};
       
       
        
        tbmodel.addRow(data);
        }}catch(Exception e){
    System.out.println(e);
    }
        ///free slots
       DefaultTableModel tbmodel1=(DefaultTableModel)jTable2.getModel();
         totalfree=tbmodel1.getRowCount();
         classfree=0;
         labfree=0;
        for(int i=0;i<tbmodel1.getRowCount();i++)
        {
         if(tbmodel1.getValueAt(i, 3).equals("Class"))
         {
        classfree++;
         }
         else if(tbmodel1.getValueAt(i, 3).equals("Lab"))
         {
         labfree++;
         }
        }
        
     }
      
      
      
      
    public timeslotes() {
        initComponents();
       jPanel2.setVisible(false);
        AutoCompleteDecorator.decorate(changeTableCombo);
        TableFilterHeader filterHeader = new TableFilterHeader(jTable1, AutoChoices.ENABLED);
        filterHeader.setPosition(TableFilterHeader.Position.TOP);
        filterHeader.setBackground(Color.WHITE);
        
        TableFilterHeader filterHeader1 = new TableFilterHeader(jTable2, AutoChoices.ENABLED);
        filterHeader1.setPosition(TableFilterHeader.Position.TOP);
        filterHeader1.setBackground(Color.WHITE);
        
        showrecord();
        showrecord1();
        
        //////All slots
         DefaultTableModel tbmodel=(DefaultTableModel)jTable1.getModel();
         total=tbmodel.getRowCount();
         totalClass=0;
         totalLab=0;
        for(int i=0;i<tbmodel.getRowCount();i++)
        {
         if(tbmodel.getValueAt(i, 3).equals("Class"))
         {
         totalClass++;
         }
         else if(tbmodel.getValueAt(i, 3).equals("Lab"))
         {
         totalLab++;
         }
        }
       TotalSlotes.setText("Total Time Slotes "+total);
       TotalClassSlotes.setText("Total Class Slotes "+totalClass);
       TotalLabSlotes.setText("Total Lab Slotes "+totalLab);
       
       
       ///free slots
       DefaultTableModel tbmodel1=(DefaultTableModel)jTable2.getModel();
         totalfree=tbmodel1.getRowCount();
         classfree=0;
         labfree=0;
        for(int i=0;i<tbmodel1.getRowCount();i++)
        {
         if(tbmodel1.getValueAt(i, 3).equals("Class"))
         {
        classfree++;
         }
         else if(tbmodel1.getValueAt(i, 3).equals("Lab"))
         {
         labfree++;
         }
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

        jLabel4 = new javax.swing.JLabel();
        rsearch = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        search2 = new com.raven.swing.SearchText();
        jButton3 = new javax.swing.JButton();
        TotalSlotes = new javax.swing.JLabel();
        TotalClassSlotes = new javax.swing.JLabel();
        TotalLabSlotes = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        changeTableCombo = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();

        setBackground(new java.awt.Color(242, 242, 242));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Algerian", 1, 24)); // NOI18N
        jLabel4.setText("TIME SLOTs");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 75, 152, 48));

        rsearch.setForeground(new java.awt.Color(255, 0, 0));
        add(rsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(664, 51, 72, 13));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/search.png"))); // NOI18N

        search2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search2KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search2, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
            .addComponent(search2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 19, -1, -1));

        jButton3.setBackground(new java.awt.Color(0, 102, 51));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("REFRESH");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 80, 150, -1));
        add(TotalSlotes, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, 190, 20));
        add(TotalClassSlotes, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 580, 190, 20));
        add(TotalLabSlotes, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 560, 190, 20));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Day", "Time", "Room", "Room Type", "Shift"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 10, 730, 390));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 750, 410));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Day", "Time", "Room", "Room Type", "Shift"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 10, 730, 390));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 750, 410));

        changeTableCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Slots", "Free Slots" }));
        changeTableCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                changeTableComboItemStateChanged(evt);
            }
        });
        add(changeTableCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 150, -1));

        jButton5.setBackground(new java.awt.Color(0, 0, 153));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("EXPORT");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, 150, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void search2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search2KeyReleased
        rsearch.setText("");
        DefaultTableModel table = (DefaultTableModel)jTable1.getModel();
        String sch = search2.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
        jTable1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(sch));
    }//GEN-LAST:event_search2KeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       dbclass dbclass=new dbclass();
       dbclass.deletetimeslotes();
       dbclass.inserttimeslotes();
      DefaultTableModel table = (DefaultTableModel)jTable1.getModel();
      table.setRowCount(0);
      showrecord();
      DefaultTableModel table1 = (DefaultTableModel)jTable2.getModel();
      table1.setRowCount(0);
      showrecord1();
      
        if(changeTableCombo.getSelectedItem().equals("All Slots"))
    {
    jPanel2.setVisible(false);
    jPanel1.setVisible(true);
    DefaultTableModel tbmodel=(DefaultTableModel)jTable1.getModel();
      total=tbmodel.getRowCount();
        totalClass=0;
         totalLab=0;
        for(int i=0;i<tbmodel.getRowCount();i++)
        {
         if(tbmodel.getValueAt(i, 3).equals("Class"))
         {
         totalClass++;
         }
         else if(tbmodel.getValueAt(i, 3).equals("Lab"))
         {
         totalLab++;
         }
        }
       TotalSlotes.setText("Total Time Slotes "+total);
       TotalClassSlotes.setText("Total Class Slotes "+totalClass);
       TotalLabSlotes.setText("Total Lab Slotes "+totalLab);
        
       
    }
    else if(changeTableCombo.getSelectedItem().equals("Free Slots"))
    {
    jPanel1.setVisible(false);
    jPanel2.setVisible(true);
    DefaultTableModel tbmodel=(DefaultTableModel)jTable2.getModel();
      total=tbmodel.getRowCount();
        totalClass=0;
         totalLab=0;
        for(int i=0;i<tbmodel.getRowCount();i++)
        {
         if(tbmodel.getValueAt(i, 3).equals("Class"))
         {
         totalClass++;
         }
         else if(tbmodel.getValueAt(i, 3).equals("Lab"))
         {
         totalLab++;
         }
        }
       TotalSlotes.setText("Total Time Slotes "+total);
       TotalClassSlotes.setText("Total Class Slotes "+totalClass);
       TotalLabSlotes.setText("Total Lab Slotes "+totalLab);
       
    }
        
        
        ///free slots
       DefaultTableModel tbmodel1=(DefaultTableModel)jTable2.getModel();
         totalfree=tbmodel1.getRowCount();
         classfree=0;
         labfree=0;
        for(int i=0;i<tbmodel1.getRowCount();i++)
        {
         if(tbmodel1.getValueAt(i, 3).equals("Class"))
         {
        classfree++;
         }
         else if(tbmodel1.getValueAt(i, 3).equals("Lab"))
         {
         labfree++;
         }
        }
       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void changeTableComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_changeTableComboItemStateChanged
      if(changeTableCombo.getSelectedItem().equals("All Slots"))
    {
    jPanel2.setVisible(false);
    jPanel1.setVisible(true);
    DefaultTableModel tbmodel=(DefaultTableModel)jTable1.getModel();
      total=tbmodel.getRowCount();
        totalClass=0;
         totalLab=0;
        for(int i=0;i<tbmodel.getRowCount();i++)
        {
         if(tbmodel.getValueAt(i, 3).equals("Class"))
         {
         totalClass++;
         }
         else if(tbmodel.getValueAt(i, 3).equals("Lab"))
         {
         totalLab++;
         }
        }
       TotalSlotes.setText("Total Time Slotes "+total);
       TotalClassSlotes.setText("Total Class Slotes "+totalClass);
       TotalLabSlotes.setText("Total Lab Slotes "+totalLab);
        
       
    }
    else if(changeTableCombo.getSelectedItem().equals("Free Slots"))
    {
    jPanel1.setVisible(false);
    jPanel2.setVisible(true);
    showrecord1();
    DefaultTableModel tbmodel=(DefaultTableModel)jTable2.getModel();
      total=tbmodel.getRowCount();
        totalClass=0;
         totalLab=0;
        for(int i=0;i<tbmodel.getRowCount();i++)
        {
         if(tbmodel.getValueAt(i, 3).equals("Class"))
         {
         totalClass++;
         }
         else if(tbmodel.getValueAt(i, 3).equals("Lab"))
         {
         totalLab++;
         }
        }
       TotalSlotes.setText("Total Time Slotes "+total);
       TotalClassSlotes.setText("Total Class Slotes "+totalClass);
       TotalLabSlotes.setText("Total Lab Slotes "+totalLab);
        
       
    }
    }//GEN-LAST:event_changeTableComboItemStateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        exportToexcel();
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TotalClassSlotes;
    private javax.swing.JLabel TotalLabSlotes;
    private javax.swing.JLabel TotalSlotes;
    private javax.swing.JComboBox<String> changeTableCombo;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel rsearch;
    public com.raven.swing.SearchText search2;
    // End of variables declaration//GEN-END:variables
}
