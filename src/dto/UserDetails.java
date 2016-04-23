package dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UserDetails {
	
	@Id @GeneratedValue
	private int userId;
	private String userName;
	@ElementCollection
	private List<String> listOfString =  new ArrayList<String>();
	@OneToMany(cascade = CascadeType.ALL)
	private List<Address> addresses = new ArrayList<Address>();

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getListOfString() {
		return listOfString;
	}

	public void setListOfString(List<String> listOfString) {
		this.listOfString = listOfString;
	}
	
	public static UserDetails generate() {
		UserDetails usd = new UserDetails();
		
		usd.setUserName("NAME " + new Random().nextInt(100));
		usd.setListOfString(Arrays.asList("Sokka", "Lingam", "Sokkalingam", "Kobe"));
		usd.setAddresses(Arrays.asList(new Address(usd, "Main St", "Waltham", "MA", "01702")));
		return usd;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "UserDetails [userId="
				+ userId
				+ ", userName="
				+ userName
				+ ", listOfString="
				+ listOfString
				+ ", addresses="
				+ addresses
				+ "]";
	}
}
