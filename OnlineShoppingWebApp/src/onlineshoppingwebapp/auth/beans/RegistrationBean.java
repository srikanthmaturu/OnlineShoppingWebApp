package onlineshoppingwebapp.auth.beans;

import java.io.Serializable;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;

import onlineshoppingwebapp.beans.Bean;
import onlineshoppingwebapp.mysqlhelpers.UserDBO;
import onlineshoppingwebapp.translators.BeanDBOConvertor;

public class RegistrationBean extends Bean  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String id, firstName, lastName, address, city, state, emailAddress, postalCode, phoneNumber, birthday, type, status, 
	numOfVisits, username, password;
	
	public static BidiMap<String, String> regToUserDBOBiMap = null;
	public static Boolean initialized = false;
	
	public static void initialize(){
		if(initialized)
			return;
		regToUserDBOBiMap = new TreeBidiMap<String, String>();
		regToUserDBOBiMap.put("id", "Id");
		regToUserDBOBiMap.put("firstName", "FirstName");
		regToUserDBOBiMap.put("lastName", "LastName");
		regToUserDBOBiMap.put("address", "Address");
		regToUserDBOBiMap.put("city", "City");
		regToUserDBOBiMap.put("state", "State");
		regToUserDBOBiMap.put("postalCode", "PostalCode");
		regToUserDBOBiMap.put("emailAddress", "EmailAddress");
		regToUserDBOBiMap.put("phoneNumber", "PhoneNumber");
		regToUserDBOBiMap.put("birthday", "Birthday");
		regToUserDBOBiMap.put("type", "Type");
		regToUserDBOBiMap.put("username", "Username");
		regToUserDBOBiMap.put("password", "Password");
		initialized = true;
	}
	
	public RegistrationBean(){
		intializeFieldsMap();
		initialize();
	}
	
	
	/**
	 * @return the UserDBO
	 */
	public UserDBO getUserDBO(){
		UserDBO user = new UserDBO();
		BeanDBOConvertor.convert(this, this.getClass(), user, user.getClass(), regToUserDBOBiMap);
		return user;
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
