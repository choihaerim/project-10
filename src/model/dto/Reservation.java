package model.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@SequenceGenerator(name="res_seq", sequenceName="res_seq_id", initialValue=1, allocationSize=1)
public class Reservation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="res_seq")
	private Long reservation_id;
	
	private String time;
	
	private int member_cnt;
	
	private String cancel_yn;
	
	@ManyToOne//객체의 primary key 값을 자동 참조. name 태그는 컬럼명을 정의해 줄 뿐임.
	@JoinColumn(name="attraction_id")
	private Attraction attraction;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
}
