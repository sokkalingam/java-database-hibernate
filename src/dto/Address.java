package dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address {
	
	public Address() {
		
	}
	
	public Address(String streetName, String cityName, String stateName, String zipcode) {
		super();
		this.streetName = streetName;
		this.cityName = cityName;
		this.stateName = stateName;
		this.zipcode = zipcode;
	}
	@Id @GeneratedValue
	private Integer id;
	private String streetName;
	@Override
	public String toString() {
		return "Address [id="
				+ id
				+ ", streetName="
				+ streetName
				+ ", cityName="
				+ cityName
				+ ", stateName="
				+ stateName
				+ ", zipcode="
				+ zipcode
				+ "]";
	}
	private String cityName;
	private String stateName;
	private String zipcode;
	
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
