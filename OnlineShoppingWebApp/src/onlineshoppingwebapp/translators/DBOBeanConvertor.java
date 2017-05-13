package onlineshoppingwebapp.translators;

import java.lang.reflect.Field;

import org.apache.commons.collections4.BidiMap;

import onlineshoppingwebapp.logger.LogPrinter;
import onlineshoppingwebapp.mysqlhelpers.DBO;
import onlineshoppingwebapp.mysqlhelpers.DBOConfig;

public class DBOBeanConvertor {
	
	public static <T1 extends DBO, T2> void convert(T1 dbo, Class<?> dboClass, DBOConfig dboConfig, T2 bean, Class<?> beanClass, BidiMap<String, String> beanToDBOMap){
		//Class<T1> dboClass = (Class<T1>) udboClass;
		//Class<T2> beanClass = (Class<T2>) ubeanClass;
		Field [] beanFields = beanClass.getDeclaredFields();
		
		for(int i = 0; i < beanFields.length; i++){
			if(beanToDBOMap.containsKey(beanFields[i].getName())){
				String dboFieldName = beanToDBOMap.get(beanFields[i].getName());
				try {	
					if(dbo.selected[dboConfig.map.get(dboFieldName)]){
						beanFields[i].set(bean, dbo.getFieldStringTypeValue(dboFieldName));
						//LogPrinter.printLog(" BeanFieldName: " + beanFields[i].getName() + " Value: " + (String)(beanFields[i].get(bean)));
					}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

