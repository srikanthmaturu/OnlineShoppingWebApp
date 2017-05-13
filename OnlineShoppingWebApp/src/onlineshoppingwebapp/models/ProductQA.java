package onlineshoppingwebapp.models;

import java.util.ArrayList;

import onlineshoppingwebapp.mysqlhelpers.ProductQADBO;
import onlineshoppingwebapp.mysqlhelpers.Table;
import onlineshoppingwebapp.productqa.beans.ProductQABean;

public class ProductQA {
	Table<ProductQADBO> productQATable;
	String productQATableNameSQLValue = "`ProductQA`";
	
	public ProductQA(){
		productQATable = new Table<ProductQADBO>(Database.defaultServerName, ProductQADBO.class, 
				ProductQADBO.setupDBOConfig(), Database.defaultDatabaseName + "." + productQATableNameSQLValue);
	}
	
	
	public ArrayList<ProductQABean> getProductQA(Integer productId){
		ArrayList<ProductQABean> productQABeans = new ArrayList<ProductQABean>();
		ArrayList<ProductQADBO> productQA;
		
		productQA = productQATable.select(" ProductId = " + productId.toString(), -1, -1);
		for(ProductQADBO productQADBO: productQA){
			productQABeans.add(productQADBO.getBean());
		}
		return productQABeans;
	}
	
	public void insertProductQA(ProductQADBO productQADBO){
		productQATable.insert(productQADBO);
	}
	
	public void deleteProductQA(Integer productId){
		ProductQADBO productQA = new ProductQADBO();
		productQA.setFieldValue("ProductId", productId.toString());
		productQATable.delete(productQA, productQA.selected);
	}
	
	public void updateAnswer(Integer questionId, String answer){
		ProductQADBO productQA = new ProductQADBO();
		productQA.setFieldValue("Answer", answer);
		productQATable.update(productQA, productQA.selected, " Id = "+questionId.toString());
	}
}
