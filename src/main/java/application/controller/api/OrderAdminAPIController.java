package application.controller.api;

import application.constant.OrderConstant;
import application.data.model.Order;
import application.data.model.OrderProduct;
import application.data.model.SizeColor;
import application.data.service.OrderService;
import application.data.service.SizeColorService;
import application.model.api.BaseApiResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/order")
public class OrderAdminAPIController {

    private static final Logger logger = LogManager.getLogger(ProductAdminController.class);
    @Autowired
    OrderService orderService;

    @Autowired
    SizeColorService sizeColorService;


    @PostMapping("/update/{orderId}/{status}")
    public BaseApiResult deleteProduct(@PathVariable int orderId,@PathVariable int status){
        BaseApiResult result = new BaseApiResult();

        try {
            Order order = orderService.findOne(orderId);
                if (order.getStatus() == OrderConstant.CHUA_THANH_TOAN){
                    if(status == OrderConstant.DA_THANH_TOAN){
                        order.setStatus(status);
                        for (OrderProduct  orderProduct: order.getListProductOrders()){
                                orderProduct.setDatePayment(new Date());
                        }
                        orderService.updateOrder(order);
                        result.setSuccess(true);
                        result.setMessage("Cập nhật thành công!");
                        return result;
                    }
                    if(status == OrderConstant.HUY){
                        order.setStatus(status);
                        for (OrderProduct  orderProduct: order.getListProductOrders()){
                            List<SizeColor> sizeColorList = sizeColorService.getProductByColor(orderProduct.getProduct().getId(),orderProduct.getColor());
                            for (SizeColor sizeColor: sizeColorList){
                                int amount = sizeColor.getAmount() + orderProduct.getAmount();
                                sizeColor.setAmount(amount);
                                sizeColorService.updateSizeColor(sizeColor);
                            }
                        }
                        orderService.updateOrder(order);
                        result.setSuccess(true);
                        result.setMessage("Cập nhật thành công!");
                        return result;
                    }

                }



        }catch (Exception e){
            logger.error(e.getMessage());
        }
        result.setSuccess(false);
        result.setMessage("Lỗi!");
        return result;
    }
}
