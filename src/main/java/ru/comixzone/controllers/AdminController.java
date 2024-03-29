package ru.comixzone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.comixzone.entities.Comix;
import ru.comixzone.entities.SystemUser;
import ru.comixzone.entities.User;
import ru.comixzone.repositories.RoleRepository;
import ru.comixzone.services.ComixService;
import ru.comixzone.services.UserService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Mickey on 15.06.2019.
 */

@Controller
@Transactional
public class AdminController {

    private final RoleRepository roleRepository;
    private  ComixService comixService;
    private final UserService userService;

    @Autowired
    public AdminController(RoleRepository roleRepository, ComixService comixService,
                           @Lazy UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.comixService = comixService;
    }

    @GetMapping("/admin")
    public String adminIndexPage(Model model) {
        model.addAttribute("activePage", "None");
        return "admin/index";
    }

    @GetMapping("/admin/users")
    public String adminUsersPage(Model model) {
        model.addAttribute("activePage", "Users");
        model.addAttribute("users", userService.findAll());
        return "admin/users";
    }

    @GetMapping("/admin/user/{id}/edit")
    public String adminEditUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit");
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/user_form";
    }

    @GetMapping("/admin/user/create")
    public String adminCreateUser(Model model) {
        model.addAttribute("create");
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/user_form";
    }

    @PostMapping("/admin/user")
    public String adminUpsertUser(@Valid SystemUser user, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", "Users");

        if (bindingResult.hasErrors()) {
            return "admin/user_form";
        }

        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/roles")
    public String adminRolesPage(Model model) {
        model.addAttribute("activePage", "Roles");
        return "admin/index";
    }

    @GetMapping("/admin/categories")
    public String adminCategoriesPage(Model model) {
        model.addAttribute("activePage", "Categories");
        return "admin/index";
    }

    @GetMapping("/admin/comixbook")
    public String adminComixPage(Model model) {
        List<Comix> allComix = comixService.getAllComixList();
        model.addAttribute("comixList", allComix);
        return "admin/comix_adm";
    }

    @RequestMapping(path = "/admin/comixbook/remove/{id}", method = RequestMethod.GET)
    public String removeById(@PathVariable(value = "id") Long comixId) {
        comixService.removeById(comixId);
        return "redirect:/admin/comixbook";
    }

    @RequestMapping(path = "/admin/comixbook/add", method = RequestMethod.GET)
    public String showAddForm(Model model) {
        Comix comix = new Comix();
        comix.setName("");
        model.addAttribute("comix", comix);
        return "admin/comix_add_adm";
    }

    @RequestMapping(path = "/admin/comixbook//add", method = RequestMethod.POST)
    public String showAddForm(Comix comix) {
        comixService.addComix(comix);
        return "redirect:/admin/comixbook";
    }

}
