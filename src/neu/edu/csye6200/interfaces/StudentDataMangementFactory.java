package neu.edu.csye6200.interfaces;

public class StudentDataMangementFactory extends AbstractDataManagementFactory{
    private static StudentDataMangementFactory factoryInstance =null;
    private static StudentDataManagement studentDataManagement=null;

    private StudentDataMangementFactory() {
    }
    
    public static AbstractDataManagementFactory getFactoryInstance() {
        if(factoryInstance==null){
           factoryInstance = new StudentDataMangementFactory();
        }
        return factoryInstance;
    }
    @Override
    public DataManagement getObject() {
        if(studentDataManagement == null)
            studentDataManagement = new StudentDataManagement();
        return studentDataManagement;
    }
}
