/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neu.edu.csye6200.interfaces;

/**
 *
 * @author 15085
 */
public class ImmunizationDataManagementFactory extends AbstractDataManagementFactory{
    private static ImmunizationDataManagementFactory factoryInstance =null;
     private static ImmunizationDataManagement immunizationDataManagement=null;

    private ImmunizationDataManagementFactory() {
        
    }
    
    public static AbstractDataManagementFactory getFactoryInstance() {
        if(factoryInstance==null){
           factoryInstance = new ImmunizationDataManagementFactory();
        }
        return factoryInstance;
    }
    @Override
    public DataManagement getObject() {
        if(immunizationDataManagement == null)
            immunizationDataManagement = new ImmunizationDataManagement();
        return immunizationDataManagement;
    }
}
    
    

