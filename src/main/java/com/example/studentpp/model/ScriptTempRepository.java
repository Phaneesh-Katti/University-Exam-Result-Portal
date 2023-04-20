package com.example.studentpp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScriptTempRepository extends JpaRepository<Script, String>
{

    List<Script> findBySrn(String srn);

    List<Script> findByReviewRequested(String reviewRequested);

    List<Script> findByRevalRequested(String pending);

}