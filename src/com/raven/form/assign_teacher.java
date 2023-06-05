package com.raven.form;

import com.raven.model.StatusType;
import com.raven.swing.ScrollBar;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.lang.Thread;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import javax.swing.Timer;
import javax.swing.table.TableModel;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class assign_teacher extends javax.swing.JPanel {

   
    String tid,course_type,phone_num;
    String section1,section2,section3,section4;
    String cours1,course2,course3,course4;
    int chours;
    String c="Class";
    String l="Lab";
    String shift;
    
    //creating variables for delete data from database using jtable onclick event
    String dtid="Null"
          ,dtname="Null"
          ,dcourse="Null"
          ,dsection="Null";
          
    //creating varibles for increment or decrement in combo box
    int countCombo=0;
   
// Method for export Excel file 
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
        XSSFSheet ws1 = wb.createSheet();
       
        
        
        if(dataCombo.getSelectedItem().equals("Allocation Data"))
        {
        DefaultTableModel model1 = (DefaultTableModel)jTable1.getModel();
      
        TreeMap<String, Object[]> map1 = new TreeMap<>();
        
        
           for (int i = 0; i < model1.getRowCount(); i++) {
            
            map1.put(Integer.toString(i), new Object[]{
                model1.getValueAt(i, 0),
                model1.getValueAt(i, 1),
                model1.getValueAt(i, 2),
                model1.getValueAt(i, 3),
                model1.getValueAt(i, 4),
                model1.getValueAt(i, 5)
            }
            );
      
        }
           
         
        Set<String> id1 = map1.keySet();
        XSSFRow fRow1;
        int rowId1 = 1;  
        
        for (String Key : id1) {
            fRow1 = ws1.createRow(rowId1++);
            Object[] value = map1.get(Key);
            int cellId = 0;
            for (Object object : value) {
                XSSFCell cel = fRow1.createCell(cellId++);
                cel.setCellValue(object.toString());
            }
        }
        row = ws1.createRow(0);
        for (int c = 0; c < model1.getColumnCount(); c++) {
            cell = row.createCell(c);
            cell.setCellValue(model1.getColumnName(c));
        }
           
       
        
        try {
            FileOutputStream fos = new FileOutputStream(new File(path));
            wb.write(fos);
            JOptionPane.showMessageDialog(this, "Exported Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
      }
      else if(dataCombo.getSelectedItem().equals("Ignored Data"))
        {
        DefaultTableModel model1 = (DefaultTableModel)jTable2.getModel();
      
        TreeMap<String, Object[]> map1 = new TreeMap<>();
        
        
           for (int i = 0; i < model1.getRowCount(); i++) {
            
            map1.put(Integer.toString(i), new Object[]{
                model1.getValueAt(i, 0),
                model1.getValueAt(i, 1),
                model1.getValueAt(i, 2),
                model1.getValueAt(i, 3),
                model1.getValueAt(i, 4),
                model1.getValueAt(i, 5)
            }
            );
      
        }
           
         
        Set<String> id1 = map1.keySet();
        XSSFRow fRow1;
        int rowId1 = 1;  
        
        for (String Key : id1) {
            fRow1 = ws1.createRow(rowId1++);
            Object[] value = map1.get(Key);
            int cellId = 0;
            for (Object object : value) {
                XSSFCell cel = fRow1.createCell(cellId++);
                cel.setCellValue(object.toString());
            }
        }
        row = ws1.createRow(0);
        for (int c = 0; c < model1.getColumnCount(); c++) {
            cell = row.createCell(c);
            cell.setCellValue(model1.getColumnName(c));
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
     
     
     
  /////////Method for imort data from excel  
     
     public void importFromExcel() throws FileNotFoundException, IOException
 {
     DefaultTableModel tbmodel = (DefaultTableModel) jTable1.getModel();
     DefaultTableModel tbmodel1 = (DefaultTableModel) jTable2.getModel();
      ArrayList<String> data=new ArrayList<String>(); /// i create this Array list to store row data from excel sheet temprory
      
        JOptionPane.showMessageDialog(null, "Excel File Must Have "+ tbmodel.getColumnCount()+" Number Of Coulums And Data Should Be In Same Series as  Below Table", "Rules", JOptionPane.INFORMATION_MESSAGE);   
        
        JFileChooser fileChooser = new JFileChooser(); 
        fileChooser.showSaveDialog(this);
        File f = fileChooser.getSelectedFile();
        FileInputStream fis = new FileInputStream(f);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
       XSSFSheet sheet = workbook.getSheetAt(0); //// this line is used to get sheet from excel by sheet number
       
       int rowCount=sheet.getPhysicalNumberOfRows(); /// this line is used to get Number of total Rows in Sheet
       for(int i=0;i<rowCount;i++)
       {
          data.removeAll(data); /// this line i used to Delete Previous row data from  data Array list this  
          
           XSSFRow row=sheet.getRow(i); 
           int cellCount=row.getPhysicalNumberOfCells(); /// this line is used to get number of total cells in a particuler row 
           for(int j=0;j<cellCount;j++){
               XSSFCell cell=row.getCell(j); /// this line is used to selet cell from row and store cell variable 
               data.add(getCellValue(cell).toString()); // in this line i get data from cell and store in data Array list
               
           }
          
           //// Now below code is to insert data in data base and also check data before insert
           String name=data.get(0);
           String id=data.get(1);
           String section=data.get(2);
           String course=data.get(3);
           String c_type=data.get(4);
           String number=data.get(5);
           String skipData[]={name,id,section,course,c_type,number};
           dbclass insert = new dbclass();
           
            try {
                
               if(c_type.equals("Regular"))
               {
                   
                    ResultSet rs = insert.countCourseType(id,c_type);
                    rs.next();
                    if(rs.getInt(1)<4)
                    {
                         ResultSet rs2 = insert.checkDuplicateEntry(course,section);
                        
                         if(rs2.isBeforeFirst()==true){
                           JOptionPane.showMessageDialog(null,"dulicate entry "+ course + " Course is Already Assign to Other Teacher For Section " +  section,"dulicate entry",JOptionPane.WARNING_MESSAGE);
                           tbmodel1.addRow(skipData);
                           
                           
                        }
                        else{
                        insert.inserttassign(id, section,course,name,c_type,number);
                       
                           ResultSet rss = insert.chk_chours(course);
                           rss.next();
                             chours=rss.getInt("chours");
                            ResultSet rss1 = insert.get_sec_shift(section);
                            rss1.next();
                            shift=rss1.getString("shift");
                            if(chours==3)
                            {
                            insert.insertclass(course,name,section,c,shift);
                            insert.insertclass(course,name,section,c,shift);
                            }
                            else if(chours==4)
                            {
                            insert.insertclass(course,name,section,c,shift);
                            insert.insertclass(course,name,section,c,shift);
                            insert.insertclass(course,name,section,l,shift);
                            }
                            else
                            {
                            
                            }
                        
//                        JOptionPane.showMessageDialog(null, "data succesfully added", "data add", JOptionPane.INFORMATION_MESSAGE);
 
                        tbmodel.setRowCount(0);
                        showrecord();
                        }
                        
                    }
                    else
                    {
                    JOptionPane.showMessageDialog(null, "you can not Allocate Regular Course More then 4 To "+name, "Error Massege", JOptionPane.ERROR_MESSAGE);
                    tbmodel1.addRow(skipData);
                    }
                 
               }
               else if(c_type.equals("Visiting"))
               {
                   
                    ResultSet rs = insert.countCourseType(id,c_type);
                    rs.next();
                    if(rs.getInt(1)<3)
                    {
                         ResultSet rs2 = insert.checkDuplicateEntry(course,section);
                        
                         if(rs2.isBeforeFirst()==true){
                           JOptionPane.showMessageDialog(null,"dulicate entry "+ course + " Course is Already Assign to Other Teacher For Section " +  section,"dulicate entry",JOptionPane.WARNING_MESSAGE);
                           tbmodel1.addRow(skipData);
                        }
                        else{
                        insert.inserttassign(id, section,course,name,c_type,number);
                       
                           ResultSet rss = insert.chk_chours(course);
                           rss.next();
                             chours=rss.getInt("chours");
                             ResultSet rss1 = insert.get_sec_shift(section);
                            rss1.next();
                            shift=rss1.getString("shift");
                            if(chours==3)
                            {
                            insert.insertclass(course,name,section,c,shift);
                            insert.insertclass(course,name,section,c,shift);
                            }
                            else if(chours==4)
                            {
                            insert.insertclass(course,name,section,c,shift);
                            insert.insertclass(course,name,section,c,shift);
                            insert.insertclass(course,name,section,l,shift);
                            }
                            else
                            {
                            
                            }
                        
//                        JOptionPane.showMessageDialog(null, "data succesfully added", "data add", JOptionPane.INFORMATION_MESSAGE);
                      
                        tbmodel.setRowCount(0);
                        showrecord();
                        }
                        
                    }
                    else
                    {
                    JOptionPane.showMessageDialog(null, "you can not Allocate Visiting Course More then 3 To "+name, "Error Massege", JOptionPane.ERROR_MESSAGE);
                    tbmodel1.addRow(skipData);
                    }
                 
               }
               
            }catch(Exception ex)
            {
                System.out.println(ex);
            }
           
           
           
           
           
       }
       
        
       workbook.close(); // workbook close 
       fis.close();  // file Input Stream close
 }
 
 //////method to get cell data from excel /// this method is used in impot excel method
 public static String getCellValue(XSSFCell cell)
 {
    switch(cell.getCellType())
    {
        case NUMERIC:
            return String.valueOf(cell.getNumericCellValue());
        case BOOLEAN:
            return String.valueOf(cell.getBooleanCellValue());
        case STRING:
            return cell.getStringCellValue();
        default:
           return cell.getStringCellValue(); 
    }
 
 }
     
     
     
    
 


    public void showrecord() {

        dbclass dbclass = new dbclass();
        ResultSet rs = dbclass.gettassign();

        try {
            while (rs.next()) {
                String data[] = {rs.getString("tname"), rs.getString("tid"), rs.getString("tsec"), rs.getString("secCourse"),rs.getString("course_type"),rs.getString("phone_num")};

                DefaultTableModel tbmodel = (DefaultTableModel) jTable1.getModel();

                tbmodel.addRow(data);
            }
        } catch (Exception e) {
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

    public void seccombo() {

        dbclass dbclass = new dbclass();
        ResultSet rs2 = dbclass.getsec();

        try {
            while (rs2.next()) {
                String data = rs2.getString("batch");
                sec1.addItem(data);
                sec2.addItem(data);
                sec3.addItem(data);
                sec4.addItem(data);

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

   
   

    public assign_teacher() {
        initComponents();
        TableFilterHeader filterHeader = new TableFilterHeader(jTable1, AutoChoices.ENABLED);
        filterHeader.setPosition(TableFilterHeader.Position.TOP);
        filterHeader.setBackground(Color.WHITE);
        
        TableFilterHeader filterHeader1 = new TableFilterHeader(jTable2, AutoChoices.ENABLED);
        filterHeader1.setPosition(TableFilterHeader.Position.TOP);
        filterHeader1.setBackground(Color.WHITE);
       
        DefaultTableModel tbmodel = (DefaultTableModel) jTable1.getModel();
        tbmodel.setRowCount(0);
        showrecord();
        tcombo();
        seccombo();
      
        AutoCompleteDecorator.decorate(tcombo);
        AutoCompleteDecorator.decorate(sec1);
        AutoCompleteDecorator.decorate(sec2);
        AutoCompleteDecorator.decorate(sec3);
        AutoCompleteDecorator.decorate(sec4);
        AutoCompleteDecorator.decorate(Course1);
        AutoCompleteDecorator.decorate(Course2);
        AutoCompleteDecorator.decorate(Course3);
        AutoCompleteDecorator.decorate(Course4);
        AutoCompleteDecorator.decorate(dataCombo);
        
        datapanel.setVisible(true);
        skipdatapanel.setVisible(false);
        ctypecombo.setVisible(false);
        sec1.setVisible(false);
        sec2.setVisible(false);
        sec3.setVisible(false);
        sec4.setVisible(false);
        Course1.setVisible(false);
        Course2.setVisible(false);
        Course3.setVisible(false);
        Course4.setVisible(false);
        incButton.setVisible(false);
        decButton.setVisible(false);
        
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        tcombo = new javax.swing.JComboBox<>();
        sec1 = new javax.swing.JComboBox<>();
        Course1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        datapanel = new com.raven.swing.PanelBorder();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        skipdatapanel = new com.raven.swing.PanelBorder();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        search = new com.raven.swing.SearchText();
        rsearch = new javax.swing.JLabel();
        rtname = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        ctypecombo = new javax.swing.JComboBox<>();
        sec2 = new javax.swing.JComboBox<>();
        sec3 = new javax.swing.JComboBox<>();
        sec4 = new javax.swing.JComboBox<>();
        Course2 = new javax.swing.JComboBox<>();
        Course3 = new javax.swing.JComboBox<>();
        Course4 = new javax.swing.JComboBox<>();
        incButton = new javax.swing.JLabel();
        decButton = new javax.swing.JLabel();
        dataCombo = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(242, 242, 242));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Algerian", 1, 24)); // NOI18N
        jLabel4.setText("Course Allocation");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 270, -1));

        tcombo.setSelectedItem(null);
        tcombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tcomboActionPerformed(evt);
            }
        });
        add(tcombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 210, -1));

        sec1.setSelectedItem(null);
        sec1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sec1ActionPerformed(evt);
            }
        });
        add(sec1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 186, -1));

        add(Course1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, 186, -1));

        jLabel1.setFont(new java.awt.Font("Engravers MT", 1, 14)); // NOI18N
        jLabel1.setText("SECTIONS");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(193, 185, 128, 25));

        jLabel2.setFont(new java.awt.Font("Engravers MT", 1, 14)); // NOI18N
        jLabel2.setText("COURSES");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(433, 185, 128, 25));

        jSeparator1.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 185, 15, 180));

        datapanel.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Teacher Name", "Teacher Id", "Section", "Course", "course type", "Number"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout datapanelLayout = new javax.swing.GroupLayout(datapanel);
        datapanel.setLayout(datapanelLayout);
        datapanelLayout.setHorizontalGroup(
            datapanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, datapanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
                .addContainerGap())
        );
        datapanelLayout.setVerticalGroup(
            datapanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datapanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        add(datapanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 911, 240));

        skipdatapanel.setBackground(new java.awt.Color(255, 255, 255));
        skipdatapanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Teacher Name", "Teacher Id", "Section", "Course", "course type", "Number"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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

        skipdatapanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 36, 891, 184));

        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("This Data is Temprory Stored In Table If You Want To Save Permenent Export Excel File ");
        skipdatapanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 10, 692, 20));

        add(skipdatapanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 911, 240));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel5.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 10, 188, -1));

        jButton3.setBackground(new java.awt.Color(0, 102, 51));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("RESET");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 58, 188, -1));

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
        jPanel5.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 106, 188, -1));

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
        jPanel5.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 204, 188, -1));

        jButton6.setBackground(new java.awt.Color(0, 102, 0));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("IMPORT");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 252, 188, -1));

        jButton7.setBackground(new java.awt.Color(102, 0, 0));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("DELETE ALL");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 156, 188, -1));

        add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(691, 63, 210, 310));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/search.png"))); // NOI18N

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
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
            .addComponent(search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 9, -1, -1));

        rsearch.setForeground(new java.awt.Color(255, 0, 0));
        add(rsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(635, 39, 72, 13));

        rtname.setForeground(new java.awt.Color(204, 0, 0));
        add(rtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 86, 16));

        time.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        time.setForeground(new java.awt.Color(204, 0, 0));
        add(time, new org.netbeans.lib.awtextra.AbsoluteConstraints(665, 0, 223, 25));

        ctypecombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Regular", "Visiting" }));
        ctypecombo.setSelectedItem(null);
        ctypecombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctypecomboActionPerformed(evt);
            }
        });
        add(ctypecombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, 90, -1));

        sec2.setSelectedItem(null);
        sec2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sec2ActionPerformed(evt);
            }
        });
        add(sec2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 186, -1));

        sec3.setSelectedItem(null);
        sec3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sec3ActionPerformed(evt);
            }
        });
        add(sec3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 186, -1));

        sec4.setSelectedItem(null);
        sec4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sec4ActionPerformed(evt);
            }
        });
        add(sec4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 186, -1));

        add(Course2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 260, 186, -1));

        add(Course3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, 186, -1));

        add(Course4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 320, 186, -1));

        incButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/icons8-plus-30.png"))); // NOI18N
        incButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        incButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                incButtonMouseClicked(evt);
            }
        });
        add(incButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 220, 30, 30));

        decButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/icons8-minus-30.png"))); // NOI18N
        decButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        decButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decButtonMouseClicked(evt);
            }
        });
        add(decButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 220, 30, 30));

        dataCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Allocation Data", "Ignored Data" }));
        dataCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                dataComboItemStateChanged(evt);
            }
        });
        add(dataCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 120, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void sec1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sec1ActionPerformed
        dbclass dbclass = new dbclass();
        ResultSet rs = dbclass.checksem(sec1.getSelectedItem().toString());
        try {
            rs.next();
            String semester = rs.getString("semester");
            ResultSet rs1 = dbclass.getcourse(semester);
            Course1.removeAllItems();
            while (rs1.next()) {
                String data = rs1.getString("cname");
                Course1.addItem(data);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_sec1ActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        rsearch.setText("");
        DefaultTableModel table = (DefaultTableModel) jTable1.getModel();
        String sch = search.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
        jTable1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(sch));
    }//GEN-LAST:event_searchKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int i= jTable1.getSelectedRow();
        TableModel model=jTable1.getModel();
       dtname=(model.getValueAt(i, 0).toString());
       dtid=(model.getValueAt(i, 1).toString());
       dsection=(model.getValueAt(i, 2).toString());
       dcourse=(model.getValueAt(i, 3).toString());

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
            dbclass insert = new dbclass();
           
            try {
                
               if(course_type.equals("Regular"))
               {
                   
                    ResultSet rs = insert.countCourseType(tid,course_type);
                 if(countCombo==1)
                 {
                    rs.next();
                    if(rs.getInt(1)<4)
                    {
                         ResultSet rs2 = insert.checkDuplicateEntry(Course1.getSelectedItem().toString() , sec1.getSelectedItem().toString());
                        
                         if(rs2.isBeforeFirst()==true){
                           JOptionPane.showMessageDialog(null,"dulicate entry "+ Course1.getSelectedItem().toString() + " Course is Already Assign to Other Teacher For Section " +  sec1.getSelectedItem().toString(),"dulicate entry",JOptionPane.WARNING_MESSAGE);
                   
                        }
                        else{
                        insert.inserttassign(tid, sec1.getSelectedItem().toString(), Course1.getSelectedItem().toString(), tcombo.getSelectedItem().toString(),course_type,phone_num);
                       
                           ResultSet rss = insert.chk_chours(Course1.getSelectedItem().toString());
                           rss.next();
                             chours=rss.getInt("chours");
                             ResultSet rss1 = insert.get_sec_shift(sec1.getSelectedItem().toString());
                            rss1.next();
                            shift=rss1.getString("shift");
                            if(chours==3)
                            {
                            insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                            insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                            }
                            else if(chours==4)
                            {
                            insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                            insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                            insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),l,shift);
                            }
                            else
                            {
                            
                            }
                        
                        JOptionPane.showMessageDialog(null, "data succesfully added", "data add", JOptionPane.INFORMATION_MESSAGE);
                        DefaultTableModel tbmodel = (DefaultTableModel) jTable1.getModel();
                        tbmodel.setRowCount(0);
                        showrecord();
                        }
                        
                    }
                    else
                    {
                    JOptionPane.showMessageDialog(null, "you can not add Regular Course More then 4", "Error Massege", JOptionPane.ERROR_MESSAGE);
                    }
                 }
                 else if(countCombo==2)
                 {
                    rs.next();
                    if(rs.getInt(1)<3)
                    {
                            ResultSet rs2 = insert.checkDuplicateEntry(Course1.getSelectedItem().toString() , sec1.getSelectedItem().toString());
                            if(rs2.isBeforeFirst()==true){
                            JOptionPane.showMessageDialog(null,"dulicate entry "+ Course1.getSelectedItem().toString() + " Course is Already Assign to Other Teacher For Section " +  sec1.getSelectedItem().toString(),"dulicate entry",JOptionPane.WARNING_MESSAGE);

                           }
                           else{
                           insert.inserttassign(tid, sec1.getSelectedItem().toString(), Course1.getSelectedItem().toString(), tcombo.getSelectedItem().toString(),course_type,phone_num);
                               
                                ResultSet rss = insert.chk_chours(Course1.getSelectedItem().toString());
                                 rss.next();
                                    chours=rss.getInt("chours");
                                    ResultSet rss1 = insert.get_sec_shift(sec1.getSelectedItem().toString());
                            rss1.next();
                            shift=rss1.getString("shift");
                                if(chours==3)
                                {
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                                }
                                else if(chours==4)
                                {
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),l,shift);
                                }
                                else
                                {

                                }
                           JOptionPane.showMessageDialog(null, "data succesfully added", "data add", JOptionPane.INFORMATION_MESSAGE);
                           DefaultTableModel tbmodel = (DefaultTableModel) jTable1.getModel();
                           tbmodel.setRowCount(0);
                           showrecord();
                           }

                            ResultSet rs3 = insert.checkDuplicateEntry(Course2.getSelectedItem().toString() , sec2.getSelectedItem().toString());
                            if(rs3.isBeforeFirst()==true){
                            JOptionPane.showMessageDialog(null,"dulicate entry "+ Course2.getSelectedItem().toString() + " Course is Already Assign to Other Teacher For Section " +  sec2.getSelectedItem().toString(),"dulicate entry",JOptionPane.WARNING_MESSAGE);

                           }
                           else{
                           insert.inserttassign(tid, sec2.getSelectedItem().toString(), Course2.getSelectedItem().toString(), tcombo.getSelectedItem().toString(),course_type,phone_num);
                              
                                ResultSet rss = insert.chk_chours(Course2.getSelectedItem().toString());
                                 rss.next();    
                                chours=rss.getInt("chours");
                                ResultSet rss1 = insert.get_sec_shift(sec2.getSelectedItem().toString());
                            rss1.next();
                            shift=rss1.getString("shift");
                                if(chours==3)
                                {
                                insert.insertclass(Course2.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec2.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course2.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec2.getSelectedItem().toString(),c,shift);
                                }
                                else if(chours==4)
                                {
                                insert.insertclass(Course2.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec2.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course2.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec2.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course2.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec2.getSelectedItem().toString(),l,shift);
                                }
                                else
                                {

                                }
                           JOptionPane.showMessageDialog(null, "data succesfully added", "data add", JOptionPane.INFORMATION_MESSAGE);
                           DefaultTableModel tbmodel = (DefaultTableModel) jTable1.getModel();
                           tbmodel.setRowCount(0);
                           showrecord();
                           }
                        
                    }
                    else
                    {
                    JOptionPane.showMessageDialog(null, "you can not add Regular Course More then 4", "Error Massege", JOptionPane.ERROR_MESSAGE);
                    }
                 }
                 
                 else if(countCombo==3)
                 {
                    rs.next();
                    if(rs.getInt(1)<2)
                    {
                        ResultSet rs2 = insert.checkDuplicateEntry(Course1.getSelectedItem().toString() , sec1.getSelectedItem().toString());
                            if(rs2.isBeforeFirst()==true){
                            JOptionPane.showMessageDialog(null,"dulicate entry "+ Course1.getSelectedItem().toString() + " Course is Already Other to some Teacher For Section " +  sec1.getSelectedItem().toString(),"dulicate entry",JOptionPane.WARNING_MESSAGE);

                           }
                           else{
                           insert.inserttassign(tid, sec1.getSelectedItem().toString(), Course1.getSelectedItem().toString(), tcombo.getSelectedItem().toString(),course_type,phone_num);
                              
                                ResultSet rss = insert.chk_chours(Course1.getSelectedItem().toString());
                                 rss.next();
                                chours=rss.getInt("chours");
                                ResultSet rss1 = insert.get_sec_shift(sec1.getSelectedItem().toString());
                            rss1.next();
                            shift=rss1.getString("shift");
                                if(chours==3)
                                {
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                                }
                                else if(chours==4)
                                {
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),l,shift);
                                }
                                else
                                {

                                }
                           JOptionPane.showMessageDialog(null, "data succesfully added", "data add", JOptionPane.INFORMATION_MESSAGE);
                           DefaultTableModel tbmodel = (DefaultTableModel) jTable1.getModel();
                           tbmodel.setRowCount(0);
                           showrecord();
                           }

                            ResultSet rs3 = insert.checkDuplicateEntry(Course2.getSelectedItem().toString() , sec2.getSelectedItem().toString());
                            if(rs3.isBeforeFirst()==true){
                            JOptionPane.showMessageDialog(null,"dulicate entry "+ Course2.getSelectedItem().toString() + " Course is Already Assign to Other Teacher For Section " +  sec2.getSelectedItem().toString(),"dulicate entry",JOptionPane.WARNING_MESSAGE);

                           }
                           else{
                           insert.inserttassign(tid, sec2.getSelectedItem().toString(), Course2.getSelectedItem().toString(), tcombo.getSelectedItem().toString(),course_type,phone_num);
                             
                                ResultSet rss = insert.chk_chours(Course2.getSelectedItem().toString());
                                 rss.next();
                                chours=rss.getInt("chours");
                                ResultSet rss1 = insert.get_sec_shift(sec2.getSelectedItem().toString());
                                rss1.next();
                                shift=rss1.getString("shift");
                                if(chours==3)
                                {
                                insert.insertclass(Course2.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec2.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course2.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec2.getSelectedItem().toString(),c,shift);
                                }
                                else if(chours==4)
                                {
                                insert.insertclass(Course2.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec2.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course2.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec2.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course2.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec2.getSelectedItem().toString(),l,shift);
                                }
                                else
                                {

                                }
                           JOptionPane.showMessageDialog(null, "data succesfully added", "data add", JOptionPane.INFORMATION_MESSAGE);
                           DefaultTableModel tbmodel = (DefaultTableModel) jTable1.getModel();
                           tbmodel.setRowCount(0);
                           showrecord();
                           }
                           
                            ResultSet rs4 = insert.checkDuplicateEntry(Course3.getSelectedItem().toString() , sec3.getSelectedItem().toString());
                            if(rs4.isBeforeFirst()==true){
                            JOptionPane.showMessageDialog(null,"dulicate entry "+ Course3.getSelectedItem().toString() + " Course is Already Assign to Other Teacher For Section " +  sec3.getSelectedItem().toString(),"dulicate entry",JOptionPane.WARNING_MESSAGE);

                           }
                           else{
                           insert.inserttassign(tid, sec3.getSelectedItem().toString(), Course3.getSelectedItem().toString(), tcombo.getSelectedItem().toString(),course_type,phone_num);
                              
                                ResultSet rss = insert.chk_chours(Course3.getSelectedItem().toString());
                                 rss.next();
                                chours=rss.getInt("chours");
                                ResultSet rss1 = insert.get_sec_shift(sec3.getSelectedItem().toString());
                                 rss1.next();
                                 shift=rss1.getString("shift");
                                if(chours==3)
                                {
                                insert.insertclass(Course3.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec3.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course3.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec3.getSelectedItem().toString(),c,shift);
                                }
                                else if(chours==4)
                                {
                                insert.insertclass(Course3.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec3.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course3.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec3.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course3.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec3.getSelectedItem().toString(),l,shift);
                                }
                                else
                                {

                                }
                           JOptionPane.showMessageDialog(null, "data succesfully added", "data add", JOptionPane.INFORMATION_MESSAGE);
                           DefaultTableModel tbmodel = (DefaultTableModel) jTable1.getModel();
                           tbmodel.setRowCount(0);
                           showrecord();
                           }
                        
                    }
                    else
                    {
                    JOptionPane.showMessageDialog(null, "you can not add Regular Course More then 4", "Error Massege", JOptionPane.ERROR_MESSAGE);
                    }
                 }
                 
                  else if(countCombo==4)
                 {
                    rs.next();
                    if(rs.getInt(1)<1)
                    {
                        ResultSet rs2 = insert.checkDuplicateEntry(Course1.getSelectedItem().toString() , sec1.getSelectedItem().toString());
                            if(rs2.isBeforeFirst()==true){
                            JOptionPane.showMessageDialog(null,"dulicate entry "+ Course1.getSelectedItem().toString() + " Course is Already Assign to Other Teacher For Section " +  sec1.getSelectedItem().toString(),"dulicate entry",JOptionPane.WARNING_MESSAGE);

                           }
                           else{
                           insert.inserttassign(tid, sec1.getSelectedItem().toString(), Course1.getSelectedItem().toString(), tcombo.getSelectedItem().toString(),course_type,phone_num);
                            
                                ResultSet rss = insert.chk_chours(Course1.getSelectedItem().toString());
                                 rss.next();
                                chours=rss.getInt("chours");
                                ResultSet rss1 = insert.get_sec_shift(sec1.getSelectedItem().toString());
                                rss1.next();
                                shift=rss1.getString("shift");
                                if(chours==3)
                                {
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                                }
                                else if(chours==4)
                                {
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),l,shift);
                                }
                                else
                                {

                                }
                           JOptionPane.showMessageDialog(null, "data succesfully added", "data add", JOptionPane.INFORMATION_MESSAGE);
                           DefaultTableModel tbmodel = (DefaultTableModel) jTable1.getModel();
                           tbmodel.setRowCount(0);
                           showrecord();
                           }

                            ResultSet rs3 = insert.checkDuplicateEntry(Course2.getSelectedItem().toString() , sec2.getSelectedItem().toString());
                            if(rs3.isBeforeFirst()==true){
                            JOptionPane.showMessageDialog(null,"dulicate entry "+ Course2.getSelectedItem().toString() + " Course is Already Assign to Other Teacher For Section " +  sec2.getSelectedItem().toString(),"dulicate entry",JOptionPane.WARNING_MESSAGE);

                           }
                           else{
                           insert.inserttassign(tid, sec2.getSelectedItem().toString(), Course2.getSelectedItem().toString(), tcombo.getSelectedItem().toString(),course_type,phone_num);
                              
                                ResultSet rss = insert.chk_chours(Course2.getSelectedItem().toString());
                                 rss.next();
                                chours=rss.getInt("chours");
                                ResultSet rss1 = insert.get_sec_shift(sec2.getSelectedItem().toString());
                                rss1.next();
                                shift=rss1.getString("shift");
                                if(chours==3)
                                {
                                insert.insertclass(Course2.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec2.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course2.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec2.getSelectedItem().toString(),c,shift);
                                }
                                else if(chours==4)
                                {
                                insert.insertclass(Course2.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec2.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course2.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec2.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course2.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec2.getSelectedItem().toString(),l,shift);
                                }
                                else
                                {

                                }
                           JOptionPane.showMessageDialog(null, "data succesfully added", "data add", JOptionPane.INFORMATION_MESSAGE);
                           DefaultTableModel tbmodel = (DefaultTableModel) jTable1.getModel();
                           tbmodel.setRowCount(0);
                           showrecord();
                           }
                           
                            ResultSet rs4 = insert.checkDuplicateEntry(Course3.getSelectedItem().toString() , sec3.getSelectedItem().toString());
                            if(rs4.isBeforeFirst()==true){
                            JOptionPane.showMessageDialog(null,"dulicate entry "+ Course3.getSelectedItem().toString() + " Course is Already Assign to Other Teacher For Section " +  sec3.getSelectedItem().toString(),"dulicate entry",JOptionPane.WARNING_MESSAGE);

                           }
                           else{
                           insert.inserttassign(tid, sec3.getSelectedItem().toString(), Course3.getSelectedItem().toString(), tcombo.getSelectedItem().toString(),course_type,phone_num);
                            
                                ResultSet rss = insert.chk_chours(Course3.getSelectedItem().toString());
                                 rss.next();
                                chours=rss.getInt("chours");
                                ResultSet rss1 = insert.get_sec_shift(sec3.getSelectedItem().toString());
                                rss1.next();
                                shift=rss1.getString("shift");
                                if(chours==3)
                                {
                                insert.insertclass(Course3.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec3.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course3.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec3.getSelectedItem().toString(),c,shift);
                                }
                                else if(chours==4)
                                {
                                insert.insertclass(Course3.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec3.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course3.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec3.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course3.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec3.getSelectedItem().toString(),l,shift);
                                }
                                else
                                {

                                }
                           JOptionPane.showMessageDialog(null, "data succesfully added", "data add", JOptionPane.INFORMATION_MESSAGE);
                           DefaultTableModel tbmodel = (DefaultTableModel) jTable1.getModel();
                           tbmodel.setRowCount(0);
                           showrecord();
                           }
                            
                            ResultSet rs5 = insert.checkDuplicateEntry(Course4.getSelectedItem().toString() , sec4.getSelectedItem().toString());
                            if(rs5.isBeforeFirst()==true){
                            JOptionPane.showMessageDialog(null,"dulicate entry "+ Course4.getSelectedItem().toString() + " Course is Already Assign to Other Teacher For Section " +  sec4.getSelectedItem().toString(),"dulicate entry",JOptionPane.WARNING_MESSAGE);

                           }
                           else{
                           insert.inserttassign(tid, sec4.getSelectedItem().toString(), Course4.getSelectedItem().toString(), tcombo.getSelectedItem().toString(),course_type,phone_num);
                            
                                ResultSet rss = insert.chk_chours(Course4.getSelectedItem().toString());
                                 rss.next();
                                chours=rss.getInt("chours");
                                ResultSet rss1 = insert.get_sec_shift(sec4.getSelectedItem().toString());
                                rss1.next();
                                shift=rss1.getString("shift");
                                if(chours==3)
                                {
                                insert.insertclass(Course4.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec4.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course4.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec4.getSelectedItem().toString(),c,shift);
                                }
                                else if(chours==4)
                                {
                                insert.insertclass(Course4.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec4.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course4.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec4.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course4.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec4.getSelectedItem().toString(),l,shift);
                                }
                                else
                                {

                                }
                           JOptionPane.showMessageDialog(null, "data succesfully added", "data add", JOptionPane.INFORMATION_MESSAGE);
                           DefaultTableModel tbmodel = (DefaultTableModel) jTable1.getModel();
                           tbmodel.setRowCount(0);
                           showrecord();
                           } 
                        
                    }
                    else
                    {
                    JOptionPane.showMessageDialog(null, "you can not add Regular Course More then 4", "Error Massege", JOptionPane.ERROR_MESSAGE);
                    }
                 }

               
               }
               else if(course_type.equals("Visiting"))
               {
                    
                    ResultSet rs = insert.countCourseType(tid,course_type);
                 if(countCombo==1)
                 {
                    rs.next();
                    if(rs.getInt(1)<3)
                    {
                         ResultSet rs2 = insert.checkDuplicateEntry(Course1.getSelectedItem().toString() , sec1.getSelectedItem().toString());
                        if(rs2.isBeforeFirst()==true){
                           JOptionPane.showMessageDialog(null,"dulicate entry "+ Course1.getSelectedItem().toString() + " Course is Already Assign to Other Teacher For Section " +  sec1.getSelectedItem().toString(),"dulicate entry",JOptionPane.WARNING_MESSAGE);
                   
                        }
                        else{
                        insert.inserttassign(tid, sec1.getSelectedItem().toString(), Course1.getSelectedItem().toString(), tcombo.getSelectedItem().toString(),course_type,phone_num);
                        
                                ResultSet rss = insert.chk_chours(Course1.getSelectedItem().toString());
                                 rss.next();
                                chours=rss.getInt("chours");
                                ResultSet rss1 = insert.get_sec_shift(sec1.getSelectedItem().toString());
                                rss1.next();
                                shift=rss1.getString("shift");
                                if(chours==3)
                                {
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                                }
                                else if(chours==4)
                                {
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),l,shift);
                                }
                                else
                                {

                                }
                        JOptionPane.showMessageDialog(null, "data succesfully added", "data add", JOptionPane.INFORMATION_MESSAGE);
                        DefaultTableModel tbmodel = (DefaultTableModel) jTable1.getModel();
                        tbmodel.setRowCount(0);
                        showrecord();
                        }
                        
                    }
                    else
                    {
                    JOptionPane.showMessageDialog(null, "you can not add Visiting Course More then 3", "Error Massege", JOptionPane.ERROR_MESSAGE);
                    }
                 }
                 else if(countCombo==2)
                 {
                    rs.next();
                    if(rs.getInt(1)<2)
                    {
                            ResultSet rs2 = insert.checkDuplicateEntry(Course1.getSelectedItem().toString() , sec1.getSelectedItem().toString());
                            if(rs2.isBeforeFirst()==true){
                            JOptionPane.showMessageDialog(null,"dulicate entry "+ Course1.getSelectedItem().toString() + " Course is Already Assign to Other Teacher For Section " +  sec1.getSelectedItem().toString(),"dulicate entry",JOptionPane.WARNING_MESSAGE);

                           }
                           else{
                           insert.inserttassign(tid, sec1.getSelectedItem().toString(), Course1.getSelectedItem().toString(), tcombo.getSelectedItem().toString(),course_type,phone_num);
                           
                                ResultSet rss = insert.chk_chours(Course1.getSelectedItem().toString());
                                 rss.next();
                                chours=rss.getInt("chours");
                                ResultSet rss1 = insert.get_sec_shift(sec1.getSelectedItem().toString());
                                rss1.next();
                                shift=rss1.getString("shift");
                                if(chours==3)
                                {
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                                }
                                else if(chours==4)
                                {
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),l,shift);
                                }
                                else
                                {

                                }
                           JOptionPane.showMessageDialog(null, "data succesfully added", "data add", JOptionPane.INFORMATION_MESSAGE);
                           DefaultTableModel tbmodel = (DefaultTableModel) jTable1.getModel();
                           tbmodel.setRowCount(0);
                           showrecord();
                           }

                            ResultSet rs3 = insert.checkDuplicateEntry(Course2.getSelectedItem().toString() , sec2.getSelectedItem().toString());
                            if(rs3.isBeforeFirst()==true){
                            JOptionPane.showMessageDialog(null,"dulicate entry "+ Course2.getSelectedItem().toString() + " Course is Already Assign to Other Teacher For Section " +  sec2.getSelectedItem().toString(),"dulicate entry",JOptionPane.WARNING_MESSAGE);

                           }
                           else{
                           insert.inserttassign(tid, sec2.getSelectedItem().toString(), Course2.getSelectedItem().toString(), tcombo.getSelectedItem().toString(),course_type,phone_num);
                           
                                ResultSet rss = insert.chk_chours(Course2.getSelectedItem().toString());
                                 rss.next();
                                chours=rss.getInt("chours");
                                ResultSet rss1 = insert.get_sec_shift(sec2.getSelectedItem().toString());
                                rss1.next();
                                shift=rss1.getString("shift");
                                if(chours==3)
                                {
                                insert.insertclass(Course2.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec2.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course2.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec2.getSelectedItem().toString(),c,shift);
                                }
                                else if(chours==4)
                                {
                                insert.insertclass(Course2.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec2.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course2.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec2.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course2.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec2.getSelectedItem().toString(),l,shift);
                                }
                                else
                                {

                                }
                           JOptionPane.showMessageDialog(null, "data succesfully added", "data add", JOptionPane.INFORMATION_MESSAGE);
                           DefaultTableModel tbmodel = (DefaultTableModel) jTable1.getModel();
                           tbmodel.setRowCount(0);
                           showrecord();
                           }
                        
                    }
                    else
                    {
                    JOptionPane.showMessageDialog(null, "you can not add Visiting Course More then 3", "Error Massege", JOptionPane.ERROR_MESSAGE);
                    }
                 }
                 
                 else if(countCombo==3)
                 {
                    rs.next();
                    if(rs.getInt(1)<1)
                    {
                        ResultSet rs2 = insert.checkDuplicateEntry(Course1.getSelectedItem().toString() , sec1.getSelectedItem().toString());
                            if(rs2.isBeforeFirst()==true){
                            JOptionPane.showMessageDialog(null,"dulicate entry "+ Course1.getSelectedItem().toString() + " Course is Already Assign to Other Teacher For Section " +  sec1.getSelectedItem().toString(),"dulicate entry",JOptionPane.WARNING_MESSAGE);

                           }
                           else{
                           insert.inserttassign(tid, sec1.getSelectedItem().toString(), Course1.getSelectedItem().toString(), tcombo.getSelectedItem().toString(),course_type,phone_num);
                           
                                ResultSet rss = insert.chk_chours(Course1.getSelectedItem().toString());
                                 rss.next();
                                chours=rss.getInt("chours");
                                ResultSet rss1 = insert.get_sec_shift(sec1.getSelectedItem().toString());
                                rss1.next();
                                shift=rss1.getString("shift");
                                if(chours==3)
                                {
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                                }
                                else if(chours==4)
                                {
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course1.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec1.getSelectedItem().toString(),l,shift);
                                }
                                else
                                {

                                }
                           JOptionPane.showMessageDialog(null, "data succesfully added", "data add", JOptionPane.INFORMATION_MESSAGE);
                           DefaultTableModel tbmodel = (DefaultTableModel) jTable1.getModel();
                           tbmodel.setRowCount(0);
                           showrecord();
                           }

                            ResultSet rs3 = insert.checkDuplicateEntry(Course2.getSelectedItem().toString() , sec2.getSelectedItem().toString());
                            if(rs3.isBeforeFirst()==true){
                            JOptionPane.showMessageDialog(null,"dulicate entry "+ Course2.getSelectedItem().toString() + " Course is Already Assign to Other Teacher For Section " +  sec2.getSelectedItem().toString(),"dulicate entry",JOptionPane.WARNING_MESSAGE);

                           }
                           else{
                           insert.inserttassign(tid, sec2.getSelectedItem().toString(), Course2.getSelectedItem().toString(), tcombo.getSelectedItem().toString(),course_type,phone_num);
                           
                                ResultSet rss = insert.chk_chours(Course2.getSelectedItem().toString());
                                 rss.next();
                                chours=rss.getInt("chours");
                                ResultSet rss1 = insert.get_sec_shift(sec2.getSelectedItem().toString());
                                rss1.next();
                                shift=rss1.getString("shift");
                                if(chours==3)
                                {
                                insert.insertclass(Course2.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec2.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course2.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec2.getSelectedItem().toString(),c,shift);
                                }
                                else if(chours==4)
                                {
                                insert.insertclass(Course2.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec2.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course2.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec2.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course2.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec2.getSelectedItem().toString(),l,shift);
                                }
                                else
                                {

                                }
                           JOptionPane.showMessageDialog(null, "data succesfully added", "data add", JOptionPane.INFORMATION_MESSAGE);
                           DefaultTableModel tbmodel = (DefaultTableModel) jTable1.getModel();
                           tbmodel.setRowCount(0);
                           showrecord();
                           }
                           
                            ResultSet rs4 = insert.checkDuplicateEntry(Course3.getSelectedItem().toString() , sec3.getSelectedItem().toString());
                            if(rs4.isBeforeFirst()==true){
                            JOptionPane.showMessageDialog(null,"dulicate entry "+ Course3.getSelectedItem().toString() + " Course is Already Assign to Other Teacher For Section " +  sec3.getSelectedItem().toString(),"dulicate entry",JOptionPane.WARNING_MESSAGE);

                           }
                           else{
                           insert.inserttassign(tid, sec3.getSelectedItem().toString(), Course3.getSelectedItem().toString(), tcombo.getSelectedItem().toString(),course_type,phone_num);
                           
                                ResultSet rss = insert.chk_chours(Course3.getSelectedItem().toString());
                                 rss.next();
                                chours=rss.getInt("chours");
                                ResultSet rss1 = insert.get_sec_shift(sec3.getSelectedItem().toString());
                                rss1.next();
                                shift=rss1.getString("shift");
                                if(chours==3)
                                {
                                insert.insertclass(Course3.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec3.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course3.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec3.getSelectedItem().toString(),c,shift);
                                }
                                else if(chours==4)
                                {
                                insert.insertclass(Course3.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec3.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course3.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec3.getSelectedItem().toString(),c,shift);
                                insert.insertclass(Course3.getSelectedItem().toString(),tcombo.getSelectedItem().toString(), sec3.getSelectedItem().toString(),l,shift);
                                }
                                else
                                {

                                }
                           JOptionPane.showMessageDialog(null, "data succesfully added", "data add", JOptionPane.INFORMATION_MESSAGE);
                           DefaultTableModel tbmodel = (DefaultTableModel) jTable1.getModel();
                           tbmodel.setRowCount(0);
                           showrecord();
                           }
                        
                    }
                    else
                    {
                    JOptionPane.showMessageDialog(null, "you can not add Visiting Course More then 3", "Error Massege", JOptionPane.ERROR_MESSAGE);
                    }
                 }
                 

               }
                
            } catch (Exception ex) {
                System.out.println(ex);
            }

        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        countCombo=0;
        rtname.setText("");
        search.setText(null);
        rsearch.setText("");
        ctypecombo.setVisible(false);
        sec1.setVisible(false); 
        sec2.setVisible(false);
        sec3.setVisible(false);
        sec4.setVisible(false);
        Course1.setVisible(false);
        Course2.setVisible(false);
        Course3.setVisible(false);
        Course4.setVisible(false);
        incButton.setVisible(false);
        decButton.setVisible(false);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      
            dbclass dbclass = new dbclass();
           
            try {
                if (dtid!="Null" && dcourse!="Null" && dsection!="Null") {
                    dbclass.deletetassign(dtid,dcourse,dsection);
                    dbclass.deleteclass(dcourse,dtname,dsection);
                    JOptionPane.showMessageDialog(null, "data succesfully deleted", "data delete", JOptionPane.INFORMATION_MESSAGE);
                    DefaultTableModel tbmodel = (DefaultTableModel)jTable1.getModel();
                    tbmodel.setRowCount(0);
                    showrecord();
                } else {
                    JOptionPane.showMessageDialog(null, "Plz Select Row from table To Delete Data", "Select Row", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                System.out.println(ex);
            }

         
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tcomboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tcomboActionPerformed
        ctypecombo.setVisible(true);
        
        dbclass dbclass=new dbclass();
      ResultSet rs= dbclass.getteacherdata(tcombo.getSelectedItem().toString());
        try
        {
        rs.next();
       tid=rs.getString("tid");
       phone_num=rs.getString("tphone");
        }
        catch(Exception e)
        {
            System.out.println(e);
        
        }
        
    }//GEN-LAST:event_tcomboActionPerformed

    private void ctypecomboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ctypecomboActionPerformed

        course_type=ctypecombo.getSelectedItem().toString();
        countCombo=1;
        
        sec2.setVisible(false);
        sec3.setVisible(false);
        sec4.setVisible(false);
        Course2.setVisible(false);
        Course3.setVisible(false);
        Course4.setVisible(false);
        
        sec1.setVisible(true);
        Course1.setVisible(true);
        incButton.setVisible(true);
        decButton.setVisible(true);
        add(incButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 220, 30, 30));
        add(decButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 220, 30, 30));
         
    }//GEN-LAST:event_ctypecomboActionPerformed

    private void sec2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sec2ActionPerformed
        dbclass dbclass = new dbclass();
        ResultSet rs = dbclass.checksem(sec2.getSelectedItem().toString());
        try {
            rs.next();
            String semester = rs.getString("semester");
            ResultSet rs1 = dbclass.getcourse(semester);
            Course2.removeAllItems();
            while (rs1.next()) {
                String data = rs1.getString("cname");
                Course2.addItem(data);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_sec2ActionPerformed

    private void sec3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sec3ActionPerformed
       dbclass dbclass = new dbclass();
        ResultSet rs = dbclass.checksem(sec3.getSelectedItem().toString());
        try {
            rs.next();
            String semester = rs.getString("semester");
            ResultSet rs1 = dbclass.getcourse(semester);
            Course3.removeAllItems();
            while (rs1.next()) {
                String data = rs1.getString("cname");
                Course3.addItem(data);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_sec3ActionPerformed

    private void sec4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sec4ActionPerformed
        dbclass dbclass = new dbclass();
        ResultSet rs = dbclass.checksem(sec4.getSelectedItem().toString());
        try {
            rs.next();
            String semester = rs.getString("semester");
            ResultSet rs1 = dbclass.getcourse(semester);
            Course4.removeAllItems();
            while (rs1.next()) {
                String data = rs1.getString("cname");
                Course4.addItem(data);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_sec4ActionPerformed

    private void incButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_incButtonMouseClicked
         if(ctypecombo.getSelectedItem().equals("Regular"))
         {
       
                if(countCombo<4)
              {
                    countCombo++;
                    if(countCombo==2)
                    {
                    sec1.setVisible(true);
                    sec2.setVisible(true);
                    Course1.setVisible(true);
                    Course2.setVisible(true);
                    add(incButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, 30, 120));
                    add(decButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 210, 30, 120));
                    }
                    else if(countCombo==3)
                    {
                    sec1.setVisible(true);
                    sec2.setVisible(true);
                    sec3.setVisible(true);
                    Course1.setVisible(true);
                    Course2.setVisible(true);
                    Course3.setVisible(true);
                    add(incButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, 30, 180));
                    add(decButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 210, 30, 180));
                    }
                    else if(countCombo==4)
                    {
                    sec1.setVisible(true);
                    sec2.setVisible(true);
                    sec3.setVisible(true);
                    sec4.setVisible(true);
                    Course1.setVisible(true);
                    Course2.setVisible(true);
                    Course3.setVisible(true);
                    Course4.setVisible(true);
                    add(incButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, 30, 240));
                    add(decButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 210, 30, 240));
                    }
                    else
                    {

                    }
              }
         }
      
      if(ctypecombo.getSelectedItem().equals("Visiting"))
         {
       
                if(countCombo<3)
              {
                    countCombo++;
                    if(countCombo==2)
                    {
                    sec1.setVisible(true);
                    sec2.setVisible(true);
                    Course1.setVisible(true);
                    Course2.setVisible(true);
                    add(incButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, 30, 120));
                    add(decButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 210, 30, 120));
                    }
                    else if(countCombo==3)
                    {
                    sec1.setVisible(true);
                    sec2.setVisible(true);
                    sec3.setVisible(true);
                    Course1.setVisible(true);
                    Course2.setVisible(true);
                    Course3.setVisible(true);
                    add(incButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, 30, 180));
                    add(decButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 210, 30, 180));
                    }
                    else
                    {

                    }
              }
         }
    }//GEN-LAST:event_incButtonMouseClicked

    private void decButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decButtonMouseClicked
      sec2.setVisible(false);
        sec3.setVisible(false);
        sec4.setVisible(false);
        Course2.setVisible(false);
        Course3.setVisible(false);
        Course4.setVisible(false);
         add(incButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 220, 30, 30));
         add(decButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 220, 30, 30));
         
        if(ctypecombo.getSelectedItem().equals("Regular"))
         {
       
                if(countCombo>1)
              {
                    countCombo--;
                    if(countCombo==2)
                    {
                    sec1.setVisible(true);
                    sec2.setVisible(true);
                    Course1.setVisible(true);
                    Course2.setVisible(true);
                    add(incButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, 30, 120));
                    add(decButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 210, 30, 120));
                    }
                    else if(countCombo==3)
                    {
                    sec1.setVisible(true);
                    sec2.setVisible(true);
                    sec3.setVisible(true);
                    Course1.setVisible(true);
                    Course2.setVisible(true);
                    Course3.setVisible(true);
                    add(incButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, 30, 180));
                    add(decButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 210, 30, 180));

                    }
                    else if(countCombo==4)
                    {
                    sec1.setVisible(true);
                    sec2.setVisible(true);
                    sec3.setVisible(true);
                    sec4.setVisible(true);
                    Course1.setVisible(true);
                    Course2.setVisible(true);
                    Course3.setVisible(true);
                    Course4.setVisible(true);
                    add(incButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, 30, 240));
                    add(decButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 210, 30, 240));
                    }
                    else
                    {

                    }
              }
         }
      
      if(ctypecombo.getSelectedItem().equals("Visiting"))
         {
       
                if(countCombo>1)
              {
                    countCombo--;
                    if(countCombo==2)
                    {
                    sec1.setVisible(true);
                    sec2.setVisible(true);
                    Course1.setVisible(true);
                    Course2.setVisible(true);
                    add(incButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, 30, 120));
                    add(decButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 210, 30, 120));
                    }
                    else if(countCombo==3)
                    {
                    sec1.setVisible(true);
                    sec2.setVisible(true);
                    sec3.setVisible(true);
                    Course1.setVisible(true);
                    Course2.setVisible(true);
                    Course3.setVisible(true);
                    add(incButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, 30, 180));
                    add(decButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 210, 30, 180));
                    }
                    else
                    {

                    }
              }
         }
    }//GEN-LAST:event_decButtonMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        exportToexcel();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            importFromExcel();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       dbclass db=new dbclass();
       DefaultTableModel table = (DefaultTableModel) jTable1.getModel();
       int check=JOptionPane.showConfirmDialog(null,"Do you really Want to Delete All Data","Delete All Data",JOptionPane.YES_NO_OPTION );
        if(check==0){
         db.deleteAlltassign();
         db.deleteAllclass();
         table.setRowCount(0);
         showrecord();
        }
        else
        {
        
        }
       
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void dataComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_dataComboItemStateChanged
        
        if(dataCombo.getSelectedItem().equals("Allocation Data"))
      {
      skipdatapanel.setVisible(false);
      datapanel.setVisible(true);
      }
      else if(dataCombo.getSelectedItem().equals("Ignored Data"))
      {
      datapanel.setVisible(false);
      skipdatapanel.setVisible(true);
      }
    }//GEN-LAST:event_dataComboItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Course1;
    private javax.swing.JComboBox<String> Course2;
    private javax.swing.JComboBox<String> Course3;
    private javax.swing.JComboBox<String> Course4;
    private javax.swing.JComboBox<String> ctypecombo;
    private javax.swing.JComboBox<String> dataCombo;
    private com.raven.swing.PanelBorder datapanel;
    private javax.swing.JLabel decButton;
    private javax.swing.JLabel incButton;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel rsearch;
    private javax.swing.JLabel rtname;
    public com.raven.swing.SearchText search;
    private javax.swing.JComboBox<String> sec1;
    private javax.swing.JComboBox<String> sec2;
    private javax.swing.JComboBox<String> sec3;
    private javax.swing.JComboBox<String> sec4;
    private com.raven.swing.PanelBorder skipdatapanel;
    private javax.swing.JComboBox<String> tcombo;
    public javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
