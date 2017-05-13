package onlineshoppingwebapp.mysqlhelpers;

import onlineshoppingwebapp.orders.beans.OrderBean;
import onlineshoppingwebapp.productqa.beans.ProductQABean;
import onlineshoppingwebapp.translators.DBOBeanConvertor;

public class OrderDBO extends DBO{
	
	public static String tableName =  "Orders";
	
	public static DBOConfig setupDBOConfig(){
		if(DBOConfigManager.dboConfigs.containsKey(tableName)){
			return DBOConfigManager.dboConfigs.get(tableName);
		}
			
		String [] fnames =  {"Id", "CustomerId", "TotalCost", "OrderDate", "ShippingAddress", "BillingAddress",
		"CreditCardNumber"};
		String [] ftypes = {"java.lang.Integer", "java.lang.Integer", "java.lang.Double", "java.lang.String", 
				"java.lang.String", "java.lang.String", "java.lang.String"};
		
		DBOConfig dboConfig = new DBOConfig(tableName, fnames, ftypes);
		DBOConfigManager.dboConfigs.put(tableName, dboConfig);
		return dboConfig;
	}

	public OrderDBO(){
		initialize(setupDBOConfig());
	}
	
	public OrderBean getBean(){
		OrderBean orderBean = new OrderBean();
		DBOBeanConvertor.convert(this, OrderDBO.class, this.dboConfig, orderBean, OrderBean.class, OrderBean.orderToOrderDBOBiMap);
		return orderBean;
	}
}
