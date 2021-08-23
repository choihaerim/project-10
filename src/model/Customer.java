package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@SequenceGenerator(name="no1_seq", sequenceName="customer_no_seq", initialValue=1, allocationSize=1)
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="no2_seq")
	private Long no;
	
	@Column(name="customer_id")
	private int customerId;
	
	private String name;
	
	private float height;
	
	@Column(name="alarm_yn")
	private String alarmYN;
	
	//(fetch = FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name="resevation_id")
	private Reservation reservationId;
}
