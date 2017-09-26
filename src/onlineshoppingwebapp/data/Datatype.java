/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineshoppingwebapp.data;

import onlineshoppingwebapp.logger.LogPrinter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;

import javafx.beans.binding.StringExpression;

/**
 *
 * @author Srikanth
 */
public class Datatype {
	
	public Map<Class<?>, Object> data = new HashMap<Class<?>, Object>();
    
    public boolean used = false;
    
    public String type = null;
    public Class<?> typeclass;
    
    public Datatype() {
    }

    public void setType(String type) throws ClassNotFoundException {
    	this.type = type;
    	typeclass = Class.forName(type);
    }
    
    public String getType() {
    	if(type == null){
    		return "none";
    	}
    	return type;
    }

    public Class<?> getClassType() {
    	if(type == null){
    		return null;
    	}
    	return typeclass;
    }
    
    public void setValue(String value){
        used = false;
        switch(type)
        {
            case "java.lang.Integer":
            	//System.out.println(value);
            	if(value != null){
            		data.put(typeclass, Integer.parseInt(value));
            	}
                break;
            case "java.lang.Double":
            	if(value != null){
            		data.put(typeclass, Double.parseDouble(value));
            	}
                break;
            case "java.lang.String":
            	data.put(typeclass, value);
                break;
            case "java.lang.Boolean": 
            	Boolean bool = Boolean.parseBoolean(value);
                //LogPrinter.printLog("received value "+value);
               // LogPrinter.printLog("stored value "+bool);
                if("0".equals(value)||"1".equals(value)){
                   // LogPrinter.printLog("Stored Appropriately");
                 if("0".equals(value)){
                     bool = false;
                 }
                 else {
                     bool = true;
                 }
                }
                data.put(typeclass, bool);
                break;
            case "java.util.Date":
            	data.put(typeclass, Date.valueOf(value));
                break;
            case "java.lang.Long":
            	data.put(typeclass, Long.parseLong(value));
                break;
            default:
                used=true;
                System.out.println("Not Initialized");
        }
        used=!used;
        
    }
    
    public Object getValue(){
    	if(type != null && used){
    		return data.get(typeclass);
    	}
    	return null;
    }
    
    public String getStringValue() {
    	if(used){
    		return String.valueOf(typeclass.cast(data.get(typeclass)));
    	}
    	return null;
    }
    
    public String getSQLStringValue() {
    	if(used){
    		Object o = data.get(typeclass);
    		if(type == "java.lang.String"){
    			return String.valueOf("\""+StringEscapeUtils.escapeJava((String) typeclass.cast(o))+"\"");
    		}
    		else if(type == "java.util.Date"){
    			java.sql.Date date = java.sql.Date.valueOf(((java.util.Date)getValue()).toString());
    			return String.valueOf("\""+date.toString()+"\"");
    		}
    		else{
    			return String.valueOf(typeclass.cast(o));
    		}
    	}
     	return null;
    }
    
}
