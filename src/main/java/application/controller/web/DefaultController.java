package application.controller.web;

import application.constant.StatusRegisterUserEnum;
import application.data.model.User;
import application.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class DefaultController extends BaseController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    @GetMapping("/success")
    public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException {

            String role =  authResult.getAuthorities().toString();

            if(role.contains("ROLE_ADMIN")){
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/admin"));
            }
            else if(role.contains("ROLE_USER")) {
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
            }
    }



    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

    @GetMapping("/404")
    public String error404() {
        return "/error/404";
    }

    @GetMapping(path="/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "/register";
    }


    @RequestMapping(path="/register-user", method = RequestMethod.POST)
    public String registerNewUser(@Valid @ModelAttribute("user") User user){
        StatusRegisterUserEnum statusRegisterUserEnum = userService.registerNewUser(user);
        if (statusRegisterUserEnum.equals(StatusRegisterUserEnum.Existed_Username)){
            return "redirect:/register?email_exits";
        } else if (statusRegisterUserEnum.equals(StatusRegisterUserEnum.Existed_Email)){
            return "redirect:/register?user_exits";
        } else if (statusRegisterUserEnum.equals(StatusRegisterUserEnum.Error_OnSystem)){
            return "redirect:/register?error_system";
        }else{
            return "redirect:/login";
        }
    }


//    @PostMapping("/login")
//    public String login1(Model model,
//                         @Valid @ModelAttribute("productname") ObjectProductVM productName,
//                         @RequestParam(name = "username", required = false) String username) {
//
//        User user = userService.findUserByUsername(username);
//        if (user != null){
//            List<Integer> roles = userService.findRoleByUser(user.getId());
//            for (Integer role: roles){
//                if ( role == RoleIdConstant.Role_Admin){
//                    return "admin";
//                } else {
//                    return "";
//                }
//            }
//
//        }
//        return "/login";
//    }
}
