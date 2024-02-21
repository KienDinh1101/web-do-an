package application.data.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="tbl_cart")
public class Cart {
	
	@Id
	@Column(name="cart_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

    @Column(name = "guid")
    private String guid;

    @Column(name = "username")
    private String userName;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<CartProduct> listCartProducts = new ArrayList<>();

	public Cart() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<CartProduct> getListCartProducts() {
		return listCartProducts;
	}

	public void setListCartProducts(List<CartProduct> listCartProducts) {
		this.listCartProducts = listCartProducts;
	}
    
    
}
