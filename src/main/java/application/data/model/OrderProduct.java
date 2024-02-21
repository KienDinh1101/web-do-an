package application.data.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="tbl_product_order")
public class OrderProduct {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_order_id")
    @Id
    private int id;
	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "amount")
    private int amount;

    @Column(name = "price")
    private double price;
    
    @Column(name="date_payment")
    private Date datePayment;
    
    @Column(name="year_guarantee")
    private int yearGuarantee;
    
    @Column(name="color")
    private String color;

	@Column(name = "pay")
	private int pay;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public Date getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(Date datePayment) {
		this.datePayment = datePayment;
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

	public OrderProduct() {
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}
}
