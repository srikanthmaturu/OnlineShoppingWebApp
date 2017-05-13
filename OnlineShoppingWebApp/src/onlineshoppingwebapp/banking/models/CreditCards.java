package onlineshoppingwebapp.banking.models;

import java.util.ArrayList;

import onlineshoppingwebapp.auth.beans.RegistrationBean;
import onlineshoppingwebapp.models.Database;
import onlineshoppingwebapp.mysqlhelpers.CreditCardDBO;
import onlineshoppingwebapp.mysqlhelpers.DBOConfig;
import onlineshoppingwebapp.mysqlhelpers.Table;
import onlineshoppingwebapp.mysqlhelpers.UserDBO;

public class CreditCards {
	Table<CreditCardDBO> creditCardsTable;
	String creditCardsTableNameSQLValue = "`CreditCards`";
	DBOConfig creditCardDBOConfig;
	
	public CreditCards(){
		creditCardDBOConfig = CreditCardDBO.setupDBOConfig();
		creditCardsTable = new Table<CreditCardDBO>(Database.defaultServerName, CreditCardDBO.class, 
				creditCardDBOConfig, Database.defaultDatabaseName + "." + creditCardsTableNameSQLValue);
	}
	
	public  Integer createCreditCard(){
		return creditCardsTable.createNewEntryReturnId();
	}
	
	public void updateCreditCard(CreditCardDBO creditCardDBO){
		String creditCardId = creditCardDBO.getFieldSQLStringValue("Id");
		creditCardDBO.resetField("Id");
		creditCardsTable.update(creditCardDBO, creditCardDBO.selected, " Id = " + creditCardId);
	}
	
	public void updateBalance(CreditCardDBO creditCardDBO, Double creditCardBalance){
		if(!verifyCreditCard(creditCardDBO)){
			return;
		}
		Integer creditCardId = getCreditCardId(creditCardDBO);
		creditCardDBO = new CreditCardDBO();
		creditCardDBO.setFieldValue("Balance", creditCardBalance.toString());
		creditCardsTable.update(creditCardDBO, creditCardDBO.selected, " Id = " + creditCardId.toString());
	}
	
	public boolean verifyCreditCard(CreditCardDBO creditCardDBO){
		boolean valid = false;
		ArrayList<CreditCardDBO> users = creditCardsTable.select(creditCardDBO.selected, creditCardDBO, creditCardDBO.selected, 1);
		if(users.size() > 0){
			return !valid;
		}
		return valid;
	}
	
	public Integer getCreditCardId(CreditCardDBO creditCardDBO){
		boolean selection[] = new boolean [creditCardDBO.selected.length];
		selection[0] = true;
		ArrayList<CreditCardDBO> creditCards = creditCardsTable.select(selection, creditCardDBO, creditCardDBO.selected, 1);
		
		if(creditCards.size() == 0){
			return -1;
		}
		
		return Integer.parseInt(creditCards.get(0).getBean().getId());
	}
	
	public Double getBalance(Integer creditCardId){
		CreditCardDBO creditCardDBO = new CreditCardDBO();
		creditCardDBO.setFieldValue("Balance", "-1");
		ArrayList<CreditCardDBO> creditCards = creditCardsTable.select(creditCardDBO.selected, " Id = " + creditCardId.toString(), -1, 1);
		return Double.parseDouble(creditCards.get(0).getBean().getBalance());
	}	
	
	public void modifyBalance(String creditCardId, Double currency, String modificationOperator){
		creditCardsTable.modifyFieldValueByOperator("Balance", currency, modificationOperator, " Id = " + creditCardId);
	}
	
}
