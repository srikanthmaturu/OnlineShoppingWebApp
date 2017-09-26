package onlineshoppingwebapp.mysqlhelpers;

import onlineshoppingwebapp.products.beans.ProductCategoryBean;
import onlineshoppingwebapp.translators.DBOBeanConvertor;

public class ProductCategoryDBO extends DBO{	
	public static String tableName = "ProductCategories";
	
	public static DBOConfig setupDBOConfig(){
		if(DBOConfigManager.dboConfigs.containsKey(tableName)){
			return DBOConfigManager.dboConfigs.get(tableName);
		}
			
		String [] fnames =  {"Id", "ProductCategory"};
		String [] ftypes = {"java.lang.Integer", "java.lang.String"};
		
		DBOConfig dboConfig = new DBOConfig(tableName, fnames, ftypes);
		DBOConfigManager.dboConfigs.put(tableName, dboConfig);
		return dboConfig;
	}

	public ProductCategoryDBO(){
		initialize(setupDBOConfig());
	}
	
	public ProductCategoryBean getBean(){
		ProductCategoryBean productCategoryBean = new ProductCategoryBean();
		DBOBeanConvertor.convert(this, ProductCategoryDBO.class, this.dboConfig, productCategoryBean, ProductCategoryBean.class, ProductCategoryBean.productCategoryToProductCategoryDBOBiMap);
		return productCategoryBean;
	}
}
