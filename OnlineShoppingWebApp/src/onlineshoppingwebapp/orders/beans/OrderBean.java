package onlineshoppingwebapp.orders.beans;

import java.io.Serializable;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;

import onlineshoppingwebapp.beans.Bean;

public class OrderBean extends Bean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String id, customerId, totalCost, orderDate, shippingAddress, billingAddress, creditCardNumber;
	
	public static BidiMap<String, String> orderToOrderDBOBiMap = null;
	public static Boolean initialized = false;

	public static void initialize(){
		if(initialized)
			return;
		orderToOrderDBOBiMap = new TreeBidiMap<String, String>();
		orderToOrderDBOBiMap.put("id", "Id");
		orderToOrderDBOBiMap.put("customerId", "CustomerId");
		orderToOrderDBOBiMap.put("totalCost", "TotalCost");
		orderToOrderDBOBiMap.put("orderDate", "OrderDate");
		orderToOrderDBOBiMap.put("shippingAddress", "ShippingAddress");
		orderToOrderDBOBiMap.put("billingAddress", "BillingAddress");
		orderToOrderDBOBiMap.put("creditCardNumber", "CreditCardNumber");
		initialized = true;
	}
	
	public OrderBean(){
		intializeFieldsMap();
		initialize();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

