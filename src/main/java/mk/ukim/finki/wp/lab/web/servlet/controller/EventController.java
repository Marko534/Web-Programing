package mk.ukim.finki.wp.lab.web.servlet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//public class EventController {
//}


@Controller
@RequestMapping("/home")
public class EventController {
    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("bodyContent", "home");
        return "master-template";
    }
}
