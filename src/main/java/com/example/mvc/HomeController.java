package com.example.mvc;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String login(Model model) {
        return "home";
    }

    @RequestMapping("/secret")
    public String secret(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", authentication);
        return "secret";
    }

    @ResponseBody
    @GetMapping("/security")
    public String securityRequest() {
        System.out.println("logger");
        return "Wow, it's do something secret";
    }

    @ResponseBody
    @PostMapping("/securityPost")
    public String securityRequestpost() {
        System.out.println("logger post");
        return "Wow, it's do something secret";
    }
}
