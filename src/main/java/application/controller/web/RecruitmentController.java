package application.controller.web;

import application.model.viewmodel.common.ObjectProductVM;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/tuyen-dung")
public class RecruitmentController  extends  BaseController{

    @GetMapping(value = "")
    public String news(Model model,
                       HttpServletResponse response,
                       HttpServletRequest request,
                       final Principal principal,
                       @Valid @ModelAttribute("productname") ObjectProductVM productName){

        totalCart(model,response,request,principal);

        return "tuyendung";

    }
}
