package application.model.viewmodel.order;

import java.util.Date;
import java.util.List;

public class OrderDetailVM {

    private List<OrderProductVM> orderProductVMS;

    private OrderVM orderVMS;

    private String totalPrice;
    private int totalProduct;
    private  int status;
    private  int orderId;
    private Date dateNow;



    public OrderVM getOrderVMS() {
        return orderVMS;
    }

    public void setOrderVMS(OrderVM orderVMS) {
        this.orderVMS = orderVMS;
    }

    public Date getDateNow() {
        return dateNow;
    }

    public void setDateNow(Date dateNow) {
        this.dateNow = dateNow;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<OrderProductVM> getOrderProductVMS() {
        return orderProductVMS;
    }

    public void setOrderProductVMS(List<OrderProductVM> orderProductVMS) {
        this.orderProductVMS = orderProductVMS;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(int totalProduct) {
        this.totalProduct = totalProduct;
    }
}
