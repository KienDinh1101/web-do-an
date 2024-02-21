package application.controller.admin;
;
import application.data.model.OrderProduct;
import application.data.service.OrderProductService;
import application.data.service.OrderService;
import application.model.viewmodel.admin.ReportVM;
import application.model.viewmodel.order.OrderDetailVM;
import application.model.viewmodel.order.OrderProductVM;
import application.untity.FormatCurrency;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/report")
public class ReportAdminController extends CommonAdminController {
    private static final Logger logger = LogManager.getLogger(ReportAdminController.class);

    @Autowired
    OrderService orderService;

    @Autowired
    OrderProductService orderProductService;

    @GetMapping("")
    public String reportHome(Model model, HttpServletResponse response,
                             HttpServletRequest request,
                             final Principal principal,
                             @RequestParam(name = "datePaymentStart", required = false) String datePaymentStart,
                             @RequestParam(name = "datePaymentEnd", required = false) String datePaymentEnd){


        String image = image(response,request,principal);
        model.addAttribute("image",image);
        OrderDetailVM vm = new OrderDetailVM();
        ReportVM rp = new ReportVM();
        List<OrderProductVM> orderProductVMS = new ArrayList<>();


        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm a");
        SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
        Date dateStart = null;
        Date dateEnd = null;
        String dateS =  "";

        String dateE = "";
        try {
            dateStart = format.parse(String.valueOf(datePaymentStart));
            dateS = fm.format(dateStart);
            dateEnd = format.parse(String.valueOf(datePaymentEnd));
            dateE =  fm.format(dateEnd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!dateS.equals("") && !dateE.equals("")){
            rp.setDateE(dateE);
            rp.setDateS(dateS);
        }


        List<OrderProduct> orderProducts = orderProductService.getOrderProductByDate(dateStart,dateEnd);
        FormatCurrency fc = new FormatCurrency();
        double totalPrice = 0;
        int totalProduct = 0;
        if(orderProducts != null && orderProducts.size() > 0) {

            for(OrderProduct orderProduct : orderProducts) {
                OrderProductVM orderProductVM = new OrderProductVM();
                orderProductVM.setId(orderProduct.getId());
                orderProductVM.setProductId(orderProduct.getProduct().getId());
                orderProductVM.setMainImage(orderProduct.getProduct().getMainImage());
                orderProductVM.setAmount(orderProduct.getAmount());
                orderProductVM.setName(orderProduct.getProduct().getName());
                orderProductVM.setOrderId(orderProduct.getOrder().getId());
                orderProductVM.setColor(orderProduct.getColor());
                orderProductVM.setYearGuarantee(orderProduct.getYearGuarantee());
                orderProductVM.setPay(orderProduct.getPay());
                orderProductVM.setDatePayment(orderProduct.getDatePayment());
                orderProductVM.setPrice(fc.vietNam(orderProduct.getPrice()));

                totalPrice += orderProduct.getPrice() * orderProduct.getAmount();
                totalProduct += orderProduct.getAmount();
                orderProductVMS.add(orderProductVM);
            }

        }
        vm.setTotalPrice(fc.vietNam(totalPrice));
        rp.setTotalProduct(totalProduct);
        vm.setOrderProductVMS(orderProductVMS);
        model.addAttribute("rp",rp);
        model.addAttribute("vm",vm);

        return "admin/report/report-price-product";
    }
}
