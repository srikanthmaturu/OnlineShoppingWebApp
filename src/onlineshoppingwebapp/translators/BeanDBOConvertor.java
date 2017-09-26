package onlineshoppingwebapp.translators;

import java.lang.reflect.Field;

import org.apache.commons.collections4.BidiMap;

import onlineshoppingwebapp.mysqlhelpers.*;

public class BeanDBOConvertor {
	
	public static <T1, T2 extends DBO> void convert(T1 bean, Class<?> beanClass, T2 dbo, Class<?> dboClass, BidiMap<String, String> beanToDBOMap){
		Field [] beanFields = beanClass.getDeclaredFields();
		
		for(int i = 0; i < beanFields.length; i++){
			if(beanToDBOMap.containsKey(beanFields[i].getName())){
				try {
					//System.out.println("BeanFieldName:" + beanFields[i].getName() +  " Mapped DBO Name: " + beanToDBOMap.get(beanFields[i].getName()));
					dbo.setFieldValue(beanToDBOMap.get(beanFields[i].getName()), (String)(beanFields[i].get(bean)));
					
					//System.out.println( (String)(beanFields[i].get(bean)));
					//System.out.println(dbo.getFieldStringTypeValue(beanToDBOMap.get(beanFields[i].getName())));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}	
	}
}
