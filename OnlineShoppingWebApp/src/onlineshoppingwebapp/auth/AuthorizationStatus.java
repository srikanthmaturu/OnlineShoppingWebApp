package onlineshoppingwebapp.auth;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationStatus {
	
	public static boolean isLoggedIn(HttpServletRequest request){
		return (boolean)request.getSession().getAttribute("loggedin");
	}
	
	public static String getTypeOfUser(HttpServletRequest request){
		return (String)request.getSession().getAttribute("user_type");
	}	
}
