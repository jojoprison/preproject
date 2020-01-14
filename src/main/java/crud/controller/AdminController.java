package crud.controller;

import crud.model.User;
import crud.model.UserProfile;
import crud.service.UserProfileService;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/admin")
@SessionAttributes("roles")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    @Autowired
    MessageSource messageSource;

    @GetMapping(value = {"/", "/list"})
    public String listUsers(ModelMap model) {

        List<User> users = userService.getAll();

        model.addAttribute("users", users);
        model.addAttribute("logged_in_user", UserController.getPrincipal());

        return "user_list";
    }

    @GetMapping(value = {"/new_user"})
    public String newUserPage(ModelMap model) {

        User user = new User();

        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        model.addAttribute("logged_in_user", UserController.getPrincipal());

        return "admin/registration";
    }

    @PostMapping(value = {"/new_user"})
    public String newUser(@Valid User user, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            return "admin/registration";
        }

        if (!userService.isSSOUnique(user.getId(), user.getSsoId())) {

            FieldError ssoError = new FieldError("user", "ssoId",
                    messageSource.getMessage("non.unique.ssoId",
                            new String[]{user.getSsoId()}, Locale.getDefault()));

            result.addError(ssoError);

            return "admin/registration";
        }

        userService.add(user);

        model.addAttribute("success", "User " + user.getName() +
                ", " + user.getEmail() + " registered successfully!");
        model.addAttribute("logged_in_user", UserController.getPrincipal());

        return "admin/registration_success";
    }

    @GetMapping(value = {"/edit_user_{ssoId}"})
    public String editUserPage(@PathVariable String ssoId, ModelMap model) {

        User user = userService.getBySSO(ssoId);

        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("logged_in_user", UserController.getPrincipal());

        return "admin/registration";
    }

    @PostMapping(value = {"/edit_user_{ssoId}"})
    public String updateUser(@Valid User user, BindingResult result,
                             ModelMap model, @PathVariable String ssoId) {

        if (result.hasErrors()) {
            return "admin/registration";
        }

        userService.update(user);

        model.addAttribute("success", "User " + user.getName() +
                ", " + user.getEmail() + " updated successfully!");
        model.addAttribute("logged_in_user", UserController.getPrincipal());

        return "admin/registration_success";
    }

    @GetMapping(value = {"/delete_user_{ssoId}"})
    public String deleteUser(@PathVariable String ssoId) {

        userService.deleteBySSO(ssoId);

        return "redirect:/admin/list";
    }

    @GetMapping(value = "/access_denied")
    public String accessDeniedPage(ModelMap model) {

        model.addAttribute("logged_in_user", UserController.getPrincipal());

        return "admin/access_denied";
    }

    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.getAll();
    }
}