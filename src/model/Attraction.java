package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@SequenceGenerator(name="no_seq", sequenceName="attraction_no_seq", initialValue=1, allocationSize=1)
public class Attraction {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="no_seq")
	private Long no;
	
	@Column(name="attraction_id")
	private int attractionId;
	
	@Column(name="attraction_name")
	private String attractionName;
	
	private String location;
	
	@Column(name="height_limit")
	private float heightLimit;
	
	@Column(name="parent_yn")
	private String parentYN;
	
	private Reservation reservation;
}
