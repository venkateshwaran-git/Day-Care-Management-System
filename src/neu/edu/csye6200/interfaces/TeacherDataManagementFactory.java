/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neu.edu.csye6200.interfaces;

public class TeacherDataManagementFactory extends AbstractDataManagementFactory{
    private static TeacherDataManagementFactory factoryInstance =null; 
    private static TeacherDataManagement teacherDataManagement=null;

    private TeacherDataManagementFactory() {
       
    }
    
    public static AbstractDataManagementFactory getFactoryInstance() {
        if(factoryInstance==null){
           factoryInstance = new TeacherDataManagementFactory();
        }
        return factoryInstance;
    }
    @Override
    public DataManagement getObject() {
        if(teacherDataManagement == null)
            teacherDataManagement = new TeacherDataManagement();
        return teacherDataManagement;
    }
    
}
