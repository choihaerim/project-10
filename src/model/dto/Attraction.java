/**
 * PROJECT : 놀이기구 사전 예약 프로그램
 * NAME : Attraction.java
 * DESC : 놀이기구(Attraction) 정보
 * 
 * @author  최해림
 * @version 1.0
 */
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
@SequenceGenerator(name="att_seq", sequenceName="att_seq_id", initialValue=1, allocationSize=1)
@NamedQuery(query="select a from Attraction a where a.name=:name", name="Attraction.findByName")
public class Attraction {
	
	/** sequence */
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="att_seq")
	@Column(name="attraction_id")
	private Long attractionId;
	
	/** 놀이기구 이름 */
	private String name;
	
	/** 놀이기구 위치 */
	private String location;
	
	/** 해당 놀이기구 신장(키) 제한 */
	@Column(name="height_limit")
	private int heightLimit;
	
	/** 부모 동반 탑승 여부 */
	@Column(name="parent_yn")
	private String parentYN;
	
	@OneToMany(mappedBy="attraction", fetch = FetchType.EAGER)
	List<Reservation> reservations = new ArrayList<>();

	@Override
	public String toString() {
		return "Attraction [attractionId=" + attractionId + ", name=" + name + ", location=" + location
				+ ", heightLimit=" + heightLimit + ", parentYN=" + parentYN + ", reservations=" + reservations + "]";
	}
	
}