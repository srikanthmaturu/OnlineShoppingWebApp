package onlineshoppingwebapp.products.beans;

import java.io.Serializable;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;

import onlineshoppingwebapp.beans.Bean;

public class ProductBean extends Bean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String id, productName, productCategoryIndex, productDescription, price, availableQuantity, 
	estimatedDeliveryDays, sellerId, productPhotosLinks, productVideosLinks, productThumbNailLink;

	public static BidiMap<String, String> productToProductDBOBiMap = null;
	public static Boolean initialized = false;

	public static void initialize(){
		if(initialized)
			return;
		productToProductDBOBiMap = new TreeBidiMap<String, String>();
		productToProductDBOBiMap.put("id", "Id");
		productToProductDBOBiMap.put("productName", "ProductName");
		productToProductDBOBiMap.put("productCategoryIndex", "ProductCategoryIndex");
		productToProductDBOBiMap.put("productDescription", "ProductDescription");
		productToProductDBOBiMap.put("price", "Price");
		productToProductDBOBiMap.put("availableQuantity", "AvailableQuantity");
		productToProductDBOBiMap.put("estimatedDeliveryDays", "EstimatedDeliveryDays");
		productToProductDBOBiMap.put("sellerId", "SellerId");
		productToProductDBOBiMap.put("productPhotosLinks", "ProductPhotosLinks");
		productToProductDBOBiMap.put("productVideosLinks", "ProductVideosLinks");
		productToProductDBOBiMap.put("productThumbNailLink", "ProductThumbnail");

		initialized = true;
	}

	public ProductBean(){
		intializeFieldsMap();
		initialize();
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategoryIndex() {
		return productCategoryIndex;
	}

	public void setProductCategoryIndex(String productCategoryIndex) {
		this.productCategoryIndex = productCategoryIndex;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(String availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public String getEstimatedDeliveryDays() {
		return estimatedDeliveryDays;
	}

	public void setEstimatedDeliveryDays(String estimatedDeliveryDays) {
		this.estimatedDeliveryDays = estimatedDeliveryDays;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getProductPhotosLinks() {
		return productPhotosLinks;
	}

	public void setProductPhotosLinks(String productPhotosLinks) {
		this.productPhotosLinks = productPhotosLinks;
	}

	public String getProductVideosLinks() {
		return productVideosLinks;
	}

	public void setProductVideosLinks(String productVideosLinks) {
		this.productVideosLinks = productVideosLinks;
	}

	public String getProductThumbNailLink() {
		return productThumbNailLink;
	}

	public void setProductThumbNailLink(String productThumbNailLink) {
		this.productThumbNailLink = productThumbNailLink;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}