package application.controller.web;

import application.data.model.User;
import application.data.service.UserService;
import application.model.viewmodel.common.ObjectProductVM;
import application.untity.RandomStrings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Controller
public class PasswordController {

    @Autowired
    UserService userService;

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/forgot-password")
    public String forgotPassword() {


        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String forgot(@RequestParam String email) {
        User user =  userService.findUserByEmail(email);
        if(user!= null){
            RandomStrings rs = new RandomStrings();
            String resetPassword = rs.randomAlphaNumeric(10);
            user.setPasswordReset(resetPassword);
            userService.updateUser(user);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Đặt lại mật khẩu");
            message.setText("Nhấp vào đường link dưới đây để đổi mật khẩu:  \n"+
                    "http://localhost:8080/reset-password/?email="+email+"&reset="+resetPassword);

            try {

                this.emailSender.send(message);
                return "redirect:/forgot-password?success";

            } catch (Exception e) {
                return "redirect:/forgot-password?fail";
            }

        } else  {

            return "redirect:/forgot-password?fail";
        }

    }

    @GetMapping("/reset-password")
    public String resetPasswordGet(
            Model model,
            @Valid @ModelAttribute("productname") ObjectProductVM productName,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "reset", required = false) String reset) {

            model.addAttribute("email", email);
            model.addAttribute("reset", reset);


        return "reset-password";
    }
    @PostMapping("/reset-password")
    public String resetPasswordPost(Model model,
                                    @Valid @ModelAttribute("productname") ObjectProductVM productName,
                                    @RequestParam String email,
                                    @RequestParam String reset,
                                    @RequestParam String newPassword,
                                    @RequestParam String confirmPassword) {
        try {
            User userEntity =  userService.findByEmailAndPassword(email,reset);
            if (userEntity != null && userEntity.getPasswordReset() != null){
                    if( newPassword != "" &&(newPassword.equals(confirmPassword))){
                        userEntity.setPasswordHash(passwordEncoder.encode(newPassword.trim()));
                        userEntity.setPasswordReset(null);
                        userService.updateUser(userEntity);
                        model.addAttribute("user", null);
                        return "redirect:/reset-password?success";
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "redirect:/reset-password?fail";
    }

}
