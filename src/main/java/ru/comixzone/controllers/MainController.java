package ru.comixzone.controllers;

/**
 * Created by Mickey on 14.06.2019.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }
}

