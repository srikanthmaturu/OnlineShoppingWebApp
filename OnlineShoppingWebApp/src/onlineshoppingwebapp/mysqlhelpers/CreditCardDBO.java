package onlineshoppingwebapp.mysqlhelpers;

import onlineshoppingwebapp.banking.beans.CreditCardBean;
import onlineshoppingwebapp.customerreviews.beans.CustomerReviewBean;
import onlineshoppingwebapp.translators.DBOBeanConvertor;

public class CreditCardDBO extends DBO{

	public static String tableName = "CreditCards";
	
	public static DBOConfig setupDBOConfig(){
		if(DBOConfigManager.dboConfigs.containsKey(tableName)){
			return DBOConfigManager.dboConfigs.get(tableName);
		}
		
		String [] fnames =  {"Id", "CardHolderName", "CreditCardNumber", "Balance", "CardType", "UserId", "CVV",
		"ExpirationDate"};
		String [] ftypes = {"java.lang.Integer", "java.lang.String", "java.lang.String", "java.lang.Double", "java.lang.String",
				"java.lang.Integer", "java.lang.String", "java.util.Date"};
		
		DBOConfig dboConfig = new DBOConfig(tableName, fnames, ftypes);
		DBOConfigManager.dboConfigs.put(tableName, dboConfig);
		return dboConfig;
	}

	public CreditCardDBO(){
		initialize(setupDBOConfig());
	}
	
	public CreditCardBean getBean(){
		CreditCardBean creditCardBean = new CreditCardBean();
		DBOBeanConvertor.convert(this, CreditCardDBO.class, this.dboConfig, creditCardBean, CreditCardBean.class, CreditCardBean.creditCardToCreditCardDBOBiMap);
		return creditCardBean;
	}
}
