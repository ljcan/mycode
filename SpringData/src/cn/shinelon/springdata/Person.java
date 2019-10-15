package cn.shinelon.springdata;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Table(name="jpa_person")
@Entity
public class Person {
	private Integer id;
	private String lastName;
	private Date birth;
	private String email;
	private Integer addressId;
	
	private Address address;
	//Ö÷¼ü
	@GeneratedValue
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	@Column(name="add_id")
	public Integer getAddressId() {
		return addressId;
	}
	@JoinColumn(name="address_id")
	@ManyToOne
	public Address getAddress() {
		return address;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", lastName=" + lastName + ", birth=" + birth + ", email=" + email + "]";
	}
	
}
