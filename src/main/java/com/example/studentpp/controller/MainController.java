package com.example.studentpp.controller;

import com.example.studentpp.model.Script;
import com.example.studentpp.model.ScriptTempRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {


    private final ScriptTempRepository scriptTempRepository;

    MainController(ScriptTempRepository scriptTempRepository) {
        this.scriptTempRepository = scriptTempRepository;
    }

    //////////////////////////
    // COE related mappings
    /////////////////////////

    // COE landing page
    @GetMapping("/COE")
    public String coeLanding()
    {
        return "coeLanding";
    }

    // Subsequent inner buttons are handled inside COE java file


    ///////////////////////////
    // Student related mappings
    ///////////////////////////

    //When student clicks on apply for review - logic
    @PostMapping("/reviewRequestConfirmation")
    public String reviewRequestConfirmation(@RequestParam("srn") String srn, @RequestParam("id") Long id) {
        Script script = scriptTempRepository.findById(String.valueOf(id)).orElse(null);
        if (script != null) {
            script.setReviewRequested("Pending");
            scriptTempRepository.save(script);
        }
        return "redirect:/Dashboard"; // return the name of your confirmation page
    }
    ///////////////////////////////////////////////////

    //When student clicks on apply for reval - logic
    @PostMapping("/revalRequestConfirmation")
    public String revalRequestConfirmation(@RequestParam("srn") String srn, @RequestParam("id") Long id){
        Script script = scriptTempRepository.findById(String.valueOf(id)).orElse(null);
        if (script != null) {
            script.setRevalRequested("Pending");
            scriptTempRepository.save(script);
        }
        return "redirect:/Dashboard";
    }

}
