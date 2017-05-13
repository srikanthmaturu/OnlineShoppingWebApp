package onlineshoppingwebapp.shoppingcart.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import onlineshoppingwebapp.models.Products;
import onlineshoppingwebapp.models.Users;
import onlineshoppingwebapp.products.beans.ProductBean;

public class ShoppingCartBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HashMap<Integer, Integer> shoppingCartItems;
	public HashMap<Integer, ProductBean> shoppingCartProducts;
	public HashMap<Integer, String> shoppingCartSellers;
	public Double totalCost = 0.0;
	public Products productsModel;
	public Users usersModel;
	
	public ShoppingCartBean(){
		shoppingCartItems = new HashMap<Integer, Integer>();
		shoppingCartProducts = new HashMap<Integer, ProductBean>();
		productsModel = new Products();
		shoppingCartSellers = new HashMap<Integer, String>();
		usersModel = new Users();
	}
	
	public void addItem(Integer productId, Integer quantity){
		if(!shoppingCartItems.containsKey(productId)){
			ProductBean product = productsModel.getProduct(productId);
			shoppingCartProducts.put(productId, product);
			System.out.println("Added product: " + productId + " to the shopping cart!" + " Quantity: " + quantity );
			String sellerName = usersModel.getUser(Integer.parseInt(shoppingCartProducts.get(productId).sellerId)).firstName;
			//System.out.println("Seller " + sellerName + " ProductId: " + productId);
			shoppingCartSellers.put(productId, sellerName);
			//System.out.println(" Searching: " + shoppingCartSellers.get(productId));
		}else {
			quantity = quantity + shoppingCartItems.get(productId);
		}
		shoppingCartItems.put(productId, quantity);
		computeTotalCost();
	}
	
	public void removeItem(Integer productId){
		shoppingCartItems.remove(productId);
		shoppingCartProducts.remove(productId);
		shoppingCartSellers.remove(productId);
		computeTotalCost();
	}
	
	public void updateQuantity(Integer productId, Integer newQuantity){
		if(newQuantity == 0){
			removeItem(productId);
			return;
		}
		shoppingCartItems.put(productId, newQuantity);
		computeTotalCost();
	}
	
	public Integer getQuantity(Integer productId){
		return shoppingCartItems.get(productId);
	}
	
	public Double getItemPrice(Integer productId){
		return ((double)shoppingCartItems.get(productId))*((int)Integer.parseInt(shoppingCartProducts.get(productId).getPrice()));
	}
	
	public void computeTotalCost(){
		Integer quantity;
		ProductBean product;
		totalCost = 0.0;
		for(Integer itemId: shoppingCartItems.keySet()){
			quantity = shoppingCartItems.get(itemId);
			product = shoppingCartProducts.get(itemId);
			//System.out.println(product + " " + quantity);
			totalCost += quantity*Integer.parseInt(product.getPrice());
		}
	}
	
	public Double getTotalCost(){
		return totalCost;
	}
	
	public Integer getSize(){
		return shoppingCartItems.size();
	}
	
	public List<Integer> getProductsList(){
		Integer[] indexList = new Integer[shoppingCartProducts.keySet().size()];
		return Arrays.asList(shoppingCartProducts.keySet().toArray(indexList));
	}
	
	public ProductBean getProductBean(Integer productId){
		return shoppingCartProducts.get(productId);
	}
	
	public String getSellerName(Integer productId){
		//System.out.println(shoppingCartSellers.get(productId) + " " + productId);
		return shoppingCartSellers.get(productId);
	}
}
