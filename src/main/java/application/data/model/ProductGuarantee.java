package application.data.model;

import javax.persistence.*;

@Entity(name = "tbl_product_guarantee")
public class ProductGuarantee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "detail")
    private String detail;

    @Column(name = "day_guarantee")
    private String guaranteeDay;

    @Column(name = "product_order_id", insertable = false, updatable = false)
    private int productOrderId;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_order_id")
    private Product product;

    public ProductGuarantee() {
    }

    public int getId() {
        return id;
    }

    public int getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(int productOrderId) {
        this.productOrderId = productOrderId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getGuaranteeDay() {
        return guaranteeDay;
    }

    public void setGuaranteeDay(String guaranteeDay) {
        this.guaranteeDay = guaranteeDay;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
