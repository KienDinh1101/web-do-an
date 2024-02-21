package application.model.viewmodel.common;

public class ProductPriceVM {
    private double price;
    private  String priceString;
    private double discount;
    private  String discountString;
    private int amount;

    public String getPriceString() {
        return priceString;
    }

    public void setPriceString(String priceString) {
        this.priceString = priceString;
    }

    public String getDiscountString() {
        return discountString;
    }

    public void setDiscountString(String discountString) {
        this.discountString = discountString;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
