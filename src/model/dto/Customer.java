package model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@SequenceGenerator(name="cus_seq", sequenceName="cus_seq_id", initialValue=1, allocationSize=1)
@NamedQuery(query="select c from Customer c where c.name=:name", name="Customer.findByName")
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cus_seq")
	@Column(name="customer_id")
	private Long customerId;
	
	private String name;
	
	private int height;
	
	@Column(name="alarm_yn")
	private String alarmYN;
	
	@OneToMany(mappedBy="customer", fetch = FetchType.EAGER)
	List<Reservation> reservations = new ArrayList<>();
	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", height=" + height + ", alarmYN=" + alarmYN +  "]";
	}
}