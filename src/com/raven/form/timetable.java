package com.raven.form;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_CANCEL_OPTION;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
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


public class timetable extends javax.swing.JPanel {

    
    int classes,timeslots;
    int morClassesCount,morClassTimeSlotsCount,morLabsCount,morLabTimeSlotsCount;
    int eveClassesCount,eveClassTimeSlotsCount,eveLabsCount,eveLabTimeSlotsCount;
    int teacherDailyClasses=3;
    int teacherDailyLabs=1;
    int sectionDailyClasses=3;
    int sectionDailyLabs=1;
    
    
    
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
       
        
       if(ttcombo.getSelectedItem().toString()=="TimeTable")
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
                model.getValueAt(i, 4),
                model.getValueAt(i, 5),
                model.getValueAt(i, 6)
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
       
       
       else  if(ttcombo.getSelectedItem().toString()=="Ignored Classes")
       {
        DefaultTableModel model = (DefaultTableModel)IgnoreDataTable.getModel();
      
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
       
           ResultSet rs = dbclass.gettimetable();
        
        try{
            while(rs.next()) {
               
                
    String data[]={rs.getString("CourseTitle"),rs.getString("CourseCoordinator"),rs.getString("Section"),rs.getString("Day"),rs.getString("Timing"),rs.getString("ClassRoom"),rs.getString("RoomType")};
       
        DefaultTableModel tbmodel=(DefaultTableModel)jTable1.getModel();
        tbmodel.addRow(data);
        }}catch(Exception e){
    System.out.println(e);
    }
      
     }
 
 public void showrecord1() {
    
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
 
 
 ////////////////////////For Morning Classes.////////////////////////////////////
 public void Classes() {
    
    dbclass dbclass=new dbclass();
       
           ResultSet rs = dbclass.getMorningClasses();
        
        try{
            while(rs.next()) {
               
                
    String data[]={rs.getString("cname"),rs.getString("tname"),rs.getString("section"),rs.getString("ctype"),rs.getString("shift")};
                  
        DefaultTableModel tbmodel=(DefaultTableModel)Classes.getModel();
        
        tbmodel.addRow(data);
        }
        morClassesCount=Classes.getRowCount();
           
        }catch(Exception e){
    System.out.println(e);
    }
        
        
     }
 
 
    public void ClassTimeSlots() {

       dbclass dbclass=new dbclass();
       DefaultTableModel tbmodel=(DefaultTableModel)ClassTimeSlots.getModel();
       tbmodel.setRowCount(0);
              ResultSet rs = dbclass.getMorningClassRooms();

           try{
               while(rs.next()) {


       String data[]={rs.getString("day"),rs.getString("time"),rs.getString("room"),rs.getString("roomType"),rs.getString("shift")};
               
           

           tbmodel.addRow(data);
           }
           morClassTimeSlotsCount=ClassTimeSlots.getRowCount();
           }catch(Exception e){
       System.out.println(e);
       }



        }
    
    
    
     public void Labs() {
    
    dbclass dbclass=new dbclass();
       
           ResultSet rs = dbclass.getMorningLabs();
        
        try{
            while(rs.next()) {
               
                
    String data[]={rs.getString("cname"),rs.getString("tname"),rs.getString("section"),rs.getString("ctype"),rs.getString("shift")};
                  
        DefaultTableModel tbmodel=(DefaultTableModel)Labs.getModel();
        
        tbmodel.addRow(data);
        }
         morLabsCount=Labs.getRowCount();
        }catch(Exception e){
    System.out.println(e);
    }
        
        
     }
 
 
    public void LabTimeSlots() {

       dbclass dbclass=new dbclass();
       DefaultTableModel tbmodel=(DefaultTableModel)LabTimeSlots.getModel();
       tbmodel.setRowCount(0);

              ResultSet rs = dbclass.getMorningLabRooms();

           try{
               while(rs.next()) {


       String data[]={rs.getString("day"),rs.getString("time"),rs.getString("room"),rs.getString("RoomType"),rs.getString("shift")};
               
          
           tbmodel.addRow(data);
           }
           morLabTimeSlotsCount=LabTimeSlots.getRowCount();
           }catch(Exception e){
       System.out.println(e);
       }


        }
    
    
    
 ///////////////////For Evening Classes.//////////////////////////////////////
    
    public void Classes1() {
    
    dbclass dbclass=new dbclass();
       
           ResultSet rs = dbclass.getEveningClasses();
        
        try{
            while(rs.next()) {
               
                
    String data[]={rs.getString("cname"),rs.getString("tname"),rs.getString("section"),rs.getString("ctype"),rs.getString("shift")};
                  
        DefaultTableModel tbmodel=(DefaultTableModel)Classes1.getModel();
        
        tbmodel.addRow(data);
        }
        eveClassesCount=Classes1.getRowCount();
           
        }catch(Exception e){
    System.out.println(e);
    }
        
        
     }
 
 
    public void ClassTimeSlots1() {

       dbclass dbclass=new dbclass();
       DefaultTableModel tbmodel=(DefaultTableModel)ClassTimeSlots1.getModel();
       tbmodel.setRowCount(0);
              ResultSet rs = dbclass.getEveningClassRooms();

           try{
               while(rs.next()) {


       String data[]={rs.getString("day"),rs.getString("time"),rs.getString("room"),rs.getString("roomType"),rs.getString("shift")};
               
           

           tbmodel.addRow(data);
           }
           eveClassTimeSlotsCount=ClassTimeSlots1.getRowCount();
           }catch(Exception e){
       System.out.println(e);
       }



        }
    
    
    
     public void Labs1() {
    
    dbclass dbclass=new dbclass();
       
           ResultSet rs = dbclass.getEveningLabs();
        
        try{
            while(rs.next()) {
               
                
    String data[]={rs.getString("cname"),rs.getString("tname"),rs.getString("section"),rs.getString("ctype"),rs.getString("shift")};
                  
        DefaultTableModel tbmodel=(DefaultTableModel)Labs1.getModel();
        
        tbmodel.addRow(data);
        }
         eveLabsCount=Labs1.getRowCount();
        }catch(Exception e){
    System.out.println(e);
    }
        
        
     }
 
 
    public void LabTimeSlots1() {

       dbclass dbclass=new dbclass();
       DefaultTableModel tbmodel=(DefaultTableModel)LabTimeSlots1.getModel();
       tbmodel.setRowCount(0);

              ResultSet rs = dbclass.getEveningLabRooms();

           try{
               while(rs.next()) {


       String data[]={rs.getString("day"),rs.getString("time"),rs.getString("room"),rs.getString("RoomType"),rs.getString("shift")};
               
          
           tbmodel.addRow(data);
           }
           eveLabTimeSlotsCount=LabTimeSlots1.getRowCount();
           }catch(Exception e){
       System.out.println(e);
       }


        }
    
    
    
    
    
   
   
    public timetable() {
       initComponents();
       AutoCompleteDecorator.decorate(ttcombo);
       
        TableFilterHeader filterHeader = new TableFilterHeader(jTable1, AutoChoices.ENABLED);
        filterHeader.setPosition(TableFilterHeader.Position.TOP);
        filterHeader.setBackground(Color.WHITE);
        
         TableFilterHeader filterHeader1 = new TableFilterHeader(IgnoreDataTable, AutoChoices.ENABLED);
        filterHeader1.setPosition(TableFilterHeader.Position.TOP);
        filterHeader1.setBackground(Color.WHITE);
        
       timetable.setVisible(true);
       ttTable.setVisible(true);
       dummy.setVisible(false);
       ignoredata.setVisible(false);
      
       
       
      
       
        DefaultTableModel table = (DefaultTableModel) jTable1.getModel();
        table.setRowCount(0);
       
        showrecord();
        showrecord1();
        /////Morning//////
        Classes();
        ClassTimeSlots();
        Labs();
        LabTimeSlots();
        ////Evening///////
        Classes1();
        ClassTimeSlots1();
        Labs1();
        LabTimeSlots1();
        
         dbclass dbclass = new dbclass();
       
     try { 
         ResultSet rs = dbclass.countclasses();
         rs.next();
         classes=rs.getInt(1);
        ResultSet rs1 = dbclass.counttimeslots();
         rs1.next();
         timeslots=rs1.getInt(1);
        
        
     } catch (SQLException ex) {
         System.out.println(ex);
     }
           
     timeslotes ts=new timeslotes();
     ts.showrecord1();
        msg1.setForeground(new Color(51,204,0));
        msg1.setText(ts.classfree+" Class Rooms Slots Are Free");
        
        msg2.setForeground(new Color(51,204,0));
        msg2.setText(ts.labfree+" Lab Slots Are Free");
        
     
     
       
    }
    
  
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main = new javax.swing.JPanel();
        timetable = new javax.swing.JPanel();
        ttTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        ignoredata = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        IgnoreDataTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        tlabs = new javax.swing.JSpinner();
        sclass = new javax.swing.JSpinner();
        slabs = new javax.swing.JSpinner();
        tclass = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        msg1 = new javax.swing.JLabel();
        msg2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        search2 = new com.raven.swing.SearchText();
        jLabel4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        ttcombo = new javax.swing.JComboBox<>();
        dummy = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Classes = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        ClassTimeSlots = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        Labs = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        LabTimeSlots = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        Classes1 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        Labs1 = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        ClassTimeSlots1 = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        LabTimeSlots1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(242, 242, 242));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        main.setBackground(new java.awt.Color(255, 255, 255));

        timetable.setBackground(new java.awt.Color(242, 242, 242));
        timetable.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ttTable.setBackground(new java.awt.Color(242, 242, 242));
        ttTable.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course Title", "Teacher", "Section", "Day", "Time", "Room", "Room Type"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        ttTable.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 880, 400));

        timetable.add(ttTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 890, 400));

        ignoredata.setBackground(new java.awt.Color(242, 242, 242));
        ignoredata.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jScrollPane10.setViewportView(IgnoreDataTable);

        ignoredata.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 11, 890, 380));

        timetable.add(ignoredata, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 400));

        jPanel1.setBackground(new java.awt.Color(242, 242, 242));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tlabs.setModel(new javax.swing.SpinnerNumberModel(1, 1, 7, 1));
        jPanel1.add(tlabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 50, -1));

        sclass.setModel(new javax.swing.SpinnerNumberModel(2, 1, 7, 1));
        jPanel1.add(sclass, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 50, -1));

        slabs.setModel(new javax.swing.SpinnerNumberModel(1, 1, 7, 1));
        jPanel1.add(slabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 50, -1));

        tclass.setModel(new javax.swing.SpinnerNumberModel(2, 1, 7, 1));
        jPanel1.add(tclass, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 50, -1));

        jLabel3.setText("Daily Section Labs");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 140, 20));

        jLabel5.setText("Daily Teacher Classes");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, 20));

        jLabel6.setText("Daily Teacher Labs");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 140, 20));

        jLabel7.setText("Daily Section Classes");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 140, 20));

        timetable.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 530, 470, 70));

        jButton3.setBackground(new java.awt.Color(0, 102, 51));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("GENRATE");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        timetable.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 560, 150, -1));

        msg1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        msg1.setForeground(new java.awt.Color(204, 0, 0));
        timetable.add(msg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, 220, 20));

        msg2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        msg2.setForeground(new java.awt.Color(204, 0, 0));
        timetable.add(msg2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 220, 21));

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

        timetable.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 8, -1, -1));

        jLabel4.setFont(new java.awt.Font("Algerian", 1, 24)); // NOI18N
        jLabel4.setText("time table");
        timetable.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(367, 68, -1, -1));

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
        timetable.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 520, 148, -1));

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
        timetable.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(758, 39, 148, -1));

        ttcombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TimeTable", "Ignored Classes" }));
        ttcombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ttcomboItemStateChanged(evt);
            }
        });
        timetable.add(ttcombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 150, -1));

        dummy.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Classes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "cname", "tname", "section", "ctype", "shift"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(Classes);

        dummy.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, 110));

        ClassTimeSlots.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "day", "time", "room", "roomtype", "shift"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(ClassTimeSlots);

        dummy.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 410, 110));

        Labs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "cname", "tname", "section", "ctype", "shift"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(Labs);

        dummy.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, 110));

        LabTimeSlots.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "day", "time", "room", "roomtype", "shift"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(LabTimeSlots);

        dummy.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, 410, 110));

        Classes1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "cname", "tname", "section", "ctype", "shift"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane6.setViewportView(Classes1);

        dummy.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, 110));

        Labs1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "cname", "tname", "section", "ctype", "shift"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane7.setViewportView(Labs1);

        dummy.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, -1, 110));

        ClassTimeSlots1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "day", "time", "room", "roomtype", "shift"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane8.setViewportView(ClassTimeSlots1);

        dummy.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 310, 410, 110));

        LabTimeSlots1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "day", "time", "room", "roomtype", "shift"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane9.setViewportView(LabTimeSlots1);

        dummy.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 430, 410, 110));

        javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(main);
        main.setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
            .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(dummy, javax.swing.GroupLayout.PREFERRED_SIZE, 930, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(timetable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
            .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(dummy, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(timetable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 620));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dbclass dbclass = new dbclass();
     
        
        teacherDailyClasses=(int) tclass.getValue();
        teacherDailyLabs=(int) tlabs.getValue();
        sectionDailyClasses=(int) sclass.getValue();
        sectionDailyLabs=(int) slabs.getValue();
        
       dbclass.deletetimeslotes();
       dbclass.inserttimeslotes();
       dbclass.deletetimetable();
       dbclass.deleteIgnoreTTdata();
       
         DefaultTableModel table = (DefaultTableModel) jTable1.getModel();
        
         ////////////Table 1 to 4 for Morning Classes data////////////////////
        DefaultTableModel table1 = (DefaultTableModel) Classes.getModel();
        table1.setRowCount(0);
        Classes();
        
        DefaultTableModel table2 = (DefaultTableModel) ClassTimeSlots.getModel();
        table2.setRowCount(0);
        ClassTimeSlots();
        
        DefaultTableModel table3 = (DefaultTableModel) Labs.getModel();
        table3.setRowCount(0);
        Labs();
        
        DefaultTableModel table4 = (DefaultTableModel) LabTimeSlots.getModel();
        table4.setRowCount(0);
        LabTimeSlots();
        
        ///////////////////table 5 to 8 for Evening Classes data//////////////////////////
        DefaultTableModel table5 = (DefaultTableModel) Classes1.getModel();
        table5.setRowCount(0);
        Classes1();
        
        DefaultTableModel table6 = (DefaultTableModel) ClassTimeSlots1.getModel();
        table6.setRowCount(0);
        ClassTimeSlots1();
        
        DefaultTableModel table7 = (DefaultTableModel) Labs1.getModel();
        table7.setRowCount(0);
        Labs1();
        
        DefaultTableModel table8 = (DefaultTableModel) LabTimeSlots1.getModel();
        table8.setRowCount(0);
        LabTimeSlots1();
        
        
       
     try { 
         ResultSet rs = dbclass.countclasses();
         rs.next();
         classes=rs.getInt(1);
        ResultSet rs1 = dbclass.counttimeslots();
         rs1.next();
         timeslots=rs1.getInt(1);
        
        
     } catch (SQLException ex) {
         System.out.println(ex);
     }
           
       
     
        
        
        /////////////////////////////Morning Classes///////////////////////////////////////
        TableModel model1=Classes.getModel();
        TableModel model2=ClassTimeSlots.getModel();
        TableModel model3=Labs.getModel();
        TableModel model4=LabTimeSlots.getModel();
        try{
        if(morClassesCount>=morClassTimeSlotsCount)
        {   
            
            for(int i=0;i<morClassTimeSlotsCount;i++)
            {
             ClassTimeSlots(); 
             int k=0;// this variable is used to change time slot if clash found in timetable
             int j=0;
            
                 try{
                      while(j<1){
                          
             ResultSet rs0=dbclass.chkRoomSameTimeClash(model2.getValueAt(k, 0).toString(), model2.getValueAt(k, 1).toString(), model2.getValueAt(k, 2).toString());
             boolean rsRoom=rs0.isBeforeFirst();
             ResultSet rs=dbclass.chkSecSameTimeClash(model1.getValueAt(i, 2).toString(), model2.getValueAt(k, 0).toString(), model2.getValueAt(k, 1).toString());
             boolean rsSec=rs.isBeforeFirst();
             ResultSet rs1=dbclass.chkTecherSameTimeClash(model1.getValueAt(i, 1).toString(), model2.getValueAt(k, 0).toString(), model2.getValueAt(k, 1).toString());
             boolean rsTech=rs1.isBeforeFirst();
             ResultSet rs2=dbclass.countTeacherDailyClasses(model1.getValueAt(i, 1).toString(),model2.getValueAt(k, 0).toString());
             rs2.next();
             int countTeachClasses=rs2.getInt(1);
             ResultSet rs3=dbclass.countSectionDailyClasses(model1.getValueAt(i, 2).toString(),model2.getValueAt(k, 0).toString());
             rs3.next();
             int countSecClasses=rs3.getInt(1);
             j++;
             ////////////////////////////////
//             ResultSet rs4=dbclass.chkTeacherWishTime(model1.getValueAt(i, 0).toString());
//             boolean rsWishTime=rs4.isBeforeFirst();
//                    j++;
//                  if(rsWishTime==true){
//                       System.out.println("Wishhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
//                        while(rs4.next())
//                        {
//                            System.out.println("Wishhhh2222222222hhhhhhhhh");
//                            if(rs4.getString("time")==model2.getValueAt(k, 1).toString())
//                            {
//                                if( rsRoom==true || rsSec==true || rsTech==true || countTeachClasses==teacherDailyClasses || countSecClasses==sectionDailyClasses)
//                              {
//                                  
//
//                                  System.out.println("Clash");
//                                  if(rs4.isLast()==true)
//                                  {
//                                  k++;
//                                  j--;
//                                      System.out.println("XXXXXXX"+k);
//                                  }
//                                  
//                                   if(k>=model2.getRowCount()){
//                                    dbclass.insertignoreTTdata(model1.getValueAt(i, 0).toString(), model1.getValueAt(i, 1).toString(), model1.getValueAt(i, 2).toString(), model1.getValueAt(i, 3).toString(), model1.getValueAt(i, 4).toString());
//                                   j++;
//                                   System.out.println("XXXXXXX"+k);
//                                   break;
//                                   }
//                              } 
//                                else
//                                {
//                                 dbclass.inserttimetable(model1.getValueAt(i, 0).toString(), model1.getValueAt(i, 1).toString(), model1.getValueAt(i, 2).toString(), model2.getValueAt(k, 0).toString(), model2.getValueAt(k, 1).toString(), model2.getValueAt(k, 2).toString(), model2.getValueAt(k, 3).toString(),model2.getValueAt(k, 4).toString());
//                                 System.out.println("XXXXXXX"+k);
//                                 break;
//                                }
//                            }
//                            else
//                                {
//                                    if(rs4.isLast()==true)
//                                    {
//
//                                        k++;
//                                    }
//
//                                }
//                            
//                        }
//                  
//                  
//                  }
//                  else{
//                    
//                   if(rsRoom==true || rsSec==true || rsTech==true || countTeachClasses==teacherDailyClasses || countSecClasses==sectionDailyClasses)
//                    {
//                        
//                       System.out.println("Clash");
//                        k++;
//                        j--;
//                        if(k>=model2.getRowCount()){
//                         dbclass.insertignoreTTdata(model1.getValueAt(i, 0).toString(), model1.getValueAt(i, 1).toString(), model1.getValueAt(i, 2).toString(), model1.getValueAt(i, 3).toString(), model1.getValueAt(i, 4).toString());
//                        j++;
//                        }
//                    } 
//                    else
//                    {
//                     dbclass.inserttimetable(model1.getValueAt(i, 0).toString(), model1.getValueAt(i, 1).toString(), model1.getValueAt(i, 2).toString(), model2.getValueAt(k, 0).toString(), model2.getValueAt(k, 1).toString(), model2.getValueAt(k, 2).toString(), model2.getValueAt(k, 3).toString(),model2.getValueAt(k, 4).toString());
//           
//                    }
//                      }
///////////////////////////////////////////////////////
            if(rsRoom==true || rsSec==true || rsTech==true || countTeachClasses==teacherDailyClasses || countSecClasses==sectionDailyClasses)
                    {
                        
                       System.out.println("Clash");
                        k++;
                        j--;
                        if(k>=model2.getRowCount()){
                         dbclass.insertignoreTTdata(model1.getValueAt(i, 0).toString(), model1.getValueAt(i, 1).toString(), model1.getValueAt(i, 2).toString(), model1.getValueAt(i, 3).toString(), model1.getValueAt(i, 4).toString());
                        j++;
                        }
                    } 
                    else
                    {
                     dbclass.inserttimetable(model1.getValueAt(i, 0).toString(), model1.getValueAt(i, 1).toString(), model1.getValueAt(i, 2).toString(), model2.getValueAt(k, 0).toString(), model2.getValueAt(k, 1).toString(), model2.getValueAt(k, 2).toString(), model2.getValueAt(k, 3).toString(),model2.getValueAt(k, 4).toString());

                    }
                }
                 }
                catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
            table.setRowCount(0);
            showrecord();
        }
        else if(morClassesCount<=morClassTimeSlotsCount)
        {
           
            for(int i=0;i<morClassesCount;i++)
            {
             ClassTimeSlots();
           int k=0;
           int j=0;   
//                jProgressBar1.setValue((i*100/ClassesCount));
                 try{
                      while(j<1){
             ResultSet rs0=dbclass.chkRoomSameTimeClash(model2.getValueAt(k, 0).toString(), model2.getValueAt(k, 1).toString(), model2.getValueAt(k, 2).toString());
             boolean rsRoom=rs0.isBeforeFirst();
             ResultSet rs=dbclass.chkSecSameTimeClash(model1.getValueAt(i, 2).toString(), model2.getValueAt(k, 0).toString(), model2.getValueAt(k, 1).toString());
             boolean rsSec=rs.isBeforeFirst();
             ResultSet rs1=dbclass.chkTecherSameTimeClash(model1.getValueAt(i, 1).toString(), model2.getValueAt(k, 0).toString(), model2.getValueAt(k, 1).toString());
             boolean rsTech=rs1.isBeforeFirst();
             ResultSet rs2=dbclass.countTeacherDailyClasses(model1.getValueAt(i, 1).toString(),model2.getValueAt(k, 0).toString());
             rs2.next();
             int countTeachClasses=rs2.getInt(1);
             ResultSet rs3=dbclass.countSectionDailyClasses(model1.getValueAt(i, 2).toString(),model2.getValueAt(k, 0).toString());
             rs3.next();
             int countSecClasses=rs3.getInt(1);
             j++;
             //////////////////////////////////
//             ResultSet rs4=dbclass.chkTeacherWishTime(model1.getValueAt(i, 0).toString());
//             boolean rsWishTime=rs4.isBeforeFirst();
//                    j++;
//                  if(rsWishTime==true){
//                      System.out.println("Wishhhhhhhhhhhhhhhhhhh");
//                        while(rs4.next())
//                        {
//                            System.out.println("Wishhhh222222222");
//                            if(rs4.getString("time")==model2.getValueAt(k, 1).toString())
//                            {
//                                if( rsRoom==true || rsSec==true || rsTech==true || countTeachClasses==teacherDailyClasses || countSecClasses==sectionDailyClasses)
//                              {
//                                  
//                                  
//                                  System.out.println("Clash");
//                                  if(rs4.isLast()==true)
//                                  {
//                                  k++;
//                                  j--;
//                                   System.out.println("XXXXXXX"+k);
//                                  }
//                                  
//                                   if(k>=model2.getRowCount()){
//                                    dbclass.insertignoreTTdata(model1.getValueAt(i, 0).toString(), model1.getValueAt(i, 1).toString(), model1.getValueAt(i, 2).toString(), model1.getValueAt(i, 3).toString(), model1.getValueAt(i, 4).toString());
//                                   j++;
//                                   System.out.println("XXXXXXX"+k);
//                                   break;
//                                   }
//                              } 
//                                else
//                                {
//                                 dbclass.inserttimetable(model1.getValueAt(i, 0).toString(), model1.getValueAt(i, 1).toString(), model1.getValueAt(i, 2).toString(), model2.getValueAt(k, 0).toString(), model2.getValueAt(k, 1).toString(), model2.getValueAt(k, 2).toString(), model2.getValueAt(k, 3).toString(),model2.getValueAt(k, 4).toString());
//                                System.out.println("XXXXXXX"+k);
//                                 break;
//                                }
//                            }
//                            else
//                                {
//                                    if(rs4.isLast()==true)
//                                    {
//
//                                        k++;
//                                    }
//
//                                }
//                        }
//                  
//                  
//                  }
//                  else{
//                    if(rsRoom==true || rsSec==true || rsTech==true || countTeachClasses==teacherDailyClasses || countSecClasses==sectionDailyClasses)
//                    {
//                         
//                       System.out.println("Clash");
//                        k++;
//                        j--;
//                        if(k>=model2.getRowCount()){
//                        dbclass.insertignoreTTdata(model1.getValueAt(i, 0).toString(), model1.getValueAt(i, 1).toString(), model1.getValueAt(i, 2).toString(), model1.getValueAt(i, 3).toString(), model1.getValueAt(i, 4).toString());
//                        j++;
//                        }
//                    }  
//                    else
//                    {
//                     dbclass.inserttimetable(model1.getValueAt(i, 0).toString(), model1.getValueAt(i, 1).toString(), model1.getValueAt(i, 2).toString(), model2.getValueAt(k, 0).toString(), model2.getValueAt(k, 1).toString(), model2.getValueAt(k, 2).toString(), model2.getValueAt(k, 3).toString(),model2.getValueAt(k, 4).toString());
//           
//                    }
//                  }
//////////////////////////////////////////////////////////////
                    if(rsRoom==true || rsSec==true || rsTech==true || countTeachClasses==teacherDailyClasses || countSecClasses==sectionDailyClasses)
                    {
                        
                       System.out.println("Clash");
                        k++;
                        j--;
                        if(k>=model2.getRowCount()){
                         dbclass.insertignoreTTdata(model1.getValueAt(i, 0).toString(), model1.getValueAt(i, 1).toString(), model1.getValueAt(i, 2).toString(), model1.getValueAt(i, 3).toString(), model1.getValueAt(i, 4).toString());
                        j++;
                        }
                    } 
                    else
                    {
                     dbclass.inserttimetable(model1.getValueAt(i, 0).toString(), model1.getValueAt(i, 1).toString(), model1.getValueAt(i, 2).toString(), model2.getValueAt(k, 0).toString(), model2.getValueAt(k, 1).toString(), model2.getValueAt(k, 2).toString(), model2.getValueAt(k, 3).toString(),model2.getValueAt(k, 4).toString());
           
                    }
                }
                 }
                catch (SQLException ex) {
                    System.out.println(ex);
                }
                 
            }
            table.setRowCount(0);
            showrecord();
        }
        else
        {
        
        }
        
        if(morLabsCount>=morLabTimeSlotsCount)
        {       
            for(int i=0;i<morLabTimeSlotsCount;i++)
            {
               LabTimeSlots();
               int k=0;
               int j=0; 
              
               
             try{
                 while(j<1){
                    ResultSet rs0=dbclass.chkRoomSameTimeClash(model4.getValueAt(k, 0).toString(), model4.getValueAt(k, 1).toString(), model4.getValueAt(k, 2).toString());
                    boolean rsRoom=rs0.isBeforeFirst();
                    ResultSet rs=dbclass.chkSecSameTimeClash(model3.getValueAt(i, 2).toString(), model4.getValueAt(k, 0).toString(), model4.getValueAt(k, 1).toString());
                    boolean rsSec=rs.isBeforeFirst();
                    ResultSet rs1=dbclass.chkTecherSameTimeClash(model3.getValueAt(i, 1).toString(), model4.getValueAt(k, 0).toString(), model4.getValueAt(k, 1).toString());
                    boolean rsTech=rs1.isBeforeFirst(); 
                    ResultSet rs2=dbclass.countTeacherDailyLabs(model3.getValueAt(i, 1).toString(),model4.getValueAt(k, 0).toString());
                    rs2.next();
                    int countTeachLabs=rs2.getInt(1);
                    ResultSet rs3=dbclass.countSectionDailyLabs(model3.getValueAt(i, 2).toString(),model4.getValueAt(k, 0).toString());
                    rs3.next();
                    int countSecLabs=rs3.getInt(1);
                    j++;
                    if(rsRoom==true || rsSec==true || rsTech==true || countTeachLabs==teacherDailyLabs || countSecLabs==sectionDailyLabs)
                    {
                        System.out.println("Clash");
                        k++;
                        j--;
                        if(k>=model4.getRowCount()){
                        dbclass.insertignoreTTdata(model3.getValueAt(i, 0).toString(), model3.getValueAt(i, 1).toString(), model3.getValueAt(i, 2).toString(), model3.getValueAt(i, 3).toString(), model3.getValueAt(i, 4).toString());
                        j++;
                        }
                        
                    }  
                    else
                    {
                     dbclass.inserttimetable(model3.getValueAt(i, 0).toString(), model3.getValueAt(i, 1).toString(), model3.getValueAt(i, 2).toString(), model4.getValueAt(k, 0).toString(), model4.getValueAt(k, 1).toString(), model4.getValueAt(k, 2).toString(), model4.getValueAt(k, 3).toString(),model4.getValueAt(k, 4).toString());
                    
                    }
                  } 
                }
                catch (SQLException ex) {
                    System.out.println(ex);
                }
              
            }
            table.setRowCount(0);
            showrecord();
        }
        else if(morLabsCount<=morLabTimeSlotsCount)
        {
           
            for(int i=0;i<morLabsCount;i++)
            {
               LabTimeSlots();
               int k=0;
               int j=0; 
                
               
             try{
                 while(j<1){
                    ResultSet rs0=dbclass.chkRoomSameTimeClash(model4.getValueAt(k, 0).toString(), model4.getValueAt(k, 1).toString(), model4.getValueAt(k, 2).toString());
                    boolean rsRoom=rs0.isBeforeFirst();
                    ResultSet rs=dbclass.chkSecSameTimeClash(model3.getValueAt(i, 2).toString(), model4.getValueAt(k, 0).toString(), model4.getValueAt(k, 1).toString());
                    boolean rsSec=rs.isBeforeFirst();
                    ResultSet rs1=dbclass.chkTecherSameTimeClash(model3.getValueAt(i, 1).toString(), model4.getValueAt(k, 0).toString(), model4.getValueAt(k, 1).toString());
                    boolean rsTech=rs1.isBeforeFirst(); 
                    ResultSet rs2=dbclass.countTeacherDailyLabs(model3.getValueAt(i, 1).toString(),model4.getValueAt(k, 0).toString());
                    rs2.next();
                    int countTeachLabs=rs2.getInt(1);
                    ResultSet rs3=dbclass.countSectionDailyLabs(model3.getValueAt(i, 2).toString(),model4.getValueAt(k, 0).toString());
                    rs3.next();
                    int countSecLabs=rs3.getInt(1);
                    j++;
                    if(rsRoom==true || rsSec==true || rsTech==true || countTeachLabs==teacherDailyLabs || countSecLabs==sectionDailyLabs)
                    {
                        
                       System.out.println("Clash");
                        k++;
                        j--;
                        if(k>=model4.getRowCount()){
                         dbclass.insertignoreTTdata(model3.getValueAt(i, 0).toString(), model3.getValueAt(i, 1).toString(), model3.getValueAt(i, 2).toString(), model3.getValueAt(i, 3).toString(), model3.getValueAt(i, 4).toString());
                        j++;
                        }
                    } 
                    else
                    {
                     dbclass.inserttimetable(model3.getValueAt(i, 0).toString(), model3.getValueAt(i, 1).toString(), model3.getValueAt(i, 2).toString(), model4.getValueAt(k, 0).toString(), model4.getValueAt(k, 1).toString(), model4.getValueAt(k, 2).toString(), model4.getValueAt(k, 3).toString(),model4.getValueAt(k, 4).toString());
             
                    }
                  } 
                }
                catch (SQLException ex) {
                    System.out.println(ex);
                }
              
            }
            table.setRowCount(0);
            showrecord();
        }
        else
        {
        
        }
     }catch(Exception ex)
             {
                 System.out.println(ex);
              JOptionPane.showMessageDialog(null,ex,"error In Morning Classes",JOptionPane.INFORMATION_MESSAGE);
             }
        
        
        //////////////For Evening Classes///////////////////////////
        
        
        TableModel model5=Classes1.getModel();
        TableModel model6=ClassTimeSlots1.getModel();
        TableModel model7=Labs1.getModel();
        TableModel model8=LabTimeSlots1.getModel();
        try{
        if(eveClassesCount>=eveClassTimeSlotsCount)
        {   
            
            for(int i=0;i<eveClassTimeSlotsCount;i++)
            {
             ClassTimeSlots1(); 
             int k=0;// this variable is used to change time slot if clash found in timetable
             int j=0;
            
//                jProgressBar1.setValue((i*100/ClassTimeSlotsCount));
                 try{
                      while(j<1){
                          
             ResultSet rs0=dbclass.chkRoomSameTimeClash(model6.getValueAt(k, 0).toString(), model6.getValueAt(k, 1).toString(), model6.getValueAt(k, 2).toString());
             boolean rsRoom=rs0.isBeforeFirst();
             ResultSet rs=dbclass.chkSecSameTimeClash(model5.getValueAt(i, 2).toString(), model6.getValueAt(k, 0).toString(), model6.getValueAt(k, 1).toString());
             boolean rsSec=rs.isBeforeFirst();
             ResultSet rs1=dbclass.chkTecherSameTimeClash(model5.getValueAt(i, 1).toString(), model6.getValueAt(k, 0).toString(), model6.getValueAt(k, 1).toString());
             boolean rsTech=rs1.isBeforeFirst();
             ResultSet rs2=dbclass.countTeacherDailyClasses(model5.getValueAt(i, 1).toString(),model6.getValueAt(k, 0).toString());
             rs2.next();
             int countTeachClasses=rs2.getInt(1);
             ResultSet rs3=dbclass.countSectionDailyClasses(model5.getValueAt(i, 2).toString(),model6.getValueAt(k, 0).toString());
             rs3.next();
             int countSecClasses=rs3.getInt(1);
                    j++;
                  
                    if(rsRoom==true || rsSec==true || rsTech==true || countTeachClasses==teacherDailyClasses || countSecClasses==sectionDailyClasses)
                    {

                       System.out.println("Clash");
                        k++;
                        j--;
                        if(k>=model6.getRowCount()){
                         dbclass.insertignoreTTdata(model5.getValueAt(i, 0).toString(), model5.getValueAt(i, 1).toString(), model5.getValueAt(i, 2).toString(), model5.getValueAt(i, 3).toString(), model5.getValueAt(i, 4).toString());
                        j++;
                        }
                    } 
                    else
                    {
                     dbclass.inserttimetable(model5.getValueAt(i, 0).toString(), model5.getValueAt(i, 1).toString(), model5.getValueAt(i, 2).toString(), model6.getValueAt(k, 0).toString(), model6.getValueAt(k, 1).toString(), model6.getValueAt(k, 2).toString(), model6.getValueAt(k, 3).toString(),model6.getValueAt(k, 4).toString());
           
                    }
                }
                 }
                catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
            table.setRowCount(0);
            showrecord();
        }
        else if(eveClassesCount<=eveClassTimeSlotsCount)
        {
           
            for(int i=0;i<eveClassesCount;i++)
            {
             ClassTimeSlots1();
           int k=0;
           int j=0;
//                jProgressBar1.setValue((i*100/ClassesCount));
                 try{
                      while(j<1){
             ResultSet rs0=dbclass.chkRoomSameTimeClash(model6.getValueAt(k, 0).toString(), model6.getValueAt(k, 1).toString(), model6.getValueAt(k, 2).toString());
             boolean rsRoom=rs0.isBeforeFirst();
             ResultSet rs=dbclass.chkSecSameTimeClash(model5.getValueAt(i, 2).toString(), model6.getValueAt(k, 0).toString(), model6.getValueAt(k, 1).toString());
             boolean rsSec=rs.isBeforeFirst();
             ResultSet rs1=dbclass.chkTecherSameTimeClash(model5.getValueAt(i, 1).toString(), model6.getValueAt(k, 0).toString(), model6.getValueAt(k, 1).toString());
             boolean rsTech=rs1.isBeforeFirst();
             ResultSet rs2=dbclass.countTeacherDailyClasses(model5.getValueAt(i, 1).toString(),model6.getValueAt(k, 0).toString());
             rs2.next();
             int countTeachClasses=rs2.getInt(1);
             ResultSet rs3=dbclass.countSectionDailyClasses(model5.getValueAt(i, 2).toString(),model6.getValueAt(k, 0).toString());
             rs3.next();
             int countSecClasses=rs3.getInt(1);
                    j++;
                  
                    if(rsRoom==true || rsSec==true || rsTech==true || countTeachClasses==teacherDailyClasses || countSecClasses==sectionDailyClasses)
                    {

                       System.out.println("Clash");
                        k++;
                        j--;
                        if(k>=model6.getRowCount()){
                        dbclass.insertignoreTTdata(model5.getValueAt(i, 0).toString(), model5.getValueAt(i, 1).toString(), model5.getValueAt(i, 2).toString(), model5.getValueAt(i, 3).toString(), model5.getValueAt(i, 4).toString());
                        j++;
                        }
                    } 
                    else
                    {
                     dbclass.inserttimetable(model5.getValueAt(i, 0).toString(), model5.getValueAt(i, 1).toString(), model5.getValueAt(i, 2).toString(), model6.getValueAt(k, 0).toString(), model6.getValueAt(k, 1).toString(), model6.getValueAt(k, 2).toString(), model6.getValueAt(k, 3).toString(),model6.getValueAt(k, 4).toString());
                    }
                }
                 }
                catch (SQLException ex) {
                    System.out.println(ex);
                }
                 
            }
            table.setRowCount(0);
            showrecord();
        }
        else
        {
        
        }
        
        if(eveLabsCount>=eveLabTimeSlotsCount)
        {   
            
            for(int i=0;i<eveLabTimeSlotsCount;i++)
            {
             LabTimeSlots1(); 
             int k=0;// this variable is used to change time slot if clash found in timetable
             int j=0;
            
//                jProgressBar1.setValue((i*100/ClassTimeSlotsCount));
                 try{
                      while(j<1){
             ResultSet rs0=dbclass.chkRoomSameTimeClash(model8.getValueAt(k, 0).toString(), model8.getValueAt(k, 1).toString(), model8.getValueAt(k, 2).toString());
             boolean rsRoom=rs0.isBeforeFirst();
             ResultSet rs=dbclass.chkSecSameTimeClash(model7.getValueAt(i, 2).toString(), model8.getValueAt(k, 0).toString(), model8.getValueAt(k, 1).toString());
             boolean rsSec=rs.isBeforeFirst();
             ResultSet rs1=dbclass.chkTecherSameTimeClash(model7.getValueAt(i, 1).toString(), model8.getValueAt(k, 0).toString(), model8.getValueAt(k, 1).toString());
             boolean rsTech=rs1.isBeforeFirst();
             ResultSet rs2=dbclass.countTeacherDailyClasses(model7.getValueAt(i, 1).toString(),model8.getValueAt(k, 0).toString());
             rs2.next();
             int countTeachClasses=rs2.getInt(1);
             ResultSet rs3=dbclass.countSectionDailyClasses(model7.getValueAt(i, 2).toString(),model8.getValueAt(k, 0).toString());
             rs3.next();
             int countSecClasses=rs3.getInt(1);
                    j++;
                  
                    if(rsRoom==true || rsSec==true || rsTech==true || countTeachClasses==teacherDailyClasses || countSecClasses==sectionDailyClasses)
                    {

                       System.out.println("Clash");
                        k++;
                        j--;
                        if(k>=model8.getRowCount()){
                        dbclass.insertignoreTTdata(model7.getValueAt(i, 0).toString(), model7.getValueAt(i, 1).toString(), model7.getValueAt(i, 2).toString(), model7.getValueAt(i, 3).toString(), model7.getValueAt(i, 4).toString());
                        j++;
                        }
                    } 
                    else
                    {
                     dbclass.inserttimetable(model7.getValueAt(i, 0).toString(), model7.getValueAt(i, 1).toString(), model7.getValueAt(i, 2).toString(), model8.getValueAt(k, 0).toString(), model8.getValueAt(k, 1).toString(), model8.getValueAt(k, 2).toString(), model8.getValueAt(k, 3).toString(),model8.getValueAt(k, 4).toString());
                    }
                }
                 }
                catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
            table.setRowCount(0);
            showrecord();
        }
        else if(eveLabsCount<=eveLabTimeSlotsCount)
        {
           
            for(int i=0;i<eveLabsCount;i++)
            {
                LabTimeSlots1();
           int k=0;
           int j=0;
                 try{
                      while(j<1){
             ResultSet rs0=dbclass.chkRoomSameTimeClash(model8.getValueAt(k, 0).toString(), model8.getValueAt(k, 1).toString(), model8.getValueAt(k, 2).toString());
             boolean rsRoom=rs0.isBeforeFirst();
             ResultSet rs=dbclass.chkSecSameTimeClash(model7.getValueAt(i, 2).toString(), model8.getValueAt(k, 0).toString(), model8.getValueAt(k, 1).toString());
             boolean rsSec=rs.isBeforeFirst();
             ResultSet rs1=dbclass.chkTecherSameTimeClash(model7.getValueAt(i, 1).toString(), model8.getValueAt(k, 0).toString(), model8.getValueAt(k, 1).toString());
             boolean rsTech=rs1.isBeforeFirst();
             ResultSet rs2=dbclass.countTeacherDailyClasses(model7.getValueAt(i, 1).toString(),model8.getValueAt(k, 0).toString());
             rs2.next();
             int countTeachClasses=rs2.getInt(1);
             ResultSet rs3=dbclass.countSectionDailyClasses(model7.getValueAt(i, 2).toString(),model8.getValueAt(k, 0).toString());
             rs3.next();
             int countSecClasses=rs3.getInt(1);
                    j++;
                  
                    if(rsRoom==true || rsSec==true || rsTech==true || countTeachClasses==teacherDailyClasses || countSecClasses==sectionDailyClasses)
                    {

                       System.out.println("Clash111");
                        k++;
                        j--;
                        if(k>=model8.getRowCount()){
                        dbclass.insertignoreTTdata(model7.getValueAt(i, 0).toString(), model7.getValueAt(i, 1).toString(), model7.getValueAt(i, 2).toString(), model7.getValueAt(i, 3).toString(), model7.getValueAt(i, 4).toString());
                        j++;
                        }
                    } 
                    else
                    {
                     dbclass.inserttimetable(model7.getValueAt(i, 0).toString(), model7.getValueAt(i, 1).toString(), model7.getValueAt(i, 2).toString(), model8.getValueAt(k, 0).toString(), model8.getValueAt(k, 1).toString(), model8.getValueAt(k, 2).toString(), model8.getValueAt(k, 3).toString(),model8.getValueAt(k, 4).toString());
                    }
                }
                 }
                catch (SQLException ex) {
                    System.out.println(ex);
                }
                 
            }
            table.setRowCount(0);
            showrecord();
        }
        else
        {
        
        }
        
     }catch(Exception ex)
             {
                 System.out.println(ex);
              JOptionPane.showMessageDialog(null,ex,"error In Evening Classes",JOptionPane.INFORMATION_MESSAGE);
             }
        
       timeslotes ts=new timeslotes();
       ts.showrecord1();
       
        msg1.setForeground(new Color(51,204,0));
        msg1.setText(ts.classfree+" Class Rooms Slots Are Free");
        
        msg2.setForeground(new Color(51,204,0));
        msg2.setText(ts.labfree+" Lab Slots Are Free");
        
     DefaultTableModel tbmodel=(DefaultTableModel)IgnoreDataTable.getModel();
     tbmodel.setRowCount(0);
     showrecord1();
        
       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void search2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search2KeyReleased

        DefaultTableModel table = (DefaultTableModel) jTable1.getModel();
        String sch = search2.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
        jTable1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(sch));
    }//GEN-LAST:event_search2KeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dbclass dbclass=new dbclass();
        
       DefaultTableModel table = (DefaultTableModel) jTable1.getModel();
       DefaultTableModel table1 = (DefaultTableModel) IgnoreDataTable.getModel();
     
       int check=JOptionPane.showConfirmDialog(null,"Do you really Want to Delete TimeTable","Delete TimeTable",JOptionPane.YES_NO_OPTION );
        if(check==0){
         dbclass.deletetimetable();
         table.setRowCount(0);
         
         dbclass.deleteIgnoreTTdata();
         table1.setRowCount(0);
         
         showrecord();
         showrecord1();
         ClassTimeSlots();
         LabTimeSlots();
         ClassTimeSlots1();
         LabTimeSlots1();
         
       timeslotes ts=new timeslotes();
       ts.showrecord1();
        msg1.setForeground(new Color(51,204,0));
        msg1.setText(ts.classfree+" Class Rooms Slots Are Free");
        
        msg2.setForeground(new Color(51,204,0));
        msg2.setText(ts.labfree+" Lab Slots Are Free");
     
        }
        else
        {
        
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
      exportToexcel();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void ttcomboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ttcomboItemStateChanged
        if(ttcombo.getSelectedItem().toString()=="TimeTable"){
            ignoredata.setVisible(false);
            ttTable.setVisible(true);
          

        }
        else if(ttcombo.getSelectedItem().toString()=="Ignored Classes"){
            ttTable.setVisible(false);
            ignoredata.setVisible(true);
           
            
        }
    }//GEN-LAST:event_ttcomboItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ClassTimeSlots;
    private javax.swing.JTable ClassTimeSlots1;
    private javax.swing.JTable Classes;
    private javax.swing.JTable Classes1;
    private javax.swing.JTable IgnoreDataTable;
    private javax.swing.JTable LabTimeSlots;
    private javax.swing.JTable LabTimeSlots1;
    private javax.swing.JTable Labs;
    private javax.swing.JTable Labs1;
    private javax.swing.JPanel dummy;
    private javax.swing.JPanel ignoredata;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel main;
    private javax.swing.JLabel msg1;
    private javax.swing.JLabel msg2;
    private javax.swing.JSpinner sclass;
    public com.raven.swing.SearchText search2;
    private javax.swing.JSpinner slabs;
    private javax.swing.JSpinner tclass;
    private javax.swing.JPanel timetable;
    private javax.swing.JSpinner tlabs;
    private javax.swing.JPanel ttTable;
    private javax.swing.JComboBox<String> ttcombo;
    // End of variables declaration//GEN-END:variables
}
