package application.data.service;

import application.data.model.OrderProduct;
import application.data.repository.OrderProductRepository;
import application.model.dto.OrderProductMonth;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderProductService {
    private static final Logger logger = LogManager.getLogger(OrderProductService.class);

    @Autowired
    OrderProductRepository orderProductRepository;

    public List<OrderProduct> findAll() {
        return orderProductRepository.findAll();
    }


    public OrderProduct findOne(int orderProductId) {
        return orderProductRepository.findOne(orderProductId);
    }

    public List<OrderProduct> getOrderProductByOrder(Integer orderId) {
        return orderProductRepository.getOrderProductByOrder(orderId);
    }
    public boolean updateOrder(OrderProduct orderProduct) {
        try {
            orderProductRepository.save(orderProduct);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }
    public List<OrderProduct> getOrderProductByDate(Date datePaymentStart, Date datePaymentEnd) {
        return orderProductRepository.getOrderProductByDate(datePaymentStart,datePaymentEnd);
    }

    public List<Object[]> listOrderByMonth(Integer year) {
        return orderProductRepository.listOrderByMonth(year);
    }




}
