package onlineshoppingwebapp.orders.beans;

import java.io.Serializable;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;

import onlineshoppingwebapp.beans.Bean;

public class OrderItemBean extends Bean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String id, orderId, sellerId, productId, productPrice, quantity, shippingStatus, shippingRefNo, status;
	
	
	public static BidiMap<String, String> orderItemToOrderItemDBOBiMap = null;
	public static Boolean initialized = false;

	public static void initialize(){
		if(initialized)
			return;
		orderItemToOrderItemDBOBiMap = new TreeBidiMap<String, String>();
		orderItemToOrderItemDBOBiMap.put("id", "Id");
		orderItemToOrderItemDBOBiMap.put("orderId", "OrderId");
		orderItemToOrderItemDBOBiMap.put("sellerId", "SellerId");
		orderItemToOrderItemDBOBiMap.put("productId", "ProductId");
		orderItemToOrderItemDBOBiMap.put("productPrice", "ProductPrice");
		orderItemToOrderItemDBOBiMap.put("quantity", "Quantity");
		orderItemToOrderItemDBOBiMap.put("shippingStatus", "ShippingStatus");
		orderItemToOrderItemDBOBiMap.put("shippingRefNo", "ShippingRefNo");
		orderItemToOrderItemDBOBiMap.put("status", "Status");
		initialized = true;
	}

	public OrderItemBean(){
		intializeFieldsMap();
		initialize();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getShippingStatus() {
		return shippingStatus;
	}
	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}
	public String getShippingRefNo() {
		return shippingRefNo;
	}
	public void setShippingRefNo(String shippingRefNo) {
		this.shippingRefNo = shippingRefNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
