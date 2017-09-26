package onlineshoppingwebapp.mysqlhelpers;

import onlineshoppingwebapp.auth.beans.RegistrationBean;
import onlineshoppingwebapp.translators.DBOBeanConvertor;

public class UserDBO extends DBO{

	public static String tableName = "Users";
	
	public static DBOConfig setupDBOConfig(){
		if(DBOConfigManager.dboConfigs.containsKey(tableName)){
			return DBOConfigManager.dboConfigs.get(tableName);
		}
		
		String [] fnames =  {"Id", "FirstName", "LastName", "Address", "City", "State", "PostalCode", "EmailAddress",
				"PhoneNumber", "Birthday", "Type", "Status", "NumOfVisits", "Username", "Password"};
		String [] ftypes = {"java.lang.Integer", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.Boolean",
				"java.lang.Boolean", "java.lang.Integer", "java.lang.String", "java.lang.String"};

		
		DBOConfig dboConfig = new DBOConfig(tableName, fnames, ftypes);
		DBOConfigManager.dboConfigs.put(tableName, dboConfig);
		return dboConfig;
	}

	public UserDBO(){
		initialize(setupDBOConfig());
	}
	
	public RegistrationBean getBean(){
		RegistrationBean registrationBean = new RegistrationBean();
		DBOBeanConvertor.convert(this, UserDBO.class, this.dboConfig, registrationBean, RegistrationBean.class, RegistrationBean.regToUserDBOBiMap);
		return registrationBean;
	}
	
}
