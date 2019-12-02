package crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView userLogin() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login_success");

        return modelAndView;
    }
}