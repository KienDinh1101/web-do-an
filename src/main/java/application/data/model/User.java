package application.data.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity(name = "tbl_user")
public class User {
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="username",unique=true)
	private String userName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="avatar")
	private String avatar;
	
	@Column(name="name")
	private String name;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="address")
	private String address;

	 @Column(name="password_hash")
	 private String passwordHash;

	@Column(name="password_reset")
	private String passwordReset;
	
	@Transient
	private String password;
	
	@Column(name="phone_number")
	private String phone;
	
	@Column(name="create_date")
	private Date createDate;


	public User() {
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getPasswordReset() {
		return passwordReset;
	}

	public void setPasswordReset(String passwordReset) {
		this.passwordReset = passwordReset;
	}
}
