
package neu.edu.csye6200.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Teacher extends Person{
    private int teachID;
    private boolean isAssigned;
    private String classRoomName;
    public Teacher(){
    }

    public Teacher(String firstName,String lastName,Date registerTime,int teachID,boolean isAssigned,String classRoomName) {
        this.registerTime = registerTime;
        this.firstName = firstName;
        this.lastName = lastName;
        this.teachID = teachID;
        this.classRoomName = classRoomName;
        
        this.isAssigned = isAssigned;
    }

    public int getTeachID() {
        return teachID;
    }

    public void setTeachID(int teachID) {
        this.teachID = teachID;
    }

    public boolean getIsAssigned() {
        return isAssigned;
    }

    public void setIsAssigned(boolean isAssigned) {
        this.isAssigned = isAssigned;
    }

    public String getClassRoomName() {
        return classRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        this.classRoomName = classRoomName;
    }
    public String toString(){
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(registerTime);
        String name = isAssigned? classRoomName :"";
        return  firstName + "," + lastName +","+date+","+teachID+","+ isAssigned+ "," +name;
    }
    
    
}
