package onlineshoppingwebapp.productqa.beans;

import java.io.Serializable;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;

import onlineshoppingwebapp.beans.Bean;

public class ProductQABean extends Bean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String id, productId, customerId, question, answer;
	
	public static BidiMap<String, String> productQAToProductQADBOBiMap = null;
	public static Boolean initialized = false;

	public static void initialize(){
		if(initialized)
			return;
		productQAToProductQADBOBiMap = new TreeBidiMap<String, String>();
		productQAToProductQADBOBiMap.put("id", "Id");
		productQAToProductQADBOBiMap.put("productId", "ProductId");
		productQAToProductQADBOBiMap.put("customerId", "CustomerId");
		productQAToProductQADBOBiMap.put("question", "Question");
		productQAToProductQADBOBiMap.put("answer", "Answer");

		initialized = true;
	}

	public ProductQABean(){
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

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
