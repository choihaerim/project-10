package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Customer {
	
	@Id
	@Column
	private Long customer_id;
	
	private String name;
	
	private int height;
	
	private String alarm_yn;
	
	//arraylist 추가
}
