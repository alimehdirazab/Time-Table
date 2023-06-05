
package com.raven.form;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



public class dbclass {

    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    dbclass(){
    
    
     try{
        Class.forName("com.mysql.cj.jdbc.Driver");
       con=DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
       st=con.createStatement();
        System.out.println("DB is Connected");
       }
     catch(Exception e){
        System.out.println(e);
       }
        
     
     
     
    }
    
    
    public void insertteacher(String tname,String tid,String tphone,String tload,String ttype){
    
    try{
    String query="insert into teachers(tname,tid,tphone,t_load,t_type)values('"+tname+"','"+tid+"','"+tphone+"','"+tload+"','"+ttype+"')";
    st.executeUpdate(query);
    }catch(Exception e){
    System.out.println(e);
    }
    
    }
    
    public ResultSet getteachers() {
    
    
        try{
       String query="Select * From teachers";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
    public ResultSet getteachersname(String department) {
    
    
        try{
       String query="SELECT tname FROM `teachers` WHERE `tdpt`= '"+department+"'" ;
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
    
    
    
    
    
     public ResultSet sreachteacher(String tid) {
    
    
        try{
       String query="Select * From teachers where tid='"+tid+"'";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
     
     
     
      public ResultSet checkteacherName(String tname) {
    
    
        try{
       String query="Select * From teachers where tname='"+tname+"'";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
     
     
     
     
      public ResultSet checkteacherNum(String tnum) {
    
    
        try{
       String query="Select * From teachers where tphone='"+tnum+"'";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
     


     public void deleteteacher(String tid) {
    
    
        try{
       String query="delete From teachers where tid='"+tid+"'";
       
        st.executeUpdate(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        
        
     }
     
      public void updateteacher(String tname,String tid,String tphone,String searchid,String tload,String ttype){
    
    try{
    String query="update teachers set tname='"+tname+"',tid='"+tid+"',tphone='"+tphone+"',t_load='"+tload+"',t_type='"+ttype+"' where tid='"+searchid+"'";
    st.executeUpdate(query);
    }catch(Exception e){
    System.out.println(e);
    }
    
    }

     
     
      public ResultSet getdepartment() {
    
    
        try{
       String query="Select * From departments";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }

      
      
      
      
      
      public void insertcourse(String cname,String ccode,String semester,int chours){
    
    try{
    String query="insert into courses(cname,ccode,semester,chours)values('"+cname+"','"+ccode+"','"+semester+"','"+chours+"')";
    st.executeUpdate(query);
    }catch(Exception e){
    System.out.println(e);
    }
    
    }
      
            public ResultSet getcourse() {
    
    
        try{
       String query="Select * From courses";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }

      
             public ResultSet sreachcourse(String ccode) {
    
    
        try{
       String query="Select * From courses where ccode='"+ccode+"'";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
             
             
             
              public ResultSet checkCourseName(String cname) {
    
    
        try{
       String query="Select * From courses where cname='"+cname+"'";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
             
             
             
             
             
               public void deletecourse(String ccode) {
    
    
        try{
       String query="delete From courses where ccode='"+ccode+"'";
       
        st.executeUpdate(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        
        
     }
             
             
             
             
              public void updatecourse(String cname,String ccode,String semester,String search,int chours){
    
    try{
    String query="update courses set cname='"+cname+"',ccode='"+ccode+"',semester='"+semester+"',chours='"+chours+"' where ccode='"+search+"'";
    st.executeUpdate(query);
    }catch(Exception e){
    System.out.println(e);
    }
    
    }
              
              
               public void insertdepartment(String dname){
    
    try{
    String query="insert into departments(depart)values('"+dname+"')";
    st.executeUpdate(query);
    }catch(Exception e){
    System.out.println(e);
    }
    
    }
         
               
                  public void deletedepartment(String dname) {
    
    
        try{
       String query="delete From departments where depart='"+dname+"'";
       
        st.executeUpdate(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        
        
     }
                  
                   public ResultSet sreachdepartment(String dname) {
    
    
        try{
       String query="Select * From departments where depart='"+dname+"'";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
               
      
                   
                   
              public void updatedepartment(String dname,String search){
    
    try{
    String query="update departments set depart='"+dname+"' where depart='"+search+"'";
    st.executeUpdate(query);
    }catch(Exception e){
    System.out.println(e);
    }
    
    }
                   
                   
       
    
    





  
      public void insertroom(String rno,String capacity,String location,String rtype){
    
    try{
    String query="insert into rooms(RoomNumber,RoomCapacity,RoomLocation,RoomType)values('"+rno+"','"+capacity+"','"+location+"','"+rtype+"')";
    st.executeUpdate(query);
    }catch(Exception e){
    System.out.println(e);
    JOptionPane.showMessageDialog(null,e,"teacher add",JOptionPane.ERROR_MESSAGE);
    }
    
    }

  public void deleteroom(String rno) {
    
    
        try{
       String query="delete From rooms where RoomNumber='"+rno+"'";
       
        st.executeUpdate(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        
        
     }


   public ResultSet sreachroom(String rno) {
    
    
        try{
       String query="Select * From rooms where RoomNumber='"+rno+"'";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }


   
   
              public void updateroom(String rno,String capacity,String location,String rtype,String search){
    
    try{
    String query="update rooms set RoomNumber='"+rno+"',RoomCapacity='"+capacity+"',RoomLocation='"+location+"',RoomType='"+rtype+"' where RoomNumber='"+search+"'";
    st.executeUpdate(query);
    }catch(Exception e){
    System.out.println(e);
    }
    
    }
   
   
   
     public ResultSet getrooms() {
    
    
        try{
       String query="Select * From rooms";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
              
     
     
     
              
           
     
                   public ResultSet sreachbatch(String bname) {
    
    
        try{
       String query="Select * From batches where batch='"+bname+"'";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
        
     
     
      
               public void insertbatch(String semester,String bname,String tload,String shift){
    
    try{
    String query="insert into batches(semester,batch,t_load,shift)values('"+semester+"','"+bname+"','"+tload+"','"+shift+"')";
    st.executeUpdate(query);
    }catch(Exception e){
    System.out.println(e);
    }
    
    }
               
               
               
                public void deletebatch(String bname) {
    
    
        try{
       String query="delete From batches where batch='"+bname+"'";
       
        st.executeUpdate(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        
        
     }
                
                
                
                
                       
              public void updatebatch(String semester,String bname,String search,String tload,String shift){
    
    try{
    String query="update batches set semester='"+semester+"' , batch='"+bname+"' , t_load='"+tload+"' , shift='"+shift+"' where batch='"+search+"'";
    st.executeUpdate(query);
    }catch(Exception e){
    System.out.println(e);
    }
    
    }
              
              
     public ResultSet getbatch() {
    
    
        try{
       String query="Select * From batches";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
     
     
     
     
     
     
     
     
                   public ResultSet sreachtime(String time) {
    
    
        try{
       String query="Select * From times where timing='"+time+"'";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
        
     
     
      
               public void inserttime(String time,String shift){
    
    try{
    String query="insert into times(timing,shift)values('"+time+"','"+shift+"')";
    st.executeUpdate(query);
    }catch(Exception e){
    System.out.println(e);
    }
    
    }
               
               
               
                public void deletetime(String time) {
    
    
        try{
       String query="delete From times where timing='"+time+"'";
       
        st.executeUpdate(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        
        
     }
                
                
                
                
                       
              public void updatetime(String time,String shift,String search){
    
    try{
    String query="update times set timing='"+time+"',shift='"+shift+"' where timing='"+search+"'";
    st.executeUpdate(query);
    }catch(Exception e){
    System.out.println(e);
    }
    
    }
              
              
     public ResultSet gettime() {
    
    
        try{
       String query="Select * From times";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
     
     
    
     
     //-------------------------------------------DashBoard------------------------------------------------------------
      public ResultSet countteacher() {
    
    
        try{
       String query="Select count(*) From teachers";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
      
      
      
        public ResultSet countcourse() {
    
    
        try{
       String query="Select count(*) From courses";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
        
          public ResultSet countdepartment() {
    
    
        try{
       String query="Select count(*) From departments";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
        
        
          
           
          public ResultSet countroom() {
    
    
        try{
       String query="Select count(*) From rooms";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
          
          
          
           
          public ResultSet countbatch() {
    
    
        try{
       String query="Select count(*) From batches";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
          
          
           
          public ResultSet counttime() {
    
    
        try{
       String query="Select count(*) From times";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
          
          
          
            public ResultSet countclasses() {
    
    
        try{
       String query="Select count(*) From classes";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
      
            
            
            public ResultSet counttimeslots() {
    
    
        try{
       String query="Select count(*) From timeslots";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
          
           
          public ResultSet counttimetable() {
    
    
        try{
       String query="Select count(*) From timetable";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
          
        public ResultSet countTimetableClasses() {
    
    
        try{
       String query="Select count(*) From timetable where RoomType=\"Class\" ";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }    
        
        public ResultSet countTimetableLabs() {
    
    
        try{
       String query="Select count(*) From timetable where RoomType=\"Lab\" ";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }    
          
          
          
          
              public ResultSet clashes() {
    
    
        try{
       String query1="Select Day From timetable";
       String query2="Select Day From timetable";
       String query3="Select Day From timetable";
        rs=st.executeQuery(query1);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
              
              
          
     
              
               public ResultSet getteacherdata(String tname) {
    
    
        try{
       String query="Select * From teachers where tname='"+tname+"'";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
               
               
                 public ResultSet checksem(String batch) {
    
    
        try{
       String query="Select * From batches where batch='"+batch+"'";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
               
                 public ResultSet getsec() {
    
    
        try{
       String query="Select * From batches";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
                 
                 
            
                 
                 
                   public ResultSet getcourse(String semester) {
    
    
        try{
       String query="Select * From courses where semester='"+semester+"'";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
                   
                   
                   
                   
                   
                   
               public void inserttassign(String tid,String tsec,String course,String tname,String course_type,String phone_num){
    
    try{
    String query="insert into assignteacher values('"+tid+"','"+tsec+"','"+course+"','"+tname+"','"+course_type+"','"+phone_num+"')";
    st.executeUpdate(query);
    }catch(Exception e){
    System.out.println(e);
    }
    
    }
               
               
                public ResultSet checkDuplicateEntry(String course,String Section) {
    
    
        try{
       String query="Select * From assignteacher where  secCourse='"+course+"' && tsec='"+Section+"'";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
                
                
               
                public ResultSet sreachtassign(String tid) {
    
    
        try{
       String query="Select * From assignteacher where tid='"+tid+"'";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
   
               
               
               
               
                public ResultSet countCourseType(String tid,String course_type) {
    
    
        try{
       String query="Select count(*) From assignteacher where tid='"+tid+"' && course_type='"+course_type+"'";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
                 
                public ResultSet gettassign() {
    
    
        try{
       String query="Select * From assignteacher";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
                
                
                
                
            public void deletetassign(String tid,String course,String Section) {
    
    
        try{
       String query="delete From assignteacher where tid='"+tid+"' && secCourse='"+course+"' && tsec='"+Section+"'";
       
        st.executeUpdate(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        
        
     }
            
            
             public void deleteAlltassign() {
    
    
        try{
       String query="delete From assignteacher";
       
        st.executeUpdate(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        
        
     }
            
            
                        
              public void updatetassign(String tsec,String course,String tid,String section,String cours){
    
    try{
    String query="update assignteacher set  tsec='"+tsec+"' , secCourse='"+course+"' where tid='"+tid+"' && tsec='"+section+"' && secCourse='"+cours+"' ";
    st.executeUpdate(query);
    }catch(Exception e){
    System.out.println(e);
    }
    
    }
              
              
      ////////////////////////////classes///////////////////////////////////////////////
              
                 public ResultSet chk_chours(String cname) {
    
    
        try{
       String query="Select chours From courses where cname='"+cname+"' ";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
                 
                 public ResultSet get_sec_shift(String sec) {
    
    
        try{
       String query="Select shift From batches where batch='"+sec+"' ";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
                 
                 
                 
         public void insertclass(String cname,String tname,String section,String ctype,String shift){
    
    try{
    String query="insert into classes values('"+cname+"','"+tname+"','"+section+"','"+ctype+"','"+shift+"')";
    st.executeUpdate(query);
    }catch(Exception e){
    System.out.println(e);
    }
    
    }
         
          public void deleteclass(String cname,String tname,String section) {
    
    
        try{
       String query="delete From classes where cname='"+cname+"' && tname='"+tname+"' && section='"+section+"'";
       
        st.executeUpdate(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        
        
     }
          
          
             public void deleteAllclass() {
    
    
        try{
       String query="delete From classes";
       
        st.executeUpdate(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        
        
     }
          
                 
              
              
              
              
              
              
              
                 public ResultSet countVisiting(String tid) {
    
    
        try{
       String query="Select count(*) From assignteacher where course_type=\"Visting\" && tid='"+tid+"' ";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
                 
                 
                 public ResultSet countRegular(String tid) {
    
    
        try{
       String query="Select count(*) From assignteacher where course_type=\"Regular\" && tid='"+tid+"' ";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
              
              
              
              
              
                public ResultSet gettimeslotes() {
    
    
        try{
       String query="Select * From timeslots";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
              
              
              
            
            
            
              public void inserttimeslotes(){
    
    try{
   // String query="INSERT INTO `timeslots` (SELECT days.days ,times.timing, rooms.RoomNumber ,rooms.RoomType,\"na\" from rooms,days,times GROUP BY days.days , rooms.RoomNumber,times.timing ORDER BY days.sNo,rooms.sNo,times.sNo)EXCEPT (SELECT * FROM timeslots)";
   String query="INSERT INTO `timeslots` (SELECT days.days ,times.timing, rooms.RoomNumber ,rooms.RoomType ,times.shift from rooms,days,times GROUP BY days.days , rooms.RoomNumber,times.timing ORDER BY rooms.sNo)";
   st.executeUpdate(query);
    }catch(Exception e){
    System.out.println(e);
    }
    
    }
      public void deletetimeslotes() {
    
    
        try{
       String query="delete From timeslots";
       
        st.executeUpdate(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        
        
     }
      
      
      
      
      public ResultSet getFreeSlotst() {
    
    
        try{
       String query="SELECT day,time,room,roomType,shift FROM (SELECT timeslots.day as day,timeslots.time as time,timeslots.room as room,timeslots.RoomType as roomType, timeslots.shift as shift FROM timeslots UNION ALL SELECT timetable.Day,timetable.Timing,timetable.ClassRoom,timetable.RoomType,timetable.shift FROM timetable) as tbl GROUP by day,time,room HAVING COUNT(*)=1";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
      
      
      
      
      ////////////////Teachers Report Methods
      
              public void insertTeacherReport(){
    
    try{
    String query="INSERT INTO report (SELECT teachers.tname,teachers.t_type,teachers.t_load,count(case when course_type = 'Regular' then 1 else null end) as Regular ,count(case when course_type = 'Visiting' then 1 else null end) as Visiting ,SUM(case when course_type = 'Regular' then 1 else 0 end)+SUM(case when course_type = 'Visiting' then 1 else 0 end) as Assign_load FROM assignteacher , teachers WHERE teachers.tid=assignteacher.tid GROUP BY tname)";
    st.executeUpdate(query);
    }catch(Exception e){
    System.out.println(e);
    }
      }
              
              
             
              
                  public void insertTeacherReportNotCommon(){
    
    try{
    String query="INSERT INTO report (SELECT tname,t_type,tload,regular,visiting,assign_load FROM ( SELECT DISTINCT teachers.tid as com ,teachers.tname as tname,teachers.t_type as t_type,teachers.t_load as tload,0 as regular,0 as visiting,0 assign_load FROM teachers UNION All SELECT DISTINCT assignteacher.tid,assignteacher.tname,\"\",assignteacher.tsec,0 ,0,0 FROM assignteacher) foo GROUP BY com HAVING COUNT(*)=1)";
    st.executeUpdate(query);
    }catch(Exception e){
    System.out.println(e);
    }
      }
              
              
              
              
               public void deleteTeacherReport() {
    
    
        try{
       String query="delete From report";
       
        st.executeUpdate(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        
        
     }
               
                 
                public ResultSet getTeacherReport() {
    
    
        try{
       String query="Select * From report";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
          
                
              //////////////////////Sections Report Methods  
                
                
                
                public void insertSectionReport(){
    
    try{
    String query="INSERT INTO sectionreport (SELECT batches.batch,batches.semester,batches.t_load,COUNT(assignteacher.tsec) as Assign_load FROM assignteacher , batches WHERE batches.batch=assignteacher.tsec GROUP BY assignteacher.tsec)";
    st.executeUpdate(query);
    }catch(Exception e){
    System.out.println(e);
    }
      }
              
              
             
              
                  public void insertSectionReportNotCommon(){
    
    try{
    String query="INSERT INTO sectionreport (SELECT batch,semester,t_load,assign_load FROM ( SELECT DISTINCT batches.batch as batch ,batches.semester as semester,batches.t_load as t_load,0 assign_load FROM batches UNION All SELECT DISTINCT assignteacher.tsec,assignteacher.secCourse,0,0 FROM assignteacher) foo GROUP BY batch HAVING COUNT(*)=1)";
    st.executeUpdate(query);
    }catch(Exception e){
    System.out.println(e);
    }
      }
              
              
              
              
               public void deleteSectionReport() {
    
    
        try{
       String query="delete From sectionreport";
       
        st.executeUpdate(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        
        
     }
               
                 
                public ResultSet getSectionReport() {
    
    
        try{
       String query="Select * From sectionreport";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
                
                
                
                
                
                
                  
                  
                  
                  
                  
    //////////////////////////////////////time table///////////////////////////
         
        
//            public void inserttimetable(){
//    
//    try{
//    String query="INSERT INTO timetable (SELECT t1.cname,t1.tname,t1.section,t2.day,t2.time,t2.room,t1.ctype FROM (SELECT day,time,room,RoomType,ROW_NUMBER() OVER(ORDER BY day) as rn FROM timeslots) as t2 JOIN (SELECT cname,tname,section,ctype,ROW_NUMBER() OVER(ORDER BY cname) as rn FROM classes) as t1 ON t1.rn=t2.rn)";
// 
//   st.executeUpdate(query);
//    }catch(Exception e){
//    System.out.println(e);
//    }
//      }          
                  
      
                
                
           
                 public void inserttimetable(String cname,String tname,String section,String day,String time,String room,String ctype,String shift){
    
    try{
    String query="INSERT INTO timetable values('"+cname+"','"+tname+"','"+section+"','"+day+"','"+time+"','"+room+"','"+ctype+"','"+shift+"')EXCEPT (SELECT * FROM timetable)";
 
   st.executeUpdate(query);
    }catch(Exception e){
    System.out.println(e);
    }
      }
                 
                 
                  public void insertignoreTTdata(String cname,String tname,String section,String ctype,String shift){
    
    try{
    String query="insert into ignoretimetabledata(cname,tname,section,ctype,shift) values('"+cname+"','"+tname+"','"+section+"','"+ctype+"','"+shift+"')";
    st.executeUpdate(query);
    }catch(Exception e){
    System.out.println(e);
    }
    
    }
                  
                  
                   public ResultSet getIgnoreTTdata() {
    
    
        try{
       String query="Select * From ignoretimetabledata";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }  
                   
                   
                      public void deleteIgnoreTTdata() {
    
    
        try{
       String query="delete From ignoretimetabledata";
       
        st.executeUpdate(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        
        
     }
                      
                      public void deleteIgnoreTTclass(String sNo,String cname,String tname,String section,String ctype) {
    
    
        try{
       String query="delete From ignoretimetabledata where  sNo='"+sNo+"' && cname='"+cname+"' && tname='"+tname+"' && section='"+section+"' && ctype='"+ctype+"' ";
       
        st.executeUpdate(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        
        
     }
                      
                
                
                public ResultSet getMorningClasses() {
    
    
        try{
       String query="Select * From classes where ctype=\"Class\" && shift=\"Morning\" ";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }  
                
                
                 public ResultSet getMorningLabs() {
    
    
        try{
       String query="Select * From classes where ctype=\"Lab\" && shift=\"Morning\" ";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
                 
                 
                  public ResultSet getMorningClassRooms() {
    
    
        try{
       String query="SELECT day,time,room,roomType,shift FROM (SELECT timeslots.day as day,timeslots.time as time,timeslots.room as room,timeslots.RoomType as roomType, timeslots.shift as shift FROM timeslots WHERE timeslots.RoomType=\"Class\" && timeslots.shift=\"Morning\" UNION ALL SELECT timetable.Day,timetable.Timing,timetable.ClassRoom,timetable.RoomType, timetable.shift FROM timetable WHERE timetable.RoomType=\"Class\" && timetable.shift=\"Morning\") as tbl GROUP by day,time,room HAVING COUNT(*)=1";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
                  
                  
                   public ResultSet getMorningLabRooms() {
    
    
        try{
       String query="SELECT day,time,room,roomType,shift FROM (SELECT timeslots.day as day,timeslots.time as time,timeslots.room as room,timeslots.RoomType as roomType, timeslots.shift as shift FROM timeslots WHERE timeslots.RoomType=\"Lab\" && timeslots.shift=\"Morning\" UNION ALL SELECT timetable.Day,timetable.Timing,timetable.ClassRoom,timetable.RoomType, timetable.shift FROM timetable WHERE timetable.RoomType=\"Lab\" && timetable.shift=\"Morning\") as tbl GROUP by day,time,room HAVING COUNT(*)=1";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
                   
                   
                     public ResultSet getEveningClasses() {
    
    
        try{
       String query="Select * From classes where ctype=\"Class\" && shift=\"Evening\" ";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }  
                
                
                 public ResultSet getEveningLabs() {
    
    
        try{
       String query="Select * From classes where ctype=\"Lab\" && shift=\"Evening\" ";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
                 
                 
                  public ResultSet getEveningClassRooms() {
    
    
        try{
       String query="SELECT day,time,room,roomType,shift FROM (SELECT timeslots.day as day,timeslots.time as time,timeslots.room as room,timeslots.RoomType as roomType, timeslots.shift as shift FROM timeslots WHERE timeslots.RoomType=\"Class\" && timeslots.shift=\"Evening\" UNION ALL SELECT timetable.Day,timetable.Timing,timetable.ClassRoom,timetable.RoomType, timetable.shift FROM timetable WHERE timetable.RoomType=\"Class\" && timetable.shift=\"Evening\") as tbl GROUP by day,time,room HAVING COUNT(*)=1";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
                  
                  
                   public ResultSet getEveningLabRooms() {
    
    
        try{
       String query="SELECT day,time,room,roomType,shift FROM (SELECT timeslots.day as day,timeslots.time as time,timeslots.room as room,timeslots.RoomType as roomType, timeslots.shift as shift FROM timeslots WHERE timeslots.RoomType=\"Lab\" && timeslots.shift=\"Evening\" UNION ALL SELECT timetable.Day,timetable.Timing,timetable.ClassRoom,timetable.RoomType, timetable.shift FROM timetable WHERE timetable.RoomType=\"Lab\" && timetable.shift=\"Evening\") as tbl GROUP by day,time,room HAVING COUNT(*)=1";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
                   
                   
                   
                   
                   
                public void deleteTimeTableClasses() {
    
    
        try{
       String query="delete From timetable where RoomType=\"Class\"";
       
        st.executeUpdate(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        
        
     }
            
             public void deleteTimeTableLabs() {
    
    
        try{
       String query="delete From timetable where RoomType=\"Lab\"";
       
        st.executeUpdate(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        
        
     }
               public void deletetimetable() {
    
    
        try{
       String query="delete From timetable";
       
        st.executeUpdate(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        
        
     }
               
               
               
               
                public ResultSet chkRoomSameTimeClash(String day,String time,String room) {
    
    
        try{
       String query="Select * From timetable where  Day='"+day+"' && Timing='"+time+"' && ClassRoom='"+room+"' ";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
                  
      }
               
               
               
               
               
                public ResultSet chkSecSameTimeClash(String section,String day,String time) {
    
    
        try{
       String query="Select * From timetable where Section='"+section+"' && Day='"+day+"' && Timing='"+time+"' ";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
                  
      }
                
                
                
                  public ResultSet chkTecherSameTimeClash(String tname,String day,String time) {
    
    
        try{
       String query="Select * From timetable where CourseCoordinator='"+tname+"' && Day='"+day+"' && Timing='"+time+"' ";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
                  
      }
                  
                  
                    public ResultSet countTeacherDailyClasses(String tname,String day) {
    
    
        try{
       String query="Select count(*) From timetable where CourseCoordinator='"+tname+"' && Day='"+day+"' && RoomType=\"Class\"";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
                    
                     public ResultSet countSectionDailyClasses(String section,String day) {
    
    
        try{
       String query="Select count(*) From timetable where Section='"+section+"' && Day='"+day+"' && RoomType=\"Class\" ";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
               
                     
                    public ResultSet countTeacherDailyLabs(String tname,String day) {
    
    
        try{
       String query="Select count(*) From timetable where CourseCoordinator='"+tname+"' && Day='"+day+"' && RoomType=\"Lab\"";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
                    
                     public ResultSet countSectionDailyLabs(String section,String day) {
    
    
        try{
       String query="Select count(*) From timetable where Section='"+section+"' && Day='"+day+"' && RoomType=\"Lab\" ";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
                     
                     
                     
                     
                     public ResultSet chkTeacherWishTime(String tname) {
    
    
        try{
       String query="Select * From wishtime where tname='"+tname+"' ";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
                     
                     
                     
                     
                     
                     
                     
          
                                
              public void updateForSwap(String day,String timing,String room,String tname,String section,String day1,String timing1,String room1){
    
    try{
    String query="update timetable set  Day='"+day+"' , Timing='"+timing+"' ,ClassRoom='"+room+"' where CourseCoordinator='"+tname+"' && Section='"+section+"' && Day='"+day1+"' && , Timing='"+timing1+"' && ClassRoom='"+room1+"' ";
    st.executeUpdate(query);
    }catch(Exception e){
    System.out.println(e);
    }
    
    }
              
              
           
                     
                     
               
            
            
                  
                  
        public ResultSet gettimetable() {
    
    
        try{
       String query="Select * From timetable";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
                  
      }  
        
        
        ////////////wish time///////////////
        
            public void insertwishtime(String tname,String time){
    
    try{
    String query="insert into wishtime values('"+tname+"','"+time+"')";
    st.executeUpdate(query);
    }catch(Exception e){
    System.out.println(e);
    }
    
    }
               
               
               
                public void deletewishtime(String tname,String time) {
    
    
        try{
       String query="delete From wishtime where tname='"+tname+"' && time='"+time+"'";
       
        st.executeUpdate(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        
        
     }
                
                
                
                
                       
          
              
              
     public ResultSet getwishtime() {
    
    
        try{
       String query="Select * From wishtime";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
     
     public ResultSet searchwishtime(String tname,String time) {
    
    
        try{
       String query="Select * From wishtime where tname='"+tname+"' && time='"+time+"'";
       
        rs=st.executeQuery(query);
        
        }catch(Exception e){
    System.out.println(e);
    }
        return rs;
        
     }
               
                    
                
                  
                  
              
              
            
            
//INSERT INTO `timeslots` (SELECT days.days ,times.timing, rooms.RoomNumber ,"na" from rooms,days,times GROUP BY days.days , rooms.RoomNumber,times.timing ORDER BY days.sNo,rooms.sNo,times.sNo)
//INSERT INTO report (SELECT teachers.tname,teachers.t_load,count(case when course_type = 'Regular' then 1 else null end) as Regular ,count(case when course_type = 'Visiting' then 1 else null end) as Visiting ,SUM(case when course_type = 'Regular' then 1 else 0 end)+SUM(case when course_type = 'Visiting' then 1 else 0 end) as Assign_load FROM assignteacher , teachers WHERE teachers.tid=assignteacher.tid GROUP BY tname)      
// which are not common in both tables for roport >>>> SELECT tname,tload FROM ( SELECT DISTINCT teachers.tid as com ,teachers.tname as tname,teachers.t_load as tload FROM teachers UNION All SELECT DISTINCT assignteacher.tid,assignteacher.tname,assignteacher.tsec FROM assignteacher) foo GROUP BY com HAVING COUNT(*)=1

//SELECT assignteacher.secCourse,assignteacher.tname,assignteacher.tsec,timeslots.day,timeslots.time,timeslots.room FROM assignteacher,timeslots WHERE assignteacher.sNo=timeslots.sNo ORDER BY rand()
                    
/*SELECT t1.secCourse,t1.tname,t1.tsec,t2.day,t2.time,t2.room FROM (SELECT day,time,room,ROW_NUMBER() OVER(ORDER BY day) as rn FROM timeslots) as t2 LEFT JOIN (SELECT secCourse,tname,tsec,ROW_NUMBER() OVER(ORDER BY secCourse) as rn FROM assignteacher) as t1 ON t1.rn=t2.rn
UNION
SELECT t1.secCourse,t1.tname,t1.tsec,t2.day,t2.time,t2.room FROM (SELECT day,time,room,ROW_NUMBER() OVER(ORDER BY day) as rn FROM timeslots) as t2 RIGHT JOIN (SELECT secCourse,tname,tsec,ROW_NUMBER() OVER(ORDER BY secCourse) as rn FROM assignteacher) as t1 ON t1.rn=t2.rn*/
}