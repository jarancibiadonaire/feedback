package cl.uchile.dcc.feedback.entities;

import java.io.Serializable;

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
public class Comuna implements Serializable {

	private static final long serialVersionUID = 5101647590140902900L;

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
