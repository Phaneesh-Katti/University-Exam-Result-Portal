package com.example.studentpp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import java.util.Date;
@Entity
@Table (name = "results")
public class Result implements ResultInterface{
    @Id
    private Long id;

    private String name;

    private String srn;

    private Date date;

    private String grade;

    private String reviewRequested;

    private String revalRequested;



    @Override
    public String calGrade(Double marks)
    {
        String grade = "";
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

    public void setGrade(String grade)
    {
        this.grade = grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSrn(String srn) {
        this.srn = srn;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReviewRequested(String reviewRequested) {
        this.reviewRequested = reviewRequested;
    }

    public void setRevalRequested(String revalRequested) {
        this.revalRequested = revalRequested;
    }

    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getSrn() {
        return srn;
    }
    public Date getDate() {
        return date;
    }
    public java.lang.String getGrade() {
        return grade;
    }
    public String getReviewRequested() {
        return reviewRequested;
    }
    public String getRevalRequested() {
        return revalRequested;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
