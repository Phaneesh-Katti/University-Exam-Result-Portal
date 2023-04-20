package com.example.studentpp.controller;

import com.example.studentpp.model.Result;
import com.example.studentpp.model.ResultTempRepository;
import com.example.studentpp.model.Script;
import com.example.studentpp.model.ScriptTempRepository;
import com.example.studentpp.services.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class TempController {

    private final ScriptTempRepository scriptTempRepository;
    private final ResultTempRepository resultTempRepository;
    private ResultFactory res_fac_obj = new ResultFactory();

    @Autowired
    private JdbcTemplate jdbcTemplate;


    // Temp controller constructor
    TempController(ScriptTempRepository scriptTempRepository, ResultTempRepository resultTempRepository) {
        this.scriptTempRepository = scriptTempRepository;
        this.resultTempRepository = resultTempRepository;
    }


    @GetMapping("/Examiner")
    public String examinerLanding(Model model)
    {
        List<Script> scripts = scriptTempRepository.findAll();
        model.addAttribute("scripts", scripts);
        return "examinerLanding";
        // The examiner controller logic is embedded in the return object
    }

    @PostMapping("/savescript")
    public String saveScript(@RequestParam("srn") String srn, @RequestParam("marks") Integer marks) {
        List<Script> scripts = scriptTempRepository.findBySrn(srn);
        if (scripts.isEmpty()) {
            // handle case where script is not found
            return "error";
        }

        Script script = scripts.get(0);
        Double oldMarks = script.getMarks();
        //System.out.println(oldMarks);

        script.setMarks(Double.valueOf(marks));
        //System.out.println(script.getMarks());

        if (script.getRevalRequested() != null && script.getRevalRequested().equals("Processing")) {
            if (Objects.equals(oldMarks, script.getMarks()))
            {
                script.setRevalRequested("Processed");
            }else { script.setRevalRequested("Processed+"); }
        }

        //System.out.println(script.getRevalRequested());
        Result result = res_fac_obj.getResult(script);
        resultTempRepository.save(result);
        scriptTempRepository.save(script);
        return "redirect:/Examiner";
        //Post action logic is again embedded into the HTML page
    }

    //////////////////////////////////
    // COE RELATED OPERATIONS
    //Review related Actions 1 - when COE clicks on accept Review
    @PostMapping("/reviewRequests/accept")
    public String acceptReviewRequest(@RequestParam("id") Long id) {
        // Find the script with the given ID
        Optional<Script> optionalScript = scriptTempRepository.findById(String.valueOf(id));
        if (optionalScript.isEmpty()) {
            // Return an error page if the script is not found
            return "error";
        }
        Script script = optionalScript.get();

        // Set the review status to "Accepted"
        script.setReviewRequested("Accepted");
        scriptTempRepository.save(script);

        // Redirect the user back to the pending review requests page
        return "redirect:/reviewRequests";
    }

    //Review related Actions 2 - when COE clicks on Reject Review
    @PostMapping("/reviewRequests/reject")
    public String rejectReviewRequest(@RequestParam("id") Long id) {
        // Find the script with the given ID
        Optional<Script> optionalScript = scriptTempRepository.findById(String.valueOf(id));
        if (optionalScript.isEmpty()) {
            // Return an error page if the script is not found
            return "error";
        }
        Script script = optionalScript.get();

        // Set the review status to "Accepted"
        script.setReviewRequested("Rejected");
        scriptTempRepository.save(script);

        // Redirect the user back to the pending review requests page
        return "redirect:/reviewRequests";
    }

    //Reval related Actions 1 - when COE clicks on accept Reval
    @PostMapping("/revalRequests/accept")
    public String acceptRevalRequest(@RequestParam("id") Long id) {
        // Find the script with the given ID
        Optional<Script> optionalScript = scriptTempRepository.findById(String.valueOf(id));
        if (optionalScript.isEmpty()) {
            // Return an error page if the script is not found
            return "error";
        }
        Script script = optionalScript.get();

        // Set the review status to "Accepted"
        script.setRevalRequested("Processing");
        scriptTempRepository.save(script);

        // Redirect the user back to the pending review requests page
        return "redirect:/revalRequests";
    }

    //Reval related Actions 2 - when COE clicks on reject Reval
    @PostMapping("/revalRequests/reject")
    public String rejectRevalRequest(@RequestParam("id") Long id) {
        // Find the script with the given ID
        Optional<Script> optionalScript = scriptTempRepository.findById(String.valueOf(id));
        if (optionalScript.isEmpty()) {
            // Return an error page if the script is not found
            return "error";
        }
        Script script = optionalScript.get();

        // Set the review status to "Accepted"
        script.setRevalRequested("Rejected");
        scriptTempRepository.save(script);

        // Redirect the user back to the pending review requests page
        return "redirect:/revalRequests";
    }
    // END of COE related operations
    //////////////////////////////////////////////////

    @PostMapping("/CoeVerification")
    public String coeVerification() {
        jdbcTemplate.execute("INSERT INTO permanent_results SELECT * FROM results");
        // Add any other necessary logic here
        return "resultVerification";
    }

}
