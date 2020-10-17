package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;
import static org.launchcode.javawebdevtechjobsmvc.models.JobData.*;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.


    @PostMapping("results")
    public String displaySearchResults(Model model, String searchType, @RequestParam String searchTerm) {
        ArrayList<Job> jobs;
        model.addAttribute("columns", columnChoices); // this keeps the radio buttons there

        if (searchTerm == "" || searchTerm.toLowerCase().equals("all")) {
            jobs = JobData.findAll(); // this makes them all show up
            model.addAttribute("jobs", jobs); // this is the ${jobs} in the search.html
            return "search";
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("jobs", jobs);
            model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
        }

        return "search";
    }
}



