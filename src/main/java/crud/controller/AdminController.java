package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView getAllUsers() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/users");

        List<User> users = userService.getAllUsers();
        modelAndView.addObject("userList", users);

        return modelAndView;
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.GET)
    public ModelAndView modifyPageEmpty() {

        return new ModelAndView("admin/user_modify");
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.GET)
    public ModelAndView modifyPage(@RequestParam("id") Long id) {

        ModelAndView modelAndView = new ModelAndView("admin/user_modify");

        modelAndView.addObject("user", userService.getById(id));

        return modelAndView;
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    public ModelAndView updateUser(@ModelAttribute("user") User user) {

        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("/admin");
        modelAndView.setViewName("redirect:/admin");

        userService.update(user);

        return modelAndView;
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") User user) {

        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("/admin");
        modelAndView.setViewName("redirect:/admin");

        userService.add(user);

        return modelAndView;
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
    public ModelAndView deleteUser(@RequestParam("id") Long id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");

        User userDelete = userService.getById(id);

        userService.delete(userDelete);

        return modelAndView;
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView errorPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error_access");

        return modelAndView;
    }
}