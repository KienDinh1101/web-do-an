package application.model.viewmodel.order;

import java.util.Date;

public class OrderProductVM {
    private int id ;
    private int productId;
    private String productName;
    private String mainImage;
    private int amount;
    private String price;
    private String name;
    private String color;
    private int yearGuarantee;
    private int pay;
    private int orderId;
    private Date datePayment;
    private double totalPriceOne;

    public double getTotalPriceOne() {
        return totalPriceOne;
    }

    public void setTotalPriceOne(double totalPriceOne) {
        this.totalPriceOne = totalPriceOne;
    }

    public Date getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(Date datePayment) {
        this.datePayment = datePayment;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getYearGuarantee() {
        return yearGuarantee;
    }

    public void setYearGuarantee(int yearGuarantee) {
        this.yearGuarantee = yearGuarantee;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }
}
