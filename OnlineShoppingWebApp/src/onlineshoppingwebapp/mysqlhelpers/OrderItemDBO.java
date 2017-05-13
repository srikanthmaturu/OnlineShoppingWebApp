package onlineshoppingwebapp.mysqlhelpers;

import onlineshoppingwebapp.orders.beans.OrderBean;
import onlineshoppingwebapp.orders.beans.OrderItemBean;
import onlineshoppingwebapp.translators.DBOBeanConvertor;

public class OrderItemDBO extends DBO{
	public static String tableName = "OrderItems";
	
	public static DBOConfig setupDBOConfig(){
		if(DBOConfigManager.dboConfigs.containsKey(tableName)){
			return DBOConfigManager.dboConfigs.get(tableName);
		}
			
		String [] fnames =  {"Id", "OrderId", "SellerId", "ProductId", "ProductPrice", "Quantity", "ShippingStatus", "ShippingRefNo",
		"Status"};
		String [] ftypes = {"java.lang.Integer", "java.lang.Integer", "java.lang.Integer", "java.lang.Integer", "java.lang.Integer",
				"java.lang.Integer", "java.lang.Boolean", "java.lang.Integer",
		"java.lang.Boolean"};
		
		DBOConfig dboConfig = new DBOConfig(tableName, fnames, ftypes);
		DBOConfigManager.dboConfigs.put(tableName, dboConfig);
		return dboConfig;
	}

	public OrderItemDBO(){
		initialize(setupDBOConfig());
	}
	
	public OrderItemBean getBean(){
		OrderItemBean orderItemBean = new OrderItemBean();
		DBOBeanConvertor.convert(this, OrderItemDBO.class, this.dboConfig, orderItemBean, OrderItemBean.class, OrderItemBean.orderItemToOrderItemDBOBiMap);
		return orderItemBean;
	}
	
}
