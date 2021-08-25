package model.dto;

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
@Setter
@Getter
@ToString

@Entity
@SequenceGenerator(name="att_seq", sequenceName="att_seq_id", initialValue=1, allocationSize=1)
public class Attraction {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="att_seq")
	private Long attractionId;
	
	private String name;
	
	private String location;
	
	private int heightLimit;
	
	private String parentYN;
	
	@OneToMany(mappedBy="attraction")
	List<Reservation> reservations = new ArrayList<>();
}