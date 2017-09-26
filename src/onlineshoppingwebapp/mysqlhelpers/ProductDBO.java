package onlineshoppingwebapp.mysqlhelpers;

import onlineshoppingwebapp.products.beans.ProductBean;
import onlineshoppingwebapp.translators.DBOBeanConvertor;

public class ProductDBO extends DBO {
	
	public static String tableName = "Products";
	
	public static DBOConfig setupDBOConfig(){
		if(DBOConfigManager.dboConfigs.containsKey(tableName)){
			return DBOConfigManager.dboConfigs.get(tableName);
		}
			
		String [] fnames =  {"Id", "ProductName", "ProductCategoryIndex", "ProductDescription", "Price", 
				"AvailableQuantity", "EstimatedDeliveryDays", "SellerId", "ProductPhotosLinks", "ProductVideosLinks",
		"ProductThumbnail"};
		String [] ftypes = {"java.lang.Integer", "java.lang.String", "java.lang.Integer", "java.lang.String", "java.lang.Integer",
				"java.lang.Integer", "java.lang.Integer", "java.lang.Integer", "java.lang.String", "java.lang.String", "java.lang.String"};
		
		DBOConfig dboConfig = new DBOConfig(tableName, fnames, ftypes);
		DBOConfigManager.dboConfigs.put(tableName, dboConfig);
		return dboConfig;
	}

	public ProductDBO(){
		initialize(setupDBOConfig());
	}
	
	public ProductBean getBean(){
		ProductBean productBean = new ProductBean();
		DBOBeanConvertor.convert(this, ProductDBO.class, this.dboConfig, productBean, ProductBean.class, ProductBean.productToProductDBOBiMap);
		return productBean;
	}

}


