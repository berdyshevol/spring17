package com.softserve.edu.controller;

import com.softserve.edu.model.Marathon;
import com.softserve.edu.model.Role;
import com.softserve.edu.model.User;
import com.softserve.edu.service.MarathonService;
import com.softserve.edu.service.RoleService;
import com.softserve.edu.service.UserService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Data
public class StudentController {

    private UserService studentService;
    private RoleService roleService;
    private MarathonService marathonService;

    public StudentController(UserService studentService, RoleService roleService, MarathonService marathonService) {
        this.studentService = studentService;
        this.roleService = roleService;
        this.marathonService = marathonService;
    }

    @GetMapping("/create-student")
    public String createStudent(Model model) {
        List<Role> roles = roleService.getAll();
        model.addAttribute("roles", roles);
        model.addAttribute("user", new User());
        return "create-student";
    }

    @PostMapping("students/{marathon_id}/add")
    public String createStudent(@PathVariable("marathon_id") long marathonId, @RequestParam("role_id") long roleId,
                                @Validated @ModelAttribute User user, BindingResult result) {
        if (result.hasErrors()) {
            return "create-student";
        }
        user.setRole(roleService.getRoleById(roleId));
        studentService.addUserToMarathon(
                studentService.createOrUpdateUser(user),
                marathonService.getMarathonById(marathonId));
        return "redirect:/students/" + marathonId;
    }

    @GetMapping("students/{marathon_id}/add")
    public String createStudent(@RequestParam("user_id") long userId, @PathVariable("marathon_id") long marathonId) {
        studentService.addUserToMarathon(
                studentService.getUserById(userId),
                marathonService.getMarathonById(marathonId));
        return "redirect:/students/" + marathonId;
    }

    @GetMapping("/students/{marathon_id}/edit/{student_id}")
    public String updateStudent(@PathVariable("marathon_id") long marathonId, @PathVariable("student_id") long studentId, Model model) {
        User user = studentService.getUserById(studentId);
        List<Role> roles = roleService.getAll();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "update-student";
    }

    @PostMapping("/students/{marathon_id}/edit/{student_id}")
    public String updateStudent(@PathVariable("marathon_id") long marathonId, @PathVariable("student_id") long studentId,
                                @RequestParam("role_id") long roleId, @Validated @ModelAttribute User user, BindingResult result) {
        if (result.hasErrors()) {
            return "update-marathon";
        }
        user.setRole(roleService.getRoleById(roleId));
        studentService.createOrUpdateUser(user);
        return "redirect:/students/" + marathonId;
    }

    @GetMapping("/students/{marathon_id}/delete/{student_id}")
    public String deleteStudent(@PathVariable("marathon_id") long marathonId, @PathVariable("student_id") long studentId) {
        studentService.deleteUserFromMarathon(
                studentService.getUserById(studentId),
                marathonService.getMarathonById(marathonId));
        return "redirect:/students/" + marathonId;
    }

    @GetMapping("/students")
    public String getAllStudents(Model model) {
        List<User> students = studentService.getAll();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/students/edit/{id}")
    public String updateStudent(@PathVariable long id, Model model) {
        User user = studentService.getUserById(id);
        List<Role> roles = roleService.getAll();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "update-student";
    }

    @PostMapping("/students/edit/{id}")
    public String updateStudent(@PathVariable long id, @RequestParam("role_id") long roleId,
                                @Validated @ModelAttribute User user, BindingResult result) {
        if (result.hasErrors()) {
            return "update-marathon";
        }
        user.setRole(roleService.getRoleById(roleId));
        studentService.createOrUpdateUser(user);
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable long id) {
        User student = studentService.getUserById(id);
        for (Marathon marathon : student.getMarathons()) {
            studentService.deleteUserFromMarathon(student, marathon);
        }
        studentService.deleteUserById(id);
        return "redirect:/students";
    }
}
