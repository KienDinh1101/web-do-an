package application.controller.admin;

import application.data.model.Order;
import application.data.model.OrderProduct;
import application.data.service.OrderService;
import application.model.viewmodel.home.HomeLandingVM;
import application.model.viewmodel.order.OrderDetailVM;
import application.model.viewmodel.order.OrderProductVM;
import application.model.viewmodel.order.OrderVM;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/admin/order")
public class OrderAdminController extends CommonAdminController {

    private static final Logger logger = LogManager.getLogger(OrderAdminController.class);

    @Autowired
    OrderService orderService;

    @GetMapping("")
    public String tableOrder(Model model, HttpServletResponse response,
                             HttpServletRequest request,
                             final Principal principal) {
        String image = image(response,request,principal);
        model.addAttribute("image",image);
        HomeLandingVM vm = new HomeLandingVM();

       List<Order>  orders = orderService.findAll();
       List<OrderVM> orderVMList = new ArrayList<>();
       for(Order order: orders){
           OrderVM orderVM = new OrderVM();

           orderVM.setId(order.getId());
           orderVM.setCustomerName(order.getCustomerName());
           orderVM.setAddress(order.getAddress());
           orderVM.setEmail(order.getEmail());
           orderVM.setStatus(order.getStatus());
           orderVM.setCreatedDate(order.getCreateDate());
           orderVM.setPhoneNumber(order.getPhoneNumber());
           orderVM.setPrice(String.valueOf(order.getPrice()));
           orderVMList.add(orderVM);
       }

        vm.setOrderVMList(orderVMList);

        model.addAttribute("vm",vm);
        return "admin/order/order";
    }
    @GetMapping("/detail/{orderId}")
    public String getOrderDetail(Model model, HttpServletResponse response,
                                 HttpServletRequest request,
                                 final Principal principal,
                                 @PathVariable("orderId") int orderId) {

        String image = image(response,request,principal);
        model.addAttribute("image",image);

        OrderDetailVM vm = new OrderDetailVM();

        DecimalFormat df = new DecimalFormat("####0.00");

        double totalPrice = 0;

        List<OrderProductVM> orderProductVMS = new ArrayList<>();

        Order orderEntity = orderService.findOne(orderId);


        if(orderEntity != null) {

            for(OrderProduct orderProduct : orderEntity.getListProductOrders()) {
                OrderProductVM orderProductVM = new OrderProductVM();
                orderProductVM.setId(orderEntity.getId());
                orderProductVM.setProductId(orderProduct.getProduct().getId());
                orderProductVM.setMainImage(orderProduct.getProduct().getMainImage());
                orderProductVM.setAmount(orderProduct.getAmount());
                orderProductVM.setName(orderProduct.getProduct().getName());
                orderProductVM.setOrderId(orderProduct.getOrder().getId());
                orderProductVM.setColor(orderProduct.getColor());
                orderProductVM.setYearGuarantee(orderProduct.getYearGuarantee());
                orderProductVM.setPay(orderProduct.getPay());
                orderProductVM.setDatePayment(orderProduct.getDatePayment());
                orderProductVM.setPrice(df.format(orderProduct.getPrice()));

                totalPrice += orderProduct.getPrice() * orderProduct.getAmount();

                orderProductVMS.add(orderProductVM);
            }
            vm.setStatus(orderEntity.getStatus());
        }

        vm.setOrderId(orderId);
        vm.setOrderProductVMS(orderProductVMS);
        vm.setTotalPrice(df.format(totalPrice));
        vm.setTotalProduct(orderProductVMS.size());

        model.addAttribute("vm",vm);

        return "/admin/order/order-detail";
    }

    @GetMapping("/print/{orderId}")
    public String printOrder(Model model, HttpServletResponse response,
                             HttpServletRequest request,
                             final Principal principal ,@PathVariable("orderId") int orderId) {


        String image = image(response,request,principal);
        model.addAttribute("image",image);

        OrderDetailVM vm = new OrderDetailVM();

        DecimalFormat df = new DecimalFormat("####0.00");

        double totalPrice = 0;

        Order order = orderService.findOne(orderId);
        OrderVM orderVM = new OrderVM();
        if (order != null) {
            orderVM.setId(order.getId());
            orderVM.setCustomerName(order.getCustomerName());
            orderVM.setAddress(order.getAddress());
            orderVM.setEmail(order.getEmail());
            orderVM.setPhoneNumber(order.getPhoneNumber());
            orderVM.setStatus(order.getStatus());
        }
        vm.setOrderVMS(orderVM);

        List<OrderProductVM> orderProductVMS = new ArrayList<>();
        for(OrderProduct orderProduct: order.getListProductOrders()){
            OrderProductVM orderProductVM = new OrderProductVM();
            orderProductVM.setProductName(orderProduct.getProduct().getName());
            orderProductVM.setColor(orderProduct.getColor());
            orderProductVM.setAmount(orderProduct.getAmount());
            orderProductVM.setPrice(df.format(orderProduct.getPrice()));
            orderProductVM.setTotalPriceOne(orderProduct.getPrice() * orderProduct.getAmount());
            totalPrice += orderProduct.getPrice() * orderProduct.getAmount();
            orderProductVMS.add(orderProductVM);
        }

        vm.setTotalPrice(df.format(totalPrice));
        vm.setDateNow(new Date());
        vm.setOrderProductVMS(orderProductVMS);

        model.addAttribute("vm",vm);


        return "/admin/order/order-print";
    }
}
