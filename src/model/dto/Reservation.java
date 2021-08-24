package model.dto;

import javax.persistence.CascadeType;
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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(name="res_seq", sequenceName="res_seq_id", initialValue=1, allocationSize=1)
public class Reservation {
	
	@Id
	@Column(name = "reservation_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="res_seq")
	private int reservationId;
	
	private String time;
	
	private int memberCnt;
	
	private String cancelYN;
	
	@ManyToOne(cascade=CascadeType.ALL)//객체의 primary key 값을 자동 참조. name 태그는 컬럼명을 정의해 줄 뿐임.
	@JoinColumn(name="attractionId")
	private Attraction attraction;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="customerId")
	private Customer customer;
}