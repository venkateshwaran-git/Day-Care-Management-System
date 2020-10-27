
package neu.edu.csye6200.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import neu.edu.csye6200.model.Immunization;
import neu.edu.csye6200.model.Student;
import neu.edu.csye6200.model.Teacher;

public class FileIO<T> {

	public static <T> void writeFile(List<T> data, Class<T> clazz, String fileName) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName)))) {
			for(T t:data){
                            bw.append(t.toString());
                            bw.newLine();
                        }
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public static <T> void writeFileAppended(List<T> data, String fileName) {   
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName), true))) {
			for(T t:data){
                            bw.append(t.toString());
                            bw.newLine();
                        }
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	
	/**
	 * This is the basic method to transit a CSV file into data list. It can automatically package the data into certain type that you defined.
	 * Using java reflection to do this thing. Normally you should choose this method to read data. A CSV file always have column titles.
	 * @param clazz The reflection of the class you need to read.
	 * @return
	 */
	public static <T> List<T> readFile(Class<T> clazz, String pathToCsvFile) throws ParseException {
            List<T> data = new ArrayList<>();
               String fileName = "../../../../resources/" + pathToCsvFile;
		try (BufferedReader inLine = new BufferedReader(new FileReader(pathToCsvFile)))
		{
			String inputLine = null;	// read one line from file at a time
			while ((inputLine = inLine.readLine()) != null){
				// Parse line converting each string token into a Student object field
				String[] fields = inputLine.split(",");
				if(pathToCsvFile.equalsIgnoreCase("student.csv")) {
                                        String fname = fields[0];
					String lname = fields[1];
                                        Date registerTime=new SimpleDateFormat("dd/MM/yyyy").parse(fields[2]); 
					int id = new Integer(fields[3]);
                                        int age = new Integer(fields[4]);
					String fatherName = fields[5];
					String motherName = fields[6];
                                        T s = (T)new Student(fname,lname,registerTime,id,age,fatherName,motherName);
					data.add(s);	
				}
				else if(pathToCsvFile.equalsIgnoreCase("teacher.csv")){
					String fname = fields[0];
					String lname = fields[1];
                                        Date registerTime=new SimpleDateFormat("dd/MM/yyyy").parse(fields[2]);
					int id = new Integer(fields[3]);
                                        boolean isAssigned = fields[4].equalsIgnoreCase("true")?true:false;
                                        String classRoomName = isAssigned?fields[5]:null;
                                        T t = (T) new Teacher(fname,lname,registerTime,id,isAssigned,classRoomName);
					data.add(t);	
				}
                                else if(pathToCsvFile.equalsIgnoreCase("stuImm.csv")){
                                    int id = new Integer(fields[0]);
                                    int immunId= new Integer(fields[1]);
                                    String immunName = fields[2];
                                    String duration = fields[3];
                                    Date immunDate=new SimpleDateFormat("dd/MM/yyyy").parse(fields[4]);
                                    boolean status =  fields[5].equalsIgnoreCase("true")?true:false; 
                                    T t = (T) new Immunization(id,immunId,immunName,duration,immunDate,status);
				     data.add(t);
                                }

			}
		}
	   catch (IOException e) {
		   // catch IOException (and implicitly FileNotFoundException)
		   e.printStackTrace();
	   }
	  return data;
        }
            
}
