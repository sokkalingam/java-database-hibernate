package dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserDetails {
	
	@Id @GeneratedValue
	private int userId;
	private String userName;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> listOfString =  new ArrayList<String>();

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
		
		return usd;
	}
	
	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", userName=" + userName + ", listOfString=" + listOfString + "]";
	}
}
