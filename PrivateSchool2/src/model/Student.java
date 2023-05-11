
package model;

import java.util.Date;


public class Student {

    private int student_id;
    private String first_name;
    private String last_name;
    private Date date_of_birth;
    private int tuition_fees;

    public Student() {
    }

    public Student(int student_id, String first_name, String last_name) {
        this.student_id = student_id;
        this.first_name = first_name;
        this.last_name = last_name;
    }
    
    

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public int getTuition_fees() {
        return tuition_fees;
    }

    public void setTuition_fees(int tuition_fees) {
        this.tuition_fees = tuition_fees;
    }
    
   

    
}
