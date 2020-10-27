
package neu.edu.csye6200.model;

import java.util.ArrayList;
import java.util.List;

public class ClassRoom {
        private String name;
	private int size;
	private int maxAge;
        private int minAge;
        private int teachersSize;
	private List<Student> students;
	private List<Teacher> teacher;

    public ClassRoom() {
    }
    public ClassRoom(String name,int maxAge,int minAge,int size,int teachersSize) {
        students = new ArrayList<>();
        teacher = new ArrayList<>();
        this.name = name;
        this.size = size;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.teachersSize = teachersSize;
    }

    public int getTeachersSize() {
        return teachersSize;
    }

    public void setTeachersSize(int teachersSize) {
        this.teachersSize = teachersSize;
    }
    
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }



    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeacher() {
        return teacher;
    }

    public void setTeacher(List<Teacher> teacher) {
        this.teacher = teacher;
    }
    public boolean isAvailableClassRoom(){
        if(size==students.size()){
            return false;
        }
        return true;
    } 
}
