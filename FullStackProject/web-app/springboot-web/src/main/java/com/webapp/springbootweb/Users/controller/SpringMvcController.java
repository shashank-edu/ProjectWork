package com.webapp.springbootweb.Users.controller;

import com.webapp.springbootweb.Products.ExceptionalHandeling.ProductNotFoundException;
import com.webapp.springbootweb.Products.entities.Product;
import com.webapp.springbootweb.Users.Repository.UserRepository;
import com.webapp.springbootweb.Users.Service.UserService;
import com.webapp.springbootweb.Users.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller 
@RequestMapping("/user")
public class SpringMvcController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") int id){

        return this.userService.getUser(id);
    }



    @GetMapping("/adduser")
    public User add() {
        User user = new User();
        user.setEmail("Admin@gmail.com");
        user.setPassword(this.bCryptPasswordEncoder.encode("password"));
        user.setRole("ROLE_ADMIN");
        user.setUsername("admin1");
        user.setUserId(2);
        userRepository.save(user);
        return user;
    }

    @PostMapping("/save")
    public String addUser(User user, RedirectAttributes redirectAttribute) {
        userService.addUser(user);
        redirectAttribute.addFlashAttribute("message", "The User has been saved successfully.");
        return "redirect:list";
    }

    @GetMapping("/new")
    public String ShowAddNewPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add new user");
        return "AddUser";
    }

    @GetMapping("/edit/{id}")
    public String findProductById(@PathVariable int id, Model model, RedirectAttributes redirectAttribute) {
        try {
            User user = userService.getUser(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
            return "AddUser";
        } catch (ProductNotFoundException e) {
            redirectAttribute.addFlashAttribute("message", e.getMessage());
            return "redirect:/list";
        }

    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable int id, RedirectAttributes redirectAttribute) {
        try {
            userService.deleteUser(id);
            redirectAttribute.addFlashAttribute("message", "User ID- " + id + " Successfully deleted!");
        } catch (ProductNotFoundException e) {
            redirectAttribute.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/user/list";
    }
}
