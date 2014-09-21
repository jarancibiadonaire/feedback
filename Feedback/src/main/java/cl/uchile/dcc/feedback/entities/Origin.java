package cl.uchile.dcc.feedback.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="origin")
public class Origin implements Serializable {

	private static final long serialVersionUID = 1129974037456930859L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="origin_type_id")
	private Integer id;
	
	@Column(name="origin_type")
	private String type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	} 
	
}
