
package neu.edu.csye6200.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Immunization {
    private int studentId;
    private int immunId;
    private String immunName;
    private String duration;
    private Date immunDate;
    private boolean status;
    public Immunization() {}
    public Immunization(int studId,int immunId, String immunName, String duration,Date immunDate,boolean status) {
            this.studentId = studId;
            this.immunId = immunId;
            this.immunName = immunName;
            this.duration = duration;
            this.immunDate = immunDate;
            this.status = status;
    }

    public int getStudentId() {
        return studentId;
    }
    
    
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getImmunId() {
        return immunId;
    }

    public void setImmunId(int immunId) {
        this.immunId = immunId;
    }

    public String getImmunName() {
        return immunName;
    }

    public void setImmunName(String immunName) {
        this.immunName = immunName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getImmunDate() {
        return immunDate;
    }

    public void setImmunDate(Date immunDate) {
        this.immunDate = immunDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
   public String toString(){
       String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(immunDate);
       return  studentId + "," + immunId +","+immunName+","+duration+","+ date+ "," + status;
   }
    
}
