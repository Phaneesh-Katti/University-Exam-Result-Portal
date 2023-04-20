package com.example.studentpp.services;

import com.example.studentpp.model.Result;
import com.example.studentpp.model.Script;

public class ResultFactory implements AbstractResultFactory{

    @Override
    public Result getResult(Script script)
    {
        Double marks = script.getMarks();

        //Result object
        Result res_obj = new Result();
        //Call cal grade - defined in Result Interface - !!! switch !!!
        res_obj.setGrade(res_obj.calGrade(marks));
        res_obj.setName(script.getName());
        res_obj.setSrn(script.getSrn());
        res_obj.setId(script.getId());
        res_obj.setDate(script.getDate());
        res_obj.setReviewRequested(script.getReviewRequested());
        res_obj.setRevalRequested(script.getRevalRequested());
        return res_obj;
    }
}