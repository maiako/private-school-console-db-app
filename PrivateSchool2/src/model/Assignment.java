package model;

import java.sql.Date;

public class Assignment {

    private int assignment_id;
    private String title;
    private String description;
    private Date sub_date_time;
    private double oral_mark;
    private double total_mark;

 

    public int getAssignment_id() {
        return assignment_id;
    }

    public void setAssignment_id(int assignment_id) {
        this.assignment_id = assignment_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getSub_date_time() {
        return sub_date_time;
    }

    public void setSub_date_time(Date sub_date_time) {
        this.sub_date_time = sub_date_time;
    }

    public double getOral_mark() {
        return oral_mark;
    }

    public void setOral_mark(double oral_mark) {
        this.oral_mark = oral_mark;
    }

    public double getTotal_mark() {
        return total_mark;
    }

    public void setTotal_mark(double total_mark) {
        this.total_mark = total_mark;
    }

    
}
