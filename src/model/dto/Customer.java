package model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

@Entity
@SequenceGenerator(name="cus_seq", sequenceName="cus_seq_id", initialValue=1, allocationSize=1)
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cus_seq")
	@Column(name="customer_id")
	private Long customerId;
	
	private String name;
	
	private int height;
	
	@Column(name="alarm_yn")
	private String alarmYN;
	
	@OneToMany(mappedBy="customer")
	List<Reservation> reservations = new ArrayList<>();
}

