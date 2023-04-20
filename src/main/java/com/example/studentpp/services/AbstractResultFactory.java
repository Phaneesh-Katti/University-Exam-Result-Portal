package com.example.studentpp.services;

import com.example.studentpp.model.Result;
import com.example.studentpp.model.ResultInterface;
import com.example.studentpp.model.Script;

interface AbstractResultFactory {
     Result getResult(Script script);
}
