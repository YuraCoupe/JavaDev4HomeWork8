package ua.goit.ProductStore.controller;

import org.eclipse.sisu.plexus.config.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.ProductStore.model.*;
import ua.goit.ProductStore.service.RoleService;
import ua.goit.ProductStore.service.UserService;
import ua.goit.ProductStore.service.ProductService;
import ua.goit.ProductStore.validator.ManufacturerValidator;
import ua.goit.ProductStore.validator.UserValidator;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final UserValidator validatorService;

    @Autowired
    public UserController(UserService userService, RoleService roleService, UserValidator validatorService) {
        this.userService = userService;
        this.roleService = roleService;
        this.validatorService = validatorService;
    }

    @Autowired
    @Qualifier("userValidator")
    private Validator validator;

    @InitBinder("user")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
        binder.registerCustomEditor(Role.class, "roles", new RoleEditor(roleService));
    }

    @ModelAttribute("user")
    public User createUserModel() {
        return new User();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getUsers(Model model) {
        Set<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(path = "/administrators", method = RequestMethod.GET)
    public String getUsersWithAdministratorRole(Model model) {
        Set<User> users = userService.findUsersWithAdministratorRole();
        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public String getUsersWithUserRole(Model model) {
        Set<User> users = userService.findUsersWithUserRole();
        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public String showNewForm(Model model) {
        Set<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        //model.addAttribute("manufacturer", new Manufacturer());
        model.addAttribute("user", new User());
        return "user";
    }

    @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
    public String showEditForm(@PathVariable UUID id, Model model) {
        Set<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        //model.addAttribute("role", new Role());
        model.addAttribute("user", userService.findById(id));
        return "user";
    }

    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    public String showEditFormWithParam(@RequestParam UUID id, Model model) {
        Set<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        //model.addAttribute("role", new Role());
        model.addAttribute("user", userService.findById(id));
        return "user";    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView submit(@ModelAttribute("user") @Validated User user,
                         BindingResult result) {
        ModelAndView model = new ModelAndView();
        if (result.hasErrors()) {
//            if (Objects.nonNull(user.getId())) {
//                user = userService.findById(user.getId());
//            }
            Set<Role> roles = roleService.findAll();
            model.addObject("roles", roles);
            model.addObject("user", user);
            model.setViewName("user");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        userService.save(user);
        Set<User> users = userService.findAll();
        model.addObject("users", users);
        model.setViewName("users");
        return model;
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable UUID id, ModelMap model){
        ErrorMessage errorMessage = validatorService.validateUserToDelete(id);
        if (!errorMessage.getErrors().isEmpty()) {
            model.addAttribute("errorMessage", errorMessage);
        } else {
            userService.delete(id);
        }
        Set<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }
}
