package com.example.studentpp.view;

import com.example.studentpp.model.Result;
import com.example.studentpp.model.ResultTempRepository;
import com.example.studentpp.model.Script;
import com.example.studentpp.model.ScriptTempRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class COE {

    private static ScriptTempRepository scriptTempRepository= null;
    private static ResultTempRepository resultTempRepository = null;

    private COE(ScriptTempRepository scriptTempRepository, ResultTempRepository resultTempRepository) {
        this.scriptTempRepository = scriptTempRepository;
        this.resultTempRepository = resultTempRepository;

    }
    /////////////////////////////
    // Singleton

    private static COE coe_obj;

    public static synchronized COE getSingletonFunc(){
        coe_obj = new COE(scriptTempRepository, resultTempRepository);
        return coe_obj;
    }
    //sync code u do properly

    //End of singleton implementation
    //////////////////////////////

    ///////////////////////////////////////////////////////////
    // Mappings for COE landing page - 3 buttons 3 get mappings

    //1. review requests mapping
    @GetMapping("/reviewRequests")
    public String coeReviewRequests(Model model)
    {
        List<Script> scripts = scriptTempRepository.findByReviewRequested("Pending");
        model.addAttribute("scripts", scripts);
        return "reviewRequests";
    }

    //2. reval requests mapping
    @GetMapping("/revalRequests")
    public String coeRevalRequests(Model model) {
        List<Script> scripts = scriptTempRepository.findByRevalRequested("Pending");
        model.addAttribute("scripts", scripts);
        return "revalRequests";
    }

    //3. result verification mapping
    @GetMapping("/resultVerification")
    public String coeResultVerification(Model model)
    {
        List<Result> results = resultTempRepository.findAll();
        model.addAttribute("results", results);
        return "resultVerification";
    }
    //end of mapping for coe landing page
    /////////////////////////////////////
}
