package application.data.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name="tbl_role")
public class Role {
	@Id
	@Column(name="role_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "tbl_user_role",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> listRole = new HashSet<>();

	public Role() {
	}


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

	public Set<User> getListRole() {
		return listRole;
	}

	public void setListRole(Set<User> listRole) {
		this.listRole = listRole;
	}

	
	
	
}
