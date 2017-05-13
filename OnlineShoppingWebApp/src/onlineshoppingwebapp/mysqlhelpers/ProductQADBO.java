package onlineshoppingwebapp.mysqlhelpers;

import onlineshoppingwebapp.productqa.beans.ProductQABean;
import onlineshoppingwebapp.translators.DBOBeanConvertor;

public class ProductQADBO extends DBO{
	
	public static String tableName = "ProductQA";
	
	public static DBOConfig setupDBOConfig(){
		if(DBOConfigManager.dboConfigs.containsKey(tableName)){
			return DBOConfigManager.dboConfigs.get(tableName);
		}
			
		String [] fnames =  {"Id", "ProductId", "CustomerId", "Question", "Answer"};

		String [] ftypes = {"java.lang.Integer", "java.lang.Integer", "java.lang.Integer", "java.lang.String", "java.lang.String"};

		DBOConfig dboConfig = new DBOConfig(tableName, fnames, ftypes);
		DBOConfigManager.dboConfigs.put(tableName, dboConfig);
		return dboConfig;
	}

	public ProductQADBO(){
		initialize(setupDBOConfig());
	}
	
	public ProductQABean getBean(){
		ProductQABean productQABean = new ProductQABean();
		DBOBeanConvertor.convert(this, ProductQADBO.class, this.dboConfig, productQABean, ProductQABean.class, ProductQABean.productQAToProductQADBOBiMap);
		return productQABean;
	}

}
