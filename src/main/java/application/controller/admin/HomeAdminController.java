package application.controller.admin;


import application.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class HomeAdminController extends CommonAdminController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public String home(Model model, HttpServletResponse response,
                       HttpServletRequest request,
                       final Principal principal) {
        String image = image(response,request,principal);
        model.addAttribute("image",image);


        return "/admin/admin";
    }

}
