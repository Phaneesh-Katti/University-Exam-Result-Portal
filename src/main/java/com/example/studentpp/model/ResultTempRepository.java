package com.example.studentpp.model;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultTempRepository extends JpaRepository<Result, String>{
    List<Result> findBySrn(String srn);
}