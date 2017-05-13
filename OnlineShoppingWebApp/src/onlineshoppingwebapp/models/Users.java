package onlineshoppingwebapp.models;

import java.util.ArrayList;

import onlineshoppingwebapp.auth.beans.RegistrationBean;
import onlineshoppingwebapp.mysqlhelpers.*;

public class Users {
	String usersTableNameSQLValue = "`Users`";
	DBOConfig userDBOConfig;
	Table<UserDBO> userTable;
	
	public Users(){
		userDBOConfig = UserDBO.setupDBOConfig();
		userTable = new Table<UserDBO>(Database.defaultServerName, UserDBO.class, 
				userDBOConfig, Database.defaultDatabaseName + "." + usersTableNameSQLValue);
	}
	
	public Boolean registerUser(UserDBO user){
		String whereClause = " Id = " + user.getFieldSQLStringValue("Id");
		user.resetField("Id");
		userTable.update(user, user.selected, whereClause);
		return true;
	}
	
	public  Integer createNewUser(){
		return userTable.createNewEntryReturnId();
	}
	
	public boolean validateUser(UserDBO user){
		boolean valid = false;
		ArrayList<UserDBO> users = userTable.select(user.selected, user, user.selected, 1);
		if(users.size() > 0){
			return !valid;
		}
		return valid;
	}
	
	public UserDBO getUser(UserDBO user){
		ArrayList<UserDBO> users = userTable.select(null, user, user.selected, 1);
		return users.get(0);
	}
	
	public RegistrationBean getUser(Integer userId){
		ArrayList<UserDBO> users = userTable.select(" id = " +  userId.toString(), -1, -1);
		return users.get(0).getBean();
	}
	
	public String getUserName(Integer userId){
		ArrayList<UserDBO> users = userTable.select(" id = " +  userId.toString(), -1, -1);
		return users.get(0).getBean().firstName;
	}
	
}
