package model;

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
@Getter
@Setter
@ToString

@Entity
@SequenceGenerator(name="no2_seq", sequenceName="reserv_no_seq", initialValue=1, allocationSize=1)
public class Reservation {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="no1_seq")
	private Long no;
	
	@Column(name="resevation_id")
	private int reservationId;
	
	@Column(name="customer_id")
	private int customerId;
	
	private String time;
	
	@Column(name="member_cnt")
	private int memberCnt;
	
	@Column(name="cancel_yn")
	private String cancelYN;
	
	@OneToMany(mappedBy="reservationId")
	List<Customer> customers = new ArrayList<>();
}
