/**
 * 
 */
package onlineshoppingwebapp.models;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.commons.lang3.time.DateUtils;

import onlineshoppingwebapp.mysqlhelpers.DBOConfig;
import onlineshoppingwebapp.mysqlhelpers.OrderDBO;
import onlineshoppingwebapp.mysqlhelpers.OrderItemDBO;
import onlineshoppingwebapp.mysqlhelpers.ProductDBO;
import onlineshoppingwebapp.mysqlhelpers.Table;
import onlineshoppingwebapp.orders.beans.OrderBean;
import onlineshoppingwebapp.orders.beans.OrderItemBean;
import onlineshoppingwebapp.products.beans.ProductBean;
import onlineshoppingwebapp.shoppingcart.beans.PurchaseBean;
import onlineshoppingwebapp.shoppingcart.beans.ShoppingCartBean;

/**
 * @author Srikanth
 *
 */
public class Orders {
	
	Table<OrderDBO> ordersTable;
	Table<OrderItemDBO> orderItemsTable;
	String ordersTableNameSQLValue = "`Orders`", orderItemsTableNameSQLValue = "`OrderItems`";
	DBOConfig ordersDBOConfig, orderItemsDBOConfig;
	
	public Orders(){
		ordersDBOConfig = OrderDBO.setupDBOConfig();
		orderItemsDBOConfig = OrderItemDBO.setupDBOConfig();
		ordersTable = new Table<OrderDBO>(Database.defaultServerName, OrderDBO.class, 
				ordersDBOConfig, Database.defaultDatabaseName + "." + ordersTableNameSQLValue);
		orderItemsTable = new Table<OrderItemDBO>(Database.defaultServerName, OrderItemDBO.class, 
				orderItemsDBOConfig, Database.defaultDatabaseName + "." + orderItemsTableNameSQLValue);
	}
	
	public  Integer createNewOrder(){
		return ordersTable.createNewEntryReturnId();
	}
	
	public ArrayList<OrderItemBean> getSellerOrderItems(Integer sellerId){
		ArrayList<OrderItemBean> sellerOrderItems = new ArrayList<OrderItemBean>();
		ArrayList<OrderItemDBO> sellerOrderItemsDBOS = orderItemsTable.select(" SellerId = " + sellerId.toString(), -1, -1);
		for(OrderItemDBO sellerOrderItemDBO: sellerOrderItemsDBOS){
			sellerOrderItems.add(sellerOrderItemDBO.getBean());
		}
		return sellerOrderItems;
	}
	
	public ArrayList<OrderItemBean> getOrderItemsByOrderId(Integer orderId){
		ArrayList<OrderItemBean> orderItems = new ArrayList<OrderItemBean>();
		ArrayList<OrderItemDBO> orderItemsDBOS = orderItemsTable.select(" OrderId = " + orderId.toString(), -1, -1);
		for(OrderItemDBO orderItemDBO: orderItemsDBOS){
			orderItems.add(orderItemDBO.getBean());
		}
		return orderItems;
	}
	
	public OrderBean getOrderById(Integer orderId){
		ArrayList<OrderDBO> orderDBOS = ordersTable.select(" Id = " + orderId.toString(), -1, -1);
		return orderDBOS.get(0).getBean();
	}
	
	public ArrayList<OrderBean> getCustomerOrders(Integer customerId){
		ArrayList<OrderBean> customerOrders = new ArrayList<OrderBean>();
		ArrayList<OrderDBO> customerOrderDBOS = ordersTable.select(" CustomerId =  " + customerId.toString(), -1, -1);
		for(OrderDBO customerOrderDBO: customerOrderDBOS){
			customerOrders.add(customerOrderDBO.getBean());
		}
		return customerOrders;
	}
	
	
	public Integer createNewOrderItem(){
		return orderItemsTable.createNewEntryReturnId();
	}
	
	public void updateOrderItemStatus(Integer orderItemId, String status){
		OrderItemDBO orderItemDBO = new OrderItemDBO();
		orderItemDBO.setFieldValue("Status", status);
		orderItemsTable.update(orderItemDBO, orderItemDBO.selected, " Id = " + orderItemId.toString());
	}
	
	public void updateShippingStatus(Integer orderItemId, String shippingRefNo){
		OrderItemDBO orderItemDBO = new OrderItemDBO();
		orderItemDBO.setFieldValue("ShippingRefNo", shippingRefNo);
		orderItemDBO.setFieldValue("ShippingStatus", "1");
		orderItemsTable.update(orderItemDBO, orderItemDBO.selected, " Id = " + orderItemId.toString());
	}
	
	public void udpateOrder(OrderDBO order){
		ordersTable.update(order, order.selected, " Id = " + order.getFieldSQLStringValue("Id"));
	}
	
	public void updateOrderItem(OrderItemDBO orderItem){
		orderItemsTable.update(orderItem, orderItem.selected, " Id = " + orderItem.getFieldSQLStringValue("Id"));
	}
	
	public Integer placeOrder(ShoppingCartBean shoppingCart, PurchaseBean purchaseBean){
		Integer quantity, orderItemId;
		ProductBean productBean;
		Integer orderId = this.createNewOrder();
		OrderBean orderBean = new OrderBean();
		OrderItemBean orderItemBean;
		orderBean.setId(orderId.toString());
		
		DateFormat dateFormat = new SimpleDateFormat("dd/mm/yy");
		orderBean.setOrderDate(dateFormat.format(new Date()).toString());
		
		orderBean.setBillingAddress(purchaseBean.getBillingAddress());
		orderBean.setShippingAddress(purchaseBean.getShippingAddress());
		orderBean.setCreditCardNumber(purchaseBean.getCreditCardNumber());
		orderBean.setCustomerId(purchaseBean.getCustomerId());
		
		orderBean.setTotalCost(shoppingCart.totalCost.toString());
		
		OrderItemDBO orderItemDBO;
		for(Integer productId: shoppingCart.shoppingCartItems.keySet()){
			quantity = shoppingCart.shoppingCartItems.get(productId);
			productBean = shoppingCart.shoppingCartProducts.get(productId);
			
			orderItemBean = new OrderItemBean();
			orderItemId = orderItemsTable.createNewEntryReturnId();
			orderItemBean.setId(orderItemId.toString());
			orderItemBean.setOrderId(orderId.toString());
			orderItemBean.setProductId(productBean.getId());
			orderItemBean.setProductPrice(productBean.getPrice());
			orderItemBean.setQuantity(quantity.toString());
			orderItemBean.setSellerId(productBean.getSellerId());
			orderItemBean.setShippingRefNo("-1");
			orderItemBean.setShippingStatus("0");
			orderItemBean.setStatus("1");
			orderItemDBO = new OrderItemDBO();
			orderItemBean.convertToDBO(orderItemBean, OrderItemBean.orderItemToOrderItemDBOBiMap, orderItemDBO);
			
			String whereClause = " Id = " + orderItemDBO.getFieldSQLStringValue("Id");
			orderItemDBO.resetField("Id");
			orderItemsTable.update(orderItemDBO, orderItemDBO.selected, whereClause);
		}	
		OrderDBO orderDBO = new OrderDBO();
		orderBean.convertToDBO(orderBean, OrderBean.orderToOrderDBOBiMap, orderDBO);
		
		String whereClause = " Id = " + orderDBO.getFieldSQLStringValue("Id");
		orderDBO.resetField("Id");
		ordersTable.update(orderDBO, orderDBO.selected, whereClause);
		
		return orderId;
	}
	
}
