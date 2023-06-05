package com.raven.form;

import java.awt.Color;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;


public class manual_timetable extends javax.swing.JPanel {
int classRow=-1;
int timeslotRow=-1;
    int teacherDailyClasses=2;
    int teacherDailyLabs=1;
    int sectionDailyClasses=2;
    int sectionDailyLabs=1;
    String chkClassType;
    
    public void showrecord() {
    
    dbclass dbclass=new dbclass();
       
           ResultSet rs = dbclass.getIgnoreTTdata();
        
        try{
            while(rs.next()) {
               
                
    String data[]={rs.getString("sNo"),rs.getString("cname"),rs.getString("tname"),rs.getString("section"),rs.getString("ctype"),rs.getString("shift")};
       
        DefaultTableModel tbmodel=(DefaultTableModel)IgnoreDataTable.getModel();
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
       
       
     }
    
    
    public manual_timetable() {
        initComponents();
        TableFilterHeader filterHeader = new TableFilterHeader(jTable2, AutoChoices.ENABLED);
        filterHeader.setPosition(TableFilterHeader.Position.TOP);
        filterHeader.setBackground(Color.WHITE);
        
         TableFilterHeader filterHeader1 = new TableFilterHeader(IgnoreDataTable, AutoChoices.ENABLED);
        filterHeader1.setPosition(TableFilterHeader.Position.TOP);
        filterHeader1.setBackground(Color.WHITE);
        
        showrecord();
        showrecord1();
        
        classcountPanel.setVisible(false);
        labcountPanel.setVisible(false);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        IgnoreDataTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        labcountPanel = new javax.swing.JPanel();
        tlabs = new javax.swing.JSpinner();
        slabs = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        classcountPanel = new javax.swing.JPanel();
        sclass = new javax.swing.JSpinner();
        tclass = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(242, 242, 242));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        IgnoreDataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S No", "Couese Name", "Teachder Name ", "Section", "Class Type", "Shift"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        IgnoreDataTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IgnoreDataTableMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(IgnoreDataTable);

        jPanel1.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 880, 170));

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
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 880, 160));

        jButton3.setBackground(new java.awt.Color(34, 40, 243));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("ADD ");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 390, 140, -1));

        labcountPanel.setBackground(new java.awt.Color(242, 242, 242));
        labcountPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tlabs.setModel(new javax.swing.SpinnerNumberModel(1, 1, 7, 1));
        labcountPanel.add(tlabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 50, -1));

        slabs.setModel(new javax.swing.SpinnerNumberModel(1, 1, 7, 1));
        labcountPanel.add(slabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 50, -1));

        jLabel3.setText("Daily Section Labs");
        labcountPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 140, 20));

        jLabel6.setText("Daily Teacher Labs");
        labcountPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, 20));

        jPanel1.add(labcountPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 420, 470, 40));

        classcountPanel.setBackground(new java.awt.Color(242, 242, 242));
        classcountPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sclass.setModel(new javax.swing.SpinnerNumberModel(2, 1, 7, 1));
        classcountPanel.add(sclass, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 50, -1));

        tclass.setModel(new javax.swing.SpinnerNumberModel(2, 1, 7, 1));
        classcountPanel.add(tclass, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 50, -1));

        jLabel8.setText("Daily Teacher Classes");
        classcountPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, 20));

        jLabel10.setText("Daily Section Classes");
        classcountPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 140, 20));

        jPanel1.add(classcountPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 380, 470, 40));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 910, 550));

        jButton1.setBackground(new java.awt.Color(153, 153, 153));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, -1));

        jButton2.setBackground(new java.awt.Color(153, 153, 153));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Edit");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 80, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        teacherDailyClasses=(int) tclass.getValue();
        teacherDailyLabs=(int) tlabs.getValue();
        sectionDailyClasses=(int) sclass.getValue();
        sectionDailyLabs=(int) slabs.getValue();
        
        TableModel model1=IgnoreDataTable.getModel();
        TableModel model2=jTable2.getModel();
        dbclass dbclass=new dbclass();
       
        
        try{
          if(classRow==-1)
          {
           JOptionPane.showMessageDialog(null,"Please select Class","Please select Class",JOptionPane.ERROR_MESSAGE);
            
          }
          else if(timeslotRow==-1)
          {
           JOptionPane.showMessageDialog(null,"Please select Time Slot","Please select Time Slot",JOptionPane.ERROR_MESSAGE);
            
          }
          else
          {
            if(chkClassType.equals("Class"))
            {
                
             ResultSet rs0=dbclass.chkRoomSameTimeClash(model2.getValueAt(timeslotRow, 0).toString(), model2.getValueAt(timeslotRow, 1).toString(), model2.getValueAt(timeslotRow, 2).toString());
             boolean rsRoom=rs0.isBeforeFirst();
             ResultSet rs=dbclass.chkSecSameTimeClash(model1.getValueAt(classRow, 3).toString(), model2.getValueAt(timeslotRow, 0).toString(), model2.getValueAt(timeslotRow, 1).toString());
             boolean rsSec=rs.isBeforeFirst();
             ResultSet rs1=dbclass.chkTecherSameTimeClash(model1.getValueAt(classRow, 2).toString(), model2.getValueAt(timeslotRow, 0).toString(), model2.getValueAt(timeslotRow, 1).toString());
             boolean rsTech=rs1.isBeforeFirst();
             ResultSet rs2=dbclass.countTeacherDailyClasses(model1.getValueAt(classRow, 2).toString(),model2.getValueAt(timeslotRow, 0).toString());
             rs2.next();
             int countTeachClasses=rs2.getInt(1);
             ResultSet rs3=dbclass.countSectionDailyClasses(model1.getValueAt(classRow, 3).toString(),model2.getValueAt(timeslotRow, 0).toString());
             rs3.next();
             int countSecClasses=rs3.getInt(1);
             
             if(rsRoom==true || rsSec==true || rsTech==true || countTeachClasses==teacherDailyClasses || countSecClasses==sectionDailyLabs)
                    {
                        if(rsRoom==true)
                        {
                        JOptionPane.showMessageDialog(null,"This Time Slot is Already Allocated to an other Section","Room Clash",JOptionPane.WARNING_MESSAGE);
                        }
                        else if(rsSec==true)
                        {
                        JOptionPane.showMessageDialog(null,"Section "+model1.getValueAt(classRow, 3).toString()+" Have Already Class On this time ","Section Same Time Class Clash",JOptionPane.WARNING_MESSAGE);
                        }
                        else if(rsTech==true)
                        {
                        JOptionPane.showMessageDialog(null," "+model1.getValueAt(classRow, 2).toString()+" Have Already Class On this time ","Teacher Same Time Class Clash",JOptionPane.WARNING_MESSAGE);
                        }
                        else if(countTeachClasses==teacherDailyClasses)
                        {
                        JOptionPane.showMessageDialog(null," "+model1.getValueAt(classRow, 2).toString()+" can't take Class on " +model2.getValueAt(timeslotRow, 0).toString()+" Beacuse he/She Already Take " +teacherDailyClasses+" Classes  On this Day ","Teacher Daily Classes Limit Clash",JOptionPane.WARNING_MESSAGE);
                        }
                        else if(countSecClasses==sectionDailyLabs)
                        {
                        JOptionPane.showMessageDialog(null," "+model1.getValueAt(classRow, 3).toString()+" can't take Class on " +model2.getValueAt(timeslotRow, 0).toString()+" Beacuse This Section Already Have " +teacherDailyClasses+" Classes  On this Day ","Section Daily Classes Limit Clash",JOptionPane.WARNING_MESSAGE);
                        }
                        
                        
                      
                        
                    } 
                    else
                    {
                     dbclass.inserttimetable(model1.getValueAt(classRow, 1).toString(), model1.getValueAt(classRow, 2).toString(), model1.getValueAt(classRow, 3).toString(), model2.getValueAt(timeslotRow, 0).toString(), model2.getValueAt(timeslotRow, 1).toString(), model2.getValueAt(timeslotRow, 2).toString(), model1.getValueAt(classRow, 4).toString(),model2.getValueAt(timeslotRow, 4).toString());
                     dbclass.deleteIgnoreTTclass(model1.getValueAt(classRow,0).toString(),model1.getValueAt(classRow, 1).toString(), model1.getValueAt(classRow, 2).toString(), model1.getValueAt(classRow, 3).toString(), model1.getValueAt(classRow, 4).toString());
                    
                    DefaultTableModel table1 = (DefaultTableModel)IgnoreDataTable.getModel();
                    table1.setRowCount(0);
                    showrecord();
                    DefaultTableModel table2 = (DefaultTableModel)jTable2.getModel();
                    table2.setRowCount(0);
                    showrecord1();
                    
                    JOptionPane.showMessageDialog(null,"Time Succesfully Allocated To Class","Time Succesfully Allocated To Class",JOptionPane.INFORMATION_MESSAGE);
          
                    }
            }
            else if(chkClassType.equals("Lab"))
            {
                
             ResultSet rs0=dbclass.chkRoomSameTimeClash(model2.getValueAt(timeslotRow, 0).toString(), model2.getValueAt(timeslotRow, 1).toString(), model2.getValueAt(timeslotRow, 2).toString());
             boolean rsRoom=rs0.isBeforeFirst();
             ResultSet rs=dbclass.chkSecSameTimeClash(model1.getValueAt(classRow, 3).toString(), model2.getValueAt(timeslotRow, 0).toString(), model2.getValueAt(timeslotRow, 1).toString());
             boolean rsSec=rs.isBeforeFirst();
             ResultSet rs1=dbclass.chkTecherSameTimeClash(model1.getValueAt(classRow, 2).toString(), model2.getValueAt(timeslotRow, 0).toString(), model2.getValueAt(timeslotRow, 1).toString());
             boolean rsTech=rs1.isBeforeFirst();
             ResultSet rs2=dbclass.countTeacherDailyLabs(model1.getValueAt(classRow, 2).toString(),model2.getValueAt(timeslotRow, 0).toString());
             rs2.next();
             int countTeachLabs=rs2.getInt(1);
             ResultSet rs3=dbclass.countSectionDailyLabs(model1.getValueAt(classRow, 3).toString(),model2.getValueAt(timeslotRow, 0).toString());
             rs3.next();
             int countSecLabs=rs3.getInt(1);
             
             if(rsRoom==true || rsSec==true || rsTech==true || countTeachLabs==teacherDailyLabs || countSecLabs==sectionDailyClasses)
                    {
                        
                      if(rsRoom==true)
                        {
                        JOptionPane.showMessageDialog(null,"This Time Slot is Already Allocated to an other Section","Room Clash",JOptionPane.WARNING_MESSAGE);
                        }
                        else if(rsSec==true)
                        {
                        JOptionPane.showMessageDialog(null,"Section "+model1.getValueAt(classRow, 3).toString()+" Have Already Class On this time ","Section Same Time Class Clash",JOptionPane.WARNING_MESSAGE);
                        }
                        else if(rsTech==true)
                        {
                        JOptionPane.showMessageDialog(null," "+model1.getValueAt(classRow, 2).toString()+" Have Already Class On this time ","Teacher Same Time Class Clash",JOptionPane.WARNING_MESSAGE);
                        }
                        else if(countTeachLabs==teacherDailyLabs)
                        {
                        JOptionPane.showMessageDialog(null," "+model1.getValueAt(classRow, 2).toString()+" can't take Lab on " +model2.getValueAt(timeslotRow, 0).toString()+" Beacuse he/She Already Take " +teacherDailyClasses+" Lab  On this Day ","Teacher Daily Labs Limit Clash",JOptionPane.WARNING_MESSAGE);
                        }
                        else if(countSecLabs==sectionDailyClasses)
                        {
                        JOptionPane.showMessageDialog(null," "+model1.getValueAt(classRow, 3).toString()+" can't take Class on " +model2.getValueAt(timeslotRow, 0).toString()+" Beacuse This Section Already Have " +teacherDailyClasses+" Labs  On this Day ","Section Daily Labs Limit Clash",JOptionPane.WARNING_MESSAGE);
                        }
                        
                    } 
                    else
                    {
                     dbclass.inserttimetable(model1.getValueAt(classRow, 1).toString(), model1.getValueAt(classRow, 2).toString(), model1.getValueAt(classRow, 3).toString(), model2.getValueAt(timeslotRow, 0).toString(), model2.getValueAt(timeslotRow, 1).toString(), model2.getValueAt(timeslotRow, 2).toString(),model1.getValueAt(classRow, 4).toString(),model2.getValueAt(timeslotRow, 4).toString());
                     dbclass.deleteIgnoreTTclass(model1.getValueAt(classRow,0).toString(),model1.getValueAt(classRow, 1).toString(), model1.getValueAt(classRow, 2).toString(), model1.getValueAt(classRow, 3).toString(), model1.getValueAt(classRow, 4).toString());
                    
                    DefaultTableModel table1 = (DefaultTableModel)IgnoreDataTable.getModel();
                    table1.setRowCount(0);
                    showrecord();
                    DefaultTableModel table2 = (DefaultTableModel)jTable2.getModel();
                    table2.setRowCount(0);
                    showrecord1();
                    JOptionPane.showMessageDialog(null,"Time Succesfully Allocated To Class","Time Succesfully Allocated To Class",JOptionPane.INFORMATION_MESSAGE);
          
                    }
            }
            else
            {
                
            }
         }
      }
      catch(Exception ex)
        {
            System.out.println(ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void IgnoreDataTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IgnoreDataTableMouseClicked
       classRow=IgnoreDataTable.getSelectedRow();
       TableModel model=IgnoreDataTable.getModel();
       
       chkClassType=model.getValueAt(classRow, 4).toString();
       
       if(chkClassType.equals("Class")){
        classcountPanel.setVisible(true);
        labcountPanel.setVisible(false);
       }
       else if(chkClassType.equals("Lab")){
        classcountPanel.setVisible(false);
        labcountPanel.setVisible(true);
       }
    }//GEN-LAST:event_IgnoreDataTableMouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
       timeslotRow=jTable2.getSelectedRow();
    }//GEN-LAST:event_jTable2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable IgnoreDataTable;
    private javax.swing.JPanel classcountPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JPanel labcountPanel;
    private javax.swing.JSpinner sclass;
    private javax.swing.JSpinner slabs;
    private javax.swing.JSpinner tclass;
    private javax.swing.JSpinner tlabs;
    // End of variables declaration//GEN-END:variables
}
