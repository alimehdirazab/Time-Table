package com.raven.form;

import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import static org.apache.poi.hemf.record.emfplus.HemfPlusRecordType.object;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class summeryReport extends javax.swing.JPanel {
    
    int chkTable=1;
    

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
        XSSFSheet ws1 = wb.createSheet();
       
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
      
        TreeMap<String, Object[]> map = new TreeMap<>();
        
       
//        map.put("0", new Object[]{
//            model.getColumnName(0),
//            model.getColumnName(1),
//            model.getColumnName(2),
//            model.getColumnName(3),
//            model.getColumnName(4)});
//        
  
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
        
        
        
        // for 2nd sheet
        DefaultTableModel model1 = (DefaultTableModel)jTable2.getModel();
      
        TreeMap<String, Object[]> map1 = new TreeMap<>();
        
        
           for (int i = 0; i < model1.getRowCount(); i++) {
            
            map1.put(Integer.toString(i), new Object[]{
                model1.getValueAt(i, 0),
                model1.getValueAt(i, 1),
                model1.getValueAt(i, 2),
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

    public void showrecord() {

        dbclass dbclass = new dbclass();
        ResultSet rs = dbclass.getTeacherReport();

        try {
            while (rs.next()) {
                String data[] = {rs.getString("tname"),rs.getString("t_load"), rs.getString("Regular"), rs.getString("Visiting"), rs.getString("Assign_load"),rs.getString("t_type")};

                DefaultTableModel tbmodel = (DefaultTableModel) jTable1.getModel();

                tbmodel.addRow(data);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
    
    public void showrecord1() {

        dbclass dbclass = new dbclass();
        ResultSet rs = dbclass.getSectionReport();

        try {
            while (rs.next()) {
                String data[] = {rs.getString("section"),rs.getString("semester"),rs.getString("t_load"),rs.getString("assign_load")};

                DefaultTableModel tbmodel = (DefaultTableModel) jTable2.getModel();

                tbmodel.addRow(data);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
    
    public void countLoadTeacher()
    {
        int rowCount=jTable1.getRowCount();
        int reguler = 0;
        int visiting= 0;
        int totalAssign=0;
        int tload=0;
        int totalTeacher=rowCount;
        
        
    DefaultTableModel tbmodel = (DefaultTableModel) jTable1.getModel();
    for(int i=0;i<rowCount;i++)
    {
    reguler+=Integer.parseInt(tbmodel.getValueAt(i, 2).toString());
    visiting+=Integer.parseInt(tbmodel.getValueAt(i, 3).toString());
    totalAssign+=Integer.parseInt(tbmodel.getValueAt(i, 4).toString());
    tload+=Integer.parseInt(tbmodel.getValueAt(i, 1).toString());
    }
    regulerCount.setText("");
    regulerCount.setText("Total Ruguler   "+reguler);
    visitingCount.setText("");
    visitingCount.setText("Total Visiting "+visiting);
    totalassign.setText("");
    totalassign.setText("Total Courses Allocated "+totalAssign);
    remain.setText("");
    remain.setText("Ramaining               "+(tload-totalAssign));
    tteacher.setText("");
    tteacher.setText("Total Teachers "+totalTeacher);
    teacherLoad.setText("");
    teacherLoad.setText("Total Teachers Load "+tload);
    
    }
    
    
    
    public void countLoadSection()
    {
        int rowCount=jTable2.getRowCount();
        int totalAssign=0;
        int tload=0;
        int totalTeacher=rowCount;
        
        
    DefaultTableModel tbmodel = (DefaultTableModel) jTable2.getModel();
    for(int i=0;i<rowCount;i++)
    {
    totalAssign+=Integer.parseInt(tbmodel.getValueAt(i, 3).toString());
    tload+=Integer.parseInt(tbmodel.getValueAt(i, 2).toString());
    }
    regulerCount.setText("");
    visitingCount.setText("");
    
    totalassign.setText("");
    totalassign.setText("Total Courses Allocated "+totalAssign);
    remain.setText("");
    remain.setText("Ramaining               "+(tload-totalAssign));
    tteacher.setText("");
    tteacher.setText("Total Sections "+totalTeacher);
    teacherLoad.setText("");
    teacherLoad.setText("Total Sections Load "+tload);
    }
    
    
    
    
    public void changeTable1Color() {
        
        jTable1.setDefaultRenderer(Object.class ,new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jTable1, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(jTable1, value, isSelected, hasFocus, row, column);
                
            
        int load=Integer.parseInt(jTable1.getValueAt(row, 1).toString());
        int assign_load=Integer.parseInt(jTable1.getValueAt(row, 4).toString());
            
        if((load-assign_load)>0)
        {
        setBackground(jTable1.getBackground());
        setForeground(jTable1.getForeground()); 
        }
        else if((load-assign_load)==0)
        {
        setBackground(Color.GREEN);
        setForeground(Color.BLACK); 
        }
        else
        {
        setBackground(Color.RED);
        }
        
                return this;
                
                
            }
        });
    }
    
    
    
    public void changeTable2Color() {
        
        jTable2.setDefaultRenderer(Object.class ,new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jTable2, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(jTable2, value, isSelected, hasFocus, row, column);
                
            
        int load=Integer.parseInt(jTable2.getValueAt(row, 2).toString());
        int assign_load=Integer.parseInt(jTable2.getValueAt(row, 3).toString());
            
        if((load-assign_load)>0)
        {
        setBackground(jTable2.getBackground());
        setForeground(jTable2.getForeground()); 
        }
        else if((load-assign_load)==0)
        {
        setBackground(Color.GREEN);
        setForeground(Color.BLACK); 
        }
        else
        {
        setBackground(Color.RED);
        }
        
                return this;
                
                
            }
        });
    }
    
    

    public summeryReport() {
        initComponents();
        AutoCompleteDecorator.decorate(ttype);
      changeTable1Color();
      changeTable2Color();

        TableFilterHeader filterHeader1 = new TableFilterHeader(jTable1, AutoChoices.ENABLED);
        filterHeader1.setPosition(TableFilterHeader.Position.TOP);
        filterHeader1.setBackground(Color.WHITE);
        
        TableFilterHeader filterHeader2 = new TableFilterHeader(jTable2, AutoChoices.ENABLED);
        filterHeader2.setPosition(TableFilterHeader.Position.TOP);
        filterHeader2.setBackground(Color.WHITE);
        
        
        
        jButton5.setForeground(Color.green);
        jButton6.setForeground(Color.WHITE);
        jButton5.setBackground(Color.DARK_GRAY);
        jButton6.setBackground(new java.awt.Color(102, 102, 102));
        showrecord();
        showrecord1();
        countLoadTeacher();
        sectionPane.setVisible(false);
        
        dbclass dbclass = new dbclass();
        
        dbclass.deleteTeacherReport();
        dbclass.insertTeacherReport();
        dbclass.insertTeacherReportNotCommon();
        
        dbclass.deleteSectionReport();
        dbclass.insertSectionReport();
        dbclass.insertSectionReportNotCommon();
        
        
        DefaultTableModel table1 = (DefaultTableModel) jTable1.getModel();
        DefaultTableModel table2 = (DefaultTableModel) jTable2.getModel();
        table1.setRowCount(0);
        table2.setRowCount(0);
        showrecord();
        showrecord1();
       
       
        
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        search2 = new com.raven.swing.SearchText();
        jLabel4 = new javax.swing.JLabel();
        sectionPane = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        teacherPane = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        regulerCount = new javax.swing.JLabel();
        visitingCount = new javax.swing.JLabel();
        totalassign = new javax.swing.JLabel();
        remain = new javax.swing.JLabel();
        tteacher = new javax.swing.JLabel();
        teacherLoad = new javax.swing.JLabel();
        ttype = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(242, 242, 242));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(708, 65, 161, 46));

        jButton4.setBackground(new java.awt.Color(0, 0, 153));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("EXPORT");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(708, 549, 161, 46));

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

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 14, -1, -1));

        jLabel4.setFont(new java.awt.Font("Algerian", 1, 24)); // NOI18N
        jLabel4.setText("Summary REPORT");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, -1, -1));

        sectionPane.setBackground(new java.awt.Color(242, 242, 242));

        jTable2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Section", "Semester", "Total Load", "Assign Load"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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

        javax.swing.GroupLayout sectionPaneLayout = new javax.swing.GroupLayout(sectionPane);
        sectionPane.setLayout(sectionPaneLayout);
        sectionPaneLayout.setHorizontalGroup(
            sectionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sectionPaneLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
                .addContainerGap())
        );
        sectionPaneLayout.setVerticalGroup(
            sectionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sectionPaneLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(sectionPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 157, 800, 360));

        teacherPane.setBackground(new java.awt.Color(242, 242, 242));

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Teacher Name", "Total Load", "Regular", "Visiting", "Assign Load", "Teacher Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout teacherPaneLayout = new javax.swing.GroupLayout(teacherPane);
        teacherPane.setLayout(teacherPaneLayout);
        teacherPaneLayout.setHorizontalGroup(
            teacherPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, teacherPaneLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 727, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        teacherPaneLayout.setVerticalGroup(
            teacherPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teacherPaneLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(teacherPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 790, 360));

        jPanel4.setBackground(new java.awt.Color(242, 242, 242));

        jButton5.setBackground(new java.awt.Color(102, 102, 102));
        jButton5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Teacher Report");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(102, 102, 102));
        jButton6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Section Report");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 350, 40));

        regulerCount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(regulerCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 560, 180, 20));

        visitingCount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(visitingCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 580, 180, 20));

        totalassign.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(totalassign, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 560, 190, 20));

        remain.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(remain, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 580, 190, 20));

        tteacher.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(tteacher, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 560, 190, 20));

        teacherLoad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(teacherLoad, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 580, 190, 20));

        ttype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Internal", "External" }));
        ttype.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ttypeItemStateChanged(evt);
            }
        });
        add(ttype, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 530, 130, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dbclass dbclass = new dbclass();
        dbclass.deleteTeacherReport();
        dbclass.insertTeacherReport();
        dbclass.insertTeacherReportNotCommon();
        
        dbclass.deleteSectionReport();
        dbclass.insertSectionReport();
        dbclass.insertSectionReportNotCommon();
        
        
        DefaultTableModel table1 = (DefaultTableModel) jTable1.getModel();
        DefaultTableModel table2 = (DefaultTableModel) jTable2.getModel();
        table1.setRowCount(0);
        table2.setRowCount(0);
        showrecord();
        showrecord1();

           changeTable1Color();
           changeTable2Color();
           
           if(chkTable==1)
           {
           countLoadTeacher();
           }
           else if(chkTable==2)
           {
           countLoadSection();
           }
           ttype.setSelectedItem("All");

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        exportToexcel();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void search2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search2KeyReleased

        if(chkTable==1){
        DefaultTableModel table1 = (DefaultTableModel) jTable1.getModel();
        String sch1 = search2.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table1);
        jTable1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(sch1));
        }
        else if(chkTable==2)
        {
        DefaultTableModel table2 = (DefaultTableModel) jTable2.getModel();
        String sch2 = search2.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table2);
        jTable2.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(sch2));
        }
    }//GEN-LAST:event_search2KeyReleased

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
      
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       jButton5.setForeground(Color.green);
       jButton6.setForeground(Color.WHITE);
       jButton5.setBackground(Color.DARK_GRAY);
       jButton6.setBackground(new java.awt.Color(102, 102, 102));
        chkTable=1;
        sectionPane.setVisible(false);
        teacherPane.setVisible(true);
        ttype.setVisible(true);
        countLoadTeacher();
        ttype.setSelectedItem("All");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       jButton5.setForeground(Color.white);
       jButton6.setForeground(Color.green);
       jButton6.setBackground(Color.DARK_GRAY);
       jButton5.setBackground(new java.awt.Color(102, 102, 102));
        chkTable=2;
        teacherPane.setVisible(false);
        sectionPane.setVisible(true);  
        ttype.setVisible(false);
        countLoadSection();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void ttypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ttypeItemStateChanged
       if(ttype.getSelectedItem().toString().equals("All"))
       {
       countLoadTeacher();
       }
       else  if(ttype.getSelectedItem().toString().equals("Internal"))
       {
        int rowCount=jTable1.getRowCount();
        int reguler = 0;
        int visiting= 0;
        int totalAssign=0;
        int tload=0;
        int totalTeacher=0;
        
        
    DefaultTableModel tbmodel = (DefaultTableModel) jTable1.getModel();
    for(int i=0;i<rowCount;i++)
    {
        if(tbmodel.getValueAt(i, 5).toString().equals("Internal"))
        {
    reguler+=Integer.parseInt(tbmodel.getValueAt(i, 2).toString());
    visiting+=Integer.parseInt(tbmodel.getValueAt(i, 3).toString());
    totalAssign+=Integer.parseInt(tbmodel.getValueAt(i, 4).toString());
    tload+=Integer.parseInt(tbmodel.getValueAt(i, 1).toString());
    totalTeacher++;
        }
    }
    regulerCount.setText("");
    regulerCount.setText("Total Ruguler   "+reguler);
    visitingCount.setText("");
    visitingCount.setText("Total Visiting "+visiting);
    totalassign.setText("");
    totalassign.setText("Total Courses Allocated "+totalAssign);
    remain.setText("");
    remain.setText("Ramaining               "+(tload-totalAssign));
    tteacher.setText("");
    tteacher.setText("Total Teachers "+totalTeacher);
    teacherLoad.setText("");
    teacherLoad.setText("Total Teachers Load "+tload);
    
       }
        else  if(ttype.getSelectedItem().toString().equals("External"))
       {
        int rowCount=jTable1.getRowCount();
        int reguler = 0;
        int visiting= 0;
        int totalAssign=0;
        int tload=0;
        int totalTeacher=0;
        
        
    DefaultTableModel tbmodel = (DefaultTableModel) jTable1.getModel();
    for(int i=0;i<rowCount;i++)
    {
        if(tbmodel.getValueAt(i, 5).toString().equals("External"))
        {
    reguler+=Integer.parseInt(tbmodel.getValueAt(i, 2).toString());
    visiting+=Integer.parseInt(tbmodel.getValueAt(i, 3).toString());
    totalAssign+=Integer.parseInt(tbmodel.getValueAt(i, 4).toString());
    tload+=Integer.parseInt(tbmodel.getValueAt(i, 1).toString());
    totalTeacher++;
        }
    }
    regulerCount.setText("");
    regulerCount.setText("Total Ruguler   "+reguler);
    visitingCount.setText("");
    visitingCount.setText("Total Visiting "+visiting);
    totalassign.setText("");
    totalassign.setText("Total Courses Allocated "+totalAssign);
    remain.setText("");
    remain.setText("Ramaining               "+(tload-totalAssign));
    tteacher.setText("");
    tteacher.setText("Total Teachers "+totalTeacher);
    teacherLoad.setText("");
    teacherLoad.setText("Total Teachers Load "+tload);
    
       }
       
       
    }//GEN-LAST:event_ttypeItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel regulerCount;
    private javax.swing.JLabel remain;
    public com.raven.swing.SearchText search2;
    private javax.swing.JPanel sectionPane;
    private javax.swing.JLabel teacherLoad;
    private javax.swing.JPanel teacherPane;
    private javax.swing.JLabel totalassign;
    private javax.swing.JLabel tteacher;
    private javax.swing.JComboBox<String> ttype;
    private javax.swing.JLabel visitingCount;
    // End of variables declaration//GEN-END:variables
}
