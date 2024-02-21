package application.data.model;

import javax.persistence.*;

@Entity(name = "tbl_size_color")
public class SizeColor {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "size_color_id")
    @Id
    private int id;

    @Column(name = "price")
    private double price;


    @Column(name = "color")
    private String color;

    @Column(name = "amount")
    private int amount;

    @Column(name = "product_id", insertable = false, updatable = false)
    private int productId;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;


    public SizeColor() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
