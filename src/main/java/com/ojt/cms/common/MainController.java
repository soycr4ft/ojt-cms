package com.ojt.cms.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.model.Model;

@Controller
public class MainController {
    @GetMapping("/")
    public String home(Model model) throws Exception {
        return "index";
    }

}
