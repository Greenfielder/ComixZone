package ru.comixzone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.comixzone.entities.Comix;
import ru.comixzone.services.ComixService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Mickey on 15.06.2019.
 */

@Controller
@RequestMapping("/comix")
@Transactional
public class ComixController {
    private ComixService comixService;

    @Autowired
    public void setComixService(ComixService comixService) {
        this.comixService = comixService;
    }

    @RequestMapping("/list")
    @Transactional
    public String showStudentsList(Model model) {
        List<Comix> allComix = comixService.getAllComixList();
        model.addAttribute("comixList", allComix);
        return "comix-list";
    }

    @RequestMapping(path="/add", method= RequestMethod.GET)
    public String showAddForm(Model model) {
        Comix comix = new Comix();
        comix.setName("Unknown");
        model.addAttribute("comix", comix);
        return "add-comix-form";
    }

    @RequestMapping(path="/add", method=RequestMethod.POST)
    public String showAddForm(Comix comix) {
        comixService.addComix(comix);
        return "redirect:/comix/list";
    }

}
