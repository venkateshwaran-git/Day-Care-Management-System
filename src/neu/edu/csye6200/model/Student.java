
package neu.edu.csye6200.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student extends Person{
    private int stuId;
    private int age;//monthly age
    private String fatherName;
    private String motherName;
    private List<Immunization> immunizations;
    public Student(){
        
    }

    public Student(String firstName, String lastName,Date registerTime,int stuId, int age, String fatherName, String motherName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.registerTime = registerTime;
        this.stuId = stuId;
        this.age = age;
        this.fatherName = fatherName;
        this.motherName = motherName;
        immunizations =  new ArrayList<>();
        
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public List<Immunization> getImmunizations() {
        return immunizations;
    }

    public void setImmunizations(List<Immunization> immunizations) {
        this.immunizations = immunizations;
    }
    
    public String toString(){
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(registerTime);
       return  firstName + "," + lastName +","+date+","+stuId+","+ age+ "," + fatherName +","+ motherName;
    }

}
