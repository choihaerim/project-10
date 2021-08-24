package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;


@Entity
@SequenceGenerator(name="att_seq", sequenceName="att_seq_id", initialValue=1, allocationSize=1)
public class Attraction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="att_seq")
	private Long attraction_id;
	
	private String location;
	
	private int height_limit;
	
	private String parent_yn;
	
	@OneToMany(mappedBy="attraction")
	List<Reservation> reservations = new ArrayList<>();
}
