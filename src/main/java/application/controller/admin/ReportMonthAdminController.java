package application.controller.admin;

import application.data.service.OrderProductService;
import application.data.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
@RequestMapping("/admin/report")
public class ReportMonthAdminController extends CommonAdminController {
    private static final Logger logger = LogManager.getLogger(ReportAdminController.class);

    @Autowired
    OrderService orderService;

    @Autowired
    OrderProductService orderProductService;
    @GetMapping("/month")
    public String reportHome(Model model, HttpServletResponse response,
                             HttpServletRequest request,
                             final Principal principal){

        String image = image(response,request,principal);
        model.addAttribute("image",image);


        return "/admin/report/report-price-by-month";
    }

}
