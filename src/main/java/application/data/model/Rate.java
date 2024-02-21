package application.data.model;

import javax.persistence.*;

@Entity(name = "tbl_rate")
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rate_id")
    private int id;

    @Column(name = "star")
    private int star;

    @Column(name = "username")
    private String userName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;
    @Column(name = "product_id", insertable = false, updatable = false)
    private int productId;

    public Rate() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
