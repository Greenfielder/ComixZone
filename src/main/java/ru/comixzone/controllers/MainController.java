package ru.comixzone.controllers;

/**
 * Created by Mickey on 14.06.2019.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.comixzone.entities.Comix;
import ru.comixzone.services.ComixService;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class MainController {
    private ComixService comixService;

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

//    @GetMapping("/")
//    @Transactional
//    public String showStudentsList(Model model) {
//        List<Comix> allComix = comixService.getAllComixList();
//        model.addAttribute("comixList", allComix);
//        return "index";
//    }

}

