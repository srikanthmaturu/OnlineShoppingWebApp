/**
 * 
 */
package onlineshoppingwebapp.models;

import java.util.ArrayList;

import onlineshoppingwebapp.logger.LogPrinter;
import onlineshoppingwebapp.mysqlhelpers.DBOConfig;
import onlineshoppingwebapp.mysqlhelpers.DBOConfigManager;
import onlineshoppingwebapp.mysqlhelpers.ProductCategoryDBO;
import onlineshoppingwebapp.mysqlhelpers.ProductDBO;
import onlineshoppingwebapp.mysqlhelpers.Table;
import onlineshoppingwebapp.products.beans.ProductBean;
import onlineshoppingwebapp.products.beans.ProductCategoryBean;

/**
 * @author Srikanth
 *
 */
public class Products {
	Table<ProductDBO> productsTable;
	String productsTableNameSQLValue = "`Products`", productsCategoriesTableNameSQLValue =  "`ProductCategories`";
	DBOConfig productsDBOConfig;
	DBOConfig productCategoriesDBOConfig;
	public Products(){
		productsDBOConfig = ProductDBO.setupDBOConfig();
		productCategoriesDBOConfig = ProductCategoryDBO.setupDBOConfig();
		productsTable = new Table<ProductDBO>(Database.defaultServerName, ProductDBO.class, 
				productsDBOConfig, Database.defaultDatabaseName + "." + productsTableNameSQLValue);
	}
	
	public ProductBean getNewProduct(Integer productId){
		ArrayList<ProductDBO> productDBOS = productsTable.select(" Id = " + productId.toString(), -1, -1);
		
		if(productDBOS.size() > 0){
			return productDBOS.get(0).getBean();
		}
		else{
			return null;
		}
	}
	
	public ProductDBO createNewProduct(){
		ProductDBO product = new ProductDBO();
		Integer id = productsTable.createNewEntryReturnId();
		product.setFieldValue("Id", id.toString());
		return product;
	}
	
	public void updateProduct(ProductDBO product){
		productsTable.update(product, product.selected, " Id = " + product.getFieldSQLStringValue("Id"));
	}
	
	public void decrementProductQuantity(Integer productId, Integer quantity){
		productsTable.modifyFieldValueByOperator("Quantity", (double)quantity, "-", " Id = " + productId.toString());
	}
	
	public Integer getProductQuantity(Integer productId){
		ProductBean product = null;
		ArrayList<ProductDBO> productDBOS = productsTable.select(" Id = "+productId.toString(), -1, 1);
		if(!productDBOS.isEmpty()){
			product = productDBOS.get(0).getBean();
		}
		return Integer.parseInt(product.getAvailableQuantity());
	}
	
	public ArrayList<ProductBean> getProductsLists(int sellerId){
		ArrayList<ProductDBO> productDBOS = productsTable.select(" SellerId = " + sellerId, -1 , -1);
		ArrayList<ProductBean> productBeans = new ArrayList<ProductBean>();
		for(ProductDBO productDBO : productDBOS){
			productBeans.add(productDBO.getBean());
		}
		return productBeans;
	}
	
	public ArrayList<ProductCategoryBean> getProductCategories(){
		Table<ProductCategoryDBO> productCategoriesTable = new Table<ProductCategoryDBO> (Database.defaultServerName, ProductCategoryDBO.class, 
				productCategoriesDBOConfig, Database.defaultDatabaseName + "." + productsCategoriesTableNameSQLValue );
		ArrayList<ProductCategoryDBO> productCategoryDBOS = productCategoriesTable.select(null, -1, -1);
		ArrayList<ProductCategoryBean> productCategoryBeans = new ArrayList<ProductCategoryBean>();
		for (ProductCategoryDBO productCategoryDBO : productCategoryDBOS){
			productCategoryBeans.add(productCategoryDBO.getBean());
		}
		return productCategoryBeans;
	}
	
	public boolean deleteProduct(Integer productId){
		boolean result = false;
		ProductDBO product = new ProductDBO();
		product.setFieldValue("Id", productId.toString());
		productsTable.delete(product, product.selected);
		return result;
	}
	
	public ArrayList<ProductBean> getProductsBySearchQuery(String[] searchQueryWords){
		boolean selection[] = new boolean[productsDBOConfig.nooffields];
		selection[productsDBOConfig.map.get("ProductName")] = true;
		selection[productsDBOConfig.map.get("ProductCategoryIndex")] = true;
		selection[productsDBOConfig.map.get("ProductDescription")] = true;
		ArrayList<ProductDBO> productDBOS = productsTable.search(selection, searchQueryWords);
		ArrayList<ProductBean> productBeans = new ArrayList<ProductBean>();
		for(ProductDBO productDBO : productDBOS){
			productBeans.add(productDBO.getBean());
		}
		return productBeans;
	}
	
	public ProductBean getProduct(Integer productId){
		ProductBean product = null;
		ArrayList<ProductDBO> productDBOS = productsTable.select(" Id = "+productId.toString(), -1, 1);
		if(!productDBOS.isEmpty()){
			product = productDBOS.get(0).getBean();
		}
		return product;
	}
	
}

