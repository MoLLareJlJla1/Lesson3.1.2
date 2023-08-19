package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.Service.UserService;
import web.model.User;


@Controller
public class MyController {
    private final UserService service;

    @Autowired
    public MyController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    String show(Model model) {
        model.addAttribute("showAllAttribute", service.showAll());
        return "showAll";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        User us = new User();
        model.addAttribute("addUs", new User());
        return "user-info";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("form2") User user) {
        service.save(user);
        return "redirect:/";
    }

    @PutMapping("/updateInfo/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("addUs", service.getUser(id));
        return "user-info";
    }

    @DeleteMapping("/deleteInfo/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        service.deleteUser(id);
        return "redirect:/";
    }
}
