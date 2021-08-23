package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Reservation {
	
	@Id
	private Long reservation_id;
	
	private Long costomer_id;
	
	private String time;
	
	private int member_cnt;
	
	private String cancel_yn;
}
