package onlineshoppingwebapp.banking.beans;

import java.io.Serializable;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;

import onlineshoppingwebapp.beans.Bean;

public class CreditCardBean extends Bean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String id, cardHolderName, creditCardNumber, balance, cardType, userId, cvv, expirationDate;
	
	public static BidiMap<String, String> creditCardToCreditCardDBOBiMap = null;
	public static Boolean initialized = false;

	public static void initialize(){
		if(initialized)
			return;
		creditCardToCreditCardDBOBiMap = new TreeBidiMap<String, String>();
		creditCardToCreditCardDBOBiMap.put("id", "Id");
		creditCardToCreditCardDBOBiMap.put("cardHolderName", "CardHolderName");
		creditCardToCreditCardDBOBiMap.put("creditCardNumber", "CreditCardNumber");
		creditCardToCreditCardDBOBiMap.put("balance", "Balance");
		creditCardToCreditCardDBOBiMap.put("cardType", "CardType");
		creditCardToCreditCardDBOBiMap.put("userId", "UserId");
		creditCardToCreditCardDBOBiMap.put("cvv", "CVV");
		creditCardToCreditCardDBOBiMap.put("expirationDate", "ExpirationDate");
		
		initialized = true;
	}

	public CreditCardBean(){
		intializeFieldsMap();
		initialize();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public static BidiMap<String, String> getCreditCardToCreditCardDBOBiMap() {
		return creditCardToCreditCardDBOBiMap;
	}

	public static void setCreditCardToCreditCardDBOBiMap(BidiMap<String, String> creditCardToCreditCardDBOBiMap) {
		CreditCardBean.creditCardToCreditCardDBOBiMap = creditCardToCreditCardDBOBiMap;
	}

	public static Boolean getInitialized() {
		return initialized;
	}

	public static void setInitialized(Boolean initialized) {
		CreditCardBean.initialized = initialized;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
