package application.data.service;

import application.data.model.Order;
import application.data.model.Product;
import application.data.repository.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private static final Logger logger = LogManager.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public void addNewOrder(Order order) {
        orderRepository.save(order);
    }

    public Order findOne(int orderId) {
        return orderRepository.findOne(orderId);
    }

    public List<Order> findOrderByGuidOrUserName(String guid, String userName) {
        return orderRepository.findOrderByGuidOrUserName(guid,userName);
    }
    public boolean updateOrder(Order order) {
        try {
            orderRepository.save(order);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

}
