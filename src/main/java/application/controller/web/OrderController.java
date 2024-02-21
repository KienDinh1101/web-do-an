package application.controller.web;

import application.data.model.*;
import application.data.service.*;
import application.model.viewmodel.common.ProductVM;
import application.model.viewmodel.order.OrderDetailVM;
import application.model.viewmodel.order.OrderHistoryVM;
import application.model.viewmodel.order.OrderProductVM;
import application.model.viewmodel.order.OrderVM;
import application.untity.FormatCurrency;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/order")
public class OrderController extends BaseController {

    private static final Logger logger = LogManager.getLogger(OrderController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private SizeColorService sizeColorService;


    @Autowired
    private CartProductService cartProductService;

    @GetMapping("/checkout")
    public String checkout(Model model,
                           HttpServletResponse response,
                           HttpServletRequest request,
                           @Valid @ModelAttribute("productname") ProductVM productName,
                           final Principal principal) {

        OrderVM order = new OrderVM();
        if(principal!= null) {
            String  username = SecurityContextHolder.getContext().getAuthentication().getName();
            User userEntity = userService.findUserByUsername(username);
            if(userEntity!= null) {
                order.setAddress(userEntity.getAddress());
                order.setCustomerName(userEntity.getName());
                order.setPhoneNumber(userEntity.getPhone());
                order.setEmail(userEntity.getEmail());
            }
        }


        model.addAttribute("order",order);
        totalCart(model,response,request,principal);
        return "/checkout";
    }

    @PostMapping("/checkout")
    public String checkout(Model model,
                          @Valid @ModelAttribute("order") OrderVM orderVM,
                           @Valid @ModelAttribute("productname") ProductVM productName,
                           HttpServletResponse response,
                           HttpServletRequest request,
                           final Principal principal) {
        Order order = new Order();

        boolean flag = false;

        Cookie cookie[] = request.getCookies();

        String guid = null;

        if(cookie!=null) {
            for(Cookie c : cookie) {
                if(c.getName().equals("guid")) {
                    flag = true;
                    guid = c.getValue();
                }
            }
        }

        if(flag == true) {

            double totalPrice = 0;

            if(principal != null) {
                String  username = SecurityContextHolder.getContext().getAuthentication().getName();
                order.setUserName(username);
            }

            order.setGuid(guid);
            order.setAddress(orderVM.getAddress());
            order.setEmail(orderVM.getEmail());
            order.setPhoneNumber(orderVM.getPhoneNumber());
            order.setCustomerName(orderVM.getCustomerName());
            order.setStatus(1);
            order.setCreateDate(new Date());

            Cart cartEntity = cartService.findFirstCartByGuid(guid);
            if(cartEntity != null) {
                List<CartProduct> cartProductList = cartProductService.findCartProductByCart(cartEntity);
                if (cartProductList.size() > 0) {
                    List<OrderProduct> orderProducts = new ArrayList<>();
                    for (CartProduct cartProduct : cartEntity.getListCartProducts()) {
                        List<SizeColor> sizeColorList = sizeColorService.getProductByColor(cartProduct.getProduct().getId(),cartProduct.getColor());
                        for (SizeColor sizeColor: sizeColorList){
                            if(sizeColor.getAmount()>= cartProduct.getAmount()){
                                OrderProduct orderProduct = new OrderProduct();
                                orderProduct.setOrder(order);
                                orderProduct.setPay(1);

                                orderProduct.setYearGuarantee(cartProduct.getProduct().getYearGuaratee());
                                orderProduct.setColor(cartProduct.getColor());
                                orderProduct.setProduct(cartProduct.getProduct());
                                orderProduct.setAmount(cartProduct.getAmount());

                                double price = cartProduct.getAmount() * cartProduct.getPrice();
                                totalPrice += price;

                                orderProduct.setPrice(cartProduct.getPrice());
                                orderProducts.add(orderProduct);
                                List<SizeColor> sizeColors = sizeColorService.getProductByColor(cartProduct.getProductId(),cartProduct.getColor());
                                if(sizeColors.size() > 0){
                                    for (SizeColor sizeColor1: sizeColors){
                                        int amount = sizeColor1.getAmount() - cartProduct.getAmount();
                                        sizeColor.setAmount(amount);
                                        sizeColorService.updateSizeColor(sizeColor1);
                                    }

                                }
                                order.setListProductOrders(orderProducts);
                                order.setPrice(totalPrice);

                                orderService.addNewOrder(order);
                                cartService.deleteCart(cartEntity.getId());

                            } else {
                                return "redirect:/order/checkout?fail";
                            }
                        }


                    }



                }

            }
        }

        return "redirect:/order/history";
    }

    @GetMapping("/history")
    public String orderHistory(Model model,
                               @Valid @ModelAttribute("productname") ProductVM productName,
                               HttpServletResponse response,
                               HttpServletRequest request,
                               final Principal principal) {

        OrderHistoryVM vm = new OrderHistoryVM();

        FormatCurrency fc = new FormatCurrency();


        List<OrderVM> orderVMS = new ArrayList<>();

        Cookie[] cookie = request.getCookies();

        String guid = null;
        boolean flag = false;

        List<Order> orderEntityList = null;

        if(principal != null) {
            String  username = SecurityContextHolder.getContext().getAuthentication().getName();
            orderEntityList = orderService.findOrderByGuidOrUserName(null,username);
        } else {
            if(cookie != null) {
                for(Cookie c : cookie) {
                    if(c.getName().equals("guid")) {
                        flag = true;
                        guid = c.getValue();
                    }
                }
                if(flag == true) {
                    orderEntityList = orderService.findOrderByGuidOrUserName(guid,null);
                }
            }
        }

        if(orderEntityList != null) {
            for(Order order : orderEntityList) {
                OrderVM orderVM = new OrderVM();
                orderVM.setId(order.getId());
                orderVM.setCustomerName(order.getCustomerName());
                orderVM.setEmail(order.getEmail());
                orderVM.setAddress(order.getAddress());
                orderVM.setPhoneNumber(order.getPhoneNumber());
                orderVM.setPrice(fc.vietNam(order.getPrice()));
                orderVM.setCreatedDate(order.getCreateDate());
                orderVM.setStatus(order.getStatus());
                orderVMS.add(orderVM);
            }
        }


        vm.setOrderVMS(orderVMS);

        model.addAttribute("vm",vm);
        totalCart(model,response,request,principal);
        return "/order-history";
    }


    @GetMapping("/history/{orderId}")
    public String getOrderDetail(Model model,
                                 HttpServletResponse response,
                                 HttpServletRequest request,
                                 final Principal principal,
                                 @Valid @ModelAttribute("productname") ProductVM productName,
                                 @PathVariable("orderId") int orderId) {

        OrderDetailVM vm = new OrderDetailVM();

        FormatCurrency fc = new FormatCurrency();

        double totalPrice = 0;

        List<OrderProductVM> orderProductVMS = new ArrayList<>();

        Order orderEntity = orderService.findOne(orderId);

        if(orderEntity != null) {
            for(OrderProduct orderProduct : orderEntity.getListProductOrders()) {
                OrderProductVM orderProductVM = new OrderProductVM();

                orderProductVM.setProductId(orderProduct.getProduct().getId());
                orderProductVM.setMainImage(orderProduct.getProduct().getMainImage());
                orderProductVM.setAmount(orderProduct.getAmount());
                orderProductVM.setName(orderProduct.getProduct().getName());

                orderProductVM.setPrice(fc.vietNam(orderProduct.getPrice() * orderProduct.getAmount()));

                totalPrice += orderProduct.getPrice() * orderProduct.getAmount();

                orderProductVMS.add(orderProductVM);
            }
        }


        vm.setOrderProductVMS(orderProductVMS);
        vm.setTotalPrice(fc.vietNam(totalPrice));
        vm.setTotalProduct(orderProductVMS.size());

        model.addAttribute("vm",vm);
        totalCart(model,response,request,principal);
        return "/order-detail";
    }

}
