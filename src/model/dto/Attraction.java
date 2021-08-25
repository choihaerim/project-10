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

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="att_seq")
	@Column(name="attraction_id")
	private Long attractionId;
	
	private String name;
	
	private String location;
	
	@Column(name="height_limit")
	private int heightLimit;
	
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
