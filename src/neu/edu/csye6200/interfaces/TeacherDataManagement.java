/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neu.edu.csye6200.interfaces;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import neu.edu.csye6200.model.Teacher;
import neu.edu.csye6200.util.FileIO;

/**
 *
 * @author 15085
 */
public class TeacherDataManagement implements DataManagement<Teacher>{
//    private static TeacherDataManagement teacherDataManagement;
//    public static TeacherDataManagement getInstance() {
//	if(teacherDataManagement == null)
//            teacherDataManagement = new TeacherDataManagement();
//        return teacherDataManagement;
//    }
    @Override
	public List<Teacher> getDataList() {
            List<Teacher> teachers =null;
        try {
            teachers=  FileIO.readFile(Teacher.class, "teacher.csv");
        } catch (ParseException ex) {
            Logger.getLogger(TeacherDataManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teachers;
	}

	@Override
	public List<Teacher> getDataList(String key) {
		List<Teacher> ts = new ArrayList<>();
                for(Teacher t:getDataList()){
                    if(t.getIsAssigned() && t.getClassRoomName().equalsIgnoreCase(key)){
                        ts.add(t);
                    }
                }
		return ts;
	}

	@Override
	public List<Teacher> getDataList(int key) {
		List<Teacher> ts = new ArrayList<>();
                for(Teacher t:getDataList()){
                    if(t.getTeachID() == key){
                        ts.add(t);
                    }
                }
		return ts;
	}

	@Override
	public List<Teacher> getDataList(int min, int max) {
		return null;
	}

	@Override
	public List<Teacher> getDataList(String min, String max) {
		List<Teacher> ts = new ArrayList<>();
		getDataList().forEach(t -> {if(t.firstName.compareTo(min) >= 0 && t.firstName.compareTo(max) <= 0) ts.add(t);});
		return ts;
	}
	
	@Override
	public void registerOneObject(Teacher teacher) {
		List<Teacher> registerNewTeacher = new ArrayList<>();
		int maxId = 0;
		for(Teacher t: getDataList()) {
                    if(t.getTeachID() > maxId) 
                        maxId = t.getTeachID();
                }
		teacher.setTeachID(maxId+1);
		registerNewTeacher.add(teacher);
		FileIO.writeFileAppended(registerNewTeacher, "Teacher.csv");
	}

	@Override
	public void deleteOneObject(Teacher t) {
		List<Teacher> ts = new ArrayList<>();
                for(Teacher teacher: getDataList()){
                    if(t.getTeachID()!= teacher.getTeachID()){
                        ts.add(teacher);
                    }
                }
		rewriteData(ts);
	}

	@Override
	public void updateOneObject(Teacher t) {
		List<Teacher> ts = new ArrayList<>();
                for(Teacher teacher: getDataList()){
                    if(t.getTeachID()!= teacher.getTeachID()){
                        ts.add(teacher);
                    }
                    else {
                        ts.add(t);
                    }
                }
		
		rewriteData(ts);
	}
	
	@Override
	public void rewriteData(List<Teacher> list) {
		FileIO.writeFile(list, Teacher.class, "teacher.csv");
	}
    
}
