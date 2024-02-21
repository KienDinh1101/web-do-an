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

@Entity(name="tbl_product_image")
public class ProductImage {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_image_id")
	@Id
	private int id;
	
	@Column(name="link")
	private String link;
	
	@Column(name ="create_date")
	private Date createDate;
	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

	public ProductImage() {
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
