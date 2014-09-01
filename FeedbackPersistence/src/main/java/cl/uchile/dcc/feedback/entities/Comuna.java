package cl.uchile.dcc.feedback.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comuna")
public class Comuna {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="comuna_id")
	private Integer id;
	
	@Column(name="comuna_name")
	private String name;
	
	@JoinColumn(name="region")
	@ManyToOne
	private Region region;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}

}
