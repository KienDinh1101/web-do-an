package application.data.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="tbl_promotion")
public class Promotion {
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "promotion_id")
    @Id
    private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="start_date")
	private Date start_date;
	
	@Column(name="end_date")
	private Date end_date;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "promotion")
    private List<ProductPromotion> listProductPromotions = new ArrayList<>();



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public List<ProductPromotion> getListProductPromotions() {
		return listProductPromotions;
	}

	public void setListProductPromotions(List<ProductPromotion> listProductPromotions) {
		this.listProductPromotions = listProductPromotions;
	}
	
	
}
