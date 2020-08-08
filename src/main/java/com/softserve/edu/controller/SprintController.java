package com.softserve.edu.controller;

import com.softserve.edu.model.Marathon;
import com.softserve.edu.model.Sprint;
import com.softserve.edu.service.MarathonService;
import com.softserve.edu.service.SprintService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class SprintController {

    private SprintService sprintService;
    private MarathonService marathonService;

    public SprintController(SprintService sprintService, MarathonService marathonService) {
        this.sprintService = sprintService;
        this.marathonService = marathonService;
    }

    @GetMapping("/create-sprint/{marathon_id}")
    public String createSprint(@PathVariable("marathon_id") long marathonId, Model model) {
        model.addAttribute("sprint", new Sprint());
        return "create-sprint";
    }

    @PostMapping("/create-sprint/{marathon_id}")
    public String createSprint(@Validated @ModelAttribute Sprint sprint, @PathVariable("marathon_id") long marathonId,
                               @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                               @RequestParam("finish") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finish,
                               BindingResult result) {

        if (result.hasErrors()) {
            return "create-sprint/" + marathonId;
        }
        sprint.setStartDate(start);
        sprint.setEndDate(finish);
        sprintService.addSprintToMarathon(sprint, marathonService.getMarathonById(marathonId));
        return "redirect:/sprints/" + marathonId;
    }

    @GetMapping("/sprints/{marathon_id}")
    public String getAllSprints(@PathVariable("marathon_id") long marathonId, Model model) {
        List<Sprint> sprints = sprintService.getSprintsByMarathonId(marathonId);
        model.addAttribute("sprints", sprints);
        model.addAttribute("marathon_id", marathonId);
        return "sprints";
    }
}
