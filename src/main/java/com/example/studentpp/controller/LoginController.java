package com.example.studentpp.controller;

import com.example.studentpp.model.Result;
import com.example.studentpp.model.ResultTempRepository;
import com.example.studentpp.model.Script;
import com.example.studentpp.model.ScriptTempRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {

    String tempSRN;
    private final ScriptTempRepository scriptTempRepository;
    private final ResultTempRepository resultTempRepository;

    LoginController(ScriptTempRepository scriptTempRepository,ResultTempRepository resultTempRepository) {
        this.scriptTempRepository = scriptTempRepository;
        this.resultTempRepository = resultTempRepository;
    }

    @GetMapping("/")
    public String home(){
        return "login";
    }
    @PostMapping("/login")
    public String MainLogin(@RequestParam String username, @RequestParam String password)
    {
        if (username.equals(password))
        {
            if (username.equals("coe")) { return "redirect:/COE";}
            else if (username.equals("examiner")) {return "redirect:/Examiner";}
            else { tempSRN = username;
                return "redirect:/Dashboard"; }
        }

        else
            return "login";
    }
    @GetMapping("/Dashboard")
    public String studentScripts(Model model) {
        List<Result> result = resultTempRepository.findBySrn(tempSRN);
        model.addAttribute("results", result);
        List<Script> script = scriptTempRepository.findBySrn(tempSRN);
        model.addAttribute("scripts", script);
        return "studentScript";
    }
}
