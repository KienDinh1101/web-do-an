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

@Entity(name = "tbl_order")
public class Order {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	@Id
	private int id;
	
	@Column(name = "guid")
    private String guid;

    @Column(name = "username")
    private String userName;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

	private String email;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "price")
	private Double price;

	@Column(name="status")
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getEmail() {
		return email;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderProduct> listProductOrders = new ArrayList<>();

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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	

	public List<OrderProduct> getListProductOrders() {
		return listProductOrders;
	}

	public void setListProductOrders(List<OrderProduct> listProductOrders) {
		this.listProductOrders = listProductOrders;
	}

	public Order() {
	}


}
