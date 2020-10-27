
package neu.edu.csye6200.interfaces;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import neu.edu.csye6200.model.Immunization;
import neu.edu.csye6200.util.FileIO;

public class ImmunizationDataManagement implements DataManagement<Immunization> {
	@Override
	public List<Immunization> getDataList() {
                
		List<Immunization> immuns=null;
            try {
                immuns = FileIO.readFile(Immunization.class, "stuImm.csv");
            } catch (ParseException ex) {
                Logger.getLogger(ImmunizationDataManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
                return immuns;
	}
	/**
	 * search by immunization name
	 */
	@Override
	public List<Immunization> getDataList(String key) {
		List<Immunization> selectedImmus = new ArrayList<>();
		getDataList().forEach(immu -> {if(immu.getImmunName().equals(key)) selectedImmus.add(immu);});
		return selectedImmus;
	}

	@Override
	public List<Immunization> getDataList(int key) {
		// TODO Auto-generated method stub
		
		List<Immunization> selectedImmuns = new ArrayList<>();
		for(Immunization i:getDataList()){
                    if(i.getStudentId() ==key){
                        System.out.println(i.getImmunName());
                        selectedImmuns.add(i);
                    }
                    
                }
		return selectedImmuns;
	}

	@Override
	public List<Immunization> getDataList(int min, int max) {
		List<Immunization> selectedImmuns = new ArrayList<>();
		for(Immunization i:getDataList()){
                    if(i.getStudentId() ==min && i.getImmunId()==max){
                        selectedImmuns.add(i);
                    }
                    
                }
		return selectedImmuns;
	}

	@Override
	public List<Immunization> getDataList(String min, String max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerOneObject(Immunization newlyAddedImmun) {
		List<Immunization> immuns = new ArrayList<>();
		int maxId = 0;
		List<Immunization> allStus = getDataList();
		for(Immunization i : allStus) {
                    if(i.getImmunId()>maxId){
                        maxId = i.getImmunId();  
                    }
                }
		newlyAddedImmun.setImmunId(maxId+1);
		immuns.add(newlyAddedImmun);
                for(Immunization i:immuns){
                    System.out.println(i);
                }
		FileIO.writeFileAppended(immuns, "stuImm.csv");
	}

	@Override
	public void deleteOneObject(Immunization deletedImmun) {
		// TODO Auto-generated method stub
		List<Immunization> immuns = new ArrayList<>();
                for(Immunization i: getDataList()){
                    if(i.getImmunId()!= deletedImmun.getImmunId()){
                        immuns.add(i);
                    }
                }
		rewriteData(immuns);
		
	}

	@Override
	public void updateOneObject(Immunization t) {
		// TODO Auto-generated method stub
		List<Immunization> immuns = new ArrayList<>();
                for(Immunization i: getDataList()){
                    if(i.getImmunId() != t.getImmunId()){
                        immuns.add(i);
                    }
                    else {
                        immuns.add(t);
                    }
                }

		rewriteData(immuns);
	}


    @Override
    public void rewriteData(List<Immunization> list) {
       FileIO.writeFile(list, Immunization.class, "stuImm.csv");
    }
    
}
