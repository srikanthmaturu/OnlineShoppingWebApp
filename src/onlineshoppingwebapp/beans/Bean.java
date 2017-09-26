package onlineshoppingwebapp.beans;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.apache.commons.collections4.BidiMap;

import onlineshoppingwebapp.auth.beans.RegistrationBean;
import onlineshoppingwebapp.logger.LogPrinter;
import onlineshoppingwebapp.mysqlhelpers.DBO;
import onlineshoppingwebapp.translators.BeanDBOConvertor;

public abstract class Bean {
	
	public HashMap<String, Field> fieldsMap;
	
	public void intializeFieldsMap(){
		Field [] beanFields = getClass().getDeclaredFields();
		fieldsMap =  new HashMap<String, Field>();
		for(int i = 0; i < beanFields.length; i++){
			fieldsMap.put(beanFields[i].getName(), beanFields[i]);
		}
	}
	
	public void setFieldValue(String fieldName, String fieldValue){
		if(fieldsMap.containsKey(fieldName)){
			try {
				fieldsMap.get(fieldName).set(this, fieldValue);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void displayBeanFields(){
		Field [] beanFields = getClass().getDeclaredFields();
		
		for(int i = 0; i < beanFields.length; i++){
			try {
				if(beanFields[i].isAccessible()){
					LogPrinter.printLog("Field : " + beanFields[i].getName() + "  Value = " + (String)(beanFields[i].get(this)));
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void convertToDBO(Bean bean, BidiMap<String, String> beanMap, DBO dbo){
		BeanDBOConvertor.convert(bean, bean.getClass(), dbo, dbo.getClass(), beanMap);
	}
	
}

