/**
 * 
 */
package onlineshoppingwebapp.auth.beans;

import java.io.Serializable;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;

import onlineshoppingwebapp.beans.Bean;

/**
 * @author Srikanth
 *
 */
public class LoginBean extends Bean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8982257538706826480L;
	public String username, password;
	
	public static BidiMap<String, String> loginToUserDBOBiMap = null;
	public static Boolean initialized = false;
	
	public static void initialize(){
		if(initialized)
			return;
		loginToUserDBOBiMap = new TreeBidiMap<String, String>();
		loginToUserDBOBiMap.put("username", "Username");
		loginToUserDBOBiMap.put("password", "Password");
		initialized = true;
	}
	
	public LoginBean(){
		intializeFieldsMap();
		initialize();
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
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
