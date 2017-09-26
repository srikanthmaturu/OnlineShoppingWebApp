package onlineshoppingwebapp.products.beans;

import java.io.Serializable;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;

import onlineshoppingwebapp.beans.Bean;

public class ProductCategoryBean extends Bean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String id, productCategory;
	public static BidiMap<String, String> productCategoryToProductCategoryDBOBiMap = null;
	public static Boolean initialized = false;

	public static void initialize(){
		if(initialized)
			return;
		productCategoryToProductCategoryDBOBiMap = new TreeBidiMap<String, String>();
		productCategoryToProductCategoryDBOBiMap.put("id", "Id");
		productCategoryToProductCategoryDBOBiMap.put("productCategory", "ProductCategory");

		initialized = true;
	}

	public ProductCategoryBean(){
		intializeFieldsMap();
		initialize();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	
}
