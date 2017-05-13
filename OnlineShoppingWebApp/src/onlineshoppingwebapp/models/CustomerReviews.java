package onlineshoppingwebapp.models;

import java.util.ArrayList;

import onlineshoppingwebapp.customerreviews.beans.CustomerReviewBean;
import onlineshoppingwebapp.mysqlhelpers.CustomerReviewDBO;
import onlineshoppingwebapp.mysqlhelpers.ProductDBO;
import onlineshoppingwebapp.mysqlhelpers.ProductQADBO;
import onlineshoppingwebapp.mysqlhelpers.Table;
import onlineshoppingwebapp.products.beans.ProductBean;

public class CustomerReviews {
	Table<CustomerReviewDBO> customerReviewsTable;
	String customerReviewsTableNameSQLValue = "`CustomerReviews`";
	
	public CustomerReviews(){
		customerReviewsTable = new Table<CustomerReviewDBO>(Database.defaultServerName, CustomerReviewDBO.class, 
				CustomerReviewDBO.setupDBOConfig(), Database.defaultDatabaseName + "." + customerReviewsTableNameSQLValue);
	}
	
	public Integer createNewCustomerReview(){
		Integer id = customerReviewsTable.createNewEntryReturnId();
		return id;
	}
	
	public void updateCustomerReview(CustomerReviewDBO customerReviewDBO){
		Integer id = Integer.parseInt(customerReviewDBO.getFieldSQLStringValue("Id"));
		customerReviewDBO.resetField("Id");
		customerReviewsTable.update(customerReviewDBO, customerReviewDBO.selected, " Id = " + id.toString());
	}
	
	public ArrayList<CustomerReviewBean> getCustomerReviews(Integer productId){
		ArrayList<CustomerReviewDBO> customerReviews;
		ArrayList<CustomerReviewBean> customerReviewBeans = new ArrayList<CustomerReviewBean>();
		
		customerReviews = customerReviewsTable.select(" ProductId = " + productId.toString(), -1, -1);
		
		for(CustomerReviewDBO customerReviewDBO: customerReviews){
			customerReviewBeans.add(customerReviewDBO.getBean());
		}
		return customerReviewBeans;
	}
	
	public void deleteProductReviews(Integer productId){
		CustomerReviewDBO customerReviewDBO = new CustomerReviewDBO();
		customerReviewDBO.setFieldValue("ProductId", productId.toString());
		customerReviewsTable.delete(customerReviewDBO, customerReviewDBO.selected);
	}
}
