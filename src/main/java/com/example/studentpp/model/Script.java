package com.example.studentpp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "scripts")
public class Script {

    private String srn;
    private String name;
    private Date date;
    private Double marks;
    @Lob
    private byte[] image;
    @Id
    private Long id;
    private String reviewRequested;
    private String revalRequested;


    public Script() {}

    public Script(String srn, String name, Date date, byte[] image, String grade) {
        this.srn = srn;
        this.name = name;
        this.date = date;
        this.marks = null;
        this.image = image;

    }

    public String getSrn() { return srn; }
    public String getName() { return name; }
    public Date getDate() { return date;}
    public Double getMarks() { return marks;}
    public Long getId() {
        return id;
    }
    public byte[] getImage(){ return image; }
    public String getReviewRequested() {
        return reviewRequested;
    }
    public String getRevalRequested() { return revalRequested; }
    public String getGrade(){
        String grade="";
        if (marks==null){

            grade = "~";
        }
        else {
            if (marks >= 90 && marks <= 100) {
                grade = "S";
            } else if (marks <= 90 && marks >= 80) {
                grade = "A";
            } else if (marks <= 80 && marks >= 70) {
                grade = "B";
            } else if (marks <= 70 && marks >= 60) {
                grade = "C";
            } else if (marks <= 60) {
                grade = "F";
            } else {
                grade = "~";
            }

        }
        return grade;
    }
    public void setReviewRequested(String reviewRequested) { this.reviewRequested = reviewRequested; }
    public void setRevalRequested(String revalRequested) { this.revalRequested = revalRequested; }
    public void setId(Long id) {
        this.id = id;
    }
    public void setMarks(Double marks) { this.marks = marks; }

}


