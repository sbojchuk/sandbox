package com.example.mvc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.LinkedHashMap;

@Controller
public class HomeController {

    private static final Log LOGGER = LogFactory.getLog(HomeController.class);

    @RequestMapping("/")
    public String login(Model model) {
        return "home";
    }

    @ModelAttribute("user")
    public Principal user(Principal principal) {
        return principal;
    }

    @ModelAttribute("avatarURL")
    public String avatarURL(Principal principal) {
        if (principal instanceof OAuth2Authentication) {
            LinkedHashMap<String, Object> userDetails = (LinkedHashMap<String, Object>) ((OAuth2Authentication)
                    principal).getUserAuthentication().getDetails();
            return (String) userDetails.get("avatar_url");
        }
        return "";
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
        LOGGER.info("logger");
        return "Wow, it's do something secret";
    }

    @ResponseBody
    @PostMapping("/securityPost")
    public String securityRequestpost() {
        LOGGER.info("logger post");
        return "Wow, it's do something secret";
    }
}
