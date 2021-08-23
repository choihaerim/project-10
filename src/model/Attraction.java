package model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Attraction {
	
	@Id
	private Long attraction_id;
	
	private String location;
	
	private int height_limit;
	
	private String parent_yn;
	
	//arraylist 추가
}
