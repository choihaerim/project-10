package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Reservation {
	
	@Id
	private Long reservation_id;
	
	private Long costomer_id;
	
	private String time;
	
	private int member_cnt;
	
	private String cancel_yn;
	
	@ManyToOne
	@JoinColumn(name="attraction_id")
	private Attraction attraction;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
}
