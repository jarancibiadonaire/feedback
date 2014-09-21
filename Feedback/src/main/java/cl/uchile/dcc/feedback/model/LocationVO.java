package cl.uchile.dcc.feedback.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LocationVO implements Serializable {

	private static final long serialVersionUID = 5718031640849145228L;

	private Integer id;
	
	private BigDecimal lat;
	
	private BigDecimal lng;
	
	private String address;
	
	private String comuna;
	
	private String region;
	
	private Date createdDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getComuna() {
		return comuna;
	}

	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getLatLng(){
		return "("+this.lat+","+this.lng+")";
	}

}
