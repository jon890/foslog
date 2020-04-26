package com.bifos.foslog.web;

import com.bifos.foslog.config.auth.LoginUser;
import com.bifos.foslog.config.auth.dto.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String index(Model model, @LoginUser SessionUser user) {
        if (user != null) model.addAttribute("loginUserName", user.getName());
        return "/profile/index";
    }
}
