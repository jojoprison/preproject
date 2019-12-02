package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("user")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView getIndex(ModelAndView modelAndView) {

        modelAndView.setViewName("index");

        System.out.println(modelAndView.getModel());

        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpSession session,
                              @RequestParam("email") String email,
                              @RequestParam("password") String password,
                              RedirectAttributes redirectAttributes) {

        ModelAndView modelAndView = new ModelAndView();

        if (userService.validate(email, password)) {

            User user = userService.getByEmail(email);

            session.setAttribute("userAuth", user);
            session.setMaxInactiveInterval(10 * 60);

            if (user.getRole().equals("admin")) {
                modelAndView.setViewName("redirect:/admin");
            } else {
                modelAndView.setViewName("redirect:/user");
            }
        } else {
            modelAndView.setViewName("redirect:/");
            // из-за редиректа передаем флеш-аттрибут
            redirectAttributes.addFlashAttribute("message", "Either e-mail or password is wrong.");
//            modelAndView.addObject("message", "Either e-mail or password is wrong.");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ModelAndView logout(HttpSession session) {

        ModelAndView modelAndView = new ModelAndView();

        User user = (User) session.getAttribute("userAuth");

        if (user != null) {
            System.out.println("User {" + user.getEmail() + ", " + user.getRole() + "} logout.");
        }

        session.invalidate();

        modelAndView.setViewName("redirect:/");

        return modelAndView;
    }
}