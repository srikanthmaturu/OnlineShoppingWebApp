package onlineshoppingwebapp.shoppingcart.beans;

import java.io.Serializable;

import onlineshoppingwebapp.beans.Bean;

public class PurchaseBean extends Bean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String saAddressLine1, saAddressLine2, saCity, saZipcode, saState, saCountry, saPhoneNumber, 
					baAddressLine1, baAddressLine2,  baCity, baZipcode, baState, baCountry, baPhoneNumber, creditCardNumber,
					shippingAddress, billingAddress, customerId;
	
	public void computeAddresses(){
		shippingAddress = saAddressLine1 + "\n" + saAddressLine2 + "\n" + saCity + "\nZip:" + saZipcode + "\n" + saState + "\n" +
											saCountry + "\nPhone:" + saPhoneNumber;
		
		billingAddress = baAddressLine1 + "\n" + baAddressLine2 + "\n" + baCity + "\nZip:" + baZipcode + "\n" + baState + "\n" +
				baCountry + "\nPhone:" + baPhoneNumber;

	}

	public String getSaAddressLine1() {
		return saAddressLine1;
	}

	public void setSaAddressLine1(String saAddressLine1) {
		this.saAddressLine1 = saAddressLine1;
	}

	public String getSaAddressLine2() {
		return saAddressLine2;
	}

	public void setSaAddressLine2(String saAddressLine2) {
		this.saAddressLine2 = saAddressLine2;
	}

	public String getSaCity() {
		return saCity;
	}

	public void setSaCity(String saCity) {
		this.saCity = saCity;
	}

	public String getSaZipcode() {
		return saZipcode;
	}

	public void setSaZipcode(String saZipcode) {
		this.saZipcode = saZipcode;
	}

	public String getSaState() {
		return saState;
	}

	public void setSaState(String saState) {
		this.saState = saState;
	}

	public String getSaCountry() {
		return saCountry;
	}

	public void setSaCountry(String saCountry) {
		this.saCountry = saCountry;
	}

	public String getSaPhoneNumber() {
		return saPhoneNumber;
	}

	public void setSaPhoneNumber(String saPhoneNumber) {
		this.saPhoneNumber = saPhoneNumber;
	}

	public String getBaAddressLine1() {
		return baAddressLine1;
	}

	public void setBaAddressLine1(String baAddressLine1) {
		this.baAddressLine1 = baAddressLine1;
	}

	public String getBaAddressLine2() {
		return baAddressLine2;
	}

	public void setBaAddressLine2(String baAddressLine2) {
		this.baAddressLine2 = baAddressLine2;
	}

	public String getBaCity() {
		return baCity;
	}

	public void setBaCity(String baCity) {
		this.baCity = baCity;
	}

	public String getBaZipcode() {
		return baZipcode;
	}

	public void setBaZipcode(String baZipcode) {
		this.baZipcode = baZipcode;
	}

	public String getBaState() {
		return baState;
	}

	public void setBaState(String baState) {
		this.baState = baState;
	}

	public String getBaCountry() {
		return baCountry;
	}

	public void setBaCountry(String baCountry) {
		this.baCountry = baCountry;
	}

	public String getBaPhoneNumber() {
		return baPhoneNumber;
	}

	public void setBaPhoneNumber(String baPhoneNumber) {
		this.baPhoneNumber = baPhoneNumber;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	

}
