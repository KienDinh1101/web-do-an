package application.model.viewmodel.order;

import java.util.List;

public class OrderHistoryVM {
    private List<OrderVM> orderVMS;

    public List<OrderVM> getOrderVMS() {
        return orderVMS;
    }

    public void setOrderVMS(List<OrderVM> orderVMS) {
        this.orderVMS = orderVMS;
    }
}
