package application.model.viewmodel.cart;

import application.model.viewmodel.common.ObjectProductVM;

import java.util.List;

public class CartVM {
    private int productAmount;
    private List<CartProductVM> cartProductVMS;
    private ObjectProductVM objectProductVM;
    private String totalPrice;

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public List<CartProductVM> getCartProductVMS() {
        return cartProductVMS;
    }

    public void setCartProductVMS(List<CartProductVM> cartProductVMS) {
        this.cartProductVMS = cartProductVMS;
    }

    public ObjectProductVM getObjectProductVM() {
        return objectProductVM;
    }

    public void setObjectProductVM(ObjectProductVM objectProductVM) {
        this.objectProductVM = objectProductVM;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
