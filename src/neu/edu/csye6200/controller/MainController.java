
package neu.edu.csye6200.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import neu.edu.csye6200.data.DataStore;
import neu.edu.csye6200.userInterface.MainJFrame;
import javax.swing.JPanel;

public class MainController {
    private MainJFrame view;
    private ClassRoomController classController;
    private StudentController studentController;
    private TeacherController teacherController;
    public MainController(MainJFrame view) {
        this.view = view;
        code();
        
    }
    public MainJFrame getView() {
        return view;
    }
    private void code(){
        
        
        
        
        
        
                           
        
        
        
        this.view.getAdminBtn().addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
           view.classRoomJPanel();
           classController = ClassRoomController.getInstance(view.getClassRoomJPanel());
           ClassRoomController.getInstance(view.getClassRoomJPanel()).setTableRecords();
           
           
      }});
      this.view.getStudentBtn1().addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            view.studentJPanel();
            studentController = StudentController.getInstance(view.getStudentJPanel());
            
      }});
      this.view.getTeacherBtn().addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            view.teacherJPanel();
            teacherController = TeacherController.getInstance(view.getTeacherJPanel());
            teacherController.setTableRecords();
            //ClassRoomController.getInstance(view.getClassRoomJPanel()).setTableRecords();
            
            
      }});
      
      
      this.view.getBtnStateRules().addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e)
        {
            view.StateRulesJpanel();
            
              view.getStateRulesjPanel().getAge().addActionListener(new ActionListener() {//add actionlistner to listen for change
    @Override
    public void actionPerformed(ActionEvent e) {
                    if(view.getStateRulesjPanel().getAge().getSelectedIndex()==0){
                                   System.out.println("table1");
     view.getStateRulesjPanel().getjTable1().setVisible(true);
         view.getStateRulesjPanel().getjTable2().setVisible(false);

         view.getStateRulesjPanel().getjTable3().setVisible(false);
view.getStateRulesjPanel().getjTable4().setVisible(false);

              

       }
       if(view.getStateRulesjPanel().getAge().getSelectedIndex()==1){
           System.out.println("table2");
             view.getStateRulesjPanel().getjTable1().setVisible(false);
       view.getStateRulesjPanel().getjTable2().setVisible(true);
       
       view.getStateRulesjPanel().getjTable3().setVisible(false);
       view.getStateRulesjPanel().getjTable4().setVisible(false);
          
       }
       if(view.getStateRulesjPanel().getAge().getSelectedIndex()==2){
                      System.out.println("table3");
       view.getStateRulesjPanel().getjTable1().setVisible(false);
      view.getStateRulesjPanel(). getjTable2().setVisible(false);
       view.getStateRulesjPanel().getjTable3().setVisible(true);
       view.getStateRulesjPanel().getjTable4().setVisible(false);
          
       }
       if(view.getStateRulesjPanel().getAge().getSelectedIndex()==3){
                      System.out.println("table4");
                 view.getStateRulesjPanel().getjTable1().setVisible(false);
       view.getStateRulesjPanel().getjTable2().setVisible(false);
       view.getStateRulesjPanel().getjTable3().setVisible(false);
       view.getStateRulesjPanel().getjTable4().setVisible(true);
          
    }                                          
                }

           
           
           });

            
            }
        
      
    
      
      
      });
      
                

      
      
          
           
            //ClassRoomController.getInstance(view.getClassRoomJPanel()).setTableRecords();
            
      
      
      
    }
}
