package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userService;

    private final RoleServiceImpl roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String getUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "admin/list";
    }

    @GetMapping("/add")
    public String getUserFormCreation(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("role", roleService.findAllRoles());
        return "admin/add";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user, @RequestParam(value = "select_role", required = false) String[] roles) {
        userService.saveUser(user, roles);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteByIdUsers(id);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String getUserFormEdit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findByIdUsers(id));
        model.addAttribute("role", roleService.findAllRoles());
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id, @RequestParam(value = "select_role", required = false) String[] roles) {
        userService.updateUser(id, user, roles);
        return "redirect:/admin";
    }
}
