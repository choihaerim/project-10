package model.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@SequenceGenerator(name="cus_seq", sequenceName="cus_seq_id", initialValue=1, allocationSize=1)
@NamedQuery(query="select c from Customer c where c.name=:name", name="Customer.findByName")
public class Customer {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cus_seq")
	private Long customer_id;
	
	private String name;
	
	private int height;
	
	private String alarm_yn;
	
	@OneToMany(mappedBy="customer")
	List<Reservation> reservations = new ArrayList<>();

	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", name=" + name + ", height=" + height + ", alarm_yn="
				+ alarm_yn + reservations;
	}
	
	
}
