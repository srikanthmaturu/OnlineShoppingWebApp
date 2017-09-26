package onlineshoppingwebapp.customerreviews.beans;

import java.io.Serializable;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;

import onlineshoppingwebapp.beans.Bean;

public class CustomerReviewBean extends Bean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String id, productId, customerId, reviewDate, rating, review;

	public static BidiMap<String, String>  customerReviewToCustomerReviewDBOBiMap= null;
	public static Boolean initialized = false;

	public static void initialize(){
		if(initialized)
			return;
		customerReviewToCustomerReviewDBOBiMap = new TreeBidiMap<String, String>();
		customerReviewToCustomerReviewDBOBiMap.put("id", "Id");
		customerReviewToCustomerReviewDBOBiMap.put("productId", "ProductId");
		customerReviewToCustomerReviewDBOBiMap.put("customerId", "CustomerId");
		customerReviewToCustomerReviewDBOBiMap.put("reviewDate", "ReviewDate");
		customerReviewToCustomerReviewDBOBiMap.put("rating", "Rating");
		customerReviewToCustomerReviewDBOBiMap.put("review", "Review");

		initialized = true;
	}

	public CustomerReviewBean(){
		intializeFieldsMap();
		initialize();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public static BidiMap<String, String> getCustomerReviewToCustomerReviewDBOBiMap() {
		return customerReviewToCustomerReviewDBOBiMap;
	}

	public static void setCustomerReviewToCustomerReviewDBOBiMap(
			BidiMap<String, String> customerReviewToCustomerReviewDBOBiMap) {
		CustomerReviewBean.customerReviewToCustomerReviewDBOBiMap = customerReviewToCustomerReviewDBOBiMap;
	}

	public static Boolean getInitialized() {
		return initialized;
	}

	public static void setInitialized(Boolean initialized) {
		CustomerReviewBean.initialized = initialized;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
