
package neu.edu.csye6200.controller;

import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import neu.edu.csye6200.data.DataStore;
import neu.edu.csye6200.interfaces.StudentDataMangementFactory;
import neu.edu.csye6200.interfaces.TeacherDataManagementFactory;
import neu.edu.csye6200.model.ClassRoom;
import neu.edu.csye6200.model.Rules;
import neu.edu.csye6200.model.Student;
import neu.edu.csye6200.model.Teacher;
import neu.edu.csye6200.userInterface.ClassRoomJPanel;
import neu.edu.csye6200.userInterface.ViewStudentJPanel;

public class ClassRoomController {
    private ViewStudentJPanel viewJPanel;
    private ClassRoomJPanel classRoomPanel;
    
    private static ClassRoomController instance;
    public static ClassRoomController getInstance(ClassRoomJPanel classRoomPanel) {
            instance = new ClassRoomController(classRoomPanel);
            return instance;
    }
    private ClassRoomController(ClassRoomJPanel classRoomPanel) {
        this.classRoomPanel = classRoomPanel;
    }
    public void setTableRecords(){
        DefaultTableModel dtm = (DefaultTableModel)classRoomPanel.getTblClassRooms().getModel();
        dtm.setRowCount(0);
        for(Rules rule : DataStore.getInstance().getRules()){
            // create a row and pouplate each rules data  in the row
            Object[] row = new Object[dtm.getColumnCount()];
            if(rule.getMaxAge() == Integer.MAX_VALUE){
               row[0]=rule.getMinAge() + "- and up"; 
            }
            else {
                row[0]=rule.getMinAge() + "-" + rule.getMaxAge();
            }
            row[1]=rule.getGroupSize();
            row[2]=rule.getRatio();
            row[3]= rule.getMaxGroup();
            dtm.addRow(row);
        } 
        this.classRoomPanel.getBtnShowDetail().addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            // selectedRow contains row selected
        int selectedRow = classRoomPanel.getTblClassRooms().getSelectedRow();
        if(selectedRow>=0){
              // if user selected a row then open ViewJPanel and display data of selected row
              String age = (String) classRoomPanel.getTblClassRooms().getValueAt(selectedRow, 0);
              String minAge[] = age.split("-");
              ClassRoom c = getClassRoom(Integer.parseInt(minAge[0]));
              viewJPanel = new ViewStudentJPanel(classRoomPanel.getUserProcessControlJPanel());
              populateTableWithStudents(c);
              classRoomPanel.getUserProcessControlJPanel().add("ViewStudentJPanel",viewJPanel);
             CardLayout layout = (CardLayout) classRoomPanel.getUserProcessControlJPanel().getLayout();
              layout.next(classRoomPanel.getUserProcessControlJPanel());
        }
        else {
            // if user didn't selected a row show message
            JOptionPane.showMessageDialog(null, "Please select a row from table first to view Details!","Warning",JOptionPane.WARNING_MESSAGE);
        }
      }});
    }
    public void populateTableWithStudents( ClassRoom c){
        viewJPanel.getTextFieldMinAge1().setText(Integer.toString(c.getMinAge()));
        viewJPanel.getTextFieldMaxAge().setText(Integer.toString(c.getMaxAge()));
        viewJPanel.getTextFieldStudentsSize().setText(Integer.toString(c.getSize()));
        viewJPanel.getTextFieldTeachersSize().setText(Integer.toString(c.getTeachersSize()));
        viewJPanel.getTextFieldClassRoomName().setText(c.getName());
        DefaultTableModel dtm = (DefaultTableModel)viewJPanel.getTblStudents().getModel();
        dtm.setRowCount(0);
        List<Student> students = StudentDataMangementFactory.getFactoryInstance().getObject().getDataList(c.getMinAge(),c.getMaxAge());
        System.out.println(c.getMinAge()+" " + c.getMaxAge());
        for(Student s :students){
            // create a row and pouplate  data  in the row
            Object[] row = new Object[dtm.getColumnCount()];
            row[0]=s.firstName;
            row[1]=s.lastName;
            row[2]=s.getAge();
            row[3]= s.getStuId();
            dtm.addRow(row);
        }
        populateTeacherRecords(c);
    }
    public void populateTeacherRecords(ClassRoom c){
        List<Teacher> teachers = TeacherDataManagementFactory.getFactoryInstance().getObject().getDataList(c.getName());
        DefaultTableModel dtm = (DefaultTableModel)viewJPanel.getTblTeacherRecord().getModel();
        dtm.setRowCount(0);
        if(!teachers.isEmpty()){
            for(Teacher t :teachers){
                Object[] row = new Object[dtm.getColumnCount()];
                row[0]=t.getFirstName();
                row[1]=t.getLastName();
                String pattern = "dd/MM/yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format(t.getRegisterTime());
                row[2]=date;
                row[3]=t.getTeachID();
                dtm.addRow(row);  
            } 
        }
        viewJPanel.getBtnStudenAssignedToATeacher().addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            displayRecordForTeacher(c);
        }}); 
    }
    public void displayRecordForTeacher(ClassRoom c){
        int selectedRow = viewJPanel.getTblTeacherRecord().getSelectedRow();
        DefaultTableModel dtm = (DefaultTableModel)viewJPanel.getTblStudentRecordForTeacher().getModel();
        if(selectedRow>=0){
            String teacherFirstName = (String) viewJPanel.getTblTeacherRecord().getValueAt(selectedRow, 0);
            List<Student> students = StudentDataMangementFactory.getFactoryInstance().getObject().getDataList(c.getMinAge(),c.getMaxAge());
            Rules r = getRule(c.getMinAge(),c.getMaxAge());
            if(selectedRow ==0){
                dtm.setRowCount(0);
                for(int i=0;i<r.getGroupSize() && i<students.size();i++){
                    Object[] row = new Object[dtm.getColumnCount()];
                    Student s = students.get(i);
                    row[0]= s.getFirstName();
                    row[1]=s.getLastName();
                    row[2]=s.getStuId();
                    row[3]=teacherFirstName;
                    dtm.addRow(row);  
                }
            }
            else if(selectedRow ==1){
                dtm.setRowCount(0);
                int count = r.getGroupSize() + r.getGroupSize();
                for(int i=r.getGroupSize();i< count && i<students.size();i++){
                     Object[] row = new Object[dtm.getColumnCount()];
                     Student s = students.get(i);
                     row[0]= s.getFirstName();
                     row[1]=s.getLastName();
                     row[2]=s.getStuId();
                     row[3]=teacherFirstName;
                     dtm.addRow(row);
                }
            }
            else if(selectedRow ==2){
                dtm.setRowCount(0);
                int count = r.getGroupSize() + r.getGroupSize()+r.getGroupSize();
                int start = r.getGroupSize() + r.getGroupSize();
                for(int i=start;i< count && i<students.size();i++){
                    Object[] row = new Object[dtm.getColumnCount()];
                    Student s = students.get(i);
                    row[0]= s.getFirstName();
                    row[1]=s.getLastName();
                    row[2]=s.getStuId();
                    row[3]=teacherFirstName;
                    dtm.addRow(row);
                }
             }     
        }
        else {
            // if user didn't selected a row show message
            JOptionPane.showMessageDialog(null, "Please select a row from table first to view Details!","Warning",JOptionPane.WARNING_MESSAGE);
        }
        
    }
    public ClassRoom getClassRoom(int minAge){
        for(ClassRoom c:DataStore.getInstance().getClassrooms()){
            if(minAge == c.getMinAge()){
                return c;
            }
        }
        return null;
    }
    public Rules getRule(int minAge,int maxAge){
        for(Rules r:DataStore.getInstance().getRules()){
            if(minAge == r.getMinAge() && maxAge == r.getMaxAge()){
                return r;
            }
        }
        return null;  
    }
}
