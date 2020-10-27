/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neu.edu.csye6200.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import neu.edu.csye6200.data.DataStore;
import neu.edu.csye6200.interfaces.ImmunizationDataManagement;
import neu.edu.csye6200.interfaces.ImmunizationDataManagementFactory;
import neu.edu.csye6200.interfaces.StudentDataManagement;
import neu.edu.csye6200.interfaces.StudentDataMangementFactory;
import neu.edu.csye6200.model.ClassRoom;
import neu.edu.csye6200.model.Immunization;
import neu.edu.csye6200.model.Rules;
import neu.edu.csye6200.model.Student;
import neu.edu.csye6200.userInterface.StudentJPanel;

public class StudentController {
    private StudentJPanel studentJPanel;
    private Student studentCurrent;
    private Immunization immunCurrent=null;
    private Map<String,String> m = new HashMap<>();
    private static StudentController instance =null;
     public static StudentController getInstance(StudentJPanel studentJPanel){
            instance = new StudentController(studentJPanel);
        return instance;
    }

     
     
     
     
    private StudentController(StudentJPanel studentJPanel) {
        this.studentJPanel = studentJPanel;
        upDate();
        
        
    }
    public void upDate(){
             this.m.put("1", "HiB");
        this.m.put("2", "Varicella");
        this.m.put("3", "TDaP");
        this.m.put("4", "Polio");
        this.m.put("5", "MMR");
        
        this.studentJPanel.getUpdateStudentBtn().addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            try{
               Integer.parseInt(studentJPanel.getTxtFieldStudentId().getText());
               
            }
            catch(NumberFormatException exception){
               JOptionPane.showMessageDialog(null, "Please enter an integer for id!","Warning",JOptionPane.WARNING_MESSAGE);
               return;
            }
           int  id  = Integer.parseInt(studentJPanel.getTxtFieldStudentId().getText());
           List<Student> student=  StudentDataMangementFactory.getFactoryInstance().getObject().getDataList(id);
           if(student.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter a valid student id!","Warning",JOptionPane.WARNING_MESSAGE);
                return;
           }
           else {
               studentJPanel.updateStudent();
               studentCurrent = student.get(0);
               fillImmunizationRecords(student.get(0));
               fillStudentRecords(student.get(0));
               updateStudentRecords(student.get(0));
               List<Immunization> alert =ImmunizationDataManagementFactory.getFactoryInstance().getObject().getDataList(id);
               for(Immunization a : alert){
                   int exp=Character. getNumericValue(a.getDuration().charAt(0));
                   
                   String date = new SimpleDateFormat("dd-MM-yyyy").format(a.getImmunDate());
                   String currDate= new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                   int currMonth=Integer.parseInt(currDate.split("-")[1]);
                   int month  =Integer.parseInt(date.split("-")[1]);
                   
                   if(exp-(currMonth-month)>0){
                       int due = exp-(currMonth-month);
                       
                   
                   studentJPanel.getUpdateStudentJPanel().getAlert().append(a.getImmunName()+" due in "+due+" Months"+'\n');}
               }
                List<Student> stu =StudentDataMangementFactory.getFactoryInstance().getObject().getDataList(id);
                 String currDate= new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                  int currMonth=Integer.parseInt(currDate.split("-")[1]);
                  int renew=0;
                for(Student s:stu){
                    
                   String reg = new SimpleDateFormat("dd-MM-yyyy").format(s.getRegisterTime());
                 System.out.println(reg);
                   renew=12-(currMonth-Integer.parseInt(reg.split("-")[1])); 
                   }   
                studentJPanel.getUpdateStudentJPanel().getjTextArea2().append("Renew in "+renew+" Months"+'\n');
                
           
           }
           
      }});
      
       this.studentJPanel.getBtnAddStudent().addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            studentJPanel.addStudent();
           addNewStudent();
      }});
   
        
    }
    
    
    public void fillStudentRecords(Student s){
        studentJPanel.getUpdateStudentJPanel().getTxtFieldFName().setText(s.getFirstName());
        studentJPanel.getUpdateStudentJPanel().getTxtFieldLName().setText(s.getLastName());
        studentJPanel.getUpdateStudentJPanel().getTxtFieldAge().setText(Integer.toString(s.getAge()));
        studentJPanel.getUpdateStudentJPanel().getTxtFieldFatherName().setText(s.getFatherName());
        studentJPanel.getUpdateStudentJPanel().getTxtFieldMotherName().setText(s.getMotherName());
        studentJPanel.getUpdateStudentJPanel().getTxtFieldStudentId().setText(Integer.toString(s.getStuId()));
    }
    public void updateStudentRecords(Student s){
       
        System.out.println(s.getAge());
        this.studentJPanel.getUpdateStudentJPanel().getBtnUpdate().addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
        String fName = studentJPanel.getUpdateStudentJPanel().getTxtFieldFName().getText();
        String lName = studentJPanel.getUpdateStudentJPanel().getTxtFieldLName().getText();
        String age =  studentJPanel.getUpdateStudentJPanel().getTxtFieldAge().getText();
        String fatherName = studentJPanel.getUpdateStudentJPanel().getTxtFieldFatherName().getText();
        String motherName = studentJPanel.getUpdateStudentJPanel().getTxtFieldMotherName().getText();
        
            try{
               Integer.parseInt(studentJPanel.getUpdateStudentJPanel().getTxtFieldAge().getText());
            }
            catch(NumberFormatException exception){
               JOptionPane.showMessageDialog(null, "Please enter an integer for age!","Warning",JOptionPane.WARNING_MESSAGE);
               return;
            }
            Student student = new Student(fName,lName,s.getRegisterTime(),s.getStuId(),Integer.parseInt(age),fatherName,motherName);
            studentCurrent = student;
            StudentDataMangementFactory.getFactoryInstance().getObject().updateOneObject(student);
            JOptionPane.showMessageDialog(null, "Updated Successfully!");
            fillStudentRecords(student);
            
      }});
         this.studentJPanel.getUpdateStudentJPanel().getjButton1().addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
                       int  id  = Integer.parseInt(studentJPanel.getTxtFieldStudentId().getText());

                            List<Student> stu =StudentDataMangementFactory.getFactoryInstance().getObject().getDataList(id);
                            stu.get(0).setRegisterTime(new Date());
                       JOptionPane.showMessageDialog(null, "Renew Successful","Thank You !",JOptionPane.WARNING_MESSAGE);
                studentJPanel.getUpdateStudentJPanel().getjTextArea2().setText("Due in 12 months");


        }});
        
      this.studentJPanel.getUpdateStudentJPanel().getBtnDeleteImmun().addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
             deleteImmunization();
           
      }});
      this.studentJPanel.getUpdateStudentJPanel().getBtnAddImmun().addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            studentJPanel.getUpdateStudentJPanel().addImmunization();
             addNewImmunization();
           
      }});
       this.studentJPanel.getUpdateStudentJPanel().getBtnUpdateImmun().addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
             updateImmunization();
           
      }});
    }
    public javax.swing.JPanel getStudentJpanel(){
        return this.studentJPanel;}
    public void updateImmunization(){
         
         
        int selectedRow = studentJPanel.getUpdateStudentJPanel().getTblImmunInfo().getSelectedRow();
             if(selectedRow>=0){
                String immunId = (String) studentJPanel.getUpdateStudentJPanel().getTblImmunInfo().getValueAt(selectedRow, 1);
                int studentId = Integer.parseInt(studentJPanel.getTxtFieldStudentId().getText());
                List<Immunization> immuns = ImmunizationDataManagementFactory.getFactoryInstance().getObject().getDataList(studentId,Integer.parseInt(immunId));
                if(!immuns.isEmpty()){
                   immunCurrent = immuns.get(0);
                    this.studentJPanel.getUpdateStudentJPanel().updateImmunization();
                    fillRecordsForImmunization(immunCurrent);
                }  
        }
        else {
            // if user didn't selected a row show message
            JOptionPane.showMessageDialog(null, "Please select a row from table first to update!","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        studentJPanel.getUpdateStudentJPanel().getUpdateImmunJPanel().getBtnUpdateImmunization().addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
                updateImmunizationRecord(immunCurrent);
      }});
      
        
    }
      public void updateImmunizationRecord(Immunization i){
          if(i!=null){
            Date dateCorrected = new Date();
            String immunName = studentJPanel.getUpdateStudentJPanel().getUpdateImmunJPanel().getTextFieldName().getText();
            String duration = studentJPanel.getUpdateStudentJPanel().getUpdateImmunJPanel().getTextFieldDuration().getText();
            String date = studentJPanel.getUpdateStudentJPanel().getUpdateImmunJPanel().getTextFieldDate().getText();
            String id  = studentJPanel.getUpdateStudentJPanel().getUpdateImmunJPanel().getTextFieldId().getText();
            if(immunName.equals("") || duration.equals("") || date.equals("")||id.equals("")){
                JOptionPane.showMessageDialog(null,"All fields are required!");
                return;
                
            }
            try {
                dateCorrected=new SimpleDateFormat("dd/MM/yyyy").parse(date);
            } catch (ParseException ex) {
                Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
            boolean status = false;
            if(studentJPanel.getUpdateStudentJPanel().getUpdateImmunJPanel().getRadioBtnYes().isSelected()){
                status = true;
            }
            Immunization updated = new Immunization(studentCurrent.getStuId(),Integer.parseInt(id),immunName,duration,dateCorrected,status);
            ImmunizationDataManagementFactory.getFactoryInstance().getObject().updateOneObject(updated);
            JOptionPane.showMessageDialog(null,"Updated Successfully");
            fillRecordsForImmunization(updated);

          }
          
      }
        
    
    public void fillRecordsForImmunization(Immunization i){
        // displaying date
        Format f = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = f.format(i.getImmunDate());
        studentJPanel.getUpdateStudentJPanel().getUpdateImmunJPanel().getTextFieldName().setText(i.getImmunName());
        studentJPanel.getUpdateStudentJPanel().getUpdateImmunJPanel().getTextFieldDuration().setText(i.getDuration());
        studentJPanel.getUpdateStudentJPanel().getUpdateImmunJPanel().getTextFieldDate().setText(strDate);
        studentJPanel.getUpdateStudentJPanel().getUpdateImmunJPanel().getTextFieldId().setText(Integer.toString(i.getImmunId()));
        if(i.isStatus()){
           studentJPanel.getUpdateStudentJPanel().getUpdateImmunJPanel().getRadioBtnYes().setSelected(true);
        }
        else {
            studentJPanel.getUpdateStudentJPanel().getUpdateImmunJPanel().getRaadioBtnNo().setSelected(true);
        }
    }
    public void addNewImmunization(){
       
       
studentJPanel.getUpdateStudentJPanel().getAddImmunJPanel().getTextFieldName().setText(m.get((String)studentJPanel.getUpdateStudentJPanel().getAddImmunJPanel().getImmId().getSelectedItem()));
     
     this.studentJPanel.getUpdateStudentJPanel().getAddImmunJPanel().getImmId().addActionListener (new ActionListener () {
    public void actionPerformed(ActionEvent e) {
      studentJPanel.getUpdateStudentJPanel().getAddImmunJPanel().getTextFieldName().setText(m.get((String)studentJPanel.getUpdateStudentJPanel().getAddImmunJPanel().getImmId().getSelectedItem()));
     
    }
});
        studentJPanel.getUpdateStudentJPanel().getAddImmunJPanel().getBtnAddImmunization().addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            Date dateCorrected = new Date();
             String immunName = m.get((String)studentJPanel.getUpdateStudentJPanel().getAddImmunJPanel().getImmId().getSelectedItem());

            String duration = studentJPanel.getUpdateStudentJPanel().getAddImmunJPanel().getTextFieldDuration().getText();
            String date = studentJPanel.getUpdateStudentJPanel().getAddImmunJPanel().getTextFieldDate().getText();
            //String id  = studentJPanel.getUpdateStudentJPanel().getAddImmunJPanel().getTextFieldId().getText();
            if(immunName.equals("") || duration.equals("") || date.equals("")){
                JOptionPane.showMessageDialog(null,"All fields are required!");
                return; 
            }
            try {
                dateCorrected=new SimpleDateFormat("dd/MM/yyyy").parse(date);
            } catch (ParseException ex) {
                Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
            boolean status = false;
            if(studentJPanel.getUpdateStudentJPanel().getAddImmunJPanel().getRadioBtnYes().isSelected()){
                status = true;
            }
            Immunization newImmun = new Immunization(studentCurrent.getStuId(),0,immunName,duration,dateCorrected,status);
            ImmunizationDataManagementFactory.getFactoryInstance().getObject().registerOneObject(newImmun);
            JOptionPane.showMessageDialog(null,"Added Successfully");
            fillEmptyRecordsForImmunization();
                
      }});
    }
      public void fillEmptyRecordsForImmunization(){
          studentJPanel.getUpdateStudentJPanel().getAddImmunJPanel().getTextFieldName().setText("");
          studentJPanel.getUpdateStudentJPanel().getAddImmunJPanel().getTextFieldDuration().setText("");
          studentJPanel.getUpdateStudentJPanel().getAddImmunJPanel().getTextFieldDate().setText("");
          studentJPanel.getUpdateStudentJPanel().getAddImmunJPanel().getRadioBtnYes().setSelected(false);
          studentJPanel.getUpdateStudentJPanel().getAddImmunJPanel().getRaadioBtnNo().setSelected(false);
      }
        
        
    
    public void deleteImmunization(){
        int selectedRow = studentJPanel.getUpdateStudentJPanel().getTblImmunInfo().getSelectedRow();
             if(selectedRow>=0){
                String immunId = (String) studentJPanel.getUpdateStudentJPanel().getTblImmunInfo().getValueAt(selectedRow, 1);
                int studentId = Integer.parseInt(studentJPanel.getTxtFieldStudentId().getText());
                List<Immunization> immuns = ImmunizationDataManagementFactory.getFactoryInstance().getObject().getDataList(studentId,Integer.parseInt(immunId));
                if(!immuns.isEmpty()){
                    Immunization i= immuns.get(0);
                    ImmunizationDataManagementFactory.getFactoryInstance().getObject().deleteOneObject(i);
                     JOptionPane.showMessageDialog(null,"Deleted Successfully!");
                     fillImmunizationRecords(studentCurrent);
                }
              
        }
        else {
            // if user didn't selected a row show message
            JOptionPane.showMessageDialog(null, "Please select a row from table first to delete!","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
    }
    
    public void fillImmunizationRecords(Student s){
        System.out.println(s.getStuId());
        List<Object> immunizations =ImmunizationDataManagementFactory.getFactoryInstance().getObject().getDataList(s.getStuId()) ;
        DefaultTableModel dtm = (DefaultTableModel)studentJPanel.getUpdateStudentJPanel().getTblImmunInfo().getModel();
        dtm.setRowCount(0);
        for(Object immun : immunizations){
            Immunization i = (Immunization)immun;
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(i.getImmunDate());
            // create a row and pouplate each rules data  in the row
            Object[] row = new Object[dtm.getColumnCount()];
            row[0] = i.getImmunName();
            row[1]= Integer.toString(i.getImmunId());
            row[2]= date;
            row[3]= i.isStatus();
            Date immunDate=i.getImmunDate();
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(immunDate);
            LocalDate now=LocalDate.now();
            Date dateNow=java.sql.Date.valueOf(now);
            if(now.getMonthValue()==calendar.get(Calendar.MONTH) && now.getDayOfMonth()==calendar.get(Calendar.DAY_OF_MONTH)){
                row[4]="Today is Immunization Anniversary!!";
            }
            else{
                LocalDate ld=immunDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                while(ld.isBefore(now)){
                    ld=ld.plusYears(1);
                }
                long days=ChronoUnit.DAYS.between(ld,now);
                row[4]="Immunization Anniversay after "+Math.abs(days)+" days";
            }
            dtm.addRow(row);

    }  
   }
    
   public void addNewStudent(){
       
       this.studentJPanel.getAddStudentJPanel().getBtnAdd().addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
        String fName = studentJPanel.getAddStudentJPanel().getTxtFieldFName().getText();
        String lName = studentJPanel.getAddStudentJPanel().getTxtFieldLName().getText();
        String age =  studentJPanel.getAddStudentJPanel().getTxtFieldAge().getText();
        String fatherName = studentJPanel.getAddStudentJPanel().getTxtFieldFatherName().getText();
        String motherName = studentJPanel.getAddStudentJPanel().getTxtFieldMotherName().getText();
            try{
               Integer.parseInt(studentJPanel.getAddStudentJPanel().getTxtFieldAge().getText());
            }
            catch(NumberFormatException exception){
               JOptionPane.showMessageDialog(null, "Please enter an integer for age!","Warning",JOptionPane.WARNING_MESSAGE);
               return;
            }
            if(studentsOfClassRoom(Integer.parseInt(age))){
                Student student = new Student(fName,lName,new Date(),0,Integer.parseInt(age),fatherName,motherName);
                StudentDataMangementFactory.getFactoryInstance().getObject().registerOneObject(student);
                JOptionPane.showMessageDialog(null, "Added Successfully!");
            }
            else {
                JOptionPane.showMessageDialog(null, "Registration for this age is full. Try another Time!");
            }
            emptyAddJPanel();
            
      }});
       
   }
   public boolean studentsOfClassRoom(int age){
            List<Student> students = new ArrayList<>();
            ClassRoom cl=null;
            for(ClassRoom c: DataStore.getInstance().getClassrooms()){
                if(age>c.getMinAge() && age<=c.getMaxAge()){
                    cl =c;
                    break;
                }
            }
            if(cl!=null){
                for(Object s: StudentDataMangementFactory.getFactoryInstance().getObject().getDataList()){
                   Student st = (Student)s;
                if(st.getAge()> cl.getMinAge() && st.getAge()<=cl.getMaxAge()){
                   students.add(st);
                }
            }
            if(cl.getSize()>students.size()){
                return true;
            }  
            }
            
            return false;
        }
   public void emptyAddJPanel(){
        studentJPanel.getAddStudentJPanel().getTxtFieldFName().setText("");
        studentJPanel.getAddStudentJPanel().getTxtFieldLName().setText("");
        studentJPanel.getAddStudentJPanel().getTxtFieldAge().setText("");
        studentJPanel.getAddStudentJPanel().getTxtFieldFatherName().setText("");
        studentJPanel.getAddStudentJPanel().getTxtFieldMotherName().setText("");
       
   }
    
   
}
