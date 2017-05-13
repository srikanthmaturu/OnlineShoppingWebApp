package onlineshoppingwebapp.mysqlhelpers;


import onlineshoppingwebapp.customerreviews.beans.CustomerReviewBean;
import onlineshoppingwebapp.translators.DBOBeanConvertor;

public class CustomerReviewDBO extends DBO{
	
	public static String tableName = "CustomerReviews";
	
	public static DBOConfig setupDBOConfig(){
		if(DBOConfigManager.dboConfigs.containsKey(tableName)){
			return DBOConfigManager.dboConfigs.get(tableName);
		}
		
		String [] fnames =  {"Id", "ProductId", "CustomerId", "ReviewDate", "Rating", "Review"};
		String [] ftypes = {"java.lang.Integer", "java.lang.Integer", "java.lang.Integer", "java.lang.String", "java.lang.String",
		"java.lang.String"};
		
		DBOConfig dboConfig = new DBOConfig(tableName, fnames, ftypes);
		DBOConfigManager.dboConfigs.put(tableName, dboConfig);
		return dboConfig;
	}

	public CustomerReviewDBO(){
		initialize(setupDBOConfig());
	}
	
	public CustomerReviewBean getBean(){
		CustomerReviewBean customerReviewBean = new CustomerReviewBean();
		DBOBeanConvertor.convert(this, CustomerReviewDBO.class, this.dboConfig, customerReviewBean, CustomerReviewBean.class, CustomerReviewBean.customerReviewToCustomerReviewDBOBiMap);
		return customerReviewBean;
	}
}
