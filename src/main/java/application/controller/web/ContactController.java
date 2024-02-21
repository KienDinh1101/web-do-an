package application.controller.web;

import application.constant.MyConstant;
import application.model.viewmodel.common.ObjectProductVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/lien-he")
public class ContactController extends BaseController {

    @Autowired
    public JavaMailSender emailSender;

    @GetMapping(value = "")
    public String news(Model model,
                       HttpServletResponse response,
                       HttpServletRequest request,
                       final Principal principal,
                       @Valid @ModelAttribute("productname") ObjectProductVM productName){
        totalCart(model,response,request,principal);

        return "lienhe";

    }



    @PostMapping("/send")
    public String sendSimpleEmail(
            Model model,
            HttpServletResponse response,
            HttpServletRequest request,
            final Principal principal,
            @RequestParam String name,@RequestParam String email,@RequestParam String content) {

//        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(MyConstant.MY_EMAIL);
        message.setSubject("Dịch vụ, sản phẩm");
        message.setText("Hello: "+ name +" \n"+
                "Mail: " + email +" \n"
                + content);
        // Send Message!



        SimpleMailMessage message1 = new SimpleMailMessage();

        message1.setTo(email);
        message1.setSubject("Web điện thoại");
        message1.setText("Cảm ơn: "+ name +" \n"+
                "ý kiến của bạn đã được ghi lại");

        try {
            // Send Message!
            this.emailSender.send(message);
            this.emailSender.send(message1);

            totalCart(model,response,request,principal);
            return "redirect:/lien-he?success";

        } catch (Exception e) {
            return "redirect:/lien-he?fail";
        }


    }
}
