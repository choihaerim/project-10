package model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.transaction.Transactional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@SequenceGenerator(name="res_seq", sequenceName="res_seq_id", initialValue=1, allocationSize=1)
public class Reservation {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="res_seq")
	@Column(name="reservation_id")
	private Long reservationId;
	
	private String time;
	
	@Column(name="member_cnt")
	private int memberCnt;
	
	@Column(name="cancel_yn")
	private String cancelYN;
	
	@ManyToOne//객체의 primary key 값을 자동 참조. name 태그는 컬럼명을 정의해 줄 뿐임.
	@JoinColumn(name="attraction_id")
	private Attraction attraction;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="customer_id")
	private Customer customer;

	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", time=" + time + ", memberCnt=" + memberCnt
				+ ", cancelYN=" + cancelYN + ", attraction=" + attraction.getAttractionId() + ", customer=" + customer.getCustomerId() + "]";
	}


	
}