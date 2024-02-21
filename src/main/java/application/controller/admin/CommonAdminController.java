package application.controller.admin;

import application.controller.web.BaseController;
import application.data.model.User;
import application.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

public class CommonAdminController extends BaseController {
    @Autowired
    UserService userService;
    public String image(HttpServletResponse response,
                       HttpServletRequest request,
                       final Principal principal
    ) {

        this.checkCookie(response, request, principal);
        String  username = SecurityContextHolder.getContext().getAuthentication().getName();
        String image = "";
        if(username!=null) {
            User user = userService.findUserByUsername(username);
            image = user.getAvatar();
        } else {
            image = "/admin/images/avatars/user.jpg";
        }

        return image;
    }
}
