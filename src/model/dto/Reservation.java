/**
 * PROJECT : 놀이기구 사전 예약 프로그램
 * NAME : Reservation.java
 * DESC : 예약(Reservation) 정보
 * 
 * @author  정은진(B), 방지원
 * @version 1.0
 */
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
	
	/** sequence */
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="res_seq")
	@Column(name="reservation_id")
	private Long reservationId;
	
	/** 예약 시간 */
	private String time;
	
	/** 예약 인원 */
	@Column(name="member_cnt")
	private int memberCnt;
	
	@ManyToOne(fetch=FetchType.EAGER)//객체의 primary key 값을 자동 참조. name 태그는 컬럼명을 정의해 줄 뿐임.
	@JoinColumn(name="attraction_id")
	private Attraction attraction;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="customer_id")
	private Customer customer;

	@Override
	public String toString() {
		return "[예약번호=" + reservationId + ", 시간=" + time + ", 일행 수=" + memberCnt
				+ ", 놀이기구번호=" + attraction.getAttractionId() + ", 회원번호=" + customer.getCustomerId()+"]";
	}
	
}