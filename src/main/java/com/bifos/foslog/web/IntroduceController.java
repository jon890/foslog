package com.bifos.foslog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IntroduceController {

    @GetMapping("/introduce")
    public String profileIndex() {
        return "introduce/index";
    }
}
